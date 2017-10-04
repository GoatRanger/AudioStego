package dji.pilot.playback.litchi;

import dji.log.DJILogHelper;
import dji.midware.media.i.d;
import dji.midware.media.i.d.a;

class DJIPlayBackVideoPreviewActivity$20 implements a {
    final /* synthetic */ DJIPlayBackVideoPreviewActivity a;

    DJIPlayBackVideoPreviewActivity$20(DJIPlayBackVideoPreviewActivity dJIPlayBackVideoPreviewActivity) {
        this.a = dJIPlayBackVideoPreviewActivity;
    }

    public void a(d dVar, final int i) {
        DJILogHelper.getInstance().LOGD("mediaPlayer", "percent=" + i, true, false);
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ DJIPlayBackVideoPreviewActivity$20 b;

            public void run() {
                if (!DJIPlayBackVideoPreviewActivity.h(this.b.a)) {
                    DJIPlayBackVideoPreviewActivity.b(this.b.a, true);
                }
                if (i < 100) {
                    DJIPlayBackVideoPreviewActivity.i(this.b.a).show();
                    DJIPlayBackVideoPreviewActivity.j(this.b.a).setText(i + "%");
                    return;
                }
                DJIPlayBackVideoPreviewActivity.b(this.b.a, false);
                DJIPlayBackVideoPreviewActivity.i(this.b.a).go();
            }
        });
    }
}
