package dji.pilot.usercenter.protocol.a;

import dji.pilot.usercenter.mode.c;
import dji.pilot.usercenter.mode.n;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class e {
    public static Object a(String str) {
        n nVar = new n();
        try {
            JSONObject jSONObject = new JSONObject(str);
            nVar.bo = jSONObject.optInt("status", 1);
            nVar.bp = jSONObject.optString(n.k, "");
            if (nVar.bo == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray(n.l);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int length = optJSONArray.length();
                    List arrayList = new ArrayList();
                    for (int i = 0; i < length; i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        c cVar = new c();
                        c.a(optJSONObject, cVar);
                        arrayList.add(cVar);
                    }
                    nVar.bq = arrayList;
                }
            }
        } catch (Exception e) {
        }
        return nVar;
    }

    public static Object b(String str) {
        n nVar = new n();
        try {
            JSONObject jSONObject = new JSONObject(str);
            nVar.bo = jSONObject.optInt("status", 1);
            nVar.bp = jSONObject.optString(n.k, "");
        } catch (Exception e) {
        }
        return nVar;
    }

    public static Object c(String str) {
        n nVar = new n();
        try {
            JSONObject jSONObject = new JSONObject(str);
            nVar.bo = jSONObject.optInt("status", 1);
            nVar.bp = jSONObject.optString(n.k, "");
        } catch (Exception e) {
        }
        return nVar;
    }

    public static Object d(String str) {
        n nVar = new n();
        try {
            JSONObject jSONObject = new JSONObject(str);
            nVar.bo = jSONObject.optInt("status", 1);
            nVar.bp = jSONObject.optString(n.k, "");
        } catch (Exception e) {
        }
        return nVar;
    }
}
