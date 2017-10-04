package com.here.a.a.a.a;

public class m {
    public final double a;
    public final double b;
    public final ad<Double> c;

    public m(double d, double d2) {
        this(d, d2, null);
    }

    public m(double d, double d2, Double d3) {
        this.a = d;
        this.b = d2;
        this.c = ad.b(d3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        m mVar = (m) obj;
        if (Double.compare(mVar.a, this.a) == 0 && Double.compare(mVar.b, this.b) == 0 && this.c.equals(mVar.c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.a);
        int i = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        long doubleToLongBits2 = Double.doubleToLongBits(this.b);
        return (((i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + this.c.hashCode();
    }
}
