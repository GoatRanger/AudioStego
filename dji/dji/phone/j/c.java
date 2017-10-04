package dji.phone.j;

import android.content.Context;
import android.opengl.ETC1Util;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import dji.log.DJILogHelper;
import dji.midware.media.h.b.a;
import dji.midware.media.h.d;
import dji.phone.tracking.b;
import dji.pilot.fpv.R;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.LinkedList;
import java.util.List;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

public class c extends Thread {
    private static final int C = 4;
    private static final int I = 0;
    public static ByteBuffer f = ByteBuffer.allocateDirect((b.c * b.d) * 4);
    private static final String g = "DJILPRenderThread";
    private static float[] i = new float[]{-1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f};
    private static short[] j = new short[]{(short) 0, (short) 1, (short) 2, (short) 1, (short) 2, (short) 3};
    private static final int k = 3;
    private static final int l = 2;
    private EGLContext A;
    private EGLSurface B;
    private FloatBuffer D;
    private FloatBuffer E;
    private ShortBuffer F;
    private int G;
    private Context H;
    private Handler J;
    private HandlerThread K;
    private boolean L = false;
    int a;
    int b;
    int c = 1920;
    int d = a.e;
    long e = 0;
    private final int h;
    private boolean m;
    private boolean n;
    private boolean o = false;
    private boolean p = true;
    private boolean q = true;
    private f r = f.NORMAL;
    private int s;
    private int t = 0;
    private Object u;
    private b v;
    private List<a> w = new LinkedList();
    private EGL10 x;
    private EGLDisplay y;
    private EGLConfig z;

    public void a() {
        this.o = true;
    }

    public void b() {
        this.o = false;
        synchronized (this) {
            notify();
            f();
        }
    }

    private boolean k() {
        return this.u instanceof Surface;
    }

    private void a(String str) {
        if (k()) {
            Log.d(g, "livestreamLog: " + str);
        }
    }

