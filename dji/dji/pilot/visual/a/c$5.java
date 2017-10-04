package dji.pilot.visual.a;

import com.alipay.sdk.j.i;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.visual.util.c;

class c$5 implements d {
    final /* synthetic */ boolean a;
    final /* synthetic */ c b;

    c$5(c cVar, boolean z) {
        this.b = cVar;
        this.a = z;
    }

    public void onSuccess(Object obj) {
        c.a("camera Tracking success-" + this.a);
    }

    public void onFailure(a aVar) {
        if (!(a.e == aVar || a.E == aVar)) {
            c.k(this.b).sendMessageDelayed(c.k(this.b).obtainMessage(1024, 1, this.a ? 1 : 0), 200);
        }
        c.a("camera Tracking fail-" + this.a + i.b + aVar);
    }
}
