package dji.pilot.groundStation.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import dji.pilot.R;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import it.sauronsoftware.ftp4j.FTPCodes;

public class DJIGSSpeedBar extends View {
    int a;
    int b;
    Paint c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private a m;
    private boolean n;

    public interface a {
        void a(View view, int i, boolean z);
    }

    public DJIGSSpeedBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = -1;
        this.b = 0;
        this.c = null;
        this.d = -100;
        this.e = 100;
        this.f = 500;
        this.g = 4;
        this.h = 2;
        this.i = 50;
        this.j = 20;
        this.k = 0;
        this.l = 0;
        this.m = null;
        this.n = false;
        this.a = -1;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.c.setStrokeWidth(3.0f);
        this.k = Color.argb(255, 255, 255, 255);
        this.l = Color.argb(FTPCodes.DATA_CONNECTION_ALREADY_OPEN, 100, 100, 100);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f = getMeasuredWidth();
        this.g = getMeasuredHeight();
        this.h = this.g / 5;
    }

    public void setOnValueChanged(a aVar) {
        this.m = aVar;
    }

    public void setRange(int i, int i2, boolean z) {
        this.d = i;
        this.e = i2;
        this.n = z;
    }

    public void setValue(int i) {
        this.b = i;
        invalidate();
    }

    public void setProgress(int i) {
        this.b = i;
        if (this.m != null) {
            this.m.a(this, this.b, false);
        }
        invalidate();
    }

    public int getProgress() {
        return this.b;
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        requestFocus();
        int action = motionEvent.getAction();
        if (action == 0 || action == 2 || action == 1) {
            int x = (int) motionEvent.getX();
            if (x != this.a) {
                this.a = x;
                this.b = (((this.a * 2) - this.f) * this.e) / this.f;
                if (this.b < this.d) {
                    this.b = this.d;
                } else if (this.b > this.e) {
                    this.b = this.e;
                }
                if (!(this.m == null || action == 1)) {
                    this.m.a(this, this.b, false);
                }
                invalidate();
            }
            if (action == 1) {
                invalidate();
                if (this.m != null) {
                    this.m.a(this, this.b, true);
                }
            } else {
                invalidate();
            }
        }
        return true;
    }

    @SuppressLint({"DrawAllocation", "DefaultLocale"})
    public void onDraw(Canvas canvas) {
        int i;
        int i2;
        super.onDraw(canvas);
        this.a = (((this.b * this.f) / this.e) + this.f) / 2;
        if (this.n) {
            String format = String.format("%d°", new Object[]{Integer.valueOf(this.b)});
            Rect rect = new Rect();
            this.c.setStyle(Style.FILL);
            this.c.setTextSize((float) this.i);
            this.c.getTextBounds(format, 0, format.length(), rect);
            this.c.setColor(this.k);
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                i = (this.g - 80) - this.j;
            } else {
                i = (this.g - 25) - this.j;
            }
            int i3 = this.a;
            if (i3 < i / 2) {
                i3 = i / 2;
            } else if (i3 > this.f - (i / 2)) {
                i3 = this.f - (i / 2);
            }
            canvas.drawText(format, (float) (i3 - (rect.width() / 2)), (float) (this.g - this.j), this.c);
            i2 = i;
        } else {
            i2 = this.g;
        }
        this.c.setStyle(Style.FILL);
        i = this.a;
        int i4 = i2 / 2;
        int i5 = i < i2 / 2 ? i2 / 2 : i > this.f - (i2 / 2) ? this.f - (i2 / 2) : i;
        this.c.setColor(this.l);
        canvas.drawRoundRect(new RectF(0.0f, (float) ((i2 / 2) - this.h), (float) this.f, (float) ((i2 / 2) + this.h)), (float) this.h, (float) this.h, this.c);
        this.c.setColor(getResources().getColor(R.color.er));
        if (i5 < this.f / 2) {
            canvas.drawRect((float) i5, (float) ((i2 / 2) - this.h), (float) (this.f / 2), (float) ((i2 / 2) + this.h), this.c);
        } else {
            canvas.drawRect((float) (this.f / 2), (float) ((i2 / 2) - this.h), (float) i5, (float) ((i2 / 2) + this.h), this.c);
        }
        this.c.setColor(-1);
        canvas.drawCircle((float) (this.f / 2), (float) (i2 / 2), (float) this.h, this.c);
        canvas.drawCircle((float) i5, (float) i4, (float) (i2 / 2), this.c);
    }
}
