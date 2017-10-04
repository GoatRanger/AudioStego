package dji.pilot2.academy.model;

import dji.log.DJILogHelper;
import dji.pilot.college.b.b;
import dji.pilot.usercenter.mode.n;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AcademyHotFaqInfo {
    public long mGetTime;
    public List<a> mHotFaqInfos;

    public static class a {
        public int a;
        public int b;
        public String c;
        public String d;
        public boolean e;
        public String f;
        public String g;

        public static a a(JSONObject jSONObject) {
            a aVar = null;
            if (jSONObject != null) {
                aVar = new a();
                try {
                    aVar.a = jSONObject.optInt("id");
                    aVar.c = jSONObject.optString("title");
                    aVar.d = jSONObject.optString(b.n, "");
                    aVar.e = jSONObject.optBoolean("status");
                    aVar.f = jSONObject.optString(n.L, "");
                    aVar.g = jSONObject.optString(n.M, "");
                } catch (Exception e) {
                }
            }
            return aVar;
        }
    }

    public static List<a> parsel(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("result") != 0) {
                DJILogHelper.getInstance().LOGI("bob", "json err");
                return null;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            if (optJSONArray == null) {
                return null;
            }
            List<a> arrayList = new ArrayList();
            if (optJSONArray != null && optJSONArray.length() > 0) {
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    a a = a.a(optJSONArray.getJSONObject(i));
                    if (a != null) {
                        arrayList.add(a);
                    }
                }
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
