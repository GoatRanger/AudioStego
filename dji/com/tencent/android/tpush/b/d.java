package com.tencent.android.tpush.b;

import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.p;
import org.json.JSONObject;

public class d {
    public int a = 1;
    public String b = "";
    public e c = new e();
    public String d = "";
    public String e = "";
    public String f = "";
    public int g = 0;
    public String h = "";
    public String i = "";
    public String j = "";

    private void a(String str) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject(str);
        if (!jSONObject2.isNull("action_type")) {
            this.a = jSONObject2.getInt("action_type");
        }
        if (!jSONObject2.isNull(Constants.FLAG_ACTIVITY_NAME)) {
            this.b = jSONObject2.getString(Constants.FLAG_ACTIVITY_NAME);
        }
        if (!jSONObject2.isNull("aty_attr")) {
            String optString = jSONObject2.optString("aty_attr");
            if (!p.a(optString)) {
                try {
                    JSONObject jSONObject3 = new JSONObject(optString);
                    this.c.a = jSONObject3.optInt("if");
                    this.c.b = jSONObject3.optInt("pf");
                } catch (Throwable e) {
                    a.c(Constants.LogTag, "decode activityAttribute error", e);
                }
            }
        }
        if (!jSONObject2.isNull("intent")) {
            this.d = jSONObject2.getString("intent");
        }
        if (!jSONObject2.isNull("browser")) {
            this.e = jSONObject2.getString("browser");
            jSONObject = new JSONObject(this.e);
            if (!jSONObject.isNull("url")) {
                this.f = jSONObject.getString("url");
            }
            if (!jSONObject.isNull("confirm")) {
                this.g = jSONObject.getInt("confirm");
            }
        }
        if (!jSONObject2.isNull("package_name")) {
            this.i = jSONObject2.getString("package_name");
            jSONObject = new JSONObject(this.i);
            if (!jSONObject.isNull(Constants.FLAG_PACKAGE_DOWNLOAD_URL)) {
                this.j = jSONObject.getString(Constants.FLAG_PACKAGE_DOWNLOAD_URL);
            }
            if (!jSONObject.isNull(Constants.FLAG_PACKAGE_NAME)) {
                this.h = jSONObject.getString(Constants.FLAG_PACKAGE_NAME);
            }
            if (!jSONObject.isNull("confirm")) {
                this.g = jSONObject.getInt("confirm");
            }
        }
    }
}
