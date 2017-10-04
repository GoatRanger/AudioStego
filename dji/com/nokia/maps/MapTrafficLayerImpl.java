package com.nokia.maps;

import com.here.android.mpa.mapping.MapTrafficLayer;
import com.here.android.mpa.mapping.MapTrafficLayer.RenderLayer;
import com.here.android.mpa.mapping.TrafficEvent.Severity;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class MapTrafficLayerImpl extends BaseNativeObject {
    private static am<MapTrafficLayer, MapTrafficLayerImpl> b;
    private cq a = new cq(MapTrafficLayerImpl.class.getName());
    private Object c = new Object();
    private Severity d = Severity.NORMAL;
    private MapImpl e;

    private native void destroyMapTrafficLayerNative();

    private native void enableLayerNative(int i, boolean z);

    private native boolean isLayerEnabledNative(int i);

    private native boolean setDisplayFilterNative(int i);

    public static void a(am<MapTrafficLayer, MapTrafficLayerImpl> amVar) {
        b = amVar;
    }

    public static MapTrafficLayer a(MapTrafficLayerImpl mapTrafficLayerImpl) {
        if (mapTrafficLayerImpl != null) {
            return (MapTrafficLayer) b.a(mapTrafficLayerImpl);
        }
        return null;
    }

    static {
        ce.a(MapTrafficLayer.class);
    }

    @HybridPlusNative
    private MapTrafficLayerImpl(int i, MapImpl mapImpl) {
        this.e = mapImpl;
        this.nativeptr = i;
    }

    protected void finalize() {
        destroyMapTrafficLayerNative();
    }

    public void a(RenderLayer renderLayer, boolean z) {
        enableLayerNative(renderLayer.getValue(), z);
        if (this.e != null) {
            this.e.redraw();
        }
    }

    public boolean a(RenderLayer renderLayer) {
        return isLayerEnabledNative(renderLayer.getValue());
    }

    public boolean a(Severity severity) {
        boolean displayFilterNative;
        synchronized (this.c) {
            displayFilterNative = setDisplayFilterNative(severity.getValue());
            this.d = severity;
        }
        return displayFilterNative;
    }

    public Severity a() {
        Severity severity;
        synchronized (this.c) {
            severity = this.d;
        }
        return severity;
    }
}
