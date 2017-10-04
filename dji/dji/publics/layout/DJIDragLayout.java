package dji.publics.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import dji.publics.DJIUI.DJIRelativeLayout;

public class DJIDragLayout extends DJIRelativeLayout {
    protected boolean k = false;
    protected int l = 0;
    protected int m = 0;
    protected int n = 0;
    protected int o = 0;
    protected boolean p = false;

    public DJIDragLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.n = i;
        this.o = i2;
    }

    protected void a(MotionEvent motionEvent) {
        a(motionEvent.getRawX(), motionEvent.getRawY());
    }

    protected void a(float f, float f2) {
        setX(f - ((float) (this.n / 2)));
        setY(f2 - ((float) (this.o / 2)));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.p) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.k = true;
                    this.l = (int) motionEvent.getX();
                    this.m = (int) motionEvent.getY();
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                case 1:
                case 3:
                    if (this.k) {
                        this.k = false;
                        break;
                    }
                    break;
                case 2:
                    if (this.k) {
                        a(motionEvent);
                        break;
                    }
                    break;
            }
        }
        return this.k;
    }
}
