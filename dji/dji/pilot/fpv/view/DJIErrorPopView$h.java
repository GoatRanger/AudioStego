package dji.pilot.fpv.view;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

final class DJIErrorPopView$h extends Handler {
    private final WeakReference<DJIErrorPopView> a;

    public DJIErrorPopView$h(DJIErrorPopView dJIErrorPopView) {
        super(Looper.getMainLooper());
        this.a = new WeakReference(dJIErrorPopView);
    }

    public void handleMessage(Message message) {
        DJIErrorPopView dJIErrorPopView = (DJIErrorPopView) this.a.get();
        if (dJIErrorPopView != null) {
            switch (message.what) {
                case 4096:
                    DJIErrorPopView.c(dJIErrorPopView, message.arg1);
                    return;
                default:
                    return;
            }
        }
    }
}
