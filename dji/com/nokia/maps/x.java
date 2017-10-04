package com.nokia.maps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.opengl.GLES20;
import android.view.SurfaceHolder;
import com.here.android.mpa.common.OffScreenRenderer.SurfaceUpdatedListener;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

public class x {
    private at a = null;
    private volatile boolean b = false;
    private int c = 0;
    private int d = 0;
    private AtomicBoolean e = new AtomicBoolean(false);
    private a f = null;
    private Semaphore g = new Semaphore(1);
    private z h = null;
    private boolean i = true;
    private SurfaceHolder j = null;
    private SurfaceUpdatedListener k = null;
    private Semaphore l = new Semaphore(0);

    private class a extends Thread {
        final /* synthetic */ x a;
        private EGL10 b;
        private EGLDisplay c;
        private EGLConfig d;
        private EGLContext e;
        private EGLSurface f;
        private boolean g = true;
        private boolean h = false;
        private AtomicBoolean i = new AtomicBoolean(false);
        private int j = 0;

        a(x xVar) {
            this.a = xVar;
            a();
            setPriority(5);
            setName("BasePBufferSurface-RenderThread");
        }

        void a() {
            synchronized (this.a) {
                this.h = true;
            }
        }

        private String a(int i) {
            return "GL Error No.=" + i;
        }

