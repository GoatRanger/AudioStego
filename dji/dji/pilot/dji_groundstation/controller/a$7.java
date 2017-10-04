package dji.pilot.dji_groundstation.controller;

import dji.midware.data.model.P3.DataFlycGetPushWayPointMissionInfo;
import dji.pilot.dji_groundstation.a.d.c;

class a$7 implements Runnable {
    final /* synthetic */ a a;

    a$7(a aVar) {
        this.a = aVar;
    }

    public void run() {
        if (DataFlycGetPushWayPointMissionInfo.getInstance().getMissionType() == 6) {
            d.getInstance().a(c.v);
        }
    }
}
