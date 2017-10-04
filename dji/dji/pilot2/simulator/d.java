package dji.pilot2.simulator;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.widget.LinearLayout;
import com.unity3d.player.UnityPlayer;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataFlycGetPushDeformStatus;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.TRIPOD_STATUS;
import dji.midware.data.model.P3.DataSimulatorSetGetWind;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.publics.widget.a;
import dji.sdk.api.simulator.DJIExecuteIntResultCallback;
import dji.sdk.api.simulator.DJISimulator;
import dji.sdk.api.simulator.DJISimulatorCommend;
import dji.sdk.api.simulator.DJISimulatorGetPushFlycStatusCallBack;
import dji.sdk.api.simulator.DJISimulatorGetPushTypeCallBack;
import dji.thirdparty.a.c;

public class d {
    private static DJISimulatorActivity b = null;
    private static boolean c = false;
    private static final float n = 3.0f;
    private static final double r = Math.pow(2.0d, 16.0d);
    private static final String s = "p3fin";
    private static final String t = "p4_low_fbx2";
    private static final String u = "M100Fix";
    private static final String v = "In1";
    private static final String w = "wm220Final";
    String[] a = new String[]{"g_config.advanced_function.height_limit_enabled_0", "g_config.advanced_function.radius_limit_enabled_0", "g_config.flying_limit.max_height_0", "g_config.flying_limit.max_radius_0"};
    private a d;
    private DJISimulatorCommend e;
    private DJISimulator f = new DJISimulator();
    private DJIExecuteIntResultCallback g;
    private DJISimulatorGetPushFlycStatusCallBack h;
    private DJISimulatorGetPushTypeCallBack i;
    private Boolean j = Boolean.valueOf(false);
    private Boolean k = Boolean.valueOf(false);
    private Boolean l = Boolean.valueOf(false);
    private float[] m = new float[]{7.0f, 0.0f, 0.0f};
    private UnityPlayer o;
    private Context p;
    private a q;

    public static void a(DJISimulatorActivity dJISimulatorActivity) {
        b = dJISimulatorActivity;
    }

    public static void a() {
        if (b != null && !b.isFinishing()) {
            b.finishThis();
        }
    }

    public d(Context context) {
        this.p = context;
    }

    public void b() {
        this.o = new UnityPlayer((ContextWrapper) this.p);
        this.o.requestFocus();
    }

    public void a(LinearLayout linearLayout) {
        linearLayout.addView(this.o.getView());
    }

    public void c() {
        this.e = new DJISimulatorCommend();
        this.e.isUseRealRC = true;
        this.e.isModeFromThridPart = false;
        this.e.isBatterySim = false;
        this.e.pushHz = 20;
        this.e.gpsCount = 10;
        this.e.latitude = 22.537018d;
        this.e.longitude = 113.95364d;
        this.e.altitude = 0.01d;
        this.e.mRoll = true;
        this.e.mPitch = true;
        this.e.mYaw = true;
        this.e.mPositionX = true;
        this.e.mPositionY = true;
        this.e.mPositionZ = true;
        this.e.mLatitude = true;
        this.e.mLongitude = true;
        this.g = new 1(this);
        this.h = new 2(this);
        this.f.setSimulatorGetPushFlycStatusCallBack(this.h);
        this.i = new 3(this);
        this.f.setSimulatorGetPushDroneTypeCallBack(this.i);
    }

    public void a(Configuration configuration) {
        this.o.configurationChanged(configuration);
    }

    public void a(boolean z) {
        this.o.windowFocusChanged(z);
    }

    public void d() {
        this.o.resume();
        c.a().a((Object) this);
        if (ServiceManager.getInstance().isRemoteOK()) {
            onEventBackgroundThread(o.ConnectOK);
            j();
        }
        ServiceManager.getInstance().pauseService(false);
    }

    public void e() {
        this.o.pause();
        this.f.closeSimulateCommend(this.g);
        this.f.stopHeartConnection();
        c = false;
        c.a().d((Object) this);
    }

    public static void b(boolean z) {
        c = z;
    }

    public void onEventBackgroundThread(o oVar) {
        switch (6.a[oVar.ordinal()]) {
            case 1:
                k();
                return;
            case 2:
                ((DJISimulatorActivity) this.p).runOnUiThread(new 4(this));
                return;
            default:
                return;
        }
    }

    private void k() {
        this.f.startHeartConnection();
        b bVar = new b();
        bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.NOTIFY;
        bVar.b = R.string.v2_smlt_ready_tip;
        c.a().e(bVar);
        this.f.startSimulateCommend(this.e, this.g);
    }

    public void f() {
        this.f.closeSimulateCommend(this.g);
        this.f.stopHeartConnection();
        k();
    }

    void g() {
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        Number[] numberArr = new Number[]{Integer.valueOf(1), Integer.valueOf(1), Float.valueOf(40.0f), Float.valueOf(40.0f)};
        dataFlycSetParams.a(this.a);
        dataFlycSetParams.a(numberArr);
        dataFlycSetParams.start(new 5(this));
    }

    public static boolean h() {
        return c;
    }

    public void a(float f, int i) {
        this.f.setWind(f, i);
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public void onEventBackgroundThread(DataSimulatorSetGetWind dataSimulatorSetGetWind) {
        if (this.d != null) {
            int windSpeedX = dataSimulatorSetGetWind.getWindSpeedX();
            int windSpeedY = dataSimulatorSetGetWind.getWindSpeedY();
            windSpeedX = (int) (((double) windSpeedX) - (((double) ((windSpeedX >> 15) & 1)) * r));
            windSpeedY = (int) (((double) windSpeedY) - (((double) ((windSpeedY >> 15) & 1)) * r));
            this.d.a(((float) Math.sqrt((double) ((windSpeedX * windSpeedX) + (windSpeedY * windSpeedY)))) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity, (int) Math.toDegrees(Math.atan2((double) windSpeedY, (double) windSpeedX)));
        }
    }

    public void onEventMainThread(DataFlycGetPushDeformStatus dataFlycGetPushDeformStatus) {
        TRIPOD_STATUS deformStatus = dataFlycGetPushDeformStatus.getDeformStatus();
        if (deformStatus.equals(TRIPOD_STATUS.FOLOING)) {
            e.a(e.a, "ShowWing", "");
        } else if (deformStatus.equals(TRIPOD_STATUS.STRETCHING)) {
            e.a(e.a, "HideWing", "");
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (this.j.booleanValue() != dataOsdGetPushCommon.isMotorUp()) {
            this.j = Boolean.valueOf(dataOsdGetPushCommon.isMotorUp());
            if (this.j.booleanValue()) {
                e.a(e.a, "StartAnim", "");
            } else {
                e.a(e.a, "StopAnim", "");
            }
        }
    }

    public void i() {
        e.a(e.a, "SwitchCamera", "");
    }

    public void j() {
        String str = s;
        switch (6.b[i.getInstance().c().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                str = s;
                break;
            case 5:
            case 6:
                str = t;
                break;
            case 7:
                str = u;
                break;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                str = v;
                break;
            case 13:
            case 14:
                str = w;
                break;
        }
        e.a(e.a, "SetProductType", str);
    }
}
