package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.camera.DJICameraSettingsDef.OpticalZoomSpeed;
import dji.common.error.DJICameraError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraSetOpticsZoomMode.ZoomSpeed;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$61 implements d {
    final /* synthetic */ OpticalZoomSpeed a;
    final /* synthetic */ e b;
    final /* synthetic */ b c;

    b$61(b bVar, OpticalZoomSpeed opticalZoomSpeed, e eVar) {
        this.c = bVar;
        this.a = opticalZoomSpeed;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        this.c.q = ZoomSpeed.find(this.a.value());
        this.b.a(null);
    }

    public void onFailure(a aVar) {
        this.b.a(DJICameraError.getDJIError(aVar));
    }
}
