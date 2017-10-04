package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.error.DJIError;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$4 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ a$a b;
    final /* synthetic */ a c;

    a$4(a aVar, e eVar, a$a dji_sdksharedlib_hardware_abstractions_e_a_a) {
        this.c = aVar;
        this.a = eVar;
        this.b = dji_sdksharedlib_hardware_abstractions_e_a_a;
    }

    public void onSuccess(Object obj) {
        this.a.a(Integer.valueOf(dji.midware.data.manager.P3.e.read(this.b.a()).value.intValue()));
    }

    public void onFailure(a aVar) {
        this.a.a(DJIError.getDJIError(aVar));
    }
}
