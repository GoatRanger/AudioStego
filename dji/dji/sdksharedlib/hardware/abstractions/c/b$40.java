package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJICameraError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetVOutParams;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$40 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ b b;

    b$40(b bVar, e eVar) {
        this.b = bVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        this.a.a(Boolean.valueOf(DataCameraGetVOutParams.getInstance().isHDOpen()));
    }

    public void onFailure(a aVar) {
        this.a.a(DJICameraError.getDJIError(aVar));
    }
}
