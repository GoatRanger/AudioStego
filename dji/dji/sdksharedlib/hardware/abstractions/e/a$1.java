package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.error.DJIGimbalError;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$1 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ int b;
    final /* synthetic */ a c;

    a$1(a aVar, e eVar, int i) {
        this.c = aVar;
        this.a = eVar;
        this.b = i;
    }

    public void onSuccess(Object obj) {
        this.a.a(Integer.valueOf(this.b));
    }

    public void onFailure(a aVar) {
        this.a.a(DJIGimbalError.getDJIError(aVar));
    }
}
