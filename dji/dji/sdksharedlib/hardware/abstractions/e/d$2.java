package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.error.DJIGimbalError;
import dji.common.gimbal.DJIGimbalControllerMode;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataBaseGetting;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class d$2 implements d {
    final /* synthetic */ DataBaseGetting a;
    final /* synthetic */ e b;
    final /* synthetic */ d c;

    d$2(d dVar, DataBaseGetting dataBaseGetting, e eVar) {
        this.c = dVar;
        this.a = dataBaseGetting;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        this.b.a(DJIGimbalControllerMode.values()[this.a.getValue()]);
    }

    public void onFailure(a aVar) {
        this.b.a(DJIGimbalError.GIMBAL_RESULT_FAILED);
    }
}
