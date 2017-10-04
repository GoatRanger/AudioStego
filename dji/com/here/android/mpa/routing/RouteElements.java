package com.here.android.mpa.routing;

import com.here.android.mpa.common.GeoPolyline;
import com.nokia.maps.RouteElementsImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.util.List;

@HybridPlus
public final class RouteElements {
    private RouteElementsImpl a;

    private RouteElements(RouteElementsImpl routeElementsImpl) {
        this.a = routeElementsImpl;
    }

    public GeoPolyline getGeometry() {
        return this.a.b();
    }

    public List<RouteElement> getElements() {
        return this.a.c();
    }

    static {
        RouteElementsImpl.a(new k<RouteElements, RouteElementsImpl>() {
            public RouteElementsImpl a(RouteElements routeElements) {
                return routeElements.a;
            }
        }, new am<RouteElements, RouteElementsImpl>() {
            public RouteElements a(RouteElementsImpl routeElementsImpl) {
                return new RouteElements(routeElementsImpl);
            }
        });
    }
}
