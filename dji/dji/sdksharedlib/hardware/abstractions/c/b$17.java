package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.camera.CameraSpotMeteringArea;
import dji.common.error.DJICameraError;
import dji.log.DJILog;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataBaseCameraGetting;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$17 implements d {
    final /* synthetic */ DataBaseCameraGetting a;
    final /* synthetic */ e b;
    final /* synthetic */ b c;

    b$17(b bVar, DataBaseCameraGetting dataBaseCameraGetting, e eVar) {
        this.c = bVar;
        this.a = dataBaseCameraGetting;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        int value = this.a.getValue();
        int i = (value + 1) % 12;
        value = (value + 1) / 12;
        if (i > 0) {
            i--;
        } else {
            i = 11;
            value--;
        }
        DJILog.d(com.alipay.sdk.f.d.e, "checkVersion", true, true);
        i = this.c.a(i);
        CameraSpotMeteringArea cameraSpotMeteringArea = new CameraSpotMeteringArea();
        cameraSpotMeteringArea.setColIndex(i);
        cameraSpotMeteringArea.setRowIndex(value);
        this.b.a(cameraSpotMeteringArea);
    }

    public void onFailure(a aVar) {
        if (this.b != null) {
            this.b.a(DJICameraError.getDJIError(aVar));
        }
    }
}
