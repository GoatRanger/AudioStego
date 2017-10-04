package com.flurry.sdk;

import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.flurry.sdk.jr.a;
import com.loopj.android.http.AsyncHttpClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class gy implements a {
    private static final String a = gy.class.getSimpleName();
    private static String b = "https://proton.flurry.com:443/sdk/v1/config";
    private final Runnable c = new kb(this) {
        final /* synthetic */ gy a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.f();
        }
    };
    private final ii<ho> d = new ii<ho>(this) {
        final /* synthetic */ gy a;

        {
            this.a = r1;
        }

        public void a(ho hoVar) {
            this.a.f();
        }
    };
    private final ii<jm> e = new ii<jm>(this) {
        final /* synthetic */ gy a;

        {
            this.a = r1;
        }

        public void a(jm jmVar) {
            switch (jmVar.c) {
                case CREATE:
                    this.a.b();
                    return;
                case START:
                    this.a.c();
                    return;
                case END:
                    this.a.a(jmVar.d);
                    return;
                case FINALIZE:
                    this.a.d();
                    return;
                default:
                    return;
            }
        }
    };
    private final ii<hp> f = new ii<hp>(this) {
        final /* synthetic */ gy a;

        {
            this.a = r1;
        }

        public void a(hp hpVar) {
            this.a.f();
        }
    };
    private final ii<hs> g = new ii<hs>(this) {
        final /* synthetic */ gy a;

        {
            this.a = r1;
        }

        public void a(hs hsVar) {
            if (hsVar.a) {
                this.a.f();
            }
        }
    };
    private final ir<gn> h = new ir("proton config request", new gz());
    private final ir<go> i = new ir("proton config response", new ha());
    private ig<gw> j;
    private ig<List<hb>> k;
    private final gx l = new gx();
    private final ie<String, gl> m = new ie();
    private final List<hb> n = new ArrayList();
    private boolean o;
    private String p;
    private boolean q;
    private long r = 10000;
    private long s;
    private boolean t;
    private go u;

    public gy() {
        jr a = jq.a();
        this.o = ((Boolean) a.a("ProtonEnabled")).booleanValue();
        a.a("ProtonEnabled", (a) this);
        in.a(4, a, "initSettings, protonEnabled = " + this.o);
        this.p = (String) a.a("ProtonConfigUrl");
        a.a("ProtonConfigUrl", (a) this);
        in.a(4, a, "initSettings, protonConfigUrl = " + this.p);
        ij.a().a("com.flurry.android.sdk.IdProviderFinishedEvent", this.d);
        ij.a().a("com.flurry.android.sdk.IdProviderUpdatedAdvertisingId", this.f);
        ij.a().a("com.flurry.android.sdk.NetworkStateEvent", this.g);
        this.j = new ig(hz.a().c().getFileStreamPath(m()), ".yflurryprotonconfig.", 1, new jk<gw>(this) {
            final /* synthetic */ gy a;

            {
                this.a = r1;
            }

            public jh<gw> a(int i) {
                return new gw.a();
            }
        });
        this.k = new ig(hz.a().c().getFileStreamPath(n()), ".yflurryprotonreport.", 1, new jk<List<hb>>(this) {
            final /* synthetic */ gy a;

            {
                this.a = r1;
            }

            public jh<List<hb>> a(int i) {
                return new jg(new hb.a());
            }
        });
        hz.a().b(new kb(this) {
            final /* synthetic */ gy a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.h();
            }
        });
        hz.a().b(new kb(this) {
            final /* synthetic */ gy a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.j();
            }
        });
    }

    public void a() {
        hz.a().c(this.c);
        ij.a().b("com.flurry.android.sdk.NetworkStateEvent", this.g);
        ij.a().b("com.flurry.android.sdk.IdProviderUpdatedAdvertisingId", this.f);
        ij.a().b("com.flurry.android.sdk.IdProviderFinishedEvent", this.d);
        jq.a().b("ProtonEnabled", (a) this);
    }

    public void a(String str, Object obj) {
        Object obj2 = -1;
        switch (str.hashCode()) {
            case 640941243:
                if (str.equals("ProtonEnabled")) {
                    obj2 = null;
                    break;
                }
                break;
            case 1591403975:
                if (str.equals("ProtonConfigUrl")) {
                    obj2 = 1;
                    break;
                }
                break;
        }
        switch (obj2) {
            case null:
                this.o = ((Boolean) obj).booleanValue();
                in.a(4, a, "onSettingUpdate, protonEnabled = " + this.o);
                return;
            case 1:
                this.p = (String) obj;
                in.a(4, a, "onSettingUpdate, protonConfigUrl = " + this.p);
                return;
            default:
                in.a(6, a, "onSettingUpdate internal error!");
                return;
        }
    }

    public synchronized void b() {
        if (this.o) {
            jz.b();
            f();
        }
    }

    public synchronized void c() {
        if (this.o) {
            jz.b();
            b(hm.a().d());
            i();
        }
    }

    public synchronized void a(long j) {
        if (this.o) {
            jz.b();
            b(j);
            a("flurry.session_end");
            k();
        }
    }

    public synchronized void d() {
        if (this.o) {
            jz.b();
            i();
        }
    }

    private synchronized void f() {
        if (this.o) {
            if (this.q && hn.a().c()) {
                boolean z;
                final long currentTimeMillis = System.currentTimeMillis();
                if (hn.a().e()) {
                    z = false;
                } else {
                    z = true;
                }
                if (this.u != null) {
                    if (this.t != z) {
                        in.a(3, a, "Limit ad tracking value has changed, purging");
                        this.u = null;
                    } else if (System.currentTimeMillis() < this.s + (this.u.b * 1000)) {
                        in.a(3, a, "Cached Proton config valid, no need to refresh");
                    } else if (System.currentTimeMillis() >= this.s + (this.u.c * 1000)) {
                        in.a(3, a, "Cached Proton config expired, purging");
                        this.u = null;
                    }
                }
                hx.a().a((Object) this);
                in.a(3, a, "Requesting proton config");
                try {
                    Object gnVar = new gn();
                    gnVar.a = hz.a().d();
                    gnVar.b = jw.c(hz.a().c());
                    gnVar.c = jw.d(hz.a().c());
                    gnVar.d = ia.a();
                    gnVar.e = 3;
                    gnVar.f = hw.a().c();
                    gnVar.g = z;
                    gnVar.h = new gq();
                    gnVar.h.a = new gk();
                    gnVar.h.a.a = Build.MODEL;
                    gnVar.h.a.b = Build.BRAND;
                    gnVar.h.a.c = Build.ID;
                    gnVar.h.a.d = Build.DEVICE;
                    gnVar.h.a.e = Build.PRODUCT;
                    gnVar.h.a.f = VERSION.RELEASE;
                    gnVar.i = new ArrayList();
                    for (Entry entry : hn.a().h().entrySet()) {
                        gp gpVar = new gp();
                        gpVar.a = ((hv) entry.getKey()).d;
                        if (((hv) entry.getKey()).e) {
                            gpVar.b = new String((byte[]) entry.getValue());
                        } else {
                            gpVar.b = jz.b((byte[]) entry.getValue());
                        }
                        gnVar.i.add(gpVar);
                    }
                    Location e = hr.a().e();
                    if (e != null) {
                        gnVar.j = new gs();
                        gnVar.j.a = new gr();
                        gnVar.j.a.a = e.getLatitude();
                        gnVar.j.a.b = e.getLongitude();
                        gnVar.j.a.c = e.getAccuracy();
                    }
                    Object a = this.h.a(gnVar);
                    kc iuVar = new iu();
                    iuVar.a(TextUtils.isEmpty(this.p) ? b : this.p);
                    iuVar.a(5000);
                    iuVar.a(iv.a.kPost);
                    iuVar.a(AsyncHttpClient.HEADER_CONTENT_TYPE, "application/x-flurry;version=1");
                    iuVar.a("Accept", "application/x-flurry;version=1");
                    iuVar.a("FM-Checksum", Integer.toString(ir.c(a)));
                    iuVar.a(new jd());
                    iuVar.b(new jd());
                    iuVar.a(a);
                    iuVar.a(new iu.a<byte[], byte[]>(this) {
                        final /* synthetic */ gy c;

                        public void a(iu<byte[], byte[]> iuVar, byte[] bArr) {
                            int f = iuVar.f();
                            in.a(3, gy.a, "Proton config request: HTTP status code is:" + f);
                            if (f == 400 || f == dji.pilot.flyunlimit.a.B || f == 412 || f == 415) {
                                this.c.r = 10000;
                                return;
                            }
                            go goVar;
                            if (!iuVar.d() || bArr == null) {
                                goVar = null;
                            } else {
                                this.c.a(currentTimeMillis, z, bArr);
                                try {
                                    goVar = (go) this.c.i.d(bArr);
                                } catch (Exception e) {
                                    in.a(5, gy.a, "Failed to decode proton config response: " + e);
                                    goVar = null;
                                }
                                if (goVar != null) {
                                    this.c.r = 10000;
                                    this.c.s = currentTimeMillis;
                                    this.c.t = z;
                                    this.c.u = goVar;
                                    this.c.g();
                                }
                            }
                            if (goVar == null) {
                                long parseLong;
                                long f2 = this.c.r << 1;
                                if (f == 429) {
                                    List b = iuVar.b("Retry-After");
                                    if (!b.isEmpty()) {
                                        String str = (String) b.get(0);
                                        in.a(3, gy.a, "Server returned retry time: " + str);
                                        try {
                                            parseLong = Long.parseLong(str) * 1000;
                                        } catch (NumberFormatException e2) {
                                            in.a(3, gy.a, "Server returned nonsensical retry time");
                                        }
                                        this.c.r = parseLong;
                                        in.a(3, gy.a, "Proton config request failed, backing off: " + this.c.r + "ms");
                                        hz.a().b(this.c.c, this.c.r);
                                    }
                                }
                                parseLong = f2;
                                this.c.r = parseLong;
                                in.a(3, gy.a, "Proton config request failed, backing off: " + this.c.r + "ms");
                                hz.a().b(this.c.c, this.c.r);
                            }
                        }
                    });
                    hx.a().a((Object) this, iuVar);
                } catch (Exception e2) {
                    in.a(5, a, "Proton config request failed with exception: " + e2);
                }
            }
        }
    }

    private void g() {
        if (this.u != null) {
            this.m.a();
            gm gmVar = this.u.e;
            if (gmVar != null) {
                List<Object> list = gmVar.b;
                if (list != null) {
                    for (Object obj : list) {
                        List<Object> list2 = obj.b;
                        if (list2 != null) {
                            for (Object obj2 : list2) {
                                if (!TextUtils.isEmpty(obj2)) {
                                    this.m.a(obj2, obj);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private synchronized void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            in.a(3, a, "Firing Proton callbacks for event: " + str);
            for (gl glVar : this.m.a((Object) "flurry.session_end")) {
                if (gv.a.equals(glVar.c)) {
                    ix hbVar = new hb(hm.a().d(), str, this.l.a(glVar.d), System.currentTimeMillis() + 259200000);
                    if ("flurry.session_end".equals(str)) {
                        this.n.add(hbVar);
                    } else {
                        gg.a().e().b(hbVar);
                    }
                }
            }
        }
    }

    private synchronized void b(long j) {
        Iterator it = this.n.iterator();
        while (it.hasNext()) {
            if (j == ((hb) it.next()).a()) {
                it.remove();
            }
        }
    }

    private synchronized void h() {
        gw gwVar = (gw) this.j.a();
        if (gwVar != null) {
            go goVar;
            try {
                goVar = (go) this.i.d(gwVar.c());
            } catch (Exception e) {
                in.a(5, a, "Failed to decode saved proton config response: " + e);
                this.j.b();
                goVar = null;
            }
            if (goVar != null) {
                in.a(4, a, "Loaded saved proton config response");
                this.r = 10000;
                this.s = gwVar.a();
                this.t = gwVar.b();
                this.u = goVar;
                g();
            }
        }
        this.q = true;
        hz.a().b(new kb(this) {
            final /* synthetic */ gy a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.f();
            }
        });
    }

    private synchronized void a(long j, boolean z, byte[] bArr) {
        if (bArr != null) {
            in.a(4, a, "Saving proton config response");
            gw gwVar = new gw();
            gwVar.a(j);
            gwVar.a(z);
            gwVar.a(bArr);
            this.j.a(gwVar);
        }
    }

    private synchronized void i() {
        in.a(4, a, "Sending " + this.n.size() + " queued reports.");
        for (hb b : this.n) {
            gg.a().e().b((ix) b);
        }
        l();
    }

    private synchronized void j() {
        in.a(4, a, "Loading queued report data.");
        List list = (List) this.k.a();
        if (list != null) {
            this.n.addAll(list);
        }
    }

    private synchronized void k() {
        in.a(4, a, "Saving queued report data.");
        this.k.a(this.n);
    }

    private synchronized void l() {
        this.n.clear();
        this.k.b();
    }

    private String m() {
        return ".yflurryprotonconfig." + Long.toString(jz.i(hz.a().d()), 16);
    }

    private String n() {
        return ".yflurryprotonreport." + Long.toString(jz.i(hz.a().d()), 16);
    }
}
