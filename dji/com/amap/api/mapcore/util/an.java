package com.amap.api.mapcore.util;

import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IOverlayDelegate;
import com.autonavi.amap.mapcore.interfaces.IPolygonDelegate;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

class an implements IPolygonDelegate {
    private static float u = 1.0E10f;
    private IAMapDelegate a;
    private float b = 0.0f;
    private boolean c = true;
    private boolean d;
    private String e;
    private float f;
    private int g;
    private int h;
    private List<LatLng> i;
    private List<LatLng> j;
    private CopyOnWriteArrayList<IPoint> k = new CopyOnWriteArrayList();
    private List<FPoint> l = new ArrayList();
    private FloatBuffer m;
    private FloatBuffer n;
    private int o = 0;
    private int p = 0;
    private LatLngBounds q = null;
    private boolean r = false;
    private float s = 0.0f;
    private Object t = new Object();

    public an(IAMapDelegate iAMapDelegate) {
        this.a = iAMapDelegate;
        try {
            this.e = getId();
        } catch (Throwable e) {
            ee.a(e, "PolygonDelegateImp", "create");
            e.printStackTrace();
        }
    }

    public void remove() throws RemoteException {
        this.a.removeGLOverlay(getId());
        this.a.setRunLowFrame(false);
    }

    public String getId() throws RemoteException {
        if (this.e == null) {
            this.e = v.a("Polygon");
        }
        return this.e;
    }

    public void setPoints(List<LatLng> list) throws RemoteException {
        synchronized (this.t) {
            this.j = list;
            a((List) list);
            calMapFPoint();
            this.a.setRunLowFrame(false);
        }
    }

    public List<LatLng> getPoints() throws RemoteException {
        return this.j;
    }

    public void setZIndex(float f) throws RemoteException {
        this.b = f;
        this.a.changeGLOverlayIndex();
        this.a.setRunLowFrame(false);
    }

    public float getZIndex() throws RemoteException {
        return this.b;
    }

    public void setVisible(boolean z) throws RemoteException {
        this.c = z;
        this.a.setRunLowFrame(false);
    }

    public boolean isVisible() throws RemoteException {
        return this.c;
    }

