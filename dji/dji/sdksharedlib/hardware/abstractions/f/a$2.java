package dji.sdksharedlib.hardware.abstractions.f;

import dji.common.error.DJISDKCacheError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataOsdActiveStatus;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$2 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$2(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        String a = a.a(this.b, DataOsdActiveStatus.getInstance().getSN());
        if (this.a != null) {
            this.a.a(a);
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJISDKCacheError.DISCONNECTED);
        }
    }
}
