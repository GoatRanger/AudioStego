package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraActiveStatus;
import dji.midware.data.model.b.a.b;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$65 implements d {
    final /* synthetic */ int a;
    final /* synthetic */ e b;
    final /* synthetic */ b c;

    b$65(b bVar, int i, e eVar) {
        this.c = bVar;
        this.a = i;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        DataCameraActiveStatus.getInstance().setType(b.b).start(new d(this) {
            final /* synthetic */ b$65 a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                CallbackUtils.onSuccess(this.a.b, b.a(this.a.c, DataCameraActiveStatus.getInstance().getSN(), this.a.a));
            }

            public void onFailure(a aVar) {
                CallbackUtils.onFailure(this.a.b, DJIError.getDJIError(aVar));
            }
        });
    }

    public void onFailure(a aVar) {
        CallbackUtils.onFailure(this.b, DJIError.getDJIError(aVar));
    }
}
