package dji.pilot2.usercenter.activity;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

class DJIFlightRecordPlayerActivity$3 implements OnSeekBarChangeListener {
    final /* synthetic */ DJIFlightRecordPlayerActivity a;

    DJIFlightRecordPlayerActivity$3(DJIFlightRecordPlayerActivity dJIFlightRecordPlayerActivity) {
        this.a = dJIFlightRecordPlayerActivity;
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            DJIFlightRecordPlayerActivity.f(this.a).c(i);
        }
    }
}
