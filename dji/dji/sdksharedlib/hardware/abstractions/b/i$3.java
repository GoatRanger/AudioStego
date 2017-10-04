package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataBatteryActiveStatus;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class i$3 implements d {
    final /* synthetic */ boolean a;
    final /* synthetic */ e b;
    final /* synthetic */ i c;

    i$3(i iVar, boolean z, e eVar) {
        this.c = iVar;
        this.a = z;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        Object pushSN = DataBatteryActiveStatus.getInstance().getPushSN();
        if (!this.a) {
            pushSN = this.c.d(pushSN);
        }
        if (this.b != null) {
            this.b.a(pushSN);
        }
    }

    public void onFailure(a aVar) {
        if (this.b != null) {
            this.b.a(DJIBatteryError.getDJIError(aVar));
        }
    }
}
