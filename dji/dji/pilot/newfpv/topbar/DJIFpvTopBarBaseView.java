package dji.pilot.newfpv.topbar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.common.flightcontroller.DJIFlightControllerFlightMode;
import dji.common.flightcontroller.DJIGPSSignalStatus;
import dji.common.remotecontroller.DJIRCControlMode;
import dji.common.remotecontroller.DJIRCControlStyle;
import dji.log.DJILog;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon.ConnStatus;
import dji.midware.data.model.P3.DataFlycGetPushSmartBattery;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.BatteryType;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataOsdGetPushSignalQuality;
import dji.midware.data.model.P3.DataRcGetPushBatteryInfo;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.data.model.P3.DataSmartBatteryGetPushDynamicData;
import dji.midware.data.model.P3.DataWifiGetPushSignal;
import dji.pilot.R;
import dji.pilot.battery.a.a$e;
import dji.pilot.dji_groundstation.a.e;
import dji.pilot.fpv.camera.osd.DJICameraOsdKumquatView;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.f;
import dji.pilot.fpv.model.n$b;
import dji.pilot.fpv.view.DJISmartBatteryView;
import dji.pilot.liveshare.DJILiveShareActivity;
import dji.pilot.liveshare.LiveShareFpvTopView;
import dji.pilot.liveshare.Youtube.CustomModeActivity;
import dji.pilot.liveshare.Youtube.youtubeLiveActivity;
import dji.pilot.newfpv.f.g;
import dji.pilot.newfpv.h;
import dji.pilot.newfpv.topbar.widget.DJIFpvTipView;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.reflect.AppPublicReflect.YoutubeChangeEvent;
import dji.pilot.visual.a.g$b;
import dji.pilot.visual.a.g$d;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.i;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.Locale;

public class DJIFpvTopBarBaseView extends DJIRelativeLayout implements OnClickListener, s, h<g>, c, dji.sdksharedlib.c.d {
    protected static final int a = 3000;
    protected static final int aD = 3;
    private static final String aP = DJIFpvTopBarBaseView.class.getSimpleName();
    protected static final int b = 0;
    protected static final int c = 1;
    protected static final int d = 2;
    protected static final int e = 4;
    protected static final int f = 8;
    protected static final int g = 16;
    protected static final int h = 32;
    protected static final int i = 64;
    protected static final int j = 128;
    protected static final int k = 255;
    protected static final int l = 592;
    protected static final int m = 593;
    protected static final int n = 594;
    protected static final int o = 8192;
    protected static final int p = 2000;
    protected static final long q = 100;
    protected static final String r = "99：99";
    protected static int s = 0;
    protected static int t = 0;
    protected static final String u = "--/--";
    protected static final int v = Integer.MAX_VALUE;
    protected DJIImageView A = null;
    protected DJIImageView B = null;
    protected DJITextView C = null;
    protected DJITextView D = null;
    protected View E = null;
    protected DJIImageView F = null;
    protected DJIImageView G = null;
    protected View H = null;
    protected DJIImageView I = null;
    protected View J = null;
    protected DJIImageView K = null;
    protected DJITextView L = null;
    protected DJITextView M = null;
    protected ImageView N = null;
    protected DJIImageView O = null;
    protected DJISmartBatteryView P = null;
    protected DJITextView Q = null;
    protected DJIFpvTipView R = null;
    protected LiveShareFpvTopView S = null;
    protected Context T = null;
    protected OnDismissListener U = null;
    protected OnShowListener V = null;
    protected View W = null;
    protected ConnStatus aA = ConnStatus.NORMAL;
    protected int aB = 0;
    protected int aC = 0;
    protected MODE aE = MODE.d;
    protected int aF = 3;
    protected DataOsdGetPushCommon aG = null;
    protected DataOsdGetPushSignalQuality aH = null;
    protected DataFlycGetPushSmartBattery aI = null;
    protected DataCenterGetPushBatteryCommon aJ = null;
    protected DataRcGetPushBatteryInfo aK = null;
    protected DataOsdGetPushHome aL = null;
    protected final DecimalFormat aM = new DecimalFormat("#.##");
    protected ProductType aN = ProductType.Orange;
    protected boolean aO = false;
    private dji.pilot.newfpv.g aQ = null;
    private int aR = 0;
    private dji.pilot.newfpv.d aS = null;
    private boolean aT = false;
    private dji.sdksharedlib.b.c aU = null;
    private dji.sdksharedlib.b.c aV = null;
    private dji.sdksharedlib.b.c aW = null;
    private dji.sdksharedlib.b.c aX = null;
    private dji.sdksharedlib.b.c aY = null;
    private dji.sdksharedlib.b.c aZ = null;
    protected d aa = null;
    protected volatile int ab = 0;
    protected OnClickListener ac = null;
    protected long ad = 0;
    protected dji.pilot.fpv.activity.d ae = null;
    protected dji.pilot.fpv.inner.a af = null;
    protected dji.pilot.fpv.activity.c ag = null;
    protected dji.setting.ui.b ah;
    protected volatile boolean ai = true;
    protected Dialog aj = null;
    protected FLYC_STATE ak = FLYC_STATE.OTHER;
    protected DJIFlightControllerFlightMode al = DJIFlightControllerFlightMode.Unknown;
    protected boolean am = false;
    protected boolean an = false;
    protected int ao = 0;
    protected int ap = 0;
    protected int aq = -1;
    protected int ar = -1;
    protected int as = -1;
    protected boolean at = false;
    protected RcModeChannel au = RcModeChannel.CHANNEL_UNKNOWN;
    protected boolean av = false;
    protected int aw = -1;
    protected int ax = 0;
    protected int ay = 0;
    protected BatteryType az = BatteryType.Unknown;
    private dji.sdksharedlib.b.c ba = null;
    private dji.sdksharedlib.b.c bb = null;
    private dji.sdksharedlib.b.c bc = null;
    private DJICameraOsdKumquatView bd;
    private boolean be = false;
    protected DJIStateImageView w = null;
    protected View x = null;
    protected DJITextView y = null;
    protected View z = null;

    public enum a {
        Mc_Sensor,
        Mc_Guidance,
        Flyc_Compass,
        Rc_Mode
    }

