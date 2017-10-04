package com.amap.api.mapcore.util;

public abstract class cl {
    protected int a;
    protected bg b;

    public abstract void c();

    public cl(int i, bg bgVar) {
        this.a = i;
        this.b = bgVar;
    }

    public int b() {
        return this.a;
    }

    public boolean a(cl clVar) {
        return clVar.b() == b();
    }

    public void b(cl clVar) {
        cf.a(b() + " ==> " + clVar.b() + "   " + getClass() + "==>" + clVar.getClass());
    }

    public void d() {
        cf.a("Wrong call start()  State: " + b() + "  " + getClass());
    }

    public void e() {
        cf.a("Wrong call continueDownload()  State: " + b() + "  " + getClass());
    }

    public void f() {
        cf.a("Wrong call pause()  State: " + b() + "  " + getClass());
    }

    public void a() {
        cf.a("Wrong call delete()  State: " + b() + "  " + getClass());
    }

    public void g() {
        cf.a("Wrong call fail()  State: " + b() + "  " + getClass());
    }

    public void h() {
        cf.a("Wrong call hasNew()  State: " + b() + "  " + getClass());
    }

    public void i() {
        cf.a("Wrong call complete()  State: " + b() + "  " + getClass());
    }
}
