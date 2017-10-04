package com.here.android.mpa.streetlevel;

import com.here.android.mpa.common.Vector3f;
import com.nokia.maps.am;
import com.nokia.maps.cr;
import com.nokia.maps.k;

public final class StreetLevelBillboardOrientation {
    private cr a;

    public enum Orientation {
        FIXED,
        VERTICAL_FIXED,
        BILLBOARD
    }

    public StreetLevelBillboardOrientation(Orientation orientation, Vector3f vector3f, Vector3f vector3f2) {
        this.a = new cr(orientation, vector3f, vector3f2);
    }

    private StreetLevelBillboardOrientation(cr crVar) {
        this.a = crVar;
    }

    public StreetLevelBillboardOrientation setOrientation(Orientation orientation) {
        this.a.b(orientation);
        return this;
    }

    public void setNormalVector(Vector3f vector3f) {
        this.a.a(vector3f);
    }

    public void setUpVector(Vector3f vector3f) {
        this.a.b(vector3f);
    }

    public Orientation getOrientation() {
        return this.a.a();
    }

    public Vector3f getNormalVector() {
        return this.a.b();
    }

    public Vector3f getUpVector() {
        return this.a.c();
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof StreetLevelBillboardOrientation)) {
            return this.a.equals(((StreetLevelBillboardOrientation) obj).a);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() + 527;
    }

    static {
        cr.a(new k<StreetLevelBillboardOrientation, cr>() {
            public cr a(StreetLevelBillboardOrientation streetLevelBillboardOrientation) {
                return streetLevelBillboardOrientation.a;
            }
        }, new am<StreetLevelBillboardOrientation, cr>() {
            public StreetLevelBillboardOrientation a(cr crVar) {
                if (crVar != null) {
                    return new StreetLevelBillboardOrientation(crVar);
                }
                return null;
            }
        });
    }
}
