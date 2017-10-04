package dji.pilot.flyunlimit;

import com.dji.frame.c.h;
import dji.midware.data.forbid.NFZLogUtil;
import dji.pilot.flyunlimit.b.g;
import dji.thirdparty.afinal.f.a;

class b$5 extends a<String> {
    final /* synthetic */ g a;
    final /* synthetic */ b b;

    b$5(b bVar, g gVar) {
        this.b = bVar;
        this.a = gVar;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        NFZLogUtil.LOGD("submitErrorReport onSuccess: " + str);
        dji.pilot.flyunlimit.jsonbean.a aVar = (dji.pilot.flyunlimit.jsonbean.a) h.b(str, dji.pilot.flyunlimit.jsonbean.a.class);
        if (aVar == null) {
            this.a.a(-1, "result is null");
        } else if (!aVar.a || aVar.b == 200) {
            this.a.a();
        } else {
            this.a.a(aVar.b, a.b(b.b(this.b), aVar.b));
        }
    }

    public void a(Throwable th, int i, String str) {
        NFZLogUtil.LOGD("submitErrorReport onFailure: " + str);
        this.a.a(i, str);
    }
}
