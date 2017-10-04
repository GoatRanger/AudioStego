package com.nokia.maps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.opengl.GLES20;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.here.android.mpa.ar.ARController.Error;
import com.here.odnp.config.OdnpConfigStatic;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public final class a implements PreviewCallback {
    private static boolean h = false;
    private static boolean i = false;
    private static volatile List<Size> p = null;
    private static com.here.android.mpa.common.Size q = h.b;
    private static PointF r = new PointF(0.0f, 0.0f);
    private static float w = 40.0f;
    private static float x = 60.0f;
    private final Runnable A = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.s();
        }
    };
    private final Runnable B = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.t();
        }
    };
    final ar a = new ar();
    final ar b = new ar();
    final ar c = new ar();
    final ar d = new ar();
    final Runnable e = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.o();
        }
    };
    final Runnable f = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.p();
        }
    };
    final Runnable g = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.k();
            Log.d("livesight", "*** Camera timeout on Android 5.x");
        }
    };
    private WindowManager j = null;
    private Context k;
    private SurfaceTexture l;
    private volatile Camera m;
    private int n = -1;
    private final int[] o = new int[1];
    private final fd s = new fd();
    private final AtomicBoolean t = new AtomicBoolean(false);
    private boolean u = false;
    private Parameters v = null;
    private a y = a.None;
    private a z = a.None;

    private enum a {
        None,
        Starting,
        Stopping;

        public static a[] a() {
            return (a[]) d.clone();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    a(android.content.Context r6) {
        /*
        r5 = this;
        r4 = 0;
        r1 = 1;
        r3 = -1;
        r2 = 0;
        r5.<init>();
        r0 = new com.nokia.maps.ar;
        r0.<init>();
        r5.a = r0;
        r0 = new com.nokia.maps.ar;
        r0.<init>();
        r5.b = r0;
        r0 = new com.nokia.maps.ar;
        r0.<init>();
        r5.c = r0;
        r0 = new com.nokia.maps.ar;
        r0.<init>();
        r5.d = r0;
        r5.j = r4;
        r5.n = r3;
        r0 = new int[r1];
        r5.o = r0;
        r0 = new com.nokia.maps.fd;
        r0.<init>();
        r5.s = r0;
        r0 = new java.util.concurrent.atomic.AtomicBoolean;
        r0.<init>(r2);
        r5.t = r0;
        r5.u = r2;
        r5.v = r4;
        r0 = com.nokia.maps.a.a.None;
        r5.y = r0;
        r0 = com.nokia.maps.a.a.None;
        r5.z = r0;
        r0 = new com.nokia.maps.a$1;
        r0.<init>(r5);
        r5.e = r0;
        r0 = new com.nokia.maps.a$2;
        r0.<init>(r5);
        r5.f = r0;
        r0 = new com.nokia.maps.a$3;
        r0.<init>(r5);
        r5.A = r0;
        r0 = new com.nokia.maps.a$4;
        r0.<init>(r5);
        r5.B = r0;
        r0 = new com.nokia.maps.a$5;
        r0.<init>(r5);
        r5.g = r0;
        r0 = i;
        if (r0 != 0) goto L_0x007e;
    L_0x006c:
        i = r1;
        r0 = r6.getPackageManager();
        r1 = "android.hardware.camera";
        r0 = r0.hasSystemFeature(r1);
        h = r0;
        r0 = h;
        if (r0 != 0) goto L_0x007e;
    L_0x007e:
        r5.k = r6;
        r0 = r5.o;
        r0[r2] = r3;
        r0 = r5.k;
        r1 = "window";
        r0 = r0.getSystemService(r1);
        r0 = (android.view.WindowManager) r0;
        r5.j = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nokia.maps.a.<init>(android.content.Context):void");
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        if (bArr != null && this.m != null) {
            if (this.c.b() || !this.c.a(bArr, q)) {
                a(bArr);
            }
        }
    }

    synchronized void a(boolean z) {
        if (!z) {
            switch (this.y) {
                case None:
                    this.y = a.Starting;
                    this.s.a(this.e);
                    break;
                case Starting:
                    this.s.a(this.e);
                    break;
                case Stopping:
                    this.y = a.Starting;
                    this.s.a(this.e);
                    break;
                default:
                    break;
            }
        }
        switch (this.y) {
            case None:
                this.s.b(this.f);
                this.s.b(this.e);
                this.y = a.Starting;
                o();
                break;
            case Starting:
                this.s.b(this.f);
                this.s.b(this.e);
                o();
                break;
            case Stopping:
                this.s.b(this.f);
                this.s.b(this.e);
                p();
                this.y = a.Starting;
                o();
                break;
        }
    }

    synchronized void b(boolean z) {
        if (!z) {
            switch (this.y) {
                case None:
                    this.y = a.Stopping;
                    this.s.a(this.f);
                    break;
                case Starting:
                    this.y = a.Stopping;
                    this.s.a(this.f);
                    break;
                case Stopping:
                    this.s.a(this.f);
                    break;
                default:
                    break;
            }
        }
        switch (this.y) {
            case None:
                this.s.b(this.f);
                this.s.b(this.e);
                this.y = a.Stopping;
                p();
                break;
            case Starting:
                this.s.b(this.f);
                this.s.b(this.e);
                p();
                break;
            case Stopping:
                this.s.b(this.f);
                this.s.b(this.e);
                p();
                break;
        }
    }

    synchronized void c(boolean z) {
        this.s.b(this.g);
        if (!z) {
            this.z = a.Starting;
            this.s.a(this.A);
            switch (this.z) {
                case None:
                    this.z = a.Starting;
                    this.s.a(this.A);
                    break;
                case Starting:
                    this.s.a(this.A);
                    break;
                case Stopping:
                    this.z = a.Starting;
                    this.s.a(this.A);
                    break;
                default:
                    break;
            }
        }
        switch (this.z) {
            case None:
                this.s.b(this.B);
                this.s.b(this.A);
                this.z = a.Starting;
                s();
                break;
            case Starting:
                this.s.b(this.B);
                this.s.b(this.A);
                s();
                break;
            case Stopping:
                this.s.b(this.B);
                this.s.b(this.A);
                t();
                this.z = a.Starting;
                s();
                break;
        }
    }

    synchronized void d(boolean z) {
        if (!z) {
            switch (this.z) {
                case None:
                    this.z = a.Stopping;
                    this.s.a(this.B);
                    break;
                case Starting:
                    this.z = a.Stopping;
                    this.s.a(this.B);
                    break;
                case Stopping:
                    this.s.a(this.B);
                    break;
                default:
                    break;
            }
        }
        switch (this.z) {
            case None:
                this.s.b(this.B);
                this.s.b(this.A);
                this.z = a.Stopping;
                t();
                break;
            case Starting:
                this.s.b(this.B);
                this.s.b(this.A);
                t();
                break;
            case Stopping:
                this.s.b(this.B);
                this.s.b(this.A);
                t();
                break;
        }
    }

    private synchronized void o() {
        boolean z = true;
        synchronized (this) {
            if (this.y != a.None) {
                this.y = a.None;
                q = b(q);
                if (this.t.get()) {
                    ar arVar = this.a;
                    if (this.m == null) {
                        z = false;
                    }
                    arVar.a(null, Boolean.valueOf(z));
                } else {
                    this.t.set(true);
                    this.a.a(null, Boolean.valueOf(r()));
                }
            }
        }
    }

    private synchronized void p() {
        if (this.y != a.None) {
            this.y = a.None;
            if (this.t.get()) {
                this.t.set(false);
                d(true);
            }
        }
    }

    int a() {
        return this.n;
    }

    private Camera q() {
        int numberOfCameras = Camera.getNumberOfCameras();
        CameraInfo cameraInfo = new CameraInfo();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == 0) {
                this.n = i;
                return Camera.open(i);
            }
        }
        return null;
    }

    private synchronized boolean r() {
        boolean z;
        if (h) {
            z = true;
            try {
                if (this.m == null) {
                    this.m = q();
                    z = l();
                    Log.d("livesight", "*** Camera opened, id: " + this.n);
                }
            } catch (Exception e) {
                Log.e(h.a, e.toString());
                z = false;
            }
            if (!z) {
                if (this.m != null) {
                    this.m.release();
                    this.m = null;
                    Log.d("livesight", "*** Camera released on falure to set parameters");
                }
                this.d.a(null, Error.CAMERA_UNAVAILABLE);
            }
        } else {
            z = false;
        }
        return z;
    }

    private synchronized void s() {
        boolean z = true;
        synchronized (this) {
            if (this.z != a.None) {
                if (this.m == null) {
                    a(true);
                }
                this.z = a.None;
                if (this.m != null) {
                    this.s.b(this.A);
                    if (!this.u) {
                        this.u = true;
                        PixelFormat pixelFormat = new PixelFormat();
                        PixelFormat.getPixelFormatInfo(this.v.getPreviewFormat(), pixelFormat);
                        int i = (pixelFormat.bitsPerPixel * (q.width * q.height)) / 8;
                        for (int i2 = 0; i2 < h.d; i2++) {
                            this.m.addCallbackBuffer(new byte[i]);
                        }
                        this.m.setPreviewCallbackWithBuffer(this);
                        try {
                            this.m.startPreview();
                        } catch (Exception e) {
                            Log.d(h.a, "++ ARCamera preview start FAILED " + e.toString());
                            z = false;
                        }
                        this.b.a(null, Boolean.valueOf(z));
                    }
                }
            }
        }
    }

    private synchronized void t() {
        if (this.z != a.None) {
            this.z = a.None;
            if (this.m != null) {
                this.s.b(this.B);
                if (this.u) {
                    this.u = false;
                    this.m.stopPreview();
                    this.m.setPreviewCallbackWithBuffer(null);
                    if (VERSION.SDK_INT >= 21) {
                        this.s.b(this.g);
                        this.s.a(this.g, OdnpConfigStatic.OEM_MAX_HIGH_POWER_INTERVAL);
                    }
                }
            }
        }
    }

    boolean b() {
        return this.u;
    }

    public static void a(com.here.android.mpa.common.Size size) {
        if (size != null) {
            q = size;
        }
    }

    public static com.here.android.mpa.common.Size c() {
        return q;
    }

    public static void a(int i) {
        h.c = i;
    }

    public static int d() {
        return h.c;
    }

    public static List<Size> e() {
        if (p != null) {
            return p;
        }
        try {
            Camera open = Camera.open();
            if (open == null) {
                return null;
            }
            Parameters parameters = open.getParameters();
            open.release();
            p = parameters.getSupportedPreviewSizes();
            return p;
        } catch (Exception e) {
            return null;
        }
    }

    private static Camera u() {
        try {
            return Camera.open();
        } catch (Exception e) {
            return null;
        }
    }

    com.here.android.mpa.common.Size f() {
        return new com.here.android.mpa.common.Size(q.width, q.height);
    }

    synchronized com.here.android.mpa.common.Size g() {
        com.here.android.mpa.common.Size f;
        f = f();
        if (x()) {
            int i = f.width;
            f.width = f.height;
            f.height = i;
        }
        return f;
    }

    com.here.android.mpa.common.Size b(com.here.android.mpa.common.Size size) {
        if (e() == null) {
            return q;
        }
        com.here.android.mpa.common.Size size2 = new com.here.android.mpa.common.Size(Integer.MAX_VALUE, Integer.MAX_VALUE);
        com.here.android.mpa.common.Size size3 = size2;
        for (Size size4 : p) {
            if (size4.width >= size.width || size4.height >= size.height) {
                if (size4.width == size.width && size4.height == size.height) {
                    break;
                } else if (size4.width <= size3.width || size4.height <= size3.height) {
                    size2 = new com.here.android.mpa.common.Size(size4.width, size4.height);
                    size3 = size2;
                }
            }
            size2 = size3;
            size3 = size2;
        }
        size = size3;
        if (size.width == Integer.MAX_VALUE) {
            return null;
        }
        return size;
    }

    synchronized PointF h() {
        Parameters w = w();
        if (w != null) {
            if (x()) {
                w = w.getVerticalViewAngle();
                x = w.getHorizontalViewAngle();
            } else {
                w = w.getHorizontalViewAngle();
                x = w.getVerticalViewAngle();
            }
        }
        return new PointF(w, x);
    }

    final synchronized PointF a(int i, int i2) {
        PointF pointF;
        if (i <= 0 || i2 <= 0) {
            pointF = new PointF(w, x);
        } else if (this.m != null || r()) {
            int i3;
            int i4;
            com.here.android.mpa.common.Size f = f();
            if (x()) {
                i3 = f.height;
                i4 = f.width;
            } else {
                i3 = f.width;
                i4 = f.height;
            }
            float max = Math.max(((float) i2) / ((float) i4), ((float) i) / ((float) i3));
            int ceil = (int) Math.ceil((double) (((float) i3) - (((((float) i3) * max) - ((float) i)) / max)));
            int ceil2 = (int) Math.ceil((double) (((float) i4) - (((((float) i4) * max) - ((float) i2)) / max)));
            PointF h = h();
            r = new PointF(a(h.x, (float) i3, (float) ceil), a(h.y, (float) i4, (float) ceil2));
            pointF = r;
        } else {
            pointF = new PointF(w, x);
        }
        return pointF;
    }

    private float a(float f, float f2, float f3) {
        return (float) (2.0d * Math.toDegrees(Math.atan(((double) (f3 / f2)) * Math.tan(Math.toRadians((double) (f / 2.0f))))));
    }

    @SuppressLint({"NewApi"})
    synchronized void i() {
        if (this.o[0] == -1) {
            GLES20.glGenTextures(1, this.o, 0);
            GLES20.glBindTexture(36197, this.o[0]);
            v();
            GLES20.glTexParameterf(36197, 10241, 9729.0f);
            GLES20.glTexParameterf(36197, 10240, 9729.0f);
            GLES20.glTexParameteri(36197, 10242, 33071);
            GLES20.glTexParameteri(36197, 10243, 33071);
            v();
            GLES20.glBindTexture(36197, 0);
            v();
            this.l = new SurfaceTexture(this.o[0]);
        }
    }

    synchronized void a(byte[] bArr) {
        if (bArr != null) {
            if (this.m != null) {
                this.m.addCallbackBuffer(bArr);
            }
        }
    }

    synchronized void j() {
        if (this.o[0] != -1) {
            GLES20.glDeleteTextures(1, this.o, 0);
            this.o[0] = -1;
        }
    }

    public synchronized void k() {
        this.s.b(this.g);
        if (this.m != null) {
            b(true);
            this.m.release();
            this.m = null;
            Log.d("livesight", "*** Camera released");
        }
    }

    private void v() {
        if (GLES20.glGetError() == 0) {
        }
    }

    @SuppressLint({"NewApi"})
    synchronized boolean l() throws IOException {
        boolean z = false;
        synchronized (this) {
            if (this.l != null) {
                if (this.m != null) {
                    if (this.v == null) {
                        this.v = this.m.getParameters();
                        p = this.v.getSupportedPreviewSizes();
                    }
                    if (p == null && this.v != null) {
                        p = this.v.getSupportedPreviewSizes();
                    }
                    this.v.setPreviewSize(q.width, q.height);
                    for (Integer intValue : this.v.getSupportedPreviewFormats()) {
                        if (intValue.intValue() == 17) {
                            this.v.setPreviewFormat(17);
                            break;
                        }
                    }
                    List supportedPreviewFrameRates = this.v.getSupportedPreviewFrameRates();
                    int previewFrameRate = this.v.getPreviewFrameRate();
                    Integer valueOf = Integer.valueOf(h.c);
                    if (!(previewFrameRate == h.c || supportedPreviewFrameRates == null || !supportedPreviewFrameRates.contains(valueOf))) {
                        this.v.setPreviewFrameRate(h.c);
                    }
                    this.v.setRecordingHint(true);
                    this.m.setParameters(this.v);
                    this.m.cancelAutoFocus();
                    this.m.setPreviewTexture(this.l);
                    z = true;
                }
            }
        }
        return z;
    }

    private Parameters w() {
        if (this.v == null) {
            if (this.m != null) {
                this.v = this.m.getParameters();
                return this.v;
            }
            Camera u = u();
            if (u != null) {
                this.v = u.getParameters();
                u.release();
            }
        }
        return this.v;
    }

    public static float m() {
        return r.x;
    }

    public static float n() {
        return r.y;
    }

    private boolean x() {
        int rotation = this.j.getDefaultDisplay().getRotation();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.j.getDefaultDisplay().getMetrics(displayMetrics);
        if (rotation == 0 || rotation == 2) {
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                return false;
            }
            return true;
        } else if (rotation != 1 && rotation != 3) {
            throw new RuntimeException("Rotation value returned by Window Manager is invalid");
        } else if (displayMetrics.widthPixels >= displayMetrics.heightPixels) {
            return false;
        } else {
            return true;
        }
    }
}
