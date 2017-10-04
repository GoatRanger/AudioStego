package dji.pilot.playback.litchi;

import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

class DJIPlayBackVideoPreviewActivity$9 implements OnSeekBarChangeListener {
    final /* synthetic */ DJIPlayBackVideoPreviewActivity a;

    DJIPlayBackVideoPreviewActivity$9(DJIPlayBackVideoPreviewActivity dJIPlayBackVideoPreviewActivity) {
        this.a = dJIPlayBackVideoPreviewActivity;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
        if (DJIPlayBackVideoPreviewActivity.c() == 1 || DJIPlayBackVideoPreviewActivity.c() == 2) {
            DJIPlayBackVideoPreviewActivity.I(this.a);
        }
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        if (DJIPlayBackVideoPreviewActivity.c() == 1) {
            DJIPlayBackVideoPreviewActivity.f(this.a).o();
            if (DJIPlayBackVideoPreviewActivity.J(this.a).hasMessages(8192)) {
                DJIPlayBackVideoPreviewActivity.J(this.a).removeMessages(8192);
                Log.d(this.a.TAG, "remove");
            }
            Log.d(this.a.TAG, "send");
            if (seekBar.getProgress() != seekBar.getMax()) {
                DJIPlayBackVideoPreviewActivity.J(this.a).sendMessageDelayed(DJIPlayBackVideoPreviewActivity.J(this.a).obtainMessage(8192), 500);
                Log.d(this.a.TAG, "send");
                return;
            }
            DJIPlayBackVideoPreviewActivity.g(this.a);
        } else if (DJIPlayBackVideoPreviewActivity.c() == 2) {
            if (DJIPlayBackVideoPreviewActivity.J(this.a).hasMessages(8192)) {
                DJIPlayBackVideoPreviewActivity.J(this.a).removeMessages(8192);
                Log.d(this.a.TAG, "remove");
            }
            DJIPlayBackVideoPreviewActivity.J(this.a).sendMessageDelayed(DJIPlayBackVideoPreviewActivity.J(this.a).obtainMessage(8192), 250);
        }
    }
}
