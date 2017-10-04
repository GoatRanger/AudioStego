package com.nokia.maps;

import android.content.Context;
import android.graphics.PointF;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.streetlevel.StreetLevel;
import com.here.android.mpa.streetlevel.StreetLevelBuilding;
import com.here.android.mpa.streetlevel.StreetLevelCoverage.Listener;
import com.here.android.mpa.streetlevel.StreetLevelCoverage.ResultCode;
import com.here.android.mpa.streetlevel.StreetLevelModel;
import com.here.android.mpa.streetlevel.StreetLevelModel.OnEventListener;
import com.here.android.mpa.streetlevel.StreetLevelModel.OnRetrievalListener;
import com.here.android.mpa.streetlevel.StreetLevelModelState;
import com.here.android.mpa.streetlevel.StreetLevelObject;
import com.here.android.mpa.streetlevel.StreetLevelSelectedObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.forbid.FlyForbidProtocol;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@HybridPlus
public class PanoramaModelImpl extends BaseNativeObject {
    private static k<StreetLevelModel, PanoramaModelImpl> f = null;
    private static am<StreetLevelModel, PanoramaModelImpl> g = null;
    public boolean a = false;
    PanoramaEventThread b = null;
    private int c;
    private int d;
    private cq e = new cq(PanoramaModelImpl.class.getSimpleName());
    private o h = new o();
    private p i = l.a();
    private boolean j = false;
    private AtomicBoolean k = new AtomicBoolean(false);
    private AtomicBoolean l = new AtomicBoolean(false);
    private AtomicBoolean m = new AtomicBoolean(false);
    private AtomicBoolean n = new AtomicBoolean(false);
    private PanoramaMapCompass o;
    private CopyOnWriteArrayList<OnEventListener> p = new CopyOnWriteArrayList();
    private c q;
    private Timer r;
    private Timer s = null;
    private CopyOnWriteArrayList<Runnable> t = new CopyOnWriteArrayList();
    private CopyOnWriteArrayList<Runnable> u = new CopyOnWriteArrayList();

    @HybridPlus
    private static class PanoramaEventThread extends Thread {
        private WeakReference<PanoramaModelImpl> a = null;
        private boolean b = false;
        @HybridPlusNative
        private int nativeptr;

        private native void doEventNative(PanoramaModelImpl panoramaModelImpl);

        private native void killEventThreadNative();

        private native void runEventNative();

        public PanoramaEventThread(PanoramaModelImpl panoramaModelImpl) {
            this.a = new WeakReference(panoramaModelImpl);
            this.nativeptr = panoramaModelImpl.nativeptr;
            setName("PanoramaEventDispatcher");
            runEventNative();
            start();
        }

        public void a() {
            this.b = true;
            killEventThreadNative();
            try {
                join(1000);
            } catch (Throwable e) {
                bj.c("PANORAMA_MODEL", "InterruptedException \n%s", new Object[]{bj.a(e)});
            }
        }

        private boolean b() {
            return this.a.get() != null;
        }

        public void run() {
            while (!this.b && b()) {
                runEventNative();
                if (b()) {
                    doEventNative((PanoramaModelImpl) this.a.get());
                }
            }
            bj.e("PanoramaEventDispatcher", "End loop.", new Object[0]);
        }
    }

    private class a extends TimerTask {
        final /* synthetic */ PanoramaModelImpl a;
        private float b = 0.0f;

        public a(PanoramaModelImpl panoramaModelImpl) {
            this.a = panoramaModelImpl;
            if (panoramaModelImpl.o != null) {
                panoramaModelImpl.o.setAlpha(this.b);
                panoramaModelImpl.onRedraw();
            }
            if (panoramaModelImpl.j) {
                panoramaModelImpl.setNavigationArrowVisibleNative(panoramaModelImpl.j);
            }
        }

        public void run() {
            this.b = (float) (((double) this.b) + 0.05d);
            if (this.b > 1.0f) {
                this.a.r.cancel();
                this.a.r = null;
            }
            if (this.a.o != null) {
                this.a.o.setAlpha(this.b);
            }
            this.a.onRedraw();
        }
    }

