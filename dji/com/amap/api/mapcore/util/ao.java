package com.amap.api.mapcore.util;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IOverlayDelegate;
import com.autonavi.amap.mapcore.interfaces.IPolylineDelegate;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;

class ao implements IPolylineDelegate {
    private float A = 0.0f;
    private float B = 0.0f;
    private float C;
    private float D;
    private float E;
    private float F;
    private float G = 0.0f;
    private float H = 0.0f;
    private float[] I;
    private int[] J;
    private int[] K;
    private double L = 5.0d;
    private v a;
    private String b;
    private List<IPoint> c = new ArrayList();
    private List<FPoint> d = new ArrayList();
    private List<LatLng> e = new ArrayList();
    private List<BitmapDescriptor> f = new ArrayList();
    private List<Integer> g = new ArrayList();
    private List<Integer> h = new ArrayList();
    private FloatBuffer i;
    private BitmapDescriptor j = null;
    private LatLngBounds k = null;
    private Object l = new Object();
    private boolean m = true;
    private boolean n = true;
    private boolean o = false;
    private boolean p = false;
    private boolean q = false;
    private boolean r = true;
    private boolean s = false;
    private boolean t = false;
    private boolean u = true;
    private int v = 0;
    private int w = 0;
    private int x = -16777216;
    private int y = 0;
    private float z = 10.0f;

    public void a(boolean z) {
        this.u = z;
        this.a.a.setRunLowFrame(false);
    }

    public void setGeodesic(boolean z) throws RemoteException {
        this.o = z;
        this.a.a.setRunLowFrame(false);
    }

    public boolean isGeodesic() {
        return this.o;
    }

    public void setDottedLine(boolean z) {
        if (this.v == 2 || this.v == 0) {
            this.p = z;
            if (z && this.n) {
                this.v = 2;
            }
            this.a.a.setRunLowFrame(false);
        }
    }

    public boolean isDottedLine() {
        return this.p;
    }

    public ao(v vVar) {
        this.a = vVar;
        try {
            this.b = getId();
        } catch (Throwable e) {
            ee.a(e, "PolylineDelegateImp", "create");
            e.printStackTrace();
        }
    }

    void a(List<LatLng> list) throws RemoteException {
        List arrayList = new ArrayList();
        Builder builder = LatLngBounds.builder();
        if (list != null) {
            LatLng latLng = null;
            for (LatLng latLng2 : list) {
                if (!(latLng2 == null || latLng2.equals(latLng))) {
                    IPoint iPoint;
                    if (!this.o) {
                        iPoint = new IPoint();
                        this.a.a.latlon2Geo(latLng2.latitude, latLng2.longitude, iPoint);
                        arrayList.add(iPoint);
                        builder.include(latLng2);
                    } else if (latLng != null) {
                        if (Math.abs(latLng2.longitude - latLng.longitude) < 0.01d) {
                            iPoint = new IPoint();
                            this.a.a.latlon2Geo(latLng.latitude, latLng.longitude, iPoint);
                            arrayList.add(iPoint);
                            builder.include(latLng);
                            iPoint = new IPoint();
                            this.a.a.latlon2Geo(latLng2.latitude, latLng2.longitude, iPoint);
                            arrayList.add(iPoint);
                            builder.include(latLng2);
                        } else {
                            a(latLng, latLng2, arrayList, builder);
                        }
                    }
                    latLng = latLng2;
                }
            }
        }
        this.c = arrayList;
        this.y = 0;
        if (this.c.size() > 0) {
            this.k = builder.build();
        }
        this.a.a.setRunLowFrame(false);
    }

    IPoint a(IPoint iPoint, IPoint iPoint2, IPoint iPoint3, double d, int i) {
        IPoint iPoint4 = new IPoint();
        double d2 = (double) (iPoint2.x - iPoint.x);
        double d3 = (double) (iPoint2.y - iPoint.y);
        iPoint4.y = (int) (((((double) i) * d) / Math.sqrt(((d3 * d3) / (d2 * d2)) + 1.0d)) + ((double) iPoint3.y));
        iPoint4.x = (int) (((d3 * ((double) (iPoint3.y - iPoint4.y))) / d2) + ((double) iPoint3.x));
        return iPoint4;
    }

