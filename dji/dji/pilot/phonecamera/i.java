package dji.pilot.phonecamera;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.media.CameraProfile;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.util.Log;
import dji.pilot.phonecamera.a.c;
import dji.pilot.phonecamera.e.d;
import dji.pilot.phonecamera.e.e;
import dji.pilot.phonecamera.e.g;
import dji.pilot.phonecamera.e.h;
import java.util.List;

public class i extends g {
    private static final String H = "DJILPPhotoModule";
    private static final boolean I = false;
    private static final int K = 1;
    private static final int L = 2;
    private static final int M = 4;
    private static final int N = -1;
    private int J;
    private boolean O;
    private final b P = new b();
    private final a Q = new a();

    private final class a implements e {
        final /* synthetic */ i a;

        private a(i iVar) {
            this.a = iVar;
        }

        public void a(byte[] bArr, g gVar) {
        }
    }

    private final class b implements e {
        final /* synthetic */ i a;

        private b(i iVar) {
            this.a = iVar;
        }

        public void a(byte[] bArr, g gVar) {
        }
    }

    public void a(int i, int i2, Context context, d dVar) {
        Log.d(H, "init ");
        this.k = i;
        this.u = context;
        this.E = new e(this, this.u);
        this.E.enable();
        this.l = 0;
        if (i2 < 0) {
            this.q = a(dVar);
            this.m = d.a().i();
        } else {
            this.q = a(i2, dVar);
            this.m = i2;
        }
        if (this.q != null) {
            this.F = new dji.pilot.g.a(this.u);
            this.F.a(true);
            A();
            this.r = this.q.j();
            o();
        }
    }

    private void z() {
        int i;
        try {
            i = System.getInt(this.u.getContentResolver(), "accelerometer_rotation");
        } catch (SettingNotFoundException e) {
            Log.d(H, "initScreenRotation: " + e);
            i = 0;
        }
        if (i == 0) {
            ((Activity) this.u).setRequestedOrientation(-1);
            this.O = false;
            return;
        }
        ((Activity) this.u).setRequestedOrientation(10);
        this.O = true;
    }

    public void k() {
        if (this.q != null && this.t != null) {
            if (this.v) {
                l();
            }
            g();
            this.q.a(this.t);
            e(-1);
            e("startPreview");
            this.q.e();
            this.v = true;
        }
    }

    public void l() {
        if (this.q != null) {
            Log.v(H, "stopPreview");
            if (this.v) {
                this.q.f();
                this.v = false;
            }
        }
    }

    public boolean d() {
        if (this.q == null) {
            return false;
        }
        int i;
        Log.d(H, "takePicture");
        this.q.a(true);
        if (this.O) {
            i = (360 - this.o) % 360;
        } else {
            i = this.w;
        }
        this.J = h.a(this.m, i);
        this.r.setRotation(this.J);
        a(this.r);
        h.a(this.r, this.F.a());
        a(this.r);
        this.q.a(this.n, new g(this), this.P, this.Q, new c(this));
        return true;
    }

    public void p() {
        if (!h.a(h.c, this.r.getSupportedSceneModes())) {
            e("setCameraHDR: Don't support set HDR");
            if (this.r.getSceneMode() == null) {
                this.r.setSceneMode("auto");
                a(this.r);
            }
        } else if (!h.c.equals(this.r.getSceneMode())) {
            this.r.setSceneMode(h.c);
            a(this.r);
        }
    }

    public void c(String str) {
        if (!h.a(str, this.r.getSupportedSceneModes())) {
            e("setCameraHDR: Don't support set : " + str);
            if (this.r.getSceneMode() == null) {
                this.r.setSceneMode("auto");
                e(4);
            }
        } else if (!str.equals(this.r.getSceneMode())) {
            this.r.setSceneMode(str);
            e("setCameraHDR set scene mode : " + str);
            e(4);
        }
    }

    public void a(final String str) {
        if (!"auto".equals(this.r.getSceneMode())) {
            e("setFlashMode scene mode isn't auto!!!");
        } else if (h.a(str, this.r.getSupportedFlashModes())) {
            Log.d(H, "setFlashMode: " + str);
            if (!this.r.getFlashMode().equals("torch") || str.equals("off")) {
                this.r.setFlashMode(str);
                a(this.r);
                return;
            }
            Log.d(H, "first turn torch off");
            this.r.setFlashMode("off");
            a(this.r, new h(this) {
                final /* synthetic */ i b;

                public void a(Parameters parameters) {
                    Log.d(i.H, "setFlashMode SetFailure: " + parameters);
                    this.b.c(parameters);
                    if (this.b.q != null) {
                        this.b.q.k();
                    }
                    this.b.r.setFlashMode(str);
                    this.b.a(this.b.r);
                }

                public void b(Parameters parameters) {
                    Log.d(i.H, "setFlashMode SetSuccess: set FLASH_MODE_OFF");
                    this.b.c(parameters);
                    if (this.b.q != null) {
                        this.b.q.k();
                    }
                    this.b.r.setFlashMode(str);
                    this.b.a(this.b.r);
                }
            });
        } else {
            e("setFlashMode: Don't support set FlashMode = " + str);
            this.r.setFlashMode("off");
            a(this.r);
        }
    }

