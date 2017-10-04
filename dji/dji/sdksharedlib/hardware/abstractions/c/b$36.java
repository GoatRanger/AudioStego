package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJICameraError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraSetVideoRecordMode;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$36 implements Runnable {
    final /* synthetic */ e a;
    final /* synthetic */ b b;

    b$36(b bVar, e eVar) {
        this.b = bVar;
        this.a = eVar;
    }

    public void run() {
        DataCameraSetVideoRecordMode dataCameraSetVideoRecordMode = new DataCameraSetVideoRecordMode();
        dataCameraSetVideoRecordMode.a(2, 0, 0);
        dataCameraSetVideoRecordMode.start(new d(this) {
            final /* synthetic */ b$36 a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.a.a(null);
            }

            public void onFailure(a aVar) {
                this.a.a.a(DJICameraError.getDJIError(aVar));
            }
        });
    }
}
