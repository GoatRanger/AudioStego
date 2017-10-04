package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJICameraError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetVideoCaption;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$25 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ DataCameraGetVideoCaption b;
    final /* synthetic */ b c;

    b$25(b bVar, e eVar, DataCameraGetVideoCaption dataCameraGetVideoCaption) {
        this.c = bVar;
        this.a = eVar;
        this.b = dataCameraGetVideoCaption;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.a.a(Boolean.valueOf(this.b.isGenerateVideoCaptionEnable()));
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJICameraError.getDJIError(aVar));
        }
    }
}
