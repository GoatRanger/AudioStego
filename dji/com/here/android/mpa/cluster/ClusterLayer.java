package com.here.android.mpa.cluster;

import com.here.android.mpa.mapping.MapMarker;
import com.nokia.maps.ab;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.util.Collection;

@HybridPlus
public class ClusterLayer {
    private final ab a = new ab();

    public void addMarker(MapMarker mapMarker) {
        this.a.a(mapMarker);
    }

    public void addMarkers(Collection<MapMarker> collection) {
        this.a.a((Collection) collection);
    }

    public boolean removeMarker(MapMarker mapMarker) {
        return this.a.b(mapMarker);
    }

    public boolean removeMarkers(Collection<MapMarker> collection) {
        return this.a.b((Collection) collection);
    }

    public void setTheme(ClusterTheme clusterTheme) {
        this.a.a(clusterTheme);
    }

    public Collection<MapMarker> getMarkers() {
        return this.a.a();
    }

    public String toString() {
        return "CL" + (hashCode() % 1000) + "(" + getMarkers().size() + ")";
    }

    static {
        ab.a(new k<ClusterLayer, ab>() {
            public ab a(ClusterLayer clusterLayer) {
                return clusterLayer.a;
            }
        });
    }
}
