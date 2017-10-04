package dji.midware.media.h;

import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import dji.log.DJILogHelper;
import dji.midware.media.d;
import dji.midware.media.e;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

public class a extends c {
    public static final String a = "GLContextMgr17";
    private EGL10 f;
    private EGLConfig[] g;
    private EGLDisplay h;
    private EGLContext i;
    private EGLSurface j;
    private EGLSurface k;

    public a() {
        this.h = EGL10.EGL_NO_DISPLAY;
        this.i = EGL10.EGL_NO_CONTEXT;
        this.j = EGL10.EGL_NO_SURFACE;
        this.k = EGL10.EGL_NO_SURFACE;
        this.e = true;
        this.f = (EGL10) EGLContext.getEGL();
        d();
        a();
        Log.i(a, "OpenGL init Surface/pBuffer/Context successful!");
    }

    protected void a() {
        int[] iArr = new int[]{12320, 32, 12321, 8, 12322, 8, 12323, 8, 12324, 8, 12352, 4, 12339, 4, 12344};
        int[] iArr2 = new int[1];
        this.g = new EGLConfig[1];
        if (this.f.eglChooseConfig(this.h, iArr, this.g, 1, iArr2)) {
            a("initConfig");
            e.c(false, a, "initConfig() done");
            return;
        }
        throw new RuntimeException("eglChooseConfig failed : " + GLUtils.getEGLErrorString(this.f.eglGetError()));
    }

