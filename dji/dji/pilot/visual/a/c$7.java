package dji.pilot.visual.a;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class c$7 implements d {
    final /* synthetic */ c a;

    c$7(c cVar) {
        this.a = cVar;
    }

    public void onSuccess(Object obj) {
        c.k(this.a).obtainMessage(769, 0, 0).sendToTarget();
    }

    public void onFailure(a aVar) {
        c.k(this.a).obtainMessage(769, 1, 0).sendToTarget();
    }
}
