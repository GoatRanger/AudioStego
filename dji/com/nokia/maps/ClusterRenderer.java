package com.nokia.maps;

import com.nokia.maps.annotation.Online;
import java.lang.ref.WeakReference;
import java.util.Collection;

@Online
class ClusterRenderer extends BaseNativeObject {
    private static final String a = BaseNativeObject.class.getSimpleName();
    private final WeakReference<MapImpl> b;

    private native void createNative(MapImpl mapImpl);

    private native void deleteNative();

    private native void renderNative(Object[] objArr, ClusterThemeImpl clusterThemeImpl);

    public native void clearNative();

    public ClusterRenderer(MapImpl mapImpl) {
        this.b = new WeakReference(mapImpl);
        createNative(mapImpl);
    }

    public void a(Collection<Cluster> collection, ClusterThemeImpl clusterThemeImpl) {
        MapImpl mapImpl = (MapImpl) this.b.get();
        if (mapImpl != null) {
            renderNative(collection.toArray(), clusterThemeImpl);
            mapImpl.redraw();
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.nativeptr != 0) {
            deleteNative();
        }
    }
}
