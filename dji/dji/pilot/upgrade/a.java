package dji.pilot.upgrade;

import android.content.Context;
import dji.log.DJILogHelper;
import dji.midware.f.b;
import dji.pilot.upgrade.a.f;
import dji.pilot.upgrade.a.g;
import dji.pilot.upgrade.a.h;
import dji.pilot.upgrade.a.i;
import dji.pilot.upgrade.a.l;
import dji.pilot.upgrade.a.n;
import dji.pilot.upgrade.a.o;
import dji.thirdparty.a.c;

public class a {
    private static final String a = "CameraShowVersionController";
    private static final boolean b = false;
    private static a c = null;
    private dji.pilot.upgrade.b.a d = dji.pilot.upgrade.b.a.a;
    private UpgradeBaseComponent e = null;
    private Context f = null;

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (c == null) {
                c = new a();
            }
            aVar = c;
        }
        return aVar;
    }

    public void a(Context context) {
        this.f = context;
        c.a().a((Object) this);
        d.a(a, "CameraShowVersionController init ");
        e();
    }

    public void a() {
        UpgradeConfigInfo.getInstance().b(this.f);
        if (this.e != null) {
            this.e.d();
        }
        this.e = null;
        this.f = null;
        c.a().d((Object) this);
        c = null;
    }

    private void e() {
        d.a(a, "updateValue type " + this.d, false);
        dji.pilot.upgrade.b.a f = b.getInstance().f();
        if (f == dji.pilot.upgrade.b.a.a || f == dji.pilot.upgrade.b.a.h) {
            f = b.getInstance().g();
        }
        if (f == dji.pilot.upgrade.b.a.a || f == dji.pilot.upgrade.b.a.h) {
            f = dji.pilot.upgrade.b.a.a;
        }
        if (f != this.d) {
            if (this.e != null) {
                this.e.d();
                this.e = null;
            }
            this.d = f;
            this.e = a(this.d);
            if (this.e != null) {
                this.e.a(this.f);
            }
            c.a().e(this);
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

    public dji.pilot.upgrade.b.a c() {
        return this.d;
    }

    public UpgradeBaseComponent d() {
        return this.e;
    }

    private UpgradeBaseComponent a(dji.pilot.upgrade.b.a aVar) {
        if (aVar == null) {
            return null;
        }
        UpgradeBaseComponent lVar;
        b d;
        switch (1.a[aVar.ordinal()]) {
            case 1:
                return new f();
            case 2:
                lVar = new l();
                d = dji.midware.f.a.getInstance().d();
                if (d == b.AOA || d == b.ADB) {
                    return new g();
                }
                if (d == b.WIFI) {
                    return new n();
                }
                return lVar;
            case 3:
                lVar = new o();
                d = dji.midware.f.a.getInstance().d();
                if (d == b.AOA || d == b.ADB) {
                    return new h();
                }
                if (d == b.WIFI) {
                    return new o();
                }
                return lVar;
            case 4:
                return new i();
            case 5:
                b d2 = dji.midware.f.a.getInstance().d();
                if (d2 == b.AOA || d2 == b.ADB) {
                    return new dji.pilot.upgrade.a.c();
                }
                return new dji.pilot.upgrade.a.c();
            case 6:
                return new dji.pilot.upgrade.a.b();
            default:
                return null;
        }
    }

    public void onEventBackgroundThread(UpgradeBaseComponent upgradeBaseComponent) {
        d.a(a, "component receiver");
        if (this.e == upgradeBaseComponent) {
            c.a().e(this);
            d.a(a, "component receiver version" + this.e.e());
        }
    }

    public void onEventBackgroundThread(dji.pilot.upgrade.b.a aVar) {
        d.a(a, "==========~~~~~~~~******CameraComponentType, updateValue type 2 " + aVar, false);
        e();
    }
}
