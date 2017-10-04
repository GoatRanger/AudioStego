package dji.setting.ui.battery;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.common.battery.DJIBatteryStatus;
import dji.common.battery.DJIBatteryWarningInformation;
import dji.common.product.Model;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.h;
import dji.sdksharedlib.c.d;
import dji.setting.ui.widget.DJIVerticalProgressBar;
import dji.setting.ui.widget.DividerLinearLayout;
import java.text.DecimalFormat;

public class VolumeView extends DividerLinearLayout implements d {
    private static final float a = 3.62f;
    private static final float b = 3.5f;
    private static final float[] c = new float[]{3.0f, 4.35f};
    private static final int[] d = new int[]{R.id.battery_firstpart_ly, R.id.battery_secondpart_ly, R.id.battery_thirdpart_ly, R.id.battery_forthpart_ly, R.id.battery_fifthpart_ly, R.id.battery_sixthpart_ly};
    private final Drawable[] e = new Drawable[6];
    private final Drawable[] f = new Drawable[6];
    private final Drawable[] g = new Drawable[6];
    private final Drawable[] h = new Drawable[6];
    private final b[] i = new b[6];
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private DecimalFormat p = new DecimalFormat("#.#");

    public static class a {
        public float a = 0.0f;
        public int b = 50;
        public int c = 0;
        public boolean d = false;

        public a(int i, DJIBatteryWarningInformation dJIBatteryWarningInformation, int i2) {
            boolean z = false;
            this.a = ((float) i) / 1000.0f;
            this.b = a(this.a, 100);
            if (dJIBatteryWarningInformation != null) {
                if (dJIBatteryWarningInformation.getDamagedBatteryCellIndex() == i2 + 1) {
                    z = true;
                }
                this.d = z;
                if (dJIBatteryWarningInformation.getUnderVoltageBatteryCellIndex() == i2 + 1) {
                    this.c = 2;
                } else {
                    this.c = a(this.a);
                }
            }
        }

        private int a(float f, int i) {
            if (f >= VolumeView.c[1]) {
                return 100;
            }
            if (f > VolumeView.c[0]) {
                return (int) (((f - VolumeView.c[0]) * ((float) i)) / (VolumeView.c[1] - VolumeView.c[0]));
            }
            return 0;
        }

        public static int a(float f) {
            if (f < VolumeView.b) {
                return 2;
            }
            if (f < VolumeView.a) {
                return 1;
            }
            return 0;
        }
    }

    private static final class b {
        private RelativeLayout a;
        private DJIVerticalProgressBar b;
        private ImageView c;
        private TextView d;

