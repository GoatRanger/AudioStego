package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class l$9 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ e b;
    final /* synthetic */ l c;

    l$9(l lVar, int i, e eVar) {
        this.c = lVar;
        this.a = i;
        this.b = eVar;
    }

    public void run() {
        if (l.a(this.c) <= 0 || l.b(this.c) <= 0) {
            l.c(this.c, 1000);
        }
        if (l.a(this.c) <= 0 || this.a <= l.a(this.c) - 100) {
            DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
            dataFlycSetParams.a(new String[]{"g_config.voltage.level_2_protect_0"});
            dataFlycSetParams.a(new Number[]{Integer.valueOf(this.a)});
            dataFlycSetParams.start(new d(this) {
                final /* synthetic */ l$9 a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    CallbackUtils.onSuccess(this.a.b, null);
                }

                public void onFailure(a aVar) {
                    CallbackUtils.onFailure(this.a.b, aVar);
                }
            });
            return;
        }
        CallbackUtils.onFailure(this.b, DJIError.COMMON_PARAM_ILLEGAL);
    }
}