    public void b(String str) {
        if (!"auto".equals(this.r.getSceneMode())) {
            e("setWhiteBalance scene mode isn't auto");
        } else if (h.a(str, this.r.getSupportedWhiteBalance())) {
            this.r.setWhiteBalance(str);
            e("setWhiteBalance WB = " + str);
            a(this.r);
        } else {
            e("setWhiteBalance Don't support setWhiteBalance: " + str);
            this.r.setWhiteBalance("auto");
            a(this.r);
        }
    }

    public void d(int i) {
        if (this.r == null) {
            this.r = this.q.j();
        }
        int maxExposureCompensation = this.r.getMaxExposureCompensation();
        if (i < this.r.getMinExposureCompensation() || i > maxExposureCompensation) {
            Log.w(H, "invalid exposure range: " + i);
        } else {
            Log.d(H, "setExposure: value = " + i);
            this.r.setExposureCompensation(i);
        }
        a(this.r);
    }

    public void c() {
        Log.v(H, "release camera device and photoModule instance");
        if (this.q != null) {
            n();
        }
        l();
        this.n.removeCallbacksAndMessages(null);
        j();
        this.E.disable();
        this.v = false;
        this.u = null;
        this.t = null;
        if (this.F != null) {
            this.F.a(false);
            this.F.b();
        }
    }

    private void A() {
        List supportedPreviewFormats = this.q.j().getSupportedPreviewFormats();
        for (int i = 0; i < supportedPreviewFormats.size(); i++) {
            Log.d(H, " getSupportedPreviewFormats = " + supportedPreviewFormats.get(i));
        }
    }

    public void d(String str) {
        super.d(str);
        try {
            Class.forName("dji.pilot.support.longan.DJISupportLongan").getMethod("reflectNotifyNewMedia", new Class[]{String.class}).invoke(null, new Object[]{str});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void c(int i) {
        this.l = i;
        if (this.r != null && this.q != null) {
            e("setZoom mZoomValue = " + this.l);
            B();
        }
    }

    public int h() {
        return this.l;
    }

    public int i() {
        if (this.r == null || this.q == null) {
            return this.l;
        }
        return this.r.getMaxZoom();
    }

    public Parameters m() {
        return this.r;
    }

    private void e(int i) {
        if ((i & 1) != 0) {
            C();
        }
        if ((i & 2) != 0) {
            B();
        }
        if ((i & 4) != 0) {
            D();
        }
        a(this.r);
    }

    private void B() {
        if (this.r.isZoomSupported()) {
            this.r.setZoom(this.l);
        }
        b(this.r);
    }

    private void C() {
        int[] g = h.g(this.r);
        if (g != null && g.length > 0) {
            this.r.setPreviewFpsRange(g[0], g[1]);
        }
        this.r.set(h.b, h.e);
        if ("true".equals(Boolean.valueOf(this.r.isVideoStabilizationSupported()))) {
            this.r.setVideoStabilization(true);
        }
        if (!h.a(h.a, this.r.getSupportedFocusModes())) {
            return;
        }
        if (this.G) {
            this.r.setFocusMode("auto");
        } else {
            this.r.setFocusMode(h.a);
        }
    }

    private boolean D() {
        h.a((Activity) this.u, this.r.getSupportedPictureSizes(), this.r);
        Size pictureSize = this.r.getPictureSize();
        Log.v(H, "getPictureSize  size is " + pictureSize.width + "x" + pictureSize.height);
        this.r.getSupportedPictureSizes();
        pictureSize = h.a((Activity) this.u, this.r.getSupportedPreviewSizes());
        Size previewSize = this.r.getPreviewSize();
        Log.v(H, "Preview original size is " + previewSize.width + "x" + previewSize.height);
        if (!previewSize.equals(pictureSize)) {
            this.r.setPreviewSize(pictureSize.width, pictureSize.height);
            this.q.a(this.r, new f(this));
            this.r = this.q.j();
        }
        Log.v(H, "Preview optimal size is " + pictureSize.width + "x" + pictureSize.height);
        this.r.setJpegQuality(CameraProfile.getJpegEncodingQualityParameter(this.m, 2));
        int g = c.a().g();
        if (g >= 0) {
            if ("auto".equals(this.r.getSceneMode())) {
                if (h.a(c.a().e(g), this.r.getSupportedFlashModes())) {
                    Log.d(H, "updateCameraParametersPreference: flash = " + c.a().e(g));
                    this.r.setFlashMode(c.a().e(g));
                } else {
                    e("updateCameraParametersPreference setFlashMode: Don't support set FlashMode = " + c.a().e(g));
                    this.r.setFlashMode("off");
                }
            } else {
                e("updateCameraParametersPreference setFlashMode scene mode isn't auto!!!");
            }
        }
        g = c.a().f();
        if (g >= 0) {
            if (!"auto".equals(this.r.getSceneMode())) {
                e("updateCameraParametersPreference setWhiteBalance scene mode isn't auto");
            } else if (h.a(c.a().c(g), this.r.getSupportedWhiteBalance())) {
                this.r.setWhiteBalance(c.a().c(g));
                e("updateCameraParametersPreference setWhiteBalance WB = " + c.a().c(g));
            } else {
                e("updateCameraParametersPreference setWhiteBalance Don't support setWhiteBalance: " + c.a().c(g));
                this.r.setWhiteBalance("auto");
            }
        }
        return true;
    }

    private void e(String str) {
        Log.d(H, str);
    }
}
