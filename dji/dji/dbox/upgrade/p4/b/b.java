package dji.dbox.upgrade.p4.b;

import dji.midware.data.model.P3.DataCommonGetPushUpgradeStatus.DJIUpgradeCompleteReason;

public interface b {

    public enum a {
        Init,
        Enter,
        Quit,
        Pretrans,
        Transing
    }

    void a();

    void a(int i);

    void a(a aVar, dji.midware.data.config.P3.a aVar2);

    void a(DJIUpgradeCompleteReason dJIUpgradeCompleteReason);

    void a(String str);

    void a(String str, int i);

    void b();

    void b(int i);

    void b(String str);

    void c();

    void d();

    void e();

    void f();

    void g();

    void h();

    void i();

    void j();

    void k();

    void l();

    void m();
}
