package dji.pilot2.upgrade.rollback.widget;

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

public class DJIRBProgressBar extends View implements c {
    private final Paint a;
    private int b;
    private int c;
    private int d;
    private Drawable e;
    private int f;
    private final RectF g;

    public DJIRBProgressBar(Context context) {
        this(context, null);
    }

    public DJIRBProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJIRBProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Paint();
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = null;
        this.f = 0;
        this.g = new RectF();
        if (!isInEditMode()) {
            a(context);
        }
    }

    public void setProgress(int i) {
        this.f = i;
        postInvalidate();
    }

    public int getProgress() {
        return this.f;
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

    private void a(Context context) {
        Resources resources = context.getResources();
        this.b = resources.getColor(R.color.om);
        this.c = resources.getColor(R.color.o2);
        this.d = resources.getDimensionPixelSize(R.dimen.ga);
        this.e = resources.getDrawable(R.drawable.v2_rb_icon);
        this.a.setAntiAlias(true);
    }

    protected void onDraw(Canvas canvas) {
        if (!isInEditMode()) {
            int width = getWidth();
            float f = ((float) width) / 2.0f;
            this.a.setStrokeWidth((float) this.d);
            this.a.setColor(this.b);
            this.a.setStyle(Style.STROKE);
            canvas.drawCircle(f, f, f - ((float) this.d), this.a);
            this.a.setStrokeWidth((float) this.d);
            this.a.setColor(this.c);
            this.a.setStyle(Style.STROKE);
            int i = this.d;
            this.g.set((float) i, (float) i, (float) (width - i), (float) (width - i));
            canvas.save();
            canvas.translate(f, f);
            canvas.rotate(-90.0f);
            canvas.translate(-f, -f);
            canvas.drawArc(this.g, 0.0f, (float) ((this.f * 360) / 100), false, this.a);
            canvas.restore();
            this.e.draw(canvas);
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.e != null) {
            int intrinsicWidth = this.e.getIntrinsicWidth();
            int intrinsicHeight = this.e.getIntrinsicHeight();
            int i5 = (i - intrinsicWidth) / 2;
            int i6 = (i - intrinsicHeight) / 2;
            this.e.setBounds(i5, i6, intrinsicWidth + i5, intrinsicHeight + i6);
        }
    }
}
