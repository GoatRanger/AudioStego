package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.YAW_MODE;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.controller.DataMgr.e;

public class WayPointYawModeSettingView extends CustomerSpinner {
    private static final String d = "WayPointYawModeSettingView";
    private final String[] e = new String[]{getContext().getString(R.string.gsnew_gs_way_point_auto_fly_setting_yaw_mode_1), getContext().getString(R.string.gsnew_gs_way_point_auto_fly_setting_yaw_mode_2), getContext().getString(R.string.gsnew_gs_way_point_auto_fly_setting_yaw_mode_3)};

    public WayPointYawModeSettingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setData(this.e);
        if (e.getInstance().m() == YAW_MODE.PATH_COURSE) {
            setSelection(0);
        } else if (e.getInstance().m() == YAW_MODE.AUTO_COURSE) {
            setSelection(1);
        } else if (e.getInstance().m() == YAW_MODE.REMOTE_CONTROL) {
            setSelection(2);
        }
    }

    public void setSelection(int i) {
        super.setSelection(i);
        switch (getSelectedItemPosition()) {
            case 0:
                e.getInstance().a(YAW_MODE.PATH_COURSE);
                return;
            case 1:
                e.getInstance().a(YAW_MODE.AUTO_COURSE);
                return;
            case 2:
                e.getInstance().a(YAW_MODE.REMOTE_CONTROL);
                return;
            default:
                return;
        }
    }
}
