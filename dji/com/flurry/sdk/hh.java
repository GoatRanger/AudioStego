package com.flurry.sdk;

import android.widget.Toast;
import com.flurry.sdk.jr.a;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

public class hh extends iz implements a {
    private static final String e = hh.class.getSimpleName();
    private static String f = "http://data.flurry.com/aap.do";
    private static String g = "https://data.flurry.com/aap.do";
    private String h;
    private boolean i;

    public hh() {
        this(null);
    }

    public hh(iz.a aVar) {
        super("Analytics", hh.class.getSimpleName());
        this.d = "AnalyticsData_";
        h();
        a(aVar);
    }

    private void h() {
        jr a = jq.a();
        this.i = ((Boolean) a.a("UseHttps")).booleanValue();
        a.a("UseHttps", (a) this);
        in.a(4, e, "initSettings, UseHttps = " + this.i);
        String str = (String) a.a("ReportUrl");
        a.a("ReportUrl", (a) this);
        b(str);
        in.a(4, e, "initSettings, ReportUrl = " + str);
    }

    public void a() {
        jq.a().b("UseHttps", (a) this);
        jq.a().b("ReportUrl", (a) this);
    }

    public void a(String str, Object obj) {
        Object obj2 = -1;
        switch (str.hashCode()) {
            case -239660092:
                if (str.equals("UseHttps")) {
                    obj2 = null;
                    break;
                }
                break;
            case 1650629499:
                if (str.equals("ReportUrl")) {
                    obj2 = 1;
                    break;
                }
                break;
        }
        switch (obj2) {
            case null:
                this.i = ((Boolean) obj).booleanValue();
                in.a(4, e, "onSettingUpdate, UseHttps = " + this.i);
                return;
            case 1:
                String str2 = (String) obj;
                b(str2);
                in.a(4, e, "onSettingUpdate, ReportUrl = " + str2);
                return;
            default:
                in.a(6, e, "onSettingUpdate internal error!");
                return;
        }
    }

    private void b(String str) {
        if (!(str == null || str.endsWith(".do"))) {
            in.a(5, e, "overriding analytics agent report URL without an endpoint, are you sure?");
        }
        this.h = str;
    }

    String b() {
        if (this.h != null) {
            return this.h;
        }
        if (this.i) {
            return g;
        }
        return f;
    }

    protected void a(byte[] bArr, final String str, final String str2) {
        String b = b();
        in.a(4, e, "FlurryDataSender: start upload data " + bArr + " with id = " + str + " to " + b);
        kc iuVar = new iu();
        iuVar.a(b);
        iuVar.a(100000);
        iuVar.a(iv.a.kPost);
        iuVar.a(AsyncHttpClient.HEADER_CONTENT_TYPE, RequestParams.APPLICATION_OCTET_STREAM);
        iuVar.a(new jd());
        iuVar.a((Object) bArr);
        iuVar.a(new iu.a<byte[], Void>(this) {
            final /* synthetic */ hh c;

            public void a(iu<byte[], Void> iuVar, Void voidR) {
                final int f = iuVar.f();
                if (f > 0) {
                    in.e(hh.e, "Analytics report sent.");
                    in.a(3, hh.e, "FlurryDataSender: report " + str + " sent. HTTP response: " + f);
                    if (in.c() <= 3 && in.d()) {
                        hz.a().a(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                Toast.makeText(hz.a().c(), "SD HTTP Response Code: " + f, 0).show();
                            }
                        });
                    }
                    this.c.a(str, str2, f);
                    this.c.e();
                    return;
                }
                this.c.b(str, str2);
            }
        });
        hx.a().a((Object) this, iuVar);
    }

    protected void a(String str, String str2, final int i) {
        a(new kb(this) {
            final /* synthetic */ hh b;

            public void a() {
                if (i == 200) {
                    gg.a().f();
                }
            }
        });
        super.a(str, str2, i);
    }
}
