package com.here.android.mpa.routing;

import com.nokia.maps.al;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.util.List;

@HybridPlus
public final class CoreRouter implements Router<List<RouteResult>, RoutingError> {
    private final al a = new al();

    @HybridPlus
    public interface Listener extends com.here.android.mpa.routing.Router.Listener<List<RouteResult>, RoutingError> {
        void onCalculateRouteFinished(List<RouteResult> list, RoutingError routingError);
    }

    public void calculateRoute(RoutePlan routePlan, com.here.android.mpa.routing.Router.Listener<List<RouteResult>, RoutingError> listener) {
        this.a.a(routePlan, (com.here.android.mpa.routing.Router.Listener) listener);
    }

    public void cancel() {
        this.a.b();
    }

    public boolean isBusy() {
        return this.a.a();
    }

    public CoreRouter setDynamicPenalty(DynamicPenalty dynamicPenalty) {
        this.a.a(dynamicPenalty);
        return this;
    }

    public DynamicPenalty getDynamicPenalty() {
        return this.a.c();
    }

    static {
        al.a(new k<CoreRouter, al>() {
            public al a(CoreRouter coreRouter) {
                return coreRouter != null ? coreRouter.a : null;
            }
        });
    }
}
