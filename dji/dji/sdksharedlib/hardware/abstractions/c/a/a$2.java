package dji.sdksharedlib.hardware.abstractions.c.a;

import dji.common.camera.DJICameraSettingsDef.CameraOrientation;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetCameraRotationMode;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$2 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$2(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        CallbackUtils.onSuccess(this.a, DataCameraGetCameraRotationMode.getInstance().getRotationAngleType().a() == 0 ? CameraOrientation.Landscape : CameraOrientation.Portrait);
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.a, aVar);
    }
}
