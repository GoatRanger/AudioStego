package dji.thirdparty.b;

import com.here.posclient.UpdateOptions;
import dji.thirdparty.b.a.b.c;
import java.util.concurrent.TimeUnit;

public final class d {
    public static final d a = new a().a().e();
    public static final d b = new a().c().b(Integer.MAX_VALUE, TimeUnit.SECONDS).e();
    String c;
    private final boolean d;
    private final boolean e;
    private final int f;
    private final int g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final int k;
    private final int l;
    private final boolean m;
    private final boolean n;

    public static final class a {
        boolean a;
        boolean b;
        int c = -1;
        int d = -1;
        int e = -1;
        boolean f;
        boolean g;

        public a a() {
            this.a = true;
            return this;
        }

        public a b() {
            this.b = true;
            return this;
        }

        public a a(int i, TimeUnit timeUnit) {
            if (i < 0) {
                throw new IllegalArgumentException("maxAge < 0: " + i);
            }
            long toSeconds = timeUnit.toSeconds((long) i);
            this.c = toSeconds > UpdateOptions.SOURCE_ANY ? Integer.MAX_VALUE : (int) toSeconds;
            return this;
        }

        public a b(int i, TimeUnit timeUnit) {
            if (i < 0) {
                throw new IllegalArgumentException("maxStale < 0: " + i);
            }
            long toSeconds = timeUnit.toSeconds((long) i);
            this.d = toSeconds > UpdateOptions.SOURCE_ANY ? Integer.MAX_VALUE : (int) toSeconds;
            return this;
        }

        public a c(int i, TimeUnit timeUnit) {
            if (i < 0) {
                throw new IllegalArgumentException("minFresh < 0: " + i);
            }
            long toSeconds = timeUnit.toSeconds((long) i);
            this.e = toSeconds > UpdateOptions.SOURCE_ANY ? Integer.MAX_VALUE : (int) toSeconds;
            return this;
        }

        public a c() {
            this.f = true;
            return this;
        }

        public a d() {
            this.g = true;
            return this;
        }

        public d e() {
            return new d();
        }
    }

    private d(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, String str) {
        this.d = z;
        this.e = z2;
        this.f = i;
        this.g = i2;
        this.h = z3;
        this.i = z4;
        this.j = z5;
        this.k = i3;
        this.l = i4;
        this.m = z6;
        this.n = z7;
        this.c = str;
    }

    private d(a aVar) {
        this.d = aVar.a;
        this.e = aVar.b;
        this.f = aVar.c;
        this.g = -1;
        this.h = false;
        this.i = false;
        this.j = false;
        this.k = aVar.d;
        this.l = aVar.e;
        this.m = aVar.f;
        this.n = aVar.g;
    }

    public boolean a() {
        return this.d;
    }

    public boolean b() {
        return this.e;
    }

    public int c() {
        return this.f;
    }

    public int d() {
        return this.g;
    }

    public boolean e() {
        return this.h;
    }

    public boolean f() {
        return this.i;
    }

    public boolean g() {
        return this.j;
    }

    public int h() {
        return this.k;
    }

    public int i() {
        return this.l;
    }

    public boolean j() {
        return this.m;
    }

    public boolean k() {
        return this.n;
    }

    public static d a(t tVar) {
        String b;
        boolean z = false;
        int i = -1;
        int i2 = -1;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        int i3 = -1;
        int i4 = -1;
        boolean z5 = false;
        boolean z6 = false;
        Object obj = 1;
        int a = tVar.a();
        int i5 = 0;
        String str = null;
        boolean z7 = false;
        while (i5 < a) {
            boolean z8;
            String a2 = tVar.a(i5);
            b = tVar.b(i5);
            if (a2.equalsIgnoreCase("Cache-Control")) {
                if (str != null) {
                    obj = null;
                } else {
                    str = b;
                }
            } else if (a2.equalsIgnoreCase("Pragma")) {
                obj = null;
            } else {
                z8 = z7;
                i5++;
                z7 = z8;
            }
            z8 = z7;
            int i6 = 0;
            while (i6 < b.length()) {
                String str2;
                int a3 = c.a(b, i6, "=,;");
                String trim = b.substring(i6, a3).trim();
                if (a3 == b.length() || b.charAt(a3) == ',' || b.charAt(a3) == ';') {
                    i6 = a3 + 1;
                    str2 = null;
                } else {
                    i6 = c.a(b, a3 + 1);
                    String trim2;
                    if (i6 >= b.length() || b.charAt(i6) != '\"') {
                        a3 = c.a(b, i6, ",;");
                        trim2 = b.substring(i6, a3).trim();
                        i6 = a3;
                        str2 = trim2;
                    } else {
                        i6++;
                        a3 = c.a(b, i6, "\"");
                        trim2 = b.substring(i6, a3);
                        i6 = a3 + 1;
                        str2 = trim2;
                    }
                }
                if ("no-cache".equalsIgnoreCase(trim)) {
                    z8 = true;
                } else if ("no-store".equalsIgnoreCase(trim)) {
                    z = true;
                } else if ("max-age".equalsIgnoreCase(trim)) {
                    i = c.b(str2, -1);
                } else if ("s-maxage".equalsIgnoreCase(trim)) {
                    i2 = c.b(str2, -1);
                } else if (dji.pilot.f.a.a.B.equalsIgnoreCase(trim)) {
                    z2 = true;
                } else if ("public".equalsIgnoreCase(trim)) {
                    z3 = true;
                } else if ("must-revalidate".equalsIgnoreCase(trim)) {
                    z4 = true;
                } else if ("max-stale".equalsIgnoreCase(trim)) {
                    i3 = c.b(str2, Integer.MAX_VALUE);
                } else if ("min-fresh".equalsIgnoreCase(trim)) {
                    i4 = c.b(str2, -1);
                } else if ("only-if-cached".equalsIgnoreCase(trim)) {
                    z5 = true;
                } else if ("no-transform".equalsIgnoreCase(trim)) {
                    z6 = true;
                }
            }
            i5++;
            z7 = z8;
        }
        if (obj == null) {
            b = null;
        } else {
            b = str;
        }
        return new d(z7, z, i, i2, z2, z3, z4, i3, i4, z5, z6, b);
    }

    public String toString() {
        String str = this.c;
        if (str != null) {
            return str;
        }
        str = l();
        this.c = str;
        return str;
    }

    private String l() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.d) {
            stringBuilder.append("no-cache, ");
        }
        if (this.e) {
            stringBuilder.append("no-store, ");
        }
        if (this.f != -1) {
            stringBuilder.append("max-age=").append(this.f).append(", ");
        }
        if (this.g != -1) {
            stringBuilder.append("s-maxage=").append(this.g).append(", ");
        }
        if (this.h) {
            stringBuilder.append("private, ");
        }
        if (this.i) {
            stringBuilder.append("public, ");
        }
        if (this.j) {
            stringBuilder.append("must-revalidate, ");
        }
        if (this.k != -1) {
            stringBuilder.append("max-stale=").append(this.k).append(", ");
        }
        if (this.l != -1) {
            stringBuilder.append("min-fresh=").append(this.l).append(", ");
        }
        if (this.m) {
            stringBuilder.append("only-if-cached, ");
        }
        if (this.n) {
            stringBuilder.append("no-transform, ");
        }
        if (stringBuilder.length() == 0) {
            return "";
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        return stringBuilder.toString();
    }
}
