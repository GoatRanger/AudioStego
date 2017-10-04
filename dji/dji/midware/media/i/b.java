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
import dji.midware.natives.FPVController;
import java.io.IOException;

class b extends d {
    private h C;
    private byte[] D;
    private byte[] E;
    private Object F;
    private volatile boolean G;
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

    public b() {
        this.a = false;
        this.b = false;
        this.d = new byte[]{(byte) 68, (byte) 74, (byte) 65, (byte) 86};
        this.F = new Object();
        this.G = false;
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
        this.D = new byte[1228800];
        this.E = new byte[1228800];
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
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(DJIAlbumFile dJIAlbumFile) {
                this.a.o = 10;
                this.a.h.a = dJIAlbumFile.b;
                if (this.a.i == 0) {
                    this.a.i = (int) dJIAlbumFile.c;
                }
                if (this.a.z != null) {
                    this.a.z.a(this.a);
                }
            }
        });
        this.C.a(new d(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(DJIAlbumFile dJIAlbumFile) {
                this.a.n();
                DJILogHelper.getInstance().LOGD("", "isPlaying=" + this.a.o + " onSeekComplete", false, true);
                if (this.a.B != null) {
                    this.a.B.a(this.a);
                }
            }
        });
        this.C.a(new a(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(long j) {
                this.a.n = j;
            }
        });
    }

    protected void f() {
        this.G = true;
        synchronized (this.F) {
            this.K = 0;
            this.L = 0;
            this.M = 0;
        }
        this.G = false;
        super.f();
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
        if (this.C != null) {
            this.C.e();
        }
    }

    public void b(int i) {
        super.b(i);
    }

    protected void a(int i) {
        this.G = true;
        synchronized (this.F) {
            long j = (long) (i * 1000);
            DJILogHelper.getInstance().LOGD("", "duration=" + this.i + " seekTo=" + j, false, true);
            this.l = j;
            this.n = 0;
            this.m = 0;
            this.C.a(g.a.Seek);
            FPVController.native_clear();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            FPVController.native_clear();
            this.K = 0;
            this.M = 0;
            this.L = 0;
            DJILogHelper.getInstance().LOGD("", "playedOffset=" + this.m, false, true);
            this.C.b(this.l);
            if (this.s != null) {
                try {
                    this.s.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                this.s = null;
            }
            j();
        }
        this.G = false;
    }

    protected synchronized void g() {
        if (this.s != null) {
            try {
                if (!(this.o == 3 && this.q) && (this.r || this.n - this.m >= ((long) this.u.length))) {
                    int read = this.s.read(this.u, 0, this.u.length);
                    if (read > 0 && !this.G) {
                        b(this.u, read);
                        this.m += (long) read;
                        DJILogHelper.getInstance().LOGD("", "*******************localfile read size=" + read + " playedOffset" + this.m);
                    }
                }
            } catch (IOException e) {
                DJILogHelper.getInstance().LOGD("", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(byte[] r9, int r10) {
        /*
        r8 = this;
        r1 = 1;
        r2 = 0;
        r0 = r8.b;
        if (r0 != 0) goto L_0x003d;
    L_0x0006:
        r8.b = r1;
        r4 = dji.midware.util.c.g(r9, r2);
        r6 = r8.c;
        r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r0 != 0) goto L_0x00b7;
    L_0x0012:
        r0 = r1;
    L_0x0013:
        r8.a = r0;
        r0 = dji.log.DJILogHelper.getInstance();
        r3 = r8.e;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "preMagic=";
        r6 = r6.append(r7);
        r4 = r6.append(r4);
        r5 = " isMixStream=";
        r4 = r4.append(r5);
        r5 = r8.a;
        r4 = r4.append(r5);
        r4 = r4.toString();
        r0.LOGD(r3, r4);
    L_0x003d:
        r0 = dji.log.DJILogHelper.getInstance();
        r3 = r8.e;
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "parseData size=";
        r4 = r4.append(r5);
        r4 = r4.append(r10);
        r5 = " remainSize=";
        r4 = r4.append(r5);
        r5 = r8.M;
        r4 = r4.append(r5);
        r4 = r4.toString();
        r0.LOGD(r3, r4);
        r0 = r8.a;
        if (r0 == 0) goto L_0x00ed;
    L_0x0069:
        r3 = r8.F;
        monitor-enter(r3);
        r0 = r8.M;	 Catch:{ all -> 0x00bf }
        r4 = r8.M;	 Catch:{ all -> 0x00bf }
        r4 = r4 + r10;
        r8.M = r4;	 Catch:{ all -> 0x00bf }
        r4 = r0 + r10;
        r5 = r8.D;	 Catch:{ all -> 0x00bf }
        r5 = r5.length;	 Catch:{ all -> 0x00bf }
        if (r4 < r5) goto L_0x00a2;
    L_0x007a:
        r4 = dji.log.DJILogHelper.getInstance();	 Catch:{ all -> 0x00bf }
        r5 = r8.e;	 Catch:{ all -> 0x00bf }
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00bf }
        r6.<init>();	 Catch:{ all -> 0x00bf }
        r7 = "parseData tmpRemainSize=";
        r6 = r6.append(r7);	 Catch:{ all -> 0x00bf }
        r0 = r6.append(r0);	 Catch:{ all -> 0x00bf }
        r6 = " 太大 丢掉";
        r0 = r0.append(r6);	 Catch:{ all -> 0x00bf }
        r0 = r0.toString();	 Catch:{ all -> 0x00bf }
        r4.LOGD(r5, r0);	 Catch:{ all -> 0x00bf }
        r8.M = r10;	 Catch:{ all -> 0x00bf }
        r0 = 0;
        r8.O = r0;	 Catch:{ all -> 0x00bf }
        r0 = r2;
    L_0x00a2:
        r4 = 0;
        r5 = r8.D;	 Catch:{ Exception -> 0x00ba }
        java.lang.System.arraycopy(r9, r4, r5, r0, r10);	 Catch:{ Exception -> 0x00ba }
    L_0x00a8:
        if (r2 == 0) goto L_0x00ae;
    L_0x00aa:
        r0 = r8.R;	 Catch:{ all -> 0x00bf }
        if (r0 == 0) goto L_0x00c2;
    L_0x00ae:
        r0 = r8.G;	 Catch:{ all -> 0x00bf }
        if (r0 != 0) goto L_0x00c2;
    L_0x00b2:
        r8.u();	 Catch:{ all -> 0x00bf }
        r2 = r1;
        goto L_0x00a8;
    L_0x00b7:
        r0 = r2;
        goto L_0x0013;
    L_0x00ba:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x00bf }
        goto L_0x00a8;
    L_0x00bf:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x00bf }
        throw r0;
    L_0x00c2:
        r0 = r8.K;	 Catch:{ all -> 0x00bf }
        if (r0 <= 0) goto L_0x00e0;
    L_0x00c6:
        r0 = r8.D;	 Catch:{ all -> 0x00bf }
        r1 = r8.K;	 Catch:{ all -> 0x00bf }
        r2 = r8.E;	 Catch:{ all -> 0x00bf }
        r4 = 0;
        r5 = r8.M;	 Catch:{ all -> 0x00bf }
        java.lang.System.arraycopy(r0, r1, r2, r4, r5);	 Catch:{ all -> 0x00bf }
        r0 = r8.E;	 Catch:{ all -> 0x00bf }
        r1 = 0;
        r2 = r8.D;	 Catch:{ all -> 0x00bf }
        r4 = 0;
        r5 = r8.M;	 Catch:{ all -> 0x00bf }
        java.lang.System.arraycopy(r0, r1, r2, r4, r5);	 Catch:{ all -> 0x00bf }
        r0 = 0;
        r8.K = r0;	 Catch:{ all -> 0x00bf }
    L_0x00e0:
        r0 = dji.log.DJILogHelper.getInstance();	 Catch:{ all -> 0x00bf }
        r1 = r8.e;	 Catch:{ all -> 0x00bf }
        r2 = "解完一批包";
        r0.LOGD(r1, r2);	 Catch:{ all -> 0x00bf }
        monitor-exit(r3);	 Catch:{ all -> 0x00bf }
    L_0x00ec:
        return;
    L_0x00ed:
        dji.midware.natives.FPVController.native_transferVideoData(r9, r10);
        goto L_0x00ec;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.midware.media.i.b.b(byte[], int):void");
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
        while (i < this.K + this.M && i < this.D.length) {
            if (this.D[i] == this.d[0]) {
                this.P = i;
                if ((this.P - this.K) + this.d.length >= this.M) {
                    return;
                }
                if (this.D[this.P + 1] == this.d[1] && this.D[this.P + 2] == this.d[2] && this.D[this.P + 3] == this.d[3]) {
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
        System.arraycopy(this.D, this.L, obj, 0, 3);
        int g = (int) dji.midware.util.c.g(obj, 0);
        this.L += 3;
        int i = g + 20;
        if (this.o == 0 || i <= this.M) {
            this.I = (this.D[this.L] & 240) >> 4;
            this.H = this.D[this.L] & 15;
            this.L++;
            System.arraycopy(this.D, this.L, this.J, 0, 2);
            this.L += 3;
            System.arraycopy(this.D, this.L, this.J, 2, 2);
            this.L += 3;
            System.arraycopy(this.D, this.L, this.J, 4, 2);
            this.L += 3;
            System.arraycopy(this.D, this.L, this.J, 6, 2);
            this.L += 2;
            System.arraycopy(this.D, this.L, this.E, 0, g);
            this.K += i;
            this.M -= i;
            if (this.H == 1) {
                FPVController.native_transferVideoDataDirect(this.E, g, this.J, this.J.length);
            } else if (this.H == 2) {
                ServiceManager.getInstance().d();
                FPVController.native_transferAudioData(this.E, g, this.J, this.J.length);
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

    protected void a(long j, long j2, long j3) {
        DJILogHelper.getInstance().LOGD("", "loader pgs (" + j + ", " + j2 + ", " + j3 + ")");
        if (j2 > 0 && this.u != null && FPVController.native_getQueueSize() <= 0) {
            if (this.p || (!this.q && this.n - this.m < ((long) this.u.length))) {
                this.w = ((long) this.u.length) - (this.n - this.m);
                this.v = this.w + j2;
                this.q = true;
                this.p = false;
            }
            if (this.q && this.A != null) {
                int i = (int) ((1.0f - ((((float) (this.v - j2)) * 1.0f) / ((float) this.w))) * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity);
                if (i > 100) {
                    i = 100;
                }
                if (i > 0) {
                    this.A.a(this, i);
                }
            }
            if (this.q && this.v <= j2) {
                this.q = false;
                if (this.o != 3) {
                }
            }
        }
    }
}
