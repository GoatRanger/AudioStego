package dji.setting.ui.flyc;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataFlycGetPushLedStatus;
import dji.midware.data.model.P3.DataFlycGetPushLedStatus.LED_REASON;
import dji.midware.data.model.P3.DataFlycGetPushParamsByHash;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.util.a.b;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import org.msgpack.core.MessagePack.Code;

public class SensorView extends DividerLinearLayout implements OnClickListener {
    private ProgressBar A;
    private boolean B = false;
    private final float C = b.c;
    private final float D = 1.2f;
    private float E = b.c;
    private boolean F = false;
    private final float G = 0.02f;
    private final float H = 0.015f;
    private float I = 0.02f;
    private String[] J;
    private ParamInfo K = null;
    private ParamInfo L = null;
    private TextView[] M;
    private float N;
    private float O;
    private float P;
    private float Q;
    private float R;
    private float S;
    private Handler T = new Handler(new Callback(this) {
        final /* synthetic */ SensorView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.M[message.arg1].setText("" + d.valueOf(this.a.J[message.arg1]).intValue());
                    break;
                case 10:
                    this.a.j();
                    break;
                case 20:
                    this.a.x.setClickable(true);
                    break;
            }
            return false;
        }
    });
    private Timer U;
    private boolean V = false;
    private int W = 0;
    DataFlycSetParams a;
    private float aa = 0.0f;
    DataFlycSetParams b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private ProgressBar r;
    private TextView s;
    private String t = "%.2f";
    private TextView u;
    private boolean v = false;
    private boolean w = false;
    private TextView x;
    private ArrayList<Float> y = new ArrayList(20);
    private ArrayList<Float> z = new ArrayList(20);

    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] a = new int[o.values().length];

        static {
            try {
                a[o.b.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[o.a.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public SensorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    private void b() {
        a.a((View) this, R.layout.setting_ui_flyc_sensor);
        if (!isInEditMode()) {
            this.c = (TextView) findViewById(R.id.fpv_sensor_g_x);
            this.d = (TextView) findViewById(R.id.fpv_sensor_g_y);
            this.e = (TextView) findViewById(R.id.fpv_sensor_g_z);
            this.f = (TextView) findViewById(R.id.fpv_sensor_g_mod);
            this.g = (TextView) findViewById(R.id.fpv_sensor_a_x);
            this.h = (TextView) findViewById(R.id.fpv_sensor_a_y);
            this.i = (TextView) findViewById(R.id.fpv_sensor_a_z);
            this.l = (TextView) findViewById(R.id.fpv_sensor_a_mod);
            this.m = (TextView) findViewById(R.id.fpv_sensor_c_x);
            this.n = (TextView) findViewById(R.id.fpv_sensor_c_y);
            this.o = (TextView) findViewById(R.id.fpv_sensor_c_z);
            this.p = (TextView) findViewById(R.id.fpv_sensor_c_mod);
            this.M = new TextView[]{this.c, this.d, this.e, this.g, this.h, this.i, this.m, this.n, this.o};
            this.s = (TextView) findViewById(R.id.fpv_sensor_calib_tips);
            this.q = (TextView) findViewById(R.id.fpv_sensor_adv_cali);
            this.x = (TextView) findViewById(R.id.fpv_sensor_adv_check);
            this.r = (ProgressBar) findViewById(R.id.fpv_sensor_adv_progress);
            this.A = (ProgressBar) findViewById(R.id.fpv_sensor_adv_check_pgs);
            this.u = (TextView) findViewById(R.id.fpv_sensor_adv_progress_tv);
            this.q.setOnClickListener(this);
            this.x.setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.fpv_sensor_adv_cali) {
            if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
                c();
                return;
            }
            e.a("FPV_MCSettings_AdvancedSettings_Sensors_Calibrate_Button_Advanced");
            if (!a.b().a(getContext())) {
                dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_flyc_adv_sensor_cali_desc, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ SensorView a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.a();
                        dialogInterface.dismiss();
                    }
                });
            }
        } else if (view.getId() != R.id.fpv_sensor_adv_check || DataOsdGetPushCommon.getInstance().isMotorUp() || !ServiceManager.getInstance().isRemoteOK()) {
        } else {
            if (DataFlycGetPushLedStatus.getInstance().getLedReason() == LED_REASON.IMU_NEED_CALI) {
                a(true);
                return;
            }
            this.w = true;
            this.x.setClickable(false);
            this.A.setVisibility(0);
        }
    }

    private void c() {
        dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_flyc_adv_sensor_motor_up);
    }

    private void d() {
        boolean z;
        float f = 0.0f;
        this.A.setVisibility(4);
        Iterator it = this.y.iterator();
        float f2 = 0.0f;
        while (it.hasNext()) {
            f2 = ((Float) it.next()).floatValue() + f2;
        }
        float toDegrees = (float) Math.toDegrees((double) (f2 / 20.0f));
        DJILogHelper.getInstance().LOGD("View", "gmod=" + toDegrees + " needCalibGsensor=" + this.B, false, true);
        if (Math.abs(toDegrees) > this.E) {
            this.B = true;
            this.E = 1.2f;
        } else {
            this.B = false;
            this.E = b.c;
        }
        Iterator it2 = this.z.iterator();
        while (it2.hasNext()) {
            f += ((Float) it2.next()).floatValue();
        }
        toDegrees = f / 20.0f;
        DJILogHelper.getInstance().LOGD("View", "amod=" + (1.0f - toDegrees) + " needCalibAsensor=" + this.F, false, true);
        if (Math.abs(1.0f - toDegrees) > this.I) {
            this.F = true;
            this.I = 0.015f;
        } else {
            this.F = false;
            this.I = 0.02f;
        }
        if (this.F || this.B) {
            z = true;
        } else {
            z = false;
        }
        a(z);
        this.x.setClickable(true);
    }

    private void a(boolean z) {
        String str = "";
        if (z) {
            str = getContext().getString(R.string.setting_ui_flyc_sensor_imu_need);
        } else {
            str = getContext().getString(R.string.setting_ui_flyc_sensor_imu_noneed);
        }
        dji.setting.ui.widget.a.a(getContext(), str);
    }

    protected void a() {
        dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_flyc_adv_sensor_cali_again_desc, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ SensorView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
                    this.a.c();
                } else {
                    this.a.a(1);
                }
                dialogInterface.dismiss();
            }
        });
    }

    private void a(final int i) {
        new DataFlycSetParams().a(this.K.name, Integer.valueOf(i)).start(new dji.midware.e.d(this) {
            final /* synthetic */ SensorView b;

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("View", "set calibInfo " + i + " success ", false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("View", "set calibInfo " + i + " fail " + aVar, false, true);
            }
        });
    }

    private void e() {
        if (this.U != null) {
            this.U.cancel();
        }
        this.U = new Timer();
        this.U.schedule(new TimerTask(this) {
            final /* synthetic */ SensorView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.T.sendEmptyMessage(10);
            }
        }, 0, 1000);
    }

    private void f() {
        if (this.U != null) {
            this.U.cancel();
            this.U = null;
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.r.setProgress(0);
            this.u.setText("0%");
            if (DataOsdGetPushCommon.getInstance().isGetted()) {
                onEventBackgroundThread(DataOsdGetPushCommon.getInstance());
            }
            c.a().a(this);
            g();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        h();
        super.onDetachedFromWindow();
    }

    private void g() {
        if (this.J == null) {
            this.J = new String[]{"g_real.imu.wx_0", "g_real.imu.wy_0", "g_real.imu.wz_0", "g_real.imu.ax_0", "g_real.imu.ay_0", "g_real.imu.az_0", "g_real.imu.mx_0", "g_real.imu.my_0", "g_real.imu.mz_0", "imu_app_temp_cali.state_0", "IMU_Data.gyro_tempX_0", "imu_temp.real_ctl_out_per_0"};
            this.K = d.read("imu_app_temp_cali.start_flag_0");
            this.L = d.read("imu_app_temp_cali.cali_cnt_0");
            if (DataOsdGetPushCommon.getInstance().getFlycVersion() >= 4) {
                this.J = new String[]{"g_real.imu.wx_0", "g_real.imu.wy_0", "g_real.imu.wz_0", "g_real.imu.ax_0", "g_real.imu.ay_0", "g_real.imu.az_0", "g_real.imu.mx_0", "g_real.imu.my_0", "g_real.imu.mz_0", "imu_app_temp_cali.state_0", "IMU_Data.gyro_tempX_0", "imu_temp.real_ctl_out_per_0", "imu_app_temp_cali.cali_cnt_0"};
            }
        }
        c.a().a(this.J, 10);
    }

    private void h() {
        c.a().b(this.J, 10);
    }

    public void onEventBackgroundThread(o oVar) {
        switch (AnonymousClass8.a[oVar.ordinal()]) {
            case 1:
                g();
                return;
            case 2:
                if (this.U != null) {
                    this.U.cancel();
                    this.U = null;
                }
                this.V = false;
                this.W = 0;
                this.w = false;
                this.y.clear();
                this.z.clear();
                this.T.sendEmptyMessage(20);
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(DataFlycGetPushParamsByHash dataFlycGetPushParamsByHash) {
        i();
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        this.v = dataOsdGetPushCommon.getFlycVersion() < 5;
    }

    private void i() {
        for (int i = 0; i < 9; i++) {
            this.T.post(new Runnable(this) {
                final /* synthetic */ SensorView b;

                public void run() {
                    float shortValue;
                    if (i > 5) {
                        shortValue = (float) d.valueOf(this.b.J[i]).shortValue();
                    } else {
                        shortValue = d.valueOf(this.b.J[i]).floatValue();
                    }
                    this.b.M[i].setText(String.format(this.b.t, new Object[]{Float.valueOf(shortValue)}));
                }
            });
        }
        this.T.post(new Runnable(this) {
            final /* synthetic */ SensorView a;

            {
                this.a = r1;
            }

            public void run() {
                float floatValue = d.valueOf("g_real.imu.wx_0").floatValue();
                float floatValue2 = d.valueOf("g_real.imu.wy_0").floatValue();
                float floatValue3 = d.valueOf("g_real.imu.wz_0").floatValue();
                this.a.Q = d.valueOf("g_real.imu.ax_0").floatValue();
                this.a.R = d.valueOf("g_real.imu.ay_0").floatValue();
                this.a.S = d.valueOf("g_real.imu.az_0").floatValue();
                short shortValue = d.valueOf("g_real.imu.mx_0").shortValue();
                short shortValue2 = d.valueOf("g_real.imu.my_0").shortValue();
                short shortValue3 = d.valueOf("g_real.imu.mz_0").shortValue();
                this.a.N = (float) Math.sqrt((double) (((floatValue * floatValue) + (floatValue2 * floatValue2)) + (floatValue3 * floatValue3)));
                this.a.f.setText(String.format(this.a.t, new Object[]{Float.valueOf(this.a.N)}));
                this.a.O = (float) Math.sqrt((double) (((this.a.Q * this.a.Q) + (this.a.R * this.a.R)) + (this.a.S * this.a.S)));
                this.a.l.setText(String.format(this.a.t, new Object[]{Float.valueOf(this.a.O)}));
                this.a.P = (float) Math.sqrt((double) (((shortValue * shortValue) + (shortValue2 * shortValue2)) + (shortValue3 * shortValue3)));
                this.a.p.setText(String.format(this.a.t, new Object[]{Float.valueOf(this.a.P)}));
                if (this.a.w) {
                    this.a.y.add(Float.valueOf(this.a.N));
                    this.a.z.add(Float.valueOf(this.a.O));
                    if (this.a.y.size() == 20) {
                        this.a.w = false;
                        this.a.d();
                        this.a.y.clear();
                        this.a.z.clear();
                    }
                }
                this.a.k();
            }
        });
    }

    private void j() {
        int i = 0;
        this.L = d.read(this.L.name);
        byte byteValue = d.valueOf("imu_app_temp_cali.state_0").byteValue();
        int intValue = this.L.value.intValue();
        if (byteValue != (byte) 2 && byteValue != (byte) 1 && byteValue != dji.setting.ui.flyc.imu.b.a.eZ_ && byteValue != dji.setting.ui.flyc.imu.b.a.fa_ && byteValue != (byte) -10) {
            return;
        }
        if (byteValue == dji.setting.ui.flyc.imu.b.a.fa_) {
            this.V = false;
            this.W = 0;
            this.aa = 0.0f;
            return;
        }
        if (this.V) {
            if (byteValue == (byte) 1) {
                i = ((int) ((((float) intValue) * 0.8f) * dji.pilot.visual.a.d.c)) + 50;
            } else if (byteValue == (byte) 2) {
                i = ((int) ((((float) intValue) * 0.2f) * dji.pilot.visual.a.d.c)) + 90;
            } else if (byteValue == (byte) -10) {
                if (this.W == 0) {
                    this.aa = d.valueOf("IMU_Data.gyro_tempX_0").floatValue();
                }
                this.W++;
                i = this.W / 5;
                if (this.W == 240) {
                    a(1);
                } else if (this.W < 240 && this.W % 10 == 0) {
                    float floatValue = d.valueOf("IMU_Data.gyro_tempX_0").floatValue();
                    if (((double) Math.abs(floatValue - this.aa)) < 0.3d) {
                        a(1);
                        this.W = 251;
                    } else {
                        this.aa = floatValue;
                    }
                }
            }
        } else if (!this.v) {
            i = intValue;
        } else if (byteValue == (byte) 1) {
            i = (int) (((float) intValue) * 0.8f);
        } else if (byteValue == (byte) 2) {
            i = ((int) (((float) intValue) * 0.2f)) + 80;
        }
        this.r.setProgress(i);
        this.u.setText(i + "%");
    }

    private void k() {
        int i;
        boolean z = true;
        byte byteValue = d.valueOf("imu_app_temp_cali.state_0").byteValue();
        if (!this.v) {
            int intValue = this.L.value.intValue();
            this.r.setProgress(intValue);
            this.u.setText(intValue + "%");
        }
        if (byteValue == (byte) 2 || byteValue == (byte) 1 || byteValue == dji.setting.ui.flyc.imu.b.a.eZ_ || byteValue == (byte) -10) {
            if (this.U == null && this.v) {
                e();
            }
            this.q.setEnabled(false);
            this.x.setEnabled(false);
        } else {
            if (this.U != null && this.v) {
                f();
                this.r.setProgress(100);
                this.u.setText("100%");
            }
            this.q.setEnabled(true);
            this.x.setEnabled(true);
        }
        if (byteValue == (byte) 2) {
            i = R.string.setting_ui_flyc_sensor_adv_calibrating;
        } else if (byteValue == (byte) 1) {
            i = R.string.setting_ui_flyc_sensor_adv_calibrating;
        } else if (byteValue == dji.setting.ui.flyc.imu.b.a.fa_) {
            i = R.string.setting_ui_flyc_sensor_calib_ok;
        } else if (byteValue == dji.setting.ui.flyc.imu.b.a.eZ_) {
            i = R.string.setting_ui_flyc_sensor_calib_ok;
            z = false;
        } else if (byteValue == (byte) -10) {
            if (this.v) {
                this.V = true;
            }
            i = R.string.setting_ui_flyc_sensor_adv_calibrating;
        } else if (byteValue == (byte) -11) {
            i = R.string.setting_ui_flyc_sensor_ing_low_temp;
        } else if (byteValue == (byte) -12) {
            i = R.string.setting_ui_flyc_sensor_ing_high_temp;
            z = false;
        } else if (byteValue == (byte) -13) {
            i = R.string.setting_ui_flyc_sensor_low_temp;
            z = false;
        } else if (byteValue <= Code.FIXEXT8 && byteValue >= (byte) -30) {
            i = R.string.setting_ui_flyc_sensor_error_flash;
        } else if (byteValue <= Code.INT8 && byteValue >= Code.FIXEXT2) {
            i = R.string.setting_ui_flyc_sensor_error_move;
        } else if (byteValue < (byte) 0 || byteValue == (byte) 1 || byteValue == (byte) 2 || byteValue == dji.setting.ui.flyc.imu.b.a.fa_ || byteValue == dji.setting.ui.flyc.imu.b.a.eZ_) {
            i = R.string.setting_ui_flyc_sensor_error_unknow;
        } else {
            z = false;
            i = 0;
        }
        if (z) {
            this.s.setText(i);
            this.s.setVisibility(0);
            return;
        }
        this.s.setVisibility(8);
    }
}
