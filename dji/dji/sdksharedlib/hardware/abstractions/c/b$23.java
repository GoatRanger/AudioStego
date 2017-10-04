package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.camera.DJICameraSettingsDef.CameraFileIndexMode;
import dji.common.error.DJICameraError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataBaseCameraGetting;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$23 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ DataBaseCameraGetting b;
    final /* synthetic */ b c;

    b$23(b bVar, e eVar, DataBaseCameraGetting dataBaseCameraGetting) {
        this.c = bVar;
        this.a = eVar;
        this.b = dataBaseCameraGetting;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.a.a(CameraFileIndexMode.find(this.b.getValue()));
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJICameraError.getDJIError(aVar));
        }
    }
}
