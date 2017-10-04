package dji.publics.DJIObject;

import android.os.Handler.Callback;
import android.os.Message;

class DJIBaseActivityForVirtualKey$1 implements Callback {
    final /* synthetic */ DJIBaseActivityForVirtualKey a;

    DJIBaseActivityForVirtualKey$1(DJIBaseActivityForVirtualKey dJIBaseActivityForVirtualKey) {
        this.a = dJIBaseActivityForVirtualKey;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                this.a.enter(this.a.window);
                break;
        }
        return false;
    }
}
