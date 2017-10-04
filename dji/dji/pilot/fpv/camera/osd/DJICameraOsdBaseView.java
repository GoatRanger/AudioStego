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
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
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
import dji.pilot.fpv.d.c.g;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.d;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.model.b;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import lecho.lib.hellocharts.model.l;

public class DJICameraOsdBaseView extends DJILinearLayout implements OnClickListener, d$c, s {
    protected static final int al = 3000;
    protected static final int am = 4096;
    protected static final int an = 4097;
    protected static final int ao = 4098;
    protected static final int ap = 4099;
    protected static final int aq = 4100;
    protected static final int ar = 4101;
    protected static final int as = 4102;
    protected static final int at = 4103;
    protected static final String i = "DJICameraOsdBaseView";
    protected static final boolean j = true;
    protected DJITextView A;
    protected DJITextView B;
    protected DJITextView C;
    protected DJITextView D;
    protected DJITextView E;
    protected DJIImageView F;
    protected DJITextView G;
    protected DJITextView H;
    protected DJITextView I;
    protected DJITextView J;
    protected DJITextView K;
    protected DJITextView L;
    protected DJITextView M;
    protected DJIImageView N;
    protected Context O;
    protected a P;
    protected final DecimalFormat Q = new DecimalFormat("#.##");
    protected int[] R = null;
    protected String[] S = null;
    protected String[] T = null;
    protected int[] U = null;
    protected String[] V = null;
    protected String[] W = null;
    protected SDCardState aA = SDCardState.OTHER;
    protected DiskStatus aB = DiskStatus.OTHER;
    protected MODE aC = MODE.OTHER;
    protected long aD = -1;
    protected int aE = -1;
    protected int aF = 0;
    protected int aG = 0;
    protected int aH = 0;
    protected int aI = -1;
    protected int aJ = -1;
    protected String aK = null;
    protected int aL = -1;
    protected boolean aM = false;
    protected int aN = -1;
    protected int aO = -1;
    protected int aP = -1;
    protected long aQ = -1;
    protected int aR = 255;
    protected int aS = -1;
    protected long aT = 0;
    protected String[] aa = null;
    protected int[] ab = null;
    protected String[] ac = null;
    protected int[] ad = null;
    protected String[] ae = null;
    protected int[] af = null;
    protected String[] ag = null;
    protected DataCameraGetPushShotParams ah;
    protected DataCameraGetPushShotInfo ai;
    protected DataCameraGetPushStateInfo aj;
    protected DataCameraGetPushRawParams ak;
    protected boolean au;
    protected boolean av = true;
    protected boolean aw = true;
    protected FuselageFocusMode ax = FuselageFocusMode.OTHER;
    protected ExposureMode ay = ExposureMode.i;
    protected CameraType az = CameraType.OTHER;
    protected DJILinearLayout k;
    protected DJILinearLayout l;
    protected ViewGroup m;
    protected ViewGroup n;
    protected ViewGroup o;
    protected View p;
    protected DJITextView q;
    protected DJITextView r;
    protected DJITextView s;
    protected DJITextView t;
    protected DJILinearLayout u;
    protected DJITextView v;
    protected DJIImageView w;
    protected DJITextView x;
    protected DJITextView y;
    protected DJITextView z;

    protected static final class a extends Handler {
        private final WeakReference<DJICameraOsdBaseView> a;

        public a(DJICameraOsdBaseView dJICameraOsdBaseView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJICameraOsdBaseView);
        }

