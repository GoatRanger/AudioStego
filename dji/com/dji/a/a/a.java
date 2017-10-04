package com.dji.a.a;

import com.alipay.sdk.j.i;
import com.dji.a.d.g;
import com.dji.a.f.d;
import com.dji.a.f.h;
import dji.pilot.usercenter.mode.n;
import java.util.HashMap;
import java.util.Set;

public class a {
    private HashMap<String, Object> a = new HashMap();
    private String b = "";

    public HashMap<String, Object> a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public a(String str, HashMap<String, String> hashMap, boolean z) {
        String a = a(str);
        this.a.put("uuid", com.dji.a.d.a.a().e());
        this.a.put("type", a);
        this.b = d.a();
        this.a.put("reportid", this.b);
        this.a.put(n.ax, Long.valueOf(System.currentTimeMillis()));
        this.a.put("sessionid", g.a());
        HashMap b = com.dji.a.a.a().b();
        if (b != null) {
            for (String a2 : b.keySet()) {
                this.a.put(a2, b.get(a2));
            }
        }
        if (hashMap != null) {
            HashMap hashMap2 = new HashMap();
            for (String a22 : hashMap.keySet()) {
                hashMap2.put(a22, a((String) hashMap.get(a22)));
            }
            this.a.put("eventvalue", hashMap2);
        }
        this.a.put("appver", com.dji.a.d.a.a().d());
    }

    public a(byte[] bArr) {
        this.a = a(bArr);
    }

    public HashMap<String, Object> a(byte[] bArr) {
        HashMap<String, Object> hashMap = new HashMap();
        if (bArr != null && bArr.length > 0) {
            HashMap<String, Object> a = h.a(bArr);
            if (a != null) {
                return a;
            }
        }
        return hashMap;
    }

    public byte[] c() {
        return h.a(this.a);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> keySet = this.a.keySet();
        stringBuilder.append("{");
        for (String str : keySet) {
            stringBuilder.append("{");
            stringBuilder.append(str);
            stringBuilder.append(":");
            stringBuilder.append(this.a.get(str));
            stringBuilder.append("},");
        }
        stringBuilder.append(i.d);
        return stringBuilder.toString();
    }

    private String a(String str) {
        if (str == null) {
            return "null_value_add_by_djia";
        }
        return str;
    }
}
