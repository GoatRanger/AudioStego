package com.nokia.maps.a;

import com.here.a.a.a.a.ab;
import com.here.a.a.a.a.m;
import com.here.a.a.a.l;
import com.here.a.a.a.q;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.urbanmobility.Departure;
import com.here.android.mpa.urbanmobility.DepartureBoard;
import com.here.android.mpa.urbanmobility.DepartureBoardRequest;
import com.here.android.mpa.urbanmobility.RequestManager$ResponseListener;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.Date;

public class p extends b<DepartureBoard, ab, q> {
    private static am<DepartureBoardRequest, p> c;
    private GeoCoordinate b;

    protected /* synthetic */ Object b(Object obj) {
        return a((ab) obj);
    }

    public p(String str, String str2, String str3, GeoCoordinate geoCoordinate, String str4, RequestManager$ResponseListener<DepartureBoard> requestManager$ResponseListener) {
        super(46, new q(str, str2, str3, new m(geoCoordinate.getLatitude(), geoCoordinate.getLongitude()), str4), (RequestManager$ResponseListener) requestManager$ResponseListener);
        this.b = geoCoordinate;
    }

    public void a(boolean z) {
        ((q) this.a).b(Boolean.valueOf(z));
    }

    public void a(Date date) {
        ((q) this.a).a(date);
    }

    public void b(boolean z) {
        ((q) this.a).a(Boolean.valueOf(z));
    }

    protected l<ab, q> b() {
        return l.b();
    }

    protected DepartureBoard a(ab abVar) {
        boolean z;
        o oVar = new o(abVar);
        for (Departure hasRealTimeInfo : oVar.a()) {
            if (hasRealTimeInfo.hasRealTimeInfo()) {
                z = true;
                break;
            }
        }
        z = false;
        com.nokia.maps.l.a().b(z, false);
        return o.a(oVar);
    }

    protected void c() {
        com.nokia.maps.l.a().b(false, true);
    }

    public void a(int i) {
        ((q) this.a).a(Integer.valueOf(i));
    }

    public int a() {
        return ((q) this.a).o() != null ? ((q) this.a).o().intValue() : -1;
    }

    public String i() {
        return ((q) this.a).c();
    }

    public GeoCoordinate j() {
        return this.b;
    }

    public boolean k() {
        return ((q) this.a).j() != null ? ((q) this.a).j().booleanValue() : false;
    }

    public Date l() {
        return ((q) this.a).n();
    }

    public boolean m() {
        return ((q) this.a).p() != null ? ((q) this.a).p().booleanValue() : false;
    }

    public static DepartureBoardRequest a(p pVar) {
        if (pVar != null) {
            return (DepartureBoardRequest) c.a(pVar);
        }
        return null;
    }

    public static void a(am<DepartureBoardRequest, p> amVar) {
        c = amVar;
    }

    static {
        ce.a(DepartureBoardRequest.class);
    }
}
