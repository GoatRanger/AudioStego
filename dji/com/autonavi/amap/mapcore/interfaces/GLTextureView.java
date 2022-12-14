package com.autonavi.amap.mapcore.interfaces;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLDebugHelper;
import android.opengl.GLSurfaceView.Renderer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL10;

@SuppressLint({"NewApi"})
public class GLTextureView extends TextureView implements SurfaceTextureListener {
    public static final int DEBUG_CHECK_GL_ERROR = 1;
    public static final int DEBUG_LOG_GL_CALLS = 2;
    private static final boolean LOG_ATTACH_DETACH = false;
    private static final boolean LOG_EGL = false;
    private static final boolean LOG_PAUSE_RESUME = false;
    private static final boolean LOG_RENDERER = false;
    private static final boolean LOG_RENDERER_DRAW_FRAME = false;
    private static final boolean LOG_SURFACE = false;
    private static final boolean LOG_THREADS = false;
    public static final int RENDERMODE_CONTINUOUSLY = 1;
    public static final int RENDERMODE_WHEN_DIRTY = 0;
    private static final String TAG = "GLSurfaceView";
    private static final g sGLThreadManager = new g();
    private int mDebugFlags;
    private boolean mDetached;
    private EGLConfigChooser mEGLConfigChooser;
    private int mEGLContextClientVersion;
    private EGLContextFactory mEGLContextFactory;
    private EGLWindowSurfaceFactory mEGLWindowSurfaceFactory;
    private f mGLThread;
    private GLWrapper mGLWrapper;
    private boolean mPreserveEGLContextOnPause;
    private Renderer mRenderer;
    private final WeakReference<GLTextureView> mThisWeakRef = new WeakReference(this);

