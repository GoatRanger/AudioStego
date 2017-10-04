package dji.logic.d;

import android.os.Handler.Callback;
import android.os.Message;

class b$1 implements Callback {
    final /* synthetic */ b a;

    b$1(b bVar) {
        this.a = bVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 4096:
                if (1 != message.arg1) {
                    if (message.arg1 == 0) {
                        b.b(this.a);
                        break;
                    }
                }
                b.a(this.a);
                break;
                break;
            case 4097:
                b.a(this.a);
                break;
        }
        return false;
    }
}
