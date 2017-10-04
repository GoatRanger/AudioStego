package dji.sdksharedlib.hardware.abstractions.h;

import dji.common.error.DJIRemoteControllerError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataOsdActiveStatus;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$22 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a b;

    a$22(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    public void onSuccess(Object obj) {
        String d = this.b.d(DataOsdActiveStatus.getInstance().getSN());
        if (this.a != null) {
            this.a.a(d);
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJIRemoteControllerError.getDJIError(aVar));
        }
    }
}
