package com.ut.mini.d;

import org.json.JSONException;
import org.json.JSONObject;

public class b {
    public static boolean a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("success")) {
                String string = jSONObject.getString("success");
                if (!n.a(string) && string.equals("success")) {
                    return true;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
