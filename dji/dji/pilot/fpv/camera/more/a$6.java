package dji.pilot.fpv.camera.more;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class a$6 implements d {
    final /* synthetic */ int a;
    final /* synthetic */ a b;

    a$6(a aVar, int i) {
        this.b = aVar;
        this.a = i;
    }

    public void onSuccess(Object obj) {
        a.h(this.b).sendEmptyMessageDelayed(4097, 100);
    }

    public void onFailure(a aVar) {
        if (aVar != a.E && !a.h(this.b).hasMessages(4096)) {
            a.h(this.b).sendMessageDelayed(a.h(this.b).obtainMessage(4096, this.a + 1, 0), 100);
        }
    }
}
