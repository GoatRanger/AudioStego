package dji.setting.ui.battery;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import dji.common.battery.DJIBatteryStatus;
import dji.common.product.Model;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.a.b;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.h;
import dji.sdksharedlib.c.d;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;

public class InfoView extends DividerLinearLayout implements d {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private c f;

    public InfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, getLayout());
        if (!isInEditMode()) {
            this.a = (TextView) findViewById(R.id.setting_ui_battery_current_volume);
            this.b = (TextView) findViewById(R.id.setting_ui_battery_full_volume);
            this.c = (TextView) findViewById(R.id.setting_ui_battery_current_volume_unit);
            this.d = (TextView) findViewById(R.id.setting_ui_battery_full_volume_unit);
            this.e = (TextView) findViewById(R.id.setting_ui_battery_charge_times);
        }
    }

    protected int getLayout() {
        return R.layout.setting_ui_battery_info;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            dji.sdksharedlib.a.a.a(this, h.c);
            dji.sdksharedlib.a.a.d(this, new String[]{dji.sdksharedlib.b.a.c, dji.sdksharedlib.b.a.b, dji.sdksharedlib.b.a.f, dji.sdksharedlib.b.a.m});
            if (dji.pilot.battery.a.a.getInstance().D() - 1 < 0) {
                this.f = b.d(dji.sdksharedlib.b.a.i);
            } else {
                this.f = b.a(0, dji.sdksharedlib.b.a.i);
            }
            DJISDKCache.getInstance().startListeningForUpdates(this.f, this, true);
            b();
        }
    }

    protected void onDetachedFromWindow() {
        dji.sdksharedlib.a.a.a(this);
        super.onDetachedFromWindow();
    }

    private void b() {
        int a;
        int a2 = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.d(dji.sdksharedlib.b.a.c));
        int a3 = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.d(dji.sdksharedlib.b.a.b));
        dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.d(dji.sdksharedlib.b.a.f));
        if (DJISDKCache.getInstance().getAvailableValue(this.f) != null) {
            a = dji.sdksharedlib.a.a.a(DJISDKCache.getInstance().getAvailableValue(this.f).e());
        } else {
            a = 0;
        }
        DJIBatteryStatus dJIBatteryStatus = (DJIBatteryStatus) dji.sdksharedlib.a.a.d(dji.sdksharedlib.b.a.m);
        if (((Model) dji.sdksharedlib.a.a.a(h.c)) == Model.MavicPro) {
            dJIBatteryStatus = DJIBatteryStatus.NORMAL;
        }
        if (dJIBatteryStatus == null || dJIBatteryStatus == DJIBatteryStatus.EXCEPTION) {
            this.a.setText(R.string.setting_ui_na);
            this.b.setText(R.string.setting_ui_na);
            this.e.setText(R.string.setting_ui_na);
            this.c.setVisibility(8);
            this.d.setVisibility(8);
            return;
        }
        this.a.setText("" + a2);
        this.b.setText("" + a3);
        this.e.setText("" + a);
        this.c.setVisibility(0);
        this.d.setVisibility(0);
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        b();
    }
}
