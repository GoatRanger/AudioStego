package dji.pilot.fpv.activity;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class DJIPreviewActivityLitchi$30 implements AnimationListener {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    DJIPreviewActivityLitchi$30(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        DJIPreviewActivityLitchi.Z(this.a).go();
    }
}
