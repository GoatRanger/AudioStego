package dji.internal.version;

import android.content.Context;
import dji.internal.version.a.d;
import dji.log.DJILogHelper;
import dji.midware.c.a.a;
import dji.thirdparty.a.c;

public class b {
    private static final String a = "DJIVersionCamera";
    private static final boolean b = false;
    private a c = a.None;
    private DJIVersionBaseComponent d = null;
    private Context e = null;

    public void a(Context context) {
        this.e = context;
        DJIRemoteVersionInfo.getInstance().a(this.e);
        c.a().a(this);
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
        c.a().d(this);
    }

    private void c() {
        a("updateValue type " + this.c, false);
        a e = dji.midware.c.a.getInstance().e();
        if (e == a.None || e == a.Unknow) {
            e = a.None;
        }
        if (e != this.c) {
            if (this.d != null) {
                this.d.c();
                this.d = null;
            }
            this.c = e;
            this.d = a(this.c);
            if (this.d != null) {
                this.d.a(this.e);
            }
            if (this.d == null) {
                DJILogHelper.getInstance().LOGD(a, "DJIVersionCamera mBaseComponent null ", false, true);
                c.a().e(this);
                return;
            }
            DJILogHelper.getInstance().LOGD(a, "DJIVersionCamera : " + this.d.d(), false, true);
        }
    }

    public String b() {
        if (this.d == null) {
            return null;
        }
        return this.d.d();
    }

    private DJIVersionBaseComponent a(a aVar) {
        if (aVar == null) {
            return null;
        }
        switch (aVar) {
            case X3:
                return new dji.internal.version.a.b();
            case X5:
                return new dji.internal.version.a.c();
            case X5R:
                return new d();
            default:
                return null;
        }
    }

    public void onEventBackgroundThread(DJIVersionBaseComponent dJIVersionBaseComponent) {
        if (this.d == dJIVersionBaseComponent) {
            c.a().e(this);
        }
    }

    public void onEventBackgroundThread(a aVar) {
        c();
    }

    private void a(String str) {
        DJILogHelper.getInstance().LOGD(a, str);
    }

    private void a(String str, boolean z) {
        DJILogHelper.getInstance().LOGD(a, str, false, z);
    }
}
