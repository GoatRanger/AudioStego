package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.fpv.control.DJIGenSettingDataManager;

public class WayPointLimitsDescView extends TextView {
    private static final String a = "WayPointLimitsDescView";

    public WayPointLimitsDescView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (DJIGenSettingDataManager.getInstance().v() == 0) {
            setText(String.format(getContext().getString(R.string.gsnew_gs_way_point_limits), new Object[]{Integer.valueOf((int) f.a((float) getMaxMissionRadius())), "FT", Integer.valueOf((int) f.a(5000.0f)), "FT"}));
            return;
        }
        setText(String.format(getContext().getString(R.string.gsnew_gs_way_point_limits), new Object[]{Integer.valueOf(getMaxMissionRadius()), "M", Integer.valueOf(5000), "M"}));
    }

    private int getMaxMissionRadius() {
        return 2000;
    }
}
