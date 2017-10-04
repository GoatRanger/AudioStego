package dji.pilot.fpv.control;

import android.os.Handler.Callback;
import android.os.Message;

class v$3 implements Callback {
    final /* synthetic */ v a;

    v$3(v vVar) {
        this.a = vVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1001:
                v.b(this.a, true);
                break;
        }
        return false;
    }
}
