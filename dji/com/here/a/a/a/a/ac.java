package com.here.a.a.a.a;

import com.here.a.a.a.a.t.a;
import java.util.Iterator;

public class ac {
    public final String a;
    public final ad<String> b;
    public final ad<i> c;
    public final ad<String> d;
    public final ad<t> e;
    public final ad<t> f;
    public final ad<t> g;

    public ac(String str, String str2, i iVar, String str3, t tVar, t tVar2, t tVar3) {
        if (str == null) {
            throw new NullPointerException("Operator name can't be null.");
        }
        this.a = str;
        this.b = ad.b(str2);
        this.c = ad.b(iVar);
        this.d = ad.b(str3);
        this.e = ad.b(tVar);
        this.f = ad.b(tVar2);
        this.g = ad.b(tVar3);
    }

    public static ac fromJSON(o oVar) {
        t tVar;
        t tVar2;
        t tVar3;
        String str;
        if (oVar.b("Link")) {
            tVar = null;
            tVar2 = null;
            tVar3 = null;
            str = null;
        } else {
            Iterator it = oVar.d("Link").iterator();
            String str2 = null;
            String str3 = null;
            str = null;
            String str4 = null;
            while (it.hasNext()) {
                String str5;
                t fromJSON = t.fromJSON((o) it.next());
                if (a.AGENCY == fromJSON.b || a.WEBSITE == fromJSON.b) {
                    str3 = (String) fromJSON.d.c(null);
                    str5 = str2;
                    str2 = str;
                } else if (a.AGENCY_LOGO == fromJSON.b) {
                    Object obj = fromJSON;
                    str2 = str;
                    r1 = str3;
                    str3 = str4;
                } else if (a.TARIFF == fromJSON.b) {
                    str5 = str2;
                    Object obj2 = fromJSON;
                    r1 = str3;
                    str3 = str4;
                } else {
                    str5 = str2;
                    r1 = str3;
                    str2 = str;
                    str3 = str4;
                }
                str = str2;
                str4 = str3;
                str2 = str5;
                Object obj3 = fromJSON;
            }
            tVar = str2;
            tVar2 = str3;
            String str6 = str;
            str = str4;
            tVar3 = str6;
        }
        return new ac(oVar.i("@name"), oVar.a("@code", null), oVar.b("@type") ? null : i.a(oVar.i("@type")), str, tVar3, tVar2, tVar);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ac acVar = (ac) obj;
        if (this.a.equals(acVar.a) && this.b.equals(acVar.b) && this.c.equals(acVar.c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
    }
}
