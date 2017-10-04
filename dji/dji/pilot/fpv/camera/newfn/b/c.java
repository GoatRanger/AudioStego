package dji.pilot.fpv.camera.newfn.b;

import dji.pilot.usercenter.protocol.d;

public class c {
    public int a = Integer.MAX_VALUE;
    public int b = 0;
    public int c = 0;
    public String d = "";
    public int e = 0;
    public int f = 0;
    public boolean g = true;
    public boolean h = true;

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof c)) {
            return equals;
        }
        return this.b == ((c) obj).b;
    }

    public int hashCode() {
        return this.b;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(20);
        stringBuilder.append("tId").append(String.valueOf(this.b)).append(d.H);
        stringBuilder.append("vId").append(String.valueOf(this.a)).append(d.H);
        return stringBuilder.toString();
    }
}
