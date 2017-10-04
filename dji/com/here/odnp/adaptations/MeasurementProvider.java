package com.here.odnp.adaptations;

import android.location.Location;
import android.os.Build.VERSION;
import com.here.odnp.ble.IBleManager;
import com.here.odnp.ble.IBleManager.BleScanResultContainer;
import com.here.odnp.ble.IBleManager.IBleListener;
import com.here.odnp.cell.ICellManager;
import com.here.odnp.cell.ICellManager.ICellListener;
import com.here.odnp.gnss.IGnssManager;
import com.here.odnp.gnss.IGnssManager.IGnnsListener;
import com.here.odnp.wifi.IWifiFilter;
import com.here.odnp.wifi.IWifiManager;
import com.here.odnp.wifi.IWifiManager.IWifiListener;
import com.here.odnp.wifi.IWifiManager.WifiScanResultContainer;
import com.here.odnp.wifi.WifiFilterTimestamp;
import com.here.posclient.BleMeasurement;
import com.here.posclient.CellMeasurement;
import com.here.posclient.CellularStatus;
import com.here.posclient.GnssStatus;
import com.here.posclient.IMeasurementProvider;
import com.here.posclient.MeasurementType;
import com.here.posclient.PositionEstimate;
import com.here.posclient.Status;
import com.here.posclient.WifiMeasurement;
import com.here.posclient.WifiStatus;
import java.util.List;

public class MeasurementProvider implements IMeasurementProvider {
    private static final String TAG = "odnp.adaptations.MeasurementProvider";
    private final IBleListener mBleListener = new IBleListener() {
        public void onScanResultsAvailable(BleScanResultContainer bleScanResultContainer) {
            MeasurementProvider.this.mMeasResultHandler.handleBleScanResult(bleScanResultContainer.measurementId, (BleMeasurement[]) bleScanResultContainer.scanResultList.toArray(new BleMeasurement[bleScanResultContainer.scanResultList.size()]), bleScanResultContainer.simulated);
        }

        public void onScanFailed(Status status) {
            MeasurementProvider.this.mMeasResultHandler.handleRequestError(MeasurementType.MSMTYPE_BLE, status);
        }
    };
    private IBleManager mBleManager;
    private final ICellListener mCellListener = new ICellListener() {
        public void onCellMeasurementChanged(CellMeasurement cellMeasurement) {
            MeasurementProvider.this.mMeasResultHandler.handleCellularScanResult(cellMeasurement, cellMeasurement.simulated);
        }

        public void onCellularStatusChanged(CellularStatus cellularStatus) {
            MeasurementProvider.this.mMeasResultHandler.handleCellularStatusChange(cellularStatus);
        }

        public void onCellScanFailed() {
            MeasurementProvider.this.mMeasResultHandler.handleRequestError(MeasurementType.MSMTYPE_CELLULAR, Status.GeneralError);
        }
    };
    private ICellManager mCellManager;
    private final IGnnsListener mGnssListener = new IGnnsListener() {
        public void onGnssLocationChanged(Location location, boolean z) {
            MeasurementProvider.this.mMeasResultHandler.handleGnssLocationUpdate(PositionEstimate.fromAndroidLocation(location), z);
        }

        public void onGnssStatusChanged(GnssStatus gnssStatus) {
            MeasurementProvider.this.mMeasResultHandler.handleGnssStatus(gnssStatus);
        }
    };
    private IGnssManager mGnssManager;
    private final IMeasurementResultHandler mMeasResultHandler;
    private boolean mStartBleCalled;
    private boolean mStartGnssCalled;
    private IWifiFilter mWifiFilter;
    private final IWifiListener mWifiListener = new IWifiListener() {
        public void onScanResultsAvailable(WifiScanResultContainer wifiScanResultContainer) {
            if (MeasurementProvider.this.mWifiFilter != null) {
                MeasurementProvider.this.mWifiFilter.updateMeasurements(wifiScanResultContainer.scanResultList);
                MeasurementProvider.this.mMeasResultHandler.handleWifiScanResult(wifiScanResultContainer.measurementId, MeasurementProvider.this.mWifiFilter.getFilteredMeasurements(), false, wifiScanResultContainer.simulated);
            }
        }

        public void onWifiStateChanged(WifiStatus wifiStatus) {
            MeasurementProvider.this.mMeasResultHandler.handleWifiStateChanged(wifiStatus);
        }

        public void onWifiScanFailed() {
            MeasurementProvider.this.mMeasResultHandler.handleRequestError(MeasurementType.MSMTYPE_WLAN, Status.GeneralError);
        }
    };
    private IWifiManager mWifiManager;

    public MeasurementProvider(IMeasurementResultHandler iMeasurementResultHandler) {
        this.mMeasResultHandler = iMeasurementResultHandler;
    }

