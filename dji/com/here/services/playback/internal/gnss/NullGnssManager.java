package com.here.services.playback.internal.gnss;

import android.location.Location;
import com.here.odnp.gnss.IGnssManager;
import com.here.odnp.gnss.IGnssManager.IGnnsListener;
import com.here.posclient.Status;

public class NullGnssManager implements IGnssManager {
    private static final String TAG = "services.playback.internal.gnss.NullGnssManager";

    public boolean isGnssSupported() {
        return true;
    }

    public Location getLastKnownGnssLocation() {
        return null;
    }

    public synchronized boolean startListening(IGnnsListener iGnnsListener, long j) {
        return true;
    }

    public synchronized void stopListening() {
    }

    public Status startGnss() {
        return Status.Ok;
    }

    public void stopGnss() {
    }
}
