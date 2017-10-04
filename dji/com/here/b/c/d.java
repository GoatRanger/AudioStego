package com.here.b.c;

import android.util.Log;
import com.a.a.k;
import com.here.b.d.c;
import java.util.Iterator;
import org.json.JSONObject;

public class d extends k {
    public d(JSONObject jSONObject) {
        if (jSONObject != null) {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                try {
                    super.a(str, jSONObject.get(str));
                } catch (Throwable e) {
                    c.c("JSON object had an invalid value during merge. " + Log.getStackTraceString(e));
                }
            }
        }
    }
}
