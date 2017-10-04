package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.mapping.GeoMesh;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.nio.DoubleBuffer;
import java.util.List;

@Online
public class GeoMeshImpl extends MeshImpl {
    private static am<GeoMesh, GeoMeshImpl> b = null;

    private native void createNative();

    private native void destroyNative();

    private native void setVerticesNative(double[] dArr);

    private native void setVerticesNative(GeoCoordinateImpl[] geoCoordinateImplArr);

    static {
        ce.a(GeoMesh.class);
    }

    static GeoMesh a(GeoMeshImpl geoMeshImpl) {
        if (geoMeshImpl != null) {
            return (GeoMesh) b.a(geoMeshImpl);
        }
        return null;
    }

    public static void a(am<GeoMesh, GeoMeshImpl> amVar) {
        b = amVar;
    }

    @OnlineNative
    private GeoMeshImpl(int i) {
        this.a = 1;
        this.nativeptr = i;
    }

    public GeoMeshImpl() {
        this.a = 1;
        createNative();
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyNative();
        }
    }

    public void a(List<GeoCoordinate> list) {
        if (list.size() == 0) {
            throw new IllegalArgumentException("Input vertices array list contains no coordinate.");
        } else if (list.size() > 65536) {
            throw new IllegalArgumentException("Maximum number of vertices is 65536");
        } else {
            setVerticesNative(GeoCoordinateImpl.a((List) list));
        }
    }

    public void a(DoubleBuffer doubleBuffer) {
        if (doubleBuffer.capacity() == 0) {
            throw new IllegalArgumentException("Input vertices is empty");
        } else if (doubleBuffer.capacity() % 3 != 0) {
            throw new IllegalArgumentException("Input vertices should contain double triplets.");
        } else if (doubleBuffer.capacity() / 3 > 65536) {
            throw new IllegalArgumentException("Maximum number of vertices is 65536");
        } else {
            setVerticesNative(doubleBuffer.array());
        }
    }
}
