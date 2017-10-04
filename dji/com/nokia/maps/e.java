package com.nokia.maps;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.MotionEventCompat;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import com.here.android.mpa.common.ViewRect;
import dji.pilot.publics.control.rc.b;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class e extends w {
    private static float r = 10.0f;
    private static float s = 0.9375f;
    private static float t = 2.0f;
    private static int u = 50;
    private static int v = 15;
    private final cl A = new cl();
    private final List<en> B = new CopyOnWriteArrayList();
    private long C = 0;
    private boolean D = true;
    private boolean E = true;
    private boolean F = false;
    private Pair<en, en> G;
    private double H = 30.0d;
    private float I = 0.01f;
    private float J = (this.I + 1.0f);
    private float K = (1.0f - this.I);
    public final ar a = new ar();
    public final ar b = new ar();
    public final ar c = new ar();
    public final ar d = new ar();
    private GestureDetector q = null;
    private int w = 51;
    private int x = 51;
    private float y;
    private final List<PointF> z = new ArrayList();

    private class a extends SimpleOnGestureListener {
        final /* synthetic */ e a;

        private a(e eVar) {
            this.a = eVar;
        }

        public boolean onSingleTapUp(MotionEvent motionEvent) {
            this.a.h.a(motionEvent.getX(), motionEvent.getY(), motionEvent.getPointerId(0));
            this.a.a.a(this, this.a.h);
            return true;
        }
    }

    public e(Context context) {
        this.q = new GestureDetector(context.getApplicationContext(), new a());
        this.h = new en(-1.0f, -1.0f, -1);
        this.z.add(new PointF(-1.0f, -1.0f));
        this.z.add(new PointF(-1.0f, -1.0f));
        this.y = ((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f;
        s();
    }

    private void s() {
        this.w = (int) (((float) this.w) * this.y);
        if (this.w % 2 == 0) {
            this.w++;
        }
        this.x = (int) (((float) this.x) * this.y);
        if (this.x % 2 == 0) {
            this.x++;
        }
    }

    public boolean a(MotionEvent motionEvent) {
        boolean z;
        if (this.q.onTouchEvent(motionEvent)) {
            z = true;
        } else {
            z = false;
        }
        if (motionEvent.getAction() == 0) {
            return b(motionEvent);
        }
        if (motionEvent.getAction() == 2) {
            return f(motionEvent);
        }
        if (motionEvent.getAction() == 1) {
            return c(motionEvent);
        }
        if (motionEvent.getAction() == 3) {
            return g(motionEvent);
        }
        if (motionEvent.getAction() == 5 || motionEvent.getAction() == 261) {
            return d(motionEvent);
        }
        if (motionEvent.getAction() == 6 || motionEvent.getAction() == b.j) {
            return e(motionEvent);
        }
        System.out.printf("Unhandled event%n", new Object[0]);
        return z;
    }

    private boolean b(MotionEvent motionEvent) {
        this.h.a(motionEvent.getX(), motionEvent.getY(), motionEvent.getPointerId(0));
        this.i = System.currentTimeMillis();
        this.G = null;
        n();
        this.b.a(this, this.h);
        return true;
    }

    private boolean c(MotionEvent motionEvent) {
        if (this.D) {
            if (this.j) {
                this.j = false;
                a_();
            }
            this.h.a(motionEvent.getX(), motionEvent.getY(), motionEvent.getPointerId(0));
            this.c.a(this, this.h);
            this.h = new en(-1.0f, -1.0f, -1);
        }
        if (this.E) {
            if (motionEvent.getEventTime() - this.C < 100) {
                i();
            }
            this.G = null;
        }
        return true;
    }

    private boolean d(MotionEvent motionEvent) {
        int i = 0;
        e();
        this.G = new Pair(new en(motionEvent.getX(0), motionEvent.getY(0), 0), new en(motionEvent.getX(1), motionEvent.getY(1), 1));
        int pointerCount = motionEvent.getPointerCount();
        while (i < pointerCount) {
            int pointerId = motionEvent.getPointerId(i);
            this.B.add(new en(motionEvent.getX(i), motionEvent.getY(i), pointerId));
            i++;
        }
        this.C = motionEvent.getEventTime();
        return true;
    }

    private boolean e(MotionEvent motionEvent) {
        if (this.j) {
            if (motionEvent.getPointerCount() == 2) {
                int i = ((motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8) == 0 ? 1 : 0;
                this.h.a(motionEvent.getX(i), motionEvent.getY(i), motionEvent.findPointerIndex(i));
            } else {
                this.h.a(-1.0f, -1.0f, -1);
            }
        }
        this.B.clear();
        this.F = false;
        return true;
    }

    private boolean f(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        if (pointerCount == 1 && this.D) {
            return h(motionEvent);
        }
        if (pointerCount <= 1 || !this.E) {
            return false;
        }
        return i(motionEvent);
    }

    private boolean g(MotionEvent motionEvent) {
        return c(motionEvent);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean h(android.view.MotionEvent r11) {
        /*
        r10 = this;
        r9 = 0;
        r2 = -1;
        r1 = -1082130432; // 0xffffffffbf800000 float:-1.0 double:NaN;
        r8 = 1;
        r0 = r10.h;
        r0 = r0.b();
        r0 = r11.findPointerIndex(r0);
        if (r0 != r2) goto L_0x0019;
    L_0x0011:
        r10.j = r9;
        r0 = r10.h;
        r0.a(r1, r1, r2);
    L_0x0018:
        return r8;
    L_0x0019:
        r1 = r11.getX(r0);
        r2 = r11.getY(r0);
        r0 = r10.j;
        if (r0 != 0) goto L_0x0063;
    L_0x0025:
        r0 = r10.h;
        r0 = r0.a();
        if (r0 == 0) goto L_0x0018;
    L_0x002d:
        r0 = r10.h;
        r0 = r10.a(r0);
        r3 = r0.getX();
        r3 = (float) r3;
        r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r3 <= 0) goto L_0x0061;
    L_0x003c:
        r3 = r0.getX();
        r4 = r0.getWidth();
        r3 = r3 + r4;
        r3 = (float) r3;
        r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1));
        if (r3 >= 0) goto L_0x0061;
    L_0x004a:
        r3 = r0.getY();
        r3 = (float) r3;
        r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1));
        if (r3 <= 0) goto L_0x0061;
    L_0x0053:
        r3 = r0.getY();
        r0 = r0.getHeight();
        r0 = r0 + r3;
        r0 = (float) r0;
        r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1));
        if (r0 < 0) goto L_0x0018;
    L_0x0061:
        r10.j = r8;
    L_0x0063:
        r4 = java.lang.System.currentTimeMillis();
        r6 = r10.i;
        r6 = r4 - r6;
        r0 = (float) r6;
        r3 = r10.h;
        r3 = r3.x;
        r3 = r1 - r3;
        r3 = r3 / r0;
        r6 = r10.h;
        r6 = r6.y;
        r6 = r2 - r6;
        r0 = r6 / r0;
        r10.a(r3, r0);
        r0 = r10.z;
        r0 = r0.get(r9);
        r0 = (android.graphics.PointF) r0;
        r3 = r10.h;
        r3 = r3.x;
        r6 = r10.h;
        r6 = r6.y;
        r0.set(r3, r6);
        r0 = r10.z;
        r0 = r0.get(r8);
        r0 = (android.graphics.PointF) r0;
        r0.set(r1, r2);
        r0 = r10.h;
        r3 = r10.h;
        r3 = r3.b();
        r0.a(r1, r2, r3);
        r10.i = r4;
        r0 = r10.d;
        r1 = r10.z;
        r0.a(r10, r1);
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nokia.maps.e.h(android.view.MotionEvent):boolean");
    }

    private boolean i(MotionEvent motionEvent) {
        int i = 0;
        this.A.a();
        this.A.i = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
        this.A.j = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
        long eventTime = motionEvent.getEventTime();
        if (!this.F) {
            j(motionEvent);
        }
        if (eventTime - this.C > 100) {
            g();
            j();
        }
        if (this.F && this.E) {
            k(motionEvent);
        }
        this.B.clear();
        int pointerCount = motionEvent.getPointerCount();
        while (i < pointerCount) {
            int pointerId = motionEvent.getPointerId(i);
            this.B.add(new en(motionEvent.getX(i), motionEvent.getY(i), pointerId));
            i++;
        }
        this.C = eventTime;
        return true;
    }

    private void j(MotionEvent motionEvent) {
        if (this.G != null) {
            if (Math.abs(new en(motionEvent.getX(0), motionEvent.getY(0), 0).c(new en(motionEvent.getX(1), motionEvent.getY(1), 1)) - ((en) this.G.first).c((en) this.G.second)) > this.H) {
                this.F = true;
            }
        }
    }

    private void k(MotionEvent motionEvent) {
        if (this.B.size() >= 2 && this.F) {
            en enVar = new en(motionEvent.getX(0), motionEvent.getY(0), 0);
            en enVar2 = new en(motionEvent.getX(1), motionEvent.getY(1), 1);
            double c = enVar.c(enVar2) / ((en) this.B.get(0)).c((en) this.B.get(1));
            double abs = Math.abs(c);
            if (((double) this.K) >= abs || abs >= ((double) this.J)) {
                this.A.a = true;
                this.A.d = c;
                this.A.c = false;
                this.A.f = 0.0f;
                a(this.A.d);
                float min;
                if (abs > 1.0d) {
                    min = Math.min(enVar.y, enVar2.y);
                    ((PointF) this.z.get(0)).set(enVar.x, min);
                    ((PointF) this.z.get(1)).set(enVar.x, (float) ((abs * ((double) min)) * 1.0d));
                } else {
                    min = Math.max(enVar.y, enVar2.y);
                    ((PointF) this.z.get(0)).set(enVar.x, min);
                    ((PointF) this.z.get(1)).set(enVar.x, (float) ((abs * ((double) min)) / 1.0d));
                }
                this.d.a(this, this.z);
            }
        }
    }

    void a(int i, int i2) {
        this.w = i;
        this.x = i2;
        s();
    }

    private ViewRect a(PointF pointF) {
        int i = (int) pointF.x;
        int i2 = (int) pointF.y;
        int i3 = this.w;
        int i4 = this.x;
        return new ViewRect(i - ((i3 - 1) / 2), i2 - ((i4 - 1) / 2), i3, i4);
    }

    public void a(boolean z) {
        this.D = z;
    }

    public boolean a() {
        return this.D;
    }

    public void b(boolean z) {
        this.E = z;
    }

    public boolean b() {
        return this.E;
    }

    void c() {
        long currentTimeMillis = System.currentTimeMillis();
        float b = b(currentTimeMillis);
        if (b != 1.0f) {
            a(new en(((PointF) this.z.get(1)).x, ((PointF) this.z.get(1)).y, 0), new en(((PointF) this.z.get(1)).x, ((PointF) this.z.get(1)).y * Math.abs(b), 0));
        } else {
            a(currentTimeMillis, true);
        }
    }

    protected void a(en enVar, en enVar2) {
        ((PointF) this.z.get(0)).set(enVar.x, enVar.y);
        ((PointF) this.z.get(1)).set(enVar2.x, enVar2.y);
        this.d.a(this, this.z);
    }
}
