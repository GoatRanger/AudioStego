package dji.thirdparty.f.e.d.b;

public final class p {
    private p() {
        throw new IllegalStateException("No instances!");
    }

    public static int a(int i) {
        return 1 << (32 - Integer.numberOfLeadingZeros(i - 1));
    }

    public static boolean b(int i) {
        return ((i + -1) & i) == 0;
    }
}
