package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.pilot.set.R;
import dji.pilot.set.view.base.SetTextView;

public class ImageFormatSetterView extends SetTextView {
    public ImageFormatSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected int getValuesId() {
        return R.array.camera_pictureformat_value_array;
    }

    protected int getStringArrayId() {
        return R.array.camera_pictureformat_str_array;
    }

    protected int getTitleId() {
        return R.string.fpv_camera_photoformat;
    }

    protected void a() {
        this.c = DataCameraGetPushShotParams.getInstance().getImageFormat();
        setValueView(this.c);
    }

    public void setValue(int i) {
        DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
        dataBaseCameraSetting.a("ImageFormat");
        dataBaseCameraSetting.a(i);
        dataBaseCameraSetting.start(this.f);
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        a();
    }
}
