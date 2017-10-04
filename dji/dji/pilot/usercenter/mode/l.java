package dji.pilot.usercenter.mode;

import org.json.JSONObject;

public class l extends h {
    public String a = null;
    public String b = null;
    public String c = null;
    public String d = null;
    public String e = null;
    public long f = 0;
    public a g = null;

    public static l a(JSONObject jSONObject, l lVar) {
        if (jSONObject != null) {
            if (lVar == null) {
                lVar = new l();
            }
            try {
                h.a(jSONObject, lVar);
                lVar.a = jSONObject.optString(n.N, "");
                lVar.b = jSONObject.optString(n.O, "");
                lVar.c = jSONObject.optString(n.P, "");
                lVar.d = jSONObject.optString(n.Q, "");
                lVar.e = jSONObject.optString(n.R, "");
                lVar.f = jSONObject.optLong("duration", 0);
                JSONObject optJSONObject = jSONObject.optJSONObject("account");
                if (optJSONObject != null) {
                    lVar.g = a.a(optJSONObject, null);
                }
            } catch (Exception e) {
            }
        }
        return lVar;
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
