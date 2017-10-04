package com.here.odnp.ble;

import android.content.Context;
import com.here.odnp.ble.IBleManager.BleScanResultContainer;
import com.here.odnp.ble.IBleManager.IBleListener;
import com.here.posclient.Status;
import java.util.List;

public class PlatformBleManager implements IListener, IBleManager {
    private static final String TAG = "odnp.ble.PlatformBleManager";
    private final Manager mManager;

    private interface Manager extends IListener, IBleManager {
    }

    private static class BleSupportedManager implements Manager {
        private final BleScanner mBleScanner;
        private volatile IBleListener mListener;

        BleSupportedManager(BleScanner bleScanner) {
            this.mBleScanner = bleScanner;
        }

        public Status open(IBleListener iBleListener) {
            close();
            if (iBleListener == null) {
                return Status.InvalidArgumentError;
            }
            this.mListener = iBleListener;
            return Status.Ok;
        }

        public void close() {
            this.mListener = null;
            stopBleScan();
        }

        public Status startBleScan() {
            if (this.mListener == null) {
                return Status.UsageError;
            }
            return this.mBleScanner.startScan();
        }

        public void stopBleScan() {
            this.mBleScanner.stopScan();
        }

        public boolean isBleSupported() {
            return true;
        }

        public void onScanResultsAvailable(List<BleScanResult> list) {
            IBleListener iBleListener = this.mListener;
            if (iBleListener != null) {
                try {
                    iBleListener.onScanResultsAvailable(new BleScanResultContainer(list));
                } catch (Exception e) {
                }
            }
        }

        public void onScanFailed(Status status) {
            IBleListener iBleListener = this.mListener;
            if (iBleListener != null) {
                try {
                    iBleListener.onScanFailed(status);
                } catch (Exception e) {
                }
            }
        }
    }

    private static class NoOpManager implements Manager {
        private NoOpManager() {
        }

        public Status open(IBleListener iBleListener) {
            return Status.NotSupportedError;
        }

        public void close() {
        }

        public Status startBleScan() {
            return Status.NotSupportedError;
        }

        public void stopBleScan() {
        }

        public boolean isBleSupported() {
            return false;
        }

        public void onScanResultsAvailable(List<BleScanResult> list) {
        }

        public void onScanFailed(Status status) {
        }
    }

    public PlatformBleManager(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        BleScanner create = BleScanner.create(context, this);
        if (create == null) {
            this.mManager = new NoOpManager();
        } else {
            this.mManager = new BleSupportedManager(create);
        }
    }

    public synchronized Status open(IBleListener iBleListener) {
        return this.mManager.open(iBleListener);
    }

    public synchronized void close() {
        this.mManager.close();
    }

    public boolean isBleSupported() {
        return this.mManager.isBleSupported();
    }

    public synchronized Status startBleScan() {
        return this.mManager.startBleScan();
    }

    public synchronized void stopBleScan() {
        this.mManager.stopBleScan();
    }

    public void onScanResultsAvailable(List<BleScanResult> list) {
        this.mManager.onScanResultsAvailable(list);
    }

    public void onScanFailed(Status status) {
        this.mManager.onScanFailed(status);
    }
}