        public void run() {
            while (true) {
                try {
                    this.a.g.acquire();
                    this.a.g.drainPermits();
                    if (!this.a.b) {
                        f();
                        return;
                    } else if (this.a.h == null) {
                        this.a.g.release();
                    } else {
                        synchronized (this.a) {
                            if (this.g || this.h) {
                                f();
                                if (e()) {
                                    this.a.h.onSurfaceCreated(null, this.d);
                                    this.g = false;
                                } else {
                                    throw new Exception("Unable to create EGL context");
                                }
                            }
                            if (this.h) {
                                this.a.h.onSurfaceChanged(null, this.a.c, this.a.d);
                                this.h = false;
                            }
                            this.a.l.release();
                            if (!g()) {
                                throw new RuntimeException("eglMakeCurrent failed " + a(this.b.eglGetError()));
                            } else if (this.a.e.get()) {
                                b();
                            } else {
                                GLES20.glDisable(3089);
                                GLES20.glColorMask(true, true, true, true);
                                GLES20.glDepthMask(true);
                                GLES20.glStencilMask(-1);
                                GLES20.glClear(17664);
                                this.a.h.onDrawFrame(null);
                                this.i.set(true);
                                d();
                                synchronized (this.a) {
                                    if (this.a.k != null) {
                                        this.a.k.onSurfaceUpdated();
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    throw e;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (this.a.l.hasQueuedThreads()) {
                        this.a.l.release();
                    }
                    this.a.b = false;
                    this.a.g.release();
                }
            }
        }

        private void d() {
            synchronized (ap.a) {
                if (this.b.eglSwapBuffers(this.c, this.f)) {
                } else {
                    throw new RuntimeException("Cannot swap buffers");
                }
            }
        }

        @SuppressLint({"NewApi"})
        private boolean e() {
            this.b = (EGL10) EGLContext.getEGL();
            this.c = this.b.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
            if (this.c == EGL10.EGL_NO_DISPLAY) {
                throw new RuntimeException("eglGetDisplay failed " + a(this.b.eglGetError()));
            }
            if (this.b.eglInitialize(this.c, new int[2])) {
                this.d = this.a.a.a(this.b, this.c, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 16, 12326, 8, 12339, 1, 12352, 4, 12344}, false, false, true);
                if (this.d == null) {
                    throw new RuntimeException("eglConfig not initialized");
                }
                this.e = ap.a(this.b, this.c, this.d);
                if (this.a.i) {
                    this.f = this.b.eglCreatePbufferSurface(this.c, this.d, new int[]{12375, this.a.c, 12374, this.a.d, 12344});
                } else {
                    this.f = this.b.eglCreateWindowSurface(this.c, this.d, this.a.j, null);
                }
                if (this.f == null || this.f == EGL10.EGL_NO_SURFACE) {
                    this.j = this.b.eglGetError();
                    if (this.j == 12299) {
                        bj.c("BasePBufferGLSurface", "createWindowSurface returned EGL_BAD_NATIVE_WINDOW.", new Object[0]);
                    }
                    throw new RuntimeException("createWindowSurface failed " + a(this.j));
                } else if (this.b.eglMakeCurrent(this.c, this.f, this.f, this.e)) {
                    return true;
                } else {
                    throw new RuntimeException("eglMakeCurrent failed " + a(this.b.eglGetError()));
                }
            }
            throw new RuntimeException("eglInitialize failed " + a(this.b.eglGetError()));
        }

        public void b() {
            boolean g = g();
            if (this.a.h != null && g) {
                this.a.h.c();
                this.i.set(false);
            }
            h();
        }

        private void f() {
            if (this.b != null) {
                b();
                h();
                synchronized (ap.a) {
                    this.b.eglDestroySurface(this.c, this.f);
                    this.b.eglDestroyContext(this.c, this.e);
                    this.b.eglTerminate(this.c);
                }
            }
            this.b = null;
            this.c = null;
            this.e = null;
            this.f = null;
            this.d = null;
        }

        private boolean g() {
            if (this.e.equals(this.b.eglGetCurrentContext()) && this.f.equals(this.b.eglGetCurrentSurface(12377))) {
                return true;
            }
            if (this.c == null || this.f == null) {
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
                bj.c("BasePBufferGLSurface", "Unable to detach EGL context", new Object[0]);
            }
        }

        public int c() {
            return this.j;
        }
    }

    public x(Context context) {
        a(context);
    }

    private void a(Context context) {
        this.a = new at(context);
    }

    public void a() {
        this.i = true;
        f();
    }

    public void a(SurfaceHolder surfaceHolder, SurfaceUpdatedListener surfaceUpdatedListener) {
        this.j = surfaceHolder;
        this.i = false;
        synchronized (this) {
            this.k = surfaceUpdatedListener;
        }
        f();
    }

    private void f() {
        if (this.c <= 0 || this.d <= 0) {
            throw new RuntimeException("Size is not set");
        }
        synchronized (this) {
            if (this.b) {
                throw new RuntimeException("Cannot start an already started thread");
            } else if (this.h == null) {
                throw new RuntimeException("Model is not set or is null");
            } else {
                this.b = true;
                this.l.drainPermits();
                this.f = new a(this);
                this.f.start();
                this.g.release();
            }
        }
        int c;
        try {
            this.l.acquire();
            c = this.f.c();
            if (c != 0) {
                throw new RuntimeException("Failed with EGL error: " + c);
            }
        } catch (InterruptedException e) {
            c();
            c = this.f.c();
            if (c != 0) {
                throw new RuntimeException("Failed with EGL error: " + c);
            }
        } catch (Throwable th) {
            int c2 = this.f.c();
            if (c2 != 0) {
                RuntimeException runtimeException = new RuntimeException("Failed with EGL error: " + c2);
            }
        }
    }

    public synchronized void b() {
        this.e.set(true);
        this.g.release();
    }

    public synchronized void c() {
        if (this.b) {
            this.b = false;
            this.g.release();
            this.k = null;
            this.j = null;
        } else {
            throw new RuntimeException("Cannot stop a stopped thread");
        }
    }

    public void a(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("width and height must be > 0");
        }
        this.c = i;
        this.d = i2;
        if (this.f != null) {
            this.f.a();
            this.g.release();
        }
    }

    public void a(z zVar) {
        this.h = zVar;
    }

    public void d() {
        this.g.release();
    }

    public void a(boolean z) {
        if (this.h != null) {
            this.h.a(z);
        }
    }

    public boolean e() {
        if (this.h != null) {
            return this.h.e();
        }
        return false;
    }
}
