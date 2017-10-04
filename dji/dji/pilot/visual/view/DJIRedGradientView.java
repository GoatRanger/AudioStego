package dji.pilot.visual.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import dji.publics.d.c;

public class DJIRedGradientView extends View implements c {
    private static final float a = 1.2f;
    private static final int b = 153;
    private int c = 0;
    private float d = 0.0f;
    private final GradientDrawable e = new GradientDrawable();
    private float f = a;

    public DJIRedGradientView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void testView() {
        updateHeight(this.f > a ? 0.0f : this.f + 0.1f);
    }

    public void updateHeight(float f) {
        if (f >= a) {
            go();
            return;
        }
        show();
        if (this.f != f) {
            this.f = f;
            updateView((a - f) / a);
        }
    }

    public void updateView(float f) {
        if (f != this.d) {
            this.d = f;
            if (this.c == 0) {
                this.c = (int) (((float) dji.pilot.visual.a.c.getInstance().e) * lecho.lib.hellocharts.d.c.a);
            }
            int i = (int) (((float) this.c) * f);
            int argb = Color.argb((int) (153.0f * f), 255, 0, 0);
            this.e.setColors(new int[]{0, argb});
            this.e.setBounds(0, 0, dji.pilot.visual.a.c.getInstance().d, i);
            LayoutParams layoutParams = getLayoutParams();
            layoutParams.height = i;
            setLayoutParams(layoutParams);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.e.draw(canvas);
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
}
