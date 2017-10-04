package com.google.a.g.a.a.a;

import com.google.a.c.a;
import com.google.a.h;
import com.google.a.m;

public abstract class j {
    private final a a;
    private final s b;

    public abstract String a() throws m, h;

    j(a aVar) {
        this.a = aVar;
        this.b = new s(aVar);
    }

    protected final a b() {
        return this.a;
    }

    protected final s c() {
        return this.b;
    }

    public static j a(a aVar) {
        if (aVar.a(1)) {
            return new g(aVar);
        }
        if (!aVar.a(2)) {
            return new k(aVar);
        }
        switch (s.a(aVar, 1, 4)) {
            case 4:
                return new a(aVar);
            case 5:
                return new b(aVar);
            default:
                switch (s.a(aVar, 1, 5)) {
                    case 12:
                        return new c(aVar);
                    case 13:
                        return new d(aVar);
                    default:
                        switch (s.a(aVar, 1, 7)) {
                            case 56:
                                return new e(aVar, "310", "11");
                            case 57:
                                return new e(aVar, "320", "11");
                            case 58:
                                return new e(aVar, "310", "13");
                            case 59:
                                return new e(aVar, "320", "13");
                            case 60:
                                return new e(aVar, "310", "15");
                            case 61:
                                return new e(aVar, "320", "15");
                            case 62:
                                return new e(aVar, "310", "17");
                            case 63:
                                return new e(aVar, "320", "17");
                            default:
                                throw new IllegalStateException("unknown decoder: " + aVar);
                        }
                }
        }
    }
}
