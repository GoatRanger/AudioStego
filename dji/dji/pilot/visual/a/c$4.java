package dji.pilot.visual.a;

import android.os.Handler.Callback;
import android.os.Message;

class c$4 implements Callback {
    final /* synthetic */ c a;

    c$4(c cVar) {
        this.a = cVar;
    }

    public boolean handleMessage(Message message) {
        if (768 <= message.what && message.what <= 775 && message.arg1 == 0) {
            c.a(this.a, true);
        }
        switch (message.what) {
            case 256:
                c.a(this.a).e();
                break;
            case 257:
                c.b(this.a).e();
                break;
            case 768:
                c.e(this.a);
                break;
            case 769:
                c.j(this.a);
                break;
            case 770:
                c.i(this.a);
                break;
            case 771:
                c.h(this.a);
                break;
            case 772:
                c.d(this.a);
                break;
            case 773:
                c.f(this.a);
                break;
            case 774:
                c.c(this.a);
                break;
            case 775:
                c.g(this.a);
                break;
            case 1024:
                if (1 == message.arg1) {
                    c.b(this.a, 1 == message.arg2);
                    break;
                }
                break;
        }
        return true;
    }
}
