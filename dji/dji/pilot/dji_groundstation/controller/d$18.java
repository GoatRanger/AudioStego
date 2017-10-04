package dji.pilot.dji_groundstation.controller;

import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycNavigationSwitch;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.a.c;
import dji.pilot.dji_groundstation.a.e;

class d$18 implements d {
    final /* synthetic */ d a;

    d$18(d dVar) {
        this.a = dVar;
    }

    public void onSuccess(Object obj) {
        if (DataFlycNavigationSwitch.getInstance().getResult() != 0) {
            d.a(this.a, c.a(DataFlycNavigationSwitch.getInstance().getResult()), "");
            e eVar = new e();
            eVar.s = 1;
            dji.thirdparty.a.c.a().e(eVar);
            return;
        }
        d.c(this.a);
    }

    public void onFailure(a aVar) {
        d.a(this.a, -1, aVar.toString());
    }
}
