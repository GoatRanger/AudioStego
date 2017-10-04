package dji.setting.ui.flyc;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.dji.frame.c.l;
import com.here.odnp.config.OdnpConfigStatic;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycRedundancyStatus;
import dji.midware.data.model.P3.DataFlycRedundancyStatus.COLOR_STATUS;
import dji.midware.data.model.P3.DataFlycRedundancyStatus.IMUStatus;
import dji.midware.data.model.P3.DataFlycRedundancyStatus.RS_CMD_TYPE;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.params.P3.a;
import dji.midware.e.d;
import dji.pilot.fpv.control.DJIRedundancySysController;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class A3SensorCalView extends DividerLinearLayout {
    private TextView[] a = new TextView[3];
    private ImageView[] b = new ImageView[3];
    private Button c = null;
    private TextView d = null;
    private TextView e = null;
    private ProgressBar[] f = new ProgressBar[3];
    private TextView[] g = new TextView[3];
    private boolean h = false;
    private TextView i = null;
    private LinearLayout l = null;
    private View m = null;
    private TextView n = null;
    private TextView o = null;
    private final String[] p = new String[]{a.k, a.l, a.m};
    private final String[] q = new String[]{a.w, a.x, a.y, "g_status.acc_gyro[0].cali_cnt_0", "g_status.acc_gyro[1].cali_cnt_0", "g_status.acc_gyro[2].cali_cnt_0", a.t, a.u, a.v};
    private OnClickListener r = new OnClickListener(this) {
        final /* synthetic */ A3SensorCalView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            boolean z = true;
            int id = view.getId();
            TextView textView;
            if (id == R.id.fpv_redundancy_sensor_imu_sys1_sel) {
                textView = this.a.a[0];
                if (this.a.a[0].isSelected()) {
                    z = false;
                }
                textView.setSelected(z);
            } else if (id == R.id.fpv_redundancy_sensor_imu_sys2_sel) {
                textView = this.a.a[1];
                if (this.a.a[1].isSelected()) {
                    z = false;
                }
                textView.setSelected(z);
            } else if (id == R.id.fpv_redundancy_sensor_imu_sys3_sel) {
                textView = this.a.a[2];
                if (this.a.a[2].isSelected()) {
                    z = false;
                }
                textView.setSelected(z);
            } else if (id != R.id.fpv_redundancy_sensor_calc_imu_btn) {
            } else {
                if (this.a.a[0].isSelected() || this.a.a[1].isSelected() || this.a.a[2].isSelected()) {
                    dji.setting.ui.widget.a.b(this.a.getContext(), R.string.setting_ui_redundancy_sensor_calc_confirm_desc, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            Number[] numberArr;
                            String[] strArr;
                            int i2 = 1;
                            this.a.a.c.setEnabled(false);
                            DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
                            if (2 == dji.pilot.publics.e.a.s(i.getInstance().c())) {
                                numberArr = new Number[]{Integer.valueOf(1), Integer.valueOf(1)};
                                strArr = new String[]{this.a.a.p[0], this.a.a.p[1]};
                            } else {
                                String[] e = this.a.a.p;
                                Number[] numberArr2 = new Number[3];
                                numberArr2[0] = Integer.valueOf(this.a.a.a[0].isSelected() ? 1 : 0);
                                numberArr2[1] = Integer.valueOf(this.a.a.a[1].isSelected() ? 1 : 0);
                                if (!this.a.a.a[2].isSelected()) {
                                    i2 = 0;
                                }
                                numberArr2[2] = Integer.valueOf(i2);
                                numberArr = numberArr2;
                                strArr = e;
                            }
                            dataFlycSetParams.a(strArr);
                            dataFlycSetParams.a(numberArr);
                            dataFlycSetParams.start(new d(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void onSuccess(Object obj) {
                                    this.a.a.a.a(4);
                                    this.a.a.a.t = true;
                                    this.a.a.a.a[0].postDelayed(new Runnable(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void run() {
                                            this.a.a.a.a.a(false);
                                            this.a.a.a.a.s = true;
                                        }
                                    }, 1500);
                                }

                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                    this.a.a.a.a(0);
                                }
                            });
                        }
                    });
                } else {
                    dji.setting.ui.widget.a.a(this.a.getContext(), R.string.setting_ui_redundancy_sensor_calc_no_selection);
                }
            }
        }
    };
    private boolean s = false;
    private boolean t = false;

    public A3SensorCalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            onEventMainThread(DJIRedundancySysController.getInstance(getContext()).a());
            this.t = false;
            this.h = true;
            this.i.setVisibility(8);
            boolean[] k = DJIRedundancySysController.getInstance(getContext()).k();
            int i = 0;
            for (int i2 = 0; i2 < k.length; i2++) {
                if (k[i2]) {
                    i++;
                }
                this.a[i2].setSelected(k[i2]);
            }
            this.d.setText(String.format(getContext().getString(R.string.setting_ui_redundancy_sensor_imu_calc_msg1), new Object[]{Integer.valueOf(i)}));
            if (2 == dji.pilot.publics.e.a.s(i.getInstance().c())) {
                this.a[0].setSelected(true);
                this.a[1].setSelected(true);
                this.a[2].setSelected(false);
                this.a[0].setVisibility(8);
                this.a[1].setVisibility(8);
                this.l.setVisibility(8);
                this.m.setVisibility(8);
                this.n.setText(R.string.setting_ui_redundancy_sensor_imu_1);
                this.o.setText(R.string.setting_ui_redundancy_sensor_imu_2);
                this.d.setVisibility(8);
            } else {
                this.a[0].setVisibility(0);
                this.a[1].setVisibility(0);
                this.l.setVisibility(0);
                this.m.setVisibility(0);
                this.n.setText(R.string.setting_ui_redundancy_sensor_imu_system1);
                this.o.setText(R.string.setting_ui_redundancy_sensor_imu_system2);
                this.d.setVisibility(0);
            }
            a(true);
            b();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.h = false;
        c.a().d(this);
    }

    private void a() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_flyc_sensor_imu_calc);
        if (!isInEditMode()) {
            this.a[0] = (TextView) findViewById(R.id.fpv_redundancy_sensor_imu_sys1_sel);
            this.a[1] = (TextView) findViewById(R.id.fpv_redundancy_sensor_imu_sys2_sel);
            this.a[2] = (TextView) findViewById(R.id.fpv_redundancy_sensor_imu_sys3_sel);
            this.a[0].setOnClickListener(this.r);
            this.a[1].setOnClickListener(this.r);
            this.a[2].setOnClickListener(this.r);
            this.b[0] = (ImageView) findViewById(R.id.fpv_redundancy_sensor_imu_sys1_img);
            this.b[1] = (ImageView) findViewById(R.id.fpv_redundancy_sensor_imu_sys2_img);
            this.b[2] = (ImageView) findViewById(R.id.fpv_redundancy_sensor_imu_sys3_img);
            this.c = (Button) findViewById(R.id.fpv_redundancy_sensor_calc_imu_btn);
            this.c.setOnClickListener(this.r);
            this.f[0] = (ProgressBar) findViewById(R.id.fpv_redundancy_sensor_calc_imu_system1_progress);
            this.f[1] = (ProgressBar) findViewById(R.id.fpv_redundancy_sensor_calc_imu_system2_progress);
            this.f[2] = (ProgressBar) findViewById(R.id.fpv_redundancy_sensor_calc_imu_system3_progress);
            this.g[0] = (TextView) findViewById(R.id.fpv_redundancy_sensor_calc_imu_system1_progress_value);
            this.g[1] = (TextView) findViewById(R.id.fpv_redundancy_sensor_calc_imu_system2_progress_value);
            this.g[2] = (TextView) findViewById(R.id.fpv_redundancy_sensor_calc_imu_system3_progress_value);
            this.d = (TextView) findViewById(R.id.fpv_redundancy_sensor_calc_imu_msg1);
            this.e = (TextView) findViewById(R.id.fpv_redundancy_sensor_calc_imu_msg2);
            this.i = (TextView) findViewById(R.id.fpv_redundancy_sensor_calc_imu_failed);
            this.l = (LinearLayout) findViewById(R.id.fpv_redundancy_sensor_calc_imu_system3_ly);
            this.m = findViewById(R.id.fpv_redundancy_sensor_calc_imu_div23);
            this.n = (TextView) findViewById(R.id.fpv_redundancy_sensor_imu_sys1_label);
            this.o = (TextView) findViewById(R.id.fpv_redundancy_sensor_imu_sys2_label);
        }
    }

    private void b() {
        DataFlycRedundancyStatus.getInstance().a(RS_CMD_TYPE.b).start(new d(this) {
            final /* synthetic */ A3SensorCalView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.a[0].post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        for (IMUStatus iMUStatus : DataFlycRedundancyStatus.getInstance().c()) {
                            if (iMUStatus.imuIndex >= 0 && iMUStatus.imuIndex < 3) {
                                if (COLOR_STATUS.ofValue(iMUStatus.colorStatus) == COLOR_STATUS.a) {
                                    this.a.a.a[iMUStatus.imuIndex].setEnabled(false);
                                    this.a.a.a[iMUStatus.imuIndex].setSelected(false);
                                    this.a.a.a[iMUStatus.imuIndex].setAlpha(0.3f);
                                } else {
                                    this.a.a.a[iMUStatus.imuIndex].setEnabled(true);
                                    this.a.a.a[iMUStatus.imuIndex].setAlpha(1.0f);
                                }
                            }
                        }
                    }
                });
                this.a.c();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.c();
            }
        });
    }

    private void c() {
        if (!this.t && this.h) {
            this.a[0].postDelayed(new Runnable(this) {
                final /* synthetic */ A3SensorCalView a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.b();
                }
            }, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
        }
    }

    private void d() {
        if (this.s) {
            this.s = false;
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_redundancy_sensor_calc_success_desc);
        }
    }

    private void a(final boolean z) {
        new DataFlycGetParams().setInfos(2 == dji.pilot.publics.e.a.s(i.getInstance().c()) ? new String[]{this.q[0], this.q[1], this.q[3], this.q[4], this.q[6], this.q[7]} : this.q).start(new d(this) {
            final /* synthetic */ A3SensorCalView b;

            public void onSuccess(Object obj) {
                this.b.a[0].post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        int i;
                        int[] iArr = new int[this.a.b.q.length];
                        for (i = 0; i < this.a.b.q.length; i++) {
                            iArr[i] = dji.midware.data.manager.P3.d.read(this.a.b.q[i]).value.byteValue();
                        }
                        ProductType c = i.getInstance().c();
                        int s = dji.pilot.publics.e.a.s(c);
                        i = 0;
                        CharSequence charSequence = "";
                        Object obj = null;
                        Object obj2 = 1;
                        int i2 = 0;
                        while (i2 < s) {
                            DJILogHelper.getInstance().LOGD("", "success:value=" + iArr[i2] + " index=" + i2, false, true);
                            if (iArr[i2] == 1 || iArr[i2] == 2 || iArr[i2] == 11) {
                                this.a.b.f[i2].setProgress(iArr[i2 + 3]);
                                this.a.b.f[i2].setProgressDrawable(this.a.b.getContext().getResources().getDrawable(R.drawable.setting_ui_status_pgb_green));
                                this.a.b.g[i2].setText(String.format("%d%%", new Object[]{Integer.valueOf(iArr[i2 + 3])}));
                                this.a.b.t = true;
                                obj2 = null;
                                this.a.b.a[i2].setSelected(true);
                                this.a.b.s = true;
                            } else if (iArr[i2] != 0) {
                                if (this.a.b.a[i2].isSelected() || 2 == dji.pilot.publics.e.a.s(c)) {
                                    if (iArr[i2] == 12 || iArr[i2] == 80 || iArr[i2] == 81) {
                                        this.a.b.f[i2].setProgress(100);
                                        this.a.b.f[i2].setProgressDrawable(this.a.b.getContext().getResources().getDrawable(R.drawable.setting_ui_status_pgb_green));
                                        this.a.b.g[i2].setText("100%");
                                    } else {
                                        obj = 1;
                                        this.a.b.f[i2].setProgress(100);
                                        this.a.b.g[i2].setText(R.string.setting_ui_redundancy_sensor_calc_failed);
                                        this.a.b.f[i2].setProgressDrawable(this.a.b.getContext().getResources().getDrawable(R.drawable.setting_ui_status_pgb_red));
                                    }
                                } else if (z) {
                                    this.a.b.f[i2].setProgress(0);
                                    this.a.b.g[i2].setText("");
                                }
                                if (iArr[i2] < 0 && !z) {
                                    charSequence = charSequence + this.a.b.a(i2, iArr[i2]) + "\n";
                                    i++;
                                }
                            } else if (!z && this.a.b.a[i2].isSelected()) {
                                this.a.b.f[i2].setProgress(0);
                                this.a.b.f[i2].setProgressDrawable(this.a.b.getContext().getResources().getDrawable(R.drawable.setting_ui_status_pgb_green));
                                this.a.b.g[i2].setText("0%");
                                this.a.b.t = true;
                                obj2 = null;
                                this.a.b.a[i2].setSelected(true);
                            }
                            i2++;
                        }
                        if (l.a(charSequence)) {
                            this.a.b.i.setVisibility(8);
                        } else {
                            this.a.b.i.setText(charSequence);
                            this.a.b.i.setVisibility(0);
                        }
                        if (obj2 != null) {
                            this.a.b.t = false;
                            this.a.b.c.setEnabled(true);
                            if (!z) {
                                this.a.b.d.setText(String.format(this.a.b.getContext().getString(R.string.setting_ui_redundancy_sensor_imu_calc_msg1), new Object[]{Integer.valueOf(i)}));
                                this.a.b.d.setVisibility(0);
                            }
                            this.a.b.e.setVisibility(8);
                            this.a.b.c.setVisibility(0);
                            this.a.b.a[0].setEnabled(true);
                            this.a.b.a[0].setAlpha(1.0f);
                            this.a.b.a[1].setEnabled(true);
                            this.a.b.a[1].setAlpha(1.0f);
                            this.a.b.a[2].setEnabled(true);
                            this.a.b.a[2].setAlpha(1.0f);
                            this.a.b.b();
                            if (obj == null) {
                                this.a.b.d();
                                return;
                            }
                            return;
                        }
                        this.a.b.d.setVisibility(8);
                        this.a.b.e.setVisibility(0);
                        this.a.b.c.setVisibility(4);
                        this.a.b.a[0].setEnabled(false);
                        this.a.b.a[0].setAlpha(0.3f);
                        this.a.b.a[1].setEnabled(false);
                        this.a.b.a[1].setAlpha(0.3f);
                        this.a.b.a[2].setEnabled(false);
                        this.a.b.a[2].setAlpha(0.3f);
                    }
                });
                this.b.a[0].postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.b.t) {
                            this.a.b.a(false);
                        }
                    }
                }, 1000);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (this.b.t) {
                    this.b.a[0].postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.b.a(false);
                        }
                    }, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
                }
            }
        });
    }

    private String a(int i, int i2) {
        String str = "";
        if (i == 0) {
            str = getContext().getString(R.string.setting_ui_redundancy_sensor_imu_system1);
        } else if (i == 1) {
            str = getContext().getString(R.string.setting_ui_redundancy_sensor_imu_system2);
        } else if (i != 2) {
            return "";
        } else {
            str = getContext().getString(R.string.setting_ui_redundancy_sensor_imu_system3);
        }
        if (i2 == -11) {
            return str + getContext().getString(R.string.setting_ui_redundancy_sensor_calc_failed_n11);
        }
        if (i2 == -12) {
            return str + getContext().getString(R.string.setting_ui_redundancy_sensor_calc_failed_n12);
        }
        if (i2 == -13) {
            return str + getContext().getString(R.string.setting_ui_redundancy_sensor_calc_failed_n13);
        }
        if (i2 <= -30 && i2 >= -41) {
            return str + getContext().getString(R.string.setting_ui_redundancy_sensor_calc_failed_n30_to_n41);
        }
        if (i2 <= -43 && i2 >= -48) {
            return str + getContext().getString(R.string.setting_ui_redundancy_sensor_calc_failed_n43_to_n48);
        }
        if (i2 == -50 || i2 == -71 || i2 == -72) {
            return str + getContext().getString(R.string.setting_ui_redundancy_sensor_calc_failed_n50);
        }
        if (i2 == -51 || i2 == -71 || i2 == -72) {
            return str + getContext().getString(R.string.setting_ui_redundancy_sensor_calc_failed_n51);
        }
        if (i2 == -52) {
            return str + getContext().getString(R.string.setting_ui_redundancy_sensor_calc_failed_n52);
        }
        if (i2 == -60) {
            return str + getContext().getString(R.string.setting_ui_redundancy_sensor_calc_failed_n60);
        }
        if (i2 == -61) {
            return str + getContext().getString(R.string.setting_ui_redundancy_sensor_calc_failed_n61);
        }
        return str + String.format(":error code 0x%X", new Object[]{Integer.valueOf(i2)});
    }

    private void a(final int i) {
        this.c.post(new Runnable(this) {
            final /* synthetic */ A3SensorCalView b;

            public void run() {
                this.b.c.setEnabled(true);
                this.b.c.setVisibility(i);
            }
        });
    }

    public void onEventMainThread(DJIRedundancySysController.d dVar) {
        for (int i = 0; i < this.b.length; i++) {
            if (dVar.c - 1 == i) {
                this.b[i].setVisibility(0);
            } else {
                this.b[i].setVisibility(4);
            }
        }
    }
}
