package dji.setting.ui.battery.nonsmart;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.d;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.pilot.setting.ui.R;
import dji.setting.ui.rc.RcMasterSlaveView;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.ItemViewSpinner;
import dji.thirdparty.a.c;

public class SeriousLowVoltageAction extends ItemViewSpinner {
    public static final String a = "g_config.voltage.level_2_protect_type_0";
    private int b = 0;
    private boolean c = false;

    public SeriousLowVoltageAction(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.c = true;
            String[] strArr = new String[]{getContext().getString(R.string.setting_ui_battery_non_smart_low_voltage_action_landing), getContext().getString(R.string.setting_ui_battery_non_smart_low_voltage_action_led_warning)};
            this.b = d.read(a).value.intValue();
            this.f.setData(strArr, a(this.b), (b) this);
            a();
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        this.c = false;
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    private int a(int i) {
        switch (i) {
            case 0:
                return 1;
            case 2:
                return 0;
            default:
                return i;
        }
    }

    private int c(int i) {
        switch (i) {
            case 0:
                return 2;
            case 1:
                return 0;
            default:
                return i;
        }
    }

    public void onItemClick(final int i) {
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a(new String[]{a});
        dataFlycSetParams.a(new Number[]{Integer.valueOf(c(i))});
        dataFlycSetParams.start(new dji.midware.e.d(this) {
            final /* synthetic */ SeriousLowVoltageAction b;

            public void onSuccess(Object obj) {
                this.b.b = this.b.c(i);
            }

            public void onFailure(a aVar) {
                this.b.f.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.f.setIndex(this.a.b.a(this.a.b.b));
                    }
                });
            }
        });
    }

    private void a() {
        if (this.c) {
            new DataFlycGetParams().setInfos(new String[]{a}).start(new dji.midware.e.d(this) {
                final /* synthetic */ SeriousLowVoltageAction a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.f.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.b = d.read(SeriousLowVoltageAction.a).value.intValue();
                            this.a.a.f.setIndex(this.a.a.a(this.a.a.b));
                        }
                    });
                }

                public void onFailure(a aVar) {
                    this.a.a();
                }
            });
        }
    }

    public void onEventMainThread(RcMasterSlaveView.c cVar) {
        b();
    }

    private void b() {
        this.f.setEnabled(dji.pilot.c.d.b != MODE.b);
    }
}