    public void b() {
        this.i = this.f.eglCreateContext(this.h, this.g[0], EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
        if (this.i.equals(EGL10.EGL_NO_CONTEXT)) {
            Log.e(a, "create context returns EGL_NO_CONTEXT");
        }
        a("initContext");
        e.c(false, a, "createContext() done");
    }

    protected void a(String str) {
        while (true) {
            int glGetError = GLES20.glGetError();
            if (glGetError != 0) {
                Log.e(a, str + ": glError " + glGetError);
                e.b(a, str + ": glError " + glGetError);
            } else {
                return;
            }
        }
    }

    public void a(Object obj) {
        EGLSurface eglGetCurrentSurface;
        if (obj == null) {
            eglGetCurrentSurface = this.f.eglGetCurrentSurface(12377);
            this.k = eglGetCurrentSurface;
            this.j = eglGetCurrentSurface;
            if (this.j.equals(EGL10.EGL_NO_SURFACE)) {
                e.b(a, "mEglSurfaceRead = mEglSurfaceDraw = EGL10.EGL_NO_SURFACE");
            }
        } else {
            eglGetCurrentSurface = this.f.eglCreateWindowSurface(this.h, this.g[0], obj, null);
            this.k = eglGetCurrentSurface;
            this.j = eglGetCurrentSurface;
            a("bind surface");
            if (this.j.equals(EGL10.EGL_NO_SURFACE)) {
                int eglGetError = this.f.eglGetError();
                if (eglGetError == 12299) {
                    throw new RuntimeException("create pBuffer returned EGL_BAD_NATIVE_WINDOW.");
                }
                throw new RuntimeException("create pBuffer failed : " + GLUtils.getEGLErrorString(eglGetError));
            }
        }
        e.c(false, a, "initOnScreenSurface() done");
    }

    public void c() {
        this.f.eglMakeCurrent(this.h, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
        if (!(this.j == null || this.j == EGL10.EGL_NO_SURFACE)) {
            this.f.eglDestroySurface(this.h, this.j);
            a("resetSurface eglDestroySurface");
        }
        if (!(this.k == null || this.k == this.j || this.k == EGL10.EGL_NO_SURFACE)) {
            this.f.eglDestroySurface(this.h, this.k);
            a("resetSurface eglDestroySurface");
        }
        this.j = EGL10.EGL_NO_SURFACE;
        this.k = EGL10.EGL_NO_SURFACE;
    }

    protected void d() {
        this.h = this.f.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (this.h.equals(EGL10.EGL_NO_DISPLAY)) {
            throw new RuntimeException("eglGetdisplay failed : " + GLUtils.getEGLErrorString(this.f.eglGetError()));
        }
        if (this.f.eglInitialize(this.h, new int[2])) {
            a("initDisplay");
            e.c(false, a, "initDisplay() done");
            return;
        }
        throw new RuntimeException("eglInitialize failed : " + GLUtils.getEGLErrorString(this.f.eglGetError()));
    }

    public void e() {
        if (!(this.f == null || this.h == null || this.h.equals(EGL10.EGL_NO_DISPLAY))) {
            this.f.eglMakeCurrent(this.h, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
        }
        if (d.a(false)) {
            Log.i(a, "after makeCurrentNothing() : time=" + System.currentTimeMillis());
        }
    }

    public void f() {
        if (!(this.f == null || this.h == null || this.h.equals(EGL10.EGL_NO_DISPLAY))) {
            this.f.eglMakeCurrent(this.h, this.k, this.j, this.i);
            a("make Current this context");
        }
        if (d.a(false)) {
            Log.i(a, "after make on-screen current() : time=" + System.currentTimeMillis());
        }
    }

    public void g() {
        this.j = this.f.eglGetCurrentSurface(12378);
        this.k = this.f.eglGetCurrentSurface(12377);
        this.i = this.f.eglGetCurrentContext();
        this.h = this.f.eglGetCurrentDisplay();
    }

    public void h() {
        if (!(this.k == null || this.k == EGL10.EGL_NO_SURFACE)) {
            this.f.eglDestroySurface(this.h, this.k);
            a("destroy surfaceDraw");
        }
        if (!(this.j == null || this.j == EGL10.EGL_NO_SURFACE || this.j.equals(this.k))) {
            this.f.eglDestroySurface(this.h, this.j);
            a("destroy surfaceRead");
        }
        this.k = EGL10.EGL_NO_SURFACE;
        this.j = EGL10.EGL_NO_SURFACE;
    }

    public synchronized void i() {
        if (this.e) {
            this.e = false;
            if (!(this.f == null || this.h == null || this.h == EGL10.EGL_NO_DISPLAY)) {
                this.f.eglMakeCurrent(this.h, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                h();
                if (!(this.i == null || this.i == EGL10.EGL_NO_CONTEXT)) {
                    this.f.eglDestroyContext(this.h, this.i);
                    a("destroy context");
                    this.i = EGL10.EGL_NO_CONTEXT;
                }
                if (!(this.h == null || this.h == EGL10.EGL_NO_DISPLAY)) {
                    this.f.eglTerminate(this.h);
                    a("destroy display");
                    this.h = EGL10.EGL_NO_DISPLAY;
                }
            }
            DJILogHelper.getInstance().LOGE(a, "OpenGL destoryed", false, false);
        }
    }

    public void j() {
        if (this.e) {
            this.f.eglSwapBuffers(this.h, this.k);
            a("swapBuffers");
            if (d.a(false)) {
                Log.i(a, "after swapBuffers() : time=" + System.currentTimeMillis());
            }
        }
    }

    public boolean k() {
        boolean z;
        boolean z2 = (this.h == null || this.h.equals(EGL10.EGL_NO_DISPLAY)) ? false : true;
        if (this.i == null || this.i.equals(EGL10.EGL_NO_CONTEXT)) {
            z = false;
        } else {
            z = true;
        }
        e.c(false, a, String.format("_display=%b, _context=%b", new Object[]{Boolean.valueOf(z2), Boolean.valueOf(z)}));
        if (z2 && z) {
            return true;
        }
        return false;
    }

    public int l() {
        int[] iArr = new int[1];
        this.f.eglQuerySurface(this.h, this.k, 12375, iArr);
        a("read surface width");
        return iArr[0];
    }

    public int m() {
        int[] iArr = new int[1];
        this.f.eglQuerySurface(this.h, this.k, 12374, iArr);
        a("read surface height");
        return iArr[0];
    }

    public void a(long j) {
    }

    public String n() {
        return a;
    }
}
