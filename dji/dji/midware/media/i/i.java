package dji.midware.media.i;

import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.logic.album.a.b.e;
import dji.logic.album.a.b.e.a;
import dji.logic.album.a.b.e.b;
import dji.logic.album.a.b.e.c;
import dji.logic.album.a.b.e.d;
import dji.logic.album.model.DJIAlbumFile;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.natives.FPVController;
import java.io.IOException;

class i extends d {
    private e a = new e();

    public void a() {
        super.a();
        this.u = null;
        if (this.a != null) {
            this.a.a();
            this.a = null;
        }
    }

    public void a(DJIAlbumFileInfo dJIAlbumFileInfo) {
        super.a(dJIAlbumFileInfo);
        this.i = dJIAlbumFileInfo.f;
        this.a.a(dJIAlbumFileInfo, this.x);
        this.a.a(new c(this) {
            final /* synthetic */ i a;

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
        this.a.a(new d(this) {
            final /* synthetic */ i a;

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
        this.a.a(new a(this) {
            final /* synthetic */ i a;

            {
                this.a = r1;
            }

            public void a(long j) {
                this.a.n = j;
                if (this.a.a.j()) {
                    this.a.k = this.a.i;
                    return;
                }
                this.a.k = (int) ((((float) (this.a.l + this.a.n)) * 1.0f) / 262144.0f);
            }
        });
        this.a.a(new b(this) {
            final /* synthetic */ i a;

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
        return this.a.c();
    }

    protected void c() {
        if (this.a != null) {
            this.a.k();
        }
        super.c();
    }

    protected void d() {
        this.a.b();
        j();
    }

    protected void e() {
        this.a.e();
    }

    protected void a(int i) {
        long j = (long) (((((float) i) * 1.0f) / ((float) this.i)) * ((float) this.h.a));
        DJILogHelper.getInstance().LOGD("", "fileInfo.length=" + this.h.a + " fileLen=" + this.n, false, true);
        if (this.h.a == this.n || (j > this.l && (this.r || j < (this.l + this.n) - ((long) this.u.length)))) {
            DJILogHelper.getInstance().LOGD("", "local file seekTo " + j, true, true);
            this.m = (this.n + j) - this.h.a;
            this.l = j;
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
                return;
            }
            return;
        }
        DJILogHelper.getInstance().LOGD("", "remote file seekTo " + j, true, true);
        this.l = j;
        this.n = 0;
        this.m = 0;
        e();
        ServiceManager.getInstance().pauseParseThread();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        FPVController.native_clear();
        ServiceManager.getInstance().resumeParseThread();
        DJILogHelper.getInstance().LOGD("", "remote file isCached " + this.a.j(), true, true);
        if (!this.a.j()) {
            this.a.a(this.l);
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

    protected void a(long j, long j2, long j3) {
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
                this.A.a(this, i);
            }
            if (this.q && this.v <= j2) {
                this.q = false;
            }
        }
    }
}
