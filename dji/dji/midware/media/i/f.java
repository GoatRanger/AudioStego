package dji.midware.media.i;

import android.util.Log;
import dji.log.DJILogHelper;
import dji.logic.album.a.b.e;
import dji.logic.album.a.b.e.b;
import dji.logic.album.model.DJIAlbumFile;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.media.DJIVideoDataRecver;
import dji.midware.media.f.c;
import dji.midware.media.f.d;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class f extends d implements dji.logic.album.a.a.a.a {
    private long C = 0;
    protected dji.logic.album.a.d.a<DJIAlbumFile> a = new dji.logic.album.a.d.a<DJIAlbumFile>(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public void a() {
        }

        public void a(long j, long j2) {
        }

        public void a(long j, long j2, long j3) {
        }

        public void a(DJIAlbumFile dJIAlbumFile) {
        }

        public void a(DJIAlbumPullErrorType dJIAlbumPullErrorType) {
        }
    };
    private dji.logic.album.a.a.a b = new dji.logic.album.a.a.a();
    private d c;
    private int d = 0;

    private class a extends Thread {
        final /* synthetic */ f a;

        private a(f fVar) {
            this.a = fVar;
        }

        public void run() {
            while (this.a.o != 0 && this.a.o != 2 && this.a.d < this.a.c.k) {
                try {
                    int i;
                    if (this.a.c.k - this.a.d > this.a.c.j * 5) {
                        i = this.a.c.j * 5;
                    } else {
                        i = (this.a.c.k - this.a.d) - 1;
                    }
                    if (((long) this.a.c.e[this.a.d + i]) < this.a.s.length()) {
                        int a = this.a.d + i;
                        while (this.a.d < a) {
                            this.a.y = 100;
                            this.a.r = true;
                            this.a.q = false;
                            this.a.o = 10;
                            Log.e(this.a.e, "buffer read: " + (this.a.c.e[this.a.d] + this.a.c.d[this.a.d]) + " write: " + this.a.s.length());
                            byte[] bArr = new byte[this.a.c.d[this.a.d]];
                            this.a.s.seek((long) this.a.c.e[this.a.d]);
                            this.a.s.read(bArr);
                            i = 0;
                            while (i < this.a.c.d[this.a.d]) {
                                int b = c.b(bArr, i);
                                if (b == 0) {
                                    Log.e(this.a.e, "transform 264 error: index=" + i + "error part:" + c.d(bArr, i, 10));
                                    break;
                                }
                                bArr[i] = (byte) 0;
                                i++;
                                bArr[i] = (byte) 0;
                                i++;
                                bArr[i] = (byte) 0;
                                i++;
                                bArr[i] = (byte) 1;
                                i = (i + 1) + b;
                            }
                            DJIVideoDataRecver.getInstance().onVideoRecv(bArr, 0, this.a.c.d[this.a.d], this.a.d, false, -1, 0, -1, 0, this.a.c.b, this.a.c.a, false);
                            this.a.d = this.a.d + 1;
                            try {
                                Thread.sleep((long) (1000 / this.a.c.j));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        this.a.r = false;
                        this.a.q = true;
                        Log.e(this.a.e, "waitting buffer read: " + this.a.c.e[i + this.a.d] + " write: " + this.a.C);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    public void a(DJIAlbumFileInfo dJIAlbumFileInfo) {
        super.a(dJIAlbumFileInfo);
        this.i = dJIAlbumFileInfo.f;
        this.b.a(dJIAlbumFileInfo, this.a);
        this.b.a((dji.logic.album.a.a.a.a) this);
        this.b.a(new dji.logic.album.a.b.e.a(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void a(long j) {
                try {
                    Log.e("onChange", "onchange size:" + j);
                    this.a.s.close();
                    this.a.s = new RandomAccessFile(new File(this.a.b()), "rws");
                    Log.e("onChange", "file length:" + this.a.s.length());
                    this.a.C = j;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        this.b.a(new b(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void a() {
                DJILogHelper.getInstance().LOGD("mediaPlayer", "OnCacheRename reopen");
                this.a.k = this.a.i;
                this.a.j();
            }
        });
        this.b.a(new e.c(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void a(DJIAlbumFile dJIAlbumFile) {
                this.a.o = 10;
                this.a.h.a = dJIAlbumFile.b;
                if (this.a.z != null) {
                    this.a.z.a(this.a);
                }
            }
        });
    }

    public void k() {
        Log.e(this.e, "start preload");
        s();
    }

    protected String b() {
        return this.b.c();
    }

    protected void s() {
        j();
        if (this.b.j()) {
            byte[] bArr = new byte[100];
            try {
                this.s.read(bArr);
                int a = dji.midware.media.f.f.getInstance().a(bArr);
                this.s.seek((long) a);
                byte[] bArr2 = new byte[8];
                this.s.read(bArr2);
                int b = c.b(bArr2, 0);
                if (dji.midware.media.f.b.moov.a(c.a(bArr2, 4, 4))) {
                    bArr2 = new byte[b];
                    this.s.seek((long) a);
                    this.s.read(bArr2);
                    dji.midware.media.f.f.getInstance().b(bArr2);
                    f_();
                    return;
                }
                Log.e(this.e, "not find box moov");
                return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        this.b.e_();
    }

    protected void d() {
        this.c = dji.midware.media.f.f.getInstance().a;
        this.d = 0;
        this.j = 0;
        this.b.a(0);
        new a().start();
        l();
    }

    protected void e() {
        this.b.e();
    }

    protected void a(int i) {
    }

    protected void a(long j, long j2, long j3) {
    }

    public void a() {
        if (this.b != null) {
            this.b.a();
            this.b = null;
        }
    }

    public void f_() {
        this.o = 1;
        d();
    }
}
