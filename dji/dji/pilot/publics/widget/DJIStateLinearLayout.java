package dji.pilot.publics.widget;

import android.content.Context;
import android.util.AttributeSet;
import dji.publics.DJIUI.DJILinearLayout;

public class DJIStateLinearLayout extends DJILinearLayout {
    private float a = 0.3f;

    public DJIStateLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setStateAlpha(float f) {
        this.a = f;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (isPressed() || isFocused() || !isEnabled()) {
            setAlpha(this.a);
        } else {
            setAlpha(1.0f);
        }
    }
}
