package dji.midware.sockets.a;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class e {
    public static String c = "0.0.0.0";
    protected String a = getClass().getSimpleName();
    protected d b = new d();
    private Socket d;
    private InetSocketAddress e;
    private Timer f;
    private ExecutorService g;
    private OutputStream h;
    private InputStream i;
    private boolean j = false;
    private Thread k = null;
    private Thread l = null;
    private boolean m = true;
    private byte[] n = new byte[4096];
    private Runnable o = new Runnable(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void run() {
            while (this.a.a()) {
                try {
                    int available = this.a.i.available();
                    this.a.i.read(this.a.n, 0, available);
                    if (available != 0) {
                        this.a.b.a(this.a.n, available);
                    }
                    Thread.sleep(1);
                } catch (IOException e) {
                    this.a.b("" + e.getMessage());
                    this.a.l();
                    return;
                } catch (Exception e2) {
                    this.a.b("" + e2.getMessage());
                    return;
                }
            }
        }
    };
    private Runnable p = new Runnable(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void run() {
            while (this.a.a()) {
                this.a.c();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    this.a.b("" + e.getMessage());
                }
            }
        }
    };

    public abstract void a(String str);

    public abstract void b(String str);

    protected abstract void c();

    protected abstract boolean d();

    protected abstract void e();

    public e(String str, int i) {
        a("初始化");
        this.m = str.equals(c);
        this.g = Executors.newSingleThreadExecutor();
        this.e = new InetSocketAddress(str, i);
        f();
        j();
        k();
        g();
    }

    public boolean a() {
        try {
            if (!(this.d == null || this.d.isClosed() || !this.d.isConnected())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void a(final byte[] bArr) {
        this.g.execute(new Runnable(this) {
            final /* synthetic */ e b;

            public void run() {
                if (this.b.a()) {
                    try {
                        this.b.h.write(bArr, 0, bArr.length);
                        this.b.h.flush();
                        this.b.a("发送成功");
                    } catch (IOException e) {
                        this.b.b("" + e.getMessage());
                        if (!this.b.m) {
                            this.b.l();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });
    }

    public void b() {
        if (this.f != null) {
            this.f.cancel();
            this.f = null;
        }
        try {
            if (this.d != null) {
                this.d.close();
                this.d = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void f() {
        if (!this.j) {
            new Thread(new Runnable(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.j = true;
                    try {
                        if (!(this.a.d == null || this.a.d.isClosed())) {
                            this.a.d.close();
                            this.a.d = null;
                        }
                    } catch (IOException e) {
                        this.a.b("重连 " + e.getMessage());
                    }
                    try {
                        this.a.e();
                        this.a.d = new Socket();
                        this.a.d.setKeepAlive(true);
                        this.a.d.setSoLinger(true, 0);
                        this.a.d.setReuseAddress(true);
                        this.a.d.setSoTimeout(10);
                        this.a.d.connect(this.a.e, 1000);
                        if (this.a.h != null) {
                            this.a.h.close();
                        }
                        if (this.a.i != null) {
                            this.a.i.close();
                        }
                        this.a.h = this.a.d.getOutputStream();
                        this.a.i = this.a.d.getInputStream();
                    } catch (Exception e2) {
                        this.a.b("重连出错 " + e2.getMessage());
                    }
                    this.a.j = false;
                }
            }).start();
        }
    }

    private void g() {
        new Thread(new Runnable(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.f = new Timer();
                this.a.f.schedule(new TimerTask(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.h();
                        this.a.a.i();
                    }
                }, 1000, 2000);
            }
        }).start();
    }

    private void h() {
        if (m()) {
            a("tcp 目前连接正常");
            return;
        }
        f();
        a("重连");
    }

    private void i() {
        if (!this.k.isAlive() && a()) {
            a("receiveThread restart");
            this.k.interrupt();
            this.k = null;
            j();
        }
        if (!this.l.isAlive() && a()) {
            a("parseThread restart");
            this.l.interrupt();
            this.l = null;
            k();
        }
    }

    private void j() {
        this.k = new Thread(this.o);
        this.k.setPriority(10);
        this.k.start();
    }

    private void k() {
        this.l = new Thread(this.p);
        this.l.setPriority(10);
        this.l.start();
    }

    private void l() {
        try {
            if (this.d != null && !this.d.isClosed()) {
                this.d.close();
                this.d = null;
            }
        } catch (Exception e) {
            b("" + e.getMessage());
        }
    }

    private boolean m() {
        return a() && d();
    }
}
