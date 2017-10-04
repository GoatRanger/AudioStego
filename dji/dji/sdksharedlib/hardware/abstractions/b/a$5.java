package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCenterSelfDischarge;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$5 implements d {
    final /* synthetic */ DataCenterSelfDischarge a;
    final /* synthetic */ e b;
    final /* synthetic */ a c;

    a$5(a aVar, DataCenterSelfDischarge dataCenterSelfDischarge, e eVar) {
        this.c = aVar;
        this.a = dataCenterSelfDischarge;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        int day = this.a.getDay();
        if (this.b != null) {
            this.b.a(Integer.valueOf(day));
        }
    }

    public void onFailure(a aVar) {
        if (this.b != null) {
            this.b.a(DJIBatteryError.getDJIError(aVar));
        }
    }
}
