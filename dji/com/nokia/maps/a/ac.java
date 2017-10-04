package com.nokia.maps.a;

import android.graphics.Color;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPolyline;
import com.here.android.mpa.mapping.MapObject;
import com.here.android.mpa.mapping.MapPolyline;
import com.here.android.mpa.mapping.MapRoute.RenderType;
import com.here.android.mpa.routing.Route;
import com.here.android.mpa.urbanmobility.RouteSection;
import com.nokia.maps.ca;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ac extends ca {
    static final int h = Color.rgb(42, 52, 62);
    static final int i = Color.rgb(142, 152, 162);
    static final int j = Color.argb(136, 255, 255, 255);
    private Map<MapPolyline, a> k = new HashMap();

    private static class a {
        public int a;
        public int b;

        public a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    private class b {
        public List<MapPolyline> a;
        final /* synthetic */ ac b;
        private GeoCoordinate c;
        private boolean d;

        private b(ac acVar) {
            this.b = acVar;
            this.c = null;
            this.d = false;
            this.a = new ArrayList();
        }

        public void a() {
            this.d = true;
        }

        public void a(GeoCoordinate geoCoordinate) {
            if (this.c == null) {
                this.c = geoCoordinate;
            } else if (u.a(geoCoordinate, this.c)) {
                this.a.add(a(this.c, geoCoordinate));
                this.d = false;
                this.c = geoCoordinate;
            }
        }

        public void b(GeoCoordinate geoCoordinate) {
            this.c = geoCoordinate;
        }

        public void a(MapPolyline mapPolyline) {
            this.a.add(mapPolyline);
        }

        private MapPolyline a(GeoCoordinate geoCoordinate, GeoCoordinate geoCoordinate2) {
            GeoPolyline geoPolyline = new GeoPolyline();
            geoPolyline.add(geoCoordinate);
            geoPolyline.add(geoCoordinate2);
            MapPolyline mapPolyline = new MapPolyline(geoPolyline);
            if (this.d) {
                this.b.k.put(mapPolyline, new a(ac.h, ac.i));
                mapPolyline.setLineColor(this.b.d == RenderType.SECONDARY ? ac.i : ac.h);
            } else {
                mapPolyline.setLineColor(this.b.getColor());
            }
            mapPolyline.setLineWidth(20);
            return mapPolyline;
        }
    }

    public Route a() {
        return am.a((am) this.a);
    }

    protected void c() {
        if (this.a instanceof am) {
            am amVar = (am) this.a;
            this.k.clear();
            b bVar = new b();
            List<RouteSection> s = amVar.s();
            bVar.b(amVar.i());
            for (RouteSection routeSection : s) {
                if (routeSection.getDepartureLocation().getAddress() != null) {
                    bVar.a(routeSection.getDepartureLocation().getAddress().getCoordinate());
                }
                if (routeSection.getDepartureLocation().getStation() != null) {
                    bVar.a(routeSection.getDepartureLocation().getStation().getCoordinate());
                }
                if (routeSection.getDepartureLocation().getAccessPoint() != null) {
                    bVar.a();
                    bVar.a(routeSection.getDepartureLocation().getAccessPoint().getCoordinate());
                }
                if (routeSection.getGeometry().size() > 1) {
                    List arrayList = new ArrayList(routeSection.getGeometry());
                    bVar.a((GeoCoordinate) arrayList.get(0));
                    MapPolyline mapPolyline = new MapPolyline(new GeoPolyline(arrayList));
                    a(mapPolyline, routeSection);
                    mapPolyline.setLineWidth(20);
                    bVar.a(mapPolyline);
                    bVar.b((GeoCoordinate) arrayList.get(arrayList.size() - 1));
                }
                if (routeSection.getArrivalLocation().getAccessPoint() != null) {
                    bVar.a(routeSection.getArrivalLocation().getAccessPoint().getCoordinate());
                    bVar.a();
                }
                if (routeSection.getArrivalLocation().getStation() != null) {
                    bVar.a(routeSection.getArrivalLocation().getStation().getCoordinate());
                }
                if (routeSection.getArrivalLocation().getAddress() != null) {
                    bVar.a(routeSection.getArrivalLocation().getAddress().getCoordinate());
                }
            }
            bVar.a(amVar.k());
            this.f.addAll(bVar.a);
            for (MapObject a : bVar.a) {
                this.g.a(a);
            }
        }
    }

    protected void d() {
        for (MapPolyline mapPolyline : this.f) {
            if (this.k.containsKey(mapPolyline)) {
                a aVar = (a) this.k.get(mapPolyline);
                mapPolyline.setLineColor(this.d == RenderType.SECONDARY ? aVar.b : aVar.a);
            } else {
                mapPolyline.setLineColor(getColor());
            }
        }
        o();
    }

    private void a(MapPolyline mapPolyline, RouteSection routeSection) {
        if (routeSection.getLine() != null) {
            int color = routeSection.getLine().getStyle().getColor();
            int i = j & color;
            this.k.put(mapPolyline, new a(color, i));
            if (this.d != RenderType.SECONDARY) {
                i = color;
            }
            mapPolyline.setLineColor(i);
            return;
        }
        mapPolyline.setLineColor(getColor());
    }
}
