package dji.dbox.upgrade.p4.statemachine;

import android.content.Context;
import dji.dbox.upgrade.p4.a.a;
import dji.dbox.upgrade.p4.b.b;
import dji.dbox.upgrade.p4.model.DJIUpListElement;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.model.P3.DataCommonControlUpgrade;
import dji.midware.data.model.P3.DataCommonControlUpgrade.ControlCmd;
import dji.publics.DJIObject.c;

public class f extends c {
    private g a;

    public f(Context context) {
        this.a = new g(context);
        this.a.a(true);
    }

    protected void a() {
        dji.thirdparty.a.c.a().a((Object) this);
    }

    public void b() {
        dji.thirdparty.a.c.a().d((Object) this);
    }

    public boolean c() {
        return this.a.v() || a.n;
    }

    public void a(dji.dbox.upgrade.p4.b.c cVar) {
        this.a.a(cVar);
    }

    public void a(b bVar) {
        this.a.a(bVar);
    }

    public void b(b bVar) {
        this.a.b(bVar);
    }

    public void d() {
        this.a.a();
    }

    public void e() {
        a.u = a.v;
        this.a.b();
    }

    public void a(DJIUpListElement dJIUpListElement) {
        a.u = dJIUpListElement;
        this.a.b();
    }

    public void f() {
        DataCommonControlUpgrade.getInstance().a(DeviceType.DM368).a(1).a(ControlCmd.d).start(new 1(this));
    }

    public void g() {
        if (DJIUpgradeP4Service.c()) {
            this.a.g();
        }
    }

    public void onEventBackgroundThread(DJIUpgradeP4Service.a aVar) {
        switch (2.a[aVar.ordinal()]) {
            case 1:
                this.a.e();
                return;
            case 2:
                this.a.e();
                return;
            case 4:
                this.a.d();
                return;
            default:
                return;
        }
    }

    public void h() {
        this.a.w();
    }
}
