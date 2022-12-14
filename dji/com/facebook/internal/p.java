package com.facebook.internal;

import com.facebook.k;
import org.json.JSONArray;
import org.json.JSONObject;

public class p {
    private static final String[] a = new String[]{"yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd"};

    public static JSONObject a(String str) {
        JSONObject jSONObject = new JSONObject();
        if (str != null) {
            try {
                jSONObject.put("type", str);
            } catch (Throwable e) {
                throw new k("An error occurred while setting up the open graph action", e);
            }
        }
        return jSONObject;
    }

    public static JSONObject b(String str) {
        return a(str, null, null, null, null, null, null);
    }

    public static JSONObject a(String str, String str2, String str3, String str4, String str5, JSONObject jSONObject, String str6) {
        JSONObject jSONObject2 = new JSONObject();
        if (str != null) {
            try {
                jSONObject2.put("type", str);
            } catch (Throwable e) {
                throw new k("An error occurred while setting up the graph object", e);
            }
        }
        jSONObject2.put("title", str2);
        if (str3 != null) {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("url", str3);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject3);
            jSONObject2.put("image", jSONArray);
        }
        jSONObject2.put("url", str4);
        jSONObject2.put("description", str5);
        jSONObject2.put(ab.ad, true);
        if (jSONObject != null) {
            jSONObject2.put("data", jSONObject);
        }
        if (str6 != null) {
            jSONObject2.put("id", str6);
        }
        return jSONObject2;
    }

    public static boolean a(JSONObject jSONObject) {
        return jSONObject != null ? jSONObject.optBoolean(ab.ad) : false;
    }
}
