package dji.device.widget;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class a extends MetricAffectingSpan {
    private Typeface a = null;
    private final int b;
    private final int c;

    public a(Typeface typeface, int i, int i2) {
        this.a = typeface;
        this.b = i;
        this.c = i2;
    }

    public void updateMeasureState(TextPaint textPaint) {
        boolean z = false;
        if (this.b > 0) {
            int style;
            float f;
            if (this.a == null) {
                this.a = Typeface.defaultFromStyle(this.b);
            } else {
                this.a = Typeface.create(this.a, this.b);
            }
            textPaint.setTypeface(this.a);
            if (this.a != null) {
                style = this.a.getStyle();
            } else {
                style = 0;
            }
            style = (style ^ -1) & this.b;
            if ((style & 1) != 0) {
                z = true;
            }
            textPaint.setFakeBoldText(z);
            if ((style & 2) != 0) {
                f = -0.25f;
            } else {
                f = 0.0f;
            }
            textPaint.setTextSkewX(f);
        } else {
            textPaint.setFakeBoldText(false);
            textPaint.setTextSkewX(0.0f);
            textPaint.setTypeface(this.a);
        }
        if (this.c > 0) {
            textPaint.setTextSize((float) this.c);
        }
    }

    public void updateDrawState(TextPaint textPaint) {
        updateMeasureState(textPaint);
    }
}
