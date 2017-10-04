package dji.pilot.upgrade;

import android.content.Context;
import dji.log.DJILogHelper;
import dji.pilot.upgrade.a.a;
import dji.pilot.upgrade.a.ab;
import dji.pilot.upgrade.a.d;
import dji.pilot.upgrade.a.j;
import dji.pilot.upgrade.a.l;
import dji.pilot.upgrade.a.m;
import dji.pilot.upgrade.a.n;
import dji.pilot.upgrade.a.o;
import dji.pilot.upgrade.a.p;
import dji.pilot.upgrade.a.q;
import dji.pilot.upgrade.a.r;
import dji.pilot.upgrade.a.s;
import dji.pilot.upgrade.a.u;
import dji.pilot.upgrade.a.v;
import dji.pilot.upgrade.a.w;
import dji.pilot.upgrade.a.x;
import dji.pilot.upgrade.a.z;
import dji.pilot.upgrade.b.b;
import dji.thirdparty.a.c;

public class e {
    private static final String a = "ProductShowVersionController";
    private static final boolean b = false;
    private static e c = null;
    private b d = b.a;
    private UpgradeBaseComponent e = null;
    private UpgradeBaseComponent f = null;
    private Context g = null;

    public static synchronized e getInstance() {
        e eVar;
        synchronized (e.class) {
            if (c == null) {
                c = new e();
            }
            eVar = c;
        }
        return eVar;
    }

    public void a(Context context) {
        this.g = context;
        UpgradeConfigInfo.getInstance().a(this.g);
        c.a().a((Object) this);
        f();
    }

    public void a() {
        UpgradeConfigInfo.getInstance().b(this.g);
        if (this.e != null) {
            this.e.d();
        }
        this.e = null;
        this.d = null;
        this.g = null;
        c.a().d((Object) this);
        c = null;
    }

    private void f() {
        d.a(a, "Petyr updateValue type " + this.d, false);
        b b = b.getInstance().b();
        if (b == b.a || b == b.s) {
            b = b.getInstance().c();
        }
        if (b == b.a || b == b.s) {
            b = b.a;
        }
        if (b != this.d) {
            if (this.e != null) {
                this.e.d();
                this.e = null;
            }
            if (this.f != null) {
                this.f.d();
                this.f = null;
            }
            this.d = b;
            this.e = a(this.d);
            if (this.e != null) {
                this.e.a(this.g);
            }
            if (this.d == b.h || this.d == b.i || this.d == b.q || this.d == b.r) {
                this.f = new j();
                this.f.a(this.g);
            }
            c.a().e(this);
            if (this.e == null) {
                DJILogHelper.getInstance().LOGD(a, "Petyr ProductShowVersionController mBaseComponent null ", false, true);
            } else {
                DJILogHelper.getInstance().LOGD(a, "Petyr ProductShowVersionController : " + this.e.e(), false, true);
            }
        }
    }

    public String b() {
        if (this.e == null) {
            return null;
        }
        return this.e.e();
    }

    public String c() {
        if (this.f == null) {
            return null;
        }
        return this.f.e();
    }

    public b d() {
        return this.d;
    }

    public UpgradeBaseComponent e() {
        return this.e;
    }

    private UpgradeBaseComponent a(b bVar) {
        if (bVar == null) {
            return null;
        }
        switch (1.a[bVar.ordinal()]) {
            case 1:
                return new w();
            case 2:
                return new v();
            case 3:
                return new x();
            case 4:
                return new z();
            case 5:
                return new l();
            case 6:
                return new p();
            case 7:
                return new n();
            case 8:
                return new o();
            case 9:
                return new m();
            case 10:
                return new q();
            case 11:
                return new r();
            case 12:
                return new s();
            case 13:
                return new d();
            case 14:
                return new j();
            case 15:
                return new ab();
            case 16:
                return new u();
            case 17:
                return new a();
            default:
                return null;
        }
    }

    public void onEventBackgroundThread(UpgradeBaseComponent upgradeBaseComponent) {
        if (this.e == upgradeBaseComponent) {
            c.a().e(this);
        }
    }

    public void onEventBackgroundThread(b bVar) {
        f();
    }

    public void onEventBackgroundThread(dji.dbox.upgrade.p4.a.b bVar) {
        switch (1.b[bVar.ordinal()]) {
            case 1:
                f();
                return;
            default:
                return;
        }
    }
}
