package dji.pilot.fpv.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.gs.e.b;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycGetIoc.MODE;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataRcGetPushGpsInfo;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.DJIGenSettingDataManager$c;
import dji.pilot.fpv.control.k;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.sport.DJISportAttitudeView;
import dji.pilot.groundStation.a.a.d;
import dji.pilot.publics.e.f;
import dji.pilot.visual.a.g$f;
import dji.pilot.visual.view.DJIVisualObstacleRadarView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;
import lecho.lib.hellocharts.model.l;

public class DJIAttitudeView extends DJILinearLayout implements s {
    private static final boolean a = false;
    private static final String aK = "key_center_type";
    private static final int aL = 0;
    private static final int aM = 1;
    private static final boolean b = false;
    private static final int c = 4096;
    private static final long d = 200;
    private static final int e = 8192;
    private static final long f = 200;
    private static final int g = 36864;
    private static final long h = 500;
    private static final int i = 12288;
    private static final long j = 100;
    private static final String k = "999.9KM/H";
    private static int l = 0;
    private static final String m = "9999.9FT";
    private static int n = 0;
    private DJILinearLayout A = null;
    private DJILinearLayout B = null;
    private DJIRelativeLayout C = null;
    private DJITextView D = null;
    private DJITextView E = null;
    private DJITextView F = null;
    private DJITextView G = null;
    private DJIImageView H = null;
    private DJIImageView I = null;
    private DJITextView J = null;
    private ProgressBar K = null;
    private DJIImageView L = null;
    private DJIImageView M = null;
    private DJIVisualObstacleRadarView N = null;
    private DJIGimbalYawView O = null;
    private int P = 0;
    private Display Q = null;
    private final float[] R = new float[1];
    private DataOsdGetPushCommon S = null;
    private DataOsdGetPushHome T = null;
    private DataGimbalGetPushParams U = null;
    private DataRcGetPushGpsInfo V = null;
    private DJIGenSettingDataManager W = null;
    private int aA = 0;
    private MODE aB = MODE.OTHER;
    private int aC = 0;
    private int aD = 0;
    private int aE = 0;
    private boolean aF = false;
    private Drawable aG = null;
    private Drawable aH = null;
    private Drawable aI = null;
    private int aJ = 0;
    private double aN = 0.0d;
    private double aO = 0.0d;
    private double aP = 0.0d;
    private double aQ = 0.0d;
    private double aR = 0.0d;
    private double aS = 0.0d;
    private int aT = 1;
    private boolean aU = false;
    private boolean aV = false;
    private float aW = 0.0f;
    private float aX = 0.0f;
    private float aY = 0.0f;
    private float aZ = 0.0f;
    private DJIGenSettingDataManager$c aa = null;
    private boolean ab = false;
    private boolean ac = true;
    private Animation ad = null;
    private Animation ae = null;
    private Context af = null;
    private a ag = null;
    private int ah = 0;
    private boolean ai = false;
    private boolean aj = false;
    private float ak = 0.0f;
    private int al = 0;
    private int am = 0;
    private int an = 0;
    private float ao = 0.0f;
    private float ap = 0.0f;
    private float aq = 0.0f;
    private float ar = 0.0f;
    private boolean as = false;
    private float at = 0.0f;
    private boolean au = false;
    private float av = 0.0f;
    private float aw = 0.0f;
    private float ax = 0.0f;
    private float ay = 0.0f;
    private boolean az = false;
    private final float[] ba = new float[2];
    private ProductType bb = ProductType.Orange;
    private int bc = 0;
    private int bd = 0;
    private int be = 0;
    private int bf = 0;
    private int bg = 0;
    private int bh = 0;
    private b bi = new b(22.530546958202116d, 113.93412908363342d);
    private b bj = new b(22.530546958202116d, 113.93412908363342d);
    private b bk = new b(22.530546958202116d, 113.93412908363342d);
    private boolean bl = false;
    private final int o = 400;
    private final int p = 2000;
    private final float q = l.n;
    private final float r = 0.0f;
    private FrameLayout s = null;
    private DJIRelativeLayout t = null;
    private DJIImageView u = null;
    private DJIImageView v = null;
    private DJIImageView w = null;
    private DJIImageView x = null;
    private DJIImageView y = null;
    private DJISportAttitudeView z = null;

    private static final class a extends Handler {
        private final WeakReference<DJIAttitudeView> a;

        public a(DJIAttitudeView dJIAttitudeView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJIAttitudeView);
        }

