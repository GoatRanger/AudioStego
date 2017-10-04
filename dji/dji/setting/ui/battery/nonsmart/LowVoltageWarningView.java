package dji.setting.ui.battery.nonsmart;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import dji.midware.data.model.P3.DataFlycGetParamInfoByHash;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.e.d;
import dji.pilot.battery.a.a;
import dji.pilot.battery.a.a$b;
import dji.setting.ui.rc.RcMasterSlaveView;
import dji.setting.ui.widget.ItemViewSeekbar;
import dji.thirdparty.a.c;

public class LowVoltageWarningView extends ItemViewSeekbar {
    public static final String[] a = new String[]{"g_config.voltage.level_1_protect_0", "g_config.voltage.battery_cell_0"};
    private boolean e = false;
    private int f = 0;
    private int g = 0;
    private int h = 0;

    public LowVoltageWarningView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        final int progress = (seekBar.getProgress() / a.getInstance().E()) + this.g;
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a(new String[]{a[0]});
        dataFlycSetParams.a(new Number[]{Integer.valueOf(progress)});
        dataFlycSetParams.start(new d(this) {
            final /* synthetic */ LowVoltageWarningView b;

            public void onSuccess(Object obj) {
                this.b.h = progress;
                dji.pilot.battery.a.d.getInstance().a();
                this.b.c();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.d.setOnSeekBarChangeListener(null);
                this.b.d.setProgress((this.b.h - this.b.g) * a.getInstance().E());
                this.b.d.setOnSeekBarChangeListener(this.b);
            }
        });
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.e = true;
        if (!isInEditMode()) {
            a();
            b();
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        this.e = false;
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        a();
    }

    public void onEventMainThread(RcMasterSlaveView.c cVar) {
        a();
    }

    public void onEventMainThread(a$b dji_pilot_battery_a_a_b) {
        if (dji_pilot_battery_a_a_b == a$b.CellChanged && this.f != 0) {
            int E = a.getInstance().E();
            this.d.initParams((this.f * E) - (this.g * E), String.format("%.1fV", new Object[]{Float.valueOf((((float) this.g) * 0.001f) * ((float) E))}), String.format("%.1fV", new Object[]{Float.valueOf((((float) this.f) * 0.001f) * ((float) E))}), this.g * E, (OnSeekBarChangeListener) this);
            this.d.setDiv(1000);
            this.d.setOnSeekBarChangeListener(null);
            this.d.setProgress(99);
            this.d.setProgress((this.h - this.g) * E);
            this.d.setOnSeekBarChangeListener(this);
            dji.pilot.battery.a.d.getInstance().a();
        }
    }

    private void a() {
        boolean z = false;
        boolean z2 = (dji.pilot.c.d.b == MODE.b || DataOsdGetPushCommon.getInstance().isMotorUp()) ? false : true;
        if (this.f != 0) {
            z = z2;
        }
        this.d.setEnabled(z);
    }

    private void b() {
        final DataFlycGetParamInfoByHash dataFlycGetParamInfoByHash = new DataFlycGetParamInfoByHash();
        dataFlycGetParamInfoByHash.setIndex(a[0]).start(new d(this) {
            final /* synthetic */ LowVoltageWarningView b;

            public void onSuccess(Object obj) {
                ParamInfo paramInfo = dataFlycGetParamInfoByHash.getParamInfo();
                this.b.f = paramInfo.range.b.intValue();
                this.b.g = paramInfo.range.a.intValue();
                this.b.c();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (this.b.e) {
                    this.b.b();
                }
            }
        });
    }

    private void c() {
        new DataFlycGetParams().setInfos(a).start(new d(this) {
            final /* synthetic */ LowVoltageWarningView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.d.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        int intValue = dji.midware.data.manager.P3.d.read(LowVoltageWarningView.a[1]).value.intValue();
                        this.a.a.h = dji.midware.data.manager.P3.d.read(LowVoltageWarningView.a[0]).value.intValue();
                        this.a.a.d.initParams((this.a.a.f - this.a.a.g) * intValue, String.format("%.1fV", new Object[]{Float.valueOf((((float) this.a.a.g) * 0.001f) * ((float) intValue))}), String.format("%.1fV", new Object[]{Float.valueOf((((float) this.a.a.f) * 0.001f) * ((float) intValue))}), this.a.a.g * intValue, this.a.a);
                        this.a.a.d.setDiv(1000);
                        this.a.a.d.setOnSeekBarChangeListener(null);
                        this.a.a.d.setProgress((this.a.a.h - this.a.a.g) * intValue);
                        this.a.a.d.setOnSeekBarChangeListener(this.a.a);
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (this.a.e) {
                    this.a.c();
                }
            }
        });
    }
}
