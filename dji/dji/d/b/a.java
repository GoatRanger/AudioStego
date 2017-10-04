package dji.d.b;

import dji.common.error.DJIError;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.f;

public class a extends b {
    private static final String a = "FakeComponentAbstraction";

    protected void a() {
        a(b.class, a.class);
    }

    protected void a(c cVar) {
        a(new d(), e.a, 0, cVar);
        a(new d(), e.a, 1, cVar);
    }

    @f(a = "FakeValue")
    public void a(int i, e eVar) {
        if (eVar == null) {
            return;
        }
        if (i > 0) {
            eVar.a(null);
        } else {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "FakeValue")
    public void a(e eVar) {
        if (eVar != null) {
            eVar.a(Integer.valueOf(20));
        }
    }
}