    void a(List<IPoint> list, List<IPoint> list2, double d) {
        if (list.size() == 3) {
            for (int i = 0; i <= 10; i = (int) (((float) i) + 1.0f)) {
                float f = ((float) i) / 10.0f;
                IPoint iPoint = new IPoint();
                double d2 = ((((1.0d - ((double) f)) * (1.0d - ((double) f))) * ((double) ((IPoint) list.get(0)).x)) + (((((double) (2.0f * f)) * (1.0d - ((double) f))) * ((double) ((IPoint) list.get(1)).x)) * d)) + ((double) (((float) ((IPoint) list.get(2)).x) * (f * f)));
                double d3 = ((((1.0d - ((double) f)) * (1.0d - ((double) f))) * ((double) ((IPoint) list.get(0)).y)) + (((((double) (2.0f * f)) * (1.0d - ((double) f))) * ((double) ((IPoint) list.get(1)).y)) * d)) + ((double) (((float) ((IPoint) list.get(2)).y) * (f * f)));
                double d4 = (((1.0d - ((double) f)) * (1.0d - ((double) f))) + ((((double) (2.0f * f)) * (1.0d - ((double) f))) * d)) + ((double) (f * f));
                iPoint.x = (int) (d2 / ((((1.0d - ((double) f)) * (1.0d - ((double) f))) + ((((double) (2.0f * f)) * (1.0d - ((double) f))) * d)) + ((double) (f * f))));
                iPoint.y = (int) (d3 / d4);
                list2.add(iPoint);
            }
        }
    }

    void a(LatLng latLng, LatLng latLng2, List<IPoint> list, Builder builder) {
        double abs = (Math.abs(latLng.longitude - latLng2.longitude) * 3.141592653589793d) / 180.0d;
        LatLng latLng3 = new LatLng((latLng2.latitude + latLng.latitude) / 2.0d, (latLng2.longitude + latLng.longitude) / 2.0d, false);
        builder.include(latLng).include(latLng3).include(latLng2);
        int i = latLng3.latitude > 0.0d ? -1 : 1;
        IPoint iPoint = new IPoint();
        this.a.a.latlon2Geo(latLng.latitude, latLng.longitude, iPoint);
        IPoint iPoint2 = new IPoint();
        this.a.a.latlon2Geo(latLng2.latitude, latLng2.longitude, iPoint2);
        IPoint iPoint3 = new IPoint();
        this.a.a.latlon2Geo(latLng3.latitude, latLng3.longitude, iPoint3);
        double cos = Math.cos(0.5d * abs);
        IPoint a = a(iPoint, iPoint2, iPoint3, (Math.hypot((double) (iPoint.x - iPoint2.x), (double) (iPoint.y - iPoint2.y)) * 0.5d) * Math.tan(0.5d * abs), i);
        List arrayList = new ArrayList();
        arrayList.add(iPoint);
        arrayList.add(a);
        arrayList.add(iPoint2);
        a(arrayList, (List) list, cos);
    }

    public void remove() throws RemoteException {
        this.a.c(getId());
        if (this.K != null && this.K.length > 0) {
            for (int valueOf : this.K) {
                this.a.a(Integer.valueOf(valueOf));
            }
        }
        if (this.w > 0) {
            this.a.a(Integer.valueOf(this.w));
        }
        this.a.a.setRunLowFrame(false);
    }

    public String getId() throws RemoteException {
        if (this.b == null) {
            this.b = v.a("Polyline");
        }
        return this.b;
    }

    public void setPoints(List<LatLng> list) throws RemoteException {
        try {
            this.e = list;
            synchronized (this.l) {
                a((List) list);
            }
            this.r = true;
            this.a.a.setRunLowFrame(false);
        } catch (Throwable th) {
            ee.a(th, "PolylineDelegateImp", "setPoints");
            this.c.clear();
            th.printStackTrace();
        }
    }

    public List<LatLng> getPoints() throws RemoteException {
        return this.e;
    }

