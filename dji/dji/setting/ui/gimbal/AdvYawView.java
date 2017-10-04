package dji.setting.ui.gimbal;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import dji.midware.data.manager.P3.e;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataGimbalSetUserParams;
import dji.midware.e.d;
import dji.pilot.publics.e.a;
import dji.setting.ui.widget.ItemViewSeekbar;
import dji.thirdparty.a.c;

public class AdvYawView extends ItemViewSeekbar {
    private final String a = "yaw_expo";

    public AdvYawView(Context context, AttributeSet attributeSet) {
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
        if (a.a() || DataCameraGetPushStateInfo.getInstance().getCameraType() == CameraType.DJICameraTypeGD600) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        int intValue = e.read("yaw_expo").value.intValue();
        this.d.initParams(100, "0", "100", 0, (OnSeekBarChangeListener) this);
        this.d.setProgress(intValue);
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        int intValue = e.read("yaw_expo").value.intValue();
        int progress = seekBar.getProgress();
        if (progress != intValue) {
            DataGimbalSetUserParams.getInstance().a("yaw_expo", Integer.valueOf(progress)).start(new d(this) {
                final /* synthetic */ AdvYawView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    a.c();
                    a.a();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    a.c();
                    a.a();
                }
            });
        }
    }
}
