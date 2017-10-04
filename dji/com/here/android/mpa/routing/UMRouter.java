package com.here.android.mpa.routing;

import com.here.android.mpa.urbanmobility.ErrorCode;
import com.nokia.maps.a.as;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;

@HybridPlus
public final class UMRouter implements Router<UMCalculateResult, ErrorCode> {
    private final as a = new as();

    @HybridPlus
    public interface Listener extends com.here.android.mpa.routing.Router.Listener<UMCalculateResult, ErrorCode> {
        void onCalculateRouteFinished(UMCalculateResult uMCalculateResult, ErrorCode errorCode);
    }

    @HybridPlus
    public enum SubsequentRouteType {
        EARLIER,
        LATER
    }

    public void calculateRoute(RoutePlan routePlan, com.here.android.mpa.routing.Router.Listener<UMCalculateResult, ErrorCode> listener) {
        this.a.a(routePlan, (com.here.android.mpa.routing.Router.Listener) listener);
    }

    public void calculateSubsequentRoute(UMCalculateResult uMCalculateResult, SubsequentRouteType subsequentRouteType, int i, com.here.android.mpa.routing.Router.Listener<UMCalculateResult, ErrorCode> listener) {
        this.a.a(uMCalculateResult, subsequentRouteType, i, listener);
    }

    public void cancel() {
        this.a.a();
    }

    public boolean isBusy() {
        return this.a.b();
    }

    static {
        as.a(new k<UMRouter, as>() {
            public as a(UMRouter uMRouter) {
                return uMRouter != null ? uMRouter.a : null;
            }
        });
    }
}
