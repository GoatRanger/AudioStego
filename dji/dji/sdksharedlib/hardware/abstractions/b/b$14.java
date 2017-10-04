package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.battery.DJIBatteryOverview;
import dji.common.error.DJIError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataSmartBatteryGetMultBatteryInfo;
import dji.sdksharedlib.hardware.abstractions.b.a.b;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$14 implements b {
    final /* synthetic */ e a;
    final /* synthetic */ b b;

    b$14(b bVar, e eVar) {
        this.b = bVar;
        this.a = eVar;
    }

    public void a(DataSmartBatteryGetMultBatteryInfo dataSmartBatteryGetMultBatteryInfo) {
        if (dataSmartBatteryGetMultBatteryInfo != null) {
            Object obj = new DJIBatteryOverview[dataSmartBatteryGetMultBatteryInfo.getNum()];
            for (int i = 0; i < dataSmartBatteryGetMultBatteryInfo.getNum(); i++) {
                obj[i] = new DJIBatteryOverview();
                obj[i].setIndex(i);
                int i2 = dataSmartBatteryGetMultBatteryInfo.getValues()[i];
                obj[i].setEnergyRemainingPercent(i2);
                if (i2 == 0) {
                    obj[i].setIsConnected(false);
                } else {
                    obj[i].setIsConnected(true);
                }
            }
            if (this.a != null) {
                this.a.a(obj);
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
