package dji.pilot.playback.litchi;

import android.util.Log;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class DJIPlayBackActivity$5 implements d {
    final /* synthetic */ DJIPlayBackActivity a;

    DJIPlayBackActivity$5(DJIPlayBackActivity dJIPlayBackActivity) {
        this.a = dJIPlayBackActivity;
    }

    public void onSuccess(Object obj) {
        Log.d("test playback", "resume mode succeed!" + dji.pilot.c.d.a);
    }

    public void onFailure(a aVar) {
        Log.e("test playback", "resume mode failed!" + aVar);
    }
}
