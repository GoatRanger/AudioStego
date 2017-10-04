package com.here.android.mpa.venues3d;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.ArrayList;
import java.util.List;

@HybridPlus
public final class Venue extends BaseNativeObject {
    private List<Level> a = null;
    private List<Space> b = null;
    private Content c = null;

    private native Content getContentNative();

    private native List<Level> getLevelsNative();

    private native List<Space> getSortedSpacesNative();

    private native void nativeDispose();

    public native GeoBoundingBox getBoundingBox();

    public native GeoCoordinate getCenter();

    public native String getId();

    public native Space getSpace(String str);

    @HybridPlusNative
    private Venue(int i) {
        this.nativeptr = i;
    }

    public List<Level> getLevels() {
        if (this.a == null) {
            this.a = getLevelsNative();
        }
        return this.a != null ? this.a : new ArrayList();
    }

    public List<Space> getSortedSpaces() {
        if (this.b == null) {
            this.b = getSortedSpacesNative();
        }
        return this.b != null ? this.b : new ArrayList();
    }

    public Content getContent() {
        if (this.c == null) {
            this.c = getContentNative();
        }
        return this.c;
    }

    protected void finalize() throws Throwable {
        if (this.nativeptr != 0) {
            nativeDispose();
        }
        super.finalize();
    }
}
