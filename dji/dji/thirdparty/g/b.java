package dji.thirdparty.g;

import com.alipay.sdk.j.i;

public class b {
    public static final b d = new b("UNKNOWN", false);
    public static final b e = new b("PNG");
    public static final b f = new b("GIF");
    public static final b g = new b("ICO");
    public static final b h = new b("TIFF");
    public static final b i = new b(c.k);
    public static final b j = new b("BMP");
    public static final b k = new b("PSD");
    public static final b l = new b("PBM");
    public static final b m = new b("PGM");
    public static final b n = new b("PPM");
    public static final b o = new b("PNM");
    public static final b p = new b("TGA");
    public static final b q = new b("JBig2");
    public final String a;
    public final String b;
    public final boolean c;

    private b(String str, boolean z) {
        this.a = str;
        this.b = str;
        this.c = z;
    }

    private b(String str) {
        this.a = str;
        this.b = str;
        this.c = true;
    }

    public boolean equals(Object obj) {
        if (obj instanceof b) {
            return ((b) obj).a.equals(this.a);
        }
        return false;
    }

    public String toString() {
        return "{" + this.a + ": " + this.b + i.d;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public static final b[] a() {
        return new b[]{d, e, f, h, i, j, k, l, m, n, o, p, q};
    }
}
