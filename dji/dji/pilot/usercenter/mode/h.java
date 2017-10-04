package dji.pilot.usercenter.mode;

import dji.pilot.usercenter.protocol.d;
import org.json.JSONObject;

public class h {
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;
    public String s;
    public String t;
    public int u;
    public int v;
    public boolean w;
    public String x;
    public String y;
    public String z;

    public static h a(JSONObject jSONObject, h hVar) {
        if (jSONObject != null) {
            if (hVar == null) {
                hVar = new h();
            }
            try {
                hVar.n = jSONObject.optString("id", "");
                hVar.o = jSONObject.optString(n.n, "");
                hVar.p = jSONObject.optString(n.o, "");
                hVar.q = jSONObject.optString(n.p, "");
                hVar.r = jSONObject.optString("title", "");
                hVar.s = jSONObject.optString(n.C, "");
                hVar.t = jSONObject.optString("description", "");
                hVar.u = jSONObject.optInt(n.G, 0);
                hVar.v = jSONObject.optInt(n.H, 0);
                hVar.w = jSONObject.optBoolean(n.I, false);
                hVar.x = jSONObject.optString(n.J, "");
                hVar.y = jSONObject.optString(n.L, "");
                hVar.z = jSONObject.optString(n.M, "");
            } catch (Exception e) {
            }
        }
        return hVar;
    }

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof h)) {
            return equals;
        }
        h hVar = (h) obj;
        if (this.n == null || !this.n.equals(hVar.n)) {
            return equals;
        }
        return true;
    }

    public int hashCode() {
        if (this.n != null) {
            return 17 + (this.n.hashCode() * 31);
        }
        return 17;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append("id[").append(this.n).append(d.H);
        stringBuilder.append("title[").append(this.r).append(d.H);
        stringBuilder.append("ctime[").append(this.y).append(d.H);
        stringBuilder.append("utime[").append(this.z).append(d.H);
        return stringBuilder.toString();
    }
}
