package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataSmartBatteryGetMultBatteryInfo;
import dji.sdksharedlib.hardware.abstractions.b.a.b;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$13 implements b {
    final /* synthetic */ e a;
    final /* synthetic */ b b;

    b$13(b bVar, e eVar) {
        this.b = bVar;
        this.a = eVar;
    }

    public void a(DataSmartBatteryGetMultBatteryInfo dataSmartBatteryGetMultBatteryInfo) {
        if (dataSmartBatteryGetMultBatteryInfo != null) {
            int num = dataSmartBatteryGetMultBatteryInfo.getNum();
            for (int i = 0; i < dataSmartBatteryGetMultBatteryInfo.getNum(); i++) {
                if (dataSmartBatteryGetMultBatteryInfo.getValues()[i] == 0) {
                    num--;
                }
            }
            if (this.a != null) {
                this.a.a(Integer.valueOf(num));
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
