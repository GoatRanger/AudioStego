package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataSmartBatteryGetPushCellVoltage;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;
import java.util.ArrayList;
import java.util.List;

class b$6 implements d {
    final /* synthetic */ DataSmartBatteryGetPushCellVoltage a;
    final /* synthetic */ e b;
    final /* synthetic */ b c;

    b$6(b bVar, DataSmartBatteryGetPushCellVoltage dataSmartBatteryGetPushCellVoltage, e eVar) {
        this.c = bVar;
        this.a = dataSmartBatteryGetPushCellVoltage;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        int i;
        int[] voltages = this.a.getVoltages();
        List arrayList = new ArrayList();
        for (int i2 : voltages) {
            if (i2 == 0) {
                break;
            }
            arrayList.add(Integer.valueOf(i2));
        }
        Object obj2 = new int[arrayList.size()];
        for (i = 0; i < arrayList.size(); i++) {
            obj2[i] = ((Integer) arrayList.get(i)).intValue();
        }
        if (this.b != null) {
            this.b.a(obj2);
        }
    }

    public void onFailure(a aVar) {
        if (this.b != null) {
            this.b.a(DJIBatteryError.getDJIError(aVar));
        }
    }
}
