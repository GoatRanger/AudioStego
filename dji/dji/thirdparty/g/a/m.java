package dji.thirdparty.g.a;

import com.here.posclient.UpdateOptions;
import dji.pilot.usercenter.protocol.d;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class m extends Number {
    private static final long c = -1;
    private static final NumberFormat d = DecimalFormat.getInstance();
    public final int a;
    public final int b;

    public m(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public static final m a(long j, long j2) {
        if (j > UpdateOptions.SOURCE_ANY || j < -2147483648L || j2 > UpdateOptions.SOURCE_ANY || j2 < -2147483648L) {
            while (true) {
                if ((j > UpdateOptions.SOURCE_ANY || j < -2147483648L || j2 > UpdateOptions.SOURCE_ANY || j2 < -2147483648L) && Math.abs(j) > 1 && Math.abs(j2) > 1) {
                    j >>= 1;
                    j2 >>= 1;
                }
            }
            if (j2 == 0) {
                throw new NumberFormatException("Invalid value, numerator: " + j + ", divisor: " + j2);
            }
        }
        long b = b(j, j2);
        return new m((int) (j / b), (int) (j2 / b));
    }

    private static long b(long j, long j2) {
        return j2 == 0 ? j : b(j2, j % j2);
    }

    public m a() {
        return new m(-this.a, this.b);
    }

    public double doubleValue() {
        return ((double) this.a) / ((double) this.b);
    }

    public float floatValue() {
        return ((float) this.a) / ((float) this.b);
    }

    public int intValue() {
        return this.a / this.b;
    }

    public long longValue() {
        return ((long) this.a) / ((long) this.b);
    }

    public boolean b() {
        return this.b != 0;
    }

    public String toString() {
        if (this.b == 0) {
            return "Invalid rational (" + this.a + d.t + this.b + ")";
        }
        if (this.a % this.b == 0) {
            return d.format((long) (this.a / this.b));
        }
        return this.a + d.t + this.b + " (" + d.format(((double) this.a) / ((double) this.b)) + ")";
    }

    public String c() {
        if (this.a % this.b == 0) {
            return "" + (this.a / this.b);
        }
        NumberFormat instance = DecimalFormat.getInstance();
        instance.setMaximumFractionDigits(3);
        return instance.format(((double) this.a) / ((double) this.b));
    }
}
