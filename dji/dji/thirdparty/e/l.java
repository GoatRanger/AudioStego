package dji.thirdparty.e;

import dji.thirdparty.b.ab;
import dji.thirdparty.b.ad;
import dji.thirdparty.b.ad.a;
import dji.thirdparty.b.ae;
import dji.thirdparty.b.t;
import dji.thirdparty.b.z;

public final class l<T> {
    private final ad a;
    private final T b;
    private final ae c;

    public static <T> l<T> a(T t) {
        return a((Object) t, new a().a(200).a("OK").a(z.HTTP_1_1).a(new ab.a().a("http://localhost/").d()).a());
    }

    public static <T> l<T> a(T t, t tVar) {
        if (tVar != null) {
            return a((Object) t, new a().a(200).a("OK").a(z.HTTP_1_1).a(tVar).a(new ab.a().a("http://localhost/").d()).a());
        }
        throw new NullPointerException("headers == null");
    }

    public static <T> l<T> a(T t, ad adVar) {
        if (adVar == null) {
            throw new NullPointerException("rawResponse == null");
        } else if (adVar.d()) {
            return new l(adVar, t, null);
        } else {
            throw new IllegalArgumentException("rawResponse must be successful response");
        }
    }

    public static <T> l<T> a(int i, ae aeVar) {
        if (i >= 400) {
            return a(aeVar, new a().a(i).a(z.HTTP_1_1).a(new ab.a().a("http://localhost/").d()).a());
        }
        throw new IllegalArgumentException("code < 400: " + i);
    }

    public static <T> l<T> a(ae aeVar, ad adVar) {
        if (aeVar == null) {
            throw new NullPointerException("body == null");
        } else if (adVar == null) {
            throw new NullPointerException("rawResponse == null");
        } else if (!adVar.d()) {
            return new l(adVar, null, aeVar);
        } else {
            throw new IllegalArgumentException("rawResponse should not be successful response");
        }
    }

    private l(ad adVar, T t, ae aeVar) {
        this.a = adVar;
        this.b = t;
        this.c = aeVar;
    }

    public ad a() {
        return this.a;
    }

    public int b() {
        return this.a.c();
    }

    public String c() {
        return this.a.e();
    }

    public t d() {
        return this.a.g();
    }

    public boolean e() {
        return this.a.d();
    }

    public T f() {
        return this.b;
    }

    public ae g() {
        return this.c;
    }
}
