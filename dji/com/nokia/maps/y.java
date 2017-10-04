package com.nokia.maps;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import dji.pilot.usercenter.protocol.e;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

@TargetApi(16)
public class y extends TextureView {
    private static boolean d = false;
    public static boolean g = true;
    public static int h = -16777216;
    private Semaphore a;
    private AtomicBoolean b = new AtomicBoolean(false);
    private AtomicBoolean c = new AtomicBoolean(false);
    private boolean e;
    private long f;
    private z i = null;
    private at j = null;
    private Object k = null;
    private Semaphore l = new Semaphore(1);
    private SurfaceTextureListener m = new SurfaceTextureListener(this) {
        final /* synthetic */ y a;

        {
            this.a = r1;
        }

        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            synchronized (this) {
                this.a.n = new a(this.a, surfaceTexture, i, i2);
                if (this.a.i != null) {
                    this.a.a();
                }
            }
            if (this.a.i != null) {
                try {
                    this.a.l.acquire();
                } catch (InterruptedException e) {
                }
            }
        }

        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            synchronized (this) {
                if (this.a.n != null && this.a.n.g.compareAndSet(true, false)) {
                    try {
                        this.a.a.release();
                        if (this.a.k != null) {
                            ((aa) this.a.k).c();
                        }
                        this.a.n.join(1000);
                        this.a.n = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        }

        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            synchronized (this) {
                if (this.a.n != null) {
                    this.a.n.a(surfaceTexture);
                    this.a.n.a(i, i2);
                }
            }
        }

        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    };
    private a n;

    private class a extends Thread {
        final /* synthetic */ y a;
        private EGL10 b;
        private EGLDisplay c;
        private EGLConfig d;
        private EGLContext e;
        private EGLSurface f;
        private AtomicBoolean g = new AtomicBoolean(false);
        private SurfaceTexture h = null;
        private int i = 0;
        private int j = 0;
        private AtomicBoolean k = new AtomicBoolean(false);
        private AtomicBoolean l = new AtomicBoolean(false);
        private AtomicBoolean m = new AtomicBoolean(false);

        a(y yVar, SurfaceTexture surfaceTexture, int i, int i2) {
            this.a = yVar;
            a(surfaceTexture);
            a(i, i2);
            setPriority(6);
            setName("BaseTextureView-RenderThread");
        }

        void a(SurfaceTexture surfaceTexture) {
            synchronized (this.k) {
                if (this.h != surfaceTexture) {
                    this.h = surfaceTexture;
                    this.k.set(true);
                    this.a.a.release();
                }
            }
        }

        void a(int i, int i2) {
            synchronized (this.l) {
                if (!(this.i == i && this.j == i2)) {
                    this.i = i;
                    this.j = i2;
                    this.l.set(true);
                    this.a.a.release();
                }
            }
        }

