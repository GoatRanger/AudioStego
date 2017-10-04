package lecho.lib.hellocharts.a;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.a;

@SuppressLint({"NewApi"})
public class f implements AnimatorListener, AnimatorUpdateListener, e {
    private final a b;
    private ValueAnimator c;
    private Viewport d = new Viewport();
    private Viewport e = new Viewport();
    private Viewport f = new Viewport();
    private a g = new h();

    public f(a aVar) {
        this.b = aVar;
        this.c = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.c.addListener(this);
        this.c.addUpdateListener(this);
        this.c.setDuration(300);
    }

    public void a(Viewport viewport, Viewport viewport2) {
        this.d.a(viewport);
        this.e.a(viewport2);
        this.c.setDuration(300);
        this.c.start();
    }

    public void a(Viewport viewport, Viewport viewport2, long j) {
        this.d.a(viewport);
        this.e.a(viewport2);
        this.c.setDuration(j);
        this.c.start();
    }

    public void a() {
        this.c.cancel();
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        this.f.a(((this.e.a - this.d.a) * animatedFraction) + this.d.a, ((this.e.b - this.d.b) * animatedFraction) + this.d.b, ((this.e.c - this.d.c) * animatedFraction) + this.d.c, (animatedFraction * (this.e.d - this.d.d)) + this.d.d);
        this.b.setCurrentViewport(this.f);
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
        this.b.setCurrentViewport(this.e);
        this.g.b();
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
        this.g.a();
    }

    public boolean b() {
        return this.c.isStarted();
    }

    public void a(a aVar) {
        if (aVar == null) {
            this.g = new h();
        } else {
            this.g = aVar;
        }
    }
}
