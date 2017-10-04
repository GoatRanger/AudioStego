package dji.pilot.usercenter.mode;

import dji.midware.data.config.P3.ProductType;
import dji.pilot.active.DJIActiveController;
import org.json.JSONObject;

public class d {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 3;
    public static final int d = 11;
    public static final int e = 14;
    public String f = "";
    public int g = 0;
    public String h = "";
    public String i = "";
    public String j = "";
    public String k = "";
    public String l = "";
    public String m = "";
    public String n = "";

    public static d a(JSONObject jSONObject, d dVar) {
        if (jSONObject != null) {
            if (dVar == null) {
                dVar = new d();
            }
            try {
                dVar.f = jSONObject.optString(n.bc, "");
                dVar.g = jSONObject.optInt(n.bd, 0);
                dVar.h = jSONObject.optString(n.be, "");
                dVar.i = jSONObject.optString("email", "");
                dVar.j = jSONObject.optString("appVersion", "");
                dVar.k = jSONObject.optString(n.bg, "");
                dVar.l = jSONObject.optString(n.bh, "");
                dVar.m = jSONObject.optString(n.bi, "");
                dVar.n = jSONObject.optString(n.bj, "");
            } catch (Exception e) {
            }
        }
        return dVar;
    }

    public ProductType a() {
        return DJIActiveController.a(this.h);
    }

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        if (equals || !(obj instanceof d)) {
            return equals;
        }
        d dVar = (d) obj;
        if (dVar.f == null || !dVar.f.equals(this.f)) {
            return equals;
        }
        return true;
    }

    public int hashCode() {
        if (this.f != null) {
            return this.f.hashCode() + 527;
        }
        return 17;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("name[").append(this.k).append(dji.pilot.usercenter.protocol.d.H);
        stringBuilder.append("sn[").append(this.f).append(dji.pilot.usercenter.protocol.d.H);
        return stringBuilder.toString();
    }
}
