package com.nokia.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.common.ViewRect;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapGesture.OnGestureListener.OnGestureListenerAdapter;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.OnMapRenderListener;
import com.nokia.maps.MapGestureHandler.Priority;
import com.nokia.maps.MapsEngine.b;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

class cb implements bw {
    private static final String a = cb.class.getName();
    private MapGestureHandler b;
    private cf c;
    private Map d;
    private MapImpl e;
    private Object f = new Object();
    private bx g;
    private fa h;
    private boolean i = false;
    private Bundle j = null;
    private boolean k = false;
    private MapImpl$e l = new MapImpl$e(this) {
        final /* synthetic */ cb a;

        {
            this.a = r1;
        }

        public void a() {
            if (!this.a.i) {
                this.a.h.requestRender();
            }
        }

        public void b() {
            if (!this.a.i) {
                this.a.g.h();
            }
        }
    };
    private MapImpl$a m = new MapImpl$a(this) {
        final /* synthetic */ cb a;

        {
            this.a = r1;
        }

        public void a() {
            if (this.a.b != null) {
                this.a.b.cancelKineticPanning();
            }
        }
    };
    private com.nokia.maps.cc.a n = new com.nokia.maps.cc.a(this) {
        final /* synthetic */ cb a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.h.requestRender();
        }

