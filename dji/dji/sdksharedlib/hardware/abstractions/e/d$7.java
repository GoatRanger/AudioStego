package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.error.DJIError;
import dji.common.error.DJIGimbalError;
import dji.sdksharedlib.c.c;
import dji.sdksharedlib.d.a;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$7 implements c {
    final /* synthetic */ boolean a;
    final /* synthetic */ e b;
    final /* synthetic */ d c;

    d$7(d dVar, boolean z, e eVar) {
        this.c = dVar;
        this.a = z;
        this.b = eVar;
    }

    public void a(a aVar) {
        if (aVar == null) {
            return;
        }
        if (this.a == ((Boolean) aVar.e()).booleanValue()) {
            if (this.b != null) {
                this.b.a(null);
            }
        } else if (this.b != null) {
            this.b.a(DJIGimbalError.GIMBAL_RESULT_FAILED);
        }
    }

    public void a(DJIError dJIError) {
        if (this.b != null) {
            this.b.a(dJIError);
        }
    }
}
