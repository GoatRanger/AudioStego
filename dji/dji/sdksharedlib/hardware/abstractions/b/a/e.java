package dji.sdksharedlib.hardware.abstractions.b.a;

import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataSmartBatteryGetStaticData;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.a.h;
import java.util.List;

public class e extends h {
    protected DataSmartBatteryGetStaticData a = null;
    private int e = 0;

    public e(int i) {
        this.e = i;
        this.a = new DataSmartBatteryGetStaticData();
        this.a.setIndex(i + 1);
    }

    public void a(f fVar) {
        a((Object) fVar);
    }

    protected void a(final List<Object> list) {
        this.a.start(new d(this) {
            final /* synthetic */ e b;

            public void onSuccess(Object obj) {
                for (f a : list) {
                    a.a(this.b.a);
                }
            }

            public void onFailure(a aVar) {
                for (f a : list) {
                    a.a(aVar);
                }
            }
        });
    }
}
