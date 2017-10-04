package dji.setting.ui.battery.m600;

import android.content.Context;
import android.util.AttributeSet;
import dji.common.error.DJIError;
import dji.pilot.battery.a.a;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.c.h;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.ItemViewSpinner;

public class DischargeView extends ItemViewSpinner implements d {
    private boolean a = false;

    public DischargeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a = true;
        this.f.setData(a.getInstance().a(getContext(), R.string.setting_ui_battery_discharge_day), 0, (b) this);
        if (!isInEditMode()) {
            dji.sdksharedlib.a.a.d(this, new String[]{dji.sdksharedlib.b.a.n});
            a();
        }
    }

    protected void onDetachedFromWindow() {
        this.a = false;
        dji.sdksharedlib.a.a.a(this);
        super.onDetachedFromWindow();
    }

    public void onItemClick(int i) {
        if (i + 1 != dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.d(dji.sdksharedlib.b.a.n))) {
            dji.sdksharedlib.a.a.b(dji.sdksharedlib.b.a.n, Integer.valueOf(i + 1), new h(this) {
                final /* synthetic */ DischargeView a;

                {
                    this.a = r1;
                }

                public void a() {
                    e.a("FPV_AircraftBattery_PullDownView_TimeToDischarge");
                }

                public void a(DJIError dJIError) {
                    this.a.a();
                }
            });
        }
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        a();
    }

    private void a() {
        int i = 1;
        int a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.d(dji.sdksharedlib.b.a.n));
        if (a >= 1) {
            i = a;
        }
        this.f.setIndex(i - 1);
    }
}
