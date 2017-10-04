package dji.midware.media.h.b;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import dji.log.DJILogHelper;
import dji.midware.media.e;
import dji.midware.media.h.a.b;
import dji.midware.media.h.a.c;
import dji.midware.media.h.a.d;
import dji.midware.media.h.a.f;
import java.nio.ByteBuffer;

public class a implements OnFrameAvailableListener, b {
    public static final String a = "GLYUVSurface";
    public static final boolean b = false;
    public static final boolean c = false;
    public static final int d = 1920;
    public static final int e = 1080;
    private static final boolean g = false;
    private int A;
    private int B;
    private boolean C = false;
    private boolean D = false;
    private boolean E = false;
    private HandlerThread F = null;
    private Object G = new Object();
    private Handler H;
    private int I;
    private int J;
    boolean f = true;
    private int h = 0;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private SurfaceTexture l;
    private Surface m;
    private int n = -1;
    private float[] o = new float[16];
    private int p = -1;
    private int q = -1;
    private ByteBuffer r = null;
    private dji.midware.media.h.a s;
    private dji.midware.media.h.a t;
    private c u = null;
    private b v = null;
    private f w = null;
    private d x = null;
    private dji.midware.media.k.b.a y = null;
    private Object z = new Object();

    private SurfaceTexture d() {
        return this.l;
    }

    private void e() {
        d().updateTexImage();
        if (dji.midware.media.d.a(false)) {
            Log.i(a, "after updateTexImage() : time=" + System.currentTimeMillis());
        }
    }

    public void a(int i, int i2) {
        a(i, i2, 0, 0);
    }

    public synchronized void a(int i, int i2, int i3, int i4) {
        this.A = i;
        this.B = i2;
        this.I = i4;
        this.J = i3;
        e.a("GLYUVSresizeSurface: width=" + i + " height=" + i2);
    }

    public void a(Object obj, int i, int i2) {
        a(obj, i, i2, 0, 0);
    }

