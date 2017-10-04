package dji.pilot.usercenter.mode;

import org.json.JSONObject;

public class f extends h {
    public String a = null;
    public String b = null;
    public String c = null;
    public String d = null;
    public String e = null;
    public long f = 0;

    public static f a(JSONObject jSONObject, f fVar) {
        if (jSONObject != null) {
            if (fVar == null) {
                fVar = new f();
            }
            try {
                h.a(jSONObject, fVar);
                fVar.a = jSONObject.optString(n.N, "");
                fVar.b = jSONObject.optString(n.O, "");
                fVar.c = jSONObject.optString(n.P, "");
                fVar.d = jSONObject.optString(n.Q, "");
                fVar.e = jSONObject.optString(n.R, "");
                fVar.f = jSONObject.optLong("duration", 0);
            } catch (Exception e) {
            }
        }
        return fVar;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return super.toString();
    }
}
