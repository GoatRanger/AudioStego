package com.here.android.mpa.mapping;

import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.GeoMeshImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import java.nio.DoubleBuffer;
import java.util.List;

@Online
public final class GeoMesh extends Mesh {
    private GeoMeshImpl a;

    public GeoMesh() {
        this(new GeoMeshImpl());
    }

    private GeoMesh(GeoMeshImpl geoMeshImpl) {
        super(geoMeshImpl);
        this.a = geoMeshImpl;
    }

    public GeoMesh setVertices(List<GeoCoordinate> list) {
        this.a.a((List) list);
        return this;
    }

    public GeoMesh setVertices(DoubleBuffer doubleBuffer) {
        this.a.a(doubleBuffer);
        return this;
    }

    static {
        GeoMeshImpl.a(new am<GeoMesh, GeoMeshImpl>() {
            public GeoMesh a(GeoMeshImpl geoMeshImpl) {
                if (geoMeshImpl == null || !geoMeshImpl.d()) {
                    return null;
                }
                return new GeoMesh(geoMeshImpl);
            }
        });
    }
}
