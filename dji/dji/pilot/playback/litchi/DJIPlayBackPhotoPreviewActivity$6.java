package dji.pilot.playback.litchi;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIPlayBackPhotoPreviewActivity$6 implements d {
    final /* synthetic */ DJIPlayBackPhotoPreviewActivity a;

    DJIPlayBackPhotoPreviewActivity$6(DJIPlayBackPhotoPreviewActivity dJIPlayBackPhotoPreviewActivity) {
        this.a = dJIPlayBackPhotoPreviewActivity;
    }

    public void onSuccess(Object obj) {
        DJILogHelper.getInstance().LOGD("", "wm220 进入视频回放单张模式 ", false, true);
        DJIPlayBackPhotoPreviewActivity.e(this.a).sendEmptyMessage(1003);
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD("", "wm220 进入单张模式失败" + aVar, false, true);
    }
}
