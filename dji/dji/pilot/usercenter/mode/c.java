package dji.pilot.usercenter.mode;

import dji.pilot.usercenter.protocol.d;
import org.json.JSONObject;

public class c {
    public String a = null;
    public String b = null;
    public String c = null;
    public String d = null;
    public int e = 0;

    public static c a(JSONObject jSONObject, c cVar) {
        if (jSONObject != null) {
            if (cVar == null) {
                cVar = new c();
            }
            try {
                cVar.a = jSONObject.optString(n.ad, "");
                cVar.b = jSONObject.optString("user_name", "");
                cVar.c = jSONObject.optString("content", "");
                cVar.d = jSONObject.optString(n.aq, "");
                cVar.e = jSONObject.optInt(n.ar, 0);
            } catch (Exception e) {
            }
        }
        return cVar;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        if (this.d != null) {
            int hashCode = this.d.hashCode() + 527;
        }
        return super.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("portrait[").append(this.a).append(d.H);
        stringBuilder.append("mReplyTime[").append(this.d).append(d.H);
        return super.toString();
    }
}
