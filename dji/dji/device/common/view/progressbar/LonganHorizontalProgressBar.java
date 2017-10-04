package dji.device.common.view.progressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;

public class LonganHorizontalProgressBar extends BaseCustomProgressBar {
    public LonganHorizontalProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LonganHorizontalProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected synchronized void onMeasure(int i, int i2) {
        setMeasuredDimension(MeasureSpec.getSize(i), c(i2));
        this.i = (getMeasuredWidth() - getPaddingRight()) - getPaddingLeft();
    }

    private int c(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int max = (int) (Math.max((float) Math.max(this.e, this.h), Math.abs(this.a.descent() - this.a.ascent())) + ((float) (getPaddingTop() + getPaddingBottom())));
        return mode == Integer.MIN_VALUE ? Math.min(max, size) : max;
    }

    protected synchronized void onDraw(Canvas canvas) {
        float f;
        Object obj;
        canvas.save();
        canvas.translate((float) getPaddingLeft(), (float) (getHeight() / 2));
        Object obj2 = null;
        float progress = (float) ((int) (((((float) getProgress()) * 1.0f) / ((float) getMax())) * ((float) this.i)));
        String str = getProgress() + "%";
        float measureText = this.a.measureText(str);
        float descent = (this.a.descent() + this.a.ascent()) / 2.0f;
        if (this.j) {
            if (progress + measureText > ((float) this.i)) {
                f = ((float) this.i) - measureText;
                obj2 = 1;
            } else {
                f = progress;
            }
            progress = f - ((float) (this.d / 2));
            obj = obj2;
        } else {
            f = progress;
            obj = null;
        }
        if (progress > 0.0f) {
            this.a.setColor(this.f);
            this.a.setStrokeWidth((float) this.e);
            canvas.drawLine(0.0f, 0.0f, progress, 0.0f, this.a);
        }
        if (this.j) {
            this.a.setColor(this.b);
            canvas.drawText(str, f, -descent, this.a);
        }
        if (obj == null) {
            float f2;
            if (this.j) {
                f2 = (((float) (this.d / 2)) + f) + measureText;
            } else {
                f2 = f;
            }
            this.a.setColor(this.g);
            this.a.setStrokeWidth((float) this.h);
            canvas.drawLine(f2, 0.0f, (float) this.i, 0.0f, this.a);
        }
        canvas.restore();
    }
}
