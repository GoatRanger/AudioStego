package dji.midware.media.a;

import dji.log.DJILogHelper;
import dji.logic.album.a.b.g;
import dji.logic.album.a.d.a;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.data.a.b.b;
import dji.midware.data.config.a.a.c;
import dji.midware.data.manager.P3.DJIVideoPackManager;
import dji.midware.data.model.d.d;
import dji.midware.data.model.d.h;
import dji.midware.data.model.d.j;

public class e extends g<d> {
    private static final String a = "DJIClipInfoListLoader";
    private d b;
    private byte[] c;
    private int d;

    public e() {
        this.b = new d();
        this.r = 1000;
        this.B = 10;
    }

    public void a() {
        r();
    }

    public void a(a<d> aVar) {
        this.D = aVar;
    }

    public void b() {
        super.b();
        this.v = true;
        this.x = 0;
        this.z = 0;
        this.u = false;
        dji.midware.data.model.d.g.getInstance().a(dji.midware.data.config.a.a.a.a).start(null);
        DJIVideoPackManager.getInstance().start();
        j();
        p();
    }

    private void j() {
        dji.midware.media.e.d(a, "send command index=" + this.d + " num=-1, subtype=Clip");
        j.getInstance().a(this.d).b(-1).a(c.CLIP).start(null);
    }

    public void c() {
        DJIVideoPackManager.getInstance().start();
    }

    public void d() {
        q();
        DJIVideoPackManager.getInstance().stop();
    }

    public void e() {
        if (this.v) {
            dji.midware.data.model.d.g.getInstance().a(dji.midware.data.config.a.a.a.a).start(null);
            this.K.sendMessage(this.K.obtainMessage(1, DJIAlbumPullErrorType.CLIENT_ABORT));
        }
        d();
    }

    protected void f() {
        this.D.a((long) this.b.b, (long) this.z, ((long) this.z) - this.A);
        this.A = (long) this.z;
    }

    protected void g() {
        this.D.a((long) this.b.b, (long) this.z);
    }

    public void onEventBackgroundThread(d dVar) {
        dji.midware.media.e.b(a, "here DataCameraFileSystemListInfo");
        if (this.v) {
            try {
                b c = dVar.c();
                dji.midware.media.e.d(a, "received DataCameraFileSystemListInfo at DJIClipListLoader: recevPack.seq=" + c.h + " data=" + dji.thirdparty.afinal.c.c.b(c.i) + " buffer=" + dji.thirdparty.afinal.c.c.b(this.c));
                if (c.h == 0) {
                    this.b.c = dji.midware.util.c.a(c.i, 0);
                    this.b.a = dji.midware.util.c.a(c.i, 2);
                    this.b.b = (this.b.a * 2) + 4;
                    this.c = new byte[this.b.b];
                }
                if (c.h == this.x) {
                    s();
                    this.u = false;
                    System.arraycopy(c.i, 0, this.c, this.z, c.k);
                    this.z += c.k;
                    this.K.sendEmptyMessage(2);
                    if (c.e == 1) {
                        DJILogHelper.getInstance().LOGD(a, "recvPack.isLastFlag=" + c.e, true, false);
                        DJILogHelper.getInstance().LOGD(a, "offset=" + this.z + " dataLength=" + this.c.length, true, false);
                        if (this.z == this.c.length) {
                            k();
                        } else {
                            this.K.sendMessage(this.K.obtainMessage(1, DJIAlbumPullErrorType.DATA_NOMATCH));
                        }
                    } else {
                        this.x++;
                    }
                    if (c.h > 0 && c.h % 50 == 0) {
                    }
                } else if (c.h > this.x) {
                    h();
                }
            } catch (Exception e) {
                dji.midware.media.e.a(e);
            }
        }
    }

    protected synchronized void h() {
        if (!this.u) {
            this.u = true;
            DJILogHelper.getInstance().LOGD(a, "重发 curSeq=" + this.x, true, false);
            DJIVideoPackManager.getInstance().clearVideoData();
            h.getInstance().a(dji.midware.data.config.a.a.a.a).a(this.x).b(1).start(null);
            t();
        }
    }

    protected void i() {
        t();
        dji.midware.media.e.d(a, "resendMe()");
        j();
    }

    private void k() {
        try {
            h.getInstance().a(dji.midware.data.config.a.a.a.a).a(this.x).b(0).start(null);
            d();
            int i = 4;
            this.b.d.clear();
            for (int i2 = 0; i2 < this.b.a; i2++) {
                c cVar = new c();
                cVar.e = this.c[i];
                i++;
                cVar.c = c.a.find(this.c[i]);
                i++;
                this.b.d.add(cVar);
            }
            this.K.sendMessage(this.K.obtainMessage(0, this.b));
        } catch (Exception e) {
            dji.midware.media.e.a(e);
        }
    }

    public void onEventBackgroundThread(dji.midware.data.model.d.e eVar) {
        if (this.v) {
            h.getInstance().a(dji.midware.data.config.a.a.a.a).a(this.x).start(null);
            d();
        }
    }

    public void a(int i) {
        this.d = i;
    }
}
