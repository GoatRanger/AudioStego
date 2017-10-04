package dji.pilot.fpv.leftmenu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.graphics.Rect;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout.LayoutParams;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataA2PushCommom;
import dji.midware.data.model.P3.DataEyeGetPushException;
import dji.midware.data.model.P3.DataEyeGetPushFlatCheck;
import dji.midware.data.model.P3.DataEyeGetPushFlatCheck.FlatStatus;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataFlycGetPushAvoidParam;
import dji.midware.data.model.P3.DataGimbalControl.MODE;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataGimbalGetPushType;
import dji.midware.data.model.P3.DataGimbalGetPushType.DJIGimbalType;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLIGHT_ACTION;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataRcGetPushGpsInfo;
import dji.midware.data.model.P3.DataRcSetMaster;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.pilot.R;
import dji.pilot.c.d;
import dji.pilot.dji_groundstation.a.e;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.k;
import dji.pilot.fpv.flightmode.c$b;
import dji.pilot.fpv.flightmode.c$d;
import dji.pilot.fpv.model.n$b;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView$e;
import dji.pilot.newfpv.f;
import dji.pilot.publics.objects.g;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.visual.a.g$f;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.lang.ref.WeakReference;

public class DJILeftMenu extends DJIRelativeLayout implements OnClickListener {
    private static final int A = 2;
    private static final int B = 4;
    private static final int C = 8;
    private static final int D = 16;
    private static final int E = 32;
    private static final int F = 64;
    private static final float I = 1.2f;
    private static final int J = 0;
    private static final int K = 1;
    private static final int L = 2;
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 0;
    private static final String f = "key_gimbal_notfollow_tip";
    private static final String g = "key_gimbal_fpv_tip";
    private static final String h = "key_gimbal_follow_tip";
    private static final String i = "key_gimbal_reset_tip";
    private static final String j = "key_gimbal_ahead_tip";
    private static final String k = "key_gimbal_below_tip";
    private static final int l = 4096;
    private static final long m = 1500;
    private static final int n = 4097;
    private static final long o = 15000;
    private static final int p = 4098;
    private static final long q = 15000;
    private static final int r = 4099;
    private static final long s = 200;
    private static final int t = 1;
    private static final int u = 2;
    private static final int v = 4;
    private static final int w = 8;
    private static final int x = 16;
    private static final int y = 32;
    private static final int z = 1;
    private final SparseIntArray G = new SparseIntArray(5);
    private int H = 1;
    private Context M = null;
    private DJILinearLayout N = null;
    private DJIStateImageView O = null;
    private DJITextView P = null;
    private DJILinearLayout Q = null;
    private DJIStateImageView R = null;
    private DJITextView S = null;
    private DJIGimbalMenu T = null;
    private DJIHomePointMenu U = null;
    private DJITextView V = null;
    private DJIImageView W = null;
    private final Rect aA = new Rect();
    private ViewGroup aB = null;
    private boolean aC = false;
    private int aD = -1;
    private int aE = R.drawable.joystick_switch;
    private int aF = R.drawable.leftmenu_dlg_cancel;
    private dji.pilot.fpv.flightmode.a aG = null;
    private dji.gs.e.b aH = null;
    private DJITextView aa = null;
    private DJIImageView ab = null;
    private b ac = null;
    private int ad = 0;
    private Animation ae = null;
    private Animation af = null;
    private b ag = null;
    private c ah = null;
    private boolean ai = false;
    private FLYC_STATE aj = FLYC_STATE.OTHER;
    private ProductType ak = ProductType.OTHER;
    private int al = 0;
    private DJIGimbalType am = DJIGimbalType.OTHER;
    private int an = 0;
    private RcModeChannel ao = RcModeChannel.CHANNEL_UNKNOWN;
    private boolean ap = false;
    private int aq = R.drawable.leftmenu_gohome_icon;
    private int ar = R.drawable.leftmenu_takeoff_icon;
    private int as = R.drawable.camera_controll_video_icon_start;
    private Animation at = null;
    private Animation au = null;
    private int av = 0;
    private int aw = 0;
    private int ax = 0;
    private final Rect ay = new Rect();
    private final Rect az = new Rect();

