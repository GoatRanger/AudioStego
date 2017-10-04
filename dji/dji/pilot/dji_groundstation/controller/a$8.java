package dji.pilot.dji_groundstation.controller;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.a.d$a;

class a$8 implements d {
    final /* synthetic */ a a;

    a$8(a aVar) {
        this.a = aVar;
    }

    public void onSuccess(Object obj) {
        d.getInstance().c();
        d.getInstance().a(d$a.Normal);
    }

    public void onFailure(a aVar) {
        d.getInstance().c();
        d.getInstance().a(d$a.Normal);
    }
}
