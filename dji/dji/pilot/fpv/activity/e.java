package dji.pilot.fpv.activity;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.widget.AutoScrollHelper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Toast;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataBatteryGetPushCheckStatus;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataCenterGetPushCheckStatus;
import dji.midware.data.model.P3.DataDm368_gGetPushCheckStatus;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataFlycGetPushCheckStatus;
import dji.midware.data.model.P3.DataFlycGetPushDeformStatus;
import dji.midware.data.model.P3.DataFlycGetPushDeformStatus.DEFORM_MODE;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataGimbalGetPushCheckStatus;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataOsdGetPushChannalStatus;
import dji.midware.data.model.P3.DataOsdGetPushChannalStatus.CHANNEL_STATUS;
import dji.midware.data.model.P3.DataOsdGetPushCheckStatus;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.IMU_INITFAIL_REASON;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataOsdGetPushHome.MotorEscmState;
import dji.midware.data.model.P3.DataRcGetControlMode;
import dji.midware.data.model.P3.DataRcGetMaster;
import dji.midware.data.model.P3.DataRcGetPushBatteryInfo;
import dji.midware.data.model.P3.DataRcGetSlaveMode;
import dji.midware.data.model.P3.DataRcSetControlMode.ControlMode;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.data.model.P3.DataRcSetSlaveMode;
import dji.midware.data.model.P3.DataWifiGetPushElecSignal;
import dji.midware.data.model.P3.DataWifiGetPushElecSignal.SIGNAL_STATUS;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.battery.a.a$c;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.DJIGenSettingDataManager$c;
import dji.pilot.fpv.view.DJIGenFirstStageView;
import dji.pilot.publics.control.a$d;
import dji.pilot.publics.widget.DJITitleScrollView;
import dji.pilot.publics.widget.b;
import dji.pilot.publics.widget.c;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.lang.ref.WeakReference;

public class e extends a {
    private static final int A = 2;
    private static final int B = 4;
    private static final int C = 8;
    private static final int D = 16;
    private static final int E = 32;
    private static final int F = 64;
    private static final int G = 128;
    private static final int H = 256;
    private static final int I = 512;
    private static final int J = 1024;
    private static final int K = 2047;
    private static final int L = 8192;
    private static final int Q = 8193;
    private static final long R = 20;
    public static final int h = 0;
    public static final int i = 1;
    public static int j = 0;
    private static final int k = 4096;
    private static final int l = 4097;
    private static final int m = 4098;
    private static final int n = 4099;
    private static final int o = 4100;
    private static final int p = 4101;
    private static final int q = 4102;
    private static final int r = 4103;
    private static final int s = 4104;
    private static final int y = 0;
    private static final int z = 1;
    private DJIGenFirstStageView S = null;
    private DJITitleScrollView T = null;
    private DJILinearLayout U = null;
    private DJITextView V = null;
    private DJIImageView W = null;
    private DJITextView X = null;
    private DJIImageView Y = null;
    private DJIImageView Z = null;
    private DJITextView aA = null;
    private DJITextView aB = null;
    private DJILinearLayout aC = null;
    private DJILinearLayout aD = null;
    private DJITextView aE = null;
    private DJILinearLayout aF = null;
    private DJITextView aG = null;
    private DJILinearLayout aH = null;
    private DJITextView aI = null;
    private DJILinearLayout aJ = null;
    private DJITextView aK = null;
    private DJITextView aL = null;
    private OnClickListener aM = null;
    private a aN = null;
    private boolean aO = true;
    private boolean aP = true;
    private boolean aQ = true;
    private boolean aR = true;
    private boolean aS = true;
    private DJIGenSettingDataManager$c aT = null;
    private b aU = null;
    private int aV = -1;
    private c aW = null;
    private a$c aX = null;
    private int aY = 0;
    private boolean aZ = false;
    private DJITextView aa = null;
    private DJILinearLayout ab = null;
    private DJITextView ac = null;
    private DJITextView ad = null;
    private DJIImageView ae = null;
    private DJILinearLayout af = null;
    private DJITextView ag = null;
    private DJILinearLayout ah = null;
    private DJITextView ai = null;
    private DJILinearLayout aj = null;
    private DJITextView ak = null;
    private DJILinearLayout al = null;
    private DJIImageView am = null;
    private DJITextView an = null;
    private DJILinearLayout ao = null;
    private DJILinearLayout ap = null;
    private DJITextView aq = null;
    private DJILinearLayout ar = null;
    private DJILinearLayout as = null;
    private DJITextView at = null;
    private DJIImageView au = null;
    private DJITextView av = null;
    private DJILinearLayout aw = null;
    private DJILinearLayout ax = null;
    private DJITextView ay = null;
    private DJILinearLayout az = null;
    private boolean ba = false;
    private FLYC_STATE bb = FLYC_STATE.OTHER;
    private boolean bc = false;
    private int bd = 0;
    private int be = 0;
    private int bf = 0;
    private SDCardState bg = SDCardState.OTHER;
    private int bh = 0;
    private MODE bi = MODE.a;
    private float bj = AutoScrollHelper.NO_MAX;
    private boolean bk = false;
    private IMU_INITFAIL_REASON bl = IMU_INITFAIL_REASON.None;
    private boolean bm = false;
    private CHANNEL_STATUS bn = CHANNEL_STATUS.OTHER;
    private SIGNAL_STATUS bo = SIGNAL_STATUS.OTHER;
    private int bp = 0;
    private ObjectAnimator bq = null;
    private DEFORM_MODE br;
    private b bs;
    private final int t = -1;
    private final int u = 0;
    private final int v = 1;
    private final int w = 2;
    private final int x = 3;

    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[dji.midware.data.config.P3.a.values().length];