    private class b extends TimerTask {
        final /* synthetic */ PanoramaModelImpl a;
        private float b = 1.0f;

        public b(PanoramaModelImpl panoramaModelImpl) {
            this.a = panoramaModelImpl;
            if (panoramaModelImpl.o != null) {
                panoramaModelImpl.o.setAlpha(this.b);
                panoramaModelImpl.onRedraw();
            }
            if (panoramaModelImpl.j) {
                panoramaModelImpl.setNavigationArrowVisibleNative(false);
            }
        }

        public void run() {
            this.b = (float) (((double) this.b) - 0.05d);
            if (this.b < 0.0f) {
                this.a.r.cancel();
                this.a.r = null;
            }
            if (this.a.o != null) {
                this.a.o.setAlpha(this.b);
            }
            this.a.onRedraw();
        }
    }

    interface c {
        void a();
    }

    private native boolean addPanoramaObjectNative(cx cxVar);

    private native void createPanoramaNative(int i);

    private native void destroyPanoramaNative();

    private native PointF geoToPixelNative(GeoCoordinateImpl geoCoordinateImpl);

    private native boolean getNavigationArrowVisibleNative();

    private native synchronized PanoramaImpl getPanorama();

    private native PanoramaImpl getPanorama(double d, double d2, int i);

    private native PanoramaImpl getPanorama(float f, float f2);

    private native GeoCoordinateImpl getPositionNative();

    private native SelectedObject[] getSelectedObjectsNative(float f, float f2);

    private native boolean moveCameraNative(GeoCoordinateImpl geoCoordinateImpl, float f, float f2, float f3);

    private native synchronized void moveToNative(PanoramaImpl panoramaImpl, boolean z, float f, float f2, float f3);

    private native synchronized void moveToNative(PanoramaImpl panoramaImpl, boolean z, GeoCoordinateImpl geoCoordinateImpl, float f);

    private native void pan(float f, float f2, float f3, float f4);

    private native GeoCoordinateImpl pixelToGeoNative(float f, float f2);

    private native boolean removePanoramaObjectNative(cx cxVar);

    private native synchronized void rotate(float f, float f2, float f3, float f4);

    private native synchronized boolean setNavigationArrowNative(ImageImpl imageImpl);

    private native synchronized void setNavigationArrowVisibleNative(boolean z);

    private native float[] toCameraOrientationNative(int i, int i2);

    private native void viewGeometryChangedNative(int i, int i2);

    public native boolean cancelMoveTo(boolean z);

    native synchronized boolean captureScreen(byte[] bArr);

    public native synchronized boolean draw(boolean z, boolean z2);

    public native synchronized void freeGfxResources();

    public native synchronized float getHeading();

    public native synchronized float getMaxHeading();

    public native synchronized float getMaxPitch();

    public native synchronized float getMaxRoll();

    public native synchronized float getMaxZoom();

    public native synchronized float getMinHeading();

    public native synchronized float getMinPitch();

    public native synchronized float getMinRoll();

    public native synchronized float getMinZoom();

    public native synchronized float getOverlayTransparency();

    public native synchronized float getPitch();

    public native synchronized float getRoll();

    public native StreetLevelModelState getState();

    public native int getTransitionDuration();

    public native float getTransitionPreviewDistance();

    public native synchronized float getZoom();

    native boolean isScreenCoordinateBehindCamera(float f, float f2);

    public native synchronized boolean isStreetGeometryVisible();

    public native boolean isTransitionPreviewEnabled();

    public native synchronized boolean needPanoramaData();

    public native synchronized void setHeading(float f);

    public native synchronized void setOverlayTransparency(float f);

    public native synchronized void setPitch(float f);

    public native synchronized void setRoll(float f);

    public native synchronized void setStreetGeometryVisibleNative(boolean z);

    public native void setTransitionDuration(int i);

