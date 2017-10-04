package dji.thirdparty.f;

@dji.thirdparty.f.b.b
public final class a {
    public static final d a = c.a;
    public static final d b = c.a;
    public static final d c = b.a;
    public static final d d = a.a;

    public interface d {
        boolean a() throws dji.thirdparty.f.c.c;
    }

    static class a implements d {
        static final a a = new a();

        private a() {
        }

        public boolean a() {
            return false;
        }
    }

    static class b implements d {
        static final b a = new b();

        private b() {
        }

        public boolean a() {
            return true;
        }
    }

    static class c implements d {
        static final c a = new c();

        private c() {
        }

        public boolean a() throws dji.thirdparty.f.c.c {
            throw new dji.thirdparty.f.c.c("Overflowed buffer");
        }
    }
}
