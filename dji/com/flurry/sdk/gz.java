package com.flurry.sdk;

import com.alibaba.sdk.android.ut.UTConstants;
import dji.pilot.usercenter.mode.n;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class gz implements jh<gn> {
    private static final String a = gz.class.getSimpleName();

    public /* synthetic */ Object b(InputStream inputStream) throws IOException {
        return a(inputStream);
    }

    public void a(OutputStream outputStream, gn gnVar) throws IOException {
        if (outputStream != null && gnVar != null) {
            DataOutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                final /* synthetic */ gz a;

                public void close() {
                }
            };
            JSONObject jSONObject = new JSONObject();
            try {
                Object obj;
                a(jSONObject, "project_key", gnVar.a);
                a(jSONObject, "bundle_id", gnVar.b);
                a(jSONObject, UTConstants.APP_VERSION, gnVar.c);
                jSONObject.put("sdk_version", gnVar.d);
                jSONObject.put("platform", gnVar.e);
                a(jSONObject, "platform_version", gnVar.f);
                jSONObject.put("limit_ad_tracking", gnVar.g);
                if (gnVar.h == null || gnVar.h.a == null) {
                    obj = null;
                } else {
                    obj = new JSONObject();
                    JSONObject jSONObject2 = new JSONObject();
                    a(jSONObject2, n.E, gnVar.h.a.a);
                    a(jSONObject2, "brand", gnVar.h.a.b);
                    a(jSONObject2, "id", gnVar.h.a.c);
                    a(jSONObject2, "device", gnVar.h.a.d);
                    a(jSONObject2, "product", gnVar.h.a.e);
                    a(jSONObject2, "version_release", gnVar.h.a.f);
                    obj.put("com.flurry.proton.generated.avro.AndroidTags", jSONObject2);
                }
                if (obj != null) {
                    jSONObject.put("device_tags", obj);
                } else {
                    jSONObject.put("device_tags", JSONObject.NULL);
                }
                JSONArray jSONArray = new JSONArray();
                for (gp gpVar : gnVar.i) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("type", gpVar.a);
                    a(jSONObject3, "id", gpVar.b);
                    jSONArray.put(jSONObject3);
                }
                jSONObject.put("device_ids", jSONArray);
                if (gnVar.j == null || gnVar.j.a == null) {
                    obj = null;
                } else {
                    obj = new JSONObject();
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("latitude", gnVar.j.a.a);
                    jSONObject4.put("longitude", gnVar.j.a.b);
                    jSONObject4.put("accuracy", (double) gnVar.j.a.c);
                    obj.put("com.flurry.proton.generated.avro.Geolocation", jSONObject4);
                }
                if (obj != null) {
                    jSONObject.put("geo", obj);
                } else {
                    jSONObject.put("geo", JSONObject.NULL);
                }
                in.a(5, a, "Proton Request String: " + jSONObject.toString());
                anonymousClass1.write(jSONObject.toString().getBytes());
                anonymousClass1.flush();
                anonymousClass1.close();
            } catch (Throwable e) {
                throw new IOException("Invalid Json", e);
            } catch (Throwable th) {
                anonymousClass1.close();
            }
        }
    }

    private void a(JSONObject jSONObject, String str, String str2) throws IOException, JSONException {
        if (jSONObject == null) {
            throw new IOException("Null Json object");
        } else if (str2 != null) {
            jSONObject.put(str, str2);
        } else {
            jSONObject.put(str, JSONObject.NULL);
        }
    }

    public gn a(InputStream inputStream) throws IOException {
        throw new IOException("Deserialize not supported for request");
    }
}
