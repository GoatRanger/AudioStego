package dji.sdksharedlib.hardware.abstractions.d;

import dji.sdksharedlib.e.a.a;
import dji.sdksharedlib.hardware.abstractions.f;

public class e extends h {
    protected void g_() {
        a((Object) Boolean.valueOf(false), c("IsLandingGearMovable"));
        a((Object) Boolean.valueOf(false), c(dji.sdksharedlib.b.e.i));
        a((Object) Boolean.valueOf(false), c(dji.sdksharedlib.b.e.f));
        a((Object) Integer.valueOf(1), c(dji.sdksharedlib.b.e.e));
        a((Object) Boolean.valueOf(false), c(dji.sdksharedlib.b.e.s));
    }

    protected void a() {
        super.a();
    }

    @f(a = "Tripod")
    public void d(boolean z, dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        a.getInstance().a(dji.sdksharedlib.b.e.cI, Integer.valueOf(z ? 1 : 0), new 1(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "Tripod")
    public void i(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        this.G.a(dji.sdksharedlib.b.e.cI, new 2(this, eVar));
    }
}
