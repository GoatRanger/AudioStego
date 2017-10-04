package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.android.tpush.common.Constants;
import dji.pilot.usercenter.mode.n;
import dji.pilot.usercenter.protocol.d;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class dp {

    @Deprecated
    public static class a {
        public JSONObject a;
        public JSONObject b;
        public JSONObject c;
        public JSONObject d;
        @Deprecated
        public JSONObject e;
        public JSONObject f;
        public a g;
        public c h;
        public b i;

        public static class a {
            public boolean a;
            public boolean b;
        }

        public static class b {
            public String a;
            public String b;
        }

        public static class c {
            public String a;
            public String b;
            public String c;
        }
    }

    static class b extends fw {
        private Context a;
        private dv b;
        private String c = "";

        b(Context context, dv dvVar, String str) {
            this.a = context;
            this.b = dvVar;
            this.c = str;
        }

        public Map<String, String> c() {
            Map<String, String> hashMap = new HashMap();
            hashMap.put("User-Agent", this.b.c());
            hashMap.put("platinfo", String.format("platform=Android&sdkversion=%s&product=%s", new Object[]{this.b.b(), this.b.a()}));
            hashMap.put("logversion", "2.0");
            return hashMap;
        }

        public Map<String, String> b() {
            Object q = dq.q(this.a);
            if (!TextUtils.isEmpty(q)) {
                q = ds.b(new StringBuilder(q).reverse().toString());
            }
            Map hashMap = new HashMap();
            hashMap.put(d.M, dl.f(this.a));
            hashMap.put("opertype", this.c);
            hashMap.put("plattype", "android");
            hashMap.put("product", this.b.a());
            hashMap.put("version", this.b.b());
            hashMap.put("output", "json");
            hashMap.put("androidversion", VERSION.SDK_INT + "");
            hashMap.put(Constants.FLAG_DEVICE_ID, q);
            hashMap.put("abitype", Build.CPU_ABI);
            hashMap.put("ext", this.b.d());
            String a = dn.a();
            String a2 = dn.a(this.a, a, dx.b(hashMap));
            hashMap.put("ts", a);
            hashMap.put("scode", a2);
            return hashMap;
        }

        public String a() {
            return "https://restapi.amap.com/v3/fastconnect";
        }
    }

    @Deprecated
    public static a a(byte[] bArr) {
        boolean z = false;
        a aVar = new a();
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    JSONObject jSONObject = new JSONObject(dx.a(bArr));
                    if ("1".equals(a(jSONObject, "status")) && jSONObject.has("result")) {
                        jSONObject = jSONObject.getJSONObject("result");
                        if (jSONObject != null) {
                            boolean b;
                            JSONObject jSONObject2;
                            if (dx.a(jSONObject, "exception")) {
                                b = b(jSONObject.getJSONObject("exception"));
                            } else {
                                b = false;
                            }
                            if (dx.a(jSONObject, "common")) {
                                z = a(jSONObject.getJSONObject("common"));
                            }
                            a aVar2 = new a();
                            aVar2.a = b;
                            aVar2.b = z;
                            aVar.g = aVar2;
                            if (jSONObject.has("sdkupdate")) {
                                jSONObject2 = jSONObject.getJSONObject("sdkupdate");
                                c cVar = new c();
                                a(jSONObject2, cVar);
                                aVar.h = cVar;
                            }
                            if (dx.a(jSONObject, "sdkcoordinate")) {
                                jSONObject2 = jSONObject.getJSONObject("sdkcoordinate");
                                b bVar = new b();
                                a(jSONObject2, bVar);
                                aVar.i = bVar;
                            }
                            if (dx.a(jSONObject, "callamap")) {
                                aVar.e = jSONObject.getJSONObject("callamap");
                            }
                            if (dx.a(jSONObject, "ca")) {
                                aVar.f = jSONObject.getJSONObject("ca");
                            }
                            if (dx.a(jSONObject, "locate")) {
                                aVar.d = jSONObject.getJSONObject("locate");
                            }
                            if (dx.a(jSONObject, "callamappro")) {
                                aVar.c = jSONObject.getJSONObject("callamappro");
                            }
                            if (dx.a(jSONObject, "opflag")) {
                                aVar.b = jSONObject.getJSONObject("opflag");
                            }
                            if (dx.a(jSONObject, "amappushflag")) {
                                aVar.a = jSONObject.getJSONObject("amappushflag");
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                eb.a(th, "ConfigManager", "loadConfig");
            }
        }
        return aVar;
    }

    @Deprecated
    public static a a(Context context, dv dvVar, String str) {
        try {
            return a(new fq().a(new b(context, dvVar, str)));
        } catch (Throwable th) {
            eb.a(th, "ConfigManager", "loadConfig");
            return new a();
        }
    }

    private static boolean a(String str) {
        if (str != null && str.equals("1")) {
            return true;
        }
        return false;
    }

    public static String a(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject == null) {
            return "";
        }
        String str2 = "";
        if (!jSONObject.has(str) || jSONObject.getString(str).equals("[]")) {
            return str2;
        }
        return jSONObject.optString(str);
    }

    private static void a(JSONObject jSONObject, b bVar) {
        if (jSONObject != null) {
            try {
                String a = a(jSONObject, n.K);
                String a2 = a(jSONObject, "url");
                bVar.b = a;
                bVar.a = a2;
            } catch (Throwable th) {
                eb.a(th, "ConfigManager", "parseSDKCoordinate");
            }
        }
    }

    private static void a(JSONObject jSONObject, c cVar) {
        if (jSONObject != null) {
            try {
                Object a = a(jSONObject, n.K);
                Object a2 = a(jSONObject, "url");
                Object a3 = a(jSONObject, "sdkversion");
                if (!TextUtils.isEmpty(a) && !TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3)) {
                    cVar.a = a2;
                    cVar.b = a;
                    cVar.c = a3;
                }
            } catch (Throwable th) {
                eb.a(th, "ConfigManager", "parseSDKUpdate");
            }
        }
    }

    private static boolean a(JSONObject jSONObject) {
        boolean z = false;
        if (jSONObject != null) {
            try {
                z = a(a(jSONObject.getJSONObject("commoninfo"), "com_isupload"));
            } catch (Throwable th) {
                eb.a(th, "ConfigManager", "parseCommon");
            }
        }
        return z;
    }

    private static boolean b(JSONObject jSONObject) {
        boolean z = false;
        if (jSONObject != null) {
            try {
                z = a(a(jSONObject.getJSONObject("exceptinfo"), "ex_isupload"));
            } catch (Throwable th) {
                eb.a(th, "ConfigManager", "parseException");
            }
        }
        return z;
    }
}
