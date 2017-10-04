package dji.pilot.dji_groundstation.controller;

import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycStartIoc;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.d.c;

class d$8 implements d {
    final /* synthetic */ d a;

    d$8(d dVar) {
        this.a = dVar;
    }

    public void onSuccess(Object obj) {
        int result = DataFlycStartIoc.getInstance().getResult();
        if (result == 0) {
            this.a.a(c.v);
        } else {
            d.a(this.a, R.string.gsnew_gs_tripod_send_command_failed, dji.pilot.dji_groundstation.a.c.a(result), "");
        }
    }

    public void onFailure(a aVar) {
        d.a(this.a, R.string.gsnew_gs_tripod_send_command_failed, -1, aVar.toString());
    }
}
