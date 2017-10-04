package dji.pilot.fpv.camera.newfn;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ScrollView;
import dji.common.camera.DJICameraSettingsDef.CameraOrientation;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.RecordType;
import dji.midware.data.model.P3.DataCameraGetPushTauParam;
import dji.midware.data.model.P3.DataCameraGetPushTauParam.LenFocusLength;
import dji.midware.data.model.P3.DataCameraGetPushTauParam.LenFps;
import dji.midware.data.model.P3.DataCameraGetPushTauParam.VideoResolution;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetFileIndexMode.FileIndexMode;
import dji.midware.data.model.P3.DataCameraSetFocusAid;
import dji.midware.data.model.P3.DataCameraSetForeArmLed;
import dji.midware.data.model.P3.DataCameraSetLockGimbalWhenShot;
import dji.midware.data.model.P3.DataCameraSetMCTF;
import dji.midware.data.model.P3.DataCameraSetQuickPlayBack;
import dji.midware.data.model.P3.DataCameraTauExterParamType.ExterParamType;
import dji.midware.data.model.P3.DataCameraTauFFCMode.FFCMode;
import dji.midware.data.model.P3.DataCameraTauParamAGC.AGCType;
import dji.midware.data.model.P3.DataCameraTauParamGainMode.GainMode;
import dji.midware.data.model.P3.DataCameraTauParamROI.ROIType;
import dji.midware.data.model.P3.DataCameraTauParamThermometricEnable.ThermometricType;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.control.DJIGenSettingDataManager.c;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.objects.g;
import dji.pilot.publics.widget.DJISwitch;
import dji.pilot.publics.widget.b;
import dji.pilot.visual.a.g.e;
import dji.pilot.visual.a.g.f;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.c.d;
import java.text.DecimalFormat;

public class DJICameraFnOtherView extends ScrollView implements a, d {
    private static final String f = "key_open_bg_download";
    private DJISwitch A = null;
    private DJISwitch B = null;
    private DJITextView C = null;
    private DJITextView D = null;
    private DJITextView E = null;
    private DJITextView F = null;
    private DJITextView G = null;
    private DJITextView H = null;
    private DJITextView I = null;
    private DJITextView J = null;
    private DJITextView K = null;
    private DJITextView L = null;
    private DJITextView M = null;
    private DJITextView N = null;
    private DJIRelativeLayout O = null;
    private DJIRelativeLayout P = null;
    private DJIRelativeLayout Q = null;
    private DJIRelativeLayout R = null;
    private DJIRelativeLayout S = null;
    private DJIRelativeLayout T = null;
    private DJIRelativeLayout U = null;
    private DJIRelativeLayout V = null;
    private DJIRelativeLayout W = null;
    private final int a = -1;
    private boolean aA = false;
    private boolean aB = false;
    private volatile boolean aC = false;
    private volatile boolean aD = false;
    private volatile boolean aE = false;
    private ROIType aF = ROIType.d;
    private int aG = Integer.MAX_VALUE;
    private AGCType aH = AGCType.j;
    private boolean aI = false;
    private GainMode aJ = GainMode.a;
    private VideoResolution aK = VideoResolution.UNKNOWN;
    private LenFocusLength aL = LenFocusLength.UNKNOWN;
    private LenFps aM = LenFps.UNKNOWN;
    private boolean aN = false;
    private FFCMode aO = FFCMode.b;
    private boolean aP = false;
    private ExterParamType aQ = ExterParamType.d;
    private dji.pilot.fpv.camera.more.a aR = dji.pilot.fpv.camera.more.a.getInstance();
    private DJIGenSettingDataManager aS = DJIGenSettingDataManager.getInstance();
    private c aT = null;
    private b aU = null;
    private b aV = null;
    private int aW = -1;
    private OnCheckedChangeListener aX = null;
    private OnClickListener aY = null;
    private final DecimalFormat aZ = new DecimalFormat("#.##");
    private DJIRelativeLayout aa = null;
    private DJIRelativeLayout ab = null;
    private DJIRelativeLayout ac = null;
    private DJIRelativeLayout ad = null;
    @dji.thirdparty.afinal.a.b.c(a = 2131362287)
    private DJIRelativeLayout ae = null;
    @dji.thirdparty.afinal.a.b.c(a = 2131362295)
    private DJIRelativeLayout af = null;
    @dji.thirdparty.afinal.a.b.c(a = 2131362339)
    private DJIRelativeLayout ag = null;
    @dji.thirdparty.afinal.a.b.c(a = 2131362273)
    private DJIRelativeLayout ah = null;
    private DJIRelativeLayout ai = null;
    private DJIRelativeLayout aj = null;
    private DJIRelativeLayout ak = null;
    private DJIRelativeLayout al = null;
    private DJIRelativeLayout am = null;
    private DJIRelativeLayout an = null;
    private DJIRelativeLayout ao = null;
    private DJIRelativeLayout ap = null;
    private DJIRelativeLayout aq = null;
    private DJIRelativeLayout ar = null;
    private DJIRelativeLayout as = null;
    private FileIndexMode at = FileIndexMode.a;
    private int au = Integer.MAX_VALUE;
    private RecordType av = RecordType.OTHER;
    private int aw = Integer.MAX_VALUE;
    private volatile boolean ax = false;
    private volatile boolean ay = false;
    private volatile boolean az = false;
    private final int b = 0;
    private boolean ba = false;
    private final int c = 1;
    private final int d = 2;
    private final int e = 3;
    private DJISwitch g = null;
    private DJIImageView h = null;
    private DJIImageView i = null;
    private DJIImageView j = null;
    private DJITextView k = null;
    private DJISwitch l = null;
    private DJISwitch m = null;
    private DJISwitch n = null;
    private DJISwitch o = null;
    private DJISwitch p = null;
    private DJISwitch q = null;
    private DJIImageView r = null;
    private DJITextView s = null;
    private DJISwitch t = null;
    @dji.thirdparty.afinal.a.b.c(a = 2131362288)
    private DJISwitch u = null;
    @dji.thirdparty.afinal.a.b.c(a = 2131362296)
    private DJISwitch v = null;
    @dji.thirdparty.afinal.a.b.c(a = 2131362272)
    private DJISwitch w;
    private DJITextView x = null;
    private DJITextView y = null;
    private DJISwitch z = null;

