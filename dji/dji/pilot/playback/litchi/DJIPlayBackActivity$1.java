package dji.pilot.playback.litchi;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIPlayBackActivity$1 implements d {
    final /* synthetic */ DJIPlayBackActivity a;

    DJIPlayBackActivity$1(DJIPlayBackActivity dJIPlayBackActivity) {
        this.a = dJIPlayBackActivity;
    }

    public void onSuccess(Object obj) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "dm368SetParams success");
    }

    public void onFailure(a aVar) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "dm368SetParams " + aVar);
    }
}
