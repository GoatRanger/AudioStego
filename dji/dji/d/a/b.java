package dji.d.a;

import dji.d.c;
import dji.sdksharedlib.hardware.abstractions.b.a;
import dji.thirdparty.f.d;
import dji.thirdparty.f.j.e;
import java.util.concurrent.TimeUnit;

public class b extends a {
    public static int a;

    public b() {
        a = 0;
        t();
    }

    private void t() {
        d.b(200, TimeUnit.MILLISECONDS, e.c()).n(new 1(this)).w().C();
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "LifeTimeRemainingPercentage")
    public void a(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        c.a(eVar, Integer.valueOf(a));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "NumberOfDischarge")
    public void b(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        c.a(eVar, Integer.valueOf(a));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "FullChargeEnergy")
    public void c(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        c.a(eVar, Integer.valueOf(a));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "CurrentCurrent")
    public void d(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        c.a(eVar, Integer.valueOf(a * 100));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "CurrentEnergy")
    public void e(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        c.a(eVar, Integer.valueOf(a));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "CurrentVoltage")
    public void f(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        c.a(eVar, Integer.valueOf(20));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "EnergyRemainingPercentage")
    public void g(dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        c.a(eVar, Integer.valueOf(a));
    }
}
