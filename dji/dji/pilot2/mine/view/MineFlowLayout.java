package dji.pilot2.mine.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.R;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;

public class MineFlowLayout extends ViewGroup {
    private int a;
    private int b;
    private int c;
    private int d;
    private Paint e;
    private float f;

    public MineFlowLayout(Context context) {
        this(context, null);
    }

    public MineFlowLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MineFlowLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        if (DJIOriLayout.getDeviceType().equals(DJIDeviceType.Pad)) {
            this.b = 5;
        } else {
            this.b = 3;
        }
        this.a = 0;
        this.e = new Paint();
        this.e.setColor(getContext().getResources().getColor(R.color.ke));
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int i4 = 0;
        super.onMeasure(i, i2);
        this.d = getChildCount();
        int childCount = getChildCount();
        for (i3 = 0; i3 < this.d; i3++) {
            if (getChildAt(i3).getVisibility() == 8) {
                childCount--;
            }
        }
        this.c = Math.round((((float) childCount) / ((float) this.b)) + 0.49f);
        i3 = MeasureSpec.getSize(i);
        this.f = a(i3);
        childCount = (int) b(a(i3));
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(Math.round(this.f), Integer.MIN_VALUE);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(Math.round(a(this.f)), Integer.MIN_VALUE);
        while (i4 < this.d) {
            getChildAt(i4).measure(makeMeasureSpec, makeMeasureSpec2);
            i4++;
        }
        setMeasuredDimension(i3, childCount);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = 0;
        if (z) {
            int i6 = this.a;
            i6 = this.a;
            int i7 = 1;
            i6 = 0;
            while (i5 < this.d) {
                View childAt = getChildAt(i5);
                if (childAt == null || childAt.getVisibility() != 8) {
                    float measuredWidth = (float) getChildAt(i5).getMeasuredWidth();
                    float measuredHeight = (float) getChildAt(i5).getMeasuredHeight();
                    int i8 = (int) (((float) (this.a * i7)) + (((float) (i7 - 1)) * measuredHeight));
                    int i9 = (int) (((float) (this.a * ((i6 + 1) % this.b))) + (((float) (i6 % this.b)) * measuredWidth));
                    childAt.layout(i9, i8, (int) (measuredWidth + ((float) i9)), (int) (measuredHeight + ((float) i8)));
                    if ((i6 + 1) % this.b == 0) {
                        i7++;
                    }
                    i6++;
                }
                i5++;
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        canvas.drawLine(0.0f, 0.0f, (float) measuredWidth, 0.0f, this.e);
        canvas.drawLine(0.0f, 0.0f, 0.0f, (float) measuredHeight, this.e);
        canvas.drawLine(0.0f, (float) (measuredHeight - 1), (float) measuredWidth, (float) (measuredHeight - 1), this.e);
        canvas.drawLine((float) (measuredWidth - 1), 0.0f, (float) (measuredWidth - 1), (float) measuredHeight, this.e);
        float a = a(this.f);
        measuredHeight = 0;
        measuredWidth = 1;
        while (measuredHeight < this.d) {
            int i;
            int i2 = (int) (((float) (this.a * measuredWidth)) + (((float) (measuredWidth - 1)) * a));
            int i3 = (int) (((float) (this.a * ((measuredHeight + 1) % this.b))) + (this.f * ((float) (measuredHeight % this.b))));
            if ((measuredHeight + 1) % this.b != 0) {
                Canvas canvas2 = canvas;
                canvas2.drawLine(this.f + ((float) i3), (float) i2, this.f + ((float) i3), ((float) i2) + a, this.e);
            }
            if (measuredWidth != this.c) {
                canvas2 = canvas;
                canvas2.drawLine((float) i3, ((float) i2) + a, this.f + ((float) i3), ((float) i2) + a, this.e);
            }
            if ((measuredHeight + 1) % this.b == 0) {
                i = measuredWidth + 1;
            } else {
                i = measuredWidth;
            }
            measuredHeight++;
            measuredWidth = i;
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    private float a(int i) {
        return ((float) (((i - getPaddingLeft()) - getPaddingRight()) - (this.a * (this.b + 1)))) / ((float) this.b);
    }

    private float a(float f) {
        return (3.0f * f) / DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity;
    }

    private float b(float f) {
        return (((((float) this.c) * f) * 3.0f) / DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity) + ((float) (this.a * (this.b + 1)));
    }

    public void setColCount(int i) {
        this.b = i;
        invalidate();
    }

    public void setBorderColor(int i) {
        this.e.setColor(i);
        invalidate();
    }
}
