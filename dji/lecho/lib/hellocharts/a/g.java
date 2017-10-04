package lecho.lib.hellocharts.a;

import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.a;

public class g implements e {
    final a b;
    final Handler c;
    final Interpolator d = new AccelerateDecelerateInterpolator();
    long e;
    boolean f = false;
    private Viewport g = new Viewport();
    private Viewport h = new Viewport();
    private Viewport i = new Viewport();
    private long j;
    private a k = new h();
    private final Runnable l = new Runnable(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void run() {
            long uptimeMillis = SystemClock.uptimeMillis() - this.a.e;
            if (uptimeMillis > this.a.j) {
                this.a.f = false;
                this.a.c.removeCallbacks(this.a.l);
                this.a.b.setCurrentViewport(this.a.h);
                this.a.k.b();
                return;
            }
            float min = Math.min(this.a.d.getInterpolation(((float) uptimeMillis) / ((float) this.a.j)), 1.0f);
            this.a.i.a(((this.a.h.a - this.a.g.a) * min) + this.a.g.a, ((this.a.h.b - this.a.g.b) * min) + this.a.g.b, ((this.a.h.c - this.a.g.c) * min) + this.a.g.c, (min * (this.a.h.d - this.a.g.d)) + this.a.g.d);
            this.a.b.setCurrentViewport(this.a.i);
            this.a.c.postDelayed(this, 16);
        }
    };

    public g(a aVar) {
        this.b = aVar;
        this.j = 300;
        this.c = new Handler();
    }

    public void a(Viewport viewport, Viewport viewport2) {
        this.g.a(viewport);
        this.h.a(viewport2);
        this.j = 300;
        this.f = true;
        this.k.a();
        this.e = SystemClock.uptimeMillis();
        this.c.post(this.l);
    }

    public void a(Viewport viewport, Viewport viewport2, long j) {
        this.g.a(viewport);
        this.h.a(viewport2);
        this.j = j;
        this.f = true;
        this.k.a();
        this.e = SystemClock.uptimeMillis();
        this.c.post(this.l);
    }

    public void a() {
        this.f = false;
        this.c.removeCallbacks(this.l);
        this.b.setCurrentViewport(this.h);
        this.k.b();
    }

    public boolean b() {
        return this.f;
    }

    public void a(a aVar) {
        if (aVar == null) {
            this.k = new h();
        } else {
            this.k = aVar;
        }
    }
}
