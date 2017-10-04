package dji.device.common.view.set.b;

import dji.device.common.a.c;
import dji.pilot.usercenter.protocol.d;

public class b {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public int e = 2;
    public String f = null;
    public int g = 0;
    public Object h = null;
    public int i = Integer.MAX_VALUE;
    public int j = Integer.MAX_VALUE;
    public boolean k = false;

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof b)) {
            return equals;
        }
        b bVar = (b) obj;
        if (this.e != bVar.e) {
            return equals;
        }
        if ((this.e == 0 && c.a(this.f, bVar.f)) || (this.e == 2 && this.g == bVar.g)) {
            return true;
        }
        return equals;
    }

    public int hashCode() {
        int hashCode;
        if (this.e == 0) {
            hashCode = (this.f != null ? this.f.hashCode() : 0) + 527;
        } else if (this.e == 2) {
            hashCode = this.g + 527;
        }
        return super.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append("type[").append(String.valueOf(this.e)).append(d.H);
        stringBuilder.append("value[").append(String.valueOf(this.i)).append(d.H);
        stringBuilder.append("selected[").append(String.valueOf(this.k)).append(d.H);
        return stringBuilder.toString();
    }
}
