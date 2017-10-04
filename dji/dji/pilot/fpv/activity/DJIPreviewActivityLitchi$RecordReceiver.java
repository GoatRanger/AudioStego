package dji.pilot.fpv.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import dji.log.DJILogHelper;

public class DJIPreviewActivityLitchi$RecordReceiver extends BroadcastReceiver {
    final /* synthetic */ DJIPreviewActivityLitchi a;

    public void onReceive(Context context, Intent intent) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "Broadcast onReceive", true, true);
        if (intent.getExtras().getInt("type") == 0 && DJIPreviewActivityLitchi.a(this.a) != null) {
            DJIPreviewActivityLitchi.a(this.a).f();
        }
    }

    public DJIPreviewActivityLitchi$RecordReceiver(DJIPreviewActivityLitchi dJIPreviewActivityLitchi) {
        this.a = dJIPreviewActivityLitchi;
        Log.d(dJIPreviewActivityLitchi.TAG, "RecordReceiver");
    }
}
