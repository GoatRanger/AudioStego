package dji.dbox.upgrade.p4.statemachine;

import android.content.Context;
import android.os.Message;
import com.here.odnp.config.OdnpConfigStatic;
import dji.dbox.upgrade.p4.c.a$a;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataCommonGetPushUpgradeStatus.DJIUpgradeCompleteReason;

public class g extends dji.e.a.c {
    private static final int I = 0;
    private static final int J = 1;
    private static final int K = 2;
    private static final int L = 3;
    private static final int M = 40;
    private static final int N = 10;
    private static final int O = 11;
    private static final int P = 12;
    private static final int Q = 13;
    private static final int R = 9;
    private static final int S = 14;
    private static final int T = 15;
    private static final int U = 16;
    private static final int V = 17;
    private static final int W = 18;
    private static final int X = 19;
    private static final int Y = 20;
    private static final int Z = 21;
    private static final int aa = 22;
    private static final int ab = 23;
    private static final int ac = 24;
    private static final int ad = 25;
    private static final int ae = 26;
    private static final int af = 101;
    private static final int ag = 102;
    private static final int ah = 104;
    private static final int ai = 106;
    private static final int aj = 108;
    private static final int ak = 109;
    private static final int al = 1000;
    private static final String e = g.class.getSimpleName();
    private v A;
    private s B;
    private j C;
    private p D;
    private o E;
    private q F;
    private t G;
    private r H;
    public d a;
    public int b;
    private String f;
    private b g;
    private e h;
    private c i;
    private Context j;
    private dji.dbox.upgrade.p4.b.c k;
    private dji.dbox.upgrade.p4.b.a l;
    private d m;
    private e n;
    private c o;
    private n p;
    private k q;
    private m r;
    private l s;
    private b t;
    private g u;
    private w v;
    private u w;
    private h x;
    private a y;
    private f z;

    class i extends dji.e.a.b {
        final /* synthetic */ g c;

        i(g gVar) {
            this.c = gVar;
        }

        public void a() {
            super.a();
            if (this.c.k != null) {
                this.c.k.a(getClass().getSimpleName());
            }
            DJILogHelper.getInstance().LOGI(g.e, "enter " + getClass().getSimpleName());
        }

        public void b() {
            super.b();
            DJILogHelper.getInstance().LOGI(g.e, "exit " + getClass().getSimpleName());
        }
    }

    class d extends i {
        final /* synthetic */ g b;

        d(g gVar) {
            this.b = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
        }

        public boolean a(Message message) {
            switch (message.what) {
                case 1:
                    this.b.a(this.b.t);
                    this.b.f(11);
                    return true;
                case 3:
                    this.b.a(this.b.n);
                    return true;
                default:
                    return super.a(message);
            }
        }

        public void b() {
            super.b();
        }
    }

    class l extends d {
        final /* synthetic */ g d;

        l(g gVar) {
            this.d = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
        }

        public boolean a(Message message) {
            return super.a(message);
        }

        public void b() {
            super.b();
        }
    }

    class a extends l {
        final /* synthetic */ g a;

        a(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
        }

        public boolean a(Message message) {
            switch (message.what) {
                case 0:
                    this.a.a(this.a.t);
                    this.a.f(11);
                    return true;
                default:
                    return super.a(message);
            }
        }

        public void b() {
            super.b();
        }
    }

    class k extends d {
        final /* synthetic */ g d;

        k(g gVar) {
            this.d = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
        }

        public boolean a(Message message) {
            return super.a(message);
        }

        public void b() {
            super.b();
        }
    }

    class b extends k {
        final /* synthetic */ g a;

        b(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
        }

        public boolean a(Message message) {
            switch (message.what) {
                case 9:
                    this.a.a(this.a.u);
                    this.a.f(14);
                    return true;
                case 10:
                    if (this.a.l == null) {
                        return true;
                    }
                    this.a.l.k();
                    return true;
                case 11:
                    if (this.a.g != null) {
                        this.a.g.stop();
                    }
                    if (this.a.g == null || !this.a.f.equals(DJIUpgradeP4Service.b)) {
                        this.a.f = DJIUpgradeP4Service.b;
                        this.a.g = a.a(this.a.j, this.a, this.a.f);
                    }
                    if (this.a.g == null) {
                        this.a.a(this.a.y);
                        if (this.a.l == null) {
                            return true;
                        }
                        this.a.l.a("no device");
                        return true;
                    }
                    if (this.a.l != null) {
                        this.a.l.a();
                    }
                    this.a.g.startCollect();
                    return true;
                case 12:
                    this.a.a(this.a.y);
                    return true;
                case 13:
                    if (this.a.l == null) {
                        return true;
                    }
                    this.a.l.b();
                    return true;
                case 26:
                    this.a.a(this.a.x);
                    this.a.f(23);
                    return true;
                case 40:
                    if (this.a.l == null) {
                        return true;
                    }
                    this.a.l.l();
                    return true;
                default:
                    return super.a(message);
            }
        }

