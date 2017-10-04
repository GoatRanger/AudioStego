package dji.pilot.publics.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout;
import dji.pilot.R;
import dji.publics.d.c;

public class DJIScrollBar extends FrameLayout implements c {
    public static final int a = 0;
    public static final int b = 1;
    private static final long c = 250;
    private static final long d = 400;
    private static final int e = 8;
    private int f = 0;
    private View g = null;
    private int h = 0;
    private int i = 0;
    private MarginLayoutParams j = null;
    private int k = 0;

    public DJIScrollBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = context.getResources().getDimensionPixelSize(R.dimen.m9);
    }

    public DJIScrollBar setIndex(int i) {
        this.h = i;
        return this;
    }

    public DJIScrollBar setMax(int i) {
        this.i = i;
        return this;
    }

    public void updateState() {
        int i = 8;
        int i2 = 0;
        if (!isInEditMode()) {
            int i3;
            long j;
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            if (this.j == null) {
                this.j = (MarginLayoutParams) this.g.getLayoutParams();
            }
            int i4;
            if (this.f == 0) {
                measuredHeight = this.k;
                if (this.i > 1) {
                    i = measuredWidth / (this.i > 8 ? 8 : this.i);
                    i4 = this.h == 0 ? 0 : (int) (((float) (measuredWidth - i)) * ((((float) (this.h - 1)) * 1.0f) / ((float) (this.i - 1))));
                    measuredWidth = measuredHeight;
                    i3 = i;
                    measuredHeight = 0;
                    i2 = i4;
                    j = (long) (i4 * 10);
                }
                j = d;
                i3 = measuredWidth;
                measuredWidth = measuredHeight;
                measuredHeight = 0;
            } else {
                if (this.f == 1) {
                    int i5 = this.k;
                    if (this.i > 1) {
                        if (this.i <= 8) {
                            i = this.i;
                        }
                        i = measuredHeight / i;
                        i4 = this.h == 0 ? 0 : (int) (((float) (measuredHeight - i)) * ((((float) (this.h - 1)) * 1.0f) / ((float) (this.i - 1))));
                        measuredHeight = i4;
                        measuredWidth = i;
                        i3 = i5;
                        j = (long) (i4 * 10);
                    } else {
                        j = d;
                        measuredWidth = measuredHeight;
                        i3 = i5;
                        measuredHeight = 0;
                    }
                }
                j = d;
                i3 = measuredWidth;
                measuredWidth = measuredHeight;
                measuredHeight = 0;
            }
            if (j < 250) {
                j = 250;
            } else if (j > d) {
                j = d;
            }
            this.j.width = i3;
            this.j.height = measuredWidth;
            this.g.setLayoutParams(this.j);
            this.g.animate().setDuration(j).translationX((float) i2).translationY((float) measuredHeight).start();
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.g = findViewById(R.id.bki);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i > i2) {
            this.f = 0;
        } else {
            this.f = 1;
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.j == null || this.j.width == 0) {
            updateState();
        }
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
