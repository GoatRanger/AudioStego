package dji.pilot.fpv.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.R$styleable;
import dji.pilot.publics.widget.DJIMultiSeekBar;

public class DJISmartBatteryView extends DJIMultiSeekBar {
    private int h;
    private int i;
    private int j;
    private Drawable k;
    private Drawable l;
    private Drawable m;

    public DJISmartBatteryView(Context context) {
        this(context, null);
    }

    public DJISmartBatteryView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJISmartBatteryView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = 0;
        this.i = 5;
        this.j = 0;
        this.k = null;
        this.l = null;
        this.m = null;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SmartBattery, i, 0);
        setLowWarningDrawable(obtainStyledAttributes.getDrawable(0));
        setSeriousDrawable(obtainStyledAttributes.getDrawable(1));
        setGoHomeDrawable(obtainStyledAttributes.getDrawable(2));
        obtainStyledAttributes.recycle();
    }

    public void setLowWarning(int i) {
        if (this.h != i) {
            this.h = i;
            a(getMeasuredWidth(), this.k, (((float) this.h) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
            postInvalidate();
        }
    }

    public void setSeriousWarning(int i) {
        if (this.i != i) {
            this.i = i;
            a(getMeasuredWidth(), this.l, (((float) i) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
            postInvalidate();
        }
    }

    public void setGoHomeBattery(int i) {
        if (this.j != i) {
            this.j = i;
            a(getMeasuredWidth(), this.m, (((float) i) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
            postInvalidate();
        }
    }

    public void setLowWarningDrawable(Drawable drawable) {
        Object obj;
        if (this.k == null || drawable == this.k) {
            obj = null;
        } else {
            obj = 1;
        }
        if (!(drawable == null || obj == null || (drawable.getIntrinsicWidth() == this.k.getIntrinsicWidth() && drawable.getIntrinsicHeight() == this.k.getIntrinsicHeight()))) {
            requestLayout();
        }
        this.k = drawable;
        invalidate();
        if (obj != null) {
            a(getWidth(), getHeight());
        }
    }

    public void setSeriousDrawable(Drawable drawable) {
        Object obj;
        if (this.l == null || drawable == this.l) {
            obj = null;
        } else {
            obj = 1;
        }
        if (!(drawable == null || obj == null || (drawable.getIntrinsicWidth() == this.l.getIntrinsicWidth() && drawable.getIntrinsicHeight() == this.l.getIntrinsicHeight()))) {
            requestLayout();
        }
        this.l = drawable;
        invalidate();
        if (obj != null) {
            a(getWidth(), getHeight());
        }
    }

    public void setGoHomeDrawable(Drawable drawable) {
        Object obj;
        if (this.m == null || drawable == this.m) {
            obj = null;
        } else {
            obj = 1;
        }
        if (!(drawable == null || obj == null || (drawable.getIntrinsicWidth() == this.m.getIntrinsicWidth() && drawable.getIntrinsicHeight() == this.m.getIntrinsicHeight()))) {
            requestLayout();
        }
        this.m = drawable;
        invalidate();
        if (obj != null) {
            a(getWidth(), getHeight());
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!(this.h == 0 || this.k == null || this.h >= this.e)) {
            this.k.draw(canvas);
        }
        if (!(this.i == 0 || this.l == null || this.i >= this.e)) {
            this.l.draw(canvas);
        }
        if (this.j != 0 && this.m != null) {
            this.m.draw(canvas);
        }
    }

    protected void a(int i, int i2) {
        Drawable drawable = this.a;
        Drawable drawable2 = this.c;
        int intrinsicHeight = drawable2 == null ? 0 : drawable2.getIntrinsicHeight();
        if (drawable2 != null) {
            drawable2.getIntrinsicWidth();
        }
        int i3 = this.b;
        intrinsicHeight = Math.max(this.m == null ? 0 : this.m.getIntrinsicHeight(), intrinsicHeight);
        int i4 = this.d;
        a(i, drawable2, i4 > 0 ? ((float) this.e) / ((float) i4) : 0.0f);
        a(i, this.k, (((float) this.h) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
        a(i, this.l, (((float) this.i) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
        a(i, this.m, (((float) this.j) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
        i4 = (intrinsicHeight - i3) / 2;
        intrinsicHeight = (i - getPaddingRight()) - getPaddingLeft();
        if (drawable != null) {
            drawable.setBounds(0, i4, intrinsicHeight, i4 + i3);
        }
    }

    protected void a(int i, Drawable drawable, float f) {
        int i2 = 0;
        if (drawable != null) {
            int paddingLeft = (i - getPaddingLeft()) - getPaddingRight();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int intrinsicHeight2 = this.m == null ? 0 : this.m.getIntrinsicHeight();
            if (intrinsicHeight < intrinsicHeight2) {
                i2 = Math.abs(intrinsicHeight2 - intrinsicHeight) / 2;
            }
            intrinsicHeight2 = (int) (((float) (paddingLeft - intrinsicWidth)) * f);
            drawable.setBounds(intrinsicHeight2, i2, intrinsicHeight2 + intrinsicWidth, i2 + intrinsicHeight);
        }
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 0;
        int intrinsicHeight = this.c == null ? 0 : this.c.getIntrinsicHeight();
        if (this.m != null) {
            i3 = this.m.getIntrinsicHeight();
        }
        setMeasuredDimension((getPaddingLeft() + getPaddingRight()) + MeasureSpec.getSize(i), Math.max(i3, Math.max(intrinsicHeight, MeasureSpec.getSize(i2))) + (getPaddingTop() + getPaddingBottom()));
    }
}
