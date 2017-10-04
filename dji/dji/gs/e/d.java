package dji.gs.e;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

public abstract class d extends Handler {
    private WeakReference<Context> a;

    public abstract void a(Message message);

    public d(Context context) {
        this.a = new WeakReference(context);
    }

    public void handleMessage(Message message) {
        if (((Context) this.a.get()) != null) {
            a(message);
        }
    }
}
