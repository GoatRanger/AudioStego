package dji.publics.DJIUI;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class DJIImageView$1 extends AnimatorListenerAdapter {
    final /* synthetic */ DJIImageView this$0;

    DJIImageView$1(DJIImageView dJIImageView) {
        this.this$0 = dJIImageView;
    }

    public void onAnimationEnd(Animator animator) {
        this.this$0.go();
        this.this$0.animate().setListener(null);
    }
}