        public void b() {
            super.b();
        }
    }

    class c extends i {
        final /* synthetic */ g a;

        c(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
        }

        public boolean a(Message message) {
            switch (message.what) {
                case 3:
                    this.a.a(this.a.n);
                    return true;
                default:
                    return super.a(message);
            }
        }

        public void b() {
            super.b();
        }
    }

    class e extends i {
        final /* synthetic */ g a;

        e(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
        }

        public boolean a(Message message) {
            switch (message.what) {
                case 1:
                    this.a.a(this.a.t);
                    this.a.f(11);
                    return true;
                case 2:
                    this.a.a(this.a.o);
                    return true;
                default:
                    return super.a(message);
            }
        }

        public void b() {
            super.b();
        }
    }

    class f extends l {
        final /* synthetic */ g a;

        f(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
        }

        public boolean a(Message message) {
            switch (message.what) {
                case 0:
                    this.a.a(this.a.u);
                    this.a.f(14);
                    return true;
                default:
                    return super.a(message);
            }
        }

        public void b() {
            super.b();
        }
    }

    class g extends k {
        final /* synthetic */ g a;

        g(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
        }

        public boolean a(Message message) {
            switch (message.what) {
                case 14:
                    if (this.a.l != null) {
                        this.a.l.c();
                    }
                    if (this.a.i == null) {
                        this.a.i = new c(this.a, this.a.j);
                    }
                    this.a.i.b();
                    return true;
                case 15:
                    this.a.a(this.a.z);
                    return true;
                case 16:
                    if (this.a.l != null) {
                        this.a.l.d();
                    }
                    this.a.a(this.a.v);
                    this.a.f(17);
                    return true;
                default:
                    return super.a(message);
            }
        }

        public void b() {
            super.b();
        }
    }

    class h extends k {
        final /* synthetic */ g a;

        h(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
        }

        public boolean a(Message message) {
            switch (message.what) {
                case 23:
                    return true;
                case 24:
                    if (this.a.l != null) {
                        this.a.l.a((DJIUpgradeCompleteReason) message.obj);
                    }
                    this.a.a(this.a.C);
                    return true;
                case 25:
                    this.a.a(this.a.r);
                    return true;
                default:
                    return super.a(message);
            }
        }

        public void b() {
            super.b();
        }
    }

    class j extends l {
        final /* synthetic */ g a;

        j(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
        }

        public boolean a(Message message) {
            switch (message.what) {
                case 0:
                    this.a.h.a(this.a.i.c(), true);
                    this.a.a(this.a.w);
                    this.a.f(20);
                    break;
            }
            return super.a(message);
        }

        public void b() {
            super.b();
        }
    }

    class m extends d {
        final /* synthetic */ g a;

        m(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
            if (this.a.l != null) {
                this.a.l.j();
            }
            this.a.h(11);
            this.a.a(11, (long) OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
        }

        public boolean a(Message message) {
            switch (message.what) {
                case 11:
                    this.a.a(this.a.t);
                    this.a.f(11);
                    break;
            }
            return super.a(message);
        }

        public void b() {
            super.b();
        }
    }

    class n extends d {
        final /* synthetic */ g a;

        n(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
        }

        public boolean a(Message message) {
            return super.a(message);
        }

        public void b() {
            super.b();
        }
    }

    class s extends l {
        final /* synthetic */ g e;

        s(g gVar) {
            this.e = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
        }

        public boolean a(Message message) {
            return super.a(message);
        }

        public void b() {
            super.b();
        }
    }

    class o extends s {
        final /* synthetic */ g a;

        o(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
            if (this.a.l != null) {
                this.a.l.a(dji.dbox.upgrade.p4.b.b.a.Enter, null);
            }
        }

        public boolean a(Message message) {
            switch (message.what) {
                case 0:
                    this.a.a(this.a.w);
                    this.a.b(20, this.a.a(20, 0, 0));
                    return true;
                default:
                    return super.a(message);
            }
        }

        public void b() {
            super.b();
        }
    }

