package dji.midware.sockets.a;

import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.k;
import dji.midware.util.n;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class a implements k {
    public static String j = "0.0.0.0";
    protected String a = getClass().getSimpleName();
    protected InetSocketAddress b;
    protected OutputStream c;
    protected InputStream d;
    protected Socket e;
    protected boolean f = false;
    protected boolean g = false;
    protected String h;
    protected int i;
    private ExecutorService k;
    private Thread l;
    private byte[] m = new byte[1024];
    private Runnable n = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            while (this.a.isConnected()) {
                try {
                    if (this.a.d == null) {
                        this.a.a("cmd buffer input null");
                    } else {
                        int read = this.a.d.read(this.a.m);
                        if (read > 0) {
                            this.a.a(this.a.m, 0, read);
                        }
                    }
                } catch (IOException e) {
                    this.a.b("" + e.getMessage());
                    this.a.i();
                } catch (Exception e2) {
                    this.a.b("" + e2.getMessage());
                }
            }
            if (!(this.a.e == null || this.a.g)) {
                synchronized (a.j) {
                    try {
                        if (this.a.e != null) {
                            this.a.e.close();
                        }
                    } catch (Exception e22) {
                        e22.printStackTrace();
                    }
                    this.a.e = null;
                    this.a.b("tcp 连接断开 by receive ip:" + this.a.h + "port:" + this.a.i);
                    this.a.onDisconnect();
                }
            }
            this.a.b("socket recv thread over");
        }
    };

    public abstract void a(String str);

    protected abstract void a(byte[] bArr, int i, int i2);

    public abstract void b(String str);

    protected abstract boolean b();

    protected abstract void c();

    protected abstract void f();

    protected abstract void g();

    protected abstract void i();

    public a(String str, int i) {
        this.h = str;
        this.i = i;
        this.k = Executors.newSingleThreadExecutor();
        this.b = new InetSocketAddress(str, i);
        if (ServiceManager.getContext() != null && n.a(ServiceManager.getContext())) {
            f();
        }
        this.l = new Thread(this.n);
        g();
    }

    public boolean isConnected() {
        try {
            if (!(this.e == null || this.e.isClosed() || !this.e.isConnected())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void sendmessage(final byte[] bArr) {
        if (this.k != null && !this.k.isShutdown() && !this.k.isTerminated()) {
            this.k.execute(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    if (this.b.isConnected() && this.b.c != null) {
                        try {
                            this.b.c.write(bArr, 0, bArr.length);
                            this.b.c.flush();
                        } catch (IOException e) {
                            this.b.b("" + e.getMessage());
                            this.b.i();
                        } catch (Exception e2) {
                            this.b.b("" + e2.getMessage());
                            e2.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    public void destroy() {
        b("djisocket destroy");
        if (this.k != null) {
            this.k.shutdown();
            this.k = null;
        }
        e();
    }

    protected void e() {
        if (this.d != null) {
            try {
                this.d.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (this.c != null) {
            try {
                this.c.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        if (this.e != null) {
            try {
                this.e.close();
            } catch (IOException e22) {
                e22.printStackTrace();
            }
        }
    }

    protected void h() {
        if (this.l == null) {
            a();
        } else if (!this.l.isAlive() && isConnected()) {
            a("receiveThread restart");
            this.l.interrupt();
            this.l = null;
            a();
        }
    }

    private void a() {
        this.l = new Thread(this.n);
        this.l.setPriority(9);
        this.l.start();
    }

    public boolean isOK() {
        return isConnected() && b();
    }
}
