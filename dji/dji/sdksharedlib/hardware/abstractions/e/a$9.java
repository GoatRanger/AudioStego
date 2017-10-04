package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.error.DJIError;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$9 implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ e b;
    final /* synthetic */ a c;

    a$9(a aVar, boolean z, e eVar) {
        this.c = aVar;
        this.a = z;
        this.b = eVar;
    }

    public void run() {
        int i = 0;
        while (i < 15) {
            try {
                Thread.sleep(200);
                if (this.a == (dji.midware.data.manager.P3.e.read("pitch_exp").value.intValue() == 1)) {
                    this.b.a(Boolean.valueOf(this.a));
                    return;
                } else if (i == 14) {
                    this.b.a(DJIError.COMMON_TIMEOUT);
                    return;
                } else {
                    i++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
