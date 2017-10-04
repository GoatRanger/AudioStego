package dji.pilot.publics.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;
import dji.pilot.R$styleable;

public class DJIEditText extends EditText {
    public static final int a = -1;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;

    public DJIEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public DJIEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.DJITextView);
            int i = obtainStyledAttributes.getInt(0, -1);
            obtainStyledAttributes.recycle();
            Typeface typeface = getTypeface();
            if (i == 0) {
                return;
            }
            if (1 == i) {
                typeface = Typeface.create("sans-serif-light", 0);
                if (typeface != null) {
                    setTypeface(typeface);
                }
            } else if (2 == i) {
                if (typeface != null && !typeface.isBold()) {
                    setTypeface(typeface, 1);
                }
            } else if (3 == i && typeface != null && !typeface.isBold()) {
                setTypeface(typeface, 1);
            }
        }
    }
}
