package dji.pilot.visual.a;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class c$6 implements d {
    final /* synthetic */ c a;

    c$6(c cVar) {
        this.a = cVar;
    }

    public void onSuccess(Object obj) {
        c.k(this.a).obtainMessage(774, 0, 0).sendToTarget();
    }

    public void onFailure(a aVar) {
        c.k(this.a).obtainMessage(774, 1, 0).sendToTarget();
    }
}
