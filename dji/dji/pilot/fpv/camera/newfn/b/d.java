package dji.pilot.fpv.camera.newfn.b;

public class d {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public int e = 1;
    public String f = null;
    public int g = 0;
    public int h = 0;
    public Object i = null;
    public int j = Integer.MAX_VALUE;
    public boolean k = false;

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof d)) {
            return equals;
        }
        d dVar = (d) obj;
        if (this.e != dVar.e) {
            return equals;
        }
        if ((this.e == 0 && dji.pilot.publics.e.d.a(this.f, dVar.f)) || (this.e == 1 && this.g == dVar.g)) {
            return true;
        }
        return equals;
    }

    public int hashCode() {
        int hashCode;
        if (this.e == 0) {
            hashCode = (this.f != null ? this.f.hashCode() : 0) + 527;
        } else if (this.e == 1) {
            hashCode = this.g + 527;
        }
        return super.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append("type[").append(String.valueOf(this.e)).append(dji.pilot.usercenter.protocol.d.H);
        stringBuilder.append("value[").append(String.valueOf(this.j)).append(dji.pilot.usercenter.protocol.d.H);
        stringBuilder.append("selected[").append(String.valueOf(this.k)).append(dji.pilot.usercenter.protocol.d.H);
        return stringBuilder.toString();
    }
}
