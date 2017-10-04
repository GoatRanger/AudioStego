package dji.pilot2.simulator;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class d$5 implements d {
    final /* synthetic */ d a;

    d$5(d dVar) {
        this.a = dVar;
    }

    public void onSuccess(Object obj) {
        DJILogHelper.getInstance().LOGE("wbwb", "set success");
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGE("wbwb", "set fail");
    }
}
