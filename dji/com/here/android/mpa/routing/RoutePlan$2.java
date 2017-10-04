package com.here.android.mpa.routing;

import com.nokia.maps.RoutePlanImpl;
import com.nokia.maps.am;

class RoutePlan$2 implements am<RoutePlan, RoutePlanImpl> {
    RoutePlan$2() {
    }

    public RoutePlan a(RoutePlanImpl routePlanImpl) {
        return routePlanImpl != null ? new RoutePlan(routePlanImpl, null) : null;
    }
}
