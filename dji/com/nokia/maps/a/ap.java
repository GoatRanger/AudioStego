package com.nokia.maps.a;

import com.here.a.a.a.a.m;
import com.here.a.a.a.i.c;
import com.here.a.a.a.j;
import com.here.android.mpa.common.TransitType;
import com.here.android.mpa.routing.RouteOptions.TimeType;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.UMRouteOptions;
import com.here.android.mpa.urbanmobility.RequestManager$ResponseListener;
import com.here.android.mpa.urbanmobility.TransportType;
import com.nokia.maps.RoutePlanImpl;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ap extends ao<j> {
    public ap(String str, String str2, String str3, RoutePlan routePlan, RequestManager$ResponseListener<h> requestManager$ResponseListener) {
        Integer num;
        Boolean valueOf;
        super(new j(str, str2, str3, new m(routePlan.getWaypoint(0).getOriginalPosition().getLatitude(), routePlan.getWaypoint(0).getOriginalPosition().getLongitude()), new m(routePlan.getWaypoint(1).getOriginalPosition().getLatitude(), routePlan.getWaypoint(1).getOriginalPosition().getLongitude())), routePlan, requestManager$ResponseListener);
        UMRouteOptions b = RoutePlanImpl.a(routePlan).b();
        b(Locale.getDefault().getLanguage());
        ((j) this.a).a(Integer.valueOf(b.getRouteCount()));
        ((j) this.a).b(Boolean.valueOf(b.isStrictRouteCountEnabled()));
        List arrayList = new ArrayList();
        for (TransitType transitType : TransitType.values()) {
            if (b.isPublicTransportTypeAllowed(transitType)) {
                arrayList.add(ba.a(transitType));
            }
        }
        if (!b.areFerriesAllowed()) {
            arrayList.remove(TransportType.WATER_BOAT_OR_FERRYS);
        }
        b(arrayList);
        ((j) this.a).a(c.ALL);
        ((j) this.a).c(Boolean.valueOf(true));
        j jVar = (j) this.a;
        if (b.getTransitMaximumChanges() < 0) {
            num = null;
        } else {
            num = Integer.valueOf(b.getTransitMaximumChanges());
        }
        jVar.b(num);
        ((j) this.a).d(Integer.valueOf(Float.valueOf(b.getTransitWalkTimeMultiplier() * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity).intValue()));
        jVar = (j) this.a;
        if (b.getTransitWalkMaxDistance() < 0) {
            num = null;
        } else {
            num = Integer.valueOf(b.getTransitWalkMaxDistance());
        }
        jVar.c(num);
        Date date = new Date();
        TimeType time = b.getTime(date);
        ((j) this.a).a(date);
        jVar = (j) this.a;
        if (time != null) {
            valueOf = Boolean.valueOf(TimeType.ARRIVAL == time);
        } else {
            valueOf = null;
        }
        jVar.a(valueOf);
    }

    public void a(int i) {
        ((j) this.a).a(Integer.valueOf(i));
    }

    public int a() {
        return ((j) this.a).c() != null ? ((j) this.a).c().intValue() : -1;
    }
}
