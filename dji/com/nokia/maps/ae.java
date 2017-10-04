package com.nokia.maps;

import com.here.android.mpa.cluster.ClusterViewObject;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapProxyObject.Type;
import java.util.Collection;

public final class ae extends MapProxyObjectImpl {
    private static am<ClusterViewObject, ae> b = null;
    private final Cluster a;

    ae(Cluster cluster) {
        this.a = cluster;
    }

    public Type a() {
        return Type.CLUSTER_MARKER;
    }

    public GeoBoundingBox b() {
        GeoBoundingBoxImpl boundBox = this.a.getBoundBox();
        return new GeoBoundingBox(new GeoCoordinate(boundBox.a().a(), boundBox.a().b()), new GeoCoordinate(boundBox.b().a(), boundBox.b().b()));
    }

    public Collection<MapMarker> c() {
        return this.a.a();
    }

    public int hashCode() {
        return this.a.hashCode() + 527;
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
        ae aeVar = (ae) obj;
        if (this.a == null) {
            if (aeVar.a != null) {
                return false;
            }
            return true;
        } else if (this.a.equals(aeVar.a)) {
            return true;
        } else {
            return false;
        }
    }

    static ClusterViewObject a(ae aeVar) {
        if (aeVar != null) {
            return (ClusterViewObject) b.a(aeVar);
        }
        return null;
    }

    static {
        ce.a(ClusterViewObject.class);
    }

    public static void a(am<ClusterViewObject, ae> amVar) {
        b = amVar;
    }
}
