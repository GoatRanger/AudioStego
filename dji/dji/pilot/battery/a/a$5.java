package dji.pilot.battery.a;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class a$5 implements d {
    final /* synthetic */ a a;

    a$5(a aVar) {
        this.a = aVar;
    }

    public void onSuccess(Object obj) {
        a.a(this.a, 0);
    }

    public void onFailure(a aVar) {
        if (a.b(this.a) < 0) {
            a.c(this.a).start(this);
            a.d(this.a);
            return;
        }
        a.a(this.a, 0);
    }
}
