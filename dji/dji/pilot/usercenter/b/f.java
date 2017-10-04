package dji.pilot.usercenter.b;

import android.content.Context;
import android.util.Log;
import com.dji.frame.c.c;
import dji.log.DJILogHelper;
import dji.pilot.fpv.d.c.d;
import dji.pilot.publics.objects.g;
import dji.pilot.usercenter.f.e;
import dji.pilot.usercenter.mode.j;
import dji.pilot.usercenter.mode.k;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.e.a;
import dji.pilot2.utils.s;
import dji.thirdparty.afinal.f.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class f implements d, dji.pilot.usercenter.protocol.d {
    private static final String O = "key_account_email";
    private static final String P = "key_account_word";
    private static final String Q = "key_account_token";
    private static final String R = "user_avatar.png";
    private static final String S = "key_account_nickname";
    private static final String T = "key_account_id";
    private static final String U = "key_account_uid";
    private static final String V = "key_account_phone";
    private static final String W = f.class.getSimpleName();
    private final j X;
    private final j Y;
    private final a Z;
    private volatile boolean aU;
    private b aV;
    private final j aW;
    private volatile boolean aa;
    private Context ab;
    private final a ac;
    private final ArrayList<a> ad;
    private boolean ae;
    private Map<String, String> af;
    private c.d ag;
    private volatile boolean ah;

    public static f getInstance() {
        return c.a();
    }

    public synchronized void a(Context context) {
        boolean z = true;
        synchronized (this) {
            if (!this.aa) {
                this.ab = context.getApplicationContext();
                String b = g.b(this.ab, Q, "");
                if (b.equals("")) {
                    z = false;
                }
                this.ae = z;
                String b2 = g.b(this.ab, O, "");
                if (!"".equals(b2)) {
                    this.X.h = e.b(b2);
                    this.X.i = this.X.h.trim().toLowerCase(Locale.US);
                }
                if (this.ae) {
                    this.X.Q = e.b(b);
                    dji.dbox.upgrade.p4.a.a.c(this.X.Q, this.X.i);
                }
                b = g.b(this.ab, S, "");
                if (!"".equals(b2)) {
                    this.X.j = e.b(b);
                }
                b2 = g.b(this.ab, T, "");
                if (!"".equals(b2)) {
                    this.X.f = e.b(b2);
                }
                b2 = g.b(this.ab, U, "");
                if (!"".equals(b2)) {
                    this.X.g = e.b(b2);
                }
                b2 = g.b(this.ab, V, "");
                if (!"".equals(b2)) {
                    this.X.H = e.b(b2);
                }
                if (this.ae) {
                    dji.pilot.b.a.a(context, this.X.g + "_" + this.X.j, this.X.i, this.X.H);
                }
                c.getInstance().a(this.ab);
                dji.pilot.usercenter.e.a instance = dji.pilot.usercenter.e.a.getInstance();
                instance.a(this.ab);
                instance.a(null, null);
                g.a(this.ab, P);
                q();
                this.aa = true;
            }
        }
    }

    public synchronized void a() {
        if (this.aa) {
            this.aa = false;
        }
    }

    public boolean a(a aVar) {
        boolean z = false;
        if (aVar != null) {
            synchronized (this.ad) {
                if (!this.ad.contains(aVar)) {
                    this.ad.add(0, aVar);
                    z = true;
                }
            }
        }
        return z;
    }

    public boolean b(a aVar) {
        boolean z = false;
        if (aVar != null) {
            synchronized (this.ad) {
                z = this.ad.remove(aVar);
            }
        }
        return z;
    }

    public void a(b bVar) {
        this.aV = bVar;
    }

    public void b() {
        this.aV = null;
    }

    public void b(Context context) {
        if (c()) {
            b bVar = new b();
            bVar.a("token", n());
            c.b(context).a(dji.pilot.usercenter.protocol.e.aO, bVar, new 1(this));
        }
    }

    public boolean c() {
        return this.ae;
    }

    public boolean d() {
        return this.aU;
    }

    public void a(boolean z) {
        t();
        this.ae = false;
        this.aU = false;
        this.ah = false;
        this.X.a();
        if (this.aV != null) {
            this.aV.a(this.X.i, null);
        }
        s.c(this.ab);
    }

    public String e() {
        return this.ab.getFileStreamPath(R).getAbsolutePath();
    }

    public j f() {
        this.Y.a(this.X);
        return this.Y;
    }

    public void g() {
        this.aW.a(this.X);
    }

    public j h() {
        return this.aW;
    }

    public String i() {
        return this.X.f;
    }

    public String j() {
        return this.X.i;
    }

    public String k() {
        return this.X.H;
    }

    @Deprecated
    public String l() {
        return this.X.h;
    }

    public String m() {
        if (!dji.pilot.publics.e.d.a(this.X.j)) {
            return this.X.j;
        }
        int indexOf = this.X.i.indexOf("@");
        String str = this.X.i;
        if (-1 != indexOf) {
            return this.X.i.substring(0, indexOf);
        }
        return str;
    }

    public String n() {
        return this.X.Q;
    }

    public String o() {
        return this.X.g;
    }

    public void a(String str, String str2) {
        this.X.h = str;
        this.X.k = str2;
        dji.pilot.usercenter.protocol.g.b(this.ab, str.trim(), str2, W, this.ac);
    }

    public void a(String str, String str2, String str3, String str4) {
        this.X.h = str;
        this.X.k = str2;
        dji.pilot.usercenter.protocol.g.b(this.ab, str.trim(), str2, str3, str4, W, this.ac);
    }

    public void b(String str, String str2) {
        this.X.h = str;
        this.X.k = str2.trim();
        dji.pilot.usercenter.protocol.g.a(this.ab, str.trim(), str2, W, this.ac);
    }

    public void b(String str, String str2, String str3, String str4) {
        this.X.h = str;
        this.X.k = str2.trim();
        dji.pilot.usercenter.protocol.g.a(this.ab, str.trim(), str2, str3, str4, W, this.ac);
    }

    public void a(String str, String str2, String str3) {
        dji.pilot.usercenter.protocol.g.a(this.ab, str.trim(), str2, str3, this.ac);
    }

    public void a(String str) {
        dji.pilot.usercenter.protocol.g.a(this.ab, str.trim(), this.ac);
    }

    public void p() {
        if (this.ae) {
            t();
            this.ae = false;
            this.ah = false;
            this.aU = false;
            this.X.a();
            if (dji.pilot.fpv.d.b.c(this.ab)) {
                dji.pilot.usercenter.protocol.g.a(this.ab, this.X.Q, W, this.ac);
            }
            a u = u();
            if (u != null) {
                u.a(dji.pilot.usercenter.protocol.d.n, 0, 0, null, null);
            }
            s.c(this.ab);
        }
    }

    public void q() {
        if (this.ae && !this.aU) {
            dji.pilot.usercenter.protocol.g.b(this.ab, this.X.Q, W, this.ac);
        }
    }

    public void a(HashMap<String, String> hashMap) {
        if (this.ae) {
            this.af = hashMap;
            dji.pilot.usercenter.protocol.g.a(this.ab, this.X.Q, hashMap, W, this.ac);
        }
    }

    public void b(String str) {
        if (this.ae) {
            dji.pilot.usercenter.protocol.g.c(this.ab, this.X.Q, str, W, this.ac);
        }
    }

    private boolean r() {
        return this.aa;
    }

    private void s() {
        g.a(this.ab, O, e.a(this.X.h));
        g.a(this.ab, Q, e.a(this.X.Q));
        g.a(this.ab, U, e.a(this.X.g));
        g.a(this.ab, V, e.a(this.X.H));
    }

    private void t() {
        g.a(this.ab, Q);
        g.a(this.ab, S);
        g.a(this.ab, T);
        g.a(this.ab, U);
        g.a(this.ab, V);
        dji.pilot.usercenter.f.c.e(e());
    }

    private a u() {
        a aVar = null;
        synchronized (this.ad) {
            if (!this.ad.isEmpty()) {
                aVar = (a) this.ad.get(0);
            }
        }
        return aVar;
    }

    private void a(k kVar) {
        this.X.f = kVar.i.f;
        this.X.m = kVar.i.m;
        this.X.n = kVar.i.n;
        this.X.l = kVar.i.l;
        this.X.o = kVar.i.o;
        this.X.p = kVar.i.p;
        this.X.q = kVar.i.q;
        this.X.r = kVar.i.r;
        this.X.s = kVar.i.s;
        this.X.t = kVar.i.t;
        this.X.v = kVar.i.v;
        this.X.w = kVar.i.w;
        this.X.x = kVar.i.x;
        this.X.y = kVar.i.y;
        this.X.z = kVar.i.z;
        this.X.A = kVar.i.A;
        this.X.B = kVar.i.B;
        this.X.C = kVar.i.C;
        this.X.D = kVar.i.D;
        this.X.E = kVar.i.E;
        this.X.F = kVar.i.F;
        this.X.j = kVar.i.j;
        this.X.u = kVar.i.u;
        this.X.G = kVar.i.G;
        this.X.I = kVar.i.I;
        this.X.J = kVar.i.J;
        this.X.K = kVar.i.K;
        this.X.L = kVar.i.L;
        this.X.M = kVar.i.M;
        this.X.N = kVar.i.N;
        this.X.O = kVar.i.O;
        this.X.H = kVar.i.H;
        this.X.P.clear();
        if (!kVar.i.P.isEmpty()) {
            int size = kVar.i.P.size();
            for (int i = 0; i < size; i++) {
                dji.pilot.usercenter.mode.d dVar = (dji.pilot.usercenter.mode.d) kVar.i.P.get(i);
                if (dVar != null && dVar.g == 3) {
                    this.X.P.add(dVar);
                }
            }
        }
        if (dji.pilot.publics.e.d.a(this.X.j)) {
            g.a(this.ab, S);
        } else {
            g.a(this.ab, S, e.a(this.X.j));
        }
        if (dji.pilot.publics.e.d.a(this.X.f)) {
            g.a(this.ab, T);
        } else {
            g.a(this.ab, T, e.a(this.X.f));
        }
        if (dji.pilot.publics.e.d.a(this.X.H)) {
            g.a(this.ab, V);
        } else {
            g.a(this.ab, V, e.a(this.X.H));
        }
    }

    private void a(Map<String, String> map) {
        if (map.containsKey(n.aG)) {
            String str = (String) map.get(n.aG);
            if (n.aY.equals(str)) {
                this.X.n = 1;
            } else if (n.aZ.equals(str)) {
                this.X.n = 2;
            } else {
                this.X.n = 0;
            }
        }
        if (map.containsKey("name")) {
            this.X.q = (String) map.get("name");
            this.X.j = this.X.q;
        }
        if (map.containsKey("country")) {
            this.X.u = (String) map.get("country");
        }
        if (map.containsKey("state")) {
            this.X.v = (String) map.get("state");
        }
        if (map.containsKey(n.B)) {
            this.X.w = (String) map.get(n.B);
        }
        if (dji.pilot.publics.e.d.a(this.X.j)) {
            g.a(this.ab, S);
        } else {
            g.a(this.ab, S, e.a(this.X.j));
        }
    }

    private void a(int i, int i2, Object obj) {
        int i3 = 1;
        if (obj instanceof dji.pilot.usercenter.protocol.e.b) {
            k kVar;
            int i4;
            dji.pilot.usercenter.protocol.e.b bVar = (dji.pilot.usercenter.protocol.e.b) obj;
            if (bVar.d instanceof k) {
                kVar = (k) bVar.d;
            } else {
                kVar = null;
            }
            if (kVar == null || kVar.bo != 0) {
                boolean z = false;
            } else {
                i4 = 1;
            }
            DJILogHelper.getInstance().LOGD(W, "status code[" + kVar.bo + dji.pilot.usercenter.protocol.d.H);
            if (dji.pilot.usercenter.protocol.d.l == i) {
                if (i4 != 0) {
                    dji.pilot.fpv.d.e.b(d.cw_);
                    s.b(this.ab);
                    this.ae = true;
                    if (dji.pilot.publics.e.d.a(kVar.i.h)) {
                        this.X.i = this.X.h.trim().toLowerCase(Locale.US);
                    } else {
                        this.X.i = kVar.i.h.trim();
                    }
                    this.X.g = kVar.i.g;
                    this.X.Q = kVar.i.Q;
                    this.X.k = "";
                    s();
                    q();
                    dji.pilot2.publics.a.a.getInstance().d();
                    Log.d("fly_unlimit", "DJITermsManager login 1");
                    if (!(dji.pilot2.publics.a.a.getInstance().c() || dji.pilot2.publics.a.a.getInstance().b(this.X.Q)) || dji.pilot2.publics.a.a.getInstance().c()) {
                        Log.d("fly_unlimit", "DJITermsManager login 2");
                        dji.thirdparty.a.c.a().e(dji.pilot2.publics.a.a.a.show);
                    }
                    dji.pilot2.publics.a.a.getInstance().a(this.X.Q);
                } else {
                    this.X.h = "";
                    this.X.i = "";
                    this.X.k = "";
                    this.X.j = "";
                }
            } else if (dji.pilot.usercenter.protocol.d.m == i) {
                if (i4 != 0) {
                    dji.pilot.fpv.d.e.b(d.cy_);
                    s.b(this.ab);
                    b(this.X.h, this.X.k);
                } else {
                    this.X.h = "";
                    this.X.i = "";
                    this.X.k = "";
                }
            } else if (dji.pilot.usercenter.protocol.d.o == i) {
                if (i4 != 0) {
                    this.aU = true;
                    a(kVar);
                    if (dji.pilot.publics.e.d.a(kVar.i.h)) {
                        this.X.i = this.X.h.trim().toLowerCase(Locale.US);
                    } else {
                        this.X.i = kVar.i.h.trim();
                    }
                    List a = dji.pilot.usercenter.e.a.getInstance().a(null, null);
                    if (a != null && !a.isEmpty()) {
                        int size = a.size();
                        for (r1 = 0; r1 < size; r1++) {
                            r0 = (dji.pilot.usercenter.e.b) a.get(r1);
                            if (r0.a.equalsIgnoreCase(this.X.u)) {
                                this.X.R = r0;
                                break;
                            }
                        }
                    }
                    if (!(this.ah || this.X.l == null || this.X.l.trim().length() <= 0)) {
                        c.getInstance().a(this.X.l, e(), false, false, W, this.ag);
                        this.ah = true;
                    }
                }
            } else if (dji.pilot.usercenter.protocol.d.p == i) {
                if (i4 != 0) {
                    a(this.af);
                    List a2 = dji.pilot.usercenter.e.a.getInstance().a(null, null);
                    if (a2 != null && !a2.isEmpty()) {
                        int size2 = a2.size();
                        for (r1 = 0; r1 < size2; r1++) {
                            r0 = (dji.pilot.usercenter.e.b) a2.get(r1);
                            if (r0.a.equalsIgnoreCase(this.X.u)) {
                                this.X.R = r0;
                                break;
                            }
                        }
                    }
                }
                this.af = null;
            } else if (dji.pilot.usercenter.protocol.d.q == i) {
                if (i4 != 0) {
                    this.X.l = kVar.i.l;
                }
            } else if (dji.pilot.usercenter.protocol.d.n == i) {
                t();
                this.ae = false;
                this.ah = false;
                this.X.a();
                i4 = 1;
            }
            a u = u();
            if (u != null) {
                if (i4 != 0) {
                    u.a(i, bVar.a, 0, bVar.c, obj);
                } else {
                    if (kVar != null) {
                        i3 = kVar.bo;
                    }
                    u.a(i, i3, bVar.a, kVar != null ? kVar.g : bVar.c);
                }
            }
            if (kVar != null && kVar.bo == dji.pilot.usercenter.protocol.d.h) {
                a(false);
            }
        }
    }

    private void b(int i, int i2, Object obj) {
        if (obj instanceof dji.pilot.usercenter.protocol.e.b) {
            if (dji.pilot.usercenter.protocol.d.l == i) {
                dji.pilot.fpv.d.e.b(d.cx_);
            } else if (dji.pilot.usercenter.protocol.d.m == i) {
                dji.pilot.fpv.d.e.a("UserCenter_LoginView_SignUpFailed");
            }
            dji.pilot.usercenter.protocol.e.b bVar = (dji.pilot.usercenter.protocol.e.b) obj;
            a u = u();
            if (u != null) {
                u.a(i, i2, bVar.a, bVar);
            }
        }
    }

    private f() {
        this.X = new j();
        this.Y = new j();
        this.aa = false;
        this.ab = null;
        this.ad = new ArrayList(4);
        this.ae = false;
        this.af = null;
        this.ag = null;
        this.ah = false;
        this.aU = false;
        this.aV = null;
        this.aW = new j();
        this.Z = new a(this);
        this.ac = new 2(this);
        this.ag = new 3(this);
    }
}
