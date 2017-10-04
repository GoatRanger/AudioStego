package dji.logic.c;

import android.os.Handler.Callback;
import android.os.Message;
import dji.logic.c.b.a;
import dji.thirdparty.a.c;

class b$1 implements Callback {
    final /* synthetic */ b a;

    b$1(b bVar) {
        this.a = bVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                c.a().e(a.b);
                break;
        }
        return false;
    }
}
