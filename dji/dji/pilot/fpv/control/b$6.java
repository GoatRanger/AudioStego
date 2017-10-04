package dji.pilot.fpv.control;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class b$6 extends AnimatorListenerAdapter {
    final /* synthetic */ b a;

    b$6(b bVar) {
        this.a = bVar;
    }

    public void onAnimationStart(Animator animator) {
        this.a.c.show();
    }
}
