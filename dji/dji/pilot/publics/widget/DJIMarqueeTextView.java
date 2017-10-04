package dji.pilot.publics.widget;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import dji.publics.DJIUI.DJITextView;

public class DJIMarqueeTextView extends DJITextView {
    public DJIMarqueeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setEllipsize(TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(-1);
        setSingleLine();
    }

    public boolean isFocused() {
        return true;
    }
}
