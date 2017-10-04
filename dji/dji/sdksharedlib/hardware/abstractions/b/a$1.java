package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$1 implements d {
    final /* synthetic */ DataCommonGetVersion a;
    final /* synthetic */ e b;
    final /* synthetic */ a c;

    a$1(a aVar, DataCommonGetVersion dataCommonGetVersion, e eVar) {
        this.c = aVar;
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
