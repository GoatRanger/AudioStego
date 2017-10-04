package dji.pilot2.simulator;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class DJISimulatorActivity$3 implements AnimationListener {
    final /* synthetic */ DJISimulatorActivity a;

    DJISimulatorActivity$3(DJISimulatorActivity dJISimulatorActivity) {
        this.a = dJISimulatorActivity;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        if (!DJISimulatorActivity.e(this.a) && DJISimulatorActivity.f(this.a).getVisibility() == 0) {
            DJISimulatorActivity.f(this.a).startAnimation(DJISimulatorActivity.h(this.a));
        }
    }
}
