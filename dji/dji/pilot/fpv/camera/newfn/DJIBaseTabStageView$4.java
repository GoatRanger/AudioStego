package dji.pilot.fpv.camera.newfn;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class DJIBaseTabStageView$4 implements AnimationListener {
    final /* synthetic */ DJIBaseTabStageView a;

    DJIBaseTabStageView$4(DJIBaseTabStageView dJIBaseTabStageView) {
        this.a = dJIBaseTabStageView;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.a.hide();
        this.a.e.hide();
    }
}
