package dji.pilot.active;

import android.os.Handler.Callback;
import android.os.Message;

class b$1 implements Callback {
    final /* synthetic */ b a;

    b$1(b bVar) {
        this.a = bVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                b.a(this.a, true);
                b.a(this.a);
                break;
        }
        return false;
    }
}
