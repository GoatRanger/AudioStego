package dji.pilot.fpv.camera.setting;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.WheelHorizontalView;
import antistatic.spinnerwheel.d;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetIso.TYPE;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetAELock;
import dji.midware.data.model.P3.DataCameraSetAperture;
import dji.midware.data.model.P3.DataCameraSetExposureMode.ExposureMode;
import dji.midware.data.model.P3.DataCameraSetIris;
import dji.midware.data.model.P3.DataCameraSetIso;
import dji.midware.data.model.P3.DataCameraSetShutterSpeed;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.DJIEVStripView;
import dji.pilot.fpv.camera.more.d$c;
import dji.pilot.fpv.camera.more.e;
import dji.pilot.fpv.d.c.s;
import dji.pilot.publics.widget.DJIRoundLinearLayout;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;

public class DJICameraSettingBaseView extends DJIRoundLinearLayout implements d$c, s, dji.pilot.fpv.view.DJIStageView.a {
    private static final String aM = DJICameraSettingBaseView.class.getSimpleName();
    protected static final int i = 4096;
    protected static final int j = 4097;
    protected static final int k = 4098;
    protected static final int l = 8192;
    protected static final long m = 200;
    protected static final long n = 20;
    protected DJITextView A = null;
    protected DJIRelativeLayout B = null;
    protected WheelHorizontalView C = null;
    protected DJIImageView D = null;
    protected DJITextView E = null;
    protected DJIRelativeLayout F = null;
    protected DJITextView G = null;
    protected DJITextView H = null;
    protected DJITextView I = null;
    protected DJIRelativeLayout J = null;
    protected WheelHorizontalView K = null;
    protected DJIImageView L = null;
    protected DJITextView M = null;
    protected DJILinearLayout N = null;
    protected DJIImageView O = null;
    protected DJITextView P = null;
    protected DJIImageView Q = null;
    protected DJIEVStripView R = null;
    protected e<String> S = null;
    protected e<String> T = null;
    protected Context U = null;
    protected dji.pilot.fpv.camera.more.a V = dji.pilot.fpv.camera.more.a.getInstance();
    protected b W = null;
    protected String aA = "";
    protected MODE aB = MODE.OTHER;
    protected int aC = 1500;
    protected boolean aD = false;
    protected a aE = null;
    protected DJITextView aF = null;
    protected int aG = 1;
    protected int aH = 0;
    protected int aI = 0;
    protected int aJ = 0;
    protected int aK = 0;
    protected CameraType aL = CameraType.OTHER;
    protected OnSeekBarChangeListener aa = null;
    protected OnClickListener ab = null;
    protected antistatic.spinnerwheel.b ac = null;
    protected d ad = null;
    protected String[] ae = null;
    protected String[] af = null;
    protected String[] ag = null;
    protected String[] ah = null;
    protected String[] ai = null;
    protected int[] aj = null;
    protected boolean ak = false;
    protected int al = Integer.MAX_VALUE;
    protected ExposureMode am = ExposureMode.i;
    protected String an = null;
    protected int ao = Integer.MAX_VALUE;
    protected int ap = Integer.MAX_VALUE;
    protected int aq = 0;
    protected int ar = Integer.MAX_VALUE;
    protected int as = 255;
    protected int at = 0;
    protected int au = Integer.MAX_VALUE;
    protected int av = Integer.MAX_VALUE;
    protected int aw = Integer.MIN_VALUE;
    protected int ax = Integer.MIN_VALUE;
    protected int ay = Integer.MAX_VALUE;
    protected int az = Integer.MAX_VALUE;
    protected DJICameraSettingModeView o = null;
    protected DJITextView p = null;
    protected DJIRelativeLayout q = null;
    protected DJIEVStripView r = null;
    protected DJITextView s = null;
    protected DJITextView t = null;
    protected DJIRelativeLayout u = null;
    protected DJITextView v = null;
    protected DJITextView w = null;
    protected DJITextView x = null;
    protected SeekBar y = null;
    protected DJIImageView z = null;

    public interface a {
        void a(boolean z);
    }

    private static final class b extends Handler {
        private final WeakReference<DJICameraSettingBaseView> a;

        private b(DJICameraSettingBaseView dJICameraSettingBaseView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJICameraSettingBaseView);
        }

