package dji.pilot.visual.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import dji.pilot.R;
import dji.pilot.fpv.model.b;

public class DJICircleSeekBar extends View {
    private int a;
    private int b;
    private int c;
    private int d;
    private TextPaint e;
    private Paint f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private final Rect m;
    private int n;
    private int o;
    private int p;
    private a q;

    public interface a {
        void a(View view, int i, boolean z);
    }

    public DJICircleSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 10;
        this.b = 4;
        this.c = 6;
        this.d = 20;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = -1;
        this.m = new Rect();
        this.n = -100;
        this.o = 100;
        this.p = 0;
        this.q = null;
        this.l = -1;
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.d = b.a(context, R.dimen.ro);
        this.a = b.a(context, R.dimen.gr);
        this.b = b.a(context, R.dimen.h7);
        this.c = this.b * 3;
        Resources resources = context.getResources();
        this.g = Color.argb(255, 255, 255, 255);
        this.h = Color.argb(99, 255, 255, 255);
        this.i = resources.getColor(R.color.d9);
        this.j = resources.getColor(R.color.om);
        this.k = resources.getColor(R.color.ot);
        this.f = new Paint();
        this.f.setAntiAlias(true);
        this.f.setStrokeWidth(3.0f);
        this.f.setStyle(Style.FILL);
        this.e = new TextPaint();
        this.e.setAntiAlias(true);
        this.e.setStyle(Style.FILL);
        this.e.setTextSize((float) this.d);
        this.e.setColor(this.g);
        this.e.setShadowLayer(3.0f, 3.0f, 3.0f, -16777216);
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i), 1073741824), MeasureSpec.makeMeasureSpec((this.c + this.d) + (this.a * 2), 1073741824));
    }

    public void setOnValueChanged(a aVar) {
        this.q = aVar;
    }

    public void setRange(int i, int i2) {
        this.n = i;
        this.o = i2;
    }

    public void setValue(int i) {
        this.p = i;
        invalidate();
    }

    public void setProgress(int i) {
        this.p = i;
        if (this.q != null) {
            this.q.a(this, this.p, false);
        }
        invalidate();
    }

    public int getProgress() {
        return this.p;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
        r5 = this;
        r4 = 1;
        r5.requestFocus();
        r0 = r6.getAction();
        if (r0 == 0) goto L_0x000f;
    L_0x000a:
        r1 = 2;
        if (r0 == r1) goto L_0x000f;
    L_0x000d:
        if (r0 != r4) goto L_0x004a;
    L_0x000f:
        r1 = r6.getX();
        r1 = (int) r1;
        r2 = r5.l;
        if (r1 == r2) goto L_0x003a;
    L_0x0018:
        r5.l = r1;
        r1 = r5.getWidth();
        r2 = r5.l;
        r2 = r2 * 2;
        r2 = r2 - r1;
        r3 = r5.o;
        r2 = r2 * r3;
        r1 = r2 / r1;
        r5.p = r1;
        r1 = r5.p;
        r2 = r5.n;
        if (r1 >= r2) goto L_0x004b;
    L_0x0030:
        r1 = r5.n;
        r5.p = r1;
    L_0x0034:
        r1 = r5.q;
        if (r1 == 0) goto L_0x003a;
    L_0x0038:
        if (r0 == r4) goto L_0x003a;
    L_0x003a:
        if (r0 != r4) goto L_0x0047;
    L_0x003c:
        r0 = r5.q;
        if (r0 == 0) goto L_0x0047;
    L_0x0040:
        r0 = r5.q;
        r1 = r5.p;
        r0.a(r5, r1, r4);
    L_0x0047:
        r5.invalidate();
    L_0x004a:
        return r4;
    L_0x004b:
        r1 = r5.p;
        r2 = r5.o;
        if (r1 <= r2) goto L_0x0034;
    L_0x0051:
        r1 = r5.o;
        r5.p = r1;
        goto L_0x0034;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.visual.view.DJICircleSeekBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @SuppressLint({"DrawAllocation", "DefaultLocale"})
    public void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int i = width / 2;
        this.l = (((this.p * width) / this.o) + width) / 2;
        String format = String.format("%d", new Object[]{Integer.valueOf(this.p)});
        this.e.getTextBounds(format, 0, format.length(), this.m);
        int width2 = (this.d / 5) + this.m.width();
        int i2 = this.l - (width2 / 2);
        if (i2 < 0) {
            i2 = 0;
        } else if (i2 > width - width2) {
            i2 = width - width2;
        }
        canvas.drawText(format, (float) i2, (float) (this.a + this.d), this.e);
        i2 = (this.c - this.b) / 2;
        float f = (float) (this.b / 2);
        float f2 = (float) (this.c / 2);
        float f3 = (float) ((height - i2) - this.b);
        float f4 = (float) (height - i2);
        this.f.setColor(this.h);
        canvas.drawRoundRect(new RectF(0.0f, f3, (float) width, f4), f, f, this.f);
        float f5 = (float) this.l;
        if (f5 < f) {
            f5 = f;
        } else if (f5 > ((float) width) - f) {
            f5 = ((float) width) - f;
        }
        this.f.setColor(this.i);
        if (f5 < ((float) i)) {
            canvas.drawRect(f5, f3, (float) i, f4, this.f);
        } else {
            canvas.drawRect((float) i, f3, f5, f4, this.f);
        }
        this.f.setColor(this.k);
        canvas.drawCircle((float) i, ((float) height) - f2, f, this.f);
        if (f5 < f2) {
            f5 = f2;
        } else if (f5 > ((float) width) - f2) {
            f5 = ((float) width) - f2;
        }
        this.f.setColor(this.j);
        canvas.drawCircle(f5, ((float) height) - f2, f2, this.f);
    }
}
