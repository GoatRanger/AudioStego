package com.alibaba.sdk.android.util;

import com.alibaba.sdk.android.model.Result;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {
    public static Integer optInteger(JSONObject jSONObject, String str) {
        return jSONObject.has(str) ? Integer.valueOf(jSONObject.optInt(str)) : null;
    }

    public static String optString(JSONObject jSONObject, String str) {
        return jSONObject.has(str) ? jSONObject.optString(str) : null;
    }

    public static Long optLong(JSONObject jSONObject, String str) {
        return jSONObject.has(str) ? Long.valueOf(jSONObject.optLong(str)) : null;
    }

    public static String toJson(Map<String, Object> map) {
        return toJsonObject(map).toString();
    }

    public static JSONObject toJsonObject(Map<String, ? extends Object> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Entry entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value != null) {
                    if (value instanceof Map) {
                        jSONObject.put((String) entry.getKey(), toJsonObject((Map) value));
                    } else if (value instanceof List) {
                        jSONObject.put((String) entry.getKey(), toJsonArray((List) value));
                    } else if (value.getClass().isArray()) {
                        jSONObject.put((String) entry.getKey(), toJsonArray((Object[]) value));
                    } else {
                        jSONObject.put((String) entry.getKey(), value);
                    }
                }
            }
            return jSONObject;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONArray toJsonArray(Object[] objArr) {
        JSONArray jSONArray = new JSONArray();
        for (Object obj : objArr) {
            if (obj instanceof Map) {
                jSONArray.put(toJsonObject((Map) obj));
            } else {
                jSONArray.put(obj);
            }
        }
        return jSONArray;
    }

    public static JSONArray toJsonArray(List<Object> list) {
        JSONArray jSONArray = new JSONArray();
        for (Object next : list) {
            if (next instanceof Map) {
                jSONArray.put(toJsonObject((Map) next));
            } else {
                jSONArray.put(next);
            }
        }
        return jSONArray;
    }

    public static Map<String, Object> toMap(JSONObject jSONObject) throws JSONException {
        Map<String, Object> hashMap = new HashMap();
        if (jSONObject == null) {
            return hashMap;
        }
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object opt = jSONObject.opt(str);
            if (opt instanceof JSONObject) {
                hashMap.put(str, toMap((JSONObject) opt));
            } else if (opt instanceof JSONArray) {
                hashMap.put(str, toList((JSONArray) opt));
            } else {
                hashMap.put(str, opt);
            }
        }
        return hashMap;
    }

    public static List<Object> toList(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return Collections.emptyList();
        }
        List<Object> arrayList = new ArrayList(jSONArray.length());
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            Object obj = jSONArray.get(i);
            if (obj instanceof JSONObject) {
                arrayList.add(toMap((JSONObject) obj));
            } else if (obj instanceof JSONArray) {
                arrayList.add(toList((JSONArray) obj));
            } else {
                arrayList.add(jSONArray.get(i));
            }
        }
        return arrayList;
    }

    public static String[] toStringArray(JSONArray jSONArray) throws JSONException {
        int i = 0;
        if (jSONArray == null) {
            return new String[0];
        }
        String[] strArr = new String[jSONArray.length()];
        int length = jSONArray.length();
        while (i < length) {
            strArr[i] = jSONArray.optString(i);
            i++;
        }
        return strArr;
    }

    public static Result<String> toStringResult(String str) {
        Result<String> result = new Result();
        try {
            JSONObject jSONObject = new JSONObject(str);
            result.code = jSONObject.optInt("code");
            result.data = optString(jSONObject, "data");
            result.message = optString(jSONObject, "message");
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJSONString(Result<String> result) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", result.code);
            jSONObject.put("message", result.message);
            jSONObject.put("data", result.data);
            return jSONObject.toString();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
