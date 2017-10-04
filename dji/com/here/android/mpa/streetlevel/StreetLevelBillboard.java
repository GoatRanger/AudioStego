package com.here.android.mpa.streetlevel;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.nokia.maps.PanoramaBillboard;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.k;

public final class StreetLevelBillboard extends StreetLevelIconBase {
    private PanoramaBillboard a;

    public StreetLevelBillboard(GeoCoordinate geoCoordinate, Image image) {
        this(new PanoramaBillboard(geoCoordinate, image));
    }

    @HybridPlusNative
    private StreetLevelBillboard(PanoramaBillboard panoramaBillboard) {
        super(panoramaBillboard);
        this.a = panoramaBillboard;
    }

    public StreetLevelBillboard setSize(float f, float f2) {
        this.a.a(f, f2);
        return this;
    }

    public StreetLevelBillboard setWidth(float f) {
        this.a.a(f);
        return this;
    }

    public float getWidth() {
        return this.a.c();
    }

    public StreetLevelBillboard setHeight(float f) {
        this.a.b(f);
        return this;
    }

    public float getHeight() {
        return this.a.d();
    }

    public StreetLevelBillboard setOrientation(StreetLevelBillboardOrientation streetLevelBillboardOrientation) {
        this.a.a(streetLevelBillboardOrientation);
        return this;
    }

    public StreetLevelBillboardOrientation getOrientation() {
        return this.a.b();
    }

    public StreetLevelBillboard setFacadePlacementSize(float f, float f2, float f3, float f4) {
        this.a.a(f, f2, f3, f4);
        return this;
    }

    static {
        PanoramaBillboard.a(new k<StreetLevelBillboard, PanoramaBillboard>() {
            public PanoramaBillboard a(StreetLevelBillboard streetLevelBillboard) {
                return streetLevelBillboard.a;
            }
        }, new am<StreetLevelBillboard, PanoramaBillboard>() {
            public StreetLevelBillboard a(PanoramaBillboard panoramaBillboard) {
                if (panoramaBillboard != null) {
                    return new StreetLevelBillboard(panoramaBillboard);
                }
                return null;
            }
        });
    }
}
