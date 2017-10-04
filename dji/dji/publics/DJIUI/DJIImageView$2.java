package dji.publics.DJIUI;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class DJIImageView$2 extends AnimatorListenerAdapter {
    final /* synthetic */ DJIImageView this$0;

    DJIImageView$2(DJIImageView dJIImageView) {
        this.this$0 = dJIImageView;
    }

    public void onAnimationStart(Animator animator) {
        this.this$0.show();
    }

    public void onAnimationEnd(Animator animator) {
        this.this$0.animate().setListener(null);
    }
}
