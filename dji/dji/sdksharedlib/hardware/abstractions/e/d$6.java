package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.error.DJIGimbalError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataGimbalGetHandleParams;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$6 implements d {
    final /* synthetic */ DataGimbalGetHandleParams a;
    final /* synthetic */ e b;
    final /* synthetic */ d c;

    d$6(d dVar, DataGimbalGetHandleParams dataGimbalGetHandleParams, e eVar) {
        this.c = dVar;
        this.a = dataGimbalGetHandleParams;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        boolean z = true;
        int panDirection = this.a.getPanDirection();
        if (this.b != null) {
            e eVar = this.b;
            if (panDirection != 1) {
                z = false;
            }
            eVar.a(Boolean.valueOf(z));
        }
    }

    public void onFailure(a aVar) {
        if (this.b != null) {
            this.b.a(DJIGimbalError.getDJIError(aVar));
        }
    }
}
