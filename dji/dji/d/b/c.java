package dji.d.b;

import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.e;
import dji.sdksharedlib.hardware.abstractions.f;

public class c extends b {
    private static final String a = "FakeComponentAbstraction";

    protected void a() {
        a(e.class, c.class);
    }

    @e(a = "FakePush")
    public void a(b.e eVar) {
        if (eVar != null) {
            eVar.a(Integer.valueOf(100));
        }
    }

    @f(a = "FakePush")
    public void a(int i, b.e eVar) {
        if (eVar != null) {
            eVar.a(null);
        }
    }
}
