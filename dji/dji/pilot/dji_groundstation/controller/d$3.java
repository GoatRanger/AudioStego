package dji.pilot.dji_groundstation.controller;

import dji.gs.e.b;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycNavigationSwitch;
import dji.midware.data.model.P3.DataFlycStartFollowMeWithInfo;
import dji.midware.data.model.P3.DataFlycStartFollowMeWithInfo.FOLLOWMODE;
import dji.midware.data.model.P3.DataFlycStartFollowMeWithInfo.YAWMODE;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.dji_groundstation.a.g;
import dji.pilot.fpv.d.c$i;
import dji.pilot.fpv.d.e;

class d$3 implements d {
    final /* synthetic */ b a;
    final /* synthetic */ d b;

    d$3(d dVar, b bVar) {
        this.b = dVar;
        this.a = bVar;
    }

    public void onSuccess(Object obj) {
        this.b.a(true, 2, new d(this) {
            final /* synthetic */ d$3 a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                if (DataFlycNavigationSwitch.getInstance().getResult() == 0) {
                    DataFlycStartFollowMeWithInfo.getInstance().setFollowMode(FOLLOWMODE.Relative_mode);
                    DataFlycStartFollowMeWithInfo.getInstance().setYawMode(YAWMODE.Use_Remote_Controll);
                    DataFlycStartFollowMeWithInfo.getInstance().setAltitude((short) 0);
                    DataFlycStartFollowMeWithInfo.getInstance().setHeading((short) 0);
                    DataFlycStartFollowMeWithInfo.getInstance().setSensitivity(0);
                    if (this.a.a != null) {
                        DataFlycStartFollowMeWithInfo.getInstance().setUserLatitude(f.b(this.a.a.b + d.e(this.a.b)));
                        DataFlycStartFollowMeWithInfo.getInstance().setUserLongitude(f.b(this.a.a.c + d.f(this.a.b)));
                        DataFlycStartFollowMeWithInfo.getInstance().start(new d(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                int result = DataFlycStartFollowMeWithInfo.getInstance().getResult();
                                if (result == 0) {
                                    e.c(c$i.e);
                                    this.a.a.b.a(c.n);
                                    this.a.a.b.a(g.EVENT_SMARTMODE_SWITCH_FOLLOWME_STATUS, null);
                                    dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().b(new d(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void onSuccess(Object obj) {
                                        }

                                        public void onFailure(a aVar) {
                                        }
                                    });
                                    return;
                                }
                                dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().j();
                                d.a(this.a.a.b, R.string.gsnew_gs_follow_me_send_command_failed, dji.pilot.dji_groundstation.a.c.a(result), "");
                            }

                            public void onFailure(a aVar) {
                                dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().j();
                                d.a(this.a.a.b, R.string.gsnew_gs_follow_me_send_command_failed, -1, aVar.toString());
                            }
                        });
                        return;
                    }
                    dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().j();
                    d.a(this.a.b, R.string.gsnew_gs_follow_me_send_command_failed, R.string.gsnew_gs_follow_me_can_not_get_user_location, "");
                }
            }

            public void onFailure(a aVar) {
                dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().j();
                d.a(this.a.b, R.string.gsnew_gs_follow_me_send_command_failed, -1, aVar.toString());
            }
        });
    }

    public void onFailure(a aVar) {
        dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().j();
        d.a(this.b, R.string.gsnew_gs_follow_me_send_command_failed, -1, aVar.toString());
    }
}
