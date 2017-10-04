package dji.pilot.usercenter.protocol.a;

import dji.pilot.usercenter.mode.k;
import org.json.JSONObject;

public class d {
    public static Object a(String str) {
        Object kVar = new k();
        try {
            kVar = k.a(new JSONObject(str), kVar, 1);
        } catch (Exception e) {
        }
        return kVar;
    }

    public static Object b(String str) {
        Object kVar = new k();
        try {
            JSONObject jSONObject = new JSONObject(str);
            kVar = k.a(jSONObject, kVar, 1);
            if (kVar.bo == 0) {
                kVar = k.a(jSONObject, k.a(jSONObject, kVar, 4), 32);
            }
        } catch (Exception e) {
        }
        return kVar;
    }

    public static Object c(String str) {
        Object kVar = new k();
        try {
            JSONObject jSONObject = new JSONObject(str);
            kVar = k.a(jSONObject, kVar, 1);
            if (kVar.bo == 0) {
                kVar = k.a(jSONObject, kVar, 8);
            }
        } catch (Exception e) {
        }
        return kVar;
    }

    public static Object d(String str) {
        Object kVar = new k();
        try {
            JSONObject jSONObject = new JSONObject(str);
            kVar = k.a(jSONObject, kVar, 1);
            if (kVar.bo == 0) {
                kVar = k.a(jSONObject, k.a(jSONObject, kVar, 2), 16);
            }
        } catch (Exception e) {
        }
        return kVar;
    }
}
