package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.pilot.set.R;
import dji.pilot.set.view.base.SetSwitchView;

public class CameraHardCodeSetterView extends SetSwitchView {
    public CameraHardCodeSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void a() {
    }

    protected void setValue(boolean z) {
    }

    protected int getTitleId() {
        return R.string.set_camera_hardware_decode;
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        a();
    }
}
