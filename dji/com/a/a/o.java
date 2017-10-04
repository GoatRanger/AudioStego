package com.a.a;

import android.content.Context;
import com.a.a.a.b;
import com.a.a.a.b.d;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class o extends p {

    static class a extends a<o> {
        public /* synthetic */ p b(Map map) {
            return a(map);
        }

        a(Context context, c cVar, String str) {
            super(context, cVar, "traits-" + str, o.class);
        }

        public o a(Map<String, Object> map) {
            return new o(new d(map));
        }
    }

    public /* synthetic */ p b(String str, Object obj) {
        return a(str, obj);
    }

    static o a() {
        o oVar = new o(new d());
        oVar.b(UUID.randomUUID().toString());
        oVar.a(oVar.e());
        return oVar;
    }

    private o(Map<String, Object> map) {
        super(map);
    }

    public o b() {
        return new o(Collections.unmodifiableMap(new LinkedHashMap(this)));
    }

    o a(String str) {
        return a("userId", str);
    }

    public String c() {
        return c("userId");
    }

    o b(String str) {
        return a("anonymousId", str);
    }

    public String d() {
        return c("anonymousId");
    }

    public String e() {
        CharSequence c = c();
        return b.a(c) ? d() : c;
    }

    public o a(String str, Object obj) {
        super.b(str, obj);
        return this;
    }
}
