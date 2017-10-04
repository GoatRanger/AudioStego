package dji.sdksharedlib.hardware.abstractions.b;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import java.util.concurrent.CountDownLatch;

class l$1 implements d {
    final /* synthetic */ CountDownLatch a;
    final /* synthetic */ l b;

    l$1(l lVar, CountDownLatch countDownLatch) {
        this.b = lVar;
        this.a = countDownLatch;
    }

    public void onSuccess(Object obj) {
        l.a(this.b, dji.midware.data.manager.P3.d.read("g_config.voltage.level_1_protect_0").value.intValue());
        l.b(this.b, dji.midware.data.manager.P3.d.read("g_config.voltage.level_2_protect_0").value.intValue());
        this.a.countDown();
    }

    public void onFailure(a aVar) {
        this.a.countDown();
    }
}
