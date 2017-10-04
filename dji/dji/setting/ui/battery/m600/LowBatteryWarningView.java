package dji.setting.ui.battery.m600;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataFlycGetVoltageWarnning.WarnningLevel;
import dji.midware.data.model.P3.DataFlycSetLVoltageWarnning;
import dji.midware.e.d;
import dji.pilot.battery.a.a;
import dji.pilot.battery.a.a$c;
import dji.setting.ui.widget.ItemViewSeekbar;
import dji.thirdparty.a.c;

public class LowBatteryWarningView extends ItemViewSeekbar {
    private a$c a = new a$c(this) {
        final /* synthetic */ LowBatteryWarningView a;

        {
            this.a = r1;
        }

        public void a(boolean z) {
        }

        public void b(int i, boolean z) {
        }

        public void a(int i, boolean z) {
        }

        public void a(int i, int i2, boolean z) {
            this.a.runOnUiThread(new Runnable(this) {
                final /* synthetic */ AnonymousClass1 a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a.a();
                }
            });
        }

        public void a(int i) {
        }
    };

    public LowBatteryWarningView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d.initParams(35, "15%", "50%", 15, (OnSeekBarChangeListener) this);
    }

    private void a() {
        this.d.getSeekbaBar().setEnabled(true);
        this.d.setProgress(DataFlycGetPushSmartBattery.getInstance().getLowWarning() - 15);
    }

    public void onEventMainThread(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
        a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a.getInstance().a(this.a);
        c.a().a(this);
        a();
    }

    protected void onDetachedFromWindow() {
        a.getInstance().b(this.a);
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        int progress = this.d.getProgress() + 15;
        if (a.getInstance().g() != progress) {
            DataFlycSetLVoltageWarnning instance;
            if (a.getInstance().i() > progress) {
                a.getInstance().b(progress - 5, true);
            }
            a.getInstance().a(progress, true);
            if (a.getInstance().g() < progress) {
                instance = DataFlycSetLVoltageWarnning.getInstance();
                instance.a(WarnningLevel.Second);
                instance.a(progress - 5);
                instance.a(true);
                instance.start(null);
            }
            instance = DataFlycSetLVoltageWarnning.getInstance();
            instance.a(WarnningLevel.First);
            instance.a(progress);
            instance.a(true);
            instance.start(new d(this) {
                final /* synthetic */ LowBatteryWarningView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a();
                        }
                    });
                }
            });
        }
    }
}
