package lecho.lib.hellocharts.a;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import lecho.lib.hellocharts.view.PieChartView;

@SuppressLint({"NewApi"})
public class j implements AnimatorListener, AnimatorUpdateListener, i {
    private final PieChartView b;
    private ValueAnimator c;
    private float d;
    private float e;
    private a f;

    public j(PieChartView pieChartView) {
        this(pieChartView, 200);
    }

    public j(PieChartView pieChartView, long j) {
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = new h();
        this.b = pieChartView;
        this.c = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.c.setDuration(j);
        this.c.addListener(this);
        this.c.addUpdateListener(this);
    }

    public void a(float f, float f2) {
        this.d = ((f % 360.0f) + 360.0f) % 360.0f;
        this.e = ((f2 % 360.0f) + 360.0f) % 360.0f;
        this.c.start();
    }

    public void a() {
        this.c.cancel();
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        this.b.setChartRotation((int) (((((animatedFraction * (this.e - this.d)) + this.d) % 360.0f) + 360.0f) % 360.0f), false);
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
        this.b.setChartRotation((int) this.e, false);
        this.f.b();
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
        this.f.a();
    }

    public boolean b() {
        return this.c.isStarted();
    }

    public void a(a aVar) {
        if (aVar == null) {
            this.f = new h();
        } else {
            this.f = aVar;
        }
    }
}
