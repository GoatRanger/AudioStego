package dji.setting.ui.rc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.pilot.publics.e.a;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewGroup;

public class OriginCalibrationGroupView extends ItemViewGroup {
    public OriginCalibrationGroupView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (a.c(i.getInstance().c())) {
            setVisibility(8);
        }
    }

    public void onClick(View view) {
        if (ServiceManager.getInstance().isRemoteOK()) {
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_rc_calibration_tip);
        } else {
            super.onClick(view);
        }
    }
}
