package dji.pilot.fpv.inner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataBatteryActiveStatus;
import dji.midware.data.model.P3.DataCameraActiveStatus;
import dji.midware.data.model.P3.DataCameraFormatSDCard;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraLoadParams;
import dji.midware.data.model.P3.DataCameraSaveParams.USER;
import dji.midware.data.model.P3.DataCameraSetExposureMode;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataCameraSetShutterSpeed;
import dji.midware.data.model.P3.DataFlycActiveStatus;
import dji.midware.data.model.P3.DataFlycFormatDataRecorder;
import dji.midware.data.model.P3.DataFlycFormatDataRecorder.FORMAT_ACTION;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycGetPushGpsSnr;
import dji.midware.data.model.P3.DataFlycResetParamNew;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataFlycSetPushGpsSnr;
import dji.midware.data.model.P3.DataFlycSetReadFlyDataMode;
import dji.midware.data.model.P3.DataGimbalControl;
import dji.midware.data.model.P3.DataOsdActiveStatus;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataOsdSetConfig;
import dji.midware.data.model.P3.DataRcGetControlMode;
import dji.midware.data.model.P3.DataRcSetControlMode;
import dji.midware.data.model.P3.DataRcSetControlMode.ControlMode;
import dji.midware.data.model.P3.DataRcSetCustomFuction;
import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot.publics.widget.DJIEditText;
import dji.pilot.publics.widget.DJISwitch;
import dji.pilot2.upgrade.rollback.P3CFactoryActivity;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Calendar;

public class a extends dji.pilot.fpv.activity.a {
    private static final int T = 4;
    private static final int aA = 2048;
    private static final int aB = 2304;
    private static final int aC = 4096;
    private static final int aD = 1;
    private static final int aE = 0;
    private static final int aF = 0;
    private static final int aG = 1;
    private static final int aH = 2;
    private static final int aI = 3;
    private static final int ag = 256;
    private static final int ah = 257;
    private static final int ai = 258;
    private static final int aj = 259;
    private static final int ak = 260;
    private static final int al = 261;
    private static final int am = 262;
    private static final int an = 263;
    private static final int ao = 264;
    private static final int ap = 265;
    private static final int aq = 512;
    private static final int ar = 768;
    private static final int as = 769;
    private static final int at = 770;
    private static final int au = 771;
    private static final int av = 772;
    private static final int aw = 1024;
    private static final int ax = 1280;
    private static final int ay = 1536;
    private static final int az = 1792;
    private static final String h = "g_config.gps_cfg.gps_enable_0";
    private static final String i = "g_config.control.multi_control_mode_enable_0";
    private static final String j = "g_config.fdi_open.ctrl_vibrate_fdi_open_0";
    private static final String k = "g_config.device.is_locked_0";
    private static final String[] l = new String[]{"g_config.advanced_function.avoid_ground_enabled_0", "g_config.advanced_function.height_limit_enabled_0", "g_config.advanced_function.radius_limit_enabled_0", "g_config.flying_limit.max_radius_0", "g_config.flying_limit.max_height_0", "g_config.flying_limit.min_height_0", dji.midware.data.params.P3.a.B, h, "g_config.go_home.fixed_go_home_altitude_0", i, j};
    private static final String[] m = new String[]{"g_config.control.basic_pitch_0", "g_config.control.basic_roll_0", "g_config.control.basic_tail_0", "g_config.control.tilt_atti_gain_0", "g_config.control.tilt_gyro_gain_0", "g_config.control.atti_vertical_0", "g_config.voltage2.level_1_voltage_0", "g_config.voltage2.level_2_voltage_0"};
    private DJITextView A = null;
    private DJIRelativeLayout B = null;
    private DJISnrView C = null;
    private DJITextView D = null;
    private DJITextView E = null;
    private DJIRelativeLayout F = null;
    private DJISnrView G = null;
    private DJITextView H = null;
    private DJITextView I = null;
    private OnClickListener J = null;
    private OnCheckedChangeListener K = null;
    private b L = null;
    private boolean Q = false;
    private boolean R = false;
    private boolean S = false;
    private final int[] U = new int[32];
    private final int[] V = new int[32];
    private boolean W = false;
    private final a X = new a();
    private int Y = 0;
    private dji.pilot.publics.widget.b Z = null;
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private int ae;
    private int af;
    private DJITextView n = null;
    private DJITextView o = null;
    private DJITextView p = null;
    private DJITextView q = null;
    private DJITextView r = null;
    private DJISwitch s = null;
    private DJITextView t = null;
    private DJITextView u = null;
    private DJITextView v = null;
    private DJISwitch w = null;
    private DJISwitch x = null;
    private DJIEditText y = null;
    private DJITextView z = null;

    private static final class a {
        public int a;
        public int b;
        public int c;

