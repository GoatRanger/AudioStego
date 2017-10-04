package dji.pilot.visual.a;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class c$11 implements d {
    final /* synthetic */ c a;

    c$11(c cVar) {
        this.a = cVar;
    }

    public void onSuccess(Object obj) {
        c.k(this.a).sendMessage(c.k(this.a).obtainMessage(768, 0, 0));
    }

    public void onFailure(a aVar) {
        c.k(this.a).sendMessage(c.k(this.a).obtainMessage(768, 1, 0));
    }
}
