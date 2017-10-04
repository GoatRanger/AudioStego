package dji.pilot.fpv.activity;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class DJIPreviewActivity$29 implements AnimationListener {
    final /* synthetic */ DJIPreviewActivity a;

    DJIPreviewActivity$29(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        DJIPreviewActivity.ai(this.a).go();
    }
}
