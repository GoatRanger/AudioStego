package com.flurry.sdk;

import android.text.TextUtils;
import android.widget.Toast;
import com.flurry.sdk.hb.a;
import com.flurry.sdk.hb.b;
import java.util.List;

public class hc extends iy<hb> {
    private static final String a = hc.class.getSimpleName();

    protected ig<List<hb>> a() {
        return new ig(hz.a().c().getFileStreamPath(".yflurryanreporter"), ".yflurryanreporter", 2, new jk<List<hb>>(this) {
            final /* synthetic */ hc a;

            {
                this.a = r1;
            }

            public jh<List<hb>> a(int i) {
                if (i > 1) {
                    return new jg(new a());
                }
                return new jg(new b());
            }
        });
    }

    protected void a(final hb hbVar) {
        in.a(3, a, "Sending next report for original url: " + hbVar.i() + " to current url:" + hbVar.j());
        kc iuVar = new iu();
        iuVar.a(hbVar.j());
        iuVar.a(100000);
        iuVar.a(iv.a.kGet);
        iuVar.a(false);
        iuVar.a(new iu.a<Void, Void>(this) {
            final /* synthetic */ hc b;

            public void a(final iu<Void, Void> iuVar, Void voidR) {
                in.a(3, hc.a, "AsyncReportInfo request: HTTP status code is:" + iuVar.f());
                int f = iuVar.f();
                if (f >= 200 && f < 300) {
                    in.a(3, hc.a, "Send report successful to url: " + iuVar.b());
                    this.b.c(hbVar);
                    if (in.c() <= 3 && in.d()) {
                        hz.a().a(new Runnable(this) {
                            final /* synthetic */ AnonymousClass2 b;

                            public void run() {
                                Toast.makeText(hz.a().c(), "ANL AR HTTP Response Code: " + iuVar.f() + " for url: " + iuVar.b(), 1).show();
                            }
                        });
                    }
                } else if (f < 300 || f >= 400) {
                    in.a(3, hc.a, "Send report failed to url: " + iuVar.b());
                    if (kd.h(hbVar.j())) {
                        this.b.d(hbVar);
                        return;
                    }
                    in.a(3, hc.a, "Oops! url: " + iuVar.b() + " is invalid, aborting transmission");
                    this.b.c(hbVar);
                } else {
                    String str = null;
                    List b = iuVar.b("Location");
                    if (b != null && b.size() > 0) {
                        str = kd.b((String) b.get(0), hbVar.j());
                    }
                    if (TextUtils.isEmpty(str)) {
                        in.a(3, hc.a, "Send report successful to url: " + iuVar.b());
                        this.b.c(hbVar);
                        if (in.c() <= 3 && in.d()) {
                            hz.a().a(new Runnable(this) {
                                final /* synthetic */ AnonymousClass2 b;

                                public void run() {
                                    Toast.makeText(hz.a().c(), "ANL AR HTTP Response Code: " + iuVar.f() + " for url: " + iuVar.b(), 1).show();
                                }
                            });
                            return;
                        }
                        return;
                    }
                    in.a(3, hc.a, "Send report redirecting to url: " + str);
                    hbVar.c(str);
                    this.b.a(hbVar);
                }
            }
        });
        hx.a().a((Object) this, iuVar);
    }
}
