package dji.phone.j;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.SurfaceTexture.OnFrameAvailableListener;
import dji.log.DJILogHelper;
import dji.midware.media.c;
import dji.midware.media.c.b;
import dji.phone.a.g;

public class d implements OnFrameAvailableListener {
    public static final Object a = new Object();
    private static final String d = "DJILPRenderThreadManager";
    private static final int e = 1;
    private static final int f = 2;
    public SurfaceTexture b;
    long c;
    private c g;
    private int h;
    private int i;
    private c j;
    private c k;
    private c l;
    private b m;

    private static class a {
        private static final d a = new d();

        private a() {
        }
    }

    public static final d getInstance() {
        return a.a;
    }

    private d() {
        this.b = null;
        this.m = new b(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void a(int i, int i2) {
                if (this.a.b != null) {
                    this.a.a(i2);
                    this.a.d();
                }
            }

            public void a() {
                if (this.a.b != null) {
                    this.a.c();
                }
            }
        };
    }

    private void f() {
        this.b = new SurfaceTexture(0);
        this.b.setOnFrameAvailableListener(this);
        this.b.detachFromGLContext();
    }

    public SurfaceTexture a() {
        if (this.b == null) {
            f();
        }
        return this.b;
    }

    public c a(Context context, SurfaceTexture surfaceTexture, int i, int i2) {
        boolean z = false;
        this.h = i;
        this.i = i2;
        this.l = new c(context, surfaceTexture, 1, this.m);
        this.l.a(1);
        this.l.a(i, i2);
        this.l.a(false);
        boolean z2 = dji.pilot.phonecamera.d.a().k() == dji.pilot.phonecamera.d.a().j();
        if (!z2) {
            boolean z3;
            int i3 = dji.pilot.phonecamera.d.a().c().orientation;
            if (i3 == 270) {
                z2 = true;
                z3 = true;
            } else {
                z3 = false;
            }
            DJILogHelper.getInstance().LOGD(d, "DJIMethod : createPreviewRenderThread (92)" + i3, false, true);
            z = z2;
            z2 = z3;
        } else if (dji.pilot.phonecamera.d.a().b().orientation == 90) {
            z2 = true;
        } else {
            boolean z4 = z2;
            z2 = false;
            z = z4;
        }
        g.a(d, "create render: isHorFlip" + z);
        this.l.a(z, z2);
        this.l.start();
        return this.l;
    }

    public c a(Context context, int i, int i2, a aVar) {
        boolean z = false;
        this.j = new c(context, null, 2, this.m);
        this.j.a(1);
        this.j.a(i, i2);
        this.j.a(false);
        this.j.b(false);
        if (dji.pilot.phonecamera.d.a().k() == dji.pilot.phonecamera.d.a().j()) {
            z = true;
        }
        g.a(d, "create render: isHorFlip" + z);
        this.j.a(z, true);
        this.j.a(aVar);
        this.j.start();
        return this.j;
    }

    public c a(Context context, b bVar) {
        boolean z = true;
        this.g = new c();
        this.g.a(1280, 720, bVar);
        this.k = new c(context, this.g.a(), 2, this.m);
        this.k.a(1);
        this.k.a(1280, 720);
        this.k.a(false);
        this.k.b(true);
        if (dji.pilot.phonecamera.d.a().k() != dji.pilot.phonecamera.d.a().j()) {
            z = false;
        }
        g.a(d, "create render: isHorFlip" + z);
        this.k.a(false, false);
        this.k.start();
        return this.k;
    }

    public void b(Context context, b bVar) {
        a(context, bVar);
    }

    public void b() {
        if (this.k != null) {
            this.k.interrupt();
            this.k = null;
        }
        if (this.g != null) {
            this.g.b();
        }
    }

    public void a(int i) {
        this.b.attachToGLContext(i);
    }

    public void c() {
        this.b.detachFromGLContext();
    }

    private void a(c cVar) {
        if (cVar != null && !cVar.isInterrupted()) {
            synchronized (cVar) {
                cVar.notify();
            }
        }
    }

    public void d() {
        if (this.b != null) {
            this.b.updateTexImage();
        }
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.c = surfaceTexture.getTimestamp();
        if (this.b != null) {
            a(this.l);
            a(this.k);
            a(this.j);
        }
    }

    public void e() {
        this.b = null;
        this.j = null;
        this.k = null;
        this.l = null;
        if (this.g != null) {
            this.g.b();
            this.g = null;
        }
    }
}
