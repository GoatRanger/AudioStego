package com.nokia.maps.a;

import com.here.a.a.a.a.m;
import com.here.a.a.a.a.n;
import com.here.android.mpa.common.GeoCoordinate;
import java.util.ArrayList;
import java.util.List;

public class u {
    public static GeoCoordinate a(m mVar) {
        return mVar.c.c() ? new GeoCoordinate(mVar.a, mVar.b, ((Double) mVar.c.b()).doubleValue()) : new GeoCoordinate(mVar.a, mVar.b);
    }

    public static boolean a(GeoCoordinate geoCoordinate, GeoCoordinate geoCoordinate2) {
        return Math.abs(geoCoordinate.getLongitude() - geoCoordinate2.getLongitude()) > 1.0E-6d || Math.abs(geoCoordinate.getLatitude() - geoCoordinate2.getLatitude()) > 1.0E-6d || Math.abs(geoCoordinate.getAltitude() - geoCoordinate2.getAltitude()) > 1.0E-6d;
    }

    public static List<GeoCoordinate> a(n nVar) {
        List<GeoCoordinate> arrayList = new ArrayList(nVar.a().size());
        for (m a : nVar.a()) {
            arrayList.add(a(a));
        }
        return arrayList;
    }
}
