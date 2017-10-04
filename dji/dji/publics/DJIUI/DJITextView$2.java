package dji.publics.DJIUI;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class DJITextView$2 extends AnimatorListenerAdapter {
    final /* synthetic */ DJITextView this$0;

    DJITextView$2(DJITextView dJITextView) {
        this.this$0 = dJITextView;
    }

    public void onAnimationStart(Animator animator) {
        this.this$0.show();
    }

    public void onAnimationEnd(Animator animator) {
        this.this$0.animate().setListener(null);
    }
}
