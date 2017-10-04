package dji.sdksharedlib.hardware.abstractions.b;

import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b$f;
import dji.sdksharedlib.hardware.abstractions.b.a.e;

public class h extends k {
    private static String a = "DJISDKCacheM600BatteryAbstraction";

    public void a(String str, int i, c cVar, b$f dji_sdksharedlib_hardware_abstractions_b_f) {
        super.a(str, i, cVar, dji_sdksharedlib_hardware_abstractions_b_f);
        this.i = i;
        this.c = 6;
        this.b = true;
        this.j = new dji.sdksharedlib.hardware.abstractions.b.a.c(i);
        this.k = new e(i);
    }
}
