package dji.device.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import dji.publics.DJIUI.DJIImageView;

public class DJIStateImageViewCompat extends DJIImageView {
    private View a = null;
    private float b = 0.3f;

    public DJIStateImageViewCompat(Context context) {
        super(context);
    }

    public DJIStateImageViewCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setRelativeStateView(View view) {
        this.a = view;
    }

    public void setStateAlpha(float f) {
        this.b = f;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (isPressed() || isFocused() || !isEnabled()) {
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
