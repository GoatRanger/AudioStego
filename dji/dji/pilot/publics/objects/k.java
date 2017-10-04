package dji.pilot.publics.objects;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

public final class k extends Handler {
    private final WeakReference<a> a;
    private Callback b;

    public k(a aVar, Callback callback) {
        this(Looper.getMainLooper(), aVar, callback);
    }

    public k(Looper looper, a aVar, Callback callback) {
        super(looper);
        this.b = null;
        this.a = new WeakReference(aVar);
        this.b = callback;
    }

    public void handleMessage(Message message) {
        a aVar = (a) this.a.get();
        if (this.b != null && aVar != null && !aVar.isFinished()) {
            this.b.handleMessage(message);
        }
    }
}
