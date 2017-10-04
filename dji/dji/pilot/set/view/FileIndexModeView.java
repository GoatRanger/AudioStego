package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetFileIndexMode;
import dji.midware.data.model.P3.DataCameraSetFileIndexMode.FileIndexMode;
import dji.pilot.set.R;
import dji.pilot.set.view.base.SetGroupButtonView;

public class FileIndexModeView extends SetGroupButtonView {
    public FileIndexModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected int getTitleId() {
        return R.string.fpv_gensetting_fileindex;
    }

    public void setValue(int i) {
        DataCameraSetFileIndexMode.getInstance().a(FileIndexMode.find(i));
        DataCameraSetFileIndexMode.getInstance().start(this.c);
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        a();
    }

    protected void a() {
        this.b = DataCameraGetPushStateInfo.getInstance().getFileIndexMode().a();
        setSelect(this.b);
    }

    protected int getLeftBtnStrId() {
        return R.string.set_video_file_reset;
    }

    protected int getRightBtnStrId() {
        return R.string.set_video_file_continuous;
    }
}
