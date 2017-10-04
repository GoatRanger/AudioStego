package dji.midware.data.manager.P3;

import android.os.Handler.Callback;
import android.os.Message;
import dji.thirdparty.a.c;

class h$1 implements Callback {
    final /* synthetic */ h a;

    h$1(h hVar) {
        this.a = hVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                if (this.a.isCheck) {
                    this.a.curCameraEvent = o.a;
                    c.a().e(this.a.curCameraEvent);
                    break;
                }
                break;
        }
        return false;
    }
}
