package lecho.lib.hellocharts.a;

import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import lecho.lib.hellocharts.view.a;

public class d implements b {
    final a b;
    final Handler c;
    final Interpolator d = new AccelerateDecelerateInterpolator();
    long e;
    boolean f = false;
    long g;
    private final Runnable h = new Runnable(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void run() {
            long uptimeMillis = SystemClock.uptimeMillis() - this.a.e;
            if (uptimeMillis > this.a.g) {
                this.a.f = false;
                this.a.c.removeCallbacks(this.a.h);
                this.a.b.animationDataFinished();
                return;
            }
            this.a.b.animationDataUpdate(Math.min(this.a.d.getInterpolation(((float) uptimeMillis) / ((float) this.a.g)), 1.0f));
            this.a.c.postDelayed(this, 16);
        }
    };
    private a i = new h();

    public d(a aVar) {
        this.b = aVar;
        this.c = new Handler();
    }

    public void a(long j) {
        if (j >= 0) {
            this.g = j;
        } else {
            this.g = 500;
        }
        this.f = true;
        this.i.a();
        this.e = SystemClock.uptimeMillis();
        this.c.post(this.h);
    }

    public void a() {
        this.f = false;
        this.c.removeCallbacks(this.h);
        this.b.animationDataFinished();
        this.i.b();
    }

    public boolean b() {
        return this.f;
    }

    public void a(a aVar) {
        if (aVar == null) {
            this.i = new h();
        } else {
            this.i = aVar;
        }
    }
}
