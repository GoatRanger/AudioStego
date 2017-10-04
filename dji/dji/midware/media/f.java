package dji.midware.media;

import android.util.Log;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.thirdparty.a.c;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class f {
    public static boolean b = false;
    private static final int c = 80;
    private static final int m = 2000;
    public final String a = "SmoothFilter";
    private int d = -1;
    private int e = -1;
    private long f = 0;
    private BlockingQueue<Object> g = new ArrayBlockingQueue(1000);
    private a h;
    private boolean i = true;
    private Thread j;
    private long k;
    private Runnable l = new Runnable(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public void run() {
            while (!this.a.i) {
                int size = this.a.g.size();
                if (System.currentTimeMillis() - this.a.f > 80) {
                    this.a.d();
                } else if (size < this.a.e) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    this.a.d();
                    long f = (long) this.a.d;
                    if (size > this.a.e) {
                        f = (long) (this.a.d - ((size - this.a.e) * 3));
                    }
                    if (f > 0) {
                        try {
                            Thread.sleep(f);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
        }
    };
    private long n = -1;
    private int o = 0;
    private long p = 0;
    private MODE q = null;

    public interface a {
        void a(Object obj);
    }

    public boolean a() {
        ProductType c = i.getInstance().c();
        MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
        if (mode == MODE.PLAYBACK || mode == MODE.NEW_PLAYBACK) {
            return false;
        }
        if (c == ProductType.Longan || c == ProductType.LonganPro || c == ProductType.LonganRaw || c == ProductType.LonganZoom) {
            return true;
        }
        return false;
    }

    public void b() {
        this.i = false;
        this.j = new Thread(this.l);
        this.j.start();
        c.a().a(this);
    }

    public void c() {
        this.i = true;
        if (c.a().c(this)) {
            c.a().d(this);
        }
    }

    public void a(a aVar) {
        this.h = aVar;
    }

    public synchronized void a(Object obj) {
        a(System.currentTimeMillis());
        if (obj == null) {
            Log.d("SmoothFilter", "data is null");
        } else if (!b && a()) {
            this.f = System.currentTimeMillis();
            if (this.e < 0) {
                b(obj);
            } else {
                try {
                    this.g.put(obj);
                } catch (Exception e) {
                }
            }
        } else if (this.h != null) {
            b(obj);
        }
    }

    private void d() {
        Object obj = null;
        try {
            obj = this.g.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b(obj);
    }

    private void a(long j) {
        this.o++;
        if (this.n == -1) {
            this.n = j;
        } else if (j - this.n > 2000) {
            this.d = (int) ((j - this.n) / ((long) (this.o - 1)));
            this.e = 80 / this.d;
            this.n = -1;
            this.o = 0;
        }
    }

    private void a(String str) {
        Log.d("SmoothFilter", str);
    }

    private void b(Object obj) {
        if (this.h != null && obj != null) {
            this.h.a(obj);
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (dataCameraGetPushStateInfo.getMode() != this.q) {
            this.q = dataCameraGetPushStateInfo.getMode();
            if (this.q == MODE.TAKEPHOTO || this.q == MODE.RECORD) {
                this.d = -1;
                this.e = -1;
            }
        }
    }
}
