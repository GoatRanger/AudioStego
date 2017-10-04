package com.here.services.playback.internal.ble;

import com.here.odnp.ble.IBleManager;
import com.here.odnp.ble.IBleManager.IBleListener;
import com.here.posclient.Status;
import com.here.services.playback.internal.util.PlaybackReader;

public class PlaybackBleManager implements IBleManager {
    private static final String TAG = "services.playback.internal.ble.PlaybackBleManager";
    private IBleListener mBleListener;
    private final PlaybackReader mPlaybackReader;

    public PlaybackBleManager(PlaybackReader playbackReader) {
        this.mPlaybackReader = playbackReader;
    }

    public Status open(IBleListener iBleListener) {
        this.mBleListener = iBleListener;
        this.mPlaybackReader.addBleListener(iBleListener);
        return isBleSupported() ? Status.Ok : Status.NotSupportedError;
    }

    public void close() {
        this.mPlaybackReader.removeBleListener(this.mBleListener);
        this.mBleListener = null;
    }

    public boolean isBleSupported() {
        return true;
    }

    public Status startBleScan() {
        if (!isBleSupported()) {
            return Status.NotSupportedError;
        }
        if (this.mBleListener == null) {
            return Status.UsageError;
        }
        if (!this.mPlaybackReader.isBluetoothEnabled()) {
            return Status.NotEnabledError;
        }
        if (this.mPlaybackReader.startBleScan()) {
            return Status.Ok;
        }
        return Status.GeneralError;
    }

    public void stopBleScan() {
        this.mPlaybackReader.stopBleScan();
    }
}
