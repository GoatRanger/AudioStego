package dji.logic.album.a.b;

import dji.log.DJILogHelper;
import dji.logic.album.a.d.a;
import dji.logic.album.model.DJIAlbumFile;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.data.config.a.a.c;
import dji.midware.data.manager.P3.DJIVideoPackManager;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.d.e;
import dji.midware.data.model.d.g;
import dji.midware.data.model.d.h;
import dji.midware.data.model.d.i;

public class b extends g<DJIAlbumFile> {
    protected DJIAlbumFileInfo a;
    protected DJIAlbumFile b;
    protected byte[] c;
    protected long d;
    protected String e;
    protected c f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;
    private int l;
    private long m;
    private boolean n;

    public b() {
        this.c = new byte[2097152];
        this.d = 0;
        this.g = 41943040;
        this.h = 0;
        this.i = 1;
        this.j = false;
        this.k = false;
        this.l = 0;
        this.b = new DJIAlbumFile();
        this.B = 3;
        this.r = 1500;
        if (this.k) {
            this.r = 150000000;
        }
    }

    public void a() {
        r();
    }

    public void a(DJIAlbumFileInfo dJIAlbumFileInfo, a<DJIAlbumFile> aVar) {
        this.a = dJIAlbumFileInfo;
        this.D = aVar;
        this.b.a = dJIAlbumFileInfo.d;
        this.b.b = dJIAlbumFileInfo.a;
        this.b.d = dJIAlbumFileInfo.j;
    }

    protected void c() {
        this.e = this.a.b();
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
            g.getInstance().a(dji.midware.data.config.a.a.a.File).start(null);
            DJIVideoPackManager.getInstance().start();
            if (this.j) {
                this.h = ((int) ((this.a.a - this.d) / ((long) this.g))) + 1;
                if ((this.a.a - this.d) % ((long) this.g) == 0) {
                    this.h--;
                }
            }
            i.getInstance().a(this.a.d).c(this.a.e).b(1).a(this.f).b(l()).a(this.d).start(null);
            p();
        }
    }

    private void k() {
        if (this.h != 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.v) {
                this.x = 0;
                this.i++;
                h.getInstance().a(dji.midware.data.config.a.a.a.File).a(this.x).b(0).start(null);
                i.getInstance().a(this.a.d).b(1).a(this.f).b(l()).a(this.d).start(null);
            }
        }
    }

    private long l() {
        if (this.h == 0) {
            return -1;
        }
        return (long) (this.i == this.h ? -1 : this.g);
    }

    protected boolean j() {
        this.d = this.C.g(this.e);
        if (this.d != this.a.a || this.n) {
            this.A = this.d;
            this.C.f(this.e);
            return false;
        }
        this.b.e = this.C.c(this.e);
        this.K.sendMessage(this.K.obtainMessage(0, this.b));
        return true;
    }

    public void d() {
        q();
        this.C.a(this.a.b);
        DJIVideoPackManager.getInstance().stop();
    }

    public void e() {
        if (this.v) {
            g.getInstance().a(dji.midware.data.config.a.a.a.File).start(null);
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
        this.D.a(this.b.b, this.d, j);
        this.A = this.d;
    }

    protected void g() {
        this.D.a(this.b.b, this.d);
    }

    public void onEventBackgroundThread(dji.midware.data.model.d.c cVar) {
        int i = 0;
        if (this.v) {
            dji.midware.data.a.b.b a = cVar.a();
            this.l++;
            if (this.l % 200 == 0) {
            }
            if (a.h == this.x) {
                int length;
                s();
                this.u = false;
                if (a.h == 0) {
                    i = cVar.g();
                    length = a.i.length - i;
                } else {
                    length = a.i.length;
                }
                System.arraycopy(a.i, i, this.c, this.z, length);
                this.z += length;
                this.d = ((long) length) + this.d;
                this.K.sendEmptyMessage(2);
                if (a.e != 1) {
                    this.x++;
                    if (this.z > this.c.length - 2048) {
                        m();
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    if (System.currentTimeMillis() - this.m >= 100) {
                        this.m = currentTimeMillis;
                    }
                } else if (this.d == this.a.a) {
                    n();
                } else if (this.d <= this.a.a) {
                    k();
                } else if (DataCameraGetPushStateInfo.getInstance().getVerstion() < 4 && this.n) {
                    n();
                }
            } else if (a.h > this.x) {
                DJILogHelper.getInstance().LOGD(this.q, "seq=" + this.x + " 实际=" + a.h);
                h();
            }
        }
    }

    private void m() {
        this.C.a(this.c, 0, this.z);
        this.z = 0;
    }

    protected void h() {
        if (!this.u) {
            this.u = true;
            DJIVideoPackManager.getInstance().clearVideoData();
            h.getInstance().a(dji.midware.data.config.a.a.a.File).a(this.x).b(1).start(null);
            t();
        }
    }

    private void n() {
        h.getInstance().a(dji.midware.data.config.a.a.a.File).a(this.x).b(0).start(null);
        m();
        q();
        this.K.sendMessage(this.K.obtainMessage(0, this.b));
    }

    public void onEventBackgroundThread(e eVar) {
        if (!this.v) {
            return;
        }
        if (this.d == this.a.a) {
            h.getInstance().a(dji.midware.data.config.a.a.a.File).a(this.x + 1).b(0).start(null);
            d();
            return;
        }
        s();
        DJILogHelper.getInstance().LOGD("", "收到push， curseq：" + this.x, false, false);
        h.getInstance().a(dji.midware.data.config.a.a.a.File).a(this.x).b(1).start(null);
    }

    public void a(c cVar) {
        this.f = cVar;
        switch (cVar) {
            case Pano:
                this.B = 3;
                this.r = 1500;
                this.e = this.a.f();
                this.b.f = this.C.h(this.e);
                this.n = true;
                return;
            case ORG:
                this.B = 3;
                this.r = 1500;
                c();
                this.b.f = this.C.h(this.e);
                this.n = false;
                return;
            default:
                this.D.a(DJIAlbumPullErrorType.ERROR_REQ);
                this.n = false;
                return;
        }
    }
}
