package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJICameraError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetQuickPlayBack;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$22 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ b b;

    b$22(b bVar, e eVar) {
        this.b = bVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.a.a(Integer.valueOf(DataCameraGetQuickPlayBack.getInstance().getTime()));
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJICameraError.getDJIError(aVar));
        }
    }
}
