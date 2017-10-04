package com.flurry.sdk;

import android.text.TextUtils;
import com.alipay.sdk.app.a.c;
import com.alipay.sdk.f.d;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ha implements jh<go> {
    private static final String a = ha.class.getSimpleName();

    public /* synthetic */ Object b(InputStream inputStream) throws IOException {
        return a(inputStream);
    }

    public void a(OutputStream outputStream, go goVar) throws IOException {
        throw new IOException("Serialize not supported for response");
    }

    public go a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        String str = new String(jz.a(inputStream));
        in.a(5, a, "Proton response string: " + str);
        go goVar = new go();
        try {
            JSONObject jSONObject = new JSONObject(str);
            goVar.a = jSONObject.getLong("issued_at");
            goVar.b = jSONObject.getLong("refresh_ttl");
            goVar.c = jSONObject.getLong("expiration_ttl");
            JSONObject jSONObject2 = (JSONObject) jSONObject.get("global_settings");
            if (jSONObject2 != null) {
                gt gtVar = new gt();
                gtVar.a = b(jSONObject2.getString("log_level"));
                gtVar.b = jSONObject2.getBoolean("analytics_enabled");
                goVar.d = gtVar;
            }
            jSONObject2 = (JSONObject) jSONObject.get("callbacks");
            if (jSONObject2 != null) {
                gm gmVar = new gm();
                gmVar.a = jSONObject2.getInt("max_callbacks");
                JSONArray jSONArray = jSONObject2.getJSONArray("templates");
                if (jSONArray != null) {
                    List arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        jSONObject2 = (JSONObject) jSONArray.get(i);
                        if (jSONObject2 != null) {
                            gl glVar = new gl();
                            glVar.a = jSONObject2.getString(c.p);
                            JSONArray jSONArray2 = jSONObject2.getJSONArray("events");
                            if (jSONArray2 != null) {
                                glVar.b = ka.b(jSONArray2);
                            }
                            glVar.c = a(jSONObject2.getString(d.q));
                            glVar.d = jSONObject2.getString("uri_template");
                            glVar.e = jSONObject2.getString("body_template");
                            glVar.f = jSONObject2.getInt("max_redirects");
                            glVar.g = jSONObject2.getInt("connect_timeout");
                            glVar.h = jSONObject2.getInt("request_timeout");
                            arrayList.add(glVar);
                        }
                    }
                    gmVar.b = arrayList;
                }
                goVar.e = gmVar;
            }
            return goVar;
        } catch (Throwable e) {
            throw new IOException("Exception while deserialize:", e);
        }
    }

    private gv a(String str) {
        gv gvVar = gv.a;
        try {
            if (TextUtils.isEmpty(str)) {
                return gvVar;
            }
            return (gv) Enum.valueOf(gv.class, str);
        } catch (Exception e) {
            return gvVar;
        }
    }

    private gu b(String str) {
        gu guVar = gu.OFF;
        try {
            if (TextUtils.isEmpty(str)) {
                return guVar;
            }
            return (gu) Enum.valueOf(gu.class, str);
        } catch (Exception e) {
            return guVar;
        }
    }
}
