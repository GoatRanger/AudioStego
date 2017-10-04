package lecho.lib.hellocharts.d;

import android.content.Context;
import android.os.SystemClock;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

public class h {
    private static final int a = 200;
    private Interpolator b = new DecelerateInterpolator();
    private long c = 200;
    private boolean d = true;
    private float e;
    private long f;
    private float g;

    public h(Context context) {
    }

    public void a(boolean z) {
        this.d = z;
    }

    public void a() {
        this.d = true;
        this.e = this.g;
    }

    public void a(float f) {
        this.f = SystemClock.elapsedRealtime();
        this.g = f;
        this.d = false;
        this.e = 1.0f;
    }

    public boolean b() {
        if (this.d) {
            return false;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.f;
        if (elapsedRealtime >= this.c) {
            this.d = true;
            this.e = this.g;
            return false;
        }
        float f = (((float) elapsedRealtime) * 1.0f) / ((float) this.c);
        this.e = this.b.getInterpolation(f) * this.g;
        return true;
    }

    public float c() {
        return this.e;
    }
}
