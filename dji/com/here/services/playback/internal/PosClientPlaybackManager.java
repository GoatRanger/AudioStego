package com.here.services.playback.internal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.here.odnp.posclient.IPosClientManager;
import com.here.odnp.posclient.test.IPosClientTesterSession;
import com.here.services.playback.internal.ble.NullBleManager;
import com.here.services.playback.internal.ble.PlaybackBleManager;
import com.here.services.playback.internal.cell.NullCellManager;
import com.here.services.playback.internal.cell.PlaybackCellManager;
import com.here.services.playback.internal.gnss.NullGnssManager;
import com.here.services.playback.internal.gnss.PlaybackGnssManager;
import com.here.services.playback.internal.util.PlaybackReader;
import com.here.services.playback.internal.util.PlaybackReader.IListener;
import com.here.services.playback.internal.wifi.NullWifiManager;
import com.here.services.playback.internal.wifi.PlaybackWifiManager;
import java.io.File;

public class PosClientPlaybackManager implements IListener {
    private static final String TAG = "services.playback.internal.PosClientPlaybackManager";
    private final Context mContext = this.mPosClientManager.getContext();
    private PlaybackOptions mPlaybackOptions;
    private final IPosClientManager mPosClientManager;
    private final IPosClientTesterSession mPosClientTesterSession = this.mPosClientManager.createTesterSession();

    private PosClientPlaybackManager(IPosClientManager iPosClientManager) {
        this.mPosClientManager = iPosClientManager;
    }

    public static PosClientPlaybackManager create(IPosClientManager iPosClientManager) {
        return new PosClientPlaybackManager(iPosClientManager);
    }

    public boolean startNetworkMeasurementPlayback(Bundle bundle) {
        this.mPlaybackOptions = PlaybackOptions.fromBundle(bundle);
        if (this.mPlaybackOptions == null || !this.mPosClientTesterSession.open()) {
            return false;
        }
        try {
            PlaybackReader open = PlaybackReader.open(this.mContext, this.mPlaybackOptions, this);
            if (open == null) {
                return false;
            }
            this.mPosClientTesterSession.storeMeasurementManagers();
            int technologies = this.mPlaybackOptions.getTechnologies();
            if ((technologies & 1) != 0) {
                if (!this.mPosClientTesterSession.setCellManager(new PlaybackCellManager(open))) {
                    throw new RuntimeException("setCellManager(PlaybackCellManager) failed");
                }
            } else if (!this.mPosClientTesterSession.setCellManager(new NullCellManager())) {
                throw new RuntimeException("setCellManager(NullCellManager) failed");
            }
            if ((technologies & 2) != 0) {
                if (!this.mPosClientTesterSession.setWifiManager(new PlaybackWifiManager(open))) {
                    throw new RuntimeException("setWifiManager(PlaybackWifiManager) failed");
                }
            } else if (!this.mPosClientTesterSession.setWifiManager(new NullWifiManager())) {
                throw new RuntimeException("setWifiManager(NullWifiManager) failed");
            }
            if ((technologies & 4) != 0) {
                if (!this.mPosClientTesterSession.setGnssManager(new PlaybackGnssManager(open))) {
                    throw new RuntimeException("setGnssManager(PlaybackGnssManager) failed");
                }
            } else if (!this.mPosClientTesterSession.setGnssManager(new NullGnssManager())) {
                throw new RuntimeException("setGnssManager(NullGnssManager) failed");
            }
            if ((technologies & 8) != 0) {
                if (!this.mPosClientTesterSession.setBleManager(new PlaybackBleManager(open))) {
                    throw new RuntimeException("setBleManager(PlaybackBleManager) failed");
                }
            } else if (!this.mPosClientTesterSession.setBleManager(new NullBleManager())) {
                throw new RuntimeException("setBleManager(NullBleManager) failed");
            }
            this.mPosClientTesterSession.resetPositioningFilter();
            if (open.start()) {
                reportPlaybackStart(technologies, this.mPlaybackOptions.getPlaybackFile());
                return true;
            }
            stopNetworkMeasurementPlayback();
            return false;
        } catch (Exception e) {
            stopNetworkMeasurementPlayback();
            return false;
        }
    }

    public void stopNetworkMeasurementPlayback() {
        try {
            if (this.mPosClientTesterSession.open()) {
                if (this.mPosClientTesterSession.restoreMeasurementManagers()) {
                    this.mPosClientTesterSession.resetPositioningFilter();
                }
                if (this.mPlaybackOptions != null) {
                    reportPlaybackFinished(this.mPlaybackOptions.getPlaybackFile());
                    this.mPlaybackOptions = null;
                }
            }
        } finally {
            if (this.mPlaybackOptions != null) {
                reportPlaybackFinished(this.mPlaybackOptions.getPlaybackFile());
                this.mPlaybackOptions = null;
            }
        }
    }

    public void close() {
        if (this.mPosClientTesterSession != null) {
            this.mPosClientTesterSession.close();
        }
    }

    public void onPlaybackFinished() {
        stopNetworkMeasurementPlayback();
    }

    private void reportPlaybackStart(int i, File file) {
        Intent intent = new Intent("com.here.odnp.test.playback-begin");
        intent.putExtra("filename", file.getAbsolutePath());
        intent.putExtra("technologies", i);
        this.mContext.sendBroadcast(intent);
    }

    private void reportPlaybackFinished(File file) {
        Intent intent = new Intent("com.here.odnp.test.playback-end");
        intent.putExtra("filename", file.getAbsolutePath());
        this.mContext.sendBroadcast(intent);
    }
}
