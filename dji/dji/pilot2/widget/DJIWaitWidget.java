package dji.pilot2.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.support.v4.view.InputDeviceCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import dji.pilot.visual.a.d;

public class DJIWaitWidget extends View {
    private int a = 500;
    private int b = 281;
    private int c = 90;
    private int d = 100;
    private int e = 0;
    private int f = -1;
    private Paint g = null;
    private String h = null;
    private float i = d.c;
    private Handler j = new Handler();
    private boolean k = false;
    private int l = 50;
    private Runnable m = new Runnable(this) {
        final /* synthetic */ DJIWaitWidget a;

        {
            this.a = r1;
        }

        public void run() {
            if (this.a.k && this.a.f == -1) {
                this.a.e = this.a.e + 5;
                this.a.e = this.a.e % 360;
                this.a.invalidate();
                this.a.j.postDelayed(this.a.m, (long) this.a.l);
            }
        }
    };

    public DJIWaitWidget(Context context) {
        super(context);
        a();
    }

    public DJIWaitWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.g = new Paint();
        this.g.setAntiAlias(true);
        this.h = "Loading";
    }

    protected void onWindowVisibilityChanged(int i) {
        if (i == 0 && this.f == -1) {
            this.k = true;
            this.j.postDelayed(this.m, (long) this.l);
            return;
        }
        this.k = false;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.a = getMeasuredWidth();
        this.b = getMeasuredHeight();
        setMeasuredDimension(this.a, this.b);
    }

    public void setLabelAndPosY(String str, float f) {
        this.h = str;
        this.i = f;
    }

    public void setProgress(int i) {
        if (i < 0) {
            i = 0;
        } else if (i > 100) {
            i = 100;
        }
        this.f = (i * 360) / 100;
        this.k = false;
        postInvalidate();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    @SuppressLint({"DrawAllocation", "DefaultLocale"})
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.g.setStyle(Style.FILL);
        this.g.setColor(Color.argb(180, 100, 100, 100));
        canvas.drawRect(new Rect(0, 0, this.a, this.b), this.g);
        float f = this.i * ((float) this.b);
        RectF rectF;
        if (this.f == -1) {
            rectF = new RectF((float) ((this.a / 2) - this.c), f - ((float) this.c), (float) ((this.a / 2) + this.c), ((float) this.c) + f);
            this.g.setStyle(Style.STROKE);
            this.g.setColor(-7829368);
            this.g.setStrokeWidth(10.0f);
            canvas.drawArc(rectF, (float) (this.e - 30), 300.0f, false, this.g);
            this.g.setColor(InputDeviceCompat.SOURCE_ANY);
            this.g.setStrokeWidth(10.0f);
            canvas.drawArc(rectF, (float) (this.e - 90), 60.0f, false, this.g);
        } else {
            rectF = new RectF((float) ((this.a / 2) - this.c), f - ((float) this.c), (float) ((this.a / 2) + this.c), ((float) this.c) + f);
            this.g.setStyle(Style.STROKE);
            this.g.setColor(InputDeviceCompat.SOURCE_ANY);
            this.g.setStrokeWidth(5.0f);
            canvas.drawArc(rectF, 0.0f, 360.0f, false, this.g);
            this.g.setColor(InputDeviceCompat.SOURCE_ANY);
            this.g.setStrokeWidth(20.0f);
            canvas.drawArc(rectF, -90.0f, (float) this.f, false, this.g);
            this.g.setStyle(Style.FILL);
            this.g.setColor(-1);
            String format = String.format("%d%%", new Object[]{Integer.valueOf((this.f * 100) / 360)});
            this.g.setTextSize((float) (this.c / 2));
            Rect rect = new Rect();
            this.g.getTextBounds(format, 0, format.length(), rect);
            canvas.drawText(format, (float) ((this.a - rect.width()) / 2), ((float) (rect.height() / 2)) + f, this.g);
        }
        if (this.h != null && !this.h.isEmpty()) {
            this.g.setStyle(Style.FILL);
            this.g.setColor(-1);
            Rect rect2 = new Rect();
            this.g.setTextSize((float) (this.d / 2));
            this.g.getTextBounds(this.h, 0, this.h.length(), rect2);
            canvas.drawText(this.h, (float) ((this.a - rect2.width()) / 2), (((float) rect2.height()) + (((float) this.c) + f)) + 40.0f, this.g);
        }
    }
}
