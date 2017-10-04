package dji.sdksharedlib.hardware.abstractions.c;

import dji.common.error.DJICameraError;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.e.d;
import dji.sdksharedlib.hardware.abstractions.b.e;

class b$41 implements Runnable {
    final /* synthetic */ int[] a;
    final /* synthetic */ e b;
    final /* synthetic */ b c;

    b$41(b bVar, int[] iArr, e eVar) {
        this.c = bVar;
        this.a = iArr;
        this.b = eVar;
    }

    public void run() {
        DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
        dataBaseCameraSetting.a("Photo");
        dataBaseCameraSetting.a(this.a[0]);
        dataBaseCameraSetting.a(0, 1);
        dataBaseCameraSetting.start(new d(this) {
            final /* synthetic */ b$41 a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                if (this.a.b != null) {
                    this.a.b.a(null);
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
