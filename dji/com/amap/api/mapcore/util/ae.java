package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.Marker;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IMarkerDelegate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

class ae extends View {
    IAMapDelegate a;
    a b = new a();
    private CopyOnWriteArrayList<IMarkerDelegate> c = new CopyOnWriteArrayList(new ArrayList(500));
    private CopyOnWriteArrayList<am> d = new CopyOnWriteArrayList();
    private CopyOnWriteArrayList<Integer> e = new CopyOnWriteArrayList();
    private IPoint f;
    private IMarkerDelegate g;
    private Handler h = new Handler();
    private Runnable i = new af(this);
    private final Handler j = new Handler();
    private final Runnable k = new ag(this);

    static class a implements Serializable, Comparator<Object> {
        a() {
        }

        public int compare(Object obj, Object obj2) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) obj;
            IMarkerDelegate iMarkerDelegate2 = (IMarkerDelegate) obj2;
            if (!(iMarkerDelegate == null || iMarkerDelegate2 == null)) {
                try {
                    if (iMarkerDelegate.getZIndex() > iMarkerDelegate2.getZIndex()) {
                        return 1;
                    }
                    if (iMarkerDelegate.getZIndex() < iMarkerDelegate2.getZIndex()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    ee.a(th, "MapOverlayImageView", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    public IAMapDelegate a() {
        return this.a;
    }

    public ae(Context context) {
        super(context);
    }

    public ae(Context context, AttributeSet attributeSet, IAMapDelegate iAMapDelegate) {
        super(context, attributeSet);
        this.a = iAMapDelegate;
    }

    public synchronized IMarkerDelegate a(String str) throws RemoteException {
        IMarkerDelegate iMarkerDelegate;
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            iMarkerDelegate = (IMarkerDelegate) it.next();
            if (iMarkerDelegate != null && iMarkerDelegate.getId().equals(str)) {
                break;
            }
        }
        iMarkerDelegate = null;
        return iMarkerDelegate;
    }

    public synchronized boolean a(IMarkerDelegate iMarkerDelegate) {
        return this.c.contains(iMarkerDelegate);
    }

    protected synchronized int b() {
        return this.c.size();
    }

    public synchronized void b(String str) {
        Object obj;
        Iterator it;
        IMarkerDelegate iMarkerDelegate;
        if (str != null) {
            try {
                if (str.trim().length() != 0) {
                    obj = null;
                    this.g = null;
                    this.f = null;
                    if (obj == null) {
                        it = this.c.iterator();
                        while (it.hasNext()) {
                            ((IMarkerDelegate) it.next()).remove();
                        }
                        this.c.clear();
                    } else {
                        it = this.c.iterator();
                        while (it.hasNext()) {
                            iMarkerDelegate = (IMarkerDelegate) it.next();
                            if (!str.equals(iMarkerDelegate.getId())) {
                                iMarkerDelegate.remove();
                            }
                        }
                    }
                }
            } catch (Throwable e) {
                ee.a(e, "MapOverlayImageView", "clear");
                e.printStackTrace();
            }
        }
        obj = 1;
        this.g = null;
        this.f = null;
        if (obj == null) {
            it = this.c.iterator();
            while (it.hasNext()) {
                iMarkerDelegate = (IMarkerDelegate) it.next();
                if (!str.equals(iMarkerDelegate.getId())) {
                    iMarkerDelegate.remove();
                }
            }
        } else {
            it = this.c.iterator();
            while (it.hasNext()) {
                ((IMarkerDelegate) it.next()).remove();
            }
            this.c.clear();
        }
        return;
    }

    public synchronized void b(IMarkerDelegate iMarkerDelegate) {
        this.c.add(iMarkerDelegate);
        i();
    }

    public synchronized boolean c(IMarkerDelegate iMarkerDelegate) {
        f(iMarkerDelegate);
        return this.c.remove(iMarkerDelegate);
    }

    public synchronized void d(IMarkerDelegate iMarkerDelegate) {
        try {
            if (this.c.remove(iMarkerDelegate)) {
                l();
                this.c.add(iMarkerDelegate);
            }
        } catch (Throwable th) {
            ee.a(th, "MapOverlayImageView", "set2Top");
        }
    }

    public void e(IMarkerDelegate iMarkerDelegate) {
        if (this.f == null) {
            this.f = new IPoint();
        }
        Rect rect = iMarkerDelegate.getRect();
        this.f = new IPoint(rect.left + (rect.width() / 2), rect.top);
        this.g = iMarkerDelegate;
        try {
            this.a.showInfoWindow(this.g);
        } catch (Throwable th) {
            ee.a(th, "MapOverlayImageView", "showInfoWindow");
            th.printStackTrace();
        }
    }

    public void f(IMarkerDelegate iMarkerDelegate) {
        try {
            if (iMarkerDelegate.isInfoWindowShown()) {
                this.a.hiddenInfoWindowShown();
                this.g = null;
            } else if (this.g != null && this.g.getId() == iMarkerDelegate.getId()) {
                this.g = null;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized void c() {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
            try {
                if (iMarkerDelegate.isVisible()) {
                    iMarkerDelegate.calFPoint();
                }
            } catch (Throwable th) {
                ee.a(th, "MapOverlayImageView", "calFPoint");
                th.printStackTrace();
            }
        }
    }

    private void l() {
        try {
            Collection arrayList = new ArrayList(this.c);
            Collections.sort(arrayList, this.b);
            this.c = new CopyOnWriteArrayList(arrayList);
        } catch (Throwable th) {
            ee.a(th, "MapOverlayImageView", "changeOverlayIndex");
        }
    }

    public void a(GL10 gl10) {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            gl10.glDeleteTextures(1, new int[]{((Integer) it.next()).intValue()}, 0);
            this.a.deleteTexsureId(r0.intValue());
        }
        this.e.clear();
        if (!(this.g == null || this.g.isViewMode())) {
            k();
        }
        it = this.c.iterator();
        while (it.hasNext()) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
            if (iMarkerDelegate.checkInBounds() || iMarkerDelegate.isInfoWindowShown()) {
                iMarkerDelegate.drawMarker(gl10, this.a);
            }
        }
    }

    public synchronized boolean d() {
        boolean z;
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            if (!((IMarkerDelegate) it.next()).isAllowLow()) {
                z = false;
                break;
            }
        }
        z = true;
        return z;
    }

    public IMarkerDelegate e() {
        return this.g;
    }

    public IMarkerDelegate a(MotionEvent motionEvent) {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
            if ((iMarkerDelegate instanceof ai) && a(iMarkerDelegate.getRect(), (int) motionEvent.getX(), (int) motionEvent.getY())) {
                this.g = iMarkerDelegate;
                return this.g;
            }
        }
        return null;
    }

    public synchronized void a(am amVar) {
        if (amVar != null) {
            if (amVar.b() != 0) {
                this.d.add(amVar);
            }
        }
    }

    public synchronized void a(int i) {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            am amVar = (am) it.next();
            if (amVar.b() == i) {
                this.d.remove(amVar);
            }
        }
    }

