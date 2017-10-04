package dji.midware.media.j;

import dji.midware.media.d;

public class a {
    public static final int a = ((int) (((120.0d / ((double) d.c())) * 1000.0d) - ((double) d.o)));
    private final int b;
    private final int c;

    public a(int i, int i2) {
        this.b = a + i;
        this.c = a + i2;
    }

    public String toString() {
        return this.b + "_to_" + this.c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (this.b == aVar.b && this.c == aVar.c) {
            return true;
        }
        return false;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.b;
    }
}
