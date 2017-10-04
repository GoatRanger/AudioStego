package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.params.P3.ParamInfo;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.d.a.c;

class h$5 implements c {
    final /* synthetic */ e a;
    final /* synthetic */ h b;

    h$5(h hVar, e eVar) {
        this.b = hVar;
        this.a = eVar;
    }

    public void a(ParamInfo paramInfo) {
        CallbackUtils.onSuccess(this.a, Boolean.valueOf(paramInfo.value.floatValue() > 0.0f));
    }

    public void a(a aVar) {
        CallbackUtils.onFailure(this.a, DJIError.getDJIError(aVar));
    }
}
