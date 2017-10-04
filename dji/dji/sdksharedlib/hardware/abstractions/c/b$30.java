package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJICameraError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataOsdGetMicGain;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$30 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ DataOsdGetMicGain b;
    final /* synthetic */ b c;

    b$30(b bVar, e eVar, DataOsdGetMicGain dataOsdGetMicGain) {
        this.c = bVar;
        this.a = eVar;
        this.b = dataOsdGetMicGain;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.a.a(Integer.valueOf(this.b.getMicGain()));
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJICameraError.getDJIError(aVar));
        }
    }
}
