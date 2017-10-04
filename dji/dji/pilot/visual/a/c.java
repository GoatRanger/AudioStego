package dji.pilot.visual.a;

import android.os.Handler.Callback;
import android.support.v4.widget.AutoScrollHelper;
import com.alipay.sdk.j.i;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetImageSize.RatioType;
import dji.midware.data.model.P3.DataCameraGetImageSize.SizeType;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetAELock;
import dji.midware.data.model.P3.DataCameraSetExposureMode;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetImageSize;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataCameraSetQuickPlayBack;
import dji.midware.data.model.P3.DataCameraSetTrackingParms;
import dji.midware.data.model.P3.DataCameraSetVideoFormat;
import dji.midware.data.model.P3.DataEyeGetPushAvoidanceParam;
import dji.midware.data.model.P3.DataEyeGetPushException;
import dji.midware.data.model.P3.DataEyeGetPushException.TrackExceptionStatus;
import dji.midware.data.model.P3.DataEyeGetPushFrontAvoidance.SensorType;
import dji.midware.data.model.P3.DataEyeGetPushFunctionList;
import dji.midware.data.model.P3.DataEyeGetPushPointState;
import dji.midware.data.model.P3.DataEyeGetPushTrackStatus;
import dji.midware.data.model.P3.DataFlycGetPushAvoidParam;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataGimbalControl;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataSingleCommonCtrl;
import dji.midware.data.model.P3.DataSingleCommonCtrl.CtrlState;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.midware.util.a.b;
import dji.pilot.R;
import dji.pilot.fpv.model.n;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.publics.e.a;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.objects.k;
import dji.pilot.publics.objects.k$a;
import dji.pilot.visual.a.g.d;
import dji.pilot.visual.a.g.e;
import dji.pilot.visual.a.g.f;
import java.util.Arrays;

public class c implements k$a, g {
    private static final int E = 256;
    private static final int F = 257;
    private static final int G = 768;
    private static final int H = 769;
    private static final int I = 770;
    private static final int J = 771;
    private static final int K = 772;
    private static final int L = 773;
    private static final int M = 774;
    private static final int N = 775;
    private static final int O = 1024;
    private static final long P = 800;
    private static final long Q = 0;
    protected static final String a = c.class.getSimpleName();
    private final a A;
    private final float[] B;
    private boolean C;
    private boolean D;
    private volatile boolean R;
    private DataCameraSetTrackingParms S;
    public final int[] b;
    public final int[] c;
    public int d;
    public int e;
    public int f;
    public int g;
    public RatioType h;
    public RatioType i;
    public boolean j;
    protected final a k;
    private volatile e s;
    private volatile int t;
    private volatile f u;
    private boolean v;
    private d w;
    private f x;
    private k y;
    private Callback z;

    public static c getInstance() {
        return b.a();
    }

    public d a() {
        return this.w;
    }

    public f b() {
        return this.x;
    }

    public boolean c() {
        return l() && this.w.a();
    }

    public boolean d() {
        return l() && this.x.b();
    }

    public boolean e() {
        return l() && this.s == e.c;
    }

    public e f() {
        return this.s;
    }

    public void a(e eVar) {
        if (this.s != eVar) {
            if (e.b == this.s) {
                s();
            }
            if (eVar == e.c) {
                if (d()) {
                    this.y.removeMessages(257);
                    this.y.sendEmptyMessageDelayed(257, 0);
                }
            } else if (eVar == e.b) {
                x();
                if (c()) {
                    this.y.removeMessages(256);
                    this.y.sendEmptyMessageDelayed(256, 0);
                }
            }
            this.s = eVar;
            dji.thirdparty.a.c.a().e(eVar);
        }
    }

    public void g() {
        if (l()) {
            new DataSingleCommonCtrl().a(CtrlState.STOP).start(null);
        }
    }

    public void a(boolean z) {
        this.v = z;
    }

    public boolean h() {
        return this.v;
    }

    public a i() {
        if (this.A.a != AutoScrollHelper.NO_MAX) {
            return this.A;
        }
        return null;
    }

