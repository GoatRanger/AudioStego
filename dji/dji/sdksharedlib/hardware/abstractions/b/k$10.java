package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIError;
import dji.midware.data.model.P3.DataSmartBatteryGetPushDynamicData;
import dji.sdksharedlib.hardware.abstractions.b.a.a;
import dji.sdksharedlib.hardware.abstractions.b.e;

class k$10 implements a {
    final /* synthetic */ e a;
    final /* synthetic */ k b;

    k$10(k kVar, e eVar) {
        this.b = kVar;
        this.a = eVar;
    }

    public void a(DataSmartBatteryGetPushDynamicData dataSmartBatteryGetPushDynamicData) {
        if (dataSmartBatteryGetPushDynamicData != null) {
            int current = dataSmartBatteryGetPushDynamicData.getCurrent();
            if (this.a != null) {
                this.a.a(Integer.valueOf(current));
            }
        } else if (this.a != null) {
            this.a.a(DJIError.BATTERY_GET_SMART_BATTERY_INFO_FAILED);
        }
    }

    public void a(dji.midware.data.config.P3.a aVar) {
        if (this.a != null) {
            this.a.a(DJIError.getDJIError(aVar));
        }
    }
}
