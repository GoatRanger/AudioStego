package dji.pilot.publics.objects;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.google.api.client.http.UrlEncodedParser;
import dji.common.error.DJIError;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.log.DJILogHelper;
import dji.log.WM220LogUtil;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataBatteryActiveStatus;
import dji.midware.data.model.P3.DataCameraActiveStatus;
import dji.midware.data.model.P3.DataCameraGetMode;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.ZoomFocusType;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetExposureMode;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetFocusDistance;
import dji.midware.data.model.P3.DataCameraSetFocusStroke;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode.OpticsZommMode;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode.ZoomSpeed;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataCommonSetDate;
import dji.midware.data.model.P3.DataEyeGetPushException;
import dji.midware.data.model.P3.DataFlycActiveStatus;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycGetPlaneName;
import dji.midware.data.model.P3.DataFlycGetPushAvoidParam;
import dji.midware.data.model.P3.DataFlycSetDate;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataFlycSetTimeZone;
import dji.midware.data.model.P3.DataOsdActiveStatus;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLIGHT_ACTION;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataOsdGetPushConfig;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataOsdSetSdrAssitantWrite;
import dji.midware.data.model.P3.DataOsdSetSdrForceBoost;
import dji.midware.data.model.P3.DataRcGetCustomFuction;
import dji.midware.data.model.P3.DataRcGetGimbalControlMode;
import dji.midware.data.model.P3.DataRcGetMaster;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.data.model.P3.DataRcSetRTC;
import dji.midware.data.model.P3.DataRcSetRcUnitNLang;
import dji.midware.data.model.P3.DataSingleCommonCtrl;
import dji.midware.data.model.P3.DataSingleCommonCtrl.CtrlState;
import dji.midware.data.model.P3.DataUpgradeSelfRequest;
import dji.midware.data.model.P3.DataUpgradeSelfRequest.ControlCmd;
import dji.midware.data.model.P3.DataWifiGetPassword;
import dji.midware.data.model.P3.DataWifiGetSSID;
import dji.midware.data.model.P3.DataWifiGetWifiFreqMode;
import dji.midware.data.model.P3.DataWifiRestart;
import dji.midware.data.model.P3.DataWifiSetNoisyCheckAdapt;
import dji.midware.data.model.P3.DataWifiSetWifiFreq5GMode;
import dji.midware.data.params.P3.ParamInfo;
import dji.pilot.R;
import dji.pilot.active.DJIActiveController;
import dji.pilot.c.d;
import dji.pilot.fpv.activity.DJIPreviewActivity;
import dji.pilot.fpv.activity.DJIPreviewActivityGrape;
import dji.pilot.fpv.activity.DJIPreviewActivityKumquat;
import dji.pilot.fpv.activity.DJIPreviewActivityLitchi;
import dji.pilot.fpv.activity.DJIPreviewActivityTomato;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.o;
import dji.pilot.fpv.control.t;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.reflect.FpvReflect;
import dji.pilot.usercenter.b.f;
import dji.setting.ui.rc.RcMasterSlaveView;
import dji.thirdparty.a.c;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.entity.StringEntity;