        public void handleMessage(Message message) {
            DJICameraSettingBaseView dJICameraSettingBaseView = (DJICameraSettingBaseView) this.a.get();
            if (dJICameraSettingBaseView != null) {
                switch (message.what) {
                    case 4096:
                        dJICameraSettingBaseView.g(message.arg1);
                        return;
                    case 4097:
                        dJICameraSettingBaseView.h(message.arg1);
                        return;
                    case 4098:
                        dJICameraSettingBaseView.i(message.arg1);
                        return;
                    case 8192:
                        dJICameraSettingBaseView.a(false, dJICameraSettingBaseView.y.getProgress());
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public DJICameraSettingBaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnVisibilityChangeListener(a aVar) {
        this.aE = aVar;
    }

    public void handleCameraSettingClick() {
        if (getVisibility() != 0) {
            dji.pilot.fpv.d.e.c(s.dj);
            showView();
            return;
        }
        hideView();
    }

    public void showView() {
        if (getVisibility() != 0) {
            setVisibility(0);
            onEventMainThread(DataCameraGetPushStateInfo.getInstance());
            onEventMainThread(DataCameraGetPushShotParams.getInstance());
            onEventMainThread(DataCameraGetPushShotInfo.getInstance());
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            if (this.aE != null) {
                this.aE.a(true);
            }
        }
    }

    public void hideView() {
        if (getVisibility() != 8) {
            setVisibility(8);
            if (c.a().c(this)) {
                c.a().d(this);
            }
            if (this.aE != null) {
                this.aE.a(false);
            }
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        if (cameraType != this.aL) {
            a(cameraType);
        }
        this.o.handleCameraStateChanged(dataCameraGetPushStateInfo);
        MODE mode = dataCameraGetPushStateInfo.getMode();
        if (dataCameraGetPushStateInfo.beInTrackingMode()) {
            mode = MODE.RECORD;
        }
        if (this.aB != mode) {
            this.aB = mode;
            if (c()) {
                this.aC = getIsoMaxCant();
                this.y.setMax(this.aC);
                this.x.setText(String.valueOf(this.aC + 100));
            } else {
                this.aC = c(false);
                this.y.setMax(this.aC);
                this.x.setText(this.ae[this.aC]);
            }
            if (mode != MODE.RECORD) {
                if (!dji.pilot.fpv.d.b.c(this.aL)) {
                    if (this.am == ExposureMode.c) {
                        a();
                    } else {
                        this.S.j();
                    }
                    if (this.am == ExposureMode.d) {
                        a(false);
                    } else {
                        a(true);
                    }
                }
                d(Integer.MIN_VALUE);
            } else if (this.am != ExposureMode.h) {
                if (!dji.pilot.fpv.d.b.c(this.aL)) {
                    this.S.j();
                    a(true);
                }
                d(DataCameraGetPushShotParams.getInstance().getVideoFps());
            }
        }
    }

    public void onEventMainThread(DataCameraGetPushShotInfo dataCameraGetPushShotInfo) {
        int minAperture = dataCameraGetPushShotInfo.getMinAperture();
        int maxAperture = dataCameraGetPushShotInfo.getMaxAperture();
        if (this.av != minAperture || this.aw != maxAperture) {
            int i;
            this.av = minAperture;
            this.aw = maxAperture;
            String[] I = this.V.I();
            minAperture = this.V.n(minAperture);
            if (minAperture == -1) {
                minAperture = 0;
            }
            maxAperture = this.V.n(maxAperture);
            if (maxAperture == -1 || maxAperture == 0) {
                maxAperture = I.length - 1;
            }
            if (maxAperture <= minAperture) {
                maxAperture = I.length - 1;
                i = 0;
            } else {
                i = minAperture;
            }
            this.ai = (String[]) dji.thirdparty.afinal.c.c.a(I, i, maxAperture + 1);
            this.aj = dji.thirdparty.afinal.c.c.a(this.V.J(), i, maxAperture + 1);
            this.T.a(this.ai);
            a(d(DataCameraGetPushShotParams.getInstance()));
            if (b(this.am, this.aB)) {
                a(false);
            } else {
                a(true);
            }
        }
    }

    protected boolean a(ExposureMode exposureMode, MODE mode) {
        if (exposureMode == ExposureMode.h) {
            return false;
        }
        if (dji.pilot.fpv.d.b.c(this.aL)) {
            if (exposureMode != ExposureMode.c) {
                return false;
            }
            return true;
        } else if (mode == MODE.TAKEPHOTO && exposureMode == ExposureMode.c) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean b(ExposureMode exposureMode, MODE mode) {
        if (dji.pilot.fpv.d.b.c(this.aL)) {
            if (exposureMode == ExposureMode.d) {
                return true;
            }
            return false;
        } else if (mode == MODE.TAKEPHOTO && exposureMode == ExposureMode.d) {
            return true;
        } else {
            return false;
        }
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int iso;
        this.ak = dataCameraGetPushShotParams.isAELock();
        ExposureMode exposureMode = dataCameraGetPushShotParams.getExposureMode();
        if (this.am != exposureMode) {
            a(exposureMode);
        }
        if (b(exposureMode, this.aB)) {
            a(false);
        } else {
            a(true);
        }
        if (dji.pilot.fpv.d.b.g(this.aL)) {
            iso = dataCameraGetPushShotParams.getISO();
            if (this.au != iso) {
                if ((this.au == 1 || this.au == 0) && iso != 1 && iso != 0) {
                    b();
                } else if (!((iso != 1 && iso != 0) || this.au == 1 || this.au == 0)) {
                    b();
                }
                this.au = iso;
                if (exposureMode == ExposureMode.e || !(iso == 0 || iso == 1)) {
                    this.z.setImageResource(R.drawable.camera_isoauto_normal);
                } else {
                    this.z.setImageResource(R.drawable.camera_isoauto_highlight);
                }
            }
        }
        iso = a(dataCameraGetPushShotParams);
        if (this.al != iso) {
            c(iso);
        }
        if (this.aB == MODE.RECORD) {
            iso = dataCameraGetPushShotParams.getVideoFps();
            if (this.am != ExposureMode.h || this.at == iso) {
                d(iso);
            } else {
                this.ag = dji.pilot.fpv.camera.more.a.getInstance().i(iso);
                this.S = new e(getContext(), this.ag);
                this.S.c(R.layout.fpv_camera_simple_shutter_item);
                this.S.d(R.id.vx);
                this.S.i();
                this.C.setViewAdapter(this.S);
            }
        }
        if (a(exposureMode, this.aB)) {
            a();
        }
        String c = c(dataCameraGetPushShotParams);
        dji.pilot.fpv.camera.more.a.a("Real shutter[" + c + dji.pilot.usercenter.protocol.d.H);
        if (!(c == null || c.equalsIgnoreCase(this.an))) {
            a(c);
        }
        int b = b(dataCameraGetPushShotParams);
        if (this.ao != b) {
            b(b);
        }
        b = dataCameraGetPushShotParams.getRelExposureCompensation();
        if (this.ap != b && this.am == ExposureMode.h) {
            this.t.setText(this.ah[this.V.m(dataCameraGetPushShotParams.getRelExposureCompensation())]);
            this.r.setSelectedPosition(this.V.m(dataCameraGetPushShotParams.getRelExposureCompensation()));
            this.ap = b;
        }
        b = d(dataCameraGetPushShotParams);
        if (this.ar != b) {
            a(b);
        }
        b = dataCameraGetPushShotParams.getCtrObjectForOne();
        if (this.as != b) {
            this.as = b;
            DJITextView dJITextView = this.aF;
            if (this.as == 2) {
                this.aF = this.M;
            } else if (this.as == 0) {
                this.aF = this.p;
            } else if (this.as == 1) {
                this.aF = this.A;
            } else if (this.as == 3) {
                this.aF = this.I;
            } else {
                this.aF = null;
            }
            if (dJITextView != this.aF) {
                if (dJITextView != null) {
                    dJITextView.setSelected(false);
                }
                if (this.aF != null) {
                    this.aF.setSelected(true);
                }
            }
        }
    }

    protected void a() {
        if (dji.pilot.fpv.d.b.c(this.aL)) {
            int exposureStatus = DataCameraGetPushShotParams.getInstance().getExposureStatus();
            DJILogHelper.getInstance().LOGD(aM, "=== Shutter s[" + exposureStatus + "]s[" + c(DataCameraGetPushShotParams.getInstance()) + dji.pilot.usercenter.protocol.d.H, false, true);
            if (exposureStatus == 0) {
                this.S.j();
                return;
            } else if (exposureStatus == 1) {
                exposureStatus = this.V.b(c(DataCameraGetPushShotParams.getInstance()));
                if (exposureStatus == -1) {
                    this.S.j();
                    return;
                } else {
                    this.S.a(exposureStatus, exposureStatus);
                    return;
                }
            } else if (exposureStatus == 2) {
                exposureStatus = this.V.b(c(DataCameraGetPushShotParams.getInstance()));
                if (exposureStatus < 0) {
                    this.S.j();
                    return;
                } else {
                    this.S.a(exposureStatus, exposureStatus);
                    return;
                }
            } else {
                return;
            }
        }
        String capMinShutterStr = DataCameraGetPushShotParams.getInstance().getCapMinShutterStr();
        String capMaxShutterStr = DataCameraGetPushShotParams.getInstance().getCapMaxShutterStr();
        this.S.a(this.V.b(capMinShutterStr), this.V.b(capMaxShutterStr));
    }

    protected void a(boolean z) {
        int i = 0;
        if (this.J.getVisibility() == 0) {
            if (z) {
                this.ax = Integer.MIN_VALUE;
                this.ay = Integer.MAX_VALUE;
                this.T.j();
            } else if (dji.pilot.fpv.d.b.c(this.aL)) {
                r0 = DataCameraGetPushShotParams.getInstance().getExposureStatus();
                DJILogHelper.getInstance().LOGD(aM, "=== Aperture s[" + r0 + "]a[" + d(DataCameraGetPushShotParams.getInstance()) + dji.pilot.usercenter.protocol.d.H, false, true);
                if (r0 == 0) {
                    this.T.j();
                } else if (r0 == 1) {
                    r0 = dji.pilot.fpv.camera.more.a.a(this.aj, d(DataCameraGetPushShotParams.getInstance()), -1);
                    if (r0 == -1) {
                        r0 = this.aj.length / 2;
                    }
                    this.T.a(r0, r0);
                } else if (r0 == 2) {
                    r0 = dji.pilot.fpv.camera.more.a.a(this.aj, d(DataCameraGetPushShotParams.getInstance()), 0);
                    if (r0 == -1) {
                        r0 = this.aj.length / 2;
                    }
                    this.T.a(r0, r0);
                }
            } else {
                this.ax = DataCameraGetPushShotParams.getInstance().getCapMinAperture();
                this.ay = DataCameraGetPushShotParams.getInstance().getCapMaxAperture();
                r0 = dji.pilot.fpv.camera.more.a.a(this.aj, this.ax, -1);
                if (r0 == -1) {
                    r0 = 0;
                }
                int a = dji.pilot.fpv.camera.more.a.a(this.aj, this.ay, -1);
                if (a == -1 || a == 0) {
                    a = this.aj.length - 1;
                }
                if (a > r0) {
                    i = r0;
                }
                this.T.a(i, a);
            }
        }
    }

    protected void a(int i) {
        if (!this.aD || !this.K.isEnabled()) {
            this.ar = i;
            int a = dji.pilot.fpv.camera.more.a.a(this.aj, i, -1);
            if (a == -1) {
                a = this.aj.length / 2;
                this.ar = this.aj[a];
            }
            if (!this.K.isEnabled()) {
                this.T.g(a);
            }
            this.K.setCurrentItem(a);
            this.G.setText("F " + (((double) i) / 100.0d));
        }
    }

    protected void b(int i) {
        this.ao = i;
        this.aq = this.V.m(i);
        this.P.setText(this.ah[this.aq]);
        this.R.setSelectedPosition(this.aq);
        if (!d()) {
            if (this.aq == this.ah.length / 2) {
                this.P.setEnabled(false);
                this.t.setEnabled(false);
                return;
            }
            this.P.setEnabled(true);
            this.t.setEnabled(true);
        }
    }

    protected void a(String str) {
        if (!this.aD || !this.C.isEnabled()) {
            int c;
            int length;
            int k;
            if (this.am == ExposureMode.h) {
                this.an = str;
                length = this.ag.length;
                c = this.V.c(str);
                k = this.S.k();
                if (c == -1 || (k > 0 && c >= k)) {
                    c = k > 0 ? k - 2 : length / 2;
                    this.an = this.af[c];
                }
            } else {
                this.an = str;
                length = this.af.length;
                c = this.V.b(str);
                k = this.S.k();
                if (c == -1 || (k > 0 && c >= k)) {
                    c = k > 0 ? k - 2 : length / 2;
                    this.an = this.af[c];
                }
            }
            if (!this.C.isEnabled()) {
                this.S.g(c);
            }
            this.C.setCurrentItem(c);
        }
    }

    protected void c(int i) {
        this.al = i;
        int i2;
        if (c()) {
            this.v.setText(String.valueOf(i));
            i2 = i - 100;
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 > this.aC) {
                i2 = this.aC;
            }
            this.y.setProgress(i2);
            a(this.v, this.y, i2, this.aC, false);
        } else if (!this.aD) {
            i2 = this.V.g(i);
            this.v.setText(this.ae[i2]);
            this.y.setProgress(i2);
            a(this.v, this.y, i2, this.y.getMax(), false);
        }
    }

    protected void b() {
        if (c()) {
            if (this.y.getThumbOffset() != this.aH) {
                this.y.setProgressDrawable(dji.pilot.fpv.d.b.a(getResources().getDrawable(R.drawable.iso_disable_pgb), false));
                this.y.setThumb(this.U.getResources().getDrawable(R.drawable.fpv_advanced_slider_disable_icon));
                this.y.setThumbOffset(this.aH);
                this.y.setEnabled(false);
                this.aC = getIsoMaxCant();
                this.y.setMax(this.aC);
                this.x.setText(String.valueOf(this.aC + 100));
                this.v.setTextColor(getResources().getColor(R.color.d8));
            }
        } else if (this.y.getThumbOffset() != this.aG) {
            this.y.setProgressDrawable(dji.pilot.fpv.d.b.a(getResources().getDrawable(R.drawable.fpv_playback_video_progress), false));
            this.y.setThumb(this.U.getResources().getDrawable(R.drawable.fpv_advanced_slider_normal_icon));
            this.y.setThumbOffset(this.aG);
            this.y.setEnabled(true);
            this.aC = c(false);
            this.y.setMax(this.aC);
            this.x.setText(this.ae[this.aC]);
            this.v.setTextColor(getResources().getColor(R.color.om));
        }
    }

    protected void a(ExposureMode exposureMode) {
        boolean z;
        ExposureMode exposureMode2 = this.am;
        this.am = exposureMode;
        this.o.setSelectedMode(exposureMode);
        this.z.setEnabled(exposureMode != ExposureMode.e);
        b();
        WheelHorizontalView wheelHorizontalView = this.C;
        e eVar = this.S;
        if (e()) {
            z = false;
        } else {
            z = true;
        }
        a(wheelHorizontalView, eVar, z, this.D);
        if (dji.pilot.fpv.d.b.c(this.aL) && this.aB == MODE.TAKEPHOTO) {
            if (this.am == ExposureMode.c) {
                a();
            } else {
                this.S.j();
            }
        }
        if (exposureMode == ExposureMode.e) {
            this.M.setText(R.string.camera_mm);
        } else {
            this.M.setText(R.string.fpv_camera_ev);
        }
        if (d()) {
            this.Q.setEnabled(false);
            this.Q.go();
            this.O.setEnabled(false);
            this.O.go();
            this.P.setEnabled(false);
            this.R.setVisibility(0);
        } else {
            this.Q.setEnabled(true);
            this.Q.show();
            this.O.setEnabled(true);
            this.O.show();
            if (this.aq != this.ah.length / 2) {
                this.P.setEnabled(true);
            } else {
                this.P.setEnabled(false);
            }
            this.R.setVisibility(8);
        }
        if (dji.pilot.fpv.d.b.h(this.aL)) {
            wheelHorizontalView = this.K;
            eVar = this.T;
            if (f()) {
                z = false;
            } else {
                z = true;
            }
            a(wheelHorizontalView, eVar, z, this.L);
        }
        if (!dji.pilot.fpv.d.b.b(this.aL)) {
            return;
        }
        if (this.am == ExposureMode.h) {
            DJILogHelper.getInstance().LOGD("", "Mode C", false, true);
            this.E.show();
            this.F.show();
            this.I.go();
            this.J.go();
            this.q.show();
            this.p.go();
            this.z.go();
            this.w.show();
            this.A.setText(getResources().getString(R.string.fpv_camera_shutter_angle));
            this.K.setEnabled(false);
            this.an = this.ag[this.ag.length / 2];
            this.S = new e(getContext(), this.ag);
            this.S.c(R.layout.fpv_camera_simple_shutter_item);
            this.S.d(R.id.vx);
            this.S.i();
            this.C.setViewAdapter(this.S);
            this.C.setCurrentItem(this.ag.length / 2);
            new DataCameraSetIso().a(true).a(getIsoType()).start(null);
        } else if (exposureMode2 == ExposureMode.h) {
            DJILogHelper.getInstance().LOGD("", "not Mode C", false, true);
            this.I.show();
            this.J.show();
            this.E.go();
            this.F.go();
            this.q.go();
            this.p.show();
            this.z.show();
            this.w.go();
            this.A.setText(getResources().getString(R.string.fpv_camera_shutter));
            this.an = this.af[this.af.length / 2];
            this.S = new e(getContext(), this.af);
            this.S.c(R.layout.fpv_camera_simple_shutter_item);
            this.S.d(R.id.vx);
            this.S.i();
            this.C.setViewAdapter(this.S);
            this.C.setCurrentItem(this.af.length / 2);
        }
    }

    protected void a(WheelHorizontalView wheelHorizontalView, e<String> eVar, boolean z, DJIImageView dJIImageView) {
        if (z) {
            wheelHorizontalView.setAllItemsVisible(true);
            dJIImageView.show();
        } else {
            wheelHorizontalView.setAllItemsVisible(false);
            wheelHorizontalView.setVisibleItems(1);
            dJIImageView.hide();
        }
        wheelHorizontalView.setEnabled(z);
        eVar.a(z);
    }

    protected int getIsoMaxCant() {
        if (CameraType.DJICameraTypeGD600 == this.aL) {
            return this.aB == MODE.RECORD ? 1500 : 1500;
        } else {
            if (dji.pilot.fpv.d.b.g(this.aL)) {
                if (this.aB == MODE.RECORD) {
                    return 6300;
                }
                return 25500;
            } else if (CameraType.DJICameraTypeFC220 == this.aL) {
                if (this.aB == MODE.RECORD) {
                    return 6300;
                }
                return 1500;
            } else if (CameraType.DJICameraTypeFC6310 == this.aL) {
                if (this.aB == MODE.RECORD) {
                    return 3100;
                }
                return 3100;
            } else if (this.aB == MODE.RECORD) {
                return 3100;
            } else {
                return 1500;
            }
        }
    }

    protected int b(boolean z) {
        return 0;
    }

    protected int c(boolean z) {
        if (z) {
            return this.ae.length - 4;
        }
        if (CameraType.DJICameraTypeFC6310 == this.aL) {
            if (this.aB == MODE.RECORD) {
                return this.ae.length - 3;
            }
            return this.ae.length - 2;
        } else if (dji.pilot.fpv.d.b.k(this.aL)) {
            if (this.aB == MODE.RECORD) {
                return this.ae.length - 5;
            }
            return this.ae.length - 5;
        } else if (dji.pilot.fpv.d.b.g(this.aL)) {
            if (this.aB == MODE.RECORD) {
                return this.ae.length - 3;
            }
            return this.ae.length - 1;
        } else if (this.aB == MODE.RECORD) {
            return this.ae.length - 4;
        } else {
            return this.ae.length - 5;
        }
    }

    protected boolean c() {
        if (dji.pilot.fpv.d.b.g(this.aL)) {
            int iso = DataCameraGetPushShotParams.getInstance().getISO();
            if (iso == 0 || iso == 1) {
                return true;
            }
            return false;
        } else if (dji.pilot.fpv.d.b.k(this.aL)) {
            if (this.am == ExposureMode.c || this.am == ExposureMode.b || this.am == ExposureMode.d) {
                return true;
            }
            return false;
        } else if (this.am == ExposureMode.c || this.am == ExposureMode.b) {
            return true;
        } else {
            return false;
        }
    }

    protected int a(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (c()) {
            return dataCameraGetPushShotParams.getRelISO();
        }
        return dataCameraGetPushShotParams.getISO();
    }

    protected boolean d() {
        return this.am == ExposureMode.e;
    }

    protected int b(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (d()) {
            return dataCameraGetPushShotParams.getRelExposureCompensation();
        }
        return dataCameraGetPushShotParams.getExposureCompensation();
    }

    protected boolean e() {
        if (dji.pilot.fpv.d.b.h(this.aL)) {
            if (this.am == ExposureMode.b || this.am == ExposureMode.d) {
                return true;
            }
            return false;
        } else if (this.am == ExposureMode.b) {
            return true;
        } else {
            return false;
        }
    }

    protected String c(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (e()) {
            return dataCameraGetPushShotParams.getRelShutterString();
        }
        return dataCameraGetPushShotParams.getShutterString();
    }

    protected boolean f() {
        if (!dji.pilot.fpv.d.b.h(this.aL)) {
            return false;
        }
        if (this.am == ExposureMode.b || this.am == ExposureMode.c || this.am == ExposureMode.h) {
            return true;
        }
        return false;
    }

    protected int d(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (f()) {
            return dataCameraGetPushShotParams.getRealApertureSize();
        }
        return dataCameraGetPushShotParams.getApertureSize();
    }

    protected void d(int i) {
        dji.pilot.fpv.camera.more.a.a("Setting fps[" + i + dji.pilot.usercenter.protocol.d.H);
        if (this.at != i) {
            this.at = i;
            if (i == Integer.MIN_VALUE) {
                this.S.h(-1);
                return;
            }
            try {
                int e = e(i);
                if (e <= 0) {
                    e = this.af.length;
                }
                if (e != -1 && this.C.getCurrentItem() >= e) {
                    this.C.setCurrentItem(e - 2);
                }
                this.S.h(e);
            } catch (NumberFormatException e2) {
            }
        }
    }

    protected int e(int i) throws NumberFormatException {
        int f = this.V.f(i);
        int length = this.af.length;
        for (int i2 = 0; i2 < length; i2++) {
            String str = this.af[i2];
            if (-1 != str.indexOf(39)) {
                return -1;
            }
            int indexOf = str.indexOf(46);
            if (-1 != indexOf) {
                str = str.substring(0, indexOf);
            }
            int parseInt = Integer.parseInt(str);
            if (indexOf != -1) {
                parseInt++;
            }
            if (parseInt == f) {
                return i2 + 1;
            }
            if (parseInt < f) {
                return i2;
            }
        }
        return -1;
    }

    protected void a(CameraType cameraType) {
        this.aL = cameraType;
        if (dji.pilot.fpv.d.b.h(cameraType)) {
            g();
        } else {
            h();
        }
        if (dji.pilot.fpv.d.b.g(cameraType)) {
            this.z.show();
            this.w.hide();
            return;
        }
        this.z.go();
        this.w.show();
    }

    protected void g() {
        int a = dji.pilot.fpv.model.b.a(this.U, R.dimen.et);
        LayoutParams layoutParams = this.u.getLayoutParams();
        layoutParams.height = a;
        this.u.setLayoutParams(layoutParams);
        layoutParams = this.B.getLayoutParams();
        layoutParams.height = a;
        this.B.setLayoutParams(layoutParams);
        layoutParams = this.J.getLayoutParams();
        layoutParams.height = a;
        this.J.setLayoutParams(layoutParams);
        layoutParams = this.N.getLayoutParams();
        layoutParams.height = a;
        this.N.setLayoutParams(layoutParams);
        setPadding(2, 2, 2, 2);
        this.I.show();
        this.J.show();
    }

    protected void h() {
        int a = dji.pilot.fpv.model.b.a(this.U, R.dimen.ez);
        LayoutParams layoutParams = this.u.getLayoutParams();
        layoutParams.height = a;
        this.u.setLayoutParams(layoutParams);
        layoutParams = this.B.getLayoutParams();
        layoutParams.height = a;
        this.B.setLayoutParams(layoutParams);
        layoutParams = this.J.getLayoutParams();
        layoutParams.height = a;
        this.J.setLayoutParams(layoutParams);
        layoutParams = this.N.getLayoutParams();
        layoutParams.height = a;
        this.N.setLayoutParams(layoutParams);
        setPadding(2, 2, 2, getResources().getDimensionPixelSize(R.dimen.rp) + 2);
        this.I.go();
        this.J.go();
    }

    protected void i() {
        this.U = getContext();
        this.W = new b();
        this.ae = this.V.D();
        this.af = this.V.F();
        this.ag = this.V.i(DataCameraGetPushShotParams.getInstance().getVideoFps());
        this.ah = this.V.G();
        this.ai = this.V.I();
        this.aj = this.V.J();
        this.aG = 1;
        Resources resources = this.U.getResources();
        this.aH = resources.getDimensionPixelSize(R.dimen.ex);
        this.aI = resources.getDimensionPixelSize(R.dimen.gl);
        this.aJ = resources.getDimensionPixelSize(R.dimen.n4);
    }

    protected void j() {
        this.ab = new OnClickListener(this) {
            final /* synthetic */ DJICameraSettingBaseView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                DJICameraSettingBaseView dJICameraSettingBaseView;
                if (R.id.q1 == id) {
                    if (this.a.aq > 0) {
                        dji.pilot.fpv.d.e.a("FPV_LeftBarView_CameraAdvancedView_Button_EvSub");
                        dJICameraSettingBaseView = this.a;
                        dJICameraSettingBaseView.aq--;
                        this.a.a(this.a.aq, false);
                    }
                } else if (R.id.q5 == id) {
                    if (this.a.aq < this.a.ah.length - 1) {
                        dji.pilot.fpv.d.e.a("FPV_LeftBarView_CameraAdvancedView_Button_EvAdd");
                        dJICameraSettingBaseView = this.a;
                        dJICameraSettingBaseView.aq++;
                        this.a.a(this.a.aq, false);
                    }
                } else if (R.id.q3 == id) {
                    if (this.a.aq != this.a.ah.length / 2) {
                        dji.pilot.fpv.d.e.a("FPV_LeftBarView_CameraAdvancedView_Button_EvReset");
                        this.a.aq = this.a.ah.length / 2;
                        this.a.a(this.a.aq, true);
                    }
                } else if (R.id.pk == id) {
                    if (this.a.au == 0 || this.a.au == 1) {
                        new DataCameraSetIso().a(true).a(this.a.getIsoType()).start(null);
                    } else {
                        new DataCameraSetIso().a(true).a(TYPE.AUTO).start(null);
                    }
                } else if (R.id.pn == id) {
                    this.a.n();
                }
            }
        };
    }

    protected TYPE getIsoType() {
        Object obj = null;
        int relISO = DataCameraGetPushShotParams.getInstance().getRelISO() / 100;
        int i = 0;
        while (relISO > 0) {
            int i2 = i + 1;
            int i3 = relISO >>> 1;
            if (i3 > 0) {
                obj = (relISO & 1) != 0 ? 1 : null;
                relISO = i3;
                i = i2;
            } else {
                i = i2;
                relISO = i3;
            }
        }
        i += 2;
        if (obj != null) {
            i++;
        }
        if (i < TYPE.ISO100.value()) {
            i = TYPE.ISO100.value();
        } else if (i > TYPE.ISO25600.value()) {
            i = TYPE.ISO25600.value();
        }
        return TYPE.find(i);
    }

    protected void k() {
        this.aa = new OnSeekBarChangeListener(this) {
            final /* synthetic */ DJICameraSettingBaseView a;

            {
                this.a = r1;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                this.a.aD = false;
                if (this.a.y == seekBar) {
                    this.a.W.removeMessages(8192);
                    this.a.a(true, seekBar.getProgress());
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                this.a.aD = true;
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    this.a.o();
                    if (this.a.y == seekBar) {
                        dji.pilot.fpv.d.e.a("FPV_LeftBarView_CameraAdvancedView_Button_ISOChange");
                        this.a.W.removeMessages(8192);
                        this.a.W.sendEmptyMessageDelayed(8192, DJICameraSettingBaseView.n);
                    }
                }
            }
        };
    }

    protected void l() {
        this.ac = new antistatic.spinnerwheel.b(this) {
            final /* synthetic */ DJICameraSettingBaseView a;

            {
                this.a = r1;
            }

            public void a(AbstractWheel abstractWheel, int i, int i2) {
                dji.pilot.fpv.camera.more.a.a("***wheel[" + (abstractWheel == this.a.C) + dji.pilot.usercenter.protocol.d.H);
                if (abstractWheel == this.a.C) {
                    dji.pilot.fpv.d.e.a("FPV_LeftBarView_CameraAdvancedView_Shutter_Changed");
                    if (this.a.aD && this.a.C.isEnabled()) {
                        this.a.o();
                        this.a.b(false, i2);
                        return;
                    }
                    this.a.S.g(i2);
                } else if (abstractWheel == this.a.K) {
                    dji.pilot.fpv.d.e.a("FPV_LeftBarView_CameraAdvancedView_Aperture_Changed");
                    if (this.a.aD && this.a.K.isEnabled()) {
                        this.a.o();
                        this.a.c(false, i2);
                        return;
                    }
                    this.a.T.g(i2);
                }
            }
        };
        this.ad = new d(this) {
            final /* synthetic */ DJICameraSettingBaseView a;

            {
                this.a = r1;
            }

            public void a(AbstractWheel abstractWheel) {
                this.a.aD = true;
            }

            public void b(AbstractWheel abstractWheel) {
                this.a.aD = false;
                if (abstractWheel == this.a.C) {
                    this.a.b(true, abstractWheel.getCurrentItem());
                    this.a.S.g(abstractWheel.getCurrentItem());
                } else if (abstractWheel == this.a.K) {
                    this.a.c(true, abstractWheel.getCurrentItem());
                    this.a.T.g(abstractWheel.getCurrentItem());
                }
            }
        };
    }

    protected void m() {
        i();
        j();
        k();
        l();
    }

    protected void a(int i, boolean z) {
        o();
        if (z) {
            this.V.B();
        } else {
            this.V.A();
        }
        if (z || i == this.ah.length / 2) {
            f(i);
        } else {
            f(i);
        }
    }

    protected void f(int i) {
        int l = this.V.l(i);
        if (this.ao != l) {
            DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
            dataBaseCameraSetting.a(dji.sdksharedlib.b.b.J).a(l).a(0, 1);
            dataBaseCameraSetting.start(null);
        }
    }

    protected void a(boolean z, int i) {
        this.W.removeMessages(4096);
        this.v.setText(this.ae[i]);
        a(this.v, this.y, i, this.y.getMax(), false);
        if (z) {
            g(i);
            return;
        }
        this.V.A();
        this.W.sendMessageDelayed(this.W.obtainMessage(4096, i, 0), 200);
    }

    protected void a(DJITextView dJITextView, SeekBar seekBar, int i, int i2, boolean z) {
        int measureText = (int) dJITextView.getPaint().measureText(dJITextView.getText().toString());
        int i3 = seekBar.getThumb().getBounds().left;
        if (i3 <= 0) {
            i3 = seekBar.getWidth();
            if (i3 <= 0) {
                i3 = this.aK;
            }
            i3 = (i3 * i) / i2;
        }
        measureText = this.aJ + ((i3 + this.aI) - (measureText / 2));
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) dJITextView.getLayoutParams();
        if (measureText != marginLayoutParams.leftMargin) {
            marginLayoutParams.leftMargin = measureText;
            dJITextView.setLayoutParams(marginLayoutParams);
        }
    }

    protected void g(int i) {
        int h = this.V.h(i);
        if (this.al != h) {
            new DataCameraSetIso().a(true).a(TYPE.find(h)).start(null);
        }
    }

    protected void b(boolean z, int i) {
        this.W.removeMessages(4097);
        if (z) {
            h(i);
            return;
        }
        this.V.A();
        this.W.sendMessageDelayed(this.W.obtainMessage(4097, i, 0), 200);
    }

    protected void h(int i) {
        String k;
        int i2 = 0;
        if (this.am == ExposureMode.h) {
            this.V.i(DataCameraGetPushShotParams.getInstance().getVideoFps());
            k = this.V.k(i);
        } else {
            k = this.V.j(i);
        }
        if (!k.equalsIgnoreCase(this.an)) {
            boolean z;
            String[] split = k.split(dji.pilot.usercenter.protocol.d.t);
            if (split.length > 1) {
                z = true;
            } else {
                z = false;
            }
            split = split[split.length - 1].split("\\.");
            int parseInt = Integer.parseInt(split[0]);
            if (split.length > 1) {
                i2 = Integer.parseInt(split[1]);
            }
            new DataCameraSetShutterSpeed().a(z, parseInt, i2).start(null);
        }
    }

    protected void n() {
        if (this.am == ExposureMode.h) {
            DJILogHelper.getInstance().LOGD("", "set Iris", false, true);
            new DataCameraSetIris().start(null);
        }
    }

    protected void c(boolean z, int i) {
        this.W.removeMessages(4098);
        if (z) {
            i(i);
            return;
        }
        this.V.A();
        this.W.sendMessageDelayed(this.W.obtainMessage(4098, i, 0), 200);
    }

    protected void i(int i) {
        if (i > this.ai.length) {
            i = this.ai.length - 1;
        }
        short b = b(this.ai[i]);
        if (b != this.ar) {
            new DataCameraSetAperture().a(b).start(null);
        }
    }

    protected void o() {
        if (this.ak) {
            DataCameraSetAELock.getInstance().a(false).start(null);
        }
    }

    protected short b(String str) {
        short s = (short) 630;
        try {
            return (short) ((int) (Float.parseFloat(str) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity));
        } catch (Exception e) {
            return s;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        onEventMainThread(DataCameraGetPushShotParams.getInstance());
        onEventMainThread(DataCameraGetPushShotInfo.getInstance());
    }

    public void dispatchOnPause() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    public View getView() {
        return this;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            m();
            this.o = (DJICameraSettingModeView) findViewById(R.id.p_);
            this.p = (DJITextView) findViewById(R.id.pa);
            this.s = (DJITextView) findViewById(R.id.pc);
            this.q = (DJIRelativeLayout) findViewById(R.id.pb);
            this.t = (DJITextView) findViewById(R.id.pe);
            this.r = (DJIEVStripView) findViewById(R.id.pd);
            this.u = (DJIRelativeLayout) findViewById(R.id.pf);
            this.v = (DJITextView) findViewById(R.id.pg);
            this.w = (DJITextView) findViewById(R.id.ph);
            this.y = (SeekBar) findViewById(R.id.pi);
            this.x = (DJITextView) findViewById(R.id.pj);
            this.z = (DJIImageView) findViewById(R.id.pk);
            this.A = (DJITextView) findViewById(R.id.pu);
            this.B = (DJIRelativeLayout) findViewById(R.id.pv);
            this.C = (WheelHorizontalView) findViewById(R.id.px);
            this.D = (DJIImageView) findViewById(R.id.pw);
            this.E = (DJITextView) findViewById(R.id.pl);
            this.F = (DJIRelativeLayout) findViewById(R.id.pm);
            this.G = (DJITextView) findViewById(R.id.po);
            this.H = (DJITextView) findViewById(R.id.pn);
            this.I = (DJITextView) findViewById(R.id.pp);
            this.J = (DJIRelativeLayout) findViewById(R.id.pq);
            this.K = (WheelHorizontalView) findViewById(R.id.ps);
            this.L = (DJIImageView) findViewById(R.id.pr);
            this.M = (DJITextView) findViewById(R.id.pz);
            this.N = (DJILinearLayout) findViewById(R.id.q0);
            this.O = (DJIImageView) findViewById(R.id.q1);
            this.P = (DJITextView) findViewById(R.id.q3);
            this.Q = (DJIImageView) findViewById(R.id.q5);
            this.R = (DJIEVStripView) findViewById(R.id.q4);
            this.y.setPadding(0, 0, 0, 0);
            this.w.setText(this.ae[b(true)]);
            this.y.setMax(c(true));
            this.x.setText(this.ae[c(true)]);
            this.an = this.af[this.af.length / 2];
            this.S = new e(getContext(), this.af);
            this.S.c(R.layout.fpv_camera_simple_shutter_item);
            this.S.d(R.id.vx);
            this.S.i();
            this.C.setViewAdapter(this.S);
            this.C.setCurrentItem(this.af.length / 2);
            this.T = new e(getContext(), this.ai);
            this.T.c(R.layout.fpv_camera_simple_aperture_item);
            this.T.d(R.id.vw);
            this.T.i();
            this.K.setViewAdapter(this.T);
            this.K.setCurrentItem(this.ai.length / 2);
            this.ar = b(this.ai[this.ai.length / 2]);
            this.H.setOnClickListener(this.ab);
            this.z.setOnClickListener(this.ab);
            this.O.setOnClickListener(this.ab);
            this.P.setOnClickListener(this.ab);
            this.Q.setOnClickListener(this.ab);
            this.y.setOnSeekBarChangeListener(this.aa);
            this.C.addChangingListener(this.ac);
            this.C.addScrollingListener(this.ad);
            this.K.addChangingListener(this.ac);
            this.K.addScrollingListener(this.ad);
            getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
                final /* synthetic */ DJICameraSettingBaseView a;

                {
                    this.a = r1;
                }

                public void onGlobalLayout() {
                    this.a.c(this.a.al);
                    this.a.aK = this.a.y.getWidth();
                    this.a.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }
    }
}
