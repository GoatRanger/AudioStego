package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IGroundOverlayDelegate;
import com.autonavi.amap.mapcore.interfaces.IOverlayDelegate;
import com.autonavi.amap.mapcore.interfaces.IPolylineDelegate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

class v {
    private static int c = 0;
    IAMapDelegate a;
    a b = new a();
    private CopyOnWriteArrayList<IOverlayDelegate> d = new CopyOnWriteArrayList(new ArrayList(500));
    private CopyOnWriteArrayList<Integer> e = new CopyOnWriteArrayList();
    private Handler f = new Handler();
    private Runnable g = new w(this);

    static class a implements Serializable, Comparator<Object> {
        a() {
        }

        public int compare(Object obj, Object obj2) {
            IOverlayDelegate iOverlayDelegate = (IOverlayDelegate) obj;
            IOverlayDelegate iOverlayDelegate2 = (IOverlayDelegate) obj2;
            if (!(iOverlayDelegate == null || iOverlayDelegate2 == null)) {
                try {
                    if (iOverlayDelegate.getZIndex() > iOverlayDelegate2.getZIndex()) {
                        return 1;
                    }
                    if (iOverlayDelegate.getZIndex() < iOverlayDelegate2.getZIndex()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    ee.a(th, "GLOverlayLayer", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    static String a(String str) {
        c++;
        return str + c;
    }

    public v(IAMapDelegate iAMapDelegate) {
        this.a = iAMapDelegate;
    }

    public synchronized void b(String str) {
        if (str != null) {
            try {
                if (str.trim().length() != 0) {
                    Iterator it = this.d.iterator();
                    while (it.hasNext()) {
                        IOverlayDelegate iOverlayDelegate = (IOverlayDelegate) it.next();
                        if (!str.equals(iOverlayDelegate.getId())) {
                            this.d.remove(iOverlayDelegate);
                        }
                    }
                }
            } catch (Throwable th) {
                ee.a(th, "GLOverlayLayer", "clear");
                th.printStackTrace();
                Log.d("amapApi", "GLOverlayLayer clear erro" + th.getMessage());
            }
        }
        this.d.clear();
        c = 0;
    }

    public synchronized void a() {
        try {
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                ((IOverlayDelegate) it.next()).destroy();
            }
            b(null);
        } catch (Throwable th) {
            ee.a(th, "GLOverlayLayer", "destory");
            th.printStackTrace();
            Log.d("amapApi", "GLOverlayLayer destory erro" + th.getMessage());
        }
        return;
    }

    private synchronized IOverlayDelegate d(String str) throws RemoteException {
        IOverlayDelegate iOverlayDelegate;
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            iOverlayDelegate = (IOverlayDelegate) it.next();
            if (iOverlayDelegate != null && iOverlayDelegate.getId().equals(str)) {
                break;
            }
        }
        iOverlayDelegate = null;
        return iOverlayDelegate;
    }

    public synchronized void a(IOverlayDelegate iOverlayDelegate) throws RemoteException {
        this.d.add(iOverlayDelegate);
        b();
    }

    public synchronized boolean c(String str) throws RemoteException {
        boolean remove;
        IOverlayDelegate d = d(str);
        if (d != null) {
            remove = this.d.remove(d);
        } else {
            remove = false;
        }
        return remove;
    }

    protected synchronized void b() {
        this.f.removeCallbacks(this.g);
        this.f.postDelayed(this.g, 10);
    }

    public void a(GL10 gl10, boolean z, int i) {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            gl10.glDeleteTextures(1, new int[]{((Integer) it.next()).intValue()}, 0);
            this.a.deleteTexsureId(r0.intValue());
        }
        this.e.clear();
        int size = this.d.size();
        Iterator it2 = this.d.iterator();
        while (it2.hasNext()) {
            IOverlayDelegate iOverlayDelegate = (IOverlayDelegate) it2.next();
            try {
                if (iOverlayDelegate.isVisible()) {
                    if (size > 20) {
                        if (iOverlayDelegate.checkInBounds()) {
                            if (z) {
                                if (iOverlayDelegate.getZIndex() <= ((float) i)) {
                                    iOverlayDelegate.draw(gl10);
                                }
                            } else if (iOverlayDelegate.getZIndex() > ((float) i)) {
                                iOverlayDelegate.draw(gl10);
                            }
                        }
                    } else if (z) {
                        if (iOverlayDelegate.getZIndex() <= ((float) i)) {
                            iOverlayDelegate.draw(gl10);
                        }
                    } else if (iOverlayDelegate.getZIndex() > ((float) i)) {
                        iOverlayDelegate.draw(gl10);
                    }
                }
            } catch (Throwable e) {
                ee.a(e, "GLOverlayLayer", "draw");
                e.printStackTrace();
            }
        }
    }

    public void a(Integer num) {
        if (num.intValue() != 0) {
            this.e.add(num);
        }
    }

    public synchronized void c() {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            IOverlayDelegate iOverlayDelegate = (IOverlayDelegate) it.next();
            if (iOverlayDelegate != null) {
                try {
                    iOverlayDelegate.calMapFPoint();
                } catch (Throwable e) {
                    ee.a(e, "GLOverlayLayer", "calMapFPoint");
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean d() {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            IOverlayDelegate iOverlayDelegate = (IOverlayDelegate) it.next();
            if (iOverlayDelegate != null && !iOverlayDelegate.isDrawFinish()) {
                return false;
            }
        }
        return true;
    }

    public synchronized IOverlayDelegate a(LatLng latLng) {
        IOverlayDelegate iOverlayDelegate;
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            iOverlayDelegate = (IOverlayDelegate) it.next();
            if (iOverlayDelegate != null && iOverlayDelegate.isDrawFinish() && (iOverlayDelegate instanceof IPolylineDelegate) && ((IPolylineDelegate) iOverlayDelegate).contains(latLng)) {
                break;
            }
        }
        iOverlayDelegate = null;
        return iOverlayDelegate;
    }

    public void e() {
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            IOverlayDelegate iOverlayDelegate = (IOverlayDelegate) it.next();
            if (iOverlayDelegate != null) {
                if (iOverlayDelegate instanceof IPolylineDelegate) {
                    ((IPolylineDelegate) iOverlayDelegate).reLoadTexture();
                } else if (iOverlayDelegate instanceof IGroundOverlayDelegate) {
                    ((IGroundOverlayDelegate) iOverlayDelegate).reLoadTexture();
                }
            }
        }
    }
}
