package dji.publics.DJIUI;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class DJILinearLayout$2 extends AnimatorListenerAdapter {
    final /* synthetic */ DJILinearLayout this$0;

    DJILinearLayout$2(DJILinearLayout dJILinearLayout) {
        this.this$0 = dJILinearLayout;
    }

    public void onAnimationStart(Animator animator) {
        this.this$0.show();
    }

    public void onAnimationEnd(Animator animator) {
        this.this$0.animate().setListener(null);
    }
}
