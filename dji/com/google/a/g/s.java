package com.google.a.g;

import com.google.a.c;
import com.google.a.c.a;
import com.google.a.d;
import com.google.a.e;
import com.google.a.h;
import com.google.a.m;
import com.google.a.r;
import java.util.Map;

public final class s extends x {
    private final x a = new h();

    public r a(int i, a aVar, int[] iArr, Map<e, ?> map) throws m, h, d {
        return a(this.a.a(i, aVar, iArr, (Map) map));
    }

    public r a(int i, a aVar, Map<e, ?> map) throws m, h, d {
        return a(this.a.a(i, aVar, (Map) map));
    }

    public r a(c cVar) throws m, h {
        return a(this.a.a(cVar));
    }

    public r a(c cVar, Map<e, ?> map) throws m, h {
        return a(this.a.a(cVar, map));
    }

    com.google.a.a b() {
        return com.google.a.a.UPC_A;
    }

    protected int a(a aVar, int[] iArr, StringBuilder stringBuilder) throws m {
        return this.a.a(aVar, iArr, stringBuilder);
    }

    private static r a(r rVar) throws h {
        String a = rVar.a();
        if (a.charAt(0) == '0') {
            return new r(a.substring(1), null, rVar.c(), com.google.a.a.UPC_A);
        }
        throw h.a();
    }
}
