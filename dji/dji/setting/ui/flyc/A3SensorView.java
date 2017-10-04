package dji.setting.ui.flyc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycGetPushParamsByHash;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;
import java.util.ArrayList;

public class A3SensorView extends DividerLinearLayout {
    private int[] a = new int[]{R.string.setting_ui_redundancy_sensor_imu_stat_0, R.string.setting_ui_redundancy_sensor_imu_stat_1, R.string.setting_ui_redundancy_sensor_imu_stat_2, R.string.setting_ui_redundancy_sensor_imu_stat_3, R.string.setting_ui_redundancy_sensor_imu_stat_4, R.string.setting_ui_redundancy_sensor_imu_stat_5, R.string.setting_ui_redundancy_sensor_imu_stat_6};
    private View b;
    private View c;
    private TextView d;
    private TextView e;
    private TextView f;
    private Button g;
    private StatusView[] h;
    private String[] i = new String[]{"g_config.fdi_sensor[0].acc_bias_0", "g_config.fdi_sensor[1].acc_bias_0", "g_config.fdi_sensor[2].acc_bias_0"};
    private String[] l = new String[]{"g_config.fdi_sensor[0].acc_stat_0", "g_config.fdi_sensor[1].acc_stat_0", "g_config.fdi_sensor[2].acc_stat_0"};
    private StatusView[] m;
    private String[] n = new String[]{"g_config.fdi_sensor[0].gyr_bias_0", "g_config.fdi_sensor[1].gyr_bias_0", "g_config.fdi_sensor[2].gyr_bias_0"};
    private String[] o = new String[]{"g_config.fdi_sensor[0].gyr_stat_0", "g_config.fdi_sensor[1].gyr_stat_0", "g_config.fdi_sensor[2].gyr_stat_0"};
    private String p = "g_status.ns_busy_dev_0";

