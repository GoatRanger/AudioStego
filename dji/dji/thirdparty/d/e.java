package dji.thirdparty.d;

public final class e {
    public static final int a = 3;
    public static final int b = 6;
    public static final int c = 7;
    public static final int d = 4;
    public static final int e = 2;
    public static final int f = 5;
    private static final String g = "PRETTYLOGGER";
    private static f h = new f();

    private e() {
    }

    public static h a() {
        return a(g);
    }

    public static h a(String str) {
        h = new f();
        return h.a(str);
    }

    public static void b() {
        h.b();
    }

    public static g b(String str) {
        return h.a(str, h.a().b());
    }

    public static g a(int i) {
        return h.a(null, i);
    }

    public static g a(String str, int i) {
        return h.a(str, i);
    }

    public static void a(int i, String str, String str2, Throwable th) {
        h.a(i, str, str2, th);
    }

    public static void a(String str, Object... objArr) {
        h.a(str, objArr);
    }

    public static void a(Object obj) {
        h.a(obj);
    }

    public static void b(String str, Object... objArr) {
        h.a(null, str, objArr);
    }

    public static void a(Throwable th, String str, Object... objArr) {
        h.a(th, str, objArr);
    }

    public static void c(String str, Object... objArr) {
        h.d(str, objArr);
    }

    public static void d(String str, Object... objArr) {
        h.e(str, objArr);
    }

    public static void e(String str, Object... objArr) {
        h.c(str, objArr);
    }

    public static void f(String str, Object... objArr) {
        h.f(str, objArr);
    }

    public static void c(String str) {
        h.b(str);
    }

    public static void d(String str) {
        h.c(str);
    }
}