        private a() {
            this.a = 0;
            this.b = 0;
            this.c = 0;
        }
    }

    private static final class b extends Handler {
        private final WeakReference<a> a;

        public b(a aVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(aVar);
        }

        public void handleMessage(Message message) {
            boolean z = true;
            a aVar = (a) this.a.get();
            if (aVar != null) {
                int i;
                switch (message.what) {
                    case 256:
                        if (message.arg1 == 1) {
                            aVar.y();
                            return;
                        } else {
                            aVar.b((int) R.string.inner_tool_initall_fail);
                            return;
                        }
                    case 257:
                        if (message.arg1 == 1) {
                            aVar.z();
                            return;
                        } else {
                            aVar.b((int) R.string.inner_tool_initall_fail);
                            return;
                        }
                    case 258:
                        if (message.arg1 == 1) {
                            aVar.A();
                            return;
                        } else {
                            aVar.b((int) R.string.inner_tool_initall_fail);
                            return;
                        }
                    case 259:
                        if (message.arg1 == 1) {
                            aVar.x();
                            return;
                        } else {
                            aVar.b((int) R.string.inner_tool_initall_fail);
                            return;
                        }
                    case 260:
                        if (message.arg1 != 1) {
                            aVar.b((int) R.string.inner_tool_initall_fail);
                            return;
                        } else if (dji.pilot.fpv.d.b.m(i.getInstance().c())) {
                            aVar.g(false);
                            return;
                        } else {
                            aVar.B();
                            return;
                        }
                    case 261:
                        if (message.arg1 == 1) {
                            aVar.B();
                            return;
                        } else {
                            aVar.b((int) R.string.inner_tool_initall_fail);
                            return;
                        }
                    case 262:
                        if (message.arg1 != 1) {
                            aVar.b((int) R.string.inner_tool_initall_fail);
                            return;
                        } else if (dji.pilot.fpv.d.b.l(i.getInstance().c())) {
                            aVar.E();
                            return;
                        } else {
                            aVar.C();
                            return;
                        }
                    case 263:
                        if (message.arg1 == 1) {
                            aVar.E();
                            return;
                        } else {
                            aVar.b((int) R.string.inner_tool_initall_fail);
                            return;
                        }
                    case 264:
                        if (message.arg1 != 1) {
                            aVar.b((int) R.string.inner_tool_initall_fail);
                            return;
                        } else if (message.arg2 == 0) {
                            aVar.J();
                            return;
                        } else if (message.arg2 == 1) {
                            aVar.L();
                            return;
                        } else if (message.arg2 == 2) {
                            aVar.D();
                            return;
                        } else {
                            return;
                        }
                    case a.ap /*265*/:
                        if (message.arg1 == 1) {
                            aVar.b((int) R.string.inner_tool_initall_success);
                            return;
                        } else {
                            aVar.b((int) R.string.inner_tool_initall_success);
                            return;
                        }
                    case 512:
                        if (message.arg1 == 1) {
                            aVar.b((int) R.string.inner_tool_active_success);
                            return;
                        } else {
                            aVar.b((int) R.string.inner_tool_active_fail);
                            return;
                        }
                    case a.ar /*768*/:
                        if (message.arg1 == 1) {
                            aVar.P();
                            return;
                        } else {
                            aVar.b((int) R.string.inner_tool_water_fail);
                            return;
                        }
                    case a.as /*769*/:
                        if (message.arg1 == 1) {
                            aVar.R();
                            return;
                        } else {
                            aVar.b((int) R.string.inner_tool_water_fail);
                            return;
                        }
                    case a.at /*770*/:
                        if (message.arg1 != 1) {
                            aVar.b((int) R.string.inner_tool_water_fail);
                            return;
                        } else if (dji.pilot.fpv.d.b.m(i.getInstance().c())) {
                            aVar.g(true);
                            return;
                        } else {
                            aVar.S();
                            aVar.b((int) R.string.inner_tool_water_success);
                            return;
                        }
                    case a.au /*771*/:
                        if (message.arg1 == 1) {
                            aVar.S();
                            aVar.b((int) R.string.inner_tool_water_success);
                            return;
                        }
                        aVar.b((int) R.string.inner_tool_water_fail);
                        return;
                    case a.av /*772*/:
                        if (message.arg1 == 1) {
                            aVar.Q();
                            return;
                        } else {
                            aVar.b((int) R.string.inner_tool_water_fail);
                            return;
                        }
                    case 1024:
                        i = message.arg1;
                        if (message.arg2 != 1) {
                            z = false;
                        }
                        aVar.c(i, z);
                        return;
                    case 1280:
                        i = message.arg1;
                        if (message.arg2 != 1) {
                            z = false;
                        }
                        aVar.b(i, z);
                        return;
                    case a.ay /*1536*/:
                        aVar.i();
                        return;
                    case a.az /*1792*/:
                        i = message.arg1;
                        if (message.arg2 != 1) {
                            z = false;
                        }
                        aVar.a(i, z);
                        return;
                    case 2048:
                        if (message.arg1 == 1) {
                            aVar.b((int) R.string.inner_tool_unlock_success);
                            return;
                        } else {
                            aVar.b((int) R.string.inner_tool_unlock_fail);
                            return;
                        }
                    case a.aB /*2304*/:
                        if (message.arg2 == 0) {
                            if (message.arg1 == 1) {
                                aVar.b((int) R.string.fpv_gensetting_format_sdcard_success);
                            } else {
                                aVar.b((int) R.string.fpv_gensetting_format_sdcard_fail);
                            }
                        }
                        if (message.arg2 == 1) {
                            int i2;
                            a F = aVar.X;
                            if (message.arg1 == 1) {
                                i2 = 1;
                            } else {
                                i2 = -1;
                            }
                            F.a = i2;
                            aVar.a(true);
                            return;
                        }
                        return;
                    case 4096:
                        if (message.arg1 == 1) {
                            aVar.b((int) R.string.fpv_flyc_format_recorder_success);
                            return;
                        }
                        aVar.b((int) R.string.fpv_flyc_format_recorder_fail);
                        if (message.arg2 == 1) {
                            aVar.X.b = -1;
                            aVar.w();
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public a(Context context) {
        super(context);
        j();
    }

    protected void onStart() {
        super.onStart();
        if (DataOsdGetPushHome.getInstance().isGetted()) {
            int dataRecorderStatus = DataOsdGetPushHome.getInstance().getDataRecorderStatus();
            if (dataRecorderStatus == 1) {
                this.n.setEnabled(false);
                this.n.setText(R.string.fpv_flyc_formating_recorder);
            } else {
                this.n.setEnabled(true);
                this.n.setText(R.string.fpv_flyc_format_recorder);
            }
            this.Y = dataRecorderStatus;
        } else {
            this.n.setEnabled(true);
        }
        this.t.setEnabled(true);
        c.a().a(this);
        f(false);
        h();
    }

    private void h() {
        new DataFlycGetParams().setInfos(new String[]{h, j}).start(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.L.obtainMessage(a.ay, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.L.obtainMessage(a.ay, 0, 0).sendToTarget();
            }
        });
    }

    private void i() {
        boolean z = true;
        boolean z2 = dji.midware.data.manager.P3.d.read(h).value.intValue() == 1;
        if (this.Q != z2) {
            this.Q = z2;
            this.s.setChecked(z2);
        }
        if (dji.midware.data.manager.P3.d.read(j).value.intValue() != 1) {
            z = false;
        }
        if (this.S != z) {
            this.S = z;
            this.w.setChecked(z);
        }
    }

    protected void onStop() {
        f(false);
        c.a().d(this);
        super.onStop();
    }

    private void a(int[] iArr, int[] iArr2) {
        int i = 0;
        while (i < iArr.length && i < iArr2.length) {
            iArr2[i] = iArr[i];
            i++;
        }
    }

    private int a(int i, int i2) {
        try {
            int parseInt = Integer.parseInt(this.y.getText().toString());
            if (parseInt <= 0 || parseInt >= i) {
                return i2;
            }
            return parseInt;
        } catch (Exception e) {
            return i2;
        }
    }

    private float a(int[] iArr) {
        int a = a(iArr.length, 4);
        int i = 0;
        int length = iArr.length - 1;
        while (length >= 0 && length >= iArr.length - a) {
            i += iArr[length];
            length--;
        }
        return ((float) i) / ((float) a);
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.b) {
            h();
        }
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
        int dataRecorderStatus = dataOsdGetPushHome.getDataRecorderStatus();
        if (this.Y != dataRecorderStatus) {
            if (dataRecorderStatus == 1) {
                this.n.setEnabled(false);
                this.n.setText(R.string.fpv_flyc_formating_recorder);
            } else {
                this.n.setEnabled(true);
                this.n.setText(R.string.fpv_flyc_format_recorder);
            }
            if (this.Y == 1) {
                if (dataRecorderStatus == 0) {
                    b((int) R.string.fpv_flyc_format_recorder_success);
                    if (this.X.a != 0) {
                        this.X.b = 1;
                        w();
                    }
                } else if (dataRecorderStatus == 2) {
                    b((int) R.string.fpv_flyc_format_recorder_fail);
                    if (this.X.a != 0) {
                        this.X.b = -1;
                        w();
                    }
                }
            }
            this.Y = dataRecorderStatus;
        }
    }

    public void onEventMainThread(DataFlycGetPushGpsSnr dataFlycGetPushGpsSnr) {
        if (this.B.getVisibility() == 0) {
            int[] glonassValues = dataFlycGetPushGpsSnr.getGlonassValues();
            this.G.addRecord(glonassValues);
            this.I.setText(String.valueOf(dataFlycGetPushGpsSnr.getGlonassSnrUsed()));
            a(glonassValues, this.V);
            Arrays.sort(this.V);
            this.H.setText(String.format("%.1f", new Object[]{Float.valueOf(a(this.V))}));
            glonassValues = dataFlycGetPushGpsSnr.getSnrValues();
            this.C.addRecord(glonassValues);
            this.E.setText(String.valueOf(dataFlycGetPushGpsSnr.getSnrUsed()));
            a(glonassValues, this.U);
            Arrays.sort(this.U);
            this.D.setText(String.format("%.1f", new Object[]{Float.valueOf(a(this.U))}));
        }
    }

    private void j() {
        r();
        v();
    }

    private void k() {
        dji.pilot.publics.widget.b a = dji.pilot.publics.widget.b.a(this.N, (int) R.string.app_tip, (int) R.string.inner_tool_active_tip, (int) R.string.btn_dlg_no, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }, (int) R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                this.a.F();
            }
        });
        a.setCancelable(true);
        a.setCanceledOnTouchOutside(true);
        a.show();
    }

