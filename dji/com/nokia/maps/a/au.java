package com.nokia.maps.a;

import com.here.a.a.a.a.ad;
import com.here.a.a.a.a.al;
import com.here.a.a.a.i;
import com.here.a.a.a.l;
import com.here.a.a.a.m;
import com.here.a.a.a.n;
import com.here.a.a.a.n.a;
import com.here.a.a.a.o;
import com.here.a.a.a.p;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.urbanmobility.RequestManager$ResponseListener;
import com.here.android.mpa.urbanmobility.StationSearchRequest;
import com.here.android.mpa.urbanmobility.StationSearchRequest$NameMatchingMethod;
import com.here.android.mpa.urbanmobility.StationSearchResult;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class au extends b<StationSearchResult, al, i> {
    private static am<StationSearchRequest, au> d;
    private StationSearchRequest$NameMatchingMethod b;
    private GeoCoordinate c;

    protected /* synthetic */ Object b(Object obj) {
        return a((al) obj);
    }

    public au(String str, String str2, String str3, GeoCoordinate geoCoordinate, String str4, RequestManager$ResponseListener<StationSearchResult> requestManager$ResponseListener) {
        i mVar;
        if (str4 == null || str4.isEmpty()) {
            mVar = new m(str, str2, str3, new com.here.a.a.a.a.m(geoCoordinate.getLatitude(), geoCoordinate.getLongitude()));
        } else {
            mVar = new n(str, str2, str3, new com.here.a.a.a.a.m(geoCoordinate.getLatitude(), geoCoordinate.getLongitude()), str4);
        }
        super(47, mVar, (RequestManager$ResponseListener) requestManager$ResponseListener);
        this.c = geoCoordinate;
    }

    public au(String str, String str2, String str3, String[] strArr, RequestManager$ResponseListener<StationSearchResult> requestManager$ResponseListener) {
        super(47, new p(str, str2, str3, new HashSet(Arrays.asList(strArr))), (RequestManager$ResponseListener) requestManager$ResponseListener);
    }

    public au(String str, String str2, String str3, Set<String> set, RequestManager$ResponseListener<StationSearchResult> requestManager$ResponseListener) {
        super(47, new p(str, str2, str3, set), (RequestManager$ResponseListener) requestManager$ResponseListener);
    }

    public void a(float f) {
        ad a = a(o.class);
        if (a.c()) {
            ((o) a.b()).a(Integer.valueOf(Math.round(f)));
        }
    }

    public void b(int i) {
        ad a = a(o.class);
        if (a.c()) {
            ((o) a.b()).a(Integer.valueOf(i));
        }
    }

    public void a(int i) {
        ad a = a(o.class);
        if (a.c()) {
            ((o) a.b()).b(Integer.valueOf(i));
        }
    }

    public int a() {
        ad a = a(o.class);
        Integer o = a.c() ? ((o) a.b()).o() : null;
        return o != null ? o.intValue() : -1;
    }

    public void a(Boolean bool) {
        ad a = a(o.class);
        if (a.c()) {
            ((o) a.b()).a(bool);
        }
    }

    public void a(StationSearchRequest$NameMatchingMethod stationSearchRequest$NameMatchingMethod) {
        this.b = stationSearchRequest$NameMatchingMethod;
        ad a = a(n.class);
        if (a.c()) {
            ((n) a.b()).a(b(stationSearchRequest$NameMatchingMethod));
        }
    }

    public int i() {
        ad a = a(o.class);
        Integer n = a.c() ? ((o) a.b()).n() : null;
        return n != null ? n.intValue() : -1;
    }

    public boolean j() {
        ad a = a(o.class);
        Boolean p = a.c() ? ((o) a.b()).p() : null;
        return p != null ? p.booleanValue() : false;
    }

    public StationSearchRequest$NameMatchingMethod k() {
        return this.b;
    }

    public String l() {
        ad a = a(n.class);
        return a.c() ? ((n) a.b()).c() : "";
    }

    public Collection<String> m() {
        ad a = a(p.class);
        if (a.c()) {
            return ((p) a.b()).c();
        }
        return Collections.emptySet();
    }

    public GeoCoordinate n() {
        return this.c;
    }

    protected l<al, i> b() {
        return l.c();
    }

    protected StationSearchResult a(al alVar) {
        a(false);
        return av.a(new av(alVar));
    }

    protected void c() {
        a(true);
    }

    private void a(boolean z) {
        if (this.a instanceof n) {
            com.nokia.maps.l.a().j(z);
        } else if (this.a instanceof m) {
            com.nokia.maps.l.a().l(z);
        } else {
            com.nokia.maps.l.a().k(z);
        }
    }

    private static a b(StationSearchRequest$NameMatchingMethod stationSearchRequest$NameMatchingMethod) {
        if (StationSearchRequest$NameMatchingMethod.FUZZY == stationSearchRequest$NameMatchingMethod) {
            return a.FUZZY;
        }
        if (StationSearchRequest$NameMatchingMethod.STRICT == stationSearchRequest$NameMatchingMethod) {
            return a.a;
        }
        if (stationSearchRequest$NameMatchingMethod == null) {
            return null;
        }
        throw new RuntimeException("Unknown value for Station NameMatchingMethod: " + stationSearchRequest$NameMatchingMethod);
    }

    private <T extends i> ad<T> a(Class<T> cls) {
        return ad.b(cls.isAssignableFrom(this.a.getClass()) ? (i) cls.cast(this.a) : null);
    }

    public static StationSearchRequest a(au auVar) {
        if (auVar != null) {
            return (StationSearchRequest) d.a(auVar);
        }
        return null;
    }

    public static void a(am<StationSearchRequest, au> amVar) {
        d = amVar;
    }

    static {
        ce.a(StationSearchRequest.class);
    }
}
