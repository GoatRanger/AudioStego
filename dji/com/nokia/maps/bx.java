package com.nokia.maps;

import android.opengl.GLES20;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.android.mpa.mapping.OnMapRenderListener;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

class bx extends z {
    protected MapImpl c;
    private long d;
    private int e = -1;
    private int f = -1;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private CopyOnWriteArrayList<OnMapRenderListener> j = new CopyOnWriteArrayList();
    private CopyOnWriteArrayList<a> k = new CopyOnWriteArrayList();

    interface a {
        void a();
    }

    public void a(MapImpl mapImpl) {
        this.c = mapImpl;
        if (mapImpl == null) {
            this.i = false;
        } else if (!this.i) {
            this.c.setAAEnabled(at.b());
            this.i = true;
        }
    }

    public void onDrawFrame(GL10 gl10) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glClear(16640);
        if (this.c != null) {
            if (this.h) {
                this.c.freeGfxResources();
                this.h = false;
            }
            if (!(this.c.b() == this.e && this.c.c() == this.f)) {
                onSurfaceChanged(gl10, this.e, this.f);
            }
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (this.c) {
                k();
                this.c.B();
                d();
                boolean a = a(gl10);
                this.c.C();
                this.d = System.currentTimeMillis() - currentTimeMillis;
                b(a);
            }
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        if (this.c != null) {
            this.c.a(i, i2);
        }
        this.e = i;
        this.f = i2;
        a(this.e, this.f);
        bj.e("Renderer", "MapRenderer set size:" + i + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i2, new Object[0]);
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        bj.e("Renderer", "EGL Context lost!", new Object[0]);
        if (this.c != null) {
            if (this.g) {
                this.c.freeGfxResources();
            }
            this.c.setAAEnabled(at.b());
            this.i = true;
        } else {
            this.i = false;
        }
        this.g = true;
    }

    private void i() {
        if (this.c != null) {
            this.c.destroyViewObjects();
            this.c.A();
            this.c.freeGfxResources();
            this.c.D();
            j();
            this.c.b(new Runnable(this) {
                final /* synthetic */ bx a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.c != null) {
                        this.a.c.w();
                    }
                }
            });
            this.c.a().b();
            this.c.b(new Runnable(this) {
                final /* synthetic */ bx a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.c != null && !this.a.c.a().a()) {
                        l.a().a(false, this.a.c.getMapScheme());
                    }
                }
            });
        }
        this.h = false;
        this.i = false;
    }

    public void c() {
        i();
    }

    public void a() {
        i();
    }

    public void b() {
    }

    public void f() {
        this.h = true;
    }

    public void a(OnMapRenderListener onMapRenderListener) {
        if (onMapRenderListener != null) {
            this.j.addIfAbsent(onMapRenderListener);
        }
    }

    public void b(OnMapRenderListener onMapRenderListener) {
        if (onMapRenderListener != null) {
            this.j.remove(onMapRenderListener);
        }
    }

    protected boolean a(GL10 gl10) {
        return false;
    }

    protected void g() {
        this.c = null;
        synchronized (this) {
            this.j.clear();
            this.k.clear();
        }
    }

    public void h() {
        Iterator it = this.j.iterator();
        while (it.hasNext()) {
            ((OnMapRenderListener) it.next()).onRenderBufferCreated();
        }
    }

    private void b(boolean z) {
        Iterator it = this.j.iterator();
        while (it.hasNext()) {
            ((OnMapRenderListener) it.next()).onPostDraw(z, this.d);
        }
    }

    private void a(final int i, final int i2) {
        Iterator it = this.j.iterator();
        while (it.hasNext()) {
            final OnMapRenderListener onMapRenderListener = (OnMapRenderListener) it.next();
            ez.a(new Runnable(this) {
                final /* synthetic */ bx d;

                public void run() {
                    onMapRenderListener.onSizeChanged(i, i2);
                }
            });
        }
    }

    private void j() {
        Iterator it = this.j.iterator();
        while (it.hasNext()) {
            final OnMapRenderListener onMapRenderListener = (OnMapRenderListener) it.next();
            ez.a(new Runnable(this) {
                final /* synthetic */ bx b;

                public void run() {
                    onMapRenderListener.onGraphicsDetached();
                }
            });
        }
    }

    private void k() {
        Iterator it = this.j.iterator();
        while (it.hasNext()) {
            ((OnMapRenderListener) it.next()).onPreDraw();
        }
        it = this.k.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a();
        }
    }

    void a(a aVar) {
        if (aVar != null) {
            this.k.addIfAbsent(aVar);
        }
    }
}
