package dji.pilot.fpv.topbar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.here.odnp.config.OdnpConfigStatic;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.m;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.Data2100GetPushCheckStatus;
import dji.midware.data.model.P3.DataA2PushCommom;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.RecordType;
import dji.midware.data.model.P3.DataCameraGetPushTauParam;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataCameraSetAELock;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetFocusParam;
import dji.midware.data.model.P3.DataCameraSetFocusParam.ZoomMode;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.data.model.P3.DataCameraTauParamAGC.AGCType;
import dji.midware.data.model.P3.DataCameraTauParamThermometricEnable;
import dji.midware.data.model.P3.DataCameraTauParamThermometricEnable.ThermometricType;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon.ConnStatus;
import dji.midware.data.model.P3.DataEyeGetPushFrontAvoidance.SensorType;
import dji.midware.data.model.P3.DataFlycGetIoc;
import dji.midware.data.model.P3.DataFlycGetPushAvoid;
import dji.midware.data.model.P3.DataFlycGetPushCheckStatus;
import dji.midware.data.model.P3.DataFlycGetPushDeformStatus;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataOsdGetPushChannalStatus;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.BatteryType;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataOsdGetPushCommon.TRIPOD_STATUS;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataOsdGetPushSignalQuality;
import dji.midware.data.model.P3.DataRTKPushStatus;
import dji.midware.data.model.P3.DataRcGetPushBatteryInfo;
import dji.midware.data.model.P3.DataRcSetMaster;
import dji.midware.data.model.P3.DataSmartBatteryGetPushDynamicData;
import dji.midware.data.model.P3.DataWifiGetPushElecSignal;
import dji.midware.data.model.P3.DataWifiGetPushSignal;
import dji.pilot.R;
import dji.pilot.battery.a.a$e;
import dji.pilot.dji_groundstation.a.e;
import dji.pilot.fpv.activity.g;
import dji.pilot.fpv.activity.h;
import dji.pilot.fpv.camera.more.d$c;
import dji.pilot.fpv.control.DJIGenSettingDataManager$b;
import dji.pilot.fpv.control.j;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.model.n$b;
import dji.pilot.fpv.rightbar.DJISwitchDefogView;
import dji.pilot.fpv.topbar.tip.DJIFpvTipView;
import dji.pilot.fpv.view.DJIErrorPopView.f;
import dji.pilot.fpv.view.DJISmartBatteryView;
import dji.pilot.liveshare.DJILiveShareActivity;
import dji.pilot.liveshare.LiveShareFpvTopView;
import dji.pilot.liveshare.Youtube.CustomModeActivity;
import dji.pilot.liveshare.Youtube.youtubeLiveActivity;
import dji.pilot.publics.c.c$a;
import dji.pilot.publics.control.a$d;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.reflect.AppPublicReflect.YoutubeChangeEvent;
import dji.pilot.visual.a.g$b;
import dji.pilot.visual.a.g$d;
import dji.pilot.visual.a.g$e;
import dji.pilot.visual.a.g$f;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import lecho.lib.hellocharts.model.l;

public class DJIFpvTopBaseView extends DJIRelativeLayout implements OnClickListener, d$c, s {
    protected static final int A = 4098;
    protected static final int B = 4099;
    protected static final int C = 4100;
    protected static final int D = 8192;
    protected static final long E = 100;
    protected static final String F = "99：99";
    protected static int G = 0;
    protected static int H = 0;
    protected static final String I = "--/--";
    protected static final int J = Integer.MAX_VALUE;
    protected static final int bX = 3;
    protected static final int cF = 2000;
    protected static final String i = DJIFpvTopBaseView.class.getSimpleName();
    protected static final int j = 3000;
    protected static final int k = 0;
    protected static final int l = 1;
    protected static final int m = 2;
    protected static final int n = 4;
    protected static final int o = 8;
    protected static final int p = 16;
    protected static final int q = 32;
    protected static final int r = 64;
    protected static final int s = 128;
    protected static final int t = 256;
    protected static final int u = 512;
    protected static final int v = 1024;
    protected static final int w = 2048;
    protected static final int x = 4095;
    protected static final int y = 4096;
    protected static final int z = 4097;
    protected DJILinearLayout K = null;
    protected DJIImageView L = null;
    protected DJILinearLayout M = null;
    protected DJITextView N = null;
    protected DJIRelativeLayout O = null;
    protected DJIImageView P = null;
    protected DJIImageView Q = null;
    protected DJITextView R = null;
    protected DJITextView S = null;
    protected DJILinearLayout T = null;
    protected DJIImageView U = null;
    protected DJIImageView V = null;
    protected DJILinearLayout W = null;
    protected DJIImageView aA = null;
    protected DJITextView aB = null;
    protected DJILinearLayout aC = null;
    protected DJITextView aD = null;
    protected DJIImageView aE = null;
    protected DJITextView aF = null;
    protected DJITextView aG = null;
    protected DJITextView aH = null;
    protected DJIFpvTipView aI = null;
    protected DJITextView aJ = null;
    protected LiveShareFpvTopView aK = null;
    protected Context aL = null;
    protected b aM = null;
    protected OnDismissListener aN = null;
    protected OnShowListener aO = null;
    protected View aP = null;
    protected d aQ = null;
    protected volatile int aR = 0;
    protected OnClickListener aS = null;
    protected long aT = 0;
    protected long aU = 0;
    protected long aV = 0;
    protected dji.pilot.fpv.activity.d aW = null;
    protected dji.pilot.fpv.inner.a aX = null;
    protected g aY = null;
    protected dji.pilot.fpv.activity.c aZ = null;
    protected DJIImageView aa = null;
    protected DJILinearLayout ab = null;
    protected DJIImageView ac = null;
    protected DJITextView ad = null;
    protected DJITextView ae = null;
    protected DJILinearLayout af = null;
    protected DJIImageView ag = null;
    protected DJITextView ah = null;
    protected DJIImageView ai = null;
    protected DJILinearLayout aj = null;
    protected DJIImageView ak = null;
    protected DJISmartBatteryView al = null;
    protected DJITextView am = null;
    protected View an = null;
    protected DJIImageView ao = null;
    protected DJILinearLayout ap = null;
    protected DJITextView aq = null;
    protected DJITextView ar = null;
    protected DJIImageView as = null;
    protected DJITextView at = null;
    protected DJITextView au = null;
    protected DJITextView av = null;
    protected DJITextView aw = null;
    protected DJIImageView ax = null;
    protected DJITextView ay = null;
    protected DJITextView az = null;
    protected int bA = 255;
    protected SDCardState bB = SDCardState.None;
    protected MODE bC = MODE.OTHER;
    protected RecordType bD = RecordType.OTHER;
    protected long bE = -1;
    protected int bF = -1;
    protected int bG = 0;
    protected int bH = 0;
    protected int bI = -1;
    protected int bJ = -1;
    protected String bK = null;
    protected boolean bL = false;
    protected ExposureMode bM = ExposureMode.i;
    protected int bN = 0;
    protected boolean bO = false;
    protected CameraType bP = CameraType.OTHER;
    protected FuselageFocusMode bQ = FuselageFocusMode.OTHER;
    protected int bR = -1;
    protected int bS = -1;
    protected boolean bT = false;
    protected AGCType bU = AGCType.j;
    protected int bV = -1;
    protected ThermometricType bW = ThermometricType.d;
    protected DataRcSetMaster.MODE bY = DataRcSetMaster.MODE.d;
    protected int bZ = 3;
    protected dji.setting.ui.b ba;
    protected volatile boolean bb = true;
    protected Dialog bc = null;
    protected boolean bd = false;
    protected FLYC_STATE be = FLYC_STATE.OTHER;
    protected boolean bf = false;
    protected boolean bg = false;
    protected int bh = -1;
    protected int bi = 0;
    protected int bj = 0;
    protected int bk = -1;
    protected int bl = -1;
    protected int bm = 0;
    protected int bn = -1;
    protected boolean bo = false;
    protected DataFlycGetIoc.MODE bp = DataFlycGetIoc.MODE.CourseLock;
    protected boolean bq = false;
    protected RcModeChannel br = RcModeChannel.CHANNEL_UNKNOWN;
    protected boolean bs = false;
    protected int bt = -1;
    protected int bu = 0;
    protected int bv = 0;
    protected BatteryType bw = BatteryType.Unknown;
    protected ConnStatus bx = ConnStatus.NORMAL;
    protected int by = 0;
    protected int bz = 0;
    protected boolean cA = false;
    protected boolean cB = true;
    protected boolean cC = true;
    protected final DecimalFormat cD = new DecimalFormat("#.##");
    protected ProductType cE = ProductType.Orange;
    protected volatile long cG = 0;
    protected boolean cH = false;
    private DJISwitchDefogView cI;
    private DJILinearLayout cJ;
    private boolean cK = false;
    private boolean cL = false;
    private int cM = -1;
    protected TRIPOD_STATUS ca = TRIPOD_STATUS.UNKNOWN;
    protected DataOsdGetPushCommon cb = null;
    protected DataCameraGetPushStateInfo cc = null;
    protected DataOsdGetPushSignalQuality cd = null;
    protected DataFlycGetPushSmartBattery ce = null;
    protected DataCenterGetPushBatteryCommon cf = null;
    protected DataRcGetPushBatteryInfo cg = null;
    protected DataCameraGetPushShotParams ch = null;
    protected DataCameraGetPushShotInfo ci = null;
    protected DataOsdGetPushHome cj = null;
    protected DataFlycGetPushDeformStatus ck = null;
    protected DataWifiGetPushSignal cl = null;
    protected DataCameraGetPushTauParam cm = null;
    protected int[] cn = null;
    protected String[] co = null;
    protected String[] cp = null;
    protected int[] cq = null;
    protected String[] cr = null;
    protected String[] cs = null;
    protected String[] ct = null;
    protected int[] cu = null;
    protected int[] cv = null;
    protected String[] cw = null;
    protected int[] cx = null;
    protected String[] cy = null;
    protected boolean cz = false;

    public interface b {
        void a();

        void a(int i, boolean z);

        void b(int i, boolean z);
    }

    public enum a {
        Mc_Sensor,
        Mc_Guidance,
        Flyc_Compass,
        Rc_Mode
    }

    public enum c {
        BATTERY,
        MC_SHOWBY_GUIDANCE
    }

    protected static final class d extends Handler {
        private final WeakReference<DJIFpvTopBaseView> a;

        public d(DJIFpvTopBaseView dJIFpvTopBaseView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIFpvTopBaseView);
        }

