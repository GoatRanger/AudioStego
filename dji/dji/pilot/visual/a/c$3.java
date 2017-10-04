package dji.pilot.visual.a;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class c$3 implements d {
    final /* synthetic */ c a;

    c$3(c cVar) {
        this.a = cVar;
    }

    public void onSuccess(Object obj) {
        c.k(this.a).sendMessage(c.k(this.a).obtainMessage(775, 0, 0));
    }

    public void onFailure(a aVar) {
        c.k(this.a).sendMessage(c.k(this.a).obtainMessage(775, 1, 0));
    }
}
