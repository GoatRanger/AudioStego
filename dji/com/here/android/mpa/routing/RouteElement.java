package com.here.android.mpa.routing;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.RoadElement;
import com.nokia.maps.RouteElementImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.List;

public final class RouteElement {
    private RouteElementImpl a;

    @Online
    public enum Type {
        TRANSIT(0),
        ROAD(1),
        INVALID(2);
        
        private int a;

        private Type(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    private RouteElement(RouteElementImpl routeElementImpl) {
        this.a = routeElementImpl;
    }

    @Online
    public Type getType() {
        return this.a.b();
    }

    @Online
    public RoadElement getRoadElement() {
        return this.a.c();
    }

    @HybridPlus
    public TransitRouteElement getTransitElement() {
        return this.a.d();
    }

    @Online
    public final List<GeoCoordinate> getGeometry() {
        return this.a.e();
    }

    static {
        RouteElementImpl.a(new k<RouteElement, RouteElementImpl>() {
            public RouteElementImpl a(RouteElement routeElement) {
                return routeElement.a;
            }
        }, new am<RouteElement, RouteElementImpl>() {
            public RouteElement a(RouteElementImpl routeElementImpl) {
                return (routeElementImpl == null || !routeElementImpl.isValid()) ? null : new RouteElement(routeElementImpl);
            }
        });
    }
}
