package dji.sdksharedlib.hardware.abstractions.c.b;

import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetTapZoom;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$1 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$1(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.a, Boolean.valueOf(DataCameraGetTapZoom.getInstance().getEnabled()));
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, aVar);
    }
}
