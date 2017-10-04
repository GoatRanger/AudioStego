package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.battery.DJIBatteryCell;
import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataSmartBatteryGetPushCellVoltage;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;
import java.util.ArrayList;
import java.util.List;

class k$3 implements d {
    final /* synthetic */ DataSmartBatteryGetPushCellVoltage a;
    final /* synthetic */ e b;
    final /* synthetic */ k c;

    k$3(k kVar, DataSmartBatteryGetPushCellVoltage dataSmartBatteryGetPushCellVoltage, e eVar) {
        this.c = kVar;
        this.a = dataSmartBatteryGetPushCellVoltage;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        int i = 0;
        int[] voltages = this.a.getVoltages();
        int length;
        for (int i2 : voltages) {
            int i22;
            if (i22 != 0) {
                List arrayList = new ArrayList();
                length = voltages.length;
                while (i < length) {
                    i22 = voltages[i];
                    if (i22 == 0) {
                        break;
                    }
                    arrayList.add(new DJIBatteryCell(i22));
                    i++;
                }
                this.b.a((DJIBatteryCell[]) arrayList.toArray(new DJIBatteryCell[arrayList.size()]));
                return;
            }
        }
    }

    public void onFailure(a aVar) {
        if (this.b != null) {
            this.b.a(DJIBatteryError.getDJIError(aVar));
        }
    }
}
