package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataSmartBatteryGetPushCellVoltage;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class c$1 implements d {
    final /* synthetic */ DataSmartBatteryGetPushCellVoltage a;
    final /* synthetic */ e b;
    final /* synthetic */ c c;

    c$1(c cVar, DataSmartBatteryGetPushCellVoltage dataSmartBatteryGetPushCellVoltage, e eVar) {
        this.c = cVar;
        this.a = dataSmartBatteryGetPushCellVoltage;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        int i = 0;
        int[] voltages = this.a.getVoltages();
        if (voltages != null) {
            int length = voltages.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length && voltages[i2] != 0) {
                i3++;
                i2++;
            }
            Object obj2 = new int[i3];
            while (i < i3) {
                obj2[i] = voltages[i];
                i++;
            }
            this.b.a(obj2);
            return;
        }
        this.b.a(DJIBatteryError.getDJIError(a.l));
    }

    public void onFailure(a aVar) {
        if (this.b != null) {
            this.b.a(DJIBatteryError.getDJIError(aVar));
        }
    }
}
