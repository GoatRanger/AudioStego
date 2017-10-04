package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class k$4 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ k b;

    k$4(k kVar, e eVar) {
        this.b = kVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.a.a(null);
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJIBatteryError.getDJIError(aVar));
        }
    }
}
