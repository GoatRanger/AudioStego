package dji.pilot.dji_groundstation.controller;

import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataFlycStartIoc;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.c;
import dji.pilot.dji_groundstation.a.g;
import dji.pilot.dji_groundstation.controller.DataMgr.b;

class d$4 implements d {
    final /* synthetic */ d a;

    d$4(d dVar) {
        this.a = dVar;
    }

    public void onSuccess(Object obj) {
        int result = DataFlycStartIoc.getInstance().getResult();
        if (result != 0) {
            d.a(this.a, R.string.gsnew_gs_course_lock_send_command_failed, c.a(result), "");
        } else if (i.getInstance().c().value() == ProductType.A2.value()) {
            this.a.a(dji.pilot.dji_groundstation.a.d.c.p);
            b.getInstance().b(true);
            this.a.a(g.EVENT_SMARTMODE_SWITCH_COURSELOCK_STATUS, null);
        } else {
            DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.HOMEPOINT_LOC);
            DataFlycFunctionControl.getInstance().start(new d(this) {
                final /* synthetic */ d$4 a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.a.a(dji.pilot.dji_groundstation.a.d.c.p);
                    b.getInstance().b(true);
                    this.a.a.a(g.EVENT_SMARTMODE_SWITCH_COURSELOCK_STATUS, null);
                }

                public void onFailure(a aVar) {
                    if (this.a.a.b().ordinal() == dji.pilot.dji_groundstation.a.d.c.p.ordinal()) {
                        b.getInstance().b(true);
                        this.a.a.a(g.EVENT_SMARTMODE_SWITCH_COURSELOCK_STATUS, null);
                        return;
                    }
                    d.a(this.a.a, R.string.gsnew_gs_course_lock_send_command_failed, -1, aVar.toString());
                }
            });
        }
    }

    public void onFailure(a aVar) {
        d.a(this.a, R.string.gsnew_gs_course_lock_send_command_failed, -1, aVar.toString());
    }
}
