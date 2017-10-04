package com.nokia.maps;

import com.here.android.mpa.streetlevel.StreetLevelIconPlacement;
import com.here.android.mpa.streetlevel.StreetLevelIconPlacement.HorizontalPlacement;
import com.here.android.mpa.streetlevel.StreetLevelIconPlacement.VerticalPlacement;

public class cv {
    private static k<StreetLevelIconPlacement, cv> e = null;
    private static am<StreetLevelIconPlacement, cv> f = null;
    private cq a = new cq(cv.class.getName());
    private float b;
    private HorizontalPlacement c;
    private VerticalPlacement d;

    static {
        ce.a(StreetLevelIconPlacement.class);
    }

    public static void a(k<StreetLevelIconPlacement, cv> kVar, am<StreetLevelIconPlacement, cv> amVar) {
        e = kVar;
        f = amVar;
    }

    static cv a(StreetLevelIconPlacement streetLevelIconPlacement) {
        return (cv) e.a(streetLevelIconPlacement);
    }

    static StreetLevelIconPlacement a(cv cvVar) {
        if (cvVar != null) {
            return (StreetLevelIconPlacement) f.a(cvVar);
        }
        return null;
    }

    public static final HorizontalPlacement a(int i) {
        switch (i) {
            case 0:
                return HorizontalPlacement.DEFAULT;
            case 1:
                return HorizontalPlacement.CENTROID;
            case 2:
                return HorizontalPlacement.SURFACE;
            case 3:
                return HorizontalPlacement.FACADE;
            case 4:
                return HorizontalPlacement.TRACK_CAMERA;
            default:
                throw new IllegalArgumentException("HorizontalPlacement value not supported.");
        }
    }

    public static final int a(HorizontalPlacement horizontalPlacement) {
        switch (horizontalPlacement) {
            case DEFAULT:
                return 0;
            case CENTROID:
                return 1;
            case SURFACE:
                return 2;
            case FACADE:
                return 3;
            case TRACK_CAMERA:
                return 4;
            default:
                throw new IllegalArgumentException("HorizontalPlacement value not supported.");
        }
    }

    public static final VerticalPlacement b(int i) {
        switch (i) {
            case 0:
                return VerticalPlacement.DEFAULT;
            case 1:
                return VerticalPlacement.TERRAIN;
            case 2:
                return VerticalPlacement.FACADE;
            case 3:
                return VerticalPlacement.ATTACHMENT;
            default:
                throw new IllegalArgumentException("VerticalPlacement value not supported.");
        }
    }

    public static final int a(VerticalPlacement verticalPlacement) {
        switch (verticalPlacement) {
            case DEFAULT:
                return 0;
            case TERRAIN:
                return 1;
            case FACADE:
                return 2;
            case ATTACHMENT:
                return 3;
            default:
                throw new IllegalArgumentException("VerticalPlacement value not supported.");
        }
    }

    public cv(HorizontalPlacement horizontalPlacement, VerticalPlacement verticalPlacement, float f) {
        this.c = horizontalPlacement;
        this.d = verticalPlacement;
        this.b = f;
    }

    public void b(HorizontalPlacement horizontalPlacement) {
        this.c = horizontalPlacement;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        if (this.c == null) {
            i = 0;
        } else {
            i = this.c.hashCode();
        }
        i = (((i + 31) * 31) + Float.floatToIntBits(this.b)) * 31;
        if (this.d != null) {
            i2 = this.d.hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (cv.class.isInstance(obj)) {
            obj = (cv) obj;
        } else if (!StreetLevelIconPlacement.class.isInstance(obj)) {
            return false;
        } else {
            obj = a((StreetLevelIconPlacement) obj);
        }
        if (this.c == obj.c && Float.floatToIntBits(this.b) == Float.floatToIntBits(obj.b) && this.d == obj.d) {
            return true;
        }
        return false;
    }

    public HorizontalPlacement a() {
        return this.c;
    }

    public void b(VerticalPlacement verticalPlacement) {
        this.d = verticalPlacement;
    }

    public VerticalPlacement b() {
        return this.d;
    }

    public void a(float f) {
        this.b = f;
    }

    public float c() {
        return this.b;
    }
}
