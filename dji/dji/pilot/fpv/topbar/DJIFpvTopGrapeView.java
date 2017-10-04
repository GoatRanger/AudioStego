package dji.pilot.fpv.topbar;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.model.P3.DataOsdGetPushCommon.BatteryType;
import dji.pilot.R;
import dji.pilot.fpv.d.b;

public class DJIFpvTopGrapeView extends DJIFpvTopBaseView {
    public DJIFpvTopGrapeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void changeGrapLayout() {
        this.ap.go();
        this.aq.go();
        this.ab.setEnabled(false);
        this.ac.setEnabled(false);
        this.M.setEnabled(false);
        this.cH = true;
    }

    protected void b(int i) {
        if (!b.f() || BatteryType.NonSmart != this.bw) {
            super.b(i);
        }
    }

    protected void x() {
        if (b.f() && BatteryType.NonSmart == this.bw) {
            int voltage = this.ce.getVoltage();
            if (this.bn != voltage) {
                this.bn = voltage;
                float f = (((float) voltage) * 1.0f) / 1000.0f;
                this.ad.setText(this.aL.getString(R.string.battery_voltage_unit, new Object[]{Float.valueOf(f)}));
                this.ad.setTextColor(this.aL.getResources().getColor(R.color.om));
                return;
            }
            return;
        }
        super.x();
    }
}
