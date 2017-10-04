package dji.pilot.usercenter.e;

import dji.pilot.usercenter.protocol.d;
import java.io.Serializable;

public class b implements Serializable {
    private static final long e = 7680360453111304240L;
    public String a = null;
    public String b = null;
    public boolean c = false;
    protected long d = 0;

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof b)) {
            return equals;
        }
        b bVar = (b) obj;
        if (bVar.a == null || !bVar.a.equals(this.a)) {
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
        StringBuilder stringBuilder = new StringBuilder(48);
        stringBuilder.append("code[").append(this.a).append(d.H);
        stringBuilder.append("name[").append(this.b).append(d.H);
        return stringBuilder.toString();
    }
}
