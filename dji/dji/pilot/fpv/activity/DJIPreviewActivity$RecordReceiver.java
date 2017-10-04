package dji.pilot.fpv.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import dji.log.DJILogHelper;

public class DJIPreviewActivity$RecordReceiver extends BroadcastReceiver {
    final /* synthetic */ DJIPreviewActivity a;

    public void onReceive(Context context, Intent intent) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "Broadcast onReceive", true, true);
        if (intent.getExtras().getInt("type") == 0 && DJIPreviewActivity.a(this.a) != null) {
            DJIPreviewActivity.a(this.a).f();
        }
    }

    public DJIPreviewActivity$RecordReceiver(DJIPreviewActivity dJIPreviewActivity) {
        this.a = dJIPreviewActivity;
        Log.d(dJIPreviewActivity.TAG, "RecordReceiver");
    }
}