        public void b() {
            if (this.a.e != null && !this.a.o.get()) {
                this.a.e.c(new a(this.a));
            }
        }
    };
    private AtomicBoolean o = new AtomicBoolean(false);
    private OnMapRenderListener p = new OnMapRenderListener(this) {
        final /* synthetic */ cb a;

        {
            this.a = r1;
        }

        public void onPreDraw() {
        }

        public void onPostDraw(boolean z, final long j) {
            if (z) {
                this.a.h.requestRender();
            } else if (!(this.a.e == null || this.a.o.get())) {
                this.a.e.c(new a(this.a));
            }
            ez.a(new Runnable(this) {
                final /* synthetic */ AnonymousClass5 b;

                public void run() {
                    if (this.b.a.q != null) {
                        for (bv a : this.b.a.q) {
                            a.a(j);
                        }
                    }
                }
            });
        }

        public void onSizeChanged(int i, int i2) {
        }

        public void onGraphicsDetached() {
        }

        public void onRenderBufferCreated() {
        }
    };
    private List<bv> q = new CopyOnWriteArrayList();
    private a r = new a(this) {
        final /* synthetic */ cb a;

        {
            this.a = r1;
        }

        public void a() {
            if (this.a.b != null) {
                this.a.b.m();
                this.a.b.a();
            }
        }
    };
    private OnGestureListenerAdapter s = new OnGestureListenerAdapter(this) {
        final /* synthetic */ cb a;

        {
            this.a = r1;
        }

        public boolean onLongPressEvent(PointF pointF) {
            this.a.k = this.a.c.a(pointF);
            return this.a.k;
        }
    };
    private cg t = new cg(this) {
        final /* synthetic */ cb a;

        {
            this.a = r1;
        }

        public void b(MapMarker mapMarker, PointF pointF) {
        }

        public void a(MapMarker mapMarker, PointF pointF) {
            this.a.k = false;
        }

        public void a(MapMarker mapMarker) {
        }
    };
    private b u = new b(this) {
        final /* synthetic */ cb a;

        {
            this.a = r1;
        }

        public void a(boolean z) {
            if (z && this.a.h != null) {
                this.a.h.requestRender();
            }
        }
    };

    private class a implements Runnable {
        final /* synthetic */ cb a;

        public a(cb cbVar) {
            this.a = cbVar;
            cbVar.o.set(true);
        }

        public void run() {
            this.a.o.set(false);
            synchronized (this.a.f) {
                if (this.a.d != null && this.a.e.need_redraw()) {
                    this.a.h.requestRender();
                }
            }
        }
    }

    public cb(fa faVar) {
        this.h = faVar;
    }

    private void k() {
        if (this.b != null) {
            if (this.j == null) {
                this.j = new Bundle();
            }
            this.j.putBoolean("MapViewPanningEnabled", this.b.isPanningEnabled());
            this.j.putBoolean("MapViewKineticFlickEnabled", this.b.isKineticFlickEnabled());
            this.j.putBoolean("MapViewPinchEnabled", this.b.isPinchEnabled());
            this.j.putBoolean("MapViewRotateEnabled", this.b.isRotateEnabled());
            this.j.putBoolean("MapViewTiltEnabled", this.b.isTiltEnabled());
            this.j.putBoolean("MapViewSingleTapEnabled", this.b.isSingleTapEnabled());
            this.j.putBoolean("MapViewDoubleTapEnabled", this.b.isDoubleTapEnabled());
            this.j.putBoolean("MapViewLongPressEnabled", this.b.isLongPressEnabled());
            this.j.putBoolean("MapViewTwoFingerTapEnabled", this.b.isTwoFingerTapEnabled());
        }
    }

    public Bundle f() {
        if (this.b != null) {
            k();
        }
        Bundle k = this.h.k();
        if (this.j != null) {
            k.putAll(this.j);
        }
        return k;
    }

    public void a(Parcelable parcelable) {
        this.h.a(parcelable);
        if (this.b != null && (parcelable instanceof Bundle)) {
            Bundle bundle = (Bundle) parcelable;
            this.b.setPanningEnabled(bundle.getBoolean("MapViewPanningEnabled", false));
            this.b.setKineticFlickEnabled(bundle.getBoolean("MapViewKineticFlickEnabled", false));
            this.b.setPinchEnabled(bundle.getBoolean("MapViewPinchEnabled", false));
            this.b.setRotateEnabled(bundle.getBoolean("MapViewRotateEnabled", false));
            this.b.setTiltEnabled(bundle.getBoolean("MapViewTiltEnabled", false));
            this.b.setSingleTapEnabled(bundle.getBoolean("MapViewSingleTapEnabled", false));
            this.b.setDoubleTapEnabled(bundle.getBoolean("MapViewDoubleTapEnabled", false));
            this.b.setLongPressEnabled(bundle.getBoolean("MapViewLongPressEnabled", false));
            this.b.setTwoFingerTapEnabled(bundle.getBoolean("MapViewTwoFingerTapEnabled", false));
        }
    }

    public bx i() {
        if (this.g == null) {
            this.g = new au();
        }
        return this.g;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(boolean r4) {
        /*
        r3 = this;
        r1 = r3.f;
        monitor-enter(r1);
        r0 = r3.e;	 Catch:{ all -> 0x0019 }
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r1);	 Catch:{ all -> 0x0019 }
    L_0x0008:
        return;
    L_0x0009:
        if (r4 == 0) goto L_0x001c;
    L_0x000b:
        r0 = r3.e;	 Catch:{ all -> 0x0019 }
        r2 = r3.l;	 Catch:{ all -> 0x0019 }
        r0.a(r2);	 Catch:{ all -> 0x0019 }
        r0 = r3.l;	 Catch:{ all -> 0x0019 }
        r0.a();	 Catch:{ all -> 0x0019 }
    L_0x0017:
        monitor-exit(r1);	 Catch:{ all -> 0x0019 }
        goto L_0x0008;
    L_0x0019:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0019 }
        throw r0;
    L_0x001c:
        r0 = r3.e;	 Catch:{ all -> 0x0019 }
        r2 = r3.l;	 Catch:{ all -> 0x0019 }
        r0.b(r2);	 Catch:{ all -> 0x0019 }
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nokia.maps.cb.a(boolean):void");
    }

    public void a(Map map) throws Exception {
        synchronized (this.f) {
            if (map == null) {
                j();
                this.h.j();
                return;
            }
            this.d = map;
            this.e = MapImpl.get(this.d);
            i();
            this.g.a(this.e);
            this.g.a(this.p);
            this.g.a(dw.a(this.d.getPositionIndicator()));
            this.g.a(this.r);
            a(true);
            this.e.a(this.m);
            a(this.e, MapsEngine.e());
            this.e.b(new Runnable(this) {
                final /* synthetic */ cb a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.e != null && !this.a.e.a().a()) {
                        l.a().a(true, this.a.d.getMapScheme());
                    }
                }
            });
            try {
                MapsEngine.c().A().a(this.n);
                this.h.i();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    public Map b() {
        return this.d;
    }

    private void a(MapImpl mapImpl, Context context) {
        if (this.b != null) {
            l();
        }
        this.b = bo.a(mapImpl, context);
        this.b.a(MapImpl.get(this.d).a);
        this.b.a(this.s, Priority.LOW);
        this.c = new cf(mapImpl, context);
        this.c.a(this.t);
    }

    private void l() {
        if (this.b != null) {
            if (this.d != null) {
                this.b.b(MapImpl.get(this.d).a);
            }
            k();
            this.b.removeOnGestureListener(this.s);
            this.b.b();
            this.b = null;
            this.k = false;
            this.c.b(this.t);
            this.c = null;
        }
    }

    public boolean a(MotionEvent motionEvent) {
        if (this.k) {
            if (this.c != null) {
                return this.c.a(motionEvent);
            }
            return false;
        } else if (this.b != null) {
            return this.b.a(motionEvent);
        } else {
            return false;
        }
    }

    public void d() {
        this.i = true;
        this.h.onPause();
        if (this.e != null) {
            this.e.a(true);
        }
        try {
            if (MapsEngine.c() != null) {
                if (this.d != null) {
                    PositioningManagerImpl.a().a(dw.a(this.d.getPositionIndicator()));
                }
                MapsEngine.c().a(this.u);
            }
        } catch (Exception e) {
            bj.b(a, "Exception: %s", new Object[]{e.getLocalizedMessage()});
        }
        if (this.b != null) {
            this.b.c();
        }
    }

    public void e() {
        if (this.g != null) {
            this.g.f();
        }
        this.i = false;
        if (this.e != null) {
            this.e.a(false);
        }
        try {
            if (MapsEngine.c() != null) {
                if (this.e != null) {
                    PositioningManagerImpl.a().a(new WeakReference(dw.a(this.d.getPositionIndicator())));
                }
                MapsEngine.c().a(this.u);
            }
        } catch (Exception e) {
            bj.b(a, "Exception: %s", new Object[]{e.getLocalizedMessage()});
        }
        if (this.h != null) {
            this.h.requestRender();
            this.h.onResume();
        }
        if (this.b != null) {
            this.b.d();
        }
    }

    public void a(ViewRect viewRect, PointF pointF) {
        if (this.d != null) {
            this.e.a(viewRect, pointF);
        }
    }

    public ViewRect a() {
        if (this.d != null) {
            return this.e.getClipRect();
        }
        return null;
    }

    public MapGesture c() {
        return this.b;
    }

    public void a(OnMapRenderListener onMapRenderListener) {
        i().a(onMapRenderListener);
    }

    public void b(OnMapRenderListener onMapRenderListener) {
        if (this.g != null) {
            this.g.b(onMapRenderListener);
        }
    }

    public void j() {
        if (this.g != null) {
            this.g.g();
        }
        l();
        synchronized (this.f) {
            if (this.d != null) {
                a(false);
                this.e.b(this.m);
                this.e.s();
                this.e = null;
                this.d = null;
            }
        }
        if (this.q != null) {
            this.q.clear();
        }
        try {
            MapsEngine.c().A().b(this.n);
        } catch (Exception e) {
            bj.b(a, "Map Engine problem in view cleanup", new Object[0]);
            e.printStackTrace();
        }
    }

    public void a(cg cgVar) {
        if (this.c != null && cgVar != null) {
            this.c.a(cgVar);
        }
    }

    public void b(cg cgVar) {
        if (this.c != null && cgVar != null) {
            this.c.b(cgVar);
        }
    }

    public void g() {
        if (this.c != null) {
            this.c.a();
        }
    }

    public Bitmap a(MapMarker mapMarker) {
        if (mapMarker != null) {
            Image icon = mapMarker.getIcon();
            if (icon != null) {
                int[] imageRawData = ImageImpl.a(icon).getImageRawData();
                Bitmap createBitmap = Bitmap.createBitmap((int) mapMarker.getIcon().getWidth(), (int) mapMarker.getIcon().getHeight(), Config.ARGB_8888);
                try {
                    createBitmap.setPixels(imageRawData, 0, (int) mapMarker.getIcon().getWidth(), 0, 0, (int) mapMarker.getIcon().getWidth(), (int) mapMarker.getIcon().getHeight());
                    return createBitmap;
                } catch (Exception e) {
                    return null;
                }
            }
        }
        return null;
    }

    public Bitmap h() {
        MapMarker b = bd.b();
        if (b != null) {
            return a(b);
        }
        return null;
    }

    public void a(OnScreenCaptureListener onScreenCaptureListener) {
        if (this.e != null) {
            this.e.a(onScreenCaptureListener);
            return;
        }
        throw new RuntimeException("MapView is not initialized");
    }
}
