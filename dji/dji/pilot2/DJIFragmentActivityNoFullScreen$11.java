package dji.pilot2;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIFragmentActivityNoFullScreen$11 implements d {
    final /* synthetic */ DJIFragmentActivityNoFullScreen a;

    DJIFragmentActivityNoFullScreen$11(DJIFragmentActivityNoFullScreen dJIFragmentActivityNoFullScreen) {
        this.a = dJIFragmentActivityNoFullScreen;
    }

    public void onSuccess(Object obj) {
        DJILogHelper.getInstance().LOGD("", "ControlCmd start success", false, true);
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD("", "ControlCmd start " + aVar, false, true);
    }
}
