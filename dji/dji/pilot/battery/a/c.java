package dji.pilot.battery.a;

import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon.ConnStatus;

public class c {
    public static final byte a = (byte) 0;
    public static final byte b = (byte) 1;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 4;
    private static final int f = 8;
    private static final int g = 16;
    private static final int h = 32;
    private static final int i = 64;
    private static final int j = 896;
    private static final int k = 7168;
    private static final int l = 57344;
    private static final int m = 65536;
    private static final int n = 131072;
    private static final long o = 1;
    private static final long p = 2;
    private static final long q = 4;
    private static final long r = 8;
    private static final long s = 16;
    private static final long t = 32;
    private static final long u = 64;
    private static final long v = 3968;
    private static final long w = 126976;
    private static final long x = 1048576;
    private static final long y = 2097152;
    private long A = 0;
    private ConnStatus B = ConnStatus.NORMAL;
    private boolean C = false;
    private boolean D = false;
    private boolean E = false;
    private boolean F = false;
    private boolean G = false;
    private boolean H = false;
    private boolean I = false;
    private byte J = (byte) 0;
    private byte K = (byte) 0;
    private byte L = (byte) 0;
    private boolean M = false;
    private boolean N = false;
    private int z = 0;

    public boolean a() {
        return d() || e() || f() || g() || h() || i() || j() || k() != (byte) 0 || l() != (byte) 0 || o() || this.B != ConnStatus.NORMAL;
    }

    public boolean b() {
        return this.B != ConnStatus.NORMAL;
    }

    public ConnStatus c() {
        return this.B;
    }

    public boolean d() {
        return this.C;
    }

    public boolean e() {
        return this.D;
    }

    public boolean f() {
        return this.E;
    }

    public boolean g() {
        return this.F;
    }

    public boolean h() {
        return this.G;
    }

    public boolean i() {
        return this.H;
    }

    public boolean j() {
        return this.I;
    }

    public byte k() {
        return this.J;
    }

    public byte l() {
        return this.K;
    }

    public byte m() {
        return this.L;
    }

    public boolean n() {
        return this.M;
    }

    public boolean o() {
        return this.N;
    }

    public void a(int i) {
        q();
        this.z = i;
        this.J = (byte) ((i & j) >>> 7);
        this.K = (byte) ((i & k) >>> 10);
    }

    public void b(int i) {
        boolean z;
        boolean z2 = true;
        p();
        this.z = i;
        this.C = (i & 1) != 0;
        if (((i & 2) >>> 1) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.D = z;
        if (((i & 4) >>> 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.E = z;
        if (((i & 8) >>> 3) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.F = z;
        if (((i & 16) >>> 4) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.G = z;
        if (((i & 32) >>> 5) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.H = z;
        if (((i & 64) >>> 6) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.I = z;
        this.J = (byte) ((i & j) >>> 7);
        this.K = (byte) ((i & k) >>> 10);
        this.L = (byte) ((l & i) >>> 13);
        if (((65536 & i) >>> 16) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.M = z;
        if (((131072 & i) >>> 17) == 0) {
            z2 = false;
        }
        this.N = z2;
    }

    public void a(ConnStatus connStatus) {
        this.B = connStatus;
    }

    public void p() {
        this.C = false;
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = false;
        this.H = false;
        this.I = false;
        this.J = (byte) 0;
        this.K = (byte) 0;
        this.L = (byte) 0;
        this.M = false;
        this.N = false;
    }

    public void q() {
        this.J = (byte) 0;
        this.K = (byte) 0;
    }

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof c)) {
            return equals;
        }
        if (this.z == ((c) obj).z) {
            return true;
        }
        return equals;
    }

    public int hashCode() {
        return this.z;
    }

    public String toString() {
        return Integer.toBinaryString(this.z);
    }

    public void a(long j) {
        boolean z;
        boolean z2 = true;
        p();
        this.A = j;
        this.C = (1 & j) != 0;
        if ((2 & j) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.D = z;
        if ((4 & j) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.E = z;
        if ((8 & j) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.F = z;
        if ((16 & j) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.G = z;
        if ((32 & j) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.H = z;
        if ((64 & j) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.I = z;
        this.J = (byte) ((int) ((v & j) >> 7));
        this.K = (byte) ((int) ((w & j) >> 12));
        this.L = (byte) 0;
        if ((1048576 & j) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.M = z;
        if ((y & j) == 0) {
            z2 = false;
        }
        this.N = z2;
    }
}
