package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.error.DJIGimbalError;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class h$4 implements d {
    final /* synthetic */ int a;
    final /* synthetic */ e b;
    final /* synthetic */ h c;

    h$4(h hVar, int i, e eVar) {
        this.c = hVar;
        this.a = i;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        dji.midware.data.manager.P3.e.getInstance();
        this.b.a(Integer.valueOf(dji.midware.data.manager.P3.e.read(h.r()[this.a]).value.intValue()));
    }

    public void onFailure(a aVar) {
        this.b.a(DJIGimbalError.getDJIError(aVar));
    }
}