    public boolean isFinished() {
        return !l();
    }

    public void j() {
        if (!dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().a((Object) this);
        }
        c(true);
        if (DataOsdGetPushCommon.getInstance().isGetted()) {
            onEventBackgroundThread(DataOsdGetPushHome.getInstance());
            onEventBackgroundThread(DataOsdGetPushCommon.getInstance());
        }
        this.k.a();
    }

    private void t() {
        this.t = Integer.MIN_VALUE;
        this.k.c();
        this.y.removeMessages(1024);
    }

    public void k() {
        t();
        a(f.a);
        if (dji.thirdparty.a.c.a().c((Object) this)) {
            dji.thirdparty.a.c.a().d((Object) this);
        }
        this.k.b();
    }

    private void u() {
        if (DataGimbalGetPushParams.getInstance().isGetted()) {
            onEventBackgroundThread(DataGimbalGetPushParams.getInstance());
        }
    }

    private void v() {
        if (e.b == this.s) {
            s();
        }
        this.s = e.a;
        this.D = false;
        this.C = false;
        this.A.a();
        this.y.removeMessages(256);
        this.y.removeMessages(257);
        dji.thirdparty.a.c.a().e(d.c);
    }

    public void a(int[] iArr, int[] iArr2) {
        this.b[0] = iArr[0];
        this.b[1] = iArr[1] > 0 ? iArr[1] : DJIBaseActivity.screenWidth;
        this.c[0] = iArr2[0];
        this.c[1] = iArr2[1] > 0 ? iArr2[1] : DJIBaseActivity.screenHeight;
        this.d = this.b[1] - this.b[0];
        this.e = this.c[1] - this.c[0];
    }

    public void a(RatioType ratioType, RatioType ratioType2, boolean z) {
        this.h = ratioType;
        this.i = ratioType2;
        this.j = z;
    }

    public float[] a(float f, float f2, float f3, float f4) {
        float[] fArr = this.B;
        Arrays.fill(fArr, 0.0f);
        int i = DJIBaseActivity.screenWidth;
        int i2 = this.c[1] - this.c[0];
        fArr[1] = f2 / ((float) i2);
        fArr[3] = f4 / ((float) i2);
        if (!this.j) {
            fArr[0] = f / ((float) this.d);
            fArr[2] = f3 / ((float) this.d);
        } else if (this.h == RatioType.R_16_9) {
            fArr[0] = (((float) this.b[0]) + f) / ((float) i);
            fArr[2] = f3 / ((float) i);
        } else {
            fArr[0] = (((((float) this.b[0]) + f) / b.b) / ((float) i)) + 0.125f;
            fArr[2] = f3 / (((float) i) * b.b);
        }
        dji.pilot.visual.util.c.a("Screen:" + f + i.b + f2 + i.b + f3 + i.b + f4 + ";Stream: " + Arrays.toString(fArr));
        return fArr;
    }

    public float[] b(float f, float f2, float f3, float f4) {
        float[] fArr = this.B;
        Arrays.fill(fArr, 0.0f);
        int i = DJIBaseActivity.screenWidth;
        int i2 = this.c[1] - this.c[0];
        fArr[1] = ((float) i2) * f2;
        fArr[3] = ((float) i2) * f4;
        if (!this.j) {
            fArr[0] = ((float) this.d) * f;
            fArr[2] = ((float) this.d) * f3;
        } else if (this.h == RatioType.R_16_9) {
            fArr[0] = (((float) i) * f) - ((float) this.b[0]);
            fArr[2] = ((float) i) * f3;
        } else {
            fArr[0] = (((f - 0.125f) * ((float) i)) * b.b) - ((float) this.b[0]);
            fArr[2] = (((float) i) * f3) * b.b;
        }
        dji.pilot.visual.util.c.a("Stream:" + f + i.b + f2 + i.b + f3 + i.b + f4 + ";Screen: " + Arrays.toString(fArr));
        return fArr;
    }

