package dji.midware.media.i;

import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.logic.album.a.b.h;
import dji.logic.album.a.b.h.a;
import dji.logic.album.a.b.h.c;
import dji.logic.album.a.b.h.d;
import dji.logic.album.model.DJIAlbumFile;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.d.g;
import dji.midware.g.a.b;
import dji.midware.natives.FPVController;
import java.io.IOException;

class j extends d {
    private volatile boolean C;
    private float D;
    private long E;
    private b F;
    boolean a;
    boolean b;
    long c;
    private h d;

    public j() {
        this.C = false;
        this.a = false;
        this.b = false;
        this.u = new byte[h.c];
        this.D = (((float) this.u.length) * 1.0f) / 262144.0f;
        this.d = new h();
        this.c = FPVController.native_getDJIAVPaserHeaderMagic();
        DJILogHelper.getInstance().LOGD("", "magic = " + this.c);
    }

    public void a() {
        super.a();
        this.u = null;
        if (this.d != null) {
            this.d.a();
            this.d = null;
        }
    }

    public void a(DJIAlbumFileInfo dJIAlbumFileInfo) {
        super.a(dJIAlbumFileInfo);
        this.i = dJIAlbumFileInfo.f;
        this.d.a(dJIAlbumFileInfo, this.x);
        this.d.a(new c(this) {
            final /* synthetic */ j a;

            {
                this.a = r1;
            }

            public void a(DJIAlbumFile dJIAlbumFile) {
                this.a.o = 10;
                if (this.a.i == 0) {
                    this.a.i = (int) dJIAlbumFile.c;
                }
                this.a.h.a = (long) (this.a.i * 262144);
                if (this.a.z != null) {
                    this.a.z.a(this.a);
                }
            }
        });
        this.d.a(new d(this) {
            final /* synthetic */ j a;

            {
                this.a = r1;
            }

            public void a(DJIAlbumFile dJIAlbumFile) {
                this.a.n();
                if (this.a.B != null) {
                    this.a.B.a(this.a);
                }
            }
        });
        this.d.a(new a(this) {
            final /* synthetic */ j a;

            {
                this.a = r1;
            }

            public void a(long j) {
                this.a.n = j;
                this.a.E = j;
                DJILogHelper.getInstance().LOGD("", "fileLen=" + this.a.n, false, true);
                if (this.a.d.j()) {
                    this.a.k = this.a.i;
                }
            }
        });
        this.d.a(new h.b(this) {
            final /* synthetic */ j a;

            {
                this.a = r1;
            }

            public void a() {
                DJILogHelper.getInstance().LOGD("mediaPlayer", "OnCacheRename reopen");
                this.a.k = this.a.i;
                this.a.j();
            }
        });
    }

    protected String b() {
        return this.d.c();
    }

    protected void c() {
        if (this.d != null) {
            this.d.k();
        }
        super.c();
    }

    protected void d() {
        this.d.b();
        j();
    }

    protected void e() {
        this.d.e();
    }

    protected void a(int i) {
        dji.midware.media.a h = ServiceManager.getInstance().h();
        if (h != null) {
            h.c();
        }
        this.C = true;
        float f = (((float) i) * 1.0f) / ((float) this.i);
        this.l = (long) (262144 * i);
        DJILogHelper.getInstance().LOGD("", "seekToOffset=" + this.l + " fileLen=" + this.E, false, true);
        long j = (long) (f * ((float) this.h.a));
        if (this.d.j()) {
            this.E = this.h.a;
            this.n = this.h.a;
        }
        if (this.r || (i < this.k && this.E + j > this.h.a + ((long) this.u.length))) {
            DJILogHelper.getInstance().LOGD("", "local file seekTo=" + j + " fileLen=" + this.E + " fileInfo.length=" + this.h.a, true, true);
            this.m = (j + this.E) - this.h.a;
            try {
                this.s.seek(this.m);
                FPVController.native_clear();
                g();
            } catch (IOException e) {
                e.printStackTrace();
            }
            n();
            if (this.B != null) {
                this.B.a(this);
            }
        } else {
            DJILogHelper.getInstance().LOGD("", "remote file seekTo " + i, true, true);
            j = (long) (i * 1000);
            this.n = 0;
            this.m = 0;
            e();
            ServiceManager.getInstance().pauseParseThread();
            this.d.a(g.a.Seek);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            FPVController.native_clear();
            ServiceManager.getInstance().resumeParseThread();
            DJILogHelper.getInstance().LOGD("", "remote file isCached " + this.d.j(), true, true);
            if (!this.d.j()) {
                this.d.b(j);
                if (this.s != null) {
                    try {
                        this.s.close();
                        this.s = null;
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                j();
            }
        }
        if (h != null) {
            h.a();
        }
        this.C = false;
    }

    public void m() {
        super.m();
        dji.midware.media.a h = ServiceManager.getInstance().h();
        if (h != null) {
            h.b();
        }
    }

    public void n() {
        super.n();
        dji.midware.media.a h = ServiceManager.getInstance().h();
        if (h != null) {
            h.a();
        }
    }

    public void o() {
        super.o();
        dji.midware.media.a h = ServiceManager.getInstance().h();
        if (h != null) {
            h.c();
        }
    }

    protected synchronized void g() {
        if (!this.C) {
            super.g();
        }
    }

    protected void a(long j, long j2, long j3) {
        if (j2 > 0 && this.u != null) {
            this.k = (int) ((((float) j2) * 1.0f) / 262144.0f);
            this.k = this.k < this.j ? this.j : this.k;
            if (FPVController.native_getQueueSize() <= 0) {
                if (this.p || (!this.q && this.k < this.i && ((float) (this.k - this.j)) < this.D)) {
                    this.q = true;
                    this.p = false;
                }
                DJILogHelper.getInstance().LOGD("mediaPlayer", "cachedPos=" + this.k + " position=" + this.j, false, false);
                DJILogHelper.getInstance().LOGD("mediaPlayer", " cacheTime=" + this.D + " current=" + j2, false, false);
                if (this.q && this.A != null) {
                    float f = (((float) (this.k - this.j)) * 1.0f) / (this.D > ((float) this.i) ? (float) this.i : this.D);
                    if (f > 1.0f) {
                        f = 1.0f;
                    }
                    DJILogHelper.getInstance().LOGD("mediaPlayer", "remain=" + f, false, false);
                    int i = (int) (f * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
                    if (i > 100) {
                        i = 100;
                    }
                    this.A.a(this, i);
                }
                if (!this.q) {
                    return;
                }
                if (this.k >= this.i || ((float) (this.k - this.j)) >= this.D) {
                    this.q = false;
                }
            }
        }
    }

    protected void a(byte[] bArr, int i) {
        boolean z = true;
        if (!this.b) {
            this.b = true;
            long g = dji.midware.util.c.g(bArr, 0);
            if (g != this.c) {
                z = false;
            }
            this.a = z;
            DJILogHelper.getInstance().LOGD(this.e, "preMagic=" + g + " isMixStream=" + this.a);
        }
        if (this.a) {
            if (this.F == null) {
                this.F = new b();
            }
            this.F.a(bArr, 0, i);
            return;
        }
        FPVController.native_transferVideoData(bArr, i);
    }
}
