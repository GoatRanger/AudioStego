package dji.pilot.publics.control;

import com.dji.frame.c.f;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot.publics.model.DJIUpgradeDateModel;
import dji.thirdparty.afinal.f.a;
import java.io.File;
import java.io.IOException;

class a$2 extends a<String> {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    public void a(String str) {
        this.a.k = a.e(this.a);
        File file = new File(a.t());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        f.a(file, str);
        a.a(this.a, false);
        DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "date =" + str);
        this.a.l = (DJIUpgradeDateModel) h.b(str, DJIUpgradeDateModel.class);
        if (this.a.l != null) {
            this.a.e();
        }
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(Throwable th, int i, String str) {
        DJILogHelper.getInstance().LOGE("", "date = " + str);
        a.a(this.a, false);
        this.a.c();
        a.f(this.a);
    }
}
