package dji.sdksharedlib.hardware.abstractions.b.a;

import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataSmartBatteryGetMultBatteryInfo;
import dji.sdksharedlib.hardware.a.h;
import java.util.List;

public class d extends h {
    protected DataSmartBatteryGetMultBatteryInfo a;

    public d() {
        this.a = null;
        this.a = new DataSmartBatteryGetMultBatteryInfo();
    }

    public void a(b bVar) {
        a((Object) bVar);
    }

    protected void a(final List<Object> list) {
        this.a.start(new dji.midware.e.d(this) {
            final /* synthetic */ d b;

            public void onSuccess(Object obj) {
                for (b a : list) {
                    a.a(this.b.a);
                }
            }

            public void onFailure(a aVar) {
                for (b a : list) {
                    a.a(aVar);
                }
            }
        });
    }
}
