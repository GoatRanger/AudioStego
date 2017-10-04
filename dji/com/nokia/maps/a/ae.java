package com.nokia.maps.a;

import com.here.a.a.a.a.m;
import com.here.a.a.a.a.z;
import com.here.a.a.a.g;
import com.here.a.a.a.l;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.urbanmobility.Departure;
import com.here.android.mpa.urbanmobility.MultiBoardRequest;
import com.here.android.mpa.urbanmobility.MultiBoardResult;
import com.here.android.mpa.urbanmobility.RequestManager$ResponseListener;
import com.here.android.mpa.urbanmobility.StationWithDepartureBoard;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class ae extends b<MultiBoardResult, z, g> {
    private static am<MultiBoardRequest, ae> b;

    protected /* synthetic */ Object b(Object obj) {
        return a((z) obj);
    }

    public ae(String str, String str2, String str3, GeoCoordinate geoCoordinate, RequestManager$ResponseListener<MultiBoardResult> requestManager$ResponseListener) {
        super(46, new g(str, str2, str3, new m(geoCoordinate.getLatitude(), geoCoordinate.getLongitude())), (RequestManager$ResponseListener) requestManager$ResponseListener);
    }

    public void a(Date date) {
        ((g) this.a).a(date);
    }

    public void a(boolean z) {
        ((g) this.a).a(z);
    }

    public void a(Set<String> set) {
        ((g) this.a).a((Set) set);
    }

    public void b(int i) {
        ((g) this.a).c(Integer.valueOf(i));
    }

    public void c(int i) {
        ((g) this.a).a(Integer.valueOf(i));
    }

    protected l<z, g> b() {
        return l.d();
    }

    protected MultiBoardResult a(z zVar) {
        af afVar = new af(zVar);
        Iterator it = afVar.a().iterator();
        boolean z = false;
        while (!z && it.hasNext()) {
            Iterator it2 = ((StationWithDepartureBoard) it.next()).getDepartureBoard().getDepartures().iterator();
            while (!z && it2.hasNext()) {
                if (((Departure) it2.next()).hasRealTimeInfo()) {
                    z = true;
                }
            }
        }
        com.nokia.maps.l.a().c(z, false);
        return af.a(afVar);
    }

    protected void c() {
        com.nokia.maps.l.a().c(false, true);
    }

    public void a(int i) {
        ((g) this.a).b(Integer.valueOf(i));
    }

    public int a() {
        return ((g) this.a).c() != null ? ((g) this.a).c().intValue() : -1;
    }

    public static MultiBoardRequest a(ae aeVar) {
        if (aeVar != null) {
            return (MultiBoardRequest) b.a(aeVar);
        }
        return null;
    }

    public static void a(am<MultiBoardRequest, ae> amVar) {
        b = amVar;
    }

    static {
        ce.a(MultiBoardRequest.class);
    }
}
