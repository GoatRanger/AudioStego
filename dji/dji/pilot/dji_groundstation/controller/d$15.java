package dji.pilot.dji_groundstation.controller;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class d$15 implements d {
    final /* synthetic */ d a;

    d$15(d dVar) {
        this.a = dVar;
    }

    public void onSuccess(Object obj) {
        dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().a(true);
        d.b(this.a);
    }

    public void onFailure(a aVar) {
        this.a.a(8, aVar.toString());
    }
}