        static {
            try {
                a[dji.midware.data.config.P3.a.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    private static final class a extends Handler {
        private final WeakReference<e> a;

        public a(e eVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(eVar);
        }

        public void handleMessage(Message message) {
            e eVar = (e) this.a.get();
            if (eVar != null && eVar.isShowing()) {
                switch (message.what) {
                    case 4096:
                        eVar.D();
                        return;
                    case 4097:
                        eVar.G();
                        return;
                    case 4098:
                        eVar.F();
                        return;
                    case 4099:
                        eVar.a((a$d) message.obj);
                        return;
                    case 4100:
                        eVar.E();
                        return;
                    case 4101:
                        eVar.a(0, null);
                        return;
                    case e.q /*4102*/:
                        eVar.a(1, (dji.midware.data.config.P3.a) message.obj);
                        return;
                    case e.r /*4103*/:
                        eVar.j();
                        return;
                    case e.s /*4104*/:
                        eVar.d(message.arg1);
                        return;
                    case 8192:
                        eVar.b(message.arg1);
                        return;
                    case 8193:
                        eVar.c(message.arg1);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public static boolean h() {
        return j == 0;
    }

    public static void i() {
        j = 1;
    }

    private static void k() {
        j = 0;
    }

    public e(Context context) {
        super(context);
        l();
    }

    private void l() {
        this.g.createStageView(R.layout.fpv_checklist_view, R.string.fpv_checklist_title, false);
        q();
        r();
        s();
    }

    private void p() {
        b a = b.a(this.N, (int) R.string.app_tip, (int) R.string.fpv_checklist_upgrade_exit_tip, (int) R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }, R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        a.b();
        a.setCancelable(true);
        a.setCanceledOnTouchOutside(true);
        a.show();
    }

    private void q() {
        this.aN = new a(this);
        this.aM = new OnClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.xd == id) {
                    if (this.a.ao.isShown()) {
                        this.a.ao.go();
                    } else {
                        this.a.ao.show();
                    }
                } else if (R.id.xb == id) {
                } else {
                    if (R.id.x9 == id) {
                        if (dji.pilot.fpv.d.b.l(null)) {
                            this.a.p();
                        } else {
                            this.a.g.createStageView(R.layout.fpv_checklist_upgrade_view, R.string.fpv_checklist_module_check, true);
                        }
                    } else if (R.id.yi == id) {
                        dji.pilot.fpv.d.e.a("FPV_AircraftState_Button_RCMode");
                        if (this.a.bi == MODE.a) {
                            this.a.g.createStageView(R.layout.fpv_rc_settings_mapping, R.string.fpv_rcsetting_mapping, true);
                        } else {
                            this.a.g.createStageView(R.layout.fpv_rc_settings_custom, R.string.fpv_rcsetting_custom, true);
                        }
                    } else if (R.id.z3 == id) {
                        dji.pilot.fpv.d.e.a("FPV_AircraftState_RemainingSDCardCapacity_Button_Format");
                        DataCameraGetPushStateInfo instance = DataCameraGetPushStateInfo.getInstance();
                        SDCardState sDCardState = instance.getSDCardState(true);
                        if (!(instance.getSDCardInsertState() || sDCardState == SDCardState.USBConnected)) {
                            sDCardState = SDCardState.None;
                        }
                        if (sDCardState == SDCardState.None || sDCardState == SDCardState.USBConnected) {
                            Toast.makeText(this.a.N, this.a.N.getString(R.string.sdcardstatus_cant_format, new Object[]{this.a.N.getString(dji.pilot.fpv.d.b.a(sDCardState))}), 1).show();
                            return;
                        }
                        this.a.a(1, this.a.N.getString(R.string.fpv_gensetting_format_sdcard_confirm));
                    } else if (R.id.xy == id || R.id.xf == id) {
                        if (DataOsdGetPushCommon.getInstance().isGetted()) {
                            dji.pilot.fpv.d.e.a("FPV_AircraftState_Compass_Button_Calibrate");
                            this.a.a(3, this.a.N.getString(R.string.fpv_checklist_calibration_confirm));
                        }
                    } else if (R.id.sk == id) {
                        this.a.dismiss();
                    } else if (R.id.x1 == id) {
                        MarginLayoutParams marginLayoutParams;
                        if (this.a.af.isShown()) {
                            this.a.af.go();
                            this.a.ae.go();
                            this.a.ab.setBackgroundResource(R.drawable.selector_gen_radius_corner);
                            marginLayoutParams = (MarginLayoutParams) this.a.ab.getLayoutParams();
                            marginLayoutParams.bottomMargin = dji.pilot.fpv.model.b.a(this.a.N, R.dimen.n3);
                            this.a.ab.setLayoutParams(marginLayoutParams);
                            return;
                        }
                        this.a.ab.setBackgroundResource(R.drawable.selector_gen_top_corner);
                        marginLayoutParams = (MarginLayoutParams) this.a.ab.getLayoutParams();
                        marginLayoutParams.bottomMargin = 0;
                        this.a.ab.setLayoutParams(marginLayoutParams);
                        this.a.af.show();
                        this.a.ae.show();
                    } else if (R.id.xj == id) {
                        this.a.g.createStageView(R.layout.fpv_checklist_upgrade_view, R.string.fpv_checklist_module_check, true);
                    } else if (R.id.zn == id) {
                        if (DataFlycGetPushDeformStatus.getInstance().getDeformMode() == DEFORM_MODE.Pack) {
                            DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.UnPackMode).start(new d(this) {
                                final /* synthetic */ AnonymousClass6 a;

                                {
                                    this.a = r1;
                                }

                                public void onSuccess(Object obj) {
                                }

                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                }
                            });
                        } else {
                            b.a(this.a.getContext(), this.a.N.getString(R.string.app_tip), this.a.N.getString(R.string.fpv_checklist_pack_confirm), this.a.N.getString(R.string.app_cancel), new DialogInterface.OnClickListener(this) {
                                final /* synthetic */ AnonymousClass6 a;

                                {
                                    this.a = r1;
                                }

                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }, this.a.N.getString(R.string.app_enter), new DialogInterface.OnClickListener(this) {
                                final /* synthetic */ AnonymousClass6 a;

                                {
                                    this.a = r1;
                                }

                                public void onClick(DialogInterface dialogInterface, int i) {
                                    DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.PackMode).start(new d(this) {
                                        final /* synthetic */ AnonymousClass3 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void onSuccess(Object obj) {
                                            this.a.a.a.aN.sendMessage(this.a.a.a.aN.obtainMessage(e.s, R.string.mc_param_pack_mode_success, 0));
                                        }

                                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                                            switch (AnonymousClass4.a[aVar.ordinal()]) {
                                                case 1:
                                                    this.a.a.a.aN.sendMessage(this.a.a.a.aN.obtainMessage(e.s, R.string.code_timeout, 0));
                                                    return;
                                                default:
                                                    this.a.a.a.aN.sendMessage(this.a.a.a.aN.obtainMessage(e.s, R.string.mc_param_pack_mode_failed, 0));
                                                    return;
                                            }
                                        }
                                    });
                                    dialogInterface.dismiss();
                                }
                            }).show();
                        }
                    } else if (R.id.y1 == id) {
                        this.a.g.createStageView(R.layout.fpv_checklist_escm_view, R.string.fpv_checklist_escm_desc, true);
                    }
                }
            }
        };
        this.aT = new DJIGenSettingDataManager$c(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(int i) {
            }

            public void b(int i) {
            }

            public void a(int i, boolean z) {
                if (!z) {
                    this.a.a(i);
                }
            }

            public void a(int i, boolean z, int i2, dji.midware.data.config.P3.a aVar) {
                if (!z) {
                    this.a.a(i, i2, aVar);
                }
            }
        };
        this.aX = new a$c(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
            }

            public void b(int i, boolean z) {
            }

            public void a(int i, boolean z) {
            }

            public void a(int i, int i2, boolean z) {
            }

            public void a(int i) {
                this.a.B();
            }
        };
    }

    private void r() {
        this.S = (DJIGenFirstStageView) findViewById(R.id.wz);
        this.T = (DJITitleScrollView) findViewById(R.id.x0);
        this.U = (DJILinearLayout) findViewById(R.id.xj);
        this.V = (DJITextView) findViewById(R.id.xn);
        this.W = (DJIImageView) findViewById(R.id.xo);
        this.X = (DJITextView) findViewById(R.id.xp);
        this.Y = (DJIImageView) findViewById(R.id.xq);
        this.Z = (DJIImageView) findViewById(R.id.xm);
        this.aa = (DJITextView) findViewById(R.id.zn);
        this.ab = (DJILinearLayout) findViewById(R.id.x1);
        this.ac = (DJITextView) findViewById(R.id.x3);
        this.ad = (DJITextView) findViewById(R.id.x4);
        this.ae = (DJIImageView) findViewById(R.id.x5);
        this.af = (DJILinearLayout) findViewById(R.id.x6);
        this.ag = (DJITextView) findViewById(R.id.x7);
        this.ah = (DJILinearLayout) findViewById(R.id.x8);
        this.ai = (DJITextView) findViewById(R.id.x9);
        this.aj = (DJILinearLayout) findViewById(R.id.x_);
        this.ak = (DJITextView) findViewById(R.id.xb);
        this.al = (DJILinearLayout) findViewById(R.id.xc);
        this.am = (DJIImageView) findViewById(R.id.xd);
        this.an = (DJITextView) findViewById(R.id.xf);
        this.ao = (DJILinearLayout) findViewById(R.id.xg);
        this.ap = (DJILinearLayout) findViewById(R.id.xx);
        this.aq = (DJITextView) findViewById(R.id.xy);
        this.ar = (DJILinearLayout) findViewById(R.id.xw);
        this.as = (DJILinearLayout) findViewById(R.id.y1);
        this.at = (DJITextView) findViewById(R.id.y4);
        this.au = (DJIImageView) findViewById(R.id.y5);
        this.av = (DJITextView) findViewById(R.id.z8);
        this.aw = (DJILinearLayout) findViewById(R.id.z9);
        this.ax = (DJILinearLayout) findViewById(R.id.yb);
        this.ay = (DJITextView) findViewById(R.id.ye);
        this.az = (DJILinearLayout) findViewById(R.id.yi);
        this.aA = (DJITextView) findViewById(R.id.yl);
        this.aB = (DJITextView) findViewById(R.id.y9);
        this.aC = (DJILinearLayout) findViewById(R.id.y_);
        this.aD = (DJILinearLayout) findViewById(R.id.yw);
        this.aE = (DJITextView) findViewById(R.id.yz);
        this.aF = (DJILinearLayout) findViewById(R.id.ym);
        this.aG = (DJITextView) findViewById(R.id.yp);
        this.aH = (DJILinearLayout) findViewById(R.id.ys);
        this.aI = (DJITextView) findViewById(R.id.yv);
        this.aJ = (DJILinearLayout) findViewById(R.id.z0);
        this.aK = (DJITextView) findViewById(R.id.z4);
        this.aL = (DJITextView) findViewById(R.id.z3);
    }

    private void s() {
        this.U.setOnClickListener(this.aM);
        this.ai.setOnClickListener(this.aM);
        this.ak.setOnClickListener(this.aM);
        this.am.setOnClickListener(this.aM);
        this.an.setOnClickListener(this.aM);
        this.aq.setOnClickListener(this.aM);
        this.aa.setOnClickListener(this.aM);
        this.ab.setOnClickListener(this.aM);
        this.az.setOnClickListener(this.aM);
        this.aq.setOnClickListener(this.aM);
        this.aL.setOnClickListener(this.aM);
        this.f.setOnClickListener(this.aM);
        this.as.setOnClickListener(this.aM);
        this.S.setLifeListener(this);
    }

    protected void a(int i, int i2, int i3) {
        super.a(i, i2, i3);
    }

    private void a(int i) {
        if (2 == i) {
            this.aL.setEnabled(false);
        }
    }

    private void a(int i, int i2, dji.midware.data.config.P3.a aVar) {
        if (2 == i) {
            this.aL.setEnabled(true);
            if (i2 == 0) {
                a(2, this.N.getString(R.string.fpv_gensetting_format_sdcard_success));
            } else {
                a(2, this.N.getString(R.string.fpv_gensetting_format_sdcard_fail));
            }
        }
    }

    private void a(int i, dji.midware.data.config.P3.a aVar) {
        this.aq.setEnabled(true);
        if (i == 0) {
            a(2, this.N.getString(R.string.fpv_checklist_calibration_success));
        } else {
            a(2, this.N.getString(R.string.fpv_checklist_calibration_fail));
        }
    }

    private void a(int i, String str) {
        if (this.aU == null) {
            this.aU = b.a(this.N, (int) R.string.app_tip, (int) R.string.fpv_playback_del_image, (int) R.string.btn_dlg_no, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.u();
                    this.a.aV = -1;
                }
            }, (int) R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.t();
                }
            });
            this.aU.setCancelable(true);
            this.aU.setCanceledOnTouchOutside(true);
        }
        if (this.aU != null && !this.aU.isShowing()) {
            this.aV = i;
            this.aU.b(str);
            if (this.aV == 2) {
                this.aU.d((int) R.string.app_isee);
                this.aU.b();
            } else {
                this.aU.d((int) R.string.btn_dlg_no);
                this.aU.e(R.string.btn_dlg_yes);
            }
            this.aU.show();
        }
    }

    private void t() {
        u();
        if (this.aV == 0) {
            DJIGenSettingDataManager.getInstance().g();
        } else if (this.aV == 1) {
            DJIGenSettingDataManager.getInstance().h();
        } else if (this.aV != 2 && this.aV == 3) {
            DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.Calibration).start(null);
            c();
        }
        this.aV = -1;
    }

    private void u() {
        if (this.aU != null && this.aU.isShowing()) {
            this.aU.dismiss();
        }
    }

    protected boolean a() {
        super.a();
        return true;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    protected void onStart() {
        super.onStart();
        dji.setting.ui.battery.a.a();
        z();
        this.T.scrollTo(0, 0);
        DJIGenSettingDataManager.getInstance().a(this.aT);
        dji.pilot.battery.a.a.getInstance().a(this.aX);
        dji.thirdparty.a.c.a().a(this);
    }

    protected void onStop() {
        dji.pilot.battery.a.a.getInstance().b(this.aX);
        DJIGenSettingDataManager.getInstance().b(this.aT);
        dji.pilot.battery.a.a.getInstance().f();
        dji.thirdparty.a.c.a().d(this);
        dji.setting.ui.battery.a.b();
        super.onStop();
    }

    public void d() {
        y();
        super.d();
    }

    private void v() {
        DataRcGetMaster.getInstance().start(new d(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.aN.obtainMessage(8193, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.aN.obtainMessage(8193, 0, 0).sendToTarget();
            }
        });
    }

    private void w() {
        DataRcGetControlMode.getInstance().start(new d(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.aN.obtainMessage(8192, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.aN.obtainMessage(8192, 0, 0).sendToTarget();
            }
        });
    }

    private void x() {
        DataRcGetSlaveMode.getInstance().start(new d(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.aN.obtainMessage(8192, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.aN.obtainMessage(8192, 0, 0).sendToTarget();
            }
        });
    }

    private void y() {
        v();
        onEventBackgroundThread(DataFlycGetPushDeformStatus.getInstance());
    }

    private void z() {
        this.aO = true;
        this.aS = true;
        this.aR = true;
        this.aP = true;
        this.ba = false;
        C();
        this.aq.setEnabled(true);
        this.bp = 0;
        J();
        this.bb = FLYC_STATE.OTHER;
        this.bc = false;
        a(this.bb, this.bc);
        B();
        this.bd = 0;
        this.aG.setText(this.N.getString(R.string.fpv_percent, new Object[]{Integer.valueOf(this.bd)}));
        this.bf = 0;
        this.aI.setText(this.N.getString(R.string.fpv_percent, new Object[]{Integer.valueOf(this.bf)}));
        this.bh = DataCameraGetPushStateInfo.getInstance().getSDCardFreeSize();
        this.aK.setText(this.N.getString(R.string.fpv_checklist_sd_valume, new Object[]{Integer.valueOf(this.bh)}));
        this.aL.go();
        this.aL.setEnabled(true);
        this.aA.setText(R.string.fpv_default_str);
        A();
        onEventMainThread(DataGimbalGetPushParams.getInstance());
        if (dji.pilot.fpv.d.b.l(null)) {
            this.bn = CHANNEL_STATUS.OTHER;
            onEventMainThread(DataWifiGetPushElecSignal.getInstance());
        } else {
            this.bo = SIGNAL_STATUS.OTHER;
            onEventMainThread(DataOsdGetPushChannalStatus.getInstance());
        }
        this.aY = 0;
        if (DataOsdGetPushCommon.getInstance().isGetted()) {
            D();
        }
        if (DataOsdGetPushHome.getInstance().isGetted()) {
            onEventMainThread(DataOsdGetPushHome.getInstance());
        }
        if (DataCenterGetPushBatteryCommon.getInstance().isGetted()) {
            E();
        }
        if (DataRcGetPushBatteryInfo.getInstance().isGetted()) {
            F();
        }
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            G();
            if (DataCameraGetPushStateInfo.getInstance().isOK()) {
                this.aY |= 2;
            } else {
                this.aY &= -3;
            }
        }
        if (DataFlycGetPushSmartBattery.getInstance().isGetted()) {
            if ((DataFlycGetPushSmartBattery.getInstance().getStatus() & 2048) == 2048) {
                this.aY |= 128;
            } else {
                this.aY &= -129;
            }
        }
        if (DataDm368_gGetPushCheckStatus.getInstance().isGetted()) {
            if (DataDm368_gGetPushCheckStatus.getInstance().isOK()) {
                this.aY |= 1;
            } else {
                this.aY &= -2;
            }
        }
        if (DataCenterGetPushCheckStatus.getInstance().isGetted()) {
            if (DataCenterGetPushCheckStatus.getInstance().isOK()) {
                this.aY |= 4;
            } else {
                this.aY &= -5;
            }
        }
        if (DataBatteryGetPushCheckStatus.getInstance().isGetted()) {
            if (DataBatteryGetPushCheckStatus.getInstance().isOK()) {
                this.aY |= 8;
            } else {
                this.aY &= -9;
            }
        }
        if (DataOsdGetPushCheckStatus.getInstance().isGetted()) {
            if (DataOsdGetPushCheckStatus.getInstance().isOK()) {
                this.aY |= 16;
            } else {
                this.aY &= -17;
            }
        }
        if (DataFlycGetPushCheckStatus.getInstance().isGetted()) {
            if (DataFlycGetPushCheckStatus.getInstance().isOK()) {
                this.aY |= 32;
            } else {
                this.aY &= -33;
            }
        }
        if (DataGimbalGetPushCheckStatus.getInstance().isGetted()) {
            if (DataGimbalGetPushCheckStatus.getInstance().hasException()) {
                this.aY |= 64;
            } else {
                this.aY &= -65;
            }
        }
        if (DataCenterGetPushBatteryCommon.getInstance().isGetted()) {
            int errorType = DataCenterGetPushBatteryCommon.getInstance().getErrorType();
            dji.pilot.battery.a.c cVar = new dji.pilot.battery.a.c();
            cVar.a(errorType);
            if (cVar.l() != (byte) 0) {
                this.aY |= 256;
            } else {
                this.aY &= -257;
            }
            if (DataCenterGetPushBatteryCommon.getInstance().isNeedStudy()) {
                this.aY |= 512;
            } else {
                this.aY &= -513;
            }
        }
        a(false);
        if (dji.pilot.publics.control.a.getInstance(this.N.getApplicationContext()).o()) {
            if (dji.pilot.publics.control.a.getInstance(this.N.getApplicationContext()).l()) {
                this.ah.show();
            } else {
                this.ah.go();
            }
            this.aZ = true;
            this.V.show();
            this.V.setText(R.string.fpv_checklist_need_upgrade);
        } else {
            this.ah.go();
            this.aZ = false;
            if (ServiceManager.getInstance().isConnected()) {
                this.V.show();
                this.V.setText(R.string.check_module_firmware_latest);
            } else {
                this.V.go();
            }
        }
        H();
        I();
        a(i.getInstance().c());
    }

    private void A() {
        if (dji.pilot.fpv.d.b.b()) {
            this.ab.show();
            this.ae.show();
            this.af.show();
            this.ab.setBackgroundResource(R.drawable.selector_gen_radius_corner);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.ab.getLayoutParams();
            marginLayoutParams.bottomMargin = 0;
            this.ab.setLayoutParams(marginLayoutParams);
            return;
        }
        this.ab.go();
        this.ae.go();
        this.af.go();
    }

    private void B() {
        float w = dji.pilot.battery.a.a.getInstance().w();
        if (this.bj != w) {
            this.bj = w;
            DJITextView dJITextView = this.aE;
            Context context = this.N;
            Object[] objArr = new Object[1];
            objArr[0] = String.format("%.1f", new Object[]{Float.valueOf(this.bj)});
            dJITextView.setText(context.getString(R.string.battery_temperature_unit, objArr));
        }
        dji.pilot.battery.a.c t = dji.pilot.battery.a.a.getInstance().t();
        if (t.f() || t.g()) {
            this.aE.setTextColor(this.N.getResources().getColor(R.color.gj));
        } else {
            this.aE.setTextColor(this.N.getResources().getColor(R.color.om));
        }
    }

    private void C() {
        if (this.ba) {
            this.al.show();
            this.ao.go();
            this.ap.go();
            return;
        }
        this.ap.show();
        this.ao.go();
        this.al.go();
    }

    private void a(FLYC_STATE flyc_state, boolean z) {
        this.ay.setText(dji.pilot.fpv.d.b.a(flyc_state, z, false)[0]);
    }

    private void D() {
        DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
        boolean compassError = instance.getCompassError();
        if (this.aO || this.ba != compassError) {
            this.ba = compassError;
            C();
        }
        FLYC_STATE flycState = instance.getFlycState();
        boolean isVisionUsed = instance.isVisionUsed();
        if (!(!this.aO && this.bb == flycState && this.bc == isVisionUsed)) {
            this.bb = flycState;
            this.bc = isVisionUsed;
            a(flycState, isVisionUsed);
        }
        int voltageWarning = instance.getVoltageWarning();
        if (this.aO || this.be != voltageWarning) {
            this.be = voltageWarning;
            if (voltageWarning == 1 || voltageWarning == 2) {
                this.aG.setTextColor(this.N.getResources().getColor(R.color.gj));
            } else {
                this.aG.setTextColor(this.N.getResources().getColor(R.color.om));
            }
        }
        isVisionUsed = DataOsdGetPushCommon.getInstance().isAllowImuInitfailReason();
        IMU_INITFAIL_REASON iMUinitFailReason = DataOsdGetPushCommon.getInstance().getIMUinitFailReason();
        if (this.bl != iMUinitFailReason) {
            compassError = true;
        } else {
            compassError = false;
        }
        if (!(!this.aO && this.bk == isVisionUsed && this.bl == iMUinitFailReason)) {
            this.bk = isVisionUsed;
            this.bl = iMUinitFailReason;
            if (isVisionUsed && DataOsdGetPushCommon.getInstance().isImuInitError()) {
                this.aY |= 1024;
            } else {
                this.aY &= -1025;
            }
            a(true);
        }
        if (compassError) {
            if (iMUinitFailReason == IMU_INITFAIL_REASON.GyroBiasTooLarge || iMUinitFailReason == IMU_INITFAIL_REASON.AcceBiasTooLarge) {
                this.bp |= 2;
            } else {
                this.bp &= -3;
            }
            J();
        }
        if (instance.getFlycVersion() >= 15) {
            this.as.show();
        } else {
            this.as.go();
        }
        this.aO = false;
    }

    private void E() {
        int battery = DataFlycGetPushSmartBattery.getInstance().getBattery();
        if (this.aP || battery != this.bd) {
            this.bd = battery;
            if (this.bd < 0) {
                this.bd = 0;
            } else if (this.bd > 100) {
                this.bd = 100;
            }
            this.aG.setText(this.N.getString(R.string.fpv_percent, new Object[]{Integer.valueOf(this.bd)}));
        }
        this.aP = false;
    }

    private void F() {
        int i = 100;
        int battery = DataRcGetPushBatteryInfo.getInstance().getBattery();
        if (battery < 0) {
            i = 0;
        } else if (battery <= 100) {
            i = battery;
        }
        if (this.aS || this.bf != i) {
            this.bf = i;
            this.aI.setText(this.N.getString(R.string.fpv_percent, new Object[]{Integer.valueOf(i)}));
            if (dji.pilot.fpv.d.b.i(i)) {
                this.aI.setTextColor(this.N.getResources().getColor(R.color.gj));
            } else {
                this.aI.setTextColor(this.N.getResources().getColor(R.color.om));
            }
        }
        this.aS = false;
    }

    private void G() {
        int sDCardFreeSize = DataCameraGetPushStateInfo.getInstance().getSDCardFreeSize();
        SDCardState sDCardState = DataCameraGetPushStateInfo.getInstance().getSDCardState(true);
        if (!(!this.aR && this.bg == sDCardState && this.bh == sDCardFreeSize)) {
            this.bg = sDCardState;
            this.bh = sDCardFreeSize;
            if (sDCardState == SDCardState.Normal) {
                this.aK.setText(this.N.getString(R.string.fpv_checklist_sd_valume, new Object[]{Integer.valueOf(sDCardFreeSize)}));
            } else {
                this.aK.setText(dji.pilot.fpv.d.b.a(sDCardState));
            }
            if (sDCardState == SDCardState.None) {
                this.aL.go();
            } else {
                this.aL.show();
            }
        }
        this.aR = false;
    }

    private void a(a$d dji_pilot_publics_control_a_d) {
        if (dji_pilot_publics_control_a_d != a$d.NO) {
            if (dji.pilot.publics.control.a.getInstance(this.N.getApplicationContext()).l()) {
                this.ah.show();
            } else {
                this.ah.go();
            }
            this.aZ = true;
            this.V.show();
            this.V.setText(R.string.fpv_checklist_need_upgrade);
        } else {
            this.ah.go();
            this.aZ = false;
            this.V.show();
            this.V.setText(R.string.check_module_firmware_latest);
        }
        H();
        I();
    }

    private void b(int i) {
        if (i != 1) {
            return;
        }
        if (this.bi != MODE.b) {
            ControlMode controlType = DataRcGetControlMode.getInstance().getControlType();
            if (controlType == ControlMode.b) {
                this.aA.setText(R.string.rc_master_2);
            } else if (controlType == ControlMode.a) {
                this.aA.setText(R.string.rc_master_1);
            } else if (controlType == ControlMode.c) {
                this.aA.setText(R.string.rc_master_3);
            } else if (controlType == ControlMode.d) {
                this.aA.setText(R.string.rc_master_4);
            }
        } else if (DataRcGetSlaveMode.getInstance().getControlType() == DataRcSetSlaveMode.ControlMode.b) {
            this.aA.setText(R.string.rc_slave_2);
        } else {
            this.aA.setText(R.string.rc_slave_1);
        }
    }

    private void c(int i) {
        if (1 == i) {
            MODE mode = DataRcGetMaster.getInstance().getMode();
            this.bi = mode;
            if (mode == MODE.b) {
                x();
            } else {
                w();
            }
        }
    }

    private void a(boolean z) {
        if ((this.aY & K) != 0) {
            this.W.show();
        } else {
            this.W.go();
        }
        if (z) {
            I();
        }
    }

    private void H() {
        if (this.bq == null) {
            this.bq = (ObjectAnimator) AnimatorInflater.loadAnimator(this.N, R.animator.c);
            this.bq.setTarget(this.Z);
        }
        if (dji.pilot.publics.control.rc.b.getInstance().h()) {
            this.Z.show();
            this.bq.start();
        } else {
            this.bq.cancel();
            this.Z.setAlpha(1.0f);
            this.Z.setImageAlpha(255);
            this.Z.go();
        }
        I();
    }

    private void I() {
        if (this.W.getVisibility() == 0) {
            this.X.show();
        } else {
            this.X.go();
        }
        if (this.aZ || this.W.getVisibility() == 0 || this.Z.getVisibility() == 0) {
            this.Y.show();
            this.U.setEnabled(true);
            return;
        }
        this.Y.go();
        this.U.setEnabled(false);
    }

    public void onEventMainThread(DataOsdGetPushChannalStatus dataOsdGetPushChannalStatus) {
        CHANNEL_STATUS channelStatus = dataOsdGetPushChannalStatus.getChannelStatus();
        if (channelStatus != this.bn) {
            this.bn = channelStatus;
            if (dji.pilot.fpv.d.b.a(channelStatus)) {
                this.aB.setText(R.string.fpv_checklist_status_poor);
                this.aB.setTextColor(this.N.getResources().getColor(R.color.gj));
                this.aC.show();
                return;
            }
            this.aB.setText(R.string.fpv_checklist_status_good);
            this.aB.setTextColor(this.N.getResources().getColor(R.color.om));
            this.aC.go();
        }
    }

    public void onEventMainThread(DataWifiGetPushElecSignal dataWifiGetPushElecSignal) {
        SIGNAL_STATUS signalStatus = dataWifiGetPushElecSignal.getSignalStatus();
        if (this.bo != signalStatus) {
            this.bo = signalStatus;
            if (dji.pilot.fpv.d.b.a(signalStatus)) {
                this.aB.setText(R.string.fpv_checklist_status_poor);
                this.aB.setTextColor(this.N.getResources().getColor(R.color.gj));
                this.aC.show();
                return;
            }
            this.aB.setText(R.string.fpv_checklist_status_good);
            this.aB.setTextColor(this.N.getResources().getColor(R.color.om));
            this.aC.go();
        }
    }

    public void onEventMainThread(ProductType productType) {
        a(productType);
    }

    private void a(ProductType productType) {
        if (dji.pilot.fpv.d.b.g(productType)) {
            this.aa.show();
        } else {
            this.aa.go();
        }
        if (!i.getInstance().c().isCompleteMachine()) {
            this.aa.go();
        }
    }

    public void onEventMainThread(dji.pilot.fpv.d.b.a aVar) {
        A();
    }

    public void onEventMainThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        if ((dataFlycGetPushSmartBattery.getStatus() & 2048) == 2048) {
            this.aY |= 128;
        } else {
            this.aY &= -129;
        }
        a(true);
    }

    public void onEventMainThread(DataDm368_gGetPushCheckStatus dataDm368_gGetPushCheckStatus) {
        if (dataDm368_gGetPushCheckStatus.isOK()) {
            this.aY |= 1;
        } else {
            this.aY &= -2;
        }
        a(true);
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (dataCameraGetPushStateInfo.isOK()) {
            this.aY |= 2;
        } else {
            this.aY &= -3;
        }
        a(true);
    }

    public void onEventMainThread(DataCenterGetPushCheckStatus dataCenterGetPushCheckStatus) {
        if (dataCenterGetPushCheckStatus.isOK()) {
            this.aY |= 4;
        } else {
            this.aY &= -5;
        }
        a(true);
    }

    public void onEventMainThread(DataBatteryGetPushCheckStatus dataBatteryGetPushCheckStatus) {
        if (dataBatteryGetPushCheckStatus.isOK()) {
            this.aY |= 8;
        } else {
            this.aY &= -9;
        }
        a(true);
    }

    public void onEventMainThread(DataOsdGetPushCheckStatus dataOsdGetPushCheckStatus) {
        if (dataOsdGetPushCheckStatus.isOK()) {
            this.aY |= 16;
        } else {
            this.aY &= -17;
        }
        a(true);
    }

    public void onEventMainThread(DataFlycGetPushCheckStatus dataFlycGetPushCheckStatus) {
        if (dataFlycGetPushCheckStatus.isOK()) {
            this.aY |= 32;
        } else {
            this.aY &= -33;
        }
        a(true);
        if (dataFlycGetPushCheckStatus.getIMUAdvanceCaliStatus() || dataFlycGetPushCheckStatus.getIMUBasicCaliStatus() || dataFlycGetPushCheckStatus.getVersionStatus()) {
            this.bp |= 1;
        } else {
            this.bp &= -2;
        }
        J();
    }

    private void J() {
        if (this.bp == 0) {
            this.aj.go();
            this.ar.go();
            return;
        }
        this.aj.go();
        this.ar.go();
    }

    public void onEventMainThread(DataGimbalGetPushCheckStatus dataGimbalGetPushCheckStatus) {
        if (dataGimbalGetPushCheckStatus.hasException()) {
            this.aY |= 64;
        } else {
            this.aY &= -65;
        }
        a(true);
    }

    public void onEventBackgroundThread(a$d dji_pilot_publics_control_a_d) {
        if (!this.aN.hasMessages(4099)) {
            this.aN.sendMessageDelayed(this.aN.obtainMessage(4099, dji_pilot_publics_control_a_d), R);
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (!this.aN.hasMessages(4096)) {
            this.aN.sendEmptyMessageDelayed(4096, R);
        }
    }

    public void onEventMainThread(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
        if (dataCenterGetPushBatteryCommon.isNeedStudy()) {
            this.aY |= 512;
        } else {
            this.aY &= -513;
        }
        int errorType = dataCenterGetPushBatteryCommon.getErrorType();
        dji.pilot.battery.a.c cVar = new dji.pilot.battery.a.c();
        cVar.a(errorType);
        if (cVar.l() != (byte) 0) {
            this.aY |= 256;
        } else {
            this.aY &= -257;
        }
        a(true);
    }

    public void onEventMainThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        boolean isStuck = dataGimbalGetPushParams.isStuck();
        if (this.bm != isStuck) {
            this.bm = isStuck;
            if (isStuck) {
                this.av.setText(R.string.fpv_checklist_gimbal_stuck);
                this.av.setTextColor(this.N.getResources().getColor(R.color.gj));
                this.aw.show();
                return;
            }
            this.av.setText(R.string.fpv_checklist_check_normal);
            this.av.setTextColor(this.N.getResources().getColor(R.color.om));
            this.aw.go();
        }
    }

    public void onEventBackgroundThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        if (!this.aN.hasMessages(4100)) {
            this.aN.sendEmptyMessageDelayed(4100, R);
        }
    }

    public void onEventBackgroundThread(DataRcGetPushBatteryInfo dataRcGetPushBatteryInfo) {
        if (!this.aN.hasMessages(4098)) {
            this.aN.sendEmptyMessageDelayed(4098, R);
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (!this.aN.hasMessages(4097)) {
            this.aN.sendEmptyMessageDelayed(4097, R);
        }
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
        boolean z = true;
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() >= 15) {
            boolean z2;
            this.as.show();
            for (MotorEscmState a : dataOsdGetPushHome.getMotorEscmState()) {
                if (!dji.pilot.fpv.d.b.a(a)) {
                    z2 = false;
                    break;
                }
            }
            z2 = true;
            DJILinearLayout dJILinearLayout = this.as;
            if (z2) {
                z = false;
            }
            dJILinearLayout.setEnabled(z);
            if (z2) {
                this.at.setText(R.string.fpv_checklist_check_normal);
                this.au.go();
                return;
            }
            this.at.setText(R.string.fpv_checklist_check_abnormal);
            this.au.show();
        }
    }

    public void onEventBackgroundThread(DataFlycGetPushDeformStatus dataFlycGetPushDeformStatus) {
        DEFORM_MODE deformMode = dataFlycGetPushDeformStatus.getDeformMode();
        if (deformMode != this.br) {
            this.br = deformMode;
            this.aN.sendEmptyMessage(r);
        }
    }

    public void j() {
        if (this.br == DEFORM_MODE.Pack) {
            this.aa.setText(R.string.flyc_unpack_desc);
        } else {
            this.aa.setText(R.string.flyc_pack_desc);
        }
    }

    private void d(int i) {
        if (this.bs == null) {
            this.bs = b.a(getContext(), this.N.getString(R.string.app_tip), this.N.getString(R.string.fpv_flyc_ioc_reset_confirm), this.N.getString(R.string.app_enter), new DialogInterface.OnClickListener(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
        }
        this.bs.b(i);
        this.bs.show();
    }
}
