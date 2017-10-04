package com.nokia.maps;

import com.here.android.mpa.common.GeoPolyline;
import com.here.android.mpa.routing.RouteElement;
import com.here.android.mpa.routing.RouteElements;
import com.nokia.maps.RouteImpl.a;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.util.List;

@HybridPlus
public class RouteElementsImpl extends BaseNativeObject {
    private static k<RouteElements, RouteElementsImpl> b;
    private static am<RouteElements, RouteElementsImpl> c;
    protected volatile List<RouteElement> a;
    private a d;

    private native void destroyNative();

    private native GeoPolylineImpl getGeometryNative();

    private native RouteElementImpl[] getRouteElementListNative();

    protected native boolean isValid();

    public static void a(k<RouteElements, RouteElementsImpl> kVar, am<RouteElements, RouteElementsImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    static {
        ce.a(RouteElements.class);
    }

    static RouteElements a(RouteElementsImpl routeElementsImpl) {
        if (routeElementsImpl.isValid()) {
            return (RouteElements) c.a(routeElementsImpl);
        }
        return null;
    }

    static RouteElementsImpl a(RouteElements routeElements) {
        return (RouteElementsImpl) b.a(routeElements);
    }

    @HybridPlusNative
    private RouteElementsImpl(int i) {
        this.nativeptr = i;
        this.d = a.MOS_ROUTE;
    }

    RouteElementsImpl(a aVar) {
        dy.a((Object) aVar, "Route type is null");
        dy.a(aVar != a.MOS_ROUTE, "Route type cannot be MOS_ROUTE");
        this.nativeptr = 0;
        this.d = aVar;
    }

    a a() {
        return this.d;
    }

    public GeoPolyline b() {
        return GeoPolylineImpl.a(getGeometryNative());
    }

    public List<RouteElement> c() {
        if (this.a == null) {
            synchronized (this) {
                if (this.a == null) {
                    this.a = RouteElementImpl.a(getRouteElementListNative());
                }
            }
        }
        return this.a;
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyNative();
        }
    }
}
