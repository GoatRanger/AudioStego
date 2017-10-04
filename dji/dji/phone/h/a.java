package dji.phone.h;

import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class a {
    private static final String a = "DJILPRotationAnimator";
    private static final int b = 400;

    public static void a(View view, float f, float f2, int i, AnimatorListener animatorListener) {
        if (f == 0.0f && f2 == 270.0f) {
            f2 = -90.0f;
        }
        if (f == 270.0f && f2 == 0.0f) {
            f = -90.0f;
        }
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator.setDuration((long) i);
        objectAnimator.setInterpolator(new DecelerateInterpolator());
        objectAnimator.setTarget(view);
        objectAnimator.setPropertyName("Rotation");
        objectAnimator.setFloatValues(new float[]{f, f2});
        if (animatorListener != null) {
            objectAnimator.addListener(animatorListener);
        }
        objectAnimator.start();
    }

    public static void a(View view, float f, float f2) {
        a(view, f, f2, 400, null);
    }

    public static void a(View view, float f, float f2, int i) {
        a(view, f, f2, i, null);
    }

    public static void a(View view, float f, float f2, AnimatorListener animatorListener) {
        a(view, f, f2, 400, animatorListener);
    }
}