        public void handleMessage(Message message) {
            DJICameraOsdBaseView dJICameraOsdBaseView = (DJICameraOsdBaseView) this.a.get();
            if (dJICameraOsdBaseView != null) {
                switch (message.what) {
                    case 4096:
                        dJICameraOsdBaseView.n();
                        return;
                    case 4097:
                        dJICameraOsdBaseView.l();
                        return;
                    case 4098:
                        dJICameraOsdBaseView.m();
                        return;
                    case 4099:
                        dJICameraOsdBaseView.p();
                        return;
                    case 4100:
                    case 4101:
                        return;
                    case DJICameraOsdBaseView.as /*4102*/:
                        dJICameraOsdBaseView.cameraConnect();
                        return;
                    case DJICameraOsdBaseView.at /*4103*/:
                        dJICameraOsdBaseView.cameraDisconnect();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJICameraOsdBaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.O = context;
            a();
            d();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            c();
            n();
            m();
            l();
            p();
        }
    }

    protected void a(String str) {
        DJILogHelper.getInstance().LOGD(i, str, false, true);
    }

    public void show() {
        if (g()) {
            super.show();
        }
    }

    protected void a() {
        b();
        this.P = new a(this);
        this.ah = DataCameraGetPushShotParams.getInstance();
        this.ai = DataCameraGetPushShotInfo.getInstance();
        this.aj = DataCameraGetPushStateInfo.getInstance();
        this.ak = DataCameraGetPushRawParams.getInstance();
    }

    protected void b() {
        int i;
        int i2 = 0;
        Resources resources = getContext().getResources();
        this.S = resources.getStringArray(R.array.ac);
        this.R = resources.getIntArray(R.array.ad);
        this.T = resources.getStringArray(R.array.b7);
        this.V = resources.getStringArray(R.array.a0);
        this.U = resources.getIntArray(R.array.a1);
        this.W = resources.getStringArray(R.array.b_);
        this.ab = resources.getIntArray(R.array.s);
        this.aa = resources.getStringArray(R.array.p);
        this.ac = resources.getStringArray(R.array.c3);
        this.ad = resources.getIntArray(R.array.al);
        this.ae = resources.getStringArray(R.array.ai);
        int[] intArray = resources.getIntArray(R.array.bx);
        int[] intArray2 = resources.getIntArray(R.array.bu);
        int[] iArr = new int[(intArray.length + intArray2.length)];
        int length = intArray.length;
        for (i = 0; i < length; i++) {
            iArr[i] = intArray[i];
        }
        length = intArray2.length;
        for (i = 0; i < length; i++) {
            iArr[intArray.length + i] = intArray2[i];
        }
        this.af = iArr;
        String[] stringArray = resources.getStringArray(R.array.bv);
        String[] stringArray2 = resources.getStringArray(R.array.bs);
        String[] strArr = new String[(stringArray.length + stringArray2.length)];
        int length2 = intArray.length;
        for (i = 0; i < length2; i++) {
            strArr[i] = stringArray[i];
        }
        i = intArray2.length;
        while (i2 < i) {
            strArr[intArray.length + i2] = stringArray2[i2];
            i2++;
        }
        this.ag = strArr;
    }

    protected void c() {
        this.s = (DJITextView) findViewById(R.id.vg);
        this.t = (DJITextView) findViewById(R.id.vo);
        this.u = (DJILinearLayout) findViewById(R.id.vp);
        this.v = (DJITextView) findViewById(R.id.vq);
        this.w = (DJIImageView) findViewById(R.id.vr);
        this.x = (DJITextView) findViewById(R.id.vs);
        this.y = (DJITextView) findViewById(R.id.v4);
        this.z = (DJITextView) findViewById(R.id.v5);
        this.A = (DJITextView) findViewById(R.id.v6);
        this.B = (DJITextView) findViewById(R.id.v7);
        this.D = (DJITextView) findViewById(R.id.v9);
        this.C = (DJITextView) findViewById(R.id.v8);
        this.E = (DJITextView) findViewById(R.id.v_);
        this.F = (DJIImageView) findViewById(R.id.vc);
        this.G = (DJITextView) findViewById(R.id.vd);
        this.H = (DJITextView) findViewById(R.id.ve);
        this.I = (DJITextView) findViewById(R.id.vf);
        this.J = (DJITextView) findViewById(R.id.vn);
        this.K = (DJITextView) findViewById(R.id.vk);
        this.L = (DJITextView) findViewById(R.id.vm);
        this.k = (DJILinearLayout) findViewById(R.id.v1);
        this.l = (DJILinearLayout) findViewById(R.id.vh);
        this.m = (GridLayout) findViewById(R.id.vi);
        this.q = (DJITextView) findViewById(R.id.vg);
        this.r = (DJITextView) findViewById(R.id.vo);
        this.n = (GridLayout) findViewById(R.id.v3);
        this.o = (GridLayout) findViewById(R.id.vb);
        this.p = findViewById(R.id.vr);
        this.N = (DJIImageView) findViewById(R.id.v2);
        this.x.setOnClickListener(this);
        this.v.setOnClickListener(this);
        o();
    }

    protected void d() {
        setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ DJICameraOsdBaseView a;

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
        String a = b.a();
        if (!"large".equals(a) && !"xlarge".equals(a)) {
            if (!z || z2) {
                this.aw = true;
                o();
                f();
                return;
            }
            this.aw = false;
            go();
            e();
        }
    }

    protected void e() {
        if (this.N != null) {
            this.N.go();
        }
    }

    protected void f() {
        if (this.N != null && this.aw) {
            if (j.a()) {
                this.N.show();
            } else {
                this.N.go();
            }
        }
    }

    public void setSmallMap(boolean z) {
        this.av = z;
        if (!z) {
            go();
        } else if (this.aA != SDCardState.OTHER) {
            o();
        }
    }

    public void startAnimation(Animation animation) {
        if (g()) {
            super.startAnimation(animation);
        }
    }

    protected boolean g() {
        CameraType cameraType = CameraType.OTHER;
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        }
        if (cameraType == CameraType.DJICameraTypeFC550Raw && ServiceManager.getInstance().isConnected() && this.av) {
            return true;
        }
        return false;
    }

