package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPolygon;
import com.here.android.mpa.common.GeoPolyline;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.List;

@Online
public class GeoPolylineImpl extends GeoArea {
    private static String a = GeoPolylineImpl.class.getSimpleName();
    private static k<GeoPolyline, GeoPolylineImpl> b;
    private static am<GeoPolyline, GeoPolylineImpl> c;

    private native void addNative(GeoCoordinateImpl geoCoordinateImpl);

    private native void addNative(Object[] objArr);

    private native void createGeoPolyline(GeoCoordinateImpl[] geoCoordinateImplArr);

    private native GeoCoordinateImpl getPointNative(int i);

    private native void insertNative(GeoCoordinateImpl geoCoordinateImpl, int i);

    public native void clear();

    public native int getNumberOfPoints();

    public native double length();

    public native void remove(int i);

    static {
        ce.a(GeoPolyline.class);
    }

    static GeoPolylineImpl a(GeoPolyline geoPolyline) {
        if (b != null) {
            return (GeoPolylineImpl) b.a(geoPolyline);
        }
        return null;
    }

    static GeoPolyline a(GeoPolylineImpl geoPolylineImpl) {
        if (geoPolylineImpl != null) {
            return (GeoPolyline) c.a(geoPolylineImpl);
        }
        return null;
    }

    public static void b(k<GeoPolyline, GeoPolylineImpl> kVar, am<GeoPolyline, GeoPolylineImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    public GeoPolylineImpl() {
        super(0);
        createGeoPolyline(null);
    }

    public GeoPolylineImpl(List<GeoCoordinate> list) {
        super(0);
        if (list.size() <= 0) {
            throw new IllegalArgumentException("GeoCoordinate[] points is empty.");
        }
        createGeoPolyline(GeoCoordinateImpl.a((List) list));
    }

    @OnlineNative
    protected GeoPolylineImpl(int i) {
        super(i);
    }

    public void a(List<GeoCoordinate> list) {
        if (list.size() == 0) {
            throw new IllegalArgumentException("GeoCoordinate[] points is empty.");
        }
        addNative(GeoCoordinateImpl.a((List) list));
    }

    public void b(List<GeoCoordinateImpl> list) {
        if (list.size() == 0) {
            throw new IllegalArgumentException("GeoCoordinate[] points is empty.");
        }
        addNative(list.toArray());
    }

    public void b(GeoCoordinate geoCoordinate) {
        a(GeoCoordinateImpl.get(geoCoordinate));
    }

    public void a(GeoCoordinateImpl geoCoordinateImpl) {
        if (geoCoordinateImpl.d()) {
            addNative(geoCoordinateImpl);
            return;
        }
        throw new IllegalArgumentException("GeoCoordinate point is invalid.");
    }

    public void a(GeoCoordinate geoCoordinate, int i) {
        GeoCoordinateImpl geoCoordinateImpl = GeoCoordinateImpl.get(geoCoordinate);
        if (!geoCoordinateImpl.d()) {
            throw new IllegalArgumentException("GeoCoordinate point is invalid.");
        } else if (i < 0 || i > getNumberOfPoints()) {
            throw new IllegalArgumentException("Index is out of bounds.");
        } else {
            insertNative(geoCoordinateImpl, i);
        }
    }

    public GeoCoordinate a(int i) {
        return GeoCoordinateImpl.create(b(i));
    }

    public GeoCoordinateImpl b(int i) {
        if (i >= 0 && i < getNumberOfPoints()) {
            return getPointNative(i);
        }
        throw new IllegalArgumentException("Index out of bounds.");
    }

    public int c(GeoCoordinate geoCoordinate) {
        int i = -1;
        if (geoCoordinate.isValid()) {
            double d = Double.MAX_VALUE;
            for (int i2 = 0; i2 < getNumberOfPoints(); i2++) {
                double distanceTo = a(i2).distanceTo(geoCoordinate);
                if (distanceTo < d) {
                    d = distanceTo;
                    i = i2;
                }
            }
            return i;
        }
        throw new IllegalArgumentException("GeoCoordinate point is invalid.");
    }

    public GeoCoordinate d(GeoCoordinate geoCoordinate) {
        if (c(geoCoordinate) < 0) {
            return null;
        }
        return a(c(geoCoordinate));
    }

    public int hashCode() {
        int i = this.nativeptr + 589;
        for (int i2 = 0; i2 < getNumberOfPoints(); i2++) {
            i = (i * 31) + a(i2).hashCode();
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (GeoPolyline.class.isInstance(obj)) {
            obj = a((GeoPolyline) obj);
        } else if (GeoPolygon.class.isInstance(obj)) {
            obj = GeoPolygonImpl.a((GeoPolygon) obj);
        } else if (!GeoPolylineImpl.class.isInstance(obj)) {
            return false;
        } else {
            GeoPolylineImpl geoPolylineImpl = (GeoPolylineImpl) obj;
        }
        if (obj == null || obj.getNumberOfPoints() != getNumberOfPoints()) {
            return false;
        }
        for (int i = 0; i < getNumberOfPoints(); i++) {
            if (!obj.a(i).equals(a(i))) {
                return false;
            }
        }
        return true;
    }
}
