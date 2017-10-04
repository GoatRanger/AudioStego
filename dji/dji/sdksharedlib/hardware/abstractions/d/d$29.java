package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$29 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ d b;

    d$29(d dVar, e eVar) {
        this.b = dVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.a, null);
        d.a(this.b, Float.valueOf(((float) DataOsdGetPushCommon.getInstance().getHeight()) / 10.0f), d.b(this.b, dji.sdksharedlib.b.e.aD));
        d.b(this.b, Double.valueOf(DataOsdGetPushCommon.getInstance().getLatitude()), d.c(this.b, dji.sdksharedlib.b.e.al));
        d.c(this.b, Double.valueOf(DataOsdGetPushCommon.getInstance().getLatitude()), d.d(this.b, dji.sdksharedlib.b.e.am));
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, aVar);
    }
}