    public DJICameraFnOtherView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onEventMainThread(DataCameraGetPushTauParam dataCameraGetPushTauParam) {
        boolean z = true;
        if (dji.pilot.fpv.d.b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            ROIType rOIType = dataCameraGetPushTauParam.getROIType();
            if (this.aF != rOIType) {
                this.aF = rOIType;
                this.E.setText(this.aR.au()[dji.pilot.fpv.camera.more.a.a(this.aR.av(), rOIType.a(), 0)]);
            }
            boolean isIsothermEnable = dataCameraGetPushTauParam.isIsothermEnable();
            if (this.aI != isIsothermEnable) {
                this.aI = isIsothermEnable;
                this.H.setText(isIsothermEnable ? R.string.app_on : R.string.app_off);
                isIsothermEnable = true;
            } else {
                isIsothermEnable = false;
            }
            int digitalFilter = dataCameraGetPushTauParam.getDigitalFilter();
            if (digitalFilter != this.aG || r0) {
                this.aG = digitalFilter;
                this.F.setText(this.aR.aw()[dji.pilot.fpv.camera.more.a.a(this.aR.ax(), digitalFilter, 0)]);
            }
            AGCType agc = dataCameraGetPushTauParam.getAGC();
            if (agc != this.aH) {
                this.aH = agc;
                this.G.setText(this.aR.ay()[dji.pilot.fpv.camera.more.a.a(this.aR.az(), agc.a(), 0)]);
            }
            GainMode gainMode = dataCameraGetPushTauParam.getGainMode();
            if (this.aJ != gainMode) {
                this.aJ = gainMode;
                this.I.setText(this.aR.ar()[dji.pilot.fpv.camera.more.a.a(this.aR.as(), gainMode.a(), 0)]);
            }
            FFCMode fFCMode = dataCameraGetPushTauParam.getFFCMode();
            if (fFCMode != this.aO) {
                this.aO = fFCMode;
                this.K.setText(this.aR.aF()[dji.pilot.fpv.camera.more.a.a(this.aR.aG(), fFCMode.a(), 0)]);
            }
            if (dataCameraGetPushTauParam.getThermometricType() != ThermometricType.c) {
                z = false;
            }
            if (z != this.aP) {
                this.aP = z;
                m();
            }
            ExterParamType exterParamType = dataCameraGetPushTauParam.getExterParamType();
            if (exterParamType != this.aQ && this.as.getVisibility() == 0) {
                this.aQ = exterParamType;
                if (exterParamType == ExterParamType.b) {
                    this.N.setText(R.string.tau_param_c2);
                } else if (exterParamType == ExterParamType.c) {
                    this.N.setText(R.string.tau_param_c3);
                } else {
                    this.N.setText(R.string.tau_param_c1);
                }
            }
            a(dataCameraGetPushTauParam, false);
            return;
        }
        this.aP = false;
    }