    public boolean equalsRemote(IOverlayDelegate iOverlayDelegate) throws RemoteException {
        if (equals(iOverlayDelegate) || iOverlayDelegate.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    void a(List<LatLng> list) throws RemoteException {
        Builder builder = LatLngBounds.builder();
        this.k.clear();
        if (list != null) {
            Object obj = null;
            for (LatLng latLng : list) {
                if (!latLng.equals(obj)) {
                    IPoint iPoint = new IPoint();
                    this.a.latlon2Geo(latLng.latitude, latLng.longitude, iPoint);
                    this.k.add(iPoint);
                    builder.include(latLng);
                    obj = latLng;
                }
            }
            int size = this.k.size();
            if (size > 1) {
                IPoint iPoint2 = (IPoint) this.k.get(0);
                IPoint iPoint3 = (IPoint) this.k.get(size - 1);
                if (iPoint2.x == iPoint3.x && iPoint2.y == iPoint3.y) {
                    this.k.remove(size - 1);
                }
            }
        }
        this.q = builder.build();
        if (this.m != null) {
            this.m.clear();
        }
        if (this.n != null) {
            this.n.clear();
        }
        this.o = 0;
        this.p = 0;
        this.a.setRunLowFrame(false);
    }

    public void calMapFPoint() throws RemoteException {
        synchronized (this.t) {
            this.l.clear();
            this.r = false;
            Iterator it = this.k.iterator();
            while (it.hasNext()) {
                IPoint iPoint = (IPoint) it.next();
                FPoint fPoint = new FPoint();
                this.a.geo2Map(iPoint.y, iPoint.x, fPoint);
                this.l.add(fPoint);
            }
            b();
        }
    }

    public int hashCodeRemote() throws RemoteException {
        return super.hashCode();
    }

    public boolean checkInBounds() {
        if (this.q == null) {
            return false;
        }
        LatLngBounds mapBounds = this.a.getMapBounds();
        if (mapBounds == null) {
            return true;
        }
        if (this.q.contains(mapBounds) || this.q.intersects(mapBounds)) {
            return true;
        }
        return false;
    }

    public void draw(GL10 gl10) throws RemoteException {
        if (this.k != null && this.k.size() != 0) {
            if (this.m == null || this.n == null || this.o == 0 || this.p == 0) {
                calMapFPoint();
            }
            List list = this.l;
            if (a()) {
                synchronized (this.t) {
                    list = dj.a(this.a, this.l, true);
                }
            }
            if (list.size() > 2) {
                b(list);
                if (this.m != null && this.n != null && this.o > 0 && this.p > 0) {
                    t.a(gl10, this.g, this.h, this.m, this.f, this.n, this.o, this.p);
                }
            }
            this.r = true;
        }
    }

    private boolean a() {
        float zoomLevel = this.a.getZoomLevel();
        b();
        if (zoomLevel <= 10.0f) {
            return false;
        }
        try {
            if (this.a == null) {
                return false;
            }
            Rect rect = new Rect(-100, -100, this.a.getMapWidth() + 100, this.a.getMapHeight() + 100);
            LatLng latLng = this.q.northeast;
            LatLng latLng2 = this.q.southwest;
            IPoint iPoint = new IPoint();
            this.a.getLatLng2Pixel(latLng.latitude, latLng2.longitude, iPoint);
            IPoint iPoint2 = new IPoint();
            this.a.getLatLng2Pixel(latLng.latitude, latLng.longitude, iPoint2);
            IPoint iPoint3 = new IPoint();
            this.a.getLatLng2Pixel(latLng2.latitude, latLng.longitude, iPoint3);
            IPoint iPoint4 = new IPoint();
            this.a.getLatLng2Pixel(latLng2.latitude, latLng2.longitude, iPoint4);
            if (rect.contains(iPoint.x, iPoint.y) && rect.contains(iPoint2.x, iPoint2.y) && rect.contains(iPoint3.x, iPoint3.y) && rect.contains(iPoint4.x, iPoint4.y)) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private void b(List<FPoint> list) throws RemoteException {
        int i = 0;
        b();
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        if (size >= 2) {
            FPoint fPoint = (FPoint) list.get(0);
            arrayList.add(fPoint);
            int i2 = 1;
            FPoint fPoint2 = fPoint;
            while (i2 < size - 1) {
                fPoint = (FPoint) list.get(i2);
                if (a(fPoint2, fPoint)) {
                    arrayList.add(fPoint);
                } else {
                    fPoint = fPoint2;
                }
                i2++;
                fPoint2 = fPoint;
            }
            arrayList.add(list.get(size - 1));
            float[] fArr = new float[(arrayList.size() * 3)];
            FPoint[] fPointArr = new FPoint[arrayList.size()];
            Iterator it = arrayList.iterator();
            int i3 = 0;
            while (it.hasNext()) {
                fPoint = (FPoint) it.next();
                fArr[i3 * 3] = fPoint.x;
                fArr[(i3 * 3) + 1] = fPoint.y;
                fArr[(i3 * 3) + 2] = 0.0f;
                fPointArr[i3] = fPoint;
                i3++;
            }
            FPoint[] a = a(fPointArr);
            if (a.length == 0) {
                if (u == 1.0E10f) {
                    u = 1.0E8f;
                } else {
                    u = 1.0E10f;
                }
                a = a(fPointArr);
            }
            float[] fArr2 = new float[(a.length * 3)];
            int length = a.length;
            i3 = 0;
            while (i < length) {
                FPoint fPoint3 = a[i];
                fArr2[i3 * 3] = fPoint3.x;
                fArr2[(i3 * 3) + 1] = fPoint3.y;
                fArr2[(i3 * 3) + 2] = 0.0f;
                i3++;
                i++;
            }
            this.o = fPointArr.length;
            this.p = a.length;
            this.m = dj.a(fArr);
            this.n = dj.a(fArr2);
        }
    }

    private boolean a(FPoint fPoint, FPoint fPoint2) {
        return Math.abs(fPoint2.x - fPoint.x) >= this.s || Math.abs(fPoint2.y - fPoint.y) >= this.s;
    }

    private void b() {
        float zoomLevel = this.a.getZoomLevel();
        if (this.k.size() <= 5000 || zoomLevel > 12.0f) {
            this.s = this.a.getMapProjection().getMapLenWithWin(10);
            return;
        }
        zoomLevel = (zoomLevel / 2.0f) + (this.f / 2.0f);
        if (zoomLevel > 200.0f) {
            zoomLevel = 200.0f;
        }
        this.s = this.a.getMapProjection().getMapLenWithWin((int) zoomLevel);
    }

    public void setStrokeWidth(float f) throws RemoteException {
        this.f = f;
        this.a.setRunLowFrame(false);
    }

    public float getStrokeWidth() throws RemoteException {
        return this.f;
    }

    public void setFillColor(int i) throws RemoteException {
        this.g = i;
        this.a.setRunLowFrame(false);
    }

    public int getFillColor() throws RemoteException {
        return this.g;
    }

    public void setStrokeColor(int i) throws RemoteException {
        this.h = i;
        this.a.setRunLowFrame(false);
    }

    public int getStrokeColor() throws RemoteException {
        return this.h;
    }

    public void setHoles(List<LatLng> list) throws RemoteException {
        this.i = list;
        this.a.setRunLowFrame(false);
    }

    public List<LatLng> getHoles() {
        return this.i;
    }

    public void setGeodesic(boolean z) {
        this.d = z;
        this.a.setRunLowFrame(false);
    }

    public boolean isGeodesic() {
        return this.d;
    }

    static FPoint[] a(FPoint[] fPointArr) {
        int i = 0;
        int length = fPointArr.length;
        float[] fArr = new float[(length * 2)];
        for (int i2 = 0; i2 < length; i2++) {
            fArr[i2 * 2] = fPointArr[i2].x * u;
            fArr[(i2 * 2) + 1] = fPointArr[i2].y * u;
        }
        di a = new cz().a(fArr);
        length = a.b;
        FPoint[] fPointArr2 = new FPoint[length];
        while (i < length) {
            fPointArr2[i] = new FPoint();
            fPointArr2[i].x = fArr[a.a(i) * 2] / u;
            fPointArr2[i].y = fArr[(a.a(i) * 2) + 1] / u;
            i++;
        }
        return fPointArr2;
    }

    public void destroy() {
        try {
            if (this.m != null) {
                this.m.clear();
                this.m = null;
            }
            if (this.n != null) {
                this.n = null;
            }
        } catch (Throwable th) {
            ee.a(th, "PolygonDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "PolygonDelegateImp destroy");
        }
    }

    public boolean contains(LatLng latLng) throws RemoteException {
        try {
            return dj.a(latLng, getPoints());
        } catch (Throwable th) {
            ee.a(th, "PolygonDelegateImp", "contains");
            th.printStackTrace();
            return false;
        }
    }

    public boolean isDrawFinish() {
        return this.r;
    }
}
