package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCenterSelfDischarge;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class f$2 implements d {
    final /* synthetic */ DataCenterSelfDischarge a;
    final /* synthetic */ e b;
    final /* synthetic */ f c;

    f$2(f fVar, DataCenterSelfDischarge dataCenterSelfDischarge, e eVar) {
        this.c = fVar;
        this.a = dataCenterSelfDischarge;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        this.b.a(Integer.valueOf(this.a.getDay()));
    }

    public void onFailure(a aVar) {
        if (this.b != null) {
            this.b.a(DJIBatteryError.getDJIError(aVar));
        }
    }
}
