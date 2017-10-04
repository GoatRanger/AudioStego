package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataSmartBatteryGetSetSelfDischargeDays;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class k$5 implements d {
    final /* synthetic */ e a;
    final /* synthetic */ DataSmartBatteryGetSetSelfDischargeDays b;
    final /* synthetic */ k c;

    k$5(k kVar, e eVar, DataSmartBatteryGetSetSelfDischargeDays dataSmartBatteryGetSetSelfDischargeDays) {
        this.c = kVar;
        this.a = eVar;
        this.b = dataSmartBatteryGetSetSelfDischargeDays;
    }

    public void onSuccess(Object obj) {
        if (this.a != null) {
            this.a.a(Integer.valueOf(this.b.getDays()));
        }
    }

    public void onFailure(a aVar) {
        if (this.a != null) {
            this.a.a(DJIBatteryError.getDJIError(aVar));
        }
    }
}
