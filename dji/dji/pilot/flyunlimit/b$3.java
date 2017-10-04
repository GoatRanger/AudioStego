package dji.pilot.flyunlimit;

import com.dji.frame.c.h;
import com.dji.frame.c.l;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.pilot.flyunlimit.b.e;
import dji.pilot.flyunlimit.jsonbean.DJIUnlimitUserVerifyResult;
import dji.pilot.flyunlimit.jsonbean.DJIUnlimitUserVerifyResult.VerifyData;
import dji.thirdparty.afinal.f.a;

class b$3 extends a<String> {
    final /* synthetic */ e a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ b d;

    b$3(b bVar, e eVar, String str, String str2) {
        this.d = bVar;
        this.a = eVar;
        this.b = str;
        this.c = str2;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        DJIUnlimitUserVerifyResult dJIUnlimitUserVerifyResult = (DJIUnlimitUserVerifyResult) h.b(str, DJIUnlimitUserVerifyResult.class);
        if (dJIUnlimitUserVerifyResult != null && dJIUnlimitUserVerifyResult.data != null) {
            if (dJIUnlimitUserVerifyResult.signature.compareTo(dji.pilot.a.a.c(String.format("%d%s%s%s%s%s", new Object[]{Long.valueOf(dJIUnlimitUserVerifyResult.status), dJIUnlimitUserVerifyResult.country, dJIUnlimitUserVerifyResult.type, dJIUnlimitUserVerifyResult.url, dJIUnlimitUserVerifyResult.url_key, Long.valueOf(dJIUnlimitUserVerifyResult.time)}), b.a)) != 0) {
                b.a(this.d, 15);
                if (this.a != null) {
                    this.a.a("signature is wrong");
                }
            } else if (dJIUnlimitUserVerifyResult.data.isEmpty() || l.a(((VerifyData) dJIUnlimitUserVerifyResult.data.get(0)).agreed) || !((VerifyData) dJIUnlimitUserVerifyResult.data.get(0)).agreed.equalsIgnoreCase("1")) {
                if (!l.a(dJIUnlimitUserVerifyResult.type) && dJIUnlimitUserVerifyResult.type.equalsIgnoreCase(DJIFlyForbidController.AIRMAP_DATA_SOURCE) && !l.a(dJIUnlimitUserVerifyResult.url)) {
                    b.a(this.d, dJIUnlimitUserVerifyResult.url);
                    b.b(this.d, this.b);
                    b.c(this.d, this.c);
                    if (dJIUnlimitUserVerifyResult.extra != null) {
                        b.d(this.d, dJIUnlimitUserVerifyResult.extra.user_id);
                    }
                    if (this.a != null) {
                        this.a.c();
                    }
                } else if (this.a != null) {
                    this.a.a(a.b(b.b(this.d), (int) dJIUnlimitUserVerifyResult.status));
                }
            } else if (this.a != null) {
                this.a.b();
            }
        } else if (this.a != null && dJIUnlimitUserVerifyResult != null) {
            this.a.a(a.b(b.b(this.d), (int) dJIUnlimitUserVerifyResult.status));
        }
    }

    public void a(Throwable th, int i, String str) {
        if (this.a != null) {
            this.a.a(str);
        }
    }
}