        public void handleMessage(Message message) {
            DJIAttitudeView dJIAttitudeView = (DJIAttitudeView) this.a.get();
            if (dJIAttitudeView != null) {
                switch (message.what) {
                    case 4096:
                        if (dJIAttitudeView.ai) {
                            dJIAttitudeView.a(false);
                            dJIAttitudeView.a(dJIAttitudeView.aB, dJIAttitudeView.aA);
                            return;
                        }
                        return;
                    case 8192:
                        dJIAttitudeView.a((b) message.obj);
                        return;
                    case 12288:
                        if (ServiceManager.getInstance().isRemoteOK()) {
                            dJIAttitudeView.update(true);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJIAttitudeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.af = context;
        if (!isInEditMode()) {
            c();
        }
    }

    public void show() {
        if (!isShown() && !dji.logic.c.b.getInstance().a(null)) {
            showSpeedLy();
            super.show();
            clearAnimation();
            startAnimation(this.ad);
        }
    }

    public void hide() {
        if (isShown()) {
            super.hide();
            clearAnimation();
            startAnimation(this.ae);
        }
    }

    public void go() {
        if (isShown()) {
            super.go();
            clearAnimation();
            startAnimation(this.ae);
        }
    }

    public void hideSpeedLy() {
        this.B.go();
    }

    public void showSpeedLy() {
        if (!dji.logic.c.b.getInstance().a(null)) {
            this.B.show();
        }
    }

    public boolean attiViewIsShown() {
        return this.s.isShown();
    }

    private void a() {
        DataOsdGetPushHome instance = DataOsdGetPushHome.getInstance();
        boolean a = dji.pilot.fpv.d.b.a(instance.isBeginnerMode(), instance.isMultipleModeOpen());
        RcModeChannel modeChannel = this.S.getModeChannel();
        f.a(this.s, 0);
        if (instance.isGetted() && this.S.isGetted() && this.s.getVisibility() == 0 && !dji.pilot.publics.e.a.d(null)) {
            if (a && modeChannel == RcModeChannel.CHANNEL_S) {
                this.t.go();
                this.z.show();
                this.A.setTranslationX((float) (0 - this.bg));
            } else {
                this.z.go();
                this.t.show();
                this.A.setTranslationX(0.0f);
                a(true, this.S.getGpsNum(), this.S.getLatitude(), this.S.getLongitude(), (float) this.S.getHeight(), this.S.getXSpeed(), this.S.getYSpeed(), this.S.getZSpeed(), (float) this.S.getYaw(), (float) this.S.getPitch(), (float) this.S.getRoll(), (float) this.U.getYaw(), (float) this.U.getYawAngle(), (float) this.U.getPitch(), this.S.isSwaveWork(), (float) this.S.getSwaveHeight(), this.T.getIOCMode(), this.T.getCourseLockAngle());
            }
        }
        this.s.animate().alpha(1.0f).setDuration(300).setListener(new AnimatorListener(this) {
            final /* synthetic */ DJIAttitudeView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animator animator) {
                this.a.bl = true;
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                this.a.bl = false;
            }

            public void onAnimationCancel(Animator animator) {
            }
        }).start();
        this.B.setBackgroundResource(R.drawable.osd_attitude_speed_bg);
        float f = 0.0f;
        if (this.z.getVisibility() == 0) {
            f = (float) (0 - this.bg);
        }
        this.A.animate().translationX(f).setDuration(300).start();
    }

    private void b() {
        c.a().e(dji.pilot.fpv.control.q.a.SWITCH_SHOW);
        dji.pilot.dji_groundstation.controller.a.getInstance(this.af).a(true);
        float f = (float) (this.bc - this.bd);
        if (this.z.getVisibility() == 0) {
            f = (float) (this.bc - this.be);
        }
        this.B.setBackgroundResource(R.drawable.osd_attitude_speed_rbg);
        this.A.animate().translationX(f).setDuration(300).start();
        this.s.animate().alpha(0.0f).setDuration(300).setListener(new AnimatorListener(this) {
            final /* synthetic */ DJIAttitudeView a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animator animator) {
                this.a.bl = true;
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                f.a(this.a.s, 4);
                this.a.bl = false;
            }

            public void onAnimationCancel(Animator animator) {
            }
        }).start();
    }

    public void onEventMainThread(dji.pilot.fpv.control.q.a aVar) {
        if (aVar == dji.pilot.fpv.control.q.a.SWITCH_GO && !this.aj) {
            a();
            e.c(s.dr);
        }
    }

    public void onEventMainThread(dji.pilot.groundStation.a.a.c cVar) {
        if (this.T.isGetted()) {
            a(this.T.getIOCMode(), this.T.getCourseLockAngle());
        }
    }

    public void onEventMainThread(d dVar) {
        if (this.T.isGetted()) {
            a(this.T.getIOCMode(), this.T.getCourseLockAngle());
        }
    }

    public void onEventMainThread(g$f dji_pilot_visual_a_g_f) {
        this.N.setHasVisual(dji.pilot.visual.a.c.getInstance().l());
    }

    public void onEventBackgroundThread(float[] fArr) {
        boolean z = false;
        this.aD = (int) Math.toDegrees((double) fArr[0]);
        if (this.aD - this.aC > 2 || this.aC - this.aD > 2) {
            z = true;
        }
        this.aF = z;
        if (this.aF) {
            this.aC = this.aD;
            this.aE = getDisplayRotation();
            if (this.aE == 3) {
                this.aD += 180;
            }
            this.P = this.aD + 90;
            if (!this.ag.hasMessages(4096) && this.ai) {
                this.ag.sendEmptyMessageDelayed(4096, 200);
            }
        }
    }

    private void a(b bVar) {
        if (!this.ai || (bVar.b == this.aN && bVar.c == this.aO)) {
            if (dji.pilot.fpv.d.b.a(bVar.b) && dji.pilot.fpv.d.b.b(bVar.c)) {
                this.aN = bVar.b;
                this.aO = bVar.c;
                this.aU = true;
            }
        } else if (dji.pilot.fpv.d.b.a(bVar.b) && dji.pilot.fpv.d.b.b(bVar.c)) {
            this.aU = true;
            this.aN = bVar.b;
            this.aO = bVar.c;
            a(this.S.getGpsNum(), this.aP, this.aQ, this.ao, this.ap, true);
            if (!this.as) {
                a(false, this.as, this.at, true);
            }
        }
    }

    public void onEventBackgroundThread(b bVar) {
        if (bVar != null) {
            this.ag.sendMessageDelayed(this.ag.obtainMessage(8192, bVar), 200);
        }
    }

    private void c() {
        Resources resources = this.af.getResources();
        this.ah = resources.getColor(R.color.d6);
        this.aI = resources.getDrawable(R.drawable.osd_attitude_aircraft);
        this.aG = resources.getDrawable(R.drawable.osd_attitude_bg_normal);
        this.aH = resources.getDrawable(R.drawable.osd_attitude_gimbal);
        this.aJ = resources.getDimensionPixelSize(R.dimen.io);
        this.ag = new a(this);
        this.bb = i.getInstance().c();
        this.aa = new DJIGenSettingDataManager$c(this) {
            final /* synthetic */ DJIAttitudeView a;

            {
                this.a = r1;
            }

            public void a(int i) {
                if (this.a.ai) {
                    this.a.a(i);
                }
            }

            public void a(int i, boolean z) {
            }

            public void a(int i, boolean z, int i2, dji.midware.data.config.P3.a aVar) {
            }

            public void b(int i) {
            }
        };
        this.S = DataOsdGetPushCommon.getInstance();
        this.T = DataOsdGetPushHome.getInstance();
        this.U = DataGimbalGetPushParams.getInstance();
        this.W = DJIGenSettingDataManager.getInstance();
        this.V = DataRcGetPushGpsInfo.getInstance();
        this.ad = AnimationUtils.loadAnimation(this.af, R.anim.be);
        this.ae = AnimationUtils.loadAnimation(this.af, R.anim.bf);
        this.bc = dji.pilot.fpv.model.b.a(this.af, R.dimen.oc) + dji.pilot.fpv.model.b.a(this.af, R.dimen.n3);
        this.bd = dji.pilot.fpv.model.b.a(this.af, R.dimen.io);
        Drawable drawable = getResources().getDrawable(R.drawable.sport_attitude_tip_bg);
        this.be = drawable.getIntrinsicWidth();
        this.bf = drawable.getIntrinsicHeight();
        drawable = getResources().getDrawable(R.drawable.sport_attitude_new_bg);
        this.bg = (this.be - drawable.getIntrinsicWidth()) / 2;
        this.bh = (this.bf - drawable.getIntrinsicHeight()) / 2;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.s = (FrameLayout) findViewById(R.id.u3);
            this.t = (DJIRelativeLayout) findViewById(R.id.ts);
            this.u = (DJIImageView) findViewById(R.id.tw);
            this.v = (DJIImageView) findViewById(R.id.ty);
            this.w = (DJIImageView) findViewById(R.id.u0);
            this.x = (DJIImageView) findViewById(R.id.u2);
            this.y = (DJIImageView) findViewById(R.id.u1);
            this.K = (ProgressBar) findViewById(R.id.tt);
            this.z = (DJISportAttitudeView) findViewById(R.id.u4);
            this.A = (DJILinearLayout) findViewById(R.id.u5);
            this.B = (DJILinearLayout) findViewById(R.id.ub);
            this.C = (DJIRelativeLayout) findViewById(R.id.u6);
            this.D = (DJITextView) findViewById(R.id.uc);
            this.E = (DJITextView) findViewById(R.id.ud);
            this.F = (DJITextView) findViewById(R.id.uf);
            this.G = (DJITextView) findViewById(R.id.ug);
            this.M = (DJIImageView) findViewById(R.id.ue);
            this.H = (DJIImageView) findViewById(R.id.uh);
            this.I = (DJIImageView) findViewById(R.id.ui);
            this.J = (DJITextView) findViewById(R.id.uj);
            this.N = (DJIVisualObstacleRadarView) findViewById(R.id.tu);
            this.L = (DJIImageView) findViewById(R.id.tx);
            this.O = (DJIGimbalYawView) findViewById(R.id.tv);
            if (l == 0) {
                Paint paint = new Paint();
                paint.setTextSize((float) this.af.getResources().getDimensionPixelSize(R.dimen.ro));
                paint.setTypeface(DJITextView.NBOLD);
                l = ((int) paint.measureText(k)) + this.af.getResources().getDimensionPixelSize(R.dimen.n3);
                n = ((int) paint.measureText(m)) + this.af.getResources().getDimensionPixelSize(R.dimen.n3);
            }
            a(this.D, n);
            a(this.E, n);
            a(this.F, l);
            a(this.G, l);
            b(this.aT);
            d();
            this.ag.sendEmptyMessageDelayed(12288, j);
            this.s.setEnabled(true);
            this.s.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DJIAttitudeView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (!this.a.bl && !this.a.aj) {
                        this.a.b();
                    }
                }
            });
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.t.getLayoutParams();
            marginLayoutParams.leftMargin = this.bg;
            marginLayoutParams.bottomMargin = this.bh;
            this.t.setLayoutParams(marginLayoutParams);
            marginLayoutParams = (MarginLayoutParams) this.A.getLayoutParams();
            marginLayoutParams.bottomMargin = this.bh;
            this.A.setLayoutParams(marginLayoutParams);
        }
    }

    public void setGsOnRight(boolean z) {
        this.aj = z;
        if (z) {
            this.A.setTranslationX(0.0f);
            return;
        }
        f.a(this.s, 4);
        this.A.setTranslationX((float) (this.bc - this.bd));
        this.B.setBackgroundResource(R.drawable.osd_attitude_speed_rbg);
    }

    private void a(View view, int i) {
        LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = i;
        view.setLayoutParams(layoutParams);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void a(int i) {
        if (i == 0) {
            f();
        }
    }

    public void dispatchOnCreate() {
        c.a().a(this);
        b k = k.k();
        if (k != null && dji.pilot.fpv.d.b.a(k.b) && dji.pilot.fpv.d.b.b(k.c)) {
            this.aU = true;
            this.aN = k.b;
            this.aO = k.c;
        }
        this.W.a(this.aa);
    }

    public void dispatchOnDestroy() {
        c.a().d(this);
        this.W.b(this.aa);
    }

    private SpannableString a(String str, int i, int i2, int i3) {
        SpannableString spannableString = new SpannableString(str);
        int dimensionPixelSize;
        if (i3 == 0) {
            dimensionPixelSize = this.af.getResources().getDimensionPixelSize(R.dimen.rq);
            spannableString.setSpan(new dji.pilot.publics.d.a.a(DJITextView.NBOLD, 0, dimensionPixelSize), i, str.length() - i2, 17);
            spannableString.setSpan(new dji.pilot.publics.d.a.a(DJITextView.NBOLD, 0, dimensionPixelSize), i, str.length() - i2, 17);
        } else if (1 == i3) {
            dimensionPixelSize = this.af.getResources().getDimensionPixelSize(R.dimen.rl);
            int dimensionPixelSize2 = this.af.getResources().getDimensionPixelSize(R.dimen.rp);
            spannableString.setSpan(new dji.pilot.publics.d.a.a(DJITextView.NBOLD, 0, dimensionPixelSize), i, str.length() - i2, 17);
            spannableString.setSpan(new dji.pilot.publics.d.a.b(DJITextView.NBOLD, 0, dimensionPixelSize2, this.ah), str.length() - i2, str.length(), 17);
        }
        return spannableString;
    }

    private float[] a(double d, double d2, double d3, double d4, boolean z) {
        this.ba[0] = 0.0f;
        this.ba[1] = 0.0f;
        float a = dji.pilot.fpv.d.b.a(d, d2, d3, d4);
        if (a <= 0.0f) {
            this.ba[0] = 0.0f;
        } else {
            double toDegrees = Math.toDegrees(Math.asin((double) (dji.pilot.fpv.d.b.a(d, d4, d3, d4) / a)));
            if (d3 > d) {
                if (d4 <= d2) {
                    toDegrees = 180.0d - toDegrees;
                }
            } else if (d4 > d2) {
                toDegrees = 360.0d - toDegrees;
            } else {
                toDegrees += 180.0d;
            }
            if (Double.isNaN(toDegrees)) {
                toDegrees = 0.0d;
            }
            this.ba[0] = (float) toDegrees;
        }
        this.ba[1] = a;
        return this.ba;
    }

    private void a(int i, int i2, int i3) {
        this.aq = (float) (Math.sqrt((double) ((i * i) + (i2 * i2))) * 0.10000000149011612d);
        this.ar = Math.abs(((float) i3) * 0.1f);
    }

    private void d() {
        this.z.go();
        this.t.show();
        this.E.setText(R.string.fpv_distance_na);
        this.D.setText(R.string.fpv_height_na);
        this.F.setText(R.string.fpv_vspeed_na);
        this.G.setText(R.string.fpv_hspeed_na);
        this.al = Integer.MIN_VALUE;
        this.am = Integer.MIN_VALUE;
        this.an = Integer.MIN_VALUE;
        this.M.hide();
        this.M.setBackgroundResource(R.drawable.fpv_up);
        a(true);
        c(90.0f);
        d(0.0f);
        a(true, false, 0.0f, false);
        this.ay = 0.0f;
        this.O.setYaw(this.ay);
        a(MODE.OTHER, 0);
        this.ac = true;
        e();
    }

    private void e() {
        this.aN = 0.0d;
        this.aO = 0.0d;
        this.aP = 0.0d;
        this.aQ = 0.0d;
        this.aR = 0.0d;
        this.aS = 0.0d;
        this.aT = 1;
        this.aU = false;
        this.aV = false;
        this.aW = 0.0f;
        this.aX = 0.0f;
        this.aY = 0.0f;
        this.aZ = 0.0f;
    }

    private void f() {
        int v = this.W.v();
        float a;
        float a2;
        if (v == 0) {
            a = this.W.a(this.aq);
            a2 = this.W.a(this.ar);
            float b = this.W.b(this.aX);
            float b2 = this.W.b(this.ak);
            float b3 = this.W.b(this.at);
            if (this.au) {
                this.E.setText(a(this.af.getString(R.string.fpv_distance_imperial, new Object[]{Float.valueOf(b)}), 2, 2, 1));
            }
            this.D.setText(a(this.af.getString(R.string.fpv_height_imperial, new Object[]{Float.valueOf(b2)}), 2, 2, 1));
            this.F.setText(a(this.af.getString(R.string.fpv_vspeed_imperial, new Object[]{Float.valueOf(a2)}), 4, 3, 1));
            this.G.setText(a(this.af.getString(R.string.fpv_hspeed_imperial, new Object[]{Float.valueOf(a)}), 4, 3, 1));
            if (this.as) {
                this.J.setText(a(this.af.getString(R.string.fpv_imperial, new Object[]{Float.valueOf(b3)}), 0, 2, 1));
            }
        } else if (1 == v) {
            if (this.au) {
                this.E.setText(a(this.af.getString(R.string.fpv_distance_metric, new Object[]{Float.valueOf(this.aX)}), 2, 1, 1));
            }
            this.D.setText(a(this.af.getString(R.string.fpv_height_metric, new Object[]{Float.valueOf(this.ak)}), 2, 1, 1));
            this.F.setText(a(this.af.getString(R.string.fpv_vspeed_metric, new Object[]{Float.valueOf(this.ar)}), 4, 3, 1));
            this.G.setText(a(this.af.getString(R.string.fpv_hspeed_metric, new Object[]{Float.valueOf(this.aq)}), 4, 3, 1));
            if (this.as) {
                this.J.setText(a(this.af.getString(R.string.fpv_metric, new Object[]{Float.valueOf(this.at)}), 0, 1, 1));
            }
        } else {
            a = this.W.a(this.aq);
            a2 = this.W.a(this.ar);
            if (this.au) {
                this.E.setText(a(this.af.getString(R.string.fpv_distance_metric, new Object[]{Float.valueOf(this.aX)}), 2, 1, 1));
            }
            this.D.setText(a(this.af.getString(R.string.fpv_height_metric, new Object[]{Float.valueOf(this.ak)}), 2, 1, 1));
            this.F.setText(a(this.af.getString(R.string.fpv_vspeed_metric_km, new Object[]{Float.valueOf(a2)}), 4, 4, 1));
            this.G.setText(a(this.af.getString(R.string.fpv_hspeed_metric_km, new Object[]{Float.valueOf(a)}), 4, 4, 1));
            if (this.as) {
                this.J.setText(a(this.af.getString(R.string.fpv_metric, new Object[]{Float.valueOf(this.at)}), 0, 1, 1));
            }
        }
    }

    private float a(float f) {
        if (f >= 2000.0f) {
            return l.n;
        }
        return f > 400.0f ? (l.n * ((2000.0f - f) / 1600.0f)) + 0.39999998f : 1.0f;
    }

    private void a(boolean z) {
        int measuredWidth = this.t.getMeasuredWidth();
        if (measuredWidth <= 0 || z) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.x.getLayoutParams();
            layoutParams.addRule(13);
            this.x.setLayoutParams(layoutParams);
            this.x.setRotation(0.0f);
            this.x.setScaleX(1.0f);
            this.x.setScaleY(1.0f);
            layoutParams = (RelativeLayout.LayoutParams) this.y.getLayoutParams();
            layoutParams.addRule(13, 0);
            layoutParams.leftMargin = (this.aJ - this.aH.getIntrinsicWidth()) / 2;
            layoutParams.topMargin = (this.aJ / 2) - this.aH.getIntrinsicHeight();
            this.y.setLayoutParams(layoutParams);
            this.y.setRotation(0.0f);
            this.y.setScaleX(1.0f);
            this.y.setScaleY(1.0f);
            layoutParams = (RelativeLayout.LayoutParams) this.w.getLayoutParams();
            layoutParams.addRule(13);
            layoutParams.leftMargin = 0;
            layoutParams.topMargin = 0;
            this.w.setLayoutParams(layoutParams);
            this.w.go();
            layoutParams = (RelativeLayout.LayoutParams) this.v.getLayoutParams();
            layoutParams.addRule(13);
            layoutParams.leftMargin = 0;
            layoutParams.topMargin = 0;
            this.v.setLayoutParams(layoutParams);
            this.v.show();
            f(0.0f);
            if (z) {
                this.N.setHasVisual(false);
            }
            this.N.setRotation(0.0f);
            return;
        }
        float f;
        int round;
        int round2;
        double toRadians;
        float f2 = this.aX;
        if (this.aX < this.aZ) {
            f2 = this.aZ;
        }
        if (f2 < 400.0f) {
            f = 400.0f;
        } else {
            f = f2;
        }
        int round3;
        double d;
        if (this.aT == 1) {
            this.v.show();
            layoutParams = (RelativeLayout.LayoutParams) this.v.getLayoutParams();
            layoutParams.addRule(13);
            layoutParams.leftMargin = 0;
            layoutParams.topMargin = 0;
            this.v.setLayoutParams(layoutParams);
            if (this.aU && g()) {
                this.w.show();
                layoutParams = (RelativeLayout.LayoutParams) this.w.getLayoutParams();
                layoutParams.addRule(13, 0);
                round3 = Math.round(((float) measuredWidth) * 0.0f);
                round = Math.round(((float) ((measuredWidth - round3) - this.w.getWidth())) / 2.0f);
                round2 = Math.round(((float) ((measuredWidth - round3) - this.w.getHeight())) / 2.0f);
                round3 = Math.round(((float) round3) / 2.0f);
                toRadians = Math.toRadians((double) (this.aY + ((float) this.P)));
                if (this.aZ == f) {
                    layoutParams.leftMargin = (int) (((double) (round3 + round)) + (Math.cos(toRadians) * ((double) round)));
                    layoutParams.topMargin = (int) (((double) (round3 + round2)) - (((double) round2) * Math.sin(toRadians)));
                } else {
                    layoutParams.leftMargin = (int) (((double) (round3 + round)) + (((((double) this.aZ) * Math.cos(toRadians)) * ((double) round)) / ((double) f)));
                    d = (double) round2;
                    layoutParams.topMargin = (int) (((double) (round3 + round2)) - ((d * (Math.sin(toRadians) * ((double) this.aZ))) / ((double) f)));
                }
                this.w.setLayoutParams(layoutParams);
            } else {
                this.w.go();
            }
        } else if (this.aT == 0) {
            this.w.show();
            layoutParams = (RelativeLayout.LayoutParams) this.w.getLayoutParams();
            layoutParams.addRule(13);
            layoutParams.leftMargin = 0;
            layoutParams.topMargin = 0;
            this.w.setLayoutParams(layoutParams);
            if (this.aV) {
                this.v.show();
                layoutParams = (RelativeLayout.LayoutParams) this.v.getLayoutParams();
                layoutParams.addRule(13, 0);
                round3 = Math.round(((float) measuredWidth) * 0.0f);
                round = Math.round(((float) ((measuredWidth - round3) - this.v.getWidth())) / 2.0f);
                round2 = Math.round(((float) ((measuredWidth - round3) - this.v.getHeight())) / 2.0f);
                round3 = Math.round(((float) round3) / 2.0f);
                toRadians = Math.toRadians((double) (this.aY + ((float) this.P)));
                if (this.aZ == f) {
                    layoutParams.leftMargin = (int) (((double) (round3 + round)) + (Math.cos(toRadians) * ((double) round)));
                    layoutParams.topMargin = (int) (((double) (round3 + round2)) - (((double) round2) * Math.sin(toRadians)));
                } else {
                    layoutParams.leftMargin = (int) (((double) (round3 + round)) + (((((double) this.aZ) * Math.cos(toRadians)) * ((double) round)) / ((double) f)));
                    d = (double) round2;
                    layoutParams.topMargin = (int) (((double) (round3 + round2)) - ((d * (Math.sin(toRadians) * ((double) this.aZ))) / ((double) f)));
                }
                this.v.setLayoutParams(layoutParams);
            } else {
                this.v.go();
            }
        }
        float a = a(this.aX);
        layoutParams = (RelativeLayout.LayoutParams) this.x.getLayoutParams();
        layoutParams.addRule(13, 0);
        round = Math.round(((float) measuredWidth) * 0.0f);
        round2 = Math.round(((float) ((measuredWidth - round) - this.aI.getIntrinsicWidth())) / 2.0f);
        measuredWidth = Math.round(((float) ((measuredWidth - round) - this.aI.getIntrinsicHeight())) / 2.0f);
        round = Math.round(((float) round) / 2.0f);
        toRadians = Math.toRadians((double) (this.aW + ((float) this.P)));
        if (this.aX >= f) {
            layoutParams.leftMargin = (int) (((double) (round + round2)) + (Math.cos(toRadians) * ((double) round2)));
            layoutParams.topMargin = (int) (((double) (round + measuredWidth)) - (Math.sin(toRadians) * ((double) measuredWidth)));
        } else {
            layoutParams.leftMargin = (int) (((double) (round + round2)) + (((((double) this.aX) * Math.cos(toRadians)) * ((double) round2)) / ((double) f)));
            layoutParams.topMargin = (int) (((double) (round + measuredWidth)) - (((Math.sin(toRadians) * ((double) this.aX)) * ((double) measuredWidth)) / ((double) f)));
        }
        this.x.setLayoutParams(layoutParams);
        this.x.setRotation(this.ao - ((float) this.P));
        this.x.setScaleX(a);
        this.x.setScaleY(a);
        if (!this.ab) {
            this.y.setPivotY(this.y.getPivotY() * 2.0f);
            this.ab = true;
        }
        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.y.getLayoutParams();
        layoutParams2.addRule(13, 0);
        layoutParams2.leftMargin = ((layoutParams.leftMargin + (this.aI.getIntrinsicWidth() / 2)) - (this.aH.getIntrinsicWidth() / 2)) - 1;
        layoutParams2.topMargin = ((layoutParams.topMargin + (this.aI.getIntrinsicHeight() / 2)) - this.aH.getIntrinsicHeight()) - 2;
        this.y.setLayoutParams(layoutParams2);
        this.y.setRotation((this.ap - ((float) this.P)) + 180.0f);
        this.y.setScaleX(a);
        this.y.setScaleY(a);
        f(f);
        this.N.setRotation(this.ao - ((float) this.P));
    }

    private void a(int i, double d, double d2, float f, float f2, boolean z) {
        int i2 = 0;
        if (this.aT == 1) {
            if (dji.pilot.fpv.d.b.a(this.aR)) {
                i2 = 1;
            }
            if (dji.pilot.fpv.d.b.b(this.aS)) {
                i2++;
            }
        } else if (this.aT == 0) {
            if (dji.pilot.fpv.d.b.a(this.aN)) {
                i2 = 1;
            }
            if (dji.pilot.fpv.d.b.b(this.aO)) {
                i2++;
            }
        }
        if (dji.pilot.fpv.d.b.a(d)) {
            this.aP = d;
            i2++;
        }
        if (dji.pilot.fpv.d.b.b(d2)) {
            this.aQ = d2;
            i2++;
        }
        boolean z2 = this.au;
        if (!dji.pilot.fpv.d.b.b(i) || r2 < 4) {
            this.au = false;
        } else {
            this.au = true;
        }
        Object obj = (z || this.au != z2) ? 1 : null;
        this.ao = f;
        this.ap = f2;
        if (!this.au) {
            this.E.setText(R.string.fpv_distance_na);
        } else if (obj != null) {
            if (this.aT == 1) {
                if (this.aU && g()) {
                    a(this.aR, this.aS, this.aN, this.aO, false);
                    this.aY = this.ba[0];
                    this.aZ = this.ba[1];
                }
                a(this.aR, this.aS, this.aP, this.aQ, true);
                this.aW = this.ba[0];
                this.aX = this.ba[1];
            } else if (this.aT == 0) {
                if (this.aV) {
                    a(this.aN, this.aO, this.aR, this.aS, false);
                    this.aY = this.ba[0];
                    this.aZ = this.ba[1];
                }
                a(this.aN, this.aO, this.aP, this.aQ, true);
                this.aW = this.ba[0];
                this.aX = this.ba[1];
            }
            if (this.W.v() == 0) {
                float b = this.W.b(this.aX);
                this.E.setText(a(this.af.getString(R.string.fpv_distance_imperial, new Object[]{Float.valueOf(b)}), 2, 2, 1));
            } else {
                this.E.setText(a(this.af.getString(R.string.fpv_distance_metric, new Object[]{Float.valueOf(this.aX)}), 2, 1, 1));
            }
        }
        this.ag.removeMessages(4096);
        a(false);
    }

    private void b(int i, int i2, int i3) {
        if (this.al != i || this.am != i2 || this.an != i3) {
            this.al = i;
            this.am = i2;
            this.an = i3;
            a(i, i2, i3);
            int v = this.W.v();
            float a;
            float a2;
            if (v == 0) {
                a = this.W.a(this.aq);
                a2 = this.W.a(this.ar);
                this.F.setText(a(this.af.getString(R.string.fpv_vspeed_imperial, new Object[]{Float.valueOf(a2)}), 4, 3, 1));
                this.G.setText(a(this.af.getString(R.string.fpv_hspeed_imperial, new Object[]{Float.valueOf(a)}), 4, 3, 1));
            } else if (1 == v) {
                this.F.setText(a(this.af.getString(R.string.fpv_vspeed_metric, new Object[]{Float.valueOf(this.ar)}), 4, 3, 1));
                this.G.setText(a(this.af.getString(R.string.fpv_hspeed_metric, new Object[]{Float.valueOf(this.aq)}), 4, 3, 1));
            } else {
                a = this.W.a(this.aq);
                a2 = this.W.a(this.ar);
                this.F.setText(a(this.af.getString(R.string.fpv_vspeed_metric_km, new Object[]{Float.valueOf(a2)}), 4, 4, 1));
                this.G.setText(a(this.af.getString(R.string.fpv_hspeed_metric_km, new Object[]{Float.valueOf(a)}), 4, 4, 1));
            }
            if (this.an == 0) {
                this.M.hide();
            } else if (this.an < 0) {
                this.M.show();
                this.M.setBackgroundResource(R.drawable.fpv_up);
            } else {
                this.M.show();
                this.M.setBackgroundResource(R.drawable.fpv_down);
            }
        }
    }

    private void b(float f) {
        this.ak = f;
        if (this.W.v() == 0) {
            float b = this.W.b(this.ak);
            this.D.setText(a(this.af.getString(R.string.fpv_height_imperial, new Object[]{Float.valueOf(b)}), 2, 2, 1));
            return;
        }
        this.D.setText(a(this.af.getString(R.string.fpv_height_metric, new Object[]{Float.valueOf(this.ak)}), 2, 1, 1));
    }

    private void a(boolean z, boolean z2, float f, boolean z3) {
        this.as = z2;
        if (z) {
            this.H.go();
            this.I.go();
            this.J.go();
        } else if (z2) {
            this.H.show();
            this.I.show();
            this.I.setBackgroundResource(R.drawable.osd_attitude_visual_icon);
            this.J.show();
            this.at = f;
            if (((double) f) <= 1.2d) {
                this.J.setTextColor(this.af.getResources().getColor(R.color.gj));
            } else {
                this.J.setTextColor(this.af.getResources().getColor(R.color.om));
            }
            if (this.W.v() == 0) {
                r0 = this.W.b(f);
                this.J.setText(a(this.af.getString(R.string.fpv_imperial, new Object[]{Float.valueOf(r0)}), 0, 2, 1));
                return;
            }
            this.J.setText(a(this.af.getString(R.string.fpv_metric, new Object[]{Float.valueOf(this.at)}), 0, 1, 1));
        } else if (this.aU) {
            this.H.show();
            this.I.show();
            this.I.setBackgroundResource(R.drawable.osd_rc_distance);
            this.J.show();
            this.J.setTextColor(this.af.getResources().getColor(R.color.om));
            r0 = dji.pilot.fpv.d.b.a(this.aN, this.aO, this.aP, this.aQ);
            if (this.W.v() == 0) {
                r0 = this.W.b(r0);
                this.J.setText(a(this.af.getString(R.string.fpv_imperial, new Object[]{Float.valueOf(r0)}), 0, 2, 1));
                return;
            }
            this.J.setText(a(this.af.getString(R.string.fpv_metric, new Object[]{Float.valueOf(r0)}), 0, 1, 1));
        } else {
            this.H.go();
            this.I.go();
            this.J.go();
        }
    }

    private void c(float f) {
        int i = 100;
        this.av = f;
        int i2 = (int) ((this.av * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) / 180.0f);
        if (i2 < 0) {
            i = 0;
        } else if (i2 <= 100) {
            i = i2;
        }
        if (this.K.getProgress() != i) {
            this.K.setProgress(i);
        }
    }

    private void d(float f) {
        this.ax = f;
        this.K.setRotation(f);
    }

    private void e(float f) {
        this.ay = f;
        this.O.setYaw(this.ay);
    }

    private void f(float f) {
        this.N.setDistance(f);
    }

    private void a(MODE mode, int i) {
        boolean c = dji.pilot.groundStation.a.a.getInstance(getContext()).c();
        d z = dji.pilot.groundStation.a.a.getInstance(getContext()).z();
        this.aB = mode;
        this.aA = i;
        if (c && z == d.COURSE_LOCK && mode == MODE.CourseLock) {
            float toDegrees = (float) Math.toDegrees((double) ((((float) i) * 1.0f) / 1000.0f));
            this.L.show();
            this.L.setRotation(toDegrees - ((float) this.P));
            return;
        }
        this.L.go();
    }

    private boolean g() {
        return dji.pilot.fpv.d.b.a(null);
    }

    public void update(boolean z) {
        boolean z2 = false;
        if (this.ac) {
            b k = k.k();
            if (k != null && dji.pilot.fpv.d.b.a(k.b) && dji.pilot.fpv.d.b.b(k.c)) {
                this.aU = true;
                this.aN = k.b;
                this.aO = k.c;
            }
        }
        if ((this.ac || z) && this.T.isHomeRecord()) {
            double latitude = this.T.getLatitude();
            double longitude = this.T.getLongitude();
            if (dji.pilot.fpv.d.b.a(latitude) && dji.pilot.fpv.d.b.b(longitude) && this.aR != latitude && this.aS != longitude) {
                this.aV = true;
                this.aR = latitude;
                this.aS = longitude;
                z2 = true;
            }
        }
        if (!(!this.ac || g() || this.aT == 1)) {
            this.aT = 1;
            b(1);
        }
        DataOsdGetPushHome instance = DataOsdGetPushHome.getInstance();
        boolean a = dji.pilot.fpv.d.b.a(instance.isBeginnerMode(), instance.isMultipleModeOpen());
        RcModeChannel modeChannel = this.S.getModeChannel();
        if (this.s.getVisibility() == 0 && !dji.pilot.publics.e.a.d(null)) {
            if (a && modeChannel == RcModeChannel.CHANNEL_S) {
                this.t.go();
                this.z.show();
                this.A.setTranslationX((float) (0 - this.bg));
            } else {
                this.z.go();
                this.t.show();
                this.A.setTranslationX(0.0f);
            }
        }
        a(z2, this.S.getGpsNum(), this.S.getLatitude(), this.S.getLongitude(), (float) this.S.getHeight(), this.S.getXSpeed(), this.S.getYSpeed(), this.S.getZSpeed(), (float) this.S.getYaw(), (float) this.S.getPitch(), (float) this.S.getRoll(), (float) this.U.getYaw(), (float) this.U.getYawAngle(), (float) this.U.getPitch(), this.S.isSwaveWork(), (float) this.S.getSwaveHeight(), this.T.getIOCMode(), this.T.getCourseLockAngle());
    }

    public void disconnect() {
        this.ag.removeMessages(4096);
        this.ai = false;
        this.au = false;
        d();
    }

    private void b(int i) {
        int indexOfChild;
        if (i == 0) {
            indexOfChild = this.t.indexOfChild(this.w);
            if (indexOfChild != 5) {
                this.t.removeViewAt(indexOfChild);
                this.t.addView(this.w, 5);
            }
        } else if (i == 1) {
            indexOfChild = this.t.indexOfChild(this.v);
            if (indexOfChild != 5) {
                this.t.removeViewAt(indexOfChild);
                this.t.addView(this.v, 5);
            }
        }
    }

    private void a(boolean z, int i, double d, double d2, float f, int i2, int i3, int i4, float f2, float f3, float f4, float f5, float f6, float f7, boolean z2, float f8, MODE mode, int i5) {
        this.ai = true;
        float f9 = (0.1f * f2) + 0.0f;
        float f10 = 90.0f - (0.1f * f3);
        float f11 = f * 0.1f;
        float f12 = f6 * 0.1f;
        float f13 = (f12 + f9) + 180.0f;
        float f14 = (0.1f * f7) + 90.0f;
        float f15 = (0.1f * f4) + 0.0f;
        float f16 = f8 * 0.1f;
        if (this.ac) {
            b(f11);
            a(i, d, d2, f9, f13, true);
            a(false, z2, f16, true);
            c(f10);
            d(f15);
            e(f12);
            a(mode, i5);
        } else {
            Object obj = (this.aP == d && this.aQ == d2) ? null : 1;
            if (z || obj != null) {
                a(i, d, d2, f9, f13, true);
            } else if (this.ao != f9) {
                a(i, d, d2, f9, f13, false);
            } else if (this.ap != f13) {
                a(i, d, d2, f9, f13, false);
            }
            if (this.av != f10) {
                c(f10);
            }
            if (!(z2 == this.as && f16 == this.at && (z2 || !this.aU || obj == null))) {
                a(false, z2, f16, true);
            }
            if (this.ak != f11) {
                b(f11);
            }
            if (this.ax != f15) {
                d(f15);
            }
            if (this.ay != f12) {
                e(f12);
            }
            if (!(this.aB == mode && this.aA == i5)) {
                a(mode, i5);
            }
        }
        b(i2, i3, i4);
        this.ac = false;
    }

    private int getDisplayRotation() {
        if (this.Q == null) {
            this.Q = ((WindowManager) this.af.getSystemService("window")).getDefaultDisplay();
        }
        return this.Q.getRotation();
    }
}