        private b() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
        }
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        c();
    }

    public VolumeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    private void b() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_battery_volume);
        if (!isInEditMode()) {
            int length = this.i.length;
            for (int i = 0; i < length; i++) {
                b bVar = new b();
                bVar.a = (RelativeLayout) findViewById(d[i]);
                bVar.b = (DJIVerticalProgressBar) bVar.a.findViewById(R.id.battery_part_pgb);
                bVar.c = (ImageView) bVar.a.findViewById(R.id.battery_part_icon);
                bVar.d = (TextView) bVar.a.findViewById(R.id.battery_part_tv);
                this.i[i] = bVar;
                this.e[i] = getContext().getResources().getDrawable(R.drawable.setting_ui_battery_volume_low_pgb);
                this.f[i] = getContext().getResources().getDrawable(R.drawable.setting_ui_battery_volume_disable_pgb);
                this.g[i] = getContext().getResources().getDrawable(R.drawable.setting_ui_battery_volume_normal_pgb);
                this.h[i] = getContext().getResources().getDrawable(R.drawable.setting_ui_battery_volume_yellow_pgb);
            }
            this.l = (TextView) findViewById(R.id.setting_ui_battery_voltage);
            this.m = (TextView) findViewById(R.id.setting_ui_battery_temperature);
            this.n = (TextView) findViewById(R.id.setting_ui_battery_voltage_unit);
            this.o = (TextView) findViewById(R.id.setting_ui_battery_temperature_unit);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            dji.sdksharedlib.a.a.a(this, h.c);
            dji.sdksharedlib.a.a.d(this, new String[]{dji.sdksharedlib.b.a.l, dji.sdksharedlib.b.a.j, dji.sdksharedlib.b.a.d, dji.sdksharedlib.b.a.h, dji.sdksharedlib.b.a.m});
            c();
        }
    }

    protected void onDetachedFromWindow() {
        dji.sdksharedlib.a.a.a(this);
        super.onDetachedFromWindow();
    }

    private void c() {
        Model model = (Model) dji.sdksharedlib.a.a.a(h.c);
        int[] iArr = (int[]) dji.sdksharedlib.a.a.d(dji.sdksharedlib.b.a.l);
        DJIBatteryWarningInformation dJIBatteryWarningInformation = (DJIBatteryWarningInformation) dji.sdksharedlib.a.a.d(dji.sdksharedlib.b.a.j);
        DJIBatteryStatus dJIBatteryStatus = (DJIBatteryStatus) dji.sdksharedlib.a.a.d(dji.sdksharedlib.b.a.m);
        if (model == Model.MavicPro) {
            dJIBatteryStatus = DJIBatteryStatus.NORMAL;
        }
        if (model == null || r3 == null || iArr == null || dJIBatteryWarningInformation == null) {
            d();
            return;
        }
        int i;
        if (dji.pilot.publics.e.a.b(model)) {
            this.i[3].a.setVisibility(0);
            this.i[4].a.setVisibility(0);
            this.i[5].a.setVisibility(0);
            i = 6;
        } else if (dji.pilot.publics.e.a.c(null)) {
            this.i[3].a.setVisibility(8);
            this.i[4].a.setVisibility(8);
            this.i[5].a.setVisibility(8);
            i = 3;
        } else if (dji.pilot.publics.e.a.a(model)) {
            this.i[3].a.setVisibility(0);
            this.i[4].a.setVisibility(8);
            this.i[5].a.setVisibility(8);
            i = 4;
        } else {
            i = 0;
        }
        int length = this.i.length;
        a[] aVarArr = new a[i];
        for (length = 0; length < i; length++) {
            aVarArr[length] = new a(iArr[length], null, length);
        }
        for (length = 0; length < i; length++) {
            Drawable drawable;
            b bVar = this.i[length];
            a aVar = aVarArr[length];
            bVar.d.setText(getResources().getString(R.string.setting_ui_battery_partvoltage_unit, new Object[]{Float.valueOf(aVar.a)}));
            bVar.c.setVisibility(8);
            if (aVar.d) {
                drawable = this.f[length];
                bVar.c.setVisibility(0);
            } else if (aVar.c == 2) {
                drawable = this.e[length];
            } else if (aVar.c == 1) {
                drawable = this.h[length];
            } else {
                drawable = this.g[length];
            }
            if (bVar.b.getProgressDrawable() != drawable) {
                bVar.b.setProgressDrawable(drawable);
            }
            if (bVar.b.getProgress() != aVar.b) {
                bVar.b.setProgress(aVar.b);
            }
        }
        Integer num = (Integer) dji.sdksharedlib.a.a.d(dji.sdksharedlib.b.a.d);
        Float f = (Float) dji.sdksharedlib.a.a.d(dji.sdksharedlib.b.a.h);
        float intValue = num == null ? 0.0f : ((float) num.intValue()) / 1000.0f;
        float floatValue = f == null ? 0.0f : f.floatValue();
        this.l.setText(getContext().getString(R.string.setting_ui_battery_voltage_unit, new Object[]{Float.valueOf(intValue)}));
        this.l.setTextColor(a(aVarArr));
        this.m.setText(getContext().getString(R.string.setting_ui_battery_temperature_unit, new Object[]{a(floatValue)}));
        this.n.setTextColor(a(aVarArr));
        this.n.setVisibility(0);
        this.o.setVisibility(0);
    }

    private void d() {
        for (b bVar : this.i) {
            bVar.d.setText(R.string.setting_ui_na);
            bVar.c.setVisibility(8);
            bVar.b.setProgress(0);
        }
        this.l.setText(R.string.setting_ui_na);
        this.l.setTextColor(getResources().getColor(R.color.white));
        this.m.setText(R.string.setting_ui_na);
        this.n.setTextColor(getResources().getColor(R.color.white));
        this.n.setVisibility(8);
        this.o.setVisibility(8);
    }

    private int a(a[] aVarArr) {
        if (aVarArr == null) {
            return getContext().getResources().getColor(R.color.white);
        }
        int i;
        float f = 0.0f;
        for (i = 0; i < aVarArr.length; i++) {
            if (i == 0) {
                f = aVarArr[i].a;
            } else if (f > aVarArr[i].a) {
                f = aVarArr[i].a;
            }
        }
        i = dji.pilot.battery.a.a.c(f);
        if (i == 2) {
            return getContext().getResources().getColor(R.color.setting_ui_battery_red);
        }
        if (i == 1) {
            return getContext().getResources().getColor(R.color.setting_ui_battery_yellow);
        }
        return getContext().getResources().getColor(R.color.setting_ui_battery_green);
    }

    private final String a(float f) {
        return this.p.format((double) f);
    }
}
