package dji.d.b;

import dji.common.error.DJIError;
import dji.sdksharedlib.hardware.abstractions.a;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.e;
import dji.sdksharedlib.hardware.abstractions.f;

public class d extends dji.sdksharedlib.hardware.abstractions.d {
    private static final String a = "FakeComponentAbstraction";

    protected void a() {
        a(e.class, d.class);
    }

    @e(a = "FakeSubValue")
    public void a(b.e eVar) {
        if (eVar == null) {
            return;
        }
        if (this.z != null) {
            eVar.a(this.z.e());
        } else {
            eVar.a(Integer.valueOf(-1));
        }
    }

    @f(a = "FakeSubValue")
    public void a(int i, b.e eVar) {
        if (eVar == null) {
            return;
        }
        if (this.z == null) {
            eVar.a(null);
        } else if (this.z.e().intValue() == i) {
            eVar.a(null);
        } else {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    @a(a = "FakeAction")
    public void a(b.e eVar, int i, a aVar, boolean z) {
        if (!z) {
            eVar.a(DJIError.COMMON_UNDEFINED);
        } else if (i == this.z.e().intValue()) {
            eVar.a(null);
        } else {
            eVar.a(DJIError.COMMON_PARAM_ILLEGAL);
        }
    }

    @a(a = "FakeActionNoParam")
    public void b(b.e eVar) {
        eVar.a(null);
    }
}
