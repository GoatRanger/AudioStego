package com.nokia.maps;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.MotionEventCompat;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import com.here.android.mpa.common.ViewRect;
import com.here.android.mpa.mapping.Map$Animation;
import com.here.android.mpa.mapping.Map$Projection;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapGesture.OnGestureListener;
import com.nokia.maps.MapGestureHandler.MapUserInteractionListener;
import com.nokia.maps.MapGestureHandler.Priority;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.publics.control.rc.b;
import dji.pilot.visual.a.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class bp extends w implements MapGestureHandler {
    private static float M;
    private static float N;
    private static double O = 1.0d;
    private static double P = 1.0d;
    private static final String a = bp.class.getName();
    private static float ab = 10.0f;
    private static float ac = 0.9375f;
    private static float ad = 2.0f;
    private static int ae = 50;
    private static int af = 15;
    private static int aq = 25;
    private static float ar = 10.0f;
    private static int x = 300;
    private float A = 0.01f;
    private float B = (this.A + 1.0f);
    private float C = (1.0f - this.A);
    private float D = 30.0f;
    private float E = 0.001f;
    private float F = 0.2f;
    private float G = DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMaxVelocity;
    private float H = 20.0f;
    private boolean I;
    private boolean J = false;
    private long K = 1000;
    private double L = 5.0d;
    private boolean Q;
    private boolean R;
    private boolean S;
    private boolean T;
    private boolean U;
    private boolean V;
    private boolean W;
    private boolean X;
    private boolean Y;
    private boolean Z;
    private CopyOnWriteArrayList<MapUserInteractionListener> aA = new CopyOnWriteArrayList();
    private List<cl> aB = new ArrayList(50);
    private long aC = 0;
    private float aD;
    private float aE;
    private float aF;
    private boolean aa = false;
    private float ag = 0.0f;
    private float ah = 1.0f;
    private float ai = 0.0f;
    private float aj = 1.0f;
    private boolean ak = false;
    private boolean al = false;
    private PointF am = new PointF();
    private boolean an = true;
    private boolean ao = true;
    private long ap = 0;
    private final float as = 1.1f;
    private final float at = 0.9f;
    private long au;
    private long av;
    private float aw = 0.0f;
    private float ax = 0.0f;
    private Map$Projection ay;
    private CopyOnWriteArrayList<OnGestureListener> az = new CopyOnWriteArrayList();
    private MapImpl b;
    private long c = 100;
    private boolean d = false;
    private boolean q = false;
    private List<en> r;
    private cl s = new cl();
    private Pair<en, en> t;
    private boolean u;
    private boolean v;
    private GestureDetector w;
    private boolean y = false;
    private double z = 30.0d;

    private class a extends SimpleOnGestureListener {
        final /* synthetic */ bp a;
        private List<Timer> b;

        private class a extends TimerTask {
            final /* synthetic */ a a;
            private final PointF b;
            private final Timer c;

            public a(a aVar, PointF pointF, Timer timer) {
                this.a = aVar;
                this.b = new PointF(pointF.x, pointF.y);
                this.c = timer;
            }

            public void run() {
                if (!(this.a.a.u || this.a.a.v)) {
                    ez.a(new Runnable(this) {
                        final /* synthetic */ a a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            if (!this.a.a.a.a(this.a.b)) {
                                this.a.a.a.b.a(this.a.a.a.b.b(this.a.b), Map$Animation.LINEAR);
                                this.a.a.a.y = true;
                            }
                        }
                    });
                }
                this.a.b.remove(this.c);
                this.c.cancel();
            }
        }

        private a(bp bpVar) {
            this.a = bpVar;
            this.b = new CopyOnWriteArrayList();
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (this.a.W) {
                for (Timer cancel : this.b) {
                    cancel.cancel();
                }
                this.b.clear();
                PointF pointF = new PointF(motionEvent.getX(), motionEvent.getY());
                if (!this.a.b(pointF)) {
                    this.a.b.a(this.a.b.b(pointF), Map$Animation.LINEAR, this.a.b.getZoomLevel() + bp.P, -1.0f, -1.0f);
                    this.a.y = true;
                }
            }
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (this.a.X && !this.a.u && !this.a.v) {
                this.a.c(new PointF(motionEvent.getX(), motionEvent.getY()));
                this.a.I = true;
                this.a.a();
            }
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (this.a.V) {
                Timer timer = new Timer();
                timer.schedule(new a(this, new PointF(motionEvent.getX(), motionEvent.getY()), timer), (long) bp.x);
                this.b.add(timer);
            }
            return true;
        }
    }

    public bp(MapImpl mapImpl, Context context) {
        this.b = mapImpl;
        M = this.b.getMinTilt();
        N = this.b.getMaxTilt();
        this.h = new en(-1.0f, -1.0f, -1);
        this.r = new CopyOnWriteArrayList();
        this.u = false;
        this.v = false;
        this.R = true;
        this.w = new GestureDetector(context, new a());
        this.Q = true;
        this.S = true;
        this.T = true;
        this.U = true;
        this.V = true;
        this.W = true;
        this.X = true;
        this.Y = true;
        this.Z = false;
        this.p = new an(new PointF(0.0f, 0.0f), new PointF(0.0f, 0.0f), new PointF(1.0f, 0.0f), new PointF(1.0f, 1.0f));
    }

    public boolean a(MotionEvent motionEvent) {
        boolean onTouchEvent;
        if (motionEvent.getPointerCount() == 1 && this.t == null) {
            onTouchEvent = this.w.onTouchEvent(motionEvent);
        } else {
            onTouchEvent = false;
        }
        if (motionEvent.getAction() == 1) {
            this.av = motionEvent.getEventTime() - motionEvent.getDownTime();
        }
        if (motionEvent.getPointerCount() > 2 || onTouchEvent) {
            return onTouchEvent;
        }
        if (motionEvent.getAction() == 0) {
            return b(motionEvent);
        }
        if (motionEvent.getAction() == 2) {
            return g(motionEvent);
        }
        if (motionEvent.getAction() == 1) {
            return c(motionEvent);
        }
        if (motionEvent.getAction() == 3) {
            return p(motionEvent);
        }
        if (motionEvent.getAction() == 5 || motionEvent.getAction() == 261) {
            return e(motionEvent);
        }
        if (motionEvent.getAction() == 6 || motionEvent.getAction() == b.j) {
            return f(motionEvent);
        }
        bj.e(a, "Unhandled event", new Object[0]);
        return onTouchEvent;
    }

    private boolean b(MotionEvent motionEvent) {
        this.h.a(motionEvent.getX(), motionEvent.getY(), motionEvent.getPointerId(0));
        this.i = motionEvent.getEventTime();
        this.u = false;
        this.v = false;
        this.y = false;
        this.t = null;
        this.aw = 0.0f;
        this.ax = 0.0f;
        this.ay = this.b.o();
        this.ag = 0.0f;
        this.ah = 1.0f;
        this.ai = 0.0f;
        this.aj = 1.0f;
        this.ak = true;
        this.al = false;
        n();
        a();
        return true;
    }

    private boolean c(MotionEvent motionEvent) {
        boolean z = false;
        if (this.j) {
            this.j = false;
            if (this.R && !this.d) {
                a_();
            }
            if (!this.j) {
                x();
            }
        } else if (this.J) {
            d(motionEvent);
            this.J = false;
        }
        if (this.I) {
            y();
            this.I = false;
        }
        if (!this.j) {
            this.h = new en(-1.0f, -1.0f, -1);
        }
        if (motionEvent.getEventTime() - this.ap < 100) {
            if (!(this.j || this.ak || !this.T)) {
                z = f();
                if (!(z || this.y)) {
                    E();
                }
            }
            if (!(this.j || this.al || r0 || !this.S)) {
                i();
            }
        } else if (!this.y) {
            E();
        }
        a();
        this.t = null;
        return true;
    }

    private void d(MotionEvent motionEvent) {
        if (this.Y && !d(new PointF(motionEvent.getX(), motionEvent.getY()))) {
            this.y = true;
            this.b.a(this.b.getZoomLevel() - O, Map$Animation.LINEAR);
        }
    }

    private boolean e(MotionEvent motionEvent) {
        int i = 0;
        if (motionEvent.getPointerCount() == 2) {
            z();
            D();
        }
        e();
        this.J = true;
        this.u = true;
        this.t = new Pair(new en(motionEvent.getX(0), motionEvent.getY(0), 0), new en(motionEvent.getX(1), motionEvent.getY(1), 1));
        this.am = new PointF();
        int pointerCount = motionEvent.getPointerCount();
        while (i < pointerCount) {
            int pointerId = motionEvent.getPointerId(i);
            float x = motionEvent.getX(i);
            float y = motionEvent.getY(i);
            this.r.add(new en(x, y, pointerId));
            PointF pointF = this.am;
            pointF.x = x + pointF.x;
            pointF = this.am;
            pointF.y += y;
            i++;
        }
        a();
        PointF pointF2 = this.am;
        pointF2.x /= (float) pointerCount;
        pointF2 = this.am;
        pointF2.y /= (float) pointerCount;
        this.ap = motionEvent.getEventTime();
        return true;
    }

    private boolean f(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 2) {
            A();
        }
        if (this.d || this.q) {
            if (motionEvent.getPointerCount() == 2) {
                int i = ((motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8) == 0 ? 1 : 0;
                this.h = new en(motionEvent.getX(i), motionEvent.getY(i), motionEvent.findPointerIndex(i));
            } else {
                this.h = new en(-1.0f, -1.0f, -1);
            }
        }
        this.r.clear();
        this.d = false;
        this.q = false;
        a();
        this.au = System.currentTimeMillis();
        return true;
    }

    private boolean g(MotionEvent motionEvent) {
        boolean z = false;
        int pointerCount = motionEvent.getPointerCount();
        if (pointerCount == 1) {
            z = h(motionEvent);
        } else if (pointerCount > 1) {
            z = i(motionEvent);
        }
        a();
        return z;
    }

    private boolean h(MotionEvent motionEvent) {
        if (!this.Q) {
            return true;
        }
        if (this.h == null) {
            return true;
        }
        if (this.y) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.au < 100) {
            return true;
        }
        int findPointerIndex = motionEvent.findPointerIndex(this.h.b());
        if (findPointerIndex != -1) {
            float x = motionEvent.getX(findPointerIndex);
            float y = motionEvent.getY(findPointerIndex);
            if (!this.j) {
                ViewRect e = this.b.e(this.h);
                if (x > ((float) e.getX()) && x < ((float) (e.getX() + e.getWidth())) && y > ((float) e.getY())) {
                    if (y < ((float) (e.getHeight() + e.getY()))) {
                        return true;
                    }
                }
                if (!this.h.a()) {
                    return true;
                }
                this.j = true;
                w();
            }
            float f = (float) (currentTimeMillis - this.i);
            float f2 = x - this.h.x;
            float f3 = y - this.h.y;
            a(f2 / f, f3 / f);
            if (this.ay == Map$Projection.GLOBE && this.b.getZoomLevel() <= 3.0d) {
                this.aw += Math.abs(f2);
                this.ax += Math.abs(f3);
                findPointerIndex = this.b.b() >> 3;
                int c = this.b.c() >> 3;
                if (this.ax <= ((float) c) || this.aw <= ((float) findPointerIndex)) {
                    if (this.ax > ((float) c)) {
                        f2 = this.h.x;
                        f = y;
                    } else if (this.aw > ((float) findPointerIndex) || this.aw < DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity || this.ax < DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) {
                        f = this.h.y;
                        f2 = x;
                    }
                    this.b.a(this.h, new en(f2, f, this.h.b()));
                    this.h = new en(x, y, this.h.b());
                    this.i = currentTimeMillis;
                    if (this.b.m()) {
                        dw.a(this.b.n()).a(false);
                    }
                } else {
                    f = y;
                    f2 = x;
                    this.b.a(this.h, new en(f2, f, this.h.b()));
                    this.h = new en(x, y, this.h.b());
                    this.i = currentTimeMillis;
                    if (this.b.m()) {
                        dw.a(this.b.n()).a(false);
                    }
                }
            }
            f = y;
            f2 = x;
            this.b.a(this.h, new en(f2, f, this.h.b()));
            this.h = new en(x, y, this.h.b());
            this.i = currentTimeMillis;
            if (this.b.m()) {
                dw.a(this.b.n()).a(false);
            }
        } else {
            this.j = false;
            this.h = new en(-1.0f, -1.0f, -1);
        }
        return true;
    }

    private boolean i(MotionEvent motionEvent) {
        int i = 0;
        this.s.a();
        this.s.i = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
        this.s.j = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
        long eventTime = motionEvent.getEventTime();
        if (!(this.d || this.q)) {
            j(motionEvent);
        }
        if (this.q && this.U) {
            n(motionEvent);
        }
        if (this.d) {
            if (this.S) {
                k(motionEvent);
            }
            if (this.Q) {
                m(motionEvent);
            }
            if (this.T) {
                l(motionEvent);
            }
        }
        if (!this.an) {
            this.s.c = false;
        }
        if (eventTime - this.ap > 100) {
            g();
            j();
        }
        if (this.ao && this.S && this.T) {
            a(this.s);
        } else {
            if (eventTime - this.ap > 200) {
                this.al = false;
                this.ak = false;
                this.ag = 0.0f;
                this.ah = 1.0f;
            }
            if (this.ak && Math.abs(this.ai) > ((float) aq)) {
                this.al = false;
                this.ak = false;
                this.ag = 0.0f;
                this.ah = 1.0f;
            }
            if (this.al && ((double) this.aj) > 1.5d) {
                this.al = false;
                this.ak = false;
                this.ag = 0.0f;
                this.ah = 1.0f;
            }
            if (this.al && ((double) this.aj) < 0.5d) {
                this.al = false;
                this.ak = false;
                this.ag = 0.0f;
                this.ah = 1.0f;
            }
            if (!(Math.abs(this.ag) <= ar || this.ak || this.al)) {
                this.s.a = false;
                this.al = true;
                C();
                this.ai = 0.0f;
                this.aj = 1.0f;
            }
            if (!(1.1f >= this.ah || this.al || this.ak)) {
                this.s.b = false;
                this.ak = true;
                B();
                this.ai = 0.0f;
                this.aj = 1.0f;
            }
            if (!(0.9f <= this.ah || this.al || this.ak)) {
                this.s.b = false;
                this.ak = true;
                B();
                this.ai = 0.0f;
                this.aj = 1.0f;
            }
        }
        if (this.ak) {
            this.s.f = 0.0f;
        }
        if (this.al) {
            this.s.d = 1.0d;
        }
        u();
        if (this.Y) {
            o(motionEvent);
        }
        this.r.clear();
        int pointerCount = motionEvent.getPointerCount();
        while (i < pointerCount) {
            int pointerId = motionEvent.getPointerId(i);
            this.r.add(new en(motionEvent.getX(i), motionEvent.getY(i), pointerId));
            i++;
        }
        this.ap = eventTime;
        return true;
    }

    private void j(MotionEvent motionEvent) {
        int i = 0;
        if (this.t != null) {
            boolean z;
            boolean z2;
            boolean z3;
            en enVar = new en(motionEvent.getX(0), motionEvent.getY(0), 0);
            en enVar2 = new en(motionEvent.getX(1), motionEvent.getY(1), 1);
            en enVar3 = (en) this.t.first;
            en enVar4 = (en) this.t.second;
            if (Math.abs(enVar.c(enVar2) - enVar3.c(enVar4)) > this.z) {
                z = true;
            } else {
                z = false;
            }
            if (Math.abs(en.a(enVar3, enVar4, enVar, enVar2)) > ((double) this.D)) {
                z2 = true;
            } else {
                z2 = false;
            }
            float e = enVar.e(enVar3);
            float e2 = enVar2.e(enVar4);
            float d = enVar.d(enVar3);
            float d2 = enVar2.d(enVar4);
            boolean z4 = e < 0.0f;
            if (e2 < 0.0f) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z4 == z3) {
                if (d < 0.0f) {
                    z4 = true;
                } else {
                    z4 = false;
                }
                if (d2 < 0.0f) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (z4 == z3 || Math.abs(d + d2) < this.H) {
                    float f = (e + e2) / 2.0f;
                    if (Math.abs((d + d2) / 2.0f) > this.G && Math.abs(f) < this.H) {
                        i = 1;
                    }
                }
            }
            if (z != z2 && r4 == 0) {
                this.d = true;
            } else if (!(z || z2 || r4 == 0)) {
                this.q = true;
            }
            a();
        }
    }

    private void k(MotionEvent motionEvent) {
        if (this.r.size() >= 2 && !this.q && this.d) {
            en enVar = new en(motionEvent.getX(0), motionEvent.getY(0), 0);
            en enVar2 = new en(motionEvent.getX(1), motionEvent.getY(1), 1);
            double c = enVar.c(enVar2) / ((en) this.r.get(0)).c((en) this.r.get(1));
            double abs = Math.abs(c);
            if (!this.ak) {
                this.ah = (float) (((double) this.ah) * c);
            }
            if (this.al) {
                this.aj = (float) (((double) this.aj) * c);
            }
            if (abs > ((double) this.B) || abs < ((double) this.C) || ((double) this.ah) < 0.95d || ((double) this.ah) > 1.05d) {
                PointF pointF = new PointF((enVar.x + enVar2.x) * d.c, (enVar.y + enVar2.y) * d.c);
                this.s.a = true;
                this.s.d = c;
                this.s.e = pointF;
            }
        }
    }

    private float b(double d) {
        if (d < 0.0d) {
            return (float) (360.0d + d);
        }
        return (float) d;
    }

    private void l(MotionEvent motionEvent) {
        if (this.r.size() >= 2 && !this.q) {
            en enVar;
            en enVar2;
            if (this.d) {
                enVar = (en) this.r.get(0);
                enVar2 = (en) this.r.get(1);
            } else {
                enVar = (en) this.t.first;
                enVar2 = (en) this.t.second;
            }
            en enVar3 = new en(motionEvent.getX(0), motionEvent.getY(0), 0);
            en enVar4 = new en(motionEvent.getX(1), motionEvent.getY(1), 1);
            float b = b(enVar.b(enVar2));
            float b2 = b(enVar3.b(enVar4));
            if (b > 270.0f && b2 < 90.0f) {
                b = (360.0f - b) + b2;
            } else if (b >= 90.0f || b2 <= 270.0f) {
                b = b2 - b;
            } else {
                b = (b + (360.0f - b2)) * -1.0f;
            }
            if (Math.abs(en.a(enVar, enVar2, enVar3, enVar4)) > ((double) this.E)) {
                this.s.b = true;
                this.s.f = b;
                if (!this.al) {
                    this.ag += b;
                }
                if (this.ak) {
                    this.ai += b;
                }
            }
        }
    }

    private void m(MotionEvent motionEvent) {
        if (this.Q && !this.q && this.r.size() >= 2) {
            en enVar = new en(motionEvent.getX(0), motionEvent.getY(0), 0);
            en enVar2 = new en(motionEvent.getX(1), motionEvent.getY(1), 1);
            en enVar3 = (en) this.r.get(0);
            en enVar4 = (en) this.r.get(1);
            float f = ((enVar.x - enVar3.x) + (enVar2.x - enVar4.x)) / 2.0f;
            float f2 = ((enVar.y - enVar3.y) + (enVar2.y - enVar4.y)) / 2.0f;
            if (Math.abs(f) >= 1.0f || Math.abs(f2) >= 1.0f) {
                this.s.c = true;
                this.s.g = f;
                this.s.h = f2;
            }
        }
    }

    private void u() {
        if (!this.q) {
            boolean a;
            if (this.s.a) {
                a = a((float) this.s.d, this.s.e);
            } else {
                a = false;
            }
            boolean c;
            if (this.s.b) {
                c = c(this.s.f);
            } else {
                c = false;
            }
            if (this.s.c && !this.j) {
                this.j = true;
                w();
            }
            if (!this.s.a || r0) {
                this.s.d = 1.0d;
            }
            if (!this.s.b || r1) {
                this.s.f = 0.0f;
            }
            if (this.d && ((this.s.a && !r0) || ((this.s.b && !r1) || this.s.c))) {
                if (this.s.b) {
                    a(this.s.f);
                }
                if (this.s.a) {
                    a(this.s.d);
                }
                v();
                PointF d = this.b.d();
                float f = d.x;
                float f2 = d.y;
                if (this.s.c) {
                    if (this.aa) {
                        this.b.a(f, f2, this.s.g, this.s.h, (float) this.s.d, this.s.f);
                    } else {
                        this.b.a(this.s.i, this.s.j, this.s.g, this.s.h, (float) this.s.d, this.s.f);
                    }
                } else if (this.aa) {
                    this.b.a(f, f2, 0.0f, 0.0f, (float) this.s.d, this.s.f);
                } else {
                    this.b.a(this.am.x, this.am.y, 0.0f, 0.0f, (float) this.s.d, this.s.f);
                }
                if (this.b.m()) {
                    dw.a(this.b.n()).a(false);
                }
                this.J = false;
            }
            if (this.s.c) {
                this.i = System.currentTimeMillis();
                this.h = (en) this.r.get(0);
            }
        }
    }

    private void v() {
        float zoomLevel = (float) this.b.getZoomLevel();
        if (this.s.a && zoomLevel < 11.0f) {
            zoomLevel = (float) ((((4.0E-4d * ((double) zoomLevel)) * ((double) zoomLevel)) - (((double) zoomLevel) * 0.0122d)) + 1.0998d);
            cl clVar;
            if (this.s.d > 1.0d) {
                clVar = this.s;
                clVar.d *= (double) zoomLevel;
                return;
            }
            clVar = this.s;
            clVar.d /= (double) zoomLevel;
        }
    }

    private void n(MotionEvent motionEvent) {
        if (this.r.size() >= 2 && !this.d) {
            en enVar;
            en enVar2;
            if (this.q) {
                enVar = (en) this.r.get(0);
                enVar2 = (en) this.r.get(1);
            } else {
                enVar = (en) this.t.first;
                enVar2 = (en) this.t.second;
            }
            float d = (new en(motionEvent.getX(0), motionEvent.getY(0), 0).d(enVar) + new en(motionEvent.getX(1), motionEvent.getY(1), 1).d(enVar2)) / 2.0f;
            float tilt = this.b.getTilt();
            d = (d * this.F) * -1.0f;
            if (!d(d)) {
                d = tilt - d;
                if (d > N) {
                    d = N;
                } else if (d < M) {
                    d = M;
                }
                this.b.b(d);
            }
        }
    }

    private void o(MotionEvent motionEvent) {
        if (!this.J) {
            return;
        }
        if (motionEvent.getEventTime() - this.i > this.K || ((en) this.t.first).c((en) this.r.get(0)) > this.L || ((en) this.t.second).c((en) this.r.get(1)) > this.L) {
            this.J = false;
        }
    }

    private boolean p(MotionEvent motionEvent) {
        return c(motionEvent);
    }

    protected void e() {
        boolean z = this.j;
        super.e();
        if (z) {
            x();
            this.v = true;
        }
        a();
    }

    protected void a(en enVar, en enVar2) {
        this.b.a(enVar, enVar2);
    }

    protected void a(float f, float f2, float f3, float f4) {
        float b;
        float c;
        if (f3 == 1.0f && f4 == 0.0f) {
            b = (float) (this.b.b() >> 1);
            c = (float) (this.b.c() >> 1);
        } else {
            b = this.am.x;
            c = this.am.y;
        }
        this.b.a(b, c, f, f2, f3, f4);
    }

    protected void h() {
        E();
    }

    public void a(MapUserInteractionListener mapUserInteractionListener) {
        if (mapUserInteractionListener != null) {
            this.aA.addIfAbsent(mapUserInteractionListener);
        }
    }

    public void b(MapUserInteractionListener mapUserInteractionListener) {
        if (mapUserInteractionListener != null) {
            this.aA.remove(mapUserInteractionListener);
        }
    }

    private void a(boolean z) {
        Iterator it = this.aA.iterator();
        while (it.hasNext()) {
            ((MapUserInteractionListener) it.next()).a(z);
        }
    }

    public void addOnGestureListener(OnGestureListener onGestureListener) {
        if (onGestureListener != null) {
            this.az.addIfAbsent(onGestureListener);
        }
    }

    public void removeOnGestureListener(OnGestureListener onGestureListener) {
        if (onGestureListener != null) {
            this.az.remove(onGestureListener);
        }
    }

    private void w() {
        Runnable anonymousClass1 = new Runnable(this) {
            final /* synthetic */ bp a;

            {
                this.a = r1;
            }

            public void run() {
                Iterator it = this.a.az.iterator();
                while (it.hasNext()) {
                    ((OnGestureListener) it.next()).onPanStart();
                }
            }
        };
        if (MapSettings.l()) {
            ez.a(anonymousClass1);
        } else {
            anonymousClass1.run();
        }
    }

    private void x() {
        Runnable anonymousClass2 = new Runnable(this) {
            final /* synthetic */ bp a;

            {
                this.a = r1;
            }

            public void run() {
                Iterator it = this.a.az.iterator();
                while (it.hasNext()) {
                    ((OnGestureListener) it.next()).onPanEnd();
                }
            }
        };
        if (MapSettings.l()) {
            ez.a(anonymousClass2);
        } else {
            anonymousClass2.run();
        }
    }

    private boolean a(PointF pointF) {
        boolean z = false;
        List a = this.b.a(this.b.e(pointF));
        ViewObjectImpl.a(a, this.b.d(pointF));
        if (a != null && a.size() > 0) {
            Iterator it = this.az.iterator();
            while (it.hasNext()) {
                z = ((OnGestureListener) it.next()).onMapObjectsSelected(a);
                if (z) {
                    break;
                }
            }
        }
        if (!z) {
            Iterator it2 = this.az.iterator();
            while (it2.hasNext()) {
                z = ((OnGestureListener) it2.next()).onTapEvent(pointF);
                if (z) {
                    break;
                }
            }
        }
        return z;
    }

    private boolean b(PointF pointF) {
        boolean z = false;
        Iterator it = this.az.iterator();
        while (it.hasNext()) {
            z = ((OnGestureListener) it.next()).onDoubleTapEvent(pointF);
            if (z) {
                break;
            }
        }
        return z;
    }

    private boolean a(float f, PointF pointF) {
        boolean z = false;
        Iterator it = this.az.iterator();
        while (it.hasNext()) {
            z = ((OnGestureListener) it.next()).onPinchZoomEvent(f, pointF);
            if (z) {
                break;
            }
        }
        return z;
    }

    private boolean c(float f) {
        boolean z = false;
        Iterator it = this.az.iterator();
        while (it.hasNext()) {
            z = ((OnGestureListener) it.next()).onRotateEvent(f);
            if (z) {
                break;
            }
        }
        return z;
    }

    private boolean d(float f) {
        boolean z = false;
        Iterator it = this.az.iterator();
        while (it.hasNext()) {
            z = ((OnGestureListener) it.next()).onTiltEvent(f);
            if (z) {
                break;
            }
        }
        return z;
    }

    private boolean c(PointF pointF) {
        boolean z = false;
        Iterator it = this.az.iterator();
        while (it.hasNext()) {
            z = ((OnGestureListener) it.next()).onLongPressEvent(pointF);
            if (z) {
                break;
            }
        }
        return z;
    }

    private void y() {
        Iterator it = this.az.iterator();
        while (it.hasNext()) {
            ((OnGestureListener) it.next()).onLongPressRelease();
        }
    }

    private boolean d(PointF pointF) {
        boolean z = false;
        Iterator it = this.az.iterator();
        while (it.hasNext()) {
            z = ((OnGestureListener) it.next()).onTwoFingerTapEvent(pointF);
            if (z) {
                break;
            }
        }
        return z;
    }

    private void z() {
        Iterator it = this.az.iterator();
        while (it.hasNext()) {
            ((OnGestureListener) it.next()).onMultiFingerManipulationStart();
        }
    }

    private void A() {
        Iterator it = this.az.iterator();
        while (it.hasNext()) {
            ((OnGestureListener) it.next()).onMultiFingerManipulationEnd();
        }
    }

    private void B() {
        Iterator it = this.az.iterator();
        while (it.hasNext()) {
            ((OnGestureListener) it.next()).onPinchLocked();
        }
    }

    private void C() {
        Iterator it = this.az.iterator();
        while (it.hasNext()) {
            ((OnGestureListener) it.next()).onRotateLocked();
        }
    }

    public MapGesture setPanningEnabled(boolean z) {
        this.Q = z;
        return this;
    }

    public boolean isPanningEnabled() {
        return this.Q;
    }

    public MapGesture setKineticFlickEnabled(boolean z) {
        this.R = z;
        return this;
    }

    public boolean isKineticFlickEnabled() {
        return this.R;
    }

    public MapGesture setPinchEnabled(boolean z) {
        this.S = z;
        return this;
    }

    public boolean isPinchEnabled() {
        return this.S;
    }

    public MapGesture setRotateEnabled(boolean z) {
        this.T = z;
        return this;
    }

    public boolean isRotateEnabled() {
        return this.T;
    }

    public MapGesture setTiltEnabled(boolean z) {
        this.U = z;
        return this;
    }

    public boolean isTiltEnabled() {
        return this.U;
    }

    public MapGesture setSingleTapEnabled(boolean z) {
        this.V = z;
        return this;
    }

    public boolean isSingleTapEnabled() {
        return this.V;
    }

    public MapGesture setDoubleTapEnabled(boolean z) {
        this.W = z;
        return this;
    }

    public boolean isDoubleTapEnabled() {
        return this.W;
    }

    public MapGesture setLongPressEnabled(boolean z) {
        this.X = z;
        return this;
    }

    public boolean isLongPressEnabled() {
        return this.X;
    }

    public MapGesture setTwoFingerTapEnabled(boolean z) {
        this.Y = z;
        return this;
    }

    public boolean isTwoFingerTapEnabled() {
        return this.Y;
    }

    public void a() {
        boolean z = this.j || this.I || this.d || this.q;
        if (this.Z != z) {
            this.Z = z;
            a(this.Z);
        }
    }

    public MapGesture setAllGesturesEnabled(boolean z) {
        setSingleTapEnabled(z);
        setDoubleTapEnabled(z);
        setPanningEnabled(z);
        setKineticFlickEnabled(z);
        setTiltEnabled(z);
        setLongPressEnabled(z);
        setPinchEnabled(z);
        setTwoFingerTapEnabled(z);
        setRotateEnabled(z);
        setTwoFingerPanningEnabled(z);
        setKineticFlickEnabled(z);
        return this;
    }

    public MapGesture setTwoFingerPanningEnabled(boolean z) {
        this.an = z;
        return this;
    }

    public boolean isTwoFingerPanningEnabled() {
        return this.an;
    }

    public MapGesture setFixedMapCenterOnMapRotateZoom(boolean z) {
        this.aa = z;
        return this;
    }

    public boolean isFixedMapCenterOnMapRotateZoom() {
        return this.aa;
    }

    private void D() {
        this.aC = System.currentTimeMillis();
        this.aB.clear();
    }

    private void a(cl clVar) {
        this.aB.add(new cl(clVar));
        if (System.currentTimeMillis() - this.aC >= 125) {
            int i = 0;
            int i2 = 0;
            double d = 1.0d;
            float f = 0.0f;
            for (cl clVar2 : this.aB) {
                int i3 = (int) (((float) i) + clVar2.g);
                i = (int) (((float) i2) + clVar2.h);
                d = clVar2.d * d;
                f = clVar2.f + f;
                i2 = i;
                i = i3;
            }
            if (((int) Math.sqrt((double) ((i * i) + (i2 * i2)))) > (((int) Math.sqrt((double) ((this.b.b() * this.b.b()) + (this.b.c() * this.b.c())))) >> 3)) {
                this.ak = false;
                this.al = false;
            } else if ((d > 1.0800000429153442d || d < 0.9200000166893005d) && !this.al && f < DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMaxVelocity) {
                this.ak = true;
                this.al = false;
            } else if (Math.abs(f) > DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMaxVelocity) {
                this.ak = false;
                this.al = true;
            } else if (d > 1.0800000429153442d || d < 0.9200000166893005d) {
                this.ak = true;
                this.al = false;
            } else {
                this.ak = false;
                this.al = false;
            }
            this.aC = System.currentTimeMillis();
            for (int size = this.aB.size() >> 1; size > 0; size--) {
                this.aB.remove(0);
            }
        }
        if (this.ak) {
            clVar.a = true;
            if (Math.abs(clVar.f) < 5.0f) {
                clVar.b = false;
                clVar.f = 0.0f;
            }
        } else if (this.al) {
            if (clVar.d < 1.0800000429153442d && clVar.d > 0.9200000166893005d) {
                clVar.a = false;
                clVar.d = 1.0d;
            }
            clVar.b = true;
        }
    }

    private void E() {
        float orientation = this.b.getOrientation();
        if (orientation != 0.0f && this.av > this.c) {
            if (orientation < 10.0f || orientation > 350.0f) {
                this.aD = orientation;
                this.aE = orientation;
                if (orientation < 10.0f) {
                    this.aF = orientation * -1.0f;
                } else {
                    this.aF = 360.0f - orientation;
                }
                p();
            }
        }
    }

    protected void b(float f) {
        float f2 = ((this.aF * f) + this.aD) - this.aE;
        this.aE += f2;
        this.b.a((float) (this.b.b() >> 1), (float) (this.b.c() >> 1), 0.0f, 0.0f, 1.0f, f2);
    }

    public void b() {
    }

    public void c() {
    }

    public void d() {
    }

    public void a(OnGestureListener onGestureListener, Priority priority) {
        addOnGestureListener(onGestureListener);
    }

    public void cancelKineticPanning() {
        n();
    }
}
