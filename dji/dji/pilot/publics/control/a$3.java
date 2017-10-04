package dji.pilot.publics.control;

import android.util.Log;
import com.dji.frame.c.f;
import dji.log.DJILogHelper;
import dji.pilot.publics.model.DJIUpgradePackListModel.DJIUpgradePack;
import dji.pilot.publics.objects.g;
import dji.thirdparty.a.c;
import dji.thirdparty.afinal.f.a;
import java.io.File;
import java.io.IOException;

class a$3 extends a<String> {
    final /* synthetic */ a a;

    a$3(a aVar) {
        this.a = aVar;
    }

    public void a(String str) {
        Log.i("zyx", "getlist onsuccess!");
        a.a(this.a, false);
        a.a(this.a, str);
        if (a.a(this.a) == null || a.a(this.a).versionlist == null || a.a(this.a).versionlist.size() <= 0 || ((DJIUpgradePack) a.a(this.a).versionlist.get(0)).version == null) {
            DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "getRemoteList json数据格式错误", false, true);
        } else {
            File file = new File(a.u());
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            f.a(file, str);
            g.a(a.g(this.a), "keyForVersionList", true);
            g.a(a.g(this.a), "keyForNetUpdate", System.currentTimeMillis());
        }
        this.a.m = false;
        if (!(a.a(this.a) == null || a.a(this.a).announcement == null)) {
            String str2 = a.a(this.a).announcement.guid;
            if (!str2.equals("")) {
                String b = g.b(a.g(this.a), a.c, "");
                if (b.equals("") || !b.startsWith(str2 + "$$")) {
                    this.a.m = true;
                    Log.i("zyx", "event bus show notice !");
                    c.a().e(a$c.SHOW);
                    g.a(a.g(this.a), a.c, str2 + "$$");
                }
            }
        }
        a.h(this.a);
        a.b(this.a);
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(Throwable th, int i, String str) {
        DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "getlist onFailure " + str);
        a.a(this.a, false);
    }
}
