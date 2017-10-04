package dji.pilot2.publics.a;

import com.dji.frame.c.l;
import com.here.odnp.config.OdnpConfigStatic;
import dji.pilot.groundStation.b;
import dji.pilot.usercenter.b.f;

class a$4 extends Thread {
    final /* synthetic */ a a;

    a$4(a aVar) {
        this.a = aVar;
    }

    public void run() {
        while (true) {
            if (!(!a.a(this.a).getBoolean("need_upload", false) || l.a(f.getInstance().n()) || l.a(a.a(this.a).getString("terms_ver", "")) || l.a(a.a(this.a).getString("terms_date", "")) || !b.a(a.b(this.a)) || a.c(this.a))) {
                a.a(this.a, true);
                a.d(this.a);
            }
            try {
                sleep(OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
            } catch (InterruptedException e) {
            }
        }
    }
}
