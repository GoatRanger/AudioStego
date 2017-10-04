package com.here.services.playback.internal.ble;

import com.here.odnp.ble.IBleManager;
import com.here.odnp.ble.IBleManager.IBleListener;
import com.here.posclient.Status;

public class NullBleManager implements IBleManager {
    private static final String TAG = "services.playback.internal.ble.NullBleManager";

    public Status open(IBleListener iBleListener) {
        return Status.Ok;
    }

    public void close() {
    }

    public boolean isBleSupported() {
        return true;
    }

    public Status startBleScan() {
        return Status.GeneralError;
    }

    public void stopBleScan() {
    }
}
