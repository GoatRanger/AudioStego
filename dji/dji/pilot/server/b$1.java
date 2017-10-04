package dji.pilot.server;

import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot.server.model.DJIRegisterDeviceResultModel;
import dji.thirdparty.a.c;
import dji.thirdparty.afinal.f.a;

class b$1 extends a<String> {
    final /* synthetic */ b a;

    b$1(b bVar) {
        this.a = bVar;
    }

    public void a(String str) {
        DJILogHelper.getInstance().LOGD(b.a(this.a), "registerDeviceUrl success t=" + str, "ServerLog");
        b.a(this.a, (DJIRegisterDeviceResultModel) h.b(str, DJIRegisterDeviceResultModel.class));
        if (b.b(this.a) != null) {
            b.c(this.a);
        } else {
            b.a(this.a, b$a.NO_GETED);
        }
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(Throwable th, int i, String str) {
        DJILogHelper.getInstance().LOGD(b.a(this.a), "registerDeviceUrl onFailure=" + str, "ServerLog");
        b.a(this.a, b$a.NO_GETED);
        if (!c.a().c(this.a)) {
            c.a().a(this.a);
        }
    }
}
