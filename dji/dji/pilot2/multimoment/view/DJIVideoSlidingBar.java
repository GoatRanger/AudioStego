package dji.pilot2.multimoment.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import dji.pilot.R;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;

public class DJIVideoSlidingBar extends View {
    int a = 0;
    double b = 0.0d;
    Paint c = null;
    private int d = -100;
    private int e = 100;
    private int f = 500;
    private int g = 4;
    private int h = 4;
    private int i = 20;
    private int j = 50;
    private int k = 20;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private a o = null;
    private boolean p = false;
    private Context q;
    private Bitmap r;
    private int s = 1;
    private int t = 2;
    private int u;

    public interface a {
        void a(View view, double d, boolean z);
    }

    private void a() {
        if (this.d == 0) {
            this.s = 0;
            this.t = 1;
        }
    }

    private void b() {
        if (this.d == 0) {
            this.u = 0;
        } else {
            this.u = this.f / 2;
        }
    }

    public DJIVideoSlidingBar(Context context) {
        super(context);
        this.q = context;
        c();
    }

    public DJIVideoSlidingBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.q = context;
        c();
    }

    private void c() {
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.c.setStrokeWidth(3.0f);
        this.l = Color.argb(255, 248, 231, 28);
        this.m = Color.argb(255, 255, 255, 255);
        this.n = Color.argb(255, 56, 68, 75);
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            this.i = 20;
        } else {
            this.i = 15;
        }
        this.r = BitmapFactory.decodeResource(this.q.getResources(), R.drawable.v2_multimoment_fine_slide_bar_btn);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f = getMeasuredWidth();
        this.g = getMeasuredHeight();
        b();
    }

    public void setOnValueChanged(a aVar) {
        this.o = aVar;
    }

    public void setRange(int i, int i2, boolean z) {
        this.d = i;
        this.e = i2;
        this.p = z;
        a();
        b();
    }

    public void setFontSize(int i) {
        this.j = i;
    }

    public void setValue(double d) {
        this.b = d;
        this.a = ((int) (((this.b * ((double) this.f)) / ((double) this.e)) + ((double) (this.f * this.s)))) / this.t;
        invalidate();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        requestFocus();
        int action = motionEvent.getAction();
        if (action == 0 || action == 2 || action == 1) {
            int x = (int) motionEvent.getX();
            if (x != this.a) {
                this.a = x;
                if (Math.abs(this.a - this.u) < 15) {
                    this.a = this.u;
                }
                this.b = (((double) ((this.a * this.t) - (this.f * this.s))) * ((double) this.e)) / ((double) this.f);
                if (this.b < ((double) this.d)) {
                    this.b = (double) this.d;
                } else if (this.b > ((double) this.e)) {
                    this.b = (double) this.e;
                }
                if (this.o != null) {
                    this.o.a(this, this.b, action == 1);
                }
                invalidate();
            }
            if (action == 1) {
                this.l = Color.argb(255, 248, 231, 28);
                invalidate();
                if (this.o != null) {
                    this.o.a(this, this.b, true);
                }
            } else {
                this.l = Color.argb(255, 248, 231, 28);
                invalidate();
            }
        }
        return true;
    }

    protected void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
    }

    @SuppressLint({"DrawAllocation", "DefaultLocale"})
    public void onDraw(Canvas canvas) {
        int i;
        int i2;
        super.onDraw(canvas);
        if (this.p) {
            String format = String.format("%d°", new Object[]{Double.valueOf(this.b)});
            Rect rect = new Rect();
            this.c.setStyle(Style.FILL);
            this.c.setTextSize((float) this.j);
            this.c.getTextBounds(format, 0, format.length(), rect);
            this.c.setColor(this.m);
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                i = (this.g - 80) - this.k;
            } else {
                i = (this.g - 25) - this.k;
            }
            i2 = this.a;
            if (i2 < i / 2) {
                i2 = i / 2;
            } else if (i2 > this.f - (i / 2)) {
                i2 = this.f - (i / 2);
            }
            canvas.drawText(format, (float) (i2 - (rect.width() / 2)), (float) (this.g - this.k), this.c);
        } else {
            i = this.g;
        }
        this.c.setStyle(Style.FILL);
        this.c.setColor(this.n);
        i2 = this.a - (i / 2);
        if (i2 > this.f - i) {
            i2 = this.f - i;
        }
        int i3 = this.a + (i / 2);
        if (i3 < i) {
            i3 = i;
        }
        canvas.drawRect(new Rect(0, (i / 2) - this.h, i2, (i / 2) + this.h), this.c);
        canvas.drawRect(new Rect(i3, (i / 2) - this.h, this.f, (i / 2) + this.h), this.c);
        this.c.setColor(this.l);
        if (i2 > ((this.f * this.s) / 2) + this.h) {
            canvas.drawRect(new Rect(((this.f * this.s) / 2) - this.h, (i / 2) - this.i, ((this.f * this.s) / 2) + this.h, (i / 2) + this.i), this.c);
            canvas.drawRect(new Rect(((this.f * this.s) / 2) + this.h, (i / 2) - this.h, i2, (i / 2) + this.h), this.c);
        } else if (i3 < ((this.f * this.s) / 2) - this.h) {
            canvas.drawRect(new Rect(((this.f * this.s) / 2) - this.h, (i / 2) - this.i, ((this.f * this.s) / 2) + this.h, (i / 2) + this.i), this.c);
            canvas.drawRect(new Rect(i3, (i / 2) - this.h, ((this.f * this.s) / 2) - this.h, (i / 2) + this.h), this.c);
        }
        this.c.setStyle(Style.STROKE);
        i2 = this.a;
        i3 = i / 2;
        if (i2 < i / 2) {
            i2 = i / 2;
        } else if (i2 > this.f - (i / 2)) {
            i2 = this.f - (i / 2);
        }
        i = (i / 2) - this.h;
        canvas.drawBitmap(this.r, null, new Rect(i2 - i, i3 - i, i2 + i, i + i3), null);
    }
}
