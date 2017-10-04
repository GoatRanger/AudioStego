package dji.pilot.dji_groundstation.controller;

import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycStartIoc;
import dji.midware.data.model.P3.DataFlycStartIoc.IOCType;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.R;

class d$9 implements d {
    final /* synthetic */ d a;
    final /* synthetic */ d b;

    d$9(d dVar, d dVar2) {
        this.b = dVar;
        this.a = dVar2;
    }

    public void onSuccess(Object obj) {
        DataFlycStartIoc.getInstance().setMode(IOCType.IOCTripod).start(this.a);
    }

    public void onFailure(a aVar) {
        d.a(this.b, R.string.gsnew_gs_tripod_send_command_failed, -1, aVar.toString());
    }
}
