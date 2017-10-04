package dji.pilot.dji_groundstation.controller;

import dji.gs.e.b;
import dji.gs.utils.a;
import dji.midware.data.model.P3.DataFlycHotPointMissionDownload;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.a.d$a;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.a.e;

class a$4 implements d {
    final /* synthetic */ DataFlycHotPointMissionDownload a;
    final /* synthetic */ a b;

    a$4(a aVar, DataFlycHotPointMissionDownload dataFlycHotPointMissionDownload) {
        this.b = aVar;
        this.a = dataFlycHotPointMissionDownload;
    }

    public void onSuccess(Object obj) {
        if (this.a.getResult() == 0) {
            dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().p();
            dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().b((this.a.getHotPointLongitude() * 180.0d) / 3.141592653589793d);
            dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().a((this.a.getHotPointLatitude() * 180.0d) / 3.141592653589793d);
            dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().c(this.a.getHotPointRadius());
            dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().a(this.a.getHotPointAngleSpeed());
            d.getInstance().a(d$a.Smart);
            d.getInstance().a(c.d);
            f.getInstance(a.k()).b(a.k());
            e eVar = new e();
            eVar.s = 21;
            eVar.t = a.a(new b(dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().i(), dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().j()));
            dji.thirdparty.a.c.a().e(eVar);
        }
    }

    public void onFailure(dji.midware.data.config.P3.a aVar) {
    }
}
