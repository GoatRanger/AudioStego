package dji.pilot.flyforbid;

import android.os.Handler.Callback;
import android.os.Message;

class a$6 implements Callback {
    final /* synthetic */ a a;

    a$6(a aVar) {
        this.a = aVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                a.b(this.a, a.g(this.a));
                break;
            case 2:
                a.k(this.a);
                break;
            case 3:
                this.a.e();
                break;
        }
        return false;
    }
}
