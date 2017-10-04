package dji.sdksharedlib.hardware.abstractions.a;

import dji.sdksharedlib.b.a.e;
import dji.sdksharedlib.hardware.abstractions.a.a.c;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.b.f;

public class a extends b {
    private static final String d = "DJISDKCacheAirLinkAbstraction";
    protected boolean a;
    protected boolean b;
    protected boolean c;
    private dji.sdksharedlib.hardware.abstractions.a.b.a e;
    private c f;
    private b g;

    public a(boolean z, boolean z2, boolean z3, dji.sdksharedlib.hardware.abstractions.a.b.a aVar, c cVar, b bVar) {
        this.a = z;
        this.b = z2;
        this.c = z3;
        this.e = aVar;
        this.f = cVar;
        this.g = bVar;
    }

    public void a(String str, int i, dji.sdksharedlib.d.c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
    }

    protected void a() {
        a(dji.sdksharedlib.b.a.a.class, getClass());
    }

    protected void a(dji.sdksharedlib.d.c cVar) {
        a(this.e, e.h, 0, cVar);
        a(this.f, dji.sdksharedlib.b.a.c.h, 0, cVar);
        a(this.g, dji.sdksharedlib.b.a.b.h, 0, cVar);
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "IsLBAirLinkSupported")
    public void a(b.e eVar) {
        if (eVar != null) {
            eVar.a(Boolean.valueOf(this.a));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "IsWiFiLinkSupported")
    public void b(b.e eVar) {
        if (eVar != null) {
            eVar.a(Boolean.valueOf(this.b));
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "IsAuxLinkSupported")
    public void c(b.e eVar) {
        if (eVar != null) {
            eVar.a(Boolean.valueOf(this.c));
        }
    }
}
