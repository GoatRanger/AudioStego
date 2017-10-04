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
import android.view.View.MeasureSpec;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.pilot.R;

public class DJIStickCirclePgbView extends View {
    private final Paint a = new Paint();
    private Drawable b = null;
    private Drawable c = null;
    private Drawable d = null;
    private Drawable e = null;
    private Drawable f = null;
    private Drawable g = null;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m = 0;
    private int n = 0;
    private int o = 0;
    private int p = 0;
    private int q = 0;
    private final RectF r = new RectF();

    public DJIStickCirclePgbView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (!isInEditMode()) {
            int i5 = this.n;
            int i6 = (i - this.p) / 2;
            this.b.setBounds(i6, i5, this.p + i6, i2 - i5);
            this.c.setBounds(i5, i6, i - i5, this.p + i6);
            a(getWidth() / 2, getHeight());
        }
    }

    public void setProgress(int i, int i2) {
        this.h = i2;
        this.i = i;
        a(getWidth() / 2, getHeight());
        postInvalidate();
    }

    private void a(int i, int i2) {
        int i3 = i - this.q;
        int i4 = this.n;
        int abs = (int) ((((float) Math.abs(this.i)) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) * ((float) ((i - this.q) - i4)));
        if (this.i > 0) {
            this.d.setBounds(0, 0, 0, 0);
            this.e.setBounds(this.q + i, i3, abs + (this.q + i), this.p + i3);
        } else {
            this.d.setBounds((i - this.q) - abs, i3, i - this.q, this.p + i3);
            this.e.setBounds(0, 0, 0, 0);
        }
        i4 = (int) (((float) ((i - this.q) - i4)) * (((float) Math.abs(this.h)) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity));
        if (this.h > 0) {
            this.g.setBounds(0, 0, 0, 0);
            this.f.setBounds(i3, (i - this.q) - i4, this.p + i3, i - this.q);
            return;
        }
        this.g.setBounds(i3, this.q + i, this.p + i3, i4 + (this.q + i));
        this.f.setBounds(0, 0, 0, 0);
    }

    protected void onDraw(Canvas canvas) {
        int i = this.n;
        float width = (float) getWidth();
        float f = width / 2.0f;
        this.r.set((float) i, (float) i, width - ((float) i), width - ((float) i));
        this.a.setColor(this.j);
        this.a.setStyle(Style.FILL);
        canvas.drawCircle(f, f, f, this.a);
        this.a.setStyle(Style.STROKE);
        this.a.setColor(this.l);
        canvas.drawCircle(f, f, f - ((float) i), this.a);
        this.b.draw(canvas);
        this.c.draw(canvas);
        this.d.draw(canvas);
        this.e.draw(canvas);
        this.f.draw(canvas);
        this.g.draw(canvas);
        this.a.setStyle(Style.FILL);
        this.a.setColor(this.k);
        canvas.drawCircle(f, f, (float) this.m, this.a);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        setWillNotDraw(false);
        if (!isInEditMode()) {
            Resources resources = getResources();
            this.j = resources.getColor(R.color.ak);
            this.k = resources.getColor(R.color.om);
            this.l = resources.getColor(R.color.ov);
            this.b = resources.getDrawable(R.drawable.rc_cele_pgb_bg);
            this.c = resources.getDrawable(R.drawable.rc_cele_pgb_bg);
            this.d = resources.getDrawable(R.drawable.rccele_left_pgb);
            this.e = resources.getDrawable(R.drawable.rccele_right_pgb);
            this.f = resources.getDrawable(R.drawable.rccele_top_pgb);
            this.g = resources.getDrawable(R.drawable.rccele_bottom_pgb);
            this.m = resources.getDimensionPixelSize(R.dimen.m_);
            this.o = resources.getDimensionPixelSize(R.dimen.md);
            this.p = resources.getDimensionPixelSize(R.dimen.mb);
            this.n = this.p;
            this.q = this.p / 2;
            this.a.setAntiAlias(true);
        }
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        setMeasuredDimension(size, size);
    }
}
