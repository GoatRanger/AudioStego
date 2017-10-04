package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJICameraError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetAudio;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$28 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ DataCameraGetAudio b;
    final /* synthetic */ b c;

    b$28(b bVar, e eVar, DataCameraGetAudio dataCameraGetAudio) {
        this.c = bVar;
        this.a = eVar;
        this.b = dataCameraGetAudio;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.a.a(Boolean.valueOf(this.b.isEnable()));
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJICameraError.getDJIError(aVar));
        }
    }
}
