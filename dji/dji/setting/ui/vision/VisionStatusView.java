package dji.setting.ui.vision;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.pilot.publics.e.a;
import dji.setting.ui.widget.ItemViewGroup;
import dji.thirdparty.a.c;

public class VisionStatusView extends ItemViewGroup {
    public VisionStatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onEventMainThread(ProductType productType) {
        if (a.k(productType)) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            onEventMainThread(i.getInstance().c());
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
