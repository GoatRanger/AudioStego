package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCenterGetSelfDischarge;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$4 implements d {
    final /* synthetic */ DataCenterGetSelfDischarge a;
    final /* synthetic */ e b;
    final /* synthetic */ a c;

    a$4(a aVar, DataCenterGetSelfDischarge dataCenterGetSelfDischarge, e eVar) {
        this.c = aVar;
        this.a = dataCenterGetSelfDischarge;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        int day = this.a.getDay();
        if (day < 1 || day > 10) {
            day = 7;
        }
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
