package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJICameraError;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$46 implements Runnable {
    final /* synthetic */ e a;
    final /* synthetic */ int[] b;
    final /* synthetic */ Runnable c;
    final /* synthetic */ b d;

    b$46(b bVar, e eVar, int[] iArr, Runnable runnable) {
        this.d = bVar;
        this.a = eVar;
        this.b = iArr;
        this.c = runnable;
    }

    public void run() {
        DataCameraGetPushShotParams instance = DataCameraGetPushShotParams.getInstance();
        if (instance.isGetted()) {
            if (this.a != null) {
                this.b[0] = instance.getTimeParamsNum();
                this.b[1] = instance.getTimeParamsPeriod();
                this.d.G.post(this.c);
            }
        } else if (this.a != null) {
            this.a.a(DJICameraError.CAMERA_PARAMETERS_GET_FAILED);
        }
    }
}
