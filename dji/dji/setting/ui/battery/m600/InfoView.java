package dji.setting.ui.battery.m600;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.d;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;

public class InfoView extends DividerLinearLayout implements d {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private boolean e = true;

    public InfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, getLayout());
        if (!isInEditMode()) {
            this.a = (TextView) findViewById(R.id.setting_ui_battery_current_volume);
            this.b = (TextView) findViewById(R.id.setting_ui_battery_full_volume);
            this.d = (TextView) findViewById(R.id.setting_ui_battery_voltage);
            this.c = (TextView) findViewById(R.id.setting_ui_battery_temperature);
        }
    }

    protected int getLayout() {
        return R.layout.setting_ui_battery_info_m600;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        dji.sdksharedlib.a.a.e(this, new String[]{dji.sdksharedlib.b.a.c, dji.sdksharedlib.b.a.b, dji.sdksharedlib.b.a.d, dji.sdksharedlib.b.a.t});
        b();
        this.e = false;
    }

    protected void onDetachedFromWindow() {
        dji.sdksharedlib.a.a.a(this);
        super.onDetachedFromWindow();
    }

    private void b() {
        int a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.a.c, true));
        int a2 = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.a.b, true));
        int a3 = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.a.d, true));
        int a4 = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.a.t, true));
        if (this.e) {
            this.a.setText(R.string.setting_ui_na);
            this.b.setText(R.string.setting_ui_na);
            this.d.setText(R.string.setting_ui_na);
            this.c.setText(R.string.setting_ui_na);
            return;
        }
        this.a.setText("" + a + getResources().getString(R.string.setting_ui_battery_volume_unit));
        this.b.setText("" + a2 + getResources().getString(R.string.setting_ui_battery_volume_unit));
        this.d.setText(getResources().getString(R.string.setting_ui_battery_voltage_unit, new Object[]{Float.valueOf(((float) a3) / 1000.0f)}) + " v");
        this.c.setText("" + a4 + " ℃");
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        b();
    }
}
