package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJICameraError;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$52 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ b b;

    b$52(b bVar, e eVar) {
        this.b = bVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.a.a(null);
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJICameraError.getDJIError(aVar));
        }
    }
}
