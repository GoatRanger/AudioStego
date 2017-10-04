package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.flightcontroller.DJIIMUCalibrationStatus;
import dji.common.flightcontroller.DJIIMUSensorState;
import dji.midware.data.params.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.e;

class h$7 implements d {
    final /* synthetic */ h a;

    h$7(h hVar) {
        this.a = hVar;
    }

    public void onSuccess(Object obj) {
        DJIIMUSensorState find = DJIIMUSensorState.find(dji.midware.data.manager.P3.d.valueOf("g_config.fdi_sensor[0].gyr_stat_0").intValue());
        DJIIMUSensorState find2 = DJIIMUSensorState.find(dji.midware.data.manager.P3.d.valueOf("g_config.fdi_sensor[1].gyr_stat_0").intValue());
        DJIIMUSensorState find3 = DJIIMUSensorState.find(dji.midware.data.manager.P3.d.valueOf("g_config.fdi_sensor[0].acc_stat_0").intValue());
        DJIIMUSensorState find4 = DJIIMUSensorState.find(dji.midware.data.manager.P3.d.valueOf("g_config.fdi_sensor[1].acc_stat_0").intValue());
        byte byteValue = dji.midware.data.manager.P3.d.read("g_status.acc_gyro[0].cali_cnt_0").value.byteValue();
        byte byteValue2 = dji.midware.data.manager.P3.d.read("g_status.acc_gyro[1].cali_cnt_0").value.byteValue();
        DJIIMUCalibrationStatus a = this.a.a(a.x);
        DJIIMUCalibrationStatus a2 = this.a.a(a.x);
        c.a b = new c.a().b(e.a).a(Integer.valueOf(0)).c(e.c).b(Integer.valueOf(0));
        c.a b2 = new c.a().b(e.a).a(Integer.valueOf(0)).c(e.c).b(Integer.valueOf(2));
        h.a(this.a, find, b.d(e.p).a());
        h.b(this.a, find3, b.d(e.q).a());
        h.c(this.a, Integer.valueOf(byteValue), b.d(e.O).a());
        h.d(this.a, a, b.d(e.r).a());
        h.e(this.a, find2, b2.d(e.p).a());
        h.f(this.a, find4, b2.d(e.q).a());
        h.g(this.a, Integer.valueOf(byteValue2), b2.d(e.O).a());
        h.h(this.a, a2, b2.d(e.r).a());
    }

    public void onFailure(dji.midware.data.config.P3.a aVar) {
    }
}
