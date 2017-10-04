package dji.pilot.publics.c;

import android.os.Handler.Callback;
import android.os.Message;
import dji.thirdparty.a.c;

class c$1 implements Callback {
    final /* synthetic */ c a;

    c$1(c cVar) {
        this.a = cVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                this.a.b();
                if (c.a(this.a) != c.b(this.a)) {
                    c.a(this.a, c.b(this.a));
                    c.a().e(c.a(this.a));
                }
                c.d(this.a).sendEmptyMessageDelayed(0, (long) c.c(this.a));
                break;
            case 10:
                c.e(this.a);
                break;
            case 20:
                c.f(this.a);
                break;
        }
        return false;
    }
}
