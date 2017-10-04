package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCenterGetSelfDischarge;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class i$2 implements d {
    final /* synthetic */ DataCenterGetSelfDischarge a;
    final /* synthetic */ e b;
    final /* synthetic */ i c;

    i$2(i iVar, DataCenterGetSelfDischarge dataCenterGetSelfDischarge, e eVar) {
        this.c = iVar;
        this.a = dataCenterGetSelfDischarge;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        int day = this.a.getDay();
        if (day < 1 || day > 10) {
            day = 7;
        }
        this.b.a(Integer.valueOf(day));
    }

    public void onFailure(a aVar) {
        if (this.b != null) {
            this.b.a(DJIBatteryError.getDJIError(aVar));
        }
    }
}
