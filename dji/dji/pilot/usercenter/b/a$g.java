package dji.pilot.usercenter.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

final class a$g extends Handler {
    private final WeakReference<a> a;

    public a$g(a aVar) {
        super(Looper.getMainLooper());
        this.a = new WeakReference(aVar);
    }

    public void handleMessage(Message message) {
        a aVar = (a) this.a.get();
        if (aVar != null) {
            switch (message.what) {
                case 0:
                    a.a(aVar, (String) message.obj);
                    return;
                case 1:
                    a.a(aVar, (String) message.obj, message.arg1);
                    return;
                case 2:
                    a.b(aVar, (String) message.obj, message.arg1);
                    return;
                case 4:
                    a.a(aVar);
                    return;
                default:
                    return;
            }
        }
    }
}
