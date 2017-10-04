package com.here.android.mpa.streetlevel;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.streetlevel.StreetLevelProxyObject.Type;
import com.nokia.maps.PanoramaBuilding;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.k;

public final class StreetLevelBuilding extends StreetLevelProxyObject {
    private PanoramaBuilding a;

    @HybridPlusNative
    private StreetLevelBuilding(PanoramaBuilding panoramaBuilding) {
        super(panoramaBuilding);
        this.a = panoramaBuilding;
    }

    public Identifier getIdentifier() {
        return this.a.b();
    }

    public float getHighlight() {
        return this.a.getHighlight();
    }

    public boolean setHighlight(float f) {
        return this.a.a(f);
    }

    public GeoCoordinate getPosition() {
        return this.a.c();
    }

    public Type getType() {
        return this.a.a();
    }

    static {
        PanoramaBuilding.a(new k<StreetLevelBuilding, PanoramaBuilding>() {
            public PanoramaBuilding a(StreetLevelBuilding streetLevelBuilding) {
                return streetLevelBuilding.a;
            }
        }, new am<StreetLevelBuilding, PanoramaBuilding>() {
            public StreetLevelBuilding a(PanoramaBuilding panoramaBuilding) {
                if (panoramaBuilding != null) {
                    return new StreetLevelBuilding(panoramaBuilding);
                }
                return null;
            }
        });
    }
}
