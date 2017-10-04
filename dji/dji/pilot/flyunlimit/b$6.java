package dji.pilot.flyunlimit;

import dji.midware.data.forbid.NFZLogUtil;
import dji.thirdparty.afinal.f.a;

class b$6 extends a<String> {
    final /* synthetic */ b a;

    b$6(b bVar) {
        this.a = bVar;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        NFZLogUtil.LOGD("submitErrorReport airmap onSuccess: " + str);
    }

    public void a(Throwable th, int i, String str) {
        NFZLogUtil.LOGD("submitErrorReport airmap onFailure: " + str);
    }
}
