package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycActiveStatus;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$27 implements d {
    final /* synthetic */ DataFlycActiveStatus a;
    final /* synthetic */ e b;
    final /* synthetic */ d c;

    d$27(d dVar, DataFlycActiveStatus dataFlycActiveStatus, e eVar) {
        this.c = dVar;
        this.a = dataFlycActiveStatus;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.b, d.a(this.c, this.a.getSN()));
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.b, aVar);
    }
}
