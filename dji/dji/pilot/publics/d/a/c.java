package dji.pilot.publics.d.a;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class c extends MetricAffectingSpan {
    private int a;
    private int b = 20;

    public c(int i, int i2) {
        this.a = i2;
        this.b = i;
    }

    public void updateMeasureState(TextPaint textPaint) {
        if (this.a != 0) {
            textPaint.setColor(this.a);
        }
        if (this.b > 0) {
            textPaint.setTextSize((float) this.b);
        }
    }

    public void updateDrawState(TextPaint textPaint) {
        updateMeasureState(textPaint);
    }
}