    public native void setTransitionPreview(boolean z);

    public native void setTransitionPreviewDistance(float f);

    public native synchronized void setZoom(float f);

    static {
        ce.a(StreetLevelModel.class);
    }

    o a() {
        return this.h;
    }

    public static void a(k<StreetLevelModel, PanoramaModelImpl> kVar, am<StreetLevelModel, PanoramaModelImpl> amVar) {
        f = kVar;
        g = amVar;
    }

    static PanoramaModelImpl a(StreetLevelModel streetLevelModel) {
        return (PanoramaModelImpl) f.a(streetLevelModel);
    }

    static StreetLevelModel a(PanoramaModelImpl panoramaModelImpl) {
        if (panoramaModelImpl != null) {
            return (StreetLevelModel) g.a(panoramaModelImpl);
        }
        return null;
    }

    public PanoramaModelImpl(Context context) {
        super(true);
        MapsEngine.b(context);
        createPanoramaNative(context.getResources().getDisplayMetrics().densityDpi);
        this.b = new PanoramaEventThread(this);
        c(true);
    }

    protected void finalize() {
        if (this.b != null && this.b.a != null) {
            bj.d("PANORAMA_MODEL", "Close", new Object[0]);
            this.b.a();
            destroyPanoramaNative();
        }
    }

    public boolean b() {
        return this.a;
    }

    protected synchronized PanoramaImpl a(GeoCoordinate geoCoordinate, int i) {
        return getPanorama(geoCoordinate.getLatitude(), geoCoordinate.getLongitude(), i);
    }

    private synchronized PanoramaImpl e(PointF pointF) {
        return getPanorama(pointF.x, pointF.y);
    }

