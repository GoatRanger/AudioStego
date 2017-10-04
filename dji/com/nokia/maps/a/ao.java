package com.nokia.maps.a;

import com.here.a.a.a.a.ai;
import com.here.a.a.a.i;
import com.here.a.a.a.j;
import com.here.a.a.a.l;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.UMRoute;
import com.here.android.mpa.routing.UMRouteResult;
import com.here.android.mpa.urbanmobility.ErrorCode;
import com.here.android.mpa.urbanmobility.RequestManager$ResponseListener;
import com.here.android.mpa.urbanmobility.RouteSection;
import java.util.Iterator;

public abstract class ao<UMReqType extends j> extends b<h, ai, UMReqType> {
    protected RoutePlan b;

    protected /* synthetic */ Object b(Object obj) {
        return a((ai) obj);
    }

    public ao(UMReqType uMReqType, RoutePlan routePlan, RequestManager$ResponseListener<h> requestManager$ResponseListener) {
        super(new int[]{12, 44}, (i) uMReqType, (RequestManager$ResponseListener) requestManager$ResponseListener);
        this.b = routePlan;
        ((j) this.a).d(Boolean.valueOf(true));
    }

    protected l<ai, UMReqType> b() {
        return l.a();
    }

    protected h a(ai aiVar) {
        h hVar = new h(this.b, aiVar);
        if (hVar.c().isEmpty()) {
            hVar.a(ErrorCode.INVALID_RESPONSE);
            com.nokia.maps.l.a().a(false, false, 0, true);
        } else {
            com.nokia.maps.l.a().a(a(hVar), b(hVar), ((UMRouteResult) hVar.c().get(0)).getRoute().getLength(), false);
        }
        return hVar;
    }

    protected void c() {
        com.nokia.maps.l.a().a(false, false, 0, true);
    }

    private static boolean a(h hVar) {
        for (UMRouteResult uMRoute : hVar.c()) {
            if (!uMRoute.getUMRoute().getTicketCollections().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static boolean b(h hVar) {
        boolean z = false;
        Iterator it = hVar.c().iterator();
        while (!z && it.hasNext()) {
            UMRoute uMRoute = ((UMRouteResult) it.next()).getUMRoute();
            if (uMRoute.getArrivalLocation().hasRealTimeInfo() || uMRoute.getDepartureLocation().hasRealTimeInfo()) {
                return true;
            }
            boolean z2;
            for (RouteSection routeSection : uMRoute.getSections()) {
                if (!routeSection.getArrivalLocation().hasRealTimeInfo()) {
                    if (routeSection.getDepartureLocation().hasRealTimeInfo()) {
                    }
                }
                z2 = true;
            }
            z2 = z;
            z = z2;
        }
        return z;
    }
}
