package com.here.odnp.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import com.here.odnp.util.TimeManager;
import com.here.posclient.BleMeasurement;
import com.here.posclient.Status;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

abstract class BleScanner {
    protected static final byte COMPLETE_LIST_OF_16_BIT_SERVICE_UUIDS_LENGTH_NOKIA_V2 = (byte) 3;
    protected static final byte COMPLETE_LIST_OF_16_BIT_SERVICE_UUIDS_TYPE = (byte) 3;
    private static final int MAX_SCAN_RETRY_COUNT = 1;
    protected static final String NOKIA_LOCATION_SERVICE_UUID_128_BIT = "0000FEAE-0000-1000-8000-00805F9B34FB";
    protected static final int NOKIA_LOCATION_SERVICE_UUID_16_BIT_LSB = 174;
    protected static final int NOKIA_LOCATION_SERVICE_UUID_16_BIT_MSB = 254;
    private static final long SCAN_RETRY_DELAY = TimeUnit.SECONDS.toMillis(1);
    private static final String TAG = "odnp.ble.BleScanner";
    private final Map<String, BleScanResult> mBleScanResults;
    protected final BluetoothAdapter mBluetoothAdapter;
    private final BroadcastReceiver mBluetoothStateChangeReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE)) {
                    case 10:
                        BleScanner.this.mHandler.postDelayed(new Runnable() {
                            public void run() {
                                BleScanner.this.scanFailed(Status.NotEnabledError);
                            }
                        }, 50);
                        return;
                    default:
                        return;
                }
            }
        }
    };
    protected final Context mContext;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    protected final IListener mListener;
    private final Runnable mScanResultReportTask = new Runnable() {
        public void run() {
            if (BleScanner.this.mScanState == ScanState.RUNNING) {
                try {
                    BleScanner.this.mListener.onScanResultsAvailable(BleScanner.this.getAndClearScanResults());
                } finally {
                    BleScanner.this.restartScanTimer();
                }
            }
        }
    };
    private int mScanRetryCount;
    private final Runnable mScanRetryTask = new Runnable() {
        public void run() {
            if (BleScanner.this.mScanState == ScanState.WAITING_RESTART) {
                BleScanner.this.mScanState = ScanState.STOPPED;
                Status access$300 = BleScanner.this.startScan(true);
                if (access$300 != Status.Ok || BleScanner.this.mScanState == ScanState.START_FAILED) {
                    BleScanner.this.mScanState = ScanState.START_FAILED;
                    BleScanner.this.scanFailed(access$300);
                }
            }
        }
    };
    private volatile ScanState mScanState = ScanState.STOPPED;

    interface IListener {
        void onScanFailed(Status status);

        void onScanResultsAvailable(List<BleScanResult> list);
    }

    private enum ScanState {
        STOPPED,
        RUNNING,
        WAITING_RESTART,
        START_FAILED
    }

    protected abstract boolean doStartScan();

    protected abstract void doStopScan();

    static BleScanner create(Context context, IListener iListener) {
        if (VERSION.SDK_INT < 18 || !context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            return null;
        }
        if (VERSION.SDK_INT >= 21) {
            try {
                return new BleScannerApi21(context, iListener);
            } catch (Exception e) {
                return null;
            }
        }
        try {
            return new BleScannerPreApi21(context, iListener);
        } catch (Exception e2) {
            return null;
        }
    }

    protected BleScanner(Context context, IListener iListener) {
        this.mContext = context;
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (this.mBluetoothAdapter == null) {
            throw new NullPointerException("mBluetoothAdapter is null");
        }
        this.mListener = iListener;
        this.mBleScanResults = new LinkedHashMap();
    }

    Status startScan() {
        Status startScan = startScan(false);
        if (startScan == Status.Ok || isFatalError(startScan)) {
            return startScan;
        }
        this.mScanState = ScanState.START_FAILED;
        scanFailed(startScan);
        return Status.Ok;
    }

    private Status startScan(boolean z) {
        if (z) {
            this.mScanRetryCount++;
        } else {
            this.mScanRetryCount = 0;
        }
        if (this.mBluetoothAdapter == null) {
            this.mScanState = ScanState.STOPPED;
            return Status.NotSupportedError;
        } else if (this.mBluetoothAdapter.isEnabled()) {
            if (this.mScanState != ScanState.RUNNING) {
                clearScanResults();
                if (doStartScan()) {
                    this.mScanState = ScanState.RUNNING;
                    restartScanTimer();
                    this.mContext.registerReceiver(this.mBluetoothStateChangeReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
                } else {
                    this.mScanState = ScanState.STOPPED;
                }
            }
            if (this.mScanState == ScanState.RUNNING) {
                return Status.Ok;
            }
            return Status.GeneralError;
        } else {
            this.mScanState = ScanState.STOPPED;
            return Status.NotEnabledError;
        }
    }

    void stopScan() {
        switch (this.mScanState) {
            case RUNNING:
                try {
                    doStopScan();
                    break;
                } catch (Exception e) {
                    break;
                }
            case WAITING_RESTART:
                break;
            case START_FAILED:
                this.mScanState = ScanState.STOPPED;
                break;
            case STOPPED:
                break;
            default:
                this.mScanState = ScanState.STOPPED;
                break;
        }
        this.mHandler.removeCallbacks(this.mScanResultReportTask);
        this.mHandler.removeCallbacks(this.mScanRetryTask);
        this.mContext.unregisterReceiver(this.mBluetoothStateChangeReceiver);
        this.mScanState = ScanState.STOPPED;
        clearScanResults();
    }

    protected void scanFailed(Status status) {
        if (this.mScanState == ScanState.RUNNING || this.mScanState == ScanState.START_FAILED) {
            stopScan();
            if (this.mScanRetryCount >= 1 || isFatalError(status)) {
                this.mListener.onScanFailed(status);
            } else {
                restartScanRetryTimer();
            }
        }
    }

    protected void addScanResult(BluetoothDevice bluetoothDevice, int i) {
        addScanResult(bluetoothDevice, i, 0);
    }

    protected void addScanResult(BluetoothDevice bluetoothDevice, int i, long j) {
        BleScanResult bleScanResult = new BleScanResult();
        if (j > 0) {
            bleScanResult.elapsedRealtimeTimestamp = j;
        } else {
            bleScanResult.elapsedRealtimeTimestamp = TimeManager.timeSinceBoot();
        }
        bleScanResult.timestamp = TimeManager.currentTimeMillis() / 1000;
        bleScanResult.rssi = i;
        bleScanResult.deviceAddress = BleMeasurement.toMac64(bluetoothDevice.getAddress());
        bleScanResult.deviceName = bluetoothDevice.getName();
        if (bleScanResult.deviceName == null) {
            bleScanResult.deviceName = "Unknown device";
        }
        synchronized (this) {
            this.mBleScanResults.put(bleScanResult.deviceAddress, bleScanResult);
        }
    }

    private synchronized List<BleScanResult> getAndClearScanResults() {
        List arrayList;
        arrayList = new ArrayList(this.mBleScanResults.values());
        clearScanResults();
        return arrayList;
    }

    private synchronized void clearScanResults() {
        this.mBleScanResults.clear();
    }

    private void restartScanTimer() {
        restartTimer(this.mScanResultReportTask, 550);
    }

    private void restartScanRetryTimer() {
        restartTimer(this.mScanRetryTask, SCAN_RETRY_DELAY);
        this.mScanState = ScanState.WAITING_RESTART;
    }

    private void restartTimer(Runnable runnable, long j) {
        this.mHandler.removeCallbacks(runnable);
        this.mHandler.postDelayed(runnable, j);
    }

    private boolean isFatalError(Status status) {
        return status == Status.NotSupportedError || status == Status.NotEnabledError;
    }
}
