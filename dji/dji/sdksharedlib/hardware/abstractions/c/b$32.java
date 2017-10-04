package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJICameraError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetRecordFan;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$32 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ DataCameraGetRecordFan b;
    final /* synthetic */ b c;

    b$32(b bVar, e eVar, DataCameraGetRecordFan dataCameraGetRecordFan) {
        this.c = bVar;
        this.a = eVar;
        this.b = dataCameraGetRecordFan;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.a.a(Boolean.valueOf(this.b.isForceTurnOffFan()));
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJICameraError.getDJIError(aVar));
        }
    }
}
