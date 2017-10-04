package dji.pilot.dji_groundstation.controller;

import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycNavigationSwitch;
import dji.midware.data.model.P3.DataFlycStartNoeMission;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.a.g;
import dji.pilot.fpv.d.c$i;
import dji.pilot.fpv.d.e;

class d$10 implements d {
    final /* synthetic */ d a;

    d$10(d dVar) {
        this.a = dVar;
    }

    public void onSuccess(Object obj) {
        if (DataFlycNavigationSwitch.getInstance().getResult() == 0) {
            DataFlycStartNoeMission.getInstance().a(10.0f).start(new d(this) {
                final /* synthetic */ d$10 a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    if (DataFlycStartNoeMission.getInstance().a() == 0) {
                        e.c(c$i.f);
                        this.a.a.a(c.u);
                        this.a.a.a(g.EVENT_SMARTMODE_SWITCH_TERRAINTRACKING_STATUS, null);
                        return;
                    }
                    d.a(this.a.a, R.string.gsnew_gs_terrain_lock_send_command_fail, dji.pilot.dji_groundstation.a.c.a(DataFlycStartNoeMission.getInstance().a()), "");
                }

                public void onFailure(a aVar) {
                    d.a(this.a.a, R.string.gsnew_gs_terrain_lock_send_command_fail, -1, aVar.toString());
                }
            });
        }
    }

    public void onFailure(a aVar) {
        d.a(this.a, R.string.gsnew_gs_terrain_lock_send_command_fail, -1, aVar.toString());
    }
}
