package lecho.lib.hellocharts.a;

import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import lecho.lib.hellocharts.view.PieChartView;

public class k implements i {
    final PieChartView b;
    final long c;
    final Handler d;
    final Interpolator e;
    long f;
    boolean g;
    private float h;
    private float i;
    private a j;
    private final Runnable k;

    public k(PieChartView pieChartView) {
        this(pieChartView, 200);
    }

    public k(PieChartView pieChartView, long j) {
        this.e = new AccelerateDecelerateInterpolator();
        this.g = false;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = new h();
        this.k = new Runnable(this) {
            final /* synthetic */ k a;

            {
                this.a = r1;
            }

            public void run() {
                long uptimeMillis = SystemClock.uptimeMillis() - this.a.f;
                if (uptimeMillis > this.a.c) {
                    this.a.g = false;
                    this.a.d.removeCallbacks(this.a.k);
                    this.a.b.setChartRotation((int) this.a.i, false);
                    this.a.j.b();
                    return;
                }
                float min = Math.min(this.a.e.getInterpolation(((float) uptimeMillis) / ((float) this.a.c)), 1.0f);
                this.a.b.setChartRotation((int) (((((min * (this.a.i - this.a.h)) + this.a.h) % 360.0f) + 360.0f) % 360.0f), false);
                this.a.d.postDelayed(this, 16);
            }
        };
        this.b = pieChartView;
        this.c = j;
        this.d = new Handler();
    }

    public void a(float f, float f2) {
        this.h = ((f % 360.0f) + 360.0f) % 360.0f;
        this.i = ((f2 % 360.0f) + 360.0f) % 360.0f;
        this.g = true;
        this.j.a();
        this.f = SystemClock.uptimeMillis();
        this.d.post(this.k);
    }

    public void a() {
        this.g = false;
        this.d.removeCallbacks(this.k);
        this.b.setChartRotation((int) this.i, false);
        this.j.b();
    }

    public boolean b() {
        return this.g;
    }

    public void a(a aVar) {
        if (aVar == null) {
            this.j = new h();
        } else {
            this.j = aVar;
        }
    }
}
