package dji.pilot.fpv.control;

import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.dji.frame.c.d;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushRawParams;
import dji.midware.data.model.P3.DataCameraGetPushRawParams.DiskStatus;
import dji.midware.data.model.P3.DataCameraGetPushRecordingName;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.RecordType;
import dji.midware.data.model.P3.DataCameraGetStateInfo.PhotoState;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataCameraSetExposureMode;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.data.model.P3.DataRcGetPushParams;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.midware.media.DJIVideoDecoder;
import dji.midware.media.j.g.e;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.a;
import dji.pilot.fpv.d.c.g;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.view.DJICameraAnimView;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.publics.e.c;
import dji.pilot.publics.widget.DJISwitch;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.HashMap;

public class b implements OnClickListener, OnTouchListener, OnCheckedChangeListener, g {
    private static final boolean N = false;
    private static final int O = 1;
    private static final int P = 2;
    private static final int Q = 3;
    private static final int R = 4;
    private static final int S = 5;
    private static final int T = 6;
    private static final int U = 7;
    private static final int V = 8;
    private static final int W = 9;
    private static final int X = 10;
    private static final int Y = 11;
    private static final int Z = 12;
    public static String a = null;
    private static final int aa = 13;
    private static final int ab = 14;
    private static final int ac = 15;
    private static final int ad = 19;
    private static final int ae = 20;
    private static final int af = 21;
    private static final int ag = 22;
    private static final int ah = 23;
    private static final int ai = 26;
    private static final int aj = 27;
    public static boolean p = true;
    public static boolean u;
    private MODE aA = MODE.TAKEPHOTO;
    private MODE aB;
    private boolean aC = true;
    private AnimatorListenerAdapter aD = new 6(this);
    private AnimatorListenerAdapter aE = new 7(this);
    private final Runnable aF = new 8(this);
    private boolean aG;
    private boolean aH;
    private boolean aI;
    private boolean aJ;
    private boolean aK;
    private boolean aL;
    private boolean aM = true;
    private SDCardState aN = SDCardState.OTHER;
    private boolean aO = false;
    private int aP = -1;
    private String aQ = "";
    private int aR = 0;
    private DJIRelativeLayout ak;
    private DJIRelativeLayout al = null;
    private DJIImageView am = null;
    private DJIImageView an = null;
    private DJIImageView ao = null;
    private DJICameraAnimView ap;
    private dji.c.b aq;
    private boolean ar;
    private final int as = 300;
    private final int at = 200;
    private Animation au;
    private TimeInterpolator av = new LinearInterpolator();
    private volatile RecordType aw = RecordType.NO;
    private PhotoState ax = PhotoState.NO;
    private volatile CameraType ay = CameraType.OTHER;
    private b az = null;
    protected Handler b = new Handler(new 1(this));
    protected DJIRelativeLayout c;
    protected DJIImageView d;
    protected DJISwitch e;
    protected DJIImageView f;
    protected DJIImageView g;
    protected DJIImageView h;
    protected DJIImageView i;
    protected DJIImageView j;
    protected DJIRelativeLayout k;
    protected DJIImageView l;
    protected DJIImageView m;
    protected DJIImageView n;
    protected DJITextView o = null;
    protected Context q;
    protected a r = null;
    protected TYPE s = TYPE.b;
    protected ExposureMode t = ExposureMode.OTHER;

