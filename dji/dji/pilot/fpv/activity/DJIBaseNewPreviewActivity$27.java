package dji.pilot.fpv.activity;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class DJIBaseNewPreviewActivity$27 implements AnimationListener {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$27(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        DJIBaseNewPreviewActivity.M(this.a).go();
    }
}
