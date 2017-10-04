package com.facebook.share.internal;

import android.support.annotation.Nullable;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.model.SharePhoto;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class k {

    public interface a {
        JSONObject a(SharePhoto sharePhoto);
    }

    public static JSONObject a(ShareOpenGraphAction shareOpenGraphAction, a aVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : shareOpenGraphAction.c()) {
            jSONObject.put(str, a(shareOpenGraphAction.a(str), aVar));
        }
        return jSONObject;
    }

    private static JSONObject a(ShareOpenGraphObject shareOpenGraphObject, a aVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : shareOpenGraphObject.c()) {
            jSONObject.put(str, a(shareOpenGraphObject.a(str), aVar));
        }
        return jSONObject;
    }

    private static JSONArray a(List list, a aVar) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object a : list) {
            jSONArray.put(a(a, aVar));
        }
        return jSONArray;
    }

    public static Object a(@Nullable Object obj, a aVar) throws JSONException {
        if (obj == null) {
            return JSONObject.NULL;
        }
        if ((obj instanceof String) || (obj instanceof Boolean) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Long)) {
            return obj;
        }
        if (obj instanceof SharePhoto) {
            if (aVar != null) {
                return aVar.a((SharePhoto) obj);
            }
            return null;
        } else if (obj instanceof ShareOpenGraphObject) {
            return a((ShareOpenGraphObject) obj, aVar);
        } else {
            if (obj instanceof List) {
                return a((List) obj, aVar);
            }
            throw new IllegalArgumentException("Invalid object found for JSON serialization: " + obj.toString());
        }
    }

    private k() {
    }
}
