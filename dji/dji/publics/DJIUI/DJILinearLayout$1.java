package dji.publics.DJIUI;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class DJILinearLayout$1 extends AnimatorListenerAdapter {
    final /* synthetic */ DJILinearLayout this$0;

    DJILinearLayout$1(DJILinearLayout dJILinearLayout) {
        this.this$0 = dJILinearLayout;
    }

    public void onAnimationEnd(Animator animator) {
        this.this$0.go();
        this.this$0.animate().setListener(null);
    }
}
