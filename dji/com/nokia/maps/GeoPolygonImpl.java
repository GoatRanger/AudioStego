package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPolygon;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.List;

@Online
public class GeoPolygonImpl extends GeoPolylineImpl {
    private static String a = GeoPolygonImpl.class.getSimpleName();
    private static k<GeoPolygon, GeoPolygonImpl> b;
    private static am<GeoPolygon, GeoPolygonImpl> c;

    private native void createGeoPolygonNative(GeoCoordinateImpl[] geoCoordinateImplArr);

    static {
        ce.a(GeoPolygon.class);
    }

    static GeoPolygonImpl a(GeoPolygon geoPolygon) {
        if (b != null) {
            return (GeoPolygonImpl) b.a(geoPolygon);
        }
        return null;
    }

    public static void a(k<GeoPolygon, GeoPolygonImpl> kVar, am<GeoPolygon, GeoPolygonImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    public GeoPolygonImpl() {
        super(0);
        createGeoPolygonNative(null);
        bj.a(a, "OUT nativeptr=0x%08x", new Object[]{Integer.valueOf(this.nativeptr)});
    }

    public GeoPolygonImpl(List<GeoCoordinate> list) {
        super(0);
        if (list.size() <= 0) {
            throw new IllegalArgumentException("GeoCoordinate[] points is empty.");
        }
        createGeoPolygonNative(GeoCoordinateImpl.a((List) list));
        bj.a(a, "OUT nativeptr=0x%08x", new Object[]{Integer.valueOf(this.nativeptr)});
    }

    @OnlineNative
    protected GeoPolygonImpl(int i) {
        super(i);
        bj.a(a, "OUT nativeptr=0x%08x", new Object[]{Integer.valueOf(this.nativeptr)});
    }
}
