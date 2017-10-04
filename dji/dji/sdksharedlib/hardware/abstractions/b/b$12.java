package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIError;
import dji.midware.data.model.P3.DataSmartBatteryGetPushDynamicData;
import dji.sdksharedlib.hardware.abstractions.b.a.a;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$12 implements a {
    final /* synthetic */ e a;
    final /* synthetic */ b b;

    b$12(b bVar, e eVar) {
        this.b = bVar;
        this.a = eVar;
    }

    public void a(DataSmartBatteryGetPushDynamicData dataSmartBatteryGetPushDynamicData) {
        if (dataSmartBatteryGetPushDynamicData != null) {
            int temperature = (int) (((double) dataSmartBatteryGetPushDynamicData.getTemperature()) / 10.0d);
            if (this.a != null) {
                this.a.a(Integer.valueOf(temperature));
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
