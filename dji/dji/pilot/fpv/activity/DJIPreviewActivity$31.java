package dji.pilot.fpv.activity;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class DJIPreviewActivity$31 implements AnimationListener {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$31(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        DJIPreviewActivity.ar(this.a).setVisibility(8);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
