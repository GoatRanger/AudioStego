package dji.setting.ui.battery;

import android.content.Context;
import android.util.AttributeSet;
import dji.common.error.DJIError;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCenterGetSelfDischarge;
import dji.midware.data.model.P3.DataCenterSelfDischarge;
import dji.midware.data.model.P3.DataSmartBatteryGetSetSelfDischargeDays;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.a.a;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.c.h;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.ItemViewSpinner;

public class DischargeView extends ItemViewSpinner implements d {
    private DataCenterSelfDischarge a = new DataCenterSelfDischarge();
    private DataCenterGetSelfDischarge b = new DataCenterGetSelfDischarge();
    private DataSmartBatteryGetSetSelfDischargeDays c = new DataSmartBatteryGetSetSelfDischargeDays();

    public DischargeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a.d(this, new String[]{dji.sdksharedlib.b.a.n});
            b();
            a();
        }
    }

    protected void onDetachedFromWindow() {
        a.a(this);
        super.onDetachedFromWindow();
    }

    private void a() {
        i.getInstance().c();
    }

    private void b() {
        int a = a.a(a.d(dji.sdksharedlib.b.a.n));
        if (a < 1) {
            a = 1;
        }
        String[] strArr = new String[10];
        for (int i = 0; i < 10; i++) {
            strArr[i] = getContext().getResources().getString(R.string.setting_ui_battery_discharge_day, new Object[]{Integer.valueOf(i + 1)});
        }
        if (strArr[0].contains("Days")) {
            strArr[0] = strArr[0].replace("Days", "Day");
        }
        this.f.setData(strArr, a - 1, (b) this);
    }

    public void onItemClick(int i) {
        if (i + 1 != a.a(a.d(dji.sdksharedlib.b.a.n))) {
            if (dji.pilot.publics.e.a.b()) {
                new DataSmartBatteryGetSetSelfDischargeDays().setDays(i + 1).setType(true).setIndex(0).start(new dji.midware.e.d(this) {
                    final /* synthetic */ DischargeView a;

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
                                this.a.a.b();
                            }
                        });
                    }
                });
            } else {
                a.b(dji.sdksharedlib.b.a.n, Integer.valueOf(i + 1), new h(this) {
                    final /* synthetic */ DischargeView a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                    }

                    public void a(DJIError dJIError) {
                        this.a.b();
                    }
                });
            }
        }
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        b();
    }
}
