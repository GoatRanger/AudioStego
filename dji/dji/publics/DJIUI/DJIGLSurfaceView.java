package dji.publics.DJIUI;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import dji.publics.d.a;
import dji.publics.d.c;

public class DJIGLSurfaceView extends GLSurfaceView implements a, c {
    private AnimatorListenerAdapter animGoListener = new AnimatorListenerAdapter() {
        public void onAnimationEnd(Animator animator) {
            DJIGLSurfaceView.this.go();
            DJIGLSurfaceView.this.animate().setListener(null);
        }
    };
    private AnimatorListenerAdapter animShowListener = new AnimatorListenerAdapter() {
        public void onAnimationStart(Animator animator) {
            DJIGLSurfaceView.this.show();
        }

        public void onAnimationEnd(Animator animator) {
            DJIGLSurfaceView.this.animate().setListener(null);
        }
    };

    public DJIGLSurfaceView(Context context) {
        super(context);
    }

    public DJIGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void show() {
        if (getVisibility() != 0) {
            setVisibility(0);
        }
    }

    public void hide() {
        if (getVisibility() != 4) {
            setVisibility(4);
        }
    }

    public void go() {
        if (getVisibility() != 8) {
            setVisibility(8);
        }
    }

    public void animGo() {
        animate().alpha(0.0f).setDuration(300).setListener(this.animGoListener).start();
    }

    public void animShow() {
        animate().alpha(1.0f).setDuration(300).setListener(this.animShowListener).start();
    }
}
