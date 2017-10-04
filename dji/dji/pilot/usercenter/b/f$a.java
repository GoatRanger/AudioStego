package dji.pilot.usercenter.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

final class f$a extends Handler {
    private final WeakReference<f> a;

    public f$a(f fVar) {
        super(Looper.getMainLooper());
        this.a = new WeakReference(fVar);
    }

    public void handleMessage(Message message) {
        f fVar = (f) this.a.get();
        if (fVar != null && f.a(fVar)) {
            switch (message.what) {
                case 65536:
                    f.a(fVar, message.arg1, message.arg2, message.obj);
                    return;
                case 65537:
                    f.b(fVar, message.arg1, message.arg2, message.obj);
                    return;
                default:
                    return;
            }
        }
    }
}
