package dji.publics.DJIUI;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;
import dji.frame.widget.R;
import dji.publics.d.a;
import dji.publics.d.c;

public class DJIEditText extends EditText implements a, c {
    public static final int TYPEFACE_BOLD = 3;
    public static final int TYPEFACE_DEMI = 0;
    public static final int TYPEFACE_NBOLD = 2;
    public static final int TYPEFACE_NLIGHT = 1;
    public static final int TYPEFACE_NONE = -1;
    private AnimatorListenerAdapter animGoListener = new AnimatorListenerAdapter() {
        public void onAnimationEnd(Animator animator) {
            DJIEditText.this.go();
            DJIEditText.this.animate().setListener(null);
        }
    };
    private AnimatorListenerAdapter animShowListener = new AnimatorListenerAdapter() {
        public void onAnimationStart(Animator animator) {
            DJIEditText.this.show();
        }

        public void onAnimationEnd(Animator animator) {
            DJIEditText.this.animate().setListener(null);
        }
    };

    public DJIEditText(Context context) {
        super(context);
    }

    public DJIEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initTypeface(context, attributeSet);
    }

    public DJIEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initTypeface(context, attributeSet);
    }

    private void initTypeface(Context context, AttributeSet attributeSet) {
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
