package dji.pilot.fpv.camera.newfn.b;

import dji.pilot.usercenter.protocol.d;

public class a {
    public String a = "";
    public int b = Integer.MAX_VALUE;
    public int c = Integer.MAX_VALUE;
    public boolean d = false;
    public Object e = null;
    public int f = 0;
    public int g = 100;

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof a)) {
            return equals;
        }
        return this.b == ((a) obj).b;
    }

    public int hashCode() {
        return this.b;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(16);
        stringBuilder.append("desc[").append(this.a).append(d.H);
        return stringBuilder.toString();
    }
}
