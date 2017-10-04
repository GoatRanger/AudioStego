package dji.device.common.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.fpv.R;

public class DJIEVStripViewCompat extends View {
    private Bitmap a = null;
    private Bitmap b = null;
    private Bitmap c = null;
    private Bitmap d = null;
    private Bitmap e = null;
    private Bitmap f = null;
    private float g = 0.0f;
    private float h = 0.0f;
    private float i = 0.0f;
    private float j = 0.0f;
    private float k = 3.0f;
    private float l = 0.0f;
    private float m = DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity;
    private final Paint n = new Paint();
    private int o = 0;
    private float p = 0.0f;

    public DJIEVStripViewCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getSelectedPosition() {
        return this.o;
    }

    public void setSelectedPosition(int i) {
        if (this.o != i) {
            this.o = i;
            postInvalidate();
        }
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.a = ((BitmapDrawable) getResources().getDrawable(R.drawable.strip_small)).getBitmap();
            this.b = ((BitmapDrawable) getResources().getDrawable(R.drawable.strip_small_light)).getBitmap();
            this.c = ((BitmapDrawable) getResources().getDrawable(R.drawable.strip_middle)).getBitmap();
            this.d = ((BitmapDrawable) getResources().getDrawable(R.drawable.strip_middel_light)).getBitmap();
            this.e = ((BitmapDrawable) getResources().getDrawable(R.drawable.strip_center)).getBitmap();
            this.g = (float) this.a.getWidth();
            this.h = (float) this.c.getWidth();
            this.i = (float) this.e.getWidth();
            this.l = this.g;
            this.k = this.g;
            this.m = this.h;
            this.n.setAntiAlias(true);
            this.n.setFilterBitmap(true);
            setWillNotDraw(false);
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.p = ((float) i) / 2.0f;
    }

    private Bitmap a(int i, Bitmap bitmap, boolean z) {
        if ((!z || this.o >= 9 || i > 9 - this.o) && (z || this.o <= 9 || i + 9 > this.o)) {
            return bitmap;
        }
        if (bitmap == this.a) {
            return this.b;
        }
        if (bitmap == this.c) {
            return this.d;
        }
        return bitmap;
    }

    protected void onDraw(Canvas canvas) {
        int i;
        float f = this.l;
        float f2 = this.m;
        int height = this.d.getHeight() - this.b.getHeight();
        for (i = 0; i < 3; i++) {
            canvas.drawBitmap(a(9 - (i * 3), this.c, true), f, f2, this.n);
            canvas.drawBitmap(a((9 - (i * 3)) - 1, this.a, true), (this.h + f) + this.k, ((float) height) + f2, this.n);
            canvas.drawBitmap(a((9 - (i * 3)) - 2, this.a, true), ((this.h + f) + this.g) + (this.k * 2.0f), ((float) height) + f2, this.n);
            f += (this.h + (this.g * 2.0f)) + (this.k * 3.0f);
        }
        canvas.drawBitmap(this.e, f, f2, this.n);
        f += this.i + this.k;
        for (i = 0; i < 3; i++) {
            canvas.drawBitmap(a((i * 3) + 1, this.a, false), f, ((float) height) + f2, this.n);
            canvas.drawBitmap(a((i * 3) + 2, this.a, false), (this.g + f) + this.k, ((float) height) + f2, this.n);
            canvas.drawBitmap(a((i * 3) + 3, this.c, false), ((this.g + f) + this.g) + (this.k * 2.0f), f2, this.n);
            f += (this.h + (this.g * 2.0f)) + (this.k * 3.0f);
        }
        if (this.f == null) {
            return;
        }
        if (this.o == 9) {
            canvas.drawBitmap(this.f, this.p - (this.j / 2.0f), 0.0f, this.n);
        } else if (this.o % 3 == 0) {
            if (this.o < 9) {
                f = ((float) (this.o / 3)) * ((this.h + (this.g * 2.0f)) + (this.k * 3.0f));
            } else {
                r0 = (this.o / 3) - 3;
                f = (((float) (r0 - 1)) * this.h) + (((((this.h + (this.g * 2.0f)) + (this.k * 3.0f)) * 3.0f) + this.i) + (((float) r0) * ((this.k * 3.0f) + (this.g * 2.0f))));
            }
            canvas.drawBitmap(this.f, f, 0.0f, this.n);
        } else {
            if (this.o < 9) {
                r0 = this.o % 3;
                f = (((float) (r0 - 1)) * this.g) + (((((float) (this.o / 3)) * ((this.h + (this.g * 2.0f)) + (this.k * 3.0f))) + this.h) + (((float) r0) * this.k));
            } else {
                r0 = this.o % 3;
                float f3 = (((this.h + (this.g * 2.0f)) + (this.k * 3.0f)) * 3.0f) + this.i;
                f = (((float) (r0 - 1)) * this.g) + (((((float) ((this.o / 3) - 3)) * ((this.h + (this.g * 2.0f)) + (this.k * 3.0f))) + f3) + (((float) r0) * this.k));
            }
            canvas.drawBitmap(this.f, f, 0.0f, this.n);
        }
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(((int) (((((this.k * 18.0f) + (12.0f * this.g)) + (6.0f * this.h)) + this.i) + (2.0f * this.l))) + 2, (int) (((float) (this.d != null ? this.d.getHeight() : 0)) + this.m));
    }
}