    public void a(Integer num) {
        if (num.intValue() != 0) {
            this.e.add(num);
        }
    }

    public synchronized int a(BitmapDescriptor bitmapDescriptor) {
        int b;
        if (bitmapDescriptor != null) {
            if (!(bitmapDescriptor.getBitmap() == null || bitmapDescriptor.getBitmap().isRecycled())) {
                for (int i = 0; i < this.d.size(); i++) {
                    am amVar = (am) this.d.get(i);
                    if (amVar.a().equals(bitmapDescriptor)) {
                        b = amVar.b();
                        break;
                    }
                }
                b = 0;
            }
        }
        b = 0;
        return b;
    }

    public synchronized void f() {
        try {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
                if (iMarkerDelegate != null) {
                    iMarkerDelegate.destroy();
                }
            }
            b(null);
            it = this.d.iterator();
            while (it.hasNext()) {
                ((am) it.next()).a().recycle();
            }
            this.d.clear();
        } catch (Throwable th) {
            ee.a(th, "MapOverlayImageView", "destroy");
            th.printStackTrace();
            Log.d("amapApi", "MapOverlayImageView clear erro" + th.getMessage());
        }
        return;
    }

    public boolean b(MotionEvent motionEvent) throws RemoteException {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
            if ((iMarkerDelegate instanceof ai) && iMarkerDelegate.isVisible()) {
                Rect rect = iMarkerDelegate.getRect();
                boolean a = a(rect, (int) motionEvent.getX(), (int) motionEvent.getY());
                if (a) {
                    this.f = new IPoint(rect.left + (rect.width() / 2), rect.top);
                    this.g = iMarkerDelegate;
                    return a;
                }
            }
        }
        return false;
    }

    public boolean a(Rect rect, int i, int i2) {
        return rect.contains(i, i2);
    }

    public synchronized List<Marker> g() {
        List<Marker> arrayList;
        arrayList = new ArrayList();
        try {
            Rect rect = new Rect(0, 0, this.a.getMapWidth(), this.a.getMapHeight());
            IPoint iPoint = new IPoint();
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
                if (!(iMarkerDelegate instanceof at)) {
                    FPoint mapPosition = iMarkerDelegate.getMapPosition();
                    if (mapPosition != null) {
                        this.a.getMapProjection().map2Win(mapPosition.x, mapPosition.y, iPoint);
                        if (a(rect, iPoint.x, iPoint.y)) {
                            arrayList.add(new Marker(iMarkerDelegate));
                        }
                    }
                }
            }
        } catch (Throwable th) {
            ee.a(th, "MapOverlayImageView", "getMapScreenMarkers");
            th.printStackTrace();
        }
        return arrayList;
    }

    public synchronized void h() {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
            if (iMarkerDelegate.isDestory()) {
                iMarkerDelegate.realDestroy();
            }
        }
    }

    protected synchronized void i() {
        this.h.removeCallbacks(this.i);
        this.h.postDelayed(this.i, 10);
    }

    public void j() {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            IMarkerDelegate iMarkerDelegate = (IMarkerDelegate) it.next();
            if (iMarkerDelegate != null) {
                iMarkerDelegate.reLoadTexture();
            }
        }
        if (this.d != null) {
            this.d.clear();
        }
    }

    public void k() {
        this.j.post(this.k);
    }
}