    public void close() {
        unsubscribe(MeasurementType.MSMTYPE_ALL);
    }

    public ICellManager getCellManager() {
        return this.mCellManager;
    }

    public MeasurementProvider setCellManager(ICellManager iCellManager) {
        return setCellManager(iCellManager, false);
    }

    public MeasurementProvider setCellManager(ICellManager iCellManager, boolean z) {
        unregisterCellScanResults();
        if (iCellManager == null) {
            throw new IllegalArgumentException("cellManager is null");
        }
        this.mCellManager = iCellManager;
        if (z) {
            registerCellScanResults();
        }
        return this;
    }

    public IGnssManager getGnssManager() {
        return this.mGnssManager;
    }

    public MeasurementProvider setGnssManager(IGnssManager iGnssManager) {
        return setGnssManager(iGnssManager, false);
    }

    public MeasurementProvider setGnssManager(IGnssManager iGnssManager, boolean z) {
        unregisterGnssUpdates();
        if (iGnssManager == null) {
            throw new IllegalArgumentException("gnssManager is null");
        }
        this.mGnssManager = iGnssManager;
        if (z) {
            registerGnssUpdates();
        }
        return this;
    }

    public IWifiManager getWifiManager() {
        return this.mWifiManager;
    }

    public MeasurementProvider setWifiManager(IWifiManager iWifiManager) {
        return setWifiManager(iWifiManager, false);
    }

    public MeasurementProvider setWifiManager(IWifiManager iWifiManager, boolean z) {
        unregisterWifiScanResults();
        if (iWifiManager == null) {
            throw new IllegalArgumentException("wifiManager is null");
        }
        this.mWifiManager = iWifiManager;
        if (z) {
            registerWifiScanResults();
        }
        return this;
    }

    public MeasurementProvider setWifiFilter(IWifiFilter iWifiFilter) {
        if (iWifiFilter == null) {
            throw new IllegalArgumentException("wifiFilter is null");
        }
        this.mWifiFilter = iWifiFilter;
        reset();
        return this;
    }

    public MeasurementProvider setBleManager(IBleManager iBleManager) {
        return setBleManager(iBleManager, false);
    }

    public MeasurementProvider setBleManager(IBleManager iBleManager, boolean z) {
        unregisterBleScanResults();
        if (iBleManager == null) {
            throw new IllegalArgumentException("bleManager is null");
        }
        this.mBleManager = iBleManager;
        if (z) {
            registerBleScanResults();
        }
        return this;
    }

    public IBleManager getBleManager() {
        return this.mBleManager;
    }

    public void reset() {
        if (this.mWifiFilter == null) {
            return;
        }
        if (this.mWifiManager != null) {
            this.mWifiFilter.setInitialMeasurements(this.mWifiManager.getLastScanResult());
        } else {
            this.mWifiFilter.reset();
        }
    }

    public boolean startCellularScan() {
        if (this.mCellManager == null) {
            return false;
        }
        return this.mCellManager.startCellScan();
    }

    public void cancelCellularScan() {
        if (this.mCellManager != null) {
            this.mCellManager.cancelCellScan();
        }
    }

    public boolean startWifiScan() {
        if (this.mWifiManager != null) {
            return this.mWifiManager.startWifiScan();
        }
        return false;
    }

    public void cancelWifiScan() {
        if (this.mWifiManager != null) {
            this.mWifiManager.cancelWifiScan();
        }
    }

    public int requestCurrentWlanList() {
        if (this.mWifiManager == null) {
            return Status.GeneralError.toInt();
        }
        if (VERSION.SDK_INT < 17) {
            return Status.NotSupportedError.toInt();
        }
        List lastScanResult = this.mWifiManager.getLastScanResult();
        WifiFilterBase.filterDuplicates(lastScanResult);
        if (!WifiFilterTimestamp.updateTimestamps(lastScanResult)) {
            return Status.NotSupportedError.toInt();
        }
        this.mMeasResultHandler.handleWifiScanResult(0, (WifiMeasurement[]) lastScanResult.toArray(new WifiMeasurement[lastScanResult.size()]), true, false);
        return Status.Ok.toInt();
    }

    private void registerGnssUpdates() {
        if (this.mGnssManager != null) {
            this.mGnssManager.startListening(this.mGnssListener, 500);
            if (this.mStartGnssCalled) {
                this.mGnssManager.startGnss();
            }
        }
    }

    private void unregisterGnssUpdates() {
        if (this.mGnssManager != null) {
            this.mGnssManager.stopListening();
            if (this.mStartGnssCalled) {
                this.mGnssManager.stopGnss();
            }
        }
    }

