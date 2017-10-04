package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.ArrayList;
import java.util.List;

@Online
public class GeoCoordinateImpl extends BaseNativeObject {
    private static k<GeoCoordinate, GeoCoordinateImpl> b;
    private static am<GeoCoordinate, GeoCoordinateImpl> c;
    private cq a = new cq(GeoCoordinateImpl.class.getName());

    private native void createGeoCoordinateNative(double d, double d2, double d3);

    private native void createGeoCoordinateNative(GeoCoordinateImpl geoCoordinateImpl);

    private native void createInvalidGeoCoordinateNative();

    private native void destroyGeoCoordinateNative();

    private native double distanceToNative(GeoCoordinateImpl geoCoordinateImpl);

    private native double getHeadingNative(GeoCoordinateImpl geoCoordinateImpl);

    private native double getNativeAltitude();

    private native double getNativeLatitude();

    private native double getNativeLongitude();

    private native boolean isEqual(GeoCoordinateImpl geoCoordinateImpl);

    private native boolean isValidNative();

    private native void setNativeAltitude(double d);

    private native void setNativeLatitude(double d);

    private native void setNativeLongitude(double d);

    public static void a(k<GeoCoordinate, GeoCoordinateImpl> kVar, am<GeoCoordinate, GeoCoordinateImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    @OnlineNative
    static GeoCoordinateImpl get(GeoCoordinate geoCoordinate) {
        if (b != null) {
            return (GeoCoordinateImpl) b.a(geoCoordinate);
        }
        return null;
    }

    static {
        ce.a(GeoCoordinate.class);
    }

    static GeoCoordinateImpl[] a(List<GeoCoordinate> list) {
        GeoCoordinateImpl[] geoCoordinateImplArr = new GeoCoordinateImpl[list.size()];
        for (int i = 0; i < list.size(); i++) {
            geoCoordinateImplArr[i] = get((GeoCoordinate) list.get(i));
        }
        return geoCoordinateImplArr;
    }

    @OnlineNative
    static GeoCoordinate create(GeoCoordinateImpl geoCoordinateImpl) {
        if (geoCoordinateImpl != null) {
            return (GeoCoordinate) c.a(geoCoordinateImpl);
        }
        return null;
    }

    static List<GeoCoordinate> b(List<GeoCoordinateImpl> list) {
        List arrayList = new ArrayList();
        for (GeoCoordinateImpl create : list) {
            arrayList.add(create(create));
        }
        return arrayList;
    }

    @OnlineNative
    GeoCoordinateImpl(int i) {
        this.nativeptr = i;
    }

    @OnlineNative
    public GeoCoordinateImpl() {
        createInvalidGeoCoordinateNative();
    }

    @OnlineNative
    public GeoCoordinateImpl(double d, double d2, double d3) {
        createGeoCoordinateNative(d, d2, d3);
    }

    @OnlineNative
    public GeoCoordinateImpl(double d, double d2) {
        createGeoCoordinateNative(d, d2, 1.073741824E9d);
    }

    @OnlineNative
    public GeoCoordinateImpl(GeoCoordinateImpl geoCoordinateImpl) {
        if (geoCoordinateImpl.d()) {
            createGeoCoordinateNative(geoCoordinateImpl);
            return;
        }
        throw new IllegalArgumentException("GeoCoordinate is invalid");
    }

    public double a() {
        return getNativeLatitude();
    }

    public void a(double d) {
        setNativeLatitude(d);
    }

    public double b() {
        return getNativeLongitude();
    }

    public void b(double d) {
        setNativeLongitude(d);
    }

    public double c() {
        return getNativeAltitude();
    }

    public void c(double d) {
        setNativeAltitude(d);
    }

    public double a(GeoCoordinateImpl geoCoordinateImpl) {
        if (geoCoordinateImpl.d()) {
            return distanceToNative(geoCoordinateImpl);
        }
        throw new IllegalArgumentException("GeoCoordinate is invalid");
    }

    public boolean d() {
        if (!isValidNative() || Math.abs(a()) > 90.0d) {
            return false;
        }
        return true;
    }

    public double b(GeoCoordinateImpl geoCoordinateImpl) {
        if (geoCoordinateImpl.d()) {
            return getHeadingNative(geoCoordinateImpl);
        }
        throw new IllegalArgumentException("GeoCoordinate is invalid");
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (GeoCoordinateImpl.class.isInstance(obj)) {
            obj = (GeoCoordinateImpl) obj;
        } else if (!GeoCoordinate.class.isInstance(obj)) {
            return false;
        } else {
            obj = get((GeoCoordinate) obj);
        }
        return isEqual(obj);
    }

    public String toString() {
        return String.format("GeoCoordinate [Latitude=%f Longitude=%f Altitude=%f Valid=%B]", new Object[]{Double.valueOf(a()), Double.valueOf(b()), Double.valueOf(c()), Boolean.valueOf(d())});
    }

    public int hashCode() {
        return ((((((int) getNativeLatitude()) + 31) * 31) + ((int) getNativeLongitude())) * 31) + ((int) getNativeAltitude());
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyGeoCoordinateNative();
        }
    }

    public double a(GeoCoordinate geoCoordinate) {
        return a(get(geoCoordinate));
    }
}
