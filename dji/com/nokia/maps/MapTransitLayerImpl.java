package com.nokia.maps;

import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.mapping.MapTransitLayer;
import com.here.android.mpa.mapping.MapTransitLayer.Mode;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Online
public class MapTransitLayerImpl extends BaseNativeObject {
    private static k<MapTransitLayer, MapTransitLayerImpl> b;
    private static am<MapTransitLayer, MapTransitLayerImpl> c;
    private cq a = new cq(MapTransitLayerImpl.class.getName());
    private MapImpl d;

    private native void clearTransitHighlightsNative();

    private native void destroyMapTransitLayerNative();

    private native int getModeNative();

    private native void highlightTransitAccessesNative(IdentifierImpl[] identifierImplArr);

    private native void highlightTransitLineSegmentsNative(IdentifierImpl[] identifierImplArr);

    private native void highlightTransitLinesNative(IdentifierImpl[] identifierImplArr);

    private native void highlightTransitStopsNative(IdentifierImpl[] identifierImplArr);

    private native void setModeNative(int i);

    public static void a(k<MapTransitLayer, MapTransitLayerImpl> kVar, am<MapTransitLayer, MapTransitLayerImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    static MapTransitLayer a(MapTransitLayerImpl mapTransitLayerImpl) {
        if (mapTransitLayerImpl != null) {
            return (MapTransitLayer) c.a(mapTransitLayerImpl);
        }
        return null;
    }

    static {
        ce.a(MapTransitLayer.class);
    }

    @OnlineNative
    private MapTransitLayerImpl(int i, MapImpl mapImpl) {
        this.d = mapImpl;
        this.nativeptr = i;
    }

    protected void finalize() {
        destroyMapTransitLayerNative();
    }

    public void a(Mode mode) {
        setModeNative(mode.getValue());
        if (this.d != null) {
            this.d.redraw();
        }
    }

    public Mode a() {
        switch (getModeNative()) {
            case 1:
                return Mode.STOPS_AND_ACCESSES;
            case 2:
                return Mode.EVERYTHING;
            default:
                return Mode.NOTHING;
        }
    }

    public void a(List<Identifier> list) {
        IdentifierImpl[] a = a(IdentifierImpl.a((List) list));
        if (a != null && a.length > 0) {
            highlightTransitLinesNative(a);
            if (this.d != null) {
                this.d.redraw();
            }
        }
    }

    public void b(List<Identifier> list) {
        IdentifierImpl[] a = a(IdentifierImpl.a((List) list));
        if (a != null && a.length > 0) {
            highlightTransitLineSegmentsNative(a);
            if (this.d != null) {
                this.d.redraw();
            }
        }
    }

    public void c(List<Identifier> list) {
        IdentifierImpl[] a = a(IdentifierImpl.a((List) list));
        if (a != null && a.length > 0) {
            highlightTransitStopsNative(a);
            if (this.d != null) {
                this.d.redraw();
            }
        }
    }

    public void d(List<Identifier> list) {
        IdentifierImpl[] a = a(IdentifierImpl.a((List) list));
        if (a != null && a.length > 0) {
            highlightTransitAccessesNative(a);
            if (this.d != null) {
                this.d.redraw();
            }
        }
    }

    private IdentifierImpl[] a(IdentifierImpl[] identifierImplArr) {
        int i = 0;
        if (identifierImplArr == null || identifierImplArr.length == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(identifierImplArr));
        ArrayList arrayList2 = new ArrayList();
        int length = identifierImplArr.length;
        int i2 = 0;
        while (i < length) {
            IdentifierImpl identifierImpl = identifierImplArr[i];
            if (identifierImpl == null) {
                arrayList2.add(Integer.valueOf(i2));
            } else if (identifierImpl.toString().isEmpty()) {
                arrayList2.add(Integer.valueOf(i2));
            }
            i2++;
            i++;
        }
        for (i2 = arrayList2.size() - 1; i2 >= 0; i2--) {
            arrayList.remove(((Integer) arrayList2.get(i2)).intValue());
        }
        return (IdentifierImpl[]) arrayList.toArray(new IdentifierImpl[arrayList.size()]);
    }

    public void b() {
        clearTransitHighlightsNative();
        if (this.d != null) {
            this.d.redraw();
        }
    }
}
