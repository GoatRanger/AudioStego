package dji.logic.album.a.a;

import android.util.Log;
import dji.log.DJILogHelper;
import dji.logic.album.a.b.e;
import dji.logic.album.model.DJIAlbumFile;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.data.a.b.b;
import dji.midware.data.config.a.a.c;
import dji.midware.data.manager.P3.DJIVideoPackManager;
import dji.midware.data.model.d.g;
import dji.midware.data.model.d.i;
import dji.midware.media.f.f;

public class a extends e {
    private a L;
    private boolean M = false;
    private boolean N = false;

    public interface a {
        void f_();
    }

    public void e_() {
        this.M = true;
        a(0);
    }

    public void a(int i) {
        this.N = true;
        Log.e(this.q, "start load tail");
        a((long) i);
    }

    public void a(a aVar) {
        this.L = aVar;
    }

    public void a(DJIAlbumFileInfo dJIAlbumFileInfo, dji.logic.album.a.d.a<DJIAlbumFile> aVar) {
        this.a = dJIAlbumFileInfo;
        this.D = aVar;
        this.j = false;
        this.b.c = (long) dJIAlbumFileInfo.f;
        this.b.a = dJIAlbumFileInfo.d;
        this.b.d = dJIAlbumFileInfo.j;
        this.f = dJIAlbumFileInfo.i();
        this.g = this.f + "_over";
        if (this.C.d(this.g)) {
            this.j = true;
            this.f = this.g;
        }
        this.b.f = this.C.h(this.f);
    }

    public void b() {
        a(0);
    }

    public void a(long j) {
        this.K.sendEmptyMessage(3);
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
        i.getInstance().a(this.a.d).b(1).a(c.MP4).b(-1).a(this.e).start(null);
        p();
    }

    public void onEventBackgroundThread(dji.midware.data.model.d.c cVar) {
        if (this.v) {
            b a = cVar.a();
            this.p++;
            if (this.p % 100 == 0) {
                DJILogHelper.getInstance().LOGD(this.q, "seq=" + this.x + " 实际=" + a.h, true, false);
            }
            if (a.h == this.x) {
                int g;
                int length;
                s();
                this.u = false;
                if (a.h == 0) {
                    g = cVar.g();
                    length = a.i.length - g;
                    if (!this.h) {
                        this.h = true;
                        if (this.e == 0) {
                            this.b.b = (long) (cVar.c() - g);
                            DJILogHelper.getInstance().LOGD(this.q, "alburmFile.length=" + this.b.b, true, false);
                            if (this.l != null) {
                                this.l.a(this.b);
                            }
                        } else if (this.m != null) {
                            this.m.a(this.b);
                        }
                    }
                } else {
                    length = a.i.length;
                    g = 0;
                }
                System.arraycopy(a.i, g, this.d, this.z, length);
                this.z += length;
                this.e += (long) length;
                this.K.sendEmptyMessage(2);
                if (a.e == 1) {
                    DJILogHelper.getInstance().LOGD(this.q, "tOffset=" + this.e + " fileInfo.length=" + this.b.b, true, true);
                    if (this.e != this.b.b) {
                        this.K.sendMessage(this.K.obtainMessage(1, DJIAlbumPullErrorType.DATA_NOMATCH));
                    } else if (this.N) {
                        this.N = false;
                        e();
                        f.getInstance().b(this.d);
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        this.z = 0;
                        this.L.f_();
                        return;
                    } else {
                        n();
                    }
                } else {
                    this.x++;
                    if (this.z > this.d.length - 512) {
                        m();
                    }
                }
                if (this.M && this.z > 100) {
                    this.M = false;
                    g = f.getInstance().a(this.d);
                    e();
                    Log.d(this.q, "moov offset: " + g);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    this.z = 0;
                    a(g);
                }
            } else if (a.h > this.x) {
                h();
            }
        }
    }
}
