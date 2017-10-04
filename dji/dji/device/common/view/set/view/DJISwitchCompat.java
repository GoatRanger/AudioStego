package dji.device.common.view.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Switch;

public class DJISwitchCompat extends Switch {
    private View a;
    private float b = 0.3f;

    public DJISwitchCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    public DJISwitchCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (!isInEditMode()) {
        }
    }

    public void setRelativeStateView(View view) {
        this.a = view;
    }

    public void setRelativeStateView(View view, float f) {
        this.a = view;
        this.b = f;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (isFocused() || !isEnabled()) {
            setAlpha(this.b);
            if (this.a != null) {
                this.a.setAlpha(this.b);
                return;
            }
            return;
        }
        setAlpha(1.0f);
        if (this.a != null) {
            this.a.setAlpha(1.0f);
        }
    }
}
