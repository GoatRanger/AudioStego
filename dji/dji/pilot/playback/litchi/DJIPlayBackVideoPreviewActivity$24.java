package dji.pilot.playback.litchi;

import dji.log.DJILogHelper;
import dji.midware.media.i.d;
import dji.midware.media.i.d.f;

class DJIPlayBackVideoPreviewActivity$24 implements f {
    final /* synthetic */ DJIPlayBackVideoPreviewActivity a;

    DJIPlayBackVideoPreviewActivity$24(DJIPlayBackVideoPreviewActivity dJIPlayBackVideoPreviewActivity) {
        this.a = dJIPlayBackVideoPreviewActivity;
    }

    public void a(d dVar, final int i, final int i2) {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ DJIPlayBackVideoPreviewActivity$24 c;

            public void run() {
                DJILogHelper.getInstance().LOGD("mediaPlayer", "position=" + i + " cached=" + i2, true, true);
                DJIPlayBackVideoPreviewActivity.n(this.c.a).setText(DJIPlayBackVideoPreviewActivity.a(this.c.a, i) + dji.pilot.usercenter.protocol.d.t);
                if (DJIPlayBackVideoPreviewActivity.f(this.c.a).h() != 0) {
                    DJIPlayBackVideoPreviewActivity.o(this.c.a).setProgress((int) ((((float) i) * 1000.0f) / ((float) DJIPlayBackVideoPreviewActivity.f(this.c.a).h())));
                    DJIPlayBackVideoPreviewActivity.o(this.c.a).setSecondaryProgress((int) ((((float) i2) * 1000.0f) / ((float) DJIPlayBackVideoPreviewActivity.f(this.c.a).h())));
                }
            }
        });
    }
}
