package dji.setting.ui.rc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataRcGetGimbalSpeed;
import dji.midware.data.model.P3.DataRcSetGimbalSpeed;
import dji.midware.e.d;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.DJINumberProgress;
import dji.setting.ui.widget.DividerLinearLayout;

public class GimbalSpeedView extends DividerLinearLayout {
    private static final String a = "GimbalSpeedView";
    private DJINumberProgress b;
    private DJINumberProgress c;
    private DJINumberProgress d;
    private int e;
    private int f;
    private int g;
    private OnSeekBarChangeListener h = new OnSeekBarChangeListener(this) {
        final /* synthetic */ GimbalSpeedView a;

        {
            this.a = r1;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            e.a("FPV_RCSettings_SlaveRCControlSettings_StickMode_CustomChannels_GimbalSpeedSetting_Slider_Pitch");
            this.a.setPitch(seekBar.getProgress() * 10);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        }
    };
    private OnSeekBarChangeListener i = new OnSeekBarChangeListener(this) {
        final /* synthetic */ GimbalSpeedView a;

        {
            this.a = r1;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            e.a("FPV_RCSettings_SlaveRCControlSettings_StickMode_ CustomChannels_GimbalSpeedSetting_Slider_Roll");
            this.a.setRoll(seekBar.getProgress() * 10);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        }
    };
    private OnSeekBarChangeListener l = new OnSeekBarChangeListener(this) {
        final /* synthetic */ GimbalSpeedView a;

        {
            this.a = r1;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            e.a("FPV_RCSettings_SlaveRCControlSettings_StickMode_ CustomChannels_GimbalSpeedSetting_Slider_Yaw");
            this.a.setYaw(seekBar.getProgress() * 10);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        }
    };
    private d m = new d(this) {
        final /* synthetic */ GimbalSpeedView a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
            DJILogHelper.getInstance().LOGD(GimbalSpeedView.a, "testrc success=", false, true);
            this.a.b();
        }

        public void onFailure(a aVar) {
            DJILogHelper.getInstance().LOGD(GimbalSpeedView.a, "testrc false=", false, true);
            this.a.runOnUiThread(new Runnable(this) {
                final /* synthetic */ AnonymousClass5 a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.c();
                }
            });
        }
    };

    public GimbalSpeedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_rc_gimbal_speed);
        if (!isInEditMode()) {
            this.b = (DJINumberProgress) findViewById(R.id.setting_ui_rc_gimbal_pitch);
            this.c = (DJINumberProgress) findViewById(R.id.setting_ui_rc_gimbal_roll);
            this.d = (DJINumberProgress) findViewById(R.id.setting_ui_rc_gimbal_yaw);
            this.b.initParams(10, R.string.setting_ui_rc_slow, R.string.setting_ui_rc_fast, this.h);
            this.c.initParams(10, R.string.setting_ui_rc_slow, R.string.setting_ui_rc_fast, this.i);
            this.d.initParams(10, R.string.setting_ui_rc_slow, R.string.setting_ui_rc_fast, this.l);
        }
    }

    private void b() {
        DataRcGetGimbalSpeed.getInstance().start(new d(this) {
            final /* synthetic */ GimbalSpeedView a;

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
                        this.a.a.c();
                    }
                });
            }

            public void onFailure(a aVar) {
                DJILogHelper.getInstance().LOGD("View", "testrc get pitch " + aVar, false, true);
            }
        });
    }

    private void c() {
        this.e = DataRcGetGimbalSpeed.getInstance().getPitch();
        this.f = DataRcGetGimbalSpeed.getInstance().getRoll();
        this.g = DataRcGetGimbalSpeed.getInstance().getYaw();
        DJILogHelper.getInstance().LOGD("View", "testrc pitch=" + this.e, false, true);
        DJILogHelper.getInstance().LOGD("View", "testrc roll=" + this.f, false, true);
        DJILogHelper.getInstance().LOGD("View", "testrc yaw=" + this.g, false, true);
        this.b.setProgress(Math.round((float) (this.e / 10)));
        this.d.setProgress(Math.round((float) (this.g / 10)));
        this.c.setProgress(Math.round((float) (this.f / 10)));
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void setPitch(int i) {
        DJILogHelper.getInstance().LOGD(a, "setPitch", false, true);
        DataRcSetGimbalSpeed.getInstance().a(i).b(this.f).c(this.g).start(this.m);
    }

    private void setYaw(int i) {
        DataRcSetGimbalSpeed.getInstance().a(this.e).b(this.f).c(i).start(this.m);
    }

    private void setRoll(int i) {
        DataRcSetGimbalSpeed.getInstance().a(this.e).b(i).c(this.g).start(this.m);
    }
}
