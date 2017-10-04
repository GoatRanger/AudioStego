package dji.pilot.publics.d.a;

import android.graphics.Typeface;
import android.text.TextPaint;

public class b extends a {
    private int a = -1;

    public b(Typeface typeface, int i, int i2) {
        super(typeface, i, i2);
    }

    public b(Typeface typeface, int i, int i2, int i3) {
        super(typeface, i, i2);
        this.a = i3;
    }

    public void a(int i) {
        this.a = i;
    }

    public void updateMeasureState(TextPaint textPaint) {
        super.updateMeasureState(textPaint);
        if (this.a != 0) {
            textPaint.setColor(this.a);
        }
    }

    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
    }
}
