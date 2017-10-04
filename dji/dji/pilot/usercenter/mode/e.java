package dji.pilot.usercenter.mode;

import dji.pilot.usercenter.protocol.d;
import org.json.JSONObject;

public class e extends h {
    public String a = null;
    public String b = null;
    public String c = null;
    public String d = null;
    public String e = null;
    public double f = 0.0d;
    public double g = 0.0d;
    public String h = null;
    public String i = null;
    public String j = null;
    public String k = null;
    public String l = null;
    public String m = null;

    public static e a(JSONObject jSONObject, e eVar) {
        if (jSONObject != null) {
            if (eVar == null) {
                eVar = new e();
            }
            try {
                h.a(jSONObject, eVar);
                JSONObject optJSONObject = jSONObject.optJSONObject(n.q);
                if (optJSONObject != null) {
                    eVar.a = optJSONObject.optString("url", "");
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("normal");
                    if (optJSONObject2 != null) {
                        eVar.b = optJSONObject2.optString("url", "");
                    }
                    optJSONObject2 = optJSONObject.optJSONObject(n.t);
                    if (optJSONObject2 != null) {
                        eVar.c = optJSONObject2.optString("url", "");
                    }
                    optJSONObject2 = optJSONObject.optJSONObject(n.u);
                    if (optJSONObject2 != null) {
                        eVar.d = optJSONObject2.optString("url", "");
                    }
                    optJSONObject = optJSONObject.optJSONObject(n.v);
                    if (optJSONObject != null) {
                        eVar.e = optJSONObject.optString("url", "");
                    }
                }
                eVar.f = jSONObject.optDouble(n.x, 0.0d);
                eVar.g = jSONObject.optDouble(n.y, 0.0d);
                eVar.h = jSONObject.optString("country", "");
                eVar.i = jSONObject.optString(n.A, "");
                eVar.j = jSONObject.optString(n.B, "");
                eVar.k = jSONObject.optString(n.D, "");
                eVar.l = jSONObject.optString(n.E, "");
                eVar.m = jSONObject.optString(n.K, "");
            } catch (Exception e) {
            }
        }
        return eVar;
    }

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof e)) {
            return equals;
        }
        e eVar = (e) obj;
        if (this.a == null || !this.a.endsWith(eVar.a)) {
            return equals;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = super.hashCode();
        if (this.a != null) {
            return hashCode + (this.a.hashCode() * 31);
        }
        return hashCode;
    }

    public String toString() {
        return super.toString() + "oUrl[" + this.a + d.H;
    }
}
