package dji.pilot.usercenter.widget;

import android.content.Context;
import android.util.AttributeSet;
import dji.publics.DJIUI.DJILinearLayout;

public class DJINonBgLinearLayout extends DJILinearLayout {
    public DJINonBgLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getMinimumHeight() {
        return 0;
    }

    protected int getSuggestedMinimumHeight() {
        return 0;
    }
}
