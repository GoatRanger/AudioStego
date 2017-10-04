package dji.midware.sockets.a;

import dji.logic.c.b;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.natives.UDT;
import dji.midware.sockets.P3.d;
import dji.midware.util.n;
import java.util.Timer;
import java.util.TimerTask;

public abstract class j extends c {
    private Timer g;

    public j(String str, String str2) {
        super(str, str2);
    }

    public void destroy() {
        if (this.g != null) {
            this.g.cancel();
            this.g = null;
        }
        super.destroy();
    }

    protected void c() {
        if (d.getInstance().isOK() && !b.getInstance().c()) {
            if (this.e.equals("9001")) {
            }
            if (this.b == -1) {
                this.b = UDT.socket();
            }
            if (this.e.equals("9001")) {
                try {
                } catch (Exception e) {
                }
            }
            int connect = UDT.connect(this.b, this.d, this.e);
            if (this.e.equals("9001")) {
                if (connect >= 0) {
                    f();
                } else {
                    this.c = true;
                    onConnect();
                    e();
                    b("udt 连接建立 ip:" + this.d + "port:" + this.e);
                }
                if (!this.e.equals("9001")) {
                }
            }
            if (connect >= 0) {
                this.c = true;
                onConnect();
                e();
                b("udt 连接建立 ip:" + this.d + "port:" + this.e);
            } else {
                f();
            }
            if (!this.e.equals("9001")) {
            }
        }
    }

    private void a() {
        if (ServiceManager.getContext() != null && n.a(ServiceManager.getContext()) && !isConnected()) {
            c();
        }
    }

    protected void d() {
        this.g = new Timer(getClass().getSimpleName() + " timer");
        this.g.schedule(new TimerTask(this) {
            final /* synthetic */ j a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a();
            }
        }, 500, 1000);
    }

    protected void g() {
    }

    public boolean isOK() {
        return isConnected();
    }
}
