package dji.pilot.playback.litchi;

import android.content.Intent;
import dji.log.DJILogHelper;

class DJIPlayBackActivity$7 implements Runnable {
    final /* synthetic */ Intent a;
    final /* synthetic */ DJIPlayBackActivity b;

    DJIPlayBackActivity$7(DJIPlayBackActivity dJIPlayBackActivity, Intent intent) {
        this.b = dJIPlayBackActivity;
        this.a = intent;
    }

    public void run() {
        DJILogHelper.getInstance().LOGD("", "videoresult", false, true);
        this.b.b().a(this.a.getExtras().getInt("Pos"));
    }
}
