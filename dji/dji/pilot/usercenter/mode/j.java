package dji.pilot.usercenter.mode;

import dji.pilot.usercenter.e.b;
import dji.pilot.usercenter.protocol.d;
import java.util.ArrayList;
import java.util.List;

public class j {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 0;
    public static final int e = 1;
    public String A = "";
    public String B = "";
    public String C = "";
    public int D = 0;
    public String E = "";
    public String F = "";
    public String G = "";
    public String H = "";
    public int I = 0;
    public int J = 0;
    public int K = 0;
    public int L = 0;
    public long M = 0;
    public long N = 0;
    public String O = "";
    public final List<d> P = new ArrayList();
    public String Q = "";
    public b R = null;
    public String f = "";
    public String g = "";
    @Deprecated
    public String h = "";
    public String i = "";
    public String j = "";
    public String k = "";
    public String l = "";
    public String m = "";
    public int n = 1;
    public String o = "";
    public String p = "";
    public String q = "";
    public String r = "";
    public String s = "";
    public String t = "";
    public String u = "";
    public String v = "";
    public String w = "";
    public String x = "";
    public String y = "";
    public String z = "";

    public void a(j jVar) {
        this.f = jVar.f;
        this.h = jVar.h;
        this.i = jVar.i;
        this.j = jVar.j;
        this.k = jVar.k;
        this.l = jVar.l;
        this.m = jVar.m;
        this.n = jVar.n;
        this.o = jVar.o;
        this.p = jVar.p;
        this.q = jVar.q;
        this.r = jVar.r;
        this.s = jVar.s;
        this.t = jVar.t;
        this.u = jVar.u;
        this.v = jVar.v;
        this.w = jVar.w;
        this.x = jVar.x;
        this.y = jVar.y;
        this.z = jVar.z;
        this.A = jVar.A;
        this.B = jVar.B;
        this.C = jVar.C;
        this.D = jVar.D;
        this.E = jVar.E;
        this.F = jVar.F;
        this.G = jVar.G;
        this.I = jVar.I;
        this.J = jVar.J;
        this.K = jVar.K;
        this.L = jVar.L;
        this.Q = jVar.Q;
        this.R = jVar.R;
        this.M = jVar.M;
        this.N = jVar.N;
        this.O = jVar.O;
        this.P.clear();
        if (!jVar.P.isEmpty()) {
            this.P.addAll(jVar.P);
        }
    }

    public void a() {
        this.f = "";
        this.j = "";
        this.k = "";
        this.l = "";
        this.m = "";
        this.n = 0;
        this.o = "";
        this.p = "";
        this.q = "";
        this.r = "";
        this.s = "";
        this.t = "";
        this.u = "";
        this.v = "";
        this.w = "";
        this.x = "";
        this.y = "";
        this.z = "";
        this.A = "";
        this.B = "";
        this.C = "";
        this.D = 0;
        this.E = "";
        this.F = "";
        this.G = "";
        this.I = 0;
        this.J = 0;
        this.K = 0;
        this.L = 0;
        this.R = null;
        this.M = 0;
        this.N = 0;
        this.O = "";
        this.P.clear();
    }

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof k)) {
            return equals;
        }
        j jVar = (j) obj;
        if (this.f == jVar.f || (this.Q != null && this.Q.equals(jVar.Q))) {
            return true;
        }
        return equals;
    }

    public int hashCode() {
        if (this.f != null) {
            return 17 + (this.f.hashCode() * 31);
        }
        if (this.Q != null) {
            return 17 + (this.Q.hashCode() * 31);
        }
        return 17;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(56);
        stringBuilder.append("id[").append(this.f).append(d.H);
        stringBuilder.append("email[").append(this.h).append(d.H);
        stringBuilder.append("pwd[").append(this.k).append(d.H);
        stringBuilder.append("nickname[").append(this.j).append(d.H);
        stringBuilder.append("token[").append(this.Q).append(d.H);
        return stringBuilder.toString();
    }
}
