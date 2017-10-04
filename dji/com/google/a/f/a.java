package com.google.a.f;

import com.google.a.c;
import com.google.a.d;
import com.google.a.e;
import com.google.a.h;
import com.google.a.m;
import com.google.a.p;
import com.google.a.r;
import java.util.Map;

public final class a implements p {
    private final p a;

    public a(p pVar) {
        this.a = pVar;
    }

    public r a(c cVar) throws m, d, h {
        return a(cVar, null);
    }

    public r a(c cVar, Map<e, ?> map) throws m, d, h {
        int a = cVar.a() / 2;
        int b = cVar.b() / 2;
        try {
            return this.a.a(cVar.a(0, 0, a, b), map);
        } catch (m e) {
            try {
                return this.a.a(cVar.a(a, 0, a, b), map);
            } catch (m e2) {
                try {
                    return this.a.a(cVar.a(0, b, a, b), map);
                } catch (m e3) {
                    try {
                        return this.a.a(cVar.a(a, b, a, b), map);
                    } catch (m e4) {
                        return this.a.a(cVar.a(a / 2, b / 2, a, b), map);
                    }
                }
            }
        }
    }

    public void a() {
        this.a.a();
    }
}
