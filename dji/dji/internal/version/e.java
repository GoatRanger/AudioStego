package dji.internal.version;

import android.content.Context;
import dji.internal.version.a.f;
import dji.internal.version.a.h;
import dji.internal.version.a.q;
import dji.log.DJILogHelper;
import dji.midware.c.a;
import dji.midware.c.a.d;
import dji.thirdparty.a.c;

public class e {
    private static final String a = "DJIVersionRC";
    private static final boolean b = true;
    private d c = d.None;
    private DJIVersionBaseComponent d = null;
    private Context e = null;

    public void a(Context context) {
        a("init", true);
        this.e = context;
        DJIRemoteVersionInfo.getInstance().a(this.e);
        c.a().a(this);
        c();
    }

    public void a() {
        a("uninit", true);
        DJIRemoteVersionInfo.getInstance().b(this.e);
        if (this.d != null) {
            this.d.c();
        }
        this.d = null;
        this.c = null;
        this.e = null;
        c.a().d(this);
    }

    private void c() {
        a("updateValue type " + this.c, true);
        d c = a.getInstance().c();
        if (c == d.None || c == d.Unknow) {
            c = d.None;
        }
        if (c != this.c) {
            if (this.d != null) {
                this.d.c();
                this.d = null;
            }
            this.c = c;
            this.d = a(this.c);
            if (this.d != null) {
                this.d.a(this.e);
            }
            if (this.d == null) {
                a("mBaseComponent null ", true);
                c.a().e(this);
                return;
            }
            a("version : " + this.d.d(), true);
        }
    }

    public String b() {
        if (this.d == null) {
            return null;
        }
        return this.d.d();
    }

    private DJIVersionBaseComponent a(d dVar) {
        if (dVar == null) {
            return null;
        }
        switch (dVar) {
            case P3P4:
                return new q();
            case Inspire:
                return new f();
            case LB2:
                return new h();
            default:
                return null;
        }
    }

    public void onEventBackgroundThread(DJIVersionBaseComponent dJIVersionBaseComponent) {
        a("onEventBackgroundThread :" + dJIVersionBaseComponent.b(), true);
        if (this.d == dJIVersionBaseComponent) {
            a("onEventBackgroundThread mBaseComponent == component", true);
            c.a().e(this);
        }
    }

    public void onEventBackgroundThread(d dVar) {
        a("onEventBackgroundThread :" + dVar, true);
        c();
    }

    private void a(String str) {
        DJILogHelper.getInstance().LOGD(a, str);
    }

    private void a(String str, boolean z) {
        DJILogHelper.getInstance().LOGD(a, str, false, z);
    }
}
