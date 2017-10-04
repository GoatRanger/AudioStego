package dji.logic.album.a.b;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import dji.log.DJILogHelper;
import dji.logic.album.a.b;
import dji.logic.album.a.d.a;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.thirdparty.a.c;
import java.util.Timer;
import java.util.TimerTask;

public abstract class g<E> {
    protected long A = 0;
    protected int B = 0;
    protected b C = b.getInstance();
    protected a<E> D;
    protected final int E = 0;
    protected final int F = 1;
    protected final int G = 2;
    protected final int H = 3;
    protected final int I = 4;
    protected final int J = 5;
    protected Handler K = new Handler(Looper.getMainLooper(), new Callback(this) {
        final /* synthetic */ g a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.d();
                    this.a.D.a(message.obj);
                    break;
                case 1:
                    this.a.d();
                    this.a.D.a((DJIAlbumPullErrorType) message.obj);
                    break;
                case 2:
                    this.a.g();
                    break;
                case 3:
                    this.a.D.a();
                    break;
                case 4:
                    g gVar = this.a;
                    gVar.t++;
                    if (this.a.t <= this.a.B) {
                        this.a.i();
                        break;
                    }
                    this.a.u();
                    DJILogHelper.getInstance().LOGD(this.a.q, "loader timeout", true, true);
                    this.a.e();
                    this.a.D.a(DJIAlbumPullErrorType.TIMEOUT);
                    break;
                case 5:
                    this.a.f();
                    break;
            }
            return false;
        }
    });
    protected final String q = getClass().getSimpleName();
    protected int r = 1000;
    protected Timer s;
    protected int t = 0;
    protected boolean u = false;
    protected boolean v = false;
    protected boolean w = false;
    protected int x = 0;
    protected int y = 0;
    protected int z = 0;

    public abstract void a();

    public abstract void d();

    public abstract void e();

    protected abstract void f();

    protected abstract void g();

    protected abstract void h();

    public void b() {
        this.K.sendEmptyMessage(3);
    }

    protected void p() {
        s();
        if (this.s != null) {
            this.s.cancel();
            this.s = null;
        }
        this.s = new Timer();
        this.s.schedule(new TimerTask(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.K.sendEmptyMessage(5);
            }
        }, 1000, 1000);
    }

    protected void q() {
        this.v = false;
        this.K.removeMessages(4);
        if (this.s != null) {
            this.s.cancel();
            this.s = null;
        }
    }

    protected void r() {
        c.a().d(this);
    }

    protected void s() {
        this.t = 0;
        t();
    }

    protected void t() {
        this.K.removeMessages(4);
        this.K.sendEmptyMessageDelayed(4, (long) this.r);
    }

    protected void u() {
        this.t = 0;
    }

    public g() {
        c.a().a(this);
    }

    public void onEventBackgroundThread(dji.midware.data.model.d.b bVar) {
        if (this.v) {
            this.K.sendMessage(this.K.obtainMessage(1, DJIAlbumPullErrorType.SERVER_ABORT));
        }
    }

    protected void i() {
        DJILogHelper.getInstance().LOGD(this.q, this.r + "ms内没数据 重发", true, false);
        this.u = false;
        h();
    }
}
