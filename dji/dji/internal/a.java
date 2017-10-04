package dji.internal;

import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.b;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.b.f;
import dji.sdksharedlib.b.i;
import dji.sdksharedlib.c.d;

public class a {
    private static final boolean a = false;
    private static final String b = "N/A";
    private static final String c = ".";
    private static a d;
    private String e = "";
    private String f = "";
    private String g = "";
    private String h = "";
    private String i = "";
    private String j = "";

    public String a() {
        return this.j;
    }

    private a() {
        b();
        c();
    }

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (d == null) {
                d = new a();
            }
            aVar = d;
        }
        return aVar;
    }

    public void b() {
        dji.sdksharedlib.b.c.a aVar = new dji.sdksharedlib.b.c.a();
        DJISDKCache.getInstance().startListeningForUpdates(aVar.b(e.a).a(Integer.valueOf(0)).d("FirmwareVersion").a(), new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
                this.a.e = this.a.a(aVar2);
            }
        }, true);
        DJISDKCache.getInstance().startListeningForUpdates(aVar.b(b.a).a(Integer.valueOf(0)).d("FirmwareVersion").a(), new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
                this.a.f = this.a.a(aVar2);
            }
        }, true);
        DJISDKCache.getInstance().startListeningForUpdates(aVar.b(f.a).a(Integer.valueOf(0)).d("FirmwareVersion").a(), new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
                this.a.i = this.a.a(aVar2);
            }
        }, true);
        DJISDKCache.getInstance().startListeningForUpdates(aVar.b(dji.sdksharedlib.b.a.a).a(Integer.valueOf(0)).d("FirmwareVersion").a(), new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
                this.a.h = this.a.a(aVar2);
            }
        }, true);
        DJISDKCache.getInstance().startListeningForUpdates(aVar.b(i.a).a(Integer.valueOf(0)).d("FirmwareVersion").a(), new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
                this.a.g = this.a.a(aVar2);
            }
        }, true);
    }

    public void c() {
        DJISDKCache.getInstance().startListeningForUpdates(new dji.sdksharedlib.b.c.a().b(e.a).a(Integer.valueOf(0)).d("InternalSerialNumber").a(), new d(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
                this.a.j = this.a.a(aVar2);
            }
        }, true);
    }

    private String a(dji.sdksharedlib.d.a aVar) {
        if (aVar == null) {
            return b;
        }
        if (aVar.e() instanceof String) {
            return (String) aVar.e();
        }
        if (aVar.e() instanceof DataCommonGetVersion) {
            return ((DataCommonGetVersion) aVar.e()).getFirmVer(".");
        }
        return b;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.g;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.h;
    }

    public String h() {
        return this.i;
    }

    private void a(String str) {
    }
}
