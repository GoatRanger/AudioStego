package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.flightcontroller.DJIIMUCalibrationStatus;
import dji.common.flightcontroller.DJIIMUSensorState;
import dji.midware.data.params.P3.a;
import dji.midware.e.d;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.e;

class b$3 implements d {
    final /* synthetic */ b a;

    b$3(b bVar) {
        this.a = bVar;
    }

    public void onSuccess(Object obj) {
        DJIIMUSensorState find = DJIIMUSensorState.find(dji.midware.data.manager.P3.d.read("g_config.fdi_sensor[0].gyr_stat_0").value.intValue());
        DJIIMUSensorState find2 = DJIIMUSensorState.find(dji.midware.data.manager.P3.d.read("g_config.fdi_sensor[1].gyr_stat_0").value.intValue());
        DJIIMUSensorState find3 = DJIIMUSensorState.find(dji.midware.data.manager.P3.d.read("g_config.fdi_sensor[2].gyr_stat_0").value.intValue());
        DJIIMUSensorState find4 = DJIIMUSensorState.find(dji.midware.data.manager.P3.d.read("g_config.fdi_sensor[0].acc_stat_0").value.intValue());
        DJIIMUSensorState find5 = DJIIMUSensorState.find(dji.midware.data.manager.P3.d.read("g_config.fdi_sensor[1].acc_stat_0").value.intValue());
        DJIIMUSensorState find6 = DJIIMUSensorState.find(dji.midware.data.manager.P3.d.read("g_config.fdi_sensor[2].acc_stat_0").value.intValue());
        byte byteValue = dji.midware.data.manager.P3.d.read("g_status.acc_gyro[0].cali_cnt_0").value.byteValue();
        byte byteValue2 = dji.midware.data.manager.P3.d.read("g_status.acc_gyro[1].cali_cnt_0").value.byteValue();
        byte byteValue3 = dji.midware.data.manager.P3.d.read("g_status.acc_gyro[2].cali_cnt_0").value.byteValue();
        DJIIMUCalibrationStatus a = this.a.a(a.w);
        DJIIMUCalibrationStatus a2 = this.a.a(a.x);
        DJIIMUCalibrationStatus a3 = this.a.a(a.y);
        c.a b = new c.a().b(e.a).a(Integer.valueOf(0)).c(e.c).b(Integer.valueOf(0));
        c.a b2 = new c.a().b(e.a).a(Integer.valueOf(0)).c(e.c).b(Integer.valueOf(1));
        c.a b3 = new c.a().b(e.a).a(Integer.valueOf(0)).c(e.c).b(Integer.valueOf(2));
        b.a(this.a, find, b.d(e.p).a());
        b.b(this.a, find4, b.d(e.q).a());
        b.c(this.a, Integer.valueOf(byteValue), b.d(e.O).a());
        b.d(this.a, a, b.d(e.r).a());
        b.e(this.a, find2, b2.d(e.p).a());
        b.f(this.a, find5, b2.d(e.q).a());
        b.g(this.a, Integer.valueOf(byteValue2), b2.d(e.O).a());
        b.h(this.a, a2, b2.d(e.r).a());
        b.i(this.a, find3, b3.d(e.p).a());
        b.j(this.a, find6, b3.d(e.q).a());
        b.k(this.a, Integer.valueOf(byteValue3), b3.d(e.O).a());
        b.l(this.a, a3, b3.d(e.r).a());
    }

    public void onFailure(dji.midware.data.config.P3.a aVar) {
    }
}
