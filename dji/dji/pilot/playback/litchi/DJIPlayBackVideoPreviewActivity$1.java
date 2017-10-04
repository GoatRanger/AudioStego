package dji.pilot.playback.litchi;

import android.os.Handler.Callback;
import android.os.Message;
import dji.log.DJILogHelper;

class DJIPlayBackVideoPreviewActivity$1 implements Callback {
    final /* synthetic */ DJIPlayBackVideoPreviewActivity a;

    DJIPlayBackVideoPreviewActivity$1(DJIPlayBackVideoPreviewActivity dJIPlayBackVideoPreviewActivity) {
        this.a = dJIPlayBackVideoPreviewActivity;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 100:
                DJIPlayBackVideoPreviewActivity.f(this.a).k();
                break;
            case 1001:
                DJIPlayBackVideoPreviewActivity.a(this.a).go();
                DJIPlayBackVideoPreviewActivity.b(this.a).show();
                if (c.b(DJIPlayBackVideoPreviewActivity.c(this.a).b())) {
                    DJIPlayBackVideoPreviewActivity.d(this.a).show();
                    DJIPlayBackVideoPreviewActivity.a(this.a, true);
                    DJILogHelper.getInstance().LOGD("", "****************Video download eng****************", true, true);
                }
                DJIPlayBackVideoPreviewActivity.e(this.a).setEnabled(true);
                break;
        }
        return false;
    }
}
