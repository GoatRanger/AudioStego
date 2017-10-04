package com.nokia.maps.a;

import com.here.a.a.a.a.aa;
import com.here.a.a.a.a.m;
import com.here.a.a.a.h;
import com.here.a.a.a.l;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.urbanmobility.NearbyCoverageRequest;
import com.here.android.mpa.urbanmobility.NearbyCoverageResult;
import com.here.android.mpa.urbanmobility.RequestManager$ResponseListener;
import com.nokia.maps.MapsEngine;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.Locale;

public class ag extends c<NearbyCoverageResult, aa, h> {
    private static am<NearbyCoverageRequest, ag> b;

    protected /* synthetic */ Object b(Object obj) {
        return a((aa) obj);
    }

    public ag(String str, String str2, String str3, GeoCoordinate geoCoordinate, RequestManager$ResponseListener<NearbyCoverageResult> requestManager$ResponseListener) {
        super(48, new h(str, str2, str3, new m(geoCoordinate.getLatitude(), geoCoordinate.getLongitude()), MapsEngine.g().equals(Locale.CHINA.getISO3Country())), (RequestManager$ResponseListener) requestManager$ResponseListener);
    }

    public void a(boolean z) {
        ((h) this.a).a(Boolean.valueOf(z));
    }

    protected l<aa, h> b() {
        return l.e();
    }

    protected NearbyCoverageResult a(aa aaVar) {
        com.nokia.maps.l.a().i(false);
        return ah.a(new ah(aaVar));
    }

    protected void c() {
        com.nokia.maps.l.a().i(true);
    }

    public static NearbyCoverageRequest a(ag agVar) {
        if (agVar != null) {
            return (NearbyCoverageRequest) b.a(agVar);
        }
        return null;
    }

    public static void a(am<NearbyCoverageRequest, ag> amVar) {
        b = amVar;
    }

    static {
        ce.a(NearbyCoverageRequest.class);
    }
}
