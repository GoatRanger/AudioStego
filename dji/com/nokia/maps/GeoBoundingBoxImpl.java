package com.nokia.maps;

import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import dji.pilot.usercenter.protocol.d;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Online
public class GeoBoundingBoxImpl extends BaseNativeObject {
    private static k<GeoBoundingBox, GeoBoundingBoxImpl> b = null;
    private static am<GeoBoundingBox, GeoBoundingBoxImpl> c = null;
    private cq a = new cq(GeoBoundingBoxImpl.class.getName());

    private native void createNative(GeoCoordinateImpl geoCoordinateImpl, GeoCoordinateImpl geoCoordinateImpl2);

    private native void destroyNative();

    private native double getBottom();

    private native double getLeft();

    private native double getRight();

    private native double getTop();

    private native void setBottomRightNative(GeoCoordinateImpl geoCoordinateImpl);

    private native void setCoordinatesNative(GeoCoordinateImpl geoCoordinateImpl, GeoCoordinateImpl geoCoordinateImpl2);

    private native void setTopLeftNative(GeoCoordinateImpl geoCoordinateImpl);

    public native GeoCoordinateImpl getCenter();

    static {
        ce.a(GeoBoundingBox.class);
    }

    public static void a(k<GeoBoundingBox, GeoBoundingBoxImpl> kVar, am<GeoBoundingBox, GeoBoundingBoxImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    @OnlineNative
    static GeoBoundingBoxImpl get(GeoBoundingBox geoBoundingBox) {
        return (GeoBoundingBoxImpl) b.a(geoBoundingBox);
    }

    @OnlineNative
    static GeoBoundingBox create(GeoBoundingBoxImpl geoBoundingBoxImpl) {
        if (geoBoundingBoxImpl != null) {
            return (GeoBoundingBox) c.a(geoBoundingBoxImpl);
        }
        return null;
    }

    public GeoBoundingBoxImpl(GeoCoordinateImpl geoCoordinateImpl, GeoCoordinateImpl geoCoordinateImpl2) {
        dy.a(geoCoordinateImpl.d(), "topLeft GeoCoordinate is invalid");
        dy.a(geoCoordinateImpl2.d(), "bottomRight GeoCoordinate is invalid");
        dy.a(geoCoordinateImpl.a() >= geoCoordinateImpl2.a(), "topLeft latitude is less than bottomRight latitude");
        createNative(geoCoordinateImpl, geoCoordinateImpl2);
    }

    public GeoBoundingBoxImpl(GeoCoordinate geoCoordinate, float f, float f2) {
        GeoCoordinateImpl geoCoordinateImpl = GeoCoordinateImpl.get(geoCoordinate);
        double d = (((double) (f / 2.0f)) / 6378137.0d) * 57.29577951308232d;
        double cos = ((((double) (f2 / 2.0f)) / 6378137.0d) * 57.29577951308232d) / Math.cos(Math.toRadians(geoCoordinateImpl.a()));
        createNative(new GeoCoordinateImpl(geoCoordinateImpl.a() + d, geoCoordinateImpl.b() - cos), new GeoCoordinateImpl(geoCoordinateImpl.a() - d, cos + geoCoordinateImpl.b()));
    }

    @OnlineNative
    GeoBoundingBoxImpl(int i) {
        this.nativeptr = i;
    }

    public GeoCoordinateImpl a() {
        return new GeoCoordinateImpl(getTop(), getLeft(), 0.0d);
    }

    public boolean a(GeoCoordinateImpl geoCoordinateImpl) {
        if (!geoCoordinateImpl.d() || geoCoordinateImpl.a() < b().a()) {
            return false;
        }
        setTopLeftNative(geoCoordinateImpl);
        return true;
    }

    public GeoCoordinateImpl b() {
        return new GeoCoordinateImpl(getBottom(), getRight(), 0.0d);
    }

    public boolean b(GeoCoordinateImpl geoCoordinateImpl) {
        if (!geoCoordinateImpl.d() || a().a() < geoCoordinateImpl.a()) {
            return false;
        }
        setBottomRightNative(geoCoordinateImpl);
        return true;
    }

    public boolean a(GeoCoordinateImpl geoCoordinateImpl, GeoCoordinateImpl geoCoordinateImpl2) {
        if (!geoCoordinateImpl.d() || !geoCoordinateImpl2.d() || geoCoordinateImpl.a() < geoCoordinateImpl2.a()) {
            return false;
        }
        setCoordinatesNative(geoCoordinateImpl, geoCoordinateImpl2);
        return true;
    }

    public double c() {
        double[] e = e(a());
        double[] e2 = e(b());
        if (e[1] <= e2[1]) {
            return e2[1] - e[1];
        }
        return (360.0d - e[1]) + e2[1];
    }

    public double d() {
        double[] e = e(a());
        double[] e2 = e(b());
        if (e[0] >= e2[0]) {
            return e[0] - e2[0];
        }
        return (180.0d - e2[1]) + e[1];
    }

    public static GeoBoundingBoxImpl a(GeoBoundingBoxImpl[] geoBoundingBoxImplArr) {
        GeoBoundingBoxImpl geoBoundingBoxImpl = geoBoundingBoxImplArr[0];
        for (int i = 1; i < geoBoundingBoxImplArr.length; i++) {
            geoBoundingBoxImpl = a(geoBoundingBoxImpl, geoBoundingBoxImplArr[i]);
        }
        return geoBoundingBoxImpl;
    }

    public boolean a(GeoBoundingBoxImpl geoBoundingBoxImpl) {
        return a(geoBoundingBoxImpl, true);
    }

    private boolean a(GeoBoundingBoxImpl geoBoundingBoxImpl, boolean z) {
        if (geoBoundingBoxImpl == null) {
            return false;
        }
        double[] c = c(geoBoundingBoxImpl);
        double d = c[0];
        double d2 = c[1];
        double d3 = c[2];
        double d4 = c[3];
        double[] c2 = c(this);
        double d5 = c2[0];
        double d6 = c2[1];
        double d7 = c2[2];
        double d8 = c2[3];
        return z ? d5 <= d && d3 <= d7 && d6 <= d2 && d4 <= d8 : d5 < d && d3 < d7 && d6 < d2 && d4 < d8;
    }

    private boolean a(GeoCoordinateImpl geoCoordinateImpl, GeoCoordinateImpl geoCoordinateImpl2, GeoCoordinateImpl geoCoordinateImpl3) {
        if (e(geoCoordinateImpl)[0] >= e(geoCoordinateImpl2)[0]) {
            if (e(geoCoordinateImpl)[0] >= e(geoCoordinateImpl3)[0] && e(geoCoordinateImpl2)[0] <= e(geoCoordinateImpl3)[0]) {
                return true;
            }
        } else if (e(geoCoordinateImpl)[0] >= e(geoCoordinateImpl3)[0] || e(geoCoordinateImpl2)[0] <= e(geoCoordinateImpl3)[0]) {
            return true;
        }
        return false;
    }

    public boolean c(GeoCoordinateImpl geoCoordinateImpl) {
        int i = 1;
        GeoCoordinateImpl a = a();
        GeoCoordinateImpl b = b();
        double[] e = e(geoCoordinateImpl);
        if (e(a)[1] > e(b)[1] ? e(a)[1] <= e[1] || e(b)[1] >= e[1] : e(a)[1] <= e[1] && e(b)[1] >= e[1]) {
            i = 0;
        }
        if (i != 0) {
            return a(a, b, geoCoordinateImpl);
        }
        return false;
    }

    public boolean b(GeoBoundingBoxImpl geoBoundingBoxImpl) {
        if (geoBoundingBoxImpl == null) {
            return false;
        }
        double[] c = c(geoBoundingBoxImpl);
        double d = c[0];
        double d2 = c[1];
        double d3 = c[2];
        double d4 = c[3];
        double[] c2 = c(this);
        return c2[3] >= d2 && d4 >= c2[1] && c2[2] >= d && d3 >= c2[0];
    }

    public boolean e() {
        return c() == 0.0d || d() == 0.0d;
    }

    public void d(GeoCoordinateImpl geoCoordinateImpl) {
        if (c(geoCoordinateImpl)) {
            double[] e = e(geoCoordinateImpl);
            double[] e2 = e(a());
            double[] e3 = e(b());
            a(e, e2, e3, 0);
            a(e, e2, e3, 1);
            a(a(e2));
            b(a(e3));
            return;
        }
        throw new IllegalArgumentException("box does not contain coordinate");
    }

    public boolean a(GeoBoundingBox geoBoundingBox) {
        return equals(get(geoBoundingBox));
    }

    public void a(float f, float f2) {
        double d = (((double) (f / 2.0f)) / 6378137.0d) * 57.29577951308232d;
        double cos = ((((double) (f2 / 2.0f)) / 6378137.0d) * 57.29577951308232d) / Math.cos(Math.toRadians(getCenter().a()));
        GeoCoordinateImpl geoCoordinateImpl = new GeoCoordinateImpl(getTop() + d, getLeft() - cos);
        GeoCoordinateImpl geoCoordinateImpl2 = new GeoCoordinateImpl(getBottom() - d, cos + getRight());
        a(geoCoordinateImpl);
        b(geoCoordinateImpl2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!GeoBoundingBoxImpl.class.isInstance(obj)) {
            return false;
        }
        GeoBoundingBoxImpl geoBoundingBoxImpl = (GeoBoundingBoxImpl) obj;
        if (geoBoundingBoxImpl.a().equals(a()) && geoBoundingBoxImpl.b().equals(b())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((a().hashCode() + 31) * 31) + b().hashCode();
    }

    private double[] e(GeoCoordinateImpl geoCoordinateImpl) {
        double a = geoCoordinateImpl.a();
        double b = geoCoordinateImpl.b();
        return new double[]{a + 90.0d, b + 180.0d};
    }

    private GeoCoordinateImpl a(double[] dArr) {
        return new GeoCoordinateImpl(dArr[0] - 90.0d, dArr[1] - 180.0d, 0.0d);
    }

    public static GeoBoundingBoxImpl a(GeoBoundingBoxImpl geoBoundingBoxImpl, GeoBoundingBoxImpl geoBoundingBoxImpl2) {
        if (geoBoundingBoxImpl == null) {
            return geoBoundingBoxImpl2;
        }
        if (geoBoundingBoxImpl2 == null) {
            return geoBoundingBoxImpl;
        }
        double d;
        double d2;
        double min = Math.min(Math.min(Math.min(geoBoundingBoxImpl.getTop(), geoBoundingBoxImpl.getBottom()), geoBoundingBoxImpl2.getTop()), geoBoundingBoxImpl2.getBottom());
        double max = Math.max(Math.max(Math.max(geoBoundingBoxImpl.getTop(), geoBoundingBoxImpl.getBottom()), geoBoundingBoxImpl2.getTop()), geoBoundingBoxImpl2.getBottom());
        double min2 = Math.min(Math.min(Math.min(geoBoundingBoxImpl.getRight(), geoBoundingBoxImpl.getLeft()), geoBoundingBoxImpl2.getRight()), geoBoundingBoxImpl2.getLeft());
        double max2 = Math.max(Math.max(Math.max(geoBoundingBoxImpl.getRight(), geoBoundingBoxImpl.getLeft()), geoBoundingBoxImpl2.getRight()), geoBoundingBoxImpl2.getLeft());
        double left = (geoBoundingBoxImpl.getLeft() == 180.0d || geoBoundingBoxImpl.getLeft() == -180.0d) ? 180.0d : geoBoundingBoxImpl.getLeft();
        if (geoBoundingBoxImpl.getRight() == 180.0d || geoBoundingBoxImpl.getRight() == -180.0d) {
            d = 180.0d;
        } else {
            d = geoBoundingBoxImpl.getRight();
        }
        double left2 = (geoBoundingBoxImpl2.getLeft() == 180.0d || geoBoundingBoxImpl2.getLeft() == -180.0d) ? 180.0d : geoBoundingBoxImpl2.getLeft();
        if (geoBoundingBoxImpl2.getRight() == 180.0d || geoBoundingBoxImpl2.getRight() == -180.0d) {
            d2 = 180.0d;
        } else {
            d2 = geoBoundingBoxImpl2.getRight();
        }
        if (left > d && left2 <= d2) {
            d = Math.max(d, Math.max(left2, d2));
            left2 = left;
            left = d;
        } else if (left <= d && left2 > d2) {
            left = Math.max(Math.max(left, d), d2);
        } else if (left <= d || left2 <= d2) {
            left = max2;
            left2 = min2;
        } else {
            left2 = Math.min(left, left2);
            left = Math.max(d, d2);
        }
        return new GeoBoundingBoxImpl(new GeoCoordinateImpl(max, left2), new GeoCoordinateImpl(min, left));
    }

    private double[] c(GeoBoundingBoxImpl geoBoundingBoxImpl) {
        double min = Math.min(geoBoundingBoxImpl.getTop(), geoBoundingBoxImpl.getBottom());
        double min2 = Math.min(geoBoundingBoxImpl.getRight(), geoBoundingBoxImpl.getLeft());
        double max = Math.max(geoBoundingBoxImpl.getTop(), geoBoundingBoxImpl.getBottom());
        double max2 = Math.max(geoBoundingBoxImpl.getRight(), geoBoundingBoxImpl.getLeft());
        return new double[]{min, min2, max, max2};
    }

    private void a(double[] dArr, double[] dArr2, double[] dArr3, int i) {
        double d = dArr[i] - dArr2[i];
        double d2 = dArr3[i] - dArr[i];
        if (d < d2) {
            dArr2[i] = dArr2[i] - (d2 - d);
            return;
        }
        dArr3[i] = (d - d2) + dArr3[i];
    }

    public String toString() {
        return "GeoBoundingBox [getTopLeft()=" + a() + ", getBottomRight()=" + b() + ", getWidth()=" + c() + ", getHeight()=" + d() + ", isEmpty()=" + e() + ", getCenter()=" + getCenter() + ", getTop()=" + getTop() + ", getLeft()=" + getLeft() + ", getBottom()=" + getBottom() + ", getRight()=" + getRight() + d.H;
    }

    protected void finalize() {
        if (this.nativeptr != 0) {
            destroyNative();
        }
    }

    public boolean a(GeoCoordinate geoCoordinate) {
        return a(GeoCoordinateImpl.get(geoCoordinate));
    }

    public boolean b(GeoCoordinate geoCoordinate) {
        return b(GeoCoordinateImpl.get(geoCoordinate));
    }

    public static GeoBoundingBox a(List<GeoBoundingBox> list) {
        int size = list.size();
        GeoBoundingBoxImpl[] geoBoundingBoxImplArr = new GeoBoundingBoxImpl[size];
        for (int i = 0; i < size; i++) {
            geoBoundingBoxImplArr[i] = get((GeoBoundingBox) list.get(i));
        }
        return create(a(geoBoundingBoxImplArr));
    }

    public boolean a(GeoCoordinate geoCoordinate, GeoCoordinate geoCoordinate2) {
        return a(GeoCoordinateImpl.get(geoCoordinate), GeoCoordinateImpl.get(geoCoordinate2));
    }

    public static GeoBoundingBox b(List<GeoCoordinate> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        Collections.sort(list, new Comparator<GeoCoordinate>() {
            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((GeoCoordinate) obj, (GeoCoordinate) obj2);
            }

            public int a(GeoCoordinate geoCoordinate, GeoCoordinate geoCoordinate2) {
                return Double.compare(geoCoordinate2.getLongitude(), geoCoordinate.getLongitude());
            }
        });
        double d = 90.0d;
        int i = 0;
        double d2 = -90.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        double d5 = -1.0d;
        for (GeoCoordinate geoCoordinate : list) {
            double latitude;
            double d6;
            if (geoCoordinate.getLatitude() < d) {
                latitude = geoCoordinate.getLatitude();
            } else {
                latitude = d;
            }
            if (geoCoordinate.getLatitude() > d2) {
                d = geoCoordinate.getLatitude();
            } else {
                d = d2;
            }
            int size = (i + 1) % list.size();
            GeoCoordinate geoCoordinate2 = (GeoCoordinate) list.get(size);
            double longitude = geoCoordinate2.getLongitude() - geoCoordinate.getLongitude();
            if (longitude < 0.0d) {
                longitude += 360.0d;
            }
            if (longitude > d5) {
                d4 = geoCoordinate2.getLongitude();
                d5 = geoCoordinate.getLongitude();
                d6 = longitude;
            } else {
                d6 = d5;
                d5 = d4;
                d4 = d3;
            }
            d3 = d4;
            d4 = d5;
            d5 = d6;
            i = size;
            d2 = d;
            d = latitude;
        }
        return new GeoBoundingBox(new GeoCoordinate(d2, d3), new GeoCoordinate(d, d4));
    }
}
