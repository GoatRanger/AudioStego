package dji.pilot2.widget;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.widget.TextView;

public class DJIMarqueeTextView extends TextView {
    private boolean a;

    public DJIMarqueeTextView(Context context) {
        this(context, null);
    }

    public DJIMarqueeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = false;
        if (!this.a) {
            setFocusable(true);
            setFocusableInTouchMode(true);
            setSingleLine();
            setEllipsize(TruncateAt.MARQUEE);
            setMarqueeRepeatLimit(-1);
        }
    }

    public DJIMarqueeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = false;
        if (!this.a) {
            setFocusable(true);
            setFocusableInTouchMode(true);
            setSingleLine();
            setEllipsize(TruncateAt.MARQUEE);
            setMarqueeRepeatLimit(-1);
        }
    }

    public void setIsPad(boolean z) {
        this.a = z;
        if (this.a) {
            setFocusable(false);
            setFocusableInTouchMode(false);
        }
    }

    protected void onFocusChanged(boolean z, int i, Rect rect) {
        if (z && !this.a) {
            super.onFocusChanged(z, i, rect);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        if (z && !this.a) {
            super.onWindowFocusChanged(z);
        }
    }

    public boolean isFocused() {
        return true;
    }
}
