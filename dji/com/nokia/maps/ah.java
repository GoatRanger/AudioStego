package com.nokia.maps;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ah extends z {
    private final List<z> c = new CopyOnWriteArrayList();
    private final List<z> d = new CopyOnWriteArrayList();

    public void onDrawFrame(GL10 gl10) {
        for (z onDrawFrame : this.c) {
            onDrawFrame.onDrawFrame(gl10);
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        for (z onSurfaceChanged : this.d) {
            onSurfaceChanged.onSurfaceChanged(gl10, i, i2);
        }
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        for (z onSurfaceCreated : this.d) {
            onSurfaceCreated.onSurfaceCreated(gl10, eGLConfig);
        }
    }

    public void c() {
        for (z c : this.d) {
            c.c();
        }
    }

    public void a() {
        for (z a : this.d) {
            a.a();
        }
    }

    public void b() {
        for (z b : this.d) {
            b.b();
        }
    }

    public void a(Object obj) {
        super.a(obj);
        synchronized (this.d) {
            for (z a : this.d) {
                a.a(obj);
            }
        }
    }

    public boolean a(z zVar) {
        if (zVar == null) {
            return false;
        }
        boolean addIfAbsent;
        synchronized (this.c) {
            zVar.a(this.a.get());
            addIfAbsent = ((CopyOnWriteArrayList) this.c).addIfAbsent(zVar);
        }
        return addIfAbsent;
    }

    public boolean b(z zVar) {
        if (zVar == null) {
            return false;
        }
        synchronized (this.c) {
            if (this.c.contains(zVar)) {
                zVar.a(null);
                this.c.remove(zVar);
                return true;
            }
            return false;
        }
    }

    public boolean c(z zVar) {
        if (zVar == null) {
            return false;
        }
        boolean addIfAbsent;
        synchronized (this.d) {
            addIfAbsent = ((CopyOnWriteArrayList) this.d).addIfAbsent(zVar);
        }
        return addIfAbsent;
    }
}
