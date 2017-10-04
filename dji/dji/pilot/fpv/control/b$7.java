package dji.pilot.fpv.control;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class b$7 extends AnimatorListenerAdapter {
    final /* synthetic */ b a;

    b$7(b bVar) {
        this.a = bVar;
    }

    public void onAnimationEnd(Animator animator) {
        this.a.c.hide();
    }
}
