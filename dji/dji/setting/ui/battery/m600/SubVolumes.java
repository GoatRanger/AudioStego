package dji.setting.ui.battery.m600;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.common.battery.DJIBatteryCell;
import dji.common.battery.DJIBatteryOverview;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.d;
import dji.setting.ui.widget.DJIVerticalProgressBar;
import dji.setting.ui.widget.DividerLinearLayout;

public class SubVolumes extends DividerLinearLayout implements d {
    private static final float a = 3.62f;
    private static final float b = 3.5f;
    private static final float[] c = new float[]{3.0f, 4.35f};
    private static final int[] d = new int[]{R.id.battery_sub1_ly, R.id.battery_sub2_ly, R.id.battery_sub3_ly, R.id.battery_sub4_ly, R.id.battery_sub5_ly, R.id.battery_sub6_ly};
    private final Drawable[] e = new Drawable[6];
    private final Drawable[] f = new Drawable[6];
    private final Drawable[] g = new Drawable[6];
    private final Drawable[] h = new Drawable[6];
    private final a[] i = new a[6];
    private TextView l;
    private TextView m;

    private static final class a {
        private RelativeLayout a;
        private DJIVerticalProgressBar b;
        private ImageView c;
        private TextView d;

        private a() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
        }
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        a();
    }

    private void a() {
        c();
        d();
    }

    public SubVolumes(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        dji.sdksharedlib.a.a.a(this, dji.pilot.battery.a.a.getInstance().D() - 1, new String[]{dji.sdksharedlib.b.a.p, dji.sdksharedlib.b.a.f, dji.sdksharedlib.b.a.i});
        dji.sdksharedlib.a.a.e(this, new String[]{dji.sdksharedlib.b.a.s});
        a();
    }

    protected void onDetachedFromWindow() {
        dji.sdksharedlib.a.a.a(this);
        super.onDetachedFromWindow();
    }

    private void b() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_battery_subs_volume_m600);
        if (!isInEditMode()) {
            int length = this.i.length;
            for (int i = 0; i < length; i++) {
                a aVar = new a();
                aVar.a = (RelativeLayout) findViewById(d[i]);
                aVar.b = (DJIVerticalProgressBar) aVar.a.findViewById(R.id.battery_part_pgb);
                aVar.c = (ImageView) aVar.a.findViewById(R.id.battery_part_icon);
                aVar.d = (TextView) aVar.a.findViewById(R.id.battery_part_tv);
                this.i[i] = aVar;
                this.e[i] = getContext().getResources().getDrawable(R.drawable.setting_ui_battery_volume_low_pgb);
                this.f[i] = getContext().getResources().getDrawable(R.drawable.setting_ui_battery_volume_disable_pgb);
                this.g[i] = getContext().getResources().getDrawable(R.drawable.setting_ui_battery_volume_normal_pgb);
                this.h[i] = getContext().getResources().getDrawable(R.drawable.setting_ui_battery_volume_yellow_pgb);
            }
            this.l = (TextView) findViewById(R.id.setting_ui_battery_life);
            this.m = (TextView) findViewById(R.id.setting_ui_battery_charge_times);
        }
    }

    private int a(float f, int i) {
        if (f >= c[1]) {
            return 100;
        }
        if (f > c[0]) {
            return (int) (((f - c[0]) * ((float) i)) / (c[1] - c[0]));
        }
        return 0;
    }

    private void c() {
        int[] iArr;
        int i;
        DJIBatteryCell[] dJIBatteryCellArr = (DJIBatteryCell[]) dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.a.p, dji.pilot.battery.a.a.getInstance().D() - 1);
        if (dJIBatteryCellArr != null) {
            iArr = new int[dJIBatteryCellArr.length];
            for (i = 0; i < dJIBatteryCellArr.length; i++) {
                iArr[i] = dJIBatteryCellArr[i].getVoltage();
            }
        } else {
            iArr = null;
        }
        int a;
        if (iArr != null) {
            int length = this.i.length;
            i = 0;
            while (i < length && i < iArr.length) {
                a aVar = this.i[i];
                float f = ((float) iArr[i]) / 1000.0f;
                aVar.d.setText(getResources().getString(R.string.setting_ui_battery_voltage_unit, new Object[]{Float.valueOf(f)}));
                aVar.c.setVisibility(8);
                Drawable drawable = this.g[i];
                if (f < b) {
                    drawable = this.e[i];
                } else if (f < a) {
                    drawable = this.h[i];
                }
                if (aVar.b.getProgressDrawable() != drawable) {
                    aVar.b.setProgressDrawable(drawable);
                }
                a = a(f, 100);
                if (aVar.b.getProgress() != a) {
                    aVar.b.setProgress(a);
                }
                i++;
            }
            return;
        }
        for (a aVar2 : this.i) {
            aVar2.d.setText(R.string.setting_ui_na);
            aVar2.c.setVisibility(8);
            aVar2.b.setProgress(0);
        }
    }

    private void d() {
        boolean z;
        int D = dji.pilot.battery.a.a.getInstance().D() - 1;
        DJIBatteryOverview[] dJIBatteryOverviewArr = (DJIBatteryOverview[]) dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.a.s, true);
        if (dJIBatteryOverviewArr == null || dJIBatteryOverviewArr.length <= D) {
            z = false;
        } else {
            z = dJIBatteryOverviewArr[D].isConnected();
        }
        int a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.a.f, D));
        D = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.a.i, D));
        if (z) {
            this.l.setText("" + a + getResources().getString(R.string.setting_ui_battery_percent));
            this.m.setText(String.valueOf(D));
            return;
        }
        this.l.setText(R.string.setting_ui_na);
        this.m.setText(R.string.setting_ui_na);
    }
}
