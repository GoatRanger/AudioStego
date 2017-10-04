package com.here.android.mpa.streetlevel;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.PanoramaImpl;
import com.nokia.maps.am;
import com.nokia.maps.k;
import java.util.List;

public final class StreetLevel {
    private PanoramaImpl a;

    private StreetLevel(PanoramaImpl panoramaImpl) {
        this.a = panoramaImpl;
    }

    public GeoCoordinate getPosition() {
        return this.a.a();
    }

    public boolean isDownloaded() {
        return this.a.b();
    }

    public List<StreetLevelBuilding> getVisibleBuildings() {
        return this.a.getVisibleBuildings();
    }

    static {
        PanoramaImpl.a(new k<StreetLevel, PanoramaImpl>() {
            public PanoramaImpl a(StreetLevel streetLevel) {
                return streetLevel.a;
            }
        }, new am<StreetLevel, PanoramaImpl>() {
            public StreetLevel a(PanoramaImpl panoramaImpl) {
                if (panoramaImpl != null) {
                    return new StreetLevel(panoramaImpl);
                }
                return null;
            }
        });
    }
}
