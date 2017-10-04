package dji.pilot.usercenter.mode;

import dji.pilot.usercenter.protocol.d;

public class m {
    public String a = "";
    public String b = "";
    public int c = 2;

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof m)) {
            return equals;
        }
        m mVar = (m) obj;
        if (mVar.a == null || !mVar.a.equals(this.a)) {
            return equals;
        }
        return true;
    }

    public int hashCode() {
        if (this.a != null) {
            return this.a.hashCode() + 527;
        }
        return 17;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(30);
        stringBuilder.append("name[").append(this.a).append(d.H);
        return stringBuilder.toString();
    }
}
