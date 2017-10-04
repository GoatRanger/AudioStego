package dji.pilot.fpv.camera.focus.a;

import android.os.Handler.Callback;
import android.os.Message;

class a$1 implements Callback {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 257:
                a.a(this.a);
                break;
        }
        return false;
    }
}
