package com.here.odnp.ble;

import com.here.posclient.Status;
import java.util.List;

public interface IBleManager {

    public interface IBleListener {
        void onScanFailed(Status status);

        void onScanResultsAvailable(BleScanResultContainer bleScanResultContainer);
    }

    public static class BleScanResultContainer {
        public long measurementId;
        public List<BleScanResult> scanResultList;
        public boolean simulated;

        public BleScanResultContainer(List<BleScanResult> list) {
            this.scanResultList = list;
        }

        public BleScanResultContainer(long j, boolean z, List<BleScanResult> list) {
            this.measurementId = j;
            this.simulated = z;
            this.scanResultList = list;
        }
    }

    void close();

    boolean isBleSupported();

    Status open(IBleListener iBleListener);

    Status startBleScan();

    void stopBleScan();
}