        public void handleMessage(Message message) {
            DJIFpvTopBaseView dJIFpvTopBaseView = (DJIFpvTopBaseView) this.a.get();
            if (dJIFpvTopBaseView != null) {
                switch (message.what) {
                    case 4096:
                        dJIFpvTopBaseView.update();
                        return;
                    case 4097:
                        dJIFpvTopBaseView.connect();
                        return;
                    case 4098:
                        dJIFpvTopBaseView.disconnect();
                        return;
                    case 4099:
                        dJIFpvTopBaseView.cameraConnect();
                        return;
                    case 4100:
                        dJIFpvTopBaseView.cameraDisconnect();
                        return;
                    case 8192:
                        dJIFpvTopBaseView.a(message.obj != null ? (DataRcSetMaster.MODE) message.obj : null);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJIFpvTopBaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.aL = context;
        A();
    }

    public void setOnEventListener(b bVar) {
        this.aM = bVar;
    }

    public void dispatchOnCreate() {
        dji.thirdparty.a.c.a().a(this);
        this.aI.resetStatus(true);
        onEventMainThread(i.getInstance().c());
        this.aQ.sendEmptyMessage(8192);
        this.aK.dispatchOnCreate();
        if (!dji.pilot.liveshare.b.getInstance().isLaunch()) {
            this.aK.setVisibility(8);
        }
        onEventMainThread(DataRTKPushStatus.getInstance());
    }

    public void dispatchOnDestroy() {
        this.aK.dispatchOnDestroy();
        hideDialog();
        this.aQ.removeMessages(8192);
        this.aQ.removeMessages(4096);
        dji.thirdparty.a.c.a().d(this);
    }

    private boolean U() {
        return CameraType.DJICameraTypeFC550Raw != this.bP;
    }

    public void handleCameraWidgetVisibility(boolean z, boolean z2) {
        String a = dji.pilot.fpv.model.b.a();
        if (!"large".equals(a) && !"xlarge".equals(a)) {
            if (!z || z2) {
                this.cC = U();
                if (this.cC) {
                    h();
                    l();
                    o();
                    b();
                }
                if (this.cI.canShow()) {
                    this.cI.setVisibility(0);
                    return;
                }
                return;
            }
            this.cC = false;
            this.aC.go();
            this.ap.go();
            this.aq.go();
            this.cI.setVisibility(8);
            m();
            p();
            a();
        }
    }

    public void changeBg(boolean z) {
    }

    public boolean hasDlgShowing() {
        return this.bc != null;
    }

    public void hideDialog() {
        if (this.bc != null) {
            this.bc.dismiss();
        }
    }

    public void connect() {
        this.aI.connect();
        if (DataOsdGetPushSignalQuality.getInstance().isGetted()) {
            s();
        }
    }

    public void disconnect() {
        G();
        D();
        E();
        F();
        this.aI.disconnect();
        if (dji.pilot.fpv.d.b.j(this.cE) && this.aj.getVisibility() == 0) {
            this.aj.hide();
        }
        V();
        this.bb = true;
    }

    private void V() {
        if (dji.pilot.fpv.d.b.o()) {
            this.S.go();
            this.O.setEnabled(false);
        }
    }

    public void cameraConnect() {
        a(i.getInstance().c(), true);
        this.aI.cameraConnect();
        this.bd = true;
    }

    public void cameraDisconnect() {
        H();
        E();
        F();
        I();
        D();
        this.aI.cameraDisconnect();
        this.bb = true;
        this.bd = false;
    }

    protected void a(DataRcSetMaster.MODE mode) {
        if (mode == null) {
            this.bY = dji.pilot.c.d.b;
        } else {
            this.bY = mode;
        }
        if (this.bY == DataRcSetMaster.MODE.b || this.cH) {
            this.M.setEnabled(false);
            this.ab.setEnabled(false);
            this.af.setEnabled(false);
            Z();
            return;
        }
        this.M.setEnabled(true);
        this.ab.setEnabled(true);
        this.af.setEnabled(true);
    }

    protected void a() {
        if (this.ao != null) {
            this.ao.go();
        }
    }

    protected void b() {
        if (this.ao != null && this.cC) {
            if (j.a()) {
                this.ao.show();
            } else {
                this.ao.go();
            }
        }
    }

    public void update() {
        if (x == this.aR) {
            b();
        }
        if ((this.aR & 1) != 0) {
            if (this.cb.isGetted()) {
                g();
            }
            this.aR &= -2;
        }
        if ((this.aR & 16) != 0) {
            if (this.cf.isGetted() || dji.pilot.fpv.d.b.p()) {
                if (System.currentTimeMillis() - this.cG > 2000) {
                    this.aI.update(this.cf);
                }
                w();
            }
            this.aR &= -17;
        }
        if ((this.aR & 8) != 0) {
            if (dji.pilot.fpv.d.b.n()) {
                int voltage = this.ce.getVoltage();
                if (this.bn != voltage) {
                    this.bn = voltage;
                    float f = (((float) voltage) * 1.0f) / 1000.0f;
                    this.ad.setText(this.aL.getString(R.string.battery_voltage_unit, new Object[]{Float.valueOf(f)}));
                    this.ad.setTextColor(this.aL.getResources().getColor(R.color.om));
                    this.ac.setImageResource(R.drawable.osd_electric_btn_normal);
                    if (dji.pilot.battery.a.a.b(f) != 0) {
                        this.ad.setTextColor(this.aL.getResources().getColor(R.color.a6));
                        this.ac.setImageResource(R.drawable.osd_electric_low);
                    }
                }
            } else {
                v();
            }
            this.aI.update(this.ce);
            this.aR &= -9;
        }
        if ((this.aR & 2) != 0) {
            this.aI.update(this.cc);
            j();
            this.aR &= -3;
        }
        if ((this.aR & 4) != 0) {
            s();
            this.aR &= -5;
        }
        if ((this.aR & 32) != 0) {
            this.aI.update(this.cg);
            this.aR &= -33;
        }
        if ((this.aR & 64) != 0) {
            r();
            this.aR &= -65;
        }
        if ((this.aR & 1024) != 0) {
            i();
            this.aR &= -1025;
        }
        if ((this.aR & 2048) != 0) {
            q();
            this.aR &= -2049;
        }
        if ((this.aR & 128) != 0) {
            if (this.cj.isGetted()) {
                e();
            }
            this.aR &= -129;
        }
        if ((this.aR & 256) != 0) {
            a(true);
            this.aR &= -257;
        }
        if ((this.aR & 512) != 0) {
            this.aI.update(this.cl);
            t();
            this.aR &= -513;
        }
    }

    protected void a(boolean z) {
    }

    protected void c() {
        if (dji.pilot.fpv.d.b.n(this.cE) || dji.pilot.fpv.d.b.j(this.cE)) {
            this.af.go();
            return;
        }
        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
        if (instance == null || !instance.c()) {
            this.af.show();
        } else {
            this.af.hide();
        }
    }

    protected void d() {
        if (dji.pilot.fpv.d.b.n(this.cE) || dji.pilot.fpv.d.b.j(this.cE)) {
            this.af.go();
        } else {
            this.af.hide();
        }
    }

    public void onEventMainThread(DataFlycGetPushAvoid dataFlycGetPushAvoid) {
        if (dji.pilot.fpv.d.b.j(this.cE)) {
            this.aj.show();
            if (dataFlycGetPushAvoid.isVisualSensorEnable() && dataFlycGetPushAvoid.isVisualSensorWork()) {
                this.ak.setImageResource(R.drawable.fpv_topbar_vision_normal_icon);
            } else {
                this.ak.setImageResource(R.drawable.fpv_topbar_vision_disable_icon);
            }
        }
    }

    public void onEventMainThread(g$b dji_pilot_visual_a_g_b) {
        W();
    }

    public void onEventMainThread(g$d dji_pilot_visual_a_g_d) {
        if (g$d.AVOID_CHANGED == dji_pilot_visual_a_g_d) {
            W();
        }
    }

    private void W() {
        dji.pilot.visual.a.g.c p = dji.pilot.visual.a.c.getInstance().p();
        if (dji.pilot.visual.a.g.c.e == p) {
            this.ak.setImageResource(R.drawable.fpv_topbar_vision_normal_icon);
        } else if (dji.pilot.visual.a.g.c.a == p) {
            this.ak.setImageResource(R.drawable.fpv_topbar_vision_close_icon);
        } else {
            this.ak.setImageResource(R.drawable.fpv_topbar_vision_disable_icon);
        }
    }

    public void onEventMainThread(e eVar) {
        if (eVar.s == 4) {
            if (!(eVar.t instanceof Boolean)) {
                return;
            }
            if (((Boolean) eVar.t).booleanValue()) {
                d();
            } else {
                c();
            }
        } else if (eVar.s == 17) {
            show();
            startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.bt));
        } else if (eVar.s == 18) {
            go();
            startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.bu));
        }
    }

    protected void e() {
        boolean isIOCEnabled = this.cj.isIOCEnabled();
        DataFlycGetIoc.MODE iOCMode = this.cj.getIOCMode();
        RcModeChannel modeChannel = this.cb.getModeChannel();
        boolean a = dji.pilot.fpv.d.b.a(this.cj.isBeginnerMode(), this.cj.isMultipleModeOpen());
        dji.pilot.fpv.view.DJIErrorPopView.b bVar;
        if (a) {
            if (this.br != modeChannel) {
                a(this.be, this.bf);
            }
            if (modeChannel != RcModeChannel.CHANNEL_A) {
                if (!(this.br == modeChannel && dji.pilot.fpv.d.b.a(1))) {
                    bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                    bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.b;
                    bVar.b = R.string.fpv_errorpop_flystatus_atti;
                    bVar.d = R.string.fpv_errorpop_flystatus_atti_desc;
                    bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.c;
                    bVar.g = f.b;
                    dji.thirdparty.a.c.a().e(bVar);
                }
                dji.pilot.fpv.d.b.a(1, true);
            } else if (dji.pilot.fpv.d.b.a(1)) {
                this.aQ.postDelayed(new Runnable(this) {
                    final /* synthetic */ DJIFpvTopBaseView a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (this.a.cb.getModeChannel() == RcModeChannel.CHANNEL_A) {
                            dji.pilot.fpv.d.b.a(1, false);
                            dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                            bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.b;
                            bVar.b = R.string.fpv_errorpop_flystatus_atti;
                            bVar.d = R.string.fpv_errorpop_flystatus_atti_desc;
                            bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.c;
                            dji.thirdparty.a.c.a().e(bVar);
                        }
                    }
                }, 1200);
            }
        } else if (!(this.bs == a && dji.pilot.fpv.d.b.a(1))) {
            bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
            bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.b;
            bVar.b = R.string.fpv_errorpop_flystatus_atti;
            bVar.d = R.string.fpv_errorpop_flystatus_atti_desc;
            bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.c;
            bVar.g = f.b;
            dji.thirdparty.a.c.a().e(bVar);
            dji.pilot.fpv.d.b.a(1, true);
        }
        if (this.bq != isIOCEnabled || this.br != modeChannel || this.bp != iOCMode || this.bs != a) {
            this.bq = isIOCEnabled;
            this.bp = iOCMode;
            RcModeChannel rcModeChannel = this.br;
            this.br = modeChannel;
            this.bs = a;
            dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
            if (instance != null && instance.c()) {
                return;
            }
            if (!a) {
                Z();
                d();
            } else if (this.br != RcModeChannel.CHANNEL_F) {
                Z();
                d();
                a(false);
                if (this.br != RcModeChannel.CHANNEL_A) {
                }
            } else if (isIOCEnabled) {
                c();
                if (this.bY == DataRcSetMaster.MODE.a && ((this.aY == null || !this.aY.isShowing()) && isShown() && rcModeChannel != RcModeChannel.CHANNEL_UNKNOWN)) {
                    hideDialog();
                    Y();
                }
                if (iOCMode == DataFlycGetIoc.MODE.CourseLock) {
                    this.ah.setText(R.string.direct_lock_cl);
                } else if (iOCMode == DataFlycGetIoc.MODE.HomeLock) {
                    this.ah.setText(R.string.direct_lock_hl);
                } else if (iOCMode == DataFlycGetIoc.MODE.HotspotSurround) {
                    this.ah.setText(R.string.direct_lock_pl);
                }
            } else {
                Z();
                d();
                a(false);
                if (rcModeChannel != RcModeChannel.CHANNEL_F) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - this.aT >= OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME) {
                        this.aT = currentTimeMillis;
                        dji.pilot.fpv.view.DJIErrorPopView.b bVar2 = new dji.pilot.fpv.view.DJIErrorPopView.b();
                        bVar2.a = dji.pilot.fpv.view.DJIErrorPopView.d.a;
                        bVar2.b = R.string.fpv_flyc_channel_tips_needopenioc;
                        dji.thirdparty.a.c.a().e(bVar2);
                    }
                }
            }
        }
    }

    protected void a(FLYC_STATE flyc_state, boolean z) {
        this.be = flyc_state;
        this.bf = z;
        this.N.setText(dji.pilot.fpv.d.b.a(flyc_state, z, false)[0]);
    }

    public void onEventMainThread(dji.pilot.groundStation.a.a.d dVar) {
        this.N.setText(dji.pilot.fpv.d.b.a(this.be, this.bf, false)[0]);
    }

    public void onEventMainThread(g$e dji_pilot_visual_a_g_e) {
        a(this.be, this.bf);
    }

    public void onEventMainThread(g$f dji_pilot_visual_a_g_f) {
        a(this.be, this.bf);
    }

    protected void f() {
        this.R.show();
    }

    protected void a(int i) {
        this.bh = i;
        if (i >= 50) {
            i = 0;
        }
        if (i > 0) {
            this.P.setEnabled(true);
            f();
        } else {
            this.P.setEnabled(false);
            this.R.go();
        }
        this.R.setText(String.valueOf(i));
        if (this.cb.getFlycVersion() < 6 || dji.pilot.fpv.d.b.f()) {
            this.bm = dji.pilot.fpv.d.b.c(i);
            this.Q.setImageLevel(this.bm);
        }
    }

    protected int getBatteryNormalColor() {
        return R.color.a3;
    }

    protected void b(int i) {
        if (i != -1) {
            this.bk = i;
            if (dji.pilot.publics.e.a.k() && dji.pilot.publics.e.a.j()) {
                this.ac.setImageResource(R.drawable.osd_electric_low);
                this.ad.setTextColor(this.aL.getResources().getColor(R.color.gj));
                this.ad.setText(R.string.fpv_battery_error);
            } else if (i > 100) {
                this.ac.setImageResource(R.drawable.osd_electric_low);
                this.ad.setTextColor(this.aL.getResources().getColor(R.color.gj));
                this.ad.setText(R.string.fpv_battery_error);
            } else {
                int voltageWarning = this.cb.getVoltageWarning();
                if (this.bx == ConnStatus.EXCEPTION) {
                    this.ac.setImageResource(R.drawable.osd_electric_warning);
                    this.ad.setTextColor(this.aL.getResources().getColor(R.color.om));
                    this.ad.setText(R.string.fpv_default_str);
                } else if (this.bx == ConnStatus.NORMAL) {
                    if (i <= this.bu || voltageWarning == 1 || voltageWarning == 2) {
                        this.ac.setImageResource(R.drawable.osd_electric_low);
                        this.ad.setTextColor(this.aL.getResources().getColor(R.color.gj));
                    } else {
                        this.ac.setImageResource(R.drawable.osd_electric_btn_normal);
                        this.ad.setTextColor(this.aL.getResources().getColor(getBatteryNormalColor()));
                    }
                    this.ad.setText(b(this.aL.getString(R.string.fpv_percent, new Object[]{Integer.valueOf(i)}), 1, 1));
                } else {
                    this.ac.setImageResource(R.drawable.osd_electric_warning);
                    if (i <= this.bu || voltageWarning == 1 || voltageWarning == 2) {
                        this.ad.setTextColor(this.aL.getResources().getColor(R.color.gj));
                    } else {
                        this.ad.setTextColor(this.aL.getResources().getColor(getBatteryNormalColor()));
                    }
                    this.ad.setText(b(this.aL.getString(R.string.fpv_percent, new Object[]{Integer.valueOf(i)}), 1, 1));
                }
            }
        }
    }

    protected void g() {
        FLYC_STATE flyc_state = this.be;
        FLYC_STATE flycState = this.cb.getFlycState();
        boolean isVisionUsed = this.cb.isVisionUsed();
        boolean a = dji.pilot.fpv.d.b.a(this.cj.isBeginnerMode(), this.cj.isMultipleModeOpen());
        if (!(this.be == flycState && this.bf == isVisionUsed && a == this.bg)) {
            this.bg = a;
            a(flycState, isVisionUsed);
        }
        int gpsNum = this.cb.getGpsNum();
        if (this.bh != gpsNum) {
            a(gpsNum);
        }
        if (this.cb.getFlycVersion() >= 6 && !dji.pilot.fpv.d.b.f()) {
            gpsNum = this.cb.getGpsLevel();
            if (this.bm != gpsNum) {
                this.bm = gpsNum;
                this.Q.setImageLevel(gpsNum);
            }
        }
        this.aI.update(this.cb);
        int voltageWarning = this.cb.getVoltageWarning();
        if (this.cb.getFlycVersion() >= 1) {
            a = dji.pilot.publics.e.a.k() && dji.pilot.publics.e.a.j();
            if (this.bl != voltageWarning) {
                this.bl = voltageWarning;
                b(this.bk);
                if (dji.pilot.publics.e.a.j()) {
                    if (this.bl == 1 || this.bl == 2) {
                        this.ae.setTextColor(this.aL.getResources().getColor(R.color.a6));
                    } else {
                        this.ae.setTextColor(this.aL.getResources().getColor(R.color.a3));
                    }
                }
            } else if (this.bo != a) {
                this.bo = a;
                b(this.bk);
            }
        } else if (this.cb.isGetted()) {
            gpsNum = this.cb.getBattery();
            if (!(this.bk == gpsNum && this.bl == voltageWarning)) {
                this.bl = voltageWarning;
                b(gpsNum);
            }
        }
        e();
        int[] a2 = dji.pilot.fpv.d.b.a(flyc_state, isVisionUsed, false);
        int[] a3 = dji.pilot.fpv.d.b.a(flycState, isVisionUsed, false);
        if (this.br == RcModeChannel.CHANNEL_P && a2[0] == R.string.ctrl_mode_pgps && a3[0] == R.string.ctrl_mode_atti) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.aU > OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME) {
                this.aU = currentTimeMillis;
                dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.a;
                bVar.b = R.string.fpv_errorpop_patti_desc;
                dji.thirdparty.a.c.a().e(bVar);
            }
        }
        BatteryType batteryType = this.cb.getBatteryType();
        if (batteryType != this.bw) {
            this.bw = batteryType;
            if (batteryType == BatteryType.NonSmart) {
                this.ae.go();
            } else if (!dji.pilot.battery.a.a.getInstance().a() || dji.pilot.fpv.d.b.n()) {
                this.ae.go();
            } else {
                this.ae.show();
            }
        }
    }

    public void setSmallMap(boolean z) {
        this.cB = z;
        if (z) {
            if (this.bB != SDCardState.OTHER) {
                h();
            }
            l();
            o();
            if (this.cI.canShow()) {
                this.cI.setVisibility(0);
                return;
            }
            return;
        }
        this.ap.go();
        this.aq.go();
        this.aC.go();
        this.cI.setVisibility(8);
        m();
        p();
    }

    public void setVisibleAeLock(boolean z) {
    }

    protected void c(int i) {
        if (!this.cC || !this.cB) {
            this.aq.go();
            this.ap.go();
        } else if (i == 0) {
            this.aq.show();
            this.aC.go();
            this.ap.go();
        } else if (i == 1) {
            this.aq.go();
            this.ap.show();
            this.aC.show();
        } else if (i == 2) {
            this.ap.go();
            this.aq.go();
            this.aC.go();
        }
    }

    protected void h() {
        if (this.bB == SDCardState.None) {
            c(0);
            this.aq.setText(R.string.sdcardstatus_removal);
            this.aq.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_sdcard_no, 0, 0, 0);
        } else if (dji.pilot.fpv.d.b.b(this.bB)) {
            c(1);
        } else if (this.bB == SDCardState.Full) {
            c(0);
            this.aq.setText(R.string.sdcardstatus_full);
            this.aq.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_sdcard_full, 0, 0, 0);
        } else if (this.bB == SDCardState.OTHER) {
            c(2);
        } else {
            c(0);
            this.aq.setText(dji.pilot.fpv.d.b.a(this.bB));
            this.aq.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_sdcard_slow, 0, 0, 0);
        }
    }

    protected SpannableString a(String str, int i, int i2) {
        SpannableString spannableString = new SpannableString(str);
        if (i2 == str.length()) {
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.o2)), i, i2, 17);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.ov)), 0, i, 17);
        } else {
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.o2)), i, i2, 17);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.ov)), i2, str.length(), 17);
        }
        return spannableString;
    }

    protected void i() {
        if (this.ci.isGetted() && dji.pilot.fpv.d.b.c(this.bP)) {
            FuselageFocusMode fuselageFocusMode = this.ci.getFuselageFocusMode();
            if (this.bQ != fuselageFocusMode) {
                this.bQ = fuselageFocusMode;
                if (fuselageFocusMode == FuselageFocusMode.OneAuto || fuselageFocusMode == FuselageFocusMode.ContinuousAuto) {
                    this.aF.setText(a(this.aL.getString(R.string.fpv_camera_amf), 0, 3));
                } else if (fuselageFocusMode == FuselageFocusMode.Manual || fuselageFocusMode == FuselageFocusMode.ManualFine) {
                    this.aF.setText(a(this.aL.getString(R.string.fpv_camera_amf), 2, 5));
                } else {
                    this.aF.setText(this.aL.getString(R.string.fpv_camera_amf));
                }
            }
        }
    }

    protected void j() {
        if (this.cc.isGetted()) {
            boolean z;
            CameraType cameraType = this.cc.getCameraType();
            if (this.bP != cameraType) {
                this.bP = cameraType;
                z = this.cC;
                this.cC = U();
                if (z != this.cC) {
                    h();
                }
                if (dji.pilot.fpv.d.b.e(this.bP)) {
                    this.aE.show();
                    this.aF.show();
                    i();
                } else {
                    this.aE.go();
                    this.aF.go();
                }
                if (dji.pilot.fpv.d.b.h(cameraType)) {
                    this.aB.show();
                } else {
                    this.aB.go();
                }
                int dimensionPixelSize;
                if (dji.pilot.fpv.d.b.j(cameraType)) {
                    this.av.show();
                    this.aw.show();
                    this.as.go();
                    this.ar.go();
                    this.at.go();
                    this.au.go();
                    this.aD.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    dimensionPixelSize = this.aL.getResources().getDimensionPixelSize(R.dimen.rl);
                    this.aD.setAlpha(1.0f);
                    this.aD.setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
                    k();
                    l();
                    o();
                } else {
                    this.av.go();
                    this.as.show();
                    this.ar.show();
                    this.at.show();
                    this.au.show();
                    dimensionPixelSize = this.aL.getResources().getDimensionPixelSize(R.dimen.n3);
                    this.aD.setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
                    this.aD.setCompoundDrawablesWithIntrinsicBounds(R.drawable.selector_ae_lock, 0, 0, 0);
                    this.aD.setText(R.string.fpv_camera_ae);
                    m();
                    p();
                }
            }
            SDCardState sDCardState = this.cc.getSDCardState(true);
            if (!(this.cc.getSDCardInsertState() || sDCardState == SDCardState.USBConnected)) {
                sDCardState = SDCardState.None;
            }
            if (this.bB != sDCardState) {
                this.bB = sDCardState;
                if (sDCardState == SDCardState.Slow) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - this.aV > OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME) {
                        this.aV = currentTimeMillis;
                        dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                        bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.a;
                        bVar.b = R.string.sdcardstatus_slow_tip;
                        dji.thirdparty.a.c.a().e(bVar);
                    }
                }
                h();
            }
            MODE mode = this.cc.getMode();
            if (this.bC != mode) {
                this.bC = mode;
                z = true;
            } else {
                z = false;
            }
            if (mode == MODE.RECORD) {
                RecordType recordState = this.cc.getRecordState();
                int remainedTime = this.cc.getRemainedTime();
                if (z || this.bF != remainedTime) {
                    this.bF = remainedTime;
                    this.ay.setText(e(remainedTime));
                }
                if (this.bD != recordState) {
                    this.bD = recordState;
                    if (recordState == RecordType.STARTING || recordState == RecordType.START) {
                        this.ay.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        this.ax.show();
                        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.3f);
                        alphaAnimation.setDuration(800);
                        alphaAnimation.setRepeatMode(2);
                        alphaAnimation.setRepeatCount(-1);
                        this.ax.startAnimation(alphaAnimation);
                    } else {
                        this.ax.clearAnimation();
                        this.ax.go();
                        this.ay.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_videotime_btn_normal, 0, 0, 0);
                    }
                }
            } else {
                this.bD = RecordType.OTHER;
                this.ax.clearAnimation();
                this.ax.go();
                long remainedShots = this.cc.getRemainedShots();
                if (z || this.bE != remainedShots) {
                    this.ay.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_photonum_btn_normal, 0, 0, 0);
                    this.ay.setText(dji.pilot.fpv.d.b.a(remainedShots));
                }
            }
            int videoFormat;
            if (mode == MODE.RECORD) {
                if (dji.pilot.fpv.d.b.j(cameraType)) {
                    videoFormat = (this.cm.getVideoFormat() * 10) + this.cm.getVideoFps();
                } else {
                    videoFormat = (this.ch.getVideoFormat() * 10) + this.ch.getVideoFps();
                }
                if (z || this.bH != videoFormat) {
                    this.bH = videoFormat;
                    this.az.setText(this.cy[a(this.bH, this.cx)]);
                    return;
                }
                return;
            }
            if (dji.pilot.fpv.d.b.j(cameraType)) {
                videoFormat = this.cm.getImageFormat();
            } else {
                videoFormat = this.ch.getImageFormat();
            }
            if (z || this.bG != videoFormat) {
                this.bG = videoFormat;
                this.az.setText(this.cw[a(this.bG, this.cv)]);
            }
        }
    }

    protected int getTauScale() {
        int zoomScale = this.cm.getZoomScale() / 100;
        if (zoomScale < 2) {
            return 2;
        }
        if (zoomScale < 4) {
            return 4;
        }
        if (zoomScale >= 8) {
            return 1;
        }
        if (this.bP == CameraType.DJICameraTypeTau640) {
            return 8;
        }
        return 1;
    }

    protected void k() {
        int i = 1;
        int zoomScale = this.cm.getZoomScale();
        if (this.bV != zoomScale) {
            this.bV = zoomScale;
            zoomScale /= 100;
            if (zoomScale >= 1) {
                if (4 > zoomScale || zoomScale > 8) {
                    if (zoomScale > 8) {
                        if (this.bP == CameraType.DJICameraTypeTau336) {
                            i = 4;
                        } else {
                            i = 8;
                        }
                    }
                } else if (this.bP == CameraType.DJICameraTypeTau336) {
                    i = 4;
                }
                i = zoomScale;
            }
            this.aD.setText("X " + String.valueOf(i));
        }
    }

    protected void l() {
        if (this.aG != null && this.cC && dji.pilot.fpv.d.b.j(this.bP)) {
            this.aG.show();
        }
    }

    protected void m() {
        if (this.aG != null) {
            this.aG.go();
        }
    }

    protected void n() {
        if (this.aG != null) {
            this.aG.setText(dji.pilot.fpv.d.b.a(this.aL));
        }
    }

    protected void o() {
        if (this.aH != null && this.cC && dji.pilot.fpv.d.b.j(this.bP)) {
            this.aH.show();
        }
    }

    protected void p() {
        if (this.aH != null) {
            this.aH.go();
        }
    }

    protected void q() {
        DJILogHelper.getInstance().LOGD("Tau", "Tau update Info", false, true);
        if (dji.pilot.fpv.d.b.j(this.bP)) {
            int videoFormat;
            if (this.bC == MODE.RECORD) {
                videoFormat = (this.cm.getVideoFormat() * 10) + this.cm.getVideoFps();
                if (this.bH != videoFormat) {
                    this.bH = videoFormat;
                    this.az.setText(this.cy[a(videoFormat, this.cx)]);
                }
            } else {
                videoFormat = this.cm.getImageFormat();
                if (this.bG != videoFormat) {
                    this.bG = videoFormat;
                    this.az.setText(this.cw[a(videoFormat, this.cv)]);
                }
            }
            videoFormat = this.cm.getDigitalFilter();
            boolean isIsothermEnable = this.cm.isIsothermEnable();
            if (!(this.bS == videoFormat && this.bT == isIsothermEnable)) {
                this.bS = videoFormat;
                this.bT = isIsothermEnable;
                this.av.setText(dji.pilot.fpv.camera.more.a.getInstance().aw()[dji.pilot.fpv.camera.more.a.a(dji.pilot.fpv.camera.more.a.getInstance().ax(), videoFormat, 0)]);
            }
            AGCType agc = this.cm.getAGC();
            if (agc != this.bU) {
                this.bU = agc;
                this.aw.setText(dji.pilot.fpv.camera.more.a.getInstance().ay()[dji.pilot.fpv.camera.more.a.a(dji.pilot.fpv.camera.more.a.getInstance().az(), agc.a(), 0)]);
            }
            ThermometricType thermometricType = this.cm.getThermometricType();
            if (this.bW != thermometricType) {
                this.bW = thermometricType;
                if (thermometricType == ThermometricType.c) {
                    this.aG.setSelected(true);
                    this.aG.setCompoundDrawablesWithIntrinsicBounds(R.drawable.top_area_icon, 0, 0, 0);
                } else if (thermometricType == ThermometricType.b) {
                    this.aG.setSelected(true);
                    this.aG.setCompoundDrawablesWithIntrinsicBounds(R.drawable.top_roi_hover, 0, 0, 0);
                } else {
                    this.aG.setSelected(false);
                    this.aG.setCompoundDrawablesWithIntrinsicBounds(R.drawable.top_roi_normal, 0, 0, 0);
                }
            }
            videoFormat = this.cm.getObjectControl();
            if (this.bA != videoFormat) {
                this.bA = videoFormat;
                DJITextView dJITextView = this.aJ;
                if (this.bA == 0) {
                    this.aJ = this.av;
                } else if (this.bA == 1) {
                    this.aJ = this.aD;
                } else {
                    this.aJ = null;
                }
                if (dJITextView != this.aJ) {
                    if (dJITextView != null) {
                        dJITextView.setSelected(false);
                    }
                    if (this.aJ != null) {
                        this.aJ.setSelected(true);
                    }
                }
            }
            k();
        }
    }

    protected void r() {
        boolean z = true;
        if (!dji.pilot.fpv.d.b.j(this.bP)) {
            int videoFormat;
            ExposureMode exposureMode = this.ch.getExposureMode();
            boolean z2 = this.bM != exposureMode;
            if (this.bC == MODE.RECORD) {
                videoFormat = (this.ch.getVideoFormat() * 10) + this.ch.getVideoFps();
                if (this.bH != videoFormat) {
                    this.bH = videoFormat;
                    this.az.setText(this.cy[a(videoFormat, this.cx)]);
                }
            } else {
                videoFormat = this.ch.getImageFormat();
                if (this.bG != videoFormat) {
                    this.bG = videoFormat;
                    this.az.setText(this.cw[a(videoFormat, this.cv)]);
                }
            }
            videoFormat = this.ch.getRealApertureSize();
            if (this.bR != videoFormat) {
                this.bR = videoFormat;
                float f = (((float) videoFormat) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity;
                this.aB.setText(a(this.aL.getString(R.string.fpv_aperture, new Object[]{this.cD.format((double) f)}), 2, false));
            }
            videoFormat = this.ch.getRelISO();
            if (this.bJ != videoFormat || z2) {
                this.bJ = videoFormat;
                String string = this.aL.getString(R.string.fpv_iso, new Object[]{String.valueOf(videoFormat)});
                if (exposureMode == ExposureMode.b) {
                    this.at.setText(a(string, 4, false));
                } else {
                    this.at.setText(a(string, 4, false));
                }
            }
            CharSequence relShutterString = this.ch.getRelShutterString();
            if (relShutterString != null && (!relShutterString.equalsIgnoreCase(this.bK) || z2)) {
                this.bK = relShutterString;
                this.au.setText(relShutterString);
            }
            if (exposureMode == ExposureMode.e) {
                videoFormat = this.ch.getRelExposureCompensation();
            } else {
                videoFormat = this.ch.getExposureCompensation();
            }
            if (this.bI != videoFormat) {
                this.bI = videoFormat;
                this.ar.setText(a(this.aL.getString(R.string.fpv_ev, new Object[]{this.cr[a(videoFormat, this.cq)]}), 3, false));
            }
            boolean isAELock = this.ch.isAELock();
            if (this.bL != isAELock) {
                this.bL = isAELock;
                if (isAELock) {
                    this.aD.setAlpha(1.0f);
                } else {
                    this.aD.setAlpha(l.n);
                }
                this.aD.setSelected(isAELock);
            }
            int digitalFilter = this.ch.getDigitalFilter();
            if (this.bN != digitalFilter) {
                this.bN = digitalFilter;
                if (digitalFilter == 0) {
                    this.aw.go();
                } else {
                    this.aw.show();
                    this.aw.setText(this.ct[a(digitalFilter, this.cu)]);
                    Map hashMap = new HashMap();
                    hashMap.put(dji.pilot.fpv.d.d.dH, "" + digitalFilter);
                    dji.pilot.fpv.d.e.a(s.di, hashMap);
                }
            }
            if (z2) {
                this.bM = exposureMode;
                if (exposureMode == ExposureMode.e) {
                    if (isAELock) {
                        y();
                    }
                    this.aD.setEnabled(false);
                } else {
                    this.aD.setEnabled(true);
                }
            }
            int ctrObjectForOne = this.ch.getCtrObjectForOne();
            if (this.bA != ctrObjectForOne) {
                this.bA = ctrObjectForOne;
                DJITextView dJITextView = this.aJ;
                if (this.bA == 2) {
                    this.aJ = this.ar;
                } else if (this.bA == 0) {
                    this.aJ = this.at;
                } else if (this.bA == 1) {
                    this.aJ = this.au;
                } else if (this.bA == 3) {
                    this.aJ = this.aB;
                } else {
                    this.aJ = null;
                }
                if (dJITextView != this.aJ) {
                    if (dJITextView != null) {
                        dJITextView.setSelected(false);
                    }
                    if (this.aJ != null) {
                        this.aJ.setSelected(true);
                    }
                }
            }
            if (this.ch.getPhotoType() != TYPE.c) {
                z = false;
            }
            if (this.bO != z) {
                this.bO = z;
                if (!z) {
                    this.aA.go();
                } else if (this.cC && dji.pilot.fpv.d.b.b(this.bB)) {
                    this.aA.show();
                } else {
                    this.aA.go();
                }
            }
        }
    }

    protected int a(int i, int[] iArr) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (iArr[i2] == i) {
                return i2;
            }
        }
        return 0;
    }

    protected void s() {
        int i = 0;
        int upSignalQuality = ServiceManager.getInstance().isRemoteOK() ? this.cd.getUpSignalQuality() : 0;
        if (this.bj != upSignalQuality) {
            this.bj = upSignalQuality;
            this.aa.setImageLevel(dji.pilot.fpv.d.b.a(upSignalQuality, 5));
        }
        if (!dji.pilot.fpv.d.b.l(this.cE)) {
            if (dji.pilot.c.d.i == 1) {
                if (ServiceManager.getInstance().isRemoteOK()) {
                    i = this.cd.getDownSignalQuality();
                }
            } else if (ServiceManager.getInstance().isRemoteOK()) {
                i = dji.pilot.fpv.d.b.g(this.cd.getDownSignalQuality());
            }
            if (this.bi != i) {
                this.bi = i;
                this.V.setImageLevel(dji.pilot.fpv.d.b.a(i, 5));
            }
        }
        if (this.cd.isGetted()) {
            this.aI.update(this.cd);
        }
    }

    protected void t() {
        if (dji.pilot.fpv.d.b.l(this.cE)) {
            int signal = this.cl.getSignal();
            if (this.bi != signal) {
                this.bi = signal;
                this.V.setImageLevel(dji.pilot.fpv.d.b.a(signal, 4));
            }
        }
    }

    private void a(int i, int i2, int i3) {
        int i4 = 0;
        int progress = this.al.getProgress();
        this.al.setProgress(i3);
        int i5 = i > i3 ? i3 : i;
        if (this.bu != i5) {
            this.bu = i5;
            this.al.setSecondaryProgress(i5);
            DJISmartBatteryView dJISmartBatteryView = this.al;
            if (i >= i3) {
                i = 0;
            }
            dJISmartBatteryView.setGoHomeBattery(i);
        }
        if (i2 > i3) {
            i2 = i3;
        }
        if (this.bv != i2) {
            this.bv = i2;
            this.al.setThirdProgress(this.bv);
        }
        i5 = this.ce.getLowWarning();
        if (!(i5 == this.by && progress == i3)) {
            this.by = i5;
            DJISmartBatteryView dJISmartBatteryView2 = this.al;
            if (i5 > i3) {
                i5 = 0;
            }
            dJISmartBatteryView2.setLowWarning(i5);
        }
        i5 = this.ce.getSeriousLowWarning();
        if (this.bz != i5 || progress != i3) {
            this.bz = i5;
            DJISmartBatteryView dJISmartBatteryView3 = this.al;
            if (i5 <= i3) {
                i4 = i5;
            }
            dJISmartBatteryView3.setSeriousWarning(i4);
        }
    }

    protected void u() {
        if (this.bt == -1) {
            this.am.hide();
        } else {
            this.am.show();
        }
    }

    private void a(int i, int i2) {
        int i3;
        int i4;
        int i5 = 0;
        if (this.cb.getBatteryType() == BatteryType.NonSmart) {
            if (this.bt != i2) {
                this.bt = i2;
                this.am.setText(this.aL.getString(R.string.fpv_percent, new Object[]{Integer.valueOf(i2)}));
            }
        } else if (this.bt != i) {
            this.bt = i;
            if (this.bt == 0) {
                this.am.setText(I);
            } else {
                this.am.setText(d(i));
            }
        }
        u();
        if (G == 0) {
            G = ((int) (this.am.getPaint().measureText(F) + dji.pilot.visual.a.d.c)) + com.dji.frame.c.e.b(this.aL, 6.0f);
            H = com.dji.frame.c.e.b(this.aL, 1.0f);
        }
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.am.getLayoutParams();
        int i6 = DJIBaseActivity.screenWidth;
        if (i == Integer.MAX_VALUE) {
            i3 = (i2 * i6) / 100;
            i4 = G / 2;
        } else {
            i3 = (i2 * i6) / 100;
            i4 = G / 2;
        }
        if (i3 > i6 - i4) {
            i5 = (i6 - G) - H;
        } else if (i3 > i4) {
            i5 = i3 - i4;
        }
        if (i5 != marginLayoutParams.leftMargin || marginLayoutParams.width != G) {
            marginLayoutParams.leftMargin = i5;
            marginLayoutParams.width = G;
            this.am.setLayoutParams(marginLayoutParams);
        }
    }

    protected void v() {
        boolean z;
        int goHomeBattery = this.ce.getGoHomeBattery();
        int landBattery = this.ce.getLandBattery();
        if (this.cb.getFlycVersion() >= 1) {
            int relativeCapacityPercentage;
            if (dji.pilot.fpv.d.b.p() && DataSmartBatteryGetPushDynamicData.getInstance().isGetted() && DataSmartBatteryGetPushDynamicData.getInstance().getIndex() == 0) {
                relativeCapacityPercentage = DataSmartBatteryGetPushDynamicData.getInstance().getRelativeCapacityPercentage();
                if (this.bk != relativeCapacityPercentage) {
                    b(relativeCapacityPercentage);
                }
            } else if (this.ce.isGetted()) {
                if (this.cb.getBatteryType() == BatteryType.NonSmart) {
                    relativeCapacityPercentage = this.ce.getVoltagePercent();
                } else {
                    relativeCapacityPercentage = this.ce.getBattery();
                }
                if (this.bk != relativeCapacityPercentage) {
                    b(relativeCapacityPercentage);
                }
            }
        }
        a(goHomeBattery, landBattery, this.bk);
        if (!this.cf.isGetted() || this.bx == ConnStatus.NORMAL) {
            a(this.ce.getUsefulTime(), this.bk);
        } else {
            a(Integer.MAX_VALUE, 0);
        }
        if (this.bw == BatteryType.NonSmart) {
            x();
        }
        if ((this.ce.getStatus() & 4096) == 4096) {
            z = true;
        } else {
            z = false;
        }
        if (this.ce.isGetted() && z && !this.cK && DataOsdGetPushCommon.getInstance().groundOrSky() == 2) {
            dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
            bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.b;
            bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
            bVar.b = R.string.battery_limit_flight_movement;
            dji.thirdparty.a.c.a().e(bVar);
        }
        this.cK = z;
    }

    protected void w() {
        if (!dji.pilot.fpv.d.b.n()) {
            if (System.currentTimeMillis() - this.cG > 2000) {
                ConnStatus connStatus = this.cf.getConnStatus();
                if (dji.pilot.publics.e.a.b()) {
                    DataSmartBatteryGetPushDynamicData instance = DataSmartBatteryGetPushDynamicData.getInstance();
                    connStatus = instance.isGetted() ? ConnStatus.ofData((int) instance.getStatus()) : ConnStatus.EXCEPTION;
                }
                if (this.bx != connStatus) {
                    this.bx = connStatus;
                    if (this.bx == ConnStatus.NORMAL) {
                        int voltageWarning = this.cb.getVoltageWarning();
                        if ((this.bk != -1 && this.bk <= this.bu) || voltageWarning == 1 || voltageWarning == 2) {
                            this.ac.setImageResource(R.drawable.osd_electric_low);
                        } else {
                            this.ac.setImageResource(R.drawable.osd_electric_btn_normal);
                        }
                        if (dji.pilot.fpv.d.b.f()) {
                            this.al.setVisibility(4);
                        } else {
                            this.al.setVisibility(0);
                            a(this.ce.getUsefulTime(), this.bk);
                        }
                    } else {
                        this.ac.setImageResource(R.drawable.osd_electric_warning);
                        this.al.setVisibility(4);
                        a(Integer.MAX_VALUE, 0);
                        dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                        bVar.b = R.string.fpv_errorpop_battery_error;
                        dji.thirdparty.a.c.a().e(bVar);
                    }
                }
            }
            if (this.bw != BatteryType.NonSmart) {
                x();
            }
        }
    }

    protected void x() {
        int voltage;
        if (dji.pilot.fpv.d.b.p()) {
            if (DataSmartBatteryGetPushDynamicData.getInstance().getIndex() == 0) {
                voltage = DataSmartBatteryGetPushDynamicData.getInstance().getVoltage();
            } else {
                voltage = 0;
            }
        } else if (this.bw == BatteryType.NonSmart) {
            voltage = this.ce.getVoltage();
        } else if (this.bw == BatteryType.NonSmart) {
            voltage = this.ce.getVoltage();
        } else {
            int[] partVoltages = this.cf.getPartVoltages();
            int C = dji.pilot.battery.a.a.C();
            int i = 0;
            voltage = 0;
            while (i < partVoltages.length && i < C) {
                if (i == 0) {
                    voltage = partVoltages[i];
                } else if (voltage > partVoltages[i]) {
                    voltage = partVoltages[i];
                }
                i++;
            }
        }
        if (dji.pilot.publics.e.a.k() && dji.pilot.publics.e.a.j()) {
            this.ae.setText(R.string.fpv_default_str);
            this.ae.setTextColor(this.aL.getResources().getColor(R.color.a6));
        } else if (this.bn != voltage) {
            this.bn = voltage;
            voltage = dji.pilot.battery.a.a.c((((float) voltage) * 1.0f) / 1000.0f);
            this.ae.setText(this.aL.getString(R.string.battery_voltage_unit, new Object[]{Float.valueOf(r0)}));
            if (dji.pilot.publics.e.a.j()) {
                if (this.bl == 1 || this.bl == 2) {
                    this.ae.setTextColor(this.aL.getResources().getColor(R.color.a6));
                } else {
                    this.ae.setTextColor(this.aL.getResources().getColor(R.color.a3));
                }
            } else if (voltage == 2) {
                this.ae.setTextColor(this.aL.getResources().getColor(R.color.a6));
            } else if (voltage == 1) {
                this.ae.setTextColor(this.aL.getResources().getColor(R.color.a7));
            } else {
                this.ae.setTextColor(this.aL.getResources().getColor(R.color.a3));
            }
        }
    }

    protected void y() {
        DataCameraSetAELock.getInstance().a(!this.bL).start(null);
    }

    protected void z() {
        if ((this.bQ == FuselageFocusMode.OneAuto || this.bQ == FuselageFocusMode.ContinuousAuto) && dji.pilot.fpv.camera.more.a.getInstance().g()) {
            dji.pilot.fpv.camera.more.a.getInstance().b();
        } else if (this.bQ == FuselageFocusMode.Manual || FuselageFocusMode.ManualFine == this.bQ) {
            new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.A).a(FuselageFocusMode.OneAuto.value()).start(null);
        } else {
            new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.A).a(FuselageFocusMode.Manual.value()).start(null);
        }
    }

    protected void A() {
        int i = 0;
        if (!isInEditMode()) {
            int i2;
            this.cC = U();
            Resources resources = getContext().getResources();
            this.co = resources.getStringArray(R.array.ac);
            this.cn = resources.getIntArray(R.array.ad);
            this.cp = resources.getStringArray(R.array.b7);
            this.cr = resources.getStringArray(R.array.a0);
            this.cq = resources.getIntArray(R.array.a1);
            this.cs = resources.getStringArray(R.array.b_);
            if (i.getInstance().b() == CameraType.DJICameraTypeFC220S) {
                this.cu = resources.getIntArray(R.array.t);
                this.ct = resources.getStringArray(R.array.q);
            } else {
                this.cu = resources.getIntArray(R.array.s);
                this.ct = resources.getStringArray(R.array.p);
            }
            this.cv = resources.getIntArray(R.array.an);
            this.cw = resources.getStringArray(R.array.ai);
            int[] intArray = resources.getIntArray(R.array.bx);
            int[] intArray2 = resources.getIntArray(R.array.bu);
            int[] iArr = new int[(intArray.length + intArray2.length)];
            int length = intArray.length;
            for (i2 = 0; i2 < length; i2++) {
                iArr[i2] = intArray[i2];
            }
            length = intArray2.length;
            for (i2 = 0; i2 < length; i2++) {
                iArr[intArray.length + i2] = intArray2[i2];
            }
            this.cx = iArr;
            String[] stringArray = resources.getStringArray(R.array.bv);
            String[] stringArray2 = resources.getStringArray(R.array.bs);
            String[] strArr = new String[(stringArray.length + stringArray2.length)];
            int length2 = intArray.length;
            for (i2 = 0; i2 < length2; i2++) {
                strArr[i2] = stringArray[i2];
            }
            i2 = intArray2.length;
            while (i < i2) {
                strArr[intArray.length + i] = stringArray2[i];
                i++;
            }
            this.cy = strArr;
            this.cj = DataOsdGetPushHome.getInstance();
            this.cb = DataOsdGetPushCommon.getInstance();
            this.cc = DataCameraGetPushStateInfo.getInstance();
            this.ch = DataCameraGetPushShotParams.getInstance();
            this.ci = DataCameraGetPushShotInfo.getInstance();
            this.cd = DataOsdGetPushSignalQuality.getInstance();
            this.ce = DataFlycGetPushSmartBattery.getInstance();
            this.cf = DataCenterGetPushBatteryCommon.getInstance();
            this.cg = DataRcGetPushBatteryInfo.getInstance();
            this.ck = DataFlycGetPushDeformStatus.getInstance();
            this.cl = DataWifiGetPushSignal.getInstance();
            this.cm = DataCameraGetPushTauParam.getInstance();
            this.aN = new OnDismissListener(this) {
                final /* synthetic */ DJIFpvTopBaseView a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    if (this.a.aP != null) {
                        this.a.aP.setSelected(false);
                        this.a.aP = null;
                    }
                    this.a.bc = null;
                    if (this.a.aM != null) {
                        this.a.aM.b(0, false);
                    }
                }
            };
            this.aO = new OnShowListener(this) {
                final /* synthetic */ DJIFpvTopBaseView a;

                {
                    this.a = r1;
                }

                public void onShow(DialogInterface dialogInterface) {
                    this.a.bc = (Dialog) dialogInterface;
                    if (this.a.aM != null) {
                        this.a.aM.a(0, false);
                    }
                }
            };
            this.aS = new OnClickListener(this) {
                final /* synthetic */ DJIFpvTopBaseView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    boolean z = true;
                    int id = view.getId();
                    if (id == R.id.acz) {
                        if (dji.pilot.fpv.d.b.j(this.a.bP)) {
                            DataCameraSetFocusParam.getInstance().d(true).b(ZoomMode.b).d((float) this.a.getTauScale()).start(null);
                            return;
                        }
                        dji.pilot.fpv.d.e.a("FPV_RightBarView_CameraControllView_Button_AELock");
                        dji.pilot.fpv.d.e.c(s.do);
                        this.a.y();
                    } else if (id == R.id.ad1) {
                        this.a.z();
                    } else if (id == R.id.a89) {
                        Intent intent;
                        if (dji.pilot.liveshare.b.getInstance().getStreamMode() == 0) {
                            intent = new Intent();
                            intent.setClass(this.a.aL, youtubeLiveActivity.class);
                            this.a.aL.startActivity(intent);
                        } else if (dji.pilot.liveshare.b.getInstance().getStreamMode() == 1) {
                            intent = new Intent();
                            intent.setClass(this.a.aL, CustomModeActivity.class);
                            this.a.aL.startActivity(intent);
                        } else if (dji.pilot.liveshare.b.getInstance().getStreamMode() == 2) {
                            intent = new Intent();
                            intent.setClass(this.a.aL, DJILiveShareActivity.class);
                            intent.putExtra("type", dji.pilot.f.a.a.D);
                            this.a.aL.startActivity(intent);
                        }
                    } else if (id == R.id.ad8) {
                        ThermometricType thermometricType = this.a.cm.getThermometricType();
                        if (!this.a.cm.supportSpotThermometric() || this.a.cc.getVerstion() < 3) {
                            z = false;
                        }
                        ThermometricType thermometricType2 = ThermometricType.a == thermometricType ? ThermometricType.b : ThermometricType.a;
                        if (ThermometricType.b == thermometricType && r0) {
                            thermometricType2 = ThermometricType.c;
                        }
                        new DataCameraTauParamThermometricEnable().a(thermometricType2).start(null);
                    } else if (id == R.id.ad7) {
                        dji.thirdparty.a.c.a().e(dji.pilot.fpv.camera.more.c.a.SCREEN_SHOT);
                    }
                }
            };
            this.aQ = new d(this);
        }
    }

    protected void B() {
        this.K = (DJILinearLayout) findViewById(R.id.abm);
        this.L = (DJIImageView) findViewById(R.id.abn);
        this.M = (DJILinearLayout) findViewById(R.id.abp);
        this.N = (DJITextView) findViewById(R.id.abr);
        this.O = (DJIRelativeLayout) findViewById(R.id.abt);
        this.P = (DJIImageView) findViewById(R.id.abu);
        this.Q = (DJIImageView) findViewById(R.id.abv);
        this.R = (DJITextView) findViewById(R.id.abw);
        this.S = (DJITextView) findViewById(R.id.abx);
        this.T = (DJILinearLayout) findViewById(R.id.aca);
        this.U = (DJIImageView) findViewById(R.id.acb);
        this.V = (DJIImageView) findViewById(R.id.acc);
        this.W = (DJILinearLayout) findViewById(R.id.ac8);
        this.aa = (DJIImageView) findViewById(R.id.ac_);
        this.ab = (DJILinearLayout) findViewById(R.id.acd);
        this.ac = (DJIImageView) findViewById(R.id.ace);
        this.ad = (DJITextView) findViewById(R.id.acf);
        this.ae = (DJITextView) findViewById(R.id.acg);
        this.af = (DJILinearLayout) findViewById(R.id.abz);
        this.ag = (DJIImageView) findViewById(R.id.ac0);
        this.ah = (DJITextView) findViewById(R.id.ac1);
        this.ai = (DJIImageView) findViewById(R.id.ach);
        this.ai.setOnClickListener(this);
        this.aj = (DJILinearLayout) findViewById(R.id.ac2);
        this.ak = (DJIImageView) findViewById(R.id.ac3);
        this.aj.setOnClickListener(this);
        this.L.setOnClickListener(this);
        this.M.setOnClickListener(this);
        this.T.setOnClickListener(this);
        this.W.setOnClickListener(this);
        this.ab.setOnClickListener(this);
        this.af.setOnClickListener(this);
        this.O.setOnClickListener(this);
        if (!dji.pilot.battery.a.a.getInstance().a() || dji.pilot.fpv.d.b.n()) {
            this.ae.go();
        } else {
            this.ae.show();
        }
    }

    protected void C() {
        this.an = findViewById(R.id.ack);
        this.ao = (DJIImageView) findViewById(R.id.ad5);
        this.al = (DJISmartBatteryView) findViewById(R.id.aci);
        this.am = (DJITextView) findViewById(R.id.acj);
        this.ap = (DJILinearLayout) findViewById(R.id.acm);
        this.aq = (DJITextView) findViewById(R.id.acl);
        this.ar = (DJITextView) findViewById(R.id.acq);
        this.as = (DJIImageView) findViewById(R.id.acr);
        this.at = (DJITextView) findViewById(R.id.acn);
        this.au = (DJITextView) findViewById(R.id.aco);
        this.av = (DJITextView) findViewById(R.id.acv);
        this.aw = (DJITextView) findViewById(R.id.acw);
        this.aB = (DJITextView) findViewById(R.id.acp);
        this.ax = (DJIImageView) findViewById(R.id.acs);
        this.ay = (DJITextView) findViewById(R.id.act);
        this.az = (DJITextView) findViewById(R.id.acu);
        this.aA = (DJIImageView) findViewById(R.id.acx);
        this.aC = (DJILinearLayout) findViewById(R.id.acy);
        this.aD = (DJITextView) findViewById(R.id.acz);
        this.aE = (DJIImageView) findViewById(R.id.ad0);
        this.aF = (DJITextView) findViewById(R.id.ad1);
        this.aG = (DJITextView) findViewById(R.id.ad8);
        this.aH = (DJITextView) findViewById(R.id.ad7);
        this.cI = (DJISwitchDefogView) findViewById(R.id.ad9);
        this.aK = (LiveShareFpvTopView) findViewById(R.id.a89);
        this.aF.setOnClickListener(this.aS);
        if (this.aH != null) {
            this.aH.setOnClickListener(this.aS);
        }
        this.at.setOnClickListener(this.aS);
        this.aD.setOnClickListener(this.aS);
        this.aK.setOnClickListener(this.aS);
        if (this.aG != null) {
            this.aG.setOnClickListener(this.aS);
            n();
        }
        this.an.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ DJIFpvTopBaseView a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    protected void D() {
        this.V.setImageLevel(0);
        this.aa.setImageLevel(0);
        a();
    }

    protected void E() {
        this.N.setText(R.string.fpv_default_str);
        this.P.setEnabled(false);
        this.Q.setImageLevel(0);
        this.R.go();
        this.ac.setImageResource(R.drawable.osd_electric_btn_normal);
        this.ad.setTextColor(this.aL.getResources().getColor(R.color.om));
        this.ad.setText(R.string.fpv_default_str);
        this.ae.setTextColor(getResources().getColor(R.color.om));
        this.ae.setText(R.string.fpv_default_str);
        this.ah.setText(R.string.fpv_default_str);
        this.ak.setImageResource(R.drawable.fpv_topbar_vision_close_icon);
        d();
    }

    protected void F() {
        this.al.setMax(100);
        this.al.setProgress(0);
        this.al.setSecondaryProgress(0);
        this.al.setThirdProgress(0);
        this.al.setLowWarning(0);
        this.al.setSeriousWarning(0);
        this.al.setGoHomeBattery(0);
        this.am.hide();
        this.am.setText(R.string.fpv_default_str);
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.am.getLayoutParams();
        int width = this.am.getWidth() / 2;
        if (width != marginLayoutParams.leftMargin) {
            marginLayoutParams.leftMargin = width;
            this.am.setLayoutParams(marginLayoutParams);
        }
        if (this.aJ != null) {
            this.aJ.setSelected(false);
            this.aJ = null;
        }
        this.ax.clearAnimation();
        h();
        m();
        p();
    }

    protected void G() {
        H();
        I();
    }

    protected void H() {
        this.be = FLYC_STATE.OTHER;
        this.bh = -1;
        this.bm = 0;
        this.bk = -1;
        this.bl = -1;
        this.bq = false;
        this.bp = DataFlycGetIoc.MODE.OTHER;
        this.br = RcModeChannel.CHANNEL_UNKNOWN;
        this.bn = -1;
        this.bt = -1;
        this.bu = 0;
        this.bv = 0;
        this.bw = BatteryType.Unknown;
        this.bx = ConnStatus.NORMAL;
        this.by = 0;
        this.bz = 0;
        this.bB = SDCardState.OTHER;
        this.bI = -1;
        this.bJ = -1;
        this.bK = null;
        this.bA = 255;
        this.bM = ExposureMode.i;
        this.bN = 0;
        this.bD = RecordType.OTHER;
        this.bE = -1;
        this.bF = -1;
        this.bG = -1;
        this.bH = -1;
        this.bO = false;
        this.bR = -1;
        this.bQ = FuselageFocusMode.OTHER;
        this.bP = CameraType.OTHER;
        this.bS = -1;
        this.bU = AGCType.j;
        this.bV = -1;
        this.bW = ThermometricType.d;
    }

    protected void I() {
        this.bi = 0;
        this.bj = 0;
        this.bZ = 3;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            setMotionEventSplittingEnabled(false);
            B();
            C();
            this.cJ = (DJILinearLayout) findViewById(R.id.ac4);
            this.aI = (DJIFpvTipView) findViewById(R.id.ac5);
            this.aI.setOnClickListener(this);
            G();
            D();
            E();
            F();
            this.aI.resetStatus(true);
            this.aI.update(dji.pilot.publics.c.c.getInstance().a());
            dji.pilot.battery.a.d.getInstance();
        }
    }

    public void toggleTipView(boolean z) {
    }

    protected String d(int i) {
        int[] e = dji.pilot.fpv.d.b.e(i);
        return String.format(Locale.US, "%1$02d:%2$02d", new Object[]{Integer.valueOf(e[1]), Integer.valueOf(e[0])});
    }

    protected String e(int i) {
        int[] f = dji.pilot.fpv.d.b.f(i);
        return String.format(Locale.US, "%1$02d:%2$02d:%3$02d", new Object[]{Integer.valueOf(f[2]), Integer.valueOf(f[1]), Integer.valueOf(f[0])});
    }

    protected SpannableString b(String str, int i, int i2) {
        SpannableString spannableString = new SpannableString(str);
        if (i2 == 0) {
            spannableString.setSpan(new dji.pilot.publics.d.a.a(DJITextView.NBOLD, 0, this.aL.getResources().getDimensionPixelSize(R.dimen.rp)), i, str.length(), 17);
        } else if (1 == i2) {
            spannableString.setSpan(new dji.pilot.publics.d.a.a(DJITextView.NBOLD, 0, this.aL.getResources().getDimensionPixelSize(R.dimen.ro)), 0, str.length() - i, 17);
        }
        return spannableString;
    }

    protected SpannableString a(String str, int i, boolean z) {
        SpannableString spannableString = new SpannableString(str);
        int dimensionPixelSize = this.aL.getResources().getDimensionPixelSize(R.dimen.rp);
        int dimensionPixelSize2 = this.aL.getResources().getDimensionPixelSize(R.dimen.rn);
        spannableString.setSpan(new dji.pilot.publics.d.a.a(DJITextView.NBOLD, 0, dimensionPixelSize), i, str.length(), 17);
        spannableString.setSpan(new dji.pilot.publics.d.a.b(DJITextView.NLIGHT, 2, dimensionPixelSize2, -1711276033), 0, i, 17);
        return spannableString;
    }

    protected SpannableString a(String str, boolean z) {
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new dji.pilot.publics.d.a.a(DJITextView.NBOLD, 0, this.aL.getResources().getDimensionPixelSize(R.dimen.rp)), str.length() - 6, str.length(), 17);
        return spannableString;
    }

    private void X() {
        if (!(this.bc == null || this.bc == getSettingDlg())) {
            this.bc.dismiss();
        }
        getSettingDlg().e();
    }

    private void g(int i) {
        if (!(this.bc == null || this.bc == getSettingDlg())) {
            this.bc.dismiss();
        }
        getSettingDlg().a(i);
    }

    public void handleBatteryClickPush() {
        if (getSettingDlg().isShowing()) {
            hideDialog();
        } else {
            J();
        }
    }

    protected void J() {
        g(3);
    }

    protected void K() {
        g(0);
    }

    protected void L() {
        g(8);
    }

    protected void M() {
        g(7);
    }

    protected void N() {
        g(6);
    }

    protected void O() {
        if (dji.pilot.fpv.d.b.l(this.cE)) {
            g(5);
        } else {
            g(2);
        }
    }

    protected void P() {
        g(1);
    }

    protected void Q() {
        X();
    }

    protected void R() {
        hideDialog();
    }

    protected void S() {
    }

    private void Y() {
        if (this.aY == null) {
            this.aY = new g(getContext());
            this.aY.setOnDismissListener(this.aN);
            this.aY.setOnShowListener(this.aO);
        }
        if (this.aY != null && !this.aY.isShowing()) {
            this.bc = this.aY;
            this.aY.show();
        }
    }

    private void Z() {
        if (this.aY != null && this.aY.isShowing()) {
            this.aY.dismiss();
        }
    }

    public void showCheckListDlg() {
        hideDialog();
        if (this.aW == null) {
            this.aW = new dji.pilot.fpv.activity.d(getContext());
            this.aW.setOnDismissListener(this.aN);
            this.aW.setOnShowListener(this.aO);
        }
        if (this.aW != null && !this.aW.isShowing()) {
            this.bc = this.aW;
            this.aW.show();
        }
    }

    public void hideCheckListDlg() {
        if (this.aW != null && this.aW.isShowing()) {
            this.aW.dismiss();
        }
    }

    public void showGuidanceTipDlg() {
        if (!(this.bc == null || this.bc == getGuandianDlg())) {
            this.bc.dismiss();
        }
        if (!getGuandianDlg().isShowing()) {
            getGuandianDlg().show();
        }
    }

    protected void a(int i, boolean z) {
        if (this.bb && z) {
            this.aR |= x;
            if (!this.aQ.hasMessages(4096)) {
                this.aQ.sendEmptyMessageDelayed(4096, E);
            }
            this.bb = false;
        } else if ((this.aR & i) == 0) {
            this.aR |= i;
            if (!this.aQ.hasMessages(4096)) {
                this.aQ.sendEmptyMessageDelayed(4096, E);
            }
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        a(1, true);
        if ((dataOsdGetPushCommon.getFlycState() != FLYC_STATE.AssitedTakeoff && dataOsdGetPushCommon.getFlycState() != FLYC_STATE.AutoTakeoff) || !DataCenterGetPushBatteryCommon.getInstance().isGetted()) {
            this.cL = false;
        } else if (((float) (DataCenterGetPushBatteryCommon.getInstance().getTemperature() / 10)) - dji.pilot.publics.e.e.f < DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMaxVelocity && !this.cL) {
            this.cL = true;
            dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
            bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.b;
            bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
            bVar.b = R.string.takeoff_tips_low_battery_temp;
            dji.thirdparty.a.c.a().e(bVar);
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        a(2, true);
    }

    public void onEventBackgroundThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        a(64, true);
    }

    public void onEventBackgroundThread(DataCameraGetPushTauParam dataCameraGetPushTauParam) {
        a(2048, true);
    }

    public void onEventBackgroundThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        a(1024, true);
    }

    public void onEventBackgroundThread(DataOsdGetPushSignalQuality dataOsdGetPushSignalQuality) {
        a(4, false);
    }

    public void onEventBackgroundThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        a(8, true);
    }

    public void onEventBackgroundThread(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
        a(16, true);
    }

    public void onEventBackgroundThread(DataSmartBatteryGetPushDynamicData dataSmartBatteryGetPushDynamicData) {
        a(16, true);
    }

    public void onEventBackgroundThread(DataRcGetPushBatteryInfo dataRcGetPushBatteryInfo) {
        a(32, false);
    }

    public void onEventBackgroundThread(DataOsdGetPushHome dataOsdGetPushHome) {
        a(128, true);
        a(1, true);
    }

    public void onEventBackgroundThread(DataWifiGetPushSignal dataWifiGetPushSignal) {
        a(512, false);
    }

    public void onEventBackgroundThread(p pVar) {
        this.bb = true;
        if (pVar == p.b) {
            this.aR = 0;
            this.aQ.removeMessages(4096);
            this.aQ.sendEmptyMessage(4097);
        } else if (pVar == p.a) {
            this.aR = 0;
            this.aQ.removeMessages(4096);
            this.aQ.sendEmptyMessage(4098);
        }
    }

    public void onEventBackgroundThread(o oVar) {
        this.bb = true;
        if (oVar == o.b) {
            this.cG = System.currentTimeMillis();
            this.aR |= 1;
            this.aR |= 2;
            this.aR |= 8;
            this.aR |= 64;
            this.aQ.removeMessages(4099);
            this.aQ.sendEmptyMessage(4099);
        } else if (oVar == o.a) {
            this.aQ.removeMessages(4100);
            this.aQ.sendEmptyMessage(4100);
        }
    }

    public void onEventMainThread(DJIGenSettingDataManager$b dJIGenSettingDataManager$b) {
        if (dJIGenSettingDataManager$b == DJIGenSettingDataManager$b.TEMPERATURE_UNIT_CHANGED) {
            n();
        }
    }

    public void onEventMainThread(c$a dji_pilot_publics_c_c_a) {
        this.aI.update(dji_pilot_publics_c_c_a);
    }

    public void onEventMainThread(m mVar) {
        this.aI.update(mVar);
        if (m.b == mVar) {
            this.bd = true;
        } else {
            this.bd = false;
        }
    }

    public void onEventMainThread(DataOsdGetPushChannalStatus dataOsdGetPushChannalStatus) {
        this.aI.update(dataOsdGetPushChannalStatus);
    }

    public void onEventMainThread(DataWifiGetPushElecSignal dataWifiGetPushElecSignal) {
        this.aI.update(dataWifiGetPushElecSignal);
    }

    public void onEventMainThread(dji.setting.ui.flyc.SdModeView.a aVar) {
        this.aI.update(aVar);
    }

    public void onEventMainThread(DataFlycGetPushCheckStatus dataFlycGetPushCheckStatus) {
        this.aI.update(dataFlycGetPushCheckStatus);
    }

    public void onEventBackgroundThread(dji.setting.ui.rc.RcMasterSlaveView.c cVar) {
        if (cVar != null) {
            this.aQ.sendEmptyMessage(8192);
        }
    }

    public void onEventMainThread(a$d dji_pilot_publics_control_a_d) {
        switch (dji_pilot_publics_control_a_d) {
            case YES:
            case NoMatch:
            case YES_G:
            case NoMatch_G:
                this.aI.needUptate(true, dji.pilot.publics.control.a.getInstance().l());
                return;
            default:
                this.aI.needUptate(false, dji.pilot.publics.control.a.getInstance().l());
                return;
        }
    }

    public void onEventBackgroundThread(DataFlycGetPushDeformStatus dataFlycGetPushDeformStatus) {
        a(256, false);
    }

    public void onEventMainThread(c cVar) {
        if (cVar == c.BATTERY) {
            J();
        } else if (cVar == c.MC_SHOWBY_GUIDANCE) {
            L();
        }
    }

    public void onEventMainThread(n$b dji_pilot_fpv_model_n_b) {
        if (dji_pilot_fpv_model_n_b == n$b.SHOW_GUIDANCE_USER) {
            showGuidanceTipDlg();
        }
    }

    public void onEventMainThread(a$e dji_pilot_battery_a_a_e) {
        if (dji_pilot_battery_a_a_e != a$e.SHOW) {
            this.ae.go();
        } else if (this.bw != BatteryType.NonSmart) {
            this.ae.show();
        }
    }

    private void a(ProductType productType, boolean z) {
        if (this.cE != productType || z) {
            this.cE = productType;
            if (dji.pilot.fpv.d.b.l(productType)) {
                this.U.go();
                this.V.setImageResource(R.drawable.topbar_wifi_level);
            } else {
                this.U.show();
                this.V.setImageResource(R.drawable.topbar_signal_level);
            }
            CameraType cameraType = CameraType.OTHER;
            if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
                cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
            }
            if (cameraType == CameraType.DJICameraTypeFC550Raw) {
                this.an.setVisibility(8);
            } else {
                this.an.setVisibility(0);
            }
            onEventMainThread(DataRTKPushStatus.getInstance());
            if (dji.pilot.fpv.d.b.n(productType)) {
                this.aj.show();
                onEventMainThread(dji.pilot.visual.a.c.getInstance().c(SensorType.Front));
                d();
            } else if (dji.pilot.fpv.d.b.j(this.cE)) {
                if (DataFlycGetPushAvoid.getInstance().isGetted()) {
                    onEventMainThread(DataFlycGetPushAvoid.getInstance());
                } else {
                    this.aj.hide();
                    this.ak.setImageResource(0);
                }
                d();
            } else {
                this.aj.go();
                c();
            }
            if (this.cE == ProductType.None) {
                dji.pilot.battery.a.d.getInstance().b();
            } else if (this.cE == ProductType.A3 || productType == ProductType.N3) {
                dji.pilot.battery.a.d.getInstance().a();
            }
        }
        if (i.getInstance().b() == CameraType.DJICameraTypeFC220S) {
            this.cu = this.aL.getResources().getIntArray(R.array.t);
            this.ct = this.aL.getResources().getStringArray(R.array.q);
            return;
        }
        this.cu = this.aL.getResources().getIntArray(R.array.s);
        this.ct = this.aL.getResources().getStringArray(R.array.p);
    }

    public void onEventMainThread(dji.midware.data.manager.P3.i.a aVar) {
        if (dji.pilot.fpv.d.b.l(i.getInstance().c())) {
            this.U.go();
            this.V.setImageResource(R.drawable.wifi_level);
            return;
        }
        this.U.show();
        this.V.setImageResource(R.drawable.singal_level);
    }

    public void onEventMainThread(ProductType productType) {
        a(productType, false);
    }

    public void onEventMainThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        this.aI.update(dataGimbalGetPushParams);
    }

    public void onEventMainThread(Data2100GetPushCheckStatus data2100GetPushCheckStatus) {
        this.aI.update(data2100GetPushCheckStatus);
    }

    public void onEventMainThread(dji.pilot.fpv.control.j.a aVar) {
        b();
    }

    public void onEventMainThread(CustomModeActivity customModeActivity) {
        T();
    }

    public void onEventMainThread(youtubeLiveActivity dji_pilot_liveshare_Youtube_youtubeLiveActivity) {
        T();
    }

    public void onEventMainThread(YoutubeChangeEvent youtubeChangeEvent) {
        T();
    }

    public void onEventMainThread(dji.pilot.f.a.a aVar) {
        if (aVar.I == 8 || aVar.I == 16 || aVar.I == 5) {
            T();
        }
    }

    protected void T() {
        if (dji.pilot.liveshare.b.getInstance().isLaunch()) {
            R();
            this.aK.setVisibility(0);
        } else {
            this.aK.setVisibility(8);
        }
        this.aK.handleEvent();
    }

    protected boolean f(int i) {
        return false;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (!f(id)) {
            Object obj = 1;
            if (R.id.abn == id) {
                if (this.aM != null) {
                    dji.pilot.fpv.d.e.a("FPV_OsdTopBarView_Button_BackHome");
                    this.aM.a();
                }
            } else if (R.id.abp == id) {
                dji.pilot.fpv.d.e.a("FPV_MCSettingsView");
                K();
            } else if (R.id.aca == id) {
                dji.pilot.fpv.d.e.a("FPV_ImageTransmissionSettings");
                O();
            } else if (R.id.ac8 == id) {
                dji.pilot.fpv.d.e.a("FPV_RCSettings");
                P();
            } else if (R.id.acd == id) {
                dji.pilot.fpv.d.e.a("FPV_AircraftBattery");
                J();
            } else if (R.id.ach == id) {
                dji.pilot.fpv.d.e.a("FPV_GeneralSettings");
                N();
            } else if (R.id.ac5 == id) {
                dji.pilot.fpv.d.e.a("FPV_AircraftState");
                showCheckListDlg();
            } else if (R.id.abt == id) {
                if (dji.pilot.fpv.d.b.o()) {
                    new h(this.aL).show();
                    obj = null;
                } else {
                    obj = null;
                }
            } else if (R.id.abz == id) {
                dji.pilot.fpv.d.e.a("FPV_IOCSettings");
                Y();
            } else if (R.id.ac2 == id) {
                if (dji.pilot.fpv.d.b.n(null)) {
                    M();
                } else if (dji.pilot.fpv.d.b.j(null)) {
                    L();
                }
            }
            if (obj != null) {
                this.aP = view;
                if (this.aP == null) {
                }
            }
        }
    }

    public void onEventMainThread(a aVar) {
        if (!(this.bc == null || this.bc == getSettingDlg())) {
            this.bc.dismiss();
        }
        if (!getSettingDlg().isShowing()) {
            getSettingDlg().show();
        }
        switch (aVar) {
            case Mc_Sensor:
                getSettingDlg().b();
                return;
            case Flyc_Compass:
                getSettingDlg().c();
                return;
            case Rc_Mode:
                getSettingDlg().d();
                return;
            default:
                return;
        }
    }

    private dji.setting.ui.b getSettingDlg() {
        if (this.ba == null) {
            this.ba = new dji.setting.ui.b(getContext());
            this.ba.setOnDismissListener(this.aN);
            this.ba.setOnShowListener(this.aO);
        }
        return this.ba;
    }

    private dji.pilot.fpv.activity.c getGuandianDlg() {
        if (this.aZ == null) {
            this.aZ = new dji.pilot.fpv.activity.c(getContext());
            this.aZ.setOnDismissListener(this.aN);
            this.aZ.setOnShowListener(this.aO);
        }
        return this.aZ;
    }

    public void onEventMainThread(DataSmartBatteryGetPushDynamicData dataSmartBatteryGetPushDynamicData) {
        this.aI.update(dataSmartBatteryGetPushDynamicData);
        x();
    }

    public void onEventMainThread(DataA2PushCommom dataA2PushCommom) {
        this.N.setText(dji.pilot.fpv.d.b.a(this.be, this.bf, false)[0]);
    }

    public void onEventMainThread(DataRTKPushStatus dataRTKPushStatus) {
        DJILogHelper.getInstance().LOGD("pm820", "**into DataRTKPushStatus");
        if (this.S != null && this.O != null) {
            if (dji.pilot.fpv.d.b.o() && dataRTKPushStatus.isGetted() && !dataRTKPushStatus.isPushLosed()) {
                if (dataRTKPushStatus.b() == 50) {
                    this.O.setEnabled(true);
                    this.S.show();
                    this.S.setTextColor(Color.rgb(0, 216, 255));
                } else {
                    this.O.setEnabled(true);
                    this.S.show();
                    this.S.setTextColor(Color.rgb(160, 160, 160));
                }
                int a = dataRTKPushStatus.a();
                if (this.cM != a) {
                    this.cM = a;
                    String h = h(this.cM);
                    if (!"".equals(h)) {
                        dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                        if (a == 6) {
                            bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.c;
                        }
                        bVar.c = h;
                        dji.thirdparty.a.c.a().e(bVar);
                        return;
                    }
                    return;
                }
                return;
            }
            this.S.go();
            this.O.setEnabled(false);
        }
    }

    private String h(int i) {
        String str = "";
        switch (i) {
            case 0:
                return str;
            case 1:
                return getContext().getString(R.string.rtk_error_code_start_error);
            case 2:
                return getContext().getString(R.string.rtk_error_code_data_cnnct_lose);
            case 3:
                return getContext().getString(R.string.rtk_error_code_ground_antenna);
            case 4:
                return getContext().getString(R.string.rtk_error_code_reset_ground_coord);
            case 5:
                return getContext().getString(R.string.rtk_error_code_initing);
            case 6:
                return getContext().getString(R.string.rtk_error_code_not_actived);
            default:
                return getContext().getString(R.string.rtk_error_code_unknown, new Object[]{Integer.valueOf(i)});
        }
    }
}
