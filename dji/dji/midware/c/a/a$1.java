package dji.midware.c.a;

import dji.log.DJILog;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import dji.thirdparty.a.c;

class a$1 implements d {
    final /* synthetic */ DataCommonGetVersion a;
    final /* synthetic */ a b;

    a$1(a aVar, DataCommonGetVersion dataCommonGetVersion) {
        this.b = aVar;
        this.a = dataCommonGetVersion;
    }

    public void onSuccess(Object obj) {
        a.a(this.b, this.a);
        c.a().e(this.b);
    }

    public void onFailure(a aVar) {
        DJILog.d(a.e(), "osdGetter fails : " + aVar);
    }
}
