package dji.gs.c;

import android.content.Context;
import android.graphics.Point;
import android.util.SparseArray;
import dji.gs.e.b;
import dji.gs.e.c;
import dji.midware.data.forbid.DJIFlightLimitAreaModel;
import dji.midware.natives.FlyForbid.FlyForbidParam;
import dji.pilot.visual.a.d;
import java.util.ArrayList;

public interface e {
    public static final int a = 500;
    public static final int b = 8000;
    public static final int c = 30000;
    public static final int d = 5000;
    public static final int e = 10;
    public static final int f = 8;
    public static final int g = 800;
    public static final float[] h = new float[]{d.c, 1.0f};

    public enum a {
        GoogleMap,
        HereMap,
        AMap
    }

    void A();

    boolean B();

    b C();

    void D();

    b E();

    float F();

    void G();

    void H();

    void I();

    float a(Point point, Point point2);

    int a(Point point);

    void a();

    void a(float f);

    void a(float f, float f2, boolean z);

    void a(float f, boolean z);

    void a(int i);

    void a(int i, int i2);

    void a(int i, Point point);

    void a(int i, b bVar);

    void a(int i, c cVar);

    void a(int i, dji.gs.e.e eVar);

    void a(int i, dji.gs.views.b bVar);

    void a(int i, b... bVarArr);

    void a(long j, float f, dji.gs.d.a aVar);

    void a(Context context);

    void a(SparseArray<Point> sparseArray);

    void a(dji.gs.d.b bVar);

    void a(dji.gs.d.c cVar);

    void a(dji.gs.d.e eVar);

    void a(b bVar);

    void a(b bVar, double d);

    void a(b bVar, int i);

    void a(b bVar, int i, int i2);

    void a(b bVar, boolean z);

    void a(Object obj);

    void a(ArrayList<DJIFlightLimitAreaModel> arrayList);

    void a(boolean z);

    void a(boolean z, int i);

    void a(boolean z, boolean z2);

    int b();

    Point b(Point point);

    b b(Object obj);

    c b(int i);

    void b(float f);

    void b(int i, int i2);

    void b(b bVar);

    void b(b bVar, double d);

    void b(b bVar, boolean z);

    void b(ArrayList<b> arrayList);

    void b(boolean z);

    ArrayList<? extends c> c();

    void c(float f);

    void c(int i);

    void c(int i, int i2);

    void c(b bVar);

    void c(b bVar, double d);

    void c(ArrayList<b> arrayList);

    void c(boolean z);

    int d();

    void d(int i);

    void d(b bVar);

    void d(ArrayList<b> arrayList);

    void d(boolean z);

    int e();

    Point e(int i);

    void e(b bVar);

    b f();

    void f(int i);

    void f(b bVar);

    void g();

    void g(int i);

    void g(b bVar);

    void h();

    void h(int i);

    void h(b bVar);

    void i();

    void i(b bVar);

    void j();

    void k();

    Point l();

    ArrayList<Point> m();

    FlyForbidParam n();

    boolean o();

    boolean p();

    boolean q();

    boolean r();

    void s();

    boolean t();

    void u();

    void v();

    void w();

    void x();

    void y();

    void z();
}
