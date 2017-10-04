package dji.pilot.dji_groundstation.controller;

import dji.midware.data.model.P3.DataFlycNavigationSwitch;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo.CAMERA_DIR;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo.ROTATION_DIR;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo.TO_START_POINT_MODE;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.a;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.dji_groundstation.a.g;

class d$16 implements d {
    final /* synthetic */ ROTATION_DIR a;
    final /* synthetic */ d b;

    d$16(d dVar, ROTATION_DIR rotation_dir) {
        this.b = dVar;
        this.a = rotation_dir;
    }

    public void onSuccess(Object obj) {
        if (DataFlycNavigationSwitch.getInstance().getResult() == 0) {
            DataFlycStartHotPointMissionWithInfo instance = DataFlycStartHotPointMissionWithInfo.getInstance();
            instance.setCameraDir(CAMERA_DIR.Forwards_Hot_Point);
            instance.setRotationDir(this.a);
            instance.setToStartPointMode(TO_START_POINT_MODE.Initi);
            instance.setVelocity(Math.abs(dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().k()));
            instance.setAltitude(f.a());
            instance.setLatitude((dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().i() * 3.141592653589793d) / 180.0d);
            instance.setLongitude((dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().j() * 3.141592653589793d) / 180.0d);
            instance.setRadious(dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().m());
            instance.start(new d(this) {
                final /* synthetic */ d$16 a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    int result = DataFlycStartHotPointMissionWithInfo.getInstance().getResult();
                    if (result == 0) {
                        this.a.b.a(g.EVENT_SMARTMODE_SWITCH_POI_STATUS, null);
                        this.a.b.a(c.d);
                        return;
                    }
                    a aVar = new a();
                    aVar.a = R.string.gsnew_gs_point_of_insterest_set_auto_flight_failed;
                    aVar.b = dji.pilot.dji_groundstation.a.c.a(result);
                    aVar.d = 250;
                    aVar.e = 270;
                    aVar.k = false;
                    aVar.j = R.string.gsnew_gs_failed_dialog_ok;
                    aVar.i = "gs://smartmode/poi";
                    this.a.b.a(g.EVENT_SMARTMODE_ERROR_PUSH_TO_CONFIRM_DIALOG, aVar);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    d.a(this.a.b, R.string.gsnew_gs_point_of_insterest_set_auto_flight_failed, -1, aVar.toString(), "gs://smartmode/poi");
                }
            });
        }
    }

    public void onFailure(dji.midware.data.config.P3.a aVar) {
    }
}
