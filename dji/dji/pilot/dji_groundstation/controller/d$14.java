package dji.pilot.dji_groundstation.controller;

import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycNavigationSwitch;
import dji.midware.data.model.P3.DataFlycNavigationSwitch.GS_COMMAND;
import dji.midware.e.d;

class d$14 implements d {
    final /* synthetic */ boolean a;
    final /* synthetic */ int b;
    final /* synthetic */ d c;
    final /* synthetic */ d d;

    d$14(d dVar, boolean z, int i, d dVar2) {
        this.d = dVar;
        this.a = z;
        this.b = i;
        this.c = dVar2;
    }

    public void onSuccess(Object obj) {
        new DataFlycNavigationSwitch().setCommand(this.a ? GS_COMMAND.OPEN_GROUND_STATION : GS_COMMAND.CLOSE_GROUND_STATION).setRetryTimes(this.b).start(this.c);
    }

    public void onFailure(a aVar) {
        if (this.c != null) {
            this.c.onFailure(aVar);
        }
    }
}
