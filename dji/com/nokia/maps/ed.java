package com.nokia.maps;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.alipay.sdk.j.i;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.routing.Route.TrafficPenaltyMode;
import com.here.android.mpa.routing.RouteManager;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RouteOptions.TimeType;
import com.here.android.mpa.routing.RouteOptions.TransportMode;
import com.here.android.mpa.routing.RouteOptions.Type;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.search.TransitDeparture;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

abstract class ed extends cn<Void, String> {
    private final RouteManager a;

    private enum a {
        IMPERIAL("imperial"),
        METRIC("metric");
        
        private String c;

        private a(String str) {
            this.c = str;
        }

        public String a() {
            return this.c;
        }
    }

    enum b {
        TOLL_ROAD("tollroad"),
        MOTORWAY("motorway"),
        BOAT_FERRY("boatFerry"),
        RAIL_FERRY("railFerry"),
        PUBLIC_TRANSPORT("publicTransport"),
        TUNNEL("tunnel"),
        DIRT_ROAD("dirtRoad"),
        PARK("park"),
        HOV_LANE("HOVLane"),
        STAIRS("stairs");
        
        private String k;

        private b(String str) {
            this.k = str;
        }

        public String a() {
            return this.k;
        }
    }

    private enum c {
        STRICT_EXCLUDE("-3"),
        SOFT_EXCLUDE("-2"),
        AVOID(WeiboAuthException.DEFAULT_AUTH_ERROR_CODE),
        NORMAL("0"),
        PREFER("1");
        
        private String f;

        private c(String str) {
            this.f = str;
        }

        public String a() {
            return this.f;
        }
    }

    enum d {
        INFO("info"),
        WARNING("warning"),
        RESTRICTION("restriction"),
        VIOLATION("violation"),
        TRAFFIC("traffic");
        
        private String f;

        private d(String str) {
            this.f = str;
        }

        public String a() {
            return this.f;
        }
    }

    private enum e {
        FASTEST("fastest"),
        SHORTEST("shortest");
        
        private String c;

        private e(String str) {
            this.c = str;
        }

        public String a() {
            return this.c;
        }
    }

    private enum f {
        ENABLED("enabled"),
        DISABLED("disabled"),
        DEFAULT("default");
        
        private String d;

        private f(String str) {
            this.d = str;
        }

        public String a() {
            return this.d;
        }
    }

    enum g {
        CAR("car"),
        PEDESTRIAN("pedestrian"),
        TRUCK("truck"),
        PUBLICTRANSPORTTIMETABLE("publicTransportTimeTable");
        
        private String e;

        private g(String str) {
            this.e = str;
        }

        public String a() {
            return this.e;
        }
    }

    ed(RouteManager routeManager) {
        this.a = routeManager;
    }

    protected RouteManager a() {
        return this.a;
    }

