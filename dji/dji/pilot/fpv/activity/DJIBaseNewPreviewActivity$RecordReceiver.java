package dji.pilot.fpv.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import dji.log.DJILogHelper;

public class DJIBaseNewPreviewActivity$RecordReceiver extends BroadcastReceiver {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    public void onReceive(Context context, Intent intent) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "Broadcast onReceive", true, true);
        if (intent.getExtras().getInt("type") == 0 && DJIBaseNewPreviewActivity.a(this.a) != null) {
            DJIBaseNewPreviewActivity.a(this.a).f();
        }
    }

    public DJIBaseNewPreviewActivity$RecordReceiver(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
        Log.d(dJIBaseNewPreviewActivity.TAG, "RecordReceiver");
    }
}
