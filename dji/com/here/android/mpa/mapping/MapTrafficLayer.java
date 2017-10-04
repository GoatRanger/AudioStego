package com.here.android.mpa.mapping;

import com.here.android.mpa.mapping.TrafficEvent.Severity;
import com.nokia.maps.MapTrafficLayerImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class MapTrafficLayer {
    private MapTrafficLayerImpl a;

    @HybridPlus
    public enum RenderLayer {
        FLOW(0),
        INCIDENT(1),
        ONROUTE(2);
        
        private final int a;

        private RenderLayer(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }
    }

    private MapTrafficLayer() {
    }

    private MapTrafficLayer(MapTrafficLayerImpl mapTrafficLayerImpl) {
        this.a = mapTrafficLayerImpl;
    }

    public void setEnabled(RenderLayer renderLayer, boolean z) {
        this.a.a(renderLayer, z);
    }

    public boolean isEnabled(RenderLayer renderLayer) {
        return this.a.a(renderLayer);
    }

    public boolean setDisplayFilter(Severity severity) {
        return this.a.a(severity);
    }

    public Severity getDisplayFilter() {
        return this.a.a();
    }

    static {
        MapTrafficLayerImpl.a(new am<MapTrafficLayer, MapTrafficLayerImpl>() {
            public MapTrafficLayer a(MapTrafficLayerImpl mapTrafficLayerImpl) {
                return new MapTrafficLayer(mapTrafficLayerImpl);
            }
        });
    }
}
