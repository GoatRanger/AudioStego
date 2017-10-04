package com.nokia.maps;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.android.mpa.routing.RouteManager;
import com.here.android.mpa.routing.RouteManager.Error;
import com.here.android.mpa.routing.RouteManager.Listener;
import com.here.android.mpa.routing.RouteManager.a;
import com.here.android.mpa.routing.RouteOptions.TimeType;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteResult;
import com.here.android.mpa.routing.RouteResult.ViolatedOption;
import com.here.android.mpa.search.ErrorCode;
import com.nokia.maps.restrouting.Note;
import com.nokia.maps.restrouting.Route;
import com.nokia.maps.restrouting.RoutingRestResult;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class et extends ed {
    private RoutePlan a;
    private Listener b;
    private String c;
    private ApplicationContext$c d = new TransitRouteRestHandler$1(this);

    protected /* synthetic */ Object a(byte[] bArr) throws ak {
        return b(bArr);
    }

    protected /* synthetic */ void a(Object obj) {
        b((String) obj);
    }

    et(RouteManager routeManager) {
        super(routeManager);
    }

    public Error a(RoutePlan routePlan, Listener listener) {
        this.a = RoutePlanImpl.a(new RoutePlanImpl(routePlan));
        this.b = listener;
        return b();
    }

    private synchronized Error b() {
        Error error;
        error = Error.NONE;
        boolean z = false;
        try {
            z = MapsEngine.c().isOnline();
        } catch (Exception e) {
        }
        a a = a().a();
        if ((a == a.a || a == a.b) && r1 && this.a.getWaypointCount() == 2) {
            ApplicationContext.b().check(12, this.d);
        } else {
            error = RouteManagerImpl.a(a()).a(this.a, this.b);
        }
        return error;
    }

    private void c() {
        Map hashMap = new HashMap();
        hashMap.put("lineattributes", "all");
        hashMap.put("combineChange", "true");
        Date date = new Date();
        if (this.a.getRouteOptions().getTime(date) == TimeType.ARRIVAL) {
            hashMap.put("arrival", ee.a(date));
        }
        a(a(MapsEngine.s(), this.a, hashMap));
    }

    protected String b(byte[] bArr) throws ak {
        return new String(bArr, Charset.forName("UTF-8"));
    }

    protected void b(String str) {
        Object obj = null;
        List<eh> arrayList = new ArrayList();
        Error error = Error.NONE;
        this.c = str;
        if (this.c != null) {
            RoutingRestResult routingRestResult = (RoutingRestResult) eg.a().a(this.c, RoutingRestResult.class);
            if (!(routingRestResult == null || routingRestResult.a() == null || routingRestResult.a().a().size() <= 0)) {
                Error error2 = error;
                for (Route route : routingRestResult.a().a()) {
                    eh ehVar = new eh();
                    ehVar.a(new eu(this.a, route, routingRestResult.a().b()));
                    EnumSet noneOf = EnumSet.noneOf(ViolatedOption.class);
                    for (Note note : route.d()) {
                        if (note.a().contentEquals(d.VIOLATION.a())) {
                            error2 = Error.VIOLATES_OPTIONS;
                            String toLowerCase = note.c().replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "").toLowerCase();
                            if (toLowerCase.contentEquals(b.TOLL_ROAD.a().toLowerCase())) {
                                noneOf.add(ViolatedOption.AVOID_TOLL_ROADS);
                            } else if (toLowerCase.contentEquals(b.MOTORWAY.a().toLowerCase())) {
                                noneOf.add(ViolatedOption.AVOID_HIGHWAYS);
                            } else if (toLowerCase.contentEquals(b.BOAT_FERRY.a().toLowerCase())) {
                                noneOf.add(ViolatedOption.AVOID_FERRIES);
                            } else if (toLowerCase.contentEquals(b.RAIL_FERRY.a().toLowerCase())) {
                                noneOf.add(ViolatedOption.AVOID_FERRIES);
                            } else if (toLowerCase.contentEquals(b.TUNNEL.a().toLowerCase())) {
                                noneOf.add(ViolatedOption.AVOID_TUNNELS);
                            } else if (toLowerCase.contentEquals(b.DIRT_ROAD.a().toLowerCase())) {
                                noneOf.add(ViolatedOption.AVOID_DIRT_ROADS);
                            } else if (toLowerCase.contentEquals(b.PARK.a().toLowerCase())) {
                                noneOf.add(ViolatedOption.AVOID_PARKS);
                            } else if (toLowerCase.contentEquals(b.HOV_LANE.a().toLowerCase())) {
                                noneOf.add(ViolatedOption.CARPOOL);
                            }
                        }
                    }
                    ehVar.a(noneOf);
                    arrayList.add(ehVar);
                }
                error = error2;
                obj = 1;
            }
        }
        final List arrayList2 = new ArrayList();
        for (eh a : arrayList) {
            arrayList2.add(eh.a(a));
        }
        if (obj != null) {
            ez.a(new Runnable(this) {
                final /* synthetic */ et c;

                public void run() {
                    int i = 0;
                    this.c.b.onCalculateRouteFinished(error, arrayList2);
                    if (error == Error.NONE && arrayList2.size() > 0) {
                        i = ((RouteResult) arrayList2.get(0)).getRoute().getLength();
                    }
                    l.a().a(i);
                }
            });
        } else {
            RouteManagerImpl.a(a()).a(this.a, this.b);
        }
    }

    protected void a(ErrorCode errorCode) {
        RouteManagerImpl.a(a()).a(this.a, this.b);
    }
}
