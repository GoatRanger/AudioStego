package dji.pilot.upgrade;

import android.content.Context;
import dji.log.DJILogHelper;
import dji.pilot.upgrade.a.aa;
import dji.pilot.upgrade.a.e;
import dji.pilot.upgrade.a.k;
import dji.pilot.upgrade.a.t;
import dji.pilot.upgrade.a.y;
import dji.pilot.upgrade.b.c;

public class f {
    private static final String a = "RcShowVersionController";
    private static final boolean b = false;
    private static f c = null;
    private c d = c.a;
    private UpgradeBaseComponent e = null;
    private Context f = null;

    public static synchronized f getInstance() {
        f fVar;
        synchronized (f.class) {
            if (c == null) {
                c = new f();
            }
            fVar = c;
        }
        return fVar;
    }

    public void a(Context context) {
        this.f = context;
        dji.thirdparty.a.c.a().a((Object) this);
        d.a(a, "init ");
        e();
    }

    public void a() {
        UpgradeConfigInfo.getInstance().b(this.f);
        if (this.e != null) {
            this.e.d();
        }
        this.e = null;
        this.f = null;
        dji.thirdparty.a.c.a().d((Object) this);
        c = null;
    }

    private void e() {
        d.a(a, "updateValue type " + this.d, false);
        c d = b.getInstance().d();
        if (d == c.a || d == c.j) {
            d = b.getInstance().e();
        }
        if (d == c.a || d == c.j) {
            d = c.a;
        }
        if (d != this.d) {
            if (this.e != null) {
                this.e.d();
                this.e = null;
            }
            this.d = d;
            this.e = a(this.d);
            if (this.e != null) {
                this.e.a(this.f);
            }
            dji.thirdparty.a.c.a().e(this);
            if (this.e == null) {
                DJILogHelper.getInstance().LOGD(a, "RcShowVersionController mBaseComponent null ");
            } else {
                DJILogHelper.getInstance().LOGD(a, "RcShowVersionController : " + this.e.e(), false, true);
            }
        }
    }

    public String b() {
        if (this.e == null) {
            return null;
        }
        return this.e.e();
    }

    public c c() {
        return this.d;
    }

    public UpgradeBaseComponent d() {
        return this.e;
    }

    private UpgradeBaseComponent a(c cVar) {
        if (cVar == null) {
            return null;
        }
        switch (1.a[cVar.ordinal()]) {
            case 1:
                return new y();
            case 2:
                return new aa();
            case 3:
                return new aa();
            case 4:
                return new e();
            case 5:
                return new k();
            case 6:
            case 7:
                return new aa();
            case 8:
                return new t();
            default:
                return null;
        }
    }

    public void onEventBackgroundThread(UpgradeBaseComponent upgradeBaseComponent) {
        d.a(a, "component receiver");
        if (this.e == upgradeBaseComponent) {
            dji.thirdparty.a.c.a().e(this);
            d.a(a, "component receiver version" + this.e.e());
        }
    }

    public void onEventBackgroundThread(c cVar) {
        d.a(a, "==========~~~~~~~~******updateValue type 2 " + cVar);
        e();
    }
}
