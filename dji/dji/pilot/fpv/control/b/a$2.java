package dji.pilot.fpv.control.b;

import android.os.Handler;
import android.os.Message;
import com.dji.a.a;

class a$2 extends Handler {
    final /* synthetic */ a a;

    a$2(a aVar) {
        this.a = aVar;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 2:
                a.a("here_map_sn_report", a.a(this.a, a.b(this.a).getSN()), true);
                return;
            case 3:
                if (a.c(this.a) < 3) {
                    a.a(this.a).sendEmptyMessageDelayed(4, 10000);
                    a.d(this.a);
                    return;
                }
                a.a(this.a, 0);
                a.a("here_map_sn_report", a.a(this.a, "sn_null_connect"), true);
                return;
            case 4:
                a.e(this.a);
                return;
            default:
                return;
        }
    }
}
