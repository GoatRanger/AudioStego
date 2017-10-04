package dji.pilot.dji_groundstation.controller;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.a.d$a;
import dji.pilot.dji_groundstation.a.g;

class d$1 implements d {
    final /* synthetic */ d$a a;
    final /* synthetic */ d b;

    d$1(d dVar, d$a dji_pilot_dji_groundstation_a_d_a) {
        this.b = dVar;
        this.a = dji_pilot_dji_groundstation_a_d_a;
    }

    public void onSuccess(Object obj) {
        this.b.b(this.a, 0);
        this.b.a(g.EVENT_FLIGHTMODE_SWITCH_SMART, null);
    }

    public void onFailure(a aVar) {
        this.b.b(this.a, -1);
    }
}
