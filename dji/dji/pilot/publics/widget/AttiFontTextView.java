package dji.pilot.publics.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import dji.pilot.R$styleable;

public class AttiFontTextView extends TextView {
    public static final int a = -1;
    public static final int b = 0;
    public static Typeface c;

    public AttiFontTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.AttiFontTextView);
            int i = obtainStyledAttributes.getInt(0, -1);
            obtainStyledAttributes.recycle();
            Typeface typface = getTypface(context, i);
            if (typface != null) {
                setTypeface(typface);
            }
        }
    }

    public static Typeface getTypface(Context context, int i) {
        initTypeface(context);
        if (i == 0) {
            return c;
        }
        return null;
    }

    public static void initTypeface(Context context) {
        if (c == null) {
            c = Typeface.createFromAsset(context.getAssets(), "fonts/pirulen.ttf");
        }
    }
}
