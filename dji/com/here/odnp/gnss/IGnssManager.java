package com.here.odnp.gnss;

import android.location.Location;
import com.here.posclient.GnssStatus;
import com.here.posclient.Status;

public interface IGnssManager {

    public interface IGnnsListener {
        void onGnssLocationChanged(Location location, boolean z);

        void onGnssStatusChanged(GnssStatus gnssStatus);
    }

    Location getLastKnownGnssLocation();

    boolean isGnssSupported();

    Status startGnss();

    boolean startListening(IGnnsListener iGnnsListener, long j);

    void stopGnss();

    void stopListening();
}
