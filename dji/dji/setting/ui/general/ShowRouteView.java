package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.setting.ui.widget.ItemViewSwitch;

public class ShowRouteView extends ItemViewSwitch {
    public ShowRouteView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.eS_.setChecked(DJIGenSettingDataManager.getInstance().t());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z != DJIGenSettingDataManager.getInstance().t()) {
            DJIGenSettingDataManager.getInstance().g(z);
        }
    }
}
