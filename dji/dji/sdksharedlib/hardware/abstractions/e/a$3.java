package dji.sdksharedlib.hardware.abstractions.e;

import dji.common.error.DJIError;
import dji.sdksharedlib.hardware.abstractions.b$b;
import dji.sdksharedlib.hardware.abstractions.b.e;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class a$3 implements Runnable {
    final /* synthetic */ a$a a;
    final /* synthetic */ int b;
    final /* synthetic */ e c;
    final /* synthetic */ a d;

    a$3(a aVar, a$a dji_sdksharedlib_hardware_abstractions_e_a_a, int i, e eVar) {
        this.d = aVar;
        this.a = dji_sdksharedlib_hardware_abstractions_e_a_a;
        this.b = i;
        this.c = eVar;
    }

    public void run() {
        int i = 0;
        while (i < 5) {
            try {
                Thread.sleep(200);
                a.a(this.d, new CountDownLatch(1));
                this.d.a(this.a, new b$b(this, null, null) {
                    final /* synthetic */ a$3 b;

                    public void a(Object obj) {
                        a.a(this.b.d, null);
                        a.a(this.b.d, ((Integer) obj).intValue());
                        a.a(this.b.d).countDown();
                    }

                    public void a(DJIError dJIError) {
                        a.a(this.b.d).countDown();
                    }
                });
                a.a(this.d).await(1, TimeUnit.SECONDS);
                if (a.b(this.d) == this.b) {
                    this.c.a(Integer.valueOf(a.b(this.d)));
                    return;
                } else if (i == 4) {
                    this.c.a(DJIError.COMMON_TIMEOUT);
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
