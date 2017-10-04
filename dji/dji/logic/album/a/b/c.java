package dji.logic.album.a.b;

import android.graphics.Bitmap;
import com.dji.frame.c.f;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.logic.album.a.d.a;
import dji.logic.album.model.DJIAlbumFile;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.logic.album.model.DJIAlbumPullErrorType;
import dji.midware.data.a.b.b;
import dji.midware.data.manager.P3.DJIVideoPackManager;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.d.e;
import dji.midware.data.model.d.g;
import dji.midware.data.model.d.h;
import dji.midware.data.model.d.i;
import java.io.File;
import org.msgpack.core.MessagePack.Code;

public class c extends g<DJIAlbumFile> {
    protected int a;
    protected int b;
    protected DJIAlbumFileInfo c;
    protected DJIAlbumFile d;
    protected String e;
    protected byte[] f;
    protected dji.midware.data.config.a.a.c g;
    private int h;

    public c() {
        this.h = 0;
        this.d = new DJIAlbumFile();
        this.B = 1;
        this.r = 500;
    }

    public void a() {
        r();
    }

    public void a(DJIAlbumFileInfo dJIAlbumFileInfo, a<DJIAlbumFile> aVar) {
        this.c = dJIAlbumFileInfo;
        this.D = aVar;
        this.d.a = dJIAlbumFileInfo.d;
        this.d.d = dJIAlbumFileInfo.j;
    }

    public void a(dji.midware.data.config.a.a.c cVar) {
        this.g = cVar;
        switch (cVar) {
            case THM:
                this.e = this.c.c();
                this.B = 2;
                this.r = 500;
                return;
            case Pano_SCR:
                this.e = this.c.e();
                this.B = 2;
                this.r = 500;
                return;
            case TIMELAPSE:
            case SCR:
                this.e = this.c.d();
                this.B = 2;
                this.r = 500;
                return;
            case Pano_THM:
                this.e = this.c.g();
                this.B = 2;
                this.r = 500;
                return;
            default:
                this.D.a(DJIAlbumPullErrorType.ERROR_REQ);
                return;
        }
    }

    private boolean j() {
        if (this.C.b(this.e)) {
            this.d.e = this.C.a(this.e);
            this.K.sendMessage(this.K.obtainMessage(0, this.d));
            return true;
        } else if (!this.C.d(this.e)) {
            return false;
        } else {
            this.d.e = this.C.c(this.e);
            this.C.a(this.e, this.d.e);
            this.K.sendMessage(this.K.obtainMessage(0, this.d));
            return true;
        }
    }

    public void b() {
        super.b();
        this.v = true;
        this.x = 0;
        this.z = 0;
        this.u = false;
        this.d.b = 0;
        if (!j()) {
            g.getInstance().a(dji.midware.data.config.a.a.a.File).start(null);
            DJIVideoPackManager.getInstance().start();
            i.getInstance().a(this.c.d).c(this.c.e).b(1).a(this.g).b(-1).a(0).start(null);
            p();
        }
    }

    public void d() {
        q();
        DJIVideoPackManager.getInstance().stop();
    }

    public void e() {
        if (this.v) {
            g.getInstance().a(dji.midware.data.config.a.a.a.File).start(null);
            this.K.sendMessage(this.K.obtainMessage(1, DJIAlbumPullErrorType.CLIENT_ABORT));
        }
        d();
    }

    protected void f() {
        this.D.a(this.d.b, (long) this.z, ((long) this.z) - this.A);
        this.A = (long) this.z;
    }

    protected void g() {
        this.D.a(this.d.b, (long) this.z);
    }

    public void onEventBackgroundThread(dji.midware.data.model.d.c cVar) {
        int i = 0;
        if (this.v) {
            b a = cVar.a();
            this.h++;
            if (this.h % 100 == 0) {
                DJILogHelper.getInstance().LOGD(this.q, "seq=" + this.x + " 实际=" + a.h + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + System.currentTimeMillis(), true, false);
            }
            if (a.h == this.x) {
                int length;
                s();
                this.u = false;
                if (a.h == 0) {
                    i = cVar.g();
                    this.d.b = (long) (cVar.c() - i);
                    this.f = new byte[(((int) this.d.b) + 2)];
                    length = a.i.length - i;
                    DJILogHelper.getInstance().LOGD(this.q, "infolen=" + i, true, true);
                } else {
                    length = a.i.length;
                }
                System.arraycopy(a.i, i, this.f, this.z, length);
                this.z += length;
                this.K.sendEmptyMessage(2);
                if (((long) this.z) == this.d.b) {
                    c();
                } else {
                    this.x++;
                }
            } else if (a.h > this.x) {
                h();
            }
        }
    }

    protected synchronized void h() {
        if (!this.u) {
            this.u = true;
            DJILogHelper.getInstance().LOGD(this.q, "重发 curSeq=" + this.x, true, true);
            DJIVideoPackManager.getInstance().clearVideoData();
            if (this.x == 0) {
                i.getInstance().a(this.c.d).b(1).a(this.g).b(-1).a(0).start(null);
            } else {
                h.getInstance().a(dji.midware.data.config.a.a.a.File).a(this.x).b(1).start(null);
            }
            t();
        }
    }

    protected void c() {
        Bitmap a;
        g.getInstance().a(dji.midware.data.config.a.a.a.File).start(null);
        DJILogHelper.getInstance().LOGD(this.q, "recvOver " + System.currentTimeMillis(), true, true);
        q();
        if (!(this.f[this.z - 1] == Code.STR8 || this.c.j == f.TIF)) {
            this.f[this.z] = (byte) -1;
            this.f[this.z + 1] = Code.STR8;
            this.z += 2;
        }
        if (this.c.j == f.TIF && this.g == dji.midware.data.config.a.a.c.SCR) {
            DJILogHelper.getInstance().LOGD(this.q, "index=" + this.c.d + " len=" + this.c.a);
        }
        CameraType cameraType = DataCameraGetPushStateInfo.getInstance().getCameraType();
        if ((this.c.j == f.MOV || this.c.j == f.MP4) && cameraType != CameraType.DJICameraTypeTau336 && cameraType != CameraType.DJICameraTypeTau640 && cameraType != CameraType.DJICameraTypeFC6310 && cameraType != CameraType.DJICameraTypeGD600) {
            a = dji.midware.util.g.a(this.f);
        } else if (this.c.j == f.TIF) {
            f.a(this.C.h(this.e.replace(dji.pilot2.main.a.a.n, ".tif")), this.f, 0, this.z);
            a = dji.midware.util.g.d(this.f, 0, this.z);
        } else {
            a = dji.midware.util.g.c(this.f, 0, this.z);
        }
        this.d.e = a;
        if (a != null) {
            this.C.b(this.e, a);
            if (this.c.j == f.TIF && this.g == dji.midware.data.config.a.a.c.SCR) {
                dji.b.a.a.a.a(new File(this.C.h(this.e.replace(dji.pilot2.main.a.a.n, ".tif"))), new File(this.C.h(this.e)), null);
            }
            this.C.a(this.e, a);
        }
        this.K.sendMessage(this.K.obtainMessage(0, this.d));
    }

    public void onEventBackgroundThread(e eVar) {
        if (!this.v) {
        }
    }
}
