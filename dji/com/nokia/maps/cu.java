package com.nokia.maps;

import android.content.Context;
import android.graphics.PointF;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import com.here.android.mpa.streetlevel.StreetLevelGesture;
import com.here.android.mpa.streetlevel.StreetLevelGesture.OnGestureListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class cu extends w implements OnScaleGestureListener {
    private static int A = 50;
    private static int B = 15;
    private static int C = 0;
    private static boolean D = true;
    private static k<StreetLevelGesture, cu> E = null;
    private static am<StreetLevelGesture, cu> F = null;
    private static String b = cu.class.getSimpleName();
    private static int u = 300;
    private static float x = 10.0f;
    private static float y = 0.9375f;
    private static float z = 2.0f;
    private CopyOnWriteArrayList<OnGestureListener> G = new CopyOnWriteArrayList();
    private Pair<en, en> H = null;
    private float I = 0.001f;
    private List<Timer> J = new CopyOnWriteArrayList();
    private a K;
    private float L = 0.0f;
    private boolean M;
    private cq a = new cq(cu.class.getName());
    private ScaleGestureDetector c = null;
    private GestureDetector d = null;
    private boolean q = true;
    private boolean r = true;
    private boolean s = true;
    private boolean t = true;
    private PanoramaModelImpl v;
    private int w = 0;

    public interface a {
        void a();

        void a(float f);

        void a(float f, float f2);

        void a(PointF pointF);

        void a(PointF pointF, PointF pointF2);

        void b();

        void b(PointF pointF);

        void c(PointF pointF);
    }

    private class b extends TimerTask {
        final /* synthetic */ cu a;
        private final PointF b;
        private final Timer c;

        public b(cu cuVar, PointF pointF, Timer timer) {
            this.a = cuVar;
            this.b = new PointF(pointF.x, pointF.y);
            this.c = timer;
        }

        public void run() {
            this.a.K.a(this.b);
            this.a.J.remove(this.c);
            this.c.cancel();
        }
    }

    private class c extends SimpleOnGestureListener {
        final /* synthetic */ cu a;

        private c(cu cuVar) {
            this.a = cuVar;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (this.a.r) {
                Timer timer = new Timer();
                timer.schedule(new b(this.a, new PointF(motionEvent.getX(), motionEvent.getY()), timer), (long) cu.u);
                this.a.J.add(timer);
            }
            return true;
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (!this.a.t) {
                return false;
            }
            for (Timer cancel : this.a.J) {
                cancel.cancel();
            }
            this.a.J.clear();
            if (this.a.K != null) {
                this.a.K.b(new PointF(motionEvent.getX(), motionEvent.getY()));
            }
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (this.a.K != null) {
                this.a.K.c(new PointF(motionEvent.getX(), motionEvent.getY()));
            }
        }
    }

    static {
        ce.a(StreetLevelGesture.class);
    }

    public static void a(k<StreetLevelGesture, cu> kVar, am<StreetLevelGesture, cu> amVar) {
        E = kVar;
        F = amVar;
    }

    static StreetLevelGesture a(cu cuVar) {
        if (cuVar != null) {
            return (StreetLevelGesture) F.a(cuVar);
        }
        return null;
    }

    public cu(Context context) {
        this.c = new ScaleGestureDetector(context.getApplicationContext(), this);
        this.d = new GestureDetector(context.getApplicationContext(), new c());
        this.h = null;
        this.p = new an(new PointF(0.0f, 0.0f), new PointF(0.0f, 0.0f), new PointF(0.0f, 1.0f), new PointF(1.0f, 1.0f));
    }

    public void a(PanoramaModelImpl panoramaModelImpl) {
        this.v = panoramaModelImpl;
    }

    public boolean a(MotionEvent motionEvent) {
        boolean z;
        if (this.d.onTouchEvent(motionEvent)) {
            z = true;
        } else {
            z = false;
        }
        if (this.c.onTouchEvent(motionEvent)) {
            z = true;
        }
        if (motionEvent.getAction() == 0) {
            return j(motionEvent);
        }
        if (motionEvent.getAction() == 2) {
            return f(motionEvent);
        }
        if (motionEvent.getAction() == 1) {
            return e(motionEvent);
        }
        if (motionEvent.getAction() == 3) {
            return d(motionEvent);
        }
        if (motionEvent.getAction() == 5 || motionEvent.getAction() == 261) {
            return c(motionEvent);
        }
        if (motionEvent.getAction() == 6 || motionEvent.getAction() == dji.pilot.publics.control.rc.b.j) {
            return b(motionEvent);
        }
        System.out.printf("Unhandled event", new Object[0]);
        return z;
    }

    public void a(OnGestureListener onGestureListener) {
        if (onGestureListener != null) {
            this.G.addIfAbsent(onGestureListener);
        }
    }

    public void b(OnGestureListener onGestureListener) {
        if (onGestureListener != null) {
            this.G.remove(onGestureListener);
        }
    }

    public CopyOnWriteArrayList<OnGestureListener> a() {
        return this.G;
    }

    private boolean b(MotionEvent motionEvent) {
        this.w--;
        return false;
    }

    private boolean c(MotionEvent motionEvent) {
        this.w++;
        e();
        return false;
    }

    private boolean d(MotionEvent motionEvent) {
        this.w = 0;
        e();
        return false;
    }

    private boolean e(MotionEvent motionEvent) {
        boolean z = false;
        this.w--;
        if (this.w == 0 && this.q && this.H == null) {
            if (this.j) {
                this.j = false;
            }
            a_();
            if (q() >= 2.5f) {
                if (r() < 0.0f) {
                    z = true;
                }
                super.e();
                f(z);
            }
            if (!this.j) {
                this.K.b();
            }
        } else if (this.w == 1) {
            super.e();
        }
        return true;
    }

    private boolean f(MotionEvent motionEvent) {
        this.w = motionEvent.getPointerCount();
        if (this.w == 1) {
            return g(motionEvent);
        }
        if (this.w == 2) {
            return h(motionEvent);
        }
        return false;
    }

    private boolean g(MotionEvent motionEvent) {
        if (this.q && this.h != null) {
            int findPointerIndex = motionEvent.findPointerIndex(this.h.b());
            if (findPointerIndex != -1) {
                if (!this.j && this.h.a()) {
                    if (!(this.K == null || this.j)) {
                        this.K.a();
                    }
                    this.j = true;
                }
                if (this.j) {
                    float f;
                    float x = motionEvent.getX(findPointerIndex);
                    float y = motionEvent.getY(findPointerIndex);
                    float f2 = y - this.h.y;
                    if (D && Math.abs(C) > 100) {
                        D = false;
                        f = y;
                    } else if (D) {
                        f = this.h.y;
                        C = (int) (f2 + ((float) C));
                    } else {
                        f = y;
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    f2 = (float) (currentTimeMillis - this.i);
                    a((x - this.h.x) / f2, (f - this.h.y) / f2);
                    en enVar = new en(x, y, this.h.b());
                    if (this.K != null) {
                        this.K.a(this.h, new PointF(x, f));
                    }
                    this.h = enVar;
                    this.i = currentTimeMillis;
                }
            } else {
                this.j = false;
                this.h = new en(-1.0f, -1.0f, -1);
            }
        }
        return true;
    }

    private boolean h(MotionEvent motionEvent) {
        return i(motionEvent);
    }

    private boolean i(MotionEvent motionEvent) {
        if (this.v.getPitch() > -85.0f) {
            this.H = null;
        }
        en enVar = new en(motionEvent.getX(0), motionEvent.getY(0), 0);
        en enVar2 = new en(motionEvent.getX(1), motionEvent.getY(1), 1);
        if (this.H == null) {
            this.H = new Pair(enVar, enVar2);
        } else {
            en enVar3 = (en) this.H.first;
            en enVar4 = (en) this.H.second;
            this.H = new Pair(enVar, enVar2);
            float b = b(enVar3.b(enVar4));
            float b2 = b(enVar.b(enVar2));
            if (b > 270.0f && b2 < 90.0f) {
                b = (360.0f - b) + b2;
            } else if (b >= 90.0f || b2 <= 270.0f) {
                b = b2 - b;
            } else {
                b = (b + (360.0f - b2)) * -1.0f;
            }
            if (Math.abs(en.a(enVar3, enVar4, enVar, enVar2)) > ((double) this.I)) {
                this.v.b(b);
            }
        }
        return true;
    }

    private float b(double d) {
        if (d < 0.0d) {
            return (float) (360.0d + d);
        }
        return (float) d;
    }

    private boolean j(MotionEvent motionEvent) {
        this.w++;
        this.h = new en(motionEvent.getX(0), motionEvent.getY(0), motionEvent.getPointerId(0));
        this.H = null;
        C = 0;
        D = true;
        e();
        return true;
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        if (this.s && this.K != null) {
            this.K.a(scaleGestureDetector.getScaleFactor());
        }
        return true;
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
    }

    public void a(a aVar) {
        this.K = aVar;
    }

    public void a(boolean z) {
        this.s = z;
    }

    public boolean b() {
        return this.s;
    }

    public void b(boolean z) {
        this.q = z;
    }

    public boolean c() {
        return this.q;
    }

    public void c(boolean z) {
        this.r = z;
    }

    public boolean s() {
        return this.r;
    }

    public void d(boolean z) {
        this.t = z;
    }

    public boolean t() {
        return this.t;
    }

    public void e(boolean z) {
        c(z);
        b(z);
        a(z);
        d(z);
    }

    protected void e() {
        super.e();
        this.K.b();
    }

    protected void a(en enVar, en enVar2) {
        if (this.K != null) {
            this.K.a(enVar2.x - enVar.x, enVar2.y - enVar.y);
        }
    }

    private void f(boolean z) {
        this.L = this.v.getHeading();
        this.M = z;
        this.o = 1166;
        p();
        this.j = true;
    }

    protected void b(float f) {
        float f2 = this.M ? 1.0f : -1.0f;
        if (this.h != null && this.h.a() && this.v.isScreenCoordinateBehindCamera(this.h.x, this.h.y)) {
            f2 *= -1.0f;
        }
        f2 = (f2 * (180.0f * f)) + this.L;
        if (f2 > 360.0f) {
            f2 -= 360.0f;
        } else if (f2 < 0.0f) {
            f2 += 360.0f;
        }
        this.v.setHeading(f2);
    }

    protected void o() {
        this.K.b();
        this.j = false;
    }
}
