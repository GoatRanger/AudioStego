package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIFlightControllerError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$23 implements d {
    final /* synthetic */ DataCommonGetVersion a;
    final /* synthetic */ e b;
    final /* synthetic */ d c;

    d$23(d dVar, DataCommonGetVersion dataCommonGetVersion, e eVar) {
        this.c = dVar;
        this.a = dataCommonGetVersion;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        String firmVer = this.a.getFirmVer(".");
        if (this.b != null) {
            this.b.a(firmVer);
        }
    }

    public void onFailure(a aVar) {
        if (this.b != null) {
            this.b.a(DJIFlightControllerError.getDJIError(aVar));
        }
    }
}
