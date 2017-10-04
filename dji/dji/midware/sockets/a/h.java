package dji.midware.sockets.a;

import dji.log.DJILogHelper;
import dji.logic.c.b;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.natives.UDT;
import dji.midware.util.n;
import java.util.Timer;
import java.util.TimerTask;

public abstract class h extends b {
    private Timer checkTimer;
    private boolean isFortest = true;

    protected abstract void parse(int i, byte[] bArr, int i2);

    public h(String str, int i) {
        super(str, i);
    }

    public void destroy() {
        if (this.checkTimer != null) {
            this.checkTimer.cancel();
            this.checkTimer = null;
        }
        super.destroy();
    }

    protected void connect() {
        DJILogHelper.getInstance().LOGE("wm220", "isUSBAoaConnected: " + b.getInstance().c(), false, true);
        if (!b.getInstance().c()) {
            UDT.SwUdpConnect(this.ip, this.port);
            LOGE("SwUdpConnect ...");
        }
    }

    public boolean isConnected() {
        return UDT.SwUdpIsConnected();
    }

    protected void closeSocket() {
        UDT.SwUdpClose();
    }

    private void checkConneted() {
        if (ServiceManager.getContext() != null && n.a(ServiceManager.getContext()) && !isConnected()) {
            connect();
        }
    }

    protected void startTimers() {
        this.checkTimer = new Timer(getClass().getSimpleName() + " timer");
        this.checkTimer.schedule(new TimerTask(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.checkConneted();
            }
        }, 500, 4000);
    }
}
