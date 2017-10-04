package dji.sdksharedlib.hardware.abstractions.d;

import dji.common.flightcontroller.DJIFlightControllerControlMode;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.hardware.abstractions.b;
import dji.sdksharedlib.hardware.abstractions.f;

public class c extends b {
    private static final String a = "g_config.control.control_mode[0]_0";

    protected void a() {
        super.a();
        a((Object) Boolean.valueOf(true), c(e.i));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "ControlMode")
    public void i(b.e eVar) {
        new DataFlycGetParams().setInfos(new String[]{"g_config.control.control_mode[0]_0"}).start(new 1(this, eVar));
    }

    @f(a = "ControlMode")
    public void a(DJIFlightControllerControlMode dJIFlightControllerControlMode, b.e eVar) {
        new DataFlycSetParams().a("g_config.control.control_mode[0]_0", Integer.valueOf(dJIFlightControllerControlMode.value())).start(new 2(this, eVar));
    }
}