    public b(DJIRelativeLayout dJIRelativeLayout, DJICameraAnimView dJICameraAnimView, b bVar) {
        this.c = dJIRelativeLayout;
        this.q = this.c.getContext();
        a = d.a(this.q, "RECORD_VOICE/");
        d.a(a);
        this.e = (DJISwitch) dJIRelativeLayout.findViewById(R.id.qo);
        this.f = (DJIImageView) dJIRelativeLayout.findViewById(R.id.qp);
        this.g = (DJIImageView) dJIRelativeLayout.findViewById(R.id.qq);
        this.d = (DJIImageView) dJIRelativeLayout.findViewById(R.id.qw);
        this.l = (DJIImageView) dJIRelativeLayout.findViewById(R.id.qv);
        this.m = (DJIImageView) dJIRelativeLayout.findViewById(R.id.qs);
        this.n = (DJIImageView) dJIRelativeLayout.findViewById(R.id.qy);
        this.h = (DJIImageView) dJIRelativeLayout.findViewById(R.id.r4);
        this.j = (DJIImageView) dJIRelativeLayout.findViewById(R.id.qt);
        this.k = (DJIRelativeLayout) dJIRelativeLayout.findViewById(R.id.qr);
        this.i = (DJIImageView) dJIRelativeLayout.findViewById(R.id.qu);
        this.o = (DJITextView) dJIRelativeLayout.findViewById(R.id.qx);
        this.ak = (DJIRelativeLayout) dJIRelativeLayout.findViewById(R.id.qm);
        this.al = (DJIRelativeLayout) dJIRelativeLayout.findViewById(R.id.r0);
        this.am = (DJIImageView) dJIRelativeLayout.findViewById(R.id.r1);
        this.an = (DJIImageView) dJIRelativeLayout.findViewById(R.id.r2);
        this.ao = (DJIImageView) dJIRelativeLayout.findViewById(R.id.r3);
        this.ap = dJICameraAnimView;
        this.az = bVar;
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.ak.setOnClickListener(this);
        this.e.setOnCheckedChangeListener(this);
        this.m.setOnTouchListener(this);
        this.am.setOnClickListener(this);
        boolean z = ServiceManager.getInstance().isRemoteOK() && DataCameraGetPushStateInfo.getInstance().isGetted();
        this.l.setEnabled(z);
        this.m.setEnabled(z);
        this.n.setEnabled(z);
        this.e.setEnabled(z);
        this.h.setEnabled(z);
        this.ak.setEnabled(z);
        if (!z) {
            this.c.setAlpha(dji.pilot.visual.a.d.c);
        }
        this.aq = new dji.c.b(new 3(this));
        this.au = AnimationUtils.loadAnimation(this.q, R.anim.b_);
        this.au.setAnimationListener(new 4(this));
        dJIRelativeLayout.setOnTouchListener(new 5(this));
        onEventMainThread(dji.midware.usb.P3.a.getInstance().b());
    }

    public void a(boolean z) {
        this.h.setSelected(z);
    }

    public void b(boolean z) {
        this.ak.setSelected(z);
    }

