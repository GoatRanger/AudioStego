package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJICameraError;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$37 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ Runnable b;
    final /* synthetic */ b c;

    b$37(b bVar, e eVar, Runnable runnable) {
        this.c = bVar;
        this.a = eVar;
        this.b = runnable;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.c.G.post(this.b);
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJICameraError.getDJIError(aVar));
        }
    }
}