    public synchronized void a(int i, int i2) {
        viewGeometryChangedNative(i, i2);
        this.c = i;
        this.d = i2;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public void a(PanoramaImpl panoramaImpl, boolean z, float f, float f2, float f3) {
        if (panoramaImpl != null) {
            final PanoramaImpl panoramaImpl2 = panoramaImpl;
            final boolean z2 = z;
            final float f4 = f;
            final float f5 = f2;
            final float f6 = f3;
            b(new Runnable(this) {
                final /* synthetic */ PanoramaModelImpl f;

                public void run() {
                    this.f.moveToNative(panoramaImpl2, z2, f4, f5, f6);
                }
            });
            onRedraw();
            return;
        }
        throw new NullPointerException();
    }

    public void a(PanoramaImpl panoramaImpl, boolean z, GeoCoordinateImpl geoCoordinateImpl, float f) {
        if (geoCoordinateImpl == null || panoramaImpl == null) {
            throw new NullPointerException();
        }
        final PanoramaImpl panoramaImpl2 = panoramaImpl;
        final boolean z2 = z;
        final GeoCoordinateImpl geoCoordinateImpl2 = geoCoordinateImpl;
        final float f2 = f;
        b(new Runnable(this) {
            final /* synthetic */ PanoramaModelImpl e;

            public void run() {
                this.e.moveToNative(panoramaImpl2, z2, geoCoordinateImpl2, f2);
            }
        });
        onRedraw();
    }

    public synchronized void a(final boolean z) {
        this.u.add(new Runnable(this) {
            final /* synthetic */ PanoramaModelImpl b;

            public void run() {
                this.b.setStreetGeometryVisibleNative(z);
            }
        });
        onRedraw();
    }

    public synchronized boolean e() {
        return this.j;
    }

    public synchronized void b(final boolean z) {
        this.j = z;
        this.u.add(new Runnable(this) {
            final /* synthetic */ PanoramaModelImpl b;

            public void run() {
                this.b.setNavigationArrowVisibleNative(z);
            }
        });
        onRedraw();
    }

    public synchronized void a(Image image) {
        dy.a((Object) image, "icon arguement is not null");
        dy.a(image.isValid(), "icon arguement is invalid");
        final ImageImpl a = ImageImpl.a(image);
        b(new Runnable(this) {
            final /* synthetic */ PanoramaModelImpl b;

            public void run() {
                this.b.setNavigationArrowNative(a);
            }
        });
        onRedraw();
    }

    public synchronized void a(PointF pointF, PointF pointF2) {
        pan(pointF.x, pointF.y, pointF2.x, pointF2.y);
        onRedraw();
    }

    public synchronized void b(PointF pointF, PointF pointF2) {
        rotate(pointF.x, pointF.y, pointF2.x, pointF2.y);
        onRedraw();
    }

    public synchronized void a(float f) {
        setZoom(getZoom() * f);
    }

    public synchronized void b(float f) {
        setHeading(getHeading() + f);
    }

    public synchronized List<StreetLevelSelectedObject> a(PointF pointF) {
        List<StreetLevelSelectedObject> arrayList;
        SelectedObject[] selectedObjectsNative = getSelectedObjectsNative(pointF.x, pointF.y);
        arrayList = new ArrayList(selectedObjectsNative.length);
        for (SelectedObject selectedObject : selectedObjectsNative) {
            if (selectedObject != null) {
                arrayList.add(SelectedObject.a(selectedObject, this));
            }
        }
        return arrayList;
    }

    public synchronized boolean a(cx cxVar) {
        boolean addPanoramaObjectNative;
        if (cxVar != null) {
            cxVar.a(this);
            addPanoramaObjectNative = addPanoramaObjectNative(cxVar);
            onRedraw();
        } else {
            addPanoramaObjectNative = true;
        }
        return addPanoramaObjectNative;
    }

    public synchronized boolean b(final cx cxVar) {
        boolean z;
        if (cxVar != null) {
            cxVar.a(null);
            b(new Runnable(this) {
                final /* synthetic */ PanoramaModelImpl b;

                public void run() {
                    this.b.removePanoramaObjectNative(cxVar);
                    this.b.onRedraw();
                }
            });
            onRedraw();
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public void c(boolean z) {
        this.k.set(z);
        if (this.l.get() && this.m.get() && this.k.get()) {
            if (this.o == null) {
                this.o = new PanoramaMapCompass(this);
            }
            this.n.set(true);
            this.o.c();
        } else {
            this.n.set(false);
            if (this.o != null) {
                this.o.b();
            }
        }
        if (this.o != null) {
            this.u.add(new Runnable(this) {
                final /* synthetic */ PanoramaModelImpl a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.o != null) {
                        this.a.o.setVisible(this.a.k.get());
                    }
                }
            });
            if (this.l.get()) {
                onRedraw();
            }
        }
    }

    public boolean f() {
        return this.k.get();
    }

    public void g() {
        this.l.set(true);
        if (this.b == null) {
            this.b = new PanoramaEventThread(this);
        }
        c(this.k.get());
    }

    public void h() {
        this.l.set(false);
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
        c(this.k.get());
    }

    public PanoramaMapCompass i() {
        return this.o;
    }

    public boolean a(GeoCoordinate geoCoordinate, final OnRetrievalListener onRetrievalListener) {
        if (onRetrievalListener == null) {
            throw new NullPointerException("StreetLevelRetrievalListener supplied is null");
        } else if (geoCoordinate == null) {
            throw new NullPointerException("center supplied is null");
        } else if (geoCoordinate.isValid()) {
            final int min = (int) Math.min(FlyForbidProtocol.DATABASE_UPDATE_DIST, 200.0d * Math.max(1.8d, (double) (DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMaxVelocity - getZoom())));
            if (min > 200) {
                return new cs().a(geoCoordinate, min, false, new Listener(this) {
                    final /* synthetic */ PanoramaModelImpl c;

                    public void onCoverageCheckCompleted(GeoCoordinate geoCoordinate, int i, ResultCode resultCode) {
                        if (resultCode == ResultCode.HAS_COVERAGE || resultCode == ResultCode.UNKNOWN_COVERAGE) {
                            onRetrievalListener.onGetStreetLevelCompleted(this.c.b(geoCoordinate, min));
                        } else {
                            onRetrievalListener.onGetStreetLevelCompleted(null);
                        }
                    }
                });
            }
            onRetrievalListener.onGetStreetLevelCompleted(b(geoCoordinate, min));
            return true;
        } else {
            throw new IllegalArgumentException("center is invalid");
        }
    }

    public StreetLevel b(GeoCoordinate geoCoordinate, int i) {
        if (geoCoordinate == null || !geoCoordinate.isValid()) {
            throw new IllegalArgumentException("center is null || invalid");
        }
        PanoramaImpl a = a(geoCoordinate, i);
        if (a == null || !a.isValid()) {
            a = null;
        }
        return PanoramaImpl.a(a, this);
    }

    public StreetLevel b(PointF pointF) {
        PanoramaImpl e = e(pointF);
        if (e == null || !e.isValid()) {
            e = null;
        }
        return PanoramaImpl.a(e, this);
    }

    public StreetLevel j() {
        PanoramaImpl panorama = getPanorama();
        if (panorama == null || !panorama.isValid()) {
            panorama = null;
        }
        return PanoramaImpl.a(panorama, this);
    }

    public void a(StreetLevel streetLevel, boolean z, float f, float f2, float f3) {
        a(PanoramaImpl.a(streetLevel), z, f, f2, f3);
    }

    public void a(StreetLevel streetLevel, boolean z, GeoCoordinate geoCoordinate, float f) {
        a(PanoramaImpl.a(streetLevel), z, GeoCoordinateImpl.get(geoCoordinate), f);
    }

    public boolean a(StreetLevelObject streetLevelObject) {
        if (streetLevelObject != null) {
            return a(cx.a(streetLevelObject));
        }
        return true;
    }

    public boolean b(StreetLevelObject streetLevelObject) {
        if (streetLevelObject != null) {
            return b(cx.a(streetLevelObject));
        }
        return true;
    }

    public synchronized void a(GeoCoordinate geoCoordinate, float f, float f2, float f3) {
        if (moveCameraNative(GeoCoordinateImpl.get(geoCoordinate), f, f2, f3)) {
            onRedraw();
        }
    }

    public boolean k() {
        return needPanoramaData();
    }

    public PointF a(GeoCoordinate geoCoordinate) {
        return geoToPixelNative(GeoCoordinateImpl.get(geoCoordinate));
    }

    public GeoCoordinate c(PointF pointF) {
        return GeoCoordinateImpl.create(pixelToGeoNative(pointF.x, pointF.y));
    }

    public GeoCoordinate l() {
        return GeoCoordinateImpl.create(getPositionNative());
    }

    public List<Float> d(PointF pointF) {
        List<Float> arrayList = new ArrayList();
        float[] toCameraOrientationNative = toCameraOrientationNative((int) pointF.x, (int) pointF.y);
        if (toCameraOrientationNative != null && toCameraOrientationNative.length == 2) {
            arrayList.add(Float.valueOf(toCameraOrientationNative[0]));
            arrayList.add(Float.valueOf(toCameraOrientationNative[1]));
        }
        return arrayList;
    }

    void a(c cVar) {
        this.q = cVar;
    }

    public void a(OnEventListener onEventListener) {
        if (onEventListener != null) {
            this.p.addIfAbsent(onEventListener);
        }
    }

    public void b(OnEventListener onEventListener) {
        if (onEventListener != null) {
            this.p.remove(onEventListener);
        }
    }

    private void a(TimerTask timerTask) {
        if (this.r != null) {
            this.r.cancel();
            this.r = null;
        }
        this.r = new Timer();
        this.r.scheduleAtFixedRate(timerTask, 0, 15);
    }

    @HybridPlusNative
    void onRedraw() {
        if (this.q != null) {
            this.q.a();
        }
    }

    @HybridPlusNative
    private void onOrientationStart(float f, float f2, float f3) {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((OnEventListener) it.next()).onOrientationStart(f, f2);
        }
    }