    public interface b {
        void a(int i, int i2);
    }

    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] b = new int[dji.gs.views.EventView.a.values().length];

        static {
            try {
                b[dji.gs.views.EventView.a.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            a = new int[dji.pilot.fpv.control.q.a.values().length];
            try {
                a[dji.pilot.fpv.control.q.a.SMALL.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[dji.pilot.fpv.control.q.a.SMALL_HIDE.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[dji.pilot.fpv.control.q.a.LARGE.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[dji.pilot.fpv.control.q.a.SWITCH.ordinal()] = 4;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public enum a {
        DropGoHome,
        DropLand
    }

    private static final class c extends Handler {
        private final WeakReference<DJILeftMenu> a;

        private c(DJILeftMenu dJILeftMenu) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJILeftMenu);
        }

        public void handleMessage(Message message) {
            DJILeftMenu dJILeftMenu = (DJILeftMenu) this.a.get();
            if (dJILeftMenu != null) {
                switch (message.what) {
                    case 4096:
                        dJILeftMenu.b();
                        return;
                    case 4097:
                        dJILeftMenu.S.hide();
                        return;
                    case 4098:
                        dJILeftMenu.P.hide();
                        return;
                    case 4099:
                        dJILeftMenu.l();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJILeftMenu(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.M = context;
        if (!isInEditMode()) {
            this.ah = new c();
            this.ae = AnimationUtils.loadAnimation(this.M, R.anim.bi);
            this.af = AnimationUtils.loadAnimation(context, R.anim.bk);
            this.at = AnimationUtils.loadAnimation(context, R.anim.ba);
            this.au = AnimationUtils.loadAnimation(context, R.anim.ao);
            this.ag = new b(this) {
                final /* synthetic */ DJILeftMenu a;

                {
                    this.a = r1;
                }

                public void a(int i, int i2) {
                    if (1 == i) {
                        this.a.a(i2);
                        this.a.U.hideMenu(false);
                    } else if (3 == i) {
                        this.a.T.hideMenu(false);
                    }
                }
            };
        }
    }

    private void a(String str, int i, int i2) {
        g.a(this.M, str, true);
        LayoutParams layoutParams = (LayoutParams) this.V.getLayoutParams();
        layoutParams.addRule(2, i2);
        this.V.setLayoutParams(layoutParams);
        this.V.setText(i);
        a();
        this.ah.removeMessages(4096);
        this.ah.sendEmptyMessageDelayed(4096, m);
    }

    private void a() {
        this.V.show();
        this.V.startAnimation(this.at);
    }

    private void b() {
        if (this.V.isShown()) {
            this.V.go();
            this.V.startAnimation(this.au);
        }
    }

    private void a(int i) {
        if (i == 0) {
            b();
        } else if (i == 1) {
            a(f, (int) R.string.fpv_errorpop_gimbal_yawnotfollow_mode, (int) R.id.a4s);
        } else if (i == 2) {
            a(g, (int) R.string.fpv_errorpop_gimbal_fpv_mode, (int) R.id.a4s);
        } else if (i == 3) {
            a(h, (int) R.string.fpv_errorpop_gimbal_yawfollow_mode, (int) R.id.a4s);
        } else if (i == 4) {
            if (dji.pilot.fpv.d.b.k(null)) {
                a(i, (int) R.string.fpv_errorpop_gimbal_reset_forward_mode, (int) R.id.a4s);
            } else {
                a(i, (int) R.string.fpv_errorpop_gimbal_reset_mode, (int) R.id.a4s);
            }
        } else if (i == 5) {
            a(j, (int) R.string.fpv_errorpop_gimbal_fpv_ahead, (int) R.id.a4s);
        } else if (i == 6) {
            a(k, (int) R.string.fpv_errorpop_gimbal_fpv_below, (int) R.id.a4s);
        }
    }

    public void dispatchOnCreate() {
        onEventMainThread(i.getInstance().c());
        a(d.b);
        onEventMainThread(DataOsdGetPushCommon.getInstance());
        if (DataOsdGetPushHome.getInstance().isGetted()) {
            onEventMainThread(DataOsdGetPushHome.getInstance());
        }
        if (DataGimbalGetPushType.getInstance().isGetted()) {
            onEventMainThread(DataGimbalGetPushType.getInstance());
        }
        dji.thirdparty.a.c.a().a(this);
        onEventMainThread(dji.pilot.fpv.flightmode.c.getInstance().a());
        onEventMainThread(DataGimbalGetPushParams.getInstance());
        onEventMainThread(dji.logic.c.b.getInstance().e());
        l();
        DJILogHelper.getInstance().LOGE("DJIPackManager", "gimbal = register", true, true);
    }

    public void dispatchOnDestroy() {
        dji.thirdparty.a.c.a().d(this);
    }

    public void setMutexView(ViewGroup viewGroup) {
        this.aB = viewGroup;
    }

    public void hideMenu(boolean z) {
        this.V.go();
        this.T.hideMenu(z);
        this.U.hideMenu(z);
        if (z && getVisibility() != 8) {
            go();
            startAnimation(this.af);
        }
    }

    public boolean isShowing() {
        return this.T.isShown() || this.U.isShown();
    }

    public void showMenu() {
        if (getVisibility() != 0) {
            show();
            startAnimation(this.ae);
        }
    }

    public void switchGimbalMode() {
        ProductType c = i.getInstance().c();
        MODE mode = DataGimbalGetPushParams.getInstance().getMode();
        if (dji.pilot.fpv.d.b.k(c)) {
            if (mode == MODE.FPV) {
                DataSpecialControl.getInstance().setGimbalMode(MODE.YawFollow).start(20);
            } else {
                DataSpecialControl.getInstance().setGimbalMode(MODE.FPV).start(20);
            }
        } else if (mode != MODE.YawFollow && mode != MODE.YawNoFollow) {
            DataSpecialControl.getInstance().setGimbalMode(MODE.YawNoFollow).start(20);
        } else if (mode == MODE.YawFollow) {
            DataSpecialControl.getInstance().setGimbalMode(MODE.YawNoFollow).start(20);
        } else if (mode == MODE.YawNoFollow) {
            DataSpecialControl.getInstance().setGimbalMode(MODE.YawFollow).start(20);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.av = dji.pilot.fpv.model.b.a(this.M, R.dimen.pk);
            this.aw = dji.pilot.fpv.model.b.a(this.M, R.dimen.ph);
            this.ax = dji.pilot.fpv.model.b.a(this.M, R.dimen.pr);
            this.N = (DJILinearLayout) findViewById(R.id.a4p);
            this.O = (DJIStateImageView) findViewById(R.id.a4q);
            this.P = (DJITextView) findViewById(R.id.a4r);
            this.Q = (DJILinearLayout) findViewById(R.id.a4t);
            this.R = (DJIStateImageView) findViewById(R.id.a4u);
            this.S = (DJITextView) findViewById(R.id.a4v);
            this.T = (DJIGimbalMenu) findViewById(R.id.a4s);
            this.U = (DJIHomePointMenu) findViewById(R.id.a4w);
            this.V = (DJITextView) findViewById(R.id.a50);
            this.W = (DJIImageView) findViewById(R.id.a4x);
            this.aa = (DJITextView) findViewById(R.id.a4y);
            this.ab = (DJIImageView) findViewById(R.id.a4z);
            this.O.setStateAlpha(dji.pilot.visual.a.d.c);
            this.R.setStateAlpha(dji.pilot.visual.a.d.c);
            this.O.setOnClickListener(this);
            this.R.setOnClickListener(this);
            this.W.setOnClickListener(this);
            this.aa.setOnClickListener(this);
            this.ab.setOnClickListener(this);
            this.T.setOnMenuListener(this.ag);
            this.U.setOnMenuListener(this.ag);
            onEventMainThread(dji.pilot.fpv.flightmode.c.getInstance().a());
            h();
        }
    }

    private void c() {
        boolean isMotorUp = DataOsdGetPushCommon.getInstance().isMotorUp();
        if (this.ai != isMotorUp) {
            this.ai = isMotorUp;
            if (isMotorUp) {
                this.ar = R.drawable.leftmenu_land_icon;
                this.O.setImageResource(R.drawable.leftmenu_land_icon);
            } else {
                this.ar = R.drawable.leftmenu_takeoff_icon;
                this.O.setImageResource(R.drawable.leftmenu_takeoff_icon);
            }
            if (d.b == null || d.b != DataRcSetMaster.MODE.b) {
                this.R.setEnabled(isMotorUp);
            }
            f();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE r7, dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE r8, dji.midware.data.model.P3.DataOsdGetPushCommon.FLIGHT_ACTION r9) {
        /*
        r6 = this;
        r4 = 2130838782; // 0x7f0204fe float:1.7282556E38 double:1.052774239E-314;
        r5 = 4;
        r1 = 0;
        r0 = 1;
        r3 = 2130838781; // 0x7f0204fd float:1.7282554E38 double:1.0527742385E-314;
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE.GoHome;
        if (r8 != r2) goto L_0x003f;
    L_0x000d:
        r2 = r6.aq;
        if (r3 == r2) goto L_0x0031;
    L_0x0011:
        r6.aq = r3;
        r2 = r6.R;
        r2.setImageResource(r3);
        r2 = r6.R;
        r3 = 2130839922; // 0x7f020972 float:1.7284868E38 double:1.0527748022E-314;
        r2.setBackgroundResource(r3);
        r6.a(r5, r0);
        r6.getMutexRect();
        r2 = r6.aA;
        r2.setEmpty();
        r2 = dji.pilot.fpv.d.b.f();
        if (r2 == 0) goto L_0x0031;
    L_0x0031:
        r2 = r6.a(r9);
        r3 = r6.R;
        if (r2 != 0) goto L_0x003d;
    L_0x0039:
        r3.setEnabled(r0);
    L_0x003c:
        return;
    L_0x003d:
        r0 = r1;
        goto L_0x0039;
    L_0x003f:
        r2 = dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE.GoHome;
        if (r7 == r2) goto L_0x0047;
    L_0x0043:
        r2 = r6.aq;
        if (r3 != r2) goto L_0x003c;
    L_0x0047:
        r6.aq = r4;
        r2 = r6.R;
        r2.setImageResource(r4);
        r2 = 0;
        r2 = dji.pilot.fpv.d.b.d(r2);
        if (r2 == 0) goto L_0x006e;
    L_0x0055:
        r2 = r6.R;
        r3 = r6.getResources();
        r4 = 2131689835; // 0x7f0f016b float:1.9008697E38 double:1.053194715E-314;
        r3 = r3.getColor(r4);
        r2.setBackgroundColor(r3);
    L_0x0065:
        r2 = r6.R;
        r2.setEnabled(r0);
        r6.a(r5, r1);
        goto L_0x003c;
    L_0x006e:
        r2 = r6.R;
        r3 = 2130839923; // 0x7f020973 float:1.728487E38 double:1.0527748027E-314;
        r2.setBackgroundResource(r3);
        goto L_0x0065;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.fpv.leftmenu.DJILeftMenu.a(dji.midware.data.model.P3.DataOsdGetPushCommon$FLYC_STATE, dji.midware.data.model.P3.DataOsdGetPushCommon$FLYC_STATE, dji.midware.data.model.P3.DataOsdGetPushCommon$FLIGHT_ACTION):void");
    }

    private void b(FLYC_STATE flyc_state, FLYC_STATE flyc_state2, FLIGHT_ACTION flight_action) {
        boolean z = true;
        if (flyc_state2 == FLYC_STATE.AutoLanding) {
            if (R.drawable.leftmenu_gohome_cancel_icon != this.ar) {
                this.ar = R.drawable.leftmenu_gohome_cancel_icon;
                this.O.setImageResource(R.drawable.leftmenu_gohome_cancel_icon);
                e();
            }
            boolean b = b(flight_action);
            DJIStateImageView dJIStateImageView = this.O;
            if (b) {
                z = false;
            }
            dJIStateImageView.setEnabled(z);
        } else if (flyc_state == FLYC_STATE.AutoLanding || this.ar == R.drawable.leftmenu_gohome_cancel_icon) {
            if (this.ai) {
                this.ar = R.drawable.leftmenu_land_icon;
                this.O.setImageResource(R.drawable.leftmenu_land_icon);
            } else {
                this.ar = R.drawable.leftmenu_takeoff_icon;
                this.O.setImageResource(R.drawable.leftmenu_takeoff_icon);
            }
            this.O.setEnabled(true);
            f();
        }
    }

    private void d() {
        FLIGHT_ACTION flightAction = DataOsdGetPushCommon.getInstance().getFlightAction();
        FLYC_STATE flyc_state = this.aj;
        this.aj = DataOsdGetPushCommon.getInstance().getFlycState();
        a(flyc_state, this.aj, flightAction);
        b(flyc_state, this.aj, flightAction);
        a(this.aj);
    }

    private void a(FLYC_STATE flyc_state) {
        if (flyc_state == FLYC_STATE.TRIPOD_GPS) {
            a(32, true);
        } else {
            a(32, false);
        }
    }

    private boolean a(FLIGHT_ACTION flight_action) {
        return FLIGHT_ACTION.MC_PROTECT_GOHOME == flight_action;
    }

    private boolean b(FLIGHT_ACTION flight_action) {
        return FLIGHT_ACTION.WARNING_POWER_LANDING == flight_action || FLIGHT_ACTION.SMART_POWER_LANDING == flight_action || FLIGHT_ACTION.LOW_VOLTAGE_LANDING == flight_action || FLIGHT_ACTION.SERIOUS_LOW_VOLTAGE_LANDING == flight_action || FLIGHT_ACTION.AIRPORT_AVOID_LANDING == flight_action || FLIGHT_ACTION.BATTERY_FORCE_LANDING == flight_action;
    }

    private void e() {
        this.O.setBackgroundResource(R.drawable.selector_fpv_cgh);
        a(2, true);
    }

    private void f() {
        if (dji.pilot.fpv.d.b.d(null)) {
            this.O.setBackgroundColor(getResources().getColor(R.color.j5));
        } else {
            this.O.setBackgroundResource(R.drawable.selector_fpv_circle_bg);
        }
        a(2, false);
    }

    private void a(View view, int i, int i2) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.topMargin = i;
        marginLayoutParams.bottomMargin = i2;
        view.setLayoutParams(marginLayoutParams);
    }

    private void g() {
        int childCount = getChildCount();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            if (getChildAt(i2).getVisibility() != 8) {
                i++;
            }
        }
        if (i >= 4) {
            a(this.N, 0, this.av);
            a(this.T, this.av, this.av);
            a(this.Q, this.av, this.av);
            a(this.U, this.av, this.av);
            a(this.W, this.av, this.av);
            a(this.aa, this.av, 0);
            return;
        }
        a(this.N, 0, this.aw);
        a(this.T, this.aw, this.aw);
        a(this.Q, this.aw, this.aw);
        a(this.U, this.aw, this.aw);
        a(this.W, this.aw, this.aw);
        a(this.aa, this.aw, 0);
    }

    private void h() {
        this.G.put(1, 7);
        this.G.put(2, 1);
        this.G.put(4, 4);
        this.G.put(8, 20);
        this.G.put(16, 7);
        this.G.put(32, 21);
    }

    private void a(int i, int i2) {
        this.G.put(i2, this.G.get(i2) & (i ^ -1));
    }

    private void b(int i) {
        int size = this.G.size();
        for (int i2 = 0; i2 < size; i2++) {
            a(64, this.G.keyAt(i2));
        }
    }

    private void b(int i, int i2) {
        this.G.put(i2, this.G.get(i2) | i);
    }

    private void c(int i) {
        int size = this.G.size();
        for (int i2 = 0; i2 < size; i2++) {
            b(64, this.G.keyAt(i2));
        }
    }

    private void a(dji.publics.d.c cVar, int i) {
        if (cVar != this.T || DataGimbalGetPushParams.getInstance().isGetted()) {
            if ((this.G.get(d(this.H)) & i) != 0) {
                cVar.show();
                g();
            }
        }
    }

    private void b(dji.publics.d.c cVar, int i) {
        cVar.go();
        g();
        if (i == 64) {
            dji.thirdparty.a.c.a().e(dji.pilot.fpv.model.n.a.f);
        }
    }

    private void a(dji.publics.d.c cVar, boolean z) {
        if (z) {
            cVar.show();
        } else {
            cVar.go();
        }
    }

    private int d(int i) {
        if ((i & 2) != 0) {
            return 2;
        }
        if ((i & 4) != 0) {
            return 4;
        }
        if ((i & 8) != 0) {
            return 8;
        }
        if ((i & 16) != 0) {
            return 16;
        }
        if ((i & 32) != 0) {
            return 32;
        }
        return 1;
    }

    private void c(int i, int i2) {
        boolean z;
        boolean z2 = true;
        int d = d(i2);
        int d2 = d(i);
        int i3 = this.G.get(d);
        a(this.N, (i3 & 1) != 0);
        dji.publics.d.c cVar = this.T;
        if ((i3 & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        a(cVar, z);
        cVar = this.Q;
        if ((i3 & 4) != 0) {
            z = true;
        } else {
            z = false;
        }
        a(cVar, z);
        cVar = this.W;
        if ((i3 & 8) != 0) {
            z = true;
        } else {
            z = false;
        }
        a(cVar, z);
        cVar = this.aa;
        if ((i3 & 16) != 0) {
            z = true;
        } else {
            z = false;
        }
        a(cVar, z);
        cVar = this.U;
        if ((i3 & 32) != 0) {
            z = true;
        } else {
            z = false;
        }
        a(cVar, z);
        dji.publics.d.c cVar2 = this.ab;
        if ((i3 & 64) == 0) {
            z2 = false;
        }
        a(cVar2, z2);
        g();
        if (d != d2) {
            startAnimation(this.ae);
        }
    }

    private void a(int i, boolean z) {
        int i2 = this.H;
        if (z) {
            this.H |= i;
        } else {
            this.H &= i ^ -1;
        }
        if (i2 != this.H) {
            c(i2, this.H);
        }
    }

    public void onEventMainThread(ProductType productType) {
        if (this.ak != productType && productType != null) {
            this.ak = productType;
            i();
            j();
        }
    }

    private void i() {
        DJILogHelper.getInstance().LOGD("", "Product[" + this.ak + dji.pilot.usercenter.protocol.d.H, false, true);
        if (dji.pilot.fpv.d.b.i(this.ak)) {
            b(32, 1);
            b(32, 16);
            a(4, 1);
            a(4, 16);
            a(1, 1);
            a(1, 16);
            a(this.U, 32);
            b(this.Q, 4);
            b(this.N, 1);
        } else {
            a(32, 1);
            a(32, 16);
            b(4, 1);
            b(4, 16);
            b(1, 1);
            b(1, 16);
            a(this.Q, 4);
            a(this.N, 1);
            b(this.U, 32);
        }
        g();
    }

    private void j() {
        if (this.ak.isCompleteMachine()) {
            if (dji.pilot.fpv.d.b.k(this.ak)) {
                a(2, 1);
                a(2, 16);
                b(this.T, 2);
            } else if (dji.pilot.fpv.d.b.b(this.ak)) {
                b(2, 1);
                b(2, 16);
                a(this.T, 2);
            } else if (dji.pilot.fpv.d.b.f(this.ak)) {
                k();
            } else {
                b(2, 1);
                b(2, 16);
                a(this.T, 2);
            }
        } else if (dji.pilot.fpv.d.b.i(this.ak)) {
            b(2, 1);
            b(2, 16);
            a(this.T, 2);
        } else {
            k();
        }
        g();
    }

    private void k() {
        if (this.am == DJIGimbalType.Z15 || this.am == DJIGimbalType.D5 || this.am == DJIGimbalType.A7 || this.am == DJIGimbalType.GH4 || this.am == DJIGimbalType.BMPCC) {
            b(2, 1);
            b(2, 16);
            a(this.T, 2);
            return;
        }
        a(2, 1);
        a(2, 16);
        b(this.T, 2);
    }

    public void onEventMainThread(DataGimbalGetPushType dataGimbalGetPushType) {
        DJIGimbalType type = dataGimbalGetPushType.getType();
        if (this.am != type) {
            this.am = type;
            j();
        }
    }

    public void onEventMainThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        boolean isPushLosed = DataGimbalGetPushParams.getInstance().isPushLosed();
        if (isPushLosed != this.aC) {
            this.aC = isPushLosed;
            if (this.aC) {
                a(2, 1);
                a(2, 16);
                b(this.T, 2);
            } else {
                j();
            }
        }
        this.T.handlePushGimbal();
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        int flycVersion = dataOsdGetPushCommon.getFlycVersion();
        if (this.an != flycVersion) {
            this.an = flycVersion;
            if (dji.pilot.fpv.d.b.j()) {
                a(d.b, this.an, this.ap, this.ao);
            }
        }
        if (dji.pilot.fpv.d.b.j()) {
            RcModeChannel modeChannel = dataOsdGetPushCommon.getModeChannel();
            if (this.ao != modeChannel) {
                a(d.b, this.an, this.ap, modeChannel);
            }
        }
        c();
        d();
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
        if (dji.pilot.fpv.d.b.j()) {
            boolean a = dji.pilot.fpv.d.b.a(dataOsdGetPushHome.isBeginnerMode(), dataOsdGetPushHome.isMultipleModeOpen());
            if (this.ap != a) {
                a(d.b, this.an, a, this.ao);
            }
        }
    }

    private void a(DataRcSetMaster.MODE mode, int i, boolean z, RcModeChannel rcModeChannel) {
        boolean z2 = true;
        this.ao = rcModeChannel;
        this.ap = z;
        this.an = i;
        DJILogHelper.getInstance().LOGD("LeftMenu", "RcMode[" + mode + com.alipay.sdk.j.i.b + i.getInstance().c() + com.alipay.sdk.j.i.b + z + com.alipay.sdk.j.i.b + rcModeChannel + dji.pilot.usercenter.protocol.d.H, false, true);
        if ((mode == null || mode != DataRcSetMaster.MODE.b) && !dji.pilot.fpv.d.b.a(i.getInstance().c(), z, rcModeChannel)) {
            b(8, 1);
            b(8, 16);
            if (this.W.getVisibility() != 8) {
                z2 = false;
            }
            a(this.W, 8);
        } else {
            a(8, 1);
            a(8, 16);
            if (this.W.getVisibility() != 0) {
                z2 = false;
            }
            b(this.W, 8);
        }
        if (z2) {
            startAnimation(this.ae);
        }
    }

    public void onEventMainThread(DataA2PushCommom dataA2PushCommom) {
        if (i.getInstance().c() == ProductType.A2) {
            int f = dataA2PushCommom.f();
            if (this.aD != f) {
                this.aD = f;
                a(d.b, this.an, this.ap, this.ao);
            }
        }
    }

    public void onEventMainThread(DataEyeGetPushException dataEyeGetPushException) {
        if (dji.pilot.visual.a.c.getInstance().l() && !this.ah.hasMessages(4099)) {
            this.ah.sendEmptyMessageDelayed(4099, 200);
        }
    }

    private void l() {
        DataEyeGetPushException instance = DataEyeGetPushException.getInstance();
        if (dji.pilot.visual.a.c.getInstance().l() && instance != null && instance.isGetted()) {
            boolean z = instance.isInTracking() || instance.isInTapFly();
            a(8, z);
        }
    }

    public void onEventMainThread(g$f dji_pilot_visual_a_g_f) {
        if (dji.pilot.visual.a.c.getInstance().l()) {
            l();
        } else {
            a(8, false);
        }
    }

    public void onEventMainThread(dji.pilot.groundStation.a.a.d dVar) {
        a(16, dVar != dji.pilot.groundStation.a.a.d.NONE);
        if (dji.pilot.fpv.flightmode.c.getInstance().a() == c$b.SMART) {
            a(dVar);
        }
    }

    public void onEventMainThread(e eVar) {
        if (eVar != null && !dji.pilot.fpv.d.b.d(null) && eVar.s == 16 && (eVar.t instanceof Integer)) {
            Integer num = (Integer) eVar.t;
            if (num.intValue() > 0) {
                this.W.setImageResource(num.intValue());
            }
        }
    }

    private void a(dji.pilot.groundStation.a.a.d dVar) {
        if (!dji.pilot.fpv.d.b.d(null)) {
            if (dVar == dji.pilot.groundStation.a.a.d.COURSE_LOCK) {
                this.W.setImageResource(R.drawable.mini_course_lock);
            } else if (dVar == dji.pilot.groundStation.a.a.d.HOME_LOCK) {
                this.W.setImageResource(R.drawable.mini_home_lock);
            } else if (dVar == dji.pilot.groundStation.a.a.d.FOLLOW_ME) {
                this.W.setImageResource(R.drawable.mini_followme);
            } else if (dVar == dji.pilot.groundStation.a.a.d.POI_AUTO) {
                this.W.setImageResource(R.drawable.mini_point_of_interest);
            } else if (dVar == dji.pilot.groundStation.a.a.d.WP_AUTO) {
                this.W.setImageResource(R.drawable.mini_waypoint);
            } else if (dVar == dji.pilot.groundStation.a.a.d.TERRAIN_TRACKING) {
                this.W.setImageResource(R.drawable.mini_terrain_tracking);
            } else {
                this.W.setImageResource(R.drawable.mini_smart);
            }
        }
    }

    private void a(c$d dji_pilot_fpv_flightmode_c_d) {
        if (!dji.pilot.fpv.d.b.d(null)) {
            if (dji_pilot_fpv_flightmode_c_d == c$d.COURSE_LOCK) {
                this.W.setImageResource(R.drawable.mini_course_lock);
            } else if (dji_pilot_fpv_flightmode_c_d == c$d.HOME_LOCK) {
                this.W.setImageResource(R.drawable.mini_home_lock);
            } else if (dji_pilot_fpv_flightmode_c_d == c$d.FOLLOW_ME) {
                this.W.setImageResource(R.drawable.mini_followme);
            } else if (dji_pilot_fpv_flightmode_c_d == c$d.POI_AUTO) {
                this.W.setImageResource(R.drawable.mini_point_of_interest);
            } else if (dji_pilot_fpv_flightmode_c_d == c$d.WP_AUTO) {
                this.W.setImageResource(R.drawable.mini_waypoint);
            } else if (dji_pilot_fpv_flightmode_c_d == c$d.TERRAIN_TRACKING) {
                this.W.setImageResource(R.drawable.mini_terrain_tracking);
            } else if (dji_pilot_fpv_flightmode_c_d == c$d.TRIPOD) {
                this.W.setImageResource(R.drawable.mini_tripod);
            } else {
                this.W.setImageResource(R.drawable.mini_smart);
            }
        }
    }

    public void onEventMainThread(c$b dji_pilot_fpv_flightmode_c_b) {
        if (!dji.pilot.fpv.d.b.d(null)) {
            if (dji_pilot_fpv_flightmode_c_b == c$b.TRACK) {
                this.W.setImageResource(R.drawable.mini_track);
            } else if (dji_pilot_fpv_flightmode_c_b == c$b.TRACK_SELFIE) {
                this.W.setImageResource(R.drawable.mini_selfie);
            } else if (dji_pilot_fpv_flightmode_c_b == c$b.NORMAL) {
                this.W.setImageResource(R.drawable.mini_normal);
                if (dji.logic.c.b.getInstance().a(null)) {
                    this.W.setImageResource(R.drawable.flight_mode_wifi_joystick_normal);
                    dji.thirdparty.a.c.a().e(dji.pilot.fpv.model.n.a.e);
                }
            } else if (dji_pilot_fpv_flightmode_c_b == c$b.POINT) {
                this.W.setImageResource(R.drawable.mini_point);
            } else if (dji_pilot_fpv_flightmode_c_b == c$b.SMART) {
                onEventMainThread(dji.pilot.fpv.flightmode.c.getInstance().c());
                a(dji.pilot.groundStation.a.a.getInstance(null).z());
            } else if (dji_pilot_fpv_flightmode_c_b == c$b.JOYSTICK) {
                this.W.setImageResource(R.drawable.flight_mode_wifi_joystick_normal);
            }
        }
    }

    public void onEventMainThread(c$d dji_pilot_fpv_flightmode_c_d) {
        if (dji.pilot.fpv.flightmode.c.getInstance().a() == c$b.SMART) {
            a(dji_pilot_fpv_flightmode_c_d);
        }
    }

    public void onEventMainThread(dji.setting.ui.rc.RcMasterSlaveView.c cVar) {
        if (cVar != null) {
            a(d.b);
        }
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.a) {
            r();
            a(null);
            this.an = 0;
            this.ao = RcModeChannel.CHANNEL_UNKNOWN;
            this.ap = false;
            b(this.W, 8);
        }
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.a || oVar != o.b) {
        }
    }

    public void onEventMainThread(dji.logic.c.b.c cVar) {
    }

    private void a(DataRcSetMaster.MODE mode) {
        if (mode == null || mode != DataRcSetMaster.MODE.b) {
            this.O.setEnabled(true);
            this.R.setEnabled(this.ai);
            this.U.setViewEnable(true);
        } else {
            this.O.setEnabled(false);
            this.R.setEnabled(false);
            this.U.setViewEnable(false);
        }
        if (dji.pilot.fpv.d.b.j()) {
            a(mode, this.an, this.ap, this.ao);
        }
    }

    public void onEventMainThread(dji.pilot.fpv.control.q.a aVar) {
        switch (aVar) {
            case SMALL:
                j();
                a(this.T, 2);
                break;
            case SMALL_HIDE:
                j();
                a(this.T, 2);
                break;
            case LARGE:
                a(2, 1);
                a(2, 16);
                b(this.T, 2);
                break;
        }
        g();
    }

    public void onEventMainThread(dji.gs.views.EventView.a aVar) {
        switch (AnonymousClass9.b[aVar.ordinal()]) {
        }
    }

    private void getMutexRect() {
        this.az.setEmpty();
        this.ay.setEmpty();
        if (this.aB != null) {
            this.aB.getGlobalVisibleRect(this.ay, null);
            for (int childCount = this.aB.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = this.aB.getChildAt(childCount);
                if (childAt.getVisibility() == 0) {
                    childAt.getGlobalVisibleRect(this.az, null);
                    this.az.set(this.ay.left, this.ay.top, this.az.right, this.az.bottom);
                    return;
                }
            }
        }
    }

    public void onEventMainThread(DJIErrorPopView$e dJIErrorPopView$e) {
        if (this.aB == null) {
            return;
        }
        if (this.P.getVisibility() == 0 || this.S.getVisibility() == 0) {
            getMutexRect();
            this.aA.setEmpty();
            if (this.P.getVisibility() == 0) {
                this.P.getGlobalVisibleRect(this.aA, null);
                if (this.az.intersects(this.aA.left, this.aA.top, this.aA.right, this.aA.bottom)) {
                    this.P.hide();
                    this.ah.removeMessages(4098);
                }
            }
            DJILogHelper.getInstance().LOGD("", "===Mutex[" + this.az.flattenToString() + "]TO[" + this.aA.flattenToString() + dji.pilot.usercenter.protocol.d.H, false, true);
            this.aA.setEmpty();
            if (this.S.getVisibility() == 0) {
                this.S.getGlobalVisibleRect(this.aA, null);
                if (this.az.intersects(this.aA.left, this.aA.top, this.aA.right, this.aA.bottom)) {
                    this.S.hide();
                    this.ah.removeMessages(4097);
                }
            }
            DJILogHelper.getInstance().LOGD("", "===Mutex[" + this.az.flattenToString() + "]GH[" + this.aA.flattenToString() + dji.pilot.usercenter.protocol.d.H, false, true);
        }
    }

    private void e(int i) {
        if (this.ac == null) {
            this.ac = new b(this.M);
            this.ac.a(new dji.pilot.fpv.leftmenu.b.a(this) {
                final /* synthetic */ DJILeftMenu a;

                {
                    this.a = r1;
                }

                public void a(DialogInterface dialogInterface, int i) {
                    this.a.g(i);
                }

                public void b(DialogInterface dialogInterface, int i) {
                    this.a.f(i);
                }

                public void a(DialogInterface dialogInterface, boolean z, int i) {
                    this.a.a(z);
                }
            });
            this.ac.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ DJILeftMenu a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    this.a.ad = 0;
                }
            });
        }
        if (this.ac != null && !this.ac.isShowing()) {
            this.ad = i;
            if (i == 1) {
                m();
            } else if (i == 2) {
                n();
            }
            this.ac.show();
        }
    }

    private void m() {
        String string;
        String string2;
        if (this.ai) {
            this.ac.a(1);
            this.ac.b((int) R.drawable.leftmenu_dlg_landing);
            string = this.M.getString(R.string.fpv_leftmenu_land);
            string2 = this.M.getString(R.string.fpv_leftmenu_land_desc);
            this.ac.a(8, 0);
            this.ac.e(8);
            this.ac.d(0);
        } else {
            int[] a = dji.pilot.fpv.d.b.a(DataOsdGetPushCommon.getInstance().getFlycState(), DataOsdGetPushCommon.getInstance().isVisionUsed(), false);
            float b;
            if (a[0] == R.string.ctrl_mode_atti || a[0] == R.string.ctrl_mode_patti) {
                this.ac.a(1);
                this.ac.b((int) R.drawable.leftmenu_dlg_takeoff);
                if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    b = DJIGenSettingDataManager.getInstance().b(I);
                    string = this.M.getString(R.string.fpv_imperial, new Object[]{Float.valueOf(b)});
                } else {
                    string = this.M.getString(R.string.fpv_metric, new Object[]{Float.valueOf(I)});
                }
                string = this.M.getString(R.string.takeoff_inatti, new Object[]{string});
            } else {
                this.ac.a(1);
                this.ac.b((int) R.drawable.leftmenu_dlg_takeoff);
                if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    b = DJIGenSettingDataManager.getInstance().b(I);
                    string = this.M.getString(R.string.fpv_imperial, new Object[]{Float.valueOf(b)});
                } else {
                    string = this.M.getString(R.string.fpv_metric, new Object[]{Float.valueOf(I)});
                }
                string = this.M.getString(R.string.fpv_leftmenu_takeoff_desc, new Object[]{string});
            }
            string2 = this.M.getString(R.string.fpv_leftmenu_takeoff);
            this.ac.b(string);
            this.ac.a(8, 0);
            this.ac.e(0);
            this.ac.e(this.M.getString(R.string.fpv_leftmenu_takeoff_switch));
            this.ac.d(8);
            String str = string2;
            string2 = string;
            string = str;
        }
        this.ac.a(string);
        this.ac.b(string2);
    }

    private void n() {
        if (R.drawable.leftmenu_gohome_cancel_icon == this.aq) {
            this.ac.a(1);
            this.ac.b((int) R.drawable.leftmenu_dlg_cancel);
            this.ac.a(this.M.getString(R.string.fpv_leftmenu_cancel_gohome));
            this.ac.b(this.M.getString(R.string.fpv_leftmenu_cancel_gohome_desc));
            this.ac.a(8, 0);
            this.ac.e(8);
            this.ac.d(0);
            return;
        }
        this.ac.a(1);
        this.ac.b((int) R.drawable.leftmenu_dlg_gohome);
        this.ac.a(this.M.getString(R.string.fpv_leftmenu_gohome));
        float goHomeHeight = (float) DataOsdGetPushHome.getInstance().getGoHomeHeight();
        float height = ((float) DataOsdGetPushCommon.getInstance().getHeight()) * 0.1f;
        float[] fArr = new float[1];
        Location.distanceBetween(DataOsdGetPushHome.getInstance().getLatitude(), DataOsdGetPushHome.getInstance().getLongitude(), DataOsdGetPushCommon.getInstance().getLatitude(), DataOsdGetPushCommon.getInstance().getLongitude(), fArr);
        if (fArr[0] < 20.0f) {
            this.ac.b(this.M.getString(R.string.fpv_leftmenu_gohome_inner_desc));
        } else if (20.0f > goHomeHeight || goHomeHeight > DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxPosition || height >= goHomeHeight) {
            this.ac.b(this.M.getString(R.string.fpv_leftmenu_gohome_above_desc, new Object[]{Float.valueOf(height), Float.valueOf(dji.pilot.publics.e.e.f(height))}));
        } else {
            this.ac.b(this.M.getString(R.string.fpv_leftmenu_gohome_below_desc, new Object[]{Float.valueOf(goHomeHeight), Float.valueOf(dji.pilot.publics.e.e.f(goHomeHeight))}));
        }
        this.ac.a(8, 0);
        this.ac.e(0);
        this.ac.e(this.M.getString(R.string.fpv_leftmenu_gohome_switch));
        this.ac.d(8);
    }

    private void o() {
        if (this.ac != null && this.ac.isShowing()) {
            this.ac.dismiss();
        }
    }

    private void f(int i) {
        o();
        FLYC_COMMAND flyc_command;
        if (2 == this.ad) {
            if (R.drawable.leftmenu_gohome_cancel_icon == this.aq) {
                flyc_command = FLYC_COMMAND.DropGohome;
            } else {
                flyc_command = FLYC_COMMAND.GOHOME;
            }
            a(flyc_command);
        } else if (1 == this.ad && this.ai) {
            flyc_command = FLYC_COMMAND.AUTO_LANDING;
            if (DataOsdGetPushCommon.getInstance().getFlycVersion() >= 13 && DataFlycGetPushAvoidParam.getInstance().avoidGroundForceLanding()) {
                flyc_command = FLYC_COMMAND.ForceLanding;
            }
            a(flyc_command);
        }
    }

    private void g(int i) {
        o();
        if (2 == this.ad || 1 != this.ad) {
        }
    }

    private void a(boolean z) {
        if (!z) {
            return;
        }
        if (1 == this.ad) {
            a(FLYC_COMMAND.AUTO_FLY);
            o();
        } else if (2 == this.ad) {
            a(FLYC_COMMAND.GOHOME);
            o();
        }
    }

    private void a(FLYC_COMMAND flyc_command, boolean z) {
        dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
        if (z) {
            if (FLYC_COMMAND.AUTO_FLY == flyc_command) {
                bVar.b = R.string.fpv_leftmenu_takeoff_success;
            } else if (flyc_command == FLYC_COMMAND.AUTO_LANDING || flyc_command == FLYC_COMMAND.ForceLanding) {
                bVar.b = R.string.fpv_leftmenu_land_success;
            } else if (flyc_command == FLYC_COMMAND.GOHOME) {
                bVar.b = R.string.fpv_leftmenu_gohome_success;
            } else if (flyc_command == FLYC_COMMAND.DropGohome) {
                bVar.b = R.string.fpv_leftmenu_cancel_gohome_success;
            } else if (flyc_command == FLYC_COMMAND.DropLanding) {
                bVar.b = R.string.fpv_leftmenu_drop_land_success;
            }
            bVar.a = DJIErrorPopView.d.a;
        } else {
            if (FLYC_COMMAND.AUTO_FLY == flyc_command) {
                bVar.b = R.string.fpv_leftmenu_takeoff_failed;
            } else if (flyc_command == FLYC_COMMAND.AUTO_LANDING || flyc_command == FLYC_COMMAND.ForceLanding) {
                bVar.b = R.string.fpv_leftmenu_land_failed;
            } else if (flyc_command == FLYC_COMMAND.GOHOME) {
                bVar.b = R.string.fpv_leftmenu_gohome_failed;
            } else if (flyc_command == FLYC_COMMAND.DropGohome) {
                bVar.b = R.string.fpv_leftmenu_cancel_gohome_failed;
            } else if (flyc_command == FLYC_COMMAND.DropLanding) {
                bVar.b = R.string.fpv_leftmenu_drop_land_failed;
            }
            bVar.a = DJIErrorPopView.d.b;
        }
        if (bVar.b != 0) {
            dji.thirdparty.a.c.a().e(bVar);
        }
    }

    private void a(final FLYC_COMMAND flyc_command) {
        DataFlycFunctionControl.getInstance().setCommand(flyc_command).start(new dji.midware.e.d(this) {
            final /* synthetic */ DJILeftMenu b;

            public void onSuccess(Object obj) {
                if (FLYC_COMMAND.DropGohome == flyc_command) {
                    dji.thirdparty.a.c.a().e(a.DropGoHome);
                } else if (FLYC_COMMAND.DropLanding == flyc_command) {
                    dji.thirdparty.a.c.a().e(a.DropLand);
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.a(flyc_command, false);
            }
        });
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getX() < ((float) (this.ax + this.av))) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void onClick(View view) {
        int id = view.getId();
        this.T.hideMenu(false);
        if (id == R.id.a4q) {
            dji.pilot.fpv.d.e.a("FPV_LeftBarView_Button_TakeOff");
            if (this.ar == R.drawable.leftmenu_gohome_cancel_icon) {
                o();
                a(FLYC_COMMAND.DropLanding);
                return;
            }
            e(1);
        } else if (id == R.id.a4u) {
            dji.pilot.fpv.d.e.a("FPV_LeftBarView_Button_ReturnHome");
            if (p() && R.drawable.leftmenu_gohome_icon == this.aq) {
                q();
            } else {
                e(2);
            }
        } else if (id == R.id.a4y) {
            dji.thirdparty.a.c.a().e(dji.pilot.fpv.model.n.a.a);
            dji.pilot.visual.beginner.a.getInstance().d();
        } else if (id == R.id.a4x) {
            if (dji.logic.c.b.getInstance().a(null)) {
                dji.thirdparty.a.c.a().e(f.i.SHOW);
            } else {
                dji.pilot.dji_groundstation.controller.e.a("gs://flightmode/main", getContext());
            }
        } else if (id == R.id.a4z) {
            if (this.as == this.aE) {
                dji.thirdparty.a.c.a().e(dji.pilot.fpv.model.n.a.e);
            } else {
                dji.thirdparty.a.c.a().e(dji.pilot.fpv.model.n.a.f);
            }
            setJsImageResource(this.as == this.aE ? this.aF : this.aE);
        }
    }

    private void setJsImageResource(int i) {
        if (i != this.as) {
            this.as = i;
            this.ab.setImageResource(this.as);
        }
    }

    private boolean p() {
        dji.gs.e.b bVar = (this.aH == null || !this.aH.a()) ? k.c : this.aH;
        if (bVar == null || !bVar.a()) {
            DJILogHelper.getInstance().LOGD("", "====[distance]====none avaliable", false, true);
            return false;
        }
        dji.gs.e.b j = k.j();
        if (j.a()) {
            float[] fArr = new float[1];
            Location.distanceBetween(bVar.b, bVar.c, j.b, j.c, fArr);
            double d = (double) fArr[0];
            DJILogHelper.getInstance().LOGD("", "====[distance]====" + ((int) d), false, true);
            if (d > 100.0d) {
                return true;
            }
            return false;
        }
        DJILogHelper.getInstance().LOGD("", "====[distance]====home error!!!!!!", false, true);
        return true;
    }

    private void q() {
        a aVar = new a(getContext());
        aVar.a(new dji.pilot.fpv.leftmenu.a.a(this) {
            final /* synthetic */ DJILeftMenu a;

            {
                this.a = r1;
            }

            public void a(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
                this.a.e(2);
            }

            public void b(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
                new dji.pilot.fpv.control.p(this.a.getContext()).a(new dji.pilot.fpv.control.p.a(this) {
                    final /* synthetic */ AnonymousClass5 a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                        this.a.a.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.a.e(2);
                            }
                        });
                    }

                    public void b() {
                    }
                });
            }

            public void c(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        });
        aVar.show();
    }

    public void handleFMClicked() {
        if (dji.pilot.fpv.flightmode.c.getInstance().b(c$b.SMART)) {
            if (this.aG == null) {
                this.aG = new dji.pilot.fpv.flightmode.a(getContext());
                this.aG.a(getResources().getString(R.string.fpv_flight_mode_disclaimer_title));
                this.aG.b(getResources().getString(R.string.fpv_flight_mode_vision_disclaimer)).a(new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ DJILeftMenu a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.r();
                    }
                }).b(new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ DJILeftMenu a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.r();
                        dji.pilot.fpv.flightmode.c.getInstance().c(c$b.SMART);
                        dji.pilot.groundStation.a.a.getInstance(null).b(true);
                    }
                });
                this.aG.setOnShowListener(new OnShowListener(this) {
                    final /* synthetic */ DJILeftMenu a;

                    {
                        this.a = r1;
                    }

                    public void onShow(DialogInterface dialogInterface) {
                        this.a.postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass8 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                dji.thirdparty.a.c.a().e(n$b.HIDE_VIEWS_EXCEPT_TOP);
                            }
                        }, 50);
                    }
                });
            }
            this.aG.show();
            return;
        }
        dji.pilot.groundStation.a.a.getInstance(null).b(true);
    }

    private void r() {
        if (this.aG != null && this.aG.isShowing()) {
            this.aG.dismiss();
            dji.thirdparty.a.c.a().e(n$b.SHOW_VIEWS_EXCEPT_TOP);
        }
        this.aG = null;
    }

    public void onEventBackgroundThread(DataRcGetPushGpsInfo dataRcGetPushGpsInfo) {
        dji.gs.e.b bVar = new dji.gs.e.b(dataRcGetPushGpsInfo.getLatitude(), dataRcGetPushGpsInfo.getLongitude());
        if (bVar.a() && !bVar.a(this.aH)) {
            this.aH = bVar;
        }
    }

    public void onEventBackgroundThread(DataEyeGetPushFlatCheck dataEyeGetPushFlatCheck) {
        if (dataEyeGetPushFlatCheck.getFlatStatus() == FlatStatus.UnsafeToHover) {
            this.O.setEnabled(false);
            this.O.setAlpha(dji.pilot.visual.a.d.c);
            return;
        }
        this.O.setEnabled(true);
        this.O.setAlpha(1.0f);
    }
}
