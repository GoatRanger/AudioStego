package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.config.P3.b.a;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.pilot.set.R;
import dji.pilot.set.view.base.SetTextView;

public class CameraColorView extends SetTextView {
    public CameraColorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected int getValuesId() {
        return R.array.camera_digitalfilter_value_array;
    }

    protected int getStringArrayId() {
        return R.array.camera_digitalfilter_array;
    }

    protected int getTitleId() {
        return R.string.set_color;
    }

    protected void a() {
        this.c = DataCameraGetPushShotParams.getInstance().getDigitalFilter();
        setValueView(this.c);
    }

    public void setValue(int i) {
        new DataBaseCameraSetting().a(a.Z).a(i).start(null);
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        a();
    }
}
