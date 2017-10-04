package dji.setting.ui.battery.m600;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import dji.common.battery.DJIBatteryOverview;
import dji.midware.data.model.P3.DataSmartBatteryGetPushDynamicData;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.d;
import dji.setting.ui.widget.DividerLinearLayout;
import java.lang.ref.WeakReference;

public class SubInfoView extends DividerLinearLayout implements d {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private DataSmartBatteryGetPushDynamicData e = null;
    private a f = null;

    private class a extends Handler {
        public static final int a = 1;
        final /* synthetic */ SubInfoView b;
        private WeakReference<SubInfoView> c = null;

        public a(SubInfoView subInfoView, SubInfoView subInfoView2) {
            this.b = subInfoView;
            this.c = new WeakReference(subInfoView2);
        }

        public void handleMessage(Message message) {
            SubInfoView subInfoView = (SubInfoView) this.c.get();
            if (subInfoView != null) {
                switch (message.what) {
                    case 1:
                        subInfoView.b();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public SubInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_battery_info_m600);
        if (!isInEditMode()) {
            this.a = (TextView) findViewById(R.id.setting_ui_battery_current_volume);
            this.b = (TextView) findViewById(R.id.setting_ui_battery_full_volume);
            this.d = (TextView) findViewById(R.id.setting_ui_battery_voltage);
            this.c = (TextView) findViewById(R.id.setting_ui_battery_temperature);
            this.f = new a(this, this);
            this.f.sendEmptyMessage(1);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        dji.sdksharedlib.a.a.a(this, dji.pilot.battery.a.a.getInstance().D() - 1, new String[]{dji.sdksharedlib.b.a.c, dji.sdksharedlib.b.a.b, dji.sdksharedlib.b.a.d, dji.sdksharedlib.b.a.h});
        dji.sdksharedlib.a.a.e(this, new String[]{dji.sdksharedlib.b.a.s});
        b();
    }

    protected void onDetachedFromWindow() {
        dji.sdksharedlib.a.a.a(this);
        super.onDetachedFromWindow();
    }

    private void b() {
        boolean z;
        int D = dji.pilot.battery.a.a.getInstance().D() - 1;
        DJIBatteryOverview[] dJIBatteryOverviewArr = (DJIBatteryOverview[]) dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.a.s, true);
        if (dJIBatteryOverviewArr == null || dJIBatteryOverviewArr.length <= D) {
            z = false;
        } else {
            z = dJIBatteryOverviewArr[D].isConnected();
        }
        int a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.a.c, D));
        int a2 = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.a.b, D));
        int a3 = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.a.d, D));
        D = (int) dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.a.h, D));
        if (z) {
            this.a.setText("" + a + getResources().getString(R.string.setting_ui_battery_volume_unit));
            this.b.setText("" + a2 + getResources().getString(R.string.setting_ui_battery_volume_unit));
            this.d.setText(getResources().getString(R.string.setting_ui_battery_voltage_unit, new Object[]{Float.valueOf(((float) a3) / 1000.0f)}) + " v");
            this.c.setText("" + D + " ℃");
            return;
        }
        this.a.setText(R.string.setting_ui_na);
        this.b.setText(R.string.setting_ui_na);
        this.d.setText(R.string.setting_ui_na);
        this.c.setText(R.string.setting_ui_na);
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        this.f.sendEmptyMessage(1);
    }
}
