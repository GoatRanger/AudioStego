package dji.pilot.playback.litchi;

import dji.log.DJILogHelper;
import dji.midware.media.i.d;
import dji.midware.media.i.d.b;

class DJIPlayBackVideoPreviewActivity$21 implements b {
    final /* synthetic */ DJIPlayBackVideoPreviewActivity a;

    DJIPlayBackVideoPreviewActivity$21(DJIPlayBackVideoPreviewActivity dJIPlayBackVideoPreviewActivity) {
        this.a = dJIPlayBackVideoPreviewActivity;
    }

    public void a(d dVar) {
        DJILogHelper.getInstance().LOGD("mediaPlayer", "Completion", true, true);
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ DJIPlayBackVideoPreviewActivity$21 a;

            {
                this.a = r1;
            }

            public void run() {
                DJILogHelper.getInstance().LOGD("", "handlePlayComplete", false, true);
                DJIPlayBackVideoPreviewActivity.g(this.a.a);
            }
        });
    }
}