    public float[] c(float f, float f2, float f3, float f4) {
        float[] fArr = this.B;
        Arrays.fill(fArr, 0.0f);
        fArr[0] = f / ((float) this.d);
        fArr[1] = f2 / ((float) this.e);
        fArr[2] = f3 / ((float) this.d);
        fArr[3] = f4 / ((float) this.e);
        return fArr;
    }

    public float[] d(float f, float f2, float f3, float f4) {
        float[] fArr = this.B;
        Arrays.fill(fArr, 0.0f);
        fArr[0] = ((float) this.d) * f;
        fArr[1] = ((float) this.e) * f2;
        fArr[2] = ((float) this.d) * f3;
        fArr[3] = ((float) this.e) * f4;
        return fArr;
    }

    public boolean l() {
        return f.a != this.u;
    }

    public void a(f fVar) {
        if (this.u != fVar) {
            this.u = fVar;
            if (fVar == f.a) {
                v();
            } else {
                u();
            }
            this.k.a(fVar);
            this.w.a(fVar);
            this.x.a(fVar);
            dji.thirdparty.a.c.a().e(fVar);
        }
    }

    public void b(boolean z) {
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a("g_config.avoid_obstacle_limit_cfg.avoid_obstacle_enable_0", "g_config.avoid_obstacle_limit_cfg.user_avoid_enable_0");
        dataFlycSetParams.a(Integer.valueOf(1), Integer.valueOf(1));
        dataFlycSetParams.start(new 1(this));
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        RcModeChannel modeChannel = dataOsdGetPushCommon.getModeChannel();
        DataOsdGetPushHome instance = DataOsdGetPushHome.getInstance();
        boolean a = dji.pilot.fpv.d.b.a(instance.isBeginnerMode(), instance.isMultipleModeOpen());
        FLYC_STATE flycState = dataOsdGetPushCommon.getFlycState();
        if (l()) {
            if (dji.pilot.fpv.d.b.a(dji.midware.data.manager.P3.i.getInstance().c(), a, modeChannel)) {
                a(f.a);
            } else if (FLYC_STATE.AutoLanding == flycState || FLYC_STATE.GoHome == flycState) {
                a(f.a);
            }
        }
        this.k.a(dataOsdGetPushCommon);
    }

    public void onEventBackgroundThread(DataOsdGetPushHome dataOsdGetPushHome) {
        this.k.a(dataOsdGetPushHome);
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (a.c(dji.midware.data.manager.P3.i.getInstance().c())) {
            int i = dataCameraGetPushStateInfo.beInTrackingMode() ? 1 : 0;
            if (i != this.t) {
                if (i == 1 && !(l() && this.s == e.b)) {
                    d(false);
                }
                this.t = i;
            }
        }
    }

    public void onEventBackgroundThread(o oVar) {
        if (oVar != o.ConnectLose) {
            return;
        }
        if (l()) {
            t();
        } else {
            t();
        }
    }

    public void onEventBackgroundThread(p pVar) {
        if (pVar != p.ConnectLose) {
            return;
        }
        if (l()) {
            a(f.a);
            t();
            return;
        }
        t();
    }

    public void onEventBackgroundThread(d dVar) {
        if (d.a == dVar) {
            a(f.b);
        } else if (d.b == dVar) {
            if (l()) {
                g();
                a(f.a);
            }
        } else if (d.c == dVar) {
            this.k.h();
        }
    }

    public void onEventBackgroundThread(n.a aVar) {
        if (aVar == n.a.LEFTMENU_OPT_CLICK && l()) {
            g();
        }
    }

    public void onEventMainThread(dji.pilot.fpv.flightmode.c.b bVar) {
        c(false);
    }

    private void c(boolean z) {
        dji.pilot.fpv.flightmode.c.b a = dji.pilot.fpv.flightmode.c.getInstance().a();
        this.x.a(a);
        dji.pilot.visual.util.c.a("flightMode-" + a);
        if (a == dji.pilot.fpv.flightmode.c.b.c) {
            if (z) {
                a(f.b);
                a(e.b);
            }
        } else if (a == dji.pilot.fpv.flightmode.c.b.b) {
            if (z) {
                a(f.b);
                a(e.c);
            }
        } else if (a == dji.pilot.fpv.flightmode.c.b.d && z) {
            a(f.b);
            a(e.b);
        }
    }

