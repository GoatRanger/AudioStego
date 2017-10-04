package dji.pilot.playback.litchi;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

class DJIPlayBackVideoPreviewActivity$12 implements OnCompletionListener {
    final /* synthetic */ DJIPlayBackVideoPreviewActivity a;

    DJIPlayBackVideoPreviewActivity$12(DJIPlayBackVideoPreviewActivity dJIPlayBackVideoPreviewActivity) {
        this.a = dJIPlayBackVideoPreviewActivity;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        DJIPlayBackVideoPreviewActivity.g(this.a);
    }
}
