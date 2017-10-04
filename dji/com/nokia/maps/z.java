package com.nokia.maps;

import android.opengl.GLSurfaceView.Renderer;
import android.os.Build.VERSION;
import java.lang.ref.WeakReference;

public abstract class z implements Renderer {
    WeakReference<Object> a = new WeakReference(null);
    protected boolean b = false;

    public abstract void a();

    public abstract void b();

    public abstract void c();

    public void a(Object obj) {
        this.a = new WeakReference(obj);
    }

    protected void d() {
        if (VERSION.SDK_INT >= 14) {
            f();
        }
    }

    private void f() {
        y yVar = (y) this.a.get();
        if (yVar != null) {
            yVar.f();
        }
    }

    public void a(boolean z) {
        this.b = z;
    }

    public boolean e() {
        return this.b;
    }
}