    public enum b {
        BATTERY,
        MC_SHOWBY_GUIDANCE
    }

    public static class c {
        public final a a;

        public enum a {
            BACK_BTN_CLICK,
            SHOW_DIALOG,
            HIDE_DIALOG
        }

        public c(a aVar) {
            this.a = aVar;
        }
    }

    protected static final class d extends Handler {
        private final WeakReference<DJIFpvTopBarBaseView> a;

        public d(DJIFpvTopBarBaseView dJIFpvTopBarBaseView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIFpvTopBarBaseView);
        }

        public void handleMessage(Message message) {
            DJIFpvTopBarBaseView dJIFpvTopBarBaseView = (DJIFpvTopBarBaseView) this.a.get();
            if (dJIFpvTopBarBaseView != null) {
                switch (message.what) {
                    case DJIFpvTopBarBaseView.l /*592*/:
                        dJIFpvTopBarBaseView.update();
                        return;
                    case DJIFpvTopBarBaseView.m /*593*/:
                        dJIFpvTopBarBaseView.connect();
                        return;
                    case DJIFpvTopBarBaseView.n /*594*/:
                        dJIFpvTopBarBaseView.disconnect();
                        return;
                    case 8192:
                        dJIFpvTopBarBaseView.a(message.obj != null ? (MODE) message.obj : null);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public void bind(dji.pilot.newfpv.g gVar, int i) {
        this.aQ = gVar;
        this.aR = i;
    }

    public void bindInfo(dji.pilot.newfpv.view.b.a aVar, g gVar, g gVar2) {
        this.aS = new dji.pilot.newfpv.d(this, aVar, gVar, gVar2);
    }

    public dji.pilot.newfpv.view.b.a getViewId() {
        return this.aS.b;
    }

    public dji.pilot.newfpv.d getViewInfo() {
        return this.aS;
    }

    public boolean needShow() {
        return true;
    }

    public View getSelf() {
        return this;
    }

    public void onEventMainThread(g gVar) {
        dji.pilot.newfpv.view.a.a(g.TOP_BAR_SHOW == gVar, this.aQ, this);
    }

    public void onEventMainThread(e eVar) {
        if (eVar.s == 17) {
            show();
            startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.bt));
        } else if (eVar.s == 18) {
            go();
            startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.bu));
        }
    }

    public DJIFpvTopBarBaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.T = context;
        j();
        bindInfo(dji.pilot.newfpv.view.b.a.ViewId_TopBar, g.TOP_BAR_SHOW, g.TOP_BAR_HIDE);
    }

    public boolean hasDlgShowing() {
        return this.aj != null;
    }

    public void handleBatteryClickPush() {
        if (getSettingDlg().isShowing()) {
            hideDialog();
        } else {
            s();
        }
    }

    private void D() {
        this.aU = f.a(i.a, i.B);
        this.aV = f.a(dji.sdksharedlib.b.e.a, dji.sdksharedlib.b.e.bk);
        this.aW = f.a(dji.sdksharedlib.b.e.a, dji.sdksharedlib.b.e.aj);
        this.aX = f.a(dji.sdksharedlib.b.e.a, dji.sdksharedlib.b.e.ae);
        this.aY = f.a(dji.sdksharedlib.b.e.a, dji.sdksharedlib.b.e.Q);
        this.aZ = f.a(dji.sdksharedlib.b.a.c.h, dji.sdksharedlib.b.a.c.D);
        this.ba = f.a(dji.sdksharedlib.b.a.c.h, dji.sdksharedlib.b.a.c.F);
        this.bc = f.a(dji.sdksharedlib.b.a.a, dji.sdksharedlib.b.a.g);
        this.bb = dji.sdksharedlib.a.b.i(dji.sdksharedlib.b.a.a.c);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            D();
            f.a(new dji.sdksharedlib.b.c[]{this.aU, this.aV, this.aW, this.aX, this.aY, this.aZ, this.ba, this.bb}, (dji.sdksharedlib.c.d) this, true);
            dji.thirdparty.a.c.a().a(this);
            onEventMainThread(dji.midware.data.manager.P3.i.getInstance().c());
            this.aa.sendEmptyMessage(8192);
            this.S.dispatchOnCreate();
            if (!dji.pilot.liveshare.b.getInstance().isRunning()) {
                this.S.setVisibility(8);
            }
            if (ServiceManager.getInstance().isRemoteOK()) {
                E();
            }
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode()) {
            super.onDetachedFromWindow();
            DJISDKCache.getInstance().stopListening(this);
            this.S.dispatchOnDestroy();
            hideDialog();
            this.aa.removeMessages(8192);
            this.aa.removeMessages(l);
            dji.thirdparty.a.c.a().d(this);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            setMotionEventSplittingEnabled(false);
            k();
            l();
            this.R = (DJIFpvTipView) findViewById(R.id.ac5);
            this.R.setOnClickListener(this);
            p();
            m();
            n();
            o();
            dji.pilot.battery.a.d.getInstance();
        }
    }

    private void E() {
        F();
        J();
        b();
        G();
        I();
        H();
        e();
    }

    public void onValueChange(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (cVar.equals(this.aV)) {
            F();
        } else if (cVar.equals(this.aW)) {
            F();
        } else if (cVar.equals(this.aX)) {
            J();
        } else if (cVar.equals(this.aY)) {
            b();
        } else if (cVar.equals(this.bc)) {
            G();
        } else if (cVar.equals(this.aZ)) {
            I();
        } else if (cVar.equals(this.ba)) {
            H();
        } else if (this.bb.equals(cVar)) {
            e();
        }
    }

    private void F() {
        try {
            DJIFlightControllerFlightMode dJIFlightControllerFlightMode = (DJIFlightControllerFlightMode) dji.sdksharedlib.a.a.a(this.aV);
            if (dJIFlightControllerFlightMode != null) {
                this.al = dJIFlightControllerFlightMode;
            }
            this.am = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.aW), false);
        } catch (ClassCastException e) {
            DJILog.e(aP, "cast class error " + e);
        }
        int[] a = f.a(this.al, this.am, false);
        a("updateFlyMode resIds = " + getResources().getString(a[0]));
        a("updateFlyMode mFlightMode = " + this.al);
        a("updateFlyMode mIsVisualWork = " + this.am);
        this.y.setText(a[0]);
    }

    private void G() {
        a(dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.bc)));
    }

    private void H() {
        this.G.setImageLevel(dji.pilot.fpv.d.b.a(dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.ba)), 5));
    }

    private void I() {
        int a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.aZ));
        if (dji.pilot.fpv.d.b.l(this.aN)) {
            this.H.setVisibility(8);
            return;
        }
        this.H.setVisibility(0);
        this.I.setImageLevel(dji.pilot.fpv.d.b.a(a, 5));
    }

    private void J() {
        DJIGPSSignalStatus dJIGPSSignalStatus = (DJIGPSSignalStatus) dji.sdksharedlib.a.a.a(this.aX);
        if (dJIGPSSignalStatus == null) {
            this.B.setImageLevel(DJIGPSSignalStatus.Level0.value());
            return;
        }
        if (dJIGPSSignalStatus == DJIGPSSignalStatus.None) {
            dJIGPSSignalStatus = DJIGPSSignalStatus.Level0;
        }
        this.B.setImageLevel(dJIGPSSignalStatus.value());
    }

    public void hideDialog() {
        if (this.aj != null) {
            this.aj.dismiss();
        }
    }

    public void connect() {
        if (DataOsdGetPushSignalQuality.getInstance().isGetted()) {
            d();
        }
    }

    public void disconnect() {
        p();
        m();
        n();
        o();
        if (dji.pilot.fpv.d.b.j(this.aN) && this.O.getVisibility() == 0) {
            this.O.hide();
        }
        this.ai = true;
    }

    protected void a(MODE mode) {
        if (mode == null) {
            this.aE = dji.pilot.c.d.b;
        } else {
            this.aE = mode;
        }
        dji.sdksharedlib.d.a availableValue = DJISDKCache.getInstance().getAvailableValue(this.aU);
        if (availableValue == null) {
            return;
        }
        if (((DJIRCControlMode) availableValue.e()).controlStyle == DJIRCControlStyle.SlaveCustom || this.aO) {
            this.x.setEnabled(false);
            this.J.setEnabled(false);
            return;
        }
        this.x.setEnabled(true);
        this.J.setEnabled(true);
    }

    public void update() {
        if ((this.ab & 1) != 0) {
            if (this.aG.isGetted()) {
                c();
            }
            this.ab &= -2;
        }
        if ((this.ab & 8) != 0) {
            if (this.aJ.isGetted() || dji.pilot.fpv.d.b.p()) {
                h();
            }
            this.ab &= -9;
        }
        if ((this.ab & 4) != 0) {
            if (dji.pilot.fpv.d.b.n()) {
                int voltage = this.aI.getVoltage();
                if (this.as != voltage) {
                    this.as = voltage;
                    float f = (((float) voltage) * 1.0f) / 1000.0f;
                    this.L.setText(this.T.getString(R.string.battery_voltage_unit, new Object[]{Float.valueOf(f)}));
                    this.L.setTextColor(this.T.getResources().getColor(R.color.om));
                    this.K.setImageResource(R.drawable.fpv_topbar_battery_nor);
                    if (dji.pilot.battery.a.a.b(f) != 0) {
                        this.L.setTextColor(this.T.getResources().getColor(R.color.a6));
                        this.K.setImageResource(R.drawable.fpv_topbar_battery_dangerous);
                    }
                }
            } else {
                g();
            }
            this.ab &= -5;
        }
        if ((this.ab & 2) != 0) {
            d();
            this.ab &= -3;
        }
    }

    public void onEventMainThread(g$d dji_pilot_visual_a_g_d) {
        if (g$d.AVOID_CHANGED == dji_pilot_visual_a_g_d) {
            K();
        }
    }

    public void onEventMainThread(g$b dji_pilot_visual_a_g_b) {
        K();
    }

    private void K() {
        if (this.O.getVisibility() != 0) {
            this.O.show();
        }
        dji.pilot.visual.a.g.c p = dji.pilot.visual.a.c.getInstance().p();
        if (dji.pilot.visual.a.g.c.e == p) {
            this.O.setImageResource(R.drawable.fpv_topbar_vision_normal_icon);
        } else if (dji.pilot.visual.a.g.c.a == p) {
            this.O.setImageResource(R.drawable.fpv_topbar_vision_close_icon);
        } else {
            this.O.setImageResource(R.drawable.fpv_topbar_vision_disable_icon);
        }
    }

    protected void a(FLYC_STATE flyc_state, boolean z) {
        this.ak = flyc_state;
        this.am = z;
        F();
    }

    protected void a() {
        this.C.show();
    }

    protected void b() {
        int a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.e(dji.sdksharedlib.b.e.Q));
        if (a >= 50) {
            a = 0;
        }
        if (a > 0) {
            this.A.setEnabled(true);
            a();
        } else {
            this.A.setEnabled(false);
            this.C.go();
        }
        this.C.setText(String.valueOf(a));
    }

    protected void a(int i) {
        if (i != -1) {
            this.aq = i;
            if (dji.pilot.publics.e.a.k() && dji.pilot.publics.e.a.j()) {
                this.K.setImageResource(R.drawable.fpv_topbar_battery_dangerous);
                this.L.setTextColor(this.T.getResources().getColor(R.color.gj));
                this.L.setText(R.string.fpv_battery_error);
            } else if (i > 100) {
                this.K.setImageResource(R.drawable.fpv_topbar_battery_dangerous);
                this.L.setTextColor(this.T.getResources().getColor(R.color.gj));
                this.L.setText(R.string.fpv_battery_error);
            } else {
                int voltageWarning = this.aG.getVoltageWarning();
                if (this.aA == ConnStatus.EXCEPTION) {
                    this.K.setImageResource(R.drawable.fpv_topbar_battery_dangerous);
                    this.L.setTextColor(this.T.getResources().getColor(R.color.om));
                    this.L.setText(R.string.fpv_default_str);
                } else if (this.aA == ConnStatus.NORMAL) {
                    if (i <= this.ax || voltageWarning == 1 || voltageWarning == 2) {
                        this.K.setImageResource(R.drawable.fpv_topbar_battery_dangerous);
                        this.L.setTextColor(this.T.getResources().getColor(R.color.gj));
                    } else {
                        this.K.setImageResource(R.drawable.fpv_topbar_battery_nor);
                        this.L.setTextColor(this.T.getResources().getColor(R.color.a3));
                    }
                    this.L.setText(a(this.T.getString(R.string.fpv_percent, new Object[]{Integer.valueOf(i)}), 1, 1));
                } else {
                    this.K.setImageResource(R.drawable.fpv_topbar_battery_dangerous);
                    if (i <= this.ax || voltageWarning == 1 || voltageWarning == 2) {
                        this.L.setTextColor(this.T.getResources().getColor(R.color.gj));
                    } else {
                        this.L.setTextColor(this.T.getResources().getColor(R.color.a3));
                    }
                    this.L.setText(a(this.T.getString(R.string.fpv_percent, new Object[]{Integer.valueOf(i)}), 1, 1));
                }
            }
        }
    }

    protected void c() {
        DataOsdGetPushCommon dataOsdGetPushCommon = this.aG;
        FLYC_STATE flyc_state = this.ak;
        flyc_state = dataOsdGetPushCommon.getFlycState();
        boolean isVisionUsed = dataOsdGetPushCommon.isVisionUsed();
        boolean a = dji.pilot.fpv.d.b.a(this.aL.isBeginnerMode(), this.aL.isMultipleModeOpen());
        if (!(this.ak == flyc_state && this.am == isVisionUsed && a == this.an)) {
            this.an = a;
            a(flyc_state, isVisionUsed);
        }
        int voltageWarning = dataOsdGetPushCommon.getVoltageWarning();
        if (dataOsdGetPushCommon.getFlycVersion() >= 1) {
            boolean z = dji.pilot.publics.e.a.j() && dji.pilot.publics.e.a.k();
            if (this.ar != voltageWarning) {
                this.ar = voltageWarning;
                a(this.aq);
                if (dji.pilot.publics.e.a.j()) {
                    if (this.ar == 1 || this.ar == 2) {
                        this.M.setTextColor(this.T.getResources().getColor(R.color.a6));
                    } else {
                        this.M.setTextColor(this.T.getResources().getColor(R.color.om));
                    }
                }
            } else if (this.at != z) {
                this.at = z;
                a(this.aq);
            }
        } else if (dataOsdGetPushCommon.isGetted()) {
            int battery = dataOsdGetPushCommon.getBattery();
            if (!(this.aq == battery && this.ar == voltageWarning)) {
                this.ar = voltageWarning;
                a(battery);
            }
        }
        BatteryType batteryType = dataOsdGetPushCommon.getBatteryType();
        if (batteryType != this.az) {
            this.az = batteryType;
            if (batteryType == BatteryType.NonSmart) {
                this.M.go();
            } else if (!dji.pilot.battery.a.a.getInstance().a() || dji.pilot.fpv.d.b.n()) {
                this.M.go();
            } else {
                this.M.show();
            }
        }
    }

    protected void d() {
        int i = 0;
        if (dji.pilot.fpv.d.b.l(this.aN)) {
            this.H.setVisibility(8);
            return;
        }
        this.H.setVisibility(0);
        int downSignalQuality = dji.pilot.c.d.i == 1 ? ServiceManager.getInstance().isRemoteOK() ? this.aH.getDownSignalQuality() : 0 : ServiceManager.getInstance().isRemoteOK() ? dji.pilot.fpv.d.b.g(this.aH.getDownSignalQuality()) : 0;
        if (this.ao != downSignalQuality) {
            this.ao = downSignalQuality;
            this.G.setImageLevel(dji.pilot.fpv.d.b.a(downSignalQuality, 5));
        }
        if (ServiceManager.getInstance().isRemoteOK()) {
            i = this.aH.getUpSignalQuality();
        }
        if (this.ap != i) {
            this.ap = i;
            this.I.setImageLevel(dji.pilot.fpv.d.b.a(i, 5));
        }
    }

    protected void e() {
        if (dji.pilot.fpv.d.b.l(this.aN)) {
            int a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.a(this.bb));
            DJILogHelper.getInstance().LOGD(aP, "wifi-" + a, false, true);
            this.G.setImageLevel(dji.pilot.fpv.d.b.a(a, 4));
        }
    }

    private void a(int i, int i2, int i3) {
        int i4 = 0;
        int progress = this.P.getProgress();
        this.P.setProgress(i3);
        int i5 = i > i3 ? i3 : i;
        if (this.ax != i5) {
            this.ax = i5;
            this.P.setSecondaryProgress(i5);
            DJISmartBatteryView dJISmartBatteryView = this.P;
            if (i >= i3) {
                i = 0;
            }
            dJISmartBatteryView.setGoHomeBattery(i);
        }
        if (i2 > i3) {
            i2 = i3;
        }
        if (this.ay != i2) {
            this.ay = i2;
            this.P.setThirdProgress(this.ay);
        }
        i5 = this.aI.getLowWarning();
        if (!(i5 == this.aB && progress == i3)) {
            this.aB = i5;
            DJISmartBatteryView dJISmartBatteryView2 = this.P;
            if (i5 > i3) {
                i5 = 0;
            }
            dJISmartBatteryView2.setLowWarning(i5);
        }
        i5 = this.aI.getSeriousLowWarning();
        if (this.aC != i5 || progress != i3) {
            this.aC = i5;
            DJISmartBatteryView dJISmartBatteryView3 = this.P;
            if (i5 <= i3) {
                i4 = i5;
            }
            dJISmartBatteryView3.setSeriousWarning(i4);
        }
    }

    protected void f() {
        if (this.aw == -1) {
            this.Q.hide();
        } else {
            this.Q.show();
        }
    }

    private boolean e(int i) {
        return i >= 0 && i < 3599;
    }

    private void a(int i, int i2) {
        int i3 = 0;
        if (this.aG.getBatteryType() == BatteryType.NonSmart) {
            if (this.aw != i2) {
                this.aw = i2;
                this.Q.setText(this.T.getString(R.string.fpv_percent, new Object[]{Integer.valueOf(i2)}));
            }
        } else if (this.aw != i) {
            if (e(i)) {
                this.aw = i;
            } else {
                this.aw = 0;
            }
            if (this.aw == 0) {
                this.Q.setText(u);
            } else {
                this.Q.setText(b(this.aw));
            }
        }
        f();
        if (s == 0) {
            s = ((int) (this.Q.getPaint().measureText(r) + dji.pilot.visual.a.d.c)) + com.dji.frame.c.e.b(this.T, 6.0f);
            t = com.dji.frame.c.e.b(this.T, 1.0f);
        }
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.Q.getLayoutParams();
        int width = this.w.getWidth();
        int i4 = DJIBaseActivity.screenWidth;
        int i5 = i4 - width;
        if (i == Integer.MAX_VALUE) {
            width += (i5 * i2) / 100;
            i5 = s / 2;
        } else {
            width += (i5 * i2) / 100;
            i5 = s / 2;
        }
        if (width > i4 - i5) {
            i3 = (i4 - s) - t;
        } else if (width > i5) {
            i3 = width - i5;
        }
        if (i3 != marginLayoutParams.leftMargin || marginLayoutParams.width != s) {
            marginLayoutParams.leftMargin = i3;
            marginLayoutParams.width = s;
            this.Q.setLayoutParams(marginLayoutParams);
        }
    }

    protected void g() {
        boolean z;
        int goHomeBattery = this.aI.getGoHomeBattery();
        int landBattery = this.aI.getLandBattery();
        if (this.aG.getFlycVersion() >= 1) {
            int relativeCapacityPercentage;
            if (dji.pilot.fpv.d.b.p() && DataSmartBatteryGetPushDynamicData.getInstance().isGetted() && DataSmartBatteryGetPushDynamicData.getInstance().getIndex() == 0) {
                relativeCapacityPercentage = DataSmartBatteryGetPushDynamicData.getInstance().getRelativeCapacityPercentage();
                if (this.aq != relativeCapacityPercentage) {
                    a(relativeCapacityPercentage);
                }
            } else if (this.aI.isGetted()) {
                if (this.aG.getBatteryType() == BatteryType.NonSmart) {
                    relativeCapacityPercentage = this.aI.getVoltagePercent();
                } else {
                    relativeCapacityPercentage = this.aI.getBattery();
                }
                if (this.aq != relativeCapacityPercentage) {
                    a(relativeCapacityPercentage);
                }
            }
        }
        a(goHomeBattery, landBattery, this.aq);
        if (!this.aJ.isGetted() || this.aA == ConnStatus.NORMAL) {
            a(this.aI.getUsefulTime(), this.aq);
        } else {
            a(Integer.MAX_VALUE, 0);
        }
        if (this.az == BatteryType.NonSmart) {
            i();
        }
        if ((this.aI.getStatus() & 4096) == 4096) {
            z = true;
        } else {
            z = false;
        }
        if (this.aI.isGetted() && z && !this.aT && DataOsdGetPushCommon.getInstance().groundOrSky() == 2) {
            dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
            bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.b;
            bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
            bVar.b = R.string.battery_limit_flight_movement;
            dji.thirdparty.a.c.a().e(bVar);
        }
        this.aT = z;
    }

    protected void h() {
        if (!dji.pilot.fpv.d.b.n()) {
            if (System.currentTimeMillis() - 0 > 2000) {
                ConnStatus connStatus = this.aJ.getConnStatus();
                if (dji.pilot.publics.e.a.b()) {
                    DataSmartBatteryGetPushDynamicData instance = DataSmartBatteryGetPushDynamicData.getInstance();
                    connStatus = instance.isGetted() ? ConnStatus.ofData((int) instance.getStatus()) : ConnStatus.EXCEPTION;
                }
                if (this.aA != connStatus) {
                    this.aA = connStatus;
                    if (this.aA == ConnStatus.NORMAL) {
                        int voltageWarning = this.aG.getVoltageWarning();
                        if ((this.aq != -1 && this.aq <= this.ax) || voltageWarning == 1 || voltageWarning == 2) {
                            this.K.setImageResource(R.drawable.fpv_topbar_battery_dangerous);
                        } else {
                            this.K.setImageResource(R.drawable.fpv_topbar_battery_nor);
                        }
                        if (dji.pilot.fpv.d.b.f()) {
                            this.P.setVisibility(4);
                        } else {
                            this.P.setVisibility(0);
                            a(this.aI.getUsefulTime(), this.aq);
                        }
                    } else if (this.aA != ConnStatus.OTHER) {
                        this.K.setImageResource(R.drawable.fpv_topbar_battery_dangerous);
                        this.P.setVisibility(4);
                        a(Integer.MAX_VALUE, 0);
                        dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
                        bVar.b = R.string.fpv_errorpop_battery_error;
                        dji.thirdparty.a.c.a().e(bVar);
                    }
                }
            }
            if (this.az != BatteryType.NonSmart) {
                i();
            }
        }
    }

    protected void i() {
        int voltage;
        if (dji.pilot.fpv.d.b.p()) {
            if (DataSmartBatteryGetPushDynamicData.getInstance().getIndex() == 0) {
                voltage = DataSmartBatteryGetPushDynamicData.getInstance().getVoltage();
            } else {
                voltage = 0;
            }
        } else if (this.az == BatteryType.NonSmart) {
            voltage = this.aI.getVoltage();
        } else if (this.az == BatteryType.NonSmart) {
            voltage = this.aI.getVoltage();
        } else {
            int[] partVoltages = this.aJ.getPartVoltages();
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
            this.M.setText(R.string.fpv_default_str);
            this.M.setTextColor(this.T.getResources().getColor(R.color.a6));
        } else if (this.as != voltage) {
            this.as = voltage;
            voltage = dji.pilot.battery.a.a.c((((float) voltage) * 1.0f) / 1000.0f);
            this.M.setText(this.T.getString(R.string.battery_voltage_unit, new Object[]{Float.valueOf(r0)}));
            if (dji.pilot.publics.e.a.j()) {
                if (this.ar == 1 || this.ar == 2) {
                    this.M.setTextColor(this.T.getResources().getColor(R.color.a6));
                } else {
                    this.M.setTextColor(this.T.getResources().getColor(R.color.om));
                }
            } else if (voltage == 2) {
                this.M.setTextColor(this.T.getResources().getColor(R.color.a6));
            } else if (voltage == 1) {
                this.M.setTextColor(this.T.getResources().getColor(R.color.a7));
            } else {
                this.M.setTextColor(this.T.getResources().getColor(R.color.om));
            }
        }
    }

    protected void j() {
        if (!isInEditMode()) {
            this.aL = DataOsdGetPushHome.getInstance();
            this.aG = DataOsdGetPushCommon.getInstance();
            this.aH = DataOsdGetPushSignalQuality.getInstance();
            this.aI = DataFlycGetPushSmartBattery.getInstance();
            this.aJ = DataCenterGetPushBatteryCommon.getInstance();
            this.aK = DataRcGetPushBatteryInfo.getInstance();
            this.U = new OnDismissListener(this) {
                final /* synthetic */ DJIFpvTopBarBaseView a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    if (this.a.W != null) {
                        this.a.W.setSelected(false);
                        this.a.W = null;
                    }
                    this.a.aj = null;
                    dji.thirdparty.a.c.a().e(new c(a.HIDE_DIALOG));
                }
            };
            this.V = new OnShowListener(this) {
                final /* synthetic */ DJIFpvTopBarBaseView a;

                {
                    this.a = r1;
                }

                public void onShow(DialogInterface dialogInterface) {
                    this.a.aj = (Dialog) dialogInterface;
                    dji.thirdparty.a.c.a().e(new c(a.SHOW_DIALOG));
                }
            };
            this.ac = new OnClickListener(this) {
                final /* synthetic */ DJIFpvTopBarBaseView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (view.getId() != R.id.a89) {
                        return;
                    }
                    Intent intent;
                    if (dji.pilot.liveshare.b.getInstance().getStreamMode() == 0) {
                        intent = new Intent();
                        intent.setClass(this.a.T, youtubeLiveActivity.class);
                        this.a.T.startActivity(intent);
                    } else if (dji.pilot.liveshare.b.getInstance().getStreamMode() == 1) {
                        intent = new Intent();
                        intent.setClass(this.a.T, CustomModeActivity.class);
                        this.a.T.startActivity(intent);
                    } else if (dji.pilot.liveshare.b.getInstance().getStreamMode() == 2) {
                        intent = new Intent();
                        intent.setClass(this.a.T, DJILiveShareActivity.class);
                        intent.putExtra("type", dji.pilot.f.a.a.D);
                        this.a.T.startActivity(intent);
                    }
                }
            };
            this.aa = new d(this);
        }
    }

    protected void k() {
        DJIImageView dJIImageView = (DJIImageView) findViewById(R.id.ad2);
        this.w = (DJIStateImageView) findViewById(R.id.abn);
        this.w.setRelativeStateView(dJIImageView);
        this.x = findViewById(R.id.abp);
        this.y = (DJITextView) findViewById(R.id.abr);
        this.z = findViewById(R.id.abt);
        this.A = (DJIImageView) findViewById(R.id.abu);
        this.B = (DJIImageView) findViewById(R.id.abv);
        this.C = (DJITextView) findViewById(R.id.abw);
        this.D = (DJITextView) findViewById(R.id.abx);
        this.E = findViewById(R.id.aca);
        this.F = (DJIImageView) findViewById(R.id.acb);
        this.G = (DJIImageView) findViewById(R.id.acc);
        this.H = findViewById(R.id.ac8);
        this.I = (DJIImageView) findViewById(R.id.ac_);
        this.J = findViewById(R.id.acd);
        this.K = (DJIImageView) findViewById(R.id.ace);
        this.L = (DJITextView) findViewById(R.id.acf);
        this.M = (DJITextView) findViewById(R.id.acg);
        this.N = (ImageView) findViewById(R.id.ach);
        this.N.setOnClickListener(this);
        this.O = (DJIImageView) findViewById(R.id.ac3);
        this.O.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.E.setOnClickListener(this);
        this.H.setOnClickListener(this);
        this.J.setOnClickListener(this);
        this.z.setOnClickListener(this);
        if (!dji.pilot.battery.a.a.getInstance().a() || dji.pilot.fpv.d.b.n()) {
            this.M.go();
        } else {
            this.M.show();
        }
        M();
        this.bd = (DJICameraOsdKumquatView) findViewById(R.id.ad4);
    }

    public void setSmallMap(boolean z) {
        if (z) {
            this.bd.show();
        } else {
            this.bd.go();
        }
    }

    protected void l() {
        this.P = (DJISmartBatteryView) findViewById(R.id.aci);
        this.Q = (DJITextView) findViewById(R.id.acj);
        this.S = (LiveShareFpvTopView) findViewById(R.id.a89);
        this.S.setOnClickListener(this.ac);
        this.S.setVisibility(8);
    }

    protected void m() {
        this.G.setImageLevel(0);
        this.I.setImageLevel(0);
    }

    protected void n() {
        this.y.setText(R.string.fpv_default_str);
        this.z.setEnabled(true);
        this.A.setEnabled(true);
        this.B.setImageLevel(0);
        this.C.go();
        this.K.setImageResource(R.drawable.fpv_topbar_battery_nor);
        this.L.setTextColor(this.T.getResources().getColor(R.color.om));
        this.L.setText(R.string.fpv_default_str);
        this.M.setTextColor(getResources().getColor(R.color.om));
        this.M.setText(R.string.fpv_default_str);
        this.O.setImageResource(R.drawable.fpv_topbar_vision_close_icon);
        F();
    }

    protected void o() {
        this.P.setMax(100);
        this.P.setProgress(0);
        this.P.setSecondaryProgress(0);
        this.P.setThirdProgress(0);
        this.P.setLowWarning(0);
        this.P.setSeriousWarning(0);
        this.P.setGoHomeBattery(0);
        this.Q.hide();
        this.Q.setText(R.string.fpv_default_str);
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.Q.getLayoutParams();
        int width = this.Q.getWidth() / 2;
        if (width != marginLayoutParams.leftMargin) {
            marginLayoutParams.leftMargin = width;
            this.Q.setLayoutParams(marginLayoutParams);
        }
    }

    protected void p() {
        q();
        r();
    }

    protected void q() {
        this.ak = FLYC_STATE.OTHER;
        this.aq = -1;
        this.ar = -1;
        this.au = RcModeChannel.CHANNEL_UNKNOWN;
        this.as = -1;
        this.aw = -1;
        this.ax = 0;
        this.ay = 0;
        this.az = BatteryType.Unknown;
        this.aA = ConnStatus.NORMAL;
        this.aB = 0;
        this.aC = 0;
    }

    protected void r() {
        this.ao = 0;
        this.ap = 0;
        this.aF = 3;
    }

    protected String b(int i) {
        int[] e = dji.pilot.fpv.d.b.e(i);
        return String.format(Locale.US, "%1$02d:%2$02d", new Object[]{Integer.valueOf(e[1]), Integer.valueOf(e[0])});
    }

    protected String c(int i) {
        int[] f = dji.pilot.fpv.d.b.f(i);
        return String.format(Locale.US, "%1$02d:%2$02d:%3$02d", new Object[]{Integer.valueOf(f[2]), Integer.valueOf(f[1]), Integer.valueOf(f[0])});
    }

    protected SpannableString a(String str, int i, int i2) {
        SpannableString spannableString = new SpannableString(str);
        if (i2 == 0) {
            spannableString.setSpan(new dji.pilot.publics.d.a.a(DJITextView.NBOLD, 0, this.T.getResources().getDimensionPixelSize(R.dimen.rp)), i, str.length(), 17);
        } else if (1 == i2) {
            spannableString.setSpan(new dji.pilot.publics.d.a.a(DJITextView.NBOLD, 0, this.T.getResources().getDimensionPixelSize(R.dimen.ro)), 0, str.length() - i, 17);
        }
        return spannableString;
    }

    protected SpannableString a(String str, int i, boolean z) {
        SpannableString spannableString = new SpannableString(str);
        int dimensionPixelSize = this.T.getResources().getDimensionPixelSize(R.dimen.rp);
        int dimensionPixelSize2 = this.T.getResources().getDimensionPixelSize(R.dimen.rn);
        spannableString.setSpan(new dji.pilot.publics.d.a.a(DJITextView.NBOLD, 0, dimensionPixelSize), i, str.length(), 17);
        spannableString.setSpan(new dji.pilot.publics.d.a.b(DJITextView.NLIGHT, 2, dimensionPixelSize2, -1711276033), 0, i, 17);
        return spannableString;
    }

    private void L() {
        if (!(this.aj == null || this.aj == getSettingDlg())) {
            this.aj.dismiss();
        }
        getSettingDlg().e();
    }

    private void f(int i) {
        if (!(this.aj == null || this.aj == getSettingDlg())) {
            this.aj.dismiss();
        }
        getSettingDlg().a(i);
    }

    protected void s() {
        f(3);
    }

    protected void t() {
        f(0);
    }

    protected void u() {
        f(8);
    }

    protected void v() {
        f(7);
    }

    protected void w() {
        f(6);
    }

    protected void x() {
        if (dji.pilot.fpv.d.b.l(this.aN)) {
            f(5);
        } else {
            f(2);
        }
    }

    protected void y() {
        f(1);
    }

    protected void z() {
        L();
    }

    protected void A() {
        hideDialog();
    }

    protected void B() {
    }

    public void showCheckListDlg() {
        hideDialog();
        if (this.ae == null) {
            this.ae = new dji.pilot.fpv.activity.d(getContext());
            this.ae.setOnDismissListener(this.U);
            this.ae.setOnShowListener(this.V);
        }
        if (this.ae != null && !this.ae.isShowing()) {
            this.aj = this.ae;
            this.ae.show();
        }
    }

    public void hideCheckListDlg() {
        if (this.ae != null && this.ae.isShowing()) {
            this.ae.dismiss();
        }
    }

    public void showGuidanceTipDlg() {
        if (!(this.aj == null || this.aj == getGuandianDlg())) {
            this.aj.dismiss();
        }
        if (!getGuandianDlg().isShowing()) {
            getGuandianDlg().show();
        }
    }

    protected void a(int i, boolean z) {
        if (this.ai && z) {
            this.ab |= 255;
            if (!this.aa.hasMessages(l)) {
                this.aa.sendEmptyMessageDelayed(l, q);
            }
            this.ai = false;
        } else if ((this.ab & i) == 0) {
            this.ab |= i;
            if (!this.aa.hasMessages(l)) {
                this.aa.sendEmptyMessageDelayed(l, q);
            }
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        a(1, true);
        if ((dataOsdGetPushCommon.getFlycState() != FLYC_STATE.AssitedTakeoff && dataOsdGetPushCommon.getFlycState() != FLYC_STATE.AutoTakeoff) || !DataCenterGetPushBatteryCommon.getInstance().isGetted()) {
            this.be = false;
        } else if (((float) (DataCenterGetPushBatteryCommon.getInstance().getTemperature() / 10)) - dji.pilot.publics.e.e.f < DJIFlightControllerDataType.DJIVirtualStickRollPitchControlMaxVelocity && !this.be) {
            this.be = true;
            dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
            bVar.a = dji.pilot.fpv.view.DJIErrorPopView.d.b;
            bVar.f = dji.pilot.fpv.view.DJIErrorPopView.c.a;
            bVar.b = R.string.takeoff_tips_low_battery_temp;
            dji.thirdparty.a.c.a().e(bVar);
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushSignalQuality dataOsdGetPushSignalQuality) {
        a(2, false);
    }

    public void onEventBackgroundThread(DataFlycGetPushSmartBattery dataFlycGetPushSmartBattery) {
        a(4, true);
    }

    public void onEventBackgroundThread(DataCenterGetPushBatteryCommon dataCenterGetPushBatteryCommon) {
        a(8, true);
    }

    public void onEventBackgroundThread(DataSmartBatteryGetPushDynamicData dataSmartBatteryGetPushDynamicData) {
        a(8, true);
    }

    public void onEventBackgroundThread(DataRcGetPushBatteryInfo dataRcGetPushBatteryInfo) {
        a(16, false);
    }

    public void onEventBackgroundThread(DataOsdGetPushHome dataOsdGetPushHome) {
        a(32, true);
        a(1, true);
    }

    public void onEventBackgroundThread(DataWifiGetPushSignal dataWifiGetPushSignal) {
        a(128, false);
    }

    public void onEventBackgroundThread(p pVar) {
        this.ai = true;
        if (pVar == p.b) {
            this.ab = 0;
            this.aa.removeMessages(l);
            this.aa.sendEmptyMessage(m);
        } else if (pVar == p.a) {
            this.ab = 0;
            this.aa.removeMessages(l);
            this.aa.sendEmptyMessage(n);
        }
    }

    public void onEventBackgroundThread(dji.setting.ui.rc.RcMasterSlaveView.c cVar) {
        if (cVar != null) {
            this.aa.sendEmptyMessage(8192);
        }
    }

    public void onEventMainThread(b bVar) {
        if (bVar == b.BATTERY) {
            s();
        } else if (bVar == b.MC_SHOWBY_GUIDANCE) {
            u();
        }
    }

    public void onEventMainThread(n$b dji_pilot_fpv_model_n_b) {
        if (dji_pilot_fpv_model_n_b == n$b.SHOW_GUIDANCE_USER) {
            showGuidanceTipDlg();
        }
    }

    public void onEventMainThread(a$e dji_pilot_battery_a_a_e) {
        if (dji_pilot_battery_a_a_e != a$e.SHOW) {
            this.M.go();
        } else if (this.az != BatteryType.NonSmart) {
            this.M.show();
        }
    }

    private void a(ProductType productType, boolean z) {
        if (this.aN != productType || z) {
            this.aN = productType;
            if (dji.pilot.fpv.d.b.l(productType)) {
                this.F.go();
                this.G.setImageResource(R.drawable.topbar_wifi_level);
            } else {
                this.F.show();
                this.G.setImageResource(R.drawable.topbar_signal_level);
                M();
            }
            if (dji.pilot.fpv.d.b.n(productType)) {
                this.O.show();
                onEventMainThread(g$d.AVOID_CHANGED);
            } else if (dji.pilot.fpv.d.b.j(this.aN)) {
                this.O.hide();
                this.O.setImageResource(0);
            } else {
                this.O.go();
            }
            if (this.aN == ProductType.None) {
                dji.pilot.battery.a.d.getInstance().b();
            } else if (this.aN == ProductType.A3 || this.aN == ProductType.N3) {
                dji.pilot.battery.a.d.getInstance().a();
            }
        }
    }

    public void onEventMainThread(dji.midware.data.manager.P3.i.a aVar) {
        if (dji.pilot.fpv.d.b.l(dji.midware.data.manager.P3.i.getInstance().c())) {
            this.F.go();
            this.G.setImageResource(R.drawable.topbar_wifi_level);
            return;
        }
        this.F.show();
        this.G.setImageResource(R.drawable.topbar_signal_level);
        M();
    }

    private void M() {
        if (dji.pilot.publics.e.a.f(null)) {
            this.G.setImageResource(R.drawable.tobar_sdr_signal_level);
            if (this.I != null) {
                this.I.setImageResource(R.drawable.tobar_sdr_signal_level);
            }
        } else if (this.I != null) {
            this.I.setImageResource(R.drawable.topbar_signal_level);
        }
    }

    public void onEventMainThread(ProductType productType) {
        a(productType, false);
    }

    public void onEventMainThread(CustomModeActivity customModeActivity) {
        C();
    }

    public void onEventMainThread(youtubeLiveActivity dji_pilot_liveshare_Youtube_youtubeLiveActivity) {
        C();
    }

    public void onEventMainThread(YoutubeChangeEvent youtubeChangeEvent) {
        C();
    }

    public void onEventMainThread(dji.pilot.f.a.a aVar) {
        if (aVar.I == 8 || aVar.I == 16 || aVar.I == 5) {
            C();
        }
    }

    protected void C() {
        if (dji.pilot.liveshare.b.getInstance().isLaunch()) {
            A();
            this.S.setVisibility(0);
        } else {
            this.S.setVisibility(8);
        }
        this.S.handleEvent();
    }

    protected boolean d(int i) {
        return false;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (!d(id)) {
            if (R.id.abn == id) {
                dji.pilot.fpv.d.e.a("FPV_OsdTopBarView_Button_BackHome");
                dji.thirdparty.a.c.a().e(new c(a.BACK_BTN_CLICK));
            } else if (R.id.abp == id) {
                dji.pilot.fpv.d.e.a("FPV_MCSettingsView");
                t();
            } else if (R.id.aca == id) {
                dji.pilot.fpv.d.e.a("FPV_ImageTransmissionSettings");
                x();
            } else if (R.id.ac8 == id) {
                dji.pilot.fpv.d.e.a("FPV_RCSettings");
                y();
            } else if (R.id.acd == id) {
                dji.pilot.fpv.d.e.a("FPV_AircraftBattery");
                s();
            } else if (R.id.ach == id) {
                dji.pilot.fpv.d.e.a("FPV_GeneralSettings");
                w();
            } else if (R.id.ac5 == id) {
                dji.pilot.fpv.d.e.a("FPV_AircraftState");
                showCheckListDlg();
            } else if (R.id.abt == id) {
                if (dji.pilot.fpv.d.b.o()) {
                    new dji.pilot.fpv.activity.h(this.T).show();
                }
            } else if (R.id.abz == id) {
                dji.pilot.fpv.d.e.a("FPV_IOCSettings");
            } else if (R.id.ac3 != id) {
            } else {
                if (dji.pilot.fpv.d.b.n(null)) {
                    v();
                } else if (dji.pilot.fpv.d.b.j(null)) {
                    u();
                }
            }
        }
    }

    public void onEventMainThread(a aVar) {
        if (!(this.aj == null || this.aj == getSettingDlg())) {
            this.aj.dismiss();
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
        if (this.ah == null) {
            this.ah = new dji.setting.ui.b(getContext());
            this.ah.setOnDismissListener(this.U);
            this.ah.setOnShowListener(this.V);
        }
        return this.ah;
    }

    private dji.pilot.fpv.activity.c getGuandianDlg() {
        if (this.ag == null) {
            this.ag = new dji.pilot.fpv.activity.c(getContext());
            this.ag.setOnDismissListener(this.U);
            this.ag.setOnShowListener(this.V);
        }
        return this.ag;
    }

    public void onEventMainThread(DataSmartBatteryGetPushDynamicData dataSmartBatteryGetPushDynamicData) {
        i();
    }

    private void a(String str) {
    }
}
