package dji.pilot.dji_groundstation.controller;

import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycWayPointMissionSwitch;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.a.g;

class d$17 implements d {
    final /* synthetic */ d a;

    d$17(d dVar) {
        this.a = dVar;
    }

    public void onSuccess(Object obj) {
        int result = DataFlycWayPointMissionSwitch.getInstance().getResult();
        if (result == 0) {
            this.a.a(c.g);
            this.a.a(g.EVENT_SMARTMODE_SWITCH_WAYPOINT_STATUS, null);
            return;
        }
        d.a(this.a, dji.pilot.dji_groundstation.a.c.a(result), "");
    }

    public void onFailure(a aVar) {
        d.a(this.a, -1, aVar.toString());
    }
}
