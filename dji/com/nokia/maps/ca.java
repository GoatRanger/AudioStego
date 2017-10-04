package com.nokia.maps;

import com.here.android.mpa.common.GeoPolyline;
import com.here.android.mpa.mapping.MapContainer;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;
import com.here.android.mpa.mapping.MapObject.Type;
import com.here.android.mpa.mapping.MapOverlayType;
import com.here.android.mpa.mapping.MapPolyline;
import com.here.android.mpa.mapping.MapRoute.RenderType;
import com.here.android.mpa.routing.Maneuver;
import com.here.android.mpa.routing.Route;
import com.here.android.mpa.routing.RouteOptions.TransportMode;
import com.here.android.mpa.routing.TransitManeuver;
import com.nokia.maps.annotation.HybridPlus;
import java.util.ArrayList;
import java.util.List;

@HybridPlus
public class ca extends MapRouteImpl {
    protected RouteImpl a;
    protected RenderType d = RenderType.PRIMARY;
    protected boolean e = false;
    protected List<MapPolyline> f = new ArrayList();
    protected MapContainerImpl g = new MapContainerImpl();
    private List<Maneuver> h = new ArrayList();
    private MapContainerImpl i = new MapContainerImpl();
    private int j;
    private int k;
    private MapOverlayType l;
    private boolean m;
    private boolean n;
    private boolean o;
    private MapObjectImpl[] p;
    private int q;
    private boolean r;

    public ca() {
        super(true);
        this.i.b(false);
        this.p = new MapObjectImpl[2];
        this.p[0] = this.g;
        this.p[1] = this.i;
        this.m = true;
        this.l = MapOverlayType.ROAD_OVERLAY;
        this.g.a(MapOverlayType.ROAD_OVERLAY);
        this.i.a(MapOverlayType.TRANSIT_STOP_OVERLAY);
        this.i.b(false);
        this.j = ee.a;
    }

    public boolean isManeuverNumberVisible() {
        return this.i.isVisible();
    }

    public void setManeuverNumberVisible(boolean z) {
        if (this.i.isVisible() != z || this.o) {
            if (z && !this.n) {
                List d = this.a.d();
                for (int i = 0; i < d.size(); i++) {
                    this.i.a(new MapMarker(((Maneuver) d.get(i)).getCoordinate(), ee.a(i)));
                }
                this.n = true;
            }
            this.i.b(z);
        }
    }

    public int getColor() {
        return this.e ? this.j : b(this.d);
    }

    private int b(RenderType renderType) {
        return renderType == RenderType.SECONDARY ? ee.b : ee.a;
    }

    public void a(int i) {
        if (this.d == RenderType.SECONDARY) {
            this.r = true;
        }
        if (this.j != i) {
            this.j = i;
            d();
        } else if (!this.e) {
            d();
        }
        this.e = true;
    }

    public Route a() {
        return RouteImpl.create(this.a);
    }

    public void a(Route route) {
        dy.a((Object) route, "Route is null");
        this.o = true;
        this.a = RouteImpl.a(route);
        this.h.clear();
        this.f.clear();
        this.g.a();
        this.n = false;
        this.i.a();
        c();
        setManeuverNumberVisible(this.i.isVisible());
        this.o = false;
        this.r = true;
        this.q = 0;
        o();
    }

    protected void c() {
        for (Maneuver maneuver : this.a.d()) {
            List maneuverGeometry = maneuver.getManeuverGeometry();
            if (maneuverGeometry.size() > 1) {
                MapObject mapPolyline = new MapPolyline(new GeoPolyline(maneuverGeometry));
                if (maneuver.getTransportMode() == TransportMode.PUBLIC_TRANSPORT) {
                    TransitManeuver transitManeuver = (TransitManeuver) maneuver;
                    if (this.d == RenderType.PRIMARY) {
                        if (transitManeuver.hasPrimaryLineColor()) {
                            mapPolyline.setLineColor(transitManeuver.getPrimaryLineColor());
                        }
                    } else if (this.d == RenderType.SECONDARY && transitManeuver.hasSecondaryLineColor()) {
                        mapPolyline.setLineColor(transitManeuver.getSecondaryLineColor());
                    }
                } else {
                    mapPolyline.setLineColor(getColor());
                }
                mapPolyline.setLineWidth(20);
                this.f.add(mapPolyline);
                this.h.add(maneuver);
                this.g.a(mapPolyline);
            }
        }
    }

    public void a(RenderType renderType) {
        if (this.d != renderType) {
            if (this.d == RenderType.SECONDARY || renderType == RenderType.SECONDARY) {
                this.r = true;
            }
            this.d = renderType;
            d();
        }
        this.e = false;
    }

    public RenderType b() {
        return this.d;
    }

    public void enableTraffic(boolean z) {
    }

    public boolean isTrafficEnabled() {
        return false;
    }

    public Type l() {
        return Type.ROUTE;
    }

    public MapContainer m() {
        return this.g.m();
    }

    public MapContainerImpl getParentNative() {
        return this.g.getParentNative();
    }

    public boolean isVisible() {
        return this.m;
    }

    public void b(boolean z) {
        if (this.m != z) {
            this.m = z;
            this.g.b(this.m);
            this.i.b(this.m);
        }
    }

    public int getZIndex() {
        return this.k;
    }

    public void f(int i) {
        this.k = i;
    }

    public void a(MapImpl mapImpl) {
        if (mapImpl != null) {
            super.a(mapImpl);
        }
        this.g.a(mapImpl);
        this.i.a(mapImpl);
        if (mapImpl == null) {
            super.a(null);
        }
    }

    protected void o() {
        this.g.o();
    }

    public void a(MapOverlayType mapOverlayType) {
        this.l = mapOverlayType;
    }

    public MapOverlayType q() {
        return this.l;
    }

    protected void d() {
        int color = getColor();
        for (int i = 0; i < this.h.size(); i++) {
            Maneuver maneuver = (Maneuver) this.h.get(i);
            if (((Maneuver) this.h.get(i)).getTransportMode() == TransportMode.PUBLIC_TRANSPORT) {
                TransitManeuver transitManeuver = (TransitManeuver) maneuver;
                if (this.d == RenderType.PRIMARY) {
                    if (transitManeuver.hasPrimaryLineColor()) {
                        ((MapPolyline) this.f.get(i)).setLineColor(transitManeuver.getPrimaryLineColor());
                    }
                } else if (this.d == RenderType.SECONDARY && transitManeuver.hasSecondaryLineColor()) {
                    ((MapPolyline) this.f.get(i)).setLineColor(transitManeuver.getSecondaryLineColor());
                }
            } else {
                ((MapPolyline) this.f.get(i)).setLineColor(color);
            }
        }
        o();
    }

    MapObjectImpl[] e() {
        return this.p;
    }

    void b(int i) {
        if (this.q != i) {
            for (MapPolyline lineWidth : this.f) {
                lineWidth.setLineWidth(i);
            }
            this.q = i;
            this.r = false;
        }
    }

    boolean f() {
        return this.r;
    }

    public int hashCode() {
        return this.g.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!ca.class.isInstance(obj)) {
            return false;
        }
        if (obj == null) {
            return false;
        }
        ca caVar = (ca) obj;
        if (this.g != null) {
            if (this.g.equals(caVar.g)) {
                return true;
            }
        } else if (caVar.g == null) {
            return true;
        }
        return false;
    }
}
