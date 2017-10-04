package dji.pilot.fpv.camera.newfn.b;

import dji.pilot.usercenter.protocol.d;
import java.util.ArrayList;

public class b {
    public static final int a = 1;
    public static final int b = 2;
    public int c = 0;
    public String d = "";
    public String e = "";
    public int f = Integer.MAX_VALUE;
    public int g = Integer.MAX_VALUE;
    public boolean h = false;
    public Object i = null;
    public int j = 1;
    public final ArrayList<a> k = new ArrayList();

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof b)) {
            return equals;
        }
        return this.c == ((b) obj).c;
    }

    public int hashCode() {
        return this.c;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(24);
        stringBuilder.append("group[").append(this.d).append(d.H);
        stringBuilder.append("child[").append(String.valueOf(this.k.size())).append(d.H);
        return stringBuilder.toString();
    }
}
