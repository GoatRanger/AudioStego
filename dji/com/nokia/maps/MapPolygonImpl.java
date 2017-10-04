package com.nokia.maps;

import android.graphics.Color;
import com.here.android.mpa.common.GeoPolygon;
import com.here.android.mpa.mapping.MapPolygon;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.ArrayList;
import java.util.List;

@Online
public class MapPolygonImpl extends MapObjectImpl {
    private static String a = MapPolygonImpl.class.getSimpleName();
    private static k<MapPolygon, MapPolygonImpl> d = null;
    private GeoPolygonImpl e = new GeoPolygonImpl();
    private List<GeoPolygon> f = null;
    private boolean g = false;

    private native void createNative();

    private native boolean createNative(GeoPolygonImpl geoPolygonImpl);

    private native int getAlpha();

    private native int getBlue();

    private native int getFillAlpha();

    private native int getFillBlue();

    private native int getFillGreen();

    private native int getFillRed();

    private native int getGreen();

    private native int getLineCapStyleNative();

    private native GeoPolygonImpl getPolygonNative();

    private native int getRed();

    private native boolean isValidNative();

    private native void setDepthTestEnabledNative(boolean z);

    private native void setFillColorNative(int i, int i2, int i3, int i4);

    private native void setLineColorNative(int i, int i2, int i3, int i4);

    private native boolean setPolygonNative(GeoPolygonImpl geoPolygonImpl);

    public native boolean getDepthTestEnabled();

    public native int getLineWidth();

    public native void setLineWidthNative(int i);

    public static void b(k<MapPolygon, MapPolygonImpl> kVar) {
        d = kVar;
    }

    @OnlineNative
    private MapPolygonImpl(int i) {
        super(i);
        bj.a(a, "MapPolygon(int ptr) - nativePtr=0x%08x", new Object[]{Integer.valueOf(this.nativeptr)});
    }

    public MapPolygonImpl() {
        bj.a(a, "MapPolygon() IN - nativePtr=0x%08x", new Object[]{Integer.valueOf(this.nativeptr)});
        createNative();
        bj.a(a, "MapPolygon() OUT - nativePtr=0x%08x", new Object[]{Integer.valueOf(this.nativeptr)});
    }

    public MapPolygonImpl(GeoPolygon geoPolygon, List<GeoPolygon> list) {
        bj.a(a, "MapPolygon(GeoPolygon polygon, List<GeoPolygon> holes) IN - nativePtr=0x%08x", new Object[]{Integer.valueOf(this.nativeptr)});
        this.f = list;
        GeoPolygonImpl a = GeoPolygonImpl.a(geoPolygon);
        if (a == null || !a.isValid()) {
            createNative();
            bj.c(a, "MapPolygon(GeoPolygon polygon, List<GeoPolygon> holes) OUT - nativePtr=0x%08x - EXCEPTION!!!", new Object[]{Integer.valueOf(this.nativeptr)});
            if (a == null || !a.isValid()) {
                throw new IllegalArgumentException("GeoPolygon is invalid.");
            }
        }
        b(a);
        if (createNative(d())) {
            bj.a(a, "MapPolygon(GeoPolygon polygon) OUT - nativePtr=0x%08x", new Object[]{Integer.valueOf(this.nativeptr)});
            return;
        }
        throw new IllegalArgumentException("GeoPolygon is not supported.");
    }

    public void a(int i) {
        setFillColorNative(Color.red(i), Color.green(i), Color.blue(i), Color.alpha(i));
        o();
    }

    public int a() {
        return Color.argb(getFillAlpha(), getFillRed(), getFillGreen(), getFillBlue());
    }

    private void a(GeoPolygonImpl geoPolygonImpl) {
        if (geoPolygonImpl.isValid()) {
            b(geoPolygonImpl);
            if (setPolygonNative(d())) {
                o();
                return;
            }
            throw new IllegalArgumentException("GeoPolygon is not supported.");
        }
        throw new IllegalArgumentException("GeoPolygon is invalid.");
    }

    public void a(GeoPolygon geoPolygon) {
        a(GeoPolygonImpl.a(geoPolygon));
    }

    private void b(GeoPolygonImpl geoPolygonImpl) {
        if (geoPolygonImpl != null && geoPolygonImpl.isValid() && this.e != geoPolygonImpl) {
            int numberOfPoints = geoPolygonImpl.getNumberOfPoints();
            this.e.clear();
            List arrayList = new ArrayList(numberOfPoints);
            for (int i = 0; i < numberOfPoints; i++) {
                arrayList.add(geoPolygonImpl.b(i));
            }
            this.e.b(arrayList);
        }
    }

    private GeoPolygonImpl d() {
        GeoPolygonImpl geoPolygonImpl = this.e;
        if (!this.g || this.e.getNumberOfPoints() <= 1) {
            return geoPolygonImpl;
        }
        GeoPolygonImpl geoPolygonImpl2 = new GeoPolygonImpl();
        av avVar = new av();
        int numberOfPoints = this.e.getNumberOfPoints();
        for (int i = 0; i < numberOfPoints; i++) {
            List a;
            if (i < numberOfPoints - 1) {
                a = avVar.a(this.e.b(i), this.e.b(i + 1));
            } else {
                a = avVar.a(this.e.b(i), this.e.b(0));
            }
            int size = a.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (i2 != size - 1) {
                    geoPolygonImpl2.a((GeoCoordinateImpl) a.get(i2));
                }
            }
        }
        return geoPolygonImpl2;
    }

    public void b(int i) {
        setLineColorNative(Color.red(i), Color.green(i), Color.blue(i), Color.alpha(i));
        o();
    }

    public int b() {
        return Color.argb(getAlpha(), getRed(), getGreen(), getBlue());
    }

    public void c(int i) {
        if (i < 0 || i > 100) {
            throw new IllegalArgumentException("Line width is not within the supported range [0..100].");
        }
        setLineWidthNative(i);
        o();
    }

    public boolean c() {
        return this.g;
    }

    public void a(boolean z) {
        if (this.g != z) {
            this.g = z;
            if (this.e != null) {
                a(this.e);
            }
        }
    }

    public void d(boolean z) {
        setDepthTestEnabledNative(z);
        o();
    }
}
