package dji.sdksharedlib.hardware.abstractions.f;

import dji.common.error.DJISDKCacheError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$1 implements d {
    final /* synthetic */ DataCommonGetVersion a;
    final /* synthetic */ e b;
    final /* synthetic */ b c;

    b$1(b bVar, DataCommonGetVersion dataCommonGetVersion, e eVar) {
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
            this.b.a(DJISDKCacheError.DISCONNECTED);
        }
    }
}
