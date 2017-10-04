package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class VideoCacheOpenSwitch extends ItemViewSwitch {
    DJIGenSettingDataManager a = DJIGenSettingDataManager.getInstance();

    public VideoCacheOpenSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        boolean C = this.a.C();
        this.eS_.setChecked(C);
        a(C);
    }

    private void a(boolean z) {
        if (z) {
            c.a().e(a.OPEN_SWITCH_ON);
        } else {
            c.a().e(a.OPEN_SWITCH_OFF);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        this.a.j(z);
        a(z);
    }
}
