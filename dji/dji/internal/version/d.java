package dji.internal.version;

import android.content.Context;
import dji.internal.version.a.g;
import dji.internal.version.a.i;
import dji.internal.version.a.j;
import dji.internal.version.a.k;
import dji.internal.version.a.l;
import dji.internal.version.a.m;
import dji.internal.version.a.n;
import dji.internal.version.a.o;
import dji.internal.version.a.p;
import dji.internal.version.a.r;
import dji.log.DJILogHelper;
import dji.midware.c.a;
import dji.midware.c.a.c;

public class d {
    private static final String a = "DJIVersionPlatform";
    private static final boolean b = false;
    private c c = c.None;
    private DJIVersionBaseComponent d = null;
    private Context e = null;

    public void a(Context context) {
        this.e = context;
        DJIRemoteVersionInfo.getInstance().a(this.e);
        dji.thirdparty.a.c.a().a(this);
        c();
    }

    public void a() {
        DJIRemoteVersionInfo.getInstance().b(this.e);
        if (this.d != null) {
            this.d.c();
        }
        this.d = null;
        this.c = null;
        this.e = null;
        dji.thirdparty.a.c.a().d(this);
    }

    private void c() {
        a("UpdateValue type " + this.c, false);
        c a = a.getInstance().a();
        if (a == c.None || a == c.Unknow) {
            a = c.None;
        }
        if (a != this.c) {
            if (this.d != null) {
                this.d.c();
                this.d = null;
            }
            this.c = a;
            this.d = a(this.c);
            if (this.d != null) {
                this.d.a(this.e);
            }
            if (this.d == null) {
                a("DJIVersionPlatform mBaseComponent null ", true);
                dji.thirdparty.a.c.a().e(this);
                return;
            }
            a("DJIVersionPlatform : " + this.d.d(), true);
        }
    }

    public String b() {
        if (this.d == null) {
            return null;
        }
        return this.d.d();
    }

    private DJIVersionBaseComponent a(c cVar) {
        if (cVar == null) {
            return null;
        }
        switch (cVar) {
            case P3c:
                return new n();
            case P3s:
                return new o();
            case P3x:
                return new p();
            case OSMO:
                switch (a.getInstance().e()) {
                    case X5:
                        return new l();
                    case X5R:
                        return new m();
                    default:
                        return new k();
                }
            case M100:
                return new i();
            case M600:
                return new j();
            case Inspire:
                return new dji.internal.version.a.a();
            case Unknow:
                if (a.getInstance().c() == dji.midware.c.a.d.LB2) {
                    return new g();
                }
                return null;
            case P4:
                return new r();
            default:
                return null;
        }
    }

    public void onEventBackgroundThread(DJIVersionBaseComponent dJIVersionBaseComponent) {
        if (this.d == dJIVersionBaseComponent) {
            dji.thirdparty.a.c.a().e(this);
        }
    }

    public void onEventBackgroundThread(c cVar) {
        c();
    }

    private void a(String str) {
        DJILogHelper.getInstance().LOGD(a, str);
    }

    private void a(String str, boolean z) {
        DJILogHelper.getInstance().LOGD(a, str, false, z);
    }
}
