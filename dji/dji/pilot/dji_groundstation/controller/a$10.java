package dji.pilot.dji_groundstation.controller;

import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycGetFsAction.FS_ACTION;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.fpv.control.DJIGenSettingDataManager;

class a$10 implements d {
    final /* synthetic */ a a;

    a$10(a aVar) {
        this.a = aVar;
    }

    public void onSuccess(Object obj) {
        FS_ACTION find = FS_ACTION.find(dji.midware.data.manager.P3.d.read("g_config.fail_safe.protect_action_0").value.intValue());
        String str = "";
        if (find == FS_ACTION.Landing) {
            str = a.k().getString(R.string.gsnew_gs_rc_signal_lost_landing);
        } else if (find == FS_ACTION.Hover) {
            str = a.k().getString(R.string.gsnew_gs_rc_signal_lost_hover);
        }
        if (find == FS_ACTION.GoHome || find == FS_ACTION.Landing || find == FS_ACTION.Hover) {
            if (!str.isEmpty()) {
                str + "__";
            }
            if (a.b(this.a) > 0) {
                if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    int a = (int) f.a((float) a.b(this.a));
                    str = a.k().getString(R.string.gsnew_return_to_home_attitude) + String.format("%dFT", new Object[]{Integer.valueOf(a)});
                } else {
                    str = a.k().getString(R.string.gsnew_return_to_home_attitude) + String.format("%dM", new Object[]{Integer.valueOf(a.b(this.a))});
                }
                a.a(this.a, str);
            }
        }
    }

    public void onFailure(a aVar) {
    }
}