        public void run() {
            while (true) {
                try {
                    this.a.a.acquire();
                    if (!this.g.get()) {
                        b(true);
                        return;
                    } else if (this.a.i == null) {
                        this.a.a.release();
                        Thread.sleep(100);
                    } else {
                        synchronized (this.a) {
                            synchronized (this.k) {
                                int eglGetError;
                                if (this.b != null) {
                                    eglGetError = this.b.eglGetError();
                                    g();
                                    EGLContext eglGetCurrentContext = this.b.eglGetCurrentContext();
                                    EGLDisplay eglGetCurrentDisplay = this.b.eglGetCurrentDisplay();
                                    EGLSurface eglGetCurrentSurface = this.b.eglGetCurrentSurface(12377);
                                    if (eglGetError != e.ap || eglGetCurrentContext == EGL10.EGL_NO_CONTEXT || eglGetCurrentDisplay == EGL10.EGL_NO_DISPLAY || eglGetCurrentSurface == EGL10.EGL_NO_SURFACE) {
                                        bj.f("BaseTextureView", "******** EGL check error ********" + eglGetError, new Object[0]);
                                        this.k.set(true);
                                    }
                                }
                                if (this.k.get() || this.l.get() || this.a.e) {
                                    b(true);
                                    boolean f = f();
                                    if ((f && !y.d) || at.a()) {
                                        at.c();
                                        if (at.a()) {
                                            b(this.m.get());
                                            f = f();
                                        }
                                        y.d = true;
                                    }
                                    GLES20.glClearColor(((float) Color.red(y.h)) / 255.0f, ((float) Color.green(y.h)) / 255.0f, ((float) Color.blue(y.h)) / 255.0f, ((float) Color.alpha(y.h)) / 255.0f);
                                    GLES20.glClearDepthf(1.0f);
                                    GLES20.glClear(16640);
                                    e();
                                    if (GLES20.glGetError() != 0) {
                                        bj.f("BaseTextureView", "GL error try again", new Object[0]);
                                    } else {
                                        this.a.l.release();
                                        if (f) {
                                            this.a.i.onSurfaceCreated(null, this.d);
                                            this.k.set(false);
                                        } else {
                                            throw new Exception("Unable to create EGL context");
                                        }
                                    }
                                }
                                synchronized (this.l) {
                                    if (this.l.get()) {
                                        c();
                                        this.a.i.onSurfaceChanged(null, this.i, this.j);
                                        this.l.set(false);
                                    }
                                }
                                if (this.a.b.get()) {
                                    a();
                                } else {
                                    b();
                                    c();
                                    this.a.i.onDrawFrame(null);
                                    this.m.set(true);
                                    eglGetError = GLES20.glGetError();
                                    if (eglGetError != 0) {
                                        bj.f("BaseTextureView", "GL Thread Error" + eglGetError, new Object[0]);
                                    } else if (!y.g || VERSION.SDK_INT < 16) {
                                        e();
                                    } else {
                                        ((aa) this.a.k).b();
                                        e();
                                        if (as.a()) {
                                            this.a.b();
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    synchronized (this.a) {
                        Log.wtf("BaseTextureView", "******** GL Thread crash ********" + this);
                        e.printStackTrace();
                        Log.wtf("BaseTextureView", "******** GL Thread crash ********" + this);
                        this.g.set(false);
                        this.a.a.release();
                        this.a.l.release();
                        b(true);
                        return;
                    }
                }
            }
        }

        private void c() {
            GLES20.glDisable(3089);
            GLES20.glColorMask(true, true, true, true);
            GLES20.glDepthMask(true);
            GLES20.glStencilMask(-1);
            GLES20.glClear(17664);
        }

        private void d() {
            if (y.g && VERSION.SDK_INT >= 16) {
                ((aa) this.a.k).b();
            }
            e();
        }

        private void e() {
            synchronized (this.a) {
                if (this.a.g()) {
                    g();
                    synchronized (ap.a) {
                        if (this.b.eglSwapBuffers(this.c, this.f)) {
                        } else {
                            throw new RuntimeException("Cannot swap buffers " + GLUtils.getEGLErrorString(this.b.eglGetError()));
                        }
                    }
                }
            }
        }

        private boolean f() {
            this.a.e = false;
            this.b = (EGL10) EGLContext.getEGL();
            this.c = this.b.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (this.c == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed " + GLUtils.getEGLErrorString(this.b.eglGetError()));
            }
            if (this.b.eglInitialize(this.c, new int[2])) {
                this.d = this.a.j.chooseConfig(this.b, this.c);
                if (this.d == null) {
                    throw new RuntimeException("eglConfig not initialized");
                }
                this.e = ap.a(this.b, this.c, this.d);
                this.f = this.b.eglCreateWindowSurface(this.c, this.d, this.h, null);
                if (this.f == null || this.f == EGL10.EGL_NO_SURFACE) {
                    int eglGetError = this.b.eglGetError();
                    if (eglGetError == 12299) {
                        bj.c("BaseTextureView", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.", new Object[0]);
                    }
                    throw new RuntimeException("createWindowSurface failed " + GLUtils.getEGLErrorString(eglGetError));
                } else if (this.b.eglMakeCurrent(this.c, this.f, this.f, this.e)) {
                    this.a.c.set(false);
                    return true;
                } else {
                    throw new RuntimeException("eglMakeCurrent failed " + GLUtils.getEGLErrorString(this.b.eglGetError()));
                }
            }
            throw new RuntimeException("eglInitialize failed " + GLUtils.getEGLErrorString(this.b.eglGetError()));
        }

        public void a(boolean z) {
            boolean g = g();
            if (this.a.i != null && g && z) {
                this.a.i.c();
            }
            this.m.set(false);
            h();
        }

        public void a() {
            boolean g = g();
            if (this.a.i != null && g) {
                this.a.i.a();
            }
            this.a.c.set(true);
            h();
        }

        public void b() {
            boolean g = g();
            if (this.a.i != null && g && this.a.c.get()) {
                this.a.i.b();
            }
            this.a.c.set(false);
        }

        private void b(boolean z) {
            if (this.b != null) {
                this.a.e = true;
                a(z);
                h();
                synchronized (ap.a) {
                    if (!(this.c == null || this.f == null)) {
                        this.b.eglDestroySurface(this.c, this.f);
                    }
                    if (!(this.c == null || this.e == null)) {
                        this.b.eglDestroyContext(this.c, this.e);
                    }
                    if (this.c != null) {
                        this.b.eglTerminate(this.c);
                    }
                }
            }
            this.b = null;
            this.c = null;
            this.e = null;
            this.f = null;
            this.d = null;
            this.a.c.set(false);
        }

        private boolean g() {
            if (this.c == null || this.f == null || this.e == null) {
                return false;
            }
            return this.b.eglMakeCurrent(this.c, this.f, this.f, this.e);
        }

        private void h() {
            boolean eglMakeCurrent;
            if (this.c != null) {
                eglMakeCurrent = this.b.eglMakeCurrent(this.c, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
            } else {
                eglMakeCurrent = false;
            }
            if (!eglMakeCurrent) {
                bj.c("BaseTextureView", "Unable to detach EGL context", new Object[0]);
            }
        }
    }

    public y(Context context) {
        super(context);
        a(context);
    }

    public y(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
        this.e = false;
    }

    private void a(Context context) {
        this.a = new Semaphore(1);
        this.j = new at(context);
        if (VERSION.SDK_INT >= 16) {
            this.k = new aa();
        }
        setSurfaceTextureListener(this.m);
    }

    public void onPause() {
        this.b.set(true);
        this.a.drainPermits();
        this.a.release();
        if (this.k != null) {
            ((aa) this.k).d();
        }
    }

    public void onResume() {
        this.b.set(false);
        if (this.k != null) {
            ((aa) this.k).a();
        }
        this.a.release();
    }

    public void e() {
        if (this.n != null) {
            this.n.d();
        }
    }

    public void requestRender() {
        if (!this.b.get()) {
            this.a.release();
        }
    }

    public void setRenderer(z zVar) {
        this.i = zVar;
        if (this.i != null) {
            this.i.a((Object) this);
        }
        synchronized (this) {
            if (!(this.n == null || this.n.g.get())) {
                a();
            }
            requestRender();
        }
        if (this.i != null) {
            try {
                this.l.acquire();
            } catch (InterruptedException e) {
            }
        }
    }

    private void a() {
        if (this.n != null) {
            this.n.g.set(true);
            this.l.drainPermits();
            this.n.start();
        }
    }

    public void f() {
        this.a.drainPermits();
    }

    protected boolean g() {
        return true;
    }

    private void b() {
        long uptimeMillis = SystemClock.uptimeMillis();
        long j = ((long) as.a) - (uptimeMillis - this.f);
        if (j > 0) {
            try {
                Thread.sleep(j);
            } catch (InterruptedException e) {
            }
        }
        this.f = uptimeMillis;
    }
}
