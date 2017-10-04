package dji.logic.album.a.b;

import dji.log.DJILogHelper;
import dji.logic.album.model.DJIAlbumFile;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.data.manager.P3.DJIVideoPackManager;
import dji.midware.data.model.d.g;
import dji.midware.data.model.d.h;
import dji.midware.data.model.d.i;
import java.io.File;

public class e extends g<DJIAlbumFile> {
    public static final int c = 307200;
    protected DJIAlbumFileInfo a;
    protected DJIAlbumFile b;
    protected byte[] d;
    protected long e;
    protected String f;
    protected String g;
    protected boolean h;
    protected int i;
    protected boolean j;
    protected long k;
    protected c l;
    protected d m;
    protected a n;
    protected b o;
    protected int p;

    public interface a {
        void a(long j);
    }

    public interface b {
        void a();
    }

    public interface c {
        void a(DJIAlbumFile dJIAlbumFile);
    }

    public interface d {
        void a(DJIAlbumFile dJIAlbumFile);
    }

    public e() {
        this.d = new byte[c];
        this.e = 0;
        this.h = false;
        this.i = 0;
        this.j = false;
        this.k = 0;
        this.p = 0;
        this.b = new DJIAlbumFile();
        this.B = 5;
        this.r = 1500;
    }

    public void a() {
        this.d = null;
        r();
    }

    public void a(DJIAlbumFileInfo dJIAlbumFileInfo, dji.logic.album.a.d.a<DJIAlbumFile> aVar) {
        this.a = dJIAlbumFileInfo;
        this.D = aVar;
        this.j = false;
        this.b.c = (long) dJIAlbumFileInfo.f;
        this.b.a = dJIAlbumFileInfo.d;
        this.b.d = dJIAlbumFileInfo.j;
        this.f = dJIAlbumFileInfo.d();
        this.g = this.f + "_over";
        if (this.C.d(this.g)) {
            this.j = true;
            this.f = this.g;
        }
        this.b.f = this.C.h(this.f);
    }

    public String c() {
        return this.b.f;
    }

    public boolean j() {
        return this.j;
    }

    public void b() {
        a(0);
    }

    public void a(long j) {
        super.b();
        this.h = false;
        this.k = j;
        this.e = j;
        this.v = true;
        this.x = 0;
        this.i = 0;
        this.u = false;
        if (this.j) {
            this.b.b = this.C.g(this.f);
            this.l.a(this.b);
            this.n.a(this.b.b);
            this.K.sendMessage(this.K.obtainMessage(0, this.b));
            return;
        }
        g.getInstance().a(dji.midware.data.config.a.a.a.File).start(null);
        DJIVideoPackManager.getInstance().start();
        this.C.e(this.f);
        i.getInstance().a(this.a.d).b(1).a(dji.midware.data.config.a.a.c.SCR).b(-1).a(this.e).start(null);
        p();
    }

    public void k() {
        a(this.k);
    }

    public void d() {
        if (this.v) {
            q();
            this.C.b();
            DJIVideoPackManager.getInstance().stop();
        }
    }

    public void e() {
        DJILogHelper.getInstance().LOGD(this.q, "will abort " + this.v, true, true);
        if (this.v) {
            g.getInstance().a(dji.midware.data.config.a.a.a.File).start(null);
        }
        d();
    }

    protected void f() {
        this.D.a(this.b.b, this.e, this.e - this.A);
        this.A = this.e;
    }

    protected void g() {
        this.D.a(this.b.b, this.e);
    }

    public boolean l() {
        return this.e > ((long) this.d.length) || this.e == this.b.b;
    }

    public void a(c cVar) {
        this.l = cVar;
    }

    public void a(d dVar) {
        this.m = dVar;
    }

    public void a(a aVar) {
        this.n = aVar;
    }

    public void a(b bVar) {
        this.o = bVar;
    }

    public void onEventBackgroundThread(dji.midware.data.model.d.c cVar) {
        if (this.v) {
            dji.midware.data.a.b.b a = cVar.a();
            this.p++;
            if (this.p % 100 == 0) {
                DJILogHelper.getInstance().LOGD(this.q, "seq=" + this.x + " 实际=" + a.h, true, false);
            }
            if (a.h == this.x) {
                int g;
                int i;
                s();
                this.u = false;
                if (a.h == 0) {
                    g = cVar.g();
                    int length = a.i.length - g;
                    if (!this.h) {
                        this.h = true;
                        if (this.e == 0) {
                            this.b.b = (long) (cVar.c() - g);
                            DJILogHelper.getInstance().LOGD(this.q, "alburmFile.length=" + this.b.b, true, false);
                            if (this.l != null) {
                                this.l.a(this.b);
                                i = length;
                            }
                        } else if (this.m != null) {
                            this.m.a(this.b);
                            i = length;
                        }
                    }
                    i = length;
                } else {
                    i = a.i.length;
                    boolean z = false;
                }
                System.arraycopy(a.i, g, this.d, this.z, i);
                this.z += i;
                this.e = ((long) i) + this.e;
                this.K.sendEmptyMessage(2);
                if (a.e == 1) {
                    DJILogHelper.getInstance().LOGD(this.q, "tOffset=" + this.e + " fileInfo.length=" + this.b.b, true, true);
                    if (this.e == this.b.b) {
                        n();
                        return;
                    } else {
                        this.K.sendMessage(this.K.obtainMessage(1, DJIAlbumPullErrorType.DATA_NOMATCH));
                        return;
                    }
                }
                this.x++;
                if (this.z > this.d.length - 512) {
                    m();
                }
            } else if (a.h > this.x) {
                h();
            }
        }
    }

    protected void m() {
        this.C.a(this.d, 0, this.z);
        this.i += this.z;
        this.z = 0;
        if (this.n != null) {
            this.n.a((long) this.i);
        }
    }

    protected void h() {
        if (!this.u) {
            this.u = true;
            DJILogHelper.getInstance().LOGD(this.q, "重发 curSeq=" + this.x, true, false);
            DJIVideoPackManager.getInstance().clearVideoData();
            h.getInstance().a(dji.midware.data.config.a.a.a.File).a(this.x).b(1).start(null);
            t();
        }
    }

    protected void n() {
        h.getInstance().a(dji.midware.data.config.a.a.a.File).a(this.x + 1).b(0).start(null);
        DJILogHelper.getInstance().LOGD(this.q, "recvOver ", true, true);
        m();
        if (this.b.b == this.C.g(this.f) && o()) {
            this.j = true;
            this.f = this.g;
            this.b.f = this.C.h(this.f);
            this.o.a();
        }
        q();
        this.K.sendMessage(this.K.obtainMessage(0, this.b));
    }

    protected boolean o() {
        return new File(this.C.h(this.f)).renameTo(new File(this.C.h(this.g)));
    }

    public void onEventBackgroundThread(dji.midware.data.model.d.e eVar) {
        if (this.v && this.e == this.b.b) {
            h.getInstance().a(dji.midware.data.config.a.a.a.File).a(this.x + 1).b(0).start(null);
            d();
        }
    }
}
