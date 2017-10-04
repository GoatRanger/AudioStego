package dji.device.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import dji.publics.DJIUI.DJILinearLayout;

public class DJIDragLinearLayout extends DJILinearLayout {
    protected boolean a = false;
    protected int b = 0;
    protected int c = 0;
    protected int d = 0;
    protected int e = 0;
    protected boolean f = true;

    public DJIDragLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.d = i;
        this.e = i2;
    }

    protected void a(MotionEvent motionEvent) {
        a(motionEvent.getRawX(), motionEvent.getRawY());
    }

    protected void a(float f, float f2) {
        setX(f - ((float) (this.d / 2)));
        setY(f2 - ((float) (this.e / 2)));
    }

    protected float a(float f, int[] iArr) {
        if (iArr == null || iArr.length < 2) {
            return 0.0f;
        }
        return (f - ((float) iArr[0])) / ((float) (iArr[1] - iArr[0]));
    }

    protected int b(float f, int[] iArr) {
        if (iArr == null || iArr.length < 2) {
            return 0;
        }
        return (int) (((float) iArr[0]) + (((float) (iArr[1] - iArr[0])) * f));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.a = true;
                    this.b = (int) motionEvent.getX();
                    this.c = (int) motionEvent.getY();
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                case 1:
                case 3:
                    if (this.a) {
                        this.a = false;
                        break;
                    }
                    break;
                case 2:
                    if (this.a) {
                        a(motionEvent);
                        break;
                    }
                    break;
            }
        }
        return this.a;
    }
}
