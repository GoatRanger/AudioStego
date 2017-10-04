package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

public final class ba extends m implements Cloneable {
    static Map<String, String> i = new HashMap();
    static final /* synthetic */ boolean j;
    public long a = 0;
    public byte b = (byte) 0;
    public String c = "";
    public String d = "";
    public String e = "";
    public Map<String, String> f = null;
    public String g = "";
    public boolean h = true;

    static {
        boolean z;
        if (ba.class.desiredAssertionStatus()) {
            z = false;
        } else {
            z = true;
        }
        j = z;
        i.put("", "");
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        ba baVar = (ba) obj;
        if (n.a(this.a, baVar.a) && n.a(this.b, baVar.b) && n.a(this.c, baVar.c) && n.a(this.d, baVar.d) && n.a(this.e, baVar.e) && n.a(this.f, baVar.f) && n.a(this.g, baVar.g) && n.a(this.h, baVar.h)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            if (!j) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    public void a(l lVar) {
        lVar.a(this.a, 0);
        lVar.b(this.b, 1);
        if (this.c != null) {
            lVar.a(this.c, 2);
        }
        if (this.d != null) {
            lVar.a(this.d, 3);
        }
        if (this.e != null) {
            lVar.a(this.e, 4);
        }
        if (this.f != null) {
            lVar.a(this.f, 5);
        }
        if (this.g != null) {
            lVar.a(this.g, 6);
        }
        lVar.a(this.h, 7);
    }

    public void a(k kVar) {
        this.a = kVar.a(this.a, 0, true);
        this.b = kVar.a(this.b, 1, true);
        this.c = kVar.a(2, false);
        this.d = kVar.a(3, false);
        this.e = kVar.a(4, false);
        this.f = (Map) kVar.a(i, 5, false);
        this.g = kVar.a(6, false);
        this.h = kVar.a(this.h, 7, false);
    }

    public void a(StringBuilder stringBuilder, int i) {
        i iVar = new i(stringBuilder, i);
        iVar.a(this.a, "startTime");
        iVar.a(this.b, "startType");
        iVar.a(this.c, "userId");
        iVar.a(this.d, "proceName");
        iVar.a(this.e, "sessionId");
        iVar.a(this.f, "valueMap");
        iVar.a(this.g, "gatewayIp");
        iVar.a(this.h, "coldStart");
    }
}
