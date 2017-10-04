package dji.midware.sockets.a;

import android.util.Log;
import dji.midware.data.manager.P3.k;
import dji.midware.natives.UDT;
import dji.midware.util.a.a;
import dji.publics.b.a.b;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class c implements k {
    protected String a = getClass().getSimpleName();
    protected volatile int b = -1;
    protected volatile boolean c = false;
    protected String d;
    protected String e;
    protected int f = 100;
    private ExecutorService g;
    private Thread h;
    private int i = 4096;
    private byte[] j = new byte[this.i];
    private Runnable k = new Runnable(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void run() {
            while (this.a.isConnected() && this.a.b != -1) {
                int recv = UDT.recv(this.a.b, this.a.j, 0, this.a.i, 0);
                a.getInstance("DJIUdtSocket.runnable.run").a(a.a, Integer.valueOf(recv));
                if (recv >= 0) {
                    if (recv > 0) {
                        this.a.a(this.a.j, recv);
                    }
                } else if (recv == -2) {
                    this.a.f();
                    this.a.onDisconnect();
                    this.a.b("udt 连接断开 recvLen=" + recv + " ip:" + this.a.d + "port:" + this.a.e);
                } else if (recv == -1) {
                    this.a.b("udt 无数据 recvLen=" + recv);
                }
            }
            this.a.b("socket recv thread over");
        }
    };

    public abstract void a(String str);

    protected abstract void a(byte[] bArr, int i);

    public abstract void b(String str);

    protected abstract void c();

    protected abstract void d();

    protected abstract void g();

    static {
        Log.e(b.x, "step UDT.startup() start " + System.currentTimeMillis());
        UDT.startup();
        Log.e(b.x, "step UDT.startup() end " + System.currentTimeMillis());
    }

    public c(String str, String str2) {
        this.d = str;
        this.e = str2;
        this.g = Executors.newSingleThreadExecutor();
        this.h = new Thread(this.k);
        d();
    }

    public boolean isConnected() {
        return this.b != -1 && this.c;
    }

    public void sendmessage(final byte[] bArr) {
        if (this.g != null && !this.g.isShutdown() && !this.g.isTerminated()) {
            this.g.execute(new Runnable(this) {
                final /* synthetic */ c b;

                public void run() {
                    if (this.b.isConnected() && this.b.b != -1 && UDT.send(this.b.b, bArr, 0, bArr.length, 0) != bArr.length) {
                        this.b.g();
                    }
                }
            });
        }
    }

    public void destroy() {
        if (this.g != null) {
            this.g.shutdown();
            this.g = null;
        }
        b("udt destroy 1 " + this.e);
        Thread thread = new Thread(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.f();
            }
        });
        b("udt destroy 2 " + this.e);
    }

    protected void e() {
        if (this.h == null) {
            a();
        } else if (!this.h.isAlive() && isConnected()) {
            a("receiveThread restart");
            this.h.interrupt();
            this.h = null;
            a();
        }
    }

    private void a() {
        this.h = new Thread(this.k);
        this.h.setPriority(9);
        this.h.start();
    }

    protected void f() {
        if (this.b != -1) {
            this.c = false;
            UDT.close(this.b);
            this.b = -1;
        }
    }

    public boolean isOK() {
        return isConnected();
    }
}
