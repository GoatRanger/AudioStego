package dji.d.a;

import dji.common.flightcontroller.DJIGPSSignalStatus;
import dji.common.flightcontroller.DJILocationCoordinate2D;
import dji.common.util.CallbackUtils;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.hardware.abstractions.a;
import dji.sdksharedlib.hardware.abstractions.b;
import java.util.concurrent.TimeUnit;

public class d extends dji.sdksharedlib.hardware.abstractions.d.d {
    protected static final double a = 8.99322E-6d;
    protected static final int b = 300;
    float c = 0.0f;
    float d = 0.0f;
    float e = 0.0f;
    float f = 0.0f;
    DJIGPSSignalStatus g = DJIGPSSignalStatus.find(0);
    DJILocationCoordinate2D h = new DJILocationCoordinate2D(37.421972d, -122.137385d);
    double i = this.h.getLatitude();
    double j = this.h.getLongitude();
    double k = 0.0d;
    double l = 0.0d;
    double m = 0.0d;
    boolean n = true;
    int o = 0;
    boolean p = false;
    boolean q = false;
    boolean r = false;
    boolean s = false;
    boolean t = false;
    boolean u = false;

    public d() {
        r();
        s();
        t();
        u();
        w();
        x();
        y();
        z();
        A();
        B();
        b();
        c();
        v();
        d();
    }

    protected void a() {
        super.a();
        a(e.class, getClass());
    }

    private void r() {
        dji.thirdparty.f.d.b(100, TimeUnit.MILLISECONDS, dji.thirdparty.f.j.e.c()).n(new 1(this)).w().C();
    }

    private void s() {
        dji.thirdparty.f.d.b(100, TimeUnit.MILLISECONDS, dji.thirdparty.f.j.e.c()).n(new 7(this)).w().C();
    }

    private void t() {
        dji.thirdparty.f.d.b(200, TimeUnit.MILLISECONDS, dji.thirdparty.f.j.e.c()).n(new 8(this)).w().C();
    }

    private void u() {
        dji.thirdparty.f.d.b(300, TimeUnit.MILLISECONDS, dji.thirdparty.f.j.e.c()).n(new 9(this)).w().C();
    }

    private int b(int i) {
        if (i == 0 || i >= 50) {
            return 0;
        }
        if (i <= 7) {
            return 1;
        }
        if (i > 10) {
            return 5;
        }
        return i - 6;
    }

    private void v() {
        dji.thirdparty.f.d.b(1000, TimeUnit.MILLISECONDS, dji.thirdparty.f.j.e.c()).n(new 10(this)).w().C();
    }

    private void w() {
        dji.thirdparty.f.d.b(300, TimeUnit.MILLISECONDS, dji.thirdparty.f.j.e.c()).n(new 11(this)).w().C();
    }

    private void x() {
        dji.thirdparty.f.d.b(300, TimeUnit.MILLISECONDS, dji.thirdparty.f.j.e.c()).n(new 12(this)).w().C();
    }

    private void y() {
        dji.thirdparty.f.d.b(300, TimeUnit.MILLISECONDS, dji.thirdparty.f.j.e.c()).n(new 13(this)).w().C();
    }

    private void z() {
        dji.thirdparty.f.d.b(300, TimeUnit.MILLISECONDS, dji.thirdparty.f.j.e.c()).n(new 14(this)).w().C();
    }

    private void A() {
        dji.thirdparty.f.d.b(300, TimeUnit.MILLISECONDS, dji.thirdparty.f.j.e.c()).n(new 2(this)).w().C();
    }

    private void B() {
        dji.thirdparty.f.d.b(300, TimeUnit.MILLISECONDS, dji.thirdparty.f.j.e.c()).n(new 3(this)).w().C();
    }

    @a(a = "TakeOff")
    public void a(b.e eVar) {
        this.q = true;
        this.t = true;
        this.u = true;
        CallbackUtils.onSuccess(eVar, null);
    }

    @a(a = "AutoLanding")
    public void b(b.e eVar) {
        this.r = true;
        CallbackUtils.onSuccess(eVar, null);
    }

    @a(a = "CancelAutoLanding")
    public void c(b.e eVar) {
        this.r = false;
        CallbackUtils.onSuccess(eVar, null);
    }

    @a(a = "TurnOnMotors")
    public void d(b.e eVar) {
        this.t = true;
        CallbackUtils.onSuccess(eVar, null);
    }

    @a(a = "TurnOffMotors")
    public void e(b.e eVar) {
        this.t = false;
        CallbackUtils.onSuccess(eVar, null);
    }

    @a(a = "GoHome")
    public void f(b.e eVar) {
        this.s = true;
        CallbackUtils.onSuccess(eVar, null);
    }

    @a(a = "CancelGoHome")
    public void g(b.e eVar) {
        this.s = false;
        CallbackUtils.onSuccess(eVar, null);
    }

    public void b() {
        dji.thirdparty.f.d.b(300, TimeUnit.MILLISECONDS, dji.thirdparty.f.j.e.c()).n(new 4(this)).w().C();
    }

    public void c() {
        dji.thirdparty.f.d.b(1000, TimeUnit.MILLISECONDS, dji.thirdparty.f.j.e.c()).n(new 5(this)).w().C();
    }

    public void d() {
        dji.thirdparty.f.d.b(1000, TimeUnit.MILLISECONDS, dji.thirdparty.f.j.e.c()).n(new 6(this)).w().C();
    }
}