    public void a(a aVar) {
        this.r = aVar;
        if (ServiceManager.getInstance().isRemoteOK()) {
            aVar.m();
        }
        this.s = aVar.l();
        a(this.s);
        if (c.a()) {
            p = false;
        }
        dji.thirdparty.a.c.a().a((Object) this);
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            onEventBackgroundThread(DataCameraGetPushStateInfo.getInstance());
        }
        if (DataCameraGetPushShotParams.getInstance().isGetted()) {
            onEventMainThread(DataCameraGetPushShotParams.getInstance());
        }
    }

    protected void a(int i) {
        if (i == 0) {
            this.d.setBackground(null);
        } else if (i > 14) {
            this.d.setBackgroundResource(R.drawable.record_animate_14);
        } else {
            this.d.setBackgroundResource(this.q.getResources().getIdentifier(i < 10 ? "record_animate_0" + i : "record_animate_" + i, "drawable", this.q.getPackageName()));
        }
    }

    public void a() {
        if (this.aB == MODE.TAKEPHOTO || this.aB == MODE.RECORD) {
            dji.pilot.c.d.a = this.aB;
        }
        this.aq.b();
        dji.thirdparty.a.c.a().d((Object) this);
        DataSpecialControl.getInstance().stop();
        dji.midware.media.j.g.b();
    }

    public void b() {
        if (this.c.getVisibility() != 0) {
            this.c.show();
            this.c.animate().translationX(0.0f).setDuration(300).setInterpolator(this.av).setListener(this.aD).start();
        }
    }

    public void c() {
        if (this.c.getVisibility() == 0) {
            this.c.hide();
            this.c.animate().translationX((float) (this.c.getWidth() + 10)).setDuration(300).setInterpolator(this.av).setListener(this.aE).start();
        }
    }

    private void f() {
        if (this.aB == MODE.PLAYBACK) {
            if (dji.pilot.fpv.d.b.j(this.ay)) {
                this.b.removeMessages(2);
                this.b.removeCallbacks(this.aF);
            }
            if (this.az != null) {
                this.b.sendEmptyMessage(1);
            }
        }
    }

    private void g() {
        if (!this.aC) {
            this.aC = true;
            this.b.sendEmptyMessage(6);
            this.b.sendEmptyMessage(12);
            this.b.sendMessage(this.b.obtainMessage(10, 0, 0));
        }
    }

    private void h() {
        DJILogHelper.getInstance().LOGD("", "curMode=" + this.aB, false, true);
        if (this.aB != MODE.PLAYBACK) {
            if (dji.pilot.fpv.d.b.j(this.ay)) {
                if (this.aB == null) {
                    this.b.postDelayed(this.aF, 700);
                } else if (this.az != null) {
                    this.b.sendEmptyMessage(2);
                }
            } else if (this.az != null) {
                this.b.sendEmptyMessage(2);
            }
        }
    }

    private void i() {
        RecordType recordState = DataCameraGetPushStateInfo.getInstance().getRecordState();
        if (recordState == RecordType.STARTING) {
            this.b.sendEmptyMessage(7);
        }
        if (recordState != this.aw) {
            if (recordState == RecordType.STARTING) {
                this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.n));
                this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.e));
                if (dji.pilot.fpv.d.b.j(this.ay)) {
                    this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.h));
                }
                this.aC = false;
                this.b.sendMessage(this.b.obtainMessage(10, 1, 0));
                this.ap.startVideo();
                this.s = TYPE.b;
                this.b.sendMessage(this.b.obtainMessage(3, this.s));
                j();
            } else if (recordState == RecordType.NO) {
                this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.n));
                this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.e));
                if (dji.pilot.fpv.d.b.j(this.ay)) {
                    this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.h));
                }
                this.ap.stopVideo();
                g();
                k();
            }
            this.aw = recordState;
            this.b.sendEmptyMessage(27);
        }
    }

    private void j() {
        if (!dji.pilot2.simulator.d.h() && !dji.pilot.fpv.d.b.k(null) && dji.pilot.c.a.p && DJIGenSettingDataManager.getInstance().C()) {
            dji.midware.media.j.g.a(dji.pilot.c.a.q);
            dji.midware.media.j.g.a(DJIGenSettingDataManager.getInstance().E());
            dji.thirdparty.a.c.a().e(dji.midware.media.j.g.b.a);
        }
    }

    public void onEventMainThread(e eVar) {
        if (eVar == e.a) {
            dji.pilot.fpv.view.DJIErrorPopView.b bVar = new dji.pilot.fpv.view.DJIErrorPopView.b();
            bVar.a = DJIErrorPopView.d.WARNING;
            bVar.b = R.string.videocache_nospace;
            dji.thirdparty.a.c.a().e(bVar);
        }
    }

    private void k() {
        if (!dji.pilot2.simulator.d.h() && dji.pilot.c.a.p && DJIGenSettingDataManager.getInstance().C()) {
            dji.thirdparty.a.c.a().e(dji.midware.media.j.g.b.b);
        }
    }

    private void l() {
        f();
        this.s = this.r.l();
        if (this.aC && !this.aK) {
            this.b.sendMessage(this.b.obtainMessage(3, this.s));
        }
    }

    protected void a(TYPE type) {
        switch (2.a[type.ordinal()]) {
            case 1:
                this.m.setBackgroundResource(R.drawable.camera_controll_takephoto_icon1);
                return;
            case 2:
                this.m.setBackgroundResource(R.drawable.camera_controll_takephoto_icon_hdr);
                return;
            case 3:
                this.m.setBackgroundResource(R.drawable.camera_controll_takephoto_icon_360);
                return;
            case 4:
                this.m.setBackgroundResource(R.drawable.camera_controll_takephoto_icon0);
                return;
            case 5:
                switch (this.r.n()) {
                    case 3:
                        this.m.setBackgroundResource(R.drawable.camera_controll_takephoto_icon3);
                        return;
                    case 5:
                        this.m.setBackgroundResource(R.drawable.camera_controll_takephoto_icon5);
                        return;
                    case 7:
                        this.m.setBackgroundResource(R.drawable.camera_controll_takephoto_icon7);
                        return;
                    case 14:
                        this.m.setBackgroundResource(R.drawable.camera_controll_takephoto_icon7);
                        return;
                    default:
                        this.m.setBackgroundResource(R.drawable.camera_controll_takephoto_icon3);
                        return;
                }
            case 6:
                switch (this.r.n()) {
                    case 3:
                        this.m.setBackgroundResource(R.drawable.camera_controll_takephoto_icon_aeb3);
                        return;
                    case 5:
                        this.m.setBackgroundResource(R.drawable.camera_controll_takephoto_icon_aeb5);
                        return;
                    case 7:
                        this.m.setBackgroundResource(R.drawable.camera_controll_takephoto_icon_aeb5);
                        return;
                    default:
                        this.m.setBackgroundResource(R.drawable.camera_controll_takephoto_icon_aeb3);
                        return;
                }
            default:
                this.m.setBackgroundResource(R.drawable.camera_controll_takephoto_icon1);
                return;
        }
    }

    private String b(int i) {
        int[] f = dji.pilot.fpv.d.b.f(i);
        return this.c.getContext().getString(R.string.fpv_videotime, new Object[]{Integer.valueOf(f[2]), Integer.valueOf(f[1]), Integer.valueOf(f[0])});
    }

    protected void d() {
        p = true;
        dji.pilot.c.d.a = this.aA;
        DataSpecialControl.getInstance().setPlayBackType(true).start(20);
    }

    private void m() {
        if (i.getInstance().c() == ProductType.OrangeRAW && dji.pilot.fpv.d.b.t()) {
            DJILogHelper.getInstance().LOGD("", "caseX5RException", false, true);
        } else if (this.s == TYPE.g && this.aK) {
            this.ap.stopVideo();
            DataSpecialControl.getInstance().setPhotoType(TYPE.a).start(20);
        } else if (this.s == TYPE.g) {
            DataSpecialControl.getInstance().setPhotoType(this.s, 255, this.r.n()).start(20);
        } else {
            DataSpecialControl.getInstance().setPhotoType(this.s, this.r.n(), 0).start(20);
        }
    }

    private boolean n() {
        ProductType c = i.getInstance().c();
        return c == ProductType.litchiS || c == ProductType.litchiC || c == ProductType.Longan || c == ProductType.P34K || c == ProductType.KumquatX || c == ProductType.KumquatS;
    }

    private void o() {
        if (!n()) {
            return;
        }
        if (this.s == TYPE.c || this.s == TYPE.f || this.s == TYPE.e || this.s == TYPE.b) {
            DJIVideoDecoder e = ServiceManager.getInstance().e();
            if (e != null) {
                DJILogHelper.getInstance().LOGD("cameracontroller", "fixDelayStream start");
                e.setConnectLosedelay(26000);
                this.b.removeMessages(23);
                this.b.sendEmptyMessageDelayed(23, 24000);
            }
        }
    }

    private void p() {
        if (i.getInstance().c() == ProductType.OrangeRAW && dji.pilot.fpv.d.b.t()) {
            DJILogHelper.getInstance().LOGD("", "caseX5RException", false, true);
        } else if (DataCameraGetPushRawParams.getInstance().isGetted() && DataCameraGetPushStateInfo.getInstance().getCameraType() == CameraType.DJICameraTypeFC550Raw && DataCameraGetPushRawParams.getInstance().getDiskStatus() == DiskStatus.FULL && this.aC) {
            dji.pilot.publics.widget.b.a(this.q, R.string.fpv_record_ssd_full_tittle, R.string.fpv_record_ssd_full_tip, R.string.app_cancel, null, R.string.app_isee, new 9(this)).show();
        } else {
            DataSpecialControl.getInstance().setRecordType(this.aC).start(20);
        }
    }

    private void a(MODE mode) {
        i();
        switch (2.b[mode.ordinal()]) {
            case 1:
                l();
                break;
            case 2:
                f();
                break;
            case 3:
                h();
                break;
        }
        this.aB = mode;
        if (this.aB == MODE.TAKEPHOTO || this.aB == MODE.RECORD) {
            this.aA = this.aB;
        }
        this.b.sendEmptyMessage(14);
    }

    private void q() {
        if (this.aB == MODE.TAKEPHOTO) {
            if (this.e.isChecked()) {
                this.e.setChecked(false);
                c(false);
            }
            if (dji.pilot.fpv.d.b.j(this.ay)) {
                this.al.go();
            }
        } else if (this.aB == MODE.RECORD) {
            if (!this.e.isChecked()) {
                this.e.setChecked(true);
                c(true);
            }
            if (dji.pilot.fpv.d.b.j(this.ay)) {
                this.al.show();
            }
        }
    }

    public void onEventBackgroundThread(DataRcGetPushParams dataRcGetPushParams) {
        boolean recordStatus = dataRcGetPushParams.getRecordStatus();
        if (this.aG != recordStatus) {
            this.aG = recordStatus;
            if (recordStatus) {
                dji.pilot.fpv.d.e.c(s.cQ);
            }
        }
        recordStatus = dataRcGetPushParams.getShutterStatus();
        if (this.aH != recordStatus) {
            this.aH = recordStatus;
            if (recordStatus) {
                dji.pilot.fpv.d.e.c(s.cP);
            }
        }
        recordStatus = dataRcGetPushParams.getPlayBackStatus();
        if (this.aI != recordStatus) {
            this.aI = recordStatus;
            if (this.aI) {
                dji.pilot.fpv.d.e.c(s.cO);
            }
        }
    }

    private void a(DataCameraGetPushStateInfo dataCameraGetPushStateInfo, boolean z) {
        boolean isTimePhotoing = dataCameraGetPushStateInfo.getIsTimePhotoing();
        if (this.aK != isTimePhotoing || z) {
            this.aK = isTimePhotoing;
            this.b.sendEmptyMessage(8);
        }
        if (this.aK && !this.aO) {
            this.aO = true;
            this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.n));
            this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.e));
        } else if (!this.aK && this.aO) {
            this.aO = false;
            this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.n));
            this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.e));
        }
        PhotoState photoState = dataCameraGetPushStateInfo.getPhotoState();
        if (this.ax != photoState || z) {
            this.ax = photoState;
            DJILogHelper.getInstance().LOGE("", "photoState " + photoState, false, true);
            if (photoState == PhotoState.NO) {
                if (!z) {
                    this.aM = true;
                }
                if (!this.aK) {
                    this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.m));
                    this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.am));
                }
                if (!((this.aB == MODE.RECORD && dataCameraGetPushStateInfo.getRecordState() == RecordType.STARTING) || this.aK)) {
                    this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.n));
                    this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.e));
                }
                if (!(dji.pilot.fpv.d.b.j(this.ay) && this.aB == MODE.RECORD && dataCameraGetPushStateInfo.getRecordState() == RecordType.STARTING)) {
                    this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.h));
                }
            } else if (z) {
                if (!this.aK) {
                    this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.m));
                    this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.am));
                }
                this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.n));
                this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.e));
                this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.h));
            } else if (this.aM) {
                this.aM = false;
                o();
                this.b.sendEmptyMessage(4);
                if (!this.aK) {
                    this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.m));
                    this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.am));
                }
                this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.n));
                this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.e));
                this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.h));
            }
        }
        SDCardState sDCardState = dataCameraGetPushStateInfo.getSDCardState(true);
        if (!this.aN._equals(sDCardState.value()) || z) {
            DJILogHelper.getInstance().LOGE("", "curSdcardState=" + sDCardState, false, true);
            this.aN = sDCardState;
            switch (2.c[this.aN.ordinal()]) {
                case 1:
                    this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.l));
                    this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.m));
                    this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.am));
                    this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.n));
                    return;
                case 2:
                case 3:
                case 4:
                    this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.l));
                    this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.m));
                    this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.am));
                    this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.n));
                    return;
                default:
                    this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.l));
                    this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.m));
                    this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.am));
                    this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.n));
                    return;
            }
        }
    }

    private void r() {
        this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.e));
        this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.h));
        this.b.sendMessage(this.b.obtainMessage(9, 1, 0, this.ak));
        this.b.sendMessage(this.b.obtainMessage(20, 1, 0));
        this.r.m();
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (this.ay != cameraType) {
            if (CameraType.OTHER == this.ay) {
                r();
            }
            this.ay = cameraType;
            this.b.obtainMessage(26, dji.pilot.fpv.d.b.j(cameraType) ? 1 : 0, 0).sendToTarget();
        }
        boolean isStoring = dataCameraGetPushStateInfo.getIsStoring();
        if (this.aL != isStoring) {
            this.aL = isStoring;
            this.b.removeMessages(21);
            DJILogHelper.getInstance().LOGE("cameracontroller", "isStroing " + isStoring + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.ar, false, true);
            if (!isStoring) {
                this.b.removeMessages(22);
                this.b.sendEmptyMessageDelayed(21, 200);
            } else if (!this.ar) {
                this.b.sendEmptyMessage(22);
            }
        }
        isStoring = dataCameraGetPushStateInfo.getEnabledPhoto();
        if (this.aJ != isStoring) {
            this.aJ = isStoring;
            DJILogHelper.getInstance().LOGE("", "isEnabledPhoto=" + isStoring, false, true);
        }
        a(dataCameraGetPushStateInfo, false);
        a(dataCameraGetPushStateInfo.getMode());
        if (dataCameraGetPushStateInfo.getVideoRecordTime() >= 1765) {
            DataSpecialControl.getInstance().setRecordType(false, 0, 0).start(20);
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        int focusStatus = dataCameraGetPushShotInfo.getFocusStatus();
        if (this.aP != focusStatus) {
            this.aP = focusStatus;
            if (1 == focusStatus) {
                this.l.setEnabled(false);
                this.m.setEnabled(false);
                this.am.setEnabled(false);
                this.n.setEnabled(false);
                this.e.setEnabled(false);
                this.h.setEnabled(false);
                this.ak.setEnabled(false);
                return;
            }
            this.ak.setEnabled(true);
            a(DataCameraGetPushStateInfo.getInstance(), true);
        }
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        CameraType cameraType = this.ay;
        if (cameraType == CameraType.OTHER) {
            cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        }
        if (!dji.pilot.fpv.d.b.j(cameraType)) {
            a(dataCameraGetPushShotParams.getExposureMode());
        }
    }

    protected void a(ExposureMode exposureMode) {
        if (this.t != exposureMode) {
            this.t = exposureMode;
            if (ExposureMode.M == exposureMode) {
                this.h.setImageResource(R.drawable.camera_controll_advanced_icon_m);
            } else if (ExposureMode.S == exposureMode) {
                this.h.setImageResource(R.drawable.camera_controll_advanced_icon_s);
            } else if (ExposureMode.d == exposureMode) {
                this.h.setImageResource(R.drawable.camera_controll_advanced_icon_a);
            } else if (ExposureMode.P == exposureMode) {
                this.h.setImageResource(R.drawable.camera_controll_advanced_icon_non);
            } else if (ExposureMode.C == exposureMode) {
                this.h.setImageResource(R.drawable.camera_controll_advanced_icon_c);
            } else {
                this.h.setImageResource(R.drawable.camera_controll_advanced_icon_non);
            }
        }
    }

    protected void e() {
        if (!this.ar) {
            u = true;
            this.ar = true;
            if (this.aB == MODE.RECORD) {
                this.ao.startAnimation(this.au);
            } else if (this.aB == MODE.TAKEPHOTO && this.c.isShown()) {
                this.i.startAnimation(this.au);
            }
        }
    }

    public void onEventBackgroundThread(a.a aVar) {
        if (a.a.a == aVar) {
            a(DataCameraGetPushStateInfo.getInstance().getMode());
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushRecordingName dataCameraGetPushRecordingName) {
    }

    public void onEventBackgroundThread(o oVar) {
        switch (2.d[oVar.ordinal()]) {
            case 1:
                if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
                    r();
                    return;
                }
                return;
            case 2:
                if (this.aB == MODE.PLAYBACK) {
                    this.b.sendEmptyMessage(1);
                }
                this.aP = -1;
                this.aB = MODE.OTHER;
                this.ay = CameraType.OTHER;
                DataCameraGetPushStateInfo.getInstance().clear();
                this.aN = SDCardState.OTHER;
                this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.l));
                this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.m));
                this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.am));
                this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.n));
                this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.e));
                this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.h));
                this.b.sendMessage(this.b.obtainMessage(9, 0, 0, this.ak));
                this.b.sendMessage(this.b.obtainMessage(20, 0, 0));
                this.b.sendEmptyMessage(23);
                return;
            default:
                return;
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        MODE mode = z ? MODE.RECORD : MODE.TAKEPHOTO;
        if (this.aB != mode && ServiceManager.getInstance().isRemoteOK()) {
            if (DataCameraGetPushShotParams.getInstance().getExposureMode() == ExposureMode.C) {
                new DataCameraSetExposureMode().a(ExposureMode.P.a()).start(null);
            }
            this.e.setEnabled(false);
            this.l.setEnabled(false);
            this.m.setEnabled(false);
            this.am.setEnabled(false);
            DataCameraSetMode.getInstance().a(mode).start(new 10(this, mode, z));
        }
    }

    private void c(boolean z) {
        if (z) {
            this.l.show();
            this.k.hide();
            this.g.setEnabled(true);
            this.f.setEnabled(false);
        } else {
            this.l.hide();
            this.k.show();
            this.g.setEnabled(false);
            this.f.setEnabled(true);
        }
        this.b.sendEmptyMessageDelayed(15, 500);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (R.id.r4 == id) {
            if (dji.pilot.fpv.d.b.j(this.ay)) {
                if (ServiceManager.getInstance().isConnected()) {
                    dji.pilot.fpv.d.e.a("FPV_RightBarView_CameraControllView_Button_PlayBack");
                    d();
                }
            } else if (this.az != null) {
                dji.pilot.fpv.d.e.a("FPV_RightBarView_CameraControllView_Button_Advanced");
                this.az.c();
            }
        } else if (R.id.qm == id) {
            if (this.az != null) {
                dji.pilot.fpv.d.e.a("FPV_RightBarView_CameraControllView_Button_Fn");
                this.az.d();
            }
        } else if (ServiceManager.getInstance().isConnected()) {
            switch (id) {
                case R.id.qs:
                    return;
                case R.id.qv:
                    dji.pilot.fpv.d.e.a("FPV_RightBarView_CameraControllView_Button_TakeVideo");
                    dji.pilot.fpv.d.e.c(g.I);
                    p();
                    return;
                case R.id.qy:
                    dji.pilot.fpv.d.e.a("FPV_RightBarView_CameraControllView_Button_PlayBack");
                    d();
                    return;
                case R.id.r1:
                    DataSpecialControl.getInstance().setPhotoType(TYPE.b, 1, 0).start(20);
                    this.am.setEnabled(false);
                    this.b.sendMessageDelayed(this.b.obtainMessage(9, 1, 0, this.am), 200);
                    return;
                default:
                    return;
            }
        }
    }

    private void s() {
        this.aR = 0;
        this.j.setBackgroundResource(R.drawable.camera_controll_video_bg);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                if (!(this.b.hasMessages(13) || this.aK)) {
                    break;
                }
            case 1:
            case 3:
                if (this.aR == 0) {
                    dji.pilot.fpv.d.e.a("FPV_RightBarView_CameraControllView_Button_TakePhoto");
                    HashMap hashMap = new HashMap();
                    hashMap.put(dji.pilot.fpv.d.d.dI, i.getInstance().c().toString());
                    dji.pilot.fpv.d.e.a(g.H, hashMap);
                    m();
                    this.m.setEnabled(false);
                    if (dji.pilot.fpv.d.b.j(this.ay)) {
                        this.b.sendMessageDelayed(this.b.obtainMessage(9, 1, 0, this.m), 200);
                    } else {
                        this.b.sendMessageDelayed(this.b.obtainMessage(9, 1, 0, this.m), 500);
                    }
                }
                this.b.removeMessages(13);
                s();
                break;
        }
        return false;
    }

    public void onEventMainThread(dji.midware.usb.P3.a.c cVar) {
        if (dji.pilot.publics.e.a.d(null)) {
            if (cVar == dji.midware.usb.P3.a.c.a) {
                this.n.setEnabled(false);
            } else if (cVar == dji.midware.usb.P3.a.c.b) {
                this.n.setEnabled(true);
            }
            CameraType b = i.getInstance().b();
            if (b != CameraType.DJICameraTypeFC350 && b != CameraType.DJICameraTypeFC550 && b != CameraType.DJICameraTypeFC550Raw && b != CameraType.DJICameraTypeTau336 && b != CameraType.DJICameraTypeTau640) {
                this.n.setEnabled(false);
            }
        }
    }
}
