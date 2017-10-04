package dji.midware.media.i;

import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.logic.album.a.b.h;
import dji.logic.album.a.b.h.b;
import dji.logic.album.a.b.h.c;
import dji.logic.album.a.b.h.d;
import dji.logic.album.model.DJIAlbumFile;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.natives.FPVController;
import java.io.IOException;

class a extends d {
    private h C;
    private volatile boolean D;
    private float E;
    private byte[] F;
    private byte[] G;
    private int H;
    private int I;
    private byte[] J;
    private volatile int K;
    private volatile int L;
    private volatile int M;
    private int N;
    private boolean O;
    private int P;
    private int Q;
    private boolean R;
    boolean a;
    boolean b;
    long c;
    byte[] d;

    public a() {
        this.D = false;
        this.a = false;
        this.b = false;
        this.d = new byte[]{(byte) 68, (byte) 74, (byte) 65, (byte) 86};
        this.H = 0;
        this.I = 0;
        this.J = new byte[8];
        this.K = 0;
        this.L = 0;
        this.M = 0;
        this.N = 0;
        this.O = false;
        this.P = 0;
        this.Q = 0;
        this.R = false;
        this.u = new byte[h.c];
        this.E = (((float) this.u.length) * 1.0f) / 262144.0f;
        this.F = new byte[1228800];
        this.G = new byte[1228800];
        this.C = new h();
        this.c = FPVController.native_getDJIAVPaserHeaderMagic();
        DJILogHelper.getInstance().LOGD("", "magic = " + this.c);
    }

    public void a() {
        super.a();
        this.u = null;
        if (this.C != null) {
            this.C.a();
            this.C = null;
        }
    }

