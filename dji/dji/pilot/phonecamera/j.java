package dji.pilot.phonecamera;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.location.Location;
import android.media.CamcorderProfile;
import android.media.CameraProfile;
import android.media.MediaRecorder;
import android.media.MediaRecorder.OnErrorListener;
import android.media.MediaRecorder.OnInfoListener;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.SystemClock;
import android.provider.DocumentsContract;
import android.util.Log;
import android.widget.Toast;
import dji.gs.c.e;
import dji.pilot.g.a;
import dji.pilot.phonecamera.e.d;
import dji.thirdparty.a.c;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class j extends g implements OnErrorListener, OnInfoListener {
    public static final long H = 50000000;
    private static final String J = "DJILPVideoModule";
    String I = "auto";
    private MediaRecorder K;
    private String L;
    private Uri M;
    private ParcelFileDescriptor N = null;
    private int O = 0;
    private boolean P = false;
    private float Q = 0.0f;
    private boolean R = false;
    private boolean S = false;
    private int T;
    private int U;
    private CamcorderProfile V;
    private long W;
    private String X;

    public void a(int i, int i2, Context context, d dVar) {
        Log.d(J, "init");
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
            this.F = new a(this.u);
            this.F.a(true);
            this.X = dji.pilot.storage.a.f(this.u);
            this.r = this.q.j();
            g();
            o();
        }
    }

    public void c() {
        Log.v(J, "release camera device and CameraModule instance");
        if (this.q != null) {
            n();
        }
        if (this.R) {
            f();
        }
        l();
        j();
        this.v = false;
        this.u = null;
        this.t = null;
    }

    public void a(String str) {
        if (!"auto".equals(this.r.getSceneMode())) {
            f("setFlashMode scene mode isn't auto!!!");
        } else if (h.a(str, this.r.getSupportedFlashModes())) {
            this.r.setFlashMode(str);
            a(this.r);
        } else {
            f("setFlashMode: Don't support set FlashMode = " + str);
            this.r.setFlashMode("off");
            a(this.r);
        }
    }

    public void b(String str) {
        if (!"auto".equals(this.r.getSceneMode())) {
            f("setWhiteBalance scene mode isn't auto");
        } else if (h.a(str, this.r.getSupportedWhiteBalance())) {
            f("setWhiteBalance WB = " + str);
            this.I = str;
            this.r.setWhiteBalance(this.I);
            a(this.r);
        } else {
            f("setWhiteBalance Don't support setWhiteBalance: " + str);
            this.r.setWhiteBalance("auto");
        }
    }

    public void p() {
        if (!h.a(h.c, this.r.getSupportedSceneModes())) {
            f("setCameraHDR: Don't support set HDR");
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
            f("setCameraHDR: Don't support set : " + str);
            if (this.r.getSceneMode() == null) {
                this.r.setSceneMode("auto");
                a(this.r);
            }
        } else if (!str.equals(this.r.getSceneMode())) {
            this.r.setSceneMode(str);
            f("setCameraHDR set scene mode : " + str);
            a(this.r);
        }
    }

    public void d(int i) {
        if (this.r == null) {
            this.r = this.q.j();
        }
        int maxExposureCompensation = this.r.getMaxExposureCompensation();
        if (i < this.r.getMinExposureCompensation() || i > maxExposureCompensation) {
            Log.w(J, "invalid exposure range: " + i);
        } else {
            this.r.setExposureCompensation(i);
        }
        a(this.r);
    }

    public void c(int i) {
        this.l = i;
        if (this.r != null && this.q != null) {
            f("setZoom mZoomValue = " + this.l);
            this.r.setZoom(this.l);
            b(this.r);
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

    public int e() {
        Log.v(J, "startVideoRecording");
        if (this.R) {
            return -1;
        }
        if (dji.pilot.storage.a.a(this.X) <= dji.pilot.storage.a.k) {
            this.n.post(new Runnable(this) {
                final /* synthetic */ j a;

                {
                    this.a = r1;
                }

                public void run() {
                    Toast.makeText(this.a.u, this.a.u.getText(R.string.lp_phone_camera_record_storage_lack), 1).show();
                }
            });
            Log.v(J, "Storage issue, ignore the start request");
            return -2;
        }
        D();
        if (this.K == null) {
            Log.e(J, "Fail to initialize media recorder");
            return -3;
        } else if (this.S) {
            return -4;
        } else {
            E();
            try {
                this.K.start();
                this.q.k();
                this.r = this.q.j();
                Log.d(J, "startVideoRecording: start finish");
                this.R = true;
                this.W = SystemClock.uptimeMillis();
                c.a().e(dji.pilot.phonecamera.a.a.RECORD_START);
                return 0;
            } catch (Throwable e) {
                Log.e(J, "Could not start media recorder. ", e);
                A();
                this.q.d();
                return -5;
            }
        }
    }

    private void A() {
        Log.v(J, "Releasing media recorder.");
        if (this.K != null) {
            B();
            this.K.reset();
            this.K.release();
            this.K = null;
        }
        this.L = null;
    }

    private void B() {
        if (this.L != null) {
            File file = new File(this.L);
            if (file.length() == 0 && file.delete()) {
                Log.v(J, "Empty video file deleted: " + this.L);
                this.L = null;
            }
        }
    }

    private void C() {
        l();
        k();
    }

    private void e(int i) {
        String str = a(System.currentTimeMillis()) + g(i);
        this.X = dji.pilot.storage.a.f(this.u) + dji.pilot.storage.a.d;
        File file = new File(this.X);
        if (!file.exists()) {
            file.mkdir();
        }
        this.L = this.X + '/' + str;
        Log.v(J, "generateVideoFilename New video filename: " + this.L);
    }

    private void f(int i) {
        try {
            this.M = dji.pilot.storage.a.a(this.u, 2, g(i), dji.pilot.storage.a.d);
            this.N = this.u.getContentResolver().openFileDescriptor(this.M, "rw");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(J, "Couldn't create media video file; check storage permissions?");
        }
    }

    private String g(int i) {
        if (i == 2) {
            return ".mp4";
        }
        return ".3gp";
    }

    private String a(long j) {
        return new SimpleDateFormat("'DJI'_yyyyMMdd_HHmmss").format(new Date(j));
    }

    private static void a(MediaRecorder mediaRecorder, double d) {
        mediaRecorder.setCaptureRate(d);
    }

    private void D() {
        Log.v(J, "initializeRecorder");
        if (this.q != null) {
            H();
            this.K = new MediaRecorder();
            this.q.a(true);
            try {
                this.q.c();
                if (this.S) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.S = true;
                if (this.S) {
                    return;
                }
            } catch (Throwable th) {
                if (this.S) {
                    return;
                }
            }
            if (this.P) {
                a(this.K, 1.0d / ((double) this.Q));
            }
            this.K.setCamera(this.q.a());
            if (!this.P) {
                this.K.setAudioSource(5);
            }
            this.K.setVideoSource(1);
            Log.d(J, "initializeRecorder: video bit rate = " + this.V.videoBitRate + " audio bit rate = " + this.V.audioBitRate);
            this.K.setProfile(this.V);
            F();
            this.O = dji.pilot.phonecamera.a.c.a().v();
            this.K.setMaxDuration(this.O * 1000);
            if (dji.pilot.storage.a.c(this.u)) {
                f(this.V.fileFormat);
                this.K.setOutputFile(this.N.getFileDescriptor());
            } else {
                e(this.V.fileFormat);
                this.K.setOutputFile(this.L);
            }
            long a = dji.pilot.storage.a.a(this.X) - dji.pilot.storage.a.k;
            if (0 > 0 && 0 < a) {
                a = 0;
            }
            try {
                this.K.setMaxFileSize(a);
            } catch (RuntimeException e2) {
            }
            int i = 0;
            if (this.w != -1) {
                CameraInfo cameraInfo = d.a().e()[this.m];
                if (cameraInfo.facing == 1) {
                    i = ((cameraInfo.orientation - this.w) + 360) % 360;
                } else {
                    i = (cameraInfo.orientation + this.w) % 360;
                }
            }
            this.K.setOrientationHint(i);
            h.a(this.K, this.F.a());
            try {
                this.K.prepare();
                this.K.setOnErrorListener(this);
                this.K.setOnInfoListener(this);
            } catch (Throwable e3) {
                Log.e(J, "prepare failed for " + this.L, e3);
                A();
                throw new RuntimeException(e3);
            }
        }
    }

    private void E() {
        Intent intent = new Intent("com.android.music.musicservicecommand");
        intent.putExtra("command", "pause");
        this.u.sendBroadcast(intent);
    }

    private void F() {
        if (this.V != null) {
            long a = ((dji.pilot.storage.a.a(this.X) - dji.pilot.storage.a.k) * 8) / ((long) (this.V.audioBitRate + this.V.videoBitRate));
            Log.d(J, "calcRecordingTime: time = " + a);
            if (dji.pilot.phonecamera.a.c.a().v() == 0) {
                dji.pilot.phonecamera.a.c.a().n((int) a);
            }
        }
    }

    public void k() {
        if (!this.R && this.q != null && this.t != null) {
            if (this.v) {
                l();
            }
            g();
            this.q.a(this.t);
            H();
            G();
            f("startPreview");
            this.q.e();
            this.v = true;
        }
    }

    public void l() {
        if (this.v) {
            this.q.f();
            this.v = false;
        }
    }

    public Parameters m() {
        return this.r;
    }

    private void G() {
        Log.d(J, "mDesiredPreviewWidth=" + this.T + ". mDesiredPreviewHeight=" + this.U);
        this.r.setPreviewSize(this.T, this.U);
        int[] h = h.h(this.r);
        if (h.length > 0) {
            this.r.setPreviewFpsRange(h[0], h[1]);
        } else {
            this.r.setPreviewFrameRate(this.V.videoFrameRate);
        }
        if (h.a(this.I, this.r.getSupportedWhiteBalance())) {
            this.r.setWhiteBalance(this.I);
        } else {
            this.I = this.r.getWhiteBalance();
            if (this.I == null) {
                this.I = "auto";
            }
        }
        if (this.r.isZoomSupported()) {
            this.r.setZoom(this.l);
        }
        if (h.a("continuous-video", this.r.getSupportedFocusModes())) {
            Log.d(J, "setCameraParameters: FOCUS_MODE_CONTINUOUS_VIDEO");
            this.r.setFocusMode("continuous-video");
        }
        a(false, false);
        this.r.set(h.b, "true");
        this.r.set("video-stabilization", h.e);
        Size b = h.b(this.r.getSupportedPictureSizes(), ((double) this.T) / ((double) this.U));
        if (!this.r.getPictureSize().equals(b)) {
            this.r.setPictureSize(b.width, b.height);
        }
        Log.v(J, "Video snapshot size is " + b.width + "x" + b.height);
        this.r.setJpegQuality(CameraProfile.getJpegEncodingQualityParameter(this.m, 2));
        a(this.r);
    }

    private void H() {
        int i;
        int l = dji.pilot.phonecamera.a.c.a().l();
        this.Q = dji.pilot.phonecamera.a.c.a().p();
        this.P = this.Q != 0.0f;
        if (this.P) {
            switch (l) {
                case 4:
                    if (!CamcorderProfile.hasProfile(this.m, 1004)) {
                        i = 1001;
                        break;
                    }
                    Log.d(J, "don't support time lapse 480P: ");
                    i = 1004;
                    break;
                case 5:
                    if (!CamcorderProfile.hasProfile(this.m, 1005)) {
                        Log.d(J, "don't support time lapse 720P: ");
                        i = 1001;
                        break;
                    }
                    i = 1005;
                    break;
                case 6:
                    if (!CamcorderProfile.hasProfile(this.m, 1006)) {
                        Log.d(J, "don't support time lapse 1080P: ");
                        i = 1001;
                        break;
                    }
                    i = 1006;
                    break;
                case 8:
                    if (!CamcorderProfile.hasProfile(this.m, 1008)) {
                        Log.d(J, "don't support time lapse 2160P: ");
                        i = 1001;
                        break;
                    }
                    i = 1001;
                    break;
                default:
                    i = 1001;
                    break;
            }
        }
        i = l;
        if (i == 8 && dji.pilot.phonecamera.a.c.a().w()) {
            Log.d(J, "readVideoPreferences: force 4K UHD video");
            this.V = CamcorderProfile.get(this.m, 1);
            this.V.videoFrameWidth = 3840;
            this.V.videoFrameHeight = 2160;
            this.V.videoBitRate = (int) (((double) this.V.videoBitRate) * 2.8d);
        } else if (CamcorderProfile.hasProfile(this.m, i)) {
            this.V = CamcorderProfile.get(this.m, i);
        } else if (this.P) {
            this.V = CamcorderProfile.get(this.m, 1001);
        } else {
            this.V = CamcorderProfile.get(this.m, 1);
        }
        Log.d(J, "readVideoPreferences: videoFrameWidth = " + this.V.videoFrameWidth + " videoFrameHight = " + this.V.videoFrameHeight);
        I();
        i = dji.pilot.phonecamera.a.c.a().g();
        if (i >= 0) {
            if ("auto".equals(this.r.getSceneMode())) {
                if (h.a(dji.pilot.phonecamera.a.c.a().e(i), this.r.getSupportedFlashModes())) {
                    Log.d(J, "readVideoPreferences: flash = " + dji.pilot.phonecamera.a.c.a().e(i));
                    this.r.setFlashMode(dji.pilot.phonecamera.a.c.a().e(i));
                } else {
                    f("readVideoPreferences setFlashMode: Don't support set FlashMode = " + dji.pilot.phonecamera.a.c.a().e(i));
                    this.r.setFlashMode("off");
                }
            } else {
                f("readVideoPreferences setFlashMode scene mode isn't auto!!!");
            }
        }
        i = dji.pilot.phonecamera.a.c.a().f();
        if (i >= 0) {
            if (!"auto".equals(this.r.getSceneMode())) {
                f("readVideoPreferences setWhiteBalance scene mode isn't auto");
            } else if (h.a(dji.pilot.phonecamera.a.c.a().c(i), this.r.getSupportedWhiteBalance())) {
                this.r.setWhiteBalance(dji.pilot.phonecamera.a.c.a().c(i));
                f("readVideoPreferences setWhiteBalance WB = " + dji.pilot.phonecamera.a.c.a().c(i));
            } else {
                f("readVideoPreferences setWhiteBalance Don't support setWhiteBalance: " + dji.pilot.phonecamera.a.c.a().c(i));
                this.r.setWhiteBalance("auto");
            }
        }
        b(this.r);
    }

    @TargetApi(11)
    private void I() {
        if (this.q != null) {
            this.r = this.q.j();
            if (this.r.getSupportedVideoSizes() == null) {
                this.T = this.V.videoFrameWidth;
                this.U = this.V.videoFrameHeight;
            } else {
                List supportedPreviewSizes = this.r.getSupportedPreviewSizes();
                Size preferredPreviewSizeForVideo = this.r.getPreferredPreviewSizeForVideo();
                int i = preferredPreviewSizeForVideo.width * preferredPreviewSizeForVideo.height;
                Iterator it = supportedPreviewSizes.iterator();
                while (it.hasNext()) {
                    preferredPreviewSizeForVideo = (Size) it.next();
                    if (preferredPreviewSizeForVideo.height * preferredPreviewSizeForVideo.width > i) {
                        it.remove();
                    }
                }
                preferredPreviewSizeForVideo = h.a((Activity) this.u, supportedPreviewSizes, ((double) this.V.videoFrameWidth) / ((double) this.V.videoFrameHeight));
                this.T = preferredPreviewSizeForVideo.width;
                this.U = preferredPreviewSizeForVideo.height;
            }
            f("mDesiredPreviewWidth=" + this.T + ". mDesiredPreviewHeight=" + this.U);
        }
    }

    private String J() {
        String m = dji.pilot.phonecamera.a.c.a().m();
        if (m.equals("4K")) {
            return Integer.toString(8);
        }
        if (m.equals("1080P")) {
            return Integer.toString(6);
        }
        if (m.equals(dji.pilot.phonecamera.a.c.A)) {
            return Integer.toString(5);
        }
        if (m.equals("480P")) {
            return Integer.toString(4);
        }
        return Integer.toString(5);
    }

    public void onError(MediaRecorder mediaRecorder, int i, int i2) {
        Log.e(J, "MediaRecorder error. what=" + i + ". extra=" + i2);
        if (i == 1) {
            if (this.R) {
                K();
            }
            Toast.makeText(this.u, this.u.getText(R.string.lp_phone_camera_record_storage_lack), 1).show();
        }
    }

    public void onInfo(MediaRecorder mediaRecorder, int i, int i2) {
        Log.d(J, "onInfo: what=" + i + ". extra=" + i2);
        if (i == e.g) {
            if (this.R) {
                K();
            }
        } else if (i == 801) {
            if (this.R) {
                K();
            }
            Toast.makeText(this.u, this.u.getText(R.string.lp_phone_camera_record_storage_limited_reach), 1).show();
        }
    }

    private void K() {
        c.a().e(dji.pilot.phonecamera.a.a.ON_RECORD_STOP);
    }

    @TargetApi(19)
    public boolean f() {
        boolean z;
        boolean z2 = true;
        Log.v(J, "stopVideoRecording");
        if (this.R) {
            try {
                this.K.setOnErrorListener(null);
                this.K.setOnInfoListener(null);
                this.K.stop();
                z = true;
            } catch (Throwable e) {
                Log.e(J, "stop fail", e);
                if (dji.pilot.storage.a.c(this.u)) {
                    Log.d(J, "delete corrupt video: " + this.M);
                    DocumentsContract.deleteDocument(this.u.getContentResolver(), this.M);
                } else if (this.L != null) {
                    e(this.L);
                }
                z2 = false;
                z = false;
            }
            this.R = false;
            if (z2 && r2) {
                if (dji.pilot.storage.a.c(this.u)) {
                    File a = dji.pilot.storage.a.a(this.M);
                    Log.d(J, "real file: " + a);
                    if (a != null) {
                        d(a.getAbsolutePath());
                    }
                } else {
                    d(this.L);
                }
            }
        } else {
            z = true;
        }
        try {
            if (this.N != null) {
                this.N.close();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        this.N = null;
        A();
        this.q.d();
        l();
        k();
        this.r = this.q.j();
        c.a().e(dji.pilot.phonecamera.a.a.RECORD_STOP);
        return z;
    }

    public void d(String str) {
        super.d(str);
        if (this.R) {
            try {
                Class.forName("dji.pilot.support.longan.DJISupportLongan").getMethod("reflectNotifyNewMedia", new Class[]{String.class}).invoke(null, new Object[]{str});
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(J, "notifyNewMedia: " + str);
        c.a().e(new dji.midware.media.j.c.a(str));
    }

    private void e(String str) {
        Log.v(J, "Deleting video " + str);
        if (!new File(str).delete()) {
            Log.v(J, "Could not delete " + str);
        }
    }

    public void n() {
        this.q.g();
        this.r.setFocusMode("continuous-video");
        a(this.r);
    }

    public Location y() {
        return this.F == null ? null : this.F.a();
    }

    public boolean d() {
        Log.d(J, "takePicture");
        this.r.setRotation(h.a(this.m, this.w));
        a(this.r);
        h.a(this.r, this.F.a());
        a(this.r);
        this.q.a(this.n, new g(this), null, null, new c(this));
        return true;
    }

    public MediaRecorder z() {
        return this.K;
    }

    private void f(String str) {
        Log.d(J, str);
    }
}
