package com.nokia.maps;

import android.opengl.GLES20;
import android.util.Log;
import java.nio.IntBuffer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class cy extends z {
    int c = -1;
    int d = -1;
    private cq e = new cq(cy.class.getName());
    private PanoramaModelImpl f;
    private boolean g = false;
    private boolean h = false;
    private o i = new o();
    private p j = l.a();
    private a k;

    public interface a {
        void a();

        void a(boolean z);
    }

    cy() {
    }

    public void a(PanoramaModelImpl panoramaModelImpl) {
        this.f = panoramaModelImpl;
        if (this.c != -1 && this.d != -1 && this.f != null) {
            this.f.a(this.c, this.d);
        }
    }

    public void onDrawFrame(GL10 gl10) {
        c(true);
        if (this.f != null) {
            IntBuffer.allocate(2);
            GLES20.glEnable(3042);
            GLES20.glDisable(3042);
            GLES20.glEnable(2884);
            GLES20.glDisable(2884);
            GLES20.glEnable(2960);
            GLES20.glDisable(2960);
            GLES20.glEnable(32823);
            GLES20.glDisable(32823);
            c(false);
            if (this.g) {
                this.f.freeGfxResources();
                this.g = false;
            }
            synchronized (this.f) {
                if (this.k != null) {
                    this.k.a();
                }
                d();
                boolean draw = this.f.draw(false, this.b);
                int glGetError = GLES20.glGetError();
                if (this.h && glGetError == 0) {
                    GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
                    GLES20.glClearDepthf(1.0f);
                    GLES20.glClear(16640);
                }
                if (glGetError != 0) {
                    Log.d("PANORAMA_RENDERER", String.format("GL Error detected: 0x%H", new Object[]{Integer.valueOf(glGetError)}));
                    this.f.freeGfxResources();
                }
                if (this.k != null) {
                    this.k.a(draw);
                }
            }
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        bj.e("PANORAMA_RENDERER", "onSurfaceChanged", new Object[0]);
        this.c = i;
        this.d = i2;
        if (this.f != null) {
            this.g = true;
            this.f.a(this.c, this.d);
        }
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        bj.e("PANORAMA_RENDERER", "onSurfaceCreated", new Object[0]);
    }

    private void g() {
        if (this.f != null) {
            this.f.n();
            this.f.freeGfxResources();
        }
    }

    public void c() {
        bj.e("PANORAMA_RENDERER", "onSurfaceDestruction", new Object[0]);
        g();
        this.i.b();
        if (this.f != null) {
            this.f.b(new Runnable(this) {
                final /* synthetic */ cy a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (!this.a.i.a()) {
                        this.a.j.e(false);
                    }
                }
            });
        }
    }

    public void a() {
        g();
    }

    public void b() {
    }

    private void c(boolean z) {
        if (GLES20.glGetError() != 0) {
            Log.d("PANORAMA_RENDERER", String.format("GL Error detected: 0x%H", new Object[]{Integer.valueOf(GLES20.glGetError())}));
            if (z) {
                this.f.freeGfxResources();
            }
        }
    }

    public void f() {
        this.g = true;
    }

    public void b(boolean z) {
        this.h = z;
    }

    public void a(a aVar) {
        this.k = aVar;
    }
}
