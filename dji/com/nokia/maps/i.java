package com.nokia.maps;

import android.content.Context;
import android.util.Log;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class i extends z {
    private Context c;
    private j d = null;
    private boolean e = false;

    i(Context context, j jVar) {
        this.c = context;
        this.d = jVar;
    }

    public synchronized void a(j jVar) {
        this.d = jVar;
    }

    public synchronized void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        if (!this.e) {
            Log.d(h.a, "ARRenderer::onSurfaceCreated");
            this.e = true;
            this.d.a(this.c);
        }
    }

    public synchronized void onSurfaceChanged(GL10 gl10, int i, int i2) {
        b();
        if (this.e) {
            this.d.a(i, i2);
        }
    }

    public synchronized void a() {
        c();
    }

    public synchronized void b() {
        if (!this.e) {
            this.e = true;
            this.d.a(this.c);
        }
    }

    public synchronized void c() {
        if (this.e) {
            Log.d(h.a, "ARRenderer::onSurfaceDestruction");
            this.e = false;
            this.d.c();
        }
    }

    public synchronized void onDrawFrame(GL10 gl10) {
        b();
        this.d.e();
    }
}
