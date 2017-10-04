package dji.sdksharedlib.hardware.abstractions.b.a;

import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataSmartBatteryGetPushDynamicData;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.a.h;
import java.util.List;

public class c extends h {
    protected DataSmartBatteryGetPushDynamicData a = null;
    private int e = 0;

    public c(int i) {
        this.e = i;
        this.a = new DataSmartBatteryGetPushDynamicData();
        if (i == Integer.MAX_VALUE) {
            this.a.setIndex(0).setRequestPush(false);
        } else {
            this.a.setIndex(i + 1).setRequestPush(false);
        }
    }

    public void a(a aVar) {
        a((Object) aVar);
    }

    protected void a(final List<Object> list) {
        this.a.start(new d(this) {
            final /* synthetic */ c b;

            public void onSuccess(Object obj) {
                for (a a : list) {
                    a.a(this.b.a);
                }
            }

            public void onFailure(a aVar) {
                for (a a : list) {
                    a.a(aVar);
                }
            }
        });
    }
}
