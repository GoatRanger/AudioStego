package com.nokia.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.PointF;
import android.view.MotionEvent;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.streetlevel.StreetLevel;
import com.here.android.mpa.streetlevel.StreetLevelBuilding;
import com.here.android.mpa.streetlevel.StreetLevelGesture;
import com.here.android.mpa.streetlevel.StreetLevelGesture.OnGestureListener;
import com.here.android.mpa.streetlevel.StreetLevelModel;
import com.here.android.mpa.streetlevel.StreetLevelModel.OnEventListener;
import com.here.android.mpa.streetlevel.StreetLevelSelectedObject;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

class da {
    private cq a = new cq(da.class.getName());
    private PanoramaModelImpl b;
    private cy c;
    private cu d;
    private CopyOnWriteArrayList<OnGestureListener> e;
    private db f;
    private a g = new a();
    private AtomicBoolean h = new AtomicBoolean(false);
    private boolean i = false;
    private Context j;
    private com.nokia.maps.cc.a k = new com.nokia.maps.cc.a(this) {
        final /* synthetic */ da a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.f();
        }

        public void b() {
            this.a.f();
        }
    };
    private CopyOnWriteArrayList<ek> l = new CopyOnWriteArrayList();
    private c m = new c(this) {
        final /* synthetic */ da a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.f();
            Iterator it = this.a.l.iterator();
            while (it.hasNext()) {
                ((ek) it.next()).a();
            }
        }
    };
    private OnEventListener n = new OnEventListener(this) {
        final /* synthetic */ da a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.f();
        }

        public void onZoomStart(float f) {
        }

        public void onZoomEnd(float f) {
        }

        public void onPositionChanged(GeoCoordinate geoCoordinate) {
            a();
        }

        public void onMoveStart() {
            if (!this.a.h.getAndSet(true) && this.a.r != null) {
                this.a.r.a(this.a.g);
            }
        }

        public void onMoveWait() {
        }

        public void onMoveContinue() {
        }

        public void onMoveEnd(boolean z) {
            a();
        }

        public void onMoveEnd(GeoCoordinate geoCoordinate) {
        }

        public void onBuildingShow(StreetLevelBuilding streetLevelBuilding) {
        }

        public void onBuildingHide(StreetLevelBuilding streetLevelBuilding) {
        }

        public void onIconPlaced(StreetLevelSelectedObject streetLevelSelectedObject) {
        }

        public void onStreetLevelPreviewAvailable() {
        }

        public void onStreetLevelFullyLoaded() {
            a();
            this.a.h.compareAndSet(true, false);
        }

        public void onStreetLevelInvalidated() {
            a();
        }

        public void onStreetLevelChanged() {
            a();
        }

        public void onOrientationStart(float f, float f2) {
        }

        public void onOrientationEnd(float f, float f2) {
        }
    };
    private com.nokia.maps.cy.a o = new com.nokia.maps.cy.a(this) {
        final /* synthetic */ da a;
        private boolean b = false;

        {
            this.a = r2;
        }

        public void a() {
            if (this.a.d != null) {
                this.a.d.l();
                this.b = this.a.d.c(System.currentTimeMillis());
            }
            this.a.b.m();
        }

        public void a(boolean z) {
            if (z) {
                this.a.f();
            }
            synchronized (this.a.p) {
                for (Runnable run : this.a.p) {
                    run.run();
                }
                this.a.p.clear();
            }
            this.a.b.n();
            if (this.b) {
                this.a.f.requestRender();
            }
        }
    };
    private List<Runnable> p = new CopyOnWriteArrayList();
    private com.nokia.maps.cu.a q = new com.nokia.maps.cu.a(this) {
        final /* synthetic */ da a;

        {
            this.a = r1;
        }

        public void a(PointF pointF) {
            Iterator it = this.a.e.iterator();
            boolean z = false;
            while (it.hasNext()) {
                boolean onTap;
                try {
                    onTap = ((OnGestureListener) it.next()).onTap(pointF);
                } catch (Throwable e) {
                    bj.c("PANORAMA_CONTROLLER", "Exception \n%s", new Object[]{bj.a(e)});
                    onTap = z;
                }
                if (!onTap) {
                    z = onTap;
                } else {
                    return;
                }
            }
            List<StreetLevelSelectedObject> a = this.a.b.a(pointF);
            for (StreetLevelSelectedObject streetLevelSelectedObject : a) {
                if (streetLevelSelectedObject != null && (streetLevelSelectedObject.getObject() instanceof StreetLevelBuilding)) {
                    l.a().j();
                    break;
                }
            }
            PanoramaMapCompass i = this.a.b.i();
            if (i != null) {
                PanoramaIconBase a2 = i.a();
                if (a2 != null) {
                    for (StreetLevelSelectedObject streetLevelSelectedObject2 : a) {
                        if (ViewObjectImpl.a(streetLevelSelectedObject2.getObject()).equals(a2)) {
                            Iterator it2 = this.a.e.iterator();
                            while (it2.hasNext()) {
                                try {
                                    z = ((OnGestureListener) it2.next()).onCompassSelected();
                                    continue;
                                } catch (Throwable e2) {
                                    bj.c("PANORAMA_CONTROLLER", "Exception \n%s", new Object[]{bj.a(e2)});
                                    continue;
                                }
                                if (z) {
                                    return;
                                }
                            }
                            continue;
                        }
                    }
                }
            }
            List arrayList = new ArrayList();
            for (StreetLevelSelectedObject streetLevelSelectedObject22 : a) {
                arrayList.add(streetLevelSelectedObject22);
            }
            Iterator it3 = this.a.e.iterator();
            while (it3.hasNext()) {
                try {
                    z = ((OnGestureListener) it3.next()).onObjectsSelected(arrayList);
                    continue;
                } catch (Throwable e22) {
                    bj.c("PANORAMA_CONTROLLER", "Exception \n%s", new Object[]{bj.a(e22)});
                    continue;
                }
                if (z) {
                    return;
                }
            }
            if (!z) {
                for (StreetLevelSelectedObject streetLevelSelectedObject222 : a) {
                    if (streetLevelSelectedObject222 != null) {
                        ViewObjectImpl a3 = ViewObjectImpl.a(streetLevelSelectedObject222.getObject());
                        if (a3 instanceof PanoramaLink) {
                            this.a.b.a(((PanoramaLink) a3).b(), true, -1.0f, -1.0f, -1.0f);
                        }
                    }
                }
            }
        }

        public void a(PointF pointF, PointF pointF2) {
            Iterator it = this.a.e.iterator();
            boolean z = false;
            while (it.hasNext()) {
                boolean onRotate;
                try {
                    onRotate = ((OnGestureListener) it.next()).onRotate(pointF, pointF2);
                } catch (Throwable e) {
                    bj.c("PANORAMA_CONTROLLER", "Exception \n%s", new Object[]{bj.a(e)});
                    onRotate = z;
                }
                if (!onRotate) {
                    z = onRotate;
                } else {
                    return;
                }
            }
            if (!z) {
                this.a.b.b(pointF, pointF2);
            }
        }

        public void a(float f, float f2) {
            PointF pointF = new PointF(((float) this.a.b.c()) / 2.0f, ((float) this.a.b.d()) / 2.0f);
            a(pointF, new PointF(pointF.x + f, pointF.y + f2));
        }

        public void a(float f) {
            Iterator it = this.a.e.iterator();
            boolean z = false;
            while (it.hasNext()) {
                boolean onPinchZoom;
                try {
                    onPinchZoom = ((OnGestureListener) it.next()).onPinchZoom(f);
                } catch (Throwable e) {
                    bj.c("PANORAMA_CONTROLLER", "Exception \n%s", new Object[]{bj.a(e)});
                    onPinchZoom = z;
                }
                if (!onPinchZoom) {
                    z = onPinchZoom;
                } else {
                    return;
                }
            }
            if (!z) {
                this.a.b.a(f);
            }
        }

        public void b(PointF pointF) {
            Iterator it = this.a.e.iterator();
            boolean z = false;
            while (it.hasNext()) {
                boolean onDoubleTap;
                try {
                    onDoubleTap = ((OnGestureListener) it.next()).onDoubleTap(pointF);
                } catch (Throwable e) {
                    bj.c("PANORAMA_CONTROLLER", "Exception \n%s", new Object[]{bj.a(e)});
                    onDoubleTap = z;
                }
                if (!onDoubleTap) {
                    z = onDoubleTap;
                } else {
                    return;
                }
            }
            if (!z) {
                StreetLevel b = this.a.b.b(pointF);
                if (b != null) {
                    this.a.b.a(b, true, -1.0f, -1.0f, -1.0f);
                }
            }
        }

        public void a() {
            if (this.a.b != null) {
                PanoramaMapCompass i = this.a.b.i();
                if (i != null) {
                    i.a(true);
                }
            }
        }

        public void b() {
            if (this.a.b != null) {
                PanoramaMapCompass i = this.a.b.i();
                if (i != null) {
                    i.a(false);
                }
            }
        }

        public void c(PointF pointF) {
        }
    };
    private fd r = null;

    private class a implements Runnable {
        final /* synthetic */ da a;

        private a(da daVar) {
            this.a = daVar;
        }

        public void run() {
            this.a.f();
            if (this.a.h.get()) {
                this.a.r.a(this.a.g, 16);
            }
        }
    }

    private final class b implements Runnable {
        OnScreenCaptureListener a = null;
        final /* synthetic */ da b;

        public b(da daVar, OnScreenCaptureListener onScreenCaptureListener) {
            this.b = daVar;
            this.a = onScreenCaptureListener;
        }

        public void run() {
            if (this.b.b != null) {
                final int c = this.b.b.c();
                final int d = this.b.b.d();
                byte[] bArr = new byte[((c * d) * 4)];
                Bitmap bitmap = null;
                if (this.b.b.captureScreen(bArr)) {
                    try {
                        bitmap = Bitmap.createBitmap(c, d, Config.ARGB_8888);
                        bitmap.copyPixelsFromBuffer(ByteBuffer.wrap(bArr));
                    } catch (Exception e) {
                        bj.c("PANORAMA_CONTROLLER", e.getLocalizedMessage(), new Object[0]);
                    }
                }
                ez.a(new Runnable(this) {
                    final /* synthetic */ b d;

                    public void run() {
                        bl.b(bitmap, "satellite", c, d);
                        this.d.a.onScreenCaptured(bitmap);
                    }
                });
            }
        }
    }

    public da(Context context, db dbVar) {
        this.j = context;
        this.f = dbVar;
        this.r = new fd();
    }

    public cy a() {
        if (this.c == null) {
            this.c = new cy();
        }
        return this.c;
    }

    public StreetLevelGesture b() {
        return cu.a(this.d);
    }

    public void a(StreetLevelModel streetLevelModel) {
        if (streetLevelModel == null) {
            this.c.a(null);
            this.c.a(null);
            if (this.b != null) {
                this.b.b(this.n);
                this.b.a(null);
                this.b.a().b();
            }
            this.b = null;
            if (this.d != null) {
                this.d.a(null);
            }
            this.d = null;
            this.j = null;
            if (this.l != null) {
                this.l.clear();
                return;
            }
            return;
        }
        this.b = PanoramaModelImpl.a(streetLevelModel);
        this.b.a(this.n);
        this.b.a(this.m);
        this.c.a(this.b);
        this.c.a(this.o);
        if (this.d == null) {
            this.d = new cu(this.j);
            this.d.a(this.q);
        }
        this.d.a(this.b);
        this.e = this.d.a();
        this.b.b(new Runnable(this) {
            final /* synthetic */ da a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.b != null && !this.a.b.a().a()) {
                    l.a().e(true);
                }
            }
        });
    }

    public StreetLevelModel c() {
        return PanoramaModelImpl.a(this.b);
    }

    public boolean a(MotionEvent motionEvent) {
        boolean z = false;
        if (this.d != null) {
            z = this.d.a(motionEvent);
            if (z) {
                f();
            }
        }
        return z;
    }

    public void d() {
        this.i = true;
        try {
            MapsEngine.c().A().b(this.k);
        } catch (Exception e) {
            bj.c("PANORAMA_CONTROLLER", "MapEngine error!", new Object[]{e});
        }
        if (this.r != null) {
            this.r.a();
            this.r = null;
        }
        if (this.b != null) {
            this.b.h();
        }
        this.h.compareAndSet(true, false);
    }

    public void e() {
        this.i = false;
        try {
            MapsEngine.c().A().a(this.k);
        } catch (Exception e) {
            bj.c("PANORAMA_CONTROLLER", "MapEngine error!", new Object[]{e});
        }
        if (this.c != null) {
            this.c.f();
            this.f.requestRender();
        }
        if (this.b != null) {
            this.b.g();
        }
        if (this.r == null) {
            this.r = new fd();
            if (this.h.get()) {
                this.r.a(this.g);
            }
        }
    }

    public void a(boolean z) {
        if (this.c != null) {
            this.c.b(z);
            this.f.requestRender();
        }
    }

    public void a(OnScreenCaptureListener onScreenCaptureListener) {
        if (this.b.c() == 0 || this.b.d() == 0) {
            throw new IllegalArgumentException("Width and height must be >= 0.");
        } else if (onScreenCaptureListener == null) {
            throw new IllegalArgumentException("MapBitmapEventListener is null");
        } else {
            synchronized (this.p) {
                this.p.add(new b(this, onScreenCaptureListener));
            }
            this.f.requestRender();
        }
    }

    private void f() {
        if (this.b != null && !this.i) {
            this.f.requestRender();
        }
    }
}
