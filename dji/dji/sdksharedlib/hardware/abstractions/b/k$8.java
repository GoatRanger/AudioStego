package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataSmartBatteryGetStaticData;
import dji.sdksharedlib.hardware.abstractions.b.a.f;
import dji.sdksharedlib.hardware.abstractions.b.e;

class k$8 implements f {
    final /* synthetic */ e a;
    final /* synthetic */ k b;

    k$8(k kVar, e eVar) {
        this.b = kVar;
        this.a = eVar;
    }

    public void a(DataSmartBatteryGetStaticData dataSmartBatteryGetStaticData) {
        if (dataSmartBatteryGetStaticData != null) {
            int cycleTimes = dataSmartBatteryGetStaticData.getCycleTimes();
            if (this.a != null) {
                this.a.a(Integer.valueOf(cycleTimes));
            }
        } else if (this.a != null) {
            this.a.a(DJIError.BATTERY_GET_SMART_BATTERY_INFO_FAILED);
        }
    }

    public void a(a aVar) {
        if (this.a != null) {
            this.a.a(DJIError.getDJIError(aVar));
        }
    }
}