    private void registerWifiScanResults() {
        if (this.mWifiManager != null) {
            if (this.mWifiFilter != null) {
                this.mWifiFilter.setInitialMeasurements(this.mWifiManager.getLastScanResult());
            }
            this.mWifiManager.open(this.mWifiListener);
        }
    }

    private void unregisterWifiScanResults() {
        if (this.mWifiManager != null) {
            this.mWifiManager.close();
        }
    }

    private void registerCellScanResults() {
        if (this.mCellManager != null) {
            this.mCellManager.open(this.mCellListener);
        }
    }

    private void unregisterCellScanResults() {
        if (this.mCellManager != null) {
            this.mCellManager.close();
        }
    }

    private Status registerBleScanResults() {
        Status status = Status.InternalError;
        if (this.mBleManager != null) {
            status = this.mBleManager.open(this.mBleListener);
            if (this.mStartBleCalled) {
                this.mBleManager.startBleScan();
            }
        }
        return status;
    }

    private void unregisterBleScanResults() {
        if (this.mBleManager != null) {
            this.mBleManager.close();
            if (this.mStartBleCalled) {
                this.mBleManager.stopBleScan();
            }
        }
    }

    public int subscribe(int i) {
        int toInt = Status.Ok.toInt();
        switch (MeasurementType.fromInt(i)) {
            case MSMTYPE_CELLULAR:
                registerCellScanResults();
                return toInt;
            case MSMTYPE_WLAN:
                registerWifiScanResults();
                return toInt;
            case MSMTYPE_GNSS:
                registerGnssUpdates();
                return toInt;
            case MSMTYPE_BLE:
                return registerBleScanResults().toInt();
            case MSMTYPE_ALL:
                registerCellScanResults();
                registerWifiScanResults();
                registerGnssUpdates();
                registerBleScanResults();
                return toInt;
            default:
                return Status.InvalidArgumentError.toInt();
        }
    }

    public void unsubscribe(int i) {
        unsubscribe(MeasurementType.fromInt(i));
    }

    private void unsubscribe(MeasurementType measurementType) {
        switch (measurementType) {
            case MSMTYPE_CELLULAR:
                unregisterCellScanResults();
                return;
            case MSMTYPE_WLAN:
                unregisterWifiScanResults();
                return;
            case MSMTYPE_GNSS:
                unregisterGnssUpdates();
                return;
            case MSMTYPE_BLE:
                unregisterBleScanResults();
                return;
            case MSMTYPE_ALL:
                unregisterCellScanResults();
                unregisterWifiScanResults();
                unregisterGnssUpdates();
                unregisterBleScanResults();
                return;
            default:
                return;
        }
    }

    public int startGnss() {
        this.mStartGnssCalled = true;
        if (this.mGnssManager == null) {
            return Status.GeneralError.toInt();
        }
        Status startGnss = this.mGnssManager.startGnss();
        if (startGnss == null) {
            return Status.GeneralError.toInt();
        }
        return startGnss.toInt();
    }

    public void stopGnss() {
        this.mStartGnssCalled = false;
        if (this.mGnssManager != null) {
            this.mGnssManager.stopGnss();
        }
    }

    public int startBle() {
        this.mStartBleCalled = true;
        Status status = Status.InternalError;
        if (this.mBleManager != null) {
            status = this.mBleManager.startBleScan();
        }
        return status.toInt();
    }

    public void stopBle() {
        this.mStartBleCalled = false;
        if (this.mBleManager != null) {
            this.mBleManager.stopBleScan();
        }
    }

    public int supportedMeasurementTypes() {
        int i = MeasurementType.MSMTYPE_UNKNOWN.value;
        if (isCellMeasurementSupported()) {
            i |= MeasurementType.MSMTYPE_CELLULAR.value;
        }
        if (isWifiMeasurementSupported()) {
            i = (i | MeasurementType.MSMTYPE_WLAN.value) | MeasurementType.MSMTYPE_CACHED_WLAN.value;
        }
        if (isGnssMeasurementSupported()) {
            i |= MeasurementType.MSMTYPE_GNSS.value;
        }
        if (isBleMeasurementSupported()) {
            return i | MeasurementType.MSMTYPE_BLE.value;
        }
        return i;
    }

    private boolean isCellMeasurementSupported() {
        return this.mCellManager != null && this.mCellManager.isCellSupported();
    }

    private boolean isWifiMeasurementSupported() {
        return this.mWifiManager != null && this.mWifiManager.isWifiSupported();
    }

    private boolean isGnssMeasurementSupported() {
        return this.mGnssManager != null && this.mGnssManager.isGnssSupported();
    }

    private boolean isBleMeasurementSupported() {
        return this.mBleManager != null && this.mBleManager.isBleSupported();
    }
}
