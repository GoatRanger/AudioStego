package dji.setting.ui.battery.inspire2;

import android.content.Context;
import android.util.AttributeSet;
import dji.pilot.setting.ui.R;
import dji.setting.ui.battery.m600.InfoView;

public class Inspire2InfoView extends InfoView {
    public final String a = "Inspire2InfoView";

    public Inspire2InfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected int getLayout() {
        return R.layout.setting_ui_battery_info_inspire2;
    }
}
