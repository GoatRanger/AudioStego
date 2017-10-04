package dji.device.common.view.set.b;

import dji.pilot.usercenter.protocol.d;

public class a {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public int f = 0;
    public int g = Integer.MAX_VALUE;
    public boolean h = false;
    public boolean i = true;
    public boolean j = true;
    public int k = 0;
    public int l = 0;
    public String m = "";
    public int n = 0;
    public int o = 0;

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof a)) {
            return equals;
        }
        return this.k == ((a) obj).k;
    }

    public int hashCode() {
        return this.k;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(20);
        stringBuilder.append("tId").append(String.valueOf(this.k)).append(d.H);
        stringBuilder.append("vId").append(String.valueOf(this.g)).append(d.H);
        return stringBuilder.toString();
    }
}
