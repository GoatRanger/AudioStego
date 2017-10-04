package com.nokia.maps;

import com.here.android.mpa.common.GeoPolyline;
import com.here.android.mpa.routing.Maneuver;
import com.here.android.mpa.routing.RouteElement;
import com.nokia.maps.RouteImpl.a;
import com.nokia.maps.annotation.HybridPlus;
import java.util.ArrayList;
import java.util.List;

@HybridPlus
public class es extends RouteElementsImpl {
    private GeoPolyline b;
    private List<RouteElement> c;

    es(Maneuver maneuver) {
        super(a.ENHANCED_TRANSIT_ROUTE);
        if (maneuver == null) {
            this.b = new GeoPolyline();
            this.c = new ArrayList();
            return;
        }
        List maneuverGeometry = maneuver.getManeuverGeometry();
        if (maneuverGeometry.isEmpty()) {
            this.b = new GeoPolyline();
        } else {
            this.b = new GeoPolyline(maneuverGeometry);
        }
        this.c = maneuver.getRouteElements();
    }

    es(List<RouteElement> list) {
        super(a.ENHANCED_TRANSIT_ROUTE);
        if (list == null) {
            this.b = new GeoPolyline();
            this.c = new ArrayList();
            return;
        }
        this.c = list;
        this.b = new GeoPolyline();
        for (RouteElement geometry : this.c) {
            this.b.add(geometry.getGeometry());
        }
    }

    public GeoPolyline b() {
        return this.b;
    }

    public List<RouteElement> c() {
        return this.c;
    }

    protected boolean isValid() {
        return (this.b == null || this.c == null) ? false : true;
    }
}
