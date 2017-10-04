package dji.publics.DJIUI;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import dji.publics.d.a;
import dji.publics.d.c;

public class DJIRelativeLayout extends RelativeLayout implements a, c {
    private AnimatorListenerAdapter animGoListener = new AnimatorListenerAdapter() {
        public void onAnimationEnd(Animator animator) {
            DJIRelativeLayout.this.go();
            DJIRelativeLayout.this.animate().setListener(null);
        }
    };
    private AnimatorListenerAdapter animShowListener = new AnimatorListenerAdapter() {
        public void onAnimationStart(Animator animator) {
            DJIRelativeLayout.this.show();
        }

        public void onAnimationEnd(Animator animator) {
            DJIRelativeLayout.this.animate().setListener(null);
        }
    };
    private OnResizeListener resizeListener;

    public interface OnResizeListener {
        void onResizeChanged(int i, int i2, int i3, int i4);
    }

    public DJIRelativeLayout(Context context) {
        super(context);
    }

    public DJIRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJIRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
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

    public void setOnResizeListener(OnResizeListener onResizeListener) {
        this.resizeListener = onResizeListener;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.resizeListener != null) {
            this.resizeListener.onResizeChanged(i, i2, i3, i4);
        }
    }

    public void animGo() {
        animate().alpha(0.0f).setDuration(300).setListener(this.animGoListener).start();
    }

    public void animShow() {
        animate().alpha(1.0f).setDuration(300).setListener(this.animShowListener).start();
    }
}
