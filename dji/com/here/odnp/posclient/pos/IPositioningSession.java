package com.here.odnp.posclient.pos;

import android.location.Location;
import com.here.odnp.posclient.ICloseableSession;
import com.here.posclient.Status;
import com.here.posclient.UpdateOptions;

public interface IPositioningSession extends ICloseableSession {
    public static final long NotSet = Long.MAX_VALUE;

    public interface ILocationListener {
        void onClosed();

        void onLocationChanged(Location location);

        void onLocationResolvingFailed(Status status);

        void onOptionsChanged(UpdateOptions updateOptions, UpdateOptions updateOptions2);
    }

    Location getLastPosition();

    UpdateOptions getUpdateOptions();

    void handleGlobalLocationSettingChanged(boolean z);

    boolean isOfflineModeSet();

    void onNetworkLocationDisabled(boolean z);

    void onNetworkLocationEnabled();

    void positioningConsentRevoked();

    void requestLastPosition();

    void requestPosition();

    void resetMeasurements();

    void setOfflineMode(boolean z);

    void setUpdateOptions(UpdateOptions updateOptions);

    boolean startPositionUpdates();

    void stopPositionUpdates();
}
