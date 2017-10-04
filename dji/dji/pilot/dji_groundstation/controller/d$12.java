package dji.pilot.dji_groundstation.controller;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.a.d$a;
import dji.pilot.dji_groundstation.a.e;
import dji.thirdparty.a.c;

class d$12 implements d {
    final /* synthetic */ d$a a;
    final /* synthetic */ d b;

    d$12(d dVar, d$a dji_pilot_dji_groundstation_a_d_a) {
        this.b = dVar;
        this.a = dji_pilot_dji_groundstation_a_d_a;
    }

    public void onSuccess(Object obj) {
        if (d.a(this.b) != null) {
            e eVar = new e();
            eVar.s = 1;
            c.a().e(eVar);
            d.a(this.b).a(this.a, 0);
        }
    }

    public void onFailure(a aVar) {
        if (d.a(this.b) != null) {
            d.a(this.b).a(this.a, -1);
        }
    }
}
