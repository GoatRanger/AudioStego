package com.here.android.mpa.routing;

import com.nokia.maps.RouteManagerImpl;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Internal;
import com.nokia.maps.annotation.Online;
import java.util.List;

@Deprecated
public class RouteManager {
    private static volatile RouteManager a = null;
    private static Object b = new Object();
    private final RouteManagerImpl c = new RouteManagerImpl();

    @Online
    @Deprecated
    public enum Error {
        NONE(0),
        UNKNOWN(1),
        OUT_OF_MEMORY(2),
        INVALID_PARAMETERS(3),
        INVALID_OPERATION(4),
        GRAPH_DISCONNECTED(5),
        GRAPH_DISCONNECTED_CHECK_OPTIONS(6),
        NO_START_POINT(7),
        NO_END_POINT(8),
        NO_END_POINT_CHECK_OPTIONS(9),
        CANNOT_DO_PEDESTRIAN(10),
        ROUTING_CANCELLED(11),
        VIOLATES_OPTIONS(12),
        ROUTE_CORRUPTED(13),
        INVALID_CREDENTIALS(14),
        REQUEST_TIMEOUT(15),
        OPERATION_NOT_ALLOWED(16),
        NO_CONNECTIVITY(17),
        INSUFFICIENT_MAP_DATA(18);
        
        private int a;

        private Error(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    @Online
    @Deprecated
    public interface Listener {
        @Deprecated
        void onCalculateRouteFinished(Error error, List<RouteResult> list);

        @Deprecated
        void onProgress(int i);
    }

    @Internal
    @Deprecated
    public enum a {
        AUTO(0),
        ONLINE(1),
        OFFLINE(2);
        
        private int d;

        public static a[] a() {
            return (a[]) e.clone();
        }

        private a(int i) {
            this.d = i;
        }
    }

    static {
        RouteManagerImpl.a(new 1());
    }

    @Online
    @Deprecated
    public Error calculateRoute(RoutePlan routePlan, Listener listener) {
        return this.c.a(this, routePlan, listener);
    }

    @Online
    @Deprecated
    public void cancel() {
        this.c.a();
    }

    @Online
    @Deprecated
    public boolean isBusy() {
        return this.c.b();
    }

    @HybridPlus
    @Deprecated
    public Route$TrafficPenaltyMode getTrafficPenaltyMode() {
        return this.c.c();
    }

    @HybridPlus
    @Deprecated
    public RouteManager setTrafficPenaltyMode(Route$TrafficPenaltyMode route$TrafficPenaltyMode) {
        this.c.setTrafficPenaltyMode(route$TrafficPenaltyMode.value());
        return this;
    }

    @HybridPlus
    @Deprecated
    public RouteManager setDynamicPenalty(DynamicPenalty dynamicPenalty) {
        this.c.a(dynamicPenalty);
        return this;
    }

    @HybridPlus
    @Deprecated
    public DynamicPenalty getDynamicPenalty() {
        return this.c.d();
    }

    @Internal
    @Deprecated
    public a a() {
        return this.c.f();
    }
}
