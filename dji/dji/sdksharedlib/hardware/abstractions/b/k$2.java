package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.battery.DJIBatteryWarningInformation;
import dji.common.error.DJIError;
import dji.midware.data.model.P3.DataSmartBatteryGetPushDynamicData;
import dji.sdksharedlib.hardware.abstractions.b.a.a;
import dji.sdksharedlib.hardware.abstractions.b.e;

class k$2 implements a {
    final /* synthetic */ e a;
    final /* synthetic */ k b;

    k$2(k kVar, e eVar) {
        this.b = kVar;
        this.a = eVar;
    }

    public void a(DataSmartBatteryGetPushDynamicData dataSmartBatteryGetPushDynamicData) {
        this.a.a(new DJIBatteryWarningInformation(dataSmartBatteryGetPushDynamicData.getStatus()));
    }

    public void a(dji.midware.data.config.P3.a aVar) {
        this.a.a(DJIError.getDJIError(aVar));
    }
}
