package dji.pilot.publics.objects;

import dji.log.DJILogHelper;
import dji.pilot.publics.objects.DJIApplication.2;
import dji.pilot.usercenter.b.a;
import dji.pilot.usercenter.b.a$b;
import dji.thirdparty.a.c;

class DJIApplication$2$1 implements a$b {
    final /* synthetic */ 2 a;

    DJIApplication$2$1(2 2) {
        this.a = 2;
    }

    public void a(String str, int i, Object obj) {
    }

    public void b(String str, int i, Object obj) {
        DJILogHelper.getInstance().LOGI("zcx", "scan end!");
        a.a = true;
        c.a().e(dji.pilot2.library.a.AppScanFinish);
        a.getInstance().b(this);
    }

    public void c(String str, int i, Object obj) {
        DJILogHelper.getInstance().LOGI("zcx", "start!");
    }

    public void a(int i) {
    }
}
