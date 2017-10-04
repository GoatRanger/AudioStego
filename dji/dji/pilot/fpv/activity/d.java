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
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Toast;
import com.amap.api.maps.model.HeatmapTileProvider;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.Data1860GetPushCheckStatus;
import dji.midware.data.model.P3.Data2100GetPushCheckStatus;
import dji.midware.data.model.P3.DataBatteryGetPushCheckStatus;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataCenterGetPushCheckStatus;
import dji.midware.data.model.P3.DataDm368_gGetPushCheckStatus;
import dji.midware.data.model.P3.DataEyeGetPushFunctionList;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycGetPushCheckStatus;
import dji.midware.data.model.P3.DataFlycGetPushDeformStatus;
import dji.midware.data.model.P3.DataFlycGetPushDeformStatus.DEFORM_MODE;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataFlycSetHaveCheckedStruct;
import dji.midware.data.model.P3.DataGimbalGetPushCheckStatus;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataGimbalPushBatteryInfo;
import dji.midware.data.model.P3.DataOsdGetPushChannalStatus;
import dji.midware.data.model.P3.DataOsdGetPushChannalStatus.CHANNEL_STATUS;
import dji.midware.data.model.P3.DataOsdGetPushCheckStatus;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.IMU_INITFAIL_REASON;
import dji.midware.data.model.P3.DataOsdGetPushCommon.MotorStartFailedCause;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataOsdGetPushHome.MotorEscmState;
import dji.midware.data.model.P3.DataRcGetControlMode;
import dji.midware.data.model.P3.DataRcGetMaster;
import dji.midware.data.model.P3.DataRcGetPushBatteryInfo;
import dji.midware.data.model.P3.DataRcGetSlaveMode;
import dji.midware.data.model.P3.DataRcSetControlMode.ControlMode;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.data.model.P3.DataRcSetSlaveMode;
import dji.midware.data.model.P3.DataSmartBatteryGetPushDynamicData;
import dji.midware.data.model.P3.DataWifiGetPushElecSignal;
import dji.midware.data.model.P3.DataWifiGetPushElecSignal.SIGNAL_STATUS;
import dji.pilot.R;
import dji.pilot.battery.a.a$c;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.DJIGenSettingDataManager$b;
import dji.pilot.fpv.control.DJIGenSettingDataManager$c;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIGenFirstStageView;
import dji.pilot.publics.control.a$d;
import dji.pilot.publics.e.f;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.widget.DJITitleScrollView;
import dji.pilot.publics.widget.b;
import dji.pilot.publics.widget.c;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;
import dji.setting.ui.rc.RcMasterSlaveView;
import java.lang.ref.WeakReference;

public class d extends a {
    private static final int C = 0;
    private static final int D = 1;
    private static final int E = 2;
    private static final int F = 4;
    private static final int G = 8;
    private static final int H = 16;
    private static final int I = 32;
    private static final int J = 64;
    private static final int K = 128;
    private static final int L = 256;
    private static final int Q = 512;
    private static final int R = 1024;
    private static final int S = 2048;
    private static final int T = 4096;
    private static final int U = 8191;
    private static final int V = 8192;
    private static final int W = 8193;
    private static final long X = 20;
    private static final int bT = 30000;
    private static final String cc = "g_config.aircraft.multi_rotor_type_0";
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
    private static final int t = 4105;
    private static final int u = 4106;
    private static final int v = 4107;
    private static final int w = 4108;
    private final int A = 2;
    private final int B = 3;
    private DJIGenFirstStageView Y = null;
    private DJITitleScrollView Z = null;
    private DJILinearLayout aA = null;
    private DJITextView aB = null;
    private DJIImageView aC = null;
    private DJITextView aD = null;
    private DJILinearLayout aE = null;
    private DJILinearLayout aF = null;
    private DJILinearLayout aG = null;
    private DJILinearLayout aH = null;
    private DJITextView aI = null;
    private DJILinearLayout aJ = null;
    private DJITextView aK = null;
    private DJITextView aL = null;
    private DJILinearLayout aM = null;
    private DJILinearLayout aN = null;
    private DJITextView aO = null;
    private DJILinearLayout aP = null;
    private DJITextView aQ = null;
    private DJILinearLayout aR = null;
    private DJITextView aS = null;
    private DJILinearLayout aT = null;
    private DJITextView aU = null;
    private DJITextView aV = null;
    private DJILinearLayout aW = null;
    private DJILinearLayout aX = null;
    private DJITextView aY = null;
    private DJILinearLayout aZ = null;
    private DJILinearLayout aa = null;
    private DJITextView ab = null;
    private DJIImageView ac = null;
    private DJITextView ad = null;
    private DJIImageView ae = null;
    private DJIImageView af = null;
    private DJITextView ag = null;
    private DJILinearLayout ah = null;
    private DJITextView ai = null;
    private DJITextView aj = null;
    private DJIImageView ak = null;
    private DJILinearLayout al = null;
    private DJITextView am = null;
    private DJILinearLayout an = null;
    private DJITextView ao = null;
    private DJILinearLayout ap = null;
    private DJITextView aq = null;
    private DJITextView ar = null;
    private DJILinearLayout as = null;
    private DJIImageView at = null;
    private DJITextView au = null;
    private DJITextView av = null;
    private DJILinearLayout aw = null;
    private DJILinearLayout ax = null;
    private DJITextView ay = null;
    private DJILinearLayout az = null;
    private boolean bA = false;
    private int bB = 0;
    private int bC = 0;
    private int bD = 0;
    private SDCardState bE = SDCardState.OTHER;
    private int bF = 0;
    private MODE bG = MODE.a;
    private float bH = AutoScrollHelper.NO_MAX;
    private boolean bI = false;
    private IMU_INITFAIL_REASON bJ = IMU_INITFAIL_REASON.None;
    private boolean bK = false;
    private CHANNEL_STATUS bL = CHANNEL_STATUS.OTHER;
    private SIGNAL_STATUS bM = SIGNAL_STATUS.OTHER;
    private int bN = 0;
    private DJILinearLayout bO = null;
    private DJILinearLayout bP = null;
    private DJITextView bQ = null;
    private DJITextView bR = null;
    private b bS;
    private b bU = null;
    private ObjectAnimator bV = null;
    private boolean bW = false;
    private DEFORM_MODE bX;
    private b bY;
    private int bZ = 1;
    private DJITextView ba = null;
    private DJITextView bb = null;
    private DJILinearLayout bc;
    private DJITextView bd;
    private DJILinearLayout be;
    private DJITextView bf;
    private DJITextView bg;
    private DJILinearLayout bh;
    private DJITextView bi;
    private DJITextView bj;
    private OnClickListener bk = null;
    private a bl = null;
    private boolean bm = true;
    private boolean bn = true;
    private boolean bo = true;
    private boolean bp = true;
    private boolean bq = true;
    private DJIGenSettingDataManager$c br = null;
    private b bs = null;
    private int bt = -1;
    private c bu = null;
    private a$c bv = null;
    private int bw = 0;
    private boolean bx = false;
    private boolean by = false;
    private FLYC_STATE bz = FLYC_STATE.OTHER;
    private int ca = 1;
    private final String[] cb = new String[]{"g_status.topology_verify.user_interface.imu_status_0", "g_status.topology_verify.user_interface.mag_status_0"};
    private final int x = -1;
    private final int y = 0;
    private final int z = 1;

