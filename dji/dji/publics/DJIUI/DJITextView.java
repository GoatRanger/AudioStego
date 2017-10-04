package dji.publics.DJIUI;

import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;
import dji.frame.widget.R;
import dji.publics.d.a;
import dji.publics.d.c;

public class DJITextView extends TextView implements a, c {
    public static Typeface BOLD = null;
    public static Typeface DEMI = null;
    public static Typeface NBOLD = null;
    public static Typeface NLIGHT = null;
    public static final int TYPEFACE_BOLD = 3;
    public static final int TYPEFACE_DEMI = 0;
    public static final int TYPEFACE_NBOLD = 2;
    public static final int TYPEFACE_NLIGHT = 1;
    public static final int TYPEFACE_NONE = -1;
    public static Typeface face;
    private AnimatorListenerAdapter animGoListener = new 1(this);
    private AnimatorListenerAdapter animShowListener = new 2(this);

    public static Typeface getTypface(Context context, int i) {
        initTypeface(context);
        if (i == 0) {
            return DEMI;
        }
        if (1 == i) {
            return NLIGHT;
        }
        if (2 == i) {
            return NBOLD;
        }
        if (3 == i) {
            return BOLD;
        }
        return null;
    }

    public static void initTypeface(Context context) {
        if (DEMI == null) {
            AssetManager assets = context.getAssets();
            DEMI = Typeface.createFromAsset(assets, "fonts/Dji-Demi.ttf");
            NLIGHT = Typeface.createFromAsset(assets, "fonts/Dji_NLight.ttf");
            NBOLD = Typeface.createFromAsset(assets, "fonts/Dji_NBold.ttf");
            BOLD = Typeface.createFromAsset(assets, "fonts/Dji-Bold.ttf");
            face = DEMI;
        }
    }

    public DJITextView(Context context, AttributeSet attributeSet) {
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

    public void show() {
        if (getVisibility() != 0) {
            setVisibility(0);
        }
    }

    public void hide() {
        if (getVisibility() != 4) {
            setVisibility(4);
        }
    }

    public void go() {
        if (getVisibility() != 8) {
            setVisibility(8);
        }
    }

    public void animGo() {
        animate().alpha(0.0f).setDuration(300).setListener(this.animGoListener).start();
    }

    public void animShow() {
        animate().alpha(1.0f).setDuration(300).setListener(this.animShowListener).start();
    }
}
