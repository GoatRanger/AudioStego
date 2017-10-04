package dji.pilot.fpv.camera.osd;

import android.content.Context;
import android.content.res.Resources;
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
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.GridLayout;
import com.here.odnp.config.OdnpConfigStatic;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushRawParams;
import dji.midware.data.model.P3.DataCameraGetPushRawParams.DiskStatus;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo.FuselageFocusMode;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataCameraSetAELock;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.d$c;
import dji.pilot.fpv.control.j;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.newfpv.d;
import dji.pilot.newfpv.g;
import dji.pilot.newfpv.h;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DJICameraOsdKumquatView extends DJILinearLayout implements OnClickListener, d$c, s, h<dji.pilot.newfpv.f.a> {
    protected static final int ah = 3000;
    protected static final int ai = 4096;
    protected static final int aj = 4097;
    protected static final int ak = 4098;
    protected static final int al = 4099;
    protected static final int am = 4100;
    protected static final int an = 4101;
    protected static final int ao = 4102;
    protected static final int ap = 4103;
    protected static final String i = "DJICameraOsdBaseView";
    protected static final boolean j = true;
    protected DJITextView A;
    protected DJIImageView B;
    protected DJITextView C;
    protected DJITextView D;
    protected DJITextView E;
    protected DJITextView F;
    protected DJITextView G;
    protected DJITextView H;
    protected DJITextView I;
    protected DJIImageView J;
    protected Context K;
    protected a L;
    protected final DecimalFormat M = new DecimalFormat("#.##");
    protected int[] N = null;
    protected String[] O = null;
    protected String[] P = null;
    protected int[] Q = null;
    protected String[] R = null;
    protected String[] S = null;
    protected String[] T = null;
    protected int[] U = null;
    protected String[] V = null;
    protected int[] W = null;
    protected int aA = -1;
    protected int aB = 0;
    protected int aC = -1;
    protected int aD = -1;
    protected int aE = -1;
    protected int aF = -1;
    protected String aG = null;
    protected int aH = -1;
    protected boolean aI = false;
    protected int aJ = -1;
    protected int aK = -1;
    protected int aL = -1;
    protected long aM = -1;
    protected int aN = 255;
    protected int aO = -1;
    protected long aP = 0;
    private final long aQ = 500;
    private g aR = null;
    private d aS = null;
    protected String[] aa = null;
    protected int[] ab = null;
    protected String[] ac = null;
    protected DataCameraGetPushShotParams ad;
    protected DataCameraGetPushShotInfo ae;
    protected DataCameraGetPushStateInfo af;
    protected DataCameraGetPushRawParams ag;
    protected boolean aq;
    protected boolean ar = true;
    protected boolean as = true;
    protected FuselageFocusMode at = FuselageFocusMode.OTHER;
    protected ExposureMode au = ExposureMode.i;
    protected CameraType av = CameraType.OTHER;
    protected SDCardState aw = SDCardState.OTHER;
    protected DiskStatus ax = DiskStatus.OTHER;
    protected MODE ay = MODE.OTHER;
    protected long az = -1;
    protected DJILinearLayout k;
    protected ViewGroup l;
    protected ViewGroup m;
    protected DJITextView n;
    protected DJITextView o;
    protected DJILinearLayout p;
    protected DJITextView q;
    protected DJITextView r;
    protected DJITextView s;
    protected DJITextView t;
    protected DJITextView u;
    protected DJITextView v;
    protected DJITextView w;
    protected DJITextView x;
    protected DJITextView y;
    protected DJITextView z;

    protected static final class a extends Handler {
        private final WeakReference<DJICameraOsdKumquatView> a;

        public a(DJICameraOsdKumquatView dJICameraOsdKumquatView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJICameraOsdKumquatView);
        }

        public void handleMessage(Message message) {
            DJICameraOsdKumquatView dJICameraOsdKumquatView = (DJICameraOsdKumquatView) this.a.get();
            if (dJICameraOsdKumquatView != null) {
                switch (message.what) {
                    case 4096:
                        dJICameraOsdKumquatView.n();
                        return;
                    case 4097:
                        dJICameraOsdKumquatView.l();
                        return;
                    case 4098:
                        dJICameraOsdKumquatView.m();
                        return;
                    case 4099:
                        dJICameraOsdKumquatView.p();
                        return;
                    case 4100:
                    case 4101:
                        return;
                    case DJICameraOsdKumquatView.ao /*4102*/:
                        dJICameraOsdKumquatView.cameraConnect();
                        return;
                    case DJICameraOsdKumquatView.ap /*4103*/:
                        dJICameraOsdKumquatView.cameraDisconnect();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJICameraOsdKumquatView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.K = context;
            a();
            d();
        }
    }

    public void bind(g gVar, int i) {
        this.aR = gVar;
    }

    public void bindInfo(dji.pilot.newfpv.view.b.a aVar, dji.pilot.newfpv.f.a aVar2, dji.pilot.newfpv.f.a aVar3) {
        this.aS = new d(this, aVar, aVar2, aVar3);
    }

    public dji.pilot.newfpv.view.b.a getViewId() {
        return this.aS.b;
    }

    public d getViewInfo() {
        return this.aS;
    }

    public boolean needShow() {
        return true;
    }

    public View getSelf() {
        return this;
    }

    public void onEventMainThread(dji.pilot.newfpv.f.a aVar) {
        dji.pilot.newfpv.view.a.a(dji.pilot.newfpv.f.a.OSD_SHOW == aVar, this.aR, this);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a("onAttachedToWindow = ");
            s();
        }
    }

    private void s() {
        if (!b.f(null)) {
            new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.A).a(FuselageFocusMode.OneAuto.value()).start(null);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        c();
        n();
        m();
        l();
        p();
    }

    protected void a(String str) {
    }

    public void show() {
        if (g()) {
            super.show();
        }
    }

    protected void a() {
        b();
        this.L = new a(this);
        this.ad = DataCameraGetPushShotParams.getInstance();
        this.ae = DataCameraGetPushShotInfo.getInstance();
        this.af = DataCameraGetPushStateInfo.getInstance();
        this.ag = DataCameraGetPushRawParams.getInstance();
    }

    protected void b() {
        Resources resources = getContext().getResources();
        this.O = resources.getStringArray(R.array.ac);
        this.N = resources.getIntArray(R.array.ad);
        this.P = resources.getStringArray(R.array.b7);
        this.R = resources.getStringArray(R.array.a0);
        this.Q = resources.getIntArray(R.array.a1);
        this.S = resources.getStringArray(R.array.b_);
        this.U = resources.getIntArray(R.array.s);
        this.T = resources.getStringArray(R.array.p);
        this.V = resources.getStringArray(R.array.c3);
        this.W = resources.getIntArray(R.array.al);
        this.aa = resources.getStringArray(R.array.ai);
        this.ab = resources.getIntArray(R.array.by);
        this.ac = resources.getStringArray(R.array.br);
    }

    protected void c() {
        if (!isInEditMode()) {
            this.n = (DJITextView) findViewById(R.id.vg);
            this.o = (DJITextView) findViewById(R.id.vo);
            this.p = (DJILinearLayout) findViewById(R.id.vp);
            this.q = (DJITextView) findViewById(R.id.vq);
            this.r = (DJITextView) findViewById(R.id.vs);
            this.s = (DJITextView) findViewById(R.id.v4);
            this.t = (DJITextView) findViewById(R.id.v5);
            this.u = (DJITextView) findViewById(R.id.v6);
            this.v = (DJITextView) findViewById(R.id.vt);
            this.w = (DJITextView) findViewById(R.id.vu);
            this.x = (DJITextView) findViewById(R.id.v7);
            this.z = (DJITextView) findViewById(R.id.v9);
            this.y = (DJITextView) findViewById(R.id.v8);
            this.A = (DJITextView) findViewById(R.id.v_);
            this.B = (DJIImageView) findViewById(R.id.vc);
            this.C = (DJITextView) findViewById(R.id.vd);
            this.D = (DJITextView) findViewById(R.id.ve);
            this.E = (DJITextView) findViewById(R.id.vf);
            this.F = (DJITextView) findViewById(R.id.vn);
            this.G = (DJITextView) findViewById(R.id.vk);
            this.H = (DJITextView) findViewById(R.id.vm);
            this.k = (DJILinearLayout) findViewById(R.id.v1);
            this.l = (GridLayout) findViewById(R.id.v3);
            this.m = (GridLayout) findViewById(R.id.vb);
            this.J = (DJIImageView) findViewById(R.id.v2);
            this.r.setOnClickListener(this);
            this.q.setOnClickListener(this);
            if (!b.h(i.getInstance().b())) {
                this.u.go();
                this.v.go();
            }
            if (!b.e(i.getInstance().b())) {
                this.r.go();
            }
            o();
        }
    }

    protected void d() {
        setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ DJICameraOsdKumquatView a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.vq) {
            e.a("FPV_RightBarView_CameraControllView_Button_AELock");
            e.c(s.do);
            h();
        } else if (id == R.id.vs) {
            i();
        }
    }

    public void handleCameraWidgetVisibility(boolean z, boolean z2) {
        String a = dji.pilot.fpv.model.b.a();
        if (!"large".equals(a) && !"xlarge".equals(a)) {
            if (!z || z2) {
                this.as = true;
                o();
                f();
                return;
            }
            this.as = false;
            go();
            e();
        }
    }

    protected void e() {
        if (this.J != null) {
            this.J.go();
        }
    }

    protected void f() {
        if (this.J != null && this.as) {
            if (j.a()) {
                this.J.show();
            } else {
                this.J.go();
            }
        }
    }

    public void setSmallMap(boolean z) {
        this.ar = z;
        if (!z) {
            go();
        } else if (this.aw != SDCardState.OTHER) {
            o();
        }
    }

    public void startAnimation(Animation animation) {
        if (g()) {
            super.startAnimation(animation);
        }
    }

    protected boolean g() {
        if (isInEditMode()) {
            return true;
        }
        ProductType c = i.getInstance().c();
        if ((c == ProductType.OrangeRAW || c == ProductType.KumquatS || c == ProductType.KumquatX || c == ProductType.Pomato) && ServiceManager.getInstance().isConnected() && this.ar) {
            return true;
        }
        return false;
    }

    protected void h() {
        DataCameraSetAELock.getInstance().a(!this.aq).start(null);
    }

    protected void i() {
        if ((this.at == FuselageFocusMode.OneAuto || this.at == FuselageFocusMode.ContinuousAuto) && dji.pilot.fpv.camera.more.a.getInstance().g()) {
            dji.pilot.fpv.camera.more.a.getInstance().b();
        } else if (this.at == FuselageFocusMode.Manual || FuselageFocusMode.ManualFine == this.at) {
            new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.A).a(FuselageFocusMode.OneAuto.value()).start(null);
        } else {
            new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.A).a(FuselageFocusMode.Manual.value()).start(null);
        }
    }

    protected void j() {
        int videoFormat;
        int videoFps;
        ExposureMode exposureMode = this.ad.getExposureMode();
        boolean z = this.au != exposureMode;
        if (this.ay == MODE.RECORD) {
            videoFormat = this.ad.getVideoFormat();
            videoFps = this.ad.getVideoFps();
            int i = (videoFormat * 100) + videoFps;
            a("ratio=" + videoFormat + " fps=" + videoFps);
            if (this.aC != i) {
                this.aC = i;
                this.C.setText(this.ac[a(i, this.ab)]);
            }
        } else {
            videoFormat = this.ad.getImageFormat();
            if (this.aB != videoFormat) {
                this.aB = videoFormat;
                this.C.setText(this.aa[a(videoFormat, this.W)]);
            }
        }
        videoFormat = this.ad.getRealApertureSize();
        if (this.aJ != videoFormat) {
            this.aJ = videoFormat;
            this.u.setText(this.M.format((double) ((((float) videoFormat) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity)));
        }
        videoFormat = this.ad.getRelISO();
        if (this.aF != videoFormat || z) {
            this.aF = videoFormat;
            this.s.setText(String.valueOf(videoFormat));
        }
        CharSequence relShutterString = this.ad.getRelShutterString();
        if (relShutterString != null && (!relShutterString.equalsIgnoreCase(this.aG) || z)) {
            this.aG = relShutterString;
            this.t.setText(relShutterString);
        }
        if (exposureMode == ExposureMode.e) {
            videoFormat = this.ad.getRelExposureCompensation();
        } else {
            videoFormat = this.ad.getExposureCompensation();
        }
        if (this.aE != videoFormat) {
            this.aE = videoFormat;
            this.x.setText(this.R[a(videoFormat, this.Q)]);
        }
        videoFormat = this.ad.getDigitalFilter();
        if (this.aH != videoFormat) {
            this.aH = videoFormat;
            if (videoFormat == 0) {
                this.y.go();
                this.z.go();
            } else {
                this.y.show();
                this.z.show();
                this.z.setText(this.T[a(videoFormat, this.U)]);
                Map hashMap = new HashMap();
                hashMap.put(dji.pilot.fpv.d.d.dH, "" + videoFormat);
                e.a(s.di, hashMap);
            }
        }
        videoFormat = this.ad.getWhiteBalance();
        videoFps = this.ad.getColorTemp();
        if (videoFormat == 6) {
            if (videoFps != this.aL) {
                this.A.setText(String.valueOf(videoFps * 100) + "K");
            }
        } else if (videoFormat != this.aK) {
            this.A.setText(this.V[videoFormat]);
        }
        boolean isAELock = this.ad.isAELock();
        if (this.aq != isAELock) {
            this.aq = isAELock;
            this.q.setSelected(isAELock);
        }
        if (z) {
            this.au = exposureMode;
            if (exposureMode == ExposureMode.e) {
                if (isAELock) {
                    h();
                }
                this.q.setEnabled(false);
                this.w.setText(R.string.camera_mm);
            } else {
                this.q.setEnabled(true);
                this.w.setText(R.string.fpv_camera_ev);
            }
        }
        int ctrObjectForOne = this.ad.getCtrObjectForOne();
        if (this.aN != ctrObjectForOne) {
            this.aN = ctrObjectForOne;
            DJITextView dJITextView = this.I;
            if (this.aN == 2) {
                this.I = this.x;
            } else if (this.aN == 0) {
                this.I = this.s;
            } else if (this.aN == 1) {
                this.I = this.t;
            } else if (this.aN == 3) {
                this.I = this.u;
            } else {
                this.I = null;
            }
            if (dJITextView != this.I) {
                if (dJITextView != null) {
                    dJITextView.setSelected(false);
                }
                if (this.I != null) {
                    this.I.setSelected(true);
                }
            }
        }
    }

    protected void k() {
        if (this.ae.isGetted() && b.c(this.av)) {
            FuselageFocusMode fuselageFocusMode = this.ae.getFuselageFocusMode();
            if (this.at != fuselageFocusMode) {
                a("type changed");
                this.at = fuselageFocusMode;
                if (fuselageFocusMode == FuselageFocusMode.OneAuto || fuselageFocusMode == FuselageFocusMode.ContinuousAuto) {
                    this.r.setText(a(this.K.getString(R.string.fpv_camera_amf), 0, 3));
                } else if (fuselageFocusMode == FuselageFocusMode.Manual || fuselageFocusMode == FuselageFocusMode.ManualFine) {
                    this.r.setText(a(this.K.getString(R.string.fpv_camera_amf), 2, 5));
                } else {
                    this.r.setText(this.K.getString(R.string.fpv_camera_amf));
                }
            }
        }
    }

    protected SpannableString a(String str, int i, int i2) {
        SpannableString spannableString = new SpannableString(str);
        if (i2 == str.length()) {
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.o2)), i, i2, 17);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.om)), 0, i, 17);
        } else {
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.o2)), i, i2, 17);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.om)), i2, str.length(), 17);
        }
        return spannableString;
    }

    protected void l() {
        if (DataCameraGetPushShotParams.getInstance().isGetted()) {
            j();
            k();
        }
    }

    protected void m() {
        if (DataCameraGetPushShotInfo.getInstance().isGetted()) {
            k();
        }
    }

    public void onEventMainThread(dji.setting.ui.general.ShowMfSwitchView.a aVar) {
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            a(DataCameraGetPushStateInfo.getInstance().getCameraType());
        }
    }

    private void a(CameraType cameraType) {
        if (b.c(cameraType)) {
            if (b.e(cameraType)) {
                this.r.show();
            } else {
                this.r.go();
            }
            k();
            return;
        }
        this.r.go();
    }

    protected void n() {
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            CameraType cameraType = this.af.getCameraType();
            if (this.av != cameraType) {
                this.av = cameraType;
                a(this.av);
            }
            SDCardState sDCardState = this.af.getSDCardState(true);
            if (!(this.af.getSDCardInsertState() || sDCardState == SDCardState.USBConnected)) {
                sDCardState = SDCardState.None;
            }
            if (this.aw != sDCardState) {
                this.aw = sDCardState;
                if (sDCardState == SDCardState.Slow) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - this.aP > OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME) {
                        this.aP = currentTimeMillis;
                        DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                        bVar.a = DJIErrorPopView.d.a;
                        bVar.b = R.string.sdcardstatus_slow_tip;
                        c.a().e(bVar);
                    }
                }
                o();
            }
            MODE mode = this.af.getMode();
            boolean z = false;
            if (this.ay != mode) {
                this.ay = mode;
                z = true;
            }
            if (mode == MODE.RECORD) {
                int remainedTime = this.af.getRemainedTime();
                if (z || this.aA != remainedTime) {
                    this.aA = remainedTime;
                    this.D.setText(R.string.camera_sd_time);
                    this.E.setText(b(remainedTime));
                }
            } else {
                long remainedShots = this.af.getRemainedShots();
                if (z || this.az != remainedShots) {
                    this.D.setText(R.string.capacity);
                    this.E.setText(b.a(remainedShots));
                }
            }
            if (mode == MODE.RECORD) {
                if (-1 == this.aC) {
                    this.aC = (this.ad.getVideoFormat() * 100) + this.ad.getVideoFps();
                }
                if (z) {
                    this.C.setText(this.ac[a(this.aC, this.ab)]);
                    return;
                }
                return;
            }
            if (-1 == this.aB) {
                this.aB = this.ad.getImageFormat();
            }
            if (z) {
                this.C.setText(this.aa[a(this.aB, this.W)]);
            }
        }
    }

    protected void o() {
        if (this.aw == SDCardState.None) {
            a(0);
            this.n.setText(R.string.sdcardstatus_removal);
            this.n.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_sdcard_no, 0, 0, 0);
        } else if (b.b(this.aw)) {
            a(1);
        } else if (this.aw == SDCardState.Full) {
            a(0);
            this.n.setText(R.string.sdcardstatus_full);
            this.n.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_sdcard_full, 0, 0, 0);
        } else if (this.aw == SDCardState.OTHER) {
            a(2);
        } else {
            a(0);
            this.n.setText(b.a(this.aw));
            this.n.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_sdcard_slow, 0, 0, 0);
        }
    }

    protected void a(int i) {
        a("status = " + i);
        if (!this.as || !g()) {
            go();
        } else if (i == 0) {
            if (!isShown()) {
                setVisibility(0);
            }
            this.k.show();
            this.p.show();
            this.l.setVisibility(0);
            this.m.setVisibility(8);
            this.n.show();
        } else if (i == 1) {
            if (!isShown()) {
                setVisibility(0);
            }
            this.k.show();
            this.p.show();
            this.l.setVisibility(0);
            this.m.setVisibility(0);
            this.n.go();
        }
    }

    protected void p() {
        if (this.H != null && this.G != null) {
            a("DataCameraGetPushRawParams" + DataCameraGetPushRawParams.getInstance().isGetted());
            if (DataCameraGetPushRawParams.getInstance().isGetted()) {
                long availableCapacity = this.ag.getAvailableCapacity();
                a("leftCapacity" + availableCapacity);
                if (!(this.H == null || this.aM == availableCapacity)) {
                    this.aM = availableCapacity;
                    if (availableCapacity > 1024) {
                        this.H.setText(String.valueOf(availableCapacity / 1024) + "G");
                    } else {
                        this.H.setText(String.valueOf(availableCapacity) + "M");
                    }
                }
                int resolution = this.ag.getResolution();
                int fps = this.ag.getFps();
                a("ratio:" + resolution + "fps" + fps);
                resolution = (resolution * 100) + fps;
                if (this.aD != resolution) {
                    this.aD = resolution;
                    this.G.setText(this.ac[a(resolution, this.ab)]);
                }
                DiskStatus diskStatus = this.ag.getDiskStatus();
                a("ssd status:" + diskStatus);
                if (this.ax != diskStatus) {
                    this.ax = diskStatus;
                    a(diskStatus);
                    resolution = this.ag.getAHCIStatus();
                    if (resolution != this.aO) {
                        this.aO = resolution;
                        if (resolution != -1) {
                            e.a(dji.pilot.fpv.d.c.g.M, "version", resolution == 0 ? "3.0" : "2.0");
                        }
                    }
                    if (diskStatus == DiskStatus.NOTCONNECTED) {
                        this.aO = -1;
                    }
                }
            }
        }
    }

    protected void a(DiskStatus diskStatus) {
        this.F.setVisibility(8);
        if (diskStatus == DiskStatus.NOTCONNECTED) {
            this.o.setText(R.string.ssd_status_error_nossd);
            this.o.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_ssd_no, 0, 0, 0);
        } else if (diskStatus == DiskStatus.NA) {
            this.o.setText(R.string.ssd_status_error_na);
            this.o.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_ssd_slow, 0, 0, 0);
        } else if (diskStatus == DiskStatus.LOW_FORMATING) {
            this.o.setText(R.string.ssd_status_format_low);
            this.o.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_ssd_slow, 0, 0, 0);
        } else if (diskStatus == DiskStatus.FAST_FORMATING) {
            this.o.setText(R.string.ssd_status_format_fast);
            this.o.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_ssd_slow, 0, 0, 0);
        } else if (diskStatus == DiskStatus.INITIALIZING) {
            this.o.setText(R.string.ssd_status_init);
            this.o.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_ssd_slow, 0, 0, 0);
        } else if (diskStatus == DiskStatus.DEVICE_ERROR) {
            this.o.setText(R.string.ssd_status_recognize_failed);
            this.o.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_ssd_no, 0, 0, 0);
        } else if (diskStatus == DiskStatus.VERIFY_ERROR) {
            this.o.setText(R.string.ssd_status_verify_failed);
            this.o.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_ssd_no, 0, 0, 0);
        } else if (diskStatus == DiskStatus.FULL) {
            this.o.setText(R.string.ssd_status_full);
            this.o.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_ssd_slow, 0, 0, 0);
        } else if (diskStatus == DiskStatus.STORING) {
            this.F.setVisibility(0);
        }
    }

    protected String b(int i) {
        int[] f = b.f(i);
        return String.format(Locale.US, "%1$02d:%2$02d:%3$02d", new Object[]{Integer.valueOf(f[2]), Integer.valueOf(f[1]), Integer.valueOf(f[0])});
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

    public void cameraConnect() {
        show();
        o();
    }

    public void cameraDisconnect() {
        q();
        r();
        o();
        go();
    }

    protected void q() {
        this.aq = false;
        this.at = FuselageFocusMode.OTHER;
        this.au = ExposureMode.i;
        this.av = CameraType.OTHER;
        this.aw = SDCardState.OTHER;
        this.ax = DiskStatus.NOTCONNECTED;
        this.ay = MODE.OTHER;
        this.az = -1;
        this.aA = -1;
        this.aB = 0;
        this.aC = -1;
        this.aD = -1;
        this.aE = -1;
        this.aF = -1;
        this.aG = null;
        this.aH = -1;
        this.aI = false;
        this.aJ = -1;
        this.aK = -1;
        this.aL = -1;
        this.aM = 0;
        this.aP = 0;
    }

    protected void r() {
        o();
        e();
    }

    private void c(int i) {
        if (!this.L.hasMessages(i)) {
            this.L.sendEmptyMessageDelayed(i, 500);
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        c(4097);
    }

    public void onEventBackgroundThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        c(4098);
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        c(4096);
    }

    public void onEventBackgroundThread(DataCameraGetPushRawParams dataCameraGetPushRawParams) {
        c(4099);
    }

    public void onEventBackgroundThread(o oVar) {
        if (oVar == o.b) {
            if (this.L.hasMessages(ao)) {
                this.L.removeMessages(ao);
            }
            this.L.sendEmptyMessage(ao);
        } else if (oVar == o.a) {
            if (this.L.hasMessages(ap)) {
                this.L.removeMessages(ap);
            }
            this.L.sendEmptyMessage(ap);
        }
    }

    public void onEventMainThread(dji.pilot.fpv.control.j.a aVar) {
        f();
    }

    public void onEventMainThread(dji.pilot.dji_groundstation.a.e eVar) {
        if (eVar != null) {
            if (eVar.s == 20) {
                hide();
            } else if (eVar.s == 19 && this.ar) {
                show();
            }
        }
    }
}