    static /* synthetic */ class AnonymousClass11 {
        static final /* synthetic */ int[] a = new int[dji.midware.data.config.P3.a.values().length];

        static {
            try {
                a[dji.midware.data.config.P3.a.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    private static final class a extends Handler {
        private final WeakReference<d> a;

        public a(d dVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dVar);
        }

        public void handleMessage(Message message) {
            d dVar = (d) this.a.get();
            if (dVar != null && dVar.isShowing()) {
                int intValue;
                switch (message.what) {
                    case 4096:
                        dVar.H();
                        return;
                    case 4097:
                        dVar.K();
                        return;
                    case 4098:
                        dVar.J();
                        return;
                    case 4099:
                        dVar.a((a$d) message.obj);
                        return;
                    case 4100:
                        dVar.I();
                        return;
                    case 4101:
                        dVar.a(0, null);
                        return;
                    case d.q /*4102*/:
                        dVar.a(1, (dji.midware.data.config.P3.a) message.obj);
                        return;
                    case d.r /*4103*/:
                        dVar.j();
                        return;
                    case d.s /*4104*/:
                        dVar.d(message.arg1);
                        return;
                    case d.t /*4105*/:
                        intValue = dji.midware.data.manager.P3.d.read(d.cc).value.intValue();
                        String[] stringArray = dVar.getContext().getResources().getStringArray(R.array.cc);
                        if (intValue < 0 || intValue >= stringArray.length) {
                            Message message2 = new Message();
                            message2.what = d.u;
                            dVar.bl.sendMessageDelayed(message2, 2000);
                            return;
                        }
                        dVar.aY.setText(stringArray[intValue]);
                        return;
                    case d.u /*4106*/:
                        dVar.O();
                        return;
                    case d.v /*4107*/:
                        dVar.k();
                        return;
                    case d.w /*4108*/:
                        if (dji.pilot.fpv.d.b.m()) {
                            intValue = dji.midware.data.manager.P3.d.read(dVar.cb[0]).value.intValue();
                            if (dVar.ca != intValue) {
                                dVar.ca = intValue;
                                dVar.N();
                            }
                            intValue = dji.midware.data.manager.P3.d.read(dVar.cb[1]).value.intValue();
                            if (dVar.bZ != intValue) {
                                dVar.bZ = intValue;
                                dVar.G();
                            }
                            sendEmptyMessageDelayed(d.v, 2000);
                            return;
                        }
                        return;
                    case 8192:
                        dVar.b(message.arg1);
                        return;
                    case 8193:
                        dVar.c(message.arg1);
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

    private static void p() {
        j = 0;
    }

    public d(Context context) {
        super(context);
        q();
    }

    private void q() {
        this.g.createStageView(R.layout.fpv_checklist_view, R.string.fpv_checklist_title, false);
        s();
        u();
        v();
    }

    private void r() {
        b a = b.a(this.N, (int) R.string.app_tip, (int) R.string.fpv_checklist_upgrade_exit_tip, (int) R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }, R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ d a;

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

    private void s() {
        this.bl = new a(this);
        this.bk = new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.xd == id) {
                    if (this.a.aw.isShown()) {
                        this.a.aw.go();
                    } else {
                        this.a.aw.show();
                    }
                } else if (R.id.xb == id) {
                    dji.thirdparty.a.c.a().e(dji.pilot.fpv.topbar.DJIFpvTopBaseView.a.Mc_Sensor);
                    dji.thirdparty.a.c.a().e(dji.pilot.newfpv.topbar.DJIFpvTopBarBaseView.a.Mc_Sensor);
                } else if (R.id.x9 == id) {
                    if (dji.pilot.fpv.d.b.l(null)) {
                        this.a.r();
                    } else {
                        this.a.g.createStageView(R.layout.fpv_checklist_upgrade_view, R.string.fpv_checklist_module_check, true);
                    }
                } else if (R.id.yi == id) {
                    e.a("FPV_AircraftState_Button_RCMode");
                    dji.thirdparty.a.c.a().e(dji.pilot.fpv.topbar.DJIFpvTopBaseView.a.Rc_Mode);
                    dji.thirdparty.a.c.a().e(dji.pilot.newfpv.topbar.DJIFpvTopBarBaseView.a.Rc_Mode);
                } else if (R.id.z3 == id) {
                    e.a("FPV_AircraftState_RemainingSDCardCapacity_Button_Format");
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
                } else if (R.id.xy == id) {
                    if (!DataOsdGetPushCommon.getInstance().isGetted()) {
                        Toast.makeText(this.a.N, R.string.fpv_tip_disconnect, 0).show();
                    } else if (dji.pilot.fpv.d.b.m()) {
                        this.a.dismiss();
                        dji.thirdparty.a.c.a().e(dji.pilot.fpv.topbar.DJIFpvTopBaseView.a.Flyc_Compass);
                        dji.thirdparty.a.c.a().e(dji.pilot.newfpv.topbar.DJIFpvTopBarBaseView.a.Flyc_Compass);
                    } else {
                        e.a("FPV_AircraftState_Compass_Button_Calibrate");
                        this.a.a(3, this.a.N.getString(R.string.fpv_checklist_calibration_confirm));
                    }
                } else if (R.id.xf == id) {
                    if (DataOsdGetPushCommon.getInstance().isGetted()) {
                        e.a("FPV_AircraftState_Compass_Button_Calibrate");
                        this.a.a(3, this.a.N.getString(R.string.fpv_checklist_calibration_confirm));
                        return;
                    }
                    Toast.makeText(this.a.N, R.string.fpv_tip_disconnect, 0).show();
                } else if (R.id.sk == id) {
                    this.a.dismiss();
                } else if (R.id.x1 == id) {
                    MarginLayoutParams marginLayoutParams;
                    if (this.a.al.isShown()) {
                        this.a.al.go();
                        this.a.ak.go();
                        this.a.ah.setBackgroundResource(R.drawable.selector_gen_radius_corner);
                        marginLayoutParams = (MarginLayoutParams) this.a.ah.getLayoutParams();
                        marginLayoutParams.bottomMargin = dji.pilot.fpv.model.b.a(this.a.N, R.dimen.n3);
                        this.a.ah.setLayoutParams(marginLayoutParams);
                        return;
                    }
                    this.a.ah.setBackgroundResource(R.drawable.selector_gen_top_corner);
                    marginLayoutParams = (MarginLayoutParams) this.a.ah.getLayoutParams();
                    marginLayoutParams.bottomMargin = 0;
                    this.a.ah.setLayoutParams(marginLayoutParams);
                    this.a.al.show();
                    this.a.ak.show();
                } else if (R.id.xj == id) {
                    if (this.a.ad.getText().equals(this.a.getContext().getString(R.string.fpv_checklist_check_device_locked))) {
                        if (this.a.bS == null) {
                            this.a.bS = new b(this.a.getContext(), false);
                            this.a.bS.f();
                            this.a.bS.a((int) R.string.app_tip);
                            this.a.bS.b((int) R.string.device_lock_alert_new);
                            this.a.bS.d((int) R.string.app_enter);
                            this.a.bS.a(new DialogInterface.OnClickListener(this) {
                                final /* synthetic */ AnonymousClass13 a;

                                {
                                    this.a = r1;
                                }

                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    dji.thirdparty.a.c.a().e(dji.pilot.publics.control.a.e.b);
                                }
                            });
                        }
                        if (!this.a.bS.isShowing()) {
                            this.a.bS.show();
                            return;
                        }
                        return;
                    }
                    this.a.g.createStageView(R.layout.fpv_checklist_upgrade_view, R.string.fpv_checklist_module_check, true);
                } else if (R.id.zn == id) {
                    if (DataFlycGetPushDeformStatus.getInstance().getDeformMode() == DEFORM_MODE.Pack) {
                        DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.UnPackMode).start(new dji.midware.e.d(this) {
                            final /* synthetic */ AnonymousClass13 a;

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
                            final /* synthetic */ AnonymousClass13 a;

                            {
                                this.a = r1;
                            }

                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }, this.a.N.getString(R.string.app_enter), new DialogInterface.OnClickListener(this) {
                            final /* synthetic */ AnonymousClass13 a;

                            {
                                this.a = r1;
                            }

                            public void onClick(DialogInterface dialogInterface, int i) {
                                DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.PackMode).start(new dji.midware.e.d(this) {
                                    final /* synthetic */ AnonymousClass4 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void onSuccess(Object obj) {
                                        this.a.a.a.bl.sendMessage(this.a.a.a.bl.obtainMessage(d.s, R.string.mc_param_pack_mode_success, 0));
                                    }

                                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                                        switch (AnonymousClass11.a[aVar.ordinal()]) {
                                            case 1:
                                                this.a.a.a.bl.sendMessage(this.a.a.a.bl.obtainMessage(d.s, R.string.code_timeout, 0));
                                                return;
                                            default:
                                                this.a.a.a.bl.sendMessage(this.a.a.a.bl.obtainMessage(d.s, R.string.mc_param_pack_mode_failed, 0));
                                                return;
                                        }
                                    }
                                });
                                dialogInterface.dismiss();
                            }
                        }).show();
                    }
                } else if (R.id.xt == id) {
                    this.a.g.createStageView(R.layout.fpv_checklist_redundancy_view, R.string.fpv_checklist_redundancy_desc, true);
                } else if (R.id.xh == id) {
                    this.a.dismiss();
                    dji.thirdparty.a.c.a().e(dji.pilot.fpv.activity.j.a.open);
                } else if (R.id.yq == id) {
                    dji.pilot.publics.widget.a.a(this.a.getContext(), this.a.getContext().getString(R.string.fpv_checklist_pm820_battery_status), this.a.l(), this.a.getContext().getString(R.string.ok), new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ AnonymousClass13 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
                } else if (R.id.y1 == id) {
                    this.a.g.createStageView(R.layout.fpv_checklist_escm_view, R.string.fpv_checklist_escm_desc, true);
                } else if (R.id.zj == id) {
                    this.a.a(true);
                } else if (R.id.zm == id) {
                    this.a.a(false);
                } else if (R.id.yg == id) {
                    this.a.t();
                }
            }
        };
        this.br = new DJIGenSettingDataManager$c(this) {
            final /* synthetic */ d a;

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
        this.bv = new a$c(this) {
            final /* synthetic */ d a;

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
                this.a.F();
            }
        };
    }

    private void t() {
        if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
            Toast.makeText(this.N.getApplicationContext(), R.string.vision_selfcal_motorup_tip, 0).show();
        } else {
            b.a(this.N, (int) R.string.app_tip, (int) R.string.vision_selfcal_tip, (int) R.string.app_cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }, (int) R.string.app_enter, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    dji.thirdparty.a.c.a().e(dji.pilot.fpv.model.n.a.g);
                }
            }).show();
        }
    }

    private void u() {
        this.Y = (DJIGenFirstStageView) findViewById(R.id.wz);
        this.Z = (DJITitleScrollView) findViewById(R.id.x0);
        this.aa = (DJILinearLayout) findViewById(R.id.xj);
        this.ab = (DJITextView) findViewById(R.id.xn);
        this.ac = (DJIImageView) findViewById(R.id.xo);
        this.ad = (DJITextView) findViewById(R.id.xp);
        this.ae = (DJIImageView) findViewById(R.id.xq);
        this.af = (DJIImageView) findViewById(R.id.xm);
        this.ag = (DJITextView) findViewById(R.id.zn);
        this.ah = (DJILinearLayout) findViewById(R.id.x1);
        this.ai = (DJITextView) findViewById(R.id.x3);
        this.aj = (DJITextView) findViewById(R.id.x4);
        this.ak = (DJIImageView) findViewById(R.id.x5);
        this.al = (DJILinearLayout) findViewById(R.id.x6);
        this.am = (DJITextView) findViewById(R.id.x7);
        this.an = (DJILinearLayout) findViewById(R.id.x8);
        this.ao = (DJITextView) findViewById(R.id.x9);
        this.ap = (DJILinearLayout) findViewById(R.id.x_);
        this.ar = (DJITextView) findViewById(R.id.xb);
        this.aq = (DJITextView) findViewById(R.id.xa);
        this.as = (DJILinearLayout) findViewById(R.id.xc);
        this.at = (DJIImageView) findViewById(R.id.xd);
        this.av = (DJITextView) findViewById(R.id.xe);
        this.au = (DJITextView) findViewById(R.id.xf);
        this.aw = (DJILinearLayout) findViewById(R.id.xg);
        this.ax = (DJILinearLayout) findViewById(R.id.xx);
        this.ay = (DJITextView) findViewById(R.id.xy);
        this.az = (DJILinearLayout) findViewById(R.id.xw);
        this.aA = (DJILinearLayout) findViewById(R.id.y1);
        this.aB = (DJITextView) findViewById(R.id.y4);
        this.aC = (DJIImageView) findViewById(R.id.y5);
        this.aD = (DJITextView) findViewById(R.id.z8);
        this.aE = (DJILinearLayout) findViewById(R.id.z9);
        this.aF = (DJILinearLayout) findViewById(R.id.z5);
        this.aG = (DJILinearLayout) findViewById(R.id.y0);
        this.aH = (DJILinearLayout) findViewById(R.id.yb);
        this.aI = (DJITextView) findViewById(R.id.ye);
        this.aJ = (DJILinearLayout) findViewById(R.id.yi);
        this.aK = (DJITextView) findViewById(R.id.yl);
        this.aL = (DJITextView) findViewById(R.id.y9);
        this.aM = (DJILinearLayout) findViewById(R.id.y_);
        this.aN = (DJILinearLayout) findViewById(R.id.yw);
        this.aO = (DJITextView) findViewById(R.id.yz);
        this.aP = (DJILinearLayout) findViewById(R.id.ym);
        this.aQ = (DJITextView) findViewById(R.id.yp);
        this.aR = (DJILinearLayout) findViewById(R.id.ys);
        this.aS = (DJITextView) findViewById(R.id.yv);
        this.aT = (DJILinearLayout) findViewById(R.id.z0);
        this.aU = (DJITextView) findViewById(R.id.z4);
        this.aV = (DJITextView) findViewById(R.id.z3);
        this.aW = (DJILinearLayout) findViewById(R.id.xt);
        this.aW.setOnClickListener(this.bk);
        this.aX = (DJILinearLayout) findViewById(R.id.xr);
        this.aY = (DJITextView) findViewById(R.id.xs);
        this.bc = (DJILinearLayout) findViewById(R.id.zg);
        this.bd = (DJITextView) findViewById(R.id.zj);
        this.bd.setVisibility(8);
        this.be = (DJILinearLayout) findViewById(R.id.zk);
        this.bf = (DJITextView) findViewById(R.id.zl);
        this.bg = (DJITextView) findViewById(R.id.zm);
        this.bg.getPaint().setFlags(8);
        this.bh = (DJILinearLayout) findViewById(R.id.za);
        this.bi = (DJITextView) findViewById(R.id.zd);
        this.bj = (DJITextView) findViewById(R.id.zf);
        if (!dji.pilot.publics.e.a.d(null) || DataOsdGetPushCommon.getInstance().getFlycVersion() <= 15) {
            this.bc.setVisibility(8);
            this.be.setVisibility(8);
            this.bh.setVisibility(8);
        } else {
            this.bc.setVisibility(0);
            this.be.setVisibility(0);
            this.bh.setVisibility(0);
        }
        this.bO = (DJILinearLayout) findViewById(R.id.yq);
        this.bP = (DJILinearLayout) findViewById(R.id.xh);
        this.bQ = (DJITextView) findViewById(R.id.yr);
        this.bR = (DJITextView) findViewById(R.id.xi);
        this.bO.setOnClickListener(this.bk);
        this.bP.setOnClickListener(this.bk);
        if (dji.pilot.fpv.d.b.o()) {
            this.aW.show();
            this.ay.setText(R.string.fpv_checklist_compass_detail);
        } else {
            this.aW.go();
            this.ay.setText(R.string.fpv_checklist_calibration);
        }
        if (dji.pilot.fpv.d.b.n()) {
            this.aX.show();
        } else {
            this.aX.go();
        }
        this.aZ = (DJILinearLayout) findViewById(R.id.yf);
        this.ba = (DJITextView) findViewById(R.id.yg);
        this.bb = (DJITextView) findViewById(R.id.yh);
    }

    private void v() {
        this.aa.setOnClickListener(this.bk);
        this.ag.setOnClickListener(this.bk);
        this.ao.setOnClickListener(this.bk);
        this.ar.setOnClickListener(this.bk);
        this.at.setOnClickListener(this.bk);
        this.au.setOnClickListener(this.bk);
        this.ay.setOnClickListener(this.bk);
        this.ah.setOnClickListener(this.bk);
        this.aJ.setOnClickListener(this.bk);
        this.ay.setOnClickListener(this.bk);
        this.aV.setOnClickListener(this.bk);
        this.f.setOnClickListener(this.bk);
        this.aA.setOnClickListener(this.bk);
        this.bd.setOnClickListener(this.bk);
        this.bg.setOnClickListener(this.bk);
        this.ba.setOnClickListener(this.bk);
        f.a(this.aZ, dji.pilot.fpv.d.b.n(i.getInstance().c()) ? 0 : 8);
        this.Y.setLifeListener(this);
    }

    protected void a(int i, int i2, int i3) {
        super.a(i, i2, i3);
    }

    private void a(int i) {
        if (2 == i) {
            this.aV.setEnabled(false);
        }
    }

    private void a(int i, int i2, dji.midware.data.config.P3.a aVar) {
        if (2 == i) {
            this.aV.setEnabled(true);
            if (i2 == 0) {
                a(2, this.N.getString(R.string.fpv_gensetting_format_sdcard_success));
            } else {
                a(2, this.N.getString(R.string.fpv_gensetting_format_sdcard_fail));
            }
        }
    }

    private void a(int i, dji.midware.data.config.P3.a aVar) {
        this.ay.setEnabled(true);
        this.au.setEnabled(true);
        if (i == 0) {
            a(2, this.N.getString(R.string.fpv_checklist_calibration_success));
        } else {
            a(2, this.N.getString(R.string.fpv_checklist_calibration_fail));
        }
    }

    private void a(int i, String str) {
        if (this.bs == null) {
            this.bs = b.a(this.N, (int) R.string.app_tip, (int) R.string.fpv_playback_del_image, (int) R.string.btn_dlg_no, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.x();
                    this.a.bt = -1;
                }
            }, (int) R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.w();
                }
            });
            this.bs.setCancelable(true);
            this.bs.setCanceledOnTouchOutside(true);
        }
        if (this.bs != null && !this.bs.isShowing()) {
            this.bt = i;
            this.bs.b(str);
            if (this.bt == 2) {
                this.bs.d((int) R.string.app_isee);
                this.bs.b();
            } else {
                this.bs.d((int) R.string.btn_dlg_no);
                this.bs.e(R.string.btn_dlg_yes);
            }
            this.bs.show();
        }
    }

    private void w() {
        x();
        if (this.bt == 0) {
            DJIGenSettingDataManager.getInstance().g();
        } else if (this.bt == 1) {
            DJIGenSettingDataManager.getInstance().h();
        } else if (this.bt != 2 && this.bt == 3) {
            DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.Calibration).start(null);
            c();
        }
        this.bt = -1;
    }

    private void x() {
        if (this.bs != null && this.bs.isShowing()) {
            this.bs.dismiss();
        }
    }

    protected boolean a() {
        super.a();
        return true;
    }

    protected void onCreate(Bundle bundle) {
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            a((int) (((double) DJIBaseActivity.screenWidth) * HeatmapTileProvider.DEFAULT_OPACITY), DJIBaseActivity.screenHeight, 0, 3, true, true);
        } else {
            a(DJIBaseActivity.screenWidth, DJIBaseActivity.screenHeight, 0, 17, true, true);
        }
    }

    protected void onStart() {
        super.onStart();
        dji.setting.ui.battery.a.a();
        C();
        O();
        this.Z.scrollTo(0, 0);
        DJIGenSettingDataManager.getInstance().a(this.br);
        dji.pilot.battery.a.a.getInstance().a(this.bv);
        dji.thirdparty.a.c.a().a(this);
        k();
    }

    protected void onStop() {
        dji.pilot.battery.a.a.getInstance().b(this.bv);
        DJIGenSettingDataManager.getInstance().b(this.br);
        dji.pilot.battery.a.a.getInstance().f();
        dji.thirdparty.a.c.a().d(this);
        dji.setting.ui.battery.a.b();
        super.onStop();
    }

    public void d() {
        B();
        super.d();
    }

    private void y() {
        DataRcGetMaster.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.bl.obtainMessage(8193, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.bl.obtainMessage(8193, 0, 0).sendToTarget();
            }
        });
    }

    private void z() {
        DataRcGetControlMode.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.bl.obtainMessage(8192, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.bl.obtainMessage(8192, 0, 0).sendToTarget();
            }
        });
    }

    private void A() {
        DataRcGetSlaveMode.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.bl.obtainMessage(8192, 1, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.bl.obtainMessage(8192, 0, 0).sendToTarget();
            }
        });
    }

    private void B() {
        y();
        onEventBackgroundThread(DataFlycGetPushDeformStatus.getInstance());
    }

    public void onEventMainThread(RcMasterSlaveView.c cVar) {
        if (cVar != null) {
            a(cVar.a);
        }
    }

    private void a(MODE mode) {
        if (mode != MODE.b) {
            this.ay.show();
            this.au.show();
            return;
        }
        this.ay.go();
        this.au.go();
    }

    private void C() {
        this.bm = true;
        this.bq = true;
        this.bp = true;
        this.bn = true;
        a(dji.pilot.c.d.b);
        this.by = false;
        G();
        this.ay.setEnabled(true);
        this.au.setEnabled(true);
        this.bN = 0;
        N();
        this.bz = FLYC_STATE.OTHER;
        this.bA = false;
        a(this.bz, this.bA);
        this.bH = AutoScrollHelper.NO_MAX;
        F();
        this.bB = 0;
        this.aQ.setText(this.N.getString(R.string.fpv_percent, new Object[]{Integer.valueOf(this.bB)}));
        this.bD = 0;
        this.aS.setText(this.N.getString(R.string.fpv_percent, new Object[]{Integer.valueOf(this.bD)}));
        this.bF = DataCameraGetPushStateInfo.getInstance().getSDCardFreeSize();
        this.aU.setText(this.N.getString(R.string.fpv_checklist_sd_valume, new Object[]{Integer.valueOf(this.bF)}));
        this.aV.go();
        this.aV.setEnabled(true);
        this.aK.setText(R.string.fpv_default_str);
        E();
        onEventMainThread(DataGimbalGetPushParams.getInstance());
        if (dji.pilot.fpv.d.b.l(null)) {
            this.bL = CHANNEL_STATUS.OTHER;
            onEventMainThread(DataWifiGetPushElecSignal.getInstance());
        } else {
            this.bM = SIGNAL_STATUS.OTHER;
            onEventMainThread(DataOsdGetPushChannalStatus.getInstance());
        }
        this.bw = 0;
        if (DataOsdGetPushCommon.getInstance().isGetted()) {
            H();
        }
        if (DataOsdGetPushHome.getInstance().isGetted()) {
            onEventMainThread(DataOsdGetPushHome.getInstance());
        }
        if (DataCenterGetPushBatteryCommon.getInstance().isGetted()) {
            I();
        }
        if (DataRcGetPushBatteryInfo.getInstance().isGetted()) {
            J();
        }
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            K();
            if (DataCameraGetPushStateInfo.getInstance().isOK()) {
                this.bw |= 2;
            } else {
                this.bw &= -3;
            }
        }
        if (DataFlycGetPushSmartBattery.getInstance().isGetted()) {
            if ((DataFlycGetPushSmartBattery.getInstance().getStatus() & 2048) == 2048) {
                this.bw |= 128;
            } else {
                this.bw &= -129;
            }
        }
        if (DataDm368_gGetPushCheckStatus.getInstance().isGetted()) {
            if (DataDm368_gGetPushCheckStatus.getInstance().isOK()) {
                this.bw |= 1;
            } else {
                this.bw &= -2;
            }
        }
        if (DataCenterGetPushCheckStatus.getInstance().isGetted()) {
            if (DataCenterGetPushCheckStatus.getInstance().isOK()) {
                this.bw |= 4;
            } else {
                this.bw &= -5;
            }
        }
        if (DataBatteryGetPushCheckStatus.getInstance().isGetted()) {
            if (DataBatteryGetPushCheckStatus.getInstance().isOK()) {
                this.bw |= 8;
            } else {
                this.bw &= -9;
            }
        }
        if (DataOsdGetPushCheckStatus.getInstance().isGetted()) {
            if (DataOsdGetPushCheckStatus.getInstance().isOK()) {
                this.bw |= 16;
            } else {
                this.bw &= -17;
            }
        }
        if (DataFlycGetPushCheckStatus.getInstance().isGetted()) {
            onEventMainThread(DataFlycGetPushCheckStatus.getInstance());
        }
        if (DataGimbalGetPushCheckStatus.getInstance().isGetted()) {
            if (DataGimbalGetPushCheckStatus.getInstance().hasException()) {
                this.bw |= 64;
            } else {
                this.bw &= -65;
            }
        }
        if (Data1860GetPushCheckStatus.getInstance().isGetted()) {
            if (Data1860GetPushCheckStatus.getInstance().hasException()) {
                this.bw |= 2048;
            } else {
                this.bw &= -2049;
            }
        }
        if (Data2100GetPushCheckStatus.getInstance().isGetted()) {
            if (Data2100GetPushCheckStatus.getInstance().hasException()) {
                this.bw |= 4096;
            } else {
                this.bw &= -4097;
            }
        }
        if (DataCenterGetPushBatteryCommon.getInstance().isGetted()) {
            int errorType = DataCenterGetPushBatteryCommon.getInstance().getErrorType();
            dji.pilot.battery.a.c cVar = new dji.pilot.battery.a.c();
            cVar.a(errorType);
            if (cVar.l() != (byte) 0) {
                this.bw |= 256;
            } else {
                this.bw &= -257;
            }
            if (DataCenterGetPushBatteryCommon.getInstance().isNeedStudy()) {
                this.bw |= 512;
            } else {
                this.bw &= -513;
            }
        }
        b(false);
        if (dji.pilot.publics.control.a.getInstance(this.N.getApplicationContext()).o()) {
            if (dji.pilot.publics.control.a.getInstance(this.N.getApplicationContext()).l()) {
                this.an.show();
            } else {
                this.an.go();
            }
            this.bx = true;
            this.ab.show();
            this.ab.setText(R.string.fpv_checklist_need_upgrade);
        } else {
            this.an.go();
            this.bx = false;
            if (ServiceManager.getInstance().isConnected()) {
                this.ab.show();
                this.ab.setText(R.string.check_module_firmware_latest);
            } else {
                this.ab.go();
            }
        }
        L();
        M();
        D();
        a(i.getInstance().c());
        onEventMainThread(DataEyeGetPushFunctionList.getInstance());
        a(Data2100GetPushCheckStatus.getInstance(), true);
    }

    private void D() {
        final String[] strArr = new String[]{dji.midware.data.params.P3.a.i, dji.midware.data.params.P3.a.j};
        new DataFlycGetParams().setInfos(strArr).start(new dji.midware.e.d(this) {
            final /* synthetic */ d b;

            public void onSuccess(Object obj) {
                this.b.bl.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        float floatValue = dji.midware.data.manager.P3.d.read(strArr[0]).value.floatValue();
                        float floatValue2 = dji.midware.data.manager.P3.d.read(strArr[1]).value.floatValue();
                        float f = floatValue - floatValue2;
                        DJILogHelper.getInstance().LOGD(getClass().getName(), "[total time: " + floatValue + " last check time: " + floatValue2, false, true);
                        if (floatValue == 0.0f) {
                            this.a.b.bc.setVisibility(8);
                            this.a.b.be.setVisibility(8);
                        } else if (dji.pilot.fpv.d.b.r(null)) {
                            this.a.b.bc.setVisibility(0);
                            this.a.b.be.setVisibility(0);
                        }
                        if (f >= 30000.0f) {
                            this.a.b.bf.setText(this.a.b.getContext().getString(R.string.fpv_checklist_struct_check_tip, new Object[]{Integer.valueOf((int) (f / 60.0f))}));
                            this.a.b.bg.setVisibility(8);
                            this.a.b.bd.setVisibility(0);
                            return;
                        }
                        this.a.b.bf.setText(this.a.b.getContext().getString(R.string.fpv_checklist_struct_check_not_need_tip, new Object[]{Integer.valueOf((int) (DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition - (f / 60.0f)))}));
                        this.a.b.bg.setVisibility(0);
                        this.a.b.bd.setVisibility(8);
                    }
                }, 100);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(getClass().getName(), "updateStructCheckWidget onFailure: " + aVar, false, true);
            }
        });
    }

    private void a(boolean z) {
        if (this.bU == null) {
            this.bU = b.a(getContext(), (int) R.string.fpv_checklist_struct_check_tip_action, (int) R.string.fpv_checklist_struct_check_done, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    DataFlycSetHaveCheckedStruct.getInstance().start(new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            DJILogHelper.getInstance().LOGD(getClass().getSimpleName(), "DataFlycSetHaveCheckedStruct onSuccess", false, true);
                            this.a.a.D();
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            DJILogHelper.getInstance().LOGD(getClass().getSimpleName(), "DataFlycSetHaveCheckedStruct onFailure: " + aVar, false, true);
                        }
                    });
                    dialogInterface.dismiss();
                }
            }, (int) R.string.cancel, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            this.bU.b((int) R.string.fpv_checklist_struct_check_guide_content);
            this.bU.f();
            this.bU.c(3);
        }
        if (z) {
            this.bU.e();
            this.bU.e(R.string.cancel);
        } else {
            this.bU.d();
            this.bU.e(R.string.ok);
        }
        this.bU.show();
    }

    private void E() {
        if (dji.pilot.fpv.d.b.b()) {
            this.ah.show();
            this.ak.show();
            this.al.show();
            this.ah.setBackgroundResource(R.drawable.selector_gen_radius_corner);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.ah.getLayoutParams();
            marginLayoutParams.bottomMargin = 0;
            this.ah.setLayoutParams(marginLayoutParams);
            return;
        }
        this.ah.go();
        this.ak.go();
        this.al.go();
    }

    public void onEventMainThread(DJIGenSettingDataManager$b dJIGenSettingDataManager$b) {
        if (dJIGenSettingDataManager$b == DJIGenSettingDataManager$b.TEMPERATURE_UNIT_CHANGED) {
            this.aO.setText(dji.pilot.fpv.d.b.a(this.N, this.bH, false));
        }
    }

    private void F() {
        float temperature;
        if (dji.pilot.publics.e.a.d(null)) {
            temperature = ((float) DataSmartBatteryGetPushDynamicData.getInstance().getTemperature()) / 10.0f;
        } else {
            temperature = dji.pilot.battery.a.a.getInstance().w();
        }
        if (this.bH != temperature) {
            this.bH = temperature;
            this.aO.setText(dji.pilot.fpv.d.b.a(this.N, temperature, false));
        }
        dji.pilot.battery.a.c t = dji.pilot.battery.a.a.getInstance().t();
        if (t.f() || t.g()) {
            this.aO.setTextColor(this.N.getResources().getColor(R.color.gj));
        } else {
            this.aO.setTextColor(this.N.getResources().getColor(R.color.om));
        }
    }

    private void G() {
        if (dji.pilot.fpv.d.b.m()) {
            switch (this.bZ) {
                case 0:
                case 1:
                    this.as.go();
                    this.aw.go();
                    this.ax.show();
                    return;
                case 2:
                case 3:
                    if (dji.pilot.c.d.b != MODE.b) {
                        this.ay.show();
                    }
                    if (this.bZ == 0) {
                        this.av.setText(R.string.checklist_mag_stat_0);
                    } else if (this.bZ == 2) {
                        this.av.setText(R.string.checklist_mag_stat_2);
                    } else if (this.bZ == 3) {
                        this.av.setText(dji.pilot.fpv.d.b.o() ? R.string.checklist_mag_stat_3_1 : R.string.checklist_mag_stat_3_2);
                    }
                    this.au.show();
                    this.as.show();
                    this.aw.go();
                    this.ax.go();
                    return;
                case 4:
                    if (dji.pilot.c.d.b != MODE.b) {
                        this.ay.show();
                    }
                    this.av.setText(R.string.checklist_mag_stat_4);
                    this.au.go();
                    this.as.show();
                    this.aw.go();
                    this.ax.go();
                    return;
                default:
                    return;
            }
        } else if (this.by) {
            if (dji.pilot.c.d.b != MODE.b) {
                this.ay.show();
                this.au.show();
            }
            this.as.show();
            this.aw.go();
            this.ax.go();
        } else {
            this.as.go();
            this.aw.go();
            this.ax.show();
        }
    }

    private void a(FLYC_STATE flyc_state, boolean z) {
        this.aI.setText(dji.pilot.fpv.d.b.a(flyc_state, z, false)[0]);
    }

    private void H() {
        boolean compassError;
        DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
        if (!dji.pilot.fpv.d.b.m()) {
            compassError = instance.getCompassError();
            if (this.bm || this.by != compassError) {
                this.by = compassError;
                G();
            }
        }
        FLYC_STATE flycState = instance.getFlycState();
        boolean isVisionUsed = instance.isVisionUsed();
        if (!(!this.bm && this.bz == flycState && this.bA == isVisionUsed)) {
            this.bz = flycState;
            this.bA = isVisionUsed;
            a(flycState, isVisionUsed);
        }
        int voltageWarning = instance.getVoltageWarning();
        if (this.bm || this.bC != voltageWarning) {
            this.bC = voltageWarning;
            if (voltageWarning == 1 || voltageWarning == 2) {
                this.aQ.setTextColor(this.N.getResources().getColor(R.color.gj));
            } else {
                this.aQ.setTextColor(this.N.getResources().getColor(R.color.om));
            }
        }
        isVisionUsed = instance.isAllowImuInitfailReason();
        IMU_INITFAIL_REASON iMUinitFailReason = instance.getIMUinitFailReason();
        if (this.bJ != iMUinitFailReason) {
            compassError = true;
        } else {
            compassError = false;
        }
        if (!(!this.bm && this.bI == isVisionUsed && this.bJ == iMUinitFailReason)) {
            this.bI = isVisionUsed;
            this.bJ = iMUinitFailReason;
            if (isVisionUsed && instance.isImuInitError()) {
                this.bw |= 1024;
            } else {
                this.bw &= -1025;
            }
            b(true);
        }
        if (compassError) {
            if (iMUinitFailReason == IMU_INITFAIL_REASON.GyroBiasTooLarge || iMUinitFailReason == IMU_INITFAIL_REASON.AcceBiasTooLarge) {
                this.bN |= 2;
            } else {
                this.bN &= -3;
            }
            N();
        }
        if (instance.getFlycVersion() >= 15) {
            this.aA.show();
        } else {
            this.aA.go();
        }
        M();
        this.bm = false;
    }

    private void I() {
        int battery = DataFlycGetPushSmartBattery.getInstance().getBattery();
        if (this.bn || battery != this.bB) {
            this.bB = battery;
            if (this.bB < 0) {
                this.bB = 0;
            } else if (this.bB > 100) {
                this.bB = 100;
            }
            this.aQ.setText(this.N.getString(R.string.fpv_percent, new Object[]{Integer.valueOf(this.bB)}));
        }
        this.bn = false;
    }

    private void J() {
        int i = 100;
        int battery = DataRcGetPushBatteryInfo.getInstance().getBattery();
        if (battery < 0) {
            i = 0;
        } else if (battery <= 100) {
            i = battery;
        }
        if (this.bq || this.bD != i) {
            this.bD = i;
            this.aS.setText(this.N.getString(R.string.fpv_percent, new Object[]{Integer.valueOf(i)}));
            if (dji.pilot.fpv.d.b.i(i)) {
                this.aS.setTextColor(this.N.getResources().getColor(R.color.gj));
            } else {
                this.aS.setTextColor(this.N.getResources().getColor(R.color.om));
            }
        }
        this.bq = false;
    }

    private void K() {
        int sDCardFreeSize = DataCameraGetPushStateInfo.getInstance().getSDCardFreeSize();
        SDCardState sDCardState = DataCameraGetPushStateInfo.getInstance().getSDCardState(true);
        if (!(!this.bp && this.bE == sDCardState && this.bF == sDCardFreeSize)) {
            this.bE = sDCardState;
            this.bF = sDCardFreeSize;
            if (sDCardState == SDCardState.Normal) {
                this.aU.setText(this.N.getString(R.string.fpv_checklist_sd_valume, new Object[]{Integer.valueOf(sDCardFreeSize)}));
            } else {
                this.aU.setText(dji.pilot.fpv.d.b.a(sDCardState));
            }
            if (sDCardState == SDCardState.None) {
                this.aV.go();
            } else {
                this.aV.show();
            }
        }
        this.bp = false;
    }

    private void a(a$d dji_pilot_publics_control_a_d) {
        if (dji_pilot_publics_control_a_d != a$d.NO) {
            if (dji.pilot.publics.control.a.getInstance(this.N.getApplicationContext()).l()) {
                this.an.show();
            } else {
                this.an.go();
            }
            this.bx = true;
            this.ab.show();
            this.ab.setText(R.string.fpv_checklist_need_upgrade);
        } else {
            this.an.go();
            this.bx = false;
            this.ab.show();
            this.ab.setText(R.string.check_module_firmware_latest);
        }
        L();
        M();
    }

    private void b(int i) {
        if (i != 1) {
            return;
        }
        if (this.bG != MODE.b) {
            ControlMode controlType = DataRcGetControlMode.getInstance().getControlType();
            if (controlType == ControlMode.b) {
                this.aK.setText(R.string.rc_master_2);
            } else if (controlType == ControlMode.a) {
                this.aK.setText(R.string.rc_master_1);
            } else if (controlType == ControlMode.c) {
                this.aK.setText(R.string.rc_master_3);
            } else if (controlType == ControlMode.d) {
                this.aK.setText(R.string.rc_master_4);
            }
        } else if (DataRcGetSlaveMode.getInstance().getControlType() == DataRcSetSlaveMode.ControlMode.b) {
            this.aK.setText(R.string.rc_slave_2);
        } else {
            this.aK.setText(R.string.rc_slave_1);
        }
    }

    private void c(int i) {
        if (1 == i) {
            MODE mode = DataRcGetMaster.getInstance().getMode();
            this.bG = mode;
            if (mode == MODE.b) {
                A();
            } else {
                z();
            }
        }
    }

    private void b(boolean z) {
        if ((this.bw & U) != 0) {
            this.ac.show();
        } else {
            this.ac.go();
        }
        if (z) {
            M();
        }
    }

    private void L() {
        if (this.bV == null) {
            this.bV = (ObjectAnimator) AnimatorInflater.loadAnimator(this.N, R.animator.c);
            this.bV.setTarget(this.af);
        }
        if (dji.pilot.publics.control.rc.b.getInstance().h()) {
            this.af.show();
            this.bV.start();
        } else {
            this.bV.cancel();
            this.af.setAlpha(1.0f);
            this.af.setImageAlpha(255);
            this.af.go();
        }
        M();
    }

    private void M() {
        if (this.ac.getVisibility() == 0) {
            this.ad.show();
        } else {
            this.ad.go();
        }
        MotorStartFailedCause motorStartFailedCause = MotorStartFailedCause.None;
        if (!DataOsdGetPushCommon.getInstance().isMotorUp()) {
            motorStartFailedCause = DataOsdGetPushCommon.getInstance().getMotorStartCauseNoStartAction();
        }
        if (this.bx || this.ac.getVisibility() == 0 || this.af.getVisibility() == 0 || !(motorStartFailedCause == MotorStartFailedCause.None || MotorStartFailedCause.OTHER == motorStartFailedCause)) {
            this.ae.show();
            this.aa.setEnabled(true);
        } else {
            this.ae.go();
            this.aa.setEnabled(false);
        }
        if (motorStartFailedCause != MotorStartFailedCause.DeviceLocked || dji.pilot.publics.control.a.getInstance().l()) {
            this.ad.setText(R.string.fpv_checklist_check_abnormal);
            return;
        }
        this.ac.show();
        this.ad.setText(R.string.fpv_checklist_check_device_locked);
        this.ad.show();
        if (this.ab.isShown()) {
            this.ab.go();
        }
        if (this.ae.isShown()) {
            this.ae.go();
        }
    }

    public void onEventMainThread(DataOsdGetPushChannalStatus dataOsdGetPushChannalStatus) {
        CHANNEL_STATUS channelStatus = dataOsdGetPushChannalStatus.getChannelStatus();
        if (channelStatus != this.bL) {
            this.bL = channelStatus;
            if (dji.pilot.fpv.d.b.a(channelStatus)) {
                this.aL.setText(R.string.fpv_checklist_status_poor);
                this.aL.setTextColor(this.N.getResources().getColor(R.color.gj));
                this.aM.show();
                return;
            }
            this.aL.setText(R.string.fpv_checklist_status_good);
            this.aL.setTextColor(this.N.getResources().getColor(R.color.om));
            this.aM.go();
        }
    }

    public void onEventMainThread(DataWifiGetPushElecSignal dataWifiGetPushElecSignal) {
        SIGNAL_STATUS signalStatus = dataWifiGetPushElecSignal.getSignalStatus();
        if (this.bM != signalStatus) {
            this.bM = signalStatus;
            if (dji.pilot.fpv.d.b.a(signalStatus)) {
                this.aL.setText(R.string.fpv_checklist_status_poor);
                this.aL.setTextColor(this.N.getResources().getColor(R.color.gj));
                this.aM.show();
                return;
            }
            this.aL.setText(R.string.fpv_checklist_status_good);
            this.aL.setTextColor(this.N.getResources().getColor(R.color.om));
            this.aM.go();
        }
    }

    public void onEventMainThread(ProductType productType) {
        a(productType);
    }

    private void a(ProductType productType) {
        int i = 0;
        if (dji.pilot.fpv.d.b.g(productType)) {
            this.ag.show();
        } else {
            this.ag.go();
        }
        if (dji.pilot.fpv.d.b.r(null)) {
            this.aP.go();
            this.bO.show();
            this.bP.go();
        } else {
            this.aP.show();
            this.bO.go();
            this.bP.go();
        }
        if (!i.getInstance().c().isCompleteMachine()) {
            if (DataOsdGetPushCommon.getInstance().getDroneType() == DroneType.NoFlyc || DataOsdGetPushCommon.getInstance().getDroneType() == DroneType.Unknown || DataOsdGetPushCommon.getInstance().isPushLosed()) {
                this.aH.go();
                this.aG.go();
                this.aF.go();
            } else {
                this.aH.show();
                this.aG.go();
                this.aF.show();
            }
            this.ag.go();
            if (!dji.pilot.fpv.d.b.o()) {
                this.ax.go();
            }
            this.al.go();
            this.aP.go();
            this.aN.go();
            this.aE.go();
            this.aT.go();
        }
        if (dji.pilot.fpv.d.b.m()) {
            if (dji.pilot.fpv.d.b.o()) {
                this.aW.show();
            } else {
                this.aW.go();
            }
            this.ay.setText(R.string.fpv_checklist_compass_detail);
            k();
        } else {
            this.aW.go();
            this.ay.setText(R.string.fpv_checklist_calibration);
        }
        if (dji.pilot.fpv.d.b.n()) {
            this.aX.show();
        } else {
            this.aX.go();
        }
        if (dji.pilot.fpv.d.b.o()) {
            CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
            if (!(DataCameraGetPushStateInfo.getInstance().isGetted() && (cameraType == CameraType.DJICameraTypeFC350 || cameraType == CameraType.DJICameraTypeFC550))) {
                this.aT.go();
            }
        }
        if (dji.logic.c.b.getInstance().a(null)) {
            this.aJ.setVisibility(8);
            this.aR.setVisibility(8);
        } else {
            this.aJ.setVisibility(0);
            this.aR.setVisibility(0);
        }
        View view = this.aZ;
        if (!dji.pilot.fpv.d.b.n(productType)) {
            i = 8;
        }
        f.a(view, i);
    }

    public void onEventMainThread(dji.pilot.fpv.d.b.a aVar) {
        E();
    }

    public void onEventMainThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        if ((dataFlycGetPushSmartBattery.getStatus() & 2048) == 2048) {
            this.bw |= 128;
        } else {
            this.bw &= -129;
        }
        b(true);
    }

    public void onEventMainThread(DataDm368_gGetPushCheckStatus dataDm368_gGetPushCheckStatus) {
        if (dataDm368_gGetPushCheckStatus.isOK()) {
            this.bw |= 1;
        } else {
            this.bw &= -2;
        }
        b(true);
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (dataCameraGetPushStateInfo.isOK()) {
            this.bw |= 2;
        } else {
            this.bw &= -3;
        }
        b(true);
    }

    public void onEventMainThread(DataCenterGetPushCheckStatus dataCenterGetPushCheckStatus) {
        if (dataCenterGetPushCheckStatus.isOK()) {
            this.bw |= 4;
        } else {
            this.bw &= -5;
        }
        b(true);
    }

    public void onEventMainThread(DataBatteryGetPushCheckStatus dataBatteryGetPushCheckStatus) {
        if (dataBatteryGetPushCheckStatus.isOK()) {
            this.bw |= 8;
        } else {
            this.bw &= -9;
        }
        b(true);
    }

    public void onEventMainThread(DataOsdGetPushCheckStatus dataOsdGetPushCheckStatus) {
        if (dataOsdGetPushCheckStatus.isOK()) {
            this.bw |= 16;
        } else {
            this.bw &= -17;
        }
        b(true);
    }

    public void onEventMainThread(DataFlycGetPushCheckStatus dataFlycGetPushCheckStatus) {
        if (dataFlycGetPushCheckStatus.isOK()) {
            this.bw |= 32;
        } else {
            this.bw &= -33;
        }
        b(true);
        if (dataFlycGetPushCheckStatus.getIMUAdvanceCaliStatus() || dataFlycGetPushCheckStatus.getIMUBasicCaliStatus() || dataFlycGetPushCheckStatus.getVersionStatus()) {
            this.bN |= 1;
        } else {
            this.bN &= -2;
        }
        N();
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
        boolean z = true;
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() >= 15) {
            boolean z2;
            this.aA.show();
            for (MotorEscmState a : dataOsdGetPushHome.getMotorEscmState()) {
                if (!dji.pilot.fpv.d.b.a(a)) {
                    z2 = false;
                    break;
                }
            }
            z2 = true;
            DJILinearLayout dJILinearLayout = this.aA;
            if (z2) {
                z = false;
            }
            dJILinearLayout.setEnabled(z);
            if (z2) {
                this.aB.setText(R.string.fpv_checklist_check_normal);
                this.aC.go();
            } else {
                this.aB.setText(R.string.fpv_checklist_check_abnormal);
                this.aC.show();
            }
            a(dataOsdGetPushHome);
        }
    }

    private void a(DataOsdGetPushHome dataOsdGetPushHome) {
        if (dji.pilot.fpv.d.b.r(null)) {
            if (dataOsdGetPushHome.getHeight() > 2500.0f) {
                this.bj.setVisibility(0);
                this.bj.setText(getContext().getString(R.string.fpv_checklist_altitude_tip_desc, new Object[]{"" + r0}));
                this.bi.setText(R.string.fpv_checklist_altitude_check_abnormal);
                return;
            }
            this.bj.setVisibility(8);
            this.bi.setText(R.string.fpv_checklist_check_normal);
        }
    }

    private void N() {
        if (dji.pilot.fpv.d.b.m()) {
            switch (this.ca) {
                case 0:
                case 1:
                    this.ap.go();
                    this.az.show();
                    return;
                case 2:
                case 3:
                case 5:
                case 6:
                    if (this.ca == 0) {
                        this.aq.setText(R.string.checklist_imu_stat_0);
                    } else if (this.ca == 2) {
                        this.aq.setText(R.string.checklist_imu_stat_2);
                    } else if (this.ca == 3) {
                        this.aq.setText(R.string.checklist_imu_stat_3);
                    } else if (this.ca == 5) {
                        this.aq.setText(R.string.checklist_imu_stat_5);
                    } else if (this.ca == 6) {
                        this.aq.setText(R.string.checklist_imu_stat_6);
                    }
                    this.ap.show();
                    this.az.go();
                    this.ar.show();
                    return;
                case 4:
                    this.aq.setText(R.string.checklist_imu_stat_4);
                    this.ar.go();
                    this.ap.show();
                    this.az.go();
                    return;
                default:
                    return;
            }
        } else if (this.bN == 0) {
            this.ap.go();
            this.az.show();
        } else {
            this.ap.show();
            this.az.go();
        }
    }

    public void onEventMainThread(DataGimbalGetPushCheckStatus dataGimbalGetPushCheckStatus) {
        if (dataGimbalGetPushCheckStatus.hasException()) {
            this.bw |= 64;
        } else {
            this.bw &= -65;
        }
        b(true);
    }

    public void onEventMainThread(Data1860GetPushCheckStatus data1860GetPushCheckStatus) {
        if (data1860GetPushCheckStatus.hasException()) {
            this.bw |= 2048;
        } else {
            this.bw &= -2049;
        }
        b(true);
    }

    public void onEventMainThread(DataEyeGetPushFunctionList dataEyeGetPushFunctionList) {
        a(Data2100GetPushCheckStatus.getInstance(), false);
    }

    private void a(Data2100GetPushCheckStatus data2100GetPushCheckStatus, boolean z) {
        int i = (data2100GetPushCheckStatus.isDownSightDemarkAbnormal() || data2100GetPushCheckStatus.isForeSightDemarkAbnormal() || data2100GetPushCheckStatus.isBackSightDemarkAbnormal()) ? 1 : 0;
        if (z) {
            int color;
            this.bb.setText(i != 0 ? R.string.fpv_checklist_check_abnormal : R.string.fpv_checklist_check_normal);
            DJITextView dJITextView = this.bb;
            if (i != 0) {
                color = this.N.getResources().getColor(R.color.gj);
            } else {
                color = this.N.getResources().getColor(R.color.om);
            }
            dJITextView.setTextColor(color);
        }
        if (i == 0 || !DataEyeGetPushFunctionList.getInstance().supportSelfCal()) {
            f.a(this.ba, 8);
        } else {
            f.a(this.ba, 0);
        }
    }

    public void onEventMainThread(Data2100GetPushCheckStatus data2100GetPushCheckStatus) {
        if (data2100GetPushCheckStatus.hasException()) {
            this.bw |= 4096;
        } else {
            this.bw &= -4097;
        }
        b(true);
        a(data2100GetPushCheckStatus, true);
    }

    public void onEventBackgroundThread(a$d dji_pilot_publics_control_a_d) {
        if (!this.bl.hasMessages(4099)) {
            this.bl.sendMessageDelayed(this.bl.obtainMessage(4099, dji_pilot_publics_control_a_d), X);
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (!this.bl.hasMessages(4096)) {
            this.bl.sendEmptyMessageDelayed(4096, X);
        }
    }

    public void onEventMainThread(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
        if (dataCenterGetPushBatteryCommon.isNeedStudy()) {
            this.bw |= 512;
        } else {
            this.bw &= -513;
        }
        int errorType = dataCenterGetPushBatteryCommon.getErrorType();
        dji.pilot.battery.a.c cVar = new dji.pilot.battery.a.c();
        cVar.a(errorType);
        if (cVar.l() != (byte) 0) {
            this.bw |= 256;
        } else {
            this.bw &= -257;
        }
        b(true);
    }

    public void onEventMainThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        boolean isStuck = dataGimbalGetPushParams.isStuck();
        boolean isPushLosed = DataGimbalGetPushParams.getInstance().isPushLosed();
        if (this.bW != isPushLosed) {
            this.bW = isPushLosed;
            if (this.bW) {
                this.aD.setText(R.string.fpv_checklist_gimbal_no_connection);
                this.aD.setTextColor(this.N.getResources().getColor(R.color.gj));
                this.aE.go();
                this.aF.go();
                this.bK = !isStuck;
            }
        }
        if ((this.bK != isStuck && !this.bW) || dji.pilot.fpv.d.b.l()) {
            this.bK = isStuck;
            if (isStuck) {
                this.aD.setText(R.string.fpv_checklist_gimbal_stuck);
                this.aD.setTextColor(this.N.getResources().getColor(R.color.gj));
                this.aF.show();
                this.aE.show();
                return;
            }
            if (dji.pilot.fpv.d.b.l()) {
                CharSequence spannableString = new SpannableString(String.format("%s %d%%", new Object[]{getContext().getString(R.string.fpv_ronin_battery_label), Integer.valueOf(DataGimbalPushBatteryInfo.getInstance().a())}));
                spannableString.setSpan(new ForegroundColorSpan(this.N.getResources().getColor(DataGimbalPushBatteryInfo.getInstance().a() <= 10 ? R.color.a6 : R.color.a3)), getContext().getString(R.string.fpv_ronin_battery_label).length(), spannableString.length(), 33);
                this.aD.setText(spannableString);
            } else {
                this.aD.setText(R.string.fpv_checklist_check_normal);
                this.aD.setTextColor(this.N.getResources().getColor(R.color.om));
                this.aE.go();
            }
            this.aF.show();
        }
    }

    public void onEventBackgroundThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        if (!this.bl.hasMessages(4100)) {
            this.bl.sendEmptyMessageDelayed(4100, X);
        }
    }

    public void onEventBackgroundThread(DataRcGetPushBatteryInfo dataRcGetPushBatteryInfo) {
        if (!this.bl.hasMessages(4098)) {
            this.bl.sendEmptyMessageDelayed(4098, X);
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (!this.bl.hasMessages(4097)) {
            this.bl.sendEmptyMessageDelayed(4097, X);
        }
    }

    public void onEventBackgroundThread(DataFlycGetPushDeformStatus dataFlycGetPushDeformStatus) {
        DEFORM_MODE deformMode = dataFlycGetPushDeformStatus.getDeformMode();
        if (deformMode != this.bX) {
            this.bX = deformMode;
            this.bl.sendEmptyMessage(r);
        }
    }

    public void j() {
        if (this.bX == DEFORM_MODE.Pack) {
            this.ag.setText(R.string.flyc_unpack_desc);
        } else {
            this.ag.setText(R.string.flyc_pack_desc);
        }
    }

    private void d(int i) {
        if (this.bY == null) {
            this.bY = b.a(getContext(), this.N.getString(R.string.app_tip), this.N.getString(R.string.fpv_flyc_ioc_reset_confirm), this.N.getString(R.string.app_enter), new DialogInterface.OnClickListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
        }
        this.bY.b(i);
        this.bY.show();
    }

    public void k() {
        if (dji.pilot.fpv.d.b.m()) {
            new DataFlycGetParams().setInfos(this.cb).start(new dji.midware.e.d(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.bl.sendEmptyMessage(d.w);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.bl.sendEmptyMessageDelayed(d.v, 1000);
                }
            });
        }
    }

    public String l() {
        long status = DataSmartBatteryGetPushDynamicData.getInstance().getStatus();
        if ((3 & status) != 0) {
            return getContext().getString(R.string.battery_top_tips_over_current);
        }
        if ((12 & status) != 0) {
            return getContext().getString(R.string.battery_top_tips_over_temp);
        }
        if ((48 & status) != 0) {
            return getContext().getString(R.string.battery_top_tips_low_temp);
        }
        if ((2097152 & status) != 0) {
            return getContext().getString(R.string.battery_top_tips_self_release);
        }
        if ((281474976710656L & status) != 0) {
            return getContext().getString(R.string.pm820_battery_error_5);
        }
        if ((562949953421312L & status) != 0) {
            return getContext().getString(R.string.pm820_battery_error_4);
        }
        if ((2251799813685248L & status) != 0) {
            return getContext().getString(R.string.pm820_battery_error_3);
        }
        if ((9007199254740992L & status) != 0) {
            return getContext().getString(R.string.pm820_battery_error_1);
        }
        if ((status & 18014398509481984L) != 0) {
            return getContext().getString(R.string.pm820_battery_need_upgrate);
        }
        return getContext().getString(R.string.fpv_checklist_check_normal);
    }

    public void onEventMainThread(DataSmartBatteryGetPushDynamicData dataSmartBatteryGetPushDynamicData) {
        long status = dataSmartBatteryGetPushDynamicData.getStatus();
        if (dataSmartBatteryGetPushDynamicData.getIndex() == 0 && dji.pilot.publics.e.a.d(null)) {
            boolean z;
            boolean z2;
            if ((3 & status) != 0) {
                z = true;
            } else if ((12 & status) != 0) {
                z = true;
            } else if ((48 & status) != 0) {
                z = true;
            } else if ((2097152 & status) != 0) {
                z = true;
            } else if ((281474976710656L & status) != 0) {
                z = true;
            } else if ((562949953421312L & status) != 0) {
                z = true;
            } else if ((2251799813685248L & status) != 0) {
                z = true;
            } else if ((9007199254740992L & status) != 0) {
                z = true;
            } else if ((18014398509481984L & status) != 0) {
                z = true;
            } else {
                z = false;
            }
            if ((status & 1125899906842624L) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            int battery = DataFlycGetPushSmartBattery.getInstance().getBattery();
            if (z) {
                this.bQ.setText(String.format("%s, %s%d%%", new Object[]{getContext().getString(R.string.battery_top_tips_exception), getContext().getString(R.string.fpv_checklist_pm820_battery_pencent), Integer.valueOf(battery)}));
                this.bQ.setTextColor(getContext().getResources().getColor(R.color.a6));
                this.bO.show();
                this.bO.setEnabled(true);
                this.bP.go();
            } else if (z2) {
                this.bR.setText(String.format("%s, %s%d%%", new Object[]{getContext().getString(R.string.fpv_checklist_pm820_battery_exception), getContext().getString(R.string.fpv_checklist_pm820_battery_pencent), Integer.valueOf(battery)}));
                this.bR.setTextColor(getContext().getResources().getColor(R.color.a6));
                this.bO.go();
                this.bP.show();
            } else {
                this.bQ.setText(String.format("%s%d%%", new Object[]{getContext().getString(R.string.fpv_checklist_pm820_battery_pencent), Integer.valueOf(battery)}));
                int voltageWarning = DataOsdGetPushCommon.getInstance().getVoltageWarning();
                if (voltageWarning == 1 || voltageWarning == 2) {
                    this.bQ.setTextColor(this.N.getResources().getColor(R.color.a6));
                } else {
                    this.bQ.setTextColor(this.N.getResources().getColor(R.color.om));
                }
                this.bO.show();
                this.bO.setEnabled(true);
                this.bP.go();
            }
        }
    }

    private void O() {
        if (dji.pilot.fpv.d.b.n()) {
            new DataFlycGetParams().setInfos(new String[]{cc}).start(new dji.midware.e.d(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.bl.sendEmptyMessage(d.t);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    Message message = new Message();
                    message.what = d.u;
                    this.a.bl.sendMessageDelayed(message, 2000);
                }
            });
        }
    }
}
