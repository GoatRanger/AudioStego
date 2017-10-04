package dji.pilot.fpv.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.dji.frame.c.e;
import dji.pilot.R;
import dji.pilot.R$styleable;
import dji.pilot.visual.a.d;
import dji.publics.DJIUI.DJIView;
import dji.publics.d.c;

public class DJIGridLine extends DJIView implements c {
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 4;
    private static final int d = 3;
    private final Paint e = new Paint();
    private int f = 4;
    private int g = 4;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m = 0;
    private Drawable n = null;
    private int o = 1;

    public DJIGridLine(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a();
            if (attributeSet != null) {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.GridLine, 0, 0);
                this.f = obtainStyledAttributes.getInt(0, this.f);
                this.g = obtainStyledAttributes.getInt(1, this.g);
                this.h = obtainStyledAttributes.getColor(2, this.h);
                this.i = obtainStyledAttributes.getColor(3, this.i);
                this.m = obtainStyledAttributes.getColor(4, this.m);
                this.j = obtainStyledAttributes.getDimensionPixelSize(5, this.j);
                this.k = obtainStyledAttributes.getDimensionPixelSize(6, this.k);
                this.l = obtainStyledAttributes.getDimensionPixelSize(7, this.l);
                obtainStyledAttributes.recycle();
            }
        }
    }

    public void setType(int i) {
        if (this.o != i) {
            this.o = i;
            postInvalidate();
        }
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

    private void a() {
        setWillNotDraw(false);
        this.f = 4;
        this.g = 4;
        this.h = getResources().getColor(R.color.e8);
        this.i = getResources().getColor(R.color.e8);
        this.m = getResources().getColor(R.color.e8);
        this.j = e.b(getContext(), 1.0f);
        this.k = e.b(getContext(), 1.0f);
        this.l = e.b(getContext(), 1.0f);
        this.n = getResources().getDrawable(R.drawable.grid_center_icon);
        this.e.setAntiAlias(true);
    }

    private Paint a(float f) {
        this.e.setColor(this.h);
        int i = f == 0.0f ? this.j * 3 : (int) (((float) this.j) / f);
        if (i > this.j * 3) {
            i = this.j * 3;
        }
        this.e.setStrokeWidth((float) i);
        return this.e;
    }

    private Paint b(float f) {
        this.e.setColor(this.i);
        int i = f == 0.0f ? this.k * 3 : (int) (((float) this.k) / f);
        if (i > this.k * 3) {
            i = this.k * 3;
        }
        this.e.setStrokeWidth((float) i);
        return this.e;
    }

    private Paint c(float f) {
        this.e.setColor(this.m);
        int i = f == 0.0f ? this.l * 3 : (int) (((float) this.l) / f);
        if (i > this.l * 3) {
            i = this.l * 3;
        }
        this.e.setStrokeWidth((float) i);
        return this.e;
    }

    protected void onDraw(Canvas canvas) {
        int i = 0;
        float scaleX = ((View) getParent()).getScaleX();
        int measuredWidth = getMeasuredWidth() - 1;
        int measuredHeight = getMeasuredHeight() - 1;
        if (this.o == 2 || this.o == 1) {
            float f = ((float) (measuredHeight - this.j)) / ((float) (this.f - 1));
            Paint a = a(scaleX);
            float round = (float) Math.round(((float) this.j) * d.c);
            for (int i2 = 0; i2 < this.f; i2++) {
                canvas.drawLine(1.0f, round, (float) (measuredWidth - 1), round, a);
                round += f;
            }
            float f2 = ((float) (measuredWidth - this.k)) / ((float) (this.g - 1));
            Paint b = b(scaleX);
            float round2 = (float) Math.round(((float) this.k) * d.c);
            while (i < this.g) {
                canvas.drawLine(round2, 1.0f, round2, (float) (measuredHeight - 1), b);
                i++;
                round2 += f2;
            }
            if (this.o == 2) {
                a = c(scaleX);
                canvas.drawLine(1.0f, 1.0f, (float) (measuredWidth - 1), (float) (measuredHeight - 1), a);
                canvas.drawLine(1.0f, (float) (measuredHeight - 1), (float) (measuredWidth - 1), 1.0f, a);
            }
        } else if (this.o == 4) {
            this.n.draw(canvas);
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int intrinsicWidth = this.n.getIntrinsicWidth();
        int intrinsicHeight = this.n.getIntrinsicHeight();
        int i5 = (i - intrinsicWidth) / 2;
        int i6 = (i2 - intrinsicHeight) / 2;
        this.n.setBounds(i5, i6, intrinsicWidth + i5, intrinsicHeight + i6);
    }
}
