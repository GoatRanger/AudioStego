package dji.pilot2.mine.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import dji.pilot.R$styleable;
import dji.publics.DJIUI.DJIImageView;

public class FixRatioImageView extends DJIImageView {
    float a = 0.0f;
    float b = 0.0f;

    public FixRatioImageView(Context context) {
        super(context);
    }

    public FixRatioImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.FixRatioImageView, 0, 0);
        try {
            this.a = obtainStyledAttributes.getFloat(0, 1.0f);
            this.b = obtainStyledAttributes.getFloat(1, 1.0f);
            if (this.a == 0.0f) {
                this.a = 1.0f;
            }
            if (this.b == 0.0f) {
                this.b = 1.0f;
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void setWeight(float f, float f2) {
        this.b = f2;
        this.a = f;
        requestLayout();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        setMeasuredDimension(measuredWidth, (int) ((((float) measuredWidth) * this.b) / this.a));
    }
}
