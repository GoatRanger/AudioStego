package dji.pilot.publics.control;

import android.os.Handler.Callback;
import android.os.Message;
import dji.log.DJILogHelper;

class a$1 implements Callback {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                if (a.a(this.a) == null) {
                    this.a.c();
                }
                a.b(this.a);
                break;
            case 1:
                this.a.r();
                break;
            case 10:
                a.c(this.a);
                break;
            case 11:
                DJILogHelper.getInstance().LOGD("DJIUpgradeControl", "date MSG_INIT " + a.b());
                a.d(this.a);
                break;
        }
        return false;
    }
}
