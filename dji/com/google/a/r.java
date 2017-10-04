package com.google.a;

import java.util.EnumMap;
import java.util.Map;

public final class r {
    private final String a;
    private final byte[] b;
    private t[] c;
    private final a d;
    private Map<s, Object> e;
    private final long f;

    public r(String str, byte[] bArr, t[] tVarArr, a aVar) {
        this(str, bArr, tVarArr, aVar, System.currentTimeMillis());
    }

    public r(String str, byte[] bArr, t[] tVarArr, a aVar, long j) {
        this.a = str;
        this.b = bArr;
        this.c = tVarArr;
        this.d = aVar;
        this.e = null;
        this.f = j;
    }

    public String a() {
        return this.a;
    }

    public byte[] b() {
        return this.b;
    }

    public t[] c() {
        return this.c;
    }

    public a d() {
        return this.d;
    }

    public Map<s, Object> e() {
        return this.e;
    }

    public void a(s sVar, Object obj) {
        if (this.e == null) {
            this.e = new EnumMap(s.class);
        }
        this.e.put(sVar, obj);
    }

    public void a(Map<s, Object> map) {
        if (map == null) {
            return;
        }
        if (this.e == null) {
            this.e = map;
        } else {
            this.e.putAll(map);
        }
    }

    public void a(t[] tVarArr) {
        Object obj = this.c;
        if (obj == null) {
            this.c = tVarArr;
        } else if (tVarArr != null && tVarArr.length > 0) {
            Object obj2 = new t[(obj.length + tVarArr.length)];
            System.arraycopy(obj, 0, obj2, 0, obj.length);
            System.arraycopy(tVarArr, 0, obj2, obj.length, tVarArr.length);
            this.c = obj2;
        }
    }

    public long f() {
        return this.f;
    }

    public String toString() {
        return this.a;
    }
}
