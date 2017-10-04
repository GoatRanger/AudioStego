package dji.pilot.usercenter.mode;

import org.json.JSONArray;
import org.json.JSONObject;

public class k extends n {
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 4;
    public static final int d = 8;
    public static final int e = 16;
    public static final int f = 32;
    public String g = "";
    public int h = 0;
    public j i = null;

    public static k a(JSONObject jSONObject, k kVar, int i) {
        if (jSONObject != null) {
            if (kVar == null) {
                kVar = new k();
            }
            if (2 == i) {
                kVar.e(jSONObject);
            } else if (4 == i) {
                kVar.b(jSONObject);
            } else if (8 == i) {
                kVar.c(jSONObject);
            } else if (16 == i) {
                kVar.f(jSONObject);
            } else if (32 == i) {
                kVar.d(jSONObject);
            } else {
                kVar.a(jSONObject);
            }
        }
        return kVar;
    }

    public void a(JSONObject jSONObject) {
        try {
            this.bo = jSONObject.optInt("status", 1);
            this.bp = jSONObject.optString(n.k, "");
            this.g = jSONObject.optString("msg", "");
            if (this.g.equals("")) {
                this.g = jSONObject.optString("message", "");
            }
        } catch (Exception e) {
        }
    }

    public void b(JSONObject jSONObject) {
        if (this.i == null) {
            this.i = new j();
        }
        try {
            if (!jSONObject.isNull("token")) {
                this.i.Q = jSONObject.optString("token", "");
            }
        } catch (Exception e) {
        }
    }

    public void c(JSONObject jSONObject) {
        if (this.i == null) {
            this.i = new j();
        }
        try {
            if (!jSONObject.isNull("avatar")) {
                this.i.l = jSONObject.optString("avatar", "");
            }
        } catch (Exception e) {
        }
    }

    public void d(JSONObject jSONObject) {
        if (this.i == null) {
            this.i = new j();
        }
        try {
            if (!jSONObject.isNull("uid")) {
                this.i.g = jSONObject.optString("uid", "");
            }
        } catch (Exception e) {
        }
    }

    public void e(JSONObject jSONObject) {
        if (this.i == null) {
            this.i = new j();
        }
        try {
            this.i.f = jSONObject.optString("id", "");
            if (!jSONObject.isNull("email")) {
                this.i.h = jSONObject.optString("email", "");
            }
            if (!jSONObject.isNull(n.aR)) {
                this.i.j = jSONObject.optString(n.aR, "");
            }
            if (!jSONObject.isNull("avatar")) {
                this.i.l = jSONObject.optString("avatar", "");
            }
            if (!jSONObject.isNull(n.aF)) {
                this.i.m = jSONObject.optString(n.aF, "");
            }
            if (!jSONObject.isNull(n.aG)) {
                String optString = jSONObject.optString(n.aG, n.ba);
                if (n.aY.equals(optString)) {
                    this.i.n = 1;
                } else if (n.aZ.equals(optString)) {
                    this.i.n = 2;
                } else {
                    this.i.n = 0;
                }
            }
            if (!jSONObject.isNull(n.aa)) {
                this.i.o = jSONObject.optString(n.aa, "");
            }
            if (!jSONObject.isNull(n.ab)) {
                this.i.p = jSONObject.optString(n.ab, "");
            }
            if (!jSONObject.isNull("name")) {
                this.i.q = jSONObject.optString("name", "");
            }
            if (!jSONObject.isNull(n.W)) {
                this.i.r = jSONObject.optString(n.W, "");
            }
            if (!jSONObject.isNull(n.ak)) {
                this.i.s = jSONObject.optString(n.ak, "");
            }
            if (!jSONObject.isNull("country")) {
                this.i.t = jSONObject.optString("country", "");
            }
            if (!jSONObject.isNull(n.aS)) {
                this.i.u = jSONObject.optString(n.aS, "");
            }
            if (!jSONObject.isNull("state")) {
                this.i.v = jSONObject.optString("state", "");
            }
            if (!jSONObject.isNull(n.B)) {
                this.i.w = jSONObject.optString(n.B, "");
            }
            if (!jSONObject.isNull(n.aI)) {
                this.i.x = jSONObject.optString(n.aI, "");
            }
            if (!jSONObject.isNull(n.L)) {
                this.i.G = jSONObject.optString(n.L, "");
            }
            this.i.I = jSONObject.optInt(n.aT, 0);
            this.i.J = jSONObject.optInt(n.aU, 0);
            this.i.K = jSONObject.optInt(n.aV, 0);
            this.i.L = jSONObject.optInt(n.aW, 0);
            if (!jSONObject.isNull(n.aJ)) {
                this.i.y = jSONObject.optString(n.aJ, "");
            }
            if (!jSONObject.isNull(n.aK)) {
                this.i.z = jSONObject.optString(n.aK, "");
            }
            if (!jSONObject.isNull(n.aL)) {
                this.i.A = jSONObject.optString(n.aL, "");
            }
            if (!jSONObject.isNull(n.aM)) {
                this.i.B = jSONObject.optString(n.aM, "");
            }
            if (!jSONObject.isNull(n.aN)) {
                this.i.C = jSONObject.optString(n.aN, "");
            }
            if (!jSONObject.isNull(n.aO)) {
                this.i.D = jSONObject.optInt(n.aO, 0);
            }
            if (!jSONObject.isNull(n.aP)) {
                this.i.E = jSONObject.optString(n.aP, "");
            }
            if (!jSONObject.isNull(n.aQ)) {
                this.i.F = jSONObject.optString(n.aQ, "");
            }
            if (!jSONObject.isNull(n.aX)) {
                this.i.H = jSONObject.optString(n.aX, "");
            }
            this.i.M = jSONObject.optLong(n.bk, 0);
            this.i.N = jSONObject.optLong(n.bl, 0);
            if (!jSONObject.isNull(n.bm)) {
                this.i.O = jSONObject.optString(n.bm, "");
            }
        } catch (Exception e) {
        }
    }

    public void f(JSONObject jSONObject) {
        if (this.i == null) {
            this.i = new j();
        }
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(n.bb);
            if (optJSONArray != null && optJSONArray.length() > 0) {
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    this.i.P.add(d.a(optJSONArray.optJSONObject(i), new d()));
                }
            }
        } catch (Exception e) {
        }
    }
}