    private void l() {
        dji.pilot.publics.widget.b a = dji.pilot.publics.widget.b.a(this.N, (int) R.string.app_tip, (int) R.string.inner_tool_water_tip, (int) R.string.btn_dlg_no, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }, (int) R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                this.a.O();
            }
        });
        a.setCancelable(true);
        a.setCanceledOnTouchOutside(true);
        a.show();
    }

    private void p() {
        if (this.Z == null) {
            this.Z = dji.pilot.publics.widget.b.a(this.N, (int) R.string.app_tip, (int) R.string.inner_tool_reset_tip, (int) R.string.btn_dlg_no, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (!this.a.W) {
                        dialogInterface.dismiss();
                    }
                }
            }, (int) R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (!this.a.W) {
                        this.a.W = true;
                        this.a.t.setEnabled(false);
                        this.a.b(true);
                        this.a.Z.b();
                        this.a.Z.d((int) R.string.inner_tool_waiting);
                        this.a.Z.setCancelable(false);
                        this.a.Z.setCanceledOnTouchOutside(false);
                    }
                }
            });
            this.Z.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    this.a.u();
                }
            });
            this.Z.f();
        }
        if (!this.Z.isShowing()) {
            this.Z.d((int) R.string.btn_dlg_no);
            this.Z.e(R.string.btn_dlg_yes);
            this.Z.b((int) R.string.inner_tool_reset_tip);
            this.Z.setCancelable(true);
            this.Z.setCanceledOnTouchOutside(true);
            this.Z.show();
        }
    }

    private void q() {
        if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
            Toast.makeText(this.N, R.string.mc_switch_sd_mode_error_motor_up, 1).show();
        } else {
            dji.pilot.publics.widget.b.a(getContext(), getContext().getString(R.string.app_tip), getContext().getString(R.string.mc_switch_sd_mode_confirm_msg), getContext().getString(R.string.app_cancel), new DialogInterface.OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }, getContext().getString(R.string.app_enter), new DialogInterface.OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    DataFlycSetReadFlyDataMode.getInstance().start(new d(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            c.a().e(dji.setting.ui.flyc.SdModeView.a.SUCCESS);
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            c.a().e(dji.setting.ui.flyc.SdModeView.a.FAILED);
                        }
                    });
                }
            }).show();
        }
    }

    private void r() {
        this.L = new b(this);
        this.J = new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.axc) {
                    this.a.k();
                } else if (id == R.id.axb) {
                    this.a.l();
                } else if (id == R.id.axj) {
                    this.a.p();
                } else if (id == R.id.axo) {
                    this.a.q();
                } else if (id == R.id.axd) {
                    this.a.c(false);
                } else if (id == R.id.axg) {
                    this.a.b(false);
                } else if (id == R.id.ay1) {
                    this.a.a(false);
                } else if (id == R.id.axe) {
                    this.a.s();
                } else if (id == R.id.axf) {
                    this.a.t();
                }
            }
        };
        this.K = new OnCheckedChangeListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (compoundButton == this.a.s) {
                    this.a.e(z);
                } else if (compoundButton == this.a.x) {
                    this.a.f(z);
                } else if (compoundButton == this.a.w) {
                    this.a.d(z);
                }
            }
        };
    }

    private void s() {
        if (!ServiceManager.getInstance().isConnected()) {
            Toast.makeText(getContext().getApplicationContext(), R.string.fpv_tip_disconnect, 0).show();
        } else if (i.getInstance().c().isFromWifi()) {
            this.N.startActivity(new Intent(this.N, P3CFactoryActivity.class));
        } else {
            Toast.makeText(getContext().getApplicationContext(), R.string.inner_tool_cant_do, 0).show();
        }
    }

    private void t() {
        if (!ServiceManager.getInstance().isConnected()) {
            Toast.makeText(getContext().getApplicationContext(), R.string.fpv_tip_disconnect, 0).show();
        } else if (dji.pilot.publics.control.rc.b.a(i.getInstance().a())) {
            this.g.createStageView(R.layout.inner_tool_rcupgradeview, R.string.inner_tool_rc_upgrade, true);
        } else {
            Toast.makeText(getContext().getApplicationContext(), R.string.inner_tool_cant_do, 0).show();
        }
    }

    private void a(final boolean z) {
        if (z) {
            a(0);
        }
        DataFlycFormatDataRecorder.getInstance().setAction(FORMAT_ACTION.FORMAT).start(new d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                int i;
                b a = this.b.L;
                if (z) {
                    i = 1;
                } else {
                    i = 0;
                }
                a.obtainMessage(4096, 0, i).sendToTarget();
            }
        });
    }

    private void u() {
        this.X.b = 0;
        this.X.a = 0;
        this.X.c = 0;
    }

    private void a(int i) {
        if (this.X.a == 0) {
            this.u.setText(R.string.inner_tool_formating_sdcard);
            this.Z.b(this.N.getString(R.string.inner_tool_formating_sdcard));
        } else if (this.X.b == 0) {
            this.u.setText(R.string.fpv_flyc_formating_recorder);
            r0 = new StringBuilder();
            if (this.X.a == 1) {
                r0.append(this.N.getString(R.string.fpv_gensetting_format_sdcard_success));
            } else {
                r0.append(this.N.getString(R.string.fpv_gensetting_format_sdcard_fail));
            }
            r0.append("\n").append(this.N.getString(R.string.fpv_flyc_formating_recorder));
            this.Z.b(r0.toString());
        } else if (this.X.c == 0) {
            this.u.setText(i);
            r0 = new StringBuilder();
            if (this.X.a == 1) {
                r0.append(this.N.getString(R.string.fpv_gensetting_format_sdcard_success));
            } else {
                r0.append(this.N.getString(R.string.fpv_gensetting_format_sdcard_fail));
            }
            if (this.X.b == 1) {
                r0.append("\n").append(this.N.getString(R.string.fpv_flyc_format_recorder_success));
            } else {
                r0.append("\n").append(this.N.getString(R.string.fpv_flyc_format_recorder_fail));
            }
            r0.append("\n").append(this.N.getString(i));
            this.Z.b(r0.toString());
        }
    }

    private void b(final boolean z) {
        if (z) {
            u();
            a(0);
        }
        DataCameraFormatSDCard.getInstance().start(new d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                this.b.L.obtainMessage(a.aB, 1, z ? 1 : 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                int i;
                b a = this.b.L;
                if (z) {
                    i = 1;
                } else {
                    i = 0;
                }
                a.obtainMessage(a.aB, 0, i).sendToTarget();
            }
        });
    }

    private void c(boolean z) {
        int i;
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        String str = k;
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        dataFlycSetParams.a(str, Integer.valueOf(i)).start(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.L.obtainMessage(2048, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.L.obtainMessage(2048, 0, 0).sendToTarget();
            }
        });
    }

    private void d(final boolean z) {
        if (this.S != z) {
            int i;
            DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
            String str = j;
            if (z) {
                i = 1;
            } else {
                i = 0;
            }
            dataFlycSetParams.a(str, Integer.valueOf(i)).start(new d(this) {
                final /* synthetic */ a b;

                public void onSuccess(Object obj) {
                    this.b.L.obtainMessage(a.az, 1, z ? 1 : 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    int i;
                    b a = this.b.L;
                    if (z) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    a.obtainMessage(a.az, 0, i).sendToTarget();
                }
            });
        }
    }

    private void e(final boolean z) {
        int i = 1;
        DJILogHelper.getInstance().LOGD("", "mbGpsEnable=" + this.Q + " isChecked=" + z, false, true);
        if (this.Q != z) {
            DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
            String str = h;
            if (!z) {
                i = 0;
            }
            dataFlycSetParams.a(str, Integer.valueOf(i)).start(new d(this) {
                final /* synthetic */ a b;

                public void onSuccess(Object obj) {
                    int i = 0;
                    DJILogHelper.getInstance().LOGD("", "mbGpsEnable=onSuccess", false, true);
                    b a = this.b.L;
                    if (z) {
                        i = 1;
                    }
                    a.obtainMessage(1280, 1, i).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    int i = 1;
                    DJILogHelper.getInstance().LOGD("", "mbGpsEnable=" + aVar, false, true);
                    b a = this.b.L;
                    if (!z) {
                        i = 0;
                    }
                    a.obtainMessage(1280, 0, i).sendToTarget();
                }
            });
        }
    }

    private void f(final boolean z) {
        if (this.R != z) {
            DataFlycSetPushGpsSnr dataFlycSetPushGpsSnr = new DataFlycSetPushGpsSnr();
            dataFlycSetPushGpsSnr.a(z);
            dataFlycSetPushGpsSnr.start(new d(this) {
                final /* synthetic */ a b;

                public void onSuccess(Object obj) {
                    this.b.L.obtainMessage(1024, 1, z ? 1 : 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    int i;
                    b a = this.b.L;
                    if (z) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    a.obtainMessage(1024, 0, i).sendToTarget();
                }
            });
        }
    }

    private void a(int i, boolean z) {
        boolean z2 = true;
        DJISwitch dJISwitch;
        if (z) {
            if (i == 1) {
                this.S = z;
                this.w.setChecked(z);
                return;
            }
            dJISwitch = this.w;
            if (z) {
                z2 = false;
            }
            dJISwitch.setChecked(z2);
        } else if (i == 1) {
            this.S = z;
            this.w.setChecked(z);
        } else {
            dJISwitch = this.w;
            if (z) {
                z2 = false;
            }
            dJISwitch.setChecked(z2);
        }
    }

    private void b(int i, boolean z) {
        boolean z2 = true;
        DJISwitch dJISwitch;
        if (z) {
            if (i == 1) {
                this.Q = z;
                this.s.setChecked(z);
                return;
            }
            dJISwitch = this.s;
            if (z) {
                z2 = false;
            }
            dJISwitch.setChecked(z2);
        } else if (i == 1) {
            this.Q = z;
            this.s.setChecked(z);
        } else {
            dJISwitch = this.s;
            if (z) {
                z2 = false;
            }
            dJISwitch.setChecked(z2);
        }
    }

    private void c(int i, boolean z) {
        boolean z2 = true;
        DJISwitch dJISwitch;
        if (z) {
            if (i == 1) {
                this.x.setChecked(z);
                this.R = z;
                this.B.show();
                this.F.show();
                if (DataFlycGetPushGpsSnr.getInstance().isGetted()) {
                    onEventMainThread(DataFlycGetPushGpsSnr.getInstance());
                    return;
                }
                return;
            }
            dJISwitch = this.x;
            if (z) {
                z2 = false;
            }
            dJISwitch.setChecked(z2);
        } else if (i == 1) {
            this.x.setChecked(z);
            this.R = z;
            this.B.go();
            this.F.go();
        } else {
            dJISwitch = this.x;
            if (z) {
                z2 = false;
            }
            dJISwitch.setChecked(z2);
        }
    }

    private void v() {
        this.g.createStageView(R.layout.inner_tool_view, R.string.inner_tool_title, false);
        this.n = (DJITextView) findViewById(R.id.ay1);
        this.o = (DJITextView) findViewById(R.id.axg);
        this.p = (DJITextView) findViewById(R.id.axd);
        this.q = (DJITextView) findViewById(R.id.axc);
        this.r = (DJITextView) findViewById(R.id.axb);
        this.s = (DJISwitch) findViewById(R.id.axi);
        this.t = (DJITextView) findViewById(R.id.axj);
        this.u = (DJITextView) findViewById(R.id.axl);
        this.v = (DJITextView) findViewById(R.id.axo);
        this.w = (DJISwitch) findViewById(R.id.axn);
        this.x = (DJISwitch) findViewById(R.id.axq);
        this.y = (DJIEditText) findViewById(R.id.axs);
        this.B = (DJIRelativeLayout) findViewById(R.id.axt);
        this.C = (DJISnrView) findViewById(R.id.axu);
        this.D = (DJITextView) findViewById(R.id.axv);
        this.E = (DJITextView) findViewById(R.id.axw);
        this.F = (DJIRelativeLayout) findViewById(R.id.axx);
        this.G = (DJISnrView) findViewById(R.id.axy);
        this.H = (DJITextView) findViewById(R.id.axz);
        this.I = (DJITextView) findViewById(R.id.ay0);
        this.z = (DJITextView) findViewById(R.id.axe);
        this.A = (DJITextView) findViewById(R.id.axf);
        this.n.setOnClickListener(this.J);
        this.o.setOnClickListener(this.J);
        this.p.setOnClickListener(this.J);
        this.q.setOnClickListener(this.J);
        this.r.setOnClickListener(this.J);
        this.t.setOnClickListener(this.J);
        this.v.setOnClickListener(this.J);
        this.z.setOnClickListener(this.J);
        this.A.setOnClickListener(this.J);
        this.w.setOnCheckedChangeListener(this.K);
        this.s.setOnCheckedChangeListener(this.K);
        this.x.setOnCheckedChangeListener(this.K);
        this.y.setText(String.valueOf(4));
    }

    private void w() {
        this.u.setText(R.string.inner_tool_reset_mcsetting);
        a((int) R.string.inner_tool_reset_mcsetting);
        new DataFlycResetParamNew().setInfos(l).start(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.L.obtainMessage(256, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.L.obtainMessage(256, 0, 0).sendToTarget();
            }
        });
    }

    private void x() {
        this.u.setText(R.string.inner_tool_reset_mcgain);
        a((int) R.string.inner_tool_reset_mcgain);
        new DataFlycResetParamNew().setInfos(m).start(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.L.obtainMessage(260, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.L.obtainMessage(260, 0, 0).sendToTarget();
            }
        });
    }

    private void y() {
        this.u.setText(R.string.inner_tool_reset_fpamode);
        a((int) R.string.inner_tool_reset_fpamode);
        new DataFlycSetParams().a(i, Integer.valueOf(0)).start(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.L.obtainMessage(257, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.L.obtainMessage(257, 0, 0).sendToTarget();
            }
        });
    }

    private void z() {
        this.u.setText(R.string.inner_tool_reset_iocmode);
        a((int) R.string.inner_tool_reset_iocmode);
        DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.IOCClose).start(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.L.obtainMessage(258, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.L.obtainMessage(258, 0, 0).sendToTarget();
            }
        });
    }

    private void A() {
        this.u.setText(R.string.inner_tool_reset_gpsenable);
        a((int) R.string.inner_tool_reset_gpsenable);
        new DataFlycSetParams().a(h, Integer.valueOf(1)).start(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.L.obtainMessage(259, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.L.obtainMessage(259, 0, 0).sendToTarget();
            }
        });
    }

    private void g(final boolean z) {
        if (!z) {
            this.u.setText(R.string.inner_tool_reset_rccustom);
            a((int) R.string.inner_tool_reset_rccustom);
        }
        DataRcSetCustomFuction.getInstance().a(DJICustomType.b.a()).b(DJICustomType.c.a()).start(new d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                if (z) {
                    this.b.L.obtainMessage(a.au, 1, 0).sendToTarget();
                } else {
                    this.b.L.obtainMessage(261, 1, 0).sendToTarget();
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (z) {
                    this.b.L.obtainMessage(a.au, 0, 0).sendToTarget();
                } else {
                    this.b.L.obtainMessage(261, 0, 0).sendToTarget();
                }
            }
        });
    }

    private void B() {
        try {
            this.u.setText(R.string.inner_tool_reset_rcmode);
            a((int) R.string.inner_tool_reset_rcmode);
            DataRcSetControlMode.getInstance().a(ControlMode.b).a(DataRcGetControlMode.getInstance().getChannels()).start(new d(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.L.obtainMessage(262, 1, 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.L.obtainMessage(262, 0, 0).sendToTarget();
                }
            });
        } catch (NumberFormatException e) {
            this.L.obtainMessage(262, 0, 0).sendToTarget();
        }
    }

    private void C() {
        this.u.setText(R.string.inner_tool_reset_ofdm);
        a((int) R.string.inner_tool_reset_ofdm);
        DataOsdSetConfig.getInstance().a(true).start(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.L.obtainMessage(263, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.L.obtainMessage(263, 0, 0).sendToTarget();
            }
        });
    }

    private void D() {
        this.u.setText(R.string.inner_tool_reset_camera);
        a((int) R.string.inner_tool_reset_camera);
        DataCameraLoadParams.getInstance().setMode(USER.DEFAULT).start(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.L.obtainMessage(a.ap, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.L.obtainMessage(a.ap, 1, 0).sendToTarget();
            }
        });
    }

    private void E() {
        this.u.setText(R.string.inner_tool_reset_inactive);
        a((int) R.string.inner_tool_reset_inactive);
        Calendar instance = Calendar.getInstance();
        instance.clear();
        this.aa = instance.get(1);
        this.ab = instance.get(2) + 1;
        this.ac = instance.get(5);
        this.ad = instance.get(11);
        this.ae = instance.get(12);
        this.af = instance.get(13);
        H();
    }

    private void F() {
        Calendar instance = Calendar.getInstance();
        instance.clear();
        this.aa = instance.get(1);
        this.ab = instance.get(2) + 1;
        this.ac = instance.get(5);
        this.ad = instance.get(11);
        this.ae = instance.get(12);
        this.af = instance.get(13);
        G();
    }

    private void G() {
        h(true);
    }

    private void H() {
        h(false);
    }

    private void h(final boolean z) {
        new DataFlycActiveStatus().setType(dji.midware.data.model.b.a.b.c).setActiveStatus(z).setSN(DJIGlobalService.f).setYear(this.aa).setMonth(this.ab).setDay(this.ac).setHour(this.ad).setMin(this.ae).setSecond(this.af).setVersion(DataFlycActiveStatus.getInstance().getVersion()).start(new d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                if (z) {
                    this.b.L.obtainMessage(512, 1, 0).sendToTarget();
                } else {
                    this.b.L.obtainMessage(264, 1, 0).sendToTarget();
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (z) {
                    this.b.L.obtainMessage(512, 0, 0).sendToTarget();
                } else {
                    this.b.L.obtainMessage(264, 0, 0).sendToTarget();
                }
            }
        });
    }

    private void I() {
        i(true);
    }

    private void J() {
        i(false);
    }

    private void i(final boolean z) {
        new DataCameraActiveStatus().setType(dji.midware.data.model.b.a.b.c).setActiveStatus(z).setSN(DJIGlobalService.g).setYear(this.aa).setMonth(this.ab).setDay(this.ac).setHour(this.ad).setMin(this.ae).setSecond(this.af).setVersion(DataCameraActiveStatus.getInstance().getVersion()).start(new d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                if (z) {
                    this.b.L.obtainMessage(512, 1, 1).sendToTarget();
                } else {
                    this.b.L.obtainMessage(264, 1, 1).sendToTarget();
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (z) {
                    this.b.L.obtainMessage(512, 0, 1).sendToTarget();
                } else {
                    this.b.L.obtainMessage(264, 0, 1).sendToTarget();
                }
            }
        });
    }

    private void K() {
        j(true);
    }

    private void L() {
        j(false);
    }

    private void j(final boolean z) {
        new DataOsdActiveStatus().setType(dji.midware.data.model.b.a.b.c).setActiveStatus(z).setSN(DJIGlobalService.h).setYear(this.aa).setMonth(this.ab).setDay(this.ac).setHour(this.ad).setMin(this.ae).setSecond(this.af).setVersion(DataOsdActiveStatus.getInstance().getVersion()).start(new d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                if (z) {
                    this.b.L.obtainMessage(512, 1, 2).sendToTarget();
                } else {
                    this.b.L.obtainMessage(264, 1, 2).sendToTarget();
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (z) {
                    this.b.L.obtainMessage(512, 0, 2).sendToTarget();
                } else {
                    this.b.L.obtainMessage(264, 0, 2).sendToTarget();
                }
            }
        });
    }

    private void M() {
        k(true);
    }

    private void N() {
        k(false);
    }

    private void k(final boolean z) {
        new DataBatteryActiveStatus().setType(dji.midware.data.model.b.a.b.c).setActiveStatus(z).setSN(DJIGlobalService.i).setYear(this.aa).setMonth(this.ab).setDay(this.ac).setHour(this.ad).setMin(this.ae).setSecond(this.af).setVersion(DataBatteryActiveStatus.getInstance().getVersion()).start(new d(this) {
            final /* synthetic */ a b;

            public void onSuccess(Object obj) {
                if (z) {
                    this.b.L.obtainMessage(512, 1, 3).sendToTarget();
                } else {
                    this.b.L.obtainMessage(264, 1, 3).sendToTarget();
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (z) {
                    this.b.L.obtainMessage(512, 0, 3).sendToTarget();
                } else {
                    this.b.L.obtainMessage(264, 0, 3).sendToTarget();
                }
            }
        });
    }

    private void O() {
        DataCameraSetMode.getInstance().a(MODE.RECORD).start(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.L.obtainMessage(a.ar, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.L.obtainMessage(a.ar, 0, 0).sendToTarget();
            }
        });
    }

    private void P() {
        new DataCameraSetExposureMode().a(ExposureMode.c.a()).start(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.L.obtainMessage(a.av, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.L.obtainMessage(a.av, 0, 0).sendToTarget();
            }
        });
    }

    private void Q() {
        if (dji.pilot.fpv.d.b.a(null)) {
            DataSpecialControl.getInstance().setGimbalMode(DataGimbalControl.MODE.YawNoFollow).start(20);
        }
        this.L.sendMessageDelayed(this.L.obtainMessage(as, 1, 0), 200);
    }

    private void R() {
        DataCameraSetShutterSpeed dataCameraSetShutterSpeed = new DataCameraSetShutterSpeed();
        dataCameraSetShutterSpeed.a(true, 2000, 0);
        dataCameraSetShutterSpeed.start(new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.L.obtainMessage(a.at, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.L.obtainMessage(a.at, 0, 0).sendToTarget();
            }
        });
    }

    private void S() {
        DataSpecialControl.getInstance().setRecordType(true).start(20);
    }

    private void b(int i) {
        Toast.makeText(this.N, i, 1).show();
        if (i == R.string.inner_tool_initall_fail || i == R.string.inner_tool_initall_success) {
            this.t.setEnabled(true);
            if (i == R.string.inner_tool_initall_fail) {
                this.u.setText(this.u.getText().toString() + "    " + this.N.getString(i));
            } else {
                this.u.setText("");
            }
            this.W = false;
            this.Z.d((int) R.string.app_isee);
            a(i);
        }
    }
}
