package dji.midware.sockets.a;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Timer;
import java.util.TimerTask;

public abstract class g extends a {
    private ServerSocket k;
    private Timer l;
    private boolean m;

    public g(String str, int i) {
        super(str, i);
        this.m = false;
        this.g = true;
    }

    public void destroy() {
        if (this.l != null) {
            this.l.cancel();
            this.l = null;
        }
        try {
            if (this.k != null) {
                this.k.close();
                this.k = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.destroy();
    }

    protected void e() {
        super.e();
    }

    private void a() {
        d();
    }

    private void d() {
        if (!this.m) {
            this.m = true;
            j();
            this.m = false;
        }
    }

    private void j() {
        if (this.e != null) {
            try {
                this.e.sendUrgentData(255);
                return;
            } catch (Exception e) {
                b("socket 心跳出错");
                e();
                this.e = null;
                onDisconnect();
                return;
            }
        }
        b("监听客户端  start");
        try {
            this.e = this.k.accept();
            b("客户端有新的连接");
            try {
                this.c = this.e.getOutputStream();
                this.d = this.e.getInputStream();
                onConnect();
                b("新的连接 work");
            } catch (IOException e2) {
                b("出错 " + e2.getMessage());
            }
            b("监听客户端  end");
        } catch (Exception e3) {
        }
    }

    protected void f() {
        new Thread(new Runnable(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void run() {
                try {
                    if (!(this.a.k == null || this.a.k.isClosed())) {
                        this.a.k.close();
                        this.a.k = null;
                    }
                } catch (IOException e) {
                    this.a.b("重连 " + e.getMessage());
                }
                this.a.k();
            }
        }).start();
    }

    private void k() {
        if (this.k == null) {
            try {
                c();
                this.k = new ServerSocket();
                this.k.setReuseAddress(true);
                this.k.bind(this.b);
            } catch (Exception e) {
                b("连接出错 " + e.getMessage());
            }
        }
    }

    protected void g() {
        new Thread(new Runnable(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.l = new Timer();
                this.a.l.schedule(new TimerTask(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a();
                        this.a.a.h();
                    }
                }, 100, 1000);
            }
        }).start();
    }

    protected void i() {
    }

    public boolean isOK() {
        return isConnected() && b();
    }
}
