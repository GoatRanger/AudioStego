package dji.pilot.battery.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import dji.pilot.publics.e.a;
import java.lang.ref.WeakReference;

final class a$a extends Handler {
    private WeakReference<a> a = null;

    public a$a(a aVar) {
        super(Looper.getMainLooper());
        this.a = new WeakReference(aVar);
    }

    public void handleMessage(Message message) {
        a aVar = (a) this.a.get();
        if (aVar != null) {
            switch (message.what) {
                case 4096:
                    if (a.b()) {
                        a.e(aVar);
                        return;
                    } else {
                        a.f(aVar);
                        return;
                    }
                case 4097:
                    a.g(aVar);
                    return;
                case 4098:
                    a.a(aVar, message.arg1, message.arg2);
                    return;
                case 4099:
                    a.b(aVar, message.arg1, message.arg2);
                    return;
                case 4100:
                    a.c(aVar, message.arg1, message.arg2);
                    return;
                case 4101:
                    a.d(aVar, message.arg1, message.arg2);
                    return;
                case 4102:
                    a.a(aVar, true);
                    return;
                case 4103:
                    a.a(aVar, false);
                    return;
                case 4104:
                    a.b(aVar, true);
                    return;
                case 4105:
                    a.b(aVar, false);
                    return;
                case 4106:
                    a.c(aVar, true);
                    return;
                case 4107:
                    a.c(aVar, false);
                    return;
                case 4108:
                    a.d(aVar, true);
                    return;
                case 4109:
                    a.d(aVar, false);
                    return;
                default:
                    return;
            }
        }
    }
}
