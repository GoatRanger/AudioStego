package com.nokia.maps;

import com.here.android.mpa.common.ViewObject.Type;
import com.here.android.mpa.mapping.MapContainer;
import com.here.android.mpa.mapping.MapObject;
import com.here.android.mpa.mapping.MapOverlayType;
import com.nokia.maps.annotation.Online;
import java.lang.ref.WeakReference;
import java.util.BitSet;
import java.util.List;

@Online
public class MapObjectImpl extends ViewObjectImpl {
    static int b = 65535;
    private static k<MapObject, MapObjectImpl> d = null;
    private cq a = new cq(ViewObjectImpl.class.getName());
    public final ar c = new ar();
    private WeakReference<MapImpl> e = new WeakReference(null);
    private Object f = new Object();

    private native int getReserveSemanticTypeNative();

    private native int getSemanticTypeNative();

    private native int getTypeNative();

    private native long getVisibleMaskNative();

    private native void setReserveSemanticTypeNative(int i);

    private native void setSemanticTypeNative(int i);

    private native void setVisibleNative(int i, int i2, boolean z);

    private native void setVisibleNative(int i, boolean z);

    private native void setVisibleNative(boolean z);

    private native void setzIndexNative(int i);

    public native MapContainerImpl getParentNative();

    public native int getZIndex();

    public native boolean isVisible();

    static MapObjectImpl d(MapObject mapObject) {
        if (d != null) {
            return (MapObjectImpl) d.a(mapObject);
        }
        return null;
    }

    static MapObjectImpl[] a(List<MapObject> list) {
        MapObjectImpl[] mapObjectImplArr = new MapObjectImpl[list.size()];
        int i = 0;
        for (MapObject mapObject : list) {
            if (mapObject == null) {
                return null;
            }
            mapObjectImplArr[i] = d(mapObject);
            i++;
        }
        return mapObjectImplArr;
    }

    public static void a(k<MapObject, MapObjectImpl> kVar) {
        d = kVar;
    }

    public Type k() {
        return Type.USER_OBJECT;
    }

    public MapObject.Type l() {
        switch (getTypeNative()) {
            case 0:
                return MapObject.Type.MARKER;
            case 1:
                return MapObject.Type.SCREEN_MARKER;
            case 2:
                return MapObject.Type.POLYGON;
            case 3:
                return MapObject.Type.POLYLINE;
            case 4:
                return MapObject.Type.ROUTE;
            case 5:
                return MapObject.Type.CONTAINER;
            case 6:
                return MapObject.Type.CIRCLE;
            case 7:
                return MapObject.Type.LOCAL_MODEL;
            case 8:
                return MapObject.Type.GEO_MODEL;
            case 9:
                return MapObject.Type.LABELED_MARKER;
            default:
                return MapObject.Type.UNKNOWN;
        }
    }

    public MapContainer m() {
        return MapContainerImpl.a(getParentNative());
    }

    protected MapObjectImpl() {
    }

    protected MapObjectImpl(int i) {
        super(i);
    }

    public void b(boolean z) {
        if (isVisible() != z) {
            setVisibleNative(z);
            this.c.a(this, null);
            MapImpl p = p();
            if (p != null) {
                p.redraw();
            }
        }
    }

    public void d(int i) {
        setVisibleNative(i, true);
        o();
    }

    public void e(int i) {
        setVisibleNative(i, false);
        o();
    }

    public void b(int i, int i2) {
        setVisibleNative(i, i2, true);
        o();
    }

    public void c(int i, int i2) {
        setVisibleNative(i, i2, false);
        o();
    }

    public void c(boolean z) {
        MapImpl p = p();
        if (p != null) {
            setVisibleNative((int) p.getMinZoomLevel(), (int) p.getMaxZoomLevel(), z);
        }
        o();
    }

    public BitSet n() {
        BitSet bitSet = new BitSet();
        int i = 0;
        MapImpl p = p();
        if (p != null) {
            int maxZoomLevel = (int) p.getMaxZoomLevel();
            for (long visibleMaskNative = getVisibleMaskNative(); visibleMaskNative != 0 && i <= maxZoomLevel; visibleMaskNative >>>= 1) {
                if (visibleMaskNative % 2 != 0) {
                    bitSet.set(i);
                }
                i++;
            }
        }
        return bitSet;
    }

    public void f(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Z index should be >= 0.");
        }
        setzIndexNative(i);
        o();
    }

    public void a(MapImpl mapImpl) {
        synchronized (this.f) {
            this.e = new WeakReference(mapImpl);
        }
    }

    protected void o() {
        MapImpl p = p();
        if (p != null && isVisible()) {
            p.redraw();
        }
    }

    protected MapImpl p() {
        MapImpl mapImpl;
        synchronized (this.f) {
            mapImpl = (MapImpl) this.e.get();
        }
        return mapImpl;
    }

    public void a(MapOverlayType mapOverlayType) {
        setSemanticTypeNative(mapOverlayType.ordinal());
    }

    public MapOverlayType q() {
        return MapOverlayType.values()[getSemanticTypeNative()];
    }

    public void b(MapOverlayType mapOverlayType) {
        dy.a(l() == MapObject.Type.LABELED_MARKER, "Unsupported MapObject type.");
        setReserveSemanticTypeNative(mapOverlayType.ordinal());
    }

    public MapOverlayType r() {
        return MapOverlayType.values()[getReserveSemanticTypeNative()];
    }
}
