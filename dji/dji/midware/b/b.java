package dji.midware.b;

import dji.midware.b.a.c;

public class b implements Comparable {
    private static final String g = "BLEObject";
    public String a;
    public String b;
    public int c;
    public boolean d;
    public boolean e;
    public c f;

    public boolean equals(Object obj) {
        if (obj instanceof String) {
            return this.a.equals(obj);
        }
        if (obj instanceof b) {
            return this.a.equals(((b) obj).a);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public int compareTo(Object obj) {
        if (!(obj instanceof b)) {
            return 0;
        }
        return Integer.valueOf(((b) obj).c).compareTo(Integer.valueOf(this.c));
    }
}
