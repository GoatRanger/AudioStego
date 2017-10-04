package dji.thirdparty.b;

import dji.thirdparty.b.a.j;

public final class h {
    private final String a;
    private final String b;

    public h(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        return (obj instanceof h) && j.a(this.a, ((h) obj).a) && j.a(this.b, ((h) obj).b);
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.b != null) {
            hashCode = this.b.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + 899) * 31;
        if (this.a != null) {
            i = this.a.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return this.a + " realm=\"" + this.b + "\"";
    }
}
