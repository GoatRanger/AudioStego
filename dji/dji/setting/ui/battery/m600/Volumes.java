package dji.setting.ui.battery.m600;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.common.battery.DJIBatteryOverview;
import dji.midware.data.model.P3.DataSmartBatteryGetMultBatteryInfo;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.d;
import dji.setting.ui.widget.DJIVerticalProgressBar;
import dji.setting.ui.widget.DividerLinearLayout;

public class Volumes extends DividerLinearLayout implements OnClickListener, d {
    private static final int[] a = new int[]{R.id.battery_sub1_ly, R.id.battery_sub2_ly, R.id.battery_sub3_ly, R.id.battery_sub4_ly, R.id.battery_sub5_ly, R.id.battery_sub6_ly};
    private final Drawable[] b;
    private final Drawable[] c;
    private final Drawable[] d;
    private final Drawable[] e;
    private final a[] f;
    private DataSmartBatteryGetMultBatteryInfo g;

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
        b();
    }

    public Volumes(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = new Drawable[6];
        this.c = new Drawable[6];
        this.d = new Drawable[6];
        this.e = new Drawable[6];
        this.f = new a[6];
        this.g = null;
        this.g = new DataSmartBatteryGetMultBatteryInfo();
        a();
    }

    private void a() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_battery_volume_m600);
        if (!isInEditMode()) {
            int length = this.f.length;
            for (int i = 0; i < length; i++) {
                a aVar = new a();
                aVar.a = (RelativeLayout) findViewById(a[i]);
                aVar.a.setOnClickListener(this);
                aVar.b = (DJIVerticalProgressBar) aVar.a.findViewById(R.id.battery_part_pgb);
                aVar.c = (ImageView) aVar.a.findViewById(R.id.battery_part_icon);
                aVar.d = (TextView) aVar.a.findViewById(R.id.battery_part_tv);
                this.f[i] = aVar;
                this.b[i] = getContext().getResources().getDrawable(R.drawable.setting_ui_battery_volume_low_pgb);
                this.c[i] = getContext().getResources().getDrawable(R.drawable.setting_ui_battery_volume_disable_pgb);
                this.d[i] = getContext().getResources().getDrawable(R.drawable.setting_ui_battery_volume_normal_pgb);
                this.e[i] = getContext().getResources().getDrawable(R.drawable.setting_ui_battery_volume_yellow_pgb);
            }
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        dji.sdksharedlib.a.a.e(this, new String[]{dji.sdksharedlib.b.a.s});
        b();
    }

    protected void onDetachedFromWindow() {
        dji.sdksharedlib.a.a.a(this);
        super.onDetachedFromWindow();
    }

    private void b() {
        int i = 0;
        DJIBatteryOverview[] dJIBatteryOverviewArr = (DJIBatteryOverview[]) dji.sdksharedlib.a.a.a(dji.sdksharedlib.b.a.s, true);
        int length;
        int i2;
        if (dJIBatteryOverviewArr == null || dJIBatteryOverviewArr.length != this.f.length) {
            for (a aVar : this.f) {
                aVar.d.setText(R.string.setting_ui_na);
                aVar.c.setVisibility(8);
                aVar.b.setProgress(0);
            }
            return;
        }
        int[] iArr = new int[dJIBatteryOverviewArr.length];
        for (i2 = 0; i2 < dJIBatteryOverviewArr.length; i2++) {
            iArr[i2] = dJIBatteryOverviewArr[i2].getEnergyRemainingPercent();
        }
        length = this.f.length;
        while (i < length && i < iArr.length) {
            a aVar2 = this.f[i];
            aVar2.d.setText("" + iArr[i] + getResources().getString(R.string.setting_ui_battery_percent));
            aVar2.c.setVisibility(8);
            Drawable drawable = this.d[i];
            if (aVar2.b.getProgressDrawable() != drawable) {
                aVar2.b.setProgressDrawable(drawable);
            }
            if (aVar2.b.getProgress() != iArr[i]) {
                aVar2.b.setProgress(iArr[i]);
            }
            i++;
        }
    }

    public void onClick(View view) {
        if (!dji.setting.a.a.a()) {
            int id = view.getId();
            if (id == R.id.battery_sub1_ly) {
                dji.pilot.battery.a.a.getInstance().d(1);
            } else if (id == R.id.battery_sub2_ly) {
                dji.pilot.battery.a.a.getInstance().d(2);
            } else if (id == R.id.battery_sub3_ly) {
                dji.pilot.battery.a.a.getInstance().d(3);
            } else if (id == R.id.battery_sub4_ly) {
                dji.pilot.battery.a.a.getInstance().d(4);
            } else if (id == R.id.battery_sub5_ly) {
                dji.pilot.battery.a.a.getInstance().d(5);
            } else if (id == R.id.battery_sub6_ly) {
                dji.pilot.battery.a.a.getInstance().d(6);
            }
            dji.thirdparty.a.c.a().e(new dji.setting.ui.c(R.layout.setting_ui_group_battery_m600_sub, R.string.setting_ui_battery, (View) this));
        }
    }
}
