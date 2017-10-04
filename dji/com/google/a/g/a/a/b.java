package com.google.a.g.a.a;

import com.google.a.g.a.c;

final class b {
    private final boolean a;
    private final com.google.a.g.a.b b;
    private final com.google.a.g.a.b c;
    private final c d;

    b(com.google.a.g.a.b bVar, com.google.a.g.a.b bVar2, c cVar, boolean z) {
        this.b = bVar;
        this.c = bVar2;
        this.d = cVar;
        this.a = z;
    }

    boolean a() {
        return this.a;
    }

    com.google.a.g.a.b b() {
        return this.b;
    }

    com.google.a.g.a.b c() {
        return this.c;
    }

    c d() {
        return this.d;
    }

    public boolean e() {
        return this.c == null;
    }

    public String toString() {
        return "[ " + this.b + " , " + this.c + " : " + (this.d == null ? "null" : Integer.valueOf(this.d.a())) + " ]";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (a(this.b, bVar.b) && a(this.c, bVar.c) && a(this.d, bVar.d)) {
            return true;
        }
        return false;
    }

    private static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else {
            return obj.equals(obj2);
        }
    }

    public int hashCode() {
        return (a(this.b) ^ a(this.c)) ^ a(this.d);
    }

    private static int a(Object obj) {
        return obj == null ? 0 : obj.hashCode();
    }
}
