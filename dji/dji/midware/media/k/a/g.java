package dji.midware.media.k.a;

import android.util.Log;
import dji.midware.media.d;
import dji.midware.media.e;
import java.io.File;

public class g extends f implements h {
    public static String a = "TranscoderFFmpeg";
    a b = null;
    b l = null;
    boolean m = false;
    private Exception n = null;

    private class a extends Thread {
        final /* synthetic */ g a;

        private a(g gVar) {
            this.a = gVar;
        }

        public void run() {
            double A = (((double) (this.a.f.A() - this.a.f.B())) - (120.0d * d.d())) / 1000.0d;
            while (this.a.l.isAlive()) {
                int i;
                if (A == 0.0d) {
                    i = 100;
                } else {
                    i = (int) ((j.d.a() / A) * 100.0d);
                }
                if (i < 0 || i > 100) {
                    e.a("progress num error: " + i);
                } else {
                    synchronized (this.a.g) {
                        if (this.a.h != null) {
                            this.a.h.a(i);
                        }
                    }
                    e.a("progress: " + i);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    if (this.a.m) {
                        return;
                    }
                }
            }
            if (this.a.n != null) {
                synchronized (this.a.g) {
                    if (this.a.h != null) {
                        this.a.h.a(this.a.n);
                    }
                }
                return;
            }
            synchronized (this.a.g) {
                if (this.a.h != null) {
                    this.a.h.a(100);
                }
            }
            synchronized (this.a.j) {
                if (this.a.i == a.TRANSCODING) {
                    this.a.l();
                    this.a.m();
                    this.a.j();
                    this.a.i = a.STANDBY;
                }
            }
            synchronized (this.a.g) {
                if (this.a.h != null) {
                    this.a.h.a();
                }
            }
        }
    }

    private class b extends Thread {
        final /* synthetic */ g a;

        private b(g gVar) {
            this.a = gVar;
        }

        public void run() {
            try {
                j.d.b();
                j.d.a(this.a.d, this.a.e, 120);
            } catch (Exception e) {
                this.a.n = e;
                e.a(e);
            }
        }
    }

    public void f() {
        this.m = false;
        j.d.c();
        File file = new File(this.e);
        if (!file.exists()) {
            return;
        }
        if (Boolean.valueOf(file.delete()).booleanValue()) {
            Log.i(a, "mp4 File has been deleted");
        } else {
            Log.e(a, "mp4 File not deleted");
        }
    }

    public void g() {
        super.a(this.d, this.e);
        this.m = true;
        synchronized (this.g) {
            if (this.h != null) {
                this.h.b();
            }
        }
        this.l = new b();
        this.l.start();
        this.b = new a();
        this.b.start();
    }

    String h() {
        return a;
    }
}