    class p extends s {
        final /* synthetic */ g a;

        p(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
            if (this.a.l != null) {
                this.a.l.a(dji.dbox.upgrade.p4.b.b.a.Init, null);
            }
        }

        public boolean a(Message message) {
            int i = message.what;
            return super.a(message);
        }

        public void b() {
            super.b();
        }
    }

    class q extends s {
        final /* synthetic */ g a;

        q(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
            if (this.a.l != null) {
                this.a.l.a(dji.dbox.upgrade.p4.b.b.a.Pretrans, null);
            }
        }

        public boolean a(Message message) {
            switch (message.what) {
                case 0:
                    this.a.a(this.a.w);
                    this.a.b(20, this.a.a(20, 0, 0));
                    return true;
                default:
                    return super.a(message);
            }
        }

        public void b() {
            super.b();
        }
    }

    class r extends s {
        final /* synthetic */ g a;

        r(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
        }

        public boolean a(Message message) {
            switch (message.what) {
                case 0:
                    this.a.a(this.a.w);
                    this.a.b(20, this.a.a(20, 0, 0));
                    return true;
                case g.aj /*108*/:
                    if (this.a.l != null) {
                        this.a.l.a(dji.dbox.upgrade.p4.b.b.a.Quit, (dji.midware.data.config.P3.a) message.obj);
                    }
                    return true;
                default:
                    return super.a(message);
            }
        }

        public void b() {
            super.b();
        }
    }

    class t extends s {
        final /* synthetic */ g a;

        t(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
            if (this.a.l != null) {
                this.a.l.a(dji.dbox.upgrade.p4.b.b.a.Transing, null);
            }
        }

        public boolean a(Message message) {
            switch (message.what) {
                case 0:
                    this.a.a(this.a.w);
                    this.a.b(20, this.a.a(20, 0, 0));
                    return true;
                default:
                    return super.a(message);
            }
        }

        public void b() {
            super.b();
        }
    }

    class u extends k {
        final /* synthetic */ g a;

        u(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
        }

        public boolean a(Message message) {
            boolean z = false;
            switch (message.what) {
                case 20:
                    if (this.a.l != null) {
                        this.a.l.h();
                    }
                    if (this.a.h == null) {
                        this.a.h = new e(this.a);
                    }
                    e q = this.a.h;
                    String c = this.a.i.c();
                    if (message.arg1 == 1) {
                        z = true;
                    }
                    q.a(c, z);
                    this.a.h.c();
                    return true;
                case 22:
                    this.a.b = 0;
                    if (this.a.l != null) {
                        this.a.l.i();
                    }
                    this.a.a(this.a.x);
                    this.a.f(23);
                    return true;
                case 101:
                    this.a.a(this.a.D);
                    return true;
                case 102:
                    this.a.a(this.a.E);
                    return true;
                case 104:
                    this.a.a(this.a.F);
                    return true;
                case 106:
                    this.a.a(this.a.G);
                    return true;
                case g.aj /*108*/:
                    this.a.a(this.a.H);
                    this.a.f(this.a.a(message.what, message.obj));
                    return true;
                case g.ak /*109*/:
                    int e = this.a.h.e();
                    if (e != this.a.b) {
                        this.a.b = e;
                        if (this.a.l != null) {
                            this.a.l.b(e);
                        }
                    }
                    this.a.h.d();
                    return true;
                default:
                    return super.a(message);
            }
        }

        public void b() {
            super.b();
        }
    }

    class v extends l {
        final /* synthetic */ g a;

        v(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
            if (this.a.l != null) {
                this.a.l.g();
            }
        }

        public boolean a(Message message) {
            switch (message.what) {
                case 0:
                    this.a.a(this.a.v);
                    this.a.f(17);
                    return true;
                default:
                    return super.a(message);
            }
        }

        public void b() {
            super.b();
        }
    }

    class w extends k {
        final /* synthetic */ g a;

        w(g gVar) {
            this.a = gVar;
            super(gVar);
        }

        public void a() {
            super.a();
        }

        public boolean a(Message message) {
            switch (message.what) {
                case 17:
                    if (this.a.l != null) {
                        this.a.l.e();
                    }
                    this.a.i.a(new a$a(this) {
                        final /* synthetic */ w a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                            this.a.a.f(19);
                        }

                        public void b() {
                            this.a.a.f(18);
                        }
                    });
                    return true;
                case 18:
                    this.a.a(this.a.A);
                    return true;
                case 19:
                    if (this.a.l != null) {
                        this.a.l.f();
                    }
                    this.a.a(this.a.w);
                    this.a.b(20, this.a.a(20, 1, 0));
                    return true;
                default:
                    return super.a(message);
            }
        }

