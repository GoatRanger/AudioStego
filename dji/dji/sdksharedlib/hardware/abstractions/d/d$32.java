package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.params.P3.ParamInfo;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.d.a.c;

class d$32 implements c {
    final /* synthetic */ e a;
    final /* synthetic */ d b;

    d$32(d dVar, e eVar) {
        this.b = dVar;
        this.a = eVar;
    }

    public void a(ParamInfo paramInfo) {
        CallbackUtils.onSuccess(this.a, Float.valueOf(paramInfo.value.floatValue()));
    }

    public void a(a aVar) {
    }
}
