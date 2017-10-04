package dji.sdksharedlib.hardware.abstractions.a.a.b;

import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataOsdSetSweepFrequency;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.a.g;

public class b extends g {
    protected void a() {
        DataOsdSetSweepFrequency.getInstance().b(false).start(this.a);
    }

    protected void b() {
        DataOsdSetSweepFrequency.getInstance().b(true).start(new d(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(a aVar) {
            }
        });
    }
}
