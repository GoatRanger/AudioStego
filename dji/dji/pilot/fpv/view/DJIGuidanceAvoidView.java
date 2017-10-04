package dji.pilot.fpv.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.R;
import dji.publics.d.c;

public class DJIGuidanceAvoidView extends View implements c {
    public static final int a = 10;
    public static final int b = 30;
    public static final int c = 72;
    private static final int d = 0;
    private static final int e = 1;
    private static final int f = 2;
    private static final int g = 3;
    private static final int h = 24;
    private static final float s = 1.0f;
    private static final float t = 1.0f;
    private static final float u = 0.6f;
    private static final float v = 2.6f;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m = 10;
    private int n = 30;
    private Drawable o = null;
    private Paint p = null;
    private final int[] q = new int[]{1, 2, 3, 0};
    private final RectF r = new RectF();

    public DJIGuidanceAvoidView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public void updateData(int[] iArr) {
        int i = 0;
        dji.thirdparty.afinal.c.c.b(this.q, 0);
        if (iArr != null && iArr.length > 0) {
            while (i < iArr.length && i < this.q.length) {
                if (iArr[i] > 0) {
                    if (iArr[i] <= 10) {
                        this.q[i] = 1;
                    } else if (iArr[i] <= 30) {
                        this.q[i] = 2;
                    } else if (iArr[i] <= 72) {
                        this.q[i] = 3;
                    }
                }
                i++;
            }
        }
        postInvalidate();
    }

    private void a() {
        Resources resources = getResources();
        this.i = resources.getColor(R.color.v);
        this.j = resources.getColor(R.color.w);
        this.l = resources.getColor(R.color.y);
        this.k = resources.getColor(R.color.x);
        this.o = resources.getDrawable(R.drawable.avoid_aircraft);
        this.m = 4;
        this.n = resources.getDimensionPixelSize(R.dimen.rr);
        this.p = new Paint();
        this.p.setAntiAlias(true);
        this.p.setStyle(Style.STROKE);
    }

    public void show() {
        if (getVisibility() != 0) {
            setVisibility(0);
        }
    }

    public void hide() {
        if (getVisibility() != 4) {
            setVisibility(4);
        }
    }

    public void go() {
        if (getVisibility() != 8) {
            setVisibility(8);
        }
    }

    protected void onDraw(Canvas canvas) {
        float f;
        float f2;
        float f3;
        float width = (float) getWidth();
        float height = (float) getHeight();
        float f4 = width / 2.0f;
        float f5 = height / 2.0f;
        float f6 = -0.5f + ((float) this.m);
        float f7 = ((float) this.m) - 8.0f;
        if (width > height) {
            width = f5 - ((float) this.m);
            f = (f4 - width) - 8.0f;
            f2 = f6;
            f3 = width;
        } else {
            width = f4 - ((float) this.m);
            f = f7;
            f2 = (f5 - width) - 8.0f;
            f3 = width;
        }
        int intrinsicWidth = this.o.getIntrinsicWidth();
        int intrinsicHeight = this.o.getIntrinsicHeight();
        this.o.setBounds((int) (f4 - ((float) (intrinsicWidth / 2))), (int) (f5 - ((float) (intrinsicHeight / 2))), (int) (((float) (intrinsicWidth / 2)) + f4), (int) (((float) (intrinsicHeight / 2)) + f5));
        this.o.draw(canvas);
        float f8 = ((intrinsicWidth > intrinsicHeight ? (float) intrinsicWidth : (float) intrinsicHeight) / 2.0f) + ((float) this.n);
        float f9 = ((f3 - f8) - ((float) (this.m * 2))) / v;
        canvas.save();
        canvas.rotate(-123.0f, f4, f5);
        f7 = f3 - f8;
        f6 = f7 / 2.0f;
        this.p.setStrokeWidth(f7);
        this.r.set(f + f6, f2 + f6, ((2.0f * f3) + f) - f6, ((2.0f * f3) + f2) - f6);
        this.p.setColor(this.i);
        for (int i = 0; i < this.q.length; i++) {
            canvas.drawArc(this.r, (float) (i * 90), 66.0f, false, this.p);
        }
        float f10 = f9 / 2.0f;
        for (int i2 = 0; i2 < this.q.length; i2++) {
            width = (float) (i2 * 90);
            if (3 == this.q[i2]) {
                f7 = ((f3 - f8) - (0.6f * f10)) - ((((float) this.m) + f9) * 2.0f);
                f6 = ((f3 + f8) + (0.6f * f10)) + ((((float) this.m) + f9) * 2.0f);
                this.p.setStrokeWidth(0.6f * f9);
                this.p.setColor(this.j);
                this.r.set(f + f7, f7 + f2, f + f6, f6 + f2);
                canvas.drawArc(this.r, width, 66.0f, false, this.p);
            } else if (2 == this.q[i2]) {
                f7 = ((f3 - f8) - f10) - ((((float) this.m) + f9) * 1.0f);
                f6 = ((f3 + f8) + f10) + ((((float) this.m) + f9) * 1.0f);
                this.p.setStrokeWidth(f9);
                this.p.setColor(this.l);
                this.r.set(f + f7, f7 + f2, f + f6, f6 + f2);
                canvas.drawArc(this.r, width, 66.0f, false, this.p);
                f7 = ((f3 - f8) - (0.6f * f10)) - ((((float) this.m) + f9) * 2.0f);
                f6 = ((f3 + f8) + (0.6f * f10)) + ((((float) this.m) + f9) * 2.0f);
                this.p.setStrokeWidth(0.6f * f9);
                this.p.setColor(this.j);
                this.r.set(f + f7, f7 + f2, f + f6, f6 + f2);
                canvas.drawArc(this.r, width, 66.0f, false, this.p);
            } else if (1 == this.q[i2]) {
                f7 = (f3 - f8) - f10;
                f6 = (f3 + f8) + f10;
                this.p.setStrokeWidth(f9);
                this.p.setColor(this.k);
                this.r.set(f + f7, f7 + f2, f + f6, f6 + f2);
                canvas.drawArc(this.r, width - 1.0f, 66.0f + 1.0f, false, this.p);
                f7 = ((f3 - f8) - f10) - ((((float) this.m) + f9) * 1.0f);
                f6 = ((f3 + f8) + f10) + ((((float) this.m) + f9) * 1.0f);
                this.p.setStrokeWidth(f9);
                this.p.setColor(this.l);
                this.r.set(f + f7, f7 + f2, f + f6, f6 + f2);
                canvas.drawArc(this.r, width, 66.0f, false, this.p);
                f7 = ((f3 - f8) - (0.6f * f10)) - ((((float) this.m) + f9) * 2.0f);
                f6 = ((f3 + f8) + (0.6f * f10)) + ((((float) this.m) + f9) * 2.0f);
                this.p.setStrokeWidth(0.6f * f9);
                this.p.setColor(this.j);
                this.r.set(f + f7, f7 + f2, f + f6, f6 + f2);
                canvas.drawArc(this.r, width, 66.0f, false, this.p);
            }
        }
        canvas.restore();
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }
}
