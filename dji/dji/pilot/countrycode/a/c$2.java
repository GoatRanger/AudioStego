package dji.pilot.countrycode.a;

import android.location.Location;
import dji.a.a;
import java.util.TimerTask;

class c$2 extends TimerTask {
    final /* synthetic */ c a;

    c$2(c cVar) {
        this.a = cVar;
    }

    public void run() {
        if (c.a(this.a).a()) {
            Location e = a.getInstance().e();
            if (e != null && !dji.pilot.countrycode.model.a.a(e.getLongitude()) && !dji.pilot.countrycode.model.a.a(e.getLatitude())) {
                c.a(this.a).a = e.getLongitude();
                c.a(this.a).b = e.getLatitude();
                c.c(this.a).cancel();
                c.a(this.a, null);
                a.a(c.d(this.a), "【国家码获取（get）】触发点：:开机首次获取手机GPs时触发");
                c.b(this.a);
                return;
            }
            return;
        }
        a.a(c.d(this.a), "尚未获取手机gps,监听手机开机首次获取手机GPs");
    }
}
