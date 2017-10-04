package com.nokia.maps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import com.here.android.mpa.ar.ARController.Error;
import com.here.android.mpa.ar.ARPoseReading;
import com.here.android.mpa.ar.ARRadarProperties;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Size;
import com.here.android.mpa.common.Vector3f;
import com.here.android.mpa.streetlevel.StreetLevelModel;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.ar.a;
import com.nokia.maps.ar.b;
import com.nokia.maps.ar.d;
import java.util.ArrayList;
import java.util.List;

@HybridPlus
public class ARLayoutControl extends BaseNativeObject implements j {
    final ar A = new ar();
    Runnable B = new Runnable(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.sensorsAreReady();
        }
    };
    Runnable C = new Runnable(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.n();
        }
    };
    a D = new a(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            if (!(this.a.j.a(this, obj2) || obj2 == null || !(obj2 instanceof ArrayList))) {
                ArrayList arrayList = (ArrayList) obj2;
                if (arrayList.size() == 2) {
                    PointF pointF = (PointF) arrayList.get(0);
                    PointF pointF2 = (PointF) arrayList.get(1);
                    this.a.M.set((int) pointF.x, (int) pointF.y);
                    this.a.N.set((int) pointF2.x, (int) pointF2.y);
                    this.a.pan(this.a.M, this.a.N);
                }
            }
            return true;
        }
    };
    a E = new a(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            if (obj2 == null) {
                return false;
            }
            en enVar = (en) obj2;
            List b = this.a.X.b(new PointF(enVar.x, enVar.y));
            if (!(b == null || b.size() == 0)) {
                this.a.o.a(this, b);
            }
            return this.a.k.a(this, obj2);
        }
    };
    a F = new a(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            if (obj2 == null) {
                return false;
            }
            if (this.a.l.a(this, obj2)) {
                return true;
            }
            this.a.touchDown();
            return true;
        }
    };
    a G = new a(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            if (obj2 == null) {
                return false;
            }
            if (this.a.m.a(this, obj2)) {
                return true;
            }
            this.a.touchUp();
            return true;
        }
    };
    private final g J = new g();
    private ARPoseReading K = null;
    private e L;
    private Point M = new Point();
    private Point N = new Point();
    private u O;
    private int P;
    private int Q;
    private PointF R;
    private PointF S;
    private a T;
    private ARSensors U;
    private dx V;
    private Context W;
    private b X;
    private ARRadarProperties Y = null;
    private MapImpl Z = null;
    final ar a = new ar();
    private StreetLevelModel aa = null;
    private boolean ab = true;
    private cm ac = new cm();
    private boolean ad = false;
    private volatile boolean ae = false;
    private int af = -1;
    private boolean ag = true;
    private volatile boolean ah = false;
    private final a ai = new a(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            this.a.af = -1;
            this.a.ag = true;
            this.a.ah = false;
            return false;
        }
    };
    private Runnable aj = new Runnable(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.k();
        }
    };
    private Runnable ak = new Runnable(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.l();
        }
    };
    private Runnable al = new Runnable(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.i();
        }
    };
    private Runnable am = new Runnable(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.j();
        }
    };
    private Runnable an = new Runnable(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.m();
        }
    };
    private final a ao = new a(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            final boolean booleanValue = ((Boolean) obj2).booleanValue();
            ez.a(new Runnable(this) {
                final /* synthetic */ AnonymousClass5 b;

                public void run() {
                    this.b.a.cameraStarted(booleanValue);
                    this.b.a.g();
                    this.b.a.U.p();
                }
            });
            return false;
        }
    };
    private final a ap = new a(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            final boolean booleanValue = ((Boolean) obj2).booleanValue();
            ez.a(new Runnable(this) {
                final /* synthetic */ AnonymousClass6 b;

                public void run() {
                    this.b.a.cameraPreviewStarted(booleanValue);
                }
            });
            return false;
        }
    };
    private final a aq = new a(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            final Boolean bool = (Boolean) obj2;
            ez.a(new Runnable(this) {
                final /* synthetic */ AnonymousClass7 b;

                public void run() {
                    this.b.a.cameraStopped(bool.booleanValue());
                }
            });
            return false;
        }
    };
    private final a ar = new a(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            this.a.w.a(this, obj2);
            return false;
        }
    };
    private final a as = new a(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            if (this.a.ae && this.a.ah) {
                this.a.h();
            }
            return false;
        }
    };
    private a at = new a(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            return this.a.E.a(obj, obj2);
        }
    };
    private a au = new a(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            return this.a.F.a(obj, obj2);
        }
    };
    private a av = new a(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            return this.a.G.a(obj, obj2);
        }
    };
    private a aw = new a(this) {
        final /* synthetic */ ARLayoutControl a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            this.a.O.requestRender();
            return this.a.j.a(this, obj2);
        }
    };
    final ar b = new ar();
    final ar c = new ar();
    final ar d = new ar();
    final ar e = new ar();
    final ar f = new ar();
    final ar g = new ar();
    final ar h = new ar();
    final ar i = new ar();
    final ar j = new ar();
    final ar k = new ar();
    final ar l = new ar();
    final ar m = new ar();
    final ar n = new ar();
    final ar o = new ar();
    final ar p = new ar();
    ar q = null;
    final ar r = new ar();
    final ar s = new ar();
    final ar t = new ar();
    final ar u = new ar();
    final ar v = new ar();
    final ar w = new ar();
    final ar x = new ar();
    final ar y = new ar();
    final ar z = new ar();

    private native void createNative();

    private native void destroyNative();

    native void addARObject(ARObjectImpl aRObjectImpl);

    native void addARObject(ARPolylineObjectImpl aRPolylineObjectImpl);

    native void addARViewObject(ARModelObjectImpl aRModelObjectImpl);

    native void applicationIsReady();

    native void cameraPreviewStarted(boolean z);

    native void cameraStarted(boolean z);

    native void cameraStopped(boolean z);

    native void defocus();

    native void depress(long j);

    native void destroy();

    native void enableDownIcons(boolean z);

    native void enableRadar(boolean z);

    native void enableStateMachineTraces(boolean z);

    native void focus(long j);

    native boolean geoTo3dPosition(GeoCoordinateImpl geoCoordinateImpl, Vector3f vector3f);

    native long getAnimationDelay(int i);

    native long getAnimationDuration(int i);

    native int getAnimationInterpolator(int i);

    native Size getBackIconSize();

    native ARBuildingInfoImpl getBuildingInfo(PointF pointF);

    native float getCameraMaxZoomScaleUpView();

    native float getDownIconOpacity();

    native float getDownViewMaxOpacity();

    native float getDownViewMinOpacity();

    native float getDownViewPitchThreshold();

    native float getFilterCoeff(int i);

    native int getFilterSize(int i);

    native float getFixedAltitude();

    native float getFlyRotateDeg(int i);

    native Size getFrontIconSize();

    native float getInfoAnimationMinWidthFactor();

    native int getIntroAnimationMode();

    native long getIntroAnimationTime();

    native boolean getMapAutoGeoCenter();

    native boolean getMapAutoHeading();

    native boolean getMapAutoPitch();

    native boolean getMapAutoTfc();

    native boolean getMapAutoZoom();

    native float getMaxZoomScale();

    native float getMinPitchDownView();

    native float getNonSelectedItemsOpacity();

    native long[] getObjects(Point point);

    native long[] getObjectsRect(Point point, Point point2);

    native float getOcclusionOpacity();

    native int getProjectionType(long j);

    native PointF getScreenViewPoint();

    native Size getSelectedIconSize();

    native float getSelectedItemMaxViewAngle();

    native float getSelectedItemOpacity();

    native long getSensorsWaitTimeout();

    native long getTiltUpMaxTime();

    native long getTiltUpMinTime();

    native float getUpViewPitchThreshold();

    native boolean getUseDownIconOpacity();

    native void glContextEvent(int i);

    native void initProjector();

    native boolean isCameraZoomEnabledUpView();

    native boolean isEdgeDetectionEnabled();

    native boolean isMapAsPoseReadingSource();

    native boolean isOccluded(ARObjectImpl aRObjectImpl);

    native boolean isOcclusionEnabled();

    native boolean isPitchLockedUpView();

    native boolean isPoseEngineHeadingUsed();

    native boolean isShowGridEnabled();

    native boolean isStateMachineTracesEnabled();

    native boolean isVisible(ARObjectImpl aRObjectImpl);

    native void memoryCheck();

    native void pan(Point point, Point point2);

    native void panTo(GeoCoordinateImpl geoCoordinateImpl);

    native void pause();

    native boolean pixelTo3dPosition(float f, PointF pointF, Vector3f vector3f);

    native void press(long j);

    native void removeARObject(ARObjectImpl aRObjectImpl);

    native void removeARObject(ARPolylineObjectImpl aRPolylineObjectImpl);

    native void removeARViewObject(ARModelObjectImpl aRModelObjectImpl);

    native void resume();

    native void select(long j);

    native void selectWithScale(long j, boolean z, float f);

    native void sensorsAreReady();

    native void setAnimationDelay(int i, long j);

    native void setAnimationDuration(int i, long j);

    native void setAnimationInterpolator(int i, int i2);

    native void setBack2FrontIconSizeRatio(float f);

    native void setBackIconSize(Size size);

    native void setCameraAndLayout(int i, int i2, float f, float f2, int i3, int i4, float f3, float f4);

    native void setCameraFov(float f, float f2);

    native void setCameraFrameSize(int i, int i2);

    native void setCameraMaxZoomScaleUpView(float f);

    native void setCameraZoomEnabledUpView(boolean z);

    native void setDownIconOpacity(float f);

    native void setDownViewMaxOpacity(float f);

    native void setDownViewMinOpacity(float f);

    native void setDownViewPitchThreshold(float f);

    native void setEdgeDetectionEnabled(boolean z);

    native void setFilterCoeff(int i, float f);

    native void setFilterSize(int i, int i2);

    native void setFixedAltitude(float f, boolean z);

    native void setFlyRotateDeg(int i, float f);

    native void setFrontIconSize(Size size);

    native void setInfoAnimationInUpViewOnly(boolean z);

    native void setInfoAnimationMinWidthFactor(float f);

    native void setIntroAnimationMode(int i);

    native void setIntroAnimationTime(long j);

    native void setLayoutFov(float f, float f2);

    native void setLayoutSize(int i, int i2);

    native void setMapAsPoseReadingSource(boolean z);

    native void setMapAutoControlOnEntryExit(boolean z);

    native void setMapAutoGeoPosition(boolean z, boolean z2);

    native void setMapAutoHeading(boolean z, boolean z2);

    native void setMapAutoPitchNative(boolean z, boolean z2);

    native void setMapAutoTfc(boolean z, boolean z2);

    native void setMapAutoZoom(boolean z, boolean z2);

    native void setMapNative(MapImpl mapImpl);

    native void setMaxZoomScale(float f, boolean z, boolean z2);

    native void setMinPitchDownView(float f);

    native void setNonSelectedItemsOpacity(float f);

    native void setOcclusionEnabled(boolean z);

    native void setOcclusionOpacity(float f);

    native void setOrientationAngle(float f);

    native void setOrientationAnimation(boolean z);

    native void setPanoramaModelNative(PanoramaModelImpl panoramaModelImpl);

    native void setPitchLockedUpView(boolean z);

    native void setPitchThresholdForPoseEngineHeading(float f);

    native void setPlanesParam(float f, float f2, float f3, float f4);

    native void setProjectionType(long j, int i);

    native void setScreenViewPoint(PointF pointF, boolean z);

    native void setSelectedBoundingBox(PointF pointF, PointF pointF2);

    native void setSelectedIconSize(Size size);

    native void setSelectedItemMaxViewAngle(float f);

    native void setSelectedItemOpacity(float f);

    native void setSensors(ARSensors aRSensors);

    native void setSensorsWaitTimeout(long j);

    native void setShowGridEnabled(boolean z);

    native void setTiltUpMaxTime(long j);

    native void setTiltUpMinTime(long j);

    native void setUpViewPitchThreshold(float f);

    native void setUpdateDistanceThreshold(float f);

    native void setUseDownIconOpacity(boolean z);

    native void showFrontItemsOnly(boolean z);

    native void showScene(int i);

    native void showUpScene(int i);

    native boolean startLivesight();

    native void startOrientationAnimation(boolean z);

    native void stopLivesight(boolean z);

    native void touchDown();

    native void touchUp();

    native void unselect();

    native void usePoseEngineHeading(boolean z);

    @SuppressLint({"NewApi"})
    ARLayoutControl(u uVar, b bVar) {
        this.O = uVar;
        this.X = bVar;
        this.W = this.O.getContext().getApplicationContext();
        createNative();
        b();
    }

    void a(dx dxVar) {
        this.V = dxVar;
        dxVar.a.a(this.ai);
    }

    @HybridPlusNative
    private ARLayoutControl(int i) {
        this.nativeptr = i;
    }

    void a(MapImpl mapImpl) {
        this.Z = mapImpl;
        if (mapImpl != null) {
            setMapNative(mapImpl);
        }
    }

    void a(boolean z, boolean z2) {
        this.ab = z;
        setMapAutoPitchNative(z, z2);
    }

    protected void finalize() {
        destroyNative();
    }

    @HybridPlusNative
    void onEglSwapBuffers() {
        this.O.e();
    }

    @HybridPlusNative
    void onRequestToRender() {
        this.O.f();
        this.O.requestRender();
    }

    @HybridPlusNative
    void onRequestToCreateCamera() {
        if (this.T == null) {
            this.T = new a(this.W);
            this.T.i();
        }
    }

    @HybridPlusNative
    void onRequestToDestroyCamera() {
        if (this.T != null) {
            this.T.a.b(this.ao);
            this.T.b.b(this.ap);
            this.T.d.b(this.ar);
            this.T.j();
            this.T.k();
            this.T = null;
        }
    }

    @HybridPlusNative
    void onRequestToCreateSensors() {
        if (this.U == null) {
            this.U = new ARSensors(this.W, false);
            setSensors(this.U);
            this.U.a.a(new b(this) {
                final /* synthetic */ ARLayoutControl a;

                {
                    this.a = r1;
                }

                public boolean a(Object obj, Object obj2, Object obj3) {
                    if (this.a.U.s()) {
                        ez.a(this.a.B);
                    }
                    this.a.n.a(null, obj2, obj3);
                    return false;
                }
            });
            this.U.g.a(new a(this) {
                final /* synthetic */ ARLayoutControl a;

                {
                    this.a = r1;
                }

                public boolean a(Object obj, Object obj2) {
                    if (this.a.X.W()) {
                        this.a.ah = true;
                    }
                    return false;
                }
            });
            this.U.f.a(new d(this) {
                final /* synthetic */ ARLayoutControl a;

                {
                    this.a = r1;
                }

                public boolean a(Object obj, Object obj2) {
                    boolean z = true;
                    if (obj2 != null && this.a.X.W()) {
                        int intValue = ((Integer) obj2).intValue();
                        if (this.a.af == -1) {
                            this.a.af = intValue / 90;
                        }
                        this.a.setOrientationAngle((float) intValue);
                        if (intValue > 45 && intValue <= 90 && this.a.af == 0) {
                            this.a.af = 1;
                        } else if (intValue >= 0 && intValue < 45 && this.a.af == 1) {
                            this.a.af = 0;
                        } else if (intValue < dji.pilot.usercenter.protocol.d.h && intValue >= 270 && this.a.af == 0) {
                            this.a.af = 3;
                        } else if (intValue > dji.pilot.usercenter.protocol.d.h && intValue <= 360 && this.a.af == 3) {
                            this.a.af = 0;
                        }
                        if (!(this.a.ag && this.a.U.i() == this.a.af)) {
                            this.a.ah = false;
                            ARLayoutControl aRLayoutControl = this.a;
                            if (this.a.ag) {
                                z = false;
                            }
                            aRLayoutControl.ag = z;
                            this.a.startOrientationAnimation(this.a.ag);
                        }
                    }
                    return false;
                }
            });
        }
    }

    @HybridPlusNative
    void onRequestToDestroySensors() {
        if (this.U != null) {
            onRequestToStopCamera(true, false);
            onRequestToStopSensors();
            ez.b(this.B);
            this.U.j();
            this.U = null;
        }
    }

    @HybridPlusNative
    void onRequestToStartCamera(boolean z) {
        if (this.T != null) {
            this.T.a.a(this.ao);
            this.T.d.a(this.ar);
            this.T.a(z);
        }
    }

    @HybridPlusNative
    void onRequestToStopCamera(boolean z, boolean z2) {
        this.ad = false;
        if (this.T != null) {
            this.T.a.b(this.ao);
            this.T.d.b(this.ar);
            if (!z2) {
                this.T.b(z);
            }
        }
    }

    @HybridPlusNative
    void onRequestToStartSensors() {
        if (this.U != null) {
            this.U.d();
            g();
            this.U.p();
        }
    }

    @SuppressLint({"NewApi"})
    private void g() {
        if (this.T != null && this.U != null && !this.ad) {
            this.S = this.T.h();
            this.P = this.O.getWidth();
            this.Q = this.O.getHeight();
            this.R = this.T.a(this.P, this.Q);
            this.U.a(this.T, new Size(this.P, this.Q), this.R);
            Size g = this.T.g();
            if (g == null || g.width <= 0 || g.height <= 0) {
                g = new Size(1, 1);
            }
            setCameraAndLayout(this.P, this.Q, this.R.x, this.R.y, g.width, g.height, this.S.x, this.S.y);
            this.ad = true;
        }
    }

    @HybridPlusNative
    void onRequestToStopSensors() {
        this.ad = false;
        if (this.U != null) {
            this.U.e();
            if (this.T != null) {
                synchronized (this.T.c) {
                    this.U.q();
                }
                return;
            }
            this.U.q();
        }
    }

    @HybridPlusNative
    void onRequestToResumeCamera(boolean z) {
        if (this.T != null) {
            if (this.ae) {
                this.T.c.a(this.as);
            }
            this.T.b.a(this.ap);
            this.T.c(z);
        }
    }

    @HybridPlusNative
    void onRequestToPauseCamera(boolean z, boolean z2) {
        if (this.T != null) {
            this.T.c.b(this.as);
            this.T.b.b(this.ap);
            if (!z2) {
                this.T.d(z);
            }
        }
    }

    @HybridPlusNative
    void onRequestToResumeSensors() {
        if (this.U != null) {
            this.U.d();
        }
    }

    @HybridPlusNative
    void onRequestToPauseSensors() {
        if (this.U != null) {
            this.U.e();
        }
    }

    @HybridPlusNative
    void onFirstLiveSightFrame() {
        ((ai) this.O).m();
    }

    @HybridPlusNative
    void onLastLiveSightFrame() {
        this.x.a(this, null);
    }

    @HybridPlusNative
    void onMapSceneStart() {
        this.d.a(this, null);
        ez.a(this.aj);
        if (this.ae && this.ah) {
            h();
        }
    }

    @HybridPlusNative
    void onMapSceneStop() {
        this.e.a(this, null);
        ez.a(this.ak);
    }

    @HybridPlusNative
    void onCameraLiveSceneStart() {
        this.b.a(this, null);
        ez.a(this.al);
    }

    @HybridPlusNative
    void onCameraLiveSceneStop() {
        this.c.a(this, null);
        ez.a(this.am);
    }

    @HybridPlusNative
    void onSliSceneStart() {
        this.f.a(this, null);
        ez.a(this.an);
    }

    @HybridPlusNative
    void onSliSceneStop() {
        this.g.a(this, null);
        ez.a(this.C);
    }

    @HybridPlusNative
    void onCameraRecSceneStart() {
    }

    @HybridPlusNative
    void onCameraRecSceneStop() {
    }

    @HybridPlusNative
    void onCameraPlaybackSceneStart() {
    }

    @HybridPlusNative
    void onCameraPlaybackSceneStop() {
    }

    @HybridPlusNative
    void onPrePresent() {
        e b = b();
        if (b != null) {
            b.c();
        }
        this.u.a(this, null);
    }

    @HybridPlusNative
    void onPostPresent() {
        this.v.a(this, null);
    }

    @HybridPlusNative
    void onItemRemoved(long j) {
        this.X.a(j);
    }

    @HybridPlusNative
    void onPreDraw() {
        this.s.a(this, null);
    }

    @HybridPlusNative
    void onPreDrawMap(float f, float f2, GeoCoordinateImpl geoCoordinateImpl) {
        this.J.a = f;
        this.J.b = f2;
        if (geoCoordinateImpl != null) {
            if (this.J.c == null) {
                this.J.c = new GeoCoordinate(geoCoordinateImpl.a(), geoCoordinateImpl.b(), geoCoordinateImpl.c());
            } else {
                this.J.c.setLatitude(geoCoordinateImpl.a());
                this.J.c.setLongitude(geoCoordinateImpl.b());
                this.J.c.setAltitude(geoCoordinateImpl.c());
            }
        }
        if (this.ab) {
            this.Z.b(f2);
        }
        this.Z.B();
        this.Z.C();
        this.t.a(this, this.J);
    }

    @HybridPlusNative
    void onRadarUpdate(ARRadar aRRadar) {
        if (this.Y == null) {
            this.Y = ARRadar.a(aRRadar);
            ARRadar.a(this.Y).a(this.X);
        }
        this.p.a(this, this.Y);
    }

    @HybridPlusNative
    void onTerminated() {
        this.y.a(this, null);
    }

    @HybridPlusNative
    void onLivesightStatus(int i) {
        if (i >= Error.NONE.ordinal() && i < Error.values().length) {
            this.w.a(this, Error.values()[i]);
        }
    }

    @HybridPlusNative
    float onGetPitch(float f) {
        this.ac.a(f);
        this.z.a(this, this.ac);
        return this.ac.a();
    }

    @HybridPlusNative
    private void onPose(ARPoseReadingImpl aRPoseReadingImpl) {
        if (!this.r.b()) {
            if (this.K == null) {
                this.K = ARPoseReadingImpl.a(aRPoseReadingImpl);
            } else {
                ARPoseReadingImpl.a(this.K).b(aRPoseReadingImpl);
            }
            this.r.a(this, this.K);
        }
    }

    ARPoseReading a() {
        return this.K;
    }

    @HybridPlusNative
    private void onProjectionCameraUpdated() {
        this.A.a(this, null);
    }

    e b() {
        if (this.L == null) {
            this.L = this.O.a();
        }
        return this.L;
    }

    public void a(Context context) {
        try {
            glContextEvent(1);
        } catch (Exception e) {
            onRequestToDestroySensors();
            onRequestToDestroyCamera();
        }
    }

    public void a(int i, int i2) {
        this.P = i;
        this.Q = i2;
        try {
            setLayoutSize(i, i2);
            glContextEvent(2);
        } catch (Exception e) {
            onRequestToDestroySensors();
            onRequestToDestroyCamera();
        }
    }

    public void c() {
        d();
    }

    public void d() {
        try {
            glContextEvent(3);
        } catch (Exception e) {
            onRequestToDestroySensors();
            onRequestToDestroyCamera();
        }
    }

    public void e() {
        try {
            glContextEvent(0);
        } catch (Exception e) {
            onRequestToDestroySensors();
            onRequestToDestroyCamera();
        }
    }

    private void h() {
        ez.a(new Runnable(this) {
            final /* synthetic */ ARLayoutControl a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.ae && this.a.ah) {
                    this.a.ah = false;
                    this.a.ag = true;
                    this.a.startOrientationAnimation(true);
                }
            }
        });
    }

    private void i() {
        e b = b();
        b.d.a(this.D);
        b.a.a(this.E);
        b.b.a(this.F);
        b.c.a(this.G);
    }

    private void j() {
        e b = b();
        b.d.b(this.D);
        b.a.b(this.E);
        b.b.b(this.F);
        b.c.b(this.G);
    }

    private void k() {
        e b = b();
        b.d.a(this.aw);
        b.a.a(this.at);
        b.b.a(this.au);
        b.c.a(this.av);
    }

    private void l() {
        e b = b();
        b.d.b(this.aw);
        b.a.b(this.at);
        b.b.b(this.au);
        b.c.b(this.av);
    }

    private void m() {
    }

    private void n() {
    }

    ARSensors f() {
        return this.U;
    }

    void a(boolean z) {
        if (!z && this.ae && this.ah) {
            this.ah = false;
            this.ag = true;
            startOrientationAnimation(true);
        }
        this.ae = z;
        setOrientationAnimation(z);
    }
}