    private boolean w() {
        DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
        MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
        FLYC_STATE flycState = instance.getFlycState();
        DataOsdGetPushHome instance2 = DataOsdGetPushHome.getInstance();
        boolean a = dji.pilot.fpv.d.b.a(instance2.isBeginnerMode(), instance2.isMultipleModeOpen());
        if (!ServiceManager.getInstance().isRemoteOK() || ((a && instance.getModeChannel() != RcModeChannel.CHANNEL_P) || flycState == FLYC_STATE.SPORT || mode == MODE.PLAYBACK || mode == MODE.NEW_PLAYBACK || mode == MODE.DOWNLOAD)) {
            return false;
        }
        return true;
    }

    public void onEventBackgroundThread(DataEyeGetPushTrackStatus dataEyeGetPushTrackStatus) {
        if (l() && this.s == e.b) {
            this.x.a(dataEyeGetPushTrackStatus);
            this.k.a(dataEyeGetPushTrackStatus);
            this.y.removeMessages(257);
            this.y.sendEmptyMessageDelayed(257, P);
        }
    }

    public void onEventBackgroundThread(DataEyeGetPushPointState dataEyeGetPushPointState) {
        if (l() && this.s == e.c) {
            this.k.a(dataEyeGetPushPointState);
            this.w.a(dataEyeGetPushPointState);
            this.y.removeMessages(256);
            this.y.sendEmptyMessageDelayed(256, P);
        }
    }

    public dji.pilot.visual.a.g.c m() {
        return b(SensorType.Front);
    }

    public boolean a(SensorType sensorType) {
        return this.k.a(sensorType);
    }

    public boolean n() {
        return this.k.e();
    }

    public boolean o() {
        return this.k.f();
    }

    public dji.pilot.visual.a.g.c p() {
        return this.k.g();
    }

    public dji.pilot.visual.a.g.c b(SensorType sensorType) {
        return this.k.b(sensorType);
    }

    public g.b c(SensorType sensorType) {
        return this.k.c(sensorType);
    }

    public void onEventBackgroundThread(DataEyeGetPushAvoidanceParam dataEyeGetPushAvoidanceParam) {
        this.k.a(dataEyeGetPushAvoidanceParam);
    }

    public void onEventBackgroundThread(DataEyeGetPushFunctionList dataEyeGetPushFunctionList) {
        this.k.a(dataEyeGetPushFunctionList);
    }

    public void onEventBackgroundThread(DataFlycGetPushAvoidParam dataFlycGetPushAvoidParam) {
        this.k.a(dataFlycGetPushAvoidParam);
    }

    public void onEventMainThread(ProductType productType) {
        this.k.a(productType);
    }

    void q() {
        onEventBackgroundThread(DataEyeGetPushAvoidanceParam.getInstance());
    }

    public int r() {
        return d(SensorType.Front);
    }

    public int d(SensorType sensorType) {
        return this.k.d(sensorType);
    }