    public synchronized void a(Object obj, int i, int i2, int i3, int i4) {
        if (this.C) {
            c();
        }
        Log.i(a, "viewWidth=" + i + " viewHeight=" + i2);
        this.A = i;
        this.B = i2;
        this.I = i4;
        this.J = i3;
        this.C = true;
        Log.i(a, String.format("Thread %s calls GLRenderManager's init()", new Object[]{Thread.currentThread().getName()}));
        if (obj == null) {
            this.D = false;
        } else {
            this.D = true;
            this.s = new dji.midware.media.h.a();
            this.s.g();
            this.t = new dji.midware.media.h.a();
            this.t.b();
            this.t.a(obj);
            this.t.f();
            this.u = new c(true, false);
            this.u.e();
            this.v = new b(true);
            this.v.e();
            Log.i(a, " create renders done");
            if (this.k) {
                this.n = dji.midware.media.h.d.a(36197, false);
            } else {
                this.n = dji.midware.media.h.d.a(36197, true);
            }
            Log.i(a, "gen inputSurfaceTexture() done");
            this.t.e();
            this.s.f();
            this.f = true;
            this.F = new HandlerThread("GLYUVSurfaceThread", -1);
            this.F.start();
            Looper looper = this.F.getLooper();
            synchronized (this.G) {
                this.H = new Handler(looper, new Callback(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public boolean handleMessage(Message message) {
                        this.a.onFrameAvailable(null);
                        return false;
                    }
                });
                this.H.post(new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b();
                        synchronized (this.a.G) {
                            this.a.G.notify();
                        }
                        this.a.d().setOnFrameAvailableListener(this.a);
                    }
                });
                try {
                    this.G.wait();
                } catch (InterruptedException e) {
                }
            }
            Log.i(a, "init GLYUVSurface successful!");
        }
    }

    public void a(Object obj) {
        if (obj != null) {
            this.s.g();
            this.t.f();
            this.t.c();
            this.t.a(obj);
            this.t.e();
            this.s.f();
        }
    }

    public Surface a() {
        if (this.m != null) {
            return this.m;
        }
        if (d() == null) {
            return null;
        }
        this.m = new Surface(d());
        return this.m;
    }

    public void b() {
        this.l = new SurfaceTexture(this.n);
    }

    private void f() {
        dji.midware.util.a.a.getInstance("GLYUVSurface.display").a(dji.midware.util.a.a.a, Integer.valueOf(1));
        this.l.getTransformMatrix(this.o);
        if (this.i) {
            if (this.w == null) {
                this.w = new f(true, this.h);
                this.w.e();
            }
            this.w.a(this.n, 36197, this.o, false, (float) this.I, this.J, 0, this.A, this.B);
        } else if (this.j || this.k) {
            if (this.x == null) {
                if (this.k) {
                    this.x = new dji.midware.media.h.a.e(true);
                } else {
                    this.x = new dji.midware.media.h.a.a(true);
                }
                this.x.e();
            }
            this.x.c(this.n, 36197, this.o, false, (float) this.I, this.A, this.B, this.J, 0, this.A, this.B);
        } else {
            this.v.a(this.n, 36197, this.o, false, (float) this.I, this.J, 0, this.A, this.B);
        }
        if (dji.midware.media.d.a(false)) {
            Log.i(a, "after display() : time=" + System.currentTimeMillis());
        }
    }

    public synchronized Bitmap b(int i, int i2) {
        Bitmap createBitmap;
        boolean z = this.f;
        this.f = false;
        this.s.g();
        this.t.f();
        e();
        int b = dji.midware.media.h.d.b();
        g();
        try {
            this.l.getTransformMatrix(this.o);
            this.v.a(this.n, 36197, this.o, true, (float) (360 - this.I), this.J, 0, 1920, e);
            if (this.r == null) {
                this.r = ByteBuffer.allocate(dji.midware.media.d.q);
            }
            this.v.b(this.r, 1920, e);
        } catch (Exception e) {
            e.a(a, e);
        }
        dji.midware.media.h.d.d(b);
        this.t.e();
        this.s.f();
        this.f = z;
        createBitmap = Bitmap.createBitmap(1920, e, Config.ARGB_8888);
        this.r.position(0);
        this.r.limit(dji.midware.media.d.q);
        createBitmap.copyPixelsFromBuffer(this.r);
        return createBitmap;
    }

    private void c(int i, int i2) {
        this.l.getTransformMatrix(this.o);
        this.u.a(this.n, 36197, this.o, true, 0.0f, i, i2);
        if (dji.midware.media.d.a(false)) {
            Log.i(a, "after drawYPlane() : time=" + System.currentTimeMillis());
        }
    }

    @SuppressLint({"NewApi"})
    public synchronized void c() {
        if (this.C) {
            this.f = false;
            this.C = false;
            this.E = false;
            if (this.D) {
                this.s.g();
                this.t.f();
                dji.midware.media.h.d.b(this.n);
                this.n = -1;
                if (this.E) {
                    h();
                    this.E = false;
                }
                if (this.r != null) {
                    this.r = null;
                }
                if (this.u != null) {
                    this.u.f();
                    this.u = null;
                }
                if (this.v != null) {
                    this.v.f();
                    this.v = null;
                }
                if (this.x != null) {
                    this.x.f();
                    this.x = null;
                }
                if (this.w != null) {
                    this.w.f();
                    this.w = null;
                }
                this.t.i();
                this.s.f();
                if (this.m != null) {
                    this.m.release();
                    this.m = null;
                }
                if (this.l != null) {
                    this.l.release();
                }
                if (this.F != null && this.F.isAlive()) {
                    if (VERSION.SDK_INT >= 18) {
                        this.F.quitSafely();
                    } else {
                        this.F.quit();
                    }
                }
                DJILogHelper.getInstance().LOGE(a, "OpenGL destoryed", false, true);
            }
        }
    }

    public void a(dji.midware.media.k.b.a aVar) {
        synchronized (this.z) {
            this.y = aVar;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onFrameAvailable(android.graphics.SurfaceTexture r11) {
        /*
        r10 = this;
        monitor-enter(r10);
        r0 = r10.f;	 Catch:{ all -> 0x0060 }
        if (r0 == 0) goto L_0x0013;
    L_0x0005:
        r0 = dji.midware.data.manager.P3.ServiceManager.getInstance();	 Catch:{ all -> 0x0060 }
        r0 = r0.e();	 Catch:{ all -> 0x0060 }
        if (r0 == 0) goto L_0x0013;
    L_0x000f:
        r1 = r10.D;	 Catch:{ all -> 0x0060 }
        if (r1 != 0) goto L_0x0015;
    L_0x0013:
        monitor-exit(r10);
        return;
    L_0x0015:
        r2 = r0.latestPTS;	 Catch:{ all -> 0x0060 }
        r1 = r0.outputWidth;	 Catch:{ all -> 0x0060 }
        r0 = r0.outputHeight;	 Catch:{ all -> 0x0060 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0063 }
        r6 = 0;
        r7 = "GLYUVSurface";
        r8 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0063 }
        r8.<init>();	 Catch:{ Exception -> 0x0063 }
        r9 = "before makeOnScreenCurrent(): ";
        r8 = r8.append(r9);	 Catch:{ Exception -> 0x0063 }
        r4 = r8.append(r4);	 Catch:{ Exception -> 0x0063 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0063 }
        dji.midware.media.e.c(r6, r7, r4);	 Catch:{ Exception -> 0x0063 }
        r4 = r10.s;	 Catch:{ Exception -> 0x0063 }
        r4.g();	 Catch:{ Exception -> 0x0063 }
        r4 = r10.t;	 Catch:{ Exception -> 0x0063 }
        r4.f();	 Catch:{ Exception -> 0x0063 }
        r10.e();	 Catch:{ Exception -> 0x0063 }
        r10.a(r2, r1, r0);	 Catch:{ Exception -> 0x0063 }
        r10.f();	 Catch:{ Exception -> 0x0063 }
        r0 = r10.t;	 Catch:{ Exception -> 0x0063 }
        r0.j();	 Catch:{ Exception -> 0x0063 }
        r0 = r10.t;	 Catch:{ Exception -> 0x005b }
        r0.e();	 Catch:{ Exception -> 0x005b }
        r0 = r10.s;	 Catch:{ Exception -> 0x005b }
        r0.f();	 Catch:{ Exception -> 0x005b }
        goto L_0x0013;
    L_0x005b:
        r0 = move-exception;
        dji.midware.media.e.a(r0);	 Catch:{ all -> 0x0060 }
        goto L_0x0013;
    L_0x0060:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x0063:
        r0 = move-exception;
        dji.midware.media.e.a(r0);	 Catch:{ all -> 0x0077 }
        r0 = r10.t;	 Catch:{ Exception -> 0x0072 }
        r0.e();	 Catch:{ Exception -> 0x0072 }
        r0 = r10.s;	 Catch:{ Exception -> 0x0072 }
        r0.f();	 Catch:{ Exception -> 0x0072 }
        goto L_0x0013;
    L_0x0072:
        r0 = move-exception;
        dji.midware.media.e.a(r0);	 Catch:{ all -> 0x0060 }
        goto L_0x0013;
    L_0x0077:
        r0 = move-exception;
        r1 = r10.t;	 Catch:{ Exception -> 0x0083 }
        r1.e();	 Catch:{ Exception -> 0x0083 }
        r1 = r10.s;	 Catch:{ Exception -> 0x0083 }
        r1.f();	 Catch:{ Exception -> 0x0083 }
    L_0x0082:
        throw r0;	 Catch:{ all -> 0x0060 }
    L_0x0083:
        r1 = move-exception;
        dji.midware.media.e.a(r1);	 Catch:{ all -> 0x0060 }
        goto L_0x0082;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.h.b.a.onFrameAvailable(android.graphics.SurfaceTexture):void");
    }

    private void g() {
        if (!this.E) {
            this.E = true;
            dji.midware.media.h.d.a a = dji.midware.media.h.d.a(1920, (int) e);
            this.p = a.a;
            this.q = a.b;
        }
        dji.midware.media.h.d.d(this.p);
    }

    private void h() {
        dji.midware.media.h.d.b(this.q);
        this.q = -1;
        dji.midware.media.h.d.c(this.p);
        this.p = -1;
    }

    private void a(long j, int i, int i2) {
        synchronized (this.z) {
            if (this.y != null && this.y.a(j)) {
                dji.midware.util.a.a.getInstance("GLYUVSurface.sendDataToListener").a(dji.midware.util.a.a.a, Integer.valueOf(1));
                e.a(a, String.format("-----Make an I-frame. pts=%3.3f index=%d frameNum=%d w=%d h=%d", new Object[]{Double.valueOf(((double) (dji.midware.media.d.a(j) % 1000000)) / 1000.0d), Integer.valueOf(dji.midware.media.d.b(j)), Integer.valueOf(dji.midware.media.d.c(j)), Integer.valueOf(i), Integer.valueOf(i2)}));
                try {
                    int b = dji.midware.media.h.d.b();
                    g();
                    c(i, i2);
                    dji.midware.media.k.b.b a = this.y.a();
                    long currentTimeMillis = System.currentTimeMillis();
                    if (a == null) {
                        Log.i(dji.midware.media.k.b.e.a, "can't get RawFrameContainer");
                    } else {
                        Log.i(dji.midware.media.k.b.e.a, "get RawFrameContainer success after1 (ms)=" + (System.currentTimeMillis() - currentTimeMillis));
                        ByteBuffer f = a.b().f();
                        Log.i(dji.midware.media.k.b.e.a, "get RawFrameContainer success after2 (ms)=" + (System.currentTimeMillis() - currentTimeMillis));
                        this.u.a(f, i, i2);
                        Log.i(dji.midware.media.k.b.e.a, "get RawFrameContainer success after3 (ms)=" + (System.currentTimeMillis() - currentTimeMillis));
                        a.b().a((i * i2) * 4);
                        a.b().e();
                        a.a(j);
                        a.a(i, i2);
                        this.y.a(a);
                        dji.midware.media.h.d.d(b);
                    }
                } catch (Exception e) {
                    e.a(a, e);
                }
            }
        }
    }

    public void a(boolean z, int i) {
        this.i = z;
        this.h = i;
    }

    public void a(boolean z, boolean z2) {
        this.j = z;
        this.k = z2;
    }

    public void a(float f) {
        if (this.v != null) {
            this.v.a(f);
        }
    }
}
