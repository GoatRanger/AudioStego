package dji.midware.media.i;

import android.os.Process;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.logic.album.model.DJIAlbumFile;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.natives.FPVController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Timer;
import java.util.TimerTask;

public abstract class d {
    protected static final int f = 262144;
    protected static boolean g = false;
    protected a A;
    protected e B;
    private b a;
    private c b;
    private f c;
    protected String e = getClass().getSimpleName();
    protected DJIAlbumFileInfo h;
    protected int i;
    protected int j;
    protected int k;
    protected long l;
    protected long m = 0;
    protected long n;
    protected int o = 0;
    protected boolean p = false;
    protected boolean q = false;
    protected boolean r = false;
    protected RandomAccessFile s;
    protected Timer t;
    protected byte[] u = new byte[1048576];
    protected long v;
    protected long w;
    protected dji.logic.album.a.d.a<DJIAlbumFile> x = new dji.logic.album.a.d.a<DJIAlbumFile>(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.r = false;
        }

        public void a(long j, long j2) {
        }

        public void a(long j, long j2, long j3) {
            this.a.a(j, j2, j3);
        }

        public void a(DJIAlbumFile dJIAlbumFile) {
            if (this.a.q && this.a.A != null) {
                this.a.A.a(this.a, 100);
            }
            this.a.q = false;
            this.a.r = true;
            if (d.g) {
                DJILogHelper.getInstance().LOGD("mediaPlayer", "*****isBuffered*****", true, true);
            }
        }

