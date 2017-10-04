package dji.pilot2.media.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import dji.common.flightcontroller.DJIFlightControllerDataType;

public class DJIVideoPreviewSeekBar extends View {
    float a = 0.0f;
    Paint b = null;
    private int c = 500;
    private int d = 4;
    private int e = 4;
    private int f = 2;
    private int g = 0;
    private int h = 0;
    private a i = null;

    public interface a {
        void a(int i);

        void a(int i, boolean z);

        void b(int i);
    }

    public DJIVideoPreviewSeekBar(Context context) {
        super(context);
        a();
    }

    public DJIVideoPreviewSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.b = new Paint();
        this.b.setStyle(Style.FILL);
        this.g = Color.argb(255, 255, 255, 255);
        this.h = Color.argb(255, 0, 0, 0);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.c = getMeasuredWidth();
        this.d = getMeasuredHeight();
    }

    public void setProgress(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        } else if (f > DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) {
            f = DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity;
        }
        float f2 = (((float) this.c) * f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity;
        if (f2 != this.a) {
            this.a = f2;
            invalidate();
        }
    }

    public void setOnProgressChanged(a aVar) {
        this.i = aVar;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        if (x != this.a) {
            this.a = x;
            if (this.i != null) {
                if (action == 1 || action == 3) {
                    this.i.b((int) ((this.a * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) / ((float) this.c)));
                } else if (action == 0) {
                    this.i.a((int) ((this.a * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) / ((float) this.c)));
                } else if (action == 2) {
                    this.i.a((int) ((this.a * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) / ((float) this.c)), true);
                }
            }
            invalidate();
        } else if (action == 1 || action == 3) {
            this.i.b((int) ((this.a * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) / ((float) this.c)));
        }
        return true;
    }

    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.b.setColor(this.g);
        canvas.drawRect(new Rect(0, (this.d / 2) - this.f, (int) this.a, (this.d / 2) + this.f), this.b);
        this.b.setColor(this.h);
        canvas.drawRect(new Rect((int) (this.a + ((float) this.e)), (this.d / 2) - this.f, this.c, (this.d / 2) + this.f), this.b);
        this.b.setColor(this.g);
        int i = (int) (((float) this.c) - this.a > ((float) this.e) ? this.a : (float) (this.c - this.e));
        canvas.drawRect(new Rect(i, 0, this.e + i, this.d), this.b);
    }
}
