package dji.pilot.dji_groundstation.controller;

import dji.midware.e.d;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.a;
import dji.pilot.dji_groundstation.a.d$a;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.a.g;

class a$6 implements d {
    final /* synthetic */ a a;

    a$6(a aVar) {
        this.a = aVar;
    }

    public void onSuccess(Object obj) {
        d.getInstance().a(d$a.None);
        d.getInstance().a(c.a);
        a aVar = new a();
        aVar.a = R.string.gsnew_gs_follow_me_notify_dialog_title;
        aVar.b = R.string.gsnew_gs_follow_me_notify_dialog_desc;
        aVar.d = 270;
        aVar.e = 280;
        aVar.k = false;
        aVar.j = R.string.gsnew_gs_failed_dialog_ok;
        aVar.i = "gs://flightmode/main";
        d.getInstance().a(g.EVENT_SMARTMODE_ERROR_PUSH_TO_CONFIRM_DIALOG, aVar);
    }

    public void onFailure(dji.midware.data.config.P3.a aVar) {
    }
}
