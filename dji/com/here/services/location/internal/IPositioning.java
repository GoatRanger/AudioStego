package com.here.services.location.internal;

import android.location.Location;
import com.here.posclient.UpdateOptions;
import com.here.services.internal.Manager;

public interface IPositioning extends Manager {

    public interface IPositionListener {
        void onOptionsChanged(UpdateOptions updateOptions, UpdateOptions updateOptions2);

        void onPositionUpdate(Location location);

        void onSessionClosed();
    }

    void clearPositioningData();

    void close();

    Location getLastPosition();

    boolean startPositionUpdates(Options options, IPositionListener iPositionListener);

    void stopPositionUpdates(IPositionListener iPositionListener);
}
