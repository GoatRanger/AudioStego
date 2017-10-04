package dji.device.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.dji.frame.c.e;
import dji.pilot.fpv.R;
import dji.pilot.visual.a.d;
import dji.publics.d.c;

public class DJIGridLineCompat extends View implements c {
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 4;
    private static final int d = 3;
    private final Paint e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private Drawable n;
    private int o;

    public DJIGridLineCompat(Context context) {
        this(context, null);
    }

    public DJIGridLineCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJIGridLineCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new Paint();
        this.f = 4;
        this.g = 4;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = null;
        this.o = 1;
        if (!isInEditMode()) {
            a();
            if (attributeSet != null) {
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GridLine_c, i, 0);
                this.f = obtainStyledAttributes.getInt(R.styleable.GridLine_c_horizontalLines_c, this.f);
                this.g = obtainStyledAttributes.getInt(R.styleable.GridLine_c_verticalLines_c, this.g);
                this.h = obtainStyledAttributes.getColor(R.styleable.GridLine_c_horizontalLineColor_c, this.h);
                this.i = obtainStyledAttributes.getColor(R.styleable.GridLine_c_verticalLineColor_c, this.i);
                this.m = obtainStyledAttributes.getColor(R.styleable.GridLine_c_diagonalLineColor_c, this.m);
                this.j = obtainStyledAttributes.getDimensionPixelSize(R.styleable.GridLine_c_horizontalLineWidth_c, this.j);
                this.k = obtainStyledAttributes.getDimensionPixelSize(R.styleable.GridLine_c_verticalLineWidth_c, this.k);
                this.l = obtainStyledAttributes.getDimensionPixelSize(R.styleable.GridLine_c_diagonalLineWidth_c, this.l);
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
        this.h = getResources().getColor(R.color.grid_line);
        this.i = getResources().getColor(R.color.grid_line);
        this.m = getResources().getColor(R.color.grid_line);
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
