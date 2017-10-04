package dji.setting.ui.gimbal.ronin;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.model.P3.DataGimbalPushBatteryInfo;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewText;
import dji.thirdparty.a.c;

public class BatteryView extends ItemViewText {
    public BatteryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        onEventMainThread(DataGimbalPushBatteryInfo.getInstance());
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(DataGimbalPushBatteryInfo dataGimbalPushBatteryInfo) {
        int a = DataGimbalPushBatteryInfo.getInstance().a();
        this.d.setText(String.format("%d%%", new Object[]{Integer.valueOf(a)}));
        if (a <= 10) {
            this.d.setTextColor(getResources().getColor(R.color.setting_ui_battery_red));
        } else {
            this.d.setTextColor(getResources().getColor(R.color.setting_ui_battery_green));
        }
    }
}
