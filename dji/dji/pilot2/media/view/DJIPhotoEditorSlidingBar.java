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
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import it.sauronsoftware.ftp4j.FTPCodes;

public class DJIPhotoEditorSlidingBar extends View {
    int a = -1;
    int b = 0;
    Paint c = null;
    private int d = -100;
    private int e = 100;
    private int f = 500;
    private int g = 4;
    private int h = 2;
    private int i = 20;
    private int j = 50;
    private int k = 20;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private a o = null;
    private boolean p = false;

    public interface a {
        void a(View view, int i, boolean z);
    }

    public DJIPhotoEditorSlidingBar(Context context) {
        super(context);
        a();
    }

    public DJIPhotoEditorSlidingBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.a = -1;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.c.setStrokeWidth(3.0f);
        this.l = Color.argb(255, 255, 255, 255);
        this.m = Color.argb(255, 255, 255, 255);
        this.n = Color.argb(FTPCodes.DATA_CONNECTION_ALREADY_OPEN, 100, 100, 100);
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            this.i = 20;
        } else {
            this.i = 15;
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f = getMeasuredWidth();
        this.g = getMeasuredHeight();
    }

    public void setOnValueChanged(a aVar) {
        this.o = aVar;
    }

    public void setRange(int i, int i2, boolean z) {
        this.d = i;
        this.e = i2;
        this.p = z;
    }

    public void setFontSize(int i) {
        this.j = i;
    }

    public void setValue(int i) {
        this.b = i;
        this.a = (((this.b * this.f) / this.e) + this.f) / 2;
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
                this.b = (((this.a * 2) - this.f) * this.e) / this.f;
                if (this.b < this.d) {
                    this.b = this.d;
                } else if (this.b > this.e) {
                    this.b = this.e;
                }
                if (this.o != null) {
                    boolean z;
                    a aVar = this.o;
                    int i = this.b;
                    if (action == 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    aVar.a(this, i, z);
                }
                invalidate();
            }
            if (action == 1) {
                this.l = Color.argb(255, 255, 255, 255);
                invalidate();
                if (this.o != null) {
                    this.o.a(this, this.b, true);
                }
            } else {
                this.l = Color.argb(255, 255, 255, 0);
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
        if (this.a == -1) {
            this.a = this.f / 2;
        }
        if (this.p) {
            String format = String.format("%d°", new Object[]{Integer.valueOf(this.b)});
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
        if (i2 > (this.f / 2) + this.h) {
            canvas.drawRect(new Rect((this.f / 2) - this.h, (i / 2) - this.i, (this.f / 2) + this.h, (i / 2) + this.i), this.c);
            canvas.drawRect(new Rect((this.f / 2) + this.h, (i / 2) - this.h, i2, (i / 2) + this.h), this.c);
        } else if (i3 < (this.f / 2) - this.h) {
            canvas.drawRect(new Rect((this.f / 2) - this.h, (i / 2) - this.i, (this.f / 2) + this.h, (i / 2) + this.i), this.c);
            canvas.drawRect(new Rect(i3, (i / 2) - this.h, (this.f / 2) - this.h, (i / 2) + this.h), this.c);
        }
        this.c.setStyle(Style.STROKE);
        i2 = this.a;
        i3 = i / 2;
        if (i2 < i / 2) {
            i2 = i / 2;
        } else if (i2 > this.f - (i / 2)) {
            i2 = this.f - (i / 2);
        }
        canvas.drawCircle((float) i2, (float) i3, (float) ((i / 2) - this.h), this.c);
    }
}
