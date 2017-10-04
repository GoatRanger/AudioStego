package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amap.api.mapcore.indoor.IndoorBuilding;
import com.amap.api.maps.AMap.CancelableCallback;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnCacheRemoveListener;
import com.amap.api.maps.AMap.OnCameraChangeListener;
import com.amap.api.maps.AMap.OnIndoorBuildingActiveListener;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapClickListener;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.AMap.OnMapLongClickListener;
import com.amap.api.maps.AMap.OnMapScreenShotListener;
import com.amap.api.maps.AMap.OnMapTouchListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.AMap.OnMarkerDragListener;
import com.amap.api.maps.AMap.OnMyLocationChangeListener;
import com.amap.api.maps.AMap.OnPOIClickListener;
import com.amap.api.maps.AMap.OnPolylineClickListener;
import com.amap.api.maps.AMap.onMapPrintScreenListener;
import com.amap.api.maps.CustomRenderer;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.ArcOptions;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.MyTrafficStyle;
import com.amap.api.maps.model.NavigateArrowOptions;
import com.amap.api.maps.model.Poi;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapCore;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.SelectedMapPoi;
import com.autonavi.amap.mapcore.VMapDataCache;
import com.autonavi.amap.mapcore.interfaces.CameraAnimator;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate.Type;
import com.autonavi.amap.mapcore.interfaces.GLOverlay;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IArcDelegate;
import com.autonavi.amap.mapcore.interfaces.ICircleDelegate;
import com.autonavi.amap.mapcore.interfaces.IGLSurfaceView;
import com.autonavi.amap.mapcore.interfaces.IGroundOverlayDelegate;
import com.autonavi.amap.mapcore.interfaces.IMarkerDelegate;
import com.autonavi.amap.mapcore.interfaces.INavigateArrowDelegate;
import com.autonavi.amap.mapcore.interfaces.IOverlayDelegate;
import com.autonavi.amap.mapcore.interfaces.IPolygonDelegate;
import com.autonavi.amap.mapcore.interfaces.IPolylineDelegate;
import com.autonavi.amap.mapcore.interfaces.IProjectionDelegate;
import com.autonavi.amap.mapcore.interfaces.ITileOverlayDelegate;
import com.autonavi.amap.mapcore.interfaces.IUiSettingsDelegate;
import com.facebook.login.widget.ToolTipPopup;
import com.google.android.gms.location.places.Place;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class c implements Renderer, IAMapDelegate {
    private static final double aG = Math.log(2.0d);
    private CopyOnWriteArrayList<Integer> A;
    private CopyOnWriteArrayList<Integer> B;
    private com.amap.api.mapcore.util.u.c C;
    private com.amap.api.mapcore.util.u.a D;
    private com.amap.api.mapcore.util.u.b E;
    private int F;
    private MapCore G;
    private Context H;
    private a I;
    private MapProjection J;
    private GestureDetector K;
    private ScaleGestureDetector L;
    private be M;
    private SurfaceHolder N;
    private ah O;
    private az P;
    private aa Q;
    private q R;
    private ar S;
    private o T;
    private z U;
    private OnMyLocationChangeListener V;
    private OnMarkerClickListener W;
    private OnPolylineClickListener X;
    private OnMarkerDragListener Y;
    private OnMapLoadedListener Z;
    float a;
    private onMapPrintScreenListener aA;
    private OnMapScreenShotListener aB;
    private Handler aC;
    private IndoorBuilding aD;
    private CameraUpdateFactoryDelegate aE;
    private Timer aF;
    private boolean aH;
    private boolean aI;
    private boolean aJ;
    private boolean aK;
    private boolean aL;
    private boolean aM;
    private boolean aN;
    private boolean aO;
    private boolean aP;
    private Boolean aQ;
    private boolean aR;
    private boolean aS;
    private boolean aT;
    private Handler aU;
    private int aV;
    private s aW;
    private boolean aX;
    private boolean aY;
    private volatile boolean aZ;
    private OnCameraChangeListener aa;
    private OnMapClickListener ab;
    private OnMapTouchListener ac;
    private OnPOIClickListener ad;
    private OnMapLongClickListener ae;
    private OnInfoWindowClickListener af;
    private OnIndoorBuildingActiveListener ag;
    private InfoWindowAdapter ah;
    private InfoWindowAdapter ai;
    private View aj;
    private IMarkerDelegate ak;
    private ap al;
    private IProjectionDelegate am;
    private IUiSettingsDelegate an;
    private LocationSource ao;
    private Rect ap;
    private m aq;
    private bd ar;
    private aj as;
    private CameraAnimator at;
    private int au;
    private int av;
    private CancelableCallback aw;
    private int ax;
    private Drawable ay;
    private Location az;
    float b;
    private volatile boolean ba;
    private Handler bb;
    private Runnable bc;
    private volatile boolean bd;
    private boolean be;
    private boolean bf;
    private boolean bg;
    private Marker bh;
    private IMarkerDelegate bi;
    private boolean bj;
    private boolean bk;
    private boolean bl;
    private int bm;
    private boolean bn;
    private Thread bo;
    private LatLngBounds bp;
    private boolean bq;
    private boolean br;
    private int bs;
    private int bt;
    private Handler bu;
    private Runnable bv;
    private Runnable bw;
    private a bx;
    float c;
    public ae d;
    ad e;
    ba f;
    aw g;
    v h;
    u i;
    IGLSurfaceView j;
    Runnable k;
    final Handler l;
    CustomRenderer m;
    private int n;
    private int o;
    private int p;
    private Bitmap q;
    private Bitmap r;
    private int s;
    private int t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private MyTrafficStyle y;
    private float z;

    private static abstract class a implements Runnable {
        boolean b;
        boolean c;
        com.amap.api.mapcore.util.u.a d;
        com.amap.api.mapcore.util.u.c e;
        com.amap.api.mapcore.util.u.b f;

        private a() {
            this.b = false;
            this.c = false;
        }

        public void run() {
            this.b = false;
        }
    }

    private class b implements com.amap.api.mapcore.util.bd.a {
        Float a;
        Float b;
        IPoint c;
        float d;
        CameraUpdateFactoryDelegate e;
        final /* synthetic */ c f;
        private float g;
        private float h;
        private float i;
        private float j;
        private float k;

        private b(c cVar) {
            this.f = cVar;
            this.a = null;
            this.b = null;
            this.c = new IPoint();
            this.d = 0.0f;
            this.e = CameraUpdateFactoryDelegate.newInstance();
        }

        public void a(float f, float f2, float f3, float f4, float f5) {
            this.g = f2;
            this.i = f3;
            this.h = f4;
            this.j = f5;
            this.k = (this.j - this.i) / (this.h - this.g);
            this.a = null;
            this.b = null;
            if (this.f.br) {
                this.e.nowType = Type.changeGeoCenterZoomTiltBearing;
                this.f.getPixel2Geo(this.f.bs, this.f.bt, this.c);
                this.e.geoPoint = this.c;
                this.e.isUseAnchor = this.f.br;
            } else {
                this.e.nowType = Type.changeTilt;
            }
            this.e.zoom = this.f.J.getMapZoomer();
            this.e.bearing = this.f.J.getMapAngle();
        }

        public boolean a(MotionEvent motionEvent, float f, float f2, float f3, float f4) {
            try {
                if (!this.f.an.isTiltGesturesEnabled()) {
                    return true;
                }
                if (this.f.bf || this.f.bk) {
                    return true;
                }
                if (this.b == null) {
                    this.b = Float.valueOf(f4);
                }
                if (this.a == null) {
                    this.a = Float.valueOf(f2);
                }
                float f5 = this.i - f2;
                float f6 = this.j - f4;
                float f7 = this.g - f;
                float f8 = this.h - f3;
                if (((double) Math.abs(this.k - ((f4 - f2) / (f3 - f)))) >= 0.2d || (((f5 <= 0.0f || f6 <= 0.0f) && (f5 >= 0.0f || f6 >= 0.0f)) || ((f7 < 0.0f || f8 < 0.0f) && (f7 > 0.0f || f8 > 0.0f)))) {
                    return false;
                }
                f6 = (this.a.floatValue() - f2) / DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity;
                this.f.be = true;
                f5 = this.f.J.getCameraHeaderAngle();
                if (f5 > 45.0f) {
                    f5 = 45.0f;
                }
                this.d = f5 - f6;
                this.e.tilt = this.d;
                this.f.e.a(this.e);
                this.a = Float.valueOf(f2);
                this.b = Float.valueOf(f4);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
                return true;
            }
        }

        public void a() {
            if (!this.f.bf) {
                try {
                    if (!this.f.an.isZoomGesturesEnabled()) {
                        return;
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                try {
                    this.f.animateCamera(CameraUpdateFactoryDelegate.zoomOut());
                } catch (Throwable e2) {
                    ee.a(e2, "AMapDelegateImpGLSurfaceView", "onMultiTouchSingleTap");
                    e2.printStackTrace();
                }
            }
        }
    }

    private class c implements OnDoubleTapListener {
        final /* synthetic */ c a;

        private c(c cVar) {
            this.a = cVar;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onDoubleTap(android.view.MotionEvent r6) {
            /*
            r5 = this;
            r4 = 1;
            r0 = r5.a;	 Catch:{ RemoteException -> 0x000e }
            r0 = r0.an;	 Catch:{ RemoteException -> 0x000e }
            r0 = r0.isZoomGesturesEnabled();	 Catch:{ RemoteException -> 0x000e }
            if (r0 != 0) goto L_0x0012;
        L_0x000d:
            return r4;
        L_0x000e:
            r0 = move-exception;
            r0.printStackTrace();
        L_0x0012:
            r0 = r5.a;
            r0 = r0.bm;
            if (r0 > r4) goto L_0x000d;
        L_0x001a:
            r0 = r5.a;
            r0.bl = r4;
            r0 = r5.a;
            r0 = r0.J;
            r0 = r0.getMapZoomer();
            r1 = r5.a;
            r1 = r1.getMaxZoomLevel();
            r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1));
            if (r0 == 0) goto L_0x000d;
        L_0x0033:
            r0 = r6.getX();
            r1 = r6.getY();
            r0 = (int) r0;
            r1 = (int) r1;
            r2 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
            r3 = new android.graphics.Point;
            r3.<init>(r0, r1);
            r0 = com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate.zoomBy(r2, r3);
            r1 = r5.a;	 Catch:{ RemoteException -> 0x004e }
            r1.animateCamera(r0);	 Catch:{ RemoteException -> 0x004e }
            goto L_0x000d;
        L_0x004e:
            r0 = move-exception;
            r1 = "AMapDelegateImpGLSurfaceView";
            r2 = "onDoubleTap";
            com.amap.api.mapcore.util.ee.a(r0, r1, r2);
            r0.printStackTrace();
            goto L_0x000d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.c.c.onDoubleTap(android.view.MotionEvent):boolean");
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            return false;
        }

        public boolean onSingleTapConfirmed(final MotionEvent motionEvent) {
            this.a.bj = false;
            if (this.a.bn) {
                this.a.bn = false;
            } else {
                if (this.a.aj != null) {
                    if (this.a.d.a(new Rect(this.a.aj.getLeft(), this.a.aj.getTop(), this.a.aj.getRight(), this.a.aj.getBottom()), (int) motionEvent.getX(), (int) motionEvent.getY())) {
                        if (this.a.af != null) {
                            IMarkerDelegate e = this.a.d.e();
                            if (e.isVisible()) {
                                this.a.af.onInfoWindowClick(new Marker(e));
                            }
                        }
                    }
                }
                try {
                    if (this.a.d.b(motionEvent)) {
                        final IMarkerDelegate e2 = this.a.d.e();
                        if (e2 != null && e2.isVisible()) {
                            Marker marker = new Marker(e2);
                            if (this.a.W != null) {
                                if (this.a.W.onMarkerClick(marker) || this.a.d.b() <= 0) {
                                    this.a.d.d(e2);
                                } else {
                                    this.a.aC.postDelayed(new Runnable(this) {
                                        final /* synthetic */ c b;

                                        public void run() {
                                            try {
                                                this.b.a.showInfoWindow(e2);
                                            } catch (Throwable th) {
                                                ee.a(th, "AMapDelegateImpGLSurfaceView", "onSingleTapUp showInfoWindow");
                                                th.printStackTrace();
                                            }
                                        }
                                    }, 20);
                                    if (!e2.isViewMode()) {
                                        LatLng realPosition = e2.getRealPosition();
                                        if (realPosition != null) {
                                            IPoint iPoint = new IPoint();
                                            this.a.latlon2Geo(realPosition.latitude, realPosition.longitude, iPoint);
                                            this.a.moveCamera(CameraUpdateFactoryDelegate.changeGeoCenter(iPoint));
                                        }
                                    }
                                }
                            }
                            this.a.d.d(e2);
                        }
                    } else {
                        DPoint dPoint;
                        if (this.a.ab != null) {
                            dPoint = new DPoint();
                            this.a.getPixel2LatLng((int) motionEvent.getX(), (int) motionEvent.getY(), dPoint);
                            this.a.ab.onMapClick(new LatLng(dPoint.y, dPoint.x));
                        }
                        if (this.a.X != null) {
                            dPoint = new DPoint();
                            this.a.getPixel2LatLng((int) motionEvent.getX(), (int) motionEvent.getY(), dPoint);
                            LatLng latLng = new LatLng(dPoint.y, dPoint.x);
                            if (latLng != null) {
                                IOverlayDelegate a = this.a.h.a(latLng);
                                if (a != null) {
                                    this.a.X.onPolylineClick(new Polyline((IPolylineDelegate) a));
                                }
                            }
                        }
                        this.a.queueEvent(new Runnable(this) {
                            final /* synthetic */ c b;

                            public void run() {
                                final Poi a = this.b.a.a((int) motionEvent.getX(), (int) motionEvent.getY(), 25);
                                if (this.b.a.ad != null && a != null) {
                                    this.b.a.l.post(new Runnable(this) {
                                        final /* synthetic */ AnonymousClass2 b;

                                        public void run() {
                                            this.b.b.a.ad.onPOIClick(a);
                                        }
                                    });
                                }
                            }
                        });
                    }
                } catch (Throwable e3) {
                    ee.a(e3, "AMapDelegateImpGLSurfaceView", "onSingleTapUp moveCamera");
                    e3.printStackTrace();
                } catch (Throwable e32) {
                    ee.a(e32, "AMapDelegateImpGLSurfaceView", "onSingleTapUp");
                    e32.printStackTrace();
                }
            }
            return true;
        }
    }

    private class d implements OnGestureListener {
        FPoint a;
        IPoint b;
        IPoint c;
        CameraUpdateFactoryDelegate d;
        final /* synthetic */ c e;

        private d(c cVar) {
            this.e = cVar;
            this.a = new FPoint();
            this.b = new IPoint();
            this.c = new IPoint();
            this.d = CameraUpdateFactoryDelegate.changeGeoCenter(this.c);
        }

        public boolean onDown(MotionEvent motionEvent) {
            this.e.bj = false;
            if (!this.e.bl) {
                try {
                    this.e.stopAnimation();
                } catch (Throwable e) {
                    ee.a(e, "AMapDelegateImpGLSurfaceView", "onDown");
                    e.printStackTrace();
                }
            }
            this.e.bl = false;
            this.e.bm = 0;
            this.a.x = motionEvent.getX();
            this.a.y = motionEvent.getY();
            this.e.getPixel2Geo((int) this.a.x, (int) this.a.y, this.b);
            return true;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onFling(android.view.MotionEvent r11, android.view.MotionEvent r12, float r13, float r14) {
            /*
            r10 = this;
            r4 = 0;
            r9 = 1;
            r0 = r10.e;
            r0.bj = r4;
            r0 = r10.e;	 Catch:{ RemoteException -> 0x0014 }
            r0 = r0.an;	 Catch:{ RemoteException -> 0x0014 }
            r0 = r0.isScrollGesturesEnabled();	 Catch:{ RemoteException -> 0x0014 }
            if (r0 != 0) goto L_0x001f;
        L_0x0013:
            return r9;
        L_0x0014:
            r0 = move-exception;
            r1 = "AMapDelegateImpGLSurfaceView";
            r2 = "onFling";
            com.amap.api.mapcore.util.ee.a(r0, r1, r2);
            r0.printStackTrace();
        L_0x001f:
            r0 = r10.e;
            r0 = r0.ar;
            r0 = r0.a();
            if (r0 != 0) goto L_0x0013;
        L_0x002b:
            r0 = r11.getEventTime();
            r2 = r10.e;
            r2 = r2.ar;
            r2 = r2.b();
            r0 = r0 - r2;
            r2 = 30;
            r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r0 < 0) goto L_0x0013;
        L_0x0040:
            r0 = r10.e;
            r0 = r0.getMapWidth();
            r1 = r10.e;
            r1 = r1.getMapHeight();
            r6 = r0 * 2;
            r8 = r1 * 2;
            r2 = r10.e;
            r0 = r0 / 2;
            r2.au = r0;
            r0 = r10.e;
            r1 = r1 / 2;
            r0.av = r1;
            r0 = r10.e;
            r1 = 0;
            r0.aw = r1;
            r0 = r10.e;
            r0 = r0.aj;
            if (r0 == 0) goto L_0x0096;
        L_0x006c:
            r0 = r10.e;
            r0 = r0.ak;
            if (r0 == 0) goto L_0x0096;
        L_0x0074:
            r0 = r10.e;
            r0 = r0.ak;
            r0 = r0.isViewMode();
            if (r0 != 0) goto L_0x0096;
        L_0x0080:
            r0 = r10.e;
            r0.aS = r4;
            r0 = r10.e;
            r0 = r0.al;
            if (r0 == 0) goto L_0x0096;
        L_0x008d:
            r0 = r10.e;
            r0 = r0.al;
            r0.setVisible(r9);
        L_0x0096:
            r0 = r10.e;
            r0 = r0.at;
            r1 = r10.e;
            r1 = r1.au;
            r2 = r10.e;
            r2 = r2.av;
            r3 = -r13;
            r3 = (int) r3;
            r3 = r3 * 3;
            r3 = r3 / 5;
            r4 = -r14;
            r4 = (int) r4;
            r4 = r4 * 3;
            r4 = r4 / 5;
            r5 = -r6;
            r7 = -r8;
            r0.fling(r1, r2, r3, r4, r5, r6, r7, r8);
            r0 = r10.e;
            r0 = r0.g;
            if (r0 == 0) goto L_0x0013;
        L_0x00bf:
            r0 = r10.e;
            r0 = r0.g;
            r0.b(r9);
            goto L_0x0013;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.c.d.onFling(android.view.MotionEvent, android.view.MotionEvent, float, float):boolean");
        }

        public void onLongPress(MotionEvent motionEvent) {
            this.e.bj = false;
            this.e.bi = this.e.d.a(motionEvent);
            if (this.e.Y != null && this.e.bi != null && this.e.bi.isDraggable()) {
                this.e.bh = new Marker(this.e.bi);
                LatLng position = this.e.bh.getPosition();
                LatLng realPosition = this.e.bi.getRealPosition();
                IPoint iPoint = new IPoint();
                this.e.getLatLng2Pixel(realPosition.latitude, realPosition.longitude, iPoint);
                iPoint.y -= 60;
                DPoint dPoint = new DPoint();
                this.e.getPixel2LatLng(iPoint.x, iPoint.y, dPoint);
                this.e.bh.setPosition(new LatLng((position.latitude + dPoint.y) - realPosition.latitude, (dPoint.x + position.longitude) - realPosition.longitude));
                this.e.d.d(this.e.bi);
                this.e.Y.onMarkerDragStart(this.e.bh);
                this.e.bg = true;
            } else if (this.e.ae != null) {
                DPoint dPoint2 = new DPoint();
                this.e.getPixel2LatLng((int) motionEvent.getX(), (int) motionEvent.getY(), dPoint2);
                this.e.ae.onMapLongClick(new LatLng(dPoint2.y, dPoint2.x));
                this.e.bn = true;
            }
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            this.e.bj = true;
            if ((!this.e.at.isFinished() && this.e.at.getMode() == 1) || this.e.ar.a() || motionEvent2.getEventTime() - this.e.ar.b() < 30) {
                this.e.bj = false;
            } else if (motionEvent2.getPointerCount() >= 2) {
                this.e.bj = false;
            } else {
                try {
                    if (!this.e.an.isScrollGesturesEnabled()) {
                        this.e.bj = false;
                    } else if (this.e.bm > 1) {
                        this.e.bj = false;
                    } else {
                        if (!(this.e.aj == null || this.e.ak == null || this.e.ak.isViewMode() || this.e.al == null)) {
                            this.e.al.setVisible(true);
                        }
                        IPoint iPoint = new IPoint();
                        this.e.getPixel2Geo((int) motionEvent2.getX(), (int) motionEvent2.getY(), iPoint);
                        int i = this.b.x - iPoint.x;
                        int i2 = this.b.y - iPoint.y;
                        IPoint iPoint2 = new IPoint();
                        this.e.J.getGeoCenter(iPoint2);
                        this.c.x = i + iPoint2.x;
                        this.c.y = i2 + iPoint2.y;
                        this.d.geoPoint = this.c;
                        this.e.e.a(this.d);
                    }
                } catch (Throwable th) {
                    ee.a(th, "AMapDelegateImpGLSurfaceView", "onScroll");
                    th.printStackTrace();
                }
            }
            return true;
        }

        public void onShowPress(MotionEvent motionEvent) {
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }
    }

    private class e implements com.amap.api.mapcore.util.z.a {
        final /* synthetic */ c a;

        private e(c cVar) {
            this.a = cVar;
        }

        public void a(int i) {
            if (this.a.aD != null) {
                this.a.aD.activeFloorIndex = this.a.aD.floor_indexs[i];
                this.a.aD.activeFloorName = this.a.aD.floor_names[i];
                try {
                    this.a.setIndoorBuildingInfo(this.a.aD);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class f implements com.amap.api.mapcore.util.be.a {
        float a;
        float b;
        IPoint c;
        CameraUpdateFactoryDelegate d;
        final /* synthetic */ c e;

        private f(c cVar) {
            this.e = cVar;
            this.a = 0.0f;
            this.b = 0.0f;
            this.c = new IPoint();
            this.d = CameraUpdateFactoryDelegate.newInstance();
        }

        public boolean a(be beVar) {
            if (this.e.be) {
                return false;
            }
            float b = beVar.b();
            this.a += b;
            if (!this.e.bk && Math.abs(this.a) <= 30.0f && Math.abs(this.a) <= 350.0f) {
                return true;
            }
            this.e.bk = true;
            this.b = b + this.e.J.getMapAngle();
            this.d.bearing = this.b;
            this.e.e.a(this.d);
            this.a = 0.0f;
            return true;
        }

        public boolean b(be beVar) {
            try {
                if (!this.e.an.isRotateGesturesEnabled()) {
                    return false;
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (this.e.br) {
                this.d.isUseAnchor = this.e.br;
                this.d.nowType = Type.changeBearingGeoCenter;
                this.e.getPixel2Geo(this.e.bs, this.e.bt, this.c);
                this.d.geoPoint = this.c;
            } else {
                this.d.nowType = Type.changeBearing;
            }
            this.e.bk = false;
            this.a = 0.0f;
            this.e.bm = 2;
            if (this.e.be || ((float) this.e.c()) / 8.0f >= beVar.c()) {
                return false;
            }
            return true;
        }

        public void c(be beVar) {
            this.a = 0.0f;
            if (this.e.bk) {
                this.e.bk = false;
                CameraUpdateFactoryDelegate newInstance = CameraUpdateFactoryDelegate.newInstance();
                newInstance.isChangeFinished = true;
                this.e.e.a(newInstance);
            }
            this.e.t();
        }
    }

    private class g implements OnScaleGestureListener {
        CameraUpdateFactoryDelegate a;
        final /* synthetic */ c b;
        private float c;
        private IPoint d;

        private g(c cVar) {
            this.b = cVar;
            this.c = 0.0f;
            this.d = new IPoint();
            this.a = CameraUpdateFactoryDelegate.newInstance();
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            if (!this.b.be) {
                float scaleFactor = scaleGestureDetector.getScaleFactor();
                if (this.b.bf || ((double) scaleFactor) > 1.08d || ((double) scaleFactor) < 0.92d) {
                    this.b.bf = true;
                    scaleFactor = (float) (Math.log((double) scaleFactor) / c.aG);
                    this.a.zoom = dj.a(scaleFactor + this.c);
                    this.b.e.a(this.a);
                }
            }
            return false;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            try {
                if (!this.b.an.isZoomGesturesEnabled() || this.b.bm < 2) {
                    return false;
                }
            } catch (Throwable e) {
                ee.a(e, "AMapDelegateImpGLSurfaceView", "onScaleBegin");
                e.printStackTrace();
            }
            this.b.bm = 2;
            if (this.b.be) {
                return false;
            }
            if (this.b.br) {
                this.a.isUseAnchor = this.b.br;
                this.a.nowType = Type.changeGeoCenterZoom;
                this.b.getPixel2Geo(this.b.bs, this.b.bt, this.d);
                this.a.geoPoint = this.d;
            } else {
                this.a.nowType = Type.zoomTo;
            }
            this.b.bf = false;
            this.c = this.b.J.getMapZoomer();
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            this.c = 0.0f;
            if (this.b.bf) {
                this.b.bf = false;
                CameraUpdateFactoryDelegate newInstance = CameraUpdateFactoryDelegate.newInstance();
                newInstance.isChangeFinished = true;
                this.b.e.a(newInstance);
            }
            this.b.t();
        }
    }

    private class h extends TimerTask {
        c a;
        final /* synthetic */ c b;

        public h(c cVar, c cVar2) {
            this.b = cVar;
            this.a = cVar2;
        }

        public void run() {
            if (!this.b.aZ || this.b.ba || !this.b.h.d()) {
                this.b.j.requestRender();
            } else if (!this.b.d.d()) {
                this.b.j.requestRender();
            }
        }
    }

    private class i implements Runnable {
        final /* synthetic */ c a;
        private Context b;
        private OnCacheRemoveListener c;

        public i(c cVar, Context context, OnCacheRemoveListener onCacheRemoveListener) {
            this.a = cVar;
            this.b = context;
            this.c = onCacheRemoveListener;
        }

        public void run() {
            Throwable th;
            boolean z;
            Throwable th2;
            boolean z2;
            try {
                boolean z3;
                Context applicationContext = this.b.getApplicationContext();
                String b = dj.b(applicationContext);
                String a = dj.a(applicationContext);
                boolean z4 = this.a.a(new File(b));
                if (z4) {
                    try {
                        if (this.a.a(new File(a))) {
                            z3 = true;
                            this.a.G.setParameter(2601, 1, 0, 0, 0);
                            if (this.c != null) {
                                this.c.onRemoveCacheFinish(z3);
                            }
                        }
                    } catch (Throwable th3) {
                        th2 = th3;
                        z2 = z4;
                        this.a.G.setParameter(2601, 1, 0, 0, 0);
                        if (this.c != null) {
                            this.c.onRemoveCacheFinish(z2);
                        }
                        throw th2;
                    }
                }
                z3 = false;
                try {
                    this.a.G.setParameter(2601, 1, 0, 0, 0);
                    if (this.c != null) {
                        this.c.onRemoveCacheFinish(z3);
                    }
                } catch (Throwable th32) {
                    th32.printStackTrace();
                }
            } catch (Throwable th4) {
                th2 = th4;
                z2 = true;
                this.a.G.setParameter(2601, 1, 0, 0, 0);
                if (this.c != null) {
                    this.c.onRemoveCacheFinish(z2);
                }
                throw th2;
            }
        }

        public boolean equals(Object obj) {
            return obj instanceof i;
        }
    }

    public /* synthetic */ IMarkerDelegate addMarker4Imp(MarkerOptions markerOptions) throws RemoteException {
        return a(markerOptions);
    }

    public MapCore getMapCore() {
        return this.G;
    }

    public int getLineTextureID() {
        return this.n;
    }

    public MapProjection getMapProjection() {
        if (this.J == null) {
            this.J = this.G.getMapstate();
        }
        return this.J;
    }

    public void a(GL10 gl10) {
        int i = 0;
        if (!this.aT) {
            int[] iArr = new int[500];
            this.A.clear();
            gl10.glGenTextures(500, iArr, 0);
            while (i < iArr.length) {
                this.A.add(Integer.valueOf(iArr[i]));
                i++;
            }
            this.aT = true;
        }
    }

    public c(IGLSurfaceView iGLSurfaceView, Context context) {
        this(iGLSurfaceView, context, null);
        this.H = context;
    }

    private c(IGLSurfaceView iGLSurfaceView, Context context, AttributeSet attributeSet) {
        this.n = -1;
        this.o = -1;
        this.p = 40;
        this.q = null;
        this.r = null;
        this.s = 221010267;
        this.t = 101697799;
        this.a = 10.0f;
        this.b = 0.0f;
        this.c = 0.0f;
        this.u = false;
        this.v = true;
        this.w = true;
        this.x = false;
        this.y = null;
        this.z = 1.0f;
        this.A = new CopyOnWriteArrayList();
        this.B = new CopyOnWriteArrayList();
        this.e = new ad(this);
        this.C = com.amap.api.mapcore.util.u.c.DAY;
        this.D = com.amap.api.mapcore.util.u.a.NORAML;
        this.E = com.amap.api.mapcore.util.u.b.NORMAL;
        this.F = 1;
        this.I = null;
        this.N = null;
        this.ap = new Rect();
        this.au = 0;
        this.av = 0;
        this.aw = null;
        this.ax = 0;
        this.ay = null;
        this.aA = null;
        this.aB = null;
        this.aC = new Handler();
        this.aD = null;
        this.aE = null;
        this.aH = true;
        this.aI = false;
        this.aJ = false;
        this.aK = false;
        this.aL = false;
        this.aM = true;
        this.aN = false;
        this.aO = false;
        this.aP = false;
        this.aQ = Boolean.valueOf(false);
        this.aR = false;
        this.aS = true;
        this.aT = false;
        this.aU = new Handler();
        this.h = null;
        this.i = null;
        this.j = null;
        this.aV = 0;
        this.aW = new s();
        this.aZ = false;
        this.ba = false;
        this.bb = new Handler();
        this.bc = new j(this);
        this.bd = false;
        this.be = false;
        this.bf = false;
        this.bg = false;
        this.bh = null;
        this.bi = null;
        this.bj = false;
        this.bk = false;
        this.bl = false;
        this.bm = 0;
        this.bn = false;
        this.bo = new d(this);
        this.bp = null;
        this.l = new e(this);
        this.bq = false;
        this.br = false;
        this.bu = new f(this);
        this.bv = new g(this);
        this.bw = new h(this);
        this.bx = new i(this);
        r.c = dl.c(context);
        this.j = iGLSurfaceView;
        this.H = context;
        this.an = new ax(this);
        this.G = new MapCore(this.H);
        this.I = new a(this);
        this.G.setMapCallback(this.I);
        iGLSurfaceView.setRenderer(this);
        l();
        this.i = new u(this, context);
        this.am = new aq(this);
        this.aq = new m(this);
        this.K = new GestureDetector(context, new d());
        this.K.setOnDoubleTapListener(new c());
        this.K.setIsLongpressEnabled(true);
        this.L = new ScaleGestureDetector(context, new g());
        this.M = new be(context, new f());
        this.ar = new bd(context, new b());
        this.O = new ah(this, context, this) {
            final /* synthetic */ c a;

            protected void a() {
                super.a();
                this.a.aC.removeCallbacks(this.a.bw);
                this.a.aC.post(this.a.bv);
            }
        };
        this.h = new v(this);
        this.P = new az(this.H, this);
        this.S = new ar(this.H, this);
        this.T = new o(this.H);
        this.U = new z(this.H);
        this.g = new aw(this.H, this);
        this.f = new ba(this.H, this);
        this.Q = new aa(this.H, this.e, this);
        this.R = new q(this.H, this.e, this);
        this.d = new ae(this.H, attributeSet, this);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.O.addView((View) this.j, 0, layoutParams);
        this.O.addView(this.T, 1, layoutParams);
        this.O.addView(this.d, new com.amap.api.mapcore.util.ah.a(layoutParams));
        this.O.addView(this.P, layoutParams);
        this.O.addView(this.S, layoutParams);
        this.O.addView(this.g, layoutParams);
        this.O.addView(this.U, new LayoutParams(-2, -2));
        this.U.a(new e());
        this.O.addView(this.f, new com.amap.api.mapcore.util.ah.a(-2, -2, new FPoint(0.0f, 0.0f), 0, 0, 83));
        this.O.addView(this.Q, new com.amap.api.mapcore.util.ah.a(-2, -2, new FPoint(0.0f, 0.0f), 0, 0, 83));
        try {
            if (!this.an.isMyLocationButtonEnabled()) {
                this.Q.setVisibility(8);
            }
        } catch (Throwable e) {
            ee.a(e, "AMapDelegateImpGLSurfaceView", "locationView gone");
            e.printStackTrace();
        }
        this.O.addView(this.R, new com.amap.api.mapcore.util.ah.a(-2, -2, new FPoint(0.0f, 0.0f), 0, 0, 51));
        this.R.setVisibility(8);
        this.at = new CameraAnimator(context);
        this.as = new aj(this, context);
        this.ai = new InfoWindowAdapter(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public View getInfoWindow(Marker marker) {
                return null;
            }

            public View getInfoContents(Marker marker) {
                return null;
            }
        };
        this.ah = this.ai;
    }

    public void addOverlay(GLOverlay gLOverlay) {
        gLOverlay.setMap(this);
        this.aW.a(gLOverlay);
    }

    public void removeOverlay(GLOverlay gLOverlay) {
        this.aW.b(gLOverlay);
    }

    public void clearGLOverlay() {
        this.aW.a();
    }

    public void setOnMyLocationChangeListener(OnMyLocationChangeListener onMyLocationChangeListener) {
        this.V = onMyLocationChangeListener;
    }

    public void onActivityResume() {
        this.aY = false;
    }

    public void onActivityPause() {
        this.aY = true;
    }

    public void onResume() {
        if (this.aV != 1) {
            this.aV = 1;
            this.aX = false;
            if (!this.aI) {
                queueEvent(new Runnable(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.l();
                        this.a.p();
                        if (this.a.I != null) {
                            this.a.I.onResume(this.a.G);
                            this.a.setRunLowFrame(false);
                        }
                        if (this.a.g != null) {
                            this.a.g.d();
                        }
                        if (this.a.as != null) {
                            this.a.as.a();
                        }
                    }
                });
            }
            if (this.j instanceof k) {
                ((k) this.j).onResume();
            } else {
                ((l) this.j).onResume();
            }
        }
    }

    public void onPause() {
        if (this.aV == 1) {
            this.aV = -1;
            this.aX = true;
            this.aL = false;
            if (this.T != null) {
                this.T.a(true);
            }
            if (this.I != null) {
                this.I.destoryMap(this.G);
            }
            q();
            IPoint iPoint = new IPoint();
            this.J.recalculate();
            this.J.getGeoCenter(iPoint);
            this.s = iPoint.x;
            this.t = iPoint.y;
            this.a = this.J.getMapZoomer();
            this.c = this.J.getMapAngle();
            this.b = this.J.getCameraHeaderAngle();
            if (this.j instanceof k) {
                ((k) this.j).onPause();
            } else {
                ((l) this.j).onPause();
            }
            m();
        }
    }

    private void l() {
        if (!this.aI) {
            this.G.newMap();
            this.I.onResume(this.G);
            this.J = this.G.getMapstate();
            this.J.setGeoCenter(this.s, this.t);
            this.J.setMapAngle(this.c);
            this.J.setMapZoomer(this.a);
            this.J.setCameraHeaderAngle(this.b);
            this.G.setMapstate(this.J);
            this.aI = true;
            n();
            this.j.setRenderMode(0);
        }
    }

    private void m() {
        queueEvent(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.aI) {
                    this.a.C = com.amap.api.mapcore.util.u.c.DAY;
                    this.a.D = com.amap.api.mapcore.util.u.a.NORAML;
                    this.a.E = com.amap.api.mapcore.util.u.b.NORMAL;
                    try {
                        this.a.G.destroy();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    VMapDataCache.getInstance().reset();
                    this.a.aI = false;
                }
            }
        });
    }

    private void n() {
        try {
            setIndoorEnabled(this.u);
            set3DBuildingEnabled(this.v);
            setMapTextEnable(this.w);
            setTrafficEnabled(this.x);
            setMyTrafficStyle(this.y);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setMyLocationStyle(MyLocationStyle myLocationStyle) {
        if (this.as != null) {
            this.as.a(myLocationStyle);
        }
    }

    public void setMyLocationType(int i) {
        if (this.as != null) {
            this.as.a(i);
        }
    }

    public void setMyLocationRotateAngle(float f) throws RemoteException {
        if (this.as != null) {
            this.as.a(f);
        }
    }

    public void showMyLocationOverlay(Location location) throws RemoteException {
        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            try {
                if (!this.aH || this.ao == null) {
                    this.as.b();
                    this.as = null;
                    return;
                }
                if (this.as == null || this.az == null) {
                    if (this.as == null) {
                        this.as = new aj(this, this.H);
                    }
                    moveCamera(CameraUpdateFactoryDelegate.newLatLngZoom(latLng, this.J.getMapZoomer()));
                }
                this.as.a(location);
                if (!(this.V == null || (this.az != null && this.az.getBearing() == location.getBearing() && this.az.getAccuracy() == location.getAccuracy() && this.az.getLatitude() == location.getLatitude() && this.az.getLongitude() == location.getLongitude()))) {
                    this.V.onMyLocationChange(location);
                }
                this.az = new Location(location);
                setRunLowFrame(false);
            } catch (Throwable e) {
                ee.a(e, "AMapDelegateImpGLSurfaceView", "showMyLocationOverlay");
                e.printStackTrace();
            }
        }
    }

    public void showZoomControlsEnabled(boolean z) {
        if (this.f != null) {
            this.f.a(z);
        }
    }

    public void showIndoorSwitchControlsEnabled(boolean z) {
        if (this.U != null && z && o()) {
            this.U.a(true);
        }
    }

    private boolean o() {
        if (!(this.J.getMapZoomer() < 17.0f || this.aD == null || this.aD.geoCenter == null)) {
            IPoint iPoint = new IPoint();
            a(this.aD.geoCenter.x, this.aD.geoCenter.y, iPoint);
            if (this.ap.contains(iPoint.x, iPoint.y)) {
                return true;
            }
        }
        return false;
    }

    public void destroy() {
        this.aQ = Boolean.valueOf(true);
        try {
            q();
            if (this.r != null) {
                this.r.recycle();
                this.r = null;
            }
            if (this.q != null) {
                this.q.recycle();
                this.q = null;
            }
            if (!(this.l == null || this.k == null)) {
                this.l.removeCallbacks(this.k);
                this.k = null;
            }
            if (this.bb != null) {
                this.bb.removeCallbacks(this.bc);
            }
            if (this.f != null) {
                this.f.a();
            }
            if (this.S != null) {
                this.S.a();
            }
            if (this.P != null) {
                this.P.a();
            }
            if (this.Q != null) {
                this.Q.a();
            }
            if (this.R != null) {
                this.R.a();
            }
            if (this.g != null) {
                this.g.b();
                this.g.e();
            }
            if (this.h != null) {
                this.h.a();
            }
            if (this.d != null) {
                this.d.f();
            }
            if (this.U != null) {
                this.U.b();
            }
            if (this.bo != null) {
                this.bo.interrupt();
                this.bo = null;
            }
            if (this.I != null) {
                this.I.OnMapDestory(this.G);
                this.G.setMapCallback(null);
                this.I = null;
            }
            hiddenInfoWindowShown();
            dj.a(this.ay);
            if (this.A != null) {
                this.A.clear();
            }
            if (this.B != null) {
                this.B.clear();
            }
            if (this.G != null) {
                queueEvent(new Runnable(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        try {
                            this.a.G.destroy();
                            this.a.G = null;
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
                Thread.sleep(200);
            }
            if (this.O != null) {
                this.O.removeAllViews();
                this.O = null;
            }
            this.ao = null;
            this.ab = null;
            this.y = null;
            ee.b();
        } catch (Throwable th) {
            ee.a(th, "AMapDelegateImpGLSurfaceView", "destroy");
            th.printStackTrace();
        }
    }

    public void showMyLocationButtonEnabled(boolean z) {
        if (this.Q != null) {
            if (z) {
                this.Q.setVisibility(0);
            } else {
                this.Q.setVisibility(8);
            }
        }
    }

    public void showCompassEnabled(boolean z) {
        if (this.R != null) {
            this.R.a(z);
        }
    }

    void a() {
        this.l.obtainMessage(14).sendToTarget();
    }

    public void showScaleEnabled(boolean z) {
        if (this.S != null) {
            this.S.a(z);
        }
    }

    void b() {
        this.l.post(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.S.b();
            }
        });
    }

    public boolean removeGLOverlay(String str) throws RemoteException {
        setRunLowFrame(false);
        return this.h.c(str);
    }

    public synchronized void setRunLowFrame(boolean z) {
        if (!z) {
            this.ba = false;
            this.bb.removeCallbacks(this.bc);
            this.aZ = false;
        } else if (!(this.aZ || this.ba)) {
            this.ba = true;
            this.bb.postDelayed(this.bc, ToolTipPopup.a);
        }
    }

    public void onDrawFrame(GL10 gl10) {
        int i = 1;
        try {
            if (this.aI) {
                gl10.glColor4f(1.0f, 1.0f, 1.0f, dji.pilot.visual.a.d.c);
                gl10.glClear(16640);
                this.G.setGL(gl10);
                this.G.drawFrame(gl10);
                a(gl10);
                this.g.a(gl10);
                this.h.a(gl10, false, this.ax);
                this.d.a(gl10);
                this.aW.a(gl10);
                if (this.al != null) {
                    this.al.a(gl10);
                }
                if (this.aR) {
                    if (!this.G.canStopRenderMap()) {
                        i = 0;
                    }
                    Message obtainMessage = this.l.obtainMessage(16, a(0, 0, c(), d(), gl10));
                    obtainMessage.arg1 = i;
                    obtainMessage.sendToTarget();
                    this.aR = false;
                }
                if (!this.at.isFinished()) {
                    this.l.sendEmptyMessage(13);
                }
                if (this.T != null) {
                    i = this.T.getVisibility();
                    o oVar = this.T;
                    if (i != 8) {
                        if (!this.aJ) {
                            this.l.sendEmptyMessage(11);
                            this.aJ = true;
                        }
                        this.aL = true;
                        this.l.post(new Runnable(this) {
                            final /* synthetic */ c a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                if (!this.a.aX) {
                                    try {
                                        this.a.setMapType(this.a.F);
                                        if (this.a.aD != null) {
                                            this.a.setIndoorBuildingInfo(this.a.aD);
                                        }
                                    } catch (RemoteException e) {
                                        e.printStackTrace();
                                    }
                                    this.a.T.a(false);
                                }
                            }
                        });
                        return;
                    }
                    return;
                }
                return;
            }
            gl10.glClearColor(0.9453125f, 0.93359f, 0.9101f, 1.0f);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public Rect getRect() {
        return this.ap;
    }

    public int getMapWidth() {
        return this.ap.width();
    }

    public int getMapHeight() {
        return this.ap.height();
    }

    public int c() {
        return this.j.getWidth();
    }

    public int d() {
        return this.j.getHeight();
    }

    public void setMyTrafficStyle(MyTrafficStyle myTrafficStyle) {
        if (this.aI && myTrafficStyle != null) {
            this.y = myTrafficStyle;
            this.G.setParameter(2201, 1, 1, 1, 1);
            this.G.setParameter(2202, myTrafficStyle.getSmoothColor(), myTrafficStyle.getSlowColor(), myTrafficStyle.getCongestedColor(), myTrafficStyle.getSeriousCongestedColor());
        }
    }

    private synchronized void p() {
        if (this.aF != null) {
            q();
        }
        if (this.aF == null) {
            this.aF = new Timer();
        }
        this.aF.schedule(new h(this, this), 0, (long) (1000 / this.p));
    }

    private synchronized void q() {
        if (this.aF != null) {
            this.aF.cancel();
            this.aF = null;
        }
    }

    private synchronized void r() {
        try {
            if (!this.bd) {
                this.i.a();
                this.i.a(true);
                this.i.b(true);
                this.i.e(true);
                this.i.d(true);
                this.i.c(true);
                this.bd = true;
            }
        } catch (Throwable th) {
            ee.a(th, "AMapDelegateImpGLSurfaceView", "setInternaltexture");
            th.printStackTrace();
        }
    }

    public int getImaginaryLineTextureID() {
        return this.o;
    }

    public void redrawInfoWindow() {
        try {
            if (this.aS && this.aj != null && this.ak != null) {
                com.amap.api.mapcore.util.ah.a aVar = (com.amap.api.mapcore.util.ah.a) this.aj.getLayoutParams();
                if (aVar != null) {
                    this.ak.getRect();
                    int realInfoWindowOffsetX = this.ak.getRealInfoWindowOffsetX() + this.ak.getInfoWindowOffsetX();
                    int realInfoWindowOffsetY = (this.ak.getRealInfoWindowOffsetY() + this.ak.getInfoWindowOffsetY()) + 2;
                    aVar.a = this.ak.getMapPosition();
                    aVar.b = realInfoWindowOffsetX;
                    aVar.c = realInfoWindowOffsetY;
                    if (this.al != null) {
                        this.al.a(this.ak.getMapPosition());
                        this.al.setInfoWindowOffset(realInfoWindowOffsetX, realInfoWindowOffsetY);
                    }
                }
                this.O.onLayout(false, 0, 0, 0, 0);
                setRunLowFrame(false);
            }
        } catch (Throwable th) {
            ee.a(th, "AMapDelegateImpGLSurfaceView", "redrawInfoWindow");
            th.printStackTrace();
        }
    }

    public void setZOrderOnTop(boolean z) {
        this.j.setZOrderOnTop(z);
    }

    public CameraPosition getCameraPosition() throws RemoteException {
        return getCameraPositionPrj(this.br);
    }

    public float getMaxZoomLevel() {
        return r.f;
    }

    public float getMinZoomLevel() {
        return 3.0f;
    }

    public void moveCamera(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) throws RemoteException {
        if (cameraUpdateFactoryDelegate.nowType == Type.newLatLngBounds) {
            boolean z = c() > 0 && d() > 0;
            cu.a(z, (Object) "the map must have a size");
        }
        if ((this.aX || this.aY) && this.e.d() > 0) {
            CameraUpdateFactoryDelegate newInstance = CameraUpdateFactoryDelegate.newInstance();
            newInstance.nowType = Type.changeGeoCenterZoomTiltBearing;
            newInstance.geoPoint = new IPoint(this.s, this.t);
            newInstance.zoom = this.a;
            newInstance.bearing = this.c;
            newInstance.tilt = this.b;
            this.e.a(cameraUpdateFactoryDelegate);
            while (this.e.d() > 0) {
                CameraUpdateFactoryDelegate c = this.e.c();
                if (c != null) {
                    if (c.cameraPosition != null) {
                        float f;
                        CameraPosition cameraPosition = c.cameraPosition;
                        if (cameraPosition.target != null) {
                            IPoint iPoint = new IPoint();
                            MapProjection.lonlat2Geo(cameraPosition.target.longitude, cameraPosition.target.latitude, iPoint);
                            newInstance.geoPoint = iPoint;
                        }
                        newInstance.zoom = cameraPosition.zoom == 0.0f ? newInstance.zoom : cameraPosition.zoom;
                        newInstance.bearing = cameraPosition.bearing == 0.0f ? newInstance.bearing : cameraPosition.bearing;
                        if (cameraPosition.tilt == 0.0f) {
                            f = newInstance.tilt;
                        } else {
                            f = cameraPosition.tilt;
                        }
                        newInstance.tilt = f;
                    } else if (c.nowType.equals(Type.zoomIn)) {
                        newInstance.zoom += 1.0f;
                    } else if (c.nowType.equals(Type.zoomOut)) {
                        newInstance.zoom -= 1.0f;
                    } else if (c.nowType.equals(Type.zoomBy)) {
                        newInstance.zoom += newInstance.amount;
                    } else {
                        int i;
                        newInstance.geoPoint = c.geoPoint == null ? newInstance.geoPoint : c.geoPoint;
                        newInstance.zoom = c.zoom == 0.0f ? newInstance.zoom : c.zoom;
                        newInstance.bearing = c.bearing == 0.0f ? newInstance.bearing : c.bearing;
                        newInstance.tilt = c.tilt == 0.0f ? newInstance.tilt : c.tilt;
                        newInstance.xPixel = c.xPixel == 0.0f ? newInstance.xPixel : c.xPixel;
                        newInstance.yPixel = c.yPixel == 0.0f ? newInstance.yPixel : c.yPixel;
                        newInstance.width = c.width == 0 ? newInstance.width : c.width;
                        if (c.height == 0) {
                            i = newInstance.height;
                        } else {
                            i = c.height;
                        }
                        newInstance.height = i;
                    }
                    newInstance.zoom = dj.a(newInstance.zoom);
                    newInstance.tilt = dj.a(newInstance.tilt, newInstance.zoom);
                }
            }
            cameraUpdateFactoryDelegate = newInstance;
        }
        stopAnimation();
        cameraUpdateFactoryDelegate.isChangeFinished = true;
        cameraUpdateFactoryDelegate.isUseAnchor = this.br;
        this.e.a(cameraUpdateFactoryDelegate);
    }

    public void animateCamera(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) throws RemoteException {
        animateCameraWithCallback(cameraUpdateFactoryDelegate, null);
    }

    public void animateCameraWithCallback(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate, CancelableCallback cancelableCallback) throws RemoteException {
        animateCameraWithDurationAndCallback(cameraUpdateFactoryDelegate, 250, cancelableCallback);
    }

    public void animateCameraWithDurationAndCallback(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate, long j, CancelableCallback cancelableCallback) throws RemoteException {
        if (this.aX || this.aY) {
            moveCamera(cameraUpdateFactoryDelegate);
            return;
        }
        if (cameraUpdateFactoryDelegate.nowType == Type.newLatLngBounds) {
            boolean z = c() > 0 && d() > 0;
            cu.a(z, (Object) "the map must have a size");
        }
        if (!this.at.isFinished()) {
            this.at.forceFinished(true);
            if (this.aw != null) {
                this.aw.onCancel();
            }
        }
        this.at.setUseAnchor(this.br);
        this.aw = cancelableCallback;
        if (this.aO) {
            this.aP = true;
        }
        this.aN = false;
        IPoint iPoint;
        if (cameraUpdateFactoryDelegate.nowType == Type.scrollBy) {
            if (cameraUpdateFactoryDelegate.xPixel == 0.0f && cameraUpdateFactoryDelegate.yPixel == 0.0f) {
                this.l.obtainMessage(17).sendToTarget();
                return;
            }
            this.at.setUseAnchor(false);
            iPoint = new IPoint();
            this.J.getGeoCenter(iPoint);
            IPoint iPoint2 = new IPoint();
            getPixel2Geo((c() / 2) + ((int) cameraUpdateFactoryDelegate.xPixel), (d() / 2) + ((int) cameraUpdateFactoryDelegate.yPixel), iPoint2);
            this.at.setInterpolator(new AccelerateDecelerateInterpolator());
            this.at.startChangeCamera(iPoint.x, iPoint.y, this.J.getMapZoomer(), this.J.getMapAngle(), this.J.getCameraHeaderAngle(), iPoint2.x - iPoint.x, iPoint2.y - iPoint.y, 0.0f, 0.0f, 0.0f, j);
        } else if (cameraUpdateFactoryDelegate.nowType == Type.zoomIn) {
            r6 = this.J.getMapZoomer();
            r11 = dj.a(1.0f + r6) - r6;
            if (r11 == 0.0f) {
                this.l.obtainMessage(17).sendToTarget();
                return;
            }
            iPoint = new IPoint();
            if (this.br) {
                getPixel2Geo(this.bs, this.bt, iPoint);
            } else {
                this.J.getGeoCenter(iPoint);
            }
            this.at.setInterpolator(new AccelerateInterpolator());
            this.at.startChangeCamera(iPoint.x, iPoint.y, r6, this.J.getMapAngle(), this.J.getCameraHeaderAngle(), 0, 0, r11, 0.0f, 0.0f, j);
        } else if (cameraUpdateFactoryDelegate.nowType == Type.zoomOut) {
            r6 = this.J.getMapZoomer();
            r11 = dj.a(r6 - 1.0f) - r6;
            if (r11 == 0.0f) {
                this.l.obtainMessage(17).sendToTarget();
                return;
            }
            iPoint = new IPoint();
            if (this.br) {
                getPixel2Geo(this.bs, this.bt, iPoint);
            } else {
                this.J.getGeoCenter(iPoint);
            }
            this.at.setInterpolator(new AccelerateInterpolator());
            this.at.startChangeCamera(iPoint.x, iPoint.y, r6, this.J.getMapAngle(), this.J.getCameraHeaderAngle(), 0, 0, r11, 0.0f, 0.0f, j);
        } else if (cameraUpdateFactoryDelegate.nowType == Type.zoomTo) {
            r6 = this.J.getMapZoomer();
            r11 = dj.a(cameraUpdateFactoryDelegate.zoom) - r6;
            if (r11 == 0.0f) {
                this.l.obtainMessage(17).sendToTarget();
                return;
            }
            iPoint = new IPoint();
            if (this.br) {
                getPixel2Geo(this.bs, this.bt, iPoint);
            } else {
                this.J.getGeoCenter(iPoint);
            }
            this.at.setInterpolator(new AccelerateInterpolator());
            this.at.startChangeCamera(iPoint.x, iPoint.y, r6, this.J.getMapAngle(), this.J.getCameraHeaderAngle(), 0, 0, r11, 0.0f, 0.0f, j);
        } else if (cameraUpdateFactoryDelegate.nowType == Type.zoomBy) {
            this.at.setUseAnchor(false);
            float f = cameraUpdateFactoryDelegate.amount;
            r6 = this.J.getMapZoomer();
            r11 = dj.a(r6 + f) - r6;
            if (r11 == 0.0f) {
                this.l.obtainMessage(17).sendToTarget();
                return;
            }
            Point point = cameraUpdateFactoryDelegate.focus;
            IPoint iPoint3 = new IPoint();
            this.J.getGeoCenter(iPoint3);
            r9 = 0;
            r10 = 0;
            IPoint iPoint4 = new IPoint();
            int i;
            if (point != null) {
                getPixel2Geo(point.x, point.y, iPoint4);
                r3 = iPoint3.x - iPoint4.x;
                i = iPoint3.y - iPoint4.y;
                r9 = (int) ((((double) r3) / Math.pow(2.0d, (double) f)) - ((double) r3));
                r10 = (int) ((((double) i) / Math.pow(2.0d, (double) f)) - ((double) i));
            } else if (this.br) {
                getPixel2Geo(this.bs, this.bt, iPoint4);
                r3 = iPoint3.x - iPoint4.x;
                i = iPoint3.y - iPoint4.y;
                r9 = (int) ((((double) r3) / Math.pow(2.0d, (double) f)) - ((double) r3));
                r10 = (int) ((((double) i) / Math.pow(2.0d, (double) f)) - ((double) i));
            }
            this.at.setInterpolator(new AccelerateInterpolator());
            this.at.startChangeCamera(iPoint3.x, iPoint3.y, r6, this.J.getMapAngle(), this.J.getCameraHeaderAngle(), r9, r10, r11, 0.0f, 0.0f, j);
        } else if (cameraUpdateFactoryDelegate.nowType == Type.newCameraPosition) {
            iPoint = new IPoint();
            if (this.br) {
                getPixel2Geo(this.bs, this.bt, iPoint);
            } else {
                this.J.getGeoCenter(iPoint);
            }
            r3 = new IPoint();
            CameraPosition cameraPosition = cameraUpdateFactoryDelegate.cameraPosition;
            MapProjection.lonlat2Geo(cameraPosition.target.longitude, cameraPosition.target.latitude, r3);
            r6 = this.J.getMapZoomer();
            r9 = r3.x - iPoint.x;
            r10 = r3.y - iPoint.y;
            r11 = dj.a(cameraPosition.zoom) - r6;
            r7 = this.J.getMapAngle();
            r12 = (cameraPosition.bearing % 360.0f) - (r7 % 360.0f);
            if (Math.abs(r12) >= 180.0f) {
                r12 -= Math.signum(r12) * 360.0f;
            }
            r8 = this.J.getCameraHeaderAngle();
            r13 = dj.a(cameraPosition.tilt, cameraPosition.zoom) - r8;
            if (r9 == 0 && r10 == 0 && r11 == 0.0f && r12 == 0.0f && r13 == 0.0f) {
                this.l.obtainMessage(17).sendToTarget();
                return;
            } else {
                this.at.setInterpolator(new AccelerateInterpolator());
                this.at.startChangeCamera(iPoint.x, iPoint.y, r6, r7, r8, r9, r10, r11, r12, r13, j);
            }
        } else if (cameraUpdateFactoryDelegate.nowType == Type.changeBearing) {
            r7 = this.J.getMapAngle();
            r12 = (cameraUpdateFactoryDelegate.bearing % 360.0f) - (r7 % 360.0f);
            if (Math.abs(r12) >= 180.0f) {
                r12 -= Math.signum(r12) * 360.0f;
            }
            if (r12 == 0.0f) {
                this.l.obtainMessage(17).sendToTarget();
                return;
            }
            iPoint = new IPoint();
            if (this.br) {
                getPixel2Geo(this.bs, this.bt, iPoint);
            } else {
                this.J.getGeoCenter(iPoint);
            }
            this.at.setInterpolator(new AccelerateInterpolator());
            this.at.startChangeCamera(iPoint.x, iPoint.y, this.J.getMapZoomer(), r7, this.J.getCameraHeaderAngle(), 0, 0, 0.0f, r12, 0.0f, j);
        } else if (cameraUpdateFactoryDelegate.nowType == Type.changeTilt) {
            r8 = this.J.getCameraHeaderAngle();
            r13 = cameraUpdateFactoryDelegate.tilt - r8;
            if (r13 == 0.0f) {
                this.l.obtainMessage(17).sendToTarget();
                return;
            }
            iPoint = new IPoint();
            if (this.br) {
                getPixel2Geo(this.bs, this.bt, iPoint);
            } else {
                this.J.getGeoCenter(iPoint);
            }
            this.at.setInterpolator(new AccelerateInterpolator());
            this.at.startChangeCamera(iPoint.x, iPoint.y, this.J.getMapZoomer(), this.J.getMapAngle(), r8, 0, 0, 0.0f, 0.0f, r13, j);
        } else if (cameraUpdateFactoryDelegate.nowType == Type.changeCenter) {
            iPoint = new IPoint();
            if (this.br) {
                getPixel2Geo(this.bs, this.bt, iPoint);
            } else {
                this.J.getGeoCenter(iPoint);
            }
            r9 = cameraUpdateFactoryDelegate.geoPoint.x - iPoint.x;
            r10 = cameraUpdateFactoryDelegate.geoPoint.y - iPoint.y;
            if (r9 == 0 && r10 == 0) {
                this.l.obtainMessage(17).sendToTarget();
                return;
            } else {
                this.at.setInterpolator(new AccelerateDecelerateInterpolator());
                this.at.startChangeCamera(iPoint.x, iPoint.y, this.J.getMapZoomer(), this.J.getMapAngle(), this.J.getCameraHeaderAngle(), r9, r10, 0.0f, 0.0f, 0.0f, j);
            }
        } else if (cameraUpdateFactoryDelegate.nowType == Type.newLatLngBounds || cameraUpdateFactoryDelegate.nowType == Type.newLatLngBoundsWithSize) {
            int i2;
            this.at.setUseAnchor(false);
            if (cameraUpdateFactoryDelegate.nowType == Type.newLatLngBounds) {
                r3 = c();
                r9 = d();
                i2 = r3;
            } else {
                r3 = cameraUpdateFactoryDelegate.width;
                r9 = cameraUpdateFactoryDelegate.height;
                i2 = r3;
            }
            float mapAngle = this.J.getMapAngle() % 360.0f;
            float cameraHeaderAngle = this.J.getCameraHeaderAngle();
            r12 = -mapAngle;
            if (Math.abs(r12) >= 180.0f) {
                r12 -= Math.signum(r12) * 360.0f;
            }
            r13 = -cameraHeaderAngle;
            LatLngBounds latLngBounds = cameraUpdateFactoryDelegate.bounds;
            int i3 = cameraUpdateFactoryDelegate.padding;
            IPoint iPoint5 = new IPoint();
            this.J.getGeoCenter(iPoint5);
            float mapZoomer = this.J.getMapZoomer();
            this.at.setInterpolator(new AccelerateInterpolator());
            iPoint = new IPoint();
            r3 = new IPoint();
            MapProjection.lonlat2Geo(latLngBounds.northeast.longitude, latLngBounds.northeast.latitude, iPoint);
            MapProjection.lonlat2Geo(latLngBounds.southwest.longitude, latLngBounds.southwest.latitude, r3);
            r10 = iPoint.x - r3.x;
            int i4 = r3.y - iPoint.y;
            if (r10 > 0 || i4 > 0) {
                int i5 = (iPoint.x + r3.x) / 2;
                int i6 = (iPoint.y + r3.y) / 2;
                IPoint iPoint6 = new IPoint();
                getLatLng2Pixel((latLngBounds.northeast.latitude + latLngBounds.southwest.latitude) / 2.0d, (latLngBounds.northeast.longitude + latLngBounds.southwest.longitude) / 2.0d, iPoint6);
                int i7;
                if ((!this.ap.contains(iPoint6.x, iPoint6.y) ? 1 : null) == null) {
                    r3 = i2 - (i3 * 2);
                    i7 = r9 - (i3 * 2);
                    if (r3 <= 0) {
                        r3 = 1;
                    }
                    if (i7 <= 0) {
                        i7 = 1;
                    }
                    r11 = dj.a((float) ((int) (Math.min(Math.log(((double) this.J.getMapLenWithWin(r3)) / ((double) this.J.getMapLenWithGeo(r10))) / Math.log(2.0d), Math.log(((double) this.J.getMapLenWithWin(i7)) / ((double) this.J.getMapLenWithGeo(i4))) / Math.log(2.0d)) + ((double) mapZoomer)))) - mapZoomer;
                    r9 = i5 - iPoint5.x;
                    r10 = i6 - iPoint5.y;
                    if (r9 == 0 && r10 == 0 && r11 == 0.0f) {
                        this.l.obtainMessage(17).sendToTarget();
                        return;
                    } else {
                        this.at.setInterpolator(new DecelerateInterpolator());
                        this.at.startChangeCamera(iPoint5.x, iPoint5.y, mapZoomer, mapAngle, cameraHeaderAngle, r9, r10, r11, r12, r13, j);
                    }
                } else {
                    final CancelableCallback cancelableCallback2 = this.aw;
                    final LatLngBounds latLngBounds2 = latLngBounds;
                    final int i8 = i2;
                    final int i9 = r9;
                    final int i10 = i3;
                    final long j2 = j;
                    this.aw = new CancelableCallback(this) {
                        final /* synthetic */ c g;

                        public void onFinish() {
                            try {
                                this.g.animateCameraWithDurationAndCallback(CameraUpdateFactoryDelegate.newLatLngBoundsWithSize(latLngBounds2, i8, i9, i10), j2, cancelableCallback2);
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }

                        public void onCancel() {
                            if (cancelableCallback2 != null) {
                                cancelableCallback2.onCancel();
                            }
                        }
                    };
                    i4 = ((iPoint5.x + i5) / 2) - iPoint5.x;
                    r10 = ((iPoint5.y + i6) / 2) - iPoint5.y;
                    i7 = (int) dj.a((double) (((float) getMapWidth()) / 2.0f), (double) (((float) getMapHeight()) / 2.0f), (double) Math.abs(i5 - iPoint5.x), (double) Math.abs(i6 - iPoint5.y));
                    r11 = i7 == 0 ? 0.0f : ((float) i7) - mapZoomer;
                    if (r11 >= 0.0f) {
                        r11 = 0.0f;
                    }
                    this.aN = true;
                    this.at.startChangeCamera(iPoint5.x, iPoint5.y, mapZoomer, mapAngle, cameraHeaderAngle, i4, r10, r11, r12 / 2.0f, r13 / 2.0f, j / 2);
                }
            } else {
                this.l.obtainMessage(17).sendToTarget();
                return;
            }
        } else {
            cameraUpdateFactoryDelegate.isChangeFinished = true;
            this.e.a(cameraUpdateFactoryDelegate);
        }
        setRunLowFrame(false);
    }

    public void stopAnimation() throws RemoteException {
        if (!this.at.isFinished()) {
            this.at.forceFinished(true);
            a(true, null);
            if (this.aw != null) {
                this.aw.onCancel();
            }
            if (!(this.aj == null || this.al == null)) {
                this.aj.setVisibility(0);
            }
            this.aw = null;
        }
        setRunLowFrame(false);
    }

    public IPolylineDelegate addPolyline(PolylineOptions polylineOptions) throws RemoteException {
        if (polylineOptions == null) {
            return null;
        }
        IOverlayDelegate aoVar = new ao(this.h);
        aoVar.setColor(polylineOptions.getColor());
        aoVar.setGeodesic(polylineOptions.isGeodesic());
        aoVar.setDottedLine(polylineOptions.isDottedLine());
        aoVar.setPoints(polylineOptions.getPoints());
        aoVar.setVisible(polylineOptions.isVisible());
        aoVar.setWidth(polylineOptions.getWidth());
        aoVar.setZIndex(polylineOptions.getZIndex());
        aoVar.a(polylineOptions.isUseTexture());
        if (polylineOptions.getColorValues() != null) {
            aoVar.setColorValues(polylineOptions.getColorValues());
            aoVar.useGradient(polylineOptions.isUseGradient());
        }
        if (polylineOptions.getCustomTexture() != null) {
            aoVar.a(polylineOptions.getCustomTexture());
        }
        if (polylineOptions.getCustomTextureList() != null) {
            aoVar.setCustomTextureList(polylineOptions.getCustomTextureList());
            aoVar.setCustemTextureIndex(polylineOptions.getCustomTextureIndex());
        }
        this.h.a(aoVar);
        setRunLowFrame(false);
        return aoVar;
    }

    public INavigateArrowDelegate addNavigateArrow(NavigateArrowOptions navigateArrowOptions) throws RemoteException {
        if (navigateArrowOptions == null) {
            return null;
        }
        IOverlayDelegate akVar = new ak(this);
        akVar.setTopColor(navigateArrowOptions.getTopColor());
        akVar.setPoints(navigateArrowOptions.getPoints());
        akVar.setVisible(navigateArrowOptions.isVisible());
        akVar.setWidth(navigateArrowOptions.getWidth());
        akVar.setZIndex(navigateArrowOptions.getZIndex());
        this.h.a(akVar);
        setRunLowFrame(false);
        return akVar;
    }

    public IPolygonDelegate addPolygon(PolygonOptions polygonOptions) throws RemoteException {
        if (polygonOptions == null) {
            return null;
        }
        IOverlayDelegate anVar = new an(this);
        anVar.setFillColor(polygonOptions.getFillColor());
        anVar.setPoints(polygonOptions.getPoints());
        anVar.setVisible(polygonOptions.isVisible());
        anVar.setStrokeWidth(polygonOptions.getStrokeWidth());
        anVar.setZIndex(polygonOptions.getZIndex());
        anVar.setStrokeColor(polygonOptions.getStrokeColor());
        this.h.a(anVar);
        setRunLowFrame(false);
        return anVar;
    }

    public ICircleDelegate addCircle(CircleOptions circleOptions) throws RemoteException {
        if (circleOptions == null) {
            return null;
        }
        IOverlayDelegate pVar = new p(this);
        pVar.setFillColor(circleOptions.getFillColor());
        pVar.setCenter(circleOptions.getCenter());
        pVar.setVisible(circleOptions.isVisible());
        pVar.setStrokeWidth(circleOptions.getStrokeWidth());
        pVar.setZIndex(circleOptions.getZIndex());
        pVar.setStrokeColor(circleOptions.getStrokeColor());
        pVar.setRadius(circleOptions.getRadius());
        this.h.a(pVar);
        setRunLowFrame(false);
        return pVar;
    }

    public IArcDelegate addArc(ArcOptions arcOptions) throws RemoteException {
        if (arcOptions == null) {
            return null;
        }
        IOverlayDelegate nVar = new n(this);
        nVar.setStrokeColor(arcOptions.getStrokeColor());
        nVar.setStart(arcOptions.getStart());
        nVar.setPassed(arcOptions.getPassed());
        nVar.setEnd(arcOptions.getEnd());
        nVar.setVisible(arcOptions.isVisible());
        nVar.setStrokeWidth(arcOptions.getStrokeWidth());
        nVar.setZIndex(arcOptions.getZIndex());
        this.h.a(nVar);
        setRunLowFrame(false);
        return nVar;
    }

    public IGroundOverlayDelegate addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
        if (groundOverlayOptions == null) {
            return null;
        }
        IOverlayDelegate xVar = new x(this);
        xVar.setAnchor(groundOverlayOptions.getAnchorU(), groundOverlayOptions.getAnchorV());
        xVar.setDimensions(groundOverlayOptions.getWidth(), groundOverlayOptions.getHeight());
        xVar.setImage(groundOverlayOptions.getImage());
        xVar.setPosition(groundOverlayOptions.getLocation());
        xVar.setPositionFromBounds(groundOverlayOptions.getBounds());
        xVar.setBearing(groundOverlayOptions.getBearing());
        xVar.setTransparency(groundOverlayOptions.getTransparency());
        xVar.setVisible(groundOverlayOptions.isVisible());
        xVar.setZIndex(groundOverlayOptions.getZIndex());
        this.h.a(xVar);
        setRunLowFrame(false);
        return xVar;
    }

    public Marker addMarker(MarkerOptions markerOptions) throws RemoteException {
        if (markerOptions == null) {
            return null;
        }
        IMarkerDelegate aiVar = new ai(markerOptions, this.d);
        this.d.b(aiVar);
        setRunLowFrame(false);
        return new Marker(aiVar);
    }

    public Text addText(TextOptions textOptions) throws RemoteException {
        if (textOptions == null) {
            return null;
        }
        IMarkerDelegate atVar = new at(textOptions, this.d);
        this.d.b(atVar);
        setRunLowFrame(false);
        return new Text(atVar);
    }

    public ArrayList<Marker> addMarkers(ArrayList<MarkerOptions> arrayList, boolean z) throws RemoteException {
        int i = 0;
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        ArrayList<Marker> arrayList2 = new ArrayList();
        try {
            MarkerOptions markerOptions;
            if (arrayList.size() == 1) {
                markerOptions = (MarkerOptions) arrayList.get(0);
                if (markerOptions != null) {
                    arrayList2.add(addMarker(markerOptions));
                    if (z && markerOptions.getPosition() != null) {
                        moveCamera(CameraUpdateFactoryDelegate.newLatLngZoom(markerOptions.getPosition(), 18.0f));
                    }
                    return arrayList2;
                }
            }
            final Builder builder = LatLngBounds.builder();
            int i2 = 0;
            while (i2 < arrayList.size()) {
                int i3;
                markerOptions = (MarkerOptions) arrayList.get(i2);
                if (arrayList.get(i2) != null) {
                    arrayList2.add(addMarker(markerOptions));
                    if (markerOptions.getPosition() != null) {
                        builder.include(markerOptions.getPosition());
                        i3 = i + 1;
                        i2++;
                        i = i3;
                    }
                }
                i3 = i;
                i2++;
                i = i3;
            }
            if (z && i > 0) {
                if (this.aJ) {
                    this.l.postDelayed(new Runnable(this) {
                        final /* synthetic */ c b;

                        public void run() {
                            try {
                                this.b.moveCamera(CameraUpdateFactoryDelegate.newLatLngBounds(builder.build(), 50));
                            } catch (Throwable th) {
                            }
                        }
                    }, 50);
                } else {
                    this.aE = CameraUpdateFactoryDelegate.newLatLngBounds(builder.build(), 50);
                }
            }
            return arrayList2;
        } catch (Throwable th) {
            ee.a(th, "AMapDelegateImpGLSurfaceView", "addMarkers");
            th.printStackTrace();
            return arrayList2;
        }
    }

    public ai a(MarkerOptions markerOptions) throws RemoteException {
        if (markerOptions == null) {
            return null;
        }
        IMarkerDelegate aiVar = new ai(markerOptions, this.d);
        this.d.b(aiVar);
        setRunLowFrame(false);
        return aiVar;
    }

    public TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException {
        if (tileOverlayOptions == null || tileOverlayOptions.getTileProvider() == null) {
            return null;
        }
        ITileOverlayDelegate avVar = new av(tileOverlayOptions, this.g);
        this.g.a(avVar);
        setRunLowFrame(false);
        return new TileOverlay(avVar);
    }

    public void clear() throws RemoteException {
        try {
            clear(false);
        } catch (Throwable th) {
            ee.a(th, "AMapDelegateImpGLSurfaceView", "clear");
            Log.d("amapApi", "AMapDelegateImpGLSurfaceView clear erro" + th.getMessage());
            th.printStackTrace();
        }
    }

    public void clear(boolean z) throws RemoteException {
        String str = null;
        try {
            String c;
            hiddenInfoWindowShown();
            if (this.as != null) {
                if (z) {
                    c = this.as.c();
                    str = this.as.d();
                    this.h.b(str);
                    this.g.b();
                    this.d.b(c);
                    setRunLowFrame(false);
                }
                this.as.e();
            }
            c = null;
            this.h.b(str);
            this.g.b();
            this.d.b(c);
            setRunLowFrame(false);
        } catch (Throwable th) {
            ee.a(th, "AMapDelegateImpGLSurfaceView", "clear");
            Log.d("amapApi", "AMapDelegateImpGLSurfaceView clear erro" + th.getMessage());
            th.printStackTrace();
        }
    }

    public boolean removeMarker(String str) {
        IMarkerDelegate a;
        try {
            a = this.d.a(str);
        } catch (Throwable e) {
            ee.a(e, "AMapDelegateImpGLSurfaceView", "removeMarker");
            e.printStackTrace();
            a = null;
        }
        if (a == null) {
            return false;
        }
        setRunLowFrame(false);
        return this.d.c(a);
    }

    public int getMapType() throws RemoteException {
        return this.F;
    }

    public void setMapType(int i) throws RemoteException {
        this.F = i;
        if (this.aJ) {
            if (i == 1) {
                try {
                    a(com.amap.api.mapcore.util.u.a.NORAML, com.amap.api.mapcore.util.u.c.DAY);
                } catch (Throwable th) {
                    ee.a(th, "AMapDelegateImpGLSurfaceView", "setMaptype");
                    th.printStackTrace();
                    return;
                }
            } else if (i == 2) {
                a(com.amap.api.mapcore.util.u.a.SATELLITE, com.amap.api.mapcore.util.u.c.DAY);
            } else if (i == 3) {
                a(com.amap.api.mapcore.util.u.a.NORAML, com.amap.api.mapcore.util.u.c.NIGHT, com.amap.api.mapcore.util.u.b.NAVI_CAR);
            } else if (i == 4) {
                a(com.amap.api.mapcore.util.u.a.NORAML, com.amap.api.mapcore.util.u.c.DAY, com.amap.api.mapcore.util.u.b.NAVI_CAR);
            } else {
                this.F = 1;
            }
            setRunLowFrame(false);
        }
    }

    public void a(com.amap.api.mapcore.util.u.a aVar, com.amap.api.mapcore.util.u.c cVar) {
        a(aVar, cVar, com.amap.api.mapcore.util.u.b.NORMAL);
    }

    public void a(com.amap.api.mapcore.util.u.a aVar, com.amap.api.mapcore.util.u.c cVar, com.amap.api.mapcore.util.u.b bVar) {
        if (this.C != cVar || this.D != aVar || this.E != bVar) {
            if (this.aK) {
                final com.amap.api.mapcore.util.u.c cVar2 = this.C;
                final com.amap.api.mapcore.util.u.a aVar2 = this.D;
                com.amap.api.mapcore.util.u.b bVar2 = this.E;
                if (this.bd && this.aI) {
                    final com.amap.api.mapcore.util.u.c cVar3 = cVar;
                    final com.amap.api.mapcore.util.u.a aVar3 = aVar;
                    final com.amap.api.mapcore.util.u.b bVar3 = bVar;
                    queueEvent(new Runnable(this) {
                        final /* synthetic */ c f;

                        public void run() {
                            String b = this.f.i.b();
                            String c = this.f.i.c();
                            this.f.C = cVar3;
                            this.f.D = aVar3;
                            this.f.E = bVar3;
                            String b2 = this.f.i.b();
                            String c2 = this.f.i.c();
                            if (this.f.D == com.amap.api.mapcore.util.u.a.SATELLITE || this.f.C == com.amap.api.mapcore.util.u.c.NIGHT || cVar2 == com.amap.api.mapcore.util.u.c.NIGHT || aVar2 == com.amap.api.mapcore.util.u.a.SATELLITE) {
                                this.f.l.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass3 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        this.a.f.u();
                                    }
                                });
                            }
                            this.f.G.setParameter(2501, 0, 0, 0, 0);
                            if (!b.equals(b2)) {
                                this.f.i.a();
                            }
                            if (this.f.D == com.amap.api.mapcore.util.u.a.SATELLITE || aVar2 == com.amap.api.mapcore.util.u.a.SATELLITE) {
                                this.f.G.setParameter(2011, this.f.D == com.amap.api.mapcore.util.u.a.SATELLITE ? 1 : 0, 0, 0, 0);
                            }
                            if (this.f.C == com.amap.api.mapcore.util.u.c.NIGHT || cVar2 == com.amap.api.mapcore.util.u.c.NIGHT) {
                                int i;
                                MapCore g = this.f.G;
                                if (this.f.C == com.amap.api.mapcore.util.u.c.NIGHT) {
                                    i = 1;
                                } else {
                                    i = 0;
                                }
                                g.setParameter(2401, i, 0, 0, 0);
                                this.f.i.d(true);
                                this.f.i.c(true);
                            }
                            if (!c.equals(c2)) {
                                this.f.i.a(true);
                            }
                            this.f.i.b(true);
                            if (this.f.E != null) {
                                this.f.G.setParameter(2013, this.f.D.ordinal(), this.f.C.ordinal(), this.f.E.ordinal(), 0);
                            }
                            this.f.G.setParameter(2501, 1, 1, 0, 0);
                        }
                    });
                    return;
                }
                this.bx.d = aVar;
                this.bx.e = cVar;
                this.bx.b = true;
                return;
            }
            this.C = cVar;
            this.D = aVar;
            this.E = bVar;
        }
    }

    public boolean isTrafficEnabled() throws RemoteException {
        return this.x;
    }

    public void setTrafficEnabled(boolean z) throws RemoteException {
        this.x = z;
        setRunLowFrame(false);
        this.e.a(new ac(2).a(z));
    }

    public void setMapTextEnable(final boolean z) throws RemoteException {
        this.w = z;
        setRunLowFrame(false);
        queueEvent(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                if (this.b.G != null) {
                    int i;
                    MapCore g = this.b.G;
                    if (z) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    g.setParameter(1024, i, 0, 0, 0);
                }
            }
        });
    }

    public boolean isIndoorEnabled() throws RemoteException {
        return this.u;
    }

    public void setIndoorEnabled(final boolean z) throws RemoteException {
        this.u = z;
        setRunLowFrame(false);
        if (z) {
            this.G.setParameter(Place.TYPE_SUBLOCALITY_LEVEL_4, 1, 0, 0, 0);
        } else {
            this.G.setParameter(Place.TYPE_SUBLOCALITY_LEVEL_4, 0, 0, 0, 0);
            r.f = 19.0f;
            if (this.an.isZoomControlsEnabled()) {
                this.l.sendEmptyMessage(21);
            }
        }
        if (this.an.isIndoorSwitchEnabled()) {
            this.l.post(new Runnable(this) {
                final /* synthetic */ c b;

                public void run() {
                    if (z) {
                        this.b.showIndoorSwitchControlsEnabled(true);
                    } else {
                        this.b.U.a(false);
                    }
                }
            });
        }
    }

    public void set3DBuildingEnabled(final boolean z) throws RemoteException {
        this.v = z;
        setRunLowFrame(false);
        queueEvent(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                if (this.b.G != null) {
                    int i;
                    MapCore g = this.b.G;
                    if (z) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    g.setParameter(1021, i, 0, 0, 0);
                }
            }
        });
    }

    public boolean isMyLocationEnabled() throws RemoteException {
        return this.aH;
    }

    public void setMyLocationEnabled(boolean z) throws RemoteException {
        try {
            if (this.ao == null) {
                this.Q.a(false);
            } else if (z) {
                this.ao.activate(this.aq);
                this.Q.a(true);
                if (this.as == null) {
                    this.as = new aj(this, this.H);
                }
            } else {
                if (this.as != null) {
                    this.as.b();
                    this.as = null;
                }
                this.az = null;
                this.ao.deactivate();
            }
            if (!z) {
                this.an.setMyLocationButtonEnabled(z);
            }
            this.aH = z;
            setRunLowFrame(false);
        } catch (Throwable th) {
            ee.a(th, "AMapDelegateImpGLSurfaceView", "setMyLocationEnabled");
            th.printStackTrace();
        }
    }

    public Location getMyLocation() throws RemoteException {
        if (this.ao != null) {
            return this.aq.a;
        }
        return null;
    }

    public void setLocationSource(LocationSource locationSource) throws RemoteException {
        try {
            this.ao = locationSource;
            if (locationSource != null) {
                this.Q.a(true);
            } else {
                this.Q.a(false);
            }
        } catch (Throwable th) {
            ee.a(th, "AMapDelegateImpGLSurfaceView", "setLocationSource");
            th.printStackTrace();
        }
    }

    public IUiSettingsDelegate getUiSettings() throws RemoteException {
        return this.an;
    }

    public IProjectionDelegate getProjection() throws RemoteException {
        return this.am;
    }

    public void setOnCameraChangeListener(OnCameraChangeListener onCameraChangeListener) throws RemoteException {
        this.aa = onCameraChangeListener;
    }

    void a(CameraPosition cameraPosition) {
        Message message = new Message();
        message.what = 10;
        message.obj = cameraPosition;
        this.l.sendMessage(message);
    }

    public OnCameraChangeListener getOnCameraChangeListener() throws RemoteException {
        return this.aa;
    }

    public void setOnMapClickListener(OnMapClickListener onMapClickListener) throws RemoteException {
        this.ab = onMapClickListener;
    }

    public void setOnMapTouchListener(OnMapTouchListener onMapTouchListener) throws RemoteException {
        this.ac = onMapTouchListener;
    }

    public void setOnPOIClickListener(OnPOIClickListener onPOIClickListener) throws RemoteException {
        this.ad = onPOIClickListener;
    }

    public void setOnMapLongClickListener(OnMapLongClickListener onMapLongClickListener) throws RemoteException {
        this.ae = onMapLongClickListener;
    }

    public void setOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) throws RemoteException {
        this.W = onMarkerClickListener;
    }

    public void setOnPolylineClickListener(OnPolylineClickListener onPolylineClickListener) throws RemoteException {
        this.X = onPolylineClickListener;
    }

    public void setOnMarkerDragListener(OnMarkerDragListener onMarkerDragListener) throws RemoteException {
        this.Y = onMarkerDragListener;
    }

    public void setOnMaploadedListener(OnMapLoadedListener onMapLoadedListener) throws RemoteException {
        this.Z = onMapLoadedListener;
    }

    public void setOnInfoWindowClickListener(OnInfoWindowClickListener onInfoWindowClickListener) throws RemoteException {
        this.af = onInfoWindowClickListener;
    }

    public void setOnIndoorBuildingActiveListener(OnIndoorBuildingActiveListener onIndoorBuildingActiveListener) throws RemoteException {
        this.ag = onIndoorBuildingActiveListener;
    }

    public void setInfoWindowAdapter(InfoWindowAdapter infoWindowAdapter) throws RemoteException {
        if (infoWindowAdapter == null) {
            this.ah = this.ai;
        } else {
            this.ah = infoWindowAdapter;
        }
    }

    public View getView() throws RemoteException {
        return this.O;
    }

    public float checkZoomLevel(float f) throws RemoteException {
        return dj.a(f);
    }

    public float toMapLenWithWin(int i) {
        if (this.aI) {
            return this.J.getMapLenWithWin(i);
        }
        return 0.0f;
    }

    public void getPixel2LatLng(int i, int i2, DPoint dPoint) {
        a(this.J, i, i2, dPoint);
    }

    private void a(MapProjection mapProjection, int i, int i2, DPoint dPoint) {
        if (this.aI) {
            FPoint fPoint = new FPoint();
            mapProjection.win2Map(i, i2, fPoint);
            IPoint iPoint = new IPoint();
            mapProjection.map2Geo(fPoint.x, fPoint.y, iPoint);
            MapProjection.geo2LonLat(iPoint.x, iPoint.y, dPoint);
        }
    }

    public void getPixel2Geo(int i, int i2, IPoint iPoint) {
        if (this.aI) {
            FPoint fPoint = new FPoint();
            this.J.win2Map(i, i2, fPoint);
            this.J.map2Geo(fPoint.x, fPoint.y, iPoint);
        }
    }

    public void a(int i, int i2, IPoint iPoint) {
        if (this.aI) {
            FPoint fPoint = new FPoint();
            this.J.geo2Map(i, i2, fPoint);
            this.J.map2Win(fPoint.x, fPoint.y, iPoint);
        }
    }

    public void getLatLng2Map(double d, double d2, FPoint fPoint) {
        if (this.aI) {
            IPoint iPoint = new IPoint();
            MapProjection.lonlat2Geo(d2, d, iPoint);
            this.J.geo2Map(iPoint.x, iPoint.y, fPoint);
        }
    }

    public void latlon2Geo(double d, double d2, IPoint iPoint) {
        MapProjection.lonlat2Geo(d2, d, iPoint);
    }

    public void pixel2Map(int i, int i2, FPoint fPoint) {
        if (this.aI) {
            this.J.win2Map(i, i2, fPoint);
        }
    }

    public void geo2Map(int i, int i2, FPoint fPoint) {
        if (this.aI) {
            this.J.geo2Map(i2, i, fPoint);
        }
    }

    public void map2Geo(float f, float f2, IPoint iPoint) {
        if (this.aI) {
            this.J.map2Geo(f, f2, iPoint);
        }
    }

    public void geo2Latlng(int i, int i2, DPoint dPoint) {
        MapProjection.geo2LonLat(i, i2, dPoint);
    }

    public void getLatLng2Pixel(double d, double d2, IPoint iPoint) {
        if (this.aI) {
            MapProjection mapProjection = new MapProjection(this.G);
            mapProjection.recalculate();
            IPoint iPoint2 = new IPoint();
            FPoint fPoint = new FPoint();
            MapProjection.lonlat2Geo(d2, d, iPoint2);
            mapProjection.geo2Map(iPoint2.x, iPoint2.y, fPoint);
            mapProjection.map2Win(fPoint.x, fPoint.y, iPoint);
            mapProjection.recycle();
        }
    }

    private LatLng s() {
        if (!this.aI) {
            return null;
        }
        DPoint dPoint = new DPoint();
        IPoint iPoint = new IPoint();
        this.J.getGeoCenter(iPoint);
        MapProjection.geo2LonLat(iPoint.x, iPoint.y, dPoint);
        return new LatLng(dPoint.y, dPoint.x, false);
    }

    public CameraPosition getCameraPositionPrj(boolean z) {
        if (!this.aI) {
            return null;
        }
        LatLng latLng;
        if (z) {
            DPoint dPoint = new DPoint();
            getPixel2LatLng(this.bs, this.bt, dPoint);
            latLng = new LatLng(dPoint.y, dPoint.x, false);
        } else {
            latLng = s();
        }
        return CameraPosition.builder().target(latLng).bearing(this.J.getMapAngle()).tilt(this.J.getCameraHeaderAngle()).zoom(this.J.getMapZoomer()).build();
    }

    private void t() {
        if (this.bn) {
            this.bn = false;
        }
        if (this.bj) {
            this.bj = false;
            CameraUpdateFactoryDelegate newInstance = CameraUpdateFactoryDelegate.newInstance();
            newInstance.isChangeFinished = true;
            this.e.a(newInstance);
        }
        if (this.be) {
            this.be = false;
            newInstance = CameraUpdateFactoryDelegate.newInstance();
            newInstance.isChangeFinished = true;
            this.e.a(newInstance);
        }
        this.bf = false;
        this.bg = false;
        if (this.Y != null && this.bh != null) {
            this.Y.onMarkerDragEnd(this.bh);
            this.bh = null;
        }
    }

    private void a(MotionEvent motionEvent) throws RemoteException {
        if (this.bg && this.bh != null) {
            int x = (int) motionEvent.getX();
            int y = (int) (motionEvent.getY() - 60.0f);
            LatLng realPosition = this.bi.getRealPosition();
            LatLng position = this.bi.getPosition();
            DPoint dPoint = new DPoint();
            getPixel2LatLng(x, y, dPoint);
            this.bh.setPosition(new LatLng((position.latitude + dPoint.y) - realPosition.latitude, (dPoint.x + position.longitude) - realPosition.longitude));
            this.Y.onMarkerDrag(this.bh);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.aJ) {
            return false;
        }
        setRunLowFrame(false);
        if (motionEvent.getAction() == 261) {
            this.bm = motionEvent.getPointerCount();
        }
        this.K.onTouchEvent(motionEvent);
        this.ar.a(motionEvent);
        this.L.onTouchEvent(motionEvent);
        this.M.a(motionEvent);
        if (motionEvent.getAction() == 2) {
            try {
                a(motionEvent);
            } catch (Throwable e) {
                ee.a(e, "AMapDelegateImpGLSurfaceView", "onDragMarker");
                e.printStackTrace();
            }
        }
        if (motionEvent.getAction() == 1) {
            t();
        }
        setRunLowFrame(false);
        if (this.ac != null) {
            this.bu.removeMessages(1);
            Message obtainMessage = this.bu.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = MotionEvent.obtain(motionEvent);
            obtainMessage.sendToTarget();
        }
        return true;
    }

    public void showInfoWindow(IMarkerDelegate iMarkerDelegate) throws RemoteException {
        int i = -2;
        if (iMarkerDelegate != null) {
            hiddenInfoWindowShown();
            if ((iMarkerDelegate.getTitle() != null || iMarkerDelegate.getSnippet() != null) && this.ah != null) {
                this.ak = iMarkerDelegate;
                if (this.aJ) {
                    int i2;
                    Marker marker = new Marker(iMarkerDelegate);
                    this.aj = this.ah.getInfoWindow(marker);
                    try {
                        if (this.ay == null) {
                            this.ay = al.a(this.H, "infowindow_bg.9.png");
                        }
                    } catch (Throwable th) {
                        ee.a(th, "AMapDelegateImpGLSurfaceView", "showInfoWindow decodeDrawableFromAsset");
                        th.printStackTrace();
                    }
                    if (this.aj == null) {
                        this.aj = this.ah.getInfoContents(marker);
                    }
                    View linearLayout = new LinearLayout(this.H);
                    if (this.aj != null) {
                        if (this.aj.getBackground() == null) {
                            this.aj.setBackgroundDrawable(this.ay);
                        }
                        linearLayout.addView(this.aj);
                    } else {
                        linearLayout.setBackgroundDrawable(this.ay);
                        View textView = new TextView(this.H);
                        textView.setText(iMarkerDelegate.getTitle());
                        textView.setTextColor(-16777216);
                        View textView2 = new TextView(this.H);
                        textView2.setTextColor(-16777216);
                        textView2.setText(iMarkerDelegate.getSnippet());
                        linearLayout.setOrientation(1);
                        linearLayout.addView(textView);
                        linearLayout.addView(textView2);
                    }
                    this.aj = linearLayout;
                    LayoutParams layoutParams = this.aj.getLayoutParams();
                    this.aj.setDrawingCacheEnabled(true);
                    this.aj.setDrawingCacheQuality(0);
                    iMarkerDelegate.getRect();
                    int realInfoWindowOffsetX = iMarkerDelegate.getRealInfoWindowOffsetX() + iMarkerDelegate.getInfoWindowOffsetX();
                    int realInfoWindowOffsetY = (iMarkerDelegate.getRealInfoWindowOffsetY() + iMarkerDelegate.getInfoWindowOffsetY()) + 2;
                    if (layoutParams != null) {
                        i2 = layoutParams.width;
                        i = layoutParams.height;
                    } else {
                        i2 = -2;
                    }
                    layoutParams = new com.amap.api.mapcore.util.ah.a(i2, i, iMarkerDelegate.getMapPosition(), realInfoWindowOffsetX, realInfoWindowOffsetY, 81);
                    Bitmap a;
                    BitmapDescriptor fromBitmap;
                    if (this.al == null) {
                        a = dj.a(this.aj);
                        fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
                        a.recycle();
                        this.al = new ap(this, new MarkerOptions().icon(fromBitmap), this) {
                            final /* synthetic */ c a;

                            public void a() {
                                this.a.aC.removeCallbacks(this.a.bv);
                                this.a.aC.post(this.a.bw);
                            }
                        };
                        this.al.a(iMarkerDelegate.getMapPosition());
                        this.al.setInfoWindowOffset(realInfoWindowOffsetX, realInfoWindowOffsetY);
                    } else {
                        this.al.a(iMarkerDelegate.getMapPosition());
                        this.al.setInfoWindowOffset(realInfoWindowOffsetX, realInfoWindowOffsetY);
                        a = dj.a(this.aj);
                        fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
                        a.recycle();
                        this.al.setIcon(fromBitmap);
                    }
                    this.O.addView(this.aj, layoutParams);
                    iMarkerDelegate.setInfoWindowShown(true);
                    return;
                }
                this.aC.postDelayed(new Runnable(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        try {
                            this.a.showInfoWindow(this.a.ak);
                        } catch (Throwable th) {
                            ee.a(th, "AMapDelegateImpGLSurfaceView", "showInfoWindow postDelayed");
                            th.printStackTrace();
                        }
                    }
                }, 100);
            }
        }
    }

    public boolean isInfoWindowShown(IMarkerDelegate iMarkerDelegate) {
        try {
            if (!(this.ak == null || this.aj == null)) {
                return this.ak.getId().equals(iMarkerDelegate.getId());
            }
        } catch (Throwable e) {
            ee.a(e, "AMapDelegateImpGLSurfaceView", "isInfoWindowShown");
            e.printStackTrace();
        }
        return false;
    }

    public void hiddenInfoWindowShown() {
        if (this.aj != null) {
            this.aj.clearFocus();
            this.O.removeView(this.aj);
            dj.a(this.aj.getBackground());
            dj.a(this.ay);
            if (this.al != null) {
                this.al.setVisible(false);
            }
            this.aj = null;
        }
        this.ak = null;
    }

    public float getZoomLevel() {
        return this.J.getMapZoomer();
    }

    void e() {
        this.l.obtainMessage(18).sendToTarget();
    }

    public LatLngBounds getMapBounds() {
        return this.bp;
    }

    public LatLngBounds getMapBounds(LatLng latLng, float f) {
        int c = c();
        int d = d();
        if (c <= 0 || d <= 0) {
            return null;
        }
        IPoint iPoint = new IPoint();
        MapProjection.lonlat2Geo(latLng.longitude, latLng.latitude, iPoint);
        MapProjection mapProjection = new MapProjection(this.G);
        mapProjection.setCameraHeaderAngle(0.0f);
        mapProjection.setMapAngle(0.0f);
        mapProjection.setGeoCenter(iPoint.x, iPoint.y);
        mapProjection.setMapZoomer(f);
        mapProjection.recalculate();
        DPoint dPoint = new DPoint();
        a(mapProjection, 0, 0, dPoint);
        LatLng latLng2 = new LatLng(dPoint.y, dPoint.x, false);
        a(mapProjection, c, d, dPoint);
        LatLng latLng3 = new LatLng(dPoint.y, dPoint.x, false);
        mapProjection.recycle();
        return LatLngBounds.builder().include(latLng3).include(latLng2).build();
    }

    public Point getWaterMarkerPositon() {
        if (this.P == null) {
            return null;
        }
        return this.P.c();
    }

    public static Bitmap a(int i, int i2, int i3, int i4, GL10 gl10) {
        try {
            int[] iArr = new int[(i3 * i4)];
            int[] iArr2 = new int[(i3 * i4)];
            Buffer wrap = IntBuffer.wrap(iArr);
            wrap.position(0);
            gl10.glReadPixels(i, i2, i3, i4, dji.midware.media.h.d.c, 5121, wrap);
            for (int i5 = 0; i5 < i4; i5++) {
                for (int i6 = 0; i6 < i3; i6++) {
                    int i7 = iArr[(i5 * i3) + i6];
                    iArr2[(((i4 - i5) - 1) * i3) + i6] = ((i7 & -16711936) | ((i7 << 16) & 16711680)) | ((i7 >> 16) & 255);
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i3, i4, Config.ARGB_8888);
            createBitmap.setPixels(iArr2, 0, i3, 0, 0, i3, i4);
            return createBitmap;
        } catch (Throwable th) {
            ee.a(th, "AMapDelegateImpGLSurfaceView", "SavePixels");
            th.printStackTrace();
            return null;
        }
    }

    public void getMapPrintScreen(onMapPrintScreenListener com_amap_api_maps_AMap_onMapPrintScreenListener) {
        this.aA = com_amap_api_maps_AMap_onMapPrintScreenListener;
        this.aR = true;
        setRunLowFrame(false);
    }

    public void getMapScreenShot(OnMapScreenShotListener onMapScreenShotListener) {
        this.aB = onMapScreenShotListener;
        this.aR = true;
        setRunLowFrame(false);
    }

    public int getLogoPosition() {
        try {
            return this.an.getLogoPosition();
        } catch (Throwable e) {
            ee.a(e, "AMapDelegateImpGLSurfaceView", "getLogoPosition");
            e.printStackTrace();
            return 0;
        }
    }

    public void setLogoPosition(int i) {
        if (this.P != null) {
            this.P.a(i);
            this.P.invalidate();
            if (this.S.getVisibility() == 0) {
                this.S.invalidate();
            }
        }
    }

    public void setZoomPosition(int i) {
        if (this.f != null) {
            this.f.a(i);
        }
    }

    public float getScalePerPixel() {
        try {
            LatLng latLng = getCameraPosition().target;
            float f = this.a;
            if (this.aI) {
                f = this.J.getMapZoomer();
            }
            return (float) ((((Math.cos((latLng.latitude * 3.141592653589793d) / 180.0d) * 2.0d) * 3.141592653589793d) * 6378137.0d) / (Math.pow(2.0d, (double) f) * 256.0d));
        } catch (Throwable th) {
            ee.a(th, "AMapDelegateImpGLSurfaceView", "getScalePerPixel");
            th.printStackTrace();
            return 0.0f;
        }
    }

    void a(boolean z) {
        int i;
        Handler handler = this.l;
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        handler.obtainMessage(20, i, 0).sendToTarget();
    }

    protected void a(boolean z, CameraPosition cameraPosition) {
        if (this.aa != null && this.at.isFinished() && this.j.isEnabled()) {
            if (cameraPosition == null) {
                try {
                    cameraPosition = getCameraPosition();
                } catch (Throwable e) {
                    ee.a(e, "AMapDelegateImpGLSurfaceView", "cameraChangeFinish");
                    e.printStackTrace();
                }
            }
            this.aa.onCameraChangeFinish(cameraPosition);
        }
    }

    public void deleteTexsureId(int i) {
        if (this.B.contains(Integer.valueOf(i))) {
            this.A.add(Integer.valueOf(i));
            this.B.remove(this.B.indexOf(Integer.valueOf(i)));
        }
    }

    public int getTexsureId() {
        Integer valueOf = Integer.valueOf(0);
        if (this.A.size() > 0) {
            valueOf = (Integer) this.A.get(0);
            this.A.remove(0);
            this.B.add(valueOf);
        }
        return valueOf.intValue();
    }

    public List<Marker> getMapScreenMarkers() {
        boolean z = c() > 0 && d() > 0;
        cu.a(z, (Object) "地图未初始化完成！");
        return this.d.g();
    }

    public void changeGLOverlayIndex() {
        this.h.b();
    }

    public boolean isAdreno() {
        return this.aM;
    }

    public void callRunDestroy() {
        this.bq = true;
    }

    public boolean isNeedRunDestroy() {
        return this.bq;
    }

    public void runDestroy() {
        if (this.d != null) {
            this.d.h();
        }
        this.bq = false;
    }

    public void setCenterToPixel(int i, int i2) {
        if (this.I != null) {
            this.br = true;
            this.I.a(i, i2);
            this.bs = i;
            this.bt = i2;
        }
    }

    public boolean isUseAnchor() {
        return this.br;
    }

    public void setMapTextZIndex(int i) {
        this.ax = i;
    }

    public int getMapTextZIndex() {
        return this.ax;
    }

    public boolean isMaploaded() {
        return this.aJ;
    }

    public int getAnchorX() {
        return this.bs;
    }

    public int getAnchorY() {
        return this.bt;
    }

    public CameraAnimator getCameraAnimator() {
        return this.at;
    }

    public void setLoadOfflineData(final boolean z) throws RemoteException {
        queueEvent(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                int i;
                MapCore g = this.b.G;
                if (z) {
                    i = 1;
                } else {
                    i = 0;
                }
                g.setParameter(2601, i, 0, 0, 0);
            }
        });
    }

    public void removecache() {
        removecache(null);
    }

    public void removecache(OnCacheRemoveListener onCacheRemoveListener) {
        if (this.aU != null) {
            try {
                this.G.setParameter(2601, 0, 0, 0, 0);
                Runnable iVar = new i(this, this.H, onCacheRemoveListener);
                this.aU.removeCallbacks(iVar);
                this.aU.post(iVar);
            } catch (Throwable th) {
                ee.a(th, "AMapDelegateImpGLSurfaceView", "removecache");
                th.printStackTrace();
            }
        }
    }

    private boolean a(File file) throws IOException, Exception {
        if (file == null || !file.exists()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isFile()) {
                    if (!listFiles[i].delete()) {
                        return false;
                    }
                } else if (!a(listFiles[i])) {
                    return false;
                } else {
                    listFiles[i].delete();
                }
            }
        }
        return true;
    }

    public void f() {
        if (this.h != null) {
            this.h.c();
        }
        if (this.d != null) {
            this.d.c();
        }
        if (this.m != null) {
            this.m.OnMapReferencechanged();
        }
    }

    public void setVisibilityEx(int i) {
        this.j.setVisibility(i);
    }

    public void a(IndoorBuilding indoorBuilding) throws RemoteException {
        if (!this.u) {
            return;
        }
        if (indoorBuilding == null) {
            if (!o()) {
                if (this.ag != null) {
                    this.ag.OnIndoorBuilding(indoorBuilding);
                }
                if (this.aD != null) {
                    this.aD.geoCenter = null;
                }
                if (this.U.d()) {
                    this.l.post(new Runnable(this) {
                        final /* synthetic */ c a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.U.setVisibility(8);
                        }
                    });
                }
                r.f = 19.0f;
                if (this.an.isZoomControlsEnabled()) {
                    this.l.sendEmptyMessage(21);
                }
            }
        } else if (this.aD == null || !this.aD.poiid.equals(indoorBuilding.poiid) || !this.U.d()) {
            if (this.aD == null || !this.aD.poiid.equals(indoorBuilding.poiid) || this.aD.geoCenter == null) {
                this.aD = indoorBuilding;
                this.aD.geoCenter = new IPoint();
                this.J.getGeoCenter(this.aD.geoCenter);
            }
            if (this.ag != null) {
                this.ag.OnIndoorBuilding(indoorBuilding);
            }
            r.f = 20.0f;
            if (this.an.isZoomControlsEnabled()) {
                this.l.sendEmptyMessage(21);
            }
            if (this.an.isIndoorSwitchEnabled() && !this.U.d()) {
                this.an.setIndoorSwitchEnabled(true);
                this.l.post(new Runnable(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        try {
                            this.a.U.a(this.a.aD.floor_names);
                            this.a.U.a(this.a.aD.activeFloorName);
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            } else if (!this.an.isIndoorSwitchEnabled() && this.U.d()) {
                this.an.setIndoorSwitchEnabled(false);
            }
        }
    }

    public void setIndoorBuildingInfo(IndoorBuilding indoorBuilding) throws RemoteException {
        if (indoorBuilding != null && indoorBuilding.activeFloorName != null && indoorBuilding.poiid != null) {
            this.aD = indoorBuilding;
            setRunLowFrame(false);
            queueEvent(new Runnable(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.G.setIndoorBuildingToBeActive(this.a.aD.activeFloorName, this.a.aD.activeFloorIndex, this.a.aD.poiid);
                }
            });
        }
    }

    private Poi a(int i, int i2, int i3) {
        if (!this.aJ) {
            return null;
        }
        try {
            SelectedMapPoi GetSelectedMapPoi = this.G.GetSelectedMapPoi(i, i2, i3);
            if (GetSelectedMapPoi == null) {
                return null;
            }
            DPoint dPoint = new DPoint();
            MapProjection.geo2LonLat(GetSelectedMapPoi.mapx, GetSelectedMapPoi.mapy, dPoint);
            return new Poi(GetSelectedMapPoi.name, new LatLng(dPoint.y, dPoint.x, false), GetSelectedMapPoi.poiid);
        } catch (Throwable th) {
            return null;
        }
    }

    public void setCustomRenderer(CustomRenderer customRenderer) {
        this.m = customRenderer;
    }

    public Context g() {
        return this.H;
    }

    public void queueEvent(Runnable runnable) {
        if (this.j != null) {
            this.j.queueEvent(runnable);
        }
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        try {
            if (!this.aI) {
                l();
            }
            this.bd = false;
            this.G.setGL(gl10);
            r();
            this.G.surfaceCreate(gl10);
            if (this.q == null || this.q.isRecycled()) {
                this.q = dj.a(this.H, "lineTexture.png");
            }
            if (this.r == null || this.r.isRecycled()) {
                this.r = dj.a(this.H, "lineDashTexture.png");
            }
            this.aT = false;
            this.n = dj.a(gl10, this.q);
            this.o = dj.a(gl10, this.r, true);
            this.q = null;
            this.d.j();
            this.h.e();
            this.g.f();
            if (this.al != null) {
                this.al.reLoadTexture();
            }
            p();
            setRunLowFrame(false);
            if (!this.aK) {
                this.bo.setName("AuthThread");
                this.bo.start();
                this.aK = true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (this.m != null) {
            this.m.onSurfaceCreated(gl10, eGLConfig);
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        int i3 = 120;
        int i4 = 50;
        int i5 = 1;
        this.ap = new Rect(0, 0, i, i2);
        try {
            this.G.setGL(gl10);
            this.G.surfaceChange(gl10, i, i2);
            int i6 = this.H.getResources().getDisplayMetrics().densityDpi;
            float f = this.H.getResources().getDisplayMetrics().density;
            int i7 = 100;
            if (i6 > 120) {
                if (i6 <= 160) {
                    if (Math.max(i, i2) <= 480) {
                        i6 = 120;
                    } else {
                        i6 = 100;
                        i3 = 160;
                    }
                    i4 = i3;
                    i7 = i6;
                } else if (i6 <= 240) {
                    if (Math.min(i, i2) >= 1000) {
                        i7 = 60;
                        i4 = 200;
                        i5 = 2;
                    } else {
                        i7 = 70;
                        i4 = 150;
                        i5 = 2;
                    }
                } else if (i6 <= 320) {
                    i5 = 3;
                    i7 = 50;
                    i4 = 180;
                } else if (i6 <= 480) {
                    i5 = 3;
                    i7 = 50;
                    i4 = 300;
                } else {
                    i7 = 40;
                    i4 = 360;
                    i5 = 4;
                }
            }
            this.G.setParameter(2051, i7, i4, (int) (f * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity), i5);
            this.z = ((float) i7) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity;
            this.G.setParameter(1001, 0, 0, 0, 0);
            this.G.setParameter(Place.TYPE_SUBLOCALITY_LEVEL_1, 1, 0, 0, 0);
            setRunLowFrame(false);
            if (this.m != null) {
                this.m.onSurfaceChanged(gl10, i, i2);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public float getMapZoomScale() {
        return this.z;
    }

    public void reloadMap() {
        this.aJ = false;
        onPause();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onResume();
    }

    public com.amap.api.mapcore.util.u.c h() {
        return this.C;
    }

    public com.amap.api.mapcore.util.u.a i() {
        return this.D;
    }

    public com.amap.api.mapcore.util.u.b j() {
        return this.E;
    }

    private void u() {
        if (this.D == com.amap.api.mapcore.util.u.a.SATELLITE || this.C == com.amap.api.mapcore.util.u.c.NIGHT) {
            this.P.a(true);
        } else {
            this.P.a(false);
        }
    }

    public void setRenderFps(int i) {
        try {
            this.p = Math.max(10, Math.min(i, 40));
            this.l.sendEmptyMessage(22);
        } catch (Throwable th) {
        }
    }

    public boolean isDrawOnce() {
        return this.aL;
    }
}
