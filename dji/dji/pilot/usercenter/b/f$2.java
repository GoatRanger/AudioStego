package dji.pilot.usercenter.b;

import dji.pilot.usercenter.protocol.d;
import dji.pilot.usercenter.protocol.e$a;

class f$2 implements e$a {
    final /* synthetic */ f a;

    f$2(f fVar) {
        this.a = fVar;
    }

    public void a(int i, int i2, int i3, Object obj, Object obj2) {
        if (i != d.n) {
            f.b(this.a).obtainMessage(65536, i, 0, obj).sendToTarget();
        }
    }

    public void a(int i, int i2, int i3, Object obj) {
        if (i != d.n) {
            f.b(this.a).obtainMessage(65537, i, -1, obj).sendToTarget();
        }
    }

    public void a(int i, long j, long j2, int i2, Object obj) {
    }

    public void a(int i, boolean z, int i2, Object obj) {
    }
}
