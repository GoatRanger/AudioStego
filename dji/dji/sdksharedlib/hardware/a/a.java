package dji.sdksharedlib.hardware.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import dji.common.error.DJIError;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.g;
import java.util.concurrent.ConcurrentHashMap;

public class a {
    private static final String a = "DJISDKCacheAutoGetterVerifier";
    private static final int i = 0;
    private static final int j = 1000;
    private static final int k = 1;
    private static final int l = 2;
    private static final int m = 3;
    private dji.sdksharedlib.hardware.a b;
    private g c;
    private dji.sdksharedlib.c.g.a d;
    private ConcurrentHashMap<c, b> e;
    private b f;
    private HandlerThread g;
    private a h;

    private class a extends Handler {
        final /* synthetic */ a a;

        public a(a aVar, Looper looper) {
            this.a = aVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.a((c) message.obj);
                    return;
                case 2:
                    this.a.b((c) message.obj);
                    return;
                case 3:
                    this.a.d();
                    return;
                default:
                    return;
            }
        }
    }

    public class b implements Runnable {
        final /* synthetic */ a a;
        private c b;
        private int c = 0;
        private dji.sdksharedlib.c.c d = new dji.sdksharedlib.c.c(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(dji.sdksharedlib.d.a aVar) {
            }

            public void a(DJIError dJIError) {
            }
        };

        public b(a aVar, c cVar) {
            this.a = aVar;
            this.b = cVar;
        }

        public void a(int i) {
            this.c = i;
        }

        public int a() {
            return this.c;
        }

        public void run() {
            this.a.b.a(this.b, null);
        }

        public int hashCode() {
            if (this.b != null) {
                return this.b.hashCode();
            }
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (obj instanceof b) {
                return this.b.equals(((b) obj).b);
            }
            return super.equals(obj);
        }
    }

    public void a(dji.sdksharedlib.hardware.a aVar, g gVar) {
        this.b = aVar;
        this.c = gVar;
        this.e = new ConcurrentHashMap();
        this.g = new HandlerThread("dji_sdk_cache_timer_for_repeat_get");
        this.g.start();
        this.h = new a(this, this.g.getLooper());
        this.f = new b();
        this.f.a(this.g.getLooper());
        this.d = new dji.sdksharedlib.c.g.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(c cVar) {
                if (cVar != null) {
                    Message obtainMessage = this.a.h.obtainMessage(1);
                    obtainMessage.obj = cVar;
                    this.a.h.sendMessage(obtainMessage);
                }
            }

            public void b(c cVar) {
                if (cVar != null) {
                    Message obtainMessage = this.a.h.obtainMessage(2);
                    obtainMessage.obj = cVar;
                    this.a.h.sendMessage(obtainMessage);
                }
            }
        };
        gVar.a(this.d);
        dji.thirdparty.a.c.a().a(this);
    }

    public void onEventBackgroundThread(dji.sdksharedlib.hardware.a.a aVar) {
        Message obtainMessage = this.h.obtainMessage(3);
        this.h.removeMessages(3);
        this.h.sendMessageDelayed(obtainMessage, 1000);
    }

    public void a() {
        if (this.f != null) {
            this.f.b();
            this.f = null;
        }
        this.h = null;
        if (this.g != null) {
            this.g.quit();
            this.g = null;
        }
        dji.thirdparty.a.c.a().d(this);
    }

    private void a(c cVar) {
        if (!this.e.containsKey(cVar)) {
            b bVar = new b(this, cVar);
            this.e.put(cVar, bVar);
            bVar.a(this.b.a(cVar));
        }
        Runnable runnable = (b) this.e.get(cVar);
        if (runnable.a() != 0) {
            this.f.a(runnable, runnable.a());
        }
    }

    private void b(c cVar) {
        if (this.c.a(cVar) == 0 && this.e.containsKey(cVar)) {
            Runnable runnable = (b) this.e.get(cVar);
            this.f.b(runnable, runnable.a());
            this.e.remove(cVar);
        }
    }

    private void d() {
        this.f.a();
        for (c cVar : this.e.keySet()) {
            Runnable runnable = (b) this.e.get(cVar);
            runnable.a(this.b.a(cVar));
            if (runnable.a() != 0) {
                this.f.a(runnable, runnable.a());
            }
        }
    }

    public int b() {
        return this.e.size();
    }

    public int c() {
        return this.f.c();
    }

    public int a(int i) {
        return this.f.a(i);
    }
}
