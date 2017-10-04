package dji.thirdparty.c;

final class t {
    static final long a = 65536;
    static s b;
    static long c;

    private t() {
    }

    static s a() {
        synchronized (t.class) {
            if (b != null) {
                s sVar = b;
                b = sVar.h;
                sVar.h = null;
                c -= 8192;
                return sVar;
            }
            return new s();
        }
    }

    static void a(s sVar) {
        if (sVar.h != null || sVar.i != null) {
            throw new IllegalArgumentException();
        } else if (!sVar.f) {
            synchronized (t.class) {
                if (c + 8192 > 65536) {
                    return;
                }
                c += 8192;
                sVar.h = b;
                sVar.e = 0;
                sVar.d = 0;
                b = sVar;
            }
        }
    }
}
