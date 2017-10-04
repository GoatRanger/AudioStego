package com.here.android.mpa.streetlevel;

import com.nokia.maps.am;
import com.nokia.maps.cv;
import com.nokia.maps.k;

public final class StreetLevelIconPlacement {
    private cv a;

    public enum HorizontalPlacement {
        DEFAULT,
        CENTROID,
        SURFACE,
        FACADE,
        TRACK_CAMERA
    }

    public enum VerticalPlacement {
        DEFAULT,
        TERRAIN,
        FACADE,
        ATTACHMENT
    }

    public StreetLevelIconPlacement(HorizontalPlacement horizontalPlacement, VerticalPlacement verticalPlacement, float f) {
        this.a = new cv(horizontalPlacement, verticalPlacement, f);
    }

    private StreetLevelIconPlacement(cv cvVar) {
        this.a = cvVar;
    }

    public StreetLevelIconPlacement setHorizontalPlacement(HorizontalPlacement horizontalPlacement) {
        this.a.b(horizontalPlacement);
        return this;
    }

    public HorizontalPlacement getHorizontalPlacement() {
        return this.a.a();
    }

    public StreetLevelIconPlacement setVerticalPlacement(VerticalPlacement verticalPlacement) {
        this.a.b(verticalPlacement);
        return this;
    }

    public VerticalPlacement getVerticalPlacement() {
        return this.a.b();
    }

    public StreetLevelIconPlacement setVerticalPlacementHeight(float f) {
        this.a.a(f);
        return this;
    }

    public float getVerticalPlacementHeight() {
        return this.a.c();
    }

    public boolean equals(Object obj) {
        return this.a.equals(obj);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    static {
        cv.a(new k<StreetLevelIconPlacement, cv>() {
            public cv a(StreetLevelIconPlacement streetLevelIconPlacement) {
                return streetLevelIconPlacement.a;
            }
        }, new am<StreetLevelIconPlacement, cv>() {
            public StreetLevelIconPlacement a(cv cvVar) {
                if (cvVar != null) {
                    return new StreetLevelIconPlacement(cvVar);
                }
                return null;
            }
        });
    }
}
