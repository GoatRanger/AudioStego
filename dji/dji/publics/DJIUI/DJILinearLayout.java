package dji.publics.DJIUI;

import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import dji.publics.d.a;
import dji.publics.d.c;

public class DJILinearLayout extends LinearLayout implements a, c {
    private AnimatorListenerAdapter animGoListener = new 1(this);
    private AnimatorListenerAdapter animShowListener = new 2(this);

    public DJILinearLayout(Context context) {
        super(context);
    }

    public DJILinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJILinearLayout(Context context, AttributeSet attributeSet, int i) {
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

    public void animGo() {
        animate().alpha(0.0f).setDuration(300).setListener(this.animGoListener).start();
    }

    public void animShow() {
        animate().alpha(1.0f).setDuration(300).setListener(this.animShowListener).start();
    }
}
