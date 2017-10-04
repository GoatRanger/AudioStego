package dji.pilot.publics.objects;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

class f$2 implements OnSeekBarChangeListener {
    final /* synthetic */ f a;

    f$2(f fVar) {
        this.a = fVar;
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        this.a.b();
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }
}
