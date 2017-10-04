package dji.dbox.upgrade.p4.c;

import dji.dbox.upgrade.p4.a.a;
import dji.dbox.upgrade.p4.d.c;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class a$1 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ ArrayList b;
    final /* synthetic */ a$a c;
    final /* synthetic */ a d;

    a$1(a aVar, String str, ArrayList arrayList, a$a dji_dbox_upgrade_p4_c_a_a) {
        this.d = aVar;
        this.a = str;
        this.b = arrayList;
        this.c = dji_dbox_upgrade_p4_c_a_a;
    }

    public void run() {
        Object a = a.a(this.d, this.a);
        this.b.add(a.b(this.d, this.a).replace(a, ""));
        try {
            c.a(this.d.a(), a, this.b);
            this.c.a();
        } catch (IOException e) {
            a.a("", "tar package failed=" + e.getMessage());
            new File(this.d.a()).delete();
            e.printStackTrace();
            this.c.b();
        }
    }
}
