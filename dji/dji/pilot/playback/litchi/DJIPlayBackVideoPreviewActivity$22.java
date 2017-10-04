package dji.pilot.playback.litchi;

import android.util.Log;
import dji.midware.media.i.d;
import dji.midware.media.i.d.e;

class DJIPlayBackVideoPreviewActivity$22 implements e {
    final /* synthetic */ DJIPlayBackVideoPreviewActivity a;

    DJIPlayBackVideoPreviewActivity$22(DJIPlayBackVideoPreviewActivity dJIPlayBackVideoPreviewActivity) {
        this.a = dJIPlayBackVideoPreviewActivity;
    }

    public void a(d dVar) {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ DJIPlayBackVideoPreviewActivity$22 a;

            {
                this.a = r1;
            }

            public void run() {
                DJIPlayBackVideoPreviewActivity.j(this.a.a).setText("");
                Log.d(this.a.a.TAG, "isplaying:" + DJIPlayBackVideoPreviewActivity.f(this.a.a).q());
                if (DJIPlayBackVideoPreviewActivity.e(this.a.a).getVisibility() == 0 && DJIPlayBackVideoPreviewActivity.f(this.a.a).q() == 10) {
                    DJIPlayBackVideoPreviewActivity.e(this.a.a).go();
                }
                DJIPlayBackVideoPreviewActivity.k(this.a.a);
            }
        });
    }
}
