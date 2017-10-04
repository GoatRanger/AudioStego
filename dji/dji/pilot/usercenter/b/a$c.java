package dji.pilot.usercenter.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

final class a$c extends Handler {
    private final WeakReference<a> a;

    public a$c(a aVar, Looper looper) {
        super(looper);
        this.a = new WeakReference(aVar);
    }

    public void handleMessage(Message message) {
        a aVar = (a) this.a.get();
        if (aVar != null) {
            switch (message.what) {
                case 16:
                    a.a(aVar, (a$d) message.obj);
                    return;
                case 17:
                    a.a(aVar, message.obj);
                    return;
                default:
                    return;
            }
        }
    }
}
