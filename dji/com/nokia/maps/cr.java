package com.nokia.maps;

import com.here.android.mpa.common.Vector3f;
import com.here.android.mpa.streetlevel.StreetLevelBillboardOrientation;
import com.here.android.mpa.streetlevel.StreetLevelBillboardOrientation.Orientation;

public class cr {
    private static k<StreetLevelBillboardOrientation, cr> e = null;
    private static am<StreetLevelBillboardOrientation, cr> f = null;
    private cq a = new cq(cr.class.getName());
    private Orientation b;
    private Vector3f c;
    private Vector3f d;

    static {
        ce.a(StreetLevelBillboardOrientation.class);
    }

    public static void a(k<StreetLevelBillboardOrientation, cr> kVar, am<StreetLevelBillboardOrientation, cr> amVar) {
        e = kVar;
        f = amVar;
    }

    static cr a(StreetLevelBillboardOrientation streetLevelBillboardOrientation) {
        return (cr) e.a(streetLevelBillboardOrientation);
    }

    static StreetLevelBillboardOrientation a(cr crVar) {
        if (crVar != null) {
            return (StreetLevelBillboardOrientation) f.a(crVar);
        }
        return null;
    }

    public static final Orientation a(int i) {
        switch (i) {
            case 0:
                return Orientation.FIXED;
            case 1:
                return Orientation.VERTICAL_FIXED;
            case 2:
                return Orientation.BILLBOARD;
            default:
                throw new IllegalArgumentException("Billbaord Placement value not supported.");
        }
    }

    public static final int a(Orientation orientation) {
        switch (orientation) {
            case FIXED:
                return 0;
            case VERTICAL_FIXED:
                return 1;
            case BILLBOARD:
                return 2;
            default:
                throw new IllegalArgumentException("Billboard Placement value not supported.");
        }
    }

    public cr(Orientation orientation, Vector3f vector3f, Vector3f vector3f2) {
        dy.a((Object) vector3f, "normal argument is null");
        dy.a((Object) vector3f2, "up argument is null");
        this.b = orientation;
        this.c = vector3f;
        this.d = vector3f2;
    }

    public void b(Orientation orientation) {
        this.b = orientation;
    }

    public void a(Vector3f vector3f) {
        dy.a((Object) vector3f, "normal argument is null");
        this.c = vector3f;
    }

    public void b(Vector3f vector3f) {
        dy.a((Object) vector3f, "up argument is null");
        this.d = vector3f;
    }

    public Orientation a() {
        return this.b;
    }

    public Vector3f b() {
        return this.c;
    }

    public Vector3f c() {
        return this.d;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.b == null ? 0 : this.b.hashCode()) + (((this.c == null ? 0 : this.c.hashCode()) + 31) * 31)) * 31;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (cr.class.isInstance(obj)) {
            obj = (cr) obj;
        } else if (!StreetLevelBillboardOrientation.class.isInstance(obj)) {
            return false;
        } else {
            obj = a((StreetLevelBillboardOrientation) obj);
        }
        if (this.c == null) {
            if (obj.c != null) {
                return false;
            }
        } else if (!this.c.equals(obj.c)) {
            return false;
        }
        if (this.b != obj.b) {
            return false;
        }
        if (this.d == null) {
            if (obj.d != null) {
                return false;
            }
        } else if (!this.d.equals(obj.d)) {
            return false;
        }
        return true;
    }
}
