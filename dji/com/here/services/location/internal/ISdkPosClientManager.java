package com.here.services.location.internal;

import android.location.Location;
import com.here.posclient.UpdateOptions;

public interface ISdkPosClientManager {
    Location getLastPosition();

    void positioningConsentRevoked();

    boolean startLocationUpdates(UpdateOptions updateOptions);

    void stopLocationUpdates();
}
