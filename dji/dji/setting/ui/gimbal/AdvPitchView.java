package dji.setting.ui.gimbal;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.e;
import dji.midware.data.model.P3.DataGimbalSetUserParams;
import dji.midware.e.d;
import dji.setting.ui.widget.ItemViewSeekbar;
import dji.thirdparty.a.c;

public class AdvPitchView extends ItemViewSeekbar {
    private final String a = "pitch_expo";

    public AdvPitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a();
            a.a();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(a aVar) {
        a();
    }

    private void a() {
        int intValue = e.read("pitch_expo").value.intValue();
        this.d.initParams(100, "0", "100", 0, (OnSeekBarChangeListener) this);
        this.d.setProgress(intValue);
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        int intValue = e.read("pitch_expo").value.intValue();
        int progress = seekBar.getProgress();
        if (progress != intValue) {
            DataGimbalSetUserParams.getInstance().a("pitch_expo", Integer.valueOf(progress)).start(new d(this) {
                final /* synthetic */ AdvPitchView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    a.c();
                    a.a();
                }

                public void onFailure(a aVar) {
                    a.c();
                    a.a();
                }
            });
        }
    }
}
