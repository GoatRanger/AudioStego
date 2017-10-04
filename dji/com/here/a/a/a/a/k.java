package com.here.a.a.a.a;

import com.here.a.a.a.s;
import java.util.Date;

public class k extends u {
    public final ad<String> a;
    public final ad<j> b;
    public final ad<s> c;

    public k(s sVar, String str, j jVar, b bVar, ak akVar, String str2, Date date, af afVar, a aVar) {
        super(bVar, akVar, str2, date, afVar, aVar);
        this.c = ad.b(sVar);
        this.a = ad.b(str);
        this.b = ad.b(jVar);
    }

    public static k fromJSON(o oVar) {
        a aVar = null;
        s fromJSON = oVar.b("Line") ? null : s.fromJSON(oVar.c("Line"));
        String a = oVar.a("@journey_ctx", null);
        j fromJSON2 = oVar.b("Freq") ? null : j.fromJSON(oVar.c("Freq"));
        b fromJSON3 = oVar.b("Addr") ? null : b.fromJSON(oVar.c("Addr"));
        ak fromJSON4 = oVar.b("Stn") ? null : ak.fromJSON(oVar.c("Stn"));
        String a2 = oVar.a("@platform", null);
        Date a3 = oVar.b("@time") ? null : s.a(oVar.i("@time"));
        af fromJSON5 = oVar.f("RT") == null ? null : af.fromJSON(oVar.c("RT"));
        if (!oVar.b("AP")) {
            aVar = a.fromJSON(oVar.c("AP"));
        }
        return new k(fromJSON, a, fromJSON2, fromJSON3, fromJSON4, a2, a3, fromJSON5, aVar);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        k kVar = (k) obj;
        if (this.b.equals(kVar.b) && this.c.equals(kVar.c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((super.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }
}
