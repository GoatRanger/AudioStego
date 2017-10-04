package dji.logic.album.a.b;

import dji.log.DJILogHelper;
import dji.logic.album.model.DJIAlbumDirInfo;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.logic.album.model.DJIAlbumFileInfo.EXT_TYPE;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.data.a.b.b;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.a.a.c;
import dji.midware.data.manager.P3.DJIVideoPackManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.data.model.d.d;
import dji.midware.data.model.d.e;
import dji.midware.data.model.d.g;
import dji.midware.data.model.d.h;
import dji.midware.data.model.d.j;

public class a extends g<DJIAlbumDirInfo> {
    private DJIAlbumDirInfo a;
    private byte[] b;

    public a() {
        this.a = new DJIAlbumDirInfo();
        this.B = 2;
        this.r = 5000;
    }

    public void a() {
        r();
    }

    public void a(dji.logic.album.a.d.a<DJIAlbumDirInfo> aVar) {
        this.D = aVar;
    }

    public void b() {
        super.b();
        DJILogHelper.getInstance().LOGD(this.q, "DJIFileListLoader start()", true, false);
        this.v = true;
        this.x = 0;
        this.z = 0;
        this.u = false;
        g.getInstance().a(dji.midware.data.config.a.a.a.a).start(null);
        DJIVideoPackManager.getInstance().start();
        j.getInstance().a(1).b(-1).a(c.ORG).start(null);
        p();
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
            g.getInstance().a(dji.midware.data.config.a.a.a.a).start(null);
            this.K.sendMessage(this.K.obtainMessage(1, DJIAlbumPullErrorType.CLIENT_ABORT));
        }
        DJILogHelper.getInstance().LOGD(this.q, "DJIFileListLoader abort()", true, false);
        d();
    }

    protected void f() {
        this.D.a((long) this.a.b, (long) this.z, ((long) this.z) - this.A);
        this.A = (long) this.z;
    }

    protected void g() {
        this.D.a((long) this.a.b, (long) this.z);
    }

    public void onEventBackgroundThread(d dVar) {
        if (this.v) {
            b c = dVar.c();
            if (c.h == 0) {
                this.a.a = dVar.a();
                this.a.b = dVar.b();
                DJILogHelper.getInstance().LOGD(this.q, "fileCount=" + this.a.a + " dataLength=" + this.a.b, true, false);
                this.b = new byte[this.a.b];
            }
            DJILogHelper.getInstance().LOGD(this.q, "recvPack.seq=" + c.h + " curSeq=" + this.x + " offset=" + this.z + " recvPack.dataLen=" + c.k + " recvPack.isLastFlag=" + c.e, false, false);
            if (c.h == this.x) {
                s();
                this.u = false;
                System.arraycopy(c.i, 0, this.b, this.z, c.k);
                this.z += c.k;
                this.K.sendEmptyMessage(2);
                if (c.e == 1) {
                    DJILogHelper.getInstance().LOGD(this.q, "offset=" + this.z + " buffer.length=" + this.b.length, false, false);
                    if (this.z == this.b.length) {
                        j();
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
        }
    }

    protected synchronized void h() {
        if (!this.u) {
            this.u = true;
            DJILogHelper.getInstance().LOGD(this.q, "重发 curSeq=" + this.x, true, false);
            DJIVideoPackManager.getInstance().clearVideoData();
            h.getInstance().a(dji.midware.data.config.a.a.a.a).a(this.x).b(1).start(null);
            t();
        }
    }

    protected void i() {
        DJILogHelper.getInstance().LOGD(this.q, "DJIFileListLoader timeout resendMe", true, false);
        t();
        j.getInstance().a(1).b(-1).a(c.ORG).start(null);
    }

    private void j() {
        h.getInstance().a(dji.midware.data.config.a.a.a.a).a(this.x + 1).b(0).start(null);
        d();
        DJILogHelper.getInstance().LOGD(this.q, "recvOver ", true, false);
        this.a.c.clear();
        int i = 8;
        for (int i2 = 0; i2 < this.a.a; i2++) {
            DJIAlbumFileInfo dJIAlbumFileInfo = new DJIAlbumFileInfo();
            dJIAlbumFileInfo.a(dji.midware.util.c.g(this.b, i));
            i += 4;
            dJIAlbumFileInfo.a = dji.midware.util.c.g(this.b, i);
            i += 4;
            dJIAlbumFileInfo.d = dji.midware.util.c.b(this.b, i);
            i += 4;
            dJIAlbumFileInfo.f = dji.midware.util.c.a(this.b, i);
            i += 2;
            if (i.getInstance().c().equals(ProductType.KumquatS) || i.getInstance().c().equals(ProductType.KumquatX)) {
                dJIAlbumFileInfo.g = (this.b[i] >> 6) & 3;
                dJIAlbumFileInfo.h = this.b[i] & 63;
                i++;
            } else {
                dJIAlbumFileInfo.h = this.b[i];
                i++;
            }
            if (((float) dJIAlbumFileInfo.h) > 30.1f) {
                dJIAlbumFileInfo.h = (int) (((float) dJIAlbumFileInfo.h) * dji.pilot.visual.a.d.c);
            }
            if (dJIAlbumFileInfo.h > 7 && dJIAlbumFileInfo.h < 8) {
                dJIAlbumFileInfo.h *= 4;
            }
            dJIAlbumFileInfo.i = d.find(this.b[i]);
            i++;
            dJIAlbumFileInfo.j = f.find(this.b[i]);
            i++;
            dJIAlbumFileInfo.k = this.b[i];
            i++;
            if (dJIAlbumFileInfo.k <= 0) {
                dJIAlbumFileInfo.l = "";
            } else if (dJIAlbumFileInfo.k < 6) {
                dJIAlbumFileInfo.l = dji.midware.util.c.b(this.b, i, dJIAlbumFileInfo.k);
                i += dJIAlbumFileInfo.k;
            } else {
                Object obj = new byte[dJIAlbumFileInfo.k];
                System.arraycopy(this.b, i, obj, 0, dJIAlbumFileInfo.k);
                int i3 = i + dJIAlbumFileInfo.k;
                dJIAlbumFileInfo.m = true;
                DJILogHelper.getInstance().LOGD("", dji.midware.util.c.i(obj));
                i = 0;
                while (i < dJIAlbumFileInfo.k) {
                    int i4 = i + 1;
                    switch (EXT_TYPE.find(obj[i])) {
                        case VideoGUID:
                            dJIAlbumFileInfo.n = dji.midware.util.c.g(obj, i4);
                            i = i4 + 4;
                            break;
                        case PhotoGroupInfo:
                            dJIAlbumFileInfo.o = TYPE.find(obj[i4]);
                            i = i4 + 1;
                            dJIAlbumFileInfo.p = dji.midware.util.c.f(obj, i);
                            i += 2;
                            break;
                        case FileTag:
                            boolean z;
                            if (obj[i4] != (byte) 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            dJIAlbumFileInfo.q = z;
                            i = i4 + 1;
                            break;
                        default:
                            i = i4;
                            break;
                    }
                }
                i = i3;
            }
            this.a.c.add(dJIAlbumFileInfo);
        }
        this.K.sendMessage(this.K.obtainMessage(0, this.a));
    }

    public void onEventBackgroundThread(e eVar) {
        if (!this.v) {
        }
    }
}
