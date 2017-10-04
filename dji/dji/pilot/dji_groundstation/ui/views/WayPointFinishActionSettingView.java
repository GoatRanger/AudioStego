package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.FINISH_ACTION;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.controller.DataMgr.e;

public class WayPointFinishActionSettingView extends CustomerSpinner {
    private static final String e = "WayPointFinishActionSettingView";
    final String[] d = new String[]{getContext().getString(R.string.gsnew_gs_way_point_auto_fly_setting_finish_action_hove), getContext().getString(R.string.gsnew_gs_way_point_auto_fly_setting_finish_action_go_home)};

    public WayPointFinishActionSettingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setData(this.d);
        if (e.getInstance().n() == FINISH_ACTION.NO_LIMIT) {
            setSelection(0);
        } else if (e.getInstance().n() == FINISH_ACTION.GOHOME) {
            setSelection(1);
        }
    }

    public void setSelection(int i) {
        super.setSelection(i);
        switch (getSelectedItemPosition()) {
            case 0:
                e.getInstance().a(FINISH_ACTION.NO_LIMIT);
                return;
            case 1:
                e.getInstance().a(FINISH_ACTION.GOHOME);
                return;
            default:
                return;
        }
    }
}
