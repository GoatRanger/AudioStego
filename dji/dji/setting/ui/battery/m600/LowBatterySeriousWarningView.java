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
import dji.setting.ui.widget.ItemViewSeekbar;
import dji.thirdparty.a.c;

public class LowBatterySeriousWarningView extends ItemViewSeekbar {
    public LowBatterySeriousWarningView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void a() {
        this.d.initParams(25, "10%", "35%", 10, (OnSeekBarChangeListener) this);
        this.d.getSeekbaBar().setEnabled(true);
        this.d.setProgress(DataFlycGetPushSmartBattery.getInstance().getSeriousLowWarning() - 10);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        a();
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    public void onEventMainThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        a();
    }

    public void onEventMainThread(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
        a();
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        int progress = this.d.getProgress() + 10;
        if (DataFlycGetPushSmartBattery.getInstance().getSeriousLowWarning() != progress) {
            DataFlycSetLVoltageWarnning instance;
            if (a.getInstance().g() < progress) {
                instance = DataFlycSetLVoltageWarnning.getInstance();
                instance.a(WarnningLevel.First);
                instance.a(progress + 5);
                instance.a(true);
                instance.start(null);
            }
            instance = DataFlycSetLVoltageWarnning.getInstance();
            instance.a(WarnningLevel.Second);
            instance.a(progress);
            instance.a(true);
            instance.start(new d(this) {
                final /* synthetic */ LowBatterySeriousWarningView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
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
            });
        }
    }
}
