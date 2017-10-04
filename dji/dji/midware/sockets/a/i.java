package dji.midware.sockets.a;

import android.os.Handler;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.f.a;
import dji.midware.util.b;
import dji.midware.util.n;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public abstract class i extends a {
    private Timer k;
    private Handler l = new Handler(b.b());

    public i(String str, int i) {
        super(str, i);
    }

    public void destroy() {
        if (this.k != null) {
            this.k.cancel();
            this.k = null;
        }
        super.destroy();
    }

    protected void f() {
        if (ServiceManager.getContext() != null && n.a(ServiceManager.getContext()) && !dji.logic.c.b.getInstance().c()) {
            try {
                c();
                this.e = new Socket();
                this.e.setKeepAlive(true);
                this.e.setSoLinger(true, 0);
                this.e.setReuseAddress(true);
                this.e.setSoTimeout(5000);
                this.e.connect(this.b, 2000);
                if (this.c != null) {
                    this.c.close();
                }
                if (this.d != null) {
                    this.d.close();
                }
                this.c = this.e.getOutputStream();
                this.d = this.e.getInputStream();
                h();
                if (a.getInstance().d() != dji.midware.f.b.a) {
                    if (this.l == null) {
                        this.l = new Handler(b.b());
                    }
                    this.l.post(new Runnable(this) {
                        final /* synthetic */ i a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.onConnect();
                        }
                    });
                } else {
                    a("没有成功执行连接状态");
                }
                a("tcp 连接建立 ip:" + this.h + "port:" + this.i);
            } catch (Exception e) {
                this.e = null;
            }
        }
    }

    private void a() {
        if (ServiceManager.getContext() != null && n.a(ServiceManager.getContext())) {
            if (this.e == null) {
                f();
                return;
            }
            try {
                this.e.sendUrgentData(255);
            } catch (IOException e) {
                synchronized (j) {
                    try {
                        if (this.e != null) {
                            this.e.close();
                        }
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    this.e = null;
                    a("tcp断开" + e);
                    a("tcp 连接断开 ip:" + this.h + "port:" + this.i);
                    onDisconnect();
                }
            }
        }
    }

    protected void g() {
        new Thread(new Runnable(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.k = new Timer();
                this.a.k.schedule(new TimerTask(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a();
                    }
                }, 100, 2000);
            }
        }).start();
    }

    protected void i() {
    }

    public boolean isOK() {
        return isConnected() && b();
    }
}
