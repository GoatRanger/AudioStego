package dji.pilot.publics.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import dji.publics.DJIUI.DJIImageView;

public class DJIStateImageView extends DJIImageView {
    private View a = null;
    private float b = 0.3f;
    private boolean c = false;

    public DJIStateImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnlyDisable(boolean z) {
        this.c = z;
    }

    public void setRelativeStateView(View view) {
        this.a = view;
    }

    public void setStateAlpha(float f) {
        this.b = f;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if ((this.c || !(isPressed() || isFocused())) && isEnabled()) {
            setAlpha(1.0f);
            if (this.a != null) {
                this.a.setAlpha(1.0f);
                return;
            }
            return;
        }
        setAlpha(this.b);
        if (this.a != null) {
            this.a.setAlpha(this.b);
        }
    }
}
