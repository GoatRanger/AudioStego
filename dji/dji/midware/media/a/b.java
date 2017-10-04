package dji.midware.media.a;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.logic.album.a.b.g;
import dji.logic.album.a.d.a;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.data.config.a.a.c;
import dji.midware.data.manager.P3.DJIVideoPackManager;
import dji.midware.data.model.d.h;
import dji.midware.data.model.d.i;
import dji.midware.media.e;

public class b extends g<a> {
    protected c a;
    protected a b;
    protected byte[] c;
    protected long d;
    protected String e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private int j;

    public b() {
        this.c = new byte[5242880];
        this.d = 0;
        this.f = 41943040;
        this.g = 0;
        this.h = 1;
        this.i = false;
        this.j = 0;
        this.b = new a();
        this.B = 3;
        this.r = 1500;
    }

    public void a() {
        r();
    }

    public void a(c cVar, a<a> aVar) {
        this.a = cVar;
        this.D = aVar;
        this.b.b = cVar.a;
        c();
        this.b.c = this.C.h(this.e);
    }

    protected void c() {
        this.e = this.a.a();
    }

    public void b() {
        super.b();
        this.v = true;
        this.x = 0;
        this.z = 0;
        this.d = 0;
        this.A = 0;
        this.u = false;
        if (!j()) {
            dji.midware.data.model.d.g.getInstance().a(dji.midware.data.config.a.a.a.File).start(null);
            DJIVideoPackManager.getInstance().start();
            k();
            p();
        }
    }

    private void k() {
        e.a(String.format("send command: subType=clip, index=%d, subindex=%d num=%d, size=%d, offset=%d", new Object[]{Integer.valueOf(this.a.d), Integer.valueOf(this.a.e), Integer.valueOf(-1), Integer.valueOf(-1), Integer.valueOf(0)}));
        i.getInstance().a(c.CLIP).a(this.a.d).c(this.a.e).b(-1).b(-1).a(0).start(null);
    }

    private void l() {
        if (this.g != 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.v) {
                this.x = 0;
                this.h++;
                h.getInstance().a(dji.midware.data.config.a.a.a.File).a(this.x).b(0).start(null);
                k();
            }
        }
    }

    private long m() {
        if (this.g == 0) {
            return -1;
        }
        return (long) (this.h == this.g ? -1 : this.f);
    }

    protected boolean j() {
        this.d = this.C.g(this.e);
        this.C.f(this.e);
        return false;
    }

    public void d() {
        q();
        this.C.b();
        DJIVideoPackManager.getInstance().stop();
        DJILogHelper.getInstance().LOGD(this.q, "停止", true, true);
    }

    public void e() {
        if (this.v) {
            dji.midware.data.model.d.g.getInstance().a(dji.midware.data.config.a.a.a.File).start(null);
            this.K.sendMessage(this.K.obtainMessage(1, DJIAlbumPullErrorType.CLIENT_ABORT));
        }
        d();
    }

    protected void f() {
        long j = 0;
        long j2 = this.d - this.A;
        if (j2 >= 0) {
            j = j2;
        }
        this.D.a(this.b.a, this.d, j);
        this.A = this.d;
    }

    protected void g() {
        this.D.a(this.b.a, this.d);
    }

    public void onEventBackgroundThread(dji.midware.data.model.d.c cVar) {
        int i = 0;
        if (this.v) {
            try {
                dji.midware.data.a.b.b a = cVar.a();
                this.j++;
                if (this.j % 200 == 0) {
                    DJILogHelper.getInstance().LOGD(this.q, "seq=" + this.x + " 实际=" + a.h + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + System.currentTimeMillis(), false, false);
                }
                if (a.h == this.x) {
                    int length;
                    s();
                    this.u = false;
                    if (a.h == 0) {
                        i = cVar.g();
                        length = a.i.length - i;
                        e.a("file size=" + cVar.c());
                        this.b.a = (long) (cVar.c() - i);
                    } else {
                        length = a.i.length;
                    }
                    System.arraycopy(a.i, i, this.c, this.z, length);
                    this.z += length;
                    this.d = ((long) length) + this.d;
                    this.K.sendEmptyMessage(2);
                    if (a.e == 1) {
                        DJILogHelper.getInstance().LOGD(this.q, "tOffset=" + this.d + " fileInfo.length=" + this.b.a, true, false);
                        if (this.d == this.b.a) {
                            o();
                            return;
                        } else if (this.d <= this.b.a) {
                            l();
                            return;
                        } else {
                            return;
                        }
                    }
                    this.x++;
                    if (this.z > this.c.length - 512) {
                        n();
                    }
                } else if (a.h > this.x) {
                    h();
                }
            } catch (Exception e) {
                e.a(e);
            }
        }
    }

    private void n() {
        e.a("recieved " + this.z + " bytes for the file " + this.a.b);
        this.C.a(this.c, 0, this.z);
        this.z = 0;
    }

    protected synchronized void h() {
        if (!this.u) {
            this.u = true;
            DJILogHelper.getInstance().LOGD(this.q, "重发 curSeq=" + this.x, false, false);
            DJIVideoPackManager.getInstance().clearVideoData();
            h.getInstance().a(dji.midware.data.config.a.a.a.File).a(this.x).b(1).start(null);
            t();
        }
    }

    private void o() {
        h.getInstance().a(dji.midware.data.config.a.a.a.File).a(this.x).b(0).start(null);
        DJILogHelper.getInstance().LOGD(this.q, "recvOver ", true, true);
        n();
        q();
        this.K.sendMessage(this.K.obtainMessage(0, this.b));
    }

    public void onEventBackgroundThread(dji.midware.data.model.d.e eVar) {
        if (this.v && this.d == this.b.a) {
            h.getInstance().a(dji.midware.data.config.a.a.a.File).a(this.x).b(0).start(null);
            d();
        }
    }
}
