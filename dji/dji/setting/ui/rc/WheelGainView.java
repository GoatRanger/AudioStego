package dji.setting.ui.rc;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataRcGetMaster;
import dji.midware.data.model.P3.DataRcGetWheelGain;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.data.model.P3.DataRcSetWheelGain;
import dji.midware.e.d;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewSeekbar;

public class WheelGainView extends ItemViewSeekbar {
    private int a;

    public WheelGainView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.d.initParams(10, R.string.setting_ui_rc_slow, R.string.setting_ui_rc_fast, this);
            a();
        }
    }

    private void a() {
        DataRcGetWheelGain.getInstance().start(new d(this) {
            final /* synthetic */ WheelGainView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.b();
                    }
                });
            }

            public void onFailure(a aVar) {
                DJILogHelper.getInstance().LOGD("View", "testrc get pitch " + aVar, false, true);
            }
        });
        DataRcGetMaster.getInstance().start(new d(this) {
            final /* synthetic */ WheelGainView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.b();
                    }
                });
            }

            public void onFailure(a aVar) {
            }
        });
    }

    private void setWheel(final int i) {
        if (this.a != i) {
            e.a("FPV_RCSettings_MasterRCControlSettings_GimbalWheelSpeed_Slider_SlowFast");
            DJILogHelper.getInstance().LOGD("View", "setWheel progress=" + i, false, true);
            DataRcSetWheelGain.getInstance().a(i).start(new d(this) {
                final /* synthetic */ WheelGainView b;

                public void onSuccess(Object obj) {
                    this.b.a = i;
                }

                public void onFailure(a aVar) {
                    this.b.b();
                }
            });
        }
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        setWheel(seekBar.getProgress() * 10);
    }

    private void b() {
        if (DataRcGetMaster.getInstance().getMode() == MODE.b) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        this.a = DataRcGetWheelGain.getInstance().getGain();
        DJILogHelper.getInstance().LOGD("View", "testrc wheelvalue=" + this.a, false, true);
        this.d.setProgress(this.a / 10);
    }
}
