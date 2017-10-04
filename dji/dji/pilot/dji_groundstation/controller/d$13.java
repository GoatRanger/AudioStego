package dji.pilot.dji_groundstation.controller;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.a.d$a;
import dji.pilot.dji_groundstation.a.d.c;

class d$13 implements d {
    final /* synthetic */ c a;
    final /* synthetic */ d b;

    d$13(d dVar, c cVar) {
        this.b = dVar;
        this.a = cVar;
    }

    public void onSuccess(Object obj) {
        this.b.a(this.a);
        if (d.a(this.b) != null) {
            d.a(this.b).a(this.a, 0);
        }
        d.a(this.b, this.a);
    }

    public void onFailure(a aVar) {
        if (d.a(this.b) != null) {
            d.a(this.b).a(d$a.Smart, -1);
        }
    }
}
