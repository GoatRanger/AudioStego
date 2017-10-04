package com.here.odnp.ble;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.content.Context;

@TargetApi(18)
class BleScannerPreApi21 extends BleScanner {
    private static final String TAG = "odnp.ble.BleScannerPreApi21";
    private final LeScanCallback mBleScanCallback = new LeScanCallback() {
        public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
            if (BleValidator.isSupportedBleTag(bArr)) {
                BleScannerPreApi21.this.addScanResult(bluetoothDevice, i);
            }
        }
    };

    public BleScannerPreApi21(Context context, IListener iListener) {
        super(context, iListener);
    }

    protected boolean doStartScan() {
        return this.mBluetoothAdapter.startLeScan(this.mBleScanCallback);
    }

    protected void doStopScan() {
        this.mBluetoothAdapter.stopLeScan(this.mBleScanCallback);
    }
}
