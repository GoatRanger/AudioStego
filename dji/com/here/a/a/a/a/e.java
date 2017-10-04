package com.here.a.a.a.a;

import com.here.a.a.a.s;
import java.util.Date;

public class e extends u {
    public e(b bVar, ak akVar, String str, Date date, af afVar, a aVar) {
        super(bVar, akVar, str, date, afVar, aVar);
    }

    public static e fromJSON(o oVar) {
        a aVar = null;
        b fromJSON = oVar.b("Addr") ? null : b.fromJSON(oVar.c("Addr"));
        ak fromJSON2 = oVar.b("Stn") ? null : ak.fromJSON(oVar.c("Stn"));
        String a = oVar.a("@platform", null);
        Date a2 = oVar.b("@time") ? null : s.a(oVar.i("@time"));
        af fromJSON3 = oVar.f("RT") == null ? null : af.fromJSON(oVar.c("RT"));
        if (!oVar.b("AP")) {
            aVar = a.fromJSON(oVar.c("AP"));
        }
        return new e(fromJSON, fromJSON2, a, a2, fromJSON3, aVar);
    }
}
