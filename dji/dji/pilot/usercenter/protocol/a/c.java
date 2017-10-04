package dji.pilot.usercenter.protocol.a;

import dji.pilot.usercenter.mode.e;
import dji.pilot.usercenter.mode.f;
import dji.pilot.usercenter.mode.h;
import dji.pilot.usercenter.mode.i;
import dji.pilot.usercenter.mode.l;
import dji.pilot.usercenter.mode.n;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class c {
    public static Object a(String str) {
        i iVar = new i();
        try {
            JSONObject jSONObject = new JSONObject(str);
            iVar.bo = jSONObject.optInt("status", 1);
            iVar.bp = jSONObject.optString(n.k, "");
            iVar.a = jSONObject.optInt(n.al, 0);
            iVar.b = jSONObject.optInt(n.am, 0);
            iVar.c = jSONObject.optInt(n.an, 0);
            iVar.d = jSONObject.optInt(n.ao, 0);
            if (iVar.bo == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray(n.l);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int length = optJSONArray.length();
                    List arrayList = new ArrayList(length);
                    for (int i = 0; i < length; i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        e eVar = new e();
                        e.a(optJSONObject, eVar);
                        arrayList.add(eVar);
                    }
                    iVar.bq = arrayList;
                }
            }
        } catch (Exception e) {
        }
        return iVar;
    }

    public static Object b(String str) {
        i iVar = new i();
        try {
            JSONObject jSONObject = new JSONObject(str);
            iVar.bo = jSONObject.optInt("status", 1);
            iVar.bp = jSONObject.optString(n.k, "");
            iVar.a = jSONObject.optInt(n.al, 0);
            iVar.b = jSONObject.optInt(n.am, 0);
            iVar.c = jSONObject.optInt(n.an, 0);
            iVar.d = jSONObject.optInt(n.ao, 0);
            if (iVar.bo == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray(n.l);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int length = optJSONArray.length();
                    List arrayList = new ArrayList(length);
                    for (int i = 0; i < length; i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        h lVar = new l();
                        h.a(optJSONObject, lVar);
                        arrayList.add(lVar);
                    }
                    iVar.bq = arrayList;
                }
            }
        } catch (Exception e) {
        }
        return iVar;
    }

    public static Object c(String str) {
        i iVar = new i();
        try {
            JSONObject jSONObject = new JSONObject(str);
            iVar.bo = jSONObject.optInt("status", 1);
            iVar.bp = jSONObject.optString(n.k, "");
            iVar.a = jSONObject.optInt(n.al, 0);
            iVar.b = jSONObject.optInt(n.am, 0);
            iVar.c = jSONObject.optInt(n.an, 0);
            iVar.d = jSONObject.optInt(n.ao, 0);
            if (iVar.bo == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray(n.l);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int length = optJSONArray.length();
                    List arrayList = new ArrayList(length);
                    for (int i = 0; i < length; i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        e eVar = new e();
                        e.a(optJSONObject, eVar);
                        arrayList.add(eVar);
                    }
                    iVar.bq = arrayList;
                }
            }
        } catch (Exception e) {
        }
        return iVar;
    }

    public static Object d(String str) {
        i iVar = new i();
        try {
            JSONObject jSONObject = new JSONObject(str);
            iVar.bo = jSONObject.optInt("status", 1);
            iVar.bp = jSONObject.optString(n.k, "");
            iVar.a = jSONObject.optInt(n.al, 0);
            iVar.b = jSONObject.optInt(n.am, 0);
            iVar.c = jSONObject.optInt(n.an, 0);
            iVar.d = jSONObject.optInt(n.ao, 0);
            if (iVar.bo == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray(n.l);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int length = optJSONArray.length();
                    List arrayList = new ArrayList(length);
                    for (int i = 0; i < length; i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        f fVar = new f();
                        f.a(optJSONObject, fVar);
                        arrayList.add(fVar);
                    }
                    iVar.bq = arrayList;
                }
            }
        } catch (Exception e) {
        }
        return iVar;
    }

    public static Object e(String str) {
        i iVar = new i();
        try {
            JSONObject jSONObject = new JSONObject(str);
            iVar.bo = jSONObject.optInt("status", 1);
            iVar.bp = jSONObject.optString(n.k, "");
            iVar.a = jSONObject.optInt(n.al, 0);
            iVar.b = jSONObject.optInt(n.am, 0);
            iVar.c = jSONObject.optInt(n.an, 0);
            iVar.d = jSONObject.optInt(n.ao, 0);
            if (iVar.bo == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray(n.l);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int length = optJSONArray.length();
                    List arrayList = new ArrayList(length);
                    for (int i = 0; i < length; i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        f fVar = new f();
                        f.a(optJSONObject, fVar);
                        arrayList.add(fVar);
                    }
                    iVar.bq = arrayList;
                }
            }
        } catch (Exception e) {
        }
        return iVar;
    }

    public static Object f(String str) {
        i iVar = new i();
        try {
            JSONObject jSONObject = new JSONObject(str);
            iVar.bo = jSONObject.optInt("status", 1);
            iVar.bp = jSONObject.optString(n.k, "");
            iVar.a = jSONObject.optInt(n.al, 0);
            iVar.b = jSONObject.optInt(n.am, 0);
            iVar.c = jSONObject.optInt(n.an, 0);
            iVar.d = jSONObject.optInt(n.ao, 0);
            if (iVar.bo == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray(n.l);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int length = optJSONArray.length();
                    List arrayList = new ArrayList(length);
                    for (int i = 0; i < length; i++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                        f fVar = new f();
                        f.a(optJSONObject, fVar);
                        arrayList.add(fVar);
                    }
                    iVar.bq = arrayList;
                }
            }
        } catch (Exception e) {
        }
        return iVar;
    }
}
