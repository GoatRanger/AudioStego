package dji.pilot.publics.control.rc;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class b$4 implements d {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ b c;

    b$4(b bVar, int i, int i2) {
        this.c = bVar;
        this.a = i;
        this.b = i2;
    }

    public void onSuccess(Object obj) {
    }

    public void onFailure(a aVar) {
        b.a(this.c).obtainMessage(4096, this.a, this.b, aVar).sendToTarget();
    }
}
