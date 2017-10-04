package dji.logic.album.a.b;

import dji.log.DJILogHelper;
import dji.logic.album.model.DJIAlbumFile;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.midware.data.manager.P3.DJIVideoPackManager;
import dji.midware.data.model.d.e;
import dji.midware.data.model.d.f;
import dji.midware.data.model.d.g;
import dji.midware.data.model.d.k;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class h extends g<DJIAlbumFile> {
    public static final int c = 819200;
    private a L;
    private b M;
    private int N;
    private long O;
    private boolean P;
    private int Q;
    protected DJIAlbumFileInfo a;
    protected DJIAlbumFile b;
    protected byte[] d;
    protected long e;
    protected String f;
    protected String g;
    int h;
    private int i;
    private boolean j;
    private boolean k;
    private boolean l;
    private long m;
    private Timer n;
    private c o;
    private d p;

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

    public h() {
        this.d = new byte[c];
        this.e = 0;
        this.i = 0;
        this.j = false;
        this.k = false;
        this.l = false;
        this.m = 0;
        this.h = 262144;
        this.N = 0;
        this.O = 0;
        this.P = false;
        this.Q = 0;
        this.b = new DJIAlbumFile();
        this.B = 3;
        this.r = 4000;
    }

    public void a() {
        this.d = null;
        r();
    }

    public void a(DJIAlbumFileInfo dJIAlbumFileInfo, dji.logic.album.a.d.a<DJIAlbumFile> aVar) {
        this.a = dJIAlbumFileInfo;
        this.D = aVar;
        this.k = false;
        this.b.c = (long) dJIAlbumFileInfo.f;
        this.b.a = dJIAlbumFileInfo.d;
        this.f = dJIAlbumFileInfo.h();
        this.g = this.f + "_over";
        if (this.C.d(this.g)) {
            this.k = true;
            this.f = this.g;
        }
        this.b.f = this.C.h(this.f);
    }

    public String c() {
        return this.b.f;
    }

    public boolean j() {
        return this.k;
    }

    public void b() {
        this.l = false;
        a(0);
    }

    public void a(long j) {
        c(j);
    }

    public void b(long j) {
        this.l = true;
        c(j);
    }

    public void k() {
        a(this.m);
    }

    private void c(long j) {
        super.b();
        this.j = false;
        this.e = (j / 1000) * ((long) this.h);
        this.i = 0;
        this.v = true;
        this.x = 0;
        this.y = 0;
        this.u = false;
        if (this.k) {
            this.b.b = this.C.g(this.f);
            this.o.a(this.b);
            this.L.a(this.b.b);
            this.K.sendMessage(this.K.obtainMessage(0, this.b));
            return;
        }
        DJIVideoPackManager.getInstance().start();
        this.C.e(this.f);
        k.getInstance().a(this.a.d).a(j).b(-1).start(null);
        p();
        if (this.n != null) {
            this.n.cancel();
        }
        f();
        this.n = new Timer();
        this.n.schedule(new TimerTask(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public void run() {
                dji.midware.data.model.d.h.getInstance().a(dji.midware.data.config.a.a.a.Stream).a(this.a.y).b(0).start(null);
            }
        }, 0, 100);
    }

    public void d() {
        if (this.n != null) {
            this.n.cancel();
            this.n = null;
        }
        if (this.v) {
            q();
            this.C.b();
            DJIVideoPackManager.getInstance().stop();
        }
    }

    public void e() {
        a(dji.midware.data.model.d.g.a.Force);
    }

    public void a(dji.midware.data.model.d.g.a aVar) {
        DJILogHelper.getInstance().LOGD(this.q, "will abort " + this.v, true, true);
        if (this.v) {
            g.getInstance().a(dji.midware.data.config.a.a.a.Stream).a(aVar).start(null);
        }
        d();
    }

    protected void f() {
        this.D.a(this.b.b, this.e, this.e - this.A);
        this.A = this.e;
    }

    protected void g() {
    }

    public boolean l() {
        return this.e > ((long) this.d.length) || this.e == this.b.b;
    }

    public void a(c cVar) {
        this.o = cVar;
    }

    public void a(d dVar) {
        this.p = dVar;
    }

    public void a(a aVar) {
        this.L = aVar;
    }

    public void a(b bVar) {
        this.M = bVar;
    }

    public void onEventBackgroundThread(f fVar) {
        if (this.v) {
            dji.midware.data.a.b.b a = fVar.a();
            this.N++;
            if (this.N % 200 == 0) {
                DJILogHelper.getInstance().LOGD(this.q, "seq=" + this.y + " 实际=" + a.h, false, false);
            }
            if (a.h == this.y) {
                int f;
                int i;
                this.x = this.y;
                this.y = a.h + 1;
                s();
                this.u = false;
                if (a.h == 0) {
                    this.P = false;
                    this.O = System.currentTimeMillis();
                    f = fVar.f();
                    int length = a.i.length - f;
                    if (!this.j) {
                        this.j = true;
                        if (this.e == 0) {
                            if (this.a.f == 0) {
                                this.b.c = fVar.e() / 1000;
                            } else {
                                this.b.c = (long) this.a.f;
                            }
                            this.b.b = this.b.c * ((long) this.h);
                            DJILogHelper.getInstance().LOGD(this.q, "alburmFile.duration=" + this.b.c, false, false);
                            if (this.o != null) {
                                this.o.a(this.b);
                                i = length;
                            }
                        } else if (this.p != null) {
                            this.p.a(this.b);
                            i = length;
                        }
                    }
                    i = length;
                } else {
                    i = a.i.length;
                    boolean z = false;
                }
                System.arraycopy(a.i, f, this.d, this.z, i);
                this.z += i;
                this.e = ((long) i) + this.e;
                this.K.sendEmptyMessage(2);
                if (a.e == 1) {
                    this.b.b = this.e;
                    n();
                } else if (this.z > this.d.length - 2048) {
                    m();
                }
            } else if (a.h > this.x + 1) {
                this.Q = a.h;
                h();
            }
        }
    }

    private void m() {
        this.C.a(this.d, 0, this.z);
        this.i += this.z;
        this.z = 0;
        if (this.L != null) {
            this.L.a((long) this.i);
        }
    }

    protected void h() {
        if (!this.u) {
            this.u = true;
            DJILogHelper.getInstance().LOGD(this.q, "重发 nextSeq=" + this.y + " 实际=" + this.Q, true, false);
            DJIVideoPackManager.getInstance().clearVideoData();
            dji.midware.data.model.d.h.getInstance().a(dji.midware.data.config.a.a.a.Stream).a(this.y).b(1).start(null);
            t();
        }
    }

    private void n() {
        dji.midware.data.model.d.h.getInstance().a(dji.midware.data.config.a.a.a.Stream).a(this.y).b(0).start(null);
        DJILogHelper.getInstance().LOGD(this.q, "recvOver foffset=" + this.i, true, true);
        m();
        if (!this.l && o()) {
            this.k = true;
            this.f = this.g;
            this.b.f = this.C.h(this.f);
            this.M.a();
        }
        q();
        this.K.sendMessage(this.K.obtainMessage(0, this.b));
    }

    private boolean o() {
        return new File(this.C.h(this.f)).renameTo(new File(this.C.h(this.g)));
    }

    public void onEventBackgroundThread(e eVar) {
        if (this.v && this.e == this.b.b) {
            dji.midware.data.model.d.h.getInstance().a(dji.midware.data.config.a.a.a.Stream).a(this.y).b(0).start(null);
            d();
        }
    }
}
