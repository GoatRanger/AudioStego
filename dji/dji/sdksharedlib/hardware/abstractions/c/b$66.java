package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraActiveStatus;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$66 implements d {
    final /* synthetic */ int a;
    final /* synthetic */ e b;
    final /* synthetic */ b c;

    b$66(b bVar, int i, e eVar) {
        this.c = bVar;
        this.a = i;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.b, b.b(this.c, DataCameraActiveStatus.getInstance().getSN(), this.a));
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.b, DJIError.getDJIError(aVar));
    }
}
