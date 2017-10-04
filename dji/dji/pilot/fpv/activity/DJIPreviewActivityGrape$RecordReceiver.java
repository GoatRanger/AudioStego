package dji.pilot.fpv.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import dji.log.DJILogHelper;

public class DJIPreviewActivityGrape$RecordReceiver extends BroadcastReceiver {
    final /* synthetic */ DJIPreviewActivityGrape a;

    public void onReceive(Context context, Intent intent) {
        DJILogHelper.getInstance().LOGD(this.a.TAG, "Broadcast onReceive", true, true);
        if (intent.getExtras().getInt("type") == 0 && DJIPreviewActivityGrape.a(this.a) != null) {
            DJIPreviewActivityGrape.a(this.a).f();
        }
    }

    public DJIPreviewActivityGrape$RecordReceiver(DJIPreviewActivityGrape dJIPreviewActivityGrape) {
        this.a = dJIPreviewActivityGrape;
        Log.d(dJIPreviewActivityGrape.TAG, "RecordReceiver");
    }
}
