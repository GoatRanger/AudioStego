package lecho.lib.hellocharts.d;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.ViewParent;
import lecho.lib.hellocharts.g.d;
import lecho.lib.hellocharts.model.n;

public class b {
    protected GestureDetector a;
    protected ScaleGestureDetector b;
    protected a c;
    protected c d;
    protected lecho.lib.hellocharts.view.a e;
    protected lecho.lib.hellocharts.b.a f;
    protected d g;
    protected boolean h = true;
    protected boolean i = true;
    protected boolean j = true;
    protected boolean k = false;
    protected n l = new n();
    protected n m = new n();
    protected n n = new n();
    protected ViewParent o;
    protected d p;

    protected class a extends SimpleOnGestureListener {
        protected lecho.lib.hellocharts.d.a.a a = new lecho.lib.hellocharts.d.a.a();
        final /* synthetic */ b b;

        protected a(b bVar) {
            this.b = bVar;
        }

        public boolean onDown(MotionEvent motionEvent) {
            if (!this.b.i) {
                return false;
            }
            this.b.h();
            return this.b.c.a(this.b.f);
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (this.b.h) {
                return this.b.d.a(motionEvent, this.b.f);
            }
            return false;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (!this.b.i) {
                return false;
            }
            boolean a = this.b.c.a(this.b.f, f, f2, this.a);
            this.b.a(this.a);
            return a;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (this.b.i) {
                return this.b.c.a((int) (-f), (int) (-f2), this.b.f);
            }
            return false;
        }
    }

    protected class b extends SimpleOnScaleGestureListener {
        final /* synthetic */ b a;

        protected b(b bVar) {
            this.a = bVar;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            if (!this.a.h) {
                return false;
            }
            float scaleFactor = 2.0f - scaleGestureDetector.getScaleFactor();
            if (Float.isInfinite(scaleFactor)) {
                scaleFactor = 1.0f;
            }
            return this.a.d.a(this.a.f, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), scaleFactor);
        }
    }

    public b(Context context, lecho.lib.hellocharts.view.a aVar) {
        this.e = aVar;
        this.f = aVar.getChartComputator();
        this.g = aVar.getChartRenderer();
        this.a = new GestureDetector(context, new a(this));
        this.b = new ScaleGestureDetector(context, new b(this));
        this.c = new a(context);
        this.d = new c(context, g.HORIZONTAL_AND_VERTICAL);
    }

    public void a() {
        this.f = this.e.getChartComputator();
        this.g = this.e.getChartRenderer();
    }

    public boolean b() {
        boolean z = false;
        if (this.i && this.c.b(this.f)) {
            z = true;
        }
        if (this.h && this.d.a(this.f)) {
            return true;
        }
        return z;
    }

    public boolean a(MotionEvent motionEvent) {
        boolean z = this.b.onTouchEvent(motionEvent) || this.a.onTouchEvent(motionEvent);
        if (this.h && this.b.isInProgress()) {
            h();
        }
        if (!this.j) {
            return z;
        }
        if (b(motionEvent) || z) {
            return true;
        }
        return false;
    }

    public boolean a(MotionEvent motionEvent, ViewParent viewParent, d dVar) {
        this.o = viewParent;
        this.p = dVar;
        return a(motionEvent);
    }

    private void h() {
        if (this.o != null) {
            this.o.requestDisallowInterceptTouchEvent(true);
        }
    }

    private void a(lecho.lib.hellocharts.d.a.a aVar) {
        if (this.o == null) {
            return;
        }
        if (d.HORIZONTAL == this.p && !aVar.a && !this.b.isInProgress()) {
            this.o.requestDisallowInterceptTouchEvent(false);
        } else if (d.VERTICAL == this.p && !aVar.b && !this.b.isInProgress()) {
            this.o.requestDisallowInterceptTouchEvent(false);
        }
    }

    private boolean b(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                boolean c = this.g.c();
                if (c != a(motionEvent.getX(), motionEvent.getY())) {
                    if (!this.k) {
                        return true;
                    }
                    this.l.a();
                    if (!c || this.g.c()) {
                        return true;
                    }
                    this.e.callTouchListener();
                    return true;
                }
                break;
            case 1:
                if (this.g.c()) {
                    if (!a(motionEvent.getX(), motionEvent.getY())) {
                        this.g.d();
                        return true;
                    } else if (!this.k) {
                        this.e.callTouchListener();
                        this.g.d();
                        return true;
                    } else if (this.l.equals(this.m)) {
                        return true;
                    } else {
                        this.l.a(this.m);
                        this.e.callTouchListener();
                        return true;
                    }
                }
                break;
            case 2:
                if (this.g.c() && !a(motionEvent.getX(), motionEvent.getY())) {
                    this.g.d();
                    return true;
                }
            case 3:
                if (this.g.c()) {
                    this.g.d();
                    return true;
                }
                break;
        }
        return false;
    }

    private boolean a(float f, float f2) {
        this.n.a(this.m);
        this.m.a();
        if (this.g.a(f, f2)) {
            this.m.a(this.g.h());
        }
        if (this.n.b() && this.m.b() && !this.n.equals(this.m)) {
            return false;
        }
        return this.g.c();
    }

    public boolean c() {
        return this.h;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public boolean d() {
        return this.i;
    }

    public void b(boolean z) {
        this.i = z;
    }

    public g e() {
        return this.d.a();
    }

    public void a(g gVar) {
        this.d.a(gVar);
    }

    public boolean f() {
        return this.j;
    }

    public void c(boolean z) {
        this.j = z;
    }

    public boolean g() {
        return this.k;
    }

    public void d(boolean z) {
        this.k = z;
    }
}
