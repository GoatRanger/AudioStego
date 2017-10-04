package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$30 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ d b;

    d$30(d dVar, e eVar) {
        this.b = dVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.a, null);
        d.d(this.b, Integer.valueOf(0), d.e(this.b, dji.sdksharedlib.b.e.aD));
        d.e(this.b, Double.valueOf(DataOsdGetPushHome.getInstance().getLatitude()), d.f(this.b, dji.sdksharedlib.b.e.al));
        d.f(this.b, Double.valueOf(DataOsdGetPushHome.getInstance().getLatitude()), d.g(this.b, dji.sdksharedlib.b.e.am));
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, aVar);
    }
}
