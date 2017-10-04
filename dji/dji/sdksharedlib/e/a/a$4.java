package dji.sdksharedlib.e.a;

import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycGetParamInfoByHash;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$4 implements d {
    final /* synthetic */ DataFlycGetParamInfoByHash a;
    final /* synthetic */ e b;
    final /* synthetic */ a c;

    a$4(a aVar, DataFlycGetParamInfoByHash dataFlycGetParamInfoByHash, e eVar) {
        this.c = aVar;
        this.a = dataFlycGetParamInfoByHash;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        ParamInfo paramInfo = this.a.getParamInfo();
        CallbackUtils.onSuccess(this.b, new a$a(this.c, paramInfo.range.a, paramInfo.range.b, paramInfo.range.c));
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.b, aVar);
    }
}
