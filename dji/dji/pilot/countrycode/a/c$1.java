package dji.pilot.countrycode.a;

import android.location.Location;
import dji.a.a;

class c$1 implements Runnable {
    final /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    public void run() {
        while (c.a(this.a).a()) {
            Location e = a.getInstance().e();
            if (e != null) {
                c.a(this.a).a = e.getLongitude();
                c.a(this.a).b = e.getLatitude();
                c.b(this.a);
            }
        }
    }
}
