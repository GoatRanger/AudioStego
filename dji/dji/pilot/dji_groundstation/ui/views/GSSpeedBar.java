package dji.pilot.dji_groundstation.ui.views;

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
import dji.pilot.dji_groundstation.R;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import it.sauronsoftware.ftp4j.FTPCodes;

public class GSSpeedBar extends View {
    private static final String d = "GSSpeedBar";
    int a;
    int b;
    Paint c;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private a n;
    private boolean o;

    public interface a {
        void a(View view, int i, boolean z);
    }

    public GSSpeedBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = -1;
        this.b = 0;
        this.c = null;
        this.e = -100;
        this.f = 100;
        this.g = 500;
        this.h = 4;
        this.i = 2;
        this.j = 50;
        this.k = 20;
        this.l = 0;
        this.m = 0;
        this.n = null;
        this.o = false;
        this.a = -1;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.c.setStrokeWidth(3.0f);
        this.l = Color.argb(255, 255, 255, 255);
        this.m = Color.argb(FTPCodes.DATA_CONNECTION_ALREADY_OPEN, 100, 100, 100);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.g = getMeasuredWidth();
        this.h = getMeasuredHeight();
        this.i = this.h / 5;
    }

    public void setOnValueChanged(a aVar) {
        this.n = aVar;
    }

    public void setRange(int i, int i2, boolean z) {
        this.e = i;
        this.f = i2;
        this.o = z;
    }

    public void setValue(int i) {
        this.b = i;
        invalidate();
    }

    public void setProgress(int i) {
        this.b = i;
        if (this.n != null) {
            this.n.a(this, this.b, false);
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
                this.b = (((this.a * 2) - this.g) * this.f) / this.g;
                if (this.b < this.e) {
                    this.b = this.e;
                } else if (this.b > this.f) {
                    this.b = this.f;
                }
                if (!(this.n == null || action == 1)) {
                    this.n.a(this, this.b, false);
                }
                invalidate();
            }
            if (action == 1) {
                invalidate();
                if (this.n != null) {
                    this.n.a(this, this.b, true);
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
        this.a = (((this.b * this.g) / this.f) + this.g) / 2;
        if (this.o) {
            String format = String.format("%d°", new Object[]{Integer.valueOf(this.b)});
            Rect rect = new Rect();
            this.c.setStyle(Style.FILL);
            this.c.setTextSize((float) this.j);
            this.c.getTextBounds(format, 0, format.length(), rect);
            this.c.setColor(this.l);
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                i = (this.h - 80) - this.k;
            } else {
                i = (this.h - 25) - this.k;
            }
            int i3 = this.a;
            if (i3 < i / 2) {
                i3 = i / 2;
            } else if (i3 > this.g - (i / 2)) {
                i3 = this.g - (i / 2);
            }
            canvas.drawText(format, (float) (i3 - (rect.width() / 2)), (float) (this.h - this.k), this.c);
            i2 = i;
        } else {
            i2 = this.h;
        }
        this.c.setStyle(Style.FILL);
        i = this.a;
        int i4 = i2 / 2;
        int i5 = i < i2 / 2 ? i2 / 2 : i > this.g - (i2 / 2) ? this.g - (i2 / 2) : i;
        this.c.setColor(this.m);
        canvas.drawRoundRect(new RectF(0.0f, (float) ((i2 / 2) - this.i), (float) this.g, (float) ((i2 / 2) + this.i)), (float) this.i, (float) this.i, this.c);
        this.c.setColor(getResources().getColor(R.color.setting_ui_btn_hover));
        if (i5 < this.g / 2) {
            canvas.drawRect((float) i5, (float) ((i2 / 2) - this.i), (float) (this.g / 2), (float) ((i2 / 2) + this.i), this.c);
        } else {
            canvas.drawRect((float) (this.g / 2), (float) ((i2 / 2) - this.i), (float) i5, (float) ((i2 / 2) + this.i), this.c);
        }
        this.c.setColor(-1);
        canvas.drawCircle((float) (this.g / 2), (float) (i2 / 2), (float) this.i, this.c);
        canvas.drawCircle((float) i5, (float) i4, (float) (i2 / 2), this.c);
    }
}