    protected String a(String str, RoutePlan routePlan, Map<String, String> map) {
        int i;
        Builder buildUpon = Uri.parse(str + "calculateroute.json").buildUpon();
        String applicationId = ConnectionInfoImpl.getApplicationId();
        String applicationCode = ConnectionInfoImpl.getApplicationCode();
        if (applicationId != null && applicationId.length() > 0) {
            buildUpon.appendQueryParameter("app_id", applicationId);
        }
        if (applicationCode != null && applicationCode.length() > 0) {
            buildUpon.appendQueryParameter("app_code", applicationCode);
        }
        int waypointCount = routePlan.getWaypointCount();
        for (i = 0; i < waypointCount; i++) {
            GeoCoordinate waypointAt = routePlan.getWaypointAt(i);
            buildUpon.appendQueryParameter(dji.pilot.dji_groundstation.controller.e.A + i, "geo!" + waypointAt.getLatitude() + "," + waypointAt.getLongitude());
        }
        RouteOptions routeOptions = routePlan.getRouteOptions();
        i = routePlan.getRouteOptions().getRouteCount();
        if (routeOptions.getTransportMode() == TransportMode.PUBLIC_TRANSPORT) {
            i = Math.min(i, 10);
        }
        if (i > 1) {
            buildUpon.appendQueryParameter("alternatives", String.valueOf(i - 1));
        }
        Date date = new Date();
        if (routeOptions.getTime(date) == TimeType.DEPARTURE) {
            buildUpon.appendQueryParameter(TransitDeparture.DEPARTURE_TIME_KEY_NAME, ee.a(date));
        }
        if (routeOptions.getRouteType() == Type.SHORTEST) {
            applicationId = e.SHORTEST.a();
        } else {
            applicationId = e.FASTEST.a();
        }
        StringBuilder stringBuilder = new StringBuilder(applicationId);
        if (routeOptions.getTransportMode() == TransportMode.PUBLIC_TRANSPORT) {
            stringBuilder.append(i.b).append(g.PUBLICTRANSPORTTIMETABLE.a());
        } else {
            stringBuilder.append(i.b).append(g.TRUCK.a());
        }
        stringBuilder.append(i.b).append("traffic").append(":").append(a().getTrafficPenaltyMode() == TrafficPenaltyMode.DISABLED ? f.DISABLED.a() : f.ENABLED.a());
        String a = c.SOFT_EXCLUDE.a();
        CharSequence stringBuilder2 = new StringBuilder();
        if (!routeOptions.areTollRoadsAllowed()) {
            stringBuilder2.append(stringBuilder2.length() == 0 ? i.b : ",").append(b.TOLL_ROAD.a()).append(":").append(a);
        }
        if (!routeOptions.areHighwaysAllowed()) {
            stringBuilder2.append(stringBuilder2.length() == 0 ? i.b : ",").append(b.MOTORWAY.a()).append(":").append(a);
        }
        if (!routeOptions.areFerriesAllowed()) {
            stringBuilder2.append(stringBuilder2.length() == 0 ? i.b : ",").append(b.BOAT_FERRY.a()).append(":").append(a);
            stringBuilder2.append(stringBuilder2.length() == 0 ? i.b : ",").append(b.RAIL_FERRY.a()).append(":").append(a);
        }
        if (!routeOptions.areTunnelsAllowed()) {
            stringBuilder2.append(stringBuilder2.length() == 0 ? i.b : ",").append(b.TUNNEL.a()).append(":").append(a);
        }
        if (!routeOptions.areDirtRoadsAllowed()) {
            stringBuilder2.append(stringBuilder2.length() == 0 ? i.b : ",").append(b.DIRT_ROAD.a()).append(":").append(a);
        }
        if (!routeOptions.areParksAllowed()) {
            stringBuilder2.append(stringBuilder2.length() == 0 ? i.b : ",").append(b.PARK.a()).append(":").append(a);
        }
        if (!(routeOptions.getTransportMode() == TransportMode.PUBLIC_TRANSPORT || routeOptions.isCarpoolAllowed())) {
            stringBuilder2.append(stringBuilder2.length() == 0 ? i.b : ",").append(b.HOV_LANE.a()).append(":").append(a);
        }
        stringBuilder.append(stringBuilder2);
        buildUpon.appendQueryParameter("mode", stringBuilder.toString());
        if (routeOptions.getTransportMode() == TransportMode.PUBLIC_TRANSPORT) {
            buildUpon.appendQueryParameter("routeattributes", "wp,sm,bb,lg,no,li,sc");
            buildUpon.appendQueryParameter("maneuverattributes", "bb,po,tt,le,ti,li,rn,nr,ru,nu,no,ac,di,bt,tm,pt,sa,wt");
            buildUpon.appendQueryParameter("linkAttributes", "sh,le,sl,ds,fl,rn,ro,rt,rd,ma,pt,ns");
        } else {
            buildUpon.appendQueryParameter("routeattributes", "wp,sm,bb,lg,no");
            buildUpon.appendQueryParameter("maneuverattributes", "bb,po,tt,le,ti,li,rn,nr,ru,nu,no,ac,di,bt,tm");
            buildUpon.appendQueryParameter("linkAttributes", "sh,le,sl,ds,fl,rn,ro,rt,rd,ma,sc,ic,tr,ad,tz,jf,jt");
        }
        buildUpon.appendQueryParameter("legAttributes", "mn,li,le");
        buildUpon.appendQueryParameter("instructionformat", "text");
        if (Locale.getDefault().getCountry().isEmpty()) {
            applicationId = Locale.getDefault().getLanguage();
        } else {
            applicationId = Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry();
        }
        buildUpon.appendQueryParameter(dji.pilot.college.b.b.n, applicationId);
        if (routeOptions.getTransportMode() == TransportMode.PUBLIC_TRANSPORT) {
            i = routeOptions.getTransitMaximumChanges();
            if (i >= 0) {
                buildUpon.appendQueryParameter("maxnumberofchanges", String.valueOf(Math.min(i, 10)));
            }
            applicationId = ee.a(routeOptions);
            if (applicationId.length() > 0) {
                buildUpon.appendQueryParameter("avoidTransportTypes", applicationId);
            }
        }
        for (Entry entry : map.entrySet()) {
            buildUpon.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        for (Entry entry2 : RouteOptionsImpl.get(routeOptions).c().entrySet()) {
            buildUpon.appendQueryParameter((String) entry2.getKey(), (String) entry2.getValue());
        }
        buildUpon.appendQueryParameter("metricSystem", a.METRIC.a());
        buildUpon.appendQueryParameter("jsonAttributes", "41");
        return buildUpon.build().toString();
    }

    @SuppressLint({"NewApi"})
    protected void a(String str) {
        if (VERSION.SDK_INT >= 11) {
            executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{str});
            return;
        }
        execute(new String[]{str});
    }
}
