package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.a.a;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.setting.ui.widget.ItemViewSwitch;

public class AMapView extends ItemViewSwitch {
    public AMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    private void a() {
        if (a.getInstance().d()) {
            setVisibility(0);
            this.eS_.setChecked(DJIGenSettingDataManager.getInstance().q());
            return;
        }
        setVisibility(8);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z != DJIGenSettingDataManager.getInstance().q()) {
            DJIGenSettingDataManager.getInstance().e(z);
        }
    }
}
