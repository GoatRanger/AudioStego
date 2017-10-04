package com.here.odnp.ble;

import android.annotation.TargetApi;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.bluetooth.le.ScanSettings.Builder;
import android.content.Context;
import com.here.posclient.Status;
import java.util.concurrent.TimeUnit;

@TargetApi(21)
public class BleScannerApi21 extends BleScanner {
    private static final String TAG = "odnp.ble.BleScannerApi21";
    private final ScanCallback mBleScanCallback = new ScanCallback() {
        public void onScanResult(int i, ScanResult scanResult) {
            if (BleValidator.isSupportedBleTag(scanResult.getScanRecord().getBytes())) {
                BleScannerApi21.this.addScanResult(scanResult.getDevice(), scanResult.getRssi(), TimeUnit.NANOSECONDS.toMillis(scanResult.getTimestampNanos()));
            }
        }

        public void onScanFailed(int i) {
            if (i != 1) {
                BleScannerApi21.this.scanFailed(Status.GeneralError);
            }
        }
    };
    private BluetoothLeScanner mBluetoothLeScanner;
    private final ScanSettings mSettings = new Builder().setScanMode(2).setReportDelay(0).build();

    public BleScannerApi21(Context context, IListener iListener) {
        super(context, iListener);
    }

    protected boolean doStartScan() {
        this.mBluetoothLeScanner = this.mBluetoothAdapter.getBluetoothLeScanner();
        if (this.mBluetoothLeScanner == null) {
            return false;
        }
        this.mBluetoothLeScanner.startScan(null, this.mSettings, this.mBleScanCallback);
        return true;
    }

    protected void doStopScan() {
        if (this.mBluetoothLeScanner != null) {
            if (this.mBluetoothAdapter.isEnabled()) {
                this.mBluetoothLeScanner.stopScan(this.mBleScanCallback);
            }
            this.mBluetoothLeScanner = null;
        }
    }
}
