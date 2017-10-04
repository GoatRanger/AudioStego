package dji.pilot.fpv.camera.newfn;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class DJIBaseTabStageView$5 implements AnimationListener {
    final /* synthetic */ DJIBaseTabStageView a;

    DJIBaseTabStageView$5(DJIBaseTabStageView dJIBaseTabStageView) {
        this.a = dJIBaseTabStageView;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        this.a.b.go();
    }
}
