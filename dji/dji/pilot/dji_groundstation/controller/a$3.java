package dji.pilot.dji_groundstation.controller;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class a$3 implements d {
    final /* synthetic */ a a;

    a$3(a aVar) {
        this.a = aVar;
    }

    public void onSuccess(Object obj) {
        a.a(this.a);
    }

    public void onFailure(a aVar) {
        a.a(this.a);
    }
}
