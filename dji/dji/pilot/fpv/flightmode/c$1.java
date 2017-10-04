package dji.pilot.fpv.flightmode;

import dji.pilot.R;
import dji.pilot.dji_groundstation.a.e;
import dji.pilot.visual.a.c;
import dji.pilot.visual.a.g$e;
import dji.pilot.visual.a.g$f;

class c$1 implements Runnable {
    final /* synthetic */ c$b a;
    final /* synthetic */ e b;
    final /* synthetic */ c c;

    c$1(c cVar, c$b dji_pilot_fpv_flightmode_c_b, e eVar) {
        this.c = cVar;
        this.a = dji_pilot_fpv_flightmode_c_b;
        this.b = eVar;
    }

    public void run() {
        b.a(c.a(this.c), new Runnable(this) {
            final /* synthetic */ c$1 a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.c.a(this.a.a);
                c.getInstance().a(g$f.WORKING);
                c.getInstance().a(g$e.TRACK_MODE);
                this.a.b.t = new Integer(R.drawable.mini_selfie);
                dji.thirdparty.a.c.a().e(this.a.b);
            }
        });
    }
}
