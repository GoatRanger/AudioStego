package com.amap.api.mapcore.util;

import android.location.Location;
import com.amap.api.maps.LocationSource.OnLocationChangedListener;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;

class m implements OnLocationChangedListener {
    Location a;
    private IAMapDelegate b;

    m(IAMapDelegate iAMapDelegate) {
        this.b = iAMapDelegate;
    }

    public void onLocationChanged(Location location) {
        this.a = location;
        try {
            if (this.b.isMyLocationEnabled()) {
                this.b.showMyLocationOverlay(location);
            }
        } catch (Throwable e) {
            ee.a(e, "AMapOnLocationChangedListener", "onLocationChanged");
            e.printStackTrace();
        }
    }
}
