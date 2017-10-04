package com.google.a;

import com.google.a.c.a;
import com.google.a.c.b;

public final class c {
    private final b a;
    private b b;

    public c(b bVar) {
        if (bVar == null) {
            throw new IllegalArgumentException("Binarizer must be non-null.");
        }
        this.a = bVar;
    }

    public int a() {
        return this.a.c();
    }

    public int b() {
        return this.a.d();
    }

    public a a(int i, a aVar) throws m {
        return this.a.a(i, aVar);
    }

    public b c() throws m {
        if (this.b == null) {
            this.b = this.a.b();
        }
        return this.b;
    }

    public boolean d() {
        return this.a.a().b();
    }

    public c a(int i, int i2, int i3, int i4) {
        return new c(this.a.a(this.a.a().a(i, i2, i3, i4)));
    }

    public boolean e() {
        return this.a.a().c();
    }

    public c f() {
        return new c(this.a.a(this.a.a().e()));
    }

    public c g() {
        return new c(this.a.a(this.a.a().f()));
    }

    public String toString() {
        try {
            return c().toString();
        } catch (m e) {
            return "";
        }
    }
}