    protected void h() {
        DataCameraSetAELock.getInstance().a(!this.au).start(null);
    }

    protected void i() {
        if ((this.ax == FuselageFocusMode.OneAuto || this.ax == FuselageFocusMode.ContinuousAuto) && dji.pilot.fpv.camera.more.a.getInstance().g()) {
            dji.pilot.fpv.camera.more.a.getInstance().b();
        } else if (this.ax == FuselageFocusMode.Manual || FuselageFocusMode.ManualFine == this.ax) {
            new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.A).a(FuselageFocusMode.OneAuto.value()).start(null);
        } else {
            new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.A).a(FuselageFocusMode.Manual.value()).start(null);
        }
    }

    protected void j() {
        int videoFormat;
        ExposureMode exposureMode = this.ah.getExposureMode();
        boolean z = this.ay != exposureMode;
        if (this.aC == MODE.RECORD) {
            videoFormat = (this.ah.getVideoFormat() * 10) + this.ah.getVideoFps();
            if (this.aG != videoFormat) {
                this.aG = videoFormat;
                this.G.setText(this.ag[a(videoFormat, this.af)]);
            }
        } else {
            videoFormat = this.ah.getImageFormat();
            if (this.aF != videoFormat) {
                this.aF = videoFormat;
                this.G.setText(this.ae[a(videoFormat, this.ad)]);
            }
        }
        videoFormat = this.ah.getRealApertureSize();
        if (this.aN != videoFormat) {
            this.aN = videoFormat;
            this.A.setText(this.Q.format((double) ((((float) videoFormat) * 1.0f) / DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity)));
        }
        videoFormat = this.ah.getRelISO();
        if (this.aJ != videoFormat || z) {
            this.aJ = videoFormat;
            this.y.setText(String.valueOf(videoFormat));
        }
        CharSequence relShutterString = this.ah.getRelShutterString();
        if (relShutterString != null && (!relShutterString.equalsIgnoreCase(this.aK) || z)) {
            this.aK = relShutterString;
            this.z.setText(relShutterString);
        }
        if (exposureMode == ExposureMode.e) {
            videoFormat = this.ah.getRelExposureCompensation();
        } else {
            videoFormat = this.ah.getExposureCompensation();
        }
        if (this.aI != videoFormat) {
            this.aI = videoFormat;
            this.B.setText(this.V[a(videoFormat, this.U)]);
        }
        videoFormat = this.ah.getDigitalFilter();
        if (this.aL != videoFormat) {
            this.aL = videoFormat;
            if (videoFormat == 0) {
                this.C.go();
                this.D.go();
            } else {
                this.C.show();
                this.D.show();
                this.D.setText(this.aa[a(videoFormat, this.ab)]);
                Map hashMap = new HashMap();
                hashMap.put(d.dH, "" + videoFormat);
                e.a(s.di, hashMap);
            }
        }
        videoFormat = this.ah.getWhiteBalance();
        int colorTemp = this.ah.getColorTemp();
        if (videoFormat == 6) {
            if (colorTemp != this.aP) {
                this.E.setText(String.valueOf(colorTemp * 100) + "K");
            }
        } else if (videoFormat != this.aO) {
            this.E.setText(this.ac[videoFormat]);
        }
        boolean isAELock = this.ah.isAELock();
        if (this.au != isAELock) {
            this.au = isAELock;
            if (isAELock) {
                this.v.setAlpha(1.0f);
            } else {
                this.v.setAlpha(l.n);
            }
            this.v.setSelected(isAELock);
        }
        if (z) {
            this.ay = exposureMode;
            if (exposureMode == ExposureMode.e) {
                if (isAELock) {
                    h();
                }
                this.v.setEnabled(false);
            } else {
                this.v.setEnabled(true);
            }
        }
        int ctrObjectForOne = this.ah.getCtrObjectForOne();
        if (this.aR != ctrObjectForOne) {
            this.aR = ctrObjectForOne;
            DJITextView dJITextView = this.M;
            if (this.aR == 2) {
                this.M = this.B;
            } else if (this.aR == 0) {
                this.M = this.y;
            } else if (this.aR == 1) {
                this.M = this.z;
            } else if (this.aR == 3) {
                this.M = this.A;
            } else {
                this.M = null;
            }
            if (dJITextView != this.M) {
                if (dJITextView != null) {
                    dJITextView.setSelected(false);
                }
                if (this.M != null) {
                    this.M.setSelected(true);
                }
            }
        }
    }

    protected void k() {
        if (this.ai.isGetted() && dji.pilot.fpv.d.b.c(this.az)) {
            FuselageFocusMode fuselageFocusMode = this.ai.getFuselageFocusMode();
            if (this.ax != fuselageFocusMode) {
                a("type changed");
                this.ax = fuselageFocusMode;
                if (fuselageFocusMode == FuselageFocusMode.OneAuto || fuselageFocusMode == FuselageFocusMode.ContinuousAuto) {
                    this.x.setText(a(this.O.getString(R.string.fpv_camera_amf), 0, 3));
                } else if (fuselageFocusMode == FuselageFocusMode.Manual || fuselageFocusMode == FuselageFocusMode.ManualFine) {
                    this.x.setText(a(this.O.getString(R.string.fpv_camera_amf), 2, 5));
                } else {
                    this.x.setText(this.O.getString(R.string.fpv_camera_amf));
                }
            }
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

    protected void n() {
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            CameraType cameraType = this.aj.getCameraType();
            if (this.az != cameraType) {
                this.az = cameraType;
                if (dji.pilot.fpv.d.b.c(this.az)) {
                    this.w.show();
                    this.x.show();
                    k();
                } else {
                    this.w.go();
                    this.x.go();
                }
            }
            SDCardState sDCardState = this.aj.getSDCardState(true);
            if (!(this.aj.getSDCardInsertState() || sDCardState == SDCardState.USBConnected)) {
                sDCardState = SDCardState.None;
            }
            if (this.aA != sDCardState) {
                this.aA = sDCardState;
                if (sDCardState == SDCardState.Slow) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - this.aT > OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME) {
                        this.aT = currentTimeMillis;
                        DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                        bVar.a = DJIErrorPopView.d.a;
                        bVar.b = R.string.sdcardstatus_slow_tip;
                        c.a().e(bVar);
                    }
                }
                o();
            }
            MODE mode = this.aj.getMode();
            boolean z = false;
            if (this.aC != mode) {
                this.aC = mode;
                z = true;
            }
            if (mode == MODE.RECORD) {
                int remainedTime = this.aj.getRemainedTime();
                if (z || this.aE != remainedTime) {
                    this.aE = remainedTime;
                    this.H.setText(R.string.camera_sd_time);
                    this.I.setText(b(remainedTime));
                }
            } else {
                long remainedShots = this.aj.getRemainedShots();
                if (z || this.aD != remainedShots) {
                    this.H.setText(null);
                    this.I.setText(dji.pilot.fpv.d.b.a(remainedShots));
                }
            }
            if (mode == MODE.RECORD) {
                if (-1 == this.aG) {
                    this.aG = (this.ah.getVideoFormat() * 10) + this.ah.getVideoFps();
                }
                if (z) {
                    this.G.setText(this.ag[a(this.aG, this.af)]);
                    return;
                }
                return;
            }
            if (-1 == this.aF) {
                this.aF = this.ah.getImageFormat();
            }
            if (z) {
                this.G.setText(this.ae[a(this.aF, this.ad)]);
            }
        }
    }

    protected void o() {
        if (this.aA == SDCardState.None) {
            a(0);
            this.s.setText(R.string.sdcardstatus_removal);
            this.s.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_sdcard_no, 0, 0, 0);
        } else if (dji.pilot.fpv.d.b.b(this.aA)) {
            a(1);
        } else if (this.aA == SDCardState.Full) {
            a(0);
            this.s.setText(R.string.sdcardstatus_full);
            this.s.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_sdcard_full, 0, 0, 0);
        } else if (this.aA == SDCardState.OTHER) {
            a(2);
        } else {
            a(0);
            this.s.setText(dji.pilot.fpv.d.b.a(this.aA));
            this.s.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_sdcard_slow, 0, 0, 0);
        }
    }

    protected void a(int i) {
        if (!this.aw || !g()) {
            go();
        } else if (i == 0) {
            if (!isShown()) {
                setVisibility(0);
            }
            this.k.show();
            this.l.show();
            this.u.show();
            this.n.setVisibility(8);
            this.o.setVisibility(8);
            this.p.setVisibility(8);
            this.q.show();
        } else if (i == 1) {
            if (!isShown()) {
                setVisibility(0);
            }
            this.k.show();
            this.l.show();
            this.u.show();
            this.n.setVisibility(0);
            this.o.setVisibility(0);
            this.p.setVisibility(0);
            this.q.go();
        } else if (i == 2) {
            this.k.go();
            this.l.go();
            this.u.go();
        }
    }

    protected void p() {
        a("DataCameraGetPushRawParams" + DataCameraGetPushRawParams.getInstance().isGetted());
        if (DataCameraGetPushRawParams.getInstance().isGetted()) {
            long availableCapacity = this.ak.getAvailableCapacity();
            a("leftCapacity" + availableCapacity);
            if (this.aQ != availableCapacity) {
                this.aQ = availableCapacity;
                if (availableCapacity > 1024) {
                    this.L.setText(String.valueOf(availableCapacity / 1024) + "G");
                } else {
                    this.L.setText(String.valueOf(availableCapacity) + "M");
                }
            }
            int resolution = this.ak.getResolution();
            int fps = this.ak.getFps();
            a("ratio:" + resolution + "fps" + fps);
            resolution = (resolution * 10) + fps;
            if (this.aH != resolution) {
                this.aH = resolution;
                this.K.setText(this.ag[a(resolution, this.af)]);
            }
            DiskStatus diskStatus = this.ak.getDiskStatus();
            a("ssd status:" + diskStatus);
            if (this.aB != diskStatus) {
                this.aB = diskStatus;
                a(diskStatus);
                resolution = this.ak.getAHCIStatus();
                if (resolution != this.aS) {
                    this.aS = resolution;
                    if (resolution != -1) {
                        e.a(g.M, "version", resolution == 0 ? "3.0" : "2.0");
                    }
                }
                if (diskStatus == DiskStatus.NOTCONNECTED) {
                    this.aS = -1;
                }
            }
        }
    }

    protected void a(DiskStatus diskStatus) {
        int i = 1;
        this.J.setVisibility(8);
        if (diskStatus == DiskStatus.NOTCONNECTED) {
            this.t.setText(R.string.ssd_status_error_nossd);
            this.t.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_ssd_no, 0, 0, 0);
        } else if (diskStatus == DiskStatus.NA) {
            this.t.setText(R.string.ssd_status_error_na);
            this.t.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_ssd_slow, 0, 0, 0);
        } else if (diskStatus == DiskStatus.LOW_FORMATING) {
            this.t.setText(R.string.ssd_status_format_low);
            this.t.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_ssd_slow, 0, 0, 0);
        } else if (diskStatus == DiskStatus.FAST_FORMATING) {
            this.t.setText(R.string.ssd_status_format_fast);
            this.t.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_ssd_slow, 0, 0, 0);
        } else if (diskStatus == DiskStatus.INITIALIZING) {
            this.t.setText(R.string.ssd_status_init);
            this.t.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_ssd_slow, 0, 0, 0);
        } else if (diskStatus == DiskStatus.DEVICE_ERROR) {
            this.t.setText(R.string.ssd_status_recognize_failed);
            this.t.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_ssd_no, 0, 0, 0);
        } else if (diskStatus == DiskStatus.VERIFY_ERROR) {
            this.t.setText(R.string.ssd_status_verify_failed);
            this.t.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_ssd_no, 0, 0, 0);
        } else if (diskStatus == DiskStatus.FULL) {
            this.t.setText(R.string.ssd_status_full);
            this.t.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_ssd_slow, 0, 0, 0);
        } else if (diskStatus == DiskStatus.STORING) {
            this.J.setVisibility(0);
            i = 0;
        } else {
            i = 0;
        }
        if (i != 0) {
            this.m.setVisibility(8);
            this.t.show();
            return;
        }
        this.m.setVisibility(0);
        this.t.go();
    }

    protected String b(int i) {
        int[] f = dji.pilot.fpv.d.b.f(i);
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
        o();
    }

    public void cameraDisconnect() {
        q();
        r();
        o();
    }

    protected void q() {
        this.au = false;
        this.ax = FuselageFocusMode.OTHER;
        this.ay = ExposureMode.i;
        this.az = CameraType.OTHER;
        this.aA = SDCardState.OTHER;
        this.aB = DiskStatus.NOTCONNECTED;
        this.aC = MODE.OTHER;
        this.aD = -1;
        this.aE = -1;
        this.aF = 0;
        this.aG = 0;
        this.aH = 0;
        this.aI = -1;
        this.aJ = -1;
        this.aK = null;
        this.aL = -1;
        this.aM = false;
        this.aN = -1;
        this.aO = -1;
        this.aP = -1;
        this.aQ = 0;
        this.aT = 0;
    }

    protected void r() {
        o();
        e();
    }

    public void onEventBackgroundThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        this.P.sendEmptyMessage(4097);
    }

    public void onEventBackgroundThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        this.P.sendEmptyMessage(4098);
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        this.P.sendEmptyMessage(4096);
    }

    public void onEventBackgroundThread(DataCameraGetPushRawParams dataCameraGetPushRawParams) {
        this.P.sendEmptyMessage(4099);
    }

    public void onEventBackgroundThread(o oVar) {
        if (oVar == o.b) {
            if (this.P.hasMessages(as)) {
                this.P.removeMessages(as);
            }
            this.P.sendEmptyMessage(as);
        } else if (oVar == o.a) {
            if (this.P.hasMessages(at)) {
                this.P.removeMessages(at);
            }
            this.P.sendEmptyMessage(at);
        }
    }

    public void onEventMainThread(dji.pilot.fpv.control.j.a aVar) {
        f();
    }

    public void onEventMainThread(dji.pilot.dji_groundstation.a.e eVar) {
        if (eVar != null) {
            if (eVar.s == 20) {
                hide();
            } else if (eVar.s == 19 && this.av) {
                show();
            }
        }
    }
}
