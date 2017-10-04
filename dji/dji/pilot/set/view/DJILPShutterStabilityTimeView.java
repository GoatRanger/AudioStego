package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.pilot.set.R;
import dji.pilot.set.view.base.SetGroupButtonThreeView;

public class DJILPShutterStabilityTimeView extends SetGroupButtonThreeView {
    public DJILPShutterStabilityTimeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected int getTitleId() {
        return R.string.fpv_lp_camera_shutter_stability_time;
    }

    protected void a() {
        this.a = 0;
        setSelect(this.a);
    }

    public void setValue(int i) {
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        a();
    }

    protected int getLeftBtnStrId() {
        return R.string.set_shutter_stability_time_1s;
    }

    protected int getRightBtnStrId() {
        return R.string.set_shutter_stability_time_3s;
    }

    protected int getMiddleBtnStrId() {
        return R.string.set_shutter_stability_time_10s;
    }
}
