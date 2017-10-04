package dji.gs.e;

public class b {
    public static final float a = 16.0f;
    public double b;
    public double c;
    public float d;
    public float e;

    public b(double d, double d2) {
        this(d, d2, 0.0f);
    }

    public b(double d, double d2, float f) {
        this(d, d2, f, 0.0f);
    }

    public b(double d, double d2, float f, float f2) {
        this.b = d;
        this.c = d2;
        this.d = f;
        this.e = f2;
    }

    public boolean a() {
        return Math.abs(this.b) > 1.0E-8d && Math.abs(this.c) > 1.0E-8d && Math.abs(this.b) <= 90.0d && Math.abs(this.c) <= 180.0d;
    }

    public boolean b() {
        return a((float) a);
    }

    public boolean a(float f) {
        return a(this.e, f);
    }

    public static boolean a(float f, float f2) {
        return 0.0f < f && f <= f2;
    }

    public boolean a(b bVar) {
        if (bVar != null && bVar.b == this.b && bVar.c == this.c) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (bVar.b == this.b && bVar.c == this.c) {
            return true;
        }
        return false;
    }

    public String toString() {
        return this.b + "," + this.c;
    }

    public static b a(String str) {
        String[] split = str.split(",");
        if (split.length != 2) {
            return null;
        }
        return new b(Double.valueOf(split[0]).doubleValue(), Double.valueOf(split[1]).doubleValue());
    }
}