    public void onEventBackgroundThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        if (dataGimbalGetPushParams.getPitch() != -1 && l()) {
            float pitch = ((float) dataGimbalGetPushParams.getPitch()) * 0.1f;
            float roll = ((float) dataGimbalGetPushParams.getRoll()) * 0.1f;
            float yaw = ((float) dataGimbalGetPushParams.getYaw()) * 0.1f;
            float yawAngle = ((float) dataGimbalGetPushParams.getYawAngle()) * 0.1f;
            if (Math.abs(pitch - this.A.a) > 0.1f || Math.abs(yaw - this.A.c) > 0.1f || Math.abs(roll - this.A.b) > 0.1f || Math.abs(yawAngle - this.A.d) > 0.1f) {
                this.A.a = pitch;
                this.A.c = yaw;
                this.A.b = roll;
                this.A.d = yawAngle;
                dji.pilot.visual.util.d.a(yaw * dji.pilot.visual.util.d.a, pitch * dji.pilot.visual.util.d.a, roll * dji.pilot.visual.util.d.a);
                dji.thirdparty.a.c.a().e(this.A);
                this.w.a(this.A);
            }
        }
    }

    private void a(DataEyeGetPushException dataEyeGetPushException, boolean z) {
        if (z != this.C) {
            if (this.C) {
                TrackExceptionStatus trackStatus = dataEyeGetPushException.getTrackStatus();
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.a = DJIErrorPopView.d.WARNING;
                bVar.d = 0;
                bVar.b = R.string.visual_track_exception_exit_default;
                if (trackStatus == TrackExceptionStatus.LOST_TIMEOUT) {
                    bVar.d = R.string.visual_track_exception_timeout;
                } else if (trackStatus == TrackExceptionStatus.INVALID_SPEED) {
                    bVar.d = R.string.visual_track_exception_nofusion;
                } else if (trackStatus == TrackExceptionStatus.NONE_IMAGE) {
                    bVar.d = R.string.visual_track_exception_nonimage;
                } else if (trackStatus == TrackExceptionStatus.LOW_FRAME) {
                    bVar.d = R.string.visual_track_exception_lowfps;
                } else if (trackStatus == TrackExceptionStatus.NFZ) {
                    bVar.d = R.string.visual_point_non_fly_zone;
                }
                if (bVar.d != 0) {
                    dji.thirdparty.a.c.a().e(bVar);
                }
            }
            this.C = z;
        }
    }

    private void b(DataEyeGetPushException dataEyeGetPushException, boolean z) {
        if (z != this.D) {
            if (this.D) {
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.a = DJIErrorPopView.d.WARNING;
                if (dataEyeGetPushException.isInNonFlyZone()) {
                    bVar.b = R.string.visual_point_exit_default;
                    bVar.d = R.string.visual_point_non_fly_zone;
                } else if (dataEyeGetPushException.isFusionDataAbnormal()) {
                    bVar.b = R.string.visual_point_exit_default;
                    bVar.d = R.string.visual_track_exception_nofusion;
                } else if (dataEyeGetPushException.cantDetour()) {
                    bVar.b = R.string.visual_point_fail_title;
                    bVar.d = R.string.visual_point_cant_detour_tip;
                } else if (dataEyeGetPushException.isFronImageOverExposure()) {
                    bVar.b = R.string.visual_point_exit_default;
                    bVar.d = R.string.visual_point_over_exposure;
                } else if (dataEyeGetPushException.isFronImageUnderExposure()) {
                    bVar.b = R.string.visual_point_exit_default;
                    bVar.d = R.string.visual_point_under_exposure;
                } else if (dataEyeGetPushException.isFrontImageDiff() || dataEyeGetPushException.isFrontSensorDemarkAbnormal()) {
                    bVar.b = R.string.visual_point_exit_default;
                    bVar.d = R.string.visual_point_no_radar;
                } else if (dataEyeGetPushException.isOutOfRange()) {
                    bVar.b = R.string.visual_point_fail_title;
                    bVar.d = R.string.visual_point_outofrange;
                } else if (!dataEyeGetPushException.isUserTapStop()) {
                    bVar.b = R.string.visual_point_exit_default;
                }
                if (bVar.b != 0) {
                    dji.thirdparty.a.c.a().e(bVar);
                }
            }
            this.D = z;
        }
    }

    public void onEventBackgroundThread(DataEyeGetPushException dataEyeGetPushException) {
        if (l()) {
            a(dataEyeGetPushException, dataEyeGetPushException.isInTracking());
            b(dataEyeGetPushException, dataEyeGetPushException.isInTapFly());
            this.x.a(dataEyeGetPushException);
        }
    }

    private c() {
        this.b = new int[]{0, DJIBaseActivity.screenWidth};
        this.c = new int[]{0, DJIBaseActivity.screenHeight};
        this.d = DJIBaseActivity.screenWidth;
        this.e = DJIBaseActivity.screenHeight;
        this.f = 1;
        this.g = 1;
        this.h = RatioType.R_16_9;
        this.i = RatioType.R_16_9;
        this.j = true;
        this.s = e.a;
        this.t = Integer.MIN_VALUE;
        this.u = f.a;
        this.v = true;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = new a();
        this.B = new float[4];
        this.k = new a();
        this.C = false;
        this.D = false;
        this.R = false;
        this.S = null;
        this.w = new d();
        this.x = new f();
        this.z = new 4(this);
        this.y = new k(this, this.z);
    }

    private void x() {
        this.R = false;
        this.x.i();
        this.y.removeMessages(1024);
        if (a.c(dji.midware.data.manager.P3.i.getInstance().c())) {
            d(true);
            A();
            return;
        }
        y();
    }

    public void s() {
        this.x.j();
        this.y.removeMessages(1024);
        if (a.c(dji.midware.data.manager.P3.i.getInstance().c())) {
            d(false);
        }
    }

    private void d(boolean z) {
        if (this.S == null) {
            this.S = new DataCameraSetTrackingParms().a(0).b(0);
        }
        this.S.a(z).start(new 5(this, z));
    }

    private void y() {
        if (DataCameraGetPushStateInfo.getInstance().getMode() == MODE.RECORD) {
            z();
        } else {
            DataCameraSetMode.getInstance().a(MODE.RECORD).start(new 6(this));
        }
    }

    private void z() {
        DataCameraGetPushShotParams instance = DataCameraGetPushShotParams.getInstance();
        int videoFormat = instance.getVideoFormat();
        int videoFps = instance.getVideoFps();
        if (22 == videoFormat && 3 == videoFps) {
            A();
        } else {
            new DataCameraSetVideoFormat().a().a(22).b(3).start(new 7(this));
        }
    }

    private void A() {
        if (RatioType.R_16_9 == DataCameraGetPushShotParams.getInstance().getImageRatio()) {
            B();
        } else {
            DataCameraSetImageSize.getInstance().a().a(SizeType.DEFAULT).a(RatioType.R_16_9).start(new 8(this));
        }
    }

    private void B() {
        if (1 == DataCameraGetPushShotParams.getInstance().getImageFormat()) {
            C();
        } else {
            new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.SetImageFormat).a(1).start(new 9(this));
        }
    }

    private void C() {
        if (DataCameraGetPushShotParams.getInstance().isAELock()) {
            DataCameraSetAELock.getInstance().a(false).start(new 10(this));
        } else {
            D();
        }
    }

    private void D() {
        if (DataCameraGetPushShotParams.getInstance().getExposureMode() == ExposureMode.P) {
            E();
        } else {
            new DataCameraSetExposureMode().a(ExposureMode.P.a()).start(new 11(this));
        }
    }

    private void E() {
        if (DataCameraGetPushShotParams.getInstance().getExposureCompensation() == 16) {
            F();
        } else {
            new DataBaseCameraSetting().a(dji.sdksharedlib.b.b.J).a(16).start(new 2(this));
        }
    }

    private void F() {
        boolean fastPlayBackEnabled = DataCameraGetPushStateInfo.getInstance().getFastPlayBackEnabled();
        int fastPlayBackTime = DataCameraGetPushStateInfo.getInstance().getFastPlayBackTime();
        if (!fastPlayBackEnabled || fastPlayBackTime == 0) {
            G();
            return;
        }
        DataCameraSetQuickPlayBack.getInstance().a((byte) 0);
        DataCameraSetQuickPlayBack.getInstance().start(new 3(this));
    }

    private void G() {
        if (DataGimbalGetPushParams.getInstance().getMode() != DataGimbalControl.MODE.YawFollow) {
            this.R = true;
            DataSpecialControl.getInstance().setGimbalMode(DataGimbalControl.MODE.YawFollow).start(20);
        }
        if (this.R) {
            DJIErrorPopView.b bVar = new DJIErrorPopView.b();
            bVar.a = DJIErrorPopView.d.NOTIFY;
            bVar.b = R.string.visual_track_camera_setting_tip;
            dji.thirdparty.a.c.a().e(bVar);
            this.R = false;
        }
    }
}