public class DJIGlobalService extends Service implements s {
    private static final String M = "g_config.device.is_locked_0";
    private static final String N = "g_config.arm_action_type_0";
    private static final long V = 600;
    private static final long W = 100;
    private static final long Y = 100;
    public static boolean a = false;
    private static final int ac = 8192;
    private static final int ad = 8193;
    private static final int ae = 8194;
    private static final int af = 8195;
    private static final int ag = 12288;
    private static final int ah = 12289;
    private static final int ai = 12290;
    private static final int aj = 12291;
    private static final long ak = 1000;
    private static final long al = 500;
    public static boolean b = false;
    public static String d = "";
    public static String e = "";
    public static String f = "";
    public static String g = "";
    public static String h = "";
    public static String i = "";
    public static long j = 0;
    private static String p = "";
    private static String q = "";
    private static final int s = 5;
    private static final String u = "camera_fan_on_t";
    private static final String v = "camera_fan_off_t";
    private static final String w = "camera_fan_changed";
    private static final String x = "device_sn";
    private boolean A = false;
    private FLIGHT_ACTION B = FLIGHT_ACTION.NONE;
    private RcModeChannel C = RcModeChannel.CHANNEL_UNKNOWN;
    private volatile int D = 0;
    private volatile int E = 0;
    private volatile int F = 0;
    private ParamInfo G;
    private DataFlycActiveStatus H = new DataFlycActiveStatus();
    private DataCameraActiveStatus I = new DataCameraActiveStatus();
    private DataOsdActiveStatus J = new DataOsdActiveStatus();
    private DataBatteryActiveStatus K = new DataBatteryActiveStatus();
    private String[] L = new String[]{"g_config.advanced_function.radius_limit_enabled_0", "g_config.flying_limit.max_radius_0"};
    private WifiManager O;
    private boolean P = false;
    private BroadcastReceiver Q = new BroadcastReceiver(this) {
        final /* synthetic */ DJIGlobalService a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            boolean z = true;
            if (this.a.P) {
                this.a.P = false;
                boolean b = g.b(context, t.a, false);
                WM220LogUtil.LOGD("**SharedPreferences :support5G= " + b);
                if (!b) {
                    if (this.a.O == null) {
                        this.a.O = (WifiManager) this.a.getSystemService("wifi");
                    }
                    if (VERSION.SDK_INT < 21 || !this.a.O.is5GHzBandSupported()) {
                        boolean z2;
                        WM220LogUtil.LOGD("**onReceive scan result");
                        this.a.y = this.a.O.getScanResults();
                        for (ScanResult scanResult : this.a.y) {
                            if (scanResult.frequency >= 5000) {
                                z2 = true;
                                break;
                            }
                        }
                        z2 = b;
                        WM220LogUtil.LOGD("**scan if support 5g: " + z2);
                        z = z2;
                    }
                    g.a(this.a, t.a, z);
                    if (z && DataWifiGetWifiFreqMode.getInstance().isGetted() && DataWifiGetWifiFreqMode.getInstance().getFreqMode() == 0) {
                        c.a().e(b.TRUE);
                    }
                }
            }
        }
    };
    private volatile int R = 0;
    private volatile int S = 0;
    private volatile boolean T = false;
    private volatile boolean U = false;
    private volatile boolean aa = false;
    private volatile int ab = 0;
    private Handler am = new Handler(new Callback(this) {
        final /* synthetic */ DJIGlobalService a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            boolean z = true;
            switch (message.what) {
                case 20:
                    this.a.j();
                    break;
                case 100:
                    this.a.a(true);
                    this.a.am.sendEmptyMessageDelayed(100, 500);
                    break;
                case 101:
                    this.a.am.removeMessages(100);
                    this.a.a(false);
                    break;
                case 200:
                    if (d.b == MODE.Master) {
                        break;
                    }
                    break;
                case 300:
                    this.a.i();
                    break;
                case 1000:
                    this.a.g();
                    break;
                case 8192:
                    int intValue = dji.midware.data.manager.P3.d.read(DJIGlobalService.M).value.intValue();
                    DJILogHelper.getInstance().LOGD("", "device lock[" + intValue + dji.pilot.usercenter.protocol.d.H, false, true);
                    if (intValue == 0) {
                        z = false;
                    }
                    DJIGlobalService.a = z;
                    if (DJIGlobalService.a) {
                        c.a().e(dji.pilot.fpv.d.b.a.DEVICE_LOCK);
                        break;
                    }
                    break;
                case 8193:
                    o.a(dji.midware.data.manager.P3.d.read("g_config.mvo_cfg.mvo_func_en_0").value.intValue() != 0, false);
                    if (this.a.F < 10) {
                        if (dji.midware.data.manager.P3.d.read("g_config.avoid_obstacle_limit_cfg.avoid_obstacle_enable_0").value.intValue() == 0) {
                            z = false;
                        }
                        o.b(z, false);
                        break;
                    }
                    if (dji.midware.data.manager.P3.d.read("g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0").value.intValue() == 0) {
                        z = false;
                    }
                    o.b(z, false);
                    break;
                case 8194:
                    this.a.s();
                    if (this.a.R == 1) {
                        this.a.am.sendEmptyMessageDelayed(8194, 100);
                        break;
                    }
                    break;
                case DJIGlobalService.af /*8195*/:
                    boolean z2;
                    DJIGlobalService dJIGlobalService = this.a;
                    if (message.arg1 == 1) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    dJIGlobalService.b(z2);
                    if (this.a.S == 1) {
                        this.a.am.sendEmptyMessageDelayed(DJIGlobalService.af, 100);
                        break;
                    }
                    break;
                case 12288:
                    this.a.a(this.a.k);
                    break;
                case 12289:
                    this.a.p();
                    this.a.q();
                    break;
                case 12290:
                    if (message.arg1 != 0) {
                        this.a.q();
                        break;
                    }
                    d.f = dji.midware.data.manager.P3.d.read(DJIGlobalService.N).value.intValue();
                    c.a().e(dji.pilot.fpv.model.n.a.ARMACTION);
                    break;
                case 12291:
                    this.a.l();
                    break;
            }
            return false;
        }
    });
    f c;
    public volatile int k = -1;
    private final String l = getClass().getSimpleName();
    private dji.pilot.publics.control.a m;
    private dji.pilot.active.d n;
    private dji.pilot.active.b o;
    private String r = "";
    private volatile int t = 0;
    private List<ScanResult> y;
    private volatile boolean z = false;

    public enum a {
        C2LongPress,
        C2PressUp
    }

    public enum b {
        TRUE
    }

    public void onCreate() {
        super.onCreate();
        Log.e(this.l, "onTaskCreate");
        c.a().a((Object) this);
        this.m = dji.pilot.publics.control.a.getInstance(getApplicationContext());
        if (DJINetWorkReceiver.a(getApplicationContext())) {
            try {
                DJIActiveController.a(getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
            u();
        }
        if (ServiceManager.getInstance().isConnected()) {
            onEventBackgroundThread(p.ConnectOK);
        }
        if (ServiceManager.getInstance().isRemoteOK()) {
            onEventBackgroundThread(dji.midware.data.manager.P3.o.ConnectOK);
        }
        this.o = dji.pilot.active.b.getInstance();
        this.o.a((Context) this);
        this.n = new dji.pilot.active.d(this);
        w();
        this.O = (WifiManager) getSystemService("wifi");
        registerReceiver(this.Q, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
        dji.pilot.countrycode.a.c.getInstance().a(DJIApplication.a(), null);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    public void onDestroy() {
        unregisterReceiver(this.Q);
        super.onDestroy();
        Log.e(this.l, "onTaskDestroy");
        c.a().d((Object) this);
        this.m.a();
        this.o.a();
        this.n.a();
        dji.pilot.countrycode.a.c.getInstance().b();
    }

    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        if (!intent.getComponent().flattenToShortString().contains("DJISimulatorActivity")) {
            Log.e(this.l, "onTaskRemoved 1" + intent);
            Log.e(this.l, "onTaskRemoved 1");
            ServiceManager.Destroy();
            Log.e(this.l, "onTaskRemoved");
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onEventMainThread(DataUpgradeSelfRequest dataUpgradeSelfRequest) {
        f();
    }

    private void f() {
        if (i.getInstance().c() != ProductType.Pomato && i.getInstance().c() != ProductType.Orange2 && this.c == null) {
            this.c = new f(this);
            this.c.getWindow().setType(2003);
            this.c.show();
            this.c.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ DJIGlobalService a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    DataUpgradeSelfRequest.getInstance().a(ControlCmd.a).start();
                }
            });
            this.c.a(new dji.pilot.publics.objects.f.a(this) {
                final /* synthetic */ DJIGlobalService a;

                {
                    this.a = r1;
                }

                public void a(DialogInterface dialogInterface, boolean z, int i) {
                    if (z) {
                        DataUpgradeSelfRequest.getInstance().a(ControlCmd.b).start();
                        dialogInterface.dismiss();
                    }
                }
            });
        }
    }

    public void onEventBackgroundThread(ProductType productType) {
        if (dji.pilot.c.a.n) {
            this.am.sendEmptyMessageDelayed(1000, 1000);
        }
    }

    private void g() {
        Intent intent = null;
        ProductType d = i.getInstance().d();
        DJILogHelper.getInstance().LOGD(this.l, "lasType=" + d + " typenow=" + i.getInstance().c(), false, true);
        switch (i.getInstance().c()) {
            case Orange:
            case N1:
            case BigBanana:
            case OrangeRAW:
            case Olives:
                if (!((d == ProductType.Orange || d == ProductType.N1 || d == ProductType.BigBanana || d == ProductType.OrangeRAW || d == ProductType.Olives || dji.pilot.publics.e.a.d(d)) && d == ProductType.OrangeCV600)) {
                    intent = new Intent(getApplicationContext(), DJIPreviewActivity.class);
                    break;
                }
            case PM820:
            case PM820PRO:
                if (!(d == ProductType.Orange || d == ProductType.N1 || d == ProductType.BigBanana || d == ProductType.OrangeRAW || d == ProductType.Olives || dji.pilot.publics.e.a.d(d) || d == ProductType.Grape2 || d == ProductType.OrangeCV600)) {
                    intent = new Intent(getApplicationContext(), DJIPreviewActivity.class);
                    break;
                }
            case litchiC:
            case litchiS:
            case litchiX:
            case P34K:
            case Tomato:
                if (!(d == ProductType.litchiC || d == ProductType.litchiS || d == ProductType.litchiX || d == ProductType.P34K || d == ProductType.Tomato)) {
                    intent = new Intent(getApplicationContext(), DJIPreviewActivityLitchi.class);
                    break;
                }
            case Pomato:
                if (d != ProductType.Pomato) {
                    intent = new Intent(getApplicationContext(), DJIPreviewActivityTomato.class);
                    break;
                }
                break;
            case KumquatS:
            case KumquatX:
                if (!(d == ProductType.KumquatS || d == ProductType.KumquatX)) {
                    intent = new Intent(getApplicationContext(), DJIPreviewActivityKumquat.class);
                    break;
                }
            case Longan:
            case LonganPro:
            case LonganRaw:
            case LonganZoom:
                if (!dji.pilot.fpv.d.b.h(d)) {
                    intent = new Intent(getApplicationContext(), FpvReflect.getLonganClass());
                    break;
                }
                break;
            case Grape2:
                if (!(d == ProductType.A2 || d == ProductType.Grape2 || dji.pilot.publics.e.a.d(d))) {
                    intent = new Intent(getApplicationContext(), DJIPreviewActivityGrape.class);
                    break;
                }
            case A2:
            case N3:
            case A3:
                if (!(d == ProductType.A2 || d == ProductType.Grape2 || d == ProductType.A3 || d == ProductType.N3)) {
                    intent = new Intent(getApplicationContext(), DJIPreviewActivity.class);
                    break;
                }
        }
        if (intent != null) {
            intent.setFlags(268435456);
            startActivity(intent);
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (dataOsdGetPushCommon.isGetted()) {
            boolean isMotorUp = dataOsdGetPushCommon.isMotorUp();
            if (this.A != isMotorUp) {
                this.A = isMotorUp;
                if (isMotorUp) {
                    dji.pilot.fpv.d.b.a(false);
                } else {
                    dji.pilot.fpv.control.s.getInstance().a = null;
                }
            }
            if (!(this.D == 2 && this.E == 2) && dataOsdGetPushCommon.isMotorUp() && ProductType.P34K == i.getInstance().c()) {
                double latitude = dataOsdGetPushCommon.getLatitude();
                double longitude = dataOsdGetPushCommon.getLongitude();
                double latitude2 = DataOsdGetPushHome.getInstance().getLatitude();
                double longitude2 = DataOsdGetPushHome.getInstance().getLongitude();
                if (dji.pilot.fpv.d.b.a(latitude) && dji.pilot.fpv.d.b.b(longitude) && dji.pilot.fpv.d.b.a(latitude2) && dji.pilot.fpv.d.b.b(longitude2) && dji.pilot.fpv.d.b.a(latitude, longitude, latitude2, longitude2) >= 20.0f) {
                    if (this.D == 0) {
                        this.D = 1;
                        new DataWifiSetNoisyCheckAdapt().a(DeviceType.WIFI_G).a(false).start(new dji.midware.e.d(this) {
                            final /* synthetic */ DJIGlobalService a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                this.a.D = 2;
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                this.a.D = 0;
                            }
                        });
                    }
                    if (this.E == 0) {
                        this.E = 1;
                        new DataWifiSetNoisyCheckAdapt().a(DeviceType.WIFI).a(false).start(new dji.midware.e.d(this) {
                            final /* synthetic */ DJIGlobalService a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                this.a.E = 2;
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                this.a.E = 0;
                            }
                        });
                    }
                }
            }
            FLIGHT_ACTION flightAction = DataOsdGetPushCommon.getInstance().getFlightAction();
            if (this.B != flightAction) {
                this.B = flightAction;
                if (this.B == FLIGHT_ACTION.RC_ASSISTANT_TAKEOFF) {
                    e.c(s.X);
                    e.c(s.dD);
                } else if (this.B == FLIGHT_ACTION.RC_ONEKEY_GOHOME) {
                    e.c(s.Z);
                    e.c(s.dE);
                } else if (this.B == FLIGHT_ACTION.APP_AUTO_GOHOME) {
                    e.c(s.cY);
                    e.c(s.dE);
                } else if (this.B == FLIGHT_ACTION.APP_AUTO_LANDING) {
                    e.c(s.cX);
                    e.c(s.dE);
                } else if (this.B == FLIGHT_ACTION.APP_AUTO_TAKEOFF) {
                    e.c(s.cW);
                    e.c(s.dD);
                }
            }
            RcModeChannel modeChannel = DataOsdGetPushCommon.getInstance().getModeChannel();
            if (DataOsdGetPushHome.getInstance().isMultipleModeOpen() && this.C != modeChannel) {
                this.C = modeChannel;
                if (modeChannel == RcModeChannel.CHANNEL_F) {
                    e.c(s.cR);
                    DJILogHelper.getInstance().LOGD("flurry", "log F mode", false, true);
                } else if (modeChannel == RcModeChannel.CHANNEL_A) {
                    e.c(s.cS);
                    DJILogHelper.getInstance().LOGD("flurry", "log A mode", false, true);
                } else if (modeChannel == RcModeChannel.CHANNEL_S) {
                    e.c(s.cT);
                    DJILogHelper.getInstance().LOGD("flurry", "log S mode", false, true);
                }
            }
            int flycVersion = dataOsdGetPushCommon.getFlycVersion();
            if (this.F != flycVersion) {
                this.F = flycVersion;
                String str = flycVersion >= 10 ? "g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0" : "g_config.avoid_obstacle_limit_cfg.avoid_obstacle_enable_0";
                new DataFlycGetParams().setInfos(new String[]{"g_config.mvo_cfg.mvo_func_en_0", str}).start(new dji.midware.e.d(this) {
                    final /* synthetic */ DJIGlobalService a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.am.sendEmptyMessage(8193);
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                    }
                });
                new Thread(new Runnable(this) {
                    final /* synthetic */ DJIGlobalService a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        dji.midware.data.model.b.b bVar = new dji.midware.data.model.b.b();
                        bVar.a(DeviceType.BATTERY).start(new 1(this));
                        bVar.join();
                        DJILogHelper.getInstance().LOGD(this.a.l, "here to get Battery Active Version", false, true);
                        this.a.K.setVersion(DataBatteryActiveStatus.getInstance().getVersion());
                        this.a.K.setType(dji.midware.data.model.b.a.b.b).start(new 2(this));
                        this.a.K.join();
                    }
                }).start();
            }
            if (!this.z && DroneType.wm220 == dataOsdGetPushCommon.getDroneType() && 23 > dataOsdGetPushCommon.getFlycVersion() && dji.sdksharedlib.e.a.a.getInstance().b(dji.sdksharedlib.b.e.cL)) {
                this.z = true;
                dji.sdksharedlib.e.a.a.getInstance().a(dji.sdksharedlib.b.e.cL, Integer.valueOf(0), new dji.sdksharedlib.hardware.abstractions.b.e(this) {
                    final /* synthetic */ DJIGlobalService a;

                    {
                        this.a = r1;
                    }

                    public void a(Object obj) {
                    }

                    public void a(DJIError dJIError) {
                        this.a.z = false;
                    }
                });
            }
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (dataCameraGetPushShotParams.isGetted()) {
            ExposureMode exposureMode = dataCameraGetPushShotParams.getExposureMode();
            if (!dji.pilot.fpv.d.b.a(DataCameraGetPushStateInfo.getInstance().getCameraType()) && exposureMode == ExposureMode.S) {
                new DataCameraSetExposureMode().a(ExposureMode.P.a()).start(null);
            }
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        DataCameraGetMode.MODE mode = dataCameraGetPushStateInfo.getMode();
        if (DataCameraGetMode.MODE.NEW_PLAYBACK == mode || DataCameraGetMode.MODE.PLAYBACK == mode || DataCameraGetMode.MODE.DOWNLOAD == mode) {
            FLYC_STATE flycState = DataOsdGetPushCommon.getInstance().getFlycState();
            dji.pilot.fpv.camera.a.a.a(null, "mode-" + mode + com.alipay.sdk.j.i.b + flycState);
            if (FLYC_STATE.NaviSubMode_Tracking == flycState || FLYC_STATE.TRACK_HEADLOCK == flycState || FLYC_STATE.NaviSubMode_Pointing == flycState || DataEyeGetPushException.getInstance().isInPointing()) {
                new DataSingleCommonCtrl().a(CtrlState.STOP).start(null);
            }
        }
    }

    public void onEventBackgroundThread(dji.pilot.publics.objects.DJINetWorkReceiver.a aVar) {
        switch (aVar) {
            case CONNECT_OK:
                if (dji.pilot.publics.control.a.b()) {
                    w();
                    try {
                        DJIActiveController.a(getApplicationContext());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (f.getInstance().c()) {
                        f.getInstance().q();
                    }
                    dji.pilot.publics.control.a.getInstance(getApplicationContext()).d();
                    u();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void h() {
        if (this.O == null) {
            this.O = (WifiManager) getSystemService("wifi");
        }
        if (this.O.isWifiEnabled()) {
            WM220LogUtil.LOGD("handleWifiSSIDChanged last: " + this.r);
            if (!this.r.equals("")) {
                String ssid = this.O.getConnectionInfo().getSSID();
                WM220LogUtil.LOGD("handleWifiSSIDChanged mid: " + ssid);
                if (!this.r.equals(ssid)) {
                    dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                    bVar.b = R.string.wifi_hot_point_changed_tip;
                    bVar.f = DJIErrorPopView.c.STATIC;
                    c.a().e(bVar);
                }
                this.r = "";
            }
            if (dji.midware.f.a.getInstance().d() == dji.midware.f.b.WIFI) {
                this.r = this.O.getConnectionInfo().getSSID();
            }
            WM220LogUtil.LOGD("handleWifiSSIDChanged cur: " + this.r);
        }
    }

    public void onEventBackgroundThread(p pVar) {
        switch (pVar) {
            case ConnectOK:
                i();
                this.am.sendEmptyMessageDelayed(20, 200);
                return;
            case ConnectLose:
                this.am.removeMessages(300);
                k();
                return;
            default:
                return;
        }
    }

    private void i() {
        if (!dji.pilot.fpv.d.b.h(null) && DJIUpgradeP4Service.a != dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service.a.ConnectP4MC) {
            DataRcGetMaster.getInstance().start(new dji.midware.e.d(this) {
                final /* synthetic */ DJIGlobalService a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    d.b = DataRcGetMaster.getInstance().getMode();
                    RcMasterSlaveView.c cVar = new RcMasterSlaveView.c();
                    cVar.a = DataRcGetMaster.getInstance().getMode();
                    c.a().e(cVar);
                    DJILogHelper.getInstance().LOGD(this.a.l, "get DataRcGetMaster success " + d.b, false, true);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD(this.a.l, "get DataRcGetMaster " + aVar, false, true);
                    if (ServiceManager.getInstance().isConnected()) {
                        this.a.am.sendEmptyMessageDelayed(300, 200);
                    }
                }
            });
        }
    }

    private void j() {
        new Thread(new Runnable(this) {
            final /* synthetic */ DJIGlobalService a;

            {
                this.a = r1;
            }

            public void run() {
                if (!i.getInstance().c().isFromWifi()) {
                    DataRcGetCustomFuction.getInstance().start(new 1(this));
                    DataRcGetCustomFuction.getInstance().join();
                }
                DataRcGetGimbalControlMode.getInstance().start(new 2(this));
                DataRcGetGimbalControlMode.getInstance().join();
                if (!i.getInstance().c().isFromWifi()) {
                    DataRcSetRTC.getInstance().start(new 3(this));
                    DataRcSetRTC.getInstance().join();
                }
                DataCommonSetDate dataCommonSetDate = new DataCommonSetDate();
                dataCommonSetDate.a(DeviceType.DM368_G).a(1).start(new 4(this));
                dataCommonSetDate.join();
                DataRcSetRcUnitNLang.getInstance().a(DJIGenSettingDataManager.getInstance().v() == 0 ? 2 : 0).start(new 5(this));
            }
        }).start();
        dji.pilot.fpv.control.a.a.getInstance().a();
    }

    private void k() {
        d.b = MODE.OTHER;
        f = "";
        g = "";
        i = "";
        h = "";
        this.E = 0;
        this.D = 0;
        this.c = null;
        if (this.y != null) {
            this.y.clear();
        }
        dji.pilot.fpv.control.a.a.getInstance().b();
    }

    public void onEventBackgroundThread(dji.midware.data.manager.P3.o oVar) {
        DJILogHelper.getInstance().LOGD("", "DataCameraEvent=" + oVar, false, true);
        switch (oVar) {
            case ConnectOK:
                l();
                return;
            case ConnectLose:
                r();
                return;
            default:
                return;
        }
    }

    private void a(final boolean z) {
        int i = 1;
        if (this.G == null) {
            this.G = dji.midware.data.manager.P3.d.read(M);
        }
        DJILogHelper.getInstance().LOGD("", "toggleLock device[" + z + dji.pilot.usercenter.protocol.d.H, false, true);
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        String str = this.G.name;
        if (!z) {
            i = 0;
        }
        dataFlycSetParams.a(str, Integer.valueOf(i)).start(new dji.midware.e.d(this) {
            final /* synthetic */ DJIGlobalService b;

            public void onSuccess(Object obj) {
                DJIGlobalService.a = z;
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    public void onEventBackgroundThread(dji.pilot.publics.control.a.e eVar) {
        DJILogHelper.getInstance().LOGD("", "DJIUpgradeStatusForLock=" + eVar, false, true);
        switch (eVar) {
            case LOCK:
                this.am.removeMessages(100);
                this.am.sendEmptyMessage(100);
                return;
            case NO:
                this.am.removeMessages(101);
                this.am.sendEmptyMessage(101);
                return;
            default:
                return;
        }
    }

    private void l() {
        if (ServiceManager.getInstance().isRemoteOK()) {
            new Thread(new Runnable(this) {
                final /* synthetic */ DJIGlobalService a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (!(i.getInstance().c() == ProductType.litchiC || dji.pilot.fpv.d.b.h(null))) {
                        DataOsdGetPushConfig.getInstance().start(new 1(this));
                        DataOsdGetPushConfig.getInstance().join();
                    }
                    DataFlycGetPlaneName.getInstance().start(new 4(this));
                    DataFlycGetPlaneName.getInstance().join();
                    dji.midware.data.model.b.b bVar = new dji.midware.data.model.b.b();
                    bVar.a(DeviceType.FLYC).start(new 5(this));
                    bVar.join();
                    bVar = new dji.midware.data.model.b.b();
                    bVar.a(DeviceType.CAMERA).start(new 6(this));
                    bVar.join();
                    bVar = new dji.midware.data.model.b.b();
                    bVar.a(DeviceType.OSD).start(new 7(this));
                    bVar.join();
                    this.a.H.setVersion(DataFlycActiveStatus.getInstance().getVersion());
                    this.a.H.setType(dji.midware.data.model.b.a.b.b).start(new 8(this));
                    this.a.H.join();
                    this.a.J.setVersion(DataOsdActiveStatus.getInstance().getVersion());
                    this.a.J.setType(dji.midware.data.model.b.a.b.b).start(new 9(this));
                    this.a.J.join();
                    this.a.I.setVersion(DataCameraActiveStatus.getInstance().getVersion());
                    this.a.I.setType(dji.midware.data.model.b.a.b.b).start(new 10(this));
                    this.a.I.join();
                    DataFlycGetParams dataFlycGetParams = new DataFlycGetParams();
                    dataFlycGetParams.setInfos(this.a.L).start(new 11(this));
                    dataFlycGetParams.join();
                    dataFlycGetParams = new DataFlycGetParams();
                    dataFlycGetParams.setInfos(new String[]{DJIGlobalService.M}).start(new 2(this));
                    dataFlycGetParams.join();
                    if (dji.logic.c.b.getInstance().a(i.getInstance().c())) {
                        this.a.o();
                        this.a.n();
                    }
                    DataCommonSetDate dataCommonSetDate = new DataCommonSetDate();
                    dataCommonSetDate.a(DeviceType.DM368).a(1).start(new 3(this));
                    dataCommonSetDate.join();
                    this.a.m();
                }
            }).start();
            DJIGenSettingDataManager.getInstance().A();
            DataFlycSetTimeZone.getInstance().a(dji.pilot.publics.e.d.a()).start(null);
            DataFlycSetDate.getInstance().start(null);
            this.am.sendMessageDelayed(this.am.obtainMessage(12289, 1, 0), 1000);
        }
    }

    private void m() {
        if (dji.pilot.publics.c.a.b) {
            DataOsdSetSdrAssitantWrite dataOsdSetSdrAssitantWrite = new DataOsdSetSdrAssitantWrite();
            dataOsdSetSdrAssitantWrite.a().start(null);
            dataOsdSetSdrAssitantWrite.join();
        }
        if (dji.pilot.publics.c.a.c) {
            DataOsdSetSdrForceBoost dataOsdSetSdrForceBoost = new DataOsdSetSdrForceBoost();
            dataOsdSetSdrForceBoost.start(null);
            dataOsdSetSdrForceBoost.join();
        }
        if (dji.pilot.publics.c.a.d) {
            dataOsdSetSdrAssitantWrite = new DataOsdSetSdrAssitantWrite();
            dataOsdSetSdrAssitantWrite.b().start(null);
            dataOsdSetSdrAssitantWrite.join();
        }
        if (dji.pilot.publics.c.a.e) {
            dataOsdSetSdrAssitantWrite = new DataOsdSetSdrAssitantWrite();
            dataOsdSetSdrAssitantWrite.c().start(null);
            dataOsdSetSdrAssitantWrite.join();
        }
    }

    private void n() {
        DataWifiGetWifiFreqMode.getInstance().start(new dji.midware.e.d(this) {
            final /* synthetic */ DJIGlobalService a;

            {
                this.a = r1;
            }

            @SuppressLint({"NewApi"})
            public void onSuccess(Object obj) {
                int freqMode = DataWifiGetWifiFreqMode.getInstance().getFreqMode();
                DJILogHelper.getInstance().LOGD("wm220", "get wifi mode onSuccess, mode: " + freqMode);
                boolean b = g.b(this.a, t.a, false);
                if (freqMode != 2 || !b) {
                    this.a.O = (WifiManager) this.a.getSystemService("wifi");
                    if (b) {
                        c.a().e(b.TRUE);
                    } else {
                        this.a.o();
                    }
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("wm220", "get wifi mode onFailure");
            }
        });
    }

    private void o() {
        if (!this.P) {
            this.P = true;
            this.O.startScan();
        }
    }

    public static void a() {
        DataWifiSetWifiFreq5GMode.getInstance().a(2).start(new dji.midware.e.d() {
            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("wm220", "set 5g onSuccess");
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("wm220", "set 5g onFailure");
            }
        });
        DataWifiRestart.getInstance().start(new dji.midware.e.d() {
            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("wm220", "Restart Wifi onSuccess");
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("wm220", "Restart Wifi onFailure");
            }
        });
    }

    private void p() {
        if (ServiceManager.getInstance().isRemoteOK()) {
            ArrayList arrayList = new ArrayList(1);
            if (dji.pilot.publics.e.a.a(i.getInstance().c(), DataOsdGetPushCommon.getInstance().getFlycVersion())) {
                arrayList.add(dji.midware.data.params.P3.a.g);
            }
            if (!arrayList.isEmpty()) {
                String[] strArr = new String[arrayList.size()];
                arrayList.toArray(strArr);
                new DataFlycGetParams().setInfos(strArr).start(new dji.midware.e.d(this) {
                    final /* synthetic */ DJIGlobalService a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                    }
                });
            }
        }
    }

    private void q() {
        if (this.t > 5 || !ServiceManager.getInstance().isRemoteOK()) {
            this.t = 0;
            return;
        }
        new DataFlycGetParams().setInfos(new String[]{N}).start(new dji.midware.e.d(this) {
            final /* synthetic */ DJIGlobalService a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.am.sendMessage(this.a.am.obtainMessage(12290, 0, 0));
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.am.sendMessageDelayed(this.a.am.obtainMessage(12290, 1, 0), 500);
            }
        });
        this.t++;
    }

    private void r() {
        d.f = 0;
        this.t = 0;
        this.F = 0;
        dji.pilot.fpv.d.b.d();
        f = "";
        g = "";
        i = "";
        this.am.removeMessages(100);
        a = false;
        this.k = -1;
        this.am.removeMessages(8193);
        this.R = 0;
        this.S = 0;
        this.T = false;
        this.U = false;
        this.aa = false;
        this.z = false;
        this.am.removeMessages(8194);
        this.am.removeMessages(af);
    }

    public void onEventBackgroundThread(DataFlycGetPushAvoidParam dataFlycGetPushAvoidParam) {
        int i = dataFlycGetPushAvoidParam.isUserAvoidEnable() ? 1 : 0;
        if (this.k != i) {
            this.k = i;
            a(i);
        }
    }

    private void a(int i) {
        new DataFlycSetParams().a(dji.setting.ui.vision.a.e, Integer.valueOf(i)).start(new dji.midware.e.d(this) {
            final /* synthetic */ DJIGlobalService a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (aVar != dji.midware.data.config.P3.a.NOCONNECT) {
                    this.a.am.sendEmptyMessageDelayed(12288, 1000);
                }
            }
        });
    }

    public void onEventBackgroundThread(DataRcGetPushParams dataRcGetPushParams) {
        if (dji.pilot.dji_groundstation.controller.a.getInstance(null) == null || !dji.pilot.dji_groundstation.controller.a.getInstance(null).g() || dji.pilot.dji_groundstation.controller.d.getInstance().b().a() != dji.pilot.dji_groundstation.a.d.c.WayPoint_PageAddNew.a()) {
            b(dataRcGetPushParams.getCustom1());
            c(dataRcGetPushParams.getCustom2());
        }
    }

    private DJICustomType a(DJICustomType dJICustomType) {
        int i = 0;
        boolean z = d.b != null ? d.b == MODE.Master : false;
        DJICustomType[] a = dji.pilot.fpv.d.b.a(Boolean.valueOf(z));
        DJICustomType dJICustomType2 = a[0];
        int length = a.length;
        while (i < length) {
            if (a[i] == dJICustomType) {
                return dJICustomType;
            }
            i++;
        }
        return dJICustomType2;
    }

    private void b(int i) {
        boolean z = false;
        if (this.R != i) {
            if (i == 0 && this.R == 1) {
                if (!this.T) {
                    int c1 = DataRcGetCustomFuction.getInstance().getC1();
                    DJILogHelper.getInstance().LOGE(this.l, "c1 =" + c1, false, true);
                    if (DataRcGetCustomFuction.getInstance().isGetted()) {
                        DJICustomType a = a(DJICustomType.find(c1));
                        if (!(dji.pilot.fpv.d.b.t(i.getInstance().a()) && dji.pilot.fpv.d.b.a(a))) {
                            z = true;
                        }
                        if (z) {
                            c.a().e(a);
                        }
                        Map hashMap = new HashMap();
                        hashMap.put(dji.pilot.fpv.d.d.dH, "" + a.a());
                        e.a(s.cU, hashMap);
                    }
                }
                this.am.removeMessages(8194);
            } else if (i == 1) {
                this.T = false;
                if (!this.am.hasMessages(8194) && dji.pilot.fpv.d.b.e(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
                    this.am.sendEmptyMessageDelayed(8194, V);
                }
            } else {
                this.T = false;
            }
            this.R = i;
        }
    }

    private void s() {
        if (!this.T) {
            this.T = true;
        }
        if (!dji.pilot.fpv.d.b.t(i.getInstance().a()) && DataRcGetPushParams.getInstance().getGyroValue() != 1024) {
            DataCameraGetPushShotInfo instance = DataCameraGetPushShotInfo.getInstance();
            FuselageFocusMode fuselageFocusMode = instance.getFuselageFocusMode();
            if (!(fuselageFocusMode == FuselageFocusMode.Manual || fuselageFocusMode == FuselageFocusMode.ManualFine)) {
                new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.SetFocusMode).a(FuselageFocusMode.Manual.value()).start(null);
            }
            int shotFocusMaxStroke = instance.getShotFocusMaxStroke();
            int h = dji.pilot.fpv.camera.more.a.getInstance().h();
            int shotFocusCurStroke = instance.getShotFocusCurStroke() + ((int) (((float) (DataRcGetPushParams.getInstance().getGyroValue() - 1024)) * 0.02f));
            if (shotFocusCurStroke >= h) {
                if (shotFocusCurStroke > shotFocusMaxStroke) {
                    h = shotFocusMaxStroke;
                } else {
                    h = shotFocusCurStroke;
                }
            }
            new DataCameraSetFocusStroke().a(h).start(null);
        }
    }

    private void c(int i) {
        boolean z = true;
        if (this.S != i) {
            if (i == 0 && this.S == 1) {
                if (!this.U) {
                    int c2 = DataRcGetCustomFuction.getInstance().getC2();
                    DJILogHelper.getInstance().LOGE(this.l, "c2 =" + c2, false, true);
                    if (DataRcGetCustomFuction.getInstance().isGetted()) {
                        DJICustomType a = a(DJICustomType.find(c2));
                        if (dji.pilot.fpv.d.b.t(i.getInstance().a()) && dji.pilot.fpv.d.b.a(a)) {
                            z = false;
                        }
                        if (z) {
                            c.a().e(a);
                        }
                        Map hashMap = new HashMap();
                        hashMap.put(dji.pilot.fpv.d.d.dH, "" + a.a());
                        e.a(s.cV, hashMap);
                    }
                }
                t();
                this.aa = false;
                this.U = false;
                this.am.removeMessages(af);
                c.a().e(a.C2PressUp);
            } else if (i == 1) {
                t();
                this.aa = false;
                this.U = false;
                c.a().e(a.C2PressUp);
                if (!this.am.hasMessages(af)) {
                    this.am.sendMessageDelayed(this.am.obtainMessage(af, 1, 0), V);
                }
            } else {
                t();
                this.aa = false;
                this.U = false;
                c.a().e(a.C2PressUp);
            }
            this.S = i;
        }
    }

    private void t() {
        if (this.aa && dji.pilot.fpv.d.b.a(DataCameraGetPushShotInfo.getInstance().getZoomFocusType(), DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            this.aa = false;
            new DataCameraSetOpticsZoomMode().a(OpticsZommMode.STOPZOOM, ZoomSpeed.SLOWEST, 0, 0).start(null);
        }
    }

    private void b(boolean z) {
        int i = -1;
        int i2 = 1;
        if (!dji.pilot.fpv.d.b.t(i.getInstance().a())) {
            int i3;
            long j = 0;
            String e = dji.pilot.publics.control.a.getInstance().e("1400");
            if (e != null) {
                try {
                    j = dji.pilot.publics.e.d.c(e);
                } catch (Exception e2) {
                }
            } else {
                DataCommonGetVersion f = dji.pilot.publics.control.a.getInstance().f("1400");
                if (f != null) {
                    f.start(new dji.midware.e.d(this) {
                        final /* synthetic */ DJIGlobalService a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                        }
                    });
                }
            }
            boolean z2;
            if (ProductType.P34K == i.getInstance().c()) {
                if (j >= 67174661) {
                    i3 = 1;
                } else {
                    z2 = false;
                }
            } else if (j >= 67699468) {
                i3 = 1;
            } else {
                z2 = false;
            }
            if (i3 != 0) {
                DataRcGetPushParams instance = DataRcGetPushParams.getInstance();
                if (instance.isWheelChanged()) {
                    if (!this.U) {
                        this.U = true;
                    }
                    c.a().e(a.C2LongPress);
                    i3 = instance.getWheelOffset() * 2;
                    if (!instance.isWheelPositive()) {
                        i3 = -i3;
                    }
                    DJIGenSettingDataManager.getInstance().a((byte) i3);
                }
            }
            if (j >= 67699468 && DataCameraGetPushStateInfo.getInstance().isGetted()) {
                CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
                ZoomFocusType zoomFocusType = DataCameraGetPushShotInfo.getInstance().getZoomFocusType();
                int minFocusDistance;
                if (dji.pilot.fpv.d.b.a(cameraType, zoomFocusType)) {
                    DataRcGetPushParams instance2 = DataRcGetPushParams.getInstance();
                    if (instance2.getGyroValue() != 1024 && !this.aa) {
                        if (!this.U) {
                            this.U = true;
                        }
                        DataCameraGetPushShotInfo instance3 = DataCameraGetPushShotInfo.getInstance();
                        minFocusDistance = instance3.getMinFocusDistance();
                        int maxFocusDistance = instance3.getMaxFocusDistance();
                        int minFocusDistanceStep = instance3.getMinFocusDistanceStep();
                        int curFocusDistance = instance3.getCurFocusDistance();
                        i3 = (int) (((float) (instance2.getGyroValue() - 1024)) * 0.015f);
                        if (i3 >= 0 && i3 < 1) {
                            i = 1;
                        } else if (-1 >= i3 || i3 >= 0) {
                            i = i3;
                        }
                        i3 = (i * minFocusDistanceStep) + curFocusDistance;
                        if (i3 >= minFocusDistance) {
                            if (i3 > maxFocusDistance) {
                                minFocusDistance = maxFocusDistance;
                            } else {
                                minFocusDistance = i3;
                            }
                        }
                        if (minFocusDistance != curFocusDistance) {
                            this.aa = false;
                            DataCameraSetFocusDistance.getInstance().a(minFocusDistance).b(1).start(new dji.midware.e.d(this) {
                                final /* synthetic */ DJIGlobalService a;

                                {
                                    this.a = r1;
                                }

                                public void onSuccess(Object obj) {
                                    this.a.aa = false;
                                }

                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                    this.a.aa = false;
                                }
                            });
                        }
                    }
                } else if (dji.pilot.fpv.d.b.a(zoomFocusType, cameraType)) {
                    DataRcGetPushParams instance4 = DataRcGetPushParams.getInstance();
                    if (this.aa) {
                        minFocusDistance = instance4.getGyroValue() - 1024;
                        if (minFocusDistance >= 0) {
                            if (minFocusDistance > 0) {
                                i = 1;
                            } else {
                                i = minFocusDistance;
                            }
                        }
                        if (i != this.ab) {
                            if (i == 0 || this.ab != 0) {
                                new DataCameraSetOpticsZoomMode().a(OpticsZommMode.STOPZOOM, ZoomSpeed.SLOWEST, 0, 0).start(null);
                                i = 0;
                            } else if (i > 0) {
                                new DataCameraSetOpticsZoomMode().a(OpticsZommMode.CONTINUOUS, ZoomSpeed.SLOWEST, 1, 0).start(null);
                            } else if (i < 0) {
                                new DataCameraSetOpticsZoomMode().a(OpticsZommMode.CONTINUOUS, ZoomSpeed.SLOWEST, 0, 0).start(null);
                            }
                            this.ab = i;
                        }
                    } else if (instance4.getGyroValue() != 1024) {
                        if (instance4.getGyroValue() > 1024) {
                            minFocusDistance = 1;
                        } else {
                            minFocusDistance = -1;
                        }
                        this.ab = minFocusDistance;
                        this.aa = true;
                        this.U = true;
                        DataCameraSetOpticsZoomMode dataCameraSetOpticsZoomMode = new DataCameraSetOpticsZoomMode();
                        OpticsZommMode opticsZommMode = OpticsZommMode.CONTINUOUS;
                        ZoomSpeed zoomSpeed = ZoomSpeed.SLOWEST;
                        if (instance4.getGyroValue() <= 1024) {
                            i2 = 0;
                        }
                        dataCameraSetOpticsZoomMode.a(opticsZommMode, zoomSpeed, i2, 0).start(null);
                    }
                }
            }
        }
    }

    private void u() {
        if (dji.pilot.fpv.d.b.h(null) && !ServiceManager.getInstance().isConnected()) {
            try {
                v();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    private void v() throws UnsupportedEncodingException {
        if (g.b((Context) this, w, false)) {
            int b = g.b(getApplicationContext(), u, 0);
            int b2 = g.b(getApplicationContext(), v, 0);
            final String b3 = g.b(getApplicationContext(), "device_sn", "");
            if (!b3.equals("")) {
                String str = "sn=" + b3 + com.alipay.sdk.h.a.b;
                String str2 = "high_temp=" + b + com.alipay.sdk.h.a.b;
                String str3 = "low_temp=" + b2 + com.alipay.sdk.h.a.b;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(str2);
                stringBuilder.append(str3);
                stringBuilder.append("token=");
                com.dji.frame.c.c.b(this).a("https://app-service.skypixel.com/api/camera_temp", new StringEntity(stringBuilder.toString(), "UTF-8"), UrlEncodedParser.CONTENT_TYPE, new dji.thirdparty.afinal.f.a<String>(this) {
                    final /* synthetic */ DJIGlobalService b;

                    public void a(boolean z) {
                    }

                    public void a(long j, long j2) {
                    }

                    public void a(String str) {
                        DJILogHelper.getInstance().LOGD("uploadCameraTempratureInfo", "^^^^^^success" + str + "sn:" + b3, false, false);
                        g.a(this.b.getApplicationContext(), DJIGlobalService.w, false);
                    }

                    public void a(Throwable th, int i, String str) {
                        DJILogHelper.getInstance().LOGD("uploadCameraTempratureInfo", com.alipay.sdk.j.f.b + i + str, false, false);
                    }
                });
            }
        }
    }

    private void w() {
        if ((p.equals("") || q.equals("")) && dji.midware.f.a.getInstance().d() == dji.midware.f.b.WIFI) {
            DJILogHelper.getInstance().LOGD("", "*****开始获取wifi信息", false, false);
            DataWifiGetSSID instance = DataWifiGetSSID.getInstance();
            ProductType c = i.getInstance().c();
            if (dji.pilot.fpv.d.b.h(c)) {
                instance.setFromLongan(true);
            }
            instance.start(new dji.midware.e.d(this) {
                final /* synthetic */ DJIGlobalService a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    DJIGlobalService.p = DataWifiGetSSID.getInstance().getSSID();
                    DJILogHelper.getInstance().LOGD("", "onSuccess ssid:" + DJIGlobalService.p, false, false);
                    DataWifiGetSSID.getInstance().setFromLongan(false);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD("", "onFailure ssid:", false, false);
                    DataWifiGetSSID.getInstance().setFromLongan(false);
                }
            });
            DataWifiGetPassword instance2 = DataWifiGetPassword.getInstance();
            if (dji.logic.f.d.a(c)) {
                instance2.setFromLongan(true);
            }
            instance2.start(new dji.midware.e.d(this) {
                final /* synthetic */ DJIGlobalService a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    DJIGlobalService.q = DataWifiGetPassword.getInstance().getPassword();
                    DJILogHelper.getInstance().LOGD("", "onSuccess mWifiPW:" + DJIGlobalService.q, false, false);
                    DataWifiGetPassword.getInstance().setFromLongan(false);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    DJILogHelper.getInstance().LOGD("", "onFailure pw:", false, false);
                    DataWifiGetPassword.getInstance().setFromLongan(false);
                }
            });
        }
    }

    public static String b() {
        return p;
    }

    public static String c() {
        return q;
    }
}
