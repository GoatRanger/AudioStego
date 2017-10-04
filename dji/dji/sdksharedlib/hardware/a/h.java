package dji.sdksharedlib.hardware.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

public abstract class h {
    protected static final int b = 0;
    protected static final int c = 1;
    private int a = 200;
    protected Handler d = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ h a;

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.b(message.obj);
                    return;
                case 1:
                    this.a.b();
                    return;
                default:
                    return;
            }
        }
    };
    private List<Object> e = new ArrayList();

    protected abstract void a(List<Object> list);

    private void b(Object obj) {
        this.e.add(obj);
        if (!this.d.hasMessages(1)) {
            this.d.sendEmptyMessageDelayed(1, a());
        }
    }

    private void b() {
        a(new ArrayList(this.e));
        this.e.clear();
    }

    protected void a(Object obj) {
        Message obtainMessage = this.d.obtainMessage(0);
        obtainMessage.obj = obj;
        this.d.sendMessage(obtainMessage);
    }

    protected long a() {
        return (long) this.a;
    }
}
