package dji.pilot.publics.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class DJIStateButton extends Button {
    private float a = 0.3f;

    public DJIStateButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setStateAlpha(float f) {
        this.a = f;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (!isPressed() && !isFocused() && !isSelected() && !isHovered()) {
        }
    }
}
