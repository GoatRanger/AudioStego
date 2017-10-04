package dji.pilot.fpv.control.a;

import android.os.Handler;
import android.os.Message;
import dji.midware.data.model.P3.DataFlycGetPushAgpsStatus;
import dji.midware.data.model.P3.DataFlycSendAgpsData;

class a$a extends Handler {
    final /* synthetic */ a a;

    a$a(a aVar) {
        this.a = aVar;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                a.a(this.a, "case SEND_START:");
                if (a.a(this.a) == a$b.INIT) {
                    a.a(this.a, (short) 0, (byte) 1, (byte) 1, a.b(this.a));
                    return;
                }
                return;
            case 1:
                a.a(this.a, "case SEND_SUCCESS: mSendStatus " + a.a(this.a));
                switch (a$2.a[a.a(this.a).ordinal()]) {
                    case 1:
                        int f = a.c().f();
                        a.a(this.a, (short) (f / a.d()));
                        if (!(f % a.d() == 0 || f == 0)) {
                            a.c(this.a);
                        }
                        byte[] b = a.b(this.a, (short) 0);
                        a.a(this.a, a$b.NOR_DATA);
                        a.a(this.a, (short) 0, (byte) 1, (byte) 1, b);
                        a.a(this.a, "case SEND_SUCCESS:dataLen=" + f + " mPackCount=" + a.d(this.a));
                        return;
                    case 2:
                        short shortValue = ((DataFlycSendAgpsData) message.obj).a().shortValue();
                        if (shortValue < (short) 0) {
                            shortValue = (short) 0;
                        }
                        if (shortValue < a.d(this.a)) {
                            a.a(this.a, shortValue, (byte) 0, (byte) 0, a.b(this.a, shortValue));
                            return;
                        }
                        a.a(this.a, a$b.HASH_DATA);
                        a.a(this.a, shortValue, (byte) 0, (byte) 1, a.a(this.a, a.c().f(), a.c().e()));
                        return;
                    case 3:
                        a.e(this.a);
                        a.a(this.a, a$b.IDLE);
                        return;
                    default:
                        return;
                }
            case 2:
                a.a(this.a, "case SEND_FAIL: mSendStatus " + a.a(this.a));
                a.e(this.a);
                a.a(this.a, a$b.IDLE);
                return;
            case 3:
                this.a.onEventBackgroundThread(DataFlycGetPushAgpsStatus.getInstance());
                return;
            default:
                return;
        }
    }
}