    public c(Context context, Object obj, int i, b bVar) {
        this.H = context;
        this.u = obj;
        this.h = i;
        this.v = bVar;
        this.K = new HandlerThread("LP render thread data thread");
        this.K.start();
        this.J = new Handler(this, this.K.getLooper()) {
            final /* synthetic */ c a;

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        byte[] bArr = (byte[]) message.obj;
                        Log.d(c.g, "handleMessage: get data length " + bArr.length + ", listener num: " + this.a.w.size());
                        for (a a : this.a.w) {
                            a.a(this.a.h, bArr);
                        }
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public int c() {
        return this.h;
    }

    public void a(int i) {
        this.G = i;
    }

    public void a(boolean z) {
        this.p = z;
    }

    public void b(boolean z) {
        this.q = z;
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.w.add(aVar);
        }
    }

    public void b(a aVar) {
        if (aVar != null) {
            this.w.remove(aVar);
        }
    }

    public void d() {
        this.w.clear();
    }

    private int b(int i) {
        int glCreateShader = GLES20.glCreateShader(35633);
        int glCreateShader2 = GLES20.glCreateShader(35632);
        String b = e.getInstance().b(this.H, R.raw.vertex);
        String a = e.getInstance().a(this.H, this.G);
        GLES20.glShaderSource(glCreateShader, b);
        GLES20.glShaderSource(glCreateShader2, a);
        int[] iArr = new int[1];
        GLES20.glCompileShader(glCreateShader);
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] == 0) {
            throw new RuntimeException("vertex shader compile failed:" + GLES20.glGetShaderInfoLog(glCreateShader));
        }
        GLES20.glCompileShader(glCreateShader2);
        GLES20.glGetShaderiv(glCreateShader2, 35713, iArr, 0);
        if (iArr[0] == 0) {
            throw new RuntimeException("fragment shader compile failed:" + GLES20.glGetShaderInfoLog(glCreateShader2));
        }
        int glCreateProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(glCreateProgram, glCreateShader);
        GLES20.glAttachShader(glCreateProgram, glCreateShader2);
        GLES20.glLinkProgram(glCreateProgram);
        return glCreateProgram;
    }

    public void e() {
        this.D = ByteBuffer.allocateDirect(i.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.D.position(0);
        this.E = ByteBuffer.allocateDirect(g.a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.E.position(0);
        this.F = ByteBuffer.allocateDirect(j.length * 2).order(ByteOrder.nativeOrder()).asShortBuffer();
        this.F.put(j);
        this.F.position(0);
    }

    public int f() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        this.t = iArr[0];
        return this.t;
    }

    public int g() {
        return this.t;
    }

    private void l() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        this.t = iArr[0];
    }

    public void h() {
        GLES20.glUseProgram(this.s);
        int glGetAttribLocation = GLES20.glGetAttribLocation(this.s, "aPosition");
        int glGetAttribLocation2 = GLES20.glGetAttribLocation(this.s, "aTextureCoord");
        int glGetUniformLocation = GLES20.glGetUniformLocation(this.s, "sTexture");
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, this.t);
        GLES20.glTexParameteri(36197, 10241, 9729);
        GLES20.glTexParameteri(36197, 10240, 9729);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        GLES20.glEnableVertexAttribArray(glGetAttribLocation);
        this.D.position(0);
        GLES20.glVertexAttribPointer(glGetAttribLocation, 3, 5126, false, 12, this.D);
        this.E.position(0);
        GLES20.glEnableVertexAttribArray(glGetAttribLocation2);
        GLES20.glVertexAttribPointer(glGetAttribLocation2, 2, 5126, false, 8, this.E);
        GLES20.glUniform1i(glGetUniformLocation, 0);
        GLES20.glDrawElements(4, j.length, 5123, this.F);
        ETC1Util.isETC1Supported();
        GLES20.glDisableVertexAttribArray(glGetAttribLocation);
        GLES20.glDisableVertexAttribArray(glGetAttribLocation2);
    }

    private void m() {
        this.x = (EGL10) EGLContext.getEGL();
        this.y = this.x.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (EGL10.EGL_NO_DISPLAY == this.y) {
            throw new RuntimeException("eglGetDisplay failed:" + GLUtils.getEGLErrorString(this.x.eglGetError()));
        }
        if (this.x.eglInitialize(this.y, new int[2])) {
            int[] iArr;
            int[] iArr2 = new int[1];
            EGLConfig[] eGLConfigArr = new EGLConfig[1];
            if (this.q) {
                iArr = new int[]{12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12344};
            } else {
                iArr = new int[]{12339, 1, 12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12344};
            }
            this.x.eglChooseConfig(this.y, iArr, eGLConfigArr, 1, iArr2);
            if (iArr2[0] <= 0) {
                throw new RuntimeException("eglChooseConfig failed:" + GLUtils.getEGLErrorString(this.x.eglGetError()));
            }
            this.z = eGLConfigArr[0];
            this.A = this.x.eglCreateContext(this.y, this.z, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
            if (EGL10.EGL_NO_CONTEXT == this.A) {
                throw new RuntimeException("eglCreateContext failed: " + GLUtils.getEGLErrorString(this.x.eglGetError()));
            }
            if (this.q) {
                this.B = this.x.eglCreateWindowSurface(this.y, this.z, this.u, null);
                if (this.B == null || EGL10.EGL_NO_SURFACE == this.B) {
                    throw new RuntimeException("eglCreateWindowSurface failed" + GLUtils.getEGLErrorString(this.x.eglGetError()));
                }
            }
            this.B = this.x.eglCreatePbufferSurface(this.y, this.z, new int[]{12375, b.c, 12374, b.d, 12344});
            if (this.B == null || EGL10.EGL_NO_SURFACE == this.B) {
                throw new RuntimeException("eglCreateWindowSurface failed" + GLUtils.getEGLErrorString(this.x.eglGetError()));
            }
            if (this.x.eglMakeCurrent(this.y, this.B, this.B, this.A)) {
                GLES20.glPixelStorei(3317, 4);
                return;
            }
            throw new RuntimeException("eglMakeCurrent failed:" + GLUtils.getEGLErrorString(this.x.eglGetError()));
        }
        throw new RuntimeException("eglInitialize failed:" + GLUtils.getEGLErrorString(this.x.eglGetError()));
    }

    public void i() {
        this.D.clear();
        this.E.clear();
        this.F.clear();
        this.x.eglTerminate(this.y);
        GLES20.glDeleteProgram(this.s);
        GLES20.glDeleteTextures(1, new int[]{this.t}, 0);
        this.x.eglDestroySurface(this.y, this.B);
        this.x.eglDestroyContext(this.y, this.A);
        this.u = null;
        this.H = null;
    }

    public synchronized void run() {
        m();
        this.s = b(0);
        e();
        j();
        f();
        while (!this.L) {
            try {
                if (!this.o) {
                    synchronized (d.a) {
                        if (this.v != null) {
                            this.v.a(this.h, this.t);
                        }
                        GLES20.glViewport(0, 0, this.a, this.b);
                        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
                        GLES20.glClear(16384);
                        h();
                        if (this.v != null) {
                            this.v.a();
                        }
                    }
                    if (this.p) {
                        this.J.sendMessage(this.J.obtainMessage(0, n()));
                    }
                    if (this.q && !this.x.eglSwapBuffers(this.y, this.B)) {
                        DJILogHelper.getInstance().LOGE(g, "Cannot swap buffers", true, true);
                    }
                }
                wait();
            } catch (InterruptedException e) {
            }
        }
        DJILogHelper.getInstance().LOGD(g, "thread end", false, true);
        i();
    }

    public void interrupt() {
        this.L = true;
        super.interrupt();
    }

    public boolean isInterrupted() {
        return this.L;
    }

    private byte[] n() {
        f.order(ByteOrder.nativeOrder());
        f.position(0);
        GLES20.glPixelStorei(3317, 4);
        GLES20.glReadPixels(0, 0, this.a, this.b, d.c, 5121, f);
        return f.array();
    }

    public void a(int i, int i2) {
        this.a = i;
        this.b = i2;
        j();
        Log.d("", "set regionwidth:" + i + "height:" + i2);
    }

    public void a(boolean z, boolean z2) {
        this.m = z;
        this.n = z2;
    }

    public void j() {
        float f = (float) this.a;
        float f2 = (float) this.b;
        if (this.r == f.ROTATION_270 || this.r == f.ROTATION_90) {
            f = (float) this.b;
            f2 = (float) this.a;
        }
        float max = Math.max(f / ((float) this.c), f2 / ((float) this.d));
        f = ((float) Math.round(((float) this.c) * max)) / f;
        f2 = ((float) Math.round(max * ((float) this.d))) / f2;
        float[] fArr = i;
        float[] a = g.a(this.r, this.m, this.n);
        f = (1.0f - (1.0f / f)) / 2.0f;
        f2 = (1.0f - (1.0f / f2)) / 2.0f;
        float[] fArr2 = new float[]{a(a[0], f), a(a[1], f2), a(a[2], f), a(a[3], f2), a(a[4], f), a(a[5], f2), a(a[6], f), a(a[7], f2)};
        if (this.D != null && this.E != null) {
            this.D.clear();
            this.D.put(fArr).position(0);
            this.E.clear();
            this.E.put(fArr2).position(0);
        }
    }

    private float a(float f, float f2) {
        return f == 0.0f ? f2 : 1.0f - f2;
    }
}
