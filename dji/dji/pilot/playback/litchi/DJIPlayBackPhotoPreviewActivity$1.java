package dji.pilot.playback.litchi;

import android.os.Handler.Callback;
import android.os.Message;
import dji.log.DJILogHelper;
import dji.pilot.publics.objects.a;

class DJIPlayBackPhotoPreviewActivity$1 implements Callback {
    final /* synthetic */ DJIPlayBackPhotoPreviewActivity a;

    DJIPlayBackPhotoPreviewActivity$1(DJIPlayBackPhotoPreviewActivity dJIPlayBackPhotoPreviewActivity) {
        this.a = dJIPlayBackPhotoPreviewActivity;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1001:
                DJILogHelper.getInstance().LOGD("", "download complete handle", false, true);
                DJIPlayBackPhotoPreviewActivity.a(this.a);
                DJIPlayBackPhotoPreviewActivity.b(this.a).setPagingEnabled(true);
                if (DJIPlayBackPhotoPreviewActivity.c(this.a).getVisibility() == 0) {
                    DJIPlayBackPhotoPreviewActivity.c(this.a).setEnabled(true);
                    break;
                }
                break;
            case 1002:
                if (DJIPlayBackPhotoPreviewActivity.c(this.a).getVisibility() == 0) {
                    DJIPlayBackPhotoPreviewActivity.c(this.a).setEnabled(true);
                    break;
                }
                break;
            case 1003:
                DJIPlayBackVideoPreviewActivity.a(this.a, this.a.a(DJIPlayBackPhotoPreviewActivity.d(this.a)), 2, a.a);
                break;
        }
        return false;
    }
}