    public A3SensorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_flyc_a3sensor);
        if (!isInEditMode()) {
            this.c = findViewById(R.id.setting_ui_flyc_imu_ly2);
            this.b = findViewById(R.id.setting_ui_flyc_imu_ly3);
            this.d = (TextView) findViewById(R.id.setting_ui_flyc_acc_0_title);
            this.e = (TextView) findViewById(R.id.setting_ui_flyc_acc_1_title);
            this.f = (TextView) findViewById(R.id.setting_ui_flyc_acc_2_title);
            this.h = new StatusView[3];
            this.m = new StatusView[3];
            this.h[0] = (StatusView) findViewById(R.id.setting_ui_flyc_imu_acc_1);
            this.h[1] = (StatusView) findViewById(R.id.setting_ui_flyc_imu_acc_2);
            this.h[2] = (StatusView) findViewById(R.id.setting_ui_flyc_imu_acc_3);
            this.m[0] = (StatusView) findViewById(R.id.setting_ui_flyc_imu_gyr_1);
            this.m[1] = (StatusView) findViewById(R.id.setting_ui_flyc_imu_gyr_2);
            this.m[2] = (StatusView) findViewById(R.id.setting_ui_flyc_imu_gyr_3);
            this.d.setSelected(true);
            this.g = (Button) findViewById(R.id.setting_ui_flyc_cal_btn);
            this.g.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ A3SensorView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (!a.b().a(this.a.getContext())) {
                        c.a().e(new dji.setting.ui.c(R.layout.setting_ui_group_flyc_a3_sensor_cal, R.string.setting_ui_redundancy_sensor_imu_calc, this.a));
                    }
                }
            });
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        c.a().a(getAllKeys(), 1);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
        c.a().b(getAllKeys(), 1);
    }

    public void onEventMainThread(DataFlycGetPushParamsByHash dataFlycGetPushParamsByHash) {
        b();
    }

    private void b() {
        if (c()) {
            boolean z;
            StatusView statusView;
            float abs;
            int intValue;
            boolean z2;
            float f;
            LayoutParams layoutParams;
            setVisibility(0);
            ProductType c = i.getInstance().c();
            if (dji.pilot.publics.e.a.s(c) == 2) {
                this.b.setVisibility(8);
                z = true;
            } else if (dji.pilot.publics.e.a.s(c) == 1) {
                this.c.setVisibility(8);
                this.b.setVisibility(8);
                z = true;
            } else {
                this.b.setVisibility(0);
                this.c.setVisibility(0);
                z = true;
            }
            boolean z3 = false;
            boolean z4 = false;
            while (z3 < z) {
                statusView = this.h[z3];
                abs = Math.abs(d.read(this.i[z3]).value.floatValue());
                if (abs > 0.1f) {
                    abs = 0.1f;
                }
                intValue = d.read(this.l[z3]).value.intValue();
                if (intValue == 7) {
                    statusView.c.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.setting_ui_status_pgb_green));
                    z2 = z4;
                    f = abs;
                } else if (intValue == 8) {
                    statusView.c.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.setting_ui_status_pgb_yellow));
                    z2 = z4;
                    f = abs;
                } else if (intValue == 9) {
                    statusView.c.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.setting_ui_status_pgb_red));
                    f = abs;
                    z2 = true;
                } else {
                    z2 = z4;
                    f = -1.0f;
                }
                statusView.c.setVisibility(0);
                statusView.a.setVisibility(0);
                layoutParams = (LayoutParams) statusView.b.getLayoutParams();
                layoutParams.removeRule(15);
                layoutParams.addRule(12);
                statusView.b.setLayoutParams(layoutParams);
                if (f != -1.0f) {
                    statusView.a.setText(String.format("%.3f", new Object[]{Float.valueOf(f)}));
                    statusView.c.setProgress((int) ((((double) f) / 0.1d) * 100.0d));
                    if (statusView.c.getProgress() < 5) {
                        statusView.c.setProgress(5);
                    }
                    statusView.b.setVisibility(8);
                } else {
                    statusView.c.setProgress(0);
                    if (intValue < 0 || intValue >= this.a.length) {
                        statusView.b.setVisibility(8);
                    } else {
                        statusView.b.setText(this.a[intValue]);
                        statusView.b.setVisibility(0);
                        if (intValue == 1) {
                            statusView.c.setVisibility(8);
                            statusView.a.setVisibility(8);
                            layoutParams.removeRule(12);
                            layoutParams.addRule(15);
                        }
                        if (intValue == 3) {
                            z2 = true;
                        }
                    }
                }
                z3++;
                z4 = z2;
            }
            z3 = false;
            while (z3 < z) {
                statusView = this.m[z3];
                abs = Math.abs(d.read(this.n[z3]).value.floatValue());
                if (abs > 0.05f) {
                    abs = 0.05f;
                }
                intValue = d.read(this.o[z3]).value.intValue();
                if (intValue == 7) {
                    statusView.c.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.setting_ui_status_pgb_green));
                    z2 = z4;
                    f = abs;
                } else if (intValue == 8) {
                    statusView.c.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.setting_ui_status_pgb_yellow));
                    z2 = z4;
                    f = abs;
                } else if (intValue == 9) {
                    statusView.c.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.setting_ui_status_pgb_red));
                    f = abs;
                    z2 = true;
                } else {
                    z2 = z4;
                    f = -1.0f;
                }
                statusView.c.setVisibility(0);
                statusView.a.setVisibility(0);
                layoutParams = (LayoutParams) statusView.b.getLayoutParams();
                layoutParams.removeRule(15);
                layoutParams.addRule(12);
                statusView.b.setLayoutParams(layoutParams);
                if (f != -1.0f) {
                    statusView.a.setText(String.format("%.3f", new Object[]{Float.valueOf(f)}));
                    statusView.c.setProgress((int) ((((double) f) / 0.05d) * 100.0d));
                    if (statusView.c.getProgress() < 5) {
                        statusView.c.setProgress(5);
                    }
                    statusView.b.setVisibility(8);
                } else {
                    statusView.c.setProgress(0);
                    if (intValue < 0 || intValue >= this.a.length) {
                        statusView.b.setVisibility(8);
                    } else {
                        statusView.b.setText(this.a[intValue]);
                        statusView.b.setVisibility(0);
                        if (intValue == 1) {
                            statusView.c.setVisibility(8);
                            statusView.a.setVisibility(8);
                            layoutParams.removeRule(12);
                            layoutParams.addRule(15);
                        }
                        if (intValue == 3) {
                            z2 = true;
                        }
                    }
                }
                z3++;
                z4 = z2;
            }
            this.d.setSelected(false);
            this.e.setSelected(false);
            this.f.setSelected(false);
            int intValue2 = (d.read(this.p).value.intValue() >> 4) & 3;
            if (intValue2 == 1) {
                this.d.setSelected(true);
            } else if (intValue2 == 2) {
                this.e.setSelected(true);
            } else if (intValue2 == 3) {
                this.f.setSelected(true);
            }
            if (z4) {
                this.g.setAlpha(1.0f);
                return;
            } else {
                this.g.setAlpha(dji.pilot.visual.a.d.c);
                return;
            }
        }
        setVisibility(8);
    }

    private boolean c() {
        ProductType c = i.getInstance().c();
        if (dji.pilot.publics.e.a.d(c) || c == ProductType.A3 || c == ProductType.Tomato || c == ProductType.Pomato || dji.pilot.publics.e.a.c(c) || c == ProductType.N3) {
            return true;
        }
        return false;
    }

    private String[] getAllKeys() {
        int i = 0;
        ArrayList arrayList = new ArrayList();
        for (Object add : this.i) {
            arrayList.add(add);
        }
        for (Object add2 : this.l) {
            arrayList.add(add2);
        }
        for (Object add22 : this.n) {
            arrayList.add(add22);
        }
        String[] strArr = this.o;
        int length = strArr.length;
        while (i < length) {
            arrayList.add(strArr[i]);
            i++;
        }
        arrayList.add(this.p);
        String[] strArr2 = new String[arrayList.size()];
        arrayList.toArray(strArr2);
        return strArr2;
    }
}
