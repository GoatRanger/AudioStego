package dji.publics.DJIUI;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;
import dji.frame.widget.R;

public class DJIRadioButton extends RadioButton {
    public static final int TYPEFACE_BOLD = 3;
    public static final int TYPEFACE_DEMI = 0;
    public static final int TYPEFACE_NBOLD = 2;
    public static final int TYPEFACE_NLIGHT = 1;
    public static final int TYPEFACE_NONE = -1;

    public DJIRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DJITextView);
            int i = obtainStyledAttributes.getInt(R.styleable.DJITextView_djiTextFace, -1);
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
