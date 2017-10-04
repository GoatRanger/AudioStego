package android.databinding.a;

import android.databinding.n;
import android.databinding.o;
import android.databinding.p;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

@p(a = {@o(a = SeekBar.class, b = "android:progress")})
public class y {

    public interface a {
        void a(SeekBar seekBar, int i, boolean z);
    }

    public interface b {
        void a(SeekBar seekBar);
    }

    public interface c {
        void a(SeekBar seekBar);
    }

    @android.databinding.c(a = {"android:progress"})
    public static void a(SeekBar seekBar, int i) {
        if (i != seekBar.getProgress()) {
            seekBar.setProgress(i);
        }
    }

    @android.databinding.c(a = {"android:onStartTrackingTouch", "android:onStopTrackingTouch", "android:onProgressChanged", "android:progressAttrChanged"}, b = false)
    public static void a(SeekBar seekBar, final b bVar, final c cVar, final a aVar, final n nVar) {
        if (bVar == null && cVar == null && aVar == null && nVar == null) {
            seekBar.setOnSeekBarChangeListener(null);
        } else {
            seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    if (aVar != null) {
                        aVar.a(seekBar, i, z);
                    }
                    if (nVar != null) {
                        nVar.a();
                    }
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    if (bVar != null) {
                        bVar.a(seekBar);
                    }
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    if (cVar != null) {
                        cVar.a(seekBar);
                    }
                }
            });
        }
    }
}