    public interface EGLConfigChooser {
        EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay);
    }

    public interface EGLContextFactory {
        EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig);

        void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext);
    }

    public interface EGLWindowSurfaceFactory {
        EGLSurface createWindowSurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj);

        void destroySurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface);
    }

    public interface GLWrapper {
        GL wrap(GL gl);
    }

    private abstract class a implements EGLConfigChooser {
        protected int[] a;
        final /* synthetic */ GLTextureView b;

        abstract EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr);

        public a(GLTextureView gLTextureView, int[] iArr) {
            this.b = gLTextureView;
            this.a = a(iArr);
        }

        public EGLConfig chooseConfig(EGL10 egl10, EGLDisplay eGLDisplay) {
            int[] iArr = new int[1];
            if (egl10.eglChooseConfig(eGLDisplay, this.a, null, 0, iArr)) {
                int i = iArr[0];
                if (i <= 0) {
                    throw new IllegalArgumentException("No configs match configSpec");
                }
                EGLConfig[] eGLConfigArr = new EGLConfig[i];
                if (egl10.eglChooseConfig(eGLDisplay, this.a, eGLConfigArr, i, iArr)) {
                    EGLConfig a = a(egl10, eGLDisplay, eGLConfigArr);
                    if (a != null) {
                        return a;
                    }
                    throw new IllegalArgumentException("No config chosen");
                }
                throw new IllegalArgumentException("eglChooseConfig#2 failed");
            }
            throw new IllegalArgumentException("eglChooseConfig failed");
        }

        private int[] a(int[] iArr) {
            if (this.b.mEGLContextClientVersion != 2 && this.b.mEGLContextClientVersion != 3) {
                return iArr;
            }
            int length = iArr.length;
            Object obj = new int[(length + 2)];
            System.arraycopy(iArr, 0, obj, 0, length - 1);
            obj[length - 1] = 12352;
            if (this.b.mEGLContextClientVersion == 2) {
                obj[length] = 4;
            } else {
                obj[length] = 64;
            }
            obj[length + 1] = 12344;
            return obj;
        }
    }

    private class b extends a {
        protected int c;
        protected int d;
        protected int e;
        protected int f;
        protected int g;
        protected int h;
        final /* synthetic */ GLTextureView i;
        private int[] j = new int[1];

        public b(GLTextureView gLTextureView, int i, int i2, int i3, int i4, int i5, int i6) {
            this.i = gLTextureView;
            super(gLTextureView, new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344});
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
            this.g = i5;
            this.h = i6;
        }

        public EGLConfig a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig[] eGLConfigArr) {
            for (EGLConfig eGLConfig : eGLConfigArr) {
                int a = a(egl10, eGLDisplay, eGLConfig, 12325, 0);
                int a2 = a(egl10, eGLDisplay, eGLConfig, 12326, 0);
                if (a >= this.g && a2 >= this.h) {
                    a = a(egl10, eGLDisplay, eGLConfig, 12324, 0);
                    int a3 = a(egl10, eGLDisplay, eGLConfig, 12323, 0);
                    int a4 = a(egl10, eGLDisplay, eGLConfig, 12322, 0);
                    a2 = a(egl10, eGLDisplay, eGLConfig, 12321, 0);
                    if (a == this.c && a3 == this.d && a4 == this.e && a2 == this.f) {
                        return eGLConfig;
                    }
                }
            }
            return null;
        }

        private int a(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
            if (egl10.eglGetConfigAttrib(eGLDisplay, eGLConfig, i, this.j)) {
                return this.j[0];
            }
            return i2;
        }
    }

    private class c implements EGLContextFactory {
        final /* synthetic */ GLTextureView a;
        private int b;

        private c(GLTextureView gLTextureView) {
            this.a = gLTextureView;
            this.b = 12440;
        }

        public EGLContext createContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig) {
            int[] iArr = new int[]{this.b, this.a.mEGLContextClientVersion, 12344};
            EGLContext eGLContext = EGL10.EGL_NO_CONTEXT;
            if (this.a.mEGLContextClientVersion == 0) {
                iArr = null;
            }
            return egl10.eglCreateContext(eGLDisplay, eGLConfig, eGLContext, iArr);
        }

        public void destroyContext(EGL10 egl10, EGLDisplay eGLDisplay, EGLContext eGLContext) {
            if (!egl10.eglDestroyContext(eGLDisplay, eGLContext)) {
                Log.e("DefaultContextFactory", "display:" + eGLDisplay + " context: " + eGLContext);
                e.a("eglDestroyContex", egl10.eglGetError());
            }
        }
    }

    private static class d implements EGLWindowSurfaceFactory {
        private d() {
        }

        public EGLSurface createWindowSurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLConfig eGLConfig, Object obj) {
            EGLSurface eGLSurface = null;
            try {
                eGLSurface = egl10.eglCreateWindowSurface(eGLDisplay, eGLConfig, obj, null);
            } catch (Throwable e) {
                Log.e(GLTextureView.TAG, "eglCreateWindowSurface", e);
            }
            return eGLSurface;
        }

        public void destroySurface(EGL10 egl10, EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
            egl10.eglDestroySurface(eGLDisplay, eGLSurface);
        }
    }

    private static class e {
        EGL10 a;
        EGLDisplay b;
        EGLSurface c;
        EGLConfig d;
        EGLContext e;
        private WeakReference<GLTextureView> f;

        public e(WeakReference<GLTextureView> weakReference) {
            this.f = weakReference;
        }

        public void a() {
            this.a = (EGL10) EGLContext.getEGL();
            this.b = this.a.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (this.b == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed");
            }
            if (this.a.eglInitialize(this.b, new int[2])) {
                GLTextureView gLTextureView = (GLTextureView) this.f.get();
                if (gLTextureView == null) {
                    this.d = null;
                    this.e = null;
                } else {
                    this.d = gLTextureView.mEGLConfigChooser.chooseConfig(this.a, this.b);
                    this.e = gLTextureView.mEGLContextFactory.createContext(this.a, this.b, this.d);
                }
                if (this.e == null || this.e == EGL10.EGL_NO_CONTEXT) {
                    this.e = null;
                    a("createContext");
                }
                this.c = null;
                return;
            }
            throw new RuntimeException("eglInitialize failed");
        }

        public boolean b() {
            if (this.a == null) {
                throw new RuntimeException("egl not initialized");
            } else if (this.b == null) {
                throw new RuntimeException("eglDisplay not initialized");
            } else if (this.d == null) {
                throw new RuntimeException("mEglConfig not initialized");
            } else {
                g();
                GLTextureView gLTextureView = (GLTextureView) this.f.get();
                if (gLTextureView != null) {
                    this.c = gLTextureView.mEGLWindowSurfaceFactory.createWindowSurface(this.a, this.b, this.d, gLTextureView.getSurfaceTexture());
                } else {
                    this.c = null;
                }
                if (this.c == null || this.c == EGL10.EGL_NO_SURFACE) {
                    if (this.a.eglGetError() == 12299) {
                        Log.e("EglHelper", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.");
                    }
                    return false;
                } else if (this.a.eglMakeCurrent(this.b, this.c, this.c, this.e)) {
                    return true;
                } else {
                    a("EGLHelper", "eglMakeCurrent", this.a.eglGetError());
                    return false;
                }
            }
        }

        GL c() {
            GL gl = this.e.getGL();
            GLTextureView gLTextureView = (GLTextureView) this.f.get();
            if (gLTextureView == null) {
                return gl;
            }
            if (gLTextureView.mGLWrapper != null) {
                gl = gLTextureView.mGLWrapper.wrap(gl);
            }
            if ((gLTextureView.mDebugFlags & 3) == 0) {
                return gl;
            }
            Writer hVar;
            int i = 0;
            if ((gLTextureView.mDebugFlags & 1) != 0) {
                i = 1;
            }
            if ((gLTextureView.mDebugFlags & 2) != 0) {
                hVar = new h();
            } else {
                hVar = null;
            }
            return GLDebugHelper.wrap(gl, i, hVar);
        }

        public int d() {
            if (this.a.eglSwapBuffers(this.b, this.c)) {
                return dji.pilot.usercenter.protocol.e.ap;
            }
            return this.a.eglGetError();
        }

        public void e() {
            g();
        }

        private void g() {
            if (this.c != null && this.c != EGL10.EGL_NO_SURFACE) {
                this.a.eglMakeCurrent(this.b, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                GLTextureView gLTextureView = (GLTextureView) this.f.get();
                if (gLTextureView != null) {
                    gLTextureView.mEGLWindowSurfaceFactory.destroySurface(this.a, this.b, this.c);
                }
                this.c = null;
            }
        }

        public void f() {
            if (this.e != null) {
                GLTextureView gLTextureView = (GLTextureView) this.f.get();
                if (gLTextureView != null) {
                    gLTextureView.mEGLContextFactory.destroyContext(this.a, this.b, this.e);
                }
                this.e = null;
            }
            if (this.b != null) {
                this.a.eglTerminate(this.b);
                this.b = null;
            }
        }

        private void a(String str) {
            a(str, this.a.eglGetError());
        }

        public static void a(String str, int i) {
            throw new RuntimeException(b(str, i));
        }

        public static void a(String str, String str2, int i) {
            Log.w(str, b(str2, i));
        }

        public static String b(String str, int i) {
            return str + " failed: " + i;
        }
    }

    static class f extends Thread {
        private boolean a;
        private boolean b;
        private boolean c;
        private boolean d;
        private boolean e;
        private boolean f;
        private boolean g;
        private boolean h;
        private boolean i;
        private boolean j;
        private boolean k;
        private int l = 0;
        private int m = 0;
        private int n = 1;
        private boolean o = true;
        private boolean p;
        private ArrayList<Runnable> q = new ArrayList();
        private boolean r = true;
        private e s;
        private WeakReference<GLTextureView> t;

        f(WeakReference<GLTextureView> weakReference) {
            this.t = weakReference;
        }

        public void run() {
            setName("GLThread " + getId());
            try {
                l();
            } catch (InterruptedException e) {
            } finally {
                GLTextureView.sGLThreadManager.a(this);
            }
        }

        private void j() {
            if (this.i) {
                this.i = false;
                this.s.e();
            }
        }

        private void k() {
            if (this.h) {
                this.s.f();
                this.h = false;
                GLTextureView.sGLThreadManager.c(this);
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void l() throws java.lang.InterruptedException {
            /*
            r18 = this;
            r1 = new com.autonavi.amap.mapcore.interfaces.GLTextureView$e;
            r0 = r18;
            r2 = r0.t;
            r1.<init>(r2);
            r0 = r18;
            r0.s = r1;
            r1 = 0;
            r0 = r18;
            r0.h = r1;
            r1 = 0;
            r0 = r18;
            r0.i = r1;
            r3 = 0;
            r12 = 0;
            r1 = 0;
            r11 = 0;
            r10 = 0;
            r9 = 0;
            r8 = 0;
            r2 = 0;
            r7 = 0;
            r6 = 0;
            r5 = 0;
            r4 = 0;
            r14 = r3;
            r3 = r5;
            r5 = r7;
            r7 = r8;
            r8 = r9;
            r9 = r10;
            r10 = r11;
            r11 = r1;
            r17 = r2;
            r2 = r4;
            r4 = r6;
            r6 = r17;
        L_0x0031:
            r15 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d5 }
            monitor-enter(r15);	 Catch:{ all -> 0x01d5 }
        L_0x0036:
            r0 = r18;
            r1 = r0.a;	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x004d;
        L_0x003c:
            monitor-exit(r15);	 Catch:{ all -> 0x01d2 }
            r2 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;
            monitor-enter(r2);
            r18.j();	 Catch:{ all -> 0x004a }
            r18.k();	 Catch:{ all -> 0x004a }
            monitor-exit(r2);	 Catch:{ all -> 0x004a }
            return;
        L_0x004a:
            r1 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x004a }
            throw r1;
        L_0x004d:
            r0 = r18;
            r1 = r0.q;	 Catch:{ all -> 0x01d2 }
            r1 = r1.isEmpty();	 Catch:{ all -> 0x01d2 }
            if (r1 != 0) goto L_0x0081;
        L_0x0057:
            r0 = r18;
            r1 = r0.q;	 Catch:{ all -> 0x01d2 }
            r2 = 0;
            r1 = r1.remove(r2);	 Catch:{ all -> 0x01d2 }
            r1 = (java.lang.Runnable) r1;	 Catch:{ all -> 0x01d2 }
            r2 = r6;
            r6 = r4;
            r4 = r1;
            r1 = r11;
            r11 = r10;
            r10 = r9;
            r9 = r8;
            r8 = r7;
            r7 = r5;
            r5 = r3;
        L_0x006c:
            monitor-exit(r15);	 Catch:{ all -> 0x01d2 }
            if (r4 == 0) goto L_0x01f9;
        L_0x006f:
            r4.run();	 Catch:{ all -> 0x01d5 }
            r4 = 0;
            r3 = r5;
            r5 = r7;
            r7 = r8;
            r8 = r9;
            r9 = r10;
            r10 = r11;
            r11 = r1;
            r17 = r2;
            r2 = r4;
            r4 = r6;
            r6 = r17;
            goto L_0x0031;
        L_0x0081:
            r1 = 0;
            r0 = r18;
            r13 = r0.d;	 Catch:{ all -> 0x01d2 }
            r0 = r18;
            r0 = r0.c;	 Catch:{ all -> 0x01d2 }
            r16 = r0;
            r0 = r16;
            if (r13 == r0) goto L_0x02f2;
        L_0x0090:
            r0 = r18;
            r1 = r0.c;	 Catch:{ all -> 0x01d2 }
            r0 = r18;
            r13 = r0.c;	 Catch:{ all -> 0x01d2 }
            r0 = r18;
            r0.d = r13;	 Catch:{ all -> 0x01d2 }
            r13 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r13.notifyAll();	 Catch:{ all -> 0x01d2 }
            r13 = r1;
        L_0x00a4:
            r0 = r18;
            r1 = r0.k;	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x00b6;
        L_0x00aa:
            r18.j();	 Catch:{ all -> 0x01d2 }
            r18.k();	 Catch:{ all -> 0x01d2 }
            r1 = 0;
            r0 = r18;
            r0.k = r1;	 Catch:{ all -> 0x01d2 }
            r5 = 1;
        L_0x00b6:
            if (r9 == 0) goto L_0x00bf;
        L_0x00b8:
            r18.j();	 Catch:{ all -> 0x01d2 }
            r18.k();	 Catch:{ all -> 0x01d2 }
            r9 = 0;
        L_0x00bf:
            if (r13 == 0) goto L_0x00ca;
        L_0x00c1:
            r0 = r18;
            r1 = r0.i;	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x00ca;
        L_0x00c7:
            r18.j();	 Catch:{ all -> 0x01d2 }
        L_0x00ca:
            if (r13 == 0) goto L_0x00ee;
        L_0x00cc:
            r0 = r18;
            r1 = r0.h;	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x00ee;
        L_0x00d2:
            r0 = r18;
            r1 = r0.t;	 Catch:{ all -> 0x01d2 }
            r1 = r1.get();	 Catch:{ all -> 0x01d2 }
            r1 = (com.autonavi.amap.mapcore.interfaces.GLTextureView) r1;	 Catch:{ all -> 0x01d2 }
            if (r1 != 0) goto L_0x01ab;
        L_0x00de:
            r1 = 0;
        L_0x00df:
            if (r1 == 0) goto L_0x00eb;
        L_0x00e1:
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r1 = r1.a();	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x00ee;
        L_0x00eb:
            r18.k();	 Catch:{ all -> 0x01d2 }
        L_0x00ee:
            if (r13 == 0) goto L_0x0101;
        L_0x00f0:
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r1 = r1.b();	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x0101;
        L_0x00fa:
            r0 = r18;
            r1 = r0.s;	 Catch:{ all -> 0x01d2 }
            r1.f();	 Catch:{ all -> 0x01d2 }
        L_0x0101:
            r0 = r18;
            r1 = r0.e;	 Catch:{ all -> 0x01d2 }
            if (r1 != 0) goto L_0x0127;
        L_0x0107:
            r0 = r18;
            r1 = r0.g;	 Catch:{ all -> 0x01d2 }
            if (r1 != 0) goto L_0x0127;
        L_0x010d:
            r0 = r18;
            r1 = r0.i;	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x0116;
        L_0x0113:
            r18.j();	 Catch:{ all -> 0x01d2 }
        L_0x0116:
            r1 = 1;
            r0 = r18;
            r0.g = r1;	 Catch:{ all -> 0x01d2 }
            r1 = 0;
            r0 = r18;
            r0.f = r1;	 Catch:{ all -> 0x01d2 }
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r1.notifyAll();	 Catch:{ all -> 0x01d2 }
        L_0x0127:
            r0 = r18;
            r1 = r0.e;	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x013f;
        L_0x012d:
            r0 = r18;
            r1 = r0.g;	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x013f;
        L_0x0133:
            r1 = 0;
            r0 = r18;
            r0.g = r1;	 Catch:{ all -> 0x01d2 }
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r1.notifyAll();	 Catch:{ all -> 0x01d2 }
        L_0x013f:
            if (r6 == 0) goto L_0x014f;
        L_0x0141:
            r7 = 0;
            r6 = 0;
            r1 = 1;
            r0 = r18;
            r0.p = r1;	 Catch:{ all -> 0x01d2 }
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r1.notifyAll();	 Catch:{ all -> 0x01d2 }
        L_0x014f:
            r1 = r18.m();	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x01f0;
        L_0x0155:
            r0 = r18;
            r1 = r0.h;	 Catch:{ all -> 0x01d2 }
            if (r1 != 0) goto L_0x015e;
        L_0x015b:
            if (r5 == 0) goto L_0x01b1;
        L_0x015d:
            r5 = 0;
        L_0x015e:
            r0 = r18;
            r1 = r0.h;	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x02ee;
        L_0x0164:
            r0 = r18;
            r1 = r0.i;	 Catch:{ all -> 0x01d2 }
            if (r1 != 0) goto L_0x02ee;
        L_0x016a:
            r1 = 1;
            r0 = r18;
            r0.i = r1;	 Catch:{ all -> 0x01d2 }
            r11 = 1;
            r10 = 1;
            r8 = 1;
            r1 = r8;
            r8 = r10;
        L_0x0174:
            r0 = r18;
            r10 = r0.i;	 Catch:{ all -> 0x01d2 }
            if (r10 == 0) goto L_0x01ee;
        L_0x017a:
            r0 = r18;
            r10 = r0.r;	 Catch:{ all -> 0x01d2 }
            if (r10 == 0) goto L_0x02e4;
        L_0x0180:
            r7 = 1;
            r0 = r18;
            r3 = r0.l;	 Catch:{ all -> 0x01d2 }
            r0 = r18;
            r1 = r0.m;	 Catch:{ all -> 0x01d2 }
            r4 = 1;
            r10 = 1;
            r11 = 0;
            r0 = r18;
            r0.r = r11;	 Catch:{ all -> 0x01d2 }
        L_0x0190:
            r11 = 0;
            r0 = r18;
            r0.o = r11;	 Catch:{ all -> 0x01d2 }
            r11 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r11.notifyAll();	 Catch:{ all -> 0x01d2 }
            r11 = r8;
            r8 = r4;
            r4 = r2;
            r2 = r6;
            r6 = r3;
            r17 = r1;
            r1 = r10;
            r10 = r9;
            r9 = r7;
            r7 = r5;
            r5 = r17;
            goto L_0x006c;
        L_0x01ab:
            r1 = r1.mPreserveEGLContextOnPause;	 Catch:{ all -> 0x01d2 }
            goto L_0x00df;
        L_0x01b1:
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r0 = r18;
            r1 = r1.b(r0);	 Catch:{ all -> 0x01d2 }
            if (r1 == 0) goto L_0x015e;
        L_0x01bd:
            r0 = r18;
            r1 = r0.s;	 Catch:{ RuntimeException -> 0x01e3 }
            r1.a();	 Catch:{ RuntimeException -> 0x01e3 }
            r1 = 1;
            r0 = r18;
            r0.h = r1;	 Catch:{ all -> 0x01d2 }
            r12 = 1;
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r1.notifyAll();	 Catch:{ all -> 0x01d2 }
            goto L_0x015e;
        L_0x01d2:
            r1 = move-exception;
            monitor-exit(r15);	 Catch:{ all -> 0x01d2 }
            throw r1;	 Catch:{ all -> 0x01d5 }
        L_0x01d5:
            r1 = move-exception;
            r2 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;
            monitor-enter(r2);
            r18.j();	 Catch:{ all -> 0x02db }
            r18.k();	 Catch:{ all -> 0x02db }
            monitor-exit(r2);	 Catch:{ all -> 0x02db }
            throw r1;
        L_0x01e3:
            r1 = move-exception;
            r2 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r0 = r18;
            r2.c(r0);	 Catch:{ all -> 0x01d2 }
            throw r1;	 Catch:{ all -> 0x01d2 }
        L_0x01ee:
            r10 = r8;
            r8 = r1;
        L_0x01f0:
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d2 }
            r1.wait();	 Catch:{ all -> 0x01d2 }
            goto L_0x0036;
        L_0x01f9:
            if (r1 == 0) goto L_0x02e1;
        L_0x01fb:
            r0 = r18;
            r3 = r0.s;	 Catch:{ all -> 0x01d5 }
            r3 = r3.b();	 Catch:{ all -> 0x01d5 }
            if (r3 == 0) goto L_0x02ad;
        L_0x0205:
            r3 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d5 }
            monitor-enter(r3);	 Catch:{ all -> 0x01d5 }
            r1 = 1;
            r0 = r18;
            r0.j = r1;	 Catch:{ all -> 0x02aa }
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x02aa }
            r1.notifyAll();	 Catch:{ all -> 0x02aa }
            monitor-exit(r3);	 Catch:{ all -> 0x02aa }
            r1 = 0;
            r3 = r1;
        L_0x0219:
            if (r11 == 0) goto L_0x02de;
        L_0x021b:
            r0 = r18;
            r1 = r0.s;	 Catch:{ all -> 0x01d5 }
            r1 = r1.c();	 Catch:{ all -> 0x01d5 }
            r1 = (javax.microedition.khronos.opengles.GL10) r1;	 Catch:{ all -> 0x01d5 }
            r11 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d5 }
            r11.a(r1);	 Catch:{ all -> 0x01d5 }
            r11 = 0;
            r13 = r1;
        L_0x022e:
            if (r12 == 0) goto L_0x024a;
        L_0x0230:
            r0 = r18;
            r1 = r0.t;	 Catch:{ all -> 0x01d5 }
            r1 = r1.get();	 Catch:{ all -> 0x01d5 }
            r1 = (com.autonavi.amap.mapcore.interfaces.GLTextureView) r1;	 Catch:{ all -> 0x01d5 }
            if (r1 == 0) goto L_0x0249;
        L_0x023c:
            r1 = r1.mRenderer;	 Catch:{ all -> 0x01d5 }
            r0 = r18;
            r12 = r0.s;	 Catch:{ all -> 0x01d5 }
            r12 = r12.d;	 Catch:{ all -> 0x01d5 }
            r1.onSurfaceCreated(r13, r12);	 Catch:{ all -> 0x01d5 }
        L_0x0249:
            r12 = 0;
        L_0x024a:
            if (r9 == 0) goto L_0x0260;
        L_0x024c:
            r0 = r18;
            r1 = r0.t;	 Catch:{ all -> 0x01d5 }
            r1 = r1.get();	 Catch:{ all -> 0x01d5 }
            r1 = (com.autonavi.amap.mapcore.interfaces.GLTextureView) r1;	 Catch:{ all -> 0x01d5 }
            if (r1 == 0) goto L_0x025f;
        L_0x0258:
            r1 = r1.mRenderer;	 Catch:{ all -> 0x01d5 }
            r1.onSurfaceChanged(r13, r6, r5);	 Catch:{ all -> 0x01d5 }
        L_0x025f:
            r9 = 0;
        L_0x0260:
            r0 = r18;
            r1 = r0.t;	 Catch:{ all -> 0x01d5 }
            r1 = r1.get();	 Catch:{ all -> 0x01d5 }
            r1 = (com.autonavi.amap.mapcore.interfaces.GLTextureView) r1;	 Catch:{ all -> 0x01d5 }
            if (r1 == 0) goto L_0x0273;
        L_0x026c:
            r1 = r1.mRenderer;	 Catch:{ all -> 0x01d5 }
            r1.onDrawFrame(r13);	 Catch:{ all -> 0x01d5 }
        L_0x0273:
            r0 = r18;
            r1 = r0.s;	 Catch:{ all -> 0x01d5 }
            r1 = r1.d();	 Catch:{ all -> 0x01d5 }
            switch(r1) {
                case 12288: goto L_0x0297;
                case 12302: goto L_0x02d6;
                default: goto L_0x027e;
            };	 Catch:{ all -> 0x01d5 }
        L_0x027e:
            r14 = "GLThread";
            r15 = "eglSwapBuffers";
            com.autonavi.amap.mapcore.interfaces.GLTextureView.e.a(r14, r15, r1);	 Catch:{ all -> 0x01d5 }
            r14 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d5 }
            monitor-enter(r14);	 Catch:{ all -> 0x01d5 }
            r1 = 1;
            r0 = r18;
            r0.f = r1;	 Catch:{ all -> 0x02d8 }
            r1 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x02d8 }
            r1.notifyAll();	 Catch:{ all -> 0x02d8 }
            monitor-exit(r14);	 Catch:{ all -> 0x02d8 }
        L_0x0297:
            if (r8 == 0) goto L_0x02f5;
        L_0x0299:
            r1 = 1;
        L_0x029a:
            r2 = r4;
            r14 = r13;
            r4 = r6;
            r6 = r1;
            r17 = r7;
            r7 = r8;
            r8 = r9;
            r9 = r10;
            r10 = r11;
            r11 = r3;
            r3 = r5;
            r5 = r17;
            goto L_0x0031;
        L_0x02aa:
            r1 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x02aa }
            throw r1;	 Catch:{ all -> 0x01d5 }
        L_0x02ad:
            r3 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x01d5 }
            monitor-enter(r3);	 Catch:{ all -> 0x01d5 }
            r13 = 1;
            r0 = r18;
            r0.j = r13;	 Catch:{ all -> 0x02d3 }
            r13 = 1;
            r0 = r18;
            r0.f = r13;	 Catch:{ all -> 0x02d3 }
            r13 = com.autonavi.amap.mapcore.interfaces.GLTextureView.sGLThreadManager;	 Catch:{ all -> 0x02d3 }
            r13.notifyAll();	 Catch:{ all -> 0x02d3 }
            monitor-exit(r3);	 Catch:{ all -> 0x02d3 }
            r3 = r5;
            r5 = r7;
            r7 = r8;
            r8 = r9;
            r9 = r10;
            r10 = r11;
            r11 = r1;
            r17 = r2;
            r2 = r4;
            r4 = r6;
            r6 = r17;
            goto L_0x0031;
        L_0x02d3:
            r1 = move-exception;
            monitor-exit(r3);	 Catch:{ all -> 0x02d3 }
            throw r1;	 Catch:{ all -> 0x01d5 }
        L_0x02d6:
            r10 = 1;
            goto L_0x0297;
        L_0x02d8:
            r1 = move-exception;
            monitor-exit(r14);	 Catch:{ all -> 0x02d8 }
            throw r1;	 Catch:{ all -> 0x01d5 }
        L_0x02db:
            r1 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x02db }
            throw r1;
        L_0x02de:
            r13 = r14;
            goto L_0x022e;
        L_0x02e1:
            r3 = r1;
            goto L_0x0219;
        L_0x02e4:
            r10 = r11;
            r17 = r4;
            r4 = r7;
            r7 = r1;
            r1 = r3;
            r3 = r17;
            goto L_0x0190;
        L_0x02ee:
            r1 = r8;
            r8 = r10;
            goto L_0x0174;
        L_0x02f2:
            r13 = r1;
            goto L_0x00a4;
        L_0x02f5:
            r1 = r2;
            goto L_0x029a;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.autonavi.amap.mapcore.interfaces.GLTextureView.f.l():void");
        }

        public boolean a() {
            return this.h && this.i && m();
        }

        private boolean m() {
            return !this.d && this.e && !this.f && this.l > 0 && this.m > 0 && (this.o || this.n == 1);
        }

        public void a(int i) {
            if (i < 0 || i > 1) {
                throw new IllegalArgumentException("renderMode");
            }
            synchronized (GLTextureView.sGLThreadManager) {
                this.n = i;
                GLTextureView.sGLThreadManager.notifyAll();
            }
        }

        public int b() {
            int i;
            synchronized (GLTextureView.sGLThreadManager) {
                i = this.n;
            }
            return i;
        }

        public void c() {
            synchronized (GLTextureView.sGLThreadManager) {
                this.o = true;
                GLTextureView.sGLThreadManager.notifyAll();
            }
        }

        public void d() {
            synchronized (GLTextureView.sGLThreadManager) {
                this.e = true;
                this.j = false;
                GLTextureView.sGLThreadManager.notifyAll();
                while (this.g && !this.j && !this.b) {
                    try {
                        GLTextureView.sGLThreadManager.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void e() {
            synchronized (GLTextureView.sGLThreadManager) {
                this.e = false;
                GLTextureView.sGLThreadManager.notifyAll();
                while (!this.g && !this.b) {
                    try {
                        GLTextureView.sGLThreadManager.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void f() {
            synchronized (GLTextureView.sGLThreadManager) {
                this.c = true;
                GLTextureView.sGLThreadManager.notifyAll();
                while (!this.b && !this.d) {
                    try {
                        GLTextureView.sGLThreadManager.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void g() {
            synchronized (GLTextureView.sGLThreadManager) {
                this.c = false;
                this.o = true;
                this.p = false;
                GLTextureView.sGLThreadManager.notifyAll();
                while (!this.b && this.d && !this.p) {
                    try {
                        GLTextureView.sGLThreadManager.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void a(int i, int i2) {
            synchronized (GLTextureView.sGLThreadManager) {
                this.l = i;
                this.m = i2;
                this.r = true;
                this.o = true;
                this.p = false;
                GLTextureView.sGLThreadManager.notifyAll();
                while (!this.b && !this.d && !this.p && a()) {
                    try {
                        GLTextureView.sGLThreadManager.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void h() {
            synchronized (GLTextureView.sGLThreadManager) {
                this.a = true;
                GLTextureView.sGLThreadManager.notifyAll();
                while (!this.b) {
                    try {
                        GLTextureView.sGLThreadManager.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        public void i() {
            this.k = true;
            GLTextureView.sGLThreadManager.notifyAll();
        }

        public void a(Runnable runnable) {
            if (runnable == null) {
                throw new IllegalArgumentException("r must not be null");
            }
            synchronized (GLTextureView.sGLThreadManager) {
                this.q.add(runnable);
                GLTextureView.sGLThreadManager.notifyAll();
            }
        }
    }

    private static class g {
        private static String a = "GLThreadManager";
        private boolean b;
        private int c;
        private boolean d;
        private boolean e;
        private boolean f;
        private f g;

        private g() {
        }

        public synchronized void a(f fVar) {
            fVar.b = true;
            if (this.g == fVar) {
                this.g = null;
            }
            notifyAll();
        }

        public boolean b(f fVar) {
            if (this.g == fVar || this.g == null) {
                this.g = fVar;
                notifyAll();
                return true;
            }
            c();
            if (this.e) {
                return true;
            }
            if (this.g != null) {
                this.g.i();
            }
            return false;
        }

        public void c(f fVar) {
            if (this.g == fVar) {
                this.g = null;
            }
            notifyAll();
        }

        public synchronized boolean a() {
            return this.f;
        }

        public synchronized boolean b() {
            c();
            return !this.e;
        }

        public synchronized void a(GL10 gl10) {
            boolean z = true;
            synchronized (this) {
                if (!this.d) {
                    c();
                    String glGetString = gl10.glGetString(7937);
                    if (this.c < 131072) {
                        this.e = !glGetString.startsWith("Q3Dimension MSM7500 ");
                        notifyAll();
                    }
                    if (this.e) {
                        z = false;
                    }
                    this.f = z;
                    this.d = true;
                }
            }
        }

        private void c() {
            if (!this.b) {
                this.c = 131072;
                if (this.c >= 131072) {
                    this.e = true;
                }
                this.b = true;
            }
        }
    }

    static class h extends Writer {
        private StringBuilder a = new StringBuilder();

        h() {
        }

        public void close() {
            a();
        }

        public void flush() {
            a();
        }

        public void write(char[] cArr, int i, int i2) {
            for (int i3 = 0; i3 < i2; i3++) {
                char c = cArr[i + i3];
                if (c == '\n') {
                    a();
                } else {
                    this.a.append(c);
                }
            }
        }

        private void a() {
            if (this.a.length() > 0) {
                Log.v(GLTextureView.TAG, this.a.toString());
                this.a.delete(0, this.a.length());
            }
        }
    }

    private class i extends b {
        final /* synthetic */ GLTextureView j;

        public i(GLTextureView gLTextureView, boolean z) {
            int i;
            this.j = gLTextureView;
            if (z) {
                i = 16;
            } else {
                i = 0;
            }
            super(gLTextureView, 8, 8, 8, 0, i, 0);
        }
    }

    public GLTextureView(Context context) {
        super(context);
        init();
    }

    public GLTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    protected void finalize() throws Throwable {
        try {
            if (this.mGLThread != null) {
                this.mGLThread.h();
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    private void init() {
        setSurfaceTextureListener(this);
    }

    public void setGLWrapper(GLWrapper gLWrapper) {
        this.mGLWrapper = gLWrapper;
    }

    public void setDebugFlags(int i) {
        this.mDebugFlags = i;
    }

    public int getDebugFlags() {
        return this.mDebugFlags;
    }

    public void setPreserveEGLContextOnPause(boolean z) {
        this.mPreserveEGLContextOnPause = z;
    }

    public boolean getPreserveEGLContextOnPause() {
        return this.mPreserveEGLContextOnPause;
    }

    public void setRenderer(Renderer renderer) {
        checkRenderThreadState();
        if (this.mEGLConfigChooser == null) {
            this.mEGLConfigChooser = new i(this, true);
        }
        if (this.mEGLContextFactory == null) {
            this.mEGLContextFactory = new c();
        }
        if (this.mEGLWindowSurfaceFactory == null) {
            this.mEGLWindowSurfaceFactory = new d();
        }
        this.mRenderer = renderer;
        this.mGLThread = new f(this.mThisWeakRef);
        this.mGLThread.start();
    }

    public void setEGLContextFactory(EGLContextFactory eGLContextFactory) {
        checkRenderThreadState();
        this.mEGLContextFactory = eGLContextFactory;
    }

    public void setEGLWindowSurfaceFactory(EGLWindowSurfaceFactory eGLWindowSurfaceFactory) {
        checkRenderThreadState();
        this.mEGLWindowSurfaceFactory = eGLWindowSurfaceFactory;
    }

    public void setEGLConfigChooser(EGLConfigChooser eGLConfigChooser) {
        checkRenderThreadState();
        this.mEGLConfigChooser = eGLConfigChooser;
    }

    public void setEGLConfigChooser(boolean z) {
        setEGLConfigChooser(new i(this, z));
    }

    public void setEGLConfigChooser(int i, int i2, int i3, int i4, int i5, int i6) {
        setEGLConfigChooser(new b(this, i, i2, i3, i4, i5, i6));
    }

    public void setEGLContextClientVersion(int i) {
        checkRenderThreadState();
        this.mEGLContextClientVersion = i;
    }

    public void setRenderMode(int i) {
        this.mGLThread.a(i);
    }

    public void requestRender() {
        this.mGLThread.c();
    }

    public int getRenderMode() {
        return this.mGLThread.b();
    }

    public void onPause() {
        this.mGLThread.f();
    }

    public void onResume() {
        this.mGLThread.g();
    }

    public void queueEvent(Runnable runnable) {
        this.mGLThread.a(runnable);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mDetached && this.mRenderer != null) {
            int b;
            if (this.mGLThread != null) {
                b = this.mGLThread.b();
            } else {
                b = 1;
            }
            this.mGLThread = new f(this.mThisWeakRef);
            if (b != 1) {
                this.mGLThread.a(b);
            }
            this.mGLThread.start();
        }
        this.mDetached = false;
    }

    protected void onDetachedFromWindow() {
        if (this.mGLThread != null) {
            this.mGLThread.h();
        }
        this.mDetached = true;
        super.onDetachedFromWindow();
    }

    private void checkRenderThreadState() {
        if (this.mGLThread != null) {
            throw new IllegalStateException("setRenderer has already been called for this instance.");
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.mGLThread.d();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.mGLThread.e();
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        this.mGLThread.a(i, i2);
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        onSurfaceTextureSizeChanged(getSurfaceTexture(), i3 - i, i4 - i2);
        super.onLayout(z, i, i2, i3, i4);
    }
}