    public void setWidth(float f) throws RemoteException {
        this.z = f;
        this.a.a.setRunLowFrame(false);
    }

    public float getWidth() throws RemoteException {
        return this.z;
    }

    public void setColor(int i) {
        if (this.v == 0 || this.v == 2) {
            this.x = i;
            this.C = ((float) Color.alpha(i)) / 255.0f;
            this.D = ((float) Color.red(i)) / 255.0f;
            this.E = ((float) Color.green(i)) / 255.0f;
            this.F = ((float) Color.blue(i)) / 255.0f;
            if (this.n) {
                this.v = 0;
            }
            this.a.a.setRunLowFrame(false);
        }
    }

    public int getColor() throws RemoteException {
        return this.x;
    }

    public void setZIndex(float f) throws RemoteException {
        this.A = f;
        this.a.b();
        this.a.a.setRunLowFrame(false);
    }

    public float getZIndex() throws RemoteException {
        return this.A;
    }

    public void setVisible(boolean z) throws RemoteException {
        this.m = z;
        this.a.a.setRunLowFrame(false);
    }

    public boolean isVisible() throws RemoteException {
        return this.m;
    }

    public boolean equalsRemote(IOverlayDelegate iOverlayDelegate) throws RemoteException {
        if (equals(iOverlayDelegate) || iOverlayDelegate.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() throws RemoteException {
        return super.hashCode();
    }

    public boolean checkInBounds() {
        return true;
    }

    public void calMapFPoint() throws RemoteException {
        synchronized (this.l) {
            this.d.clear();
            this.t = false;
            this.I = new float[(this.c.size() * 3)];
            int i = 0;
            for (IPoint iPoint : this.c) {
                FPoint fPoint = new FPoint();
                this.a.a.geo2Map(iPoint.y, iPoint.x, fPoint);
                this.I[i * 3] = fPoint.x;
                this.I[(i * 3) + 1] = fPoint.y;
                this.I[(i * 3) + 2] = 0.0f;
                this.d.add(fPoint);
                i++;
            }
        }
        if (!this.u) {
            this.i = dj.a(this.I);
        }
        this.y = this.c.size();
        a();
    }

    private void a() {
        if (this.y <= 5000 || this.B > 12.0f) {
            this.H = this.a.a.getMapProjection().getMapLenWithWin(10);
            return;
        }
        float f = (this.z / 2.0f) + (this.B / 2.0f);
        if (f > 200.0f) {
            f = 200.0f;
        }
        this.H = this.a.a.getMapProjection().getMapLenWithWin((int) f);
    }

    private void b(List<FPoint> list) throws RemoteException {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        if (size >= 2) {
            FPoint fPoint = (FPoint) list.get(0);
            arrayList.add(fPoint);
            int i = 1;
            FPoint fPoint2 = fPoint;
            while (i < size - 1) {
                fPoint = (FPoint) list.get(i);
                if (a(fPoint2, fPoint)) {
                    arrayList.add(fPoint);
                } else {
                    fPoint = fPoint2;
                }
                i++;
                fPoint2 = fPoint;
            }
            arrayList.add(list.get(size - 1));
            this.I = new float[(arrayList.size() * 3)];
            this.J = null;
            this.J = new int[arrayList.size()];
            Iterator it = arrayList.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                fPoint = (FPoint) it.next();
                this.J[i2] = i2;
                this.I[i2 * 3] = fPoint.x;
                this.I[(i2 * 3) + 1] = fPoint.y;
                this.I[(i2 * 3) + 2] = 0.0f;
                i2++;
            }
        }
    }

    private boolean a(FPoint fPoint, FPoint fPoint2) {
        return Math.abs(fPoint2.x - fPoint.x) >= this.H || Math.abs(fPoint2.y - fPoint.y) >= this.H;
    }

    public void a(BitmapDescriptor bitmapDescriptor) {
        this.n = false;
        this.v = 1;
        this.j = bitmapDescriptor;
        this.a.a.setRunLowFrame(false);
    }

    public void draw(GL10 gl10) throws RemoteException {
        if (this.c != null && this.c.size() != 0 && this.z > 0.0f) {
            if (this.r) {
                calMapFPoint();
                this.r = false;
            }
            if (this.I != null && this.y > 0) {
                if (this.u) {
                    a(gl10);
                } else {
                    if (this.i == null) {
                        this.i = dj.a(this.I);
                    }
                    t.a(gl10, 3, this.x, this.i, this.z, this.y);
                }
            }
            this.t = true;
        }
    }

    private void a(GL10 gl10) {
        float mapLenWithWin = this.a.a.getMapProjection().getMapLenWithWin((int) this.z);
        switch (this.v) {
            case 0:
                f(gl10, mapLenWithWin);
                return;
            case 1:
                d(gl10, mapLenWithWin);
                return;
            case 2:
                e(gl10, mapLenWithWin);
                return;
            case 3:
                c(gl10, mapLenWithWin);
                return;
            case 4:
                b(gl10, mapLenWithWin);
                return;
            case 5:
                a(gl10, mapLenWithWin);
                return;
            default:
                return;
        }
    }

    private void a(GL10 gl10, float f) {
        int i = 0;
        if (!this.q) {
            this.K = new int[this.f.size()];
            for (int i2 = 0; i2 < this.K.length; i2++) {
                int i3;
                int texsureId = this.a.a.getTexsureId();
                if (texsureId == 0) {
                    int[] iArr = new int[]{0};
                    gl10.glGenTextures(1, iArr, 0);
                    i3 = iArr[0];
                } else {
                    i3 = texsureId;
                }
                dj.b(gl10, i3, ((BitmapDescriptor) this.f.get(i2)).getBitmap(), true);
                this.K[i2] = i3;
            }
            this.q = true;
        }
        int[] iArr2 = new int[this.g.size()];
        while (i < iArr2.length) {
            iArr2[i] = this.K[((Integer) this.g.get(i)).intValue()];
            i++;
        }
        AMapNativeRenderer.nativeDrawLineByMultiTextureID(this.I, this.I.length, f, iArr2, iArr2.length, this.J, this.J.length, this.G);
    }

    private void b(GL10 gl10, float f) {
        int[] iArr = new int[this.h.size()];
        for (int i = 0; i < this.h.size(); i++) {
            iArr[i] = ((Integer) this.h.get(i)).intValue();
        }
        AMapNativeRenderer.nativeDrawGradientColorLine(this.I, this.I.length, f, iArr, this.h.size(), this.J, this.J.length, this.a.a.getLineTextureID());
    }

    private void c(GL10 gl10, float f) {
        int[] iArr = new int[this.h.size()];
        for (int i = 0; i < this.h.size(); i++) {
            iArr[i] = ((Integer) this.h.get(i)).intValue();
        }
        AMapNativeRenderer.nativeDrawLineByMultiColor(this.I, this.I.length, f, this.a.a.getLineTextureID(), iArr, this.h.size(), this.J, this.J.length);
    }

    private void d(GL10 gl10, float f) {
        if (!this.q) {
            this.w = this.a.a.getTexsureId();
            if (this.w == 0) {
                int[] iArr = new int[]{0};
                gl10.glGenTextures(1, iArr, 0);
                this.w = iArr[0];
            }
            if (this.j != null) {
                dj.b(gl10, this.w, this.j.getBitmap(), true);
            }
            this.q = true;
        }
        try {
            List list = this.d;
            if (b()) {
                synchronized (this.l) {
                    list = dj.a(this.a.a, this.d, false);
                }
            }
            b(list);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AMapNativeRenderer.nativeDrawLineByTextureID(this.I, this.I.length, f, this.w, this.D, this.E, this.F, this.C, this.G, false, false, false);
    }

    private void e(GL10 gl10, float f) {
        AMapNativeRenderer.nativeDrawLineByTextureID(this.I, this.I.length, f, this.a.a.getImaginaryLineTextureID(), this.D, this.E, this.F, this.C, 0.0f, true, true, false);
    }

    private void f(GL10 gl10, float f) {
        try {
            List list = this.d;
            if (b()) {
                synchronized (this.l) {
                    list = dj.a(this.a.a, this.d, false);
                }
            }
            b(list);
            AMapNativeRenderer.nativeDrawLineByTextureID(this.I, this.I.length, f, this.a.a.getLineTextureID(), this.D, this.E, this.F, this.C, 0.0f, false, true, false);
        } catch (Throwable th) {
        }
    }

    private boolean b() {
        try {
            this.B = this.a.a.getCameraPosition().zoom;
            a();
            if (this.B <= 10.0f || this.v > 2) {
                return false;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            if (this.a.a == null) {
                return false;
            }
            Rect rect = new Rect(-100, -100, this.a.a.getMapWidth() + 100, this.a.a.getMapHeight() + 100);
            LatLng latLng = this.k.northeast;
            LatLng latLng2 = this.k.southwest;
            IPoint iPoint = new IPoint();
            this.a.a.getLatLng2Pixel(latLng.latitude, latLng2.longitude, iPoint);
            IPoint iPoint2 = new IPoint();
            this.a.a.getLatLng2Pixel(latLng.latitude, latLng.longitude, iPoint2);
            IPoint iPoint3 = new IPoint();
            this.a.a.getLatLng2Pixel(latLng2.latitude, latLng.longitude, iPoint3);
            IPoint iPoint4 = new IPoint();
            this.a.a.getLatLng2Pixel(latLng2.latitude, latLng2.longitude, iPoint4);
            if (rect.contains(iPoint.x, iPoint.y) && rect.contains(iPoint2.x, iPoint2.y) && rect.contains(iPoint3.x, iPoint3.y) && rect.contains(iPoint4.x, iPoint4.y)) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public void destroy() {
        try {
            remove();
            if (this.I != null) {
                this.I = null;
            }
            if (this.i != null) {
                this.i.clear();
                this.i = null;
            }
            if (this.f != null && this.f.size() > 0) {
                for (BitmapDescriptor recycle : this.f) {
                    recycle.recycle();
                }
            }
            if (this.j != null) {
                this.j.recycle();
            }
            if (this.h != null) {
                this.h.clear();
                this.h = null;
            }
            if (this.g != null) {
                this.g.clear();
                this.g = null;
            }
        } catch (Throwable th) {
            ee.a(th, "PolylineDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "PolylineDelegateImp destroy");
        }
    }

    public boolean isDrawFinish() {
        return this.t;
    }

    public LatLng getNearestLatLng(LatLng latLng) {
        int i = 0;
        if (latLng == null) {
            return null;
        }
        if (this.e == null || this.e.size() == 0) {
            return null;
        }
        float f = 0.0f;
        int i2 = 0;
        while (i < this.e.size()) {
            try {
                float calculateLineDistance;
                int i3;
                if (i == 0) {
                    calculateLineDistance = AMapUtils.calculateLineDistance(latLng, (LatLng) this.e.get(i));
                    i3 = i2;
                } else {
                    calculateLineDistance = AMapUtils.calculateLineDistance(latLng, (LatLng) this.e.get(i));
                    if (f > calculateLineDistance) {
                        i3 = i;
                    } else {
                        calculateLineDistance = f;
                        i3 = i2;
                    }
                }
                i++;
                i2 = i3;
                f = calculateLineDistance;
            } catch (Throwable th) {
                ee.a(th, "PolylineDelegateImp", "getNearestLatLng");
                th.printStackTrace();
                return null;
            }
        }
        return (LatLng) this.e.get(i2);
    }

    public boolean contains(LatLng latLng) {
        Object obj = new float[this.I.length];
        System.arraycopy(this.I, 0, obj, 0, this.I.length);
        if (obj.length / 3 < 2) {
            return false;
        }
        try {
            ArrayList c = c();
            if (c == null || c.size() < 1) {
                return false;
            }
            double mapLenWithWin = (double) this.a.a.getMapProjection().getMapLenWithWin(((int) this.z) / 4);
            double mapLenWithWin2 = (double) this.a.a.getMapProjection().getMapLenWithWin((int) this.L);
            FPoint a = a(latLng);
            FPoint fPoint = null;
            for (int i = 0; i < c.size() - 1; i++) {
                FPoint fPoint2;
                if (i == 0) {
                    fPoint2 = (FPoint) c.get(i);
                } else {
                    fPoint2 = fPoint;
                }
                fPoint = (FPoint) c.get(i + 1);
                if ((mapLenWithWin2 + mapLenWithWin) - a(a, fPoint2, fPoint) >= 0.0d) {
                    c.clear();
                    return true;
                }
            }
            c.clear();
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private ArrayList<FPoint> c() {
        ArrayList<FPoint> arrayList = new ArrayList();
        int i = 0;
        while (i < this.I.length) {
            float f = this.I[i];
            i++;
            float f2 = this.I[i];
            i++;
            arrayList.add(new FPoint(f, f2));
            i++;
        }
        return arrayList;
    }

    private double a(FPoint fPoint, FPoint fPoint2, FPoint fPoint3) {
        return a((double) fPoint.x, (double) fPoint.y, (double) fPoint2.x, (double) fPoint2.y, (double) fPoint3.x, (double) fPoint3.y);
    }

    private FPoint a(LatLng latLng) {
        IPoint iPoint = new IPoint();
        this.a.a.latlon2Geo(latLng.latitude, latLng.longitude, iPoint);
        FPoint fPoint = new FPoint();
        this.a.a.geo2Map(iPoint.y, iPoint.x, fPoint);
        return fPoint;
    }

    private double a(double d, double d2, double d3, double d4, double d5, double d6) {
        double d7 = ((d5 - d3) * (d - d3)) + ((d6 - d4) * (d2 - d4));
        if (d7 <= 0.0d) {
            return Math.sqrt(((d - d3) * (d - d3)) + ((d2 - d4) * (d2 - d4)));
        }
        double d8 = ((d5 - d3) * (d5 - d3)) + ((d6 - d4) * (d6 - d4));
        if (d7 >= d8) {
            return Math.sqrt(((d - d5) * (d - d5)) + ((d2 - d6) * (d2 - d6)));
        }
        d7 /= d8;
        d8 = ((d5 - d3) * d7) + d3;
        d7 = (d7 * (d6 - d4)) + d4;
        return Math.sqrt(((d7 - d2) * (d7 - d2)) + ((d - d8) * (d - d8)));
    }

    public void setTransparency(float f) {
        this.G = f;
        this.a.a.setRunLowFrame(false);
    }

    public void setCustomTextureList(List<BitmapDescriptor> list) {
        if (list != null && list.size() != 0) {
            if (list.size() > 1) {
                this.n = false;
                this.v = 5;
                this.f = list;
                this.a.a.setRunLowFrame(false);
                return;
            }
            a((BitmapDescriptor) list.get(0));
        }
    }

    public void setCustemTextureIndex(List<Integer> list) {
        if (list != null && list.size() != 0) {
            this.g = c(list);
        }
    }

    public void setColorValues(List<Integer> list) {
        if (list != null && list.size() != 0) {
            if (list.size() > 1) {
                this.n = false;
                this.h = c(list);
                this.v = 3;
                this.a.a.setRunLowFrame(false);
                return;
            }
            setColor(((Integer) list.get(0)).intValue());
        }
    }

    public void useGradient(boolean z) {
        if (z && this.h != null && this.h.size() > 1) {
            this.s = z;
            this.v = 4;
            this.a.a.setRunLowFrame(false);
        }
    }

    private List<Integer> c(List<Integer> list) {
        Object obj = new int[list.size()];
        List<Integer> arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            int intValue = ((Integer) list.get(i3)).intValue();
            if (i3 == 0) {
                arrayList.add(Integer.valueOf(intValue));
            } else if (intValue != i2) {
                arrayList.add(Integer.valueOf(intValue));
            } else {
            }
            obj[i] = i3;
            i++;
            i2 = intValue;
        }
        this.J = new int[arrayList.size()];
        System.arraycopy(obj, 0, this.J, 0, this.J.length);
        return arrayList;
    }

    public void reLoadTexture() {
        this.q = false;
        this.w = 0;
        if (this.K != null) {
            Arrays.fill(this.K, 0);
        }
    }
}
