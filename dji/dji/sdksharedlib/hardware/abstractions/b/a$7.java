package dji.sdksharedlib.hardware.abstractions.b;

import dji.common.error.DJIBatteryError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataBatteryActiveStatus;
import dji.midware.data.model.b.a.b;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class a$7 implements d {
    final /* synthetic */ boolean a;
    final /* synthetic */ e b;
    final /* synthetic */ a c;

    a$7(a aVar, boolean z, e eVar) {
        this.c = aVar;
        this.a = z;
        this.b = eVar;
    }

    public void onSuccess(Object obj) {
        DataBatteryActiveStatus.getInstance().setType(b.b).start(new d(this) {
            final /* synthetic */ a$7 a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                Object pushSN = DataBatteryActiveStatus.getInstance().getPushSN();
                if (!this.a.a) {
                    pushSN = this.a.c.d(pushSN);
                }
                if (this.a.b != null) {
                    this.a.b.a(pushSN);
                }
            }

            public void onFailure(a aVar) {
                if (this.a.b != null && this.a.b != null) {
                    this.a.b.a(DJIBatteryError.getDJIError(aVar));
                }
            }
        });
    }

    public void onFailure(a aVar) {
        if (this.b != null) {
            this.b.a(DJIBatteryError.getDJIError(aVar));
        }
    }
}
