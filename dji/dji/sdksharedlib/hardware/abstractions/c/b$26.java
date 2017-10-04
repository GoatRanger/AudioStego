package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJICameraError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$26 implements d {
    final /* synthetic */ DataCommonGetVersion a;
    final /* synthetic */ e b;
    final /* synthetic */ b c;

    b$26(b bVar, DataCommonGetVersion dataCommonGetVersion, e eVar) {
        this.c = bVar;
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
            this.b.a(DJICameraError.getDJIError(aVar));
        }
    }
}