    private void a(DataCameraGetPushTauParam dataCameraGetPushTauParam, boolean z) {
        VideoResolution videoResolution = dataCameraGetPushTauParam.getVideoResolution();
        LenFocusLength lenFocusLength = dataCameraGetPushTauParam.getLenFocusLength();
        LenFps lenFps = dataCameraGetPushTauParam.getLenFps();
        boolean supportSpotThermometric = dataCameraGetPushTauParam.supportSpotThermometric();
        if (z || this.aK != videoResolution || this.aL != lenFocusLength || this.aM != lenFps || this.aN != supportSpotThermometric) {
            this.aK = videoResolution;
            this.aL = lenFocusLength;
            this.aM = lenFps;
            this.aN = supportSpotThermometric;
            if (dji.pilot.publics.e.d.a(dji.pilot.fpv.camera.more.b.a(getContext(), videoResolution, lenFocusLength, lenFps, supportSpotThermometric))) {
                this.J.go();
                return;
            }
            this.J.show();
            this.J.setText(getContext().getString(R.string.tau_camera_profile, new Object[]{r0}));
        }
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (!dji.pilot.fpv.d.b.j(DataCameraGetPushStateInfo.getInstance().getCameraType())) {
            int antiFlicker = dataCameraGetPushShotParams.getAntiFlicker();
            if (this.aw != antiFlicker) {
                this.aw = antiFlicker;
                if (dji.pilot.fpv.d.b.k(null)) {
                    this.i.setImageResource(dji.pilot.fpv.camera.more.a.w_[antiFlicker]);
                } else {
                    this.i.setImageResource(dji.pilot.fpv.camera.more.a.w_[this.aR.w(antiFlicker)]);
                }
            }
            boolean isMCTFEnable = dataCameraGetPushShotParams.isMCTFEnable();
            if (this.ax != isMCTFEnable) {
                this.ax = isMCTFEnable;
                this.q.setChecked(isMCTFEnable);
            }
            isMCTFEnable = dataCameraGetPushShotParams.autoAEUnlock();
            if (isMCTFEnable != this.az) {
                this.az = isMCTFEnable;
                this.t.setChecked(isMCTFEnable);
            }
            isMCTFEnable = dataCameraGetPushShotParams.autoTurnOffForeLed();
            if (isMCTFEnable != this.ay) {
                this.ay = isMCTFEnable;
                this.p.setChecked(isMCTFEnable);
            }
            this.w.setChecked(dataCameraGetPushShotParams.isLockedGimbalWhenShot());
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        int fastPlayBackTime;
        this.aS.e();
        FileIndexMode fileIndexMode = dataCameraGetPushStateInfo.getFileIndexMode();
        if (fileIndexMode != this.at) {
            this.at = fileIndexMode;
            this.k.setText(this.aR.ag()[this.aR.u(fileIndexMode.a())]);
        }
        boolean fastPlayBackEnabled = dataCameraGetPushStateInfo.getFastPlayBackEnabled();
        if (fastPlayBackEnabled) {
            fastPlayBackTime = (byte) dataCameraGetPushStateInfo.getFastPlayBackTime();
            if (fastPlayBackTime > dji.pilot.fpv.camera.more.a.B_.length) {
                fastPlayBackTime = 0;
            }
        } else {
            fastPlayBackTime = 0;
        }
        if (this.au != fastPlayBackTime) {
            this.au = fastPlayBackTime;
            this.j.setImageResource(dji.pilot.fpv.camera.more.a.B_[fastPlayBackTime]);
        }
        if (this.aA != fastPlayBackEnabled) {
            this.aA = fastPlayBackEnabled;
            this.u.setChecked(fastPlayBackEnabled);
        }
        RecordType recordState = dataCameraGetPushStateInfo.getRecordState();
        if (this.av != recordState) {
            this.av = recordState;
            if (recordState == RecordType.START || recordState == RecordType.STARTING) {
                this.n.setEnabled(false);
                this.q.setEnabled(false);
                this.x.setEnabled(false);
                this.x.setAlpha(0.3f);
                return;
            }
            this.n.setEnabled(true);
            this.q.setEnabled(true);
            this.x.setEnabled(true);
            this.x.setAlpha(1.0f);
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        boolean isDigitalFocusEnable = dataCameraGetPushShotInfo.isDigitalFocusEnable();
        if (this.aE != isDigitalFocusEnable) {
            this.aE = isDigitalFocusEnable;
            this.z.setChecked(isDigitalFocusEnable);
        }
        isDigitalFocusEnable = dataCameraGetPushShotInfo.isDigitalFocusAEnable();
        if (this.aC != isDigitalFocusEnable) {
            this.aC = isDigitalFocusEnable;
            this.A.setChecked(isDigitalFocusEnable);
        }
        isDigitalFocusEnable = dataCameraGetPushShotInfo.isDigitalFocusMEnable();
        if (this.aD != isDigitalFocusEnable) {
            this.aD = isDigitalFocusEnable;
            this.B.setChecked(isDigitalFocusEnable);
        }
    }

    public void onEventMainThread(e eVar) {
        if (dji.pilot.visual.a.c.getInstance().l() && eVar == e.b) {
            this.S.setEnabled(false);
        } else {
            this.S.setEnabled(true);
        }
    }

    public void onEventMainThread(f fVar) {
        if (dji.pilot.visual.a.c.getInstance().l()) {
            onEventMainThread(dji.pilot.visual.a.c.getInstance().f());
        } else {
            this.S.setEnabled(true);
        }
    }

    private void a() {
        RecordType recordState = DataCameraGetPushStateInfo.getInstance().getRecordState();
        if (recordState == RecordType.START || recordState == RecordType.STARTING) {
            this.n.setEnabled(false);
        } else {
            this.n.setEnabled(true);
        }
        boolean j = this.aS.j();
        if (this.n.isChecked() != j) {
            this.n.setChecked(j);
        }
    }

    private void a(int i) {
        if (11 == i) {
            this.n.setEnabled(false);
        }
    }

    private void a(int i, int i2, dji.midware.data.config.P3.a aVar) {
        if (11 == i) {
            a();
        }
    }

    private void b(int i) {
        if (1 == i) {
            this.x.setEnabled(false);
        } else if (2 == i) {
            this.y.setEnabled(false);
        } else if (3 == i) {
            this.C.setEnabled(false);
        } else if (13 == i) {
            this.l.setEnabled(false);
        } else if (11 == i) {
            this.n.setEnabled(false);
        }
    }

    private void b(int i, int i2, dji.midware.data.config.P3.a aVar) {
        boolean z = true;
        if (1 == i) {
            this.x.setEnabled(true);
        } else if (2 == i) {
            this.y.setEnabled(true);
            if (i2 == 0) {
                a(2, getContext().getString(R.string.fpv_gensetting_format_sdcard_success));
            } else {
                a(2, getContext().getString(R.string.fpv_gensetting_format_sdcard_fail));
            }
        } else if (3 == i) {
            this.C.setEnabled(true);
            if (i2 == 0) {
                a(2, getContext().getString(R.string.fpv_gensetting_format_ssd_success));
            } else {
                a(2, getContext().getString(R.string.fpv_gensetting_format_ssd_fail));
            }
        } else if (13 == i) {
            this.l.setEnabled(true);
            if (i2 == 1) {
                boolean isChecked = this.l.isChecked();
                DJISwitch dJISwitch = this.l;
                if (isChecked) {
                    z = false;
                }
                dJISwitch.setChecked(z);
            }
        } else if (11 == i) {
            a();
        }
    }

    private void c(int i) {
        if (4 == i) {
            b();
        } else if (13 == i) {
            c();
        } else if (14 == i) {
            this.o.setChecked(this.aS.F());
        }
    }

    private void a(CompoundButton compoundButton, boolean z) {
        int i = 0;
        if (compoundButton == this.l) {
            this.aS.b(z);
        } else if (compoundButton == this.m) {
            if (z != this.aS.m() && z != this.ba && dji.pilot.visual.util.c.h()) {
                this.m.setEnabled(false);
                DJISDKCache.getInstance().setValue(dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.bW), z ? CameraOrientation.Portrait : CameraOrientation.Landscape, new 1(this, z));
            }
        } else if (compoundButton == this.g) {
            if (z != DJIGenSettingDataManager.getInstance().B()) {
                DJIGenSettingDataManager.getInstance().h(z);
            }
        } else if (compoundButton == this.n) {
            this.aS.a(z);
        } else if (compoundButton == this.o) {
            this.aS.m(z);
        } else if (compoundButton == this.z) {
            if (this.aE != z) {
                this.z.setEnabled(false);
                DataCameraSetFocusAid.getInstance().a(z, false).start(new 10(this, z));
            }
        } else if (compoundButton == this.A) {
            if (this.aC != z) {
                this.A.setEnabled(false);
                DataCameraSetFocusAid.getInstance().a(z, this.aD).start(new 11(this, z));
            }
        } else if (compoundButton == this.B) {
            if (this.aD != z) {
                this.B.setEnabled(false);
                DataCameraSetFocusAid.getInstance().a(this.aC, z).start(new 12(this, z));
            }
        } else if (compoundButton == this.q) {
            if (this.ax != z) {
                this.q.setEnabled(false);
                DataCameraSetMCTF.getInstance().a(z).start(new 13(this, z));
            }
        } else if (compoundButton == this.p) {
            if (this.ay != z) {
                this.p.setEnabled(false);
                DataCameraSetForeArmLed.getInstance().a(z).start(new 14(this, z));
            }
        } else if (compoundButton == this.t) {
            if (this.az != z) {
                this.t.setEnabled(false);
                DataBaseCameraSetting a = new DataBaseCameraSetting().a(dji.midware.data.config.P3.b.a.SetAEUnlockMode);
                if (!z) {
                    i = 1;
                }
                a.a(i).start(new 15(this, z));
            }
        } else if (compoundButton == this.u) {
            if (this.aA != z) {
                this.u.setEnabled(false);
                if (z) {
                    i = 2;
                }
                byte b = (byte) i;
                DataCameraSetQuickPlayBack.getInstance().a(true);
                DataCameraSetQuickPlayBack.getInstance().a(b);
                DataCameraSetQuickPlayBack.getInstance().start(new 16(this, z));
            }
        } else if (compoundButton == this.v) {
            if (DataCameraGetPushStateInfo.getInstance().beInDebugMode() == z) {
            }
        } else if (compoundButton == this.w) {
            DataCameraSetLockGimbalWhenShot.getInstance().a(z).start(new 2(this));
        }
    }

    private void b() {
        this.h.setImageResource(dji.pilot.fpv.camera.more.a.z_[dji.pilot.fpv.camera.more.a.a(this.aR.ab(), this.aS.k(), 0)]);
    }

    private void c() {
        boolean l = this.aS.l();
        if (this.l.isChecked() != l) {
            this.l.setChecked(l);
        }
    }

    private void d() {
        if (this.g != null) {
            this.g.setChecked(g.b(getContext(), f, true));
        }
    }

    private void e() {
        DJISDKCache.getInstance().getValue(dji.sdksharedlib.a.b.b(dji.sdksharedlib.b.b.bW), new 3(this));
    }

    private void f() {
        this.aT = new 4(this);
        this.aX = new 5(this);
        this.aY = new 6(this);
    }

    private void g() {
        this.h = (DJIImageView) findViewById(R.id.n0);
        this.i = (DJIImageView) findViewById(R.id.n9);
        this.k = (DJITextView) findViewById(R.id.ne);
        this.j = (DJIImageView) findViewById(R.id.nb);
        this.l = (DJISwitch) findViewById(R.id.m8);
        this.m = (DJISwitch) findViewById(R.id.mb);
        this.g = (DJISwitch) findViewById(R.id.md);
        this.n = (DJISwitch) findViewById(R.id.mf);
        this.o = (DJISwitch) findViewById(R.id.mh);
        this.q = (DJISwitch) findViewById(R.id.mr);
        this.p = (DJISwitch) findViewById(R.id.mt);
        this.r = (DJIImageView) findViewById(R.id.n3);
        this.s = (DJITextView) findViewById(R.id.n6);
        this.t = (DJISwitch) findViewById(R.id.mv);
        this.x = (DJITextView) findViewById(R.id.o9);
        this.y = (DJITextView) findViewById(R.id.o_);
        this.Q = (DJIRelativeLayout) findViewById(R.id.my);
        this.R = (DJIRelativeLayout) findViewById(R.id.n7);
        this.T = (DJIRelativeLayout) findViewById(R.id.nc);
        this.S = (DJIRelativeLayout) findViewById(R.id.n_);
        this.O = (DJIRelativeLayout) findViewById(R.id.m7);
        this.P = (DJIRelativeLayout) findViewById(R.id.me);
        this.V = (DJIRelativeLayout) findViewById(R.id.mg);
        this.aa = (DJIRelativeLayout) findViewById(R.id.mq);
        this.W = (DJIRelativeLayout) findViewById(R.id.ms);
        this.ab = (DJIRelativeLayout) findViewById(R.id.n1);
        this.ac = (DJIRelativeLayout) findViewById(R.id.n4);
        this.ad = (DJIRelativeLayout) findViewById(R.id.mu);
        this.U = (DJIRelativeLayout) findViewById(R.id.o5);
        this.l.setOnCheckedChangeListener(this.aX);
        this.m.setOnCheckedChangeListener(this.aX);
        this.g.setOnCheckedChangeListener(this.aX);
        this.n.setOnCheckedChangeListener(this.aX);
        this.o.setOnCheckedChangeListener(this.aX);
        this.q.setOnCheckedChangeListener(this.aX);
        this.p.setOnCheckedChangeListener(this.aX);
        this.t.setOnCheckedChangeListener(this.aX);
        this.u.setOnCheckedChangeListener(this.aX);
        this.v.setOnCheckedChangeListener(this.aX);
        this.w.setOnCheckedChangeListener(this.aX);
        this.Q.setOnClickListener(this.aY);
        this.R.setOnClickListener(this.aY);
        this.T.setOnClickListener(this.aY);
        this.S.setOnClickListener(this.aY);
        this.U.setOnClickListener(this.aY);
        this.ab.setOnClickListener(this.aY);
        this.ac.setOnClickListener(this.aY);
        this.x.setOnClickListener(this.aY);
        this.y.setOnClickListener(this.aY);
        this.ag.setOnClickListener(this.aY);
        if (!dji.pilot.publics.e.a.c(null)) {
            findViewById(R.id.mc).setVisibility(8);
            findViewById(R.id.m9).setVisibility(8);
        }
    }

    private void h() {
        this.ai = (DJIRelativeLayout) findViewById(R.id.mi);
        this.aj = (DJIRelativeLayout) findViewById(R.id.mk);
        this.ak = (DJIRelativeLayout) findViewById(R.id.mm);
        this.z = (DJISwitch) findViewById(R.id.mj);
        this.A = (DJISwitch) findViewById(R.id.ml);
        this.B = (DJISwitch) findViewById(R.id.mn);
        this.C = (DJITextView) findViewById(R.id.oa);
        this.D = (DJITextView) findViewById(R.id.o8);
        this.A.setOnCheckedChangeListener(this.aX);
        this.B.setOnCheckedChangeListener(this.aX);
        this.z.setOnCheckedChangeListener(this.aX);
        this.C.setOnClickListener(this.aY);
        this.D.setOnClickListener(this.aY);
    }

    private void i() {
        this.E = (DJITextView) findViewById(R.id.nh);
        this.F = (DJITextView) findViewById(R.id.nk);
        this.G = (DJITextView) findViewById(R.id.nn);
        this.H = (DJITextView) findViewById(R.id.nq);
        this.I = (DJITextView) findViewById(R.id.nt);
        this.K = (DJITextView) findViewById(R.id.o2);
        this.M = (DJITextView) findViewById(R.id.nw);
        this.L = (DJITextView) findViewById(R.id.o7);
        this.J = (DJITextView) findViewById(R.id.ob);
        this.N = (DJITextView) findViewById(R.id.nz);
        this.al = (DJIRelativeLayout) findViewById(R.id.nf);
        this.am = (DJIRelativeLayout) findViewById(R.id.ni);
        this.an = (DJIRelativeLayout) findViewById(R.id.nl);
        this.ao = (DJIRelativeLayout) findViewById(R.id.no);
        this.ap = (DJIRelativeLayout) findViewById(R.id.nr);
        this.aq = (DJIRelativeLayout) findViewById(R.id.o0);
        this.ar = (DJIRelativeLayout) findViewById(R.id.nu);
        this.as = (DJIRelativeLayout) findViewById(R.id.nx);
        this.al.setOnClickListener(this.aY);
        this.am.setOnClickListener(this.aY);
        this.an.setOnClickListener(this.aY);
        this.ao.setOnClickListener(this.aY);
        this.ap.setOnClickListener(this.aY);
        this.aq.setOnClickListener(this.aY);
        this.L.setOnClickListener(this.aY);
        this.ar.setOnClickListener(this.aY);
        this.as.setOnClickListener(this.aY);
    }

    private void j() {
        g();
        h();
        i();
    }

    private void a(int i, String str) {
        if (this.aU == null) {
            this.aU = b.a(getContext(), R.string.app_tip, R.string.fpv_playback_del_image, R.string.btn_dlg_no, new 7(this), R.string.btn_dlg_yes, new 8(this, i));
            this.aU.setCancelable(true);
            this.aU.setCanceledOnTouchOutside(true);
        }
        if (this.aU != null && !this.aU.isShowing()) {
            this.aW = i;
            this.aU.b(str);
            if (this.aW == 2) {
                this.aU.d(R.string.app_enter);
                this.aU.b();
            } else {
                this.aU.d(R.string.btn_dlg_no);
                this.aU.e(R.string.btn_dlg_yes);
            }
            this.aU.show();
        }
    }

    private void k() {
        if (this.aW == 0) {
            this.aS.g();
        } else if (this.aW == 1) {
            this.aS.h();
        } else if (this.aW != 2 && this.aW == 3) {
            this.aS.i();
        }
        l();
    }

    private void l() {
        if (this.aU != null && this.aU.isShowing()) {
            this.aU.dismiss();
            this.aW = -1;
            this.aU = null;
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            dji.pilot.publics.e.f.a(this);
            f();
            j();
        }
    }

    private void a(boolean z, CameraType cameraType, int i) {
        if (z) {
            this.Q.show();
            this.T.show();
            this.R.show();
            this.O.show();
            this.P.show();
            this.V.show();
            this.ab.show();
            if (cameraType == CameraType.DJICameraTypeGD600) {
                this.O.go();
                this.T.go();
                this.S.go();
                this.ae.go();
            } else if (CameraType.DJICameraTypeFC300S == cameraType || CameraType.DJICameraTypeFC260 == cameraType || CameraType.DJICameraTypeFC300XW == cameraType || CameraType.DJICameraTypeFC330X == cameraType || CameraType.DJICameraTypeCV600 == cameraType || CameraType.DJICameraTypeFC6310 == cameraType) {
                this.S.go();
                this.ae.go();
            } else if (CameraType.DJICameraTypeFC220 == cameraType || CameraType.DJICameraTypeFC220S == cameraType) {
                this.S.go();
                this.ae.show();
            } else {
                this.S.go();
                this.ae.go();
            }
        } else {
            this.Q.go();
            this.T.go();
            this.R.go();
            this.O.go();
            this.P.go();
            this.V.go();
            this.ab.go();
            this.S.go();
            this.ae.go();
        }
        if (dji.pilot.fpv.d.b.i(cameraType)) {
            this.ac.show();
        } else {
            this.ac.go();
        }
        if ((CameraType.DJICameraTypeFC330X == cameraType || CameraType.DJICameraTypeFC6310 == cameraType || CameraType.DJICameraTypeFC220 == cameraType || CameraType.DJICameraTypeFC220S == cameraType) && i >= 6) {
            this.W.show();
        } else {
            this.W.go();
        }
        if (CameraType.DJICameraTypeFC6310 == cameraType) {
            this.ad.show();
        } else {
            this.ad.go();
        }
        if (CameraType.DJICameraTypeFC330X == cameraType) {
            this.aa.show();
        } else {
            this.aa.go();
        }
        if (CameraType.DJICameraTypeFC220 == cameraType || CameraType.DJICameraTypeFC220S == cameraType) {
            this.ah.show();
        } else {
            this.ah.go();
        }
        if (dji.pilot.fpv.camera.a.a.c(cameraType)) {
            this.C.show();
        } else {
            this.C.go();
        }
        if (CameraType.DJICameraTypeFC6310 == cameraType) {
            this.af.go();
        } else {
            this.af.go();
        }
        if (dji.pilot.fpv.camera.a.a.d(cameraType)) {
            this.ag.show();
        } else {
            this.ag.go();
        }
    }

    private void a(boolean z, int i) {
        if (z) {
            this.al.show();
            this.am.show();
            this.an.show();
            this.ao.show();
            this.ap.show();
            if (i >= 2) {
                this.aq.show();
                this.L.show();
            } else {
                this.aq.go();
                this.L.go();
            }
            if (DataCameraGetPushTauParam.getInstance().isGetted()) {
                a(DataCameraGetPushTauParam.getInstance(), true);
            } else {
                this.J.go();
            }
            if (i < 3 || !DataCameraGetPushTauParam.getInstance().supportSpotThermometric()) {
                this.ar.go();
                this.as.go();
                return;
            }
            this.as.show();
            return;
        }
        this.al.go();
        this.am.go();
        this.an.go();
        this.ao.go();
        this.ap.go();
        this.J.go();
        this.aq.go();
        this.L.go();
        this.ar.go();
        this.as.go();
    }

    private void b(boolean z, CameraType cameraType, int i) {
        if (z) {
            boolean e = dji.pilot.fpv.d.b.e(cameraType);
            if (DataCameraGetPushShotInfo.getInstance().getSupportType() > 0) {
                this.ai.go();
                this.aj.show();
                if (e) {
                    this.ak.show();
                } else {
                    this.ak.go();
                }
            } else {
                this.ai.show();
                this.aj.go();
                this.ak.go();
            }
            if (e) {
                this.D.show();
            } else {
                this.D.go();
            }
        } else {
            this.ai.go();
            this.aj.go();
            this.ak.go();
            this.D.go();
        }
        if (cameraType == CameraType.DJICameraTypeFC550 || cameraType == CameraType.DJICameraTypeFC550Raw || cameraType == CameraType.DJICameraTypeCV600) {
            this.U.show();
        } else {
            this.U.go();
        }
        if (dji.pilot.publics.e.a.c(null)) {
            this.ai.go();
        }
    }

    public void dispatchOnStart() {
        boolean z;
        scrollTo(0, 0);
        this.x.setAlpha(1.0f);
        this.x.setEnabled(true);
        this.y.setEnabled(true);
        this.C.setEnabled(true);
        this.l.setEnabled(true);
        this.n.setEnabled(true);
        this.o.setChecked(this.aS.F());
        b();
        a();
        this.x.show();
        this.y.show();
        CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        int verstion = DataCameraGetPushStateInfo.getInstance().getVerstion();
        boolean j = dji.pilot.fpv.d.b.j(cameraType);
        boolean z2 = dji.pilot.fpv.d.b.c(cameraType) && cameraType != CameraType.DJICameraTypeGD600;
        if (j) {
            z = false;
        } else {
            z = true;
        }
        a(z, cameraType, verstion);
        a(j, verstion);
        b(z2, cameraType, verstion);
        ExposureMode exposureMode = DataCameraGetPushShotParams.getInstance().getExposureMode();
        if (ExposureMode.S == exposureMode || ExposureMode.M == exposureMode) {
            this.R.setEnabled(false);
            this.R.setAlpha(0.3f);
        } else {
            this.R.setEnabled(true);
            this.R.setAlpha(1.0f);
        }
        this.aS.d();
        this.aS.a(this.aT);
    }

    public void dispatchOnStop() {
        this.av = RecordType.OTHER;
        this.aS.b(this.aT);
    }

    private void m() {
        if (this.aP) {
            this.ar.show();
            if (this.aR.aH().b()) {
                this.M.setText(dji.pilot.fpv.d.b.a(getContext(), "%.0f", (float) this.aR.aH().c(), true));
                return;
            }
            this.M.setText(R.string.app_off_lower);
            return;
        }
        this.ar.go();
    }

    private void n() {
        if (this.ab.getVisibility() == 0) {
            this.r.setImageResource(new int[]{R.drawable.advanced_more_off, R.drawable.camera_centerpoints1, R.drawable.camera_centerpoints6, R.drawable.camera_centerpoints7, R.drawable.camera_centerpoints3, R.drawable.camera_centerpoints2, R.drawable.camera_centerpoints5, R.drawable.camera_centerpoints4}[this.aR.s()]);
        }
    }

    private void o() {
        if (this.ac.getVisibility() == 0) {
            int u = this.aR.u();
            if (u == 0) {
                this.s.setText(getResources().getString(R.string.app_off_lower));
                return;
            }
            this.s.setText(this.aZ.format((double) dji.pilot.fpv.camera.more.d.e.aq_[u][0]) + ":" + this.aZ.format((double) dji.pilot.fpv.camera.more.d.e.aq_[u][1]));
        }
    }

    public void dispatchOnResume() {
        onEventMainThread(DataCameraGetPushShotParams.getInstance());
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        onEventMainThread(DataCameraGetPushShotInfo.getInstance());
        onEventMainThread(dji.pilot.visual.a.c.getInstance().f());
        onEventMainThread(DataCameraGetPushTauParam.getInstance());
        c();
        e();
        d();
        m();
        n();
        o();
        dji.thirdparty.a.c.a().a((Object) this);
        dji.sdksharedlib.a.a.b((d) this, dji.sdksharedlib.b.b.bW);
    }

    public void dispatchOnPause() {
        dji.thirdparty.a.c.a().d((Object) this);
        dji.sdksharedlib.a.a.a((d) this);
    }

    public View getView() {
        return this;
    }

    public void onValueChange(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        new Handler(Looper.getMainLooper()).post(new 9(this, cVar, aVar2));
    }
}
