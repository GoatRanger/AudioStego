package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.config.P3.b.a;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.pilot.set.R;
import dji.pilot.set.view.base.SetGroupButtonView;

public class VideoFormatSetterView extends SetGroupButtonView {
    public VideoFormatSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected int getTitleId() {
        return R.string.fpv_camera_videoformat;
    }

    public void setValue(int i) {
        DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
        dataBaseCameraSetting.a(a.s);
        dataBaseCameraSetting.a(i).start(this.c);
    }

    protected void a() {
        this.b = DataCameraGetPushShotParams.getInstance().getVideoStoreFormat();
        setSelect(this.b);
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        a();
    }

    protected int getLeftBtnStrId() {
        return R.string.set_video_format_MOV;
    }

    protected int getRightBtnStrId() {
        return R.string.set_video_format_MP4;
    }
}
