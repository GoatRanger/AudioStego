package dji.pilot.playback.litchi;

import android.util.Log;
import dji.midware.media.i.d.d;
import dji.pilot.R;

class DJIPlayBackVideoPreviewActivity$23 implements d {
    final /* synthetic */ DJIPlayBackVideoPreviewActivity a;

    DJIPlayBackVideoPreviewActivity$23(DJIPlayBackVideoPreviewActivity dJIPlayBackVideoPreviewActivity) {
        this.a = dJIPlayBackVideoPreviewActivity;
    }

    public void a(dji.midware.media.i.d dVar) {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ DJIPlayBackVideoPreviewActivity$23 a;

            {
                this.a = r1;
            }

            public void run() {
                DJIPlayBackVideoPreviewActivity.j(this.a.a).setText("");
                Log.d(this.a.a.TAG, "onPrepared");
                DJIPlayBackVideoPreviewActivity.l(this.a.a).setText(DJIPlayBackVideoPreviewActivity.a(this.a.a, DJIPlayBackVideoPreviewActivity.f(this.a.a).h()));
                if (DJIPlayBackVideoPreviewActivity.e(this.a.a).getVisibility() == 0) {
                    DJIPlayBackVideoPreviewActivity.e(this.a.a).go();
                    DJIPlayBackVideoPreviewActivity.m(this.a.a).setImageResource(R.drawable.fpv_playback_video_pause);
                }
            }
        });
    }
}
