package com.here.a.a.a.a;

import com.here.a.a.a.i.d;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class s {
    public final String a;
    public final ad<d> b;
    public final ad<String> c;
    public final ad<ac> d;
    public final ad<String> e;
    public final ad<Boolean> f;
    public final ad<Boolean> g;
    public final ad<String> h;
    public final ad<String> i;
    public final ad<String> j;
    public final ad<String> k;
    public final ad<Integer> l;
    private final ad<String> m;
    private ad<String> n;

    public enum a {
        _12x12(12),
        _34x34(34);
        
        public final int c;

        private a(int i) {
            this.c = i;
        }
    }

    public s(String str, d dVar, String str2, ac acVar, String str3, Boolean bool, Boolean bool2, String str4, String str5, String str6, String str7, String str8, Integer num) {
        if (str == null) {
            throw new NullPointerException("Line name can't be null.");
        }
        this.a = str;
        this.b = ad.b(dVar);
        this.c = ad.b(str2);
        this.d = ad.b(acVar);
        this.e = ad.b(str3);
        this.f = ad.b(bool);
        this.g = ad.b(bool2);
        this.h = ad.b(str4);
        this.i = ad.b(str5);
        this.j = ad.b(str6);
        this.k = ad.b(str7);
        this.l = ad.b(num);
        this.m = ad.b(str8);
        this.n = ad.a();
    }

    public ad<String> a(a aVar) {
        if (!this.n.c() || !this.m.c()) {
            return ad.a();
        }
        String str = (String) this.n.b();
        if (!str.endsWith(dji.pilot.usercenter.protocol.d.t)) {
            str = str + dji.pilot.usercenter.protocol.d.t;
        }
        return ad.a(String.format("%sicons/lines/%s_%d.png", new Object[]{str, this.m.b(), Integer.valueOf(aVar.c)}));
    }

    public static s fromJSON(o oVar) {
        Integer num;
        Boolean bool;
        Boolean bool2;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        Object obj = null;
        Boolean bool3 = null;
        Integer num2 = null;
        if (oVar.b("At")) {
            num = null;
            bool = null;
            bool2 = null;
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
            str6 = null;
        } else {
            String str7;
            Iterator it = oVar.d("At").iterator();
            Boolean bool4 = null;
            str6 = null;
            String str8 = null;
            String str9 = null;
            str5 = null;
            str4 = null;
            str3 = null;
            while (it.hasNext()) {
                Boolean bool5;
                String str10;
                Integer num3;
                o oVar2 = (o) it.next();
                str = oVar2.i("@id");
                String a = oVar2.a("$", null);
                Integer num4;
                if ("operator".equalsIgnoreCase(str)) {
                    num4 = num2;
                    bool5 = bool3;
                    bool3 = bool4;
                    str10 = str7;
                    str7 = str6;
                    str6 = str8;
                    str8 = str9;
                    str9 = str5;
                    str5 = str4;
                    str4 = a;
                    num3 = num4;
                } else if (WidgetRequestParam.REQ_PARAM_COMMENT_CATEGORY.equalsIgnoreCase(str)) {
                    str4 = str3;
                    r18 = bool3;
                    bool3 = bool4;
                    str10 = str7;
                    str7 = str6;
                    str6 = str8;
                    str8 = str9;
                    str9 = str5;
                    str5 = a;
                    num3 = num2;
                    bool5 = r18;
                } else if ("bikeAllowed".equalsIgnoreCase(str)) {
                    str10 = str7;
                    str7 = str6;
                    str6 = str8;
                    str8 = str9;
                    str9 = str5;
                    str5 = str4;
                    str4 = str3;
                    r18 = bool3;
                    bool3 = Boolean.valueOf("1".equalsIgnoreCase(a));
                    num3 = num2;
                    bool5 = r18;
                } else if ("barrierFree".equalsIgnoreCase(str)) {
                    bool3 = bool4;
                    str10 = str7;
                    str7 = str6;
                    str6 = str8;
                    str8 = str9;
                    str9 = str5;
                    str5 = str4;
                    str4 = str3;
                    num4 = num2;
                    bool5 = Boolean.valueOf("1".equalsIgnoreCase(a));
                    num3 = num4;
                } else if ("color".equalsIgnoreCase(str)) {
                    str5 = str4;
                    str4 = str3;
                    r18 = bool4;
                    str10 = str7;
                    str7 = str6;
                    str6 = str8;
                    str8 = str9;
                    str9 = a;
                    num3 = num2;
                    bool5 = bool3;
                    bool3 = r18;
                } else if ("textColor".equalsIgnoreCase(str)) {
                    str9 = str5;
                    str5 = str4;
                    str4 = str3;
                    r18 = str7;
                    str7 = str6;
                    str6 = str8;
                    str8 = a;
                    num3 = num2;
                    bool5 = bool3;
                    bool3 = bool4;
                    str10 = r18;
                } else if ("outlineColor".equalsIgnoreCase(str)) {
                    str8 = str9;
                    str9 = str5;
                    str5 = str4;
                    str4 = str3;
                    r18 = str6;
                    str6 = a;
                    num3 = num2;
                    bool5 = bool3;
                    bool3 = bool4;
                    str10 = str7;
                    str7 = r18;
                } else if ("iconShape".equalsIgnoreCase(str)) {
                    str6 = str8;
                    str8 = str9;
                    str9 = str5;
                    str5 = str4;
                    str4 = str3;
                    num4 = num2;
                    bool5 = bool3;
                    bool3 = bool4;
                    str10 = str7;
                    str7 = a;
                    num3 = num4;
                } else if ("iconId".equalsIgnoreCase(str)) {
                    str7 = str6;
                    str6 = str8;
                    str8 = str9;
                    str9 = str5;
                    str5 = str4;
                    str4 = str3;
                    r18 = bool4;
                    str10 = a;
                    num3 = num2;
                    bool5 = bool3;
                    bool3 = r18;
                } else if ("uncertainity".equalsIgnoreCase(str)) {
                    num3 = a != null ? Integer.valueOf(Integer.parseInt(a)) : null;
                    bool5 = bool3;
                    bool3 = bool4;
                    str10 = str7;
                    str7 = str6;
                    str6 = str8;
                    str8 = str9;
                    str9 = str5;
                    str5 = str4;
                    str4 = str3;
                } else {
                    num3 = num2;
                    bool5 = bool3;
                    bool3 = bool4;
                    str10 = str7;
                    str7 = str6;
                    str6 = str8;
                    str8 = str9;
                    str9 = str5;
                    str5 = str4;
                    str4 = str3;
                }
                str3 = str4;
                str4 = str5;
                str5 = str9;
                str9 = str8;
                str8 = str6;
                str6 = str7;
                str7 = str10;
                bool4 = bool3;
                bool3 = bool5;
                num2 = num3;
            }
            num = num2;
            str = str7;
            str2 = str6;
            str6 = str4;
            obj = str3;
            str3 = str8;
            str4 = str9;
            bool = bool3;
            bool2 = bool4;
        }
        d dVar = null;
        if (!oVar.b("@code")) {
            int intValue = oVar.j("@code").intValue();
            if (intValue < d.a().length) {
                dVar = d.a()[intValue];
            }
        }
        return new s(oVar.i("@name"), dVar, oVar.a("@dir", null), (ac) a(oVar).get(obj), str6, bool2, bool, str5, str4, str3, str2, str, num);
    }

    protected void a(String str) {
        this.n = ad.b(str);
    }

    private static Map<String, ac> a(o oVar) {
        while (oVar != null && oVar.b("Operators")) {
            oVar = oVar.a();
        }
        if (oVar != null) {
            Collection<ac> b = ai.b(oVar);
            if (b != null) {
                Map<String, ac> hashMap = new HashMap();
                for (ac acVar : b) {
                    hashMap.put(acVar.b.c(acVar.a), acVar);
                }
                return hashMap;
            }
        }
        return Collections.emptyMap();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        s sVar = (s) obj;
        if (this.a.equals(sVar.a) && this.b.equals(sVar.b) && this.c.equals(sVar.c) && this.d.equals(sVar.d) && this.e.equals(sVar.e)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode();
    }
}
