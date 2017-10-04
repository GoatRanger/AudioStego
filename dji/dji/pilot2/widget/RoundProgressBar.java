package dji.pilot2.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.view.InputDeviceCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.R$styleable;

public class RoundProgressBar extends View {
    public static final int a = 0;
    public static final int b = 1;
    private Paint c;
    private int d;
    private int e;
    private int f;
    private float g;
    private float h;
    private int i;
    private int j;
    private boolean k;
    private int l;

    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new Paint();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.VERoundProgressBar);
        this.d = obtainStyledAttributes.getColor(0, -1);
        this.e = obtainStyledAttributes.getColor(1, InputDeviceCompat.SOURCE_ANY);
        this.f = obtainStyledAttributes.getColor(3, -1);
        this.g = obtainStyledAttributes.getDimension(4, 50.0f);
        this.h = obtainStyledAttributes.getDimension(2, 7.0f);
        this.i = obtainStyledAttributes.getInteger(5, 100);
        this.k = obtainStyledAttributes.getBoolean(6, true);
        this.l = obtainStyledAttributes.getInt(7, 0);
        obtainStyledAttributes.recycle();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth() / 2;
        int i = (int) (((float) width) - (this.h / 2.0f));
        this.c.setColor(this.d);
        this.c.setStyle(Style.STROKE);
        this.c.setStrokeWidth(this.h);
        this.c.setAntiAlias(true);
        canvas.drawCircle((float) width, (float) width, (float) i, this.c);
        Log.e("log", width + "");
        this.c.setStrokeWidth(0.0f);
        this.c.setColor(this.f);
        this.c.setTextSize(this.g);
        this.c.setTypeface(Typeface.DEFAULT_BOLD);
        int i2 = (int) ((((float) this.j) / ((float) this.i)) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
        float measureText = this.c.measureText(i2 + "%");
        if (this.k && this.l == 0) {
            canvas.drawText(i2 + "%", ((float) width) - (measureText / 2.0f), ((float) width) + (this.g / 2.0f), this.c);
        }
        this.c.setStrokeWidth(this.h);
        this.c.setColor(this.e);
        RectF rectF = new RectF((float) (width - i), (float) (width - i), (float) (width + i), (float) (width + i));
        switch (this.l) {
            case 0:
                this.c.setStyle(Style.STROKE);
                canvas.drawArc(rectF, 0.0f, (float) ((this.j * 360) / this.i), false, this.c);
                return;
            case 1:
                this.c.setStyle(Style.FILL_AND_STROKE);
                if (this.j != 0) {
                    canvas.drawArc(rectF, 0.0f, (float) ((this.j * 360) / this.i), true, this.c);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public synchronized int getMax() {
        return this.i;
    }

    public synchronized void setMax(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("max not less than 0");
        }
        this.i = i;
    }

    public synchronized int getProgress() {
        return this.j;
    }

    public synchronized void setProgress(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        }
        if (i > this.i) {
            i = this.i;
        }
        if (i <= this.i) {
            this.j = i;
            postInvalidate();
        }
    }

    public int getCricleColor() {
        return this.d;
    }

    public void setCricleColor(int i) {
        this.d = i;
    }

    public int getCricleProgressColor() {
        return this.e;
    }

    public void setCricleProgressColor(int i) {
        this.e = i;
    }

    public int getTextColor() {
        return this.f;
    }

    public void setTextColor(int i) {
        this.f = i;
    }

    public float getTextSize() {
        return this.g;
    }

    public void setTextSize(float f) {
        this.g = f;
    }

    public float getRoundWidth() {
        return this.h;
    }

    public void setRoundWidth(float f) {
        this.h = f;
    }
}
