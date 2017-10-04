package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.RoadElement;
import com.here.android.mpa.routing.RouteElement.Type;
import com.here.android.mpa.routing.TransitRouteElement;
import com.nokia.maps.RouteImpl.a;
import com.nokia.maps.annotation.HybridPlus;
import java.util.ArrayList;
import java.util.List;

@HybridPlus
public class eb extends RouteElementImpl {
    Double a;
    Double b;
    private Type c;
    private RoadElement d;
    private TransitRouteElement e;
    private List<GeoCoordinate> f;

    eb(RoadElement roadElement) {
        super(a.ENHANCED_TRANSIT_ROUTE);
        this.c = Type.ROAD;
        this.d = roadElement;
        if (roadElement != null) {
            ea eaVar = (ea) RoadElementImpl.a(roadElement);
            if (eaVar != null) {
                this.f = eaVar.d();
                this.a = eaVar.f();
                this.b = eaVar.g();
                return;
            }
            this.f = new ArrayList();
        }
    }

    eb(TransitRouteElement transitRouteElement) {
        super(a.ENHANCED_TRANSIT_ROUTE);
        this.c = Type.TRANSIT;
        this.e = transitRouteElement;
        if (transitRouteElement != null) {
            this.f = transitRouteElement.getGeometry();
            er erVar = (er) TransitRouteElementImpl.a(transitRouteElement);
            if (erVar != null) {
                this.a = erVar.n();
                this.b = erVar.o();
                return;
            }
            this.f = new ArrayList();
        }
    }

    public Type b() {
        return this.c;
    }

    public RoadElement c() {
        return this.d;
    }

    public TransitRouteElement d() {
        return this.e;
    }

    public List<GeoCoordinate> e() {
        return this.f;
    }

    public boolean isValid() {
        return this.c == Type.ROAD ? this.d != null : this.e != null;
    }

    Double f() {
        return this.a;
    }

    Double g() {
        return this.b;
    }
}
