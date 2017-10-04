package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.pilot.set.R;
import dji.pilot.set.view.base.SetGroupButtonThreeView;
import dji.sdksharedlib.b.b;

public class AntiBlinkSetterView extends SetGroupButtonThreeView {
    public AntiBlinkSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected int getTitleId() {
        return R.string.fpv_camera_antiblink;
    }

    protected void a() {
        this.a = DataCameraGetPushShotParams.getInstance().getAntiFlicker();
        setSelect(this.a);
        ExposureMode exposureMode = DataCameraGetPushShotParams.getInstance().getExposureMode();
        if (exposureMode == null || exposureMode == ExposureMode.e) {
            setVisibility(8);
        } else {
            setVisibility(0);
        }
    }

    public void setValue(int i) {
        DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
        dataBaseCameraSetting.a(b.q);
        dataBaseCameraSetting.a(i);
        dataBaseCameraSetting.start(this.b);
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        a();
    }

    protected int getLeftBtnStrId() {
        return R.string.set_anti_blink_auto;
    }

    protected int getRightBtnStrId() {
        return R.string.set_anti_blink_60hz;
    }

    protected int getMiddleBtnStrId() {
        return R.string.set_anti_blink_50hz;
    }
}
