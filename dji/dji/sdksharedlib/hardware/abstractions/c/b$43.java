package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJICameraError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraSetTimeParams;
import dji.midware.data.model.P3.DataCameraSetTimeParams.TYPE;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$43 implements Runnable {
    final /* synthetic */ int[] a;
    final /* synthetic */ e b;
    final /* synthetic */ Runnable c;
    final /* synthetic */ b d;

    b$43(b bVar, int[] iArr, e eVar, Runnable runnable) {
        this.d = bVar;
        this.a = iArr;
        this.b = eVar;
        this.c = runnable;
    }

    public void run() {
        DataCameraSetTimeParams.getInstance().a(this.a[0]).b(this.a[1]).a(TYPE.a).start(new d(this) {
            final /* synthetic */ b$43 a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                if (this.a.b != null) {
                    this.a.d.G.post(this.a.c);
                }
            }

            public void onFailure(a aVar) {
                if (this.a.b != null) {
                    this.a.b.a(DJICameraError.getDJIError(aVar));
                }
            }
        });
    }
}
