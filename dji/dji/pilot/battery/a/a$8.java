package dji.pilot.battery.a;

import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataSmartBatteryGetStaticData;
import dji.midware.e.d;

class a$8 implements d {
    final /* synthetic */ DataSmartBatteryGetStaticData a;
    final /* synthetic */ a b;

    a$8(a aVar, DataSmartBatteryGetStaticData dataSmartBatteryGetStaticData) {
        this.b = aVar;
        this.a = dataSmartBatteryGetStaticData;
    }

    public void onSuccess(Object obj) {
        a.b(this.b, this.a.getCycleTimes());
        a.c(this.b, (int) this.a.getLifePercent());
    }

    public void onFailure(a aVar) {
    }
}
