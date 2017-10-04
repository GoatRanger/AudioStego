package com.here.android.mpa.mapping;

import com.here.android.mpa.common.Identifier;
import com.nokia.maps.MapTransitLayerImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;
import java.util.List;

@Online
public final class MapTransitLayer {
    private MapTransitLayerImpl a;

    @Online
    public enum Mode {
        NOTHING(0),
        STOPS_AND_ACCESSES(1),
        EVERYTHING(2);
        
        private final int a;

        private Mode(int i) {
            this.a = i;
        }

        public int getValue() {
            return this.a;
        }
    }

    private MapTransitLayer() {
    }

    private MapTransitLayer(MapTransitLayerImpl mapTransitLayerImpl) {
        this.a = mapTransitLayerImpl;
    }

    public void setMode(Mode mode) {
        this.a.a(mode);
    }

    public Mode getMode() {
        return this.a.a();
    }

    public void highlightTransitLines(List<Identifier> list) {
        this.a.a((List) list);
    }

    public void highlightTransitStops(List<Identifier> list) {
        this.a.c(list);
    }

    public void highlightTransitAccesses(List<Identifier> list) {
        this.a.d(list);
    }

    public void highlightTransitLineSegments(List<Identifier> list) {
        this.a.b(list);
    }

    public void clearTransitHighlights() {
        this.a.b();
    }

    static {
        MapTransitLayerImpl.a(new k<MapTransitLayer, MapTransitLayerImpl>() {
            public MapTransitLayerImpl a(MapTransitLayer mapTransitLayer) {
                return mapTransitLayer.a;
            }
        }, new am<MapTransitLayer, MapTransitLayerImpl>() {
            public MapTransitLayer a(MapTransitLayerImpl mapTransitLayerImpl) {
                if (mapTransitLayerImpl != null) {
                    return new MapTransitLayer(mapTransitLayerImpl);
                }
                return null;
            }
        });
    }
}
