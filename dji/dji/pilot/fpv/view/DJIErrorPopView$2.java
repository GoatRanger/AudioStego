package dji.pilot.fpv.view;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class DJIErrorPopView$2 implements AnimationListener {
    final /* synthetic */ DJIErrorPopView a;

    DJIErrorPopView$2(DJIErrorPopView dJIErrorPopView) {
        this.a = dJIErrorPopView;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        DJIErrorPopView.a(this.a);
    }
}
