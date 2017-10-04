package dji.midware.sockets.a;

import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.k;
import dji.midware.natives.UDT;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class b implements k {
    protected String TAG = getClass().getSimpleName();
    protected String ip;
    protected int port;
    private volatile long sendCount = 0;
    private ExecutorService sendThreadPool;

    public abstract void LOGD(String str);

    public abstract void LOGE(String str);

    protected abstract void closeSocket();

    protected abstract void connect();

    protected abstract void startTimers();

    public b(String str, int i) {
        this.ip = str;
        this.port = i;
        this.sendThreadPool = Executors.newSingleThreadExecutor();
        this.sendThreadPool.execute(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.connect();
            }
        });
    }

    public void sendmessage(final byte[] bArr) {
        if (this.sendThreadPool != null && !this.sendThreadPool.isShutdown() && !this.sendThreadPool.isTerminated()) {
            this.sendCount++;
            this.sendThreadPool.execute(new Runnable(this) {
                final /* synthetic */ b b;

                public void run() {
                    if (!this.b.isConnected() || this.b.sendCount > 5) {
                        DJILogHelper.getInstance().LOGE(this.b.TAG, "sendCount > 5");
                        this.b.sendCount = this.b.sendCount - 1;
                        return;
                    }
                    if (bArr.length > 13 && bArr[9] == (byte) 1 && bArr[10] == (byte) 2) {
                        UDT.SwUdpJoyStickSend(bArr, 0, bArr.length);
                    } else {
                        UDT.SwUdpSend(bArr, 0, bArr.length);
                    }
                    this.b.sendCount = this.b.sendCount - 1;
                }
            });
        }
    }

    public void destroy() {
        LOGE("udt destroy 1 " + this.port);
        closeSocket();
        LOGE("udt destroy 2 " + this.port);
    }
}
