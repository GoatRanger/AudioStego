package com.here.android.mpa.common;

import android.location.Location;
import com.here.android.mpa.common.PositioningManager.LocationMethod;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.bg;
import com.nokia.maps.k;

@HybridPlus
public abstract class LocationDataSource {
    private bg a = new bg();

    public abstract int getGpsStatus();

    public abstract int getIndoorStatus();

    public abstract Location getLastKnownLocation();

    public abstract int getNetworkStatus();

    public abstract boolean start(LocationMethod locationMethod);

    public abstract void stop();

    protected final void onLocationUpdated(LocationMethod locationMethod, Location location) {
        this.a.a(locationMethod, location);
    }

    protected final void onStatusUpdated(LocationMethod locationMethod, int i) {
        this.a.a(locationMethod, i);
    }

    static {
        bg.a(new k<LocationDataSource, bg>() {
            public bg a(LocationDataSource locationDataSource) {
                return locationDataSource != null ? locationDataSource.a : null;
            }
        });
    }
}
