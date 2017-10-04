package com.here.android.mpa.venues3d;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.BaseNativeObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class BaseLocation extends BaseNativeObject {
    protected LocationType a = LocationType.OTHER;
    protected BaseLocation b = null;

    @HybridPlus
    public enum LocationType {
        SPACE,
        OUTDOOR,
        LEVEL,
        OTHER
    }

    @HybridPlusNative
    private native void nativeDispose();

    @HybridPlusNative
    private native void setParkingLocationNativ(BaseLocation baseLocation);

    @HybridPlusNative
    public native Area getArea();

    @HybridPlusNative
    public native GeoCoordinate getGeoCoordinate();

    @HybridPlusNative
    public native boolean isValid();

    protected BaseLocation() {
    }

    @HybridPlusNative
    protected BaseLocation(int i) {
        this.nativeptr = i;
    }

    public void setParkingLocation(BaseLocation baseLocation) {
        this.b = baseLocation;
        setParkingLocationNativ(baseLocation);
    }

    public LocationType getType() {
        return this.a;
    }

    protected void finalize() throws Throwable {
        if (this.nativeptr != 0) {
            nativeDispose();
        }
        super.finalize();
    }
}
