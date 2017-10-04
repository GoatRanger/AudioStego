package dji.phone.c;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera.Area;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.location.Location;
import android.media.MediaRecorder;
import android.os.Handler;
import android.util.Log;
import dji.pilot.phonecamera.c;
import dji.pilot.phonecamera.e;
import dji.pilot.phonecamera.e.a;
import dji.pilot.phonecamera.e.d;
import dji.pilot.phonecamera.e.f;
import dji.pilot.phonecamera.g;
import dji.pilot.phonecamera.j;
import java.util.List;

public class b implements c {
    public static final Object a = new Object();
    private final String k = "DJILPCameraModuleProxy";
    private g l;

    public b(g gVar) {
        this.l = gVar;
    }

    public int a() {
        return this.l.a();
    }

    public void a(int i, int i2, Context context, d dVar) {
        this.l.a(i, i2, context, dVar);
    }

    public e.g b() {
        return this.l.b();
    }

    public e.g a(int i) {
        return this.l.a(i);
    }

    public e.g a(d dVar) {
        return this.l.a(dVar);
    }

    public e.g a(Handler handler, int i, d dVar) {
        return this.l.a(handler, i, dVar);
    }

    public void c() {
        if (this.l != null) {
            this.l.c();
        }
        this.l = null;
    }

    public boolean d() {
        return this.l.d();
    }

    public int e() {
        if (this.l instanceof j) {
            return this.l.e();
        }
        Log.d("DJILPCameraModuleProxy", "startVideoRecording: mActiveCameraModule is not DJILPVideoModule");
        return 1;
    }

    public boolean f() {
        if (this.l instanceof j) {
            this.l.f();
            return true;
        }
        Log.d("DJILPCameraModuleProxy", "startVideoRecording: mActiveCameraModule is not DJILPVideoModule");
        return false;
    }

    public MediaRecorder g() {
        if (this.l instanceof j) {
            return ((j) this.l).z();
        }
        Log.d("DJILPCameraModuleProxy", "getMediaRecorder: MediaRecorder is null");
        return null;
    }

    public void b(int i) {
        this.l.b(i);
        if (dji.pilot.phonecamera.a.c.a().s() == dji.pilot.phonecamera.d.a().i()) {
            dji.phone.d.d.getInstance().a(dji.phone.d.c.b.CAMERA_BACK, true);
        } else if (dji.pilot.phonecamera.a.c.a().s() == dji.pilot.phonecamera.d.a().j()) {
            dji.phone.d.d.getInstance().a(dji.phone.d.c.b.CAMERA_FRONT, true);
        }
    }

    public void a(String str) {
        this.l.a(str);
    }

    public void b(String str) {
        this.l.b(str);
    }

    public void c(int i) {
        this.l.c(i);
    }

    public int h() {
        return this.l.h();
    }

    public int i() {
        return this.l.i();
    }

    public void j() {
        this.l.j();
    }

    public void a(SurfaceTexture surfaceTexture) {
        this.l.a(surfaceTexture);
    }

    public void k() {
        this.l.k();
    }

    public void l() {
        this.l.l();
    }

    public Parameters m() {
        return this.l.m();
    }

    public void a(Parameters parameters) {
        this.l.a(parameters);
    }

    public void a(Handler handler, a aVar) {
        this.l.a(handler, aVar);
    }

    public void n() {
        this.l.n();
    }

    public void o() {
        this.l.o();
    }

    public void c(String str) {
        this.l.c(str);
    }

    public void p() {
        this.l.p();
    }

    public void d(int i) {
        this.l.d(i);
    }

    public boolean a(c.a aVar) {
        return this.l.a(aVar);
    }

    public List<String> q() {
        return this.l.q();
    }

    public List<String> r() {
        return this.l.r();
    }

    public List<String> s() {
        return this.l.s();
    }

    public int t() {
        return this.l.t();
    }

    public int u() {
        return this.l.u();
    }

    public float v() {
        return this.l.v();
    }

    public Size w() {
        return this.l.w();
    }

    public void a(boolean z) {
        if (this.l != null) {
            this.l.a(z);
        }
    }

    public int x() {
        return this.l.x();
    }

    public void e(int i) {
        if (i == 0) {
            this.l = a.d();
        } else if (i == 1) {
            this.l = a.e();
        }
    }

    public void a(List<Area> list) {
        this.l.a(list);
    }

    public synchronized void b(List<Area> list) {
        this.l.b(list);
    }

    public void a(Handler handler, f fVar) {
        this.l.a(handler, fVar);
    }

    public Location y() {
        return this.l.y();
    }
}
