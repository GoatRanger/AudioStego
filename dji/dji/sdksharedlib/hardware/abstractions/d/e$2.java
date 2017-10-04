package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.sdksharedlib.hardware.a.e;
import dji.sdksharedlib.hardware.abstractions.b;

class e$2 implements e {
    final /* synthetic */ b.e a;
    final /* synthetic */ e b;

    e$2(e eVar, b.e eVar2) {
        this.b = eVar;
        this.a = eVar2;
    }

    public void a(Object obj) {
        boolean z = true;
        Number number = (Number) obj;
        b.e eVar = this.a;
        if (number.intValue() != 1) {
            z = false;
        }
        CallbackUtils.onSuccess(eVar, Boolean.valueOf(z));
    }

    public void a(DJIError dJIError) {
        CallbackUtils.onFailure(this.a, dJIError);
    }
}
