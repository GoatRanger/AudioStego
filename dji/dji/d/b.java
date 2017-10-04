package dji.d;

import dji.d.a.c;
import dji.d.a.d;
import dji.d.a.g;
import dji.d.a.i;
import dji.log.DJILog;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.b.f;
import dji.sdksharedlib.b.h;
import dji.sdksharedlib.hardware.a;

public class b extends a {
    protected void a() {
        a(h.a);
        a(1, h.a, g.class);
        g();
    }

    protected void b() {
        a(1, dji.sdksharedlib.b.b.a, c.class);
    }

    protected void c() {
        a(dji.sdksharedlib.b.a.a);
        a(1, dji.sdksharedlib.b.a.a, dji.d.a.b.class);
    }

    protected void d() {
        a(e.a);
        a(1, e.a, d.class);
    }

    protected void e() {
        a(f.a);
        a(1, f.a, dji.d.a.e.class);
    }

    protected void f() {
        a(dji.sdksharedlib.b.a.a.a);
        try {
            dji.sdksharedlib.hardware.abstractions.a.a aVar = new dji.sdksharedlib.hardware.abstractions.a.a(true, true, true, new i(), new dji.d.a.f(), new dji.d.a.a());
            if (aVar != null) {
                a(aVar);
            }
        } catch (Exception e) {
            DJILog.e("Mock AirLink", "DJISDKCacheHWAbstractionLayer addAbstraction Exception  : AirLink" + DJILog.exceptionToString(e));
        }
    }

    protected void g() {
        a(dji.sdksharedlib.b.i.a);
        a(1, dji.sdksharedlib.b.i.a, dji.d.a.h.class);
    }

    public void h() {
        j();
    }
}