        public void a(DJIAlbumPullErrorType dJIAlbumPullErrorType) {
            if (dJIAlbumPullErrorType == DJIAlbumPullErrorType.TIMEOUT) {
                this.a.c();
            } else if (this.a.b != null) {
                this.a.b.a(this.a, dJIAlbumPullErrorType);
            }
        }
    };
    protected int y = 0;
    protected d z;

    public interface a {
        void a(d dVar, int i);
    }

    public interface b {
        void a(d dVar);
    }

    public interface c {
        void a(d dVar, DJIAlbumPullErrorType dJIAlbumPullErrorType);
    }

    public interface d {
        void a(d dVar);
    }

    public interface e {
        void a(d dVar);
    }

    public interface f {
        void a(d dVar, int i, int i2);
    }

    private class g extends Thread {
        final /* synthetic */ d a;

        private g(d dVar) {
            this.a = dVar;
        }

        public void run() {
            Process.setThreadPriority(-16);
            while (this.a.o != 0) {
                this.a.y = FPVController.native_getQueueSize();
                if (this.a.o == 10 && this.a.y == 0 && this.a.n == this.a.m && this.a.r) {
                    this.a.s();
                    if (d.g) {
                        DJILogHelper.getInstance().LOGD(getName(), "*********localfile stop**********");
                    }
                } else if (this.a.y < dji.gs.c.e.g && (!this.a.q || this.a.r)) {
                    this.a.g();
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            FPVController.native_clear();
            if (d.g) {
                DJILogHelper.getInstance().LOGD(getName(), "*********playthread over**********");
            }
        }
    }

    protected abstract void a(int i);

    protected abstract void a(long j, long j2, long j3);

    protected abstract String b();

    protected abstract void d();

    protected abstract void e();

    public void a() {
        if (this.t != null) {
            this.t.cancel();
            this.t = null;
        }
        this.h = null;
    }

    public int h() {
        return this.i;
    }

    public int i() {
        return this.j;
    }

    public void a(DJIAlbumFileInfo dJIAlbumFileInfo) {
        this.h = DJIAlbumFileInfo.a(dJIAlbumFileInfo);
    }

    protected void j() {
        long j = 0;
        if (this.s != null) {
            try {
                j = this.s.getFilePointer();
                this.s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String b = b();
        DJILogHelper.getInstance().LOGD("mediaPlayer", "path=" + b);
        try {
            this.s = new RandomAccessFile(new File(b), "rws");
            this.s.seek(j);
        } catch (FileNotFoundException e2) {
            if (g) {
                DJILogHelper.getInstance().LOGD("mediaPlayer", e2.getMessage(), true, true);
            }
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public void k() {
        f();
        this.j = 0;
        ServiceManager.getInstance().a(true);
        ServiceManager.getInstance().resumeParseThread();
        FPVController.native_setFrameRate(this.h.h);
        this.o = 1;
        d();
        new g().start();
        l();
    }

    protected void l() {
        if (this.t != null) {
            this.t.cancel();
        }
        this.t = new Timer();
        this.t.schedule(new TimerTask(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.y != 0 && this.a.o == 10 && !this.a.q) {
                    d dVar = this.a;
                    dVar.j++;
                    if (this.a.c != null) {
                        this.a.c.a(this.a, this.a.j, this.a.k);
                    }
                    if (this.a.j == this.a.i) {
                        this.a.s();
                        if (d.g) {
                            DJILogHelper.getInstance().LOGD("", "*********play time over**********", true, true);
                        }
                    }
                } else if (this.a.o == 2 && this.a.c != null) {
                    this.a.c.a(this.a, this.a.j, this.a.k);
                }
            }
        }, 1000, 1000);
    }

    public void m() {
        if (this.o != 0) {
            this.o = 2;
            ServiceManager.getInstance().pauseParseThread();
        }
    }

    public void n() {
        if (this.o != 0) {
            this.o = 10;
            this.p = true;
            ServiceManager.getInstance().resumeParseThread();
        }
    }

    public void o() {
        ServiceManager.getInstance().resumeParseThread();
        if (g) {
            DJILogHelper.getInstance().LOGD("", "*********play do stop**********", true, true);
        }
        this.j = 0;
        if (this.o != 0) {
            this.o = 0;
            if (this.t != null) {
                this.t.cancel();
                this.t = null;
            }
            try {
                if (this.s != null) {
                    this.s.close();
                    this.s = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServiceManager.getInstance().a(false);
            e();
            f();
        }
    }

    public boolean p() {
        return this.i != 0;
    }

    public void b(int i) {
        if (p()) {
            g gVar;
            if (this.o == 0) {
                this.n = 0;
                ServiceManager.getInstance().a(true);
                j();
                gVar = new g();
                l();
            } else {
                gVar = null;
            }
            this.o = 3;
            if (gVar != null) {
                gVar.start();
            }
            this.j = i;
            a(i);
        }
    }

    private void s() {
        o();
        if (this.a != null) {
            this.a.a(this);
        }
        this.j = this.i;
        if (this.c != null) {
            this.c.a(this, this.j, this.k);
        }
    }

    protected void f() {
        this.q = false;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        FPVController.native_clear();
    }

    public int q() {
        return this.o;
    }

    public boolean r() {
        return this.q;
    }

    protected void a(byte[] bArr, int i) {
        FPVController.native_transferVideoData(bArr, i);
    }

    protected synchronized void g() {
        try {
            if (this.r || this.n - this.m >= ((long) this.u.length)) {
                DJILogHelper.getInstance().LOGD(getClass().getSimpleName(), "*******************localfile read start " + this.s.length() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.s.getFilePointer());
                int read = this.s.read(this.u, 0, this.u.length);
                if (read > 0) {
                    a(this.u, read);
                    this.m += (long) read;
                    if (g) {
                        DJILogHelper.getInstance().LOGD(getClass().getSimpleName(), "*******************localfile read size=" + read + " qsize=" + FPVController.native_getQueueSize() + " fileLen=" + this.n + " remain size=" + (this.n - this.m));
                    }
                } else if (g) {
                    DJILogHelper.getInstance().LOGD(getClass().getSimpleName(), "*******************localfile read size error=" + read + " qsize=" + FPVController.native_getQueueSize());
                }
            } else if (g) {
                DJILogHelper.getInstance().LOGD(getClass().getSimpleName(), "*******************localfile remain size=" + (this.n - this.m));
            }
        } catch (IOException e) {
            if (g) {
                DJILogHelper.getInstance().LOGD(getClass().getSimpleName(), e.getMessage(), true, true);
            }
            e.printStackTrace();
        }
    }

    protected void c() {
    }

    public void a(d dVar) {
        this.z = dVar;
    }

    public void a(b bVar) {
        this.a = bVar;
    }

    public void a(a aVar) {
        this.A = aVar;
    }

    public void a(e eVar) {
        this.B = eVar;
    }

    public void a(c cVar) {
        this.b = cVar;
    }

    public void a(f fVar) {
        this.c = fVar;
    }
}
