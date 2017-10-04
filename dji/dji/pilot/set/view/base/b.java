package dji.pilot.set.view.base;

import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon.ConnStatus;

public class b {
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
    private boolean A = false;
    private boolean B = false;
    private int o = 0;
    private ConnStatus p = ConnStatus.NORMAL;
    private boolean q = false;
    private boolean r = false;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private boolean w = false;
    private byte x = (byte) 0;
    private byte y = (byte) 0;
    private byte z = (byte) 0;

    public boolean a() {
        return d() || e() || f() || g() || h() || i() || j() || k() != (byte) 0 || l() != (byte) 0 || o() || this.p != ConnStatus.NORMAL;
    }

    public boolean b() {
        return this.p != ConnStatus.NORMAL;
    }

    public ConnStatus c() {
        return this.p;
    }

    public boolean d() {
        return this.q;
    }

    public boolean e() {
        return this.r;
    }

    public boolean f() {
        return this.s;
    }

    public boolean g() {
        return this.t;
    }

    public boolean h() {
        return this.u;
    }

    public boolean i() {
        return this.v;
    }

    public boolean j() {
        return this.w;
    }

    public byte k() {
        return this.x;
    }

    public byte l() {
        return this.y;
    }

    public byte m() {
        return this.z;
    }

    public boolean n() {
        return this.A;
    }

    public boolean o() {
        return this.B;
    }

    public void a(int i) {
        q();
        this.o = i;
        this.x = (byte) ((i & j) >>> 7);
        this.y = (byte) ((i & k) >>> 10);
    }

    public void b(int i) {
        boolean z;
        boolean z2 = true;
        p();
        this.o = i;
        this.q = (i & 1) != 0;
        if (((i & 2) >>> 1) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.r = z;
        if (((i & 4) >>> 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.s = z;
        if (((i & 8) >>> 3) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.t = z;
        if (((i & 16) >>> 4) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.u = z;
        if (((i & 32) >>> 5) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.v = z;
        if (((i & 64) >>> 6) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.w = z;
        this.x = (byte) ((i & j) >>> 7);
        this.y = (byte) ((i & k) >>> 10);
        this.z = (byte) ((l & i) >>> 13);
        if (((65536 & i) >>> 16) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.A = z;
        if (((131072 & i) >>> 17) == 0) {
            z2 = false;
        }
        this.B = z2;
    }

    public void a(ConnStatus connStatus) {
        this.p = connStatus;
    }

    public void p() {
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = false;
        this.v = false;
        this.w = false;
        this.x = (byte) 0;
        this.y = (byte) 0;
        this.z = (byte) 0;
        this.A = false;
        this.B = false;
    }

    public void q() {
        this.x = (byte) 0;
        this.y = (byte) 0;
    }

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof b)) {
            return equals;
        }
        if (this.o == ((b) obj).o) {
            return true;
        }
        return equals;
    }

    public int hashCode() {
        return this.o;
    }

    public String toString() {
        return Integer.toBinaryString(this.o);
    }
}
