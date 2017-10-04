package lecho.lib.hellocharts.a;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import lecho.lib.hellocharts.view.a;

@SuppressLint({"NewApi"})
public class c implements AnimatorListener, AnimatorUpdateListener, b {
    private final a b;
    private ValueAnimator c;
    private a d = new h();

    public c(a aVar) {
        this.b = aVar;
        this.c = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.c.addListener(this);
        this.c.addUpdateListener(this);
    }

    public void a(long j) {
        if (j >= 0) {
            this.c.setDuration(j);
        } else {
            this.c.setDuration(500);
        }
        this.c.start();
    }

    public void a() {
        this.c.cancel();
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.b.animationDataUpdate(valueAnimator.getAnimatedFraction());
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
        this.b.animationDataFinished();
        this.d.b();
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
        this.d.a();
    }

    public boolean b() {
        return this.c.isStarted();
    }

    public void a(a aVar) {
        if (aVar == null) {
            this.d = new h();
        } else {
            this.d = aVar;
        }
    }
}