    public void a(DJIAlbumFileInfo dJIAlbumFileInfo) {
        super.a(dJIAlbumFileInfo);
        this.i = dJIAlbumFileInfo.f;
        this.C.a(dJIAlbumFileInfo, this.x);
        this.C.a(new c(this) {
            final /* synthetic */ a a;

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
        this.C.a(new d(this) {
            final /* synthetic */ a a;

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
        this.C.a(new dji.logic.album.a.b.h.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(long j) {
                this.a.n = j;
                if (this.a.C.j()) {
                    this.a.k = this.a.i;
                }
            }
        });
        this.C.a(new b(this) {
            final /* synthetic */ a a;

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
        return this.C.c();
    }

    protected void c() {
        if (this.C != null) {
            this.C.k();
        }
        super.c();
    }

    protected void d() {
        this.C.b();
        j();
    }

    protected void e() {
        this.C.e();
    }

    protected void a(int i) {
        this.D = true;
        float f = (((float) i) * 1.0f) / ((float) this.i);
        this.l = (long) (262144 * i);
        DJILogHelper.getInstance().LOGD("", "seekToOffset=" + this.l + " fileLen=" + this.n, false, true);
        long j;
        if (this.r || i < this.k) {
            j = (long) (f * ((float) this.h.a));
            DJILogHelper.getInstance().LOGD("", "local file seekTo " + i, true, true);
            this.m = (j + this.n) - this.h.a;
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
            this.C.a(dji.midware.data.model.d.g.a.Seek);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            FPVController.native_clear();
            ServiceManager.getInstance().resumeParseThread();
            DJILogHelper.getInstance().LOGD("", "remote file isCached " + this.C.j(), true, true);
            if (!this.C.j()) {
                this.C.b(j);
                if (this.s != null) {
                    try {
                        this.s.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                j();
            }
        }
        this.D = false;
    }

    protected void a(long j, long j2, long j3) {
        if (j2 > 0 && this.u != null) {
            this.k = (int) (((float) this.l) + ((((float) j2) * 1.0f) / 262144.0f));
            if (FPVController.native_getQueueSize() <= 0) {
                if (this.p || (!this.q && this.k < this.i && ((float) (this.k - this.j)) < this.E)) {
                    this.q = true;
                    this.p = false;
                }
                DJILogHelper.getInstance().LOGD("mediaPlayer", "cachedPos=" + this.k + " position=" + this.j, true, true);
                DJILogHelper.getInstance().LOGD("mediaPlayer", " cacheTime=" + this.E, true, true);
                if (this.q && this.A != null) {
                    float f = (((float) (this.k - this.j)) * 1.0f) / (this.E > ((float) this.i) ? (float) this.i : this.E);
                    DJILogHelper.getInstance().LOGD("mediaPlayer", "remain=" + f, true, true);
                    int i = (int) ((1.0f - f) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
                    if (i > 100) {
                        i = 100;
                    }
                    this.A.a(this, i);
                }
                if (!this.q) {
                    return;
                }
                if (this.k >= this.i || ((float) (this.k - this.j)) >= this.E) {
                    this.q = false;
                }
            }
        }
    }

    protected void a(byte[] bArr, int i) {
        if (!this.b) {
            this.b = true;
            long g = dji.midware.util.c.g(bArr, 0);
            this.a = g == this.c;
            DJILogHelper.getInstance().LOGD(this.e, "preMagic=" + g + " isMixStream=" + this.a);
        }
        DJILogHelper.getInstance().LOGD(this.e, "parseData size=" + i + " remainSize=" + this.M);
        if (this.a) {
            int i2 = this.M;
            this.M += i;
            if (i2 + i >= this.F.length) {
                DJILogHelper.getInstance().LOGD(this.e, "parseData tmpRemainSize=" + i2 + " 太大 丢掉");
                this.M = i;
                this.O = false;
                i2 = 0;
            }
            try {
                System.arraycopy(bArr, 0, this.F, i2, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            boolean z = false;
            while (true) {
                if ((!z || this.R) && !this.D) {
                    u();
                    z = true;
                }
            }
            if (this.K > 0) {
                System.arraycopy(this.F, this.K, this.G, 0, this.M);
                System.arraycopy(this.G, 0, this.F, 0, this.M);
                this.K = 0;
            }
            DJILogHelper.getInstance().LOGD(this.e, "解完一批包");
            return;
        }
        FPVController.native_transferVideoData(bArr, i);
    }

    private void s() {
        do {
        } while (this.O);
        if (this.M <= 0) {
            this.M = 0;
            this.K = 0;
            return;
        }
        this.Q = this.K;
        int i = this.K;
        while (i < this.K + this.M && i < this.F.length) {
            if (this.F[i] == this.d[0]) {
                this.P = i;
                if ((this.P - this.K) + this.d.length >= this.M) {
                    return;
                }
                if (this.F[this.P + 1] == this.d[1] && this.F[this.P + 2] == this.d[2] && this.F[this.P + 3] == this.d[3]) {
                    this.O = true;
                    this.K = this.P;
                    this.M -= this.P - this.Q;
                    return;
                }
                this.K += 4;
                this.M -= 4;
            }
            i++;
        }
    }

    private void t() {
        this.K = this.Q;
    }

    private void u() {
        this.R = false;
        if (this.M <= 16) {
            DJILogHelper.getInstance().LOGD(this.e, "remainSize 不足 8个byte");
            return;
        }
        if (!this.O) {
            s();
            if (!this.O) {
                return;
            }
        }
        this.L = this.K;
        this.L += 5;
        Object obj = new byte[4];
        System.arraycopy(this.F, this.L, obj, 0, 3);
        int g = (int) dji.midware.util.c.g(obj, 0);
        this.L += 3;
        int i = g + 20;
        if (this.o == 0 || i <= this.M) {
            this.I = (this.F[this.L] & 240) >> 4;
            this.H = this.F[this.L] & 15;
            this.L++;
            System.arraycopy(this.F, this.L, this.J, 0, 2);
            this.L += 3;
            System.arraycopy(this.F, this.L, this.J, 2, 2);
            this.L += 3;
            System.arraycopy(this.F, this.L, this.J, 4, 2);
            this.L += 3;
            System.arraycopy(this.F, this.L, this.J, 6, 2);
            this.L += 2;
            System.arraycopy(this.F, this.L, this.G, 0, g);
            this.K += i;
            this.M -= i;
            if (this.H == 1) {
                FPVController.native_transferVideoDataDirect(this.G, g, this.J, this.J.length);
            } else if (this.H == 2) {
                ServiceManager.getInstance().d();
                FPVController.native_transferAudioData(this.G, g, this.J, this.J.length);
            } else {
                DJILogHelper.getInstance().LOGD(this.e, "packType=" + this.H + " packVer=" + this.I);
            }
            this.O = false;
            this.R = true;
            return;
        }
        DJILogHelper.getInstance().LOGD(this.e, "isPlaying=" + this.o + "packLenWithHead=" + i + " remainSize=" + this.M);
        this.O = false;
    }
}
