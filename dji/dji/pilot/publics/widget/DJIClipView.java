package dji.pilot.publics.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import dji.pilot.R$styleable;

public class DJIClipView extends View {
    public static final int a = 0;
    public static final int b = 1;
    private int c = 1;
    private float d = 0.0f;
    private Paint e = null;
    private final Path f = new Path();
    private Drawable g = null;
    private final RectF h = new RectF();
    private boolean i = true;
    private int j = 0;
    private float k = 0.0f;
    private Path l;

    public DJIClipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public void updateScale(float f) {
        if (f != this.d) {
            this.d = f;
            this.f.reset();
            if (0.0f < f && f < 1.0f) {
                int width = getWidth();
                int height = getHeight();
                if (this.c == 1) {
                    float f2 = (360.0f * f) + this.k;
                    this.h.set(0.0f, 0.0f, (float) width, (float) height);
                    this.f.arcTo(this.h, 0.0f, f2, false);
                    this.f.lineTo(((float) width) / 2.0f, ((float) height) / 2.0f);
                    this.f.close();
                }
            }
            postInvalidate();
        }
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (!isInEditMode()) {
            this.e = new Paint();
            this.e.setAntiAlias(true);
            this.e.setColor(-16777217);
            setLayerType(1, this.e);
            setWillNotDraw(false);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ClipView);
            this.c = obtainStyledAttributes.getInt(3, 1);
            this.g = obtainStyledAttributes.getDrawable(4);
            this.i = obtainStyledAttributes.getBoolean(2, true);
            this.j = obtainStyledAttributes.getDimensionPixelOffset(0, 0);
            this.k = obtainStyledAttributes.getFloat(1, 0.0f);
            obtainStyledAttributes.recycle();
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.g != null) {
            this.g.setBounds(0, 0, i, i2);
        }
        if (this.j != 0 && this.l == null) {
            this.l = new Path();
            this.l.addCircle(((float) i) / 2.0f, ((float) i2) / 2.0f, (float) this.j, Direction.CW);
        }
    }

    protected void onMeasure(int i, int i2) {
        int intrinsicWidth;
        int i3 = 0;
        if (this.i) {
            intrinsicWidth = this.g != null ? this.g.getIntrinsicWidth() : 0;
            if (this.g != null) {
                i3 = this.g.getIntrinsicHeight();
            }
        } else {
            intrinsicWidth = MeasureSpec.getSize(i);
            i3 = MeasureSpec.getSize(i2);
        }
        setMeasuredDimension(MeasureSpec.makeMeasureSpec(intrinsicWidth, 1073741824), MeasureSpec.makeMeasureSpec(i3, 1073741824));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.g == null) {
            return;
        }
        if (this.f.isEmpty()) {
            this.g.draw(canvas);
            return;
        }
        canvas.save();
        canvas.clipPath(this.f);
        if (this.j != 0) {
            canvas.clipPath(this.l, Op.DIFFERENCE);
        }
        this.g.draw(canvas);
        canvas.restore();
    }
}
