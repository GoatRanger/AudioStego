package dji.setting.ui.gimbal.ronin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.e;
import dji.setting.ui.SettingUIRootView.a;
import dji.setting.ui.widget.ItemViewButtonBig;
import dji.thirdparty.a.c;

public class RollFineTuneView extends ItemViewButtonBig {
    public RollFineTuneView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
        e.a("FPV_GeneralSettings_Gimbal_Button_AdjustGimbalRoll");
        DJIGenSettingDataManager.getInstance().d(true);
        c.a().e(a.CloseBtnClick);
    }
}
