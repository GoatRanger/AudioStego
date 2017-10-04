package dji.pilot2.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.dji.frame.c.e;
import dji.pilot.publics.widget.DJIStateTextView;

public class DJINewStateTextView extends DJIStateTextView {
    private float a = ((float) e.c(getContext(), getTextSize()));

    public DJINewStateTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (isPressed() || isFocused() || isSelected()) {
            setTextSize(this.a + 1.0f);
        } else {
            setTextSize(this.a);
        }
    }
}
