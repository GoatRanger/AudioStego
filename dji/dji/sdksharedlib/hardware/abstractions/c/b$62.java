package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJICameraError;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$62 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ b b;

    b$62(b bVar, e eVar) {
        this.b = bVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        this.a.a(null);
    }

    public void onFailure(a aVar) {
        this.a.a(DJICameraError.getDJIError(aVar));
    }
}
