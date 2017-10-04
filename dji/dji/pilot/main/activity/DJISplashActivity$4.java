package dji.pilot.main.activity;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import dji.pilot.publics.objects.g;

class DJISplashActivity$4 implements AnimatorListener {
    final /* synthetic */ DJISplashActivity a;

    DJISplashActivity$4(DJISplashActivity dJISplashActivity) {
        this.a = dJISplashActivity;
    }

    public void onAnimationStart(Animator animator) {
        g.a(this.a, DJISplashActivity.g, DJISplashActivity.b(this.a));
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
        DJISplashActivity.a(this.a, false);
    }

    public void onAnimationCancel(Animator animator) {
    }
}
