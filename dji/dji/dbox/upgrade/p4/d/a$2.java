package dji.dbox.upgrade.p4.d;

import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.afinal.f.a;

class a$2 extends a {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    public void a(Throwable th, int i, String str) {
        dji.dbox.upgrade.p4.a.a.a("mInnerUpCallBack, throwable = " + th + ",i = " + i + ",s = " + str + ",continue UUUUPload the next");
        a.p(this.a);
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
        dji.dbox.upgrade.p4.a.a.a(" current/count = " + j2 + d.t + j);
    }

    public void a(Object obj) {
        dji.dbox.upgrade.p4.a.a.a("mInnerUpCallBack, onSuccess .o==  " + obj);
        a.p(this.a);
    }
}
