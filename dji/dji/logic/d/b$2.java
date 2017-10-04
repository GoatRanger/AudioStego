package dji.logic.d;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class b$2 implements d {
    final /* synthetic */ b a;

    b$2(b bVar) {
        this.a = bVar;
    }

    public void onSuccess(Object obj) {
        b.c(this.a).sendMessageDelayed(b.c(this.a).obtainMessage(4096, 0, 0), 0);
    }

    public void onFailure(a aVar) {
        b.c(this.a).sendMessageDelayed(b.c(this.a).obtainMessage(4096, 1, 0), 200);
    }
}
