package dji.pilot2.upgrade.a;

import dji.dbox.upgrade.p4.d.a.b;
import dji.log.DJILogHelper;

class a$2 implements b {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    public void a() {
        a.b(this.a, true);
        DJILogHelper.getInstance().LOGE("UpgradeLogManager", "startLogUpload-onSuccess=", true, true);
        if (this.a.a != null) {
            this.a.a.a("Success");
        }
    }

    public void a(String str) {
        if (this.a.a != null) {
            this.a.a.b(str);
        }
    }
}
