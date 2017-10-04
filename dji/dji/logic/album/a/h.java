package dji.logic.album.a;

import dji.logic.album.a.b.a;
import dji.logic.album.a.b.b;
import dji.logic.album.a.b.c;
import dji.logic.album.model.DJIAlbumDirInfo;
import dji.logic.album.model.DJIAlbumFile;
import dji.logic.album.model.DJIAlbumFileInfo;
import java.util.ArrayList;

class h extends e {
    a c;
    c d;
    b e;

    public void d() {
        if (this.c != null) {
            this.c = null;
        }
        if (this.d != null) {
            this.d = null;
        }
        if (this.e != null) {
            this.e = null;
        }
    }

    public synchronized void a(d.a<DJIAlbumDirInfo> aVar) {
        e();
        if (this.c == null) {
            this.c = new a();
        }
        this.c.a(aVar);
        this.c.b();
    }

    public synchronized void c(DJIAlbumFileInfo dJIAlbumFileInfo, d.a<DJIAlbumFile> aVar) {
        e();
        if (this.e == null) {
            this.e = new b();
        }
        this.e.a(dJIAlbumFileInfo, aVar);
        this.e.a(dji.midware.data.config.a.a.c.ORG);
        this.e.b();
    }

    public void a(ArrayList<DJIAlbumFileInfo> arrayList, d.a<ArrayList<DJIAlbumFile>> aVar) {
    }

    public synchronized void a(DJIAlbumFileInfo dJIAlbumFileInfo, d.a<DJIAlbumFile> aVar) {
        e();
        if (this.d == null) {
            this.d = new c();
        }
        this.d.a(dJIAlbumFileInfo, aVar);
        this.d.a(dji.midware.data.config.a.a.c.THM);
        this.d.b();
    }

    public synchronized void b(DJIAlbumFileInfo dJIAlbumFileInfo, d.a<DJIAlbumFile> aVar) {
        e();
        if (this.d == null) {
            this.d = new c();
        }
        this.d.a(dJIAlbumFileInfo, aVar);
        this.d.a(dji.midware.data.config.a.a.c.SCR);
        this.d.b();
    }

    private void e() {
        if (this.c != null) {
            this.c.e();
        }
        if (this.d != null) {
            this.d.e();
        }
        if (this.e != null) {
            this.e.e();
        }
    }

    public synchronized void c() {
        e();
    }

    public void a() {
    }

    public void b() {
    }

    public void a(DJIAlbumDirInfo dJIAlbumDirInfo) {
    }

    public void a(DJIAlbumFileInfo dJIAlbumFileInfo) {
    }

    public synchronized void f(DJIAlbumFileInfo dJIAlbumFileInfo, d.a<DJIAlbumFile> aVar) {
        e();
        if (this.e == null) {
            this.e = new b();
        }
        this.e.a(dJIAlbumFileInfo, aVar);
        this.e.a(dji.midware.data.config.a.a.c.Pano);
        this.e.b();
    }

    public synchronized void d(DJIAlbumFileInfo dJIAlbumFileInfo, d.a<DJIAlbumFile> aVar) {
        e();
        if (this.d == null) {
            this.d = new c();
        }
        this.d.a(dJIAlbumFileInfo, aVar);
        this.d.a(dji.midware.data.config.a.a.c.Pano_THM);
        this.d.b();
    }

    public synchronized void e(DJIAlbumFileInfo dJIAlbumFileInfo, d.a<DJIAlbumFile> aVar) {
        e();
        if (this.d == null) {
            this.d = new c();
        }
        this.d.a(dJIAlbumFileInfo, aVar);
        this.d.a(dji.midware.data.config.a.a.c.Pano_SCR);
        this.d.b();
    }

    public void g(DJIAlbumFileInfo dJIAlbumFileInfo, d.a<DJIAlbumFile> aVar) {
        e();
        if (this.d == null) {
            this.d = new c();
        }
        this.d.a(dJIAlbumFileInfo, aVar);
        this.d.a(dji.midware.data.config.a.a.c.TIMELAPSE);
        this.d.b();
    }
}
