package dji.setting.ui.battery.m600;

import android.content.Context;
import android.util.AttributeSet;
import dji.pilot.battery.a.a;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewText;

public class CurIndex extends ItemViewText {
    public CurIndex(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.c.setText(getResources().getString(R.string.setting_ui_battery_sub_index) + "  " + a.getInstance().D());
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
