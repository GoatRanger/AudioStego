package com.amap.api.mapcore.util;

import android.graphics.Color;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.autonavi.amap.mapcore.AMapNativeRenderer;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.INavigateArrowDelegate;
import com.autonavi.amap.mapcore.interfaces.IOverlayDelegate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

class ak implements INavigateArrowDelegate {
    float a;
    float b;
    float c;
    float d;
    float e;
    float f;
    float g;
    float h;
    float[] i;
    private IAMapDelegate j;
    private float k = 10.0f;
    private int l = -16777216;
    private int m = -16777216;
    private float n = 0.0f;
    private boolean o = true;
    private String p;
    private CopyOnWriteArrayList<IPoint> q = new CopyOnWriteArrayList();
    private int r = 0;
    private boolean s = false;
    private LatLngBounds t = null;

    public ak(IAMapDelegate iAMapDelegate) {
        this.j = iAMapDelegate;
        try {
            this.p = getId();
        } catch (Throwable e) {
            ee.a(e, "NavigateArrowDelegateImp", "create");
            e.printStackTrace();
        }
    }

    void a(List<LatLng> list) throws RemoteException {
        Builder builder = LatLngBounds.builder();
        this.q.clear();
        if (list != null) {
            Object obj = null;
            for (LatLng latLng : list) {
                if (!(latLng == null || latLng.equals(r1))) {
                    IPoint iPoint = new IPoint();
                    this.j.latlon2Geo(latLng.latitude, latLng.longitude, iPoint);
                    this.q.add(iPoint);
                    builder.include(latLng);
                    obj = latLng;
                }
            }
        }
        this.t = builder.build();
        this.r = 0;
        this.j.setRunLowFrame(false);
    }

    public void remove() throws RemoteException {
        this.j.removeGLOverlay(getId());
        this.j.setRunLowFrame(false);
    }

    public String getId() throws RemoteException {
        if (this.p == null) {
            this.p = v.a("NavigateArrow");
        }
        return this.p;
    }

    public void setPoints(List<LatLng> list) throws RemoteException {
        a(list);
    }

    public List<LatLng> getPoints() throws RemoteException {
        return a();
    }

    private List<LatLng> a() throws RemoteException {
        if (this.q == null) {
            return null;
        }
        List<LatLng> arrayList = new ArrayList();
        Iterator it = this.q.iterator();
        while (it.hasNext()) {
            IPoint iPoint = (IPoint) it.next();
            if (iPoint != null) {
                DPoint dPoint = new DPoint();
                this.j.geo2Latlng(iPoint.x, iPoint.y, dPoint);
                arrayList.add(new LatLng(dPoint.y, dPoint.x));
            }
        }
        return arrayList;
    }

    public void setWidth(float f) throws RemoteException {
        this.k = f;
        this.j.setRunLowFrame(false);
    }

    public float getWidth() throws RemoteException {
        return this.k;
    }

    public void setTopColor(int i) throws RemoteException {
        this.l = i;
        this.a = ((float) Color.alpha(i)) / 255.0f;
        this.b = ((float) Color.red(i)) / 255.0f;
        this.c = ((float) Color.green(i)) / 255.0f;
        this.d = ((float) Color.blue(i)) / 255.0f;
        this.j.setRunLowFrame(false);
    }

    public int getTopColor() throws RemoteException {
        return this.l;
    }

    public void setSideColor(int i) throws RemoteException {
        this.m = i;
        this.e = ((float) Color.alpha(i)) / 255.0f;
        this.f = ((float) Color.red(i)) / 255.0f;
        this.g = ((float) Color.green(i)) / 255.0f;
        this.h = ((float) Color.blue(i)) / 255.0f;
        this.j.setRunLowFrame(false);
    }

    public int getSideColor() throws RemoteException {
        return this.m;
    }

    public void setZIndex(float f) throws RemoteException {
        this.n = f;
        this.j.changeGLOverlayIndex();
        this.j.setRunLowFrame(false);
    }

    public float getZIndex() throws RemoteException {
        return this.n;
    }

    public void setVisible(boolean z) throws RemoteException {
        this.o = z;
        this.j.setRunLowFrame(false);
    }

    public boolean isVisible() throws RemoteException {
        return this.o;
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
        if (this.t == null) {
            return false;
        }
        LatLngBounds mapBounds = this.j.getMapBounds();
        if (mapBounds == null) {
            return true;
        }
        if (mapBounds.contains(this.t) || this.t.intersects(mapBounds)) {
            return true;
        }
        return false;
    }

    public void calMapFPoint() throws RemoteException {
        this.s = false;
        FPoint fPoint = new FPoint();
        this.i = new float[(this.q.size() * 3)];
        Iterator it = this.q.iterator();
        int i = 0;
        while (it.hasNext()) {
            IPoint iPoint = (IPoint) it.next();
            this.j.geo2Map(iPoint.y, iPoint.x, fPoint);
            this.i[i * 3] = fPoint.x;
            this.i[(i * 3) + 1] = fPoint.y;
            this.i[(i * 3) + 2] = 0.0f;
            i++;
        }
        this.r = this.q.size();
    }

    public void draw(GL10 gl10) throws RemoteException {
        if (this.q != null && this.q.size() != 0 && this.k > 0.0f) {
            if (this.r == 0) {
                calMapFPoint();
            }
            if (this.i != null && this.r > 0) {
                float mapLenWithWin = this.j.getMapProjection().getMapLenWithWin((int) this.k);
                this.j.getMapProjection().getMapLenWithWin(1);
                AMapNativeRenderer.nativeDrawLineByTextureID(this.i, this.i.length, mapLenWithWin, this.j.getLineTextureID(), this.b, this.c, this.d, this.a, 0.0f, false, true, true);
            }
            this.s = true;
        }
    }

    public void destroy() {
        try {
            if (this.i != null) {
                this.i = null;
            }
        } catch (Throwable th) {
            ee.a(th, "NavigateArrowDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "NavigateArrowDelegateImp destroy");
        }
    }

    public boolean isDrawFinish() {
        return this.s;
    }
}