    @HybridPlusNative
    private void onOrientationEnd(float f, float f2, float f3) {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((OnEventListener) it.next()).onOrientationEnd(f, f2);
        }
    }

    @HybridPlusNative
    private void onZoomStart(float f) {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((OnEventListener) it.next()).onZoomStart(f);
        }
    }

    @HybridPlusNative
    private void onZoomEnd(float f) {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((OnEventListener) it.next()).onZoomEnd(f);
        }
    }

    @HybridPlusNative
    private void onPositionChanged(GeoCoordinate geoCoordinate) {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((OnEventListener) it.next()).onPositionChanged(geoCoordinate);
        }
        onRedraw();
    }

    @HybridPlusNative
    private void onMoveStart() {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((OnEventListener) it.next()).onMoveStart();
        }
        onRedraw();
        if (this.k.get()) {
            a(new b(this));
        }
        if (this.s != null) {
            this.s.cancel();
        }
        this.s = new Timer();
        this.s.scheduleAtFixedRate(new TimerTask(this) {
            final /* synthetic */ PanoramaModelImpl a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.onRedraw();
            }
        }, 0, 16);
    }

    @HybridPlusNative
    private void onMoveWait() {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((OnEventListener) it.next()).onMoveWait();
        }
    }

    @HybridPlusNative
    private void onMoveContinue() {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((OnEventListener) it.next()).onMoveContinue();
        }
        onRedraw();
    }

    @HybridPlusNative
    private void onMoveEnd(boolean z) {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((OnEventListener) it.next()).onMoveEnd(z);
        }
        if (!this.m.get() && z) {
            this.m.set(true);
            if (this.k.get()) {
                c(this.k.get());
            }
        }
        if (this.k.get()) {
            a(new a(this));
        }
        if (this.s != null) {
            this.s.cancel();
            this.s = null;
        }
    }

    @HybridPlusNative
    private void onMoveEnd(GeoCoordinate geoCoordinate) {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((OnEventListener) it.next()).onMoveEnd(geoCoordinate);
        }
    }

    @HybridPlusNative
    private void onBuildingShow(StreetLevelBuilding streetLevelBuilding) {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((OnEventListener) it.next()).onBuildingShow(streetLevelBuilding);
        }
    }

    @HybridPlusNative
    private void onBuildingHide(StreetLevelBuilding streetLevelBuilding) {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((OnEventListener) it.next()).onBuildingHide(streetLevelBuilding);
        }
    }

    @HybridPlusNative
    private void onIconPlaced(StreetLevelSelectedObject streetLevelSelectedObject) {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((OnEventListener) it.next()).onIconPlaced(streetLevelSelectedObject);
        }
    }

    @HybridPlusNative
    private void onPanoramaPreviewAvailable() {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            OnEventListener onEventListener = (OnEventListener) it.next();
            if (onEventListener instanceof OnEventListener) {
                onEventListener.onStreetLevelPreviewAvailable();
            }
        }
    }

    @HybridPlusNative
    private void onPanoramaFullyLoaded() {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((OnEventListener) it.next()).onStreetLevelFullyLoaded();
        }
    }

    @HybridPlusNative
    private void onPanoramaInvalidated() {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((OnEventListener) it.next()).onStreetLevelInvalidated();
        }
        onRedraw();
    }

    @HybridPlusNative
    private void onPanoramaChanged() {
        Iterator it = this.p.iterator();
        while (it.hasNext()) {
            ((OnEventListener) it.next()).onStreetLevelChanged();
        }
        onRedraw();
    }

    void m() {
        synchronized (this.u) {
            Iterator it = this.u.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
            this.u.clear();
        }
        if (this.n.get()) {
            this.o.d();
        }
    }

    void n() {
        synchronized (this.t) {
            Iterator it = this.t.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
            this.t.clear();
        }
    }

    void a(Runnable runnable) {
        synchronized (this.t) {
            this.t.add(runnable);
        }
    }

    void b(Runnable runnable) {
        synchronized (this.u) {
            this.u.add(runnable);
        }
    }
}
