package com.here.android.mpa.cluster;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.ViewObject.Type;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapProxyObject;
import com.nokia.maps.ae;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Collection;

@HybridPlus
public final class ClusterViewObject extends MapProxyObject {
    private final ae b;

    private ClusterViewObject(ae aeVar) {
        super(aeVar);
        this.b = aeVar;
    }

    public Collection<MapMarker> getMarkers() {
        return this.b.c();
    }

    public GeoBoundingBox getBoundingBox() {
        return this.b.b();
    }

    public Type getBaseType() {
        return this.b.k();
    }

    public MapProxyObject.Type getType() {
        return this.b.a();
    }

    public int hashCode() {
        return this.b.hashCode() + 527;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ClusterViewObject clusterViewObject = (ClusterViewObject) obj;
        if (this.b == null) {
            if (clusterViewObject.b != null) {
                return false;
            }
            return true;
        } else if (this.b.equals(clusterViewObject.b)) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + "#" + hashCode();
    }

    static {
        ae.a(new am<ClusterViewObject, ae>() {
            public ClusterViewObject a(ae aeVar) {
                return new ClusterViewObject(aeVar);
            }
        });
    }
}
