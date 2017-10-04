package com.nokia.maps;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.mapping.Location;
import com.here.android.mpa.mapping.LocationInfo;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class LocationImpl extends BaseNativeObject {
    private static am<Location, LocationImpl> a = null;

    private final native void destroyLocationNative();

    private final native GeoBoundingBoxImpl getBoundingBoxNative();

    private final native GeoCoordinateImpl getCoordinateNative();

    public final native LocationInfoImpl getInfoNative();

    public final native boolean isValid();

    static {
        ce.a(Location.class);
    }

    static Location a(LocationImpl locationImpl) {
        if (locationImpl != null) {
            return (Location) a.a(locationImpl);
        }
        return null;
    }

    public static void a(am<Location, LocationImpl> amVar) {
        a = amVar;
    }

    @OnlineNative
    protected LocationImpl(int i) {
        super(true);
        this.nativeptr = i;
    }

    @HybridPlusNative
    protected LocationImpl() {
        super(true);
    }

    protected void finalize() {
        a();
    }

    protected void a() {
        destroyLocationNative();
    }

    public final GeoCoordinate b() {
        return GeoCoordinateImpl.create(getCoordinateNative());
    }

    public final GeoBoundingBox c() {
        return GeoBoundingBoxImpl.create(getBoundingBoxNative());
    }

    public final LocationInfo d() {
        return LocationInfoImpl.a(getInfoNative());
    }
}
