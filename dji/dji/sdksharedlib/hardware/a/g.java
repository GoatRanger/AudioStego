package dji.sdksharedlib.hardware.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import dji.midware.data.config.P3.a;
import dji.midware.e.d;

public abstract class g {
    private static final String e = "DJISDKCacheSubscription";
    private static final int f = 1000;
    private static HandlerThread g;
    private static Handler h;
    protected d a = new d(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
            this.a.h();
        }

        public void onFailure(a aVar) {
        }
    };
    protected Runnable b = new Runnable(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.a();
            this.a.d = true;
        }
    };
    protected Runnable c = new Runnable(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.b();
            this.a.d = false;
        }
    };
    protected boolean d;

    protected abstract void a();

    protected abstract void b();

    public g() {
        g = new HandlerThread(e);
        g.start();
        h = new Handler(g.getLooper());
        Log.d("SDR", "ss");
    }

    public void c() {
        h = null;
        if (g != null) {
            g.quit();
            g = null;
        }
    }

    private void g() {
        if (h != null) {
            h.removeCallbacks(this.c);
            h.postDelayed(this.c, 10000);
        }
    }

    public void d() {
        if (this.d) {
            g();
        } else if (h != null) {
            h.post(this.b);
        }
    }

    public void e() {
        if (h != null) {
            h.removeCallbacks(this.c);
        }
        b();
    }

    public boolean f() {
        return this.d;
    }

    private void h() {
        if (h != null) {
            h.postDelayed(this.c, 10000);
            this.d = true;
        }
    }
}
