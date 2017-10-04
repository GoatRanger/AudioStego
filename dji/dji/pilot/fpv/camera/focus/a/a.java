package dji.pilot.fpv.camera.focus.a;

import android.os.Handler;
import dji.common.camera.CameraLensFocusTargetPoint;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.a.b;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.c.d;

public class a implements d {
    private static a a;
    private boolean b = false;
    private float c = 0.0f;
    private float d = 0.0f;
    private boolean e = false;
    private boolean f = false;
    private c g = b.f(e.T);
    private c h = b.f(e.U);
    private c i = b.f(e.V);
    private c j = b.f(e.W);
    private c k = b.f(e.ah);
    private c l = b.f(e.ai);
    private c m = b.f(e.ab);
    private c n = b.b(dji.sdksharedlib.b.b.bc);
    private final int o = 257;
    private final long p = 2000;
    private Handler q = new Handler(new 1(this));

    public static a getInstance() {
        if (a == null) {
            a = new a();
        }
        return a;
    }

    private a() {
        a();
    }

    public void a() {
        dji.thirdparty.a.c.a().a((Object) this);
        if (dji.pilot.publics.e.a.c(null)) {
            c();
            h();
            f();
            g();
            e();
        }
    }

    private void c() {
        dji.sdksharedlib.a.a.a((d) this, false, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n);
    }

    public void b() {
        dji.sdksharedlib.a.a.a((d) this);
        dji.thirdparty.a.c.a().d((Object) this);
    }

    public void onEventBackgroundThread(ProductType productType) {
        if (dji.pilot.publics.e.a.c(productType)) {
            c();
        } else {
            dji.sdksharedlib.a.a.a((d) this);
        }
    }

    public void onEventBackgroundThread(o oVar) {
        if (oVar == o.ConnectOK) {
            this.q.removeMessages(257);
            onEventBackgroundThread(i.getInstance().c());
        } else if (oVar == o.ConnectLose) {
            this.q.sendEmptyMessageDelayed(257, 2000);
        }
    }

    private void d() {
        this.b = false;
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = false;
        this.f = false;
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        String f = cVar.f();
        if (f.equals(e.ab)) {
            g();
        }
        if (!this.b) {
            if (f.equals(dji.sdksharedlib.b.b.bc)) {
                h();
            } else if (f.equals(e.ah) || f.equals(e.ai) || f.equals(e.W)) {
                f();
            } else if (f.equals(e.T) || f.equals(e.U) || f.equals(e.V)) {
                e();
            }
        }
    }

    private void e() {
        float c = dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.e(e.T));
        float c2 = dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.e(e.U));
        float c3 = dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.e(e.V));
        this.d = (float) Math.sqrt((double) (((c * c) + (c2 * c2)) + (c3 * c3)));
        i();
    }

    private void f() {
        if (dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.e(e.ah), false)) {
            this.c = dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.e(e.ai));
        } else {
            this.c = dji.sdksharedlib.a.a.c(dji.sdksharedlib.a.a.e(e.W));
        }
        i();
    }

    private void g() {
        boolean a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.e(e.ab), false);
        this.e = a;
        if (a) {
            i();
        } else {
            this.b = false;
        }
    }

    private void h() {
        boolean a = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.b(dji.sdksharedlib.b.b.bc), false);
        this.f = a;
        if (!a) {
            i();
        }
    }

    private void i() {
        if (!this.b && !this.f && this.e && this.c > 1.0f && this.d < 3.0f) {
            DJISDKCache.getInstance().setValue(b.b(dji.sdksharedlib.b.b.S), new CameraLensFocusTargetPoint(dji.pilot.visual.a.d.c, dji.pilot.visual.a.d.c), null);
            this.b = true;
        }
    }
}
