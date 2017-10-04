package dji.pilot.active;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class d$2 implements d {
    final /* synthetic */ d a;

    d$2(d dVar) {
        this.a = dVar;
    }

    public void onSuccess(Object obj) {
        DJILogHelper.getInstance().LOGE(d.a(this.a), "SetActiveResult success", false, true);
        d.a(this.a, false);
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGE(d.a(this.a), "SetActiveResult " + aVar, false, true);
        d.a(this.a, false);
    }
}
