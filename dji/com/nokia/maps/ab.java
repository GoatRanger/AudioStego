package com.nokia.maps;

import android.annotation.SuppressLint;
import com.here.android.mpa.cluster.ClusterLayer;
import com.here.android.mpa.cluster.ClusterTheme;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.mapping.Map$OnTransformListener;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;
import com.here.android.mpa.mapping.MapState;
import com.nokia.maps.MapMarkerImpl.a;
import com.nokia.maps.annotation.Online;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Online
public class ab implements Map$OnTransformListener, a {
    static final String a = ab.class.getSimpleName();
    private static int d = -1;
    private static volatile k<ClusterLayer, ab> g;
    @SuppressLint({"UseSparseArrays"})
    Map<Integer, MapMarker> b = new HashMap();
    ClusterTheme c = new ClusterTheme();
    private MapImpl e;
    private long f = ((long) d);
    private ac h;

    public static void a(k<ClusterLayer, ab> kVar) {
        bj.e(a, "accessor =" + kVar, new Object[0]);
        g = kVar;
    }

    public static ab a(ClusterLayer clusterLayer) {
        return (ab) g.a(clusterLayer);
    }

    public synchronized void a(MapMarker mapMarker) {
        dy.a((Object) mapMarker, "marker cannot be null");
        d(mapMarker);
        if (d()) {
            this.h.a(mapMarker);
        }
        bj.e(a, "added " + mapMarker, new Object[0]);
    }

    public synchronized void a(Collection<MapMarker> collection) {
        dy.a((Object) collection, "markers cannot be null");
        for (MapMarker d : collection) {
            d(d);
        }
        if (d()) {
            this.h.a((Collection) collection);
        }
        bj.e(a, "added " + collection.size() + " markers", new Object[0]);
    }

    public synchronized boolean b(MapMarker mapMarker) {
        boolean z = false;
        synchronized (this) {
            if (mapMarker != null) {
                z = e(mapMarker);
                if (d()) {
                    this.h.b(mapMarker);
                }
                bj.e(a, "removed " + mapMarker + " = " + z, new Object[0]);
            }
        }
        return z;
    }

    synchronized void c(MapMarker mapMarker) {
        if (e(mapMarker)) {
            this.h.b(mapMarker);
        }
    }

    private void d(MapMarker mapMarker) {
        MapMarkerImpl mapMarkerImpl = (MapMarkerImpl) MapObjectImpl.d((MapObject) mapMarker);
        mapMarkerImpl.a((a) this);
        this.b.put(Integer.valueOf(mapMarkerImpl.hashCode()), mapMarker);
    }

    private boolean e(MapMarker mapMarker) {
        return this.b.remove(Integer.valueOf(MapObjectImpl.d((MapObject) mapMarker).hashCode())) != null;
    }

    public synchronized boolean b(Collection<MapMarker> collection) {
        boolean z = false;
        synchronized (this) {
            if (collection != null) {
                boolean z2 = false;
                for (MapMarker e : collection) {
                    z2 |= e(e);
                }
                if (d()) {
                    this.h.b((Collection) collection);
                }
                bj.e(a, "removing result = " + z2, new Object[0]);
                z = z2;
            }
        }
        return z;
    }

    private boolean d() {
        return this.e != null;
    }

    public synchronized List<MapMarker> a() {
        return new ArrayList(this.b.values());
    }

    synchronized void a(MapImpl mapImpl) {
        if (d()) {
            bj.b(a, "layer " + this + " is already attached", new Object[0]);
        } else {
            this.e = mapImpl;
            this.f = Math.round(mapImpl.getZoomLevel());
            this.h = new ac(mapImpl, this.c);
            this.h.start();
            this.h.a(this.b.values());
            mapImpl.a(this);
            bj.e(a, this + " is attached to the map", new Object[0]);
        }
    }

    synchronized void b() {
        if (d()) {
            this.e.b(this);
            this.e.d(new ArrayList(this.b.values()));
            e();
            bj.e(a, this + " is dettached from the map", new Object[0]);
            this.h.interrupt();
            try {
                this.h.join();
            } catch (InterruptedException e) {
                bj.e(a, "detachFromMap interrupted", new Object[0]);
                Thread.currentThread().interrupt();
            }
        } else {
            bj.b(a, "attempt to detach not attached layer: " + this, new Object[0]);
        }
    }

    private void e() {
        this.e = null;
    }

    public synchronized void a(ClusterTheme clusterTheme) {
        if (clusterTheme == null) {
            throw new NullPointerException("theme cannot be null");
        }
        this.c = new ClusterTheme(clusterTheme);
        bj.e(a, "theme = " + clusterTheme, new Object[0]);
        if (d()) {
            this.h.a(this.c);
        }
    }

    public void onMapTransformStart() {
    }

    public synchronized void onMapTransformEnd(MapState mapState) {
        if (d() && a(mapState.getZoomLevel())) {
            this.f = Math.round(mapState.getZoomLevel());
            bj.e(a, "transform ended, clustering...", new Object[0]);
            this.h.a(this.c);
        }
    }

    private boolean a(double d) {
        boolean z = false;
        long round = Math.round(d);
        bj.e(a, "zoom1=" + d + "; zoom2=" + round + "; zoom3=" + Math.round(this.e.getZoomLevel()), new Object[0]);
        if (round != this.f) {
            z = true;
        }
        this.f = round;
        return z;
    }

    public Collection<Cluster> c() {
        return this.h.a();
    }

    public synchronized void a(GeoCoordinate geoCoordinate) {
        this.h.b();
    }

    public String toString() {
        return getClass().getSimpleName() + "#" + hashCode();
    }
}
