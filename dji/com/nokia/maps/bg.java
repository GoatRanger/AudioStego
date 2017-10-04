package com.nokia.maps;

import android.location.Location;
import com.here.android.mpa.common.LocationDataSource;
import com.here.android.mpa.common.PositioningManager.LocationMethod;

public class bg {
    private static k<LocationDataSource, bg> a = null;
    private bh b = null;

    public static void a(k<LocationDataSource, bg> kVar) {
        a = kVar;
    }

    static bg a(LocationDataSource locationDataSource) {
        return (bg) a.a(locationDataSource);
    }

    public void a(bh bhVar) {
        this.b = bhVar;
    }

    public void a(LocationMethod locationMethod, Location location) {
        if (this.b != null) {
            this.b.a(locationMethod, location);
        }
    }

    public void a(LocationMethod locationMethod, int i) {
        if (this.b != null && i >= 0 && i <= 2) {
            this.b.a(locationMethod, i);
        }
    }
}
