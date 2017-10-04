package dji.pilot2.upgrade.rollback;

import android.content.Context;
import android.util.AttributeSet;
import dji.publics.DJIUI.DJILinearLayout;

public class DJIRollBackUpgradeP4View extends DJILinearLayout {
    public DJIRollBackUpgradeP4View(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
