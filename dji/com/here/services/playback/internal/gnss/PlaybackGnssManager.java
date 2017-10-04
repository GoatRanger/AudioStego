package com.here.services.playback.internal.gnss;

import android.location.Location;
import com.here.odnp.gnss.IGnssManager;
import com.here.odnp.gnss.IGnssManager.IGnnsListener;
import com.here.posclient.GnssStatus;
import com.here.posclient.Status;
import com.here.services.playback.internal.util.PlaybackReader;

public class PlaybackGnssManager implements IGnssManager {
    private static final String TAG = "services.playback.internal.gnss.PlaybackGnssManager";
    private IGnnsListener mGnssListener;
    private Location mLastKnownLocation;
    private final PlaybackReader mPlaybackReader;

    class GnssListenerProxy implements IGnnsListener {
        private final IGnnsListener mRealListener;

        GnssListenerProxy(IGnnsListener iGnnsListener) {
            this.mRealListener = iGnnsListener;
        }

        public void onGnssLocationChanged(Location location, boolean z) {
            PlaybackGnssManager.this.mLastKnownLocation = location;
            this.mRealListener.onGnssLocationChanged(location, z);
        }

        public void onGnssStatusChanged(GnssStatus gnssStatus) {
            this.mRealListener.onGnssStatusChanged(gnssStatus);
        }
    }

    public PlaybackGnssManager(PlaybackReader playbackReader) {
        this.mPlaybackReader = playbackReader;
    }

    public boolean isGnssSupported() {
        return true;
    }

    public Location getLastKnownGnssLocation() {
        return this.mLastKnownLocation;
    }

    public synchronized boolean startListening(IGnnsListener iGnnsListener, long j) {
        boolean z;
        if (iGnnsListener == null) {
            z = false;
        } else {
            stopListening();
            this.mGnssListener = new GnssListenerProxy(iGnnsListener);
            this.mPlaybackReader.addGnssListener(this.mGnssListener);
            z = true;
        }
        return z;
    }

    public synchronized void stopListening() {
        if (this.mGnssListener != null) {
            this.mPlaybackReader.removeGnssListener(this.mGnssListener);
            this.mGnssListener = null;
        }
    }

    public Status startGnss() {
        if (this.mPlaybackReader.startGnss()) {
            return Status.Ok;
        }
        return Status.GeneralError;
    }

    public void stopGnss() {
        this.mPlaybackReader.stopGnss();
    }
}
