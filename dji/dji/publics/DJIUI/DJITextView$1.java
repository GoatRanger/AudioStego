package dji.publics.DJIUI;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class DJITextView$1 extends AnimatorListenerAdapter {
    final /* synthetic */ DJITextView this$0;

    DJITextView$1(DJITextView dJITextView) {
        this.this$0 = dJITextView;
    }

    public void onAnimationEnd(Animator animator) {
        this.this$0.go();
        this.this$0.animate().setListener(null);
    }
}
