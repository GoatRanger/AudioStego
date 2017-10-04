package dji.device.common.view.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import dji.pilot.fpv.R;

public class LonganRoundProgressBar extends BaseCustomProgressBar {
    private int l;
    private int m;
    private String n;

    public LonganRoundProgressBar(Context context) {
        this(context, null);
    }

    public LonganRoundProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = a(30);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LonganRoundProgressBar);
        this.l = (int) obtainStyledAttributes.getDimension(R.styleable.LonganRoundProgressBar_longan_radius, (float) this.l);
        obtainStyledAttributes.recycle();
        this.a.setStyle(Style.STROKE);
        this.a.setAntiAlias(true);
        this.a.setDither(true);
        this.a.setStrokeCap(Cap.SQUARE);
    }

    protected synchronized void onMeasure(int i, int i2) {
        this.m = Math.max(this.e, this.h);
        int paddingLeft = (((this.l * 2) + this.m) + getPaddingLeft()) + getPaddingRight();
        paddingLeft = Math.min(resolveSize(paddingLeft, i), resolveSize(paddingLeft, i2));
        this.l = (((paddingLeft - getPaddingLeft()) - getPaddingRight()) - this.m) / 2;
        setMeasuredDimension(paddingLeft, paddingLeft);
    }

    protected synchronized void onDraw(Canvas canvas) {
        String str = this.n == null ? getProgress() + "" : this.n;
        float measureText = this.a.measureText(str);
        float descent = (this.a.descent() + this.a.ascent()) / 2.0f;
        canvas.save();
        canvas.translate((float) (getPaddingLeft() + (this.m / 2)), (float) (getPaddingTop() + (this.m / 2)));
        this.a.setStyle(Style.STROKE);
        this.a.setColor(this.g);
        this.a.setStrokeWidth((float) this.h);
        canvas.drawCircle((float) this.l, (float) this.l, (float) this.l, this.a);
        this.a.setColor(this.f);
        this.a.setStrokeWidth((float) this.e);
        canvas.drawArc(new RectF(0.0f, 0.0f, (float) (this.l * 2), (float) (this.l * 2)), 0.0f, ((((float) getProgress()) * 1.0f) / ((float) getMax())) * 360.0f, false, this.a);
        if (this.j) {
            this.a.setStyle(Style.FILL);
            canvas.drawText(str, ((float) this.l) - (measureText / 2.0f), ((float) this.l) - descent, this.a);
        }
        canvas.restore();
    }

    public synchronized void setProgress(int i, String str) {
        super.setProgress(i);
        this.n = str;
    }

    public synchronized void setProgress(int i) {
        super.setProgress(i);
    }

    public void setProgressText(String str) {
        this.n = str;
    }
}
