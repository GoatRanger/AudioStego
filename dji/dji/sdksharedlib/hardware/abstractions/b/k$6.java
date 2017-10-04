package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class k$6 implements d {
    final /* synthetic */ DataCommonGetVersion a;
    final /* synthetic */ e b;
    final /* synthetic */ k c;

    k$6(k kVar, DataCommonGetVersion dataCommonGetVersion, e eVar) {
        this.c = kVar;
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
            this.b.a(DJIBatteryError.getDJIError(aVar));
        }
    }
}
