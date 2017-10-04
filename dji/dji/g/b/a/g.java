package dji.g.b.a;

public class g {
    public static long a(int i, long j) {
        if (j > 1099511627775L) {
            j = 1099511627775L;
        }
        return (((long) i) << 40) | j;
    }

    public static int a(long j) {
        return (int) ((280375465082880L & j) >> 40);
    }

    public static long b(long j) {
        return 1099511627775L & j;
    }
}
