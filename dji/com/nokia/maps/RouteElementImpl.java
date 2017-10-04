package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.RoadElement;
import com.here.android.mpa.routing.RouteElement;
import com.here.android.mpa.routing.RouteElement.Type;
import com.here.android.mpa.routing.TransitRouteElement;
import com.nokia.maps.RouteImpl.a;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Online
public class RouteElementImpl extends BaseNativeObject {
    private static k<RouteElement, RouteElementImpl> b = null;
    private static am<RouteElement, RouteElementImpl> c = null;
    private cq a = new cq(RouteElementImpl.class.getName());
    private a d;

    private native void destroyRouteElementNative();

    private native GeoCoordinateImpl[] getGeometryNative();

    private native RoadElementImpl getRoadElementNative();

    private native TransitRouteElementImpl getTransitElementNative();

    private native int getTypeNative();

    public native boolean isValid();

    static {
        ce.a(RouteElement.class);
    }

    public static void a(k<RouteElement, RouteElementImpl> kVar, am<RouteElement, RouteElementImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    static RouteElementImpl a(RouteElement routeElement) {
        return (RouteElementImpl) b.a(routeElement);
    }

    static List<RouteElementImpl> a(List<RouteElement> list) {
        List<RouteElementImpl> arrayList = new ArrayList();
        for (RouteElement a : list) {
            arrayList.add(a(a));
        }
        return arrayList;
    }

    static List<RouteElement> a(RouteElementImpl[] routeElementImplArr) {
        List<RouteElement> arrayList = new ArrayList();
        for (RouteElementImpl a : routeElementImplArr) {
            arrayList.add(a(a));
        }
        return arrayList;
    }

    static RouteElement a(RouteElementImpl routeElementImpl) {
        if (routeElementImpl != null) {
            return (RouteElement) c.a(routeElementImpl);
        }
        return null;
    }

    static List<RouteElement> b(List<RouteElementImpl> list) {
        List arrayList = new ArrayList();
        if (list != null) {
            for (RouteElementImpl a : list) {
                arrayList.add(a(a));
            }
        }
        return arrayList;
    }

    @OnlineNative
    private RouteElementImpl(int i) {
        this.nativeptr = i;
        this.d = a.MOS_ROUTE;
    }

    RouteElementImpl(a aVar) {
        dy.a((Object) aVar, "Route type is null");
        dy.a(aVar != a.MOS_ROUTE, "Route type cannot be MOS_ROUTE");
        this.nativeptr = 0;
        this.d = aVar;
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyRouteElementNative();
        }
    }

    a a() {
        return this.d;
    }

    public Type b() {
        return Type.values()[getTypeNative()];
    }

    public RoadElement c() {
        return RoadElementImpl.a(getRoadElementNative());
    }

    public TransitRouteElement d() {
        return TransitRouteElementImpl.a(getTransitElementNative());
    }

    public List<GeoCoordinate> e() {
        return GeoCoordinateImpl.b(Arrays.asList(getGeometryNative()));
    }
}
