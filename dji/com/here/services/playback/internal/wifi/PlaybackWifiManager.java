package com.here.services.playback.internal.wifi;

import com.here.odnp.wifi.IWifiManager;
import com.here.odnp.wifi.IWifiManager.IWifiListener;
import com.here.posclient.WifiMeasurement;
import com.here.services.playback.internal.util.PlaybackReader;
import java.util.List;

public class PlaybackWifiManager implements IWifiManager {
    private static final String TAG = "services.playback.internal.wifi.PlaybackWifiManager";
    private final PlaybackReader mPlaybackReader;
    private IWifiListener mWifiListener;

    public PlaybackWifiManager(PlaybackReader playbackReader) {
        this.mPlaybackReader = playbackReader;
    }

    public void open(IWifiListener iWifiListener) {
        this.mWifiListener = iWifiListener;
        this.mPlaybackReader.addWifiListener(iWifiListener);
    }

    public void close() {
        this.mPlaybackReader.removeWifiListener(this.mWifiListener);
        this.mWifiListener = null;
    }

    public List<WifiMeasurement> getLastScanResult() {
        return this.mPlaybackReader.getLastWifiScanResult();
    }

    public boolean startWifiScan() {
        return this.mPlaybackReader.startWifiScan();
    }

    public void cancelWifiScan() {
        this.mPlaybackReader.cancelWifiScan();
    }

    public boolean isWifiSupported() {
        return true;
    }
}
