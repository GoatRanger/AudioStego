package dji.sdksharedlib.hardware.abstractions.a.a;

import dji.common.error.DJIAirLinkError;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class c$1 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ c b;

    c$1(c cVar, e eVar) {
        this.b = cVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.a.a(null);
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJIAirLinkError.getDJIError(aVar));
        }
    }
}
