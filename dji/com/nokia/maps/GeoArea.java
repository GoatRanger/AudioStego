package com.nokia.maps;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.annotation.Online;

@Online
public class GeoArea extends BaseNativeObject {
    private static String a = GeoArea.class.getSimpleName();

    private native boolean containsNative(GeoCoordinateImpl geoCoordinateImpl);

    private static native void destroyNative(int i);

    private native GeoBoundingBoxImpl getBoundingBoxNative();

    public native boolean isValid();

    protected GeoArea() {
        bj.a(a, "nativeptr=0x%08x", new Object[]{Integer.valueOf(this.nativeptr)});
    }

    protected GeoArea(int i) {
        this.nativeptr = i;
        bj.a(a, "nativeptr=0x%08x", new Object[]{Integer.valueOf(this.nativeptr)});
    }

    protected void finalize() {
        bj.a(a, "IN - nativeptr=0x%08x", new Object[]{Integer.valueOf(this.nativeptr)});
        if (this.nativeptr != 0) {
            destroyNative(this.nativeptr);
        }
        bj.a(a, "OUT - nativeptr=0x%08x", new Object[]{Integer.valueOf(this.nativeptr)});
    }

    public boolean a(GeoCoordinate geoCoordinate) {
        if (geoCoordinate == null || !geoCoordinate.isValid()) {
            return false;
        }
        return containsNative(GeoCoordinateImpl.get(geoCoordinate));
    }

    public GeoBoundingBox a() {
        if (isValid()) {
            return GeoBoundingBoxImpl.create(getBoundingBoxNative());
        }
        return null;
    }
}
