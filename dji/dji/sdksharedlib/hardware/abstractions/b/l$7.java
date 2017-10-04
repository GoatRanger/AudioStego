package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class l$7 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ e b;
    final /* synthetic */ l c;

    l$7(l lVar, int i, e eVar) {
        this.c = lVar;
        this.a = i;
        this.b = eVar;
    }

    public void run() {
        if (l.b(this.c) <= 0 || l.a(this.c) <= 0) {
            l.c(this.c, 1000);
        }
        int i = (l.b(this.c) <= 0 || l.b(this.c) <= this.a - 100) ? 0 : 1;
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a(i != 0 ? new String[]{"g_config.voltage.level_2_protect_0", "g_config.voltage.level_1_protect_0"} : new String[]{"g_config.voltage.level_1_protect_0"});
        dataFlycSetParams.a(i != 0 ? new Number[]{Integer.valueOf(this.a - 100), Integer.valueOf(this.a)} : new Number[]{Integer.valueOf(this.a)});
        dataFlycSetParams.start(new d(this) {
            final /* synthetic */ l$7 a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                l.a(this.a.c, this.a.a);
                CallbackUtils.onSuccess(this.a.b, null);
            }

            public void onFailure(a aVar) {
                CallbackUtils.onFailure(this.a.b, aVar);
            }
        });
    }
}
