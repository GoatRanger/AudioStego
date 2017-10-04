package dji.pilot.main.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;

class DJISplashActivity$5 implements Runnable {
    final /* synthetic */ AnimatorSet a;
    final /* synthetic */ ObjectAnimator b;
    final /* synthetic */ ObjectAnimator c;
    final /* synthetic */ ObjectAnimator d;
    final /* synthetic */ ObjectAnimator e;
    final /* synthetic */ DJISplashActivity f;

    DJISplashActivity$5(DJISplashActivity dJISplashActivity, AnimatorSet animatorSet, ObjectAnimator objectAnimator, ObjectAnimator objectAnimator2, ObjectAnimator objectAnimator3, ObjectAnimator objectAnimator4) {
        this.f = dJISplashActivity;
        this.a = animatorSet;
        this.b = objectAnimator;
        this.c = objectAnimator2;
        this.d = objectAnimator3;
        this.e = objectAnimator4;
    }

    public void run() {
        DJISplashActivity.c(this.f).show();
        this.a.playTogether(new Animator[]{this.b, this.c, this.d, this.e});
        this.a.start();
    }
}
