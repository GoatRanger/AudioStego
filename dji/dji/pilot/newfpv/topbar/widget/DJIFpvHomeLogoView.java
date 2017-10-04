package dji.pilot.newfpv.topbar.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.visual.a.d;
import dji.publics.DJIUI.DJIImageView;

public class DJIFpvHomeLogoView extends DJIImageView {
    private View a = null;

    public DJIFpvHomeLogoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setRelativeStateView(View view) {
        this.a = view;
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (!isInEditMode()) {
            Drawable background = getBackground();
            if (isPressed() || isFocused()) {
                if (background != null) {
                    setAlpha(0.2f);
                } else {
                    setAlpha(0.2f);
                }
                if (this.a != null) {
                    this.a.setAlpha(0.0f);
                    return;
                }
                return;
            }
            if (background != null) {
                setAlpha(1.0f);
            } else {
                setAlpha(1.0f);
            }
            if (this.a != null) {
                this.a.setAlpha(d.c);
            }
        }
    }
}
