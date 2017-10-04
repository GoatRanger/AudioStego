package dji.sdksharedlib.hardware.abstractions.h;

import dji.common.error.DJIRemoteControllerError;
import dji.common.remotecontroller.RCCustomButtonTagParam;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataRcGetCustomFuction;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$23 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$23(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        int c1 = DataRcGetCustomFuction.getInstance().getC1();
        int c2 = DataRcGetCustomFuction.getInstance().getC2();
        RCCustomButtonTagParam rCCustomButtonTagParam = new RCCustomButtonTagParam();
        rCCustomButtonTagParam.tag1 = (short) c1;
        rCCustomButtonTagParam.tag2 = (short) c2;
        if (this.a != null) {
            this.a.a(rCCustomButtonTagParam);
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJIRemoteControllerError.getDJIError(aVar));
        }
    }
}
