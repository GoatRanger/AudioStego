package com.nokia.maps;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import com.here.android.mpa.ar.ARController;
import com.here.android.mpa.ar.ARController.Error;
import com.here.android.mpa.ar.ARController.IntroAnimationMode;
import com.here.android.mpa.ar.ARController.OnCameraEnteredListener;
import com.here.android.mpa.ar.ARController.OnCameraExitedListener;
import com.here.android.mpa.ar.ARController.OnCompassCalibrationChangedListener;
import com.here.android.mpa.ar.ARController.OnLivesightStatusListener;
import com.here.android.mpa.ar.ARController.OnMapEnteredListener;
import com.here.android.mpa.ar.ARController.OnMapExitedListener;
import com.here.android.mpa.ar.ARController.OnObjectTappedListener;
import com.here.android.mpa.ar.ARController.OnPanListener;
import com.here.android.mpa.ar.ARController.OnPitchFunction;
import com.here.android.mpa.ar.ARController.OnPoseListener;
import com.here.android.mpa.ar.ARController.OnPostPresentListener;
import com.here.android.mpa.ar.ARController.OnPreDrawListener;
import com.here.android.mpa.ar.ARController.OnPreDrawMapListener;
import com.here.android.mpa.ar.ARController.OnPrePresentListener;
import com.here.android.mpa.ar.ARController.OnProjectionCameraUpdatedListener;
import com.here.android.mpa.ar.ARController.OnRadarUpdateListener;
import com.here.android.mpa.ar.ARController.OnSensorCalibrationChangedListener;
import com.here.android.mpa.ar.ARController.OnTapListener;
import com.here.android.mpa.ar.ARController.OnTouchDownListener;
import com.here.android.mpa.ar.ARController.OnTouchUpListener;
import com.here.android.mpa.ar.ARController.ProjectionType;
import com.here.android.mpa.ar.ARController.SensorType;
import com.here.android.mpa.ar.ARController.ViewType;
import com.here.android.mpa.ar.ARController.c;
import com.here.android.mpa.ar.ARModelObject;
import com.here.android.mpa.ar.ARObject;
import com.here.android.mpa.ar.ARPolylineObject;
import com.here.android.mpa.ar.ARPoseReading;
import com.here.android.mpa.ar.ARRadarProperties;
import com.here.android.mpa.ar.AnimationInterpolator;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Size;
import com.here.android.mpa.common.Vector3f;
import com.here.android.mpa.common.ViewRect;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.ar.d;
import com.nokia.maps.ar.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@HybridPlus
public class b {
    private static am<ARController, b> U = null;
    private static k<ARController, b> V = null;
    private final CopyOnWriteArrayList<OnPrePresentListener> A = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnPostPresentListener> B = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnPanListener> C = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnTapListener> D = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnTouchDownListener> E = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnTouchUpListener> F = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnObjectTappedListener> G = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnRadarUpdateListener> H = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnPoseListener> I = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnSensorCalibrationChangedListener> J = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnLivesightStatusListener> K = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnProjectionCameraUpdatedListener> L = new CopyOnWriteArrayList();
    private OnPitchFunction M = null;
    private Map<Long, ARObject> N = new HashMap();
    private d O = null;
    private boolean P = true;
    private boolean Q = true;
    private boolean R = true;
    private boolean S = true;
    private boolean T = true;
    private d W = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            return false;
        }
    };
    private d X = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            synchronized (this.a.r) {
                Iterator it = this.a.r.iterator();
                while (it.hasNext()) {
                    ((OnCameraEnteredListener) it.next()).onCameraEntered();
                }
            }
            return false;
        }
    };
    private d Y = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            synchronized (this.a.s) {
                Iterator it = this.a.s.iterator();
                while (it.hasNext()) {
                    ((OnCameraExitedListener) it.next()).onCameraExited();
                }
            }
            return false;
        }
    };
    private d Z = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            synchronized (this.a.t) {
                Iterator it = this.a.t.iterator();
                while (it.hasNext()) {
                    ((OnMapEnteredListener) it.next()).onMapEntered();
                }
            }
            return false;
        }
    };
    final ar a = new ar();
    private d aa = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            synchronized (this.a.u) {
                Iterator it = this.a.u.iterator();
                while (it.hasNext()) {
                    ((OnMapExitedListener) it.next()).onMapExited();
                }
            }
            return false;
        }
    };
    private d ab = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            synchronized (this.a.v) {
                Iterator it = this.a.v.iterator();
                while (it.hasNext()) {
                    ((com.here.android.mpa.ar.ARController.a) it.next()).a();
                }
            }
            return false;
        }
    };
    private d ac = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            synchronized (this.a.w) {
                Iterator it = this.a.w.iterator();
                while (it.hasNext()) {
                    ((com.here.android.mpa.ar.ARController.b) it.next()).a();
                }
            }
            return false;
        }
    };
    private com.nokia.maps.ar.a ad = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            boolean z = false;
            if (obj2 != null && (obj2 instanceof List)) {
                List list = (List) obj2;
                synchronized (this.a.G) {
                    Iterator it = this.a.G.iterator();
                    while (it.hasNext()) {
                        boolean z2;
                        if (((OnObjectTappedListener) it.next()).onObjectTapped(list)) {
                            z2 = true;
                        } else {
                            z2 = z;
                        }
                        z = z2;
                    }
                }
            }
            return z;
        }
    };
    private com.nokia.maps.ar.a ae = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            boolean z;
            synchronized (this.a.D) {
                z = false;
                Iterator it = this.a.D.iterator();
                while (it.hasNext()) {
                    boolean z2;
                    if (((OnTapListener) it.next()).onTap((PointF) obj2)) {
                        z2 = true;
                    } else {
                        z2 = z;
                    }
                    z = z2;
                }
            }
            return z;
        }
    };
    private com.nokia.maps.ar.a af = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            boolean z = false;
            if (obj2 != null && (obj2 instanceof List)) {
                synchronized (this.a.C) {
                    List list = (List) obj2;
                    if (list.size() == 2) {
                        Iterator it = this.a.C.iterator();
                        while (it.hasNext()) {
                            boolean z2;
                            if (((OnPanListener) it.next()).onPan((PointF) list.get(0), (PointF) list.get(1))) {
                                z2 = true;
                            } else {
                                z2 = z;
                            }
                            z = z2;
                        }
                    }
                }
            }
            return z;
        }
    };
    private com.nokia.maps.ar.a ag = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            boolean z;
            synchronized (this.a.E) {
                z = false;
                Iterator it = this.a.E.iterator();
                while (it.hasNext()) {
                    boolean z2;
                    if (((OnTouchDownListener) it.next()).onTouchDown((PointF) obj2)) {
                        z2 = true;
                    } else {
                        z2 = z;
                    }
                    z = z2;
                }
            }
            return z;
        }
    };
    private com.nokia.maps.ar.a ah = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            boolean z;
            synchronized (this.a.F) {
                z = false;
                Iterator it = this.a.F.iterator();
                while (it.hasNext()) {
                    boolean z2;
                    if (((OnTouchUpListener) it.next()).onTouchUp((PointF) obj2)) {
                        z2 = true;
                    } else {
                        z2 = z;
                    }
                    z = z2;
                }
            }
            return z;
        }
    };
    private d ai = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            synchronized (this.a.H) {
                Iterator it = this.a.H.iterator();
                while (it.hasNext()) {
                    ((OnRadarUpdateListener) it.next()).onRadarUpdate((ARRadarProperties) obj2);
                }
            }
            return false;
        }
    };
    private com.nokia.maps.ar.a aj = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            synchronized (this.a.I) {
                Iterator it = this.a.I.iterator();
                while (it.hasNext()) {
                    ((OnPoseListener) it.next()).onPose((ARPoseReading) obj2);
                }
            }
            return false;
        }
    };
    private com.nokia.maps.ar.a ak = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            synchronized (this.a.y) {
                Iterator it = this.a.y.iterator();
                while (it.hasNext()) {
                    ((OnPreDrawListener) it.next()).onPreDraw();
                }
            }
            return false;
        }
    };
    private com.nokia.maps.ar.a al = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            g gVar = (g) obj2;
            synchronized (this.a.z) {
                Iterator it = this.a.z.iterator();
                while (it.hasNext()) {
                    OnPreDrawMapListener onPreDrawMapListener = (OnPreDrawMapListener) it.next();
                    if (gVar == null) {
                        onPreDrawMapListener.onPreDrawMap(0.0f, 0.0f, null);
                    } else {
                        onPreDrawMapListener.onPreDrawMap(gVar.a, gVar.b, gVar.c);
                    }
                }
            }
            return false;
        }
    };
    private com.nokia.maps.ar.a am = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            synchronized (this.a.A) {
                Iterator it = this.a.A.iterator();
                while (it.hasNext()) {
                    ((OnPrePresentListener) it.next()).onPrePresent();
                }
            }
            return false;
        }
    };
    private com.nokia.maps.ar.a an = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            synchronized (this.a.B) {
                Iterator it = this.a.B.iterator();
                while (it.hasNext()) {
                    ((OnPostPresentListener) it.next()).onPostPresent();
                }
            }
            return false;
        }
    };
    private e ao = new e(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2, Object obj3) {
            synchronized (this.a.J) {
                Iterator it = this.a.J.iterator();
                while (it.hasNext()) {
                    ((OnSensorCalibrationChangedListener) it.next()).onSensorCalibrationChanged(((Integer) obj2).intValue(), ((Integer) obj3).intValue());
                }
            }
            return false;
        }
    };
    private d ap = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            synchronized (this.a.K) {
                Iterator it = this.a.K.iterator();
                while (it.hasNext()) {
                    ((OnLivesightStatusListener) it.next()).onLivesightStatus((Error) obj2);
                }
            }
            return false;
        }
    };
    private com.nokia.maps.ar.a aq = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            if (this.a.p && this.a.n != null) {
                ((ai) this.a.n).p();
            }
            return false;
        }
    };
    private com.nokia.maps.ar.a ar = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            synchronized (this.a.c) {
                if (this.a.M == null) {
                    return false;
                }
                cm cmVar = (cm) obj2;
                cmVar.a(this.a.M.onPitchFunction(cmVar.a()));
                return true;
            }
        }
    };
    private com.nokia.maps.ar.a as = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            synchronized (this.a.L) {
                Iterator it = this.a.L.iterator();
                while (it.hasNext()) {
                    ((OnProjectionCameraUpdatedListener) it.next()).onProjectionCameraUpdated();
                }
            }
            return false;
        }
    };
    final Object b = new Object();
    final Object c = new Object();
    final com.nokia.maps.ar.a d = new d(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            Log.d("livesight", "Livesight stopped!!!");
            synchronized (this.a) {
                this.a.ab();
                if (this.a.l == null) {
                    this.a.ap.a(this, Error.INVALID_OPERATION);
                } else {
                    this.a.o.set(false);
                    this.a.ap.a(this, Error.STOPPED);
                }
            }
            return false;
        }
    };
    c e = c.CAMERA_LIVE;
    ViewType f = ViewType.AUTO;
    int g = 3;
    final Object h = new Object(this) {
        final /* synthetic */ b a;
        private int b;
        private final ScheduledExecutorService c = Executors.newSingleThreadScheduledExecutor();
        private Future<?> d;

        {
            this.a = r3;
            this.a.r.add(new OnCameraEnteredListener(this) {
                final /* synthetic */ AnonymousClass12 a;

                {
                    this.a = r1;
                }

                public void onCameraEntered() {
                    this.a.a();
                }
            });
            this.a.s.add(new OnCameraExitedListener(this) {
                final /* synthetic */ AnonymousClass12 a;

                {
                    this.a = r1;
                }

                public void onCameraExited() {
                    this.a.b();
                }
            });
            this.a.t.add(new OnMapEnteredListener(this) {
                final /* synthetic */ AnonymousClass12 a;

                {
                    this.a = r1;
                }

                public void onMapEntered() {
                    this.a.a();
                }
            });
            this.a.u.add(new OnMapExitedListener(this) {
                final /* synthetic */ AnonymousClass12 a;

                {
                    this.a = r1;
                }

                public void onMapExited() {
                    this.a.b();
                }
            });
        }

        private void a() {
            if (this.d != null) {
                this.d.cancel(false);
            }
            this.b++;
        }

        private void b() {
            int i = this.b - 1;
            this.b = i;
            if (i == 0) {
                this.d = this.c.schedule(new Runnable(this) {
                    final /* synthetic */ AnonymousClass12 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        l.a().f(true);
                        this.a.d = null;
                    }
                }, 5, TimeUnit.SECONDS);
            }
        }
    };
    public com.nokia.maps.ar.a i = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            this.a.T();
            return false;
        }
    };
    public com.nokia.maps.ar.a j = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            this.a.U();
            return false;
        }
    };
    public com.nokia.maps.ar.a k = new com.nokia.maps.ar.a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public boolean a(Object obj, Object obj2) {
            this.a.V();
            return false;
        }
    };
    private ARLayoutControl l = null;
    private MapImpl m = null;
    private u n = null;
    private AtomicBoolean o = new AtomicBoolean(false);
    private boolean p = false;
    private dx q = null;
    private final CopyOnWriteArrayList<OnCameraEnteredListener> r = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnCameraExitedListener> s = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnMapEnteredListener> t = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnMapExitedListener> u = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<com.here.android.mpa.ar.ARController.a> v = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<com.here.android.mpa.ar.ARController.b> w = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnCompassCalibrationChangedListener> x = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnPreDrawListener> y = new CopyOnWriteArrayList();
    private final CopyOnWriteArrayList<OnPreDrawMapListener> z = new CopyOnWriteArrayList();

    public enum a {
        ICON_FLY,
        ICON_POP_UP,
        ICON_TURN,
        ICON_PRESS,
        ICON_DEPRESS,
        INFO_OPEN,
        INFO_CLOSE,
        INTRO_HEADING,
        INTRO_PITCH,
        INTRO_ZOOM,
        INTRO_TFC,
        INTRO_GPS,
        MAP_FADE_IN,
        MAP_FADE_OUT,
        TILT_UP_PITCH,
        SELECTED_ITEM_BOUNDING_BOX,
        SELECTED_ITEM_SIZE,
        UNSELECTED_ITEM_BOUNDING_BOX,
        UNSELECTED_ITEM_SIZE,
        HEADING,
        PITCH,
        ZOOM,
        TFC,
        GPS
    }

    static {
        ce.a(ARController.class);
    }

    static ARController a(b bVar) {
        return bVar != null ? (ARController) U.a(bVar) : null;
    }

    public static void a(k<ARController, b> kVar, am<ARController, b> amVar) {
        V = kVar;
        U = amVar;
    }

    static b a(ARController aRController) {
        return (b) V.a(aRController);
    }

    public b(u uVar, dx dxVar) {
        boolean z = true;
        this.n = uVar;
        this.q = dxVar;
        this.q.a.a(this.i);
        this.q.b.a(this.j);
        this.q.c.a(this.k);
        this.l = new ARLayoutControl(this.n, this);
        this.l.a(this.q);
        this.l.a.a(this.W);
        this.l.b.a(this.X);
        this.l.c.a(this.Y);
        this.l.d.a(this.Z);
        this.l.e.a(this.aa);
        this.l.f.a(this.ab);
        this.l.g.a(this.ac);
        this.l.o.a(this.ad);
        this.l.k.a(this.ae);
        this.l.j.a(this.af);
        this.l.l.a(this.ag);
        this.l.m.a(this.ah);
        this.l.r.a(this.aj);
        this.l.n.a(this.ao);
        this.l.p.a(this.ai);
        this.l.s.a(this.ak);
        this.l.t.a(this.al);
        this.l.u.a(this.am);
        this.l.v.a(this.an);
        this.l.w.a(this.ap);
        this.l.y.a(this.aq);
        this.l.z.a(this.ar);
        this.l.x.a(this.d);
        this.l.A.a(this.as);
        n();
        o();
        m();
        p();
        q();
        if (this.n == null || !(this.n instanceof ai)) {
            z = false;
        }
        this.p = z;
        this.O = new d(this.l);
    }

    public synchronized Error a() {
        Error error;
        if (this.o.get()) {
            error = Error.INVALID_OPERATION;
        } else if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            error = Error.INVALID_OPERATION;
        } else {
            if (this.p) {
                ((ai) this.n).n();
            }
            if (this.l.startLivesight()) {
                ARSensors f = this.l.f();
                if (f == null || f.b()) {
                    this.o.set(true);
                    error = Error.NONE;
                } else {
                    if (this.p) {
                        ((ai) this.n).o();
                    }
                    this.l.stopLivesight(false);
                    error = Error.SENSORS_UNAVAILABLE;
                }
            } else {
                if (this.p) {
                    ((ai) this.n).o();
                }
                error = Error.OPERATION_NOT_ALLOWED;
            }
        }
        return error;
    }

    public synchronized Error a(boolean z) {
        Error error;
        if (!this.o.get()) {
            error = Error.INVALID_OPERATION;
        } else if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            error = Error.INVALID_OPERATION;
        } else {
            this.l.stopLivesight(z);
            error = Error.NONE;
        }
        return error;
    }

    private void ab() {
        if (this.p) {
            ((ai) this.n).l();
            ((ai) this.n).o();
        }
    }

    public void a(com.here.android.mpa.mapping.Map map) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return;
        }
        this.m = MapImpl.get(map);
        this.l.a(this.m);
    }

    public void a(int i, int i2, int i3) {
        this.O.a(i, i2, i3);
    }

    public synchronized void a(ARObject aRObject) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            synchronized (this.b) {
                this.N.put(Long.valueOf(aRObject.getUid()), aRObject);
            }
            ARObjectImpl b = ARObjectImpl.b(aRObject);
            b.a(aRObject);
            this.l.addARObject(b);
        }
    }

    public synchronized boolean b(ARObject aRObject) {
        boolean z = true;
        synchronized (this) {
            if (aRObject != null) {
                if (this.l == null) {
                    this.ap.a(this, Error.INVALID_OPERATION);
                    z = false;
                } else {
                    ARObjectImpl b = ARObjectImpl.b(aRObject);
                    this.l.removeARObject(b);
                    b.a(null);
                }
            }
        }
        return z;
    }

    public synchronized void a(ARPolylineObject aRPolylineObject) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.addARObject(ARPolylineObjectImpl.a(aRPolylineObject));
        }
    }

    public synchronized boolean b(ARPolylineObject aRPolylineObject) {
        boolean z = true;
        synchronized (this) {
            if (aRPolylineObject != null) {
                if (this.l == null) {
                    this.ap.a(this, Error.INVALID_OPERATION);
                    z = false;
                } else {
                    this.l.removeARObject(ARPolylineObjectImpl.a(aRPolylineObject));
                }
            }
        }
        return z;
    }

    public synchronized void a(ARModelObject aRModelObject) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.addARViewObject(ARModelObjectImpl.a(aRModelObject));
        }
    }

    public synchronized boolean b(ARModelObject aRModelObject) {
        boolean z = true;
        synchronized (this) {
            if (aRModelObject != null) {
                if (this.l == null) {
                    this.ap.a(this, Error.INVALID_OPERATION);
                    z = false;
                } else {
                    this.l.removeARViewObject(ARModelObjectImpl.a(aRModelObject));
                }
            }
        }
        return z;
    }

    public boolean c(ARObject aRObject) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return false;
        }
        return this.l.isVisible(ARObjectImpl.b(aRObject));
    }

    public boolean d(ARObject aRObject) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return false;
        }
        return this.l.isOccluded(ARObjectImpl.b(aRObject));
    }

    void a(long j) {
        synchronized (this.b) {
            this.N.remove(Long.valueOf(j));
        }
    }

    public ARObject b(long j) {
        ARObject aRObject;
        synchronized (this.b) {
            aRObject = (ARObject) this.N.get(Long.valueOf(j));
        }
        return aRObject;
    }

    public void b(boolean z) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.enableDownIcons(z);
        }
    }

    public void e(ARObject aRObject) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.focus(aRObject.getUid());
        }
    }

    public void b() {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.defocus();
        }
    }

    public void a(float f, float f2, float f3, float f4) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setPlanesParam(f, f2, f3, f4);
        }
    }

    public void a(float f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setBack2FrontIconSizeRatio(f);
        }
    }

    public ARObject a(PointF pointF) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return null;
        }
        long[] objects = this.l.getObjects(new Point((int) pointF.x, (int) pointF.y));
        if (objects.length <= 0) {
            return null;
        }
        this.l.press(objects[0]);
        return b(objects[0]);
    }

    public void f(ARObject aRObject) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.press(ARObjectImpl.b(aRObject).getUid());
        }
    }

    public void g(ARObject aRObject) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.depress(ARObjectImpl.b(aRObject).getUid());
        }
    }

    public void h(ARObject aRObject) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.select(ARObjectImpl.b(aRObject).getUid());
        }
    }

    public void a(ARObject aRObject, boolean z, float f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.selectWithScale(ARObjectImpl.b(aRObject).getUid(), z, f);
        }
    }

    public void c() {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.unselect();
        }
    }

    public void a(RectF rectF) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setSelectedBoundingBox(new PointF(rectF.left, rectF.top), new PointF(rectF.right, rectF.bottom));
        }
    }

    public void b(float f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setSelectedItemMaxViewAngle(f);
        }
    }

    public float d() {
        if (this.l != null) {
            return this.l.getSelectedItemMaxViewAngle();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return 0.0f;
    }

    public void c(float f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setUpViewPitchThreshold(f);
        }
    }

    public void d(float f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setDownViewPitchThreshold(f);
        }
    }

    public float e() {
        if (this.l != null) {
            return this.l.getUpViewPitchThreshold();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return 0.0f;
    }

    public float f() {
        if (this.l != null) {
            return this.l.getDownViewPitchThreshold();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return 0.0f;
    }

    public void c(boolean z) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setShowGridEnabled(z);
        }
    }

    public boolean g() {
        if (this.l != null) {
            return this.l.isShowGridEnabled();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return false;
    }

    public void d(boolean z) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.showFrontItemsOnly(z);
        }
    }

    public List<ARObject> b(PointF pointF) {
        List<ARObject> list = null;
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else if (pointF != null) {
            for (long b : this.l.getObjects(new Point((int) pointF.x, (int) pointF.y))) {
                ARObject b2 = b(b);
                if (b2 != null) {
                    if (list == null) {
                        list = new CopyOnWriteArrayList();
                    }
                    list.add(b2);
                }
            }
        }
        return list;
    }

    public List<ARObject> a(ViewRect viewRect) {
        List<ARObject> list = null;
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            for (long b : this.l.getObjectsRect(new Point(viewRect.getX(), viewRect.getY()), new Point(viewRect.getX() + viewRect.getWidth(), viewRect.getY() + viewRect.getHeight()))) {
                ARObject b2 = b(b);
                if (b2 != null) {
                    if (list == null) {
                        list = new CopyOnWriteArrayList();
                    }
                    list.add(b2);
                }
            }
        }
        return list;
    }

    public int h() {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return -1;
        }
        ARSensors f = this.l.f();
        if (f != null) {
            return f.n();
        }
        return -1;
    }

    public int i() {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return -1;
        }
        ARSensors f = this.l.f();
        if (f != null) {
            return f.m();
        }
        return -1;
    }

    public int j() {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return -1;
        }
        ARSensors f = this.l.f();
        if (f != null) {
            return f.o();
        }
        return -1;
    }

    public void a(ViewType viewType) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return;
        }
        this.f = viewType;
        this.l.showScene(viewType.ordinal());
    }

    public ViewType k() {
        return this.f;
    }

    public ARPoseReading l() {
        if (this.l != null) {
            return this.l.a();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return null;
    }

    public void a(PointF pointF, PointF pointF2) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.pan(new Point((int) pointF.x, (int) pointF.y), new Point((int) pointF2.x, (int) pointF2.y));
        }
    }

    public void a(GeoCoordinate geoCoordinate) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else if (geoCoordinate != null && geoCoordinate.isValid()) {
            this.l.panTo(GeoCoordinateImpl.get(geoCoordinate));
        }
    }

    public void e(boolean z) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setMapAutoControlOnEntryExit(z);
        }
    }

    public void a(boolean z, boolean z2) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return;
        }
        this.P = z;
        this.l.setMapAutoZoom(z, z2);
    }

    public boolean m() {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return false;
        }
        this.P = this.l.getMapAutoZoom();
        return this.P;
    }

    public void b(boolean z, boolean z2) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return;
        }
        this.Q = z;
        this.l.a(z, z2);
    }

    public boolean n() {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return false;
        }
        this.Q = this.l.getMapAutoPitch();
        return this.Q;
    }

    public void c(boolean z, boolean z2) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return;
        }
        this.R = z;
        this.l.setMapAutoHeading(z, z2);
    }

    public boolean o() {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return false;
        }
        this.R = this.l.getMapAutoHeading();
        return this.R;
    }

    public void d(boolean z, boolean z2) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return;
        }
        this.S = z;
        this.l.setMapAutoTfc(z, z2);
    }

    public boolean p() {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return false;
        }
        this.S = this.l.getMapAutoTfc();
        return this.S;
    }

    public void e(boolean z, boolean z2) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return;
        }
        this.T = z;
        this.l.setMapAutoGeoPosition(z, z2);
    }

    public boolean q() {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return false;
        }
        this.T = this.l.getMapAutoGeoCenter();
        return this.T;
    }

    public GeoCoordinateImpl r() {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return null;
        }
        ARSensors f = this.l.f();
        if (f != null) {
            return f.k();
        }
        return null;
    }

    public GeoCoordinateImpl a(AtomicBoolean atomicBoolean) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return null;
        }
        ARSensors f = this.l.f();
        if (f != null) {
            return f.a(atomicBoolean);
        }
        return null;
    }

    public void f(boolean z) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setPitchLockedUpView(z);
        }
    }

    public boolean s() {
        if (this.l != null) {
            return this.l.isPitchLockedUpView();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return false;
    }

    public void g(boolean z) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setOcclusionEnabled(z);
        }
    }

    public boolean t() {
        if (this.l != null) {
            return this.l.isOcclusionEnabled();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return false;
    }

    public void h(boolean z) {
        if (this.n != null) {
            this.n.setPanEnabled(z);
        }
    }

    public boolean u() {
        return this.n != null ? this.n.c() : false;
    }

    public void i(boolean z) {
        if (this.n != null) {
            this.n.setPinchEnabled(z);
        }
    }

    public boolean v() {
        return this.n != null ? this.n.d() : false;
    }

    public void j(boolean z) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setCameraZoomEnabledUpView(z);
        }
    }

    public boolean w() {
        if (this.l != null) {
            return this.l.isCameraZoomEnabledUpView();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return false;
    }

    public void e(float f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setCameraMaxZoomScaleUpView(f);
        }
    }

    public float x() {
        if (this.l != null) {
            return this.l.getCameraMaxZoomScaleUpView();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return -1.0f;
    }

    public void b(GeoCoordinate geoCoordinate) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return;
        }
        ARSensors f = this.l.f();
        if (f != null) {
            f.a(geoCoordinate);
        }
    }

    public boolean y() {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return false;
        }
        ARSensors f = this.l.f();
        if (f != null) {
            return f.a();
        }
        return false;
    }

    public void a(int i, int i2) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setFrontIconSize(new Size(i, i2));
        }
    }

    public void b(int i, int i2) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setBackIconSize(new Size(i, i2));
        }
    }

    public void c(int i, int i2) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setSelectedIconSize(new Size(i, i2));
        }
    }

    public Size z() {
        if (this.l != null) {
            return this.l.getSelectedIconSize();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return null;
    }

    public boolean a(ARObject aRObject, int i, int i2) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return false;
        } else if (aRObject == null) {
            return false;
        } else {
            ARObjectImpl.b(aRObject).setStartStopSizeOnMap(i, i2);
            return true;
        }
    }

    public void k(boolean z) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setUseDownIconOpacity(z);
        }
    }

    public boolean A() {
        if (this.l != null) {
            return this.l.getUseDownIconOpacity();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return false;
    }

    public void f(float f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setDownIconOpacity(f);
        }
    }

    public float B() {
        if (this.l != null) {
            return this.l.getDownIconOpacity();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return 1.0f;
    }

    public long i(ARObject aRObject) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return -1;
        } else if (aRObject != null) {
            return aRObject.getUid();
        } else {
            return -1;
        }
    }

    public void d(int i, int i2) {
        this.n.a(i, i2);
    }

    public void a(int i) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return;
        }
        this.g = i;
        this.l.setUpdateDistanceThreshold((float) i);
    }

    public int C() {
        return this.g;
    }

    public void e(int i, int i2) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setFilterSize(i, i2);
        }
    }

    public int b(int i) {
        if (this.l != null) {
            return this.l.getFilterSize(i);
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return -1;
    }

    public void a(int i, float f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setFilterCoeff(i, f);
        }
    }

    public float c(int i) {
        if (this.l != null) {
            return this.l.getFilterCoeff(i);
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return -1.0f;
    }

    public void c(long j) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setIntroAnimationTime(j);
        }
    }

    public long D() {
        if (this.l != null) {
            return this.l.getIntroAnimationTime();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return -1;
    }

    public void d(long j) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setTiltUpMaxTime(j);
        }
    }

    public long E() {
        if (this.l != null) {
            return this.l.getTiltUpMaxTime();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return -1;
    }

    public void e(long j) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setTiltUpMinTime(j);
        }
    }

    public long F() {
        if (this.l != null) {
            return this.l.getTiltUpMinTime();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return -1;
    }

    public void a(a aVar, long j) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setAnimationDelay(aVar.ordinal(), j);
        }
    }

    public long a(a aVar) {
        if (this.l != null) {
            return this.l.getAnimationDelay(aVar.ordinal());
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return -1;
    }

    public void b(a aVar, long j) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setAnimationDuration(aVar.ordinal(), j);
        }
    }

    public long b(a aVar) {
        if (this.l != null) {
            return this.l.getAnimationDuration(aVar.ordinal());
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return -1;
    }

    public void a(a aVar, AnimationInterpolator animationInterpolator) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setAnimationInterpolator(aVar.ordinal(), animationInterpolator.ordinal());
        }
    }

    public AnimationInterpolator c(a aVar) {
        if (this.l != null) {
            return AnimationInterpolator.values()[this.l.getAnimationInterpolator(aVar.ordinal())];
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return AnimationInterpolator.LINEAR;
    }

    public void g(float f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setInfoAnimationMinWidthFactor(f);
        }
    }

    public float G() {
        return this.l.getInfoAnimationMinWidthFactor();
    }

    public void b(int i, float f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setFlyRotateDeg(i, f);
        }
    }

    public float d(int i) {
        if (this.l != null) {
            return this.l.getFlyRotateDeg(i);
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return -1.0f;
    }

    public void h(float f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setDownViewMinOpacity(f);
        }
    }

    public float H() {
        if (this.l != null) {
            return this.l.getDownViewMinOpacity();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return -1.0f;
    }

    public void i(float f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setDownViewMaxOpacity(f);
        }
    }

    public float I() {
        if (this.l != null) {
            return this.l.getDownViewMaxOpacity();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return -1.0f;
    }

    public void a(ProjectionType projectionType) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setProjectionType(-1, projectionType.ordinal());
        }
    }

    public ProjectionType J() {
        if (this.l != null) {
            return ProjectionType.values()[this.l.getProjectionType(-1)];
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return ProjectionType.NEAR_FAR;
    }

    public void j(float f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setSelectedItemOpacity(f);
        }
    }

    public float K() {
        if (this.l != null) {
            return this.l.getSelectedItemOpacity();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return -1.0f;
    }

    public void k(float f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setNonSelectedItemsOpacity(f);
        }
    }

    public float L() {
        if (this.l != null) {
            return this.l.getNonSelectedItemsOpacity();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return -1.0f;
    }

    public void a(float f, boolean z, boolean z2) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setMaxZoomScale(f, z, z2);
        }
    }

    public float M() {
        if (this.l != null) {
            return this.l.getMaxZoomScale();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return -1.0f;
    }

    public void l(float f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setMinPitchDownView(f);
        }
    }

    public float N() {
        if (this.l != null) {
            return this.l.getMinPitchDownView();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return -1.0f;
    }

    public void f(long j) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setSensorsWaitTimeout(j);
        }
    }

    public long O() {
        if (this.l != null) {
            return this.l.getSensorsWaitTimeout();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return -1;
    }

    public void a(PointF pointF, boolean z) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setScreenViewPoint(pointF, z);
        }
    }

    public PointF P() {
        if (this.l != null) {
            return this.l.getScreenViewPoint();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return null;
    }

    public void m(float f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setOcclusionOpacity(f);
        }
    }

    public float Q() {
        if (this.l != null) {
            return this.l.getOcclusionOpacity();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return 1.0f;
    }

    public void l(boolean z) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setInfoAnimationInUpViewOnly(z);
        }
    }

    public void m(boolean z) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else if (this.o.get()) {
            this.ap.a(this, Error.OPERATION_NOT_ALLOWED);
        } else {
            this.l.a(z);
        }
    }

    ARLayoutControl R() {
        return this.l;
    }

    public void a(OnCameraEnteredListener onCameraEnteredListener) {
        if (onCameraEnteredListener != null) {
            synchronized (this.r) {
                this.r.addIfAbsent(onCameraEnteredListener);
            }
        }
    }

    public void b(OnCameraEnteredListener onCameraEnteredListener) {
        if (onCameraEnteredListener != null) {
            synchronized (this.r) {
                this.r.remove(onCameraEnteredListener);
            }
        }
    }

    public void a(OnCameraExitedListener onCameraExitedListener) {
        if (onCameraExitedListener != null) {
            synchronized (this.s) {
                this.s.addIfAbsent(onCameraExitedListener);
            }
        }
    }

    public void b(OnCameraExitedListener onCameraExitedListener) {
        if (onCameraExitedListener != null) {
            synchronized (this.s) {
                this.s.remove(onCameraExitedListener);
            }
        }
    }

    public void a(OnMapEnteredListener onMapEnteredListener) {
        if (onMapEnteredListener != null) {
            synchronized (this.t) {
                this.t.addIfAbsent(onMapEnteredListener);
            }
        }
    }

    public void b(OnMapEnteredListener onMapEnteredListener) {
        if (onMapEnteredListener != null) {
            synchronized (this.t) {
                this.t.remove(onMapEnteredListener);
            }
        }
    }

    public void a(OnMapExitedListener onMapExitedListener) {
        if (onMapExitedListener != null) {
            synchronized (this.u) {
                this.u.addIfAbsent(onMapExitedListener);
            }
        }
    }

    public void b(OnMapExitedListener onMapExitedListener) {
        if (onMapExitedListener != null) {
            synchronized (this.u) {
                this.u.remove(onMapExitedListener);
            }
        }
    }

    public void a(OnPanListener onPanListener) {
        if (onPanListener != null) {
            synchronized (this.C) {
                this.C.addIfAbsent(onPanListener);
            }
        }
    }

    public void b(OnPanListener onPanListener) {
        if (onPanListener != null) {
            synchronized (this.C) {
                this.C.remove(onPanListener);
            }
        }
    }

    public void a(OnTapListener onTapListener) {
        if (onTapListener != null) {
            synchronized (this.D) {
                this.D.addIfAbsent(onTapListener);
            }
        }
    }

    public void b(OnTapListener onTapListener) {
        if (onTapListener != null) {
            synchronized (this.D) {
                this.D.remove(onTapListener);
            }
        }
    }

    public void a(OnTouchDownListener onTouchDownListener) {
        if (onTouchDownListener != null) {
            synchronized (this.E) {
                this.E.addIfAbsent(onTouchDownListener);
            }
        }
    }

    public void b(OnTouchDownListener onTouchDownListener) {
        if (onTouchDownListener != null) {
            synchronized (this.E) {
                this.E.remove(onTouchDownListener);
            }
        }
    }

    public void a(OnTouchUpListener onTouchUpListener) {
        if (onTouchUpListener != null) {
            synchronized (this.F) {
                this.F.addIfAbsent(onTouchUpListener);
            }
        }
    }

    public void b(OnTouchUpListener onTouchUpListener) {
        if (onTouchUpListener != null) {
            synchronized (this.F) {
                this.F.remove(onTouchUpListener);
            }
        }
    }

    public void a(OnCompassCalibrationChangedListener onCompassCalibrationChangedListener) {
        if (onCompassCalibrationChangedListener != null) {
            synchronized (this.x) {
                this.x.addIfAbsent(onCompassCalibrationChangedListener);
            }
        }
    }

    public void b(OnCompassCalibrationChangedListener onCompassCalibrationChangedListener) {
        if (onCompassCalibrationChangedListener != null) {
            synchronized (this.x) {
                this.x.remove(onCompassCalibrationChangedListener);
            }
        }
    }

    public void a(OnSensorCalibrationChangedListener onSensorCalibrationChangedListener) {
        if (onSensorCalibrationChangedListener != null) {
            synchronized (this.J) {
                this.J.addIfAbsent(onSensorCalibrationChangedListener);
            }
        }
    }

    public void b(OnSensorCalibrationChangedListener onSensorCalibrationChangedListener) {
        if (onSensorCalibrationChangedListener != null) {
            synchronized (this.J) {
                this.J.remove(onSensorCalibrationChangedListener);
            }
        }
    }

    public void a(OnObjectTappedListener onObjectTappedListener) {
        if (onObjectTappedListener != null) {
            synchronized (this.G) {
                this.G.addIfAbsent(onObjectTappedListener);
            }
        }
    }

    public void b(OnObjectTappedListener onObjectTappedListener) {
        if (onObjectTappedListener != null) {
            synchronized (this.G) {
                this.G.remove(onObjectTappedListener);
            }
        }
    }

    public void a(OnRadarUpdateListener onRadarUpdateListener) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else if (onRadarUpdateListener != null) {
            synchronized (this.H) {
                this.H.addIfAbsent(onRadarUpdateListener);
                if (!this.H.isEmpty()) {
                    this.l.enableRadar(true);
                }
            }
        }
    }

    public void b(OnRadarUpdateListener onRadarUpdateListener) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else if (onRadarUpdateListener != null) {
            synchronized (this.H) {
                this.H.remove(onRadarUpdateListener);
                if (this.H.isEmpty()) {
                    this.l.enableRadar(false);
                }
            }
        }
    }

    public void a(OnPoseListener onPoseListener) {
        if (onPoseListener != null) {
            synchronized (this.I) {
                this.I.addIfAbsent(onPoseListener);
            }
        }
    }

    public void b(OnPoseListener onPoseListener) {
        if (onPoseListener != null) {
            synchronized (this.I) {
                this.I.remove(onPoseListener);
            }
        }
    }

    public void a(OnPreDrawListener onPreDrawListener) {
        if (onPreDrawListener != null) {
            synchronized (this.y) {
                this.y.addIfAbsent(onPreDrawListener);
            }
        }
    }

    public void b(OnPreDrawListener onPreDrawListener) {
        if (onPreDrawListener != null) {
            synchronized (this.y) {
                this.y.remove(onPreDrawListener);
            }
        }
    }

    public void a(OnPreDrawMapListener onPreDrawMapListener) {
        if (onPreDrawMapListener != null) {
            synchronized (this.z) {
                this.z.addIfAbsent(onPreDrawMapListener);
            }
        }
    }

    public void b(OnPreDrawMapListener onPreDrawMapListener) {
        if (onPreDrawMapListener != null) {
            synchronized (this.z) {
                this.z.remove(onPreDrawMapListener);
            }
        }
    }

    public void a(OnPrePresentListener onPrePresentListener) {
        if (onPrePresentListener != null) {
            synchronized (this.A) {
                this.A.addIfAbsent(onPrePresentListener);
            }
        }
    }

    public void b(OnPrePresentListener onPrePresentListener) {
        if (onPrePresentListener != null) {
            synchronized (this.A) {
                this.A.remove(onPrePresentListener);
            }
        }
    }

    public void a(OnPostPresentListener onPostPresentListener) {
        if (onPostPresentListener != null) {
            synchronized (this.B) {
                this.B.addIfAbsent(onPostPresentListener);
            }
        }
    }

    public void b(OnPostPresentListener onPostPresentListener) {
        if (onPostPresentListener != null) {
            synchronized (this.B) {
                this.B.remove(onPostPresentListener);
            }
        }
    }

    public void a(OnLivesightStatusListener onLivesightStatusListener) {
        if (onLivesightStatusListener != null) {
            synchronized (this.K) {
                this.K.addIfAbsent(onLivesightStatusListener);
            }
        }
    }

    public void b(OnLivesightStatusListener onLivesightStatusListener) {
        if (onLivesightStatusListener != null) {
            synchronized (this.K) {
                this.K.remove(onLivesightStatusListener);
            }
        }
    }

    public void a(OnPitchFunction onPitchFunction) {
        if (onPitchFunction != null) {
            synchronized (this.c) {
                this.M = onPitchFunction;
            }
        }
    }

    public void S() {
        synchronized (this.c) {
            this.M = null;
        }
    }

    public void a(OnProjectionCameraUpdatedListener onProjectionCameraUpdatedListener) {
        if (onProjectionCameraUpdatedListener != null) {
            synchronized (this.L) {
                this.L.addIfAbsent(onProjectionCameraUpdatedListener);
            }
        }
    }

    public void b(OnProjectionCameraUpdatedListener onProjectionCameraUpdatedListener) {
        if (onProjectionCameraUpdatedListener != null) {
            synchronized (this.L) {
                this.L.remove(onProjectionCameraUpdatedListener);
            }
        }
    }

    void T() {
        Log.d(h.a, "+++ APP paused!!!");
        if (this.l != null) {
            this.l.pause();
        }
        if (this.m != null) {
            this.m.a(true);
        }
    }

    void U() {
        Log.d(h.a, "+++ APP resumed!!!");
        if (this.l != null) {
            this.l.resume();
        }
        if (this.m != null) {
            this.m.a(false);
        }
    }

    synchronized void V() {
        Log.d(h.a, "+++ APP destroy - IN!!!");
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.a.a();
            c.c();
            synchronized (this.b) {
                for (Long longValue : new ArrayList(this.N.keySet())) {
                    this.N.remove(Long.valueOf(longValue.longValue()));
                }
            }
            this.l.destroy();
            this.l.a(null);
            this.l = null;
            this.O.j();
            this.O = null;
            this.n = null;
            this.q.c.a();
            this.q.a.a();
            this.q.b.a();
            this.q = null;
            Log.d(h.a, "+++ APP destroy - OUT!!!");
        }
    }

    boolean W() {
        return this.o.get();
    }

    public void a(float f, boolean z) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else if (f < 0.0f) {
            this.ap.a(this, Error.INVALID_PARAMETERS);
        } else {
            this.l.setFixedAltitude(f, z);
        }
    }

    public float X() {
        if (this.l != null) {
            return this.l.getFixedAltitude();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return 0.0f;
    }

    public void n(float f) {
        ARSensors.a(f);
    }

    public float Y() {
        return ARSensors.c();
    }

    public void a(SensorType sensorType, boolean z) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return;
        }
        ARSensors f = this.l.f();
        if (f == null || !f.b()) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else if (!f.a(sensorType, z)) {
            this.ap.a(this, Error.INVALID_OPERATION);
        }
    }

    public void a(SensorType sensorType, double d, double d2, double d3, long j) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return;
        }
        ARSensors f = this.l.f();
        if (f == null || !f.b()) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            f.a(sensorType, d, d2, d3, j);
        }
    }

    public void n(boolean z) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setEdgeDetectionEnabled(z);
        }
    }

    public boolean Z() {
        if (this.l != null) {
            return this.l.isEdgeDetectionEnabled();
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return false;
    }

    public boolean a(float f, PointF pointF, Vector3f vector3f) {
        if (this.l != null) {
            return this.l.pixelTo3dPosition(f, pointF, vector3f);
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return false;
    }

    public boolean a(GeoCoordinate geoCoordinate, Vector3f vector3f) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
            return false;
        } else if (geoCoordinate != null && geoCoordinate.isValid()) {
            return this.l.geoTo3dPosition(GeoCoordinateImpl.get(geoCoordinate), vector3f);
        } else {
            this.ap.a(this, Error.INVALID_PARAMETERS);
            return false;
        }
    }

    public void a(IntroAnimationMode introAnimationMode) {
        if (this.l == null) {
            this.ap.a(this, Error.INVALID_OPERATION);
        } else {
            this.l.setIntroAnimationMode(introAnimationMode.ordinal());
        }
    }

    public IntroAnimationMode aa() {
        if (this.l != null) {
            return IntroAnimationMode.values()[this.l.getIntroAnimationMode()];
        }
        this.ap.a(this, Error.INVALID_OPERATION);
        return IntroAnimationMode.values()[0];
    }
}
