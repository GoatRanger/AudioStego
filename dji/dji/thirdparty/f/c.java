package dji.thirdparty.f;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.pilot.usercenter.protocol.d;

public final class c<T> {
    private static final c<Void> d = new c(a.OnCompleted, null, null);
    private final a a;
    private final Throwable b;
    private final T c;

    public enum a {
        OnNext,
        OnError,
        OnCompleted
    }

    public static <T> c<T> a(T t) {
        return new c(a.OnNext, t, null);
    }

    public static <T> c<T> a(Throwable th) {
        return new c(a.OnError, null, th);
    }

    public static <T> c<T> a() {
        return d;
    }

    public static <T> c<T> a(Class<T> cls) {
        return d;
    }

    private c(a aVar, T t, Throwable th) {
        this.c = t;
        this.b = th;
        this.a = aVar;
    }

    public Throwable b() {
        return this.b;
    }

    public T c() {
        return this.c;
    }

    public boolean d() {
        return i() && this.c != null;
    }

    public boolean e() {
        return g() && this.b != null;
    }

    public a f() {
        return this.a;
    }

    public boolean g() {
        return f() == a.OnError;
    }

    public boolean h() {
        return f() == a.OnCompleted;
    }

    public boolean i() {
        return f() == a.OnNext;
    }

    public void a(e<? super T> eVar) {
        if (i()) {
            eVar.a_(c());
        } else if (h()) {
            eVar.o_();
        } else if (g()) {
            eVar.a(b());
        }
    }

    public String toString() {
        StringBuilder append = new StringBuilder(d.G).append(super.toString()).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(f());
        if (d()) {
            append.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(c());
        }
        if (e()) {
            append.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(b().getMessage());
        }
        append.append(d.H);
        return append.toString();
    }

    public int hashCode() {
        int hashCode = f().hashCode();
        if (d()) {
            hashCode = (hashCode * 31) + c().hashCode();
        }
        if (e()) {
            return (hashCode * 31) + b().hashCode();
        }
        return hashCode;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        c cVar = (c) obj;
        if (cVar.f() != f()) {
            return false;
        }
        if (d() && !c().equals(cVar.c())) {
            return false;
        }
        if (e() && !b().equals(cVar.b())) {
            return false;
        }
        if (!d() && !e() && cVar.d()) {
            return false;
        }
        if (d() || e() || !cVar.e()) {
            return true;
        }
        return false;
    }
}
