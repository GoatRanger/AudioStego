package dji.pilot.fpv.control.a;

import android.os.Handler;
import android.os.Message;

class b$2 extends Handler {
    final /* synthetic */ b a;

    b$2(b bVar) {
        this.a = bVar;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                b.d(this.a, (String) message.obj);
                return;
            case 1:
                if (b.b(this.a)) {
                    this.a.c();
                    return;
                }
                b.a(this.a, false);
                b.d(this.a, 0);
                return;
            default:
                return;
        }
    }
}
