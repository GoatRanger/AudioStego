package com.here.odnp.cell;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.odnp.cell.ICellManager.ICellListener;
import com.here.odnp.config.OdnpConfigStatic;
import com.here.posclient.CellMeasurement;
import com.here.posclient.CellMeasurement.RANType;
import com.here.posclient.CellularStatus;
import java.util.Arrays;

public class PlatformCellManager implements ICellManager {
    private static final int[] INVALID_LACS = new int[]{65534};
    private static final int MAX_BSID = 65535;
    private static final int MAX_CID = 2147483646;
    private static final int MAX_LAC = 65535;
    private static final int MAX_NID = 65535;
    private static final int MAX_SID = 32767;
    private static final int MIN_BSID = 0;
    private static final int MIN_CID = 1;
    private static final int MIN_LAC = 1;
    private static final int MIN_NID = 0;
    private static final int MIN_SID = 0;
    private static final String TAG = "odnp.cell.PlatformCellManager";
    private final Runnable mCellScanTask = new Runnable() {
        private Hook mScanHook;

        {
            if (VERSION.SDK_INT >= 17) {
                this.mScanHook = PlatformCellManager.this.mNullHook;
            } else {
                this.mScanHook = PlatformCellManager.this.mNullHook;
            }
        }

        public void run() {
            this.mScanHook = this.mScanHook.runHook();
            PlatformCellManager.this.pushCellularStatus(PlatformCellManager.this.mStoredServiceState);
            if (!PlatformCellManager.this.pushCellLocation(PlatformCellManager.this.mTelephonyManager.getCellLocation(), false)) {
                synchronized (PlatformCellManager.this) {
                    if (PlatformCellManager.this.mListener != null) {
                        PlatformCellManager.this.mListener.onCellScanFailed();
                    }
                }
            }
        }
    };
    private final CellTracker mCellTracker = new CellTracker();
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private long mCurrentCellularStatus;
    private final DchTracker mDchTracker = new DchTracker();
    private final Handler mHandler = new Handler();
    private volatile ICellListener mListener;
    private final Hook mNullHook = new Hook() {
        public Hook runHook() {
            return this;
        }
    };
    private final PhoneStateListener mPhoneStateListener = new PhoneStateListener() {
        private Hook mCellChangeHook;

        {
            if (VERSION.SDK_INT >= 17) {
                this.mCellChangeHook = PlatformCellManager.this.mNullHook;
            } else {
                this.mCellChangeHook = PlatformCellManager.this.mNullHook;
            }
        }

        public void onCallStateChanged(int i, String str) {
            PlatformCellManager.this.updateCallState(i);
        }

        public void onDataActivity(int i) {
            PlatformCellManager.this.updateDataActivityState(i);
        }

        public void onServiceStateChanged(ServiceState serviceState) {
            PlatformCellManager.this.pushCellularStatus(serviceState);
            PlatformCellManager.this.pushCellLocation(PlatformCellManager.this.mTelephonyManager.getCellLocation(), true);
        }

        public void onCellLocationChanged(CellLocation cellLocation) {
            this.mCellChangeHook = this.mCellChangeHook.runHook();
            PlatformCellManager.this.pushCellLocation(cellLocation, true);
        }
    };
    private final BroadcastReceiver mScreenStateReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                PlatformCellManager.this.onScreenOff();
            } else if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                PlatformCellManager.this.onScreenOn();
            }
        }
    };
    private ServiceState mStoredServiceState;
    private final TelephonyManager mTelephonyManager;

    interface Hook {
        Hook runHook();
    }

    private static class CellTracker {
        long mLastAccept;
        CellMeasurement mLastCell;

        private CellTracker() {
            this.mLastAccept = Long.MIN_VALUE;
        }

        boolean update(CellMeasurement cellMeasurement, boolean z) {
            if (cellMeasurement == null) {
                reset();
                return false;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (!cellMeasurement.isEqual(this.mLastCell) || !z) {
                set(cellMeasurement, elapsedRealtime);
                return true;
            } else if (elapsedRealtime - this.mLastAccept < OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME) {
                return false;
            } else {
                set(cellMeasurement, elapsedRealtime);
                return true;
            }
        }

        private void set(CellMeasurement cellMeasurement, long j) {
            this.mLastCell = cellMeasurement;
            this.mLastAccept = j;
        }

        void reset() {
            set(null, Long.MIN_VALUE);
        }
    }

    static class DchTracker {
        final Tracker mCallTracker = new Tracker("Call.DCH");
        final Tracker mDataTracker = new Tracker("Data.DCH");
        private volatile boolean mScreenOn = false;

        private static class Tracker {
            Long mStarted;
            final String mTrackerName = new String();

            Tracker(String str) {
            }

            synchronized void startTracking() {
                if (!isActive()) {
                    this.mStarted = Long.valueOf(SystemClock.elapsedRealtime());
                }
            }

            synchronized void stopTracking() {
                this.mStarted = null;
            }

            synchronized boolean isActive() {
                return this.mStarted != null;
            }
        }

        DchTracker() {
        }

        void reset() {
            this.mCallTracker.stopTracking();
            this.mDataTracker.stopTracking();
            this.mScreenOn = false;
        }

        boolean isDchModeOn() {
            return this.mCallTracker.isActive() || this.mDataTracker.isActive() || !this.mScreenOn;
        }

        void startCallTracking() {
            this.mCallTracker.startTracking();
        }

        void stopCallTracking() {
            this.mCallTracker.stopTracking();
        }

        void startDataTracking() {
            this.mDataTracker.startTracking();
        }

        void stopDataTracking() {
            this.mDataTracker.stopTracking();
        }

        void setScreen(boolean z) {
            this.mScreenOn = z;
        }
    }

    @SuppressLint({"NewApi"})
    private class GetAllCellInfoHook implements Hook {
        private GetAllCellInfoHook() {
        }

        public Hook runHook() {
            if (PlatformCellManager.this.mTelephonyManager.getAllCellInfo() == null) {
                return PlatformCellManager.this.mNullHook;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (CellInfo cellInfo : PlatformCellManager.this.mTelephonyManager.getAllCellInfo()) {
                if (cellInfo != null) {
                    stringBuilder.append(cellInfo.toString()).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                }
            }
            return this;
        }
    }

    enum NetworkType {
        UNKNOWN,
        GERAN,
        UTRAN,
        EUTRAN,
        CMDA
    }

    public PlatformCellManager(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        this.mContext = context;
        this.mTelephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        this.mConnectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
    }

    public synchronized void open(ICellListener iCellListener) {
        close();
        if (iCellListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.mListener = iCellListener;
        this.mTelephonyManager.listen(this.mPhoneStateListener, 177);
        registerScreenEvents();
        updateCallState(this.mTelephonyManager.getCallState());
        updateDataActivityState(this.mTelephonyManager.getDataActivity());
    }

    public synchronized void close() {
        if (this.mListener != null) {
            cancelCellScan();
            unregisterScreenEvents();
            this.mTelephonyManager.listen(this.mPhoneStateListener, 0);
            this.mListener = null;
            this.mCurrentCellularStatus = 0;
            this.mStoredServiceState = null;
            this.mDchTracker.reset();
            this.mCellTracker.reset();
        }
    }

    public boolean startCellScan() {
        cancelCellScan();
        return isCellSupported() && this.mHandler.post(this.mCellScanTask);
    }

    public void cancelCellScan() {
        this.mHandler.removeCallbacks(this.mCellScanTask);
    }

    public boolean isCellSupported() {
        return true;
    }

    private boolean pushCellLocation(CellLocation cellLocation, boolean z) {
        if (cellLocation != null && (cellLocation instanceof GsmCellLocation)) {
            return pushGsmCellLocation((GsmCellLocation) cellLocation, z);
        }
        return false;
    }

    private synchronized boolean pushGsmCellLocation(GsmCellLocation gsmCellLocation, boolean z) {
        boolean z2 = false;
        synchronized (this) {
            if (this.mListener != null) {
                CellMeasurement gsmCellMeasurement = getGsmCellMeasurement(gsmCellLocation);
                if (gsmCellMeasurement != null && this.mCellTracker.update(gsmCellMeasurement, z)) {
                    this.mListener.onCellMeasurementChanged(gsmCellMeasurement);
                    z2 = true;
                }
            }
        }
        return z2;
    }

    private synchronized boolean pushCdmaCellLocation(CdmaCellLocation cdmaCellLocation, boolean z) {
        boolean z2 = false;
        synchronized (this) {
            if (this.mListener != null) {
                CellMeasurement cdmaCellMeasurement = getCdmaCellMeasurement(cdmaCellLocation);
                if (cdmaCellMeasurement != null && this.mCellTracker.update(cdmaCellMeasurement, z)) {
                    this.mListener.onCellMeasurementChanged(cdmaCellMeasurement);
                    z2 = true;
                }
            }
        }
        return z2;
    }

    private synchronized void pushCellularStatus(ServiceState serviceState) {
        this.mStoredServiceState = serviceState;
        if (serviceState != null) {
            CellularStatus cellularStatus = new CellularStatus();
            cellularStatus.interfaceIndex = 0;
            if (serviceState.getState() != 0) {
                cellularStatus.status = 1;
            } else if (serviceState.getRoaming()) {
                cellularStatus.status = 4;
            } else {
                cellularStatus.status = 3;
            }
            if (this.mDchTracker.isDchModeOn()) {
                cellularStatus.status |= 4096;
            }
            if (this.mCurrentCellularStatus != cellularStatus.status) {
                this.mCurrentCellularStatus = cellularStatus.status;
                if (this.mListener != null) {
                    this.mListener.onCellularStatusChanged(cellularStatus);
                }
            }
        }
    }

    private CellMeasurement getGsmCellMeasurement(GsmCellLocation gsmCellLocation) {
        CellMeasurement cellMeasurement = new CellMeasurement();
        switch (getNetworkType()) {
            case EUTRAN:
                cellMeasurement.type = RANType.EUTRA;
                break;
            case UTRAN:
                cellMeasurement.type = RANType.UTRAFDD;
                break;
            case GERAN:
                cellMeasurement.type = RANType.GERAN;
                break;
            default:
                return null;
        }
        cellMeasurement.timeStamp = System.currentTimeMillis() / 1000;
        String networkOperator = this.mTelephonyManager.getNetworkOperator();
        if (networkOperator != null && networkOperator.length() >= 5 && networkOperator.length() <= 6) {
            cellMeasurement.gciL1Value = Integer.parseInt(networkOperator.substring(0, 3));
            cellMeasurement.gciL2Value = Integer.parseInt(networkOperator.substring(3));
        }
        if (cellMeasurement.gciL1Value == 0) {
            return null;
        }
        cellMeasurement.gciL3Value = gsmCellLocation.getLac();
        if (cellMeasurement.gciL3Value != -1 && cellMeasurement.gciL3Value >= 1 && cellMeasurement.gciL3Value <= 65535 && (cellMeasurement.type == RANType.EUTRA || Arrays.binarySearch(INVALID_LACS, cellMeasurement.gciL3Value) == -1)) {
            cellMeasurement.hasGciL3Value = true;
        }
        if (!cellMeasurement.hasGciL3Value && cellMeasurement.type == RANType.GERAN) {
            return null;
        }
        cellMeasurement.gciL4Value = gsmCellLocation.getCid();
        if (cellMeasurement.gciL4Value < 1 || cellMeasurement.gciL4Value > MAX_CID) {
            return null;
        }
        cellMeasurement.hasGciL4Value = true;
        cellMeasurement.simulated = false;
        return cellMeasurement;
    }

    private CellMeasurement getCdmaCellMeasurement(CdmaCellLocation cdmaCellLocation) {
        CellMeasurement cellMeasurement = new CellMeasurement();
        switch (getNetworkType()) {
            case CMDA:
                cellMeasurement.type = RANType.CDMA;
                cellMeasurement.timeStamp = System.currentTimeMillis() / 1000;
                cellMeasurement.gciL1Value = cdmaCellLocation.getSystemId();
                if (cellMeasurement.gciL1Value < 0 || cellMeasurement.gciL1Value > MAX_SID) {
                    return null;
                }
                cellMeasurement.gciL2Value = cdmaCellLocation.getNetworkId();
                if (cellMeasurement.gciL2Value < 0 || cellMeasurement.gciL2Value > 65535) {
                    return null;
                }
                cellMeasurement.gciL4Value = cdmaCellLocation.getBaseStationId();
                if (cellMeasurement.gciL4Value < 0 || cellMeasurement.gciL4Value > 65535) {
                    return null;
                }
                cellMeasurement.hasGciL4Value = true;
                cellMeasurement.simulated = false;
                return cellMeasurement;
            default:
                return null;
        }
    }

    private boolean isMobileNetworkActive() {
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
    }

    private boolean isCallActive() {
        return isCallActive(this.mTelephonyManager.getCallState());
    }

    private static boolean isCallActive(int i) {
        switch (i) {
            case 1:
            case 2:
                return true;
            default:
                return false;
        }
    }

    private boolean isDataActive() {
        return isDataActive(this.mTelephonyManager.getDataActivity());
    }

    private static boolean isDataActive(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
                return true;
            default:
                return false;
        }
    }

    private NetworkType getNetworkType() {
        int phoneType = this.mTelephonyManager.getPhoneType();
        int networkType = this.mTelephonyManager.getNetworkType();
        if (phoneType == 1 || phoneType == 0) {
            switch (networkType) {
                case 1:
                case 2:
                    return NetworkType.GERAN;
                case 3:
                case 8:
                case 9:
                case 10:
                case 15:
                    return NetworkType.UTRAN;
                case 13:
                    return NetworkType.EUTRAN;
            }
        }
        if (phoneType == 2 || phoneType == 0) {
            switch (networkType) {
                case 4:
                    return NetworkType.CMDA;
            }
        }
        return NetworkType.UNKNOWN;
    }

    private void updateCallState(int i) {
        boolean isDchModeOn = this.mDchTracker.isDchModeOn();
        if (isCallActive() && getNetworkType() == NetworkType.UTRAN) {
            this.mDchTracker.startCallTracking();
        } else {
            this.mDchTracker.stopCallTracking();
        }
        if (isDchModeOn != this.mDchTracker.isDchModeOn()) {
            pushCellularStatus(this.mStoredServiceState);
        }
    }

    private void updateDataActivityState(int i) {
        boolean isDchModeOn = this.mDchTracker.isDchModeOn();
        if (isMobileNetworkActive() && isDataActive() && getNetworkType() == NetworkType.UTRAN) {
            this.mDchTracker.startDataTracking();
        } else {
            this.mDchTracker.stopDataTracking();
        }
        if (isDchModeOn != this.mDchTracker.isDchModeOn()) {
            pushCellularStatus(this.mStoredServiceState);
        }
    }

    private void onScreenOn() {
        boolean isDchModeOn = this.mDchTracker.isDchModeOn();
        this.mDchTracker.setScreen(true);
        if (isDchModeOn != this.mDchTracker.isDchModeOn()) {
            pushCellularStatus(this.mStoredServiceState);
        }
    }

    private void onScreenOff() {
        boolean isDchModeOn = this.mDchTracker.isDchModeOn();
        this.mDchTracker.setScreen(false);
        if (isDchModeOn != this.mDchTracker.isDchModeOn()) {
            pushCellularStatus(this.mStoredServiceState);
        }
    }

    @TargetApi(20)
    private void registerScreenEvents() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        this.mContext.registerReceiver(this.mScreenStateReceiver, intentFilter);
        PowerManager powerManager = (PowerManager) this.mContext.getSystemService("power");
        if (VERSION.SDK_INT >= 20) {
            this.mDchTracker.setScreen(powerManager.isInteractive());
        } else {
            this.mDchTracker.setScreen(powerManager.isScreenOn());
        }
    }

    private void unregisterScreenEvents() {
        this.mContext.unregisterReceiver(this.mScreenStateReceiver);
    }
}
