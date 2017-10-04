package dji.pilot.usercenter.mode;

import dji.pilot.usercenter.protocol.d;
import org.json.JSONObject;

public class a {
    public String a = null;
    public String b = null;
    public String c = null;
    public String d = null;
    public String e = null;
    public String f = null;
    public String g = null;
    public String h = null;
    public String i = null;
    public String j = null;
    public String k = null;
    public String l = null;
    public String m = null;
    public String n = null;
    public String o = null;
    public int p = 0;
    public int q = 0;
    public String r = null;
    public String s = null;
    public String t = null;
    public String u = null;
    public String v = null;
    public String w = null;

    public static a a(JSONObject jSONObject, a aVar) {
        if (jSONObject != null) {
            if (aVar == null) {
                aVar = new a();
            }
            try {
                aVar.a = jSONObject.optString("id", "");
                aVar.b = jSONObject.optString(n.p, "");
                aVar.c = jSONObject.optString("user_name", "");
                aVar.d = jSONObject.optString(n.V, "");
                aVar.e = jSONObject.optString(n.W, "");
                aVar.f = jSONObject.optString("avatar", "");
                aVar.g = jSONObject.optString("email", "");
                aVar.h = jSONObject.optString("password", "");
                aVar.i = jSONObject.optString(n.aa, "");
                aVar.j = jSONObject.optString(n.ab, "");
                aVar.k = jSONObject.optString(n.C, "");
                aVar.l = jSONObject.optString(n.ad, "");
                aVar.m = jSONObject.optString(n.ae, "");
                aVar.n = jSONObject.optString(n.af, "");
                aVar.o = jSONObject.optString(n.ag, "");
                aVar.p = jSONObject.optInt(n.G, 0);
                aVar.q = jSONObject.optInt(n.H, 0);
                aVar.r = jSONObject.optString(n.ah, "");
                aVar.s = jSONObject.optString(n.L, "");
                aVar.t = jSONObject.optString(n.M, "");
                aVar.u = jSONObject.optString(n.ai, "");
                aVar.v = jSONObject.optString(n.aj, "");
                aVar.w = jSONObject.optString(n.ak, "");
            } catch (Exception e) {
            }
        }
        return aVar;
    }

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof a)) {
            return equals;
        }
        a aVar = (a) obj;
        if (this.a == null || !this.a.equals(aVar.a)) {
            return equals;
        }
        return true;
    }

    public int hashCode() {
        if (this.a != null) {
            return 17 + (this.a.hashCode() * 31);
        }
        return 17;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(56);
        stringBuilder.append("id[").append(this.a).append(d.H);
        stringBuilder.append("member[").append(this.b).append(d.H);
        stringBuilder.append("username[").append(this.c).append(d.H);
        stringBuilder.append("nickname[").append(this.d).append(d.H);
        return stringBuilder.toString();
    }
}
