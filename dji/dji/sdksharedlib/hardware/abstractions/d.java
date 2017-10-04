package dji.sdksharedlib.hardware.abstractions;

import dji.log.DJILog;
import dji.sdksharedlib.b.c.a;
import dji.sdksharedlib.d.c;
import java.util.HashMap;

public abstract class d extends b {
    private static final String a = "DJISubComponentHWAbstraction";

    public void a(String str, int i, String str2, int i2, c cVar, b$f dji_sdksharedlib_hardware_abstractions_b_f) {
        DJILog.i(a, String.format("init, abstraction : %s, component : %s, index : %d", new Object[]{getClass().getSimpleName(), str, Integer.valueOf(i)}));
        this.A = dji_sdksharedlib_hardware_abstractions_b_f;
        DJILog.i("DJISDKMergeHandler", "init");
        this.z = new a().b(str).a(Integer.valueOf(i)).c(str2).b(Integer.valueOf(i2)).a();
        this.D = new HashMap();
        j();
        this.E = new HashMap();
        a(cVar);
        f();
    }
}
