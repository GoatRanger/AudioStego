package dji.pilot.phonecamera;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.Camera.Area;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.location.Location;
import android.os.Handler;
import dji.pilot.phonecamera.e.d;
import dji.pilot.phonecamera.e.f;
import dji.pilot.phonecamera.e.g;
import java.util.List;

public interface c {
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = -1;
    public static final int e = -2;
    public static final int f = -3;
    public static final int g = -4;
    public static final int h = -5;
    public static final int i = 0;
    public static final int j = 1;

    public enum a {
        AUTOFUCUS,
        WB,
        SCENE,
        EXPOSURE,
        HDR,
        FLASH
    }

    int a();

    g a(int i);

    g a(Handler handler, int i, d dVar);

    g a(d dVar);

    void a(int i, int i2, Context context, d dVar);

    void a(SurfaceTexture surfaceTexture);

    void a(Parameters parameters);

    void a(Handler handler, dji.pilot.phonecamera.e.a aVar);

    void a(Handler handler, f fVar);

    void a(String str);

    void a(List<Area> list);

    void a(boolean z);

    boolean a(a aVar);

    g b();

    void b(int i);

    void b(String str);

    void b(List<Area> list);

    void c();

    void c(int i);

    void c(String str);

    void d(int i);

    boolean d();

    int e();

    boolean f();

    int h();

    int i();

    void j();

    void k();

    void l();

    Parameters m();

    void n();

    void o();

    void p();

    List<String> q();

    List<String> r();

    List<String> s();

    int t();

    int u();

    float v();

    Size w();

    int x();

    Location y();
}
