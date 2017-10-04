package dji.midware.f;

import android.os.Handler.Callback;
import android.os.Message;

class a$1 implements Callback {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public boolean handleMessage(Message message) {
        this.a.a();
        return false;
    }
}
