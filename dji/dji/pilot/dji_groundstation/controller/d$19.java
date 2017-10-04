package dji.pilot.dji_groundstation.controller;

import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.a.c;
import dji.pilot.dji_groundstation.a.g;
import dji.pilot.dji_groundstation.controller.DataMgr.DJIWPCollectionItem;
import dji.pilot.dji_groundstation.controller.DataMgr.DJIWPCollectionItem$WayPoint;

class d$19 implements d {
    final /* synthetic */ DJIWPCollectionItem a;
    final /* synthetic */ d b;

    d$19(d dVar, DJIWPCollectionItem dJIWPCollectionItem) {
        this.b = dVar;
        this.a = dJIWPCollectionItem;
    }

    public void onSuccess(Object obj) {
        int result = DataFlycUploadWayPointMissionMsg.getInstance().getResult();
        if (result != 0) {
            d.a(this.b, c.a(result), "");
            return;
        }
        int i = 0;
        while (i < this.a.getPoints().size()) {
            if (!d.d(this.b)) {
                d.a(this.b, (DJIWPCollectionItem$WayPoint) this.a.getPoints().get(i), i, i == this.a.getPoints().size() + -1);
                i++;
            } else {
                return;
            }
        }
        this.b.a(g.EVENT_SMARTMODE_SWITCH_WAYPOINT_STATUS);
    }

    public void onFailure(a aVar) {
        d.a(this.b, -1, aVar.toString());
    }
}
