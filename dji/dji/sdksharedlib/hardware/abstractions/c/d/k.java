package dji.sdksharedlib.hardware.abstractions.c.d;

import dji.midware.data.model.P3.DataCameraGetPushShotInfo;
import dji.sdksharedlib.b.b;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b$f;
import dji.sdksharedlib.hardware.abstractions.c.a;

public class k extends c {
    public void a(String str, int i, c cVar, b$f dji_sdksharedlib_hardware_abstractions_b_f) {
        super.a(str, i, cVar, dji_sdksharedlib_hardware_abstractions_b_f);
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
        c();
    }

    public void e() {
        super.e();
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
    }

    protected void c() {
        super.c();
        onEventBackgroundThread(DataCameraGetPushShotInfo.getInstance());
    }

    protected boolean E() {
        return true;
    }

    protected String M() {
        return a.k;
    }

    protected boolean F() {
        return true;
    }

    protected boolean C() {
        return true;
    }

    protected boolean v() {
        return true;
    }

    protected boolean G() {
        return true;
    }

    protected boolean L() {
        return true;
    }

    protected boolean K() {
        return false;
    }

    protected void a() {
        a(b.class, getClass());
    }
}
