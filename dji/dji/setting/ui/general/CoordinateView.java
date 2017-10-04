package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.setting.ui.widget.ItemViewSwitch;

public class CoordinateView extends ItemViewSwitch {
    public CoordinateView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.eS_.setChecked(DJIGenSettingDataManager.getInstance().s());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z != DJIGenSettingDataManager.getInstance().s()) {
            DJIGenSettingDataManager.getInstance().f(z);
        }
    }
}
