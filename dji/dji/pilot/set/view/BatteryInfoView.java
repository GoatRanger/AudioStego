package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.pilot.set.R;
import dji.thirdparty.a.c;

public class BatteryInfoView extends LinearLayout {
    static final float a = 273.15f;
    TextView b;
    TextView c;
    TextView d;
    TextView e;
    TextView f;
    final Context g;
    int h;

    public BatteryInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = context;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.h = 0;
        this.b = (TextView) findViewById(R.id.version_battery_current_voltage);
        this.c = (TextView) findViewById(R.id.version_battery_produce_date);
        this.d = (TextView) findViewById(R.id.version_battery_charge_times);
        this.e = (TextView) findViewById(R.id.battery_cell_voltage_tv);
        this.f = (TextView) findViewById(R.id.battery_serial_number_tv);
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    private final String a(Object... objArr) {
        return this.g.getString(R.string.battery_voltage_unit, objArr);
    }

    public void onEventMainThread(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
        this.b.setText(a(Float.valueOf(((float) dataCenterGetPushBatteryCommon.getCurrentPV()) / 1000.0f)));
        int[] productDate = dataCenterGetPushBatteryCommon.getProductDate();
        int i = productDate[0];
        int i2 = productDate[1];
        this.c.setText("" + i + "." + i2 + "." + productDate[2]);
        productDate = dataCenterGetPushBatteryCommon.getPartVoltages();
        float f = ((float) productDate[0]) / 1000.0f;
        float f2 = ((float) productDate[0]) / 1000.0f;
        float f3 = ((float) productDate[0]) / 1000.0f;
        this.e.setText("1:" + a(Float.valueOf(f)) + ", 2:" + a(Float.valueOf(f2)) + ", 3:" + a(Float.valueOf(f3)));
        this.f.setText("" + dataCenterGetPushBatteryCommon.getSerialNo());
        this.d.setText("" + dataCenterGetPushBatteryCommon.getLoopNum());
    }
}
