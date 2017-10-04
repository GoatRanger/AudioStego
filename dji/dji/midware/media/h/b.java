package dji.midware.media.h;

import android.annotation.TargetApi;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.opengl.GLUtils;
import android.util.Log;
import dji.log.DJILogHelper;
import dji.midware.media.d;
import dji.midware.media.e;

@TargetApi(18)
public class b extends c {
    public static final String a = "GLContextMgr18";
    private EGLConfig[] f;
    private EGLDisplay g;
    private EGLContext h;
    private EGLSurface i;
    private EGLSurface j;

    public b() {
        this.g = EGL14.EGL_NO_DISPLAY;
        this.h = EGL14.EGL_NO_CONTEXT;
        this.i = EGL14.EGL_NO_SURFACE;
        this.j = EGL14.EGL_NO_SURFACE;
        this.e = true;
        c();
        a();
        Log.i(a, "GLContextMgr18 init successful!");
    }

    protected void a() {
        int[] iArr = new int[]{12320, 32, 12321, 8, 12322, 8, 12323, 8, 12324, 8, 12352, 4, 12339, 4, 12344};
        int[] iArr2 = new int[1];
        this.f = new EGLConfig[1];
        if (EGL14.eglChooseConfig(this.g, iArr, 0, this.f, 0, 1, iArr2, 0)) {
            a("initConfig");
            e.c(false, a, "initConfig() done");
            return;
        }
        throw new RuntimeException("eglChooseConfig failed : " + GLUtils.getEGLErrorString(EGL14.eglGetError()));
    }

    public void b() {
        this.h = EGL14.eglCreateContext(this.g, this.f[0], EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
        if (this.h.equals(EGL14.EGL_NO_CONTEXT)) {
            Log.e(a, "create context returns EGL_NO_CONTEXT");
        }
        a("initContext");
        e.c(false, a, "createContext() done");
    }

    public void a(Object obj) {
        EGLSurface eglGetCurrentSurface;
        if (obj == null) {
            eglGetCurrentSurface = EGL14.eglGetCurrentSurface(12377);
            this.j = eglGetCurrentSurface;
            this.i = eglGetCurrentSurface;
            if (this.i.equals(EGL14.EGL_NO_SURFACE)) {
                e.b(a, "mEglSurfaceRead = mEglSurfaceDraw = NO_SURFACE");
            }
        } else {
            eglGetCurrentSurface = EGL14.eglCreateWindowSurface(this.g, this.f[0], obj, new int[]{12344}, 0);
            this.j = eglGetCurrentSurface;
            this.i = eglGetCurrentSurface;
            a("bind surface");
            if (this.i.equals(EGL14.EGL_NO_SURFACE)) {
                Log.e(a, "create surface returns EGL10.EGL_NO_SURFACE");
                if (EGL14.eglGetError() == 12299) {
                    throw new RuntimeException("create pBuffer returned EGL_BAD_NATIVE_WINDOW.");
                }
                throw new RuntimeException("create pBuffer failed : " + GLUtils.getEGLErrorString(EGL14.eglGetError()));
            }
        }
        e.c(false, a, "initOnScreenSurface() done");
    }

    protected void c() {
        this.g = EGL14.eglGetDisplay(0);
        if (this.g.equals(EGL14.EGL_NO_DISPLAY)) {
            throw new RuntimeException("eglGetdisplay failed : " + GLUtils.getEGLErrorString(EGL14.eglGetError()));
        }
        if (EGL14.eglInitialize(this.g, new int[1], 0, new int[1], 0)) {
            a("initDisplay");
            e.c(false, a, "initDisplay() done");
            return;
        }
        throw new RuntimeException("eglInitialize failed : " + GLUtils.getEGLErrorString(EGL14.eglGetError()));
    }

    public void e() {
        if (!(this.g == null || this.g.equals(EGL14.EGL_NO_DISPLAY))) {
            EGL14.eglMakeCurrent(this.g, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
        }
        if (d.a(false)) {
            Log.i(a, "after makeCurrentNothing() : time=" + System.currentTimeMillis());
        }
    }

    public void f() {
        if (!(this.g == null || this.g.equals(EGL14.EGL_NO_DISPLAY))) {
            EGL14.eglMakeCurrent(this.g, this.j, this.i, this.h);
            a("make Current this context");
        }
        if (d.a(false)) {
            Log.i(a, "after make on-screen current() : time=" + System.currentTimeMillis());
        }
    }

    public void g() {
        this.i = EGL14.eglGetCurrentSurface(12378);
        this.j = EGL14.eglGetCurrentSurface(12377);
        this.h = EGL14.eglGetCurrentContext();
        this.g = EGL14.eglGetCurrentDisplay();
    }

    public void h() {
        if (!(this.j == null || this.j.equals(EGL14.EGL_NO_SURFACE))) {
            EGL14.eglDestroySurface(this.g, this.j);
            a("destroy surfaceDraw");
        }
        if (!(this.i == null || this.i.equals(EGL14.EGL_NO_SURFACE) || this.i.equals(this.j))) {
            EGL14.eglDestroySurface(this.g, this.i);
            a("destroy surfaceRead");
        }
        this.j = EGL14.EGL_NO_SURFACE;
        this.i = EGL14.EGL_NO_SURFACE;
    }

    public synchronized void i() {
        if (this.e) {
            this.e = false;
            if (!this.g.equals(EGL14.EGL_NO_DISPLAY)) {
                EGL14.eglMakeCurrent(this.g, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
                h();
                if (!(this.h == null || this.h.equals(EGL14.EGL_NO_CONTEXT))) {
                    EGL14.eglDestroyContext(this.g, this.h);
                    a("destroy context");
                    this.h = EGL14.EGL_NO_CONTEXT;
                }
                if (!(this.g == null || this.g.equals(EGL14.EGL_NO_DISPLAY))) {
                    EGL14.eglTerminate(this.g);
                    a("destroy display");
                    this.g = EGL14.EGL_NO_DISPLAY;
                }
            }
            DJILogHelper.getInstance().LOGE(a, "OpenGL destoryed", false, true);
        }
    }

    public void j() {
        if (this.e) {
            EGL14.eglSwapBuffers(this.g, this.j);
            a("swapBuffers");
            if (d.a(false)) {
                Log.i(a, "after swapBuffers() : time=" + System.currentTimeMillis());
            }
        }
    }

    public boolean k() {
        boolean z;
        boolean z2 = (this.g == null || this.g.equals(EGL14.EGL_NO_DISPLAY)) ? false : true;
        if (this.h == null || this.h.equals(EGL14.EGL_NO_CONTEXT)) {
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
        EGL14.eglQuerySurface(this.g, this.j, 12375, iArr, 0);
        a("read surface width");
        return iArr[0];
    }

    public int m() {
        int[] iArr = new int[1];
        EGL14.eglQuerySurface(this.g, this.j, 12374, iArr, 0);
        a("read surface height");
        return iArr[0];
    }

    public void a(long j) {
        EGLExt.eglPresentationTimeANDROID(this.g, this.j, 1000 * j);
    }

    public String n() {
        return a;
    }
}
