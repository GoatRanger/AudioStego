package com.nokia.maps;

import android.graphics.Color;
import com.here.android.mpa.common.GeoPolyline;
import com.here.android.mpa.mapping.MapPolyline;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.ArrayList;
import java.util.List;

@Online
public class MapPolylineImpl extends MapObjectImpl {
    private static k<MapPolyline, MapPolylineImpl> a = null;
    private GeoPolylineImpl d = new GeoPolylineImpl();
    private boolean e = false;

    private native int getAlpha();

    private native int getBlue();

    private native int getGreen();

    private native int getLineCapStyleNative();

    private native int getLineJointStyleNative();

    private native GeoPolylineImpl getPolylineNative();

    private native int getRed();

    private native void setDashPrimaryLengthNative(int i);

    private native void setDashSecondaryLengthNative(int i);

    private native void setDepthTestEnabledNative(boolean z);

    private native boolean setLineCapStyleNative(int i);

    private native void setLineColorNative(int i, int i2, int i3, int i4);

    private native void setLineJointStyleNative(int i);

    private native void setPolylineNative(GeoPolylineImpl geoPolylineImpl);

    protected native void createPolylineNative();

    protected native void createPolylineNative(GeoPolylineImpl geoPolylineImpl);

    public native int getDashPrimaryLength();

    public native int getDashSecondaryLength();

    public native boolean getDepthTestEnabled();

    public native int getLineWidth();

    public native boolean isDashEnabled();

    public native boolean isValid();

    public native void setDashEnabled(boolean z);

    public native void setLineWidthNative(int i);

    public static void b(k<MapPolyline, MapPolylineImpl> kVar) {
        a = kVar;
    }

    @OnlineNative
    private MapPolylineImpl(int i) {
        super(i);
    }

    public MapPolylineImpl() {
        createPolylineNative();
    }

    public MapPolylineImpl(GeoPolyline geoPolyline) {
        GeoPolylineImpl a = GeoPolylineImpl.a(geoPolyline);
        if (geoPolyline == null || !a.isValid()) {
            createPolylineNative();
            if (!a.isValid()) {
                throw new IllegalArgumentException("GeoPolyline is invalid.");
            }
        }
        b(a);
        createPolylineNative(d());
    }

    public void a(GeoPolyline geoPolyline) {
        a(GeoPolylineImpl.a(geoPolyline));
    }

    private void a(GeoPolylineImpl geoPolylineImpl) {
        if (geoPolylineImpl.isValid()) {
            b(geoPolylineImpl);
            setPolylineNative(d());
            o();
            return;
        }
        throw new IllegalArgumentException("GeoPolyline supplied is null");
    }

    private void b(GeoPolylineImpl geoPolylineImpl) {
        if (geoPolylineImpl != null && geoPolylineImpl.isValid() && this.d != geoPolylineImpl) {
            int numberOfPoints = geoPolylineImpl.getNumberOfPoints();
            this.d.clear();
            List arrayList = new ArrayList(numberOfPoints);
            for (int i = 0; i < numberOfPoints; i++) {
                arrayList.add(geoPolylineImpl.b(i));
            }
            this.d.b(arrayList);
        }
    }

    private GeoPolylineImpl d() {
        GeoPolylineImpl geoPolylineImpl = this.d;
        if (!this.e || this.d.getNumberOfPoints() <= 1) {
            return geoPolylineImpl;
        }
        GeoPolylineImpl geoPolylineImpl2 = new GeoPolylineImpl();
        av avVar = new av();
        int numberOfPoints = this.d.getNumberOfPoints();
        int i = 0;
        while (i < this.d.getNumberOfPoints()) {
            if (i + 1 < numberOfPoints && this.d.a(i + 1) != null) {
                for (GeoCoordinateImpl a : avVar.a(this.d.b(i), this.d.b(i + 1))) {
                    geoPolylineImpl2.a(a);
                }
            }
            i++;
        }
        return geoPolylineImpl2;
    }

    public void a(int i) {
        setLineColorNative(Color.red(i), Color.green(i), Color.blue(i), Color.alpha(i));
        o();
    }

    public int a() {
        return Color.argb(getAlpha(), getRed(), getGreen(), getBlue());
    }

    public void b(int i) {
        if (setLineCapStyleNative(i)) {
            o();
        }
    }

    public int b() {
        return getLineCapStyleNative();
    }

    public void c(int i) {
        if (i < 0 || i > 100) {
            throw new IllegalArgumentException("Line width is not within the supported range [0..100].");
        }
        setLineWidthNative(i);
        o();
    }

    public void a(boolean z) {
        setDepthTestEnabledNative(z);
        o();
    }

    public void g(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Length of a dash segment must be > 0");
        }
        setDashPrimaryLengthNative(i);
        o();
    }

    public void h(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Length of a dash segment must be > 0");
        }
        setDashSecondaryLengthNative(i);
        o();
    }

    public boolean c() {
        return this.e;
    }

    public void d(boolean z) {
        if (this.e != z) {
            this.e = z;
            if (this.d != null) {
                a(this.d);
            }
        }
    }
}