        public void b() {
            super.b();
        }
    }

    public g() {
        super(e);
        this.k = null;
        this.m = new d(this);
        this.n = new e(this);
        this.o = new c(this);
        this.p = new n(this);
        this.q = new k(this);
        this.r = new m(this);
        this.s = new l(this);
        this.t = new b(this);
        this.u = new g(this);
        this.v = new w(this);
        this.w = new u(this);
        this.x = new h(this);
        this.y = new a(this);
        this.z = new f(this);
        this.A = new v(this);
        this.B = new s(this);
        this.C = new j(this);
        this.D = new p(this);
        this.E = new o(this);
        this.F = new q(this);
        this.G = new t(this);
        this.H = new r(this);
        a(this.m);
        a(this.n);
        a(this.o);
        a(this.p, this.m);
        a(this.q, this.m);
        a(this.r, this.m);
        a(this.s, this.m);
        a(this.t, this.q);
        a(this.u, this.q);
        a(this.v, this.q);
        a(this.w, this.q);
        a(this.x, this.q);
        a(this.y, this.s);
        a(this.z, this.s);
        a(this.A, this.s);
        a(this.B, this.s);
        a(this.C, this.s);
        a(this.E, this.B);
        a(this.F, this.B);
        a(this.G, this.B);
        a(this.H, this.B);
        b(this.n);
    }

    public g(Context context) {
        this();
        this.j = context;
    }

    public void a() {
        super.a();
        if (this.l == null) {
            this.l = new dji.dbox.upgrade.p4.b.a();
        }
        if (this.a == null) {
            this.a = new d(this);
        }
    }

    public void a(dji.dbox.upgrade.p4.b.c cVar) {
        this.k = cVar;
    }

    public void a(dji.dbox.upgrade.p4.b.b bVar) {
        if (bVar != null) {
            this.l.add(bVar);
        }
    }

    public void b(dji.dbox.upgrade.p4.b.b bVar) {
        if (bVar != null) {
            this.l.remove(bVar);
        }
    }

    protected void b() {
        if (z() != this.t) {
            a(this.t);
        }
        f(1000);
        f(9);
    }

    protected void c() {
        f(0);
    }

    protected void d() {
        f(3);
    }

    protected void e() {
        f(1);
    }

    protected void f() {
        f(2);
    }

    protected void g() {
        f(11);
    }

    protected void a(String str) {
        if (this.l != null) {
            this.l.a(str);
        }
        f(12);
    }

    protected void h() {
        f(13);
    }

    protected void i() {
        f(40);
    }

    protected void j() {
        f(10);
    }

    protected void b(String str) {
        if (this.l != null) {
            this.l.b(str);
        }
        f(15);
    }

    protected void k() {
        f(16);
    }

    protected void l() {
        f(18);
    }

    protected void m() {
        f(19);
    }

    protected void n() {
        f(101);
    }

    protected void o() {
        f(102);
    }

    protected void p() {
        f(104);
    }

    protected void q() {
        f(106);
    }

    protected void a(dji.midware.data.config.P3.a aVar) {
        f(a((int) aj, (Object) aVar));
    }

    protected void r() {
        f((int) ak);
    }

    protected void s() {
        f(22);
    }

    protected void a(DJIUpgradeCompleteReason dJIUpgradeCompleteReason) {
        dji.e.a.a z = z();
        dji.dbox.upgrade.p4.a.a.b(e, "listenProgressComplete state=" + z.c());
        if (z != this.x) {
            a(this.x);
            b(24, dJIUpgradeCompleteReason);
        }
        b(24, dJIUpgradeCompleteReason);
    }

    protected void t() {
        dji.e.a.a z = z();
        dji.dbox.upgrade.p4.a.a.b(e, "listenProgressComplete state=" + z.c());
        if (z != this.x) {
            a(this.x);
            f(25);
        }
        f(25);
    }

    protected void u() {
        f(26);
    }

    public void a(int i) {
        if (this.l != null) {
            this.l.a(i);
        }
    }

    public void a(String str, int i) {
        if (this.l != null) {
            this.l.a(str, i);
        }
    }

    public boolean v() {
        dji.e.a.a z = z();
        if (!(z instanceof k) || z == this.t) {
            return false;
        }
        return true;
    }

    public void w() {
        if (this.l != null) {
            this.l.m();
        }
    }
}
