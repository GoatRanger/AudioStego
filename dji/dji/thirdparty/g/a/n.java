package dji.thirdparty.g.a;

public abstract class n extends Number {
    private static final double a = 1.0E-8d;

    private static class a {
        public final m a;
        public final double b;

        private a(m mVar, double d) {
            this.a = mVar;
            this.b = d;
        }

        public static final a a(m mVar, double d) {
            return new a(mVar, Math.abs(mVar.doubleValue() - d));
        }

        public String toString() {
            return this.a.toString();
        }
    }

    public static final m a(double d) {
        if (d >= 2.147483647E9d) {
            return new m(Integer.MAX_VALUE, 1);
        }
        if (d <= -2.147483647E9d) {
            return new m(-2147483647, 1);
        }
        int i;
        if (d < 0.0d) {
            d = Math.abs(d);
            i = 1;
        } else {
            i = 0;
        }
        if (d == 0.0d) {
            return new m(0, 1);
        }
        m mVar;
        m mVar2;
        a aVar;
        int i2;
        if (d >= 1.0d) {
            i2 = (int) d;
            if (((double) i2) < d) {
                mVar = new m(i2, 1);
                mVar2 = new m(i2 + 1, 1);
            } else {
                mVar = new m(i2 - 1, 1);
                mVar2 = new m(i2, 1);
            }
        } else {
            i2 = (int) (1.0d / d);
            if (1.0d / ((double) i2) < d) {
                mVar = new m(1, i2);
                mVar2 = new m(1, i2 - 1);
            } else {
                mVar = new m(1, i2 + 1);
                mVar2 = new m(1, i2);
            }
        }
        a a = a.a(mVar, d);
        a a2 = a.a(mVar2, d);
        if (a.b < a2.b) {
            aVar = a;
        } else {
            aVar = a2;
        }
        a aVar2 = a;
        a = aVar;
        int i3 = 0;
        while (a.b > a && i3 < 100) {
            m a3 = m.a(((long) aVar2.a.a) + ((long) a2.a.a), ((long) aVar2.a.b) + ((long) a2.a.b));
            a a4 = a.a(a3, d);
            if (d >= a3.doubleValue()) {
                if (aVar2.b <= a4.b) {
                    break;
                }
                aVar2 = a4;
            } else if (a2.b <= a4.b) {
                break;
            } else {
                a2 = a4;
            }
            if (a4.b < a.b) {
                a = a4;
            }
            i3++;
        }
        return i != 0 ? a.a.a() : a.a;
    }
}
