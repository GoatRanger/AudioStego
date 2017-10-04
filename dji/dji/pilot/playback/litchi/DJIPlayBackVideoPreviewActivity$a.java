package dji.pilot.playback.litchi;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.lang.ref.WeakReference;

final class DJIPlayBackVideoPreviewActivity$a extends Handler {
    private final WeakReference<DJIPlayBackVideoPreviewActivity> a;

    public DJIPlayBackVideoPreviewActivity$a(DJIPlayBackVideoPreviewActivity dJIPlayBackVideoPreviewActivity) {
        super(Looper.getMainLooper());
        this.a = new WeakReference(dJIPlayBackVideoPreviewActivity);
    }

    public void handleMessage(Message message) {
        DJIPlayBackVideoPreviewActivity dJIPlayBackVideoPreviewActivity = (DJIPlayBackVideoPreviewActivity) this.a.get();
        if (dJIPlayBackVideoPreviewActivity != null && !dJIPlayBackVideoPreviewActivity.isFinishing()) {
            switch (message.what) {
                case 4096:
                    if (!DJIPlayBackVideoPreviewActivity.x(dJIPlayBackVideoPreviewActivity).a) {
                        DJIPlayBackVideoPreviewActivity.Q(dJIPlayBackVideoPreviewActivity);
                        return;
                    }
                    return;
                case 4097:
                    DJIPlayBackVideoPreviewActivity.R(dJIPlayBackVideoPreviewActivity);
                    return;
                case 8192:
                    if (DJIPlayBackVideoPreviewActivity.c() == 2) {
                        DJIPlayBackVideoPreviewActivity.S(dJIPlayBackVideoPreviewActivity);
                    } else {
                        DJIPlayBackVideoPreviewActivity.a(dJIPlayBackVideoPreviewActivity, DJIPlayBackVideoPreviewActivity.o(dJIPlayBackVideoPreviewActivity).getProgress(), true);
                        dJIPlayBackVideoPreviewActivity.a();
                    }
                    Log.d(dJIPlayBackVideoPreviewActivity.TAG, "seek");
                    return;
                default:
                    return;
            }
        }
    }
}
